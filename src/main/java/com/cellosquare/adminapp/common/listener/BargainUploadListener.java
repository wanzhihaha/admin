package main.java.com.cellosquare.adminapp.common.listener;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.cellosquare.adminapp.admin.bargain.entity.Bargain;
import com.cellosquare.adminapp.admin.bargain.vo.BargainUploadVO;
import com.cellosquare.adminapp.admin.bargainPrice.entity.BargainPrice;
import com.cellosquare.adminapp.admin.bargainProduct.entity.BargainProduct;
import com.cellosquare.adminapp.common.ex.UploadException;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Slf4j
public class BargainUploadListener implements ReadListener<com.cellosquare.adminapp.admin.bargain.vo.BargainUploadVO> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    private List<BargainUploadVO> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private UploadException uploadException;
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private com.cellosquare.adminapp.admin.bargain.service.impl.BargainServiceImpl bargainService;

    private com.cellosquare.adminapp.admin.seo.service.impl.AdminSeoServiceImpl adminSeoService;

    public BargainUploadListener(com.cellosquare.adminapp.admin.bargain.service.impl.BargainServiceImpl bargainService, com.cellosquare.adminapp.admin.seo.service.impl.AdminSeoServiceImpl adminSeoService, UploadException uploadException) {
        this.bargainService = bargainService;
        this.adminSeoService = adminSeoService;
        this.uploadException = uploadException;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(com.cellosquare.adminapp.admin.bargain.vo.BargainUploadVO data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        cachedDataList.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        if (CollectionUtil.isEmpty(cachedDataList)) {
            return;
        }
        uploadException.setMessage("");
        List<String> products = cachedDataList.stream().map(com.cellosquare.adminapp.admin.bargain.vo.BargainUploadVO::getProductType).collect(Collectors.toList());
        List<BargainProduct> productsDb = bargainService.getProducts(products);
        Map<String, BargainProduct> map = productsDb.stream().collect(Collectors.toMap(com.cellosquare.adminapp.admin.bargainProduct.entity.BargainProduct::getProductName, Function.identity()));
        List<BargainUploadVO> removes = Lists.newArrayList();
        for (com.cellosquare.adminapp.admin.bargain.vo.BargainUploadVO bargainUploadVO : cachedDataList) {
            com.cellosquare.adminapp.admin.bargainProduct.entity.BargainProduct bargainProduct = map.get(bargainUploadVO.getProductType());
            if (Objects.isNull(bargainProduct)) {
                removes.add(bargainUploadVO);
                uploadException.setMessage("\n产品类型("+bargainUploadVO.getProductType()+")不存在,请重新下载模板");
                continue;
            }
            bargainUploadVO.setProductId(bargainProduct.getId());
            bargainUploadVO.setMultiFlag(bargainProduct.getMultiFlag());
        }
        cachedDataList.removeAll(removes);
        filterCachedData();
        getSeoVO(cachedDataList);
        List<Bargain> upload = cachedDataList.stream().map(com.cellosquare.adminapp.admin.bargain.conver.BargainConver.INSTANCT::upload).collect(Collectors.toList());
        bargainService.saveBatch(upload);
        uploadException.setMessage("\n成功条数:"+upload.size()+"\n"+uploadException.getMessage());
        Map<String, BargainUploadVO> uploadVOMap = cachedDataList.stream().collect(Collectors.toMap(com.cellosquare.adminapp.admin.bargain.vo.BargainUploadVO::getMetaSeqNo, Function.identity()));
        List<BargainPrice> inserts = upload.stream().map(r -> {
            ArrayList<BargainPrice> dbs = Lists.newArrayList();
            com.cellosquare.adminapp.admin.bargain.vo.BargainUploadVO bargainUploadVO = uploadVOMap.get(r.getMetaSeqNo().toString());
            if (StrUtil.isNotEmpty(bargainUploadVO.getPrice1()) && StrUtil.isNotEmpty(bargainUploadVO.getUnit1())) {
                com.cellosquare.adminapp.admin.bargainPrice.entity.BargainPrice bargainPrice = new com.cellosquare.adminapp.admin.bargainPrice.entity.BargainPrice(null, bargainUploadVO.getUnit1(), bargainUploadVO.getPrice1(), r.getId());
                dbs.add(bargainPrice);
            }
            if (Objects.equals(bargainUploadVO.getMultiFlag(),"Y")) {
                if (StrUtil.isNotEmpty(bargainUploadVO.getPrice2()) && StrUtil.isNotEmpty(bargainUploadVO.getUnit2())) {
                    com.cellosquare.adminapp.admin.bargainPrice.entity.BargainPrice bargainPrice = new com.cellosquare.adminapp.admin.bargainPrice.entity.BargainPrice(null, bargainUploadVO.getUnit2(), bargainUploadVO.getPrice2(), r.getId());
                    dbs.add(bargainPrice);
                }
                if (StrUtil.isNotEmpty(bargainUploadVO.getPrice3()) && StrUtil.isNotEmpty(bargainUploadVO.getUnit3())) {
                    com.cellosquare.adminapp.admin.bargainPrice.entity.BargainPrice bargainPrice = new com.cellosquare.adminapp.admin.bargainPrice.entity.BargainPrice(null, bargainUploadVO.getUnit3(), bargainUploadVO.getPrice3(), r.getId());
                    dbs.add(bargainPrice);
                }
            }
            return dbs;
        }).flatMap(Collection::stream).collect(Collectors.toList());
        bargainService.savePrices(inserts);
    }



    /**
     * 过滤数据
     */
    private void filterCachedData() {
        int initSize = cachedDataList.size();
        int filterSize = cachedDataList.size();
        cachedDataList = cachedDataList.stream().filter(r -> {
            Set<ConstraintViolation<BargainUploadVO>> validate = validator.validate(r);
            return CollectionUtil.isEmpty(validate);
        }).collect(Collectors.toList());
        filterSize = cachedDataList.size();
        if (initSize>filterSize) {
            uploadException.setMessage(String.format("\n请检查必填数据%s条.",initSize-filterSize)+uploadException.getMessage());
        }
        cachedDataList = cachedDataList.stream().collect(collectingAndThen(toCollection(() ->
                        new TreeSet<>(Comparator.comparing(r -> r.getOrigin() + r.getDestination() + r.getProductType())))
                , ArrayList::new));
        if (CollectionUtil.isEmpty(cachedDataList)) {
            return;
        }
        List<String> originList = cachedDataList.stream().map(com.cellosquare.adminapp.admin.bargain.vo.BargainUploadVO::getOrigin).collect(Collectors.toList());
        List<String> destinationList = cachedDataList.stream().map(com.cellosquare.adminapp.admin.bargain.vo.BargainUploadVO::getDestination).collect(Collectors.toList());
        List<Long> productIdList = cachedDataList.stream().map(com.cellosquare.adminapp.admin.bargain.vo.BargainUploadVO::getProductId).collect(Collectors.toList());
        List<Bargain> db = bargainService.lambdaQuery()
                .in(com.cellosquare.adminapp.admin.bargain.entity.Bargain::getOrigin, originList)
                .in(com.cellosquare.adminapp.admin.bargain.entity.Bargain::getDestination, destinationList)
                .in(com.cellosquare.adminapp.admin.bargain.entity.Bargain::getProductId, productIdList).list();
        initSize = cachedDataList.size();
        if (CollectionUtil.isNotEmpty(db)) {
            Map<String, Bargain> bargainMap = db.stream().collect(Collectors.toMap(r -> r.getOrigin() + r.getDestination() + r.getProductId(), Function.identity(),(o1, o2)->o1));
            cachedDataList = cachedDataList.stream().filter(r->Objects.isNull(bargainMap.get(r.getOrigin()+r.getDestination()+r.getProductId()))).collect(Collectors.toList());
        }
        filterSize = cachedDataList.size();

        if (initSize>filterSize) {
            uploadException.setMessage(String.format("\n存在重复数据%s条.",initSize-filterSize)+uploadException.getMessage());
        }
    }

    /**
     * 获取seo对象
     * @param cachedDataList
     */
    private  void getSeoVO(List<BargainUploadVO> cachedDataList) {
        for (com.cellosquare.adminapp.admin.bargain.vo.BargainUploadVO bargainUploadVO : cachedDataList) {
            com.cellosquare.adminapp.admin.bargain.vo.BargainVO bargainVO = new com.cellosquare.adminapp.admin.bargain.vo.BargainVO();
            bargainVO.setMetaTitleNm("");
            adminSeoService.doSeoWriteV2(null, null, null, bargainVO);
            bargainUploadVO.setMetaSeqNo(bargainVO.getMetaSeqNo());
        }
    }
}

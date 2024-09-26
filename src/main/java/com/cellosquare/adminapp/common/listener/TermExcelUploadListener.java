package main.java.com.cellosquare.adminapp.common.listener;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.admin.terminology.entity.Terminology;
import com.cellosquare.adminapp.admin.terminology.vo.TerminologyUploadVO;
import com.cellosquare.adminapp.common.util.SeoUtils;
import com.cellosquare.adminapp.common.vo.BaseSeoParam;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TermExcelUploadListener implements ReadListener<com.cellosquare.adminapp.admin.terminology.vo.TerminologyUploadVO> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    private List<TerminologyUploadVO> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private com.cellosquare.adminapp.admin.terminology.service.impl.TerminologyServiceImpl terminologyService;

    private com.cellosquare.adminapp.admin.seo.service.impl.AdminSeoServiceImpl adminSeoService;

    public TermExcelUploadListener(com.cellosquare.adminapp.admin.terminology.service.impl.TerminologyServiceImpl terminologyService, com.cellosquare.adminapp.admin.seo.service.impl.AdminSeoServiceImpl adminSeoService) {
        this.terminologyService = terminologyService;
        this.adminSeoService = adminSeoService;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(com.cellosquare.adminapp.admin.terminology.vo.TerminologyUploadVO data, AnalysisContext context) {
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
        List<String> names = cachedDataList.stream().map(com.cellosquare.adminapp.admin.terminology.vo.TerminologyUploadVO::getTerminologyName).map(String::trim).collect(Collectors.toList());
        getSeoVO(cachedDataList);
        List<Terminology> list = terminologyService.lambdaQuery().in(com.cellosquare.adminapp.admin.terminology.entity.Terminology::getTerminologyName, names).list();
        List<String> dbName = list.stream().map(com.cellosquare.adminapp.admin.terminology.entity.Terminology::getTerminologyName).collect(Collectors.toList());
        terminologyService.saveBatch(com.cellosquare.adminapp.admin.terminology.conver.TerminologyConver.INSTANCT.upload(cachedDataList)
                .stream()
                .filter(r->!dbName.contains(r.getTerminologyName())&& StrUtil.isNotEmpty(r.getTerminologyName()))
                .collect(Collectors.toList()));
        log.info("存储数据库成功！");
    }

    /**
     * 获取seo对象
     * @param cachedDataList
     */
    private  void getSeoVO(List<TerminologyUploadVO> cachedDataList) {
        //全部术语名
        List<String> names = cachedDataList.stream().map(com.cellosquare.adminapp.admin.terminology.vo.TerminologyUploadVO::getTerminologyName).collect(Collectors.toList());
        LambdaQueryChainWrapper<com.cellosquare.adminapp.admin.terminology.entity.Terminology> lambdaQuery = terminologyService.lambdaQuery();
        for (String name : names) {
            lambdaQuery.likeLeft(com.cellosquare.adminapp.admin.terminology.entity.Terminology::getTerminologyName,name);
            if (!name.equals(CollectionUtil.getLast(names))) {
                lambdaQuery.or();
            }
        }
//        List<Terminology> list = lambdaQuery.list();
        List<Terminology> list = terminologyService.lambdaQuery().eq(com.cellosquare.adminapp.admin.terminology.entity.Terminology::getUseYn, com.cellosquare.adminapp.common.constant.UseEnum.USE.getCode()).list();
        List<String> ter_nams = list.stream().map(com.cellosquare.adminapp.admin.terminology.entity.Terminology::getTerminologyName).collect(Collectors.toList());
        List<AdminSeoVO> collect = cachedDataList.stream().map(r -> {
            //分词匹配
            List<String> terminologyTags = terminologyService.getListCY(r.getTerminologyName(), ter_nams);
           // List<String> terminologyTags = terminologyService.relateTerminologys(r.getTerminologyName());
           // List<String> terminologyTags = list.stream().filter(s -> s.getTerminologyName().contains(r.getTerminologyName())).map(Terminology::getTerminologyName).collect(Collectors.toList());
            BaseSeoParam baseSeoParam = new BaseSeoParam(com.cellosquare.adminapp.common.constant.SeoModuleEnum.TERMINOLOGY.getCode(), r.getTerminologyName(), r.getDescription(),
                    null, terminologyTags, new com.cellosquare.adminapp.admin.terminology.vo.TerminologyVO());
            SeoUtils.setSeoMsg(baseSeoParam);

            int i = adminSeoService.doSeoWriteV2(null, null, null, baseSeoParam.getAdminSeoVO());
            r.setMetaSeqNo(baseSeoParam.getAdminSeoVO().getMetaSeqNo());
            return baseSeoParam.getAdminSeoVO();
        }).collect(Collectors.toList());
    }
}

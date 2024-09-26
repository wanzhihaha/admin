package com.cellosquare.adminapp.admin.bargain.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.bargain.conver.BargainConver;
import com.cellosquare.adminapp.admin.bargain.entity.Bargain;
import com.cellosquare.adminapp.admin.bargain.mapper.BargainMapper;
import com.cellosquare.adminapp.admin.bargain.service.IBargainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cellosquare.adminapp.admin.bargain.vo.BargainExportVO;
import com.cellosquare.adminapp.admin.bargain.vo.BargainVO;
import com.cellosquare.adminapp.admin.bargainPrice.entity.BargainPrice;
import com.cellosquare.adminapp.admin.bargainPrice.service.impl.BargainPriceServiceImpl;
import com.cellosquare.adminapp.admin.bargainProduct.entity.BargainProduct;
import com.cellosquare.adminapp.admin.bargainProduct.service.impl.BargainProductServiceImpl;
import com.cellosquare.adminapp.admin.seo.service.impl.AdminSeoServiceImpl;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.common.upload.FileUploadManager;
import com.cellosquare.adminapp.common.upload.FileUploadVO;
import com.cellosquare.adminapp.common.vo.GeneralVO;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author walker
 * @since 2023-08-234 08:55:28
 */
@Service
public class BargainServiceImpl extends ServiceImpl<BargainMapper, Bargain> implements IBargainService {
    @Autowired
    private AdminSeoServiceImpl adminSeoService;
    @Autowired
    private BargainPriceServiceImpl bargainPriceService;
    @Autowired
    private BargainProductServiceImpl bargainProductService;
    public void detail(Model model, BargainVO vo) {
        Bargain bargain = getById(vo.getId());
        BargainVO bargainVO = BargainConver.INSTANCT.getBargainVO(bargain);
        List<BargainPrice> list = bargainPriceService.lambdaQuery().eq(BargainPrice::getParentId, bargain.getId()).list();
        bargainVO.setBargainPrices(list);
        BargainProduct one = bargainProductService.lambdaQuery().eq(BargainProduct::getId, bargain.getProductId()).one();
        bargainVO.setProductType(one.getProductName());
        AdminSeoVO adminSeoVO = adminSeoService.getSeoSelect(vo);
        model.addAttribute("adminSeoVO", adminSeoVO);
        model.addAttribute("detail", bargainVO);
        model.addAttribute("vo", bargainVO);
    }

    public void register(HttpServletRequest request, HttpServletResponse response, BargainVO vo, MultipartHttpServletRequest muServletRequest) throws Exception {
        adminSeoService.doSeoWriteV2(request, response, null, vo);
        upLoadPic(response, vo, muServletRequest);
        Bargain bargain = BargainConver.INSTANCT.getBargain(vo);
        save(bargain);
        savePrice(vo,bargain.getId(), request);
    }


    public void savePrice(BargainVO vo, Long id, HttpServletRequest request){
        String[] unit = request.getParameterValues("unit");
        String[] price = request.getParameterValues("price");
        if (ArrayUtil.isEmpty(unit) || ArrayUtil.isEmpty(price)) {
            return;
        }
        ArrayList<BargainPrice> priceDb = Lists.newArrayList();
        for (int i = 0; i < unit.length; i++) {
            String u = unit[i];
            String p = price[i];
            BargainPrice bargainPrice = new BargainPrice();
            bargainPrice.setUnit(u);
            bargainPrice.setPrice(p);
            bargainPrice.setParentId(id);
            priceDb.add(bargainPrice);
        }
        bargainPriceService.saveBatch(priceDb);
    }

    public void upLoadPic(HttpServletResponse response, GeneralVO vo, MultipartHttpServletRequest muServletRequest) throws Exception {
        //图片部分
        if (!vo.getListOrginFile().isEmpty()) {
            if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "listOrginFile")) {
                String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));
                FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "listOrginFile");
                vo.setListImgPath(fileVO.getFilePath());
                vo.setListImgFileNm(fileVO.getFileTempName());
                vo.setListImgOrgFileNm(fileVO.getFileOriginName());
            } else {
                throw new Exception();
            }
        }
        if (Objects.equals(vo.getPcListFileDel(),"Y")) {// 삭제를 체크했을시
            vo.setListImgPath("");
            vo.setListImgFileNm("");
            vo.setListImgOrgFileNm("");
        }
    }

    public void updateForm(Model model, BargainVO vo) {
        Bargain bargain = getById(vo.getId());
        BargainVO bargainVO = BargainConver.INSTANCT.getBargainVO(bargain);
        List<BargainPrice> list = bargainPriceService.lambdaQuery().eq(BargainPrice::getParentId, bargain.getId()).list();
        bargainVO.setBargainPrices(list);
        AdminSeoVO adminSeoVO = adminSeoService.getSeoSelect(bargainVO);
        model.addAttribute("adminSeoVO", adminSeoVO);
        model.addAttribute("detail", bargainVO);
        model.addAttribute("contIU", "U");
    }

    public void doUpdate(HttpServletRequest request, HttpServletResponse response, BargainVO vo, MultipartHttpServletRequest muServletRequest) throws Exception {
//        vaildatePrice(vo);
        upLoadPic(response, vo, muServletRequest);
        lambdaUpdate().eq(Bargain::getId,vo.getId())
                .update(BargainConver.INSTANCT.getBargain(vo));
        List<BargainPrice> list = bargainPriceService.lambdaQuery().eq(BargainPrice::getParentId, vo.getId()).list();
        if (CollectionUtil.isNotEmpty(list)) {
            List<Long> removeIds = list.stream().map(BargainPrice::getId).collect(Collectors.toList());
            bargainPriceService.removeBatchByIds(removeIds);
        }
        savePrice(vo,vo.getId(),request);
    }

    public void doDelete(Bargain vo) {
        removeById(vo.getId());
    }

    public List<BargainProduct>  getProducts(List<String> products) {
        return bargainProductService.lambdaQuery().in(BargainProduct::getProductName, products).list();
    }

    public void savePrices(List<BargainPrice> inserts) {
        bargainPriceService.saveBatch(inserts);
    }

    public List<BargainExportVO> exportTerm(String searchValue, Long productId) {
        List<Bargain> list = this.lambdaQuery()
                .and(ObjectUtil.isNotNull(productId)&&!Objects.equals(productId,-1L), r->r.eq(Bargain::getProductId,productId))
                .and(StrUtil.isNotEmpty(searchValue)
                        ,r->r.like(Bargain::getOrigin,searchValue).or().like(Bargain::getDestination,searchValue))
                .orderByDesc(Bargain::getId)
                .list();
        List<BargainExportVO> export = BargainConver.INSTANCT.export(list);
        if (CollectionUtil.isNotEmpty(export)) {
            List<Long> productIds = export.stream().map(BargainExportVO::getProductId).collect(Collectors.toList());
            List<BargainProduct> products = bargainProductService.lambdaQuery().in(BargainProduct::getId, productIds).list();
            if (CollectionUtil.isNotEmpty(products)) {
                Map<Long, BargainProduct> productMap = products.stream().collect(Collectors.toMap(BargainProduct::getId, Function.identity()));
                for (BargainExportVO bargainExportVO : export) {
                    BargainProduct bargainProduct = productMap.get(bargainExportVO.getProductId());
                    bargainExportVO.setProductType(bargainProduct.getProductName());
                }
            }
            List<Long> ids = export.stream().map(BargainExportVO::getId).collect(Collectors.toList());
            List<BargainPrice> priceList = bargainPriceService.lambdaQuery().in(BargainPrice::getParentId, ids).list();
            if (CollectionUtil.isNotEmpty(priceList)) {
                Map<Long, List<BargainPrice>> priceMap = priceList.stream().collect(Collectors.groupingBy(BargainPrice::getParentId));
                for (BargainExportVO bargainExportVO : export) {
                    List<BargainPrice> bargainPrices = priceMap.get(bargainExportVO.getId());
                    if (CollectionUtil.isEmpty(bargainPrices)) {
                        continue;
                    }
                    String price = bargainPrices.stream().map(r -> "单位:" + r.getUnit() + " 价格:" + r.getPrice()).collect(Collectors.joining("  \n"));
                    bargainExportVO.setCurrency(price);
                }
            }
        }
        return export;
    }
}

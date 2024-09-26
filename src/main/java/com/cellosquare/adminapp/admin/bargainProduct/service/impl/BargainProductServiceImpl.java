package com.cellosquare.adminapp.admin.bargainProduct.service.impl;

import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;

import com.cellosquare.adminapp.admin.bargainProduct.conver.BargainProductConver;
import com.cellosquare.adminapp.admin.bargainProduct.entity.BargainProduct;
import com.cellosquare.adminapp.admin.bargainProduct.mapper.BargainProductMapper;
import com.cellosquare.adminapp.admin.bargainProduct.service.IBargainProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cellosquare.adminapp.admin.bargainProduct.vo.BargainProductVO;
import com.cellosquare.adminapp.admin.seo.service.impl.AdminSeoServiceImpl;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.common.constant.SeoModuleEnum;
import com.cellosquare.adminapp.common.enums.OperationEnum;
import com.cellosquare.adminapp.common.upload.FileUploadManager;
import com.cellosquare.adminapp.common.upload.FileUploadVO;
import com.cellosquare.adminapp.common.util.SeoUtils;
import com.cellosquare.adminapp.common.vo.BaseSeoParam;
import com.cellosquare.adminapp.common.vo.GeneralVO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author walker
 * @since 2023-08-230 10:35:01
 */
@Service
public class BargainProductServiceImpl extends ServiceImpl<BargainProductMapper, BargainProduct> implements IBargainProductService {
    @Autowired
    private AdminSeoServiceImpl adminSeoService;
    /**
     * 详情
     * @param model
     * @param vo
     */
    public void detail(Model model, BargainProductVO vo) {
        BargainProduct product = getById(vo.getId());
        AdminSeoVO adminSeoVO = adminSeoService.getSeoSelect(vo);
        model.addAttribute("adminSeoVO", adminSeoVO);
        model.addAttribute("detail", product);
        model.addAttribute("vo", product);
    }

    /**
     * 新增
     * @param request
     * @param response
     * @param vo
     * @param muServletRequest
     */
    public void register(HttpServletRequest request, HttpServletResponse response, BargainProductVO vo, MultipartHttpServletRequest muServletRequest) throws Exception {
        verifyName(vo);
        adminSeoService.doSeoWriteV2(request, response, null, vo);
        upLoadPic(response, vo, muServletRequest);
        save(BargainProductConver.INSTANCT.getBargainProduct(vo));
    }

    /**
     * 校验重复产品名
     * @param vo
     */
    private void verifyName(BargainProductVO vo) {
        Long count = lambdaQuery()
                .eq(BargainProduct::getProductName, vo.getProductName())
                .ne(Objects.nonNull(vo.getId()), BargainProduct::getId, vo.getId()).count();
        if (count>0) {
            throw new RuntimeException("产品名重复");
        }
    }

    /**
     * 上传图片
     *
     * @param response
     * @param vo
     * @param muServletRequest
     * @throws Exception
     */
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
        if (!vo.getMobileListOrginFile().isEmpty()) {
            if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileListOrginFile")) {
                String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));
                FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileListOrginFile");
                vo.setMobileListImgPath(fileVO.getFilePath());
                vo.setMobileListImgFileNm(fileVO.getFileTempName());
                vo.setMobileListImgOrgFileNm(fileVO.getFileOriginName());
            } else {
                throw new Exception();
            }
        }
    }

    public void updateForm(Model model, BargainProductVO vo) {
        BargainProduct bargainProduct = getById(vo.getId());
        vo.setMetaSeqNo(bargainProduct.getMetaSeqNo()+"");
        AdminSeoVO adminSeoVO = adminSeoService.getSeoSelect(vo);
        model.addAttribute("adminSeoVO", adminSeoVO);
        model.addAttribute("detail", bargainProduct);
        model.addAttribute("contIU", "U");
    }


    public void doUpdate(HttpServletRequest request, HttpServletResponse response, BargainProductVO vo, MultipartHttpServletRequest muServletRequest) throws Exception {
        verifyName(vo);
        upLoadPic(response, vo, muServletRequest);
        lambdaUpdate().eq(BargainProduct::getId,vo.getId())
                .update(BargainProductConver.INSTANCT.getBargainProduct(vo));
    }

    public void doDelete(BargainProductVO vo) {
        removeById(vo.getId());
    }
}

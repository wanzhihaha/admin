package com.cellosquare.adminapp.admin.advertising.service.impl;

import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.advertising.conver.AdvertisingConver;
import com.cellosquare.adminapp.admin.advertising.entity.Advertising;
import com.cellosquare.adminapp.admin.advertising.mapper.AdvertisingMapper;
import com.cellosquare.adminapp.admin.advertising.service.IAdvertisingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cellosquare.adminapp.admin.advertising.vo.AdvertisingVO;
import com.cellosquare.adminapp.admin.article.conver.ArticleConver;
import com.cellosquare.adminapp.admin.article.vo.Article;
import com.cellosquare.adminapp.admin.seo.service.AdminSeoService;
import com.cellosquare.adminapp.common.upload.FileUploadManager;
import com.cellosquare.adminapp.common.upload.FileUploadVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author walker
 * @since 2022-12-340 15:25:29
 */
@Service
public class AdvertisingServiceImpl extends ServiceImpl<AdvertisingMapper, Advertising> implements IAdvertisingService {
//    @Autowired
//    private AdminSeoService adminSeoServiceImpl;

    @Override
    public Boolean register(HttpServletRequest request, HttpServletResponse response, AdvertisingVO vo, MultipartHttpServletRequest muServletRequest) {

       // int seoWrite = adminSeoServiceImpl.doSeoWriteV2(request, response, muServletRequest, vo);
//        if (seoWrite <= 0) {
//            return false;
//        }
        this.setImage(response, vo, muServletRequest);
        Advertising advertising = AdvertisingConver.INSTANCT.getAdvertising(vo);
        save(advertising);
        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
        return true;
    }

    @Override
    public Boolean doUpdate(HttpServletRequest request, HttpServletResponse response, AdvertisingVO vo, MultipartHttpServletRequest muServletRequest) {
//        int seoWrite = adminSeoServiceImpl.doSeoUpdatev2(request, response, muServletRequest, vo);
//        if (seoWrite <= 0) {
//            return false;
//        }
        this.setImage(response, vo, muServletRequest);
        Advertising advertising = AdvertisingConver.INSTANCT.getAdvertising(vo);
        updateById(advertising);
        return true;
    }

    private void setImage(HttpServletResponse response, AdvertisingVO vo, MultipartHttpServletRequest muServletRequest) {
        if (Objects.nonNull(vo.getPcListOrginFile()) && !vo.getPcListOrginFile().isEmpty()) {
            if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListOrginFile")) {
                String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));
                FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcListOrginFile");
                vo.setAdPicUrl(fileVO.getFilePath());
                vo.setAdPicName(fileVO.getFileTempName());
            }
        }
        if (Objects.nonNull(vo.getMobileListOrginFile()) && !vo.getMobileListOrginFile().isEmpty()) {
            if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileListOrginFile")) {
                String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));
                FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileListOrginFile");
                vo.setAdMobilePicUrl(fileVO.getFilePath());
                vo.setAdMobilePicName(fileVO.getFileTempName());
            }
        }
    }

}

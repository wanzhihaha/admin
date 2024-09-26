package com.cellosquare.adminapp.admin.seo.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.seo.mapper.AdminSeoMapper;
import com.cellosquare.adminapp.admin.seo.service.AdminSeoService;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.common.upload.FileUploadManager;
import com.cellosquare.adminapp.common.upload.FileUploadVO;

import java.util.List;

@Service
public class AdminSeoServiceImpl implements AdminSeoService {

    @Autowired
    private AdminSeoMapper adminSeoMapper;

    @Override
    public int doSeoWrite(HttpServletRequest request, HttpServletResponse response,
                          MultipartHttpServletRequest muServletRequest, AdminSeoVO vo) {

        int result = 0;
        if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "ogFile")) {
            String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathMeta")));

            FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "ogFile");

            vo.setOgImgPath(fileVO.getFilePath());
            vo.setOgImgFileNm(fileVO.getFileTempName());
            vo.setOgImgSize(String.valueOf(fileVO.getFileSize()));
            vo.setOgImgOrgFileNm(fileVO.getFileOriginName());


            result = adminSeoMapper.doSeoWrite(vo);

        }
        return result;
    }


    @Override
    public AdminSeoVO getSeoSelect(AdminSeoVO vo) {
        return adminSeoMapper.getSeoSelect(vo);
    }

    @Override
    public int doSeoDelete(AdminSeoVO vo) {
        return adminSeoMapper.doSeoDelete(vo);
    }

    @Override
    public int doSeoUpdate(HttpServletRequest request, HttpServletResponse response,
                           MultipartHttpServletRequest muServletRequest, AdminSeoVO vo) {
        int result = 0;

        String fileDel = StringUtil.nvl(vo.getOgFileDel(), "N");

        // 첨부파일을 선택 했을시
        if (!vo.getOgFile().isEmpty()) {
            if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "ogFile")) {
                String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathMeta")));

                FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "ogFile");

                vo.setOgImgPath(fileVO.getFilePath());
                vo.setOgImgFileNm(fileVO.getFileTempName());
                vo.setOgImgSize(String.valueOf(fileVO.getFileSize()));
                vo.setOgImgOrgFileNm(fileVO.getFileOriginName());
            }
        } else {    // 파일을 선택안했을시
            if (fileDel.equals("Y")) {// 삭제를 체크했을시
                vo.setOgImgPath("");
                vo.setOgImgFileNm("");
                vo.setOgImgSize("0");
                vo.setOgImgOrgFileNm("");
            } else {
                // 디비에서 seo값 구하기
                AdminSeoVO adminSeoVO = getSeoSelect(vo);

                vo.setOgImgPath(adminSeoVO.getOgImgPath());
                vo.setOgImgFileNm(adminSeoVO.getOgImgFileNm());
                vo.setOgImgSize(String.valueOf(adminSeoVO.getOgImgSize()));
                vo.setOgImgOrgFileNm(adminSeoVO.getOgImgOrgFileNm());

            }
        }
        result = adminSeoMapper.doSeoUpdate(vo);
        return result;
    }

    @Override
    public int doSeoWriteV2(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest, AdminSeoVO vo) {
        return adminSeoMapper.doSeoWrite(vo);
    }

    @Override
    public int doSeoUpdatev2(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest, AdminSeoVO vo) {
        if (StringUtils.isEmpty(vo.getMetaSeqNo())) return 0;
        return adminSeoMapper.doSeoUpdate(vo);
    }

    @Override
    public int updateAllAddMesg() {
        List<AdminSeoVO> adminSeoVOS = adminSeoMapper.selectAll("-Cello Square");
        adminSeoVOS.forEach(adminSeoVO -> {
            if (adminSeoVO.getMetaTitleNm().contains("-Cello Square")) {
                String replace = adminSeoVO.getMetaTitleNm().replace("-Cello Square", "-琴路捷Cello Square");
                adminSeoMapper.updateTitle(replace, Integer.valueOf(adminSeoVO.getMetaSeqNo()));
            }
        });
        return 0;
    }


}

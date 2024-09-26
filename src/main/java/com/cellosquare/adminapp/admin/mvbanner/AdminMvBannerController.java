package com.cellosquare.adminapp.admin.mvbanner;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.hutool.core.util.StrUtil;
import com.cellosquare.adminapp.admin.goods.vo.AdminGoodsVO;
import com.cellosquare.adminapp.common.adminAnnotion.CleanCacheAnnotion;
import com.cellosquare.adminapp.common.enums.CarouselTypeEnum;
import org.apache.commons.io.FilenameUtils;
import org.owasp.esapi.SafeFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.mvbanner.service.AdminMvBannerService;
import com.cellosquare.adminapp.admin.mvbanner.vo.AdminMvBannerVO;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.upload.FileUploadManager;
import com.cellosquare.adminapp.common.upload.FileUploadVO;
import com.cellosquare.adminapp.common.util.FileDownUtil;

@Controller
public class AdminMvBannerController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminMvBannerService adminMvBannerServiceImpl;

    /**
     * 배너 리스트
     *
     * @param request
     * @param response
     * @param model
     * @param vo
     * @return
     */
    @GetMapping(value = {"/celloSquareAdmin/mvBanner/list.do", "/celloSquareAdmin/middle/list.do"})
    public String list(HttpServletRequest request, HttpServletResponse response, Model model,
                       @ModelAttribute("vo") AdminMvBannerVO vo) {

        logger.debug("AdminBannerController :: list");
        AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
        vo.setLangCd(sessionForm.getLangCd());

        int totalCount = adminMvBannerServiceImpl.getTotalCount(vo);
        List<AdminMvBannerVO> list = new ArrayList<AdminMvBannerVO>();

        if (totalCount > 0) {
            list = adminMvBannerServiceImpl.getList(vo);
            // DB에서 TimeStamp형태로 들어와서 값이없을경우 0001-01-01로 값을 받기때문에 다시 없얘주는 작업
            // 다른방법은 쿼리문에서 toChar에서 TimeStamp를 없얘고 String에서 날짜 사이에 -값을 추가해주는 로직을 짜야됨
            for (int i = 0; i < list.size(); i++) {
                String stDate = StringUtil.nvl(list.get(i).getBannerOpenStatDate(), "");
                if (stDate.equals("0001-01-01")) {
                    list.get(i).setBannerOpenStatDate("");
                }
                String endDate = StringUtil.nvl(list.get(i).getBannerOpenEndDate(), "");
                if (endDate.equals("0001-01-01")) {
                    list.get(i).setBannerOpenEndDate("");
                }
            }
        }
        list.forEach(adminMvBannerVO -> adminMvBannerVO.setCarouselTypeName(CarouselTypeEnum.getEnumByCode(adminMvBannerVO.getCarouselType()).getDesc()));
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("list", list);

        return "admin/basic/mvbanner/list";
    }

    /**
     * 배너 등록폼
     *
     * @param request
     * @param response
     * @param vo
     * @param model
     * @return
     */
    @PostMapping(value = {"/celloSquareAdmin/mvBanner/registerForm.do", "/celloSquareAdmin/middle/registerForm.do"})
    public String writeForm(HttpServletRequest request, HttpServletResponse response, Model model,
                            @ModelAttribute("vo") AdminMvBannerVO vo) {

        logger.debug("AdminBannerController :: registerForm");
        HttpSession session = request.getSession();
        session.removeAttribute("detail");
        session.removeAttribute("vo");
        model.addAttribute("contIU", "I");
        model.addAttribute("picType", vo.getPicType());
        return "admin/basic/mvbanner/registerForm";
    }

    /**
     * 배너 등록
     *
     * @param request
     * @param resonse
     * @param muServletRequest
     * @param model
     * @param vo
     * @return
     */
    @PostMapping(value = {"/celloSquareAdmin/mvBanner/register.do", "/celloSquareAdmin/middle/register.do"})
    @CleanCacheAnnotion
    public String regist(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest
            , Model model, @ModelAttribute("vo") AdminMvBannerVO vo) throws Exception {
        //-값 제거
        String startDate = vo.getBannerOpenStatDate().replaceAll("-", "");
        String endDate = vo.getBannerOpenEndDate().replaceAll("-", "");

        vo.setBannerOpenStatDate(startDate);
        vo.setBannerOpenEndDate(endDate);

        AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
        vo.setInsPersonId(sessionForm.getAdminId());
        vo.setUpdPersonId(sessionForm.getAdminId());
        vo.setLangCd(sessionForm.getLangCd());

        // 컨텐츠 저장
        if (!vo.getPcListOrginFile().isEmpty()) {
            if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListOrginFile")) {
                String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathBanner")));

                FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcListOrginFile");

                vo.setPcListImgPath(fileVO.getFilePath());
                vo.setPcListImgFileNm(fileVO.getFileTempName());
                vo.setPcListImgSize(String.valueOf(fileVO.getFileSize()));
                vo.setPcListImgOrgFileNm(fileVO.getFileOriginName());
            } else {
                throw new Exception();
            }
        }
        if (!vo.getMobileListOrginFile().isEmpty()) {
            if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileListOrginFile")) {
                String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathBanner")));

                FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileListOrginFile");

                vo.setMobileListImgPath(fileVO.getFilePath());
                vo.setMobileListImgFileNm(fileVO.getFileTempName());
                vo.setMobileListImgSize(String.valueOf(fileVO.getFileSize()));
                vo.setMobileListImgOrgFileNm(fileVO.getFileOriginName());
            } else {
                throw new Exception();
            }
        }
        adminMvBannerServiceImpl.regist(vo);

        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
        return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/middle/list.do?picType=" + vo.getPicType();
    }


    /**
     * 이벤트 상세보기
     *
     * @param request
     * @param response
     * @param vo
     * @param model
     * @return
     */
    @PostMapping(value = {"/celloSquareAdmin/mvBanner/detail.do", "/celloSquareAdmin/middle/detail.do"})
    public String detail(HttpServletRequest request, HttpServletResponse response, Model model,
                         @ModelAttribute("vo") AdminMvBannerVO vo) {

        logger.debug("AdminBannerController :: detail");

        AdminMvBannerVO detailVO = adminMvBannerServiceImpl.getDetail(vo);
        detailVO.setCarouselTypeName(CarouselTypeEnum.getEnumByCode(detailVO.getCarouselType()).getDesc());
        String stDate = StringUtil.nvl(detailVO.getBannerOpenStatDate(), "");
        if (stDate.equals("0001-01-01")) {
            detailVO.setBannerOpenStatDate("");
        }
        String endDate = StringUtil.nvl(detailVO.getBannerOpenEndDate(), "");
        if (endDate.equals("0001-01-01")) {
            detailVO.setBannerOpenEndDate("");
        }
        if (StrUtil.isNotEmpty(detailVO.getPicType())) {
            vo.setPicType(detailVO.getPicType());
        }
        HttpSession session = request.getSession();
        session.setAttribute("detail", detailVO);
        session.setAttribute("vo", vo);
        model.addAttribute("detail", session.getAttribute("detail"));
        model.addAttribute("vo", session.getAttribute("vo"));
        return "admin/basic/mvbanner/detail";
    }

    @RequestMapping("/celloSquareAdmin/mvBanner/detail.do")
    public String goDetail(@ModelAttribute("detail") AdminGoodsVO detail, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        model.addAttribute("detail", session.getAttribute("detail"));
        model.addAttribute("vo", session.getAttribute("vo"));
        return "admin/basic/mvbanner/detail";
    }

    /**
     * 배너 수정폼
     *
     * @param request
     * @param response
     * @param vo
     * @param model
     * @return
     */
    @PostMapping(value = {"/celloSquareAdmin/mvBanner/updateForm.do", "/celloSquareAdmin/middle/updateForm.do"})
    public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
                             @ModelAttribute("vo") AdminMvBannerVO vo) {

        logger.debug("AdminBannerController :: updateForm");

        AdminMvBannerVO detailVO = adminMvBannerServiceImpl.getDetail(vo);

        try {
            String stDate = StringUtil.nvl(detailVO.getBannerOpenStatDate(), "");
            if (stDate.equals("0001-01-01")) {
                detailVO.setBannerOpenStatDate("");
            }
            String endDate = StringUtil.nvl(detailVO.getBannerOpenEndDate(), "");
            if (endDate.equals("0001-01-01")) {
                detailVO.setBannerOpenEndDate("");
            }
        } catch (Exception e) {
//			e.printStackTrace();
        }

        model.addAttribute("detail", detailVO);
        model.addAttribute("contIU", "U");
        return "admin/basic/mvbanner/registerForm";
    }


    /**
     * 수정하기
     *
     * @param request
     * @param response
     * @param muServletRequest
     * @param model
     * @param vo
     * @return
     */
    @PostMapping(value = {"/celloSquareAdmin/mvBanner/update.do", "/celloSquareAdmin/middle/update.do"})
    @CleanCacheAnnotion
    public String update(HttpServletRequest request, HttpServletResponse response,
                         MultipartHttpServletRequest muServletRequest, Model model,
                         @ModelAttribute("vo") AdminMvBannerVO vo) {

        logger.debug("AdminBannerController :: update");

        try {
            //-값 제거
            String startDate = vo.getBannerOpenStatDate().replaceAll("-", "");
            String endDate = vo.getBannerOpenEndDate().replaceAll("-", "");

            vo.setBannerOpenStatDate(startDate);
            vo.setBannerOpenEndDate(endDate);

            AdminSessionForm adminSessionForm = SessionManager.getAdminSessionForm();
            vo.setUpdPersonId(adminSessionForm.getAdminId());

            String pcListfileDel = StringUtil.nvl(vo.getPcListFileDel(), "N");
            String mobileListfileDel = StringUtil.nvl(vo.getMobileListFileDel(), "N");

            AdminMvBannerVO adminBannerVO = adminMvBannerServiceImpl.getDetail(vo);

            // pcList 첨부파일을 선택 했을시
            if (!vo.getPcListOrginFile().isEmpty()) {
                if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListOrginFile")) {
                    String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathBanner")));

                    FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcListOrginFile");

                    vo.setPcListImgPath(fileVO.getFilePath());
                    vo.setPcListImgFileNm(fileVO.getFileTempName());
                    vo.setPcListImgSize(String.valueOf(fileVO.getFileSize()));
                    vo.setPcListImgOrgFileNm(fileVO.getFileOriginName());
                } else {
                    throw new Exception();
                }
            } else {   // 파일을 선택안했을시
                if (pcListfileDel.equals("Y")) {// 삭제를 체크했을시
                    vo.setPcListImgPath("");
                    vo.setPcListImgFileNm("");
                    vo.setPcListImgSize("0");
                    vo.setPcListImgOrgFileNm("");
                    vo.setPcListImgAlt("");
                } else {
                    // 디비에서 기존 가져오기
                    vo.setPcListImgPath(adminBannerVO.getPcListImgPath());
                    vo.setPcListImgFileNm(adminBannerVO.getPcListImgFileNm());
                    vo.setPcListImgSize(String.valueOf(StringUtil.nvl(adminBannerVO.getPcListImgSize(), "0")));
                    vo.setPcListImgOrgFileNm(adminBannerVO.getPcListImgOrgFileNm());
                }
            }
            // mobileList 첨부파일을 선택 했을시
            if (!vo.getMobileListOrginFile().isEmpty()) {
                if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileListOrginFile")) {
                    String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathBanner")));

                    FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileListOrginFile");

                    vo.setMobileListImgPath(fileVO.getFilePath());
                    vo.setMobileListImgFileNm(fileVO.getFileTempName());
                    vo.setMobileListImgSize(String.valueOf(fileVO.getFileSize()));
                    vo.setMobileListImgOrgFileNm(fileVO.getFileOriginName());
                } else {
                    throw new Exception();
                }
            } else {   // 파일을 선택안했을시
                if (mobileListfileDel.equals("Y")) {// 삭제를 체크했을시
                    vo.setMobileListImgPath("");
                    vo.setMobileListImgFileNm("");
                    vo.setMobileListImgSize("0");
                    vo.setMobileListImgOrgFileNm("");
                    vo.setMobileListImgAlt("");
                } else {
                    vo.setMobileListImgPath(adminBannerVO.getMobileListImgPath());
                    vo.setMobileListImgFileNm(adminBannerVO.getMobileListImgFileNm());
                    vo.setMobileListImgSize(String.valueOf(StringUtil.nvl(adminBannerVO.getMobileListImgSize(), "0")));
                    vo.setMobileListImgOrgFileNm(adminBannerVO.getMobileListImgOrgFileNm());
                }
            }

            adminMvBannerServiceImpl.update(vo);

            Map<String, String> hmParam = new HashMap<String, String>();
            hmParam.put("mvBannerSeqNo", vo.getMvBannerSeqNo());
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("method", "post");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
            model.addAttribute("url", "./detail.do");
            model.addAttribute("hmParam", hmParam);
            model.addAttribute("picType", vo.getPicType());

            return "admin/common/message";
        } catch (Exception e) {
//			e.printStackTrace();
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
            return "admin/basic/mvbanner/registerForm";
        }
    }

    /**
     * 배너 삭제하기
     *
     * @param request
     * @param response
     * @param vo
     * @param model
     * @return
     */
    @PostMapping(value = {"/celloSquareAdmin/mvBanner/doDelete.do", "/celloSquareAdmin/middle/doDelete.do"})
    @CleanCacheAnnotion
    public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
                         @ModelAttribute("vo") AdminMvBannerVO vo) {

        logger.debug("AdminBannerController :: delete");
        try {
            adminMvBannerServiceImpl.delete(vo);

            Map<String, String> hmParam = new HashMap<String, String>();
            hmParam.put("evtSeqNo", vo.getMvBannerSeqNo());
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
            if (Objects.equals(vo.getPicType(), "2")) {
                model.addAttribute("url", "/celloSquareAdmin/middle/list.do");
                hmParam.put("picType", "2");
            } else {
                model.addAttribute("url", "/celloSquareAdmin/middle/list.do");
                hmParam.put("picType", "1");
            }

            model.addAttribute("hmParam", hmParam);

            return "admin/common/message";

        } catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/middle/detail.do";
        }
    }

    /**
     * 이미지 썸네일
     *
     * @param request
     * @param response
     * @param vo
     * @param model
     * @param adminSeoVO
     * @return
     */
    @ResponseBody
    @GetMapping("/celloSquareAdmin/mvBannerImgView.do")
    public String bannerImgView(HttpServletRequest request, HttpServletResponse response, Model model,
                                @ModelAttribute("vo") AdminMvBannerVO vo) {

        logger.debug("AdminBannerController :: imageView");
        AdminMvBannerVO adminBannerVO = adminMvBannerServiceImpl.getDetail(vo);

        try {
            if (adminBannerVO != null) {
                FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
                if (vo.getImgKinds().equals("pcList")) {
                    SafeFile pcListFile = new SafeFile(adminBannerVO.getPcListImgPath(), FilenameUtils.getName(adminBannerVO.getPcListImgFileNm()));
                    if (pcListFile.isFile()) {
                        fileDownLoadManager.fileFlush(pcListFile, adminBannerVO.getPcListImgOrgFileNm());
                    }
                } else if (vo.getImgKinds().equals("mobileList")) {
                    SafeFile mobileListfile = new SafeFile(adminBannerVO.getMobileListImgPath(), FilenameUtils.getName(adminBannerVO.getMobileListImgFileNm()));
                    if (mobileListfile.isFile()) {
                        fileDownLoadManager.fileFlush(mobileListfile, adminBannerVO.getMobileListImgOrgFileNm());
                    }
                }

            }
        } catch (Exception e) {
            //			e.printStackTrace();
        }

        return null;
    }

    /**
     * 정렬순서 저장
     *
     * @param request
     * @param response
     * @param model
     * @param vo
     * @return
     */
    @PostMapping({"/celloSquareAdmin/mvBanner/doSortOrder.do", "/celloSquareAdmin/middle/doSortOrder.do"})
    public String doSortOrder(HttpServletRequest request, HttpServletResponse response, Model model,
                              @ModelAttribute("vo") AdminMvBannerVO vo) {
        logger.debug("AdminBannerController :: doSortOrder");
        try {
            AdminMvBannerVO adminBannerVO = null;
            for (int i = 0; i < vo.getListBannerSeqNo().length; i++) {
                adminBannerVO = new AdminMvBannerVO();
                adminBannerVO.setMvBannerSeqNo(vo.getListBannerSeqNo()[i]);

                // 문자열이 숫자인지 확인
                String str = "";
                if (vo.getListOrdb().length > 0) {
                    str = vo.getListOrdb()[i];
                }
                if (!StringUtil.nvl(str).equals("")) {
                    boolean isNumeric = str.matches("[+-]?\\d*(\\.\\d+)?");
                    // 숫자라면 true
                    if (isNumeric) {
                        int num = Integer.parseInt(vo.getListOrdb()[i]);
                        if (0 <= num && 1000 > num) {
                            adminBannerVO.setOrdb(vo.getListOrdb()[i]);
                        } else { //1부터 999까지만 값을 체크 그값이 아니라면 오류메세지
                            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
                            return "admin/basic/mvbanner/list";
                        }
                    } else { //숫자가 아니면 오류메세지
                        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
                        return "admin/basic/mvbanner/list";
                    }
                }

                adminMvBannerServiceImpl.doSortOrder(adminBannerVO);
            }


            Map<String, String> hmParam = new HashMap<String, String>();
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
            model.addAttribute("url", "./list.do");
            model.addAttribute("hmParam", hmParam);
            hmParam.put("picType", vo.getPicType());
            return "admin/common/message";

        } catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/mvBanner/list.do";
        }
    }

    /**
     * 이벤트 이미지 다운로드
     *
     * @param request
     * @param response
     * @param vo
     * @param model
     * @return
     */
    @GetMapping("/celloSquareAdmin/mvBannerDownload.do")
    public void fileDown(HttpServletRequest request, HttpServletResponse response, Model model,
                         @ModelAttribute("vo") AdminMvBannerVO vo) throws IOException {

        AdminMvBannerVO adminBannerVO = adminMvBannerServiceImpl.getDetail(vo);

        String fNm = null;
        String filePath = null;
        if (vo.getImgKinds().equals("pcList")) {
            fNm = adminBannerVO.getPcListImgOrgFileNm();
            filePath = adminBannerVO.getPcListImgPath() + "/" + FilenameUtils.getName(adminBannerVO.getPcListImgFileNm());
        } else if (vo.getImgKinds().equals("mobileList")) {
            fNm = adminBannerVO.getMobileListImgOrgFileNm();
            filePath = adminBannerVO.getMobileListImgPath() + "/" + FilenameUtils.getName(adminBannerVO.getMobileListImgFileNm());
        }

        OutputStream os = null;
        FileInputStream fis = null;

        try {
            if (adminBannerVO != null) {

                SafeFile file = new SafeFile(filePath);

                response.setContentType("application/octet-stream; charset=utf-8");
                response.setContentLength((int) file.length());

                String browser = FileDownUtil.getBrowser(request);
                String disposition = FileDownUtil.getDisposition(fNm, browser);

                //fileName으로 다운받아라 라는뜻  attachment로써 다운로드 되거나 로컬에 저장될 용도록 쓰이는 것인지
                response.setHeader("Content-Disposition", disposition);
                response.setHeader("Content-Transfer-Encoding", "binary");

                os = response.getOutputStream();
                fis = new FileInputStream(file);

                FileCopyUtils.copy(fis, os);
            }
        } catch (Exception e) {
            //			e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
        }
        if (os != null) {
            os.flush();
        }
    }


}

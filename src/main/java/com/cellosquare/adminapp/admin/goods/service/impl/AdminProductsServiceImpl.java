package com.cellosquare.adminapp.admin.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.article.mapper.ArticleMapper;
import com.cellosquare.adminapp.admin.article.vo.Article;
import com.cellosquare.adminapp.admin.attachfile.mapper.AdminAttachFileMapper;
import com.cellosquare.adminapp.admin.attachfile.vo.AdminAttachFileVO;
import com.cellosquare.adminapp.admin.goods.mapper.AdminGoodsMapper;
import com.cellosquare.adminapp.admin.goods.mapper.AdminProductsMapper;
import com.cellosquare.adminapp.admin.goods.service.AdminGoodsService;
import com.cellosquare.adminapp.admin.goods.service.AdminProductsService;
import com.cellosquare.adminapp.admin.goods.vo.AdminProductsVO;
import com.cellosquare.adminapp.admin.goods.vo.Products;
import com.cellosquare.adminapp.admin.helpSupport.service.IHelpSupportService;
import com.cellosquare.adminapp.admin.helpSupport.vo.HelpSupportVo;
import com.cellosquare.adminapp.admin.seo.service.AdminSeoService;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.common.constant.SeoModuleEnum;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.upload.FileUploadManager;
import com.cellosquare.adminapp.common.upload.FileUploadVO;
import com.cellosquare.adminapp.common.util.SeoUtils;
import com.cellosquare.adminapp.common.util.XssUtils;
import com.cellosquare.adminapp.common.vo.BaseSeoParam;
import com.google.common.collect.Lists;
import com.nhncorp.lucy.security.xss.XssPreventer;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminProductsServiceImpl extends ServiceImpl<AdminProductsMapper, Products> implements AdminProductsService {
    @Autowired
    private AdminProductsMapper adminProductsMapper;
    @Autowired
    private AdminSeoService adminSeoServiceImpl;
    @Autowired
    @Lazy
    private IHelpSupportService helpSupportServiceImpl;

    /**
     * 상품 리스트 토탈 카운트
     *
     * @param vo
     * @return
     */
    public int getTotalCount(AdminProductsVO vo) {
        return adminProductsMapper.getTotalCount(vo);
    }

    /**
     * 상품 리스트 조회
     *
     * @param vo
     * @return
     */
    public List<AdminProductsVO> getList(AdminProductsVO vo) {
        return adminProductsMapper.getList(vo);
    }

    /**
     * 상품 시퀀스로 조회
     *
     * @param vo
     * @return
     */
    public AdminProductsVO getDetail(AdminProductsVO vo) {
        return adminProductsMapper.getDetail(vo);
    }


    /**
     * 정렬순서 저장
     *
     * @param vo
     * @return
     */
    public int doSortOrder(AdminProductsVO vo) {
        return adminProductsMapper.doSortOrder(vo);
    }

    /**
     * 상품 등록
     *
     * @param vo
     * @return
     */
    public int regist(AdminProductsVO vo) {
        int result = adminProductsMapper.regist(vo);
        // 첨부파일 저장
        //List<FileUploadVO> fileList = vo.getFileList();
        return result;
    }


    /**
     * 상품 이름 조회
     *
     * @param AdminProductsVO
     * @return
     */
    public AdminProductsVO getGoodsNm(AdminProductsVO AdminProductsVO) {
        return adminProductsMapper.getGoodsNm(AdminProductsVO);
    }

    /**
     * 상품 수정
     *
     * @param vo
     * @return
     */

    public int update(AdminProductsVO vo) {
        int result = 0;
        adminProductsMapper.doUpdate(vo);
        result = 1;
        return result;
    }

    /**
     * 상품 삭제
     *
     * @param vo
     * @return
     */
    public int delete(AdminProductsVO vo) {
        return adminProductsMapper.delete(vo);
    }

    public int getProductCountByNm(String nm, String ignoreSeqNo) {
        AdminProductsVO vo = new AdminProductsVO();
        vo.setProductSeqNo(ignoreSeqNo);
        vo.setProductNm(nm.trim());
        return adminProductsMapper.getProductCountByNm(vo);
    }

    @Override
    public void productList(Model model, AdminProductsVO vo) {
        AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
        vo.setLangCd(sessionForm.getLangCd());

        int totalCount = getTotalCount(vo);
        List<AdminProductsVO> list = new ArrayList<>();

        if (totalCount > 0) {
            list = getList(vo);
        }
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("list", list);
    }

    /**
     * 处理数据
     *
     * @param vo
     */
    private void dealData(AdminProductsVO vo) {
        SeoUtils.setSeoMsg(new BaseSeoParam(SeoModuleEnum.PRODUCT.getCode(), vo.getProductNm(), vo.getProductSummaryInfo(),
                vo.getProductContents(), null, vo));
    }

    @Override
    public String register(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest, AdminProductsVO vo) throws Exception {

        if (getProductCountByNm(vo.getProductNm(), "") > 0) {
            //重名
            ActionMessageUtil.setActionMessage(request, "产品名称重复");
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/products/list.do" + "?" + "productCtgry=" + vo.getProductCtgry();
        }

        AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
        vo.setInsPersonId(sessionForm.getAdminId());
        vo.setUpdPersonId(sessionForm.getAdminId());

        vo.setLangCd(sessionForm.getLangCd());

        vo.setProductContents(XssUtils.operationContent(vo.getProductContents(), vo.getProductNm()));
        dealData(vo);
        // 메타 SEO 저장
        if (adminSeoServiceImpl.doSeoWriteV2(request, response, muServletRequest, vo) > 0) {
            //图片
            setImg(response, muServletRequest, vo, new AdminProductsVO());
                       // 컨텐츠 저장
            this.dealAddOrUpdate(vo);
            //处理摘要
            vo.setProductSummaryInfo(SeoUtils.getSummaryInfo(vo.getProductSummaryInfo(), vo.getProductContents()));
            regist(vo);
        } else {
            // 강제 Exception
            throw new Exception();
        }
        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
        return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/products/list.do" + "?" + "productCtgry=" + vo.getProductCtgry();

    }

    /**
     * 图片设置
     *
     * @param response
     * @param muServletRequest
     * @param vo
     * @param AdminProductsVO
     * @throws Exception
     */
    private void setImg(HttpServletResponse response, MultipartHttpServletRequest muServletRequest,
                        AdminProductsVO vo, AdminProductsVO AdminProductsVO) throws Exception {
        String pcListfileDel = StringUtil.nvl(vo.getPcListFileDel(), "N");
        String pcDetailfileDel = StringUtil.nvl(vo.getPcDetailFileDel(), "N");
        String mobileDetailFileDel = StringUtil.nvl(vo.getMobileDetailFileDel(), "N");
        if (!vo.getPcListOrginFile().isEmpty()) {

            if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcListOrginFile")) {
                String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));

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
                vo.setPcListImgPath(AdminProductsVO.getPcListImgPath());
                vo.setPcListImgFileNm(AdminProductsVO.getPcListImgFileNm());
                vo.setPcListImgSize(String.valueOf(StringUtil.nvl(AdminProductsVO.getPcListImgSize(), "0")));
                vo.setPcListImgOrgFileNm(AdminProductsVO.getPcListImgOrgFileNm());
            }
        }
        // pcDetail 첨부파일을 선택 했을시
        if (!vo.getPcDetailOrginFile().isEmpty()) {
            if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "pcDetailOrginFile")) {
                String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));

                FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "pcDetailOrginFile");

                vo.setPcDetlImgPath(fileVO.getFilePath());
                vo.setPcDetlImgFileNm(fileVO.getFileTempName());
                vo.setPcDetlImgSize(String.valueOf(fileVO.getFileSize()));
                vo.setPcDetlImgOrgFileNm(fileVO.getFileOriginName());
            } else {
                throw new Exception();
            }
        } else {
            if (pcDetailfileDel.equals("Y")) {// 삭제를 체크했을시
                vo.setPcDetlImgPath("");
                vo.setPcDetlImgFileNm("");
                vo.setPcDetlImgSize("0");
                vo.setPcDetlImgOrgFileNm("");
                vo.setPcDetlImgAlt("");
            } else {
                vo.setPcDetlImgPath(AdminProductsVO.getPcDetlImgPath());
                vo.setPcDetlImgFileNm(AdminProductsVO.getPcDetlImgFileNm());
                vo.setPcDetlImgSize(String.valueOf(StringUtil.nvl(AdminProductsVO.getPcDetlImgSize(), "0")));
                vo.setPcDetlImgOrgFileNm(AdminProductsVO.getPcDetlImgOrgFileNm());
            }
        }

        if (!vo.getMobileDetailOrginFile().isEmpty()) {

            if (FileUploadManager.isUploadImageValid(muServletRequest, response, true, "mobileDetailOrginFile")) {
                String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));
                FileUploadVO fileVO = FileUploadManager.uploadImageFile(muServletRequest, path, "mobileDetailOrginFile");
                vo.setMobileDetlImgPath(fileVO.getFilePath());
                vo.setMobileDetlImgFileNm(fileVO.getFileTempName());
                vo.setMobileDetlImgSize(String.valueOf(fileVO.getFileSize()));
                vo.setMobileDetlImgOrgFileNm(fileVO.getFileOriginName());
            }
        } else {
            if (mobileDetailFileDel.equals("Y")) {// 삭제를 체크했을시
                vo.setMobileDetlImgPath("");
                vo.setMobileDetlImgFileNm("");
                vo.setMobileDetlImgSize("0");
                vo.setMobileDetlImgOrgFileNm("");
            } else {
                vo.setMobileDetlImgPath(AdminProductsVO.getMobileDetlImgPath());
                vo.setMobileDetlImgFileNm(AdminProductsVO.getMobileDetlImgFileNm());
                vo.setMobileDetlImgSize(String.valueOf(StringUtil.nvl(AdminProductsVO.getMobileDetlImgSize(), "0")));
                vo.setMobileDetlImgOrgFileNm(AdminProductsVO.getMobileDetlImgOrgFileNm());
            }
        }
        // 첨부파일 업로드
        if (FileUploadManager.isUploadFileValids(muServletRequest, response, true, "fileUpload")) {
            String path = StringUtil.carriageReturnDelete(StringUtil.nvl(XmlPropertyManager.getPropertyValue("uploadFilePathGoods")));
            List<FileUploadVO> fileList = FileUploadManager.uploadFiles(muServletRequest, path, "fileUpload");
            vo.setFileList(fileList);
        } else {
            throw new Exception();
        }
    }

    @Override
    public String doUpdate(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest, Model model, AdminProductsVO vo) throws Exception {
        if (getProductCountByNm(vo.getProductNm(), vo.getProductSeqNo()) > 0) {
            //重名
            ActionMessageUtil.setActionMessage(request, "产品名称重复");
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/products/list.do" + "?" + "productCtgry=" + vo.getProductCtgry();
        }
        vo.setProductContents(XssUtils.operationContent(vo.getProductContents(), vo.getProductNm()));

        AdminSessionForm adminSessionForm = SessionManager.getAdminSessionForm();
        vo.setUpdPersonId(adminSessionForm.getAdminId());
        dealData(vo);
        if (adminSeoServiceImpl.doSeoUpdatev2(request, response, muServletRequest, vo) > 0) {

            AdminProductsVO AdminProductsVO = getDetail(vo);

            setImg(response, muServletRequest,
                    vo, AdminProductsVO);
            this.dealAddOrUpdate(vo);
            update(vo);

        } else {
            // 강제 Exception
            throw new Exception();
        }

        Map<String, String> hmParam = new HashMap<String, String>();
        hmParam.put("productSeqNo", vo.getProductSeqNo());
        hmParam.put("productCtgry", vo.getProductCtgry());

        model.addAttribute("msg_type", ":submit");
        model.addAttribute("method", "post");
        model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
        model.addAttribute("url", "./detail.do");
        model.addAttribute("hmParam", hmParam);

        return "admin/common/message";
    }

    @Override
    public void updateForm(Model model, AdminProductsVO vo) {
        AdminProductsVO detailVO = getDetail(vo);
        String ackQuestion = detailVO.getAckQuestion();
        detailVO.setCommonSelect2(StringUtils.isNotEmpty(ackQuestion) ? ackQuestion.split(",") : new String[0]);
        List<String> ids = Arrays.asList(detailVO.getCommonSelect2());

        AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(detailVO);
        List<HelpSupportVo> helpSupportVos = helpSupportServiceImpl.queryQAndA();
        helpSupportVos.forEach(helpSupportVo -> {
            if (ids.contains(helpSupportVo.getId())) {
                //选中
                helpSupportVo.setIsSelect(1);
            }
        });

        model.addAttribute("ackQuestions", helpSupportVos);
        model.addAttribute("detail", detailVO);
        model.addAttribute("adminSeoVO", adminSeoVO);
        //model.addAttribute("attachFileList", attachFileList);
        model.addAttribute("contIU", "U");
    }

    @Override
    public void goDetail(Model model, AdminProductsVO vo) {
        AdminProductsVO detailVO = getDetail(vo);
        // xss 원복
        detailVO.setProductContents(XssPreventer.unescape(detailVO.getProductContents()));
        // seo 정보 가져오기
        AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(detailVO);

        String ackQuestion = detailVO.getAckQuestion();
        List<Long> ids = Arrays.asList(StringUtils.isNotEmpty(ackQuestion) ? ackQuestion.split(",") : new String[0]).stream().
                filter(s -> StringUtils.isNotEmpty(s)).map(Long::valueOf).collect(Collectors.toList());
        String names = StringUtils.EMPTY;
        if (CollectionUtils.isNotEmpty(ids)) {
            names = helpSupportServiceImpl.queryQAndAByIds(ids).stream().map(HelpSupportVo::getName).collect(Collectors.joining("、"));
        }
        model.addAttribute("detail", detailVO);
        model.addAttribute("ackQuestionNames", names);
        model.addAttribute("adminSeoVO", adminSeoVO);
        model.addAttribute("vo", vo);
    }

    private void dealAddOrUpdate(AdminProductsVO vo) {
        String[] commonSelect2 = vo.getCommonSelect2();
        if (commonSelect2 != null && commonSelect2.length > 0) {
            String collect = Arrays.asList(commonSelect2).stream().collect(Collectors.joining(","));
            vo.setAckQuestion(collect);
        }
    }
}

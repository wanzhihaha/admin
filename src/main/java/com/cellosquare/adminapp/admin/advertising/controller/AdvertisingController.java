package com.cellosquare.adminapp.admin.advertising.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.advertising.conver.AdvertisingConver;
import com.cellosquare.adminapp.admin.advertising.entity.Advertising;
import com.cellosquare.adminapp.admin.advertising.service.IAdvertisingService;
import com.cellosquare.adminapp.admin.advertising.vo.AdvertisingVO;
import com.cellosquare.adminapp.admin.article.conver.ArticleConver;
import com.cellosquare.adminapp.admin.article.vo.Article;
import com.cellosquare.adminapp.admin.article.vo.ArticleVO;
import com.cellosquare.adminapp.admin.popupmanagement.vo.AdminPopupManagementVO;
import com.cellosquare.adminapp.admin.seo.service.AdminSeoService;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.common.constant.Constants;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;
import com.nhncorp.lucy.security.xss.XssPreventer;
import org.apache.commons.io.FilenameUtils;
import org.owasp.esapi.SafeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author walker
 * @since 2022-12-340 15:25:29
 */
@Controller
@RequestMapping("/celloSquareAdmin/advertising")
public class AdvertisingController {
    @Autowired
    private IAdvertisingService advertisingServiceImpl;
    @Autowired
    private AdminSeoService adminSeoServiceImpl;

    /**
     * 列表查询
     *
     * @param model
     * @param vo
     * @return
     */
    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") AdvertisingVO vo) {
        Page<Advertising> page = advertisingServiceImpl.lambdaQuery()
                .eq(StrUtil.isNotEmpty(vo.getSearchType()) && !Objects.equals(vo.getSearchType(), Constants.SEARCH_TYPE_ALL), Advertising::getAdLocation, vo.getSearchType())
                .like(StrUtil.isNotEmpty(vo.getSearchValue()), Advertising::getAdName, vo.getSearchValue())
                .orderByDesc(Advertising::getCreateDate)
                .page(new Page<Advertising>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        model.addAttribute("totalCount", page.getTotal());
        List<AdvertisingVO> advertisingVO = page.getRecords().stream().map(AdvertisingConver.INSTANCT::getAdvertisingVO).collect(Collectors.toList());
        model.addAttribute("list", advertisingVO);
        return "admin/basic/advertising/list";
    }

    /**
     * 新建文章
     *
     * @param request
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/registerForm.do")
    public String registForm(HttpServletRequest request, Model model,
                             @ModelAttribute("vo") AdvertisingVO vo) {
        HttpSession session = request.getSession();
        session.removeAttribute("detail");
        session.removeAttribute("adminSeoVO");
        session.removeAttribute("attachFileList");
        session.removeAttribute("vo");
        model.addAttribute("contIU", "I");
        return "admin/basic/advertising/registerForm";
    }


    /**
     * 상품 등록
     *
     * @param request
     * @param response
     * @param vo
     * @param model
     * @return
     */
    @PostMapping("/register.do")
    public String register(HttpServletRequest request, HttpServletResponse response, Model model,
                           @ModelAttribute("vo") AdvertisingVO vo, MultipartHttpServletRequest muServletRequest) {
        Boolean register = advertisingServiceImpl.register(request, response, vo, muServletRequest);
        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
        return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/advertising/list.do";
    }

    /**
     * 상세보기
     *
     * @param request
     * @param response
     * @param vo
     * @param model
     * @return
     */
    @PostMapping("/detail.do")
    public String detail(HttpServletRequest request, HttpServletResponse response, Model model,
                         @ModelAttribute("vo") AdvertisingVO vo) {

        Advertising advertising = advertisingServiceImpl.getById(vo.getId());
        if (Objects.isNull(advertising)) {
            return "admin/basic/advertising/detail";
        }
        // seo 정보 가져오기
        AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(new AdminSeoVO() {{
            if (Objects.nonNull(advertising.getMetaSeqNo())) {
                setMetaSeqNo(advertising.getMetaSeqNo().toString());
            }
        }});
        AdvertisingVO advertisingVO = AdvertisingConver.INSTANCT.getAdvertisingVO(advertising);
        HttpSession session = request.getSession();
        session.setAttribute("detail", advertisingVO);
        session.setAttribute("adminSeoVO", adminSeoVO);
        session.setAttribute("vo", vo);
        model.addAttribute("detail", advertisingVO);
        model.addAttribute("adminSeoVO", adminSeoVO);
        model.addAttribute("vo", vo);
        return "admin/basic/advertising/detail";
    }

    @PostMapping("/updateForm.do")
    public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
                             @ModelAttribute("vo") AdvertisingVO vo) {

        Advertising advertising = advertisingServiceImpl.getById(vo.getId());
        // seo 정보 가져오기
        AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(new AdminSeoVO() {{
            setMetaSeqNo(vo.getMetaSeqNo());
        }});

        model.addAttribute("detail", advertising);
        model.addAttribute("adminSeoVO", adminSeoVO);
        model.addAttribute("contIU", "U");
        return "admin/basic/advertising/registerForm";
    }

    @PostMapping("/update.do")
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,
                           MultipartHttpServletRequest muServletRequest, Model model,
                           @ModelAttribute("vo") AdvertisingVO vo) {
        Boolean update = advertisingServiceImpl.doUpdate(request, response, vo, muServletRequest);
        model.addAttribute("msg_type", ":submit");
        model.addAttribute("method", "post");
        model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
        model.addAttribute("url", "./detail.do");
        Map<String, String> hmParam = new HashMap<String, String>();
        hmParam.put("id", vo.getId().toString());
        model.addAttribute("hmParam", hmParam);
        return "admin/common/message";
    }

    @PostMapping("/doSortOrder.do")
    public String doSortOrder(HttpServletRequest request, HttpServletResponse response, Model model,
                              @ModelAttribute("vo") AdvertisingVO vo) {
        try {
            for (int i = 0; i < vo.getListblogSeq().length; i++) {
                long id = Long.parseLong(vo.getListblogSeq()[i]);
                Advertising advertising = advertisingServiceImpl.getById(id);
                // 문자열이 숫자인지 확인
                String str = "";
                if (vo.getListSortOrder().length > 0) {
                    str = vo.getListSortOrder()[i];
                }
                if (!StringUtil.nvl(str).equals("")) {
                    boolean isNumeric = str.matches("[+-]?\\d*(\\.\\d+)?");
                    // 숫자라면 true
                    if (isNumeric) {
                        int num = Integer.parseInt(vo.getListSortOrder()[i]);
                        if (0 <= num && 1000 > num) {
                            advertising.setOrdb(vo.getListSortOrder()[i]);
                        } else { //1부터 999까지만 값을 체크 그값이 아니라면 오류메세지
                            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
                            return "admin/basic/advertising/list";
                        }
                    } else { //숫자가 아니면 오류메세지
                        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
                        return "admin/basic/advertising/list";
                    }
                }

                advertisingServiceImpl.updateById(advertising);
            }

            Map<String, String> hmParam = new HashMap<String, String>();
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
            model.addAttribute("url", "./list.do");
            model.addAttribute("hmParam", hmParam);

            return "admin/common/message";

        } catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/advertising/list.do";
        }
    }

    @PostMapping("/doDelete.do")
    public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
                         @ModelAttribute("vo") AdvertisingVO vo) {
        try {
            advertisingServiceImpl.removeById(vo.getId());

            // seo정보 삭제
            adminSeoServiceImpl.doSeoDelete(vo);

            Map<String, String> hmParam = new HashMap<String, String>();
            hmParam.put("id", vo.getId().toString());
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
            model.addAttribute("url", "./list.do");
            model.addAttribute("hmParam", hmParam);

            return "admin/common/message";

        } catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
            return "admin/basic/advertising/detail";
        }

    }


    /**
     * 获取图片
     *
     * @param request
     * @param response
     * @param model
     * @param vo
     * @return
     */
    @ResponseBody
    @GetMapping("/imgView.do")
    public String imgView(HttpServletRequest request, HttpServletResponse response, Model model,
                          @ModelAttribute("vo") AdvertisingVO vo) {
        Advertising byId = advertisingServiceImpl.getById(vo.getId());
        try {
            if (byId != null) {
                FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
                SafeFile pcListFile = new SafeFile(byId.getAdPicUrl(), FilenameUtils.getName(byId.getAdPicName()));
                if (pcListFile.isFile()) {
                    fileDownLoadManager.fileFlush(pcListFile, byId.getAdPicName());
                }
            }
        } catch (Exception e) {
        }

        return null;
    }


    @GetMapping("/preView.do")
    public String popupPreView(HttpServletRequest request, HttpServletResponse response, Model model,
                               @ModelAttribute("vo") AdminPopupManagementVO vo) {
        return "admin/basic/advertising/popupPreView";
    }

    /**
     * 获取图片
     *
     * @param request
     * @param response
     * @param model
     * @param vo
     * @return
     */
    @ResponseBody
    @GetMapping("/blogImgView.do")
    public String blogImgView(HttpServletRequest request, HttpServletResponse response, Model model,
                              @ModelAttribute("vo") AdvertisingVO vo) {
        Advertising advertising = advertisingServiceImpl.getById(vo.getId());
        try {
            if (advertising != null) {
                FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
                if (vo.getImgKinds().equals("mobileList")) {
                    SafeFile pcListFile = new SafeFile(advertising.getAdMobilePicUrl(), FilenameUtils.getName(advertising.getAdMobilePicName()));
                    if (pcListFile.isFile()) {
                        fileDownLoadManager.fileFlush(pcListFile, advertising.getAdMobilePicName());
                    }
                } else {
                    SafeFile pcListFile = new SafeFile(advertising.getAdPicUrl(), FilenameUtils.getName(advertising.getAdPicName()));
                    if (pcListFile.isFile()) {
                        fileDownLoadManager.fileFlush(pcListFile, advertising.getAdPicName());
                    }
                }
            }
        } catch (Exception e) {
        }

        return null;
    }
}

package com.cellosquare.adminapp.admin.article.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.ColumnCache;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.article.conver.ArticleConver;
import com.cellosquare.adminapp.admin.article.service.IArticleService;
import com.cellosquare.adminapp.admin.article.vo.Article;
import com.cellosquare.adminapp.admin.article.vo.ArticleVO;
import com.cellosquare.adminapp.admin.attachfile.vo.AdminAttachFileVO;
import com.cellosquare.adminapp.admin.blog.vo.AdminBlogVO;
import com.cellosquare.adminapp.admin.code.service.ApiCodeService;
import com.cellosquare.adminapp.admin.code.vo.ApiCodeVO;
import com.cellosquare.adminapp.admin.goods.vo.AdminGoodsVO;
import com.cellosquare.adminapp.admin.seo.service.AdminSeoService;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.admin.terminology.service.impl.TerminologyServiceImpl;
import com.cellosquare.adminapp.common.adminAnnotion.CleanCacheAnnotion;
import com.cellosquare.adminapp.common.constant.Constants;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;
import com.cellosquare.adminapp.common.enums.TermTypeEnum;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.upload.FileUploadManager;
import com.cellosquare.adminapp.common.upload.FileUploadVO;
import com.cellosquare.adminapp.common.util.XssUtils;
import com.cellosquare.adminapp.common.vo.GeneralVO;
import com.nhncorp.lucy.security.xss.XssPreventer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.owasp.esapi.SafeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author walker
 * @since 2022-12-335 10:57:25
 */
@RequestMapping("/celloSquareAdmin/article")
@Controller
@Slf4j
public class ArticleController {
    @Autowired
    private IArticleService articleServiceImpl;
    @Autowired
    private ApiCodeService apiCodeServiceImpl;
    @Autowired
    private AdminSeoService adminSeoServiceImpl;
    @Autowired
    private TerminologyServiceImpl terminologyService;
    /**
     * 列表查询
     * @param model
     * @param vo
     * @return
     */
    @GetMapping("/list.do")
    public String list( Model model, @ModelAttribute("vo") ArticleVO vo) {
        Page<Article> page = articleServiceImpl.lambdaQuery()
                .eq(StrUtil.isNotEmpty(vo.getSearchType()) && !Objects.equals(vo.getSearchType(), Constants.SEARCH_TYPE_ALL), Article::getArticleType,vo.getSearchType())
                .like(StrUtil.isNotEmpty(vo.getSearchValue()), Article::getArticleTitle, vo.getSearchValue())
                .orderByAsc(Article::getStickType)
                .orderByDesc(Article::getCreateDate)
                .page(new Page<Article>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        model.addAttribute("totalCount", page.getTotal());
        List<ArticleVO> articleVO = page.getRecords().stream().map(ArticleConver.INSTANCT::getArticleVO).collect(Collectors.toList());
        model.addAttribute("list",articleVO );
        return "admin/basic/article/list";
    }

    /**
     * 新建文章
     * @param request
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/registerForm.do")
    public String registForm(HttpServletRequest request, Model model,
                             @ModelAttribute("vo") ArticleVO vo)  {
        HttpSession session = request.getSession();
        session.removeAttribute("detail");
        session.removeAttribute("adminSeoVO");
        session.removeAttribute("attachFileList");
        session.removeAttribute("vo");
        model.addAttribute("contIU", "I");
        return "admin/basic/article/registerForm";
    }


    /**
     * 상품 등록
     * @param request
     * @param response
     * @param vo
     * @param model
     * @return
     */
    @PostMapping("/register.do")
    @CleanCacheAnnotion
    public String register(HttpServletRequest request, HttpServletResponse response, Model model,
                           @ModelAttribute("vo") ArticleVO vo, MultipartHttpServletRequest muServletRequest)  {
        try {
            Boolean register = articleServiceImpl.register(request, response, vo, muServletRequest);
            if (register) {
                return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/article/list.do";
            }
        } catch (Exception e) {
            e.printStackTrace();
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"), null, null, null, true);
            return "admin/basic/article/registerForm";
        }
        return "admin/basic/article/registerForm";
    }

    /**
     * 상세보기
     * @param request
     * @param response
     * @param vo
     * @param model
     * @return
     */
    @PostMapping("/detail.do")
    public String detail(HttpServletRequest request, HttpServletResponse response, Model model,
                         @ModelAttribute("vo") ArticleVO vo)  {

        Article article = articleServiceImpl.getById(vo.getId());
        if (Objects.isNull(article)) {
            return "admin/basic/article/detail";
        }
        // seo 정보 가져오기
        AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(new AdminSeoVO(){{
            if (Objects.nonNull(article.getMetaSeqNo())) {
                setMetaSeqNo(article.getMetaSeqNo().toString());
            }
        }});

        ArticleVO articleVO = ArticleConver.INSTANCT.getArticleVO(article);
        articleServiceImpl.getTags(articleVO);
        articleVO.setArticleContent(XssPreventer.unescape(articleVO.getArticleContent()));

        HttpSession session = request.getSession();
        session.setAttribute("detail", articleVO);
        session.setAttribute("adminSeoVO", adminSeoVO);
        session.setAttribute("vo", vo);
        model.addAttribute("detail", articleVO);
        model.addAttribute("adminSeoVO", adminSeoVO);
        model.addAttribute("vo", vo);
        return "admin/basic/article/detail";
    }

    /**
     * 获取图片
     * @param request
     * @param response
     * @param model
     * @param vo
     * @return
     */
    @ResponseBody
    @GetMapping("/blogImgView.do")
    public String blogImgView(HttpServletRequest request, HttpServletResponse response, Model model,
                              @ModelAttribute("vo") ArticleVO vo) {

        Article article = articleServiceImpl.getById(vo.getId());
        try {
            if(article != null) {
                FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
                if("articlePicBig".equals(request.getParameter("imgKinds"))) {
                    SafeFile pcListFile = new SafeFile(article.getArticlePicBig() , FilenameUtils.getName(article.getArticlePicBigName()));
                    if (pcListFile.isFile()) {
                        fileDownLoadManager.fileFlush(pcListFile, article.getArticlePicBigName());
                    }
                } else if ("articlePicTb".equals(request.getParameter("imgKinds"))) {
                    SafeFile pcDetailFile = new SafeFile(article.getArticlePicTb() , FilenameUtils.getName(article.getArticlePicTbName()));
                    if (pcDetailFile.isFile()) {
                        fileDownLoadManager.fileFlush(pcDetailFile, article.getArticlePicTbName());
                    }
                }else if ("articlePicAs".equals(request.getParameter("imgKinds"))) {
                    SafeFile pcDetailFile = new SafeFile(article.getArticlePicAs() , FilenameUtils.getName(article.getArticlePicAsName()));
                    if (pcDetailFile.isFile()) {
                        fileDownLoadManager.fileFlush(pcDetailFile, article.getArticlePicAsName());
                    }
                }
            }
        } catch (Exception e) {
        }

        return null;
    }

    @PostMapping("/updateForm.do")
    public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
                             @ModelAttribute("vo") ArticleVO vo) {

        Article article = articleServiceImpl.getById(vo.getId());
        // seo 정보 가져오기
        AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(new AdminSeoVO(){{
            setMetaSeqNo(StrUtil.isNotEmpty(vo.getMetaSeqNo())?vo.getMetaSeqNo():"0");
        }});

        ArticleVO articleVO = ArticleConver.INSTANCT.getArticleVO(article);
        articleServiceImpl.getTags(articleVO);
        article.setTerminologyTags(articleVO.getTerminologyTags());
        article.setAntistopTags(articleVO.getAntistopTags());
        model.addAttribute("detail", article);
        model.addAttribute("adminSeoVO", adminSeoVO);
        model.addAttribute("contIU", "U");
        return "admin/basic/article/registerForm";
    }

    @PostMapping("/update.do")
    @CleanCacheAnnotion
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,
                           MultipartHttpServletRequest muServletRequest, Model model,
                           @ModelAttribute("vo") ArticleVO vo) {

        try {
            Boolean update =  articleServiceImpl.doUpdate(request, response, vo, muServletRequest);
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("method", "post");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
            model.addAttribute("url", "./detail.do");
            Map<String, String> hmParam = new HashMap<String, String>();
            hmParam.put("id", vo.getId().toString());
            model.addAttribute("hmParam", hmParam);
            return "admin/common/message";
        } catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
            return "admin/basic/article/registerForm";
        }
    }

    @PostMapping("/doSortOrder.do")
    public String doSortOrder(HttpServletRequest request, HttpServletResponse response, Model model,
                              @ModelAttribute("vo") ArticleVO vo) {
        try {
            for(int i = 0; i < vo.getListblogSeq().length; i++) {
                long id = Long.parseLong(vo.getListblogSeq()[i]);
                Article article = articleServiceImpl.getById(id);
                // 문자열이 숫자인지 확인
                String str = "";
                if(vo.getListSortOrder().length>0){
                    str = vo.getListSortOrder()[i];
                }
                if(!StringUtil.nvl(str).equals("")) {
                    boolean isNumeric =  str.matches("[+-]?\\d*(\\.\\d+)?");
                    // 숫자라면 true
                    if(isNumeric) {
                        int num = Integer.parseInt(vo.getListSortOrder()[i]);
                        if(0 <= num && 1000 > num) {
                            article.setOrdb(vo.getListSortOrder()[i]);
                        } else { //1부터 999까지만 값을 체크 그값이 아니라면 오류메세지
                            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
                            return "admin/basic/article/list";
                        }
                    } else { //숫자가 아니면 오류메세지
                        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
                        return "admin/basic/article/list";
                    }
                }

                articleServiceImpl.updateById(article);
            }


            Map<String, String> hmParam = new HashMap<String, String>();
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
            model.addAttribute("url", "./list.do");
            model.addAttribute("hmParam", hmParam);

            return "admin/common/message";

        }catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
            return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/article/list.do";
        }
    }

    @PostMapping("/doDelete.do")
    @CleanCacheAnnotion
    public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
                         @ModelAttribute("vo") ArticleVO vo) {
        try {
            articleServiceImpl.removeById(vo.getId());

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
            return "admin/basic/article/detail";
        }

    }

    /**
     * 批量导入
     * @param request
     * @param response
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/upload.do")
    public String upload(HttpServletRequest request,HttpServletResponse response,MultipartFile file) throws Exception {
        articleServiceImpl.importWord(file,request,response);
        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
        return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/article/list.do";
    }
}

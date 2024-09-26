package com.cellosquare.adminapp.admin.terminology.controller;


import cn.hutool.core.io.file.FileAppender;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.dic.entity.Dic;
import com.cellosquare.adminapp.admin.dic.service.impl.DicServiceImpl;
import com.cellosquare.adminapp.admin.seo.service.impl.AdminSeoServiceImpl;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.admin.termRelation.entity.TermRelation;
import com.cellosquare.adminapp.admin.termRelation.service.impl.TermRelationServiceImpl;
import com.cellosquare.adminapp.admin.terminology.conver.TerminologyConver;
import com.cellosquare.adminapp.admin.terminology.entity.Terminology;
import com.cellosquare.adminapp.admin.terminology.service.impl.TerminologyServiceImpl;
import com.cellosquare.adminapp.admin.terminology.vo.TerminologyUploadVO;
import com.cellosquare.adminapp.admin.terminology.vo.TerminologyVO;
import com.cellosquare.adminapp.common.adminAnnotion.CleanCacheAnnotion;
import com.cellosquare.adminapp.common.listener.TermExcelUploadListener;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author walker
 * @since 2023-03-61 14:43:00
 */
@Controller
@RequestMapping("/celloSquareAdmin/terminology")
public class TerminologyController {
    @Autowired
    private TerminologyServiceImpl terminologyService;
    @Autowired
    private AdminSeoServiceImpl adminSeoServiceImpl;
    @Autowired
    private TermRelationServiceImpl termRelationService;
    @Autowired
    private DicServiceImpl dicService;

    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") TerminologyVO vo) {
        Page<Terminology> page = terminologyService.lambdaQuery()
                .like(StrUtil.isNotEmpty(vo.getSearchValue()), Terminology::getTerminologyName, vo.getSearchValue())
                .orderByDesc(Terminology::getInsDtm)
                .page(new Page<Terminology>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        model.addAttribute("totalCount", page.getTotal());
        model.addAttribute("list", TerminologyConver.INSTANCT.getAdvertisingVOs(page.getRecords()));
        return "admin/basic/terminology/list";
    }

    @PostMapping("/registerForm.do")
    public String registForm(HttpServletRequest request, Model model,
                             @ModelAttribute("vo") TerminologyVO vo) {
        HttpSession session = request.getSession();
        session.removeAttribute("detail");
        session.removeAttribute("adminSeoVO");
        session.removeAttribute("attachFileList");
        session.removeAttribute("vo");
        model.addAttribute("contIU", "I");
        return "admin/basic/terminology/registerForm";
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
    @CleanCacheAnnotion
    public String register(HttpServletRequest request, HttpServletResponse response, Model model,
                           @ModelAttribute("vo") TerminologyVO vo, MultipartHttpServletRequest muServletRequest) {
        try {
            Boolean register = terminologyService.register(request, response, vo, muServletRequest);
            if (register) {
                return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/terminology/list.do";
            }
        } catch (Exception e) {
            return "admin/basic/terminology/registerForm";
        }
        return "admin/basic/terminology/registerForm";
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
                         @ModelAttribute("vo") TerminologyVO vo) {

        Terminology terminology = terminologyService.getById(vo.getId());
        if (Objects.isNull(terminology)) {
            return "admin/basic/terminology/detail";
        }
        // seo 정보 가져오기
        AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(new AdminSeoVO() {{
            if (Objects.nonNull(terminology.getMetaSeqNo())) {
                setMetaSeqNo(terminology.getMetaSeqNo().toString());
            }
        }});

        HttpSession session = request.getSession();
        TerminologyVO advertisingVO = TerminologyConver.INSTANCT.getAdvertisingVO(terminology);
        session.setAttribute("detail", advertisingVO);
        session.setAttribute("adminSeoVO", adminSeoVO);
        session.setAttribute("vo", vo);
        model.addAttribute("detail", advertisingVO);
        model.addAttribute("adminSeoVO", adminSeoVO);
        model.addAttribute("vo", vo);
        return "admin/basic/terminology/detail";
    }

    @PostMapping("/updateForm.do")
    public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
                             @ModelAttribute("vo") TerminologyVO vo) {

        Terminology terminology = terminologyService.getById(vo.getId());
        // seo 정보 가져오기
        if (Objects.nonNull(terminology.getMetaSeqNo())) {
            AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(new AdminSeoVO() {{
                setMetaSeqNo(vo.getMetaSeqNo());
            }});
            model.addAttribute("adminSeoVO", adminSeoVO);
        }
        TerminologyVO advertisingVO = TerminologyConver.INSTANCT.getAdvertisingVO(terminology);
        model.addAttribute("detail", advertisingVO);
        model.addAttribute("contIU", "U");
        return "admin/basic/terminology/registerForm";
    }

    @PostMapping("/update.do")
    @CleanCacheAnnotion
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,
                           MultipartHttpServletRequest muServletRequest, Model model,
                           @ModelAttribute("vo") TerminologyVO vo) {

        try {
            Boolean update = terminologyService.doUpdate(request, response, vo, muServletRequest);
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
            return "admin/basic/terminology/registerForm";
        }
    }

    @PostMapping("/doSortOrder.do")
    public String doSortOrder(HttpServletRequest request, HttpServletResponse response, Model model,
                              @ModelAttribute("vo") TerminologyVO vo) {
        try {
            for (int i = 0; i < vo.getListblogSeq().length; i++) {
                long id = Long.parseLong(vo.getListblogSeq()[i]);
                Terminology terminology = terminologyService.getById(id);
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
                            terminology.setOrdb(vo.getListSortOrder()[i]);
                        } else { //1부터 999까지만 값을 체크 그값이 아니라면 오류메세지
                            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
                            return "admin/basic/terminology/list";
                        }
                    } else { //숫자가 아니면 오류메세지
                        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
                        return "admin/basic/terminology/list";
                    }
                }

                terminologyService.updateById(terminology);
            }

            Map<String, String> hmParam = new HashMap<String, String>();
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
            model.addAttribute("url", "./list.do");
            model.addAttribute("hmParam", hmParam);

            return "admin/common/message";

        } catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/terminology/list.do";
        }
    }

    @PostMapping("/doDelete.do")
    @CleanCacheAnnotion
    public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
                         @ModelAttribute("vo") TerminologyVO vo) {
        try {
            terminologyService.removeById(vo.getId());

            // seo정보 삭제
            adminSeoServiceImpl.doSeoDelete(vo);
            termRelationService.remove(termRelationService.lambdaQuery().eq(TermRelation::getTermId,vo.getId()).getWrapper());
            Map<String, String> hmParam = new HashMap<String, String>();
            hmParam.put("id", vo.getId().toString());
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
            model.addAttribute("url", "./list.do");
            model.addAttribute("hmParam", hmParam);

            return "admin/common/message";

        } catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
            return "admin/basic/terminology/detail";
        }

    }

    /**
     * 文件上传
     * <p>
     * <p>
     * <p>
     * 3. 直接读即可
     */
    @PostMapping("/upload.do")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), TerminologyUploadVO.class, new TermExcelUploadListener(terminologyService,adminSeoServiceImpl)).sheet().doRead();
        return "success";
    }

    @GetMapping("/download.do")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("术语模板", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), TerminologyUploadVO.class).sheet("术语模板").doWrite(()-> null);
    }

    @GetMapping("/exportTerm.do")
    public void exportTerm(HttpServletResponse response,String searchValue) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("术语", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), TerminologyUploadVO.class).sheet("术语").doWrite(data(searchValue));
    }

    private List<TerminologyUploadVO> data(String searchValue) {
        List<Terminology> list = terminologyService.lambdaQuery()
                .like(Terminology::getTerminologyName,searchValue)
                .select(Terminology::getTerminologyName,Terminology::getDescription)
                .list();
        List<TerminologyUploadVO> export = TerminologyConver.INSTANCT.export(list);
        return export;
    }

    @GetMapping("/addDic.do")
    @ResponseBody
    public String addDic(String dicName) throws IOException {
        if (StrUtil.isNotEmpty(dicName)) {
            Dic dic = new Dic();
            dic.setDicName(dicName);
            dicService.save(dic);
        }
        dicService.flush();
        return "success";
    }


    @GetMapping("/dicList.do")
    public String searchHashtag(@ModelAttribute("vo") TerminologyVO vo, Model model){
        Page<Dic> page = dicService.lambdaQuery()
                .like(StrUtil.isNotEmpty(vo.getSearchValue()), Dic::getDicName, vo.getSearchValue())
                .page(new Page<Dic>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        model.addAttribute("list", page.getRecords());
        model.addAttribute("totalCount", page.getTotal());
        return "admin/popup/terminology/dicList";
    }

    @GetMapping("/delDic.do")
    public String delDic(@ModelAttribute("vo") TerminologyVO vo, Model model) throws IOException {
        dicService.removeById(vo.getId());
        dicService.flush();
        return searchHashtag(vo,model);
    }

    @GetMapping("/insertDic.do")
    public String insertDic(@ModelAttribute("vo") TerminologyVO vo, Model model) throws IOException {
        if (StrUtil.isNotEmpty(vo.getSearchValue())) {
            Dic dic = new Dic();
            dic.setDicName(vo.getSearchValue());
            dicService.save(dic);
        }
        dicService.flush();
        return searchHashtag(vo,model);
    }

}

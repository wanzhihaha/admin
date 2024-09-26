package com.cellosquare.adminapp.admin.antistop.controller;


import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.antistop.conver.AntistopConver;
import com.cellosquare.adminapp.admin.antistop.entity.Antistop;
import com.cellosquare.adminapp.admin.antistop.service.impl.AntistopServiceImpl;
import com.cellosquare.adminapp.admin.antistop.vo.AntistopUploadVO;
import com.cellosquare.adminapp.admin.antistop.vo.AntistopVO;
import com.cellosquare.adminapp.admin.antistopRelation.entity.AntistopRelation;
import com.cellosquare.adminapp.admin.antistopRelation.service.impl.AntistopRelationServiceImpl;
import com.cellosquare.adminapp.admin.seo.service.impl.AdminSeoServiceImpl;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.admin.termRelation.entity.TermRelation;
import com.cellosquare.adminapp.admin.terminology.entity.Terminology;
import com.cellosquare.adminapp.admin.terminology.service.impl.TerminologyServiceImpl;
import com.cellosquare.adminapp.admin.terminology.vo.TerminologyUploadVO;
import com.cellosquare.adminapp.common.adminAnnotion.CleanCacheAnnotion;
import com.cellosquare.adminapp.common.listener.AntistopExcelUploadListener;
import com.cellosquare.adminapp.common.listener.TermExcelUploadListener;
import com.google.common.collect.Maps;
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
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author walker
 * @since 2023-03-68 09:15:21
 */
@Controller
@RequestMapping("/celloSquareAdmin/antistop")
public class AntistopController {
    @Autowired
    private AntistopServiceImpl antistopService;
    @Autowired
    private AdminSeoServiceImpl adminSeoServiceImpl;
    @Autowired
    private AntistopRelationServiceImpl antistopRelationService;
    @Autowired
    private TerminologyServiceImpl terminologyService;
    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") AntistopVO vo) {
        Page<Antistop> page = antistopService.lambdaQuery()
                .like(StrUtil.isNotEmpty(vo.getSearchValue()), Antistop::getTerminologyName,vo.getSearchValue())
                .orderByDesc(Antistop::getId)
                .page(new Page<Antistop>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        model.addAttribute("totalCount", page.getTotal());
        model.addAttribute("list", AntistopConver.INSTANCT.getAntistopVOs(page.getRecords()));
        return "admin/basic/antistop/list";
    }

    @PostMapping("/registerForm.do")
    public String registForm(HttpServletRequest request, Model model,
                             @ModelAttribute("vo") AntistopVO vo)  {
        HttpSession session = request.getSession();
        session.removeAttribute("detail");
        session.removeAttribute("adminSeoVO");
        session.removeAttribute("attachFileList");
        session.removeAttribute("vo");
        model.addAttribute("contIU", "I");
        return "admin/basic/antistop/registerForm";
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
                           @ModelAttribute("vo") AntistopVO vo, MultipartHttpServletRequest muServletRequest)  {
        try {
            Boolean register = antistopService.register( request,response,vo,muServletRequest);
            if (register) {
                return "redirect:"+ XmlPropertyManager.getPropertyValue("system.admin.path")+"/antistop/list.do";
            }
        } catch (Exception e) {
            return "admin/basic/antistop/registerForm";
        }
        return "admin/basic/antistop/registerForm";
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
                         @ModelAttribute("vo") AntistopVO vo)  {

        Antistop Antistop = antistopService.getById(vo.getId());
        if (Objects.isNull(Antistop)) {
            return "admin/basic/antistop/detail";
        }
        // seo 정보 가져오기
        AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(new AdminSeoVO(){{
            if (Objects.nonNull(Antistop.getMetaSeqNo())) {
                setMetaSeqNo(Antistop.getMetaSeqNo().toString());
            }
        }});

        HttpSession session = request.getSession();
        AntistopVO advertisingVO = AntistopConver.INSTANCT.getAntistopVO(Antistop);
        session.setAttribute("detail",advertisingVO );
        session.setAttribute("adminSeoVO", adminSeoVO);
        session.setAttribute("vo", vo);
        model.addAttribute("detail", advertisingVO);
        model.addAttribute("adminSeoVO", adminSeoVO);
        model.addAttribute("vo", vo);
        return "admin/basic/antistop/detail";
    }

    @PostMapping("/updateForm.do")
    public String updateForm(HttpServletRequest request, HttpServletResponse response, Model model,
                             @ModelAttribute("vo") AntistopVO vo) {

        Antistop Antistop = antistopService.getById(vo.getId());
        // seo 정보 가져오기
        AdminSeoVO adminSeoVO = adminSeoServiceImpl.getSeoSelect(new AdminSeoVO(){{
            setMetaSeqNo(vo.getMetaSeqNo());
        }});
        AntistopVO advertisingVO = AntistopConver.INSTANCT.getAntistopVO(Antistop);
        model.addAttribute("detail",advertisingVO );
        model.addAttribute("adminSeoVO", adminSeoVO);
        model.addAttribute("contIU", "U");
        return "admin/basic/antistop/registerForm";
    }

    @PostMapping("/update.do")
    @CleanCacheAnnotion
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,
                           MultipartHttpServletRequest muServletRequest, Model model,
                           @ModelAttribute("vo") AntistopVO vo) {

        try {
            Boolean update =  antistopService.doUpdate(request,response, vo,muServletRequest);
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
            return "admin/basic/antistop/registerForm";
        }
    }

    @PostMapping("/doSortOrder.do")
    public String doSortOrder(HttpServletRequest request, HttpServletResponse response, Model model,
                              @ModelAttribute("vo") AntistopVO vo) {
        try {
            for(int i = 0; i < vo.getListblogSeq().length; i++) {
                long id = Long.parseLong(vo.getListblogSeq()[i]);
                Antistop Antistop = antistopService.getById(id);
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
                            Antistop.setOrdb(vo.getListSortOrder()[i]);
                        } else { //1부터 999까지만 값을 체크 그값이 아니라면 오류메세지
                            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
                            return "admin/basic/antistop/list";
                        }
                    } else { //숫자가 아니면 오류메세지
                        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
                        return "admin/basic/antistop/list";
                    }
                }

                antistopService.updateById(Antistop);
            }

            Map<String, String> hmParam = new HashMap<String, String>();
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
            model.addAttribute("url", "./list.do");
            model.addAttribute("hmParam", hmParam);

            return "admin/common/message";

        }catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
            return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/antistop/list.do";
        }
    }

    @PostMapping("/doDelete.do")
    @CleanCacheAnnotion
    public String delete(HttpServletRequest request, HttpServletResponse response, Model model,
                         @ModelAttribute("vo") AntistopVO vo) {
        try {
            antistopService.removeById(vo.getId());
            // seo정보 삭제
            adminSeoServiceImpl.doSeoDelete(vo);
            antistopRelationService.remove(antistopRelationService.lambdaQuery().eq(AntistopRelation::getTermId,vo.getId()).getWrapper());
            Map<String, String> hmParam = new HashMap<String, String>();
            hmParam.put("id", vo.getId().toString());
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
            model.addAttribute("url", "./list.do");
            model.addAttribute("hmParam", hmParam);

            return "admin/common/message";

        } catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
            return "admin/basic/antistop/detail";
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
        EasyExcel.read(file.getInputStream(), AntistopUploadVO.class, new AntistopExcelUploadListener(terminologyService,antistopService,adminSeoServiceImpl)).sheet().doRead();
        return "success";
    }

    @GetMapping("/download.do")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("关键词模板", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), AntistopUploadVO.class).sheet("关键词模板").doWrite(data());
    }

    private List<AntistopUploadVO> data() {
        List<AntistopUploadVO> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            AntistopUploadVO data = new AntistopUploadVO();
            list.add(data);
        }
        return list;
    }

}

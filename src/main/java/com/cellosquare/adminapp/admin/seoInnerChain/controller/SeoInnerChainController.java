package com.cellosquare.adminapp.admin.seoInnerChain.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.cellosquare.adminapp.admin.antistop.entity.Antistop;
import com.cellosquare.adminapp.admin.seoInnerChain.conver.SeoInnerChainConver;
import com.cellosquare.adminapp.admin.seoInnerChain.entity.SeoInnerChain;
import com.cellosquare.adminapp.admin.seoInnerChain.service.impl.SeoInnerChainServiceImpl;
import com.cellosquare.adminapp.admin.seoInnerChain.vo.SeoInnerChainVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author walker
 * @since 2023-03-66 09:03:29
 */
@Controller
@RequestMapping("/celloSquareAdmin/seoInnerChain")
public class SeoInnerChainController {
    @Autowired
    private SeoInnerChainServiceImpl seoInnerChainService;
    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") SeoInnerChainVO vo) {
        Page<SeoInnerChain> page = seoInnerChainService.lambdaQuery()
                .like(StrUtil.isNotEmpty(vo.getSearchValue()), SeoInnerChain::getKeyWord,vo.getSearchValue())
                .page(new Page<SeoInnerChain>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        model.addAttribute("totalCount", page.getTotal());
        model.addAttribute("list", SeoInnerChainConver.INSTANCT.getSeoInnerChainVOs(page.getRecords()));
        return "admin/basic/seoInnerChain/list";
    }

    @PostMapping("/registerForm.do")
    public String registForm(HttpServletRequest request, Model model,
                             @ModelAttribute("vo") SeoInnerChainVO vo)  {
        HttpSession session = request.getSession();
        session.removeAttribute("detail");
        session.removeAttribute("adminSeoVO");
        session.removeAttribute("attachFileList");
        session.removeAttribute("vo");
        model.addAttribute("contIU", "I");
        return "admin/basic/seoInnerChain/registerForm";
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
    public String register(HttpServletRequest request, HttpServletResponse response, Model model,
                           @ModelAttribute("vo") SeoInnerChainVO vo, MultipartHttpServletRequest muServletRequest)  {
        try {
            Boolean register = seoInnerChainService.register(request, response, vo, muServletRequest);
            if (register) {
                return "redirect:"+ XmlPropertyManager.getPropertyValue("system.admin.path")+"/seoInnerChain/list.do";
            }
        } catch (Exception e) {
            return "admin/basic/seoInnerChain/registerForm";
        }
        return "admin/basic/seoInnerChain/registerForm";
    }
}

package com.cellosquare.adminapp.admin.registerStatistics.controller;


import com.cellosquare.adminapp.admin.helpSupport.vo.HelpSupportVo;
import com.cellosquare.adminapp.admin.registerStatistics.service.IRegisterButtonService;
import com.cellosquare.adminapp.admin.registerStatistics.vo.RegisterButtonVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hugo
 * @since 2023-05-146 08:47:10
 */
@Controller
@Slf4j
@RequestMapping("/celloSquareAdmin/registerButton")
public class RegisterButtonController {
    @Autowired
    private IRegisterButtonService registerButtonServiceImpl;

    /**
     * 查询list
     *
     * @param model
     * @param vo
     * @return
     */
    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") RegisterButtonVo vo) {
        registerButtonServiceImpl.getList(model, vo);
        return "admin/basic/registerButton/list";
    }

    /**
     * 导出
     *
     * @param request
     * @param response
     * @param vo
     * @throws Exception
     */
    @GetMapping("/download.do")
    public void download(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("vo") RegisterButtonVo vo) throws Exception {
        registerButtonServiceImpl.excelDownLoad(request, response, vo);
    }

    @ResponseBody
    @GetMapping("/downloadCount.do")
    public int downloadCount(@ModelAttribute("vo") RegisterButtonVo vo) {
        return registerButtonServiceImpl.downloadCount(vo);
    }

}

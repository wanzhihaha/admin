package com.cellosquare.adminapp.admin.registerStatistics.controller;


import com.cellosquare.adminapp.admin.registerStatistics.entity.RegisterAppSuccess;
import com.cellosquare.adminapp.admin.registerStatistics.service.IRegisterAppSuccessService;
import com.cellosquare.adminapp.admin.registerStatistics.vo.RegisterAppSuccessVo;
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

@Controller
@Slf4j
@RequestMapping("/celloSquareAdmin/registerAppSuccess")
public class RegisterAppSuccessController {
    @Autowired
    private IRegisterAppSuccessService registerAppSuccessServiceImpl;

    /**
     * 查询list
     *
     * @param model
     * @param vo
     * @return
     */
    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") RegisterAppSuccessVo vo) {
        registerAppSuccessServiceImpl.getList(model, vo);
        return "admin/basic/registerAppSuccess/list";
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
    public void download(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("vo") RegisterAppSuccessVo vo) throws Exception {
        registerAppSuccessServiceImpl.excelDownLoad(request, response, vo);
    }

    @ResponseBody
    @GetMapping("/downloadCount.do")
    public int downloadCount(@ModelAttribute("vo") RegisterAppSuccessVo vo) {
        return registerAppSuccessServiceImpl.downloadCount(vo);
    }
}

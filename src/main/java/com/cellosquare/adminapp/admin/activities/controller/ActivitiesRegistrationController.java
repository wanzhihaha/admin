package com.cellosquare.adminapp.admin.activities.controller;


import com.cellosquare.adminapp.admin.activities.service.IActivitiesRegistrationService;
import com.cellosquare.adminapp.admin.activities.vo.ActivitiesRegistrationVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hugo
 * @since 2023-03-68 14:14:19
 */
@Controller
@Slf4j
@RequestMapping("/celloSquareAdmin/activitiesRegistration")
public class ActivitiesRegistrationController {

    @Autowired
    private IActivitiesRegistrationService activitiesRegistrationServiceImpl;

    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") ActivitiesRegistrationVo vo) {
        activitiesRegistrationServiceImpl.getList(model,vo);
        return "admin/basic/activitiesRegistration/list";
    }
    @ResponseBody
    @GetMapping("/downloadCount.do")
    public int downloadCount(@ModelAttribute("vo") ActivitiesRegistrationVo vo) {
        return activitiesRegistrationServiceImpl.downloadCount(vo);
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
    public void download(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("vo") ActivitiesRegistrationVo vo) throws Exception {
        activitiesRegistrationServiceImpl.excelDownLoad(request, response, vo);
    }

}

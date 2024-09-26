package com.cellosquare.adminapp.admin.clickStatistics.controller;


import com.cellosquare.adminapp.admin.clickStatistics.service.IClickStatisticsService;
import com.cellosquare.adminapp.admin.clickStatistics.vo.ClickStatisticsVo;
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
 *  前端控制器
 * </p>
 *
 * @author walker
 * @since 2024-03-71 09:13:54
 */
@Controller
@Slf4j
@RequestMapping("/celloSquareAdmin/clickStatistics")
public class ClickStatisticsController {


    @Autowired
    private IClickStatisticsService clickStatisticsServiceImpl;

    /**
     * 查询list
     *
     * @param model
     * @param vo
     * @return
     */
    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") ClickStatisticsVo vo) {
        clickStatisticsServiceImpl.getList(model, vo);
        return "admin/basic/clickStatistics/list";
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
    public void download(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("vo") ClickStatisticsVo vo) throws Exception {
        clickStatisticsServiceImpl.excelDownLoad(request, response, vo);
    }

    @ResponseBody
    @GetMapping("/downloadCount.do")
    public int downloadCount(@ModelAttribute("vo") ClickStatisticsVo vo) {
        return clickStatisticsServiceImpl.downloadCount(vo);
    }
    
}

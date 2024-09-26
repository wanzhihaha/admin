package com.cellosquare.adminapp.admin.apihistory.controller;


import cn.hutool.http.HttpUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.cellosquare.adminapp.admin.apihistory.entity.ApiHistory;
import com.cellosquare.adminapp.admin.apihistory.service.IApiHistoryService;
import com.cellosquare.adminapp.common.adminAnnotion.CleanCacheAnnotion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author
 * @since 2023-08-230 11:03:00
 */
@Controller
@RequestMapping("/celloSquareAdmin/apiHistory")
public class ApiHistoryController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IApiHistoryService apiHistoryServiceImpl;

    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") ApiHistory vo) {
        apiHistoryServiceImpl.getList(model, vo);
        return "admin/basic/apiHistory/list";
    }

    @GetMapping("/detail.do")
    public String detail(Model model, @ModelAttribute("vo") ApiHistory vo) {
        apiHistoryServiceImpl.getDetail(model, vo);
        return "admin/basic/apiHistory/detail";
    }
    @GetMapping("/siteMap.do")
    @ResponseBody
    @CleanCacheAnnotion
    public String siteMap() throws Exception {
        String celloSquareUrl = XmlPropertyManager.getPropertyValue("celloSquare.website.prefix");
        String url1 = celloSquareUrl + "/api/sitemap/generate";
        String url2 = celloSquareUrl + "/api/sitemap/dieLink";
        String s1 = HttpUtil.get(url1);
        String s2 = HttpUtil.get(url2);
        logger.warn("siteMap log 1 "+s1);
        logger.warn("siteMap log 2 "+s2);
        return s1+"/n"+s2;
    }
}

package com.cellosquare.adminapp.admin.counselling;


import com.cellosquare.adminapp.admin.counselling.service.CounsellingInfoService;
import com.cellosquare.adminapp.admin.counselling.vo.CounsellingInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AdminCounsellingInfoController {

    @Autowired
    CounsellingInfoService counsellingInfoServiceImpl;

    @GetMapping("/celloSquareAdmin/counselling/list.do")
    public String list(Model model, @ModelAttribute("vo") CounsellingInfoVO vo) {
        counsellingInfoServiceImpl.jumpList(model, vo);
        return "admin/basic/counselling/list";
    }

    @ResponseBody
    @GetMapping("/celloSquareAdmin/counselling/downloadCount.do")
    public int count(HttpServletRequest request, HttpServletResponse response) {
        String category = request.getParameter("category");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String source = request.getParameter("source");
        CounsellingInfoVO counsellingInfoVO = new CounsellingInfoVO();
        counsellingInfoVO.setCategory(category);
        counsellingInfoVO.setStartDate(startDate + " 00:00:00");
        counsellingInfoVO.setEndDate(endDate + " 23:59:59");
        counsellingInfoVO.setSource(source);
        int count = counsellingInfoServiceImpl.selectUserInfoCount(counsellingInfoVO);
        return count;
    }

    @GetMapping("/celloSquareAdmin/counselling/download.do")
    public void excelDownLoad(HttpServletRequest request, HttpServletResponse response) throws Exception {
        counsellingInfoServiceImpl.excelDownLoad(request, response);
    }
}

package com.cellosquare.adminapp.admin.estimate;

import com.bluewaves.lab.util.DateUtil;
import com.cellosquare.adminapp.admin.estimate.service.AdminEstimateService;
import com.cellosquare.adminapp.admin.estimate.vo.AdminEstimateVO;
import com.cellosquare.adminapp.common.session.SessionManager;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
public class AdminEstimateController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminEstimateService adminEstimateServiceImpl;


    private void setDate(AdminEstimateVO vo) {
        if (StringUtils.isNotEmpty(vo.getStatDate())) {
            vo.setSearchBeginDe(vo.getStatDate() + " 00:00:00");
        }
        if (StringUtils.isNotEmpty(vo.getEndDate())) {
            vo.setSearchEndDe(vo.getEndDate() + " 23:59:59");
        }
    }

    /**
     * jump to quote DB table page
     *
     * @param model
     * @param vo
     * @return
     */
    @GetMapping("/celloSquareAdmin/estimate/list.do")
    public String list(Model model, @ModelAttribute("vo") AdminEstimateVO vo) {
        setDate(vo);
        int totalCount = adminEstimateServiceImpl.getTotalCount(vo);
        List<AdminEstimateVO> list = new ArrayList<>();

        if (totalCount > 0) {
            list = adminEstimateServiceImpl.getListByPage(vo);
        }

        model.addAttribute("totalCount", totalCount);
        model.addAttribute("list", list);

        return "admin/basic/estimate/list";
    }

    /**
     * quote history excel download
     *
     * @param response
     * @param model
     * @param vo
     */
    @ResponseBody
    @PostMapping("/celloSquareAdmin/estimate/excelDownLoad.do")
    public void excelDownLoad(HttpServletResponse response, AdminEstimateVO vo) throws Exception {
        logger.debug("AdminEstimateController :: excelDownLoad");
        vo.setLangCd(SessionManager.getAdminSessionForm().getLangCd());
        setDate(vo);
        adminEstimateServiceImpl.quoteExcelDownload(vo, response);
    }
}

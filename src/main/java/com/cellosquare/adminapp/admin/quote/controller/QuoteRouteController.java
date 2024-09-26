package com.cellosquare.adminapp.admin.quote.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.cellosquare.adminapp.admin.quote.cover.QuoteVoConver;
import com.cellosquare.adminapp.admin.quote.entity.QuoteNode;
import com.cellosquare.adminapp.admin.quote.entity.vo.AdminNationVO;
import com.cellosquare.adminapp.admin.quote.entity.vo.AdminNodeVO;
import com.cellosquare.adminapp.admin.quote.entity.vo.AdminRouteVO;
import com.cellosquare.adminapp.admin.quote.service.IQuoteNationService;
import com.cellosquare.adminapp.admin.quote.service.IQuoteNodeService;
import com.cellosquare.adminapp.admin.quote.service.IQuoteRouteService;
import com.cellosquare.adminapp.common.constant.NodeSourceEnum;
import com.cellosquare.adminapp.common.constant.RoutStatusEnum;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.vo.PageBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hugo
 * @since 2023-06-157 09:11:30
 */
@Controller
@RequestMapping("/celloSquareAdmin/route")
@Slf4j
public class QuoteRouteController {

    @Autowired
    private IQuoteRouteService quoteRouteServiceImpl;
    @Autowired
    private IQuoteNodeService quoteNodeServiceImpl;
    @Autowired
    private IQuoteNationService quoteNationServiceImpl;


    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") AdminRouteVO vo) {
        quoteRouteServiceImpl.getList(model, vo);
        return "admin/basic/route/list";
    }

    @GetMapping("/list-node-ajaxList.do")
    @ResponseBody
    public String ajaxNodeList(@ModelAttribute("vo") AdminNodeVO vo) {
        List<AdminNationVO> nationList = quoteNationServiceImpl.getNationByLangCd();
        Page<QuoteNode> page = quoteNodeServiceImpl.getInnerPage(vo);
        List<AdminNodeVO> list = page.getRecords().stream().map(QuoteVoConver.INSTANCT::getAdminNodeVO).collect(Collectors.toList());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nationList", nationList);
        jsonObject.put("list", list);
        jsonObject.put("searchNation", vo.getNationSeqNo());
        jsonObject.put("totalCount", page.getTotal());
        PageBean pageBean = new PageBean(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage()), Integer.valueOf(String.valueOf(page.getTotal())));
        jsonObject.put("pageBean", pageBean);
        return jsonObject.toString();
    }


    @PostMapping("/registerForm.do")
    public String registForm(Model model, @ModelAttribute("vo") AdminRouteVO vo) {
        vo.setPage("1");
        AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
        model.addAttribute("contIU", "I");
//        model.addAttribute("nationCd", SystemConstant.LANG_QUOTE_CD.get(sessionForm.getLangCd()));
        return "admin/basic/route/registerForm";
    }


    @PostMapping("/register.do")
    public String register(HttpServletRequest request, @ModelAttribute("vo") AdminRouteVO vo) {
        vo.setRouteSource(NodeSourceEnum.E.getCd());
        vo.setNewFlag(RoutStatusEnum.N.getCd());
        AdminRouteVO detail = quoteRouteServiceImpl.getDetailByCondition(vo);
        if (null != detail) {
            ActionMessageUtil.setActionMessage(request, "已存在相同的线路我，请重试", null, null, null, true);
            return "admin/basic/route/registerForm";
        }
        quoteRouteServiceImpl.register(vo);
        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
        return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/route/list.do";
    }


    @PostMapping("/updateForm.do")
    public String updateForm(Model model, @ModelAttribute("vo") AdminRouteVO vo) {
        vo.setPage("1");
        AdminRouteVO detailVO = quoteRouteServiceImpl.getDetail(vo);
        model.addAttribute("detail", detailVO);
        model.addAttribute("contIU", "U");
        return "admin/basic/route/registerForm";
    }


    @PostMapping("/update.do")
    public String doUpdate(Model model, @ModelAttribute("vo") AdminRouteVO vo) {
        quoteRouteServiceImpl.doUpdate(vo);
        Map<String, String> hmParam = new HashMap<>();
        hmParam.put("routeSeqNo", vo.getRouteSeqNo());
        model.addAttribute("msg_type", ":submit");
        model.addAttribute("method", "post");
        model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
        model.addAttribute("url", "./detail.do");
        model.addAttribute("hmParam", hmParam);
        return "admin/common/message";

    }
/*
    @PostMapping("/updateStatus.do")
    public String updateStatus(HttpServletRequest request, Model model, @ModelAttribute("vo") AdminRouteVO vo) {

        try {
            AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
            vo.setLangCd(sessionForm.getLangCd());
            vo.setUpdPersonId(sessionForm.getAdminId());
            String[] split = vo.getListRouteSeqNo().split(",");
            List<Integer> routeSeqNoList = new ArrayList<>();
            for (String s : split) {
                routeSeqNoList.add(Integer.valueOf(s));
            }
            adminRouteServiceImpl.updateStatus(routeSeqNoList, vo.getUseYn());
        } catch (Exception e) {
            e.printStackTrace();
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"), null, null, null, true);
        }
        model.addAttribute("msg_type", ":submit");
        model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
        model.addAttribute("url", "./list.do");
        return "admin/common/message";

    }*/


    @PostMapping("/detail.do")
    public String detail(Model model, @ModelAttribute("vo") AdminRouteVO vo) {
        AdminRouteVO detailVO = quoteRouteServiceImpl.getDetail(vo);
        model.addAttribute("detail", detailVO);
        return "admin/basic/route/detail";
    }


    @PostMapping("/doDelete.do")
    public String delete(Model model, @ModelAttribute("vo") AdminRouteVO vo) {
        quoteRouteServiceImpl.delete(vo);
        Map<String, String> hmParam = new HashMap<String, String>();
        model.addAttribute("msg_type", ":submit");
        model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
        model.addAttribute("url", "./list.do");
        return "admin/common/message";
    }

    @GetMapping("/taskTest.do")
    @ResponseBody
    public String taskTest() {
        quoteRouteServiceImpl.callRouteData();
        return "success";
    }

/*    @Autowired
    private QuoteRouteUpdateTask quoteRouteUpdateTask;

    @GetMapping("/taskTest.do")
    @ResponseBody
    public String taskTest() throws UnknownHostException, InterruptedException {

        quoteRouteUpdateTask.updateQuoteRoute();
        return "success";
    }*/

}

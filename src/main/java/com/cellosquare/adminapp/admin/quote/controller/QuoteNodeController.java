package com.cellosquare.adminapp.admin.quote.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.cellosquare.adminapp.admin.quote.cover.QuoteVoConver;
import com.cellosquare.adminapp.admin.quote.entity.QuoteNation;
import com.cellosquare.adminapp.admin.quote.entity.vo.AdminNationVO;
import com.cellosquare.adminapp.admin.quote.entity.vo.AdminNodeVO;
import com.cellosquare.adminapp.admin.quote.service.IQuoteNationService;
import com.cellosquare.adminapp.admin.quote.service.IQuoteNodeService;
import com.cellosquare.adminapp.common.constant.IsHotEnum;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.util.CommonMessageModel;
import com.cellosquare.adminapp.common.vo.PageBean;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
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
@RequestMapping("/celloSquareAdmin/node")
public class QuoteNodeController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IQuoteNodeService quoteNodeServiceImpl;

    @Autowired
    private IQuoteNationService quoteNationServiceImpl;


    /**
     * 상품 리스트
     *
     * @param request
     * @param response
     * @param model
     * @param vo
     * @return
     */

    @GetMapping("/list.do")
    public String list(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("vo") AdminNodeVO vo) {
        quoteNodeServiceImpl.getList(model, vo);
        return "admin/basic/node/list";
    }

    @GetMapping("/list-nation-ajaxList.do")
    @ResponseBody
    public String ajaxNationList(@ModelAttribute("vo") AdminNationVO vo) {
        Page<QuoteNation> list = quoteNationServiceImpl.getList(vo);
        List<AdminNationVO> collect = list.getRecords().stream().map(QuoteVoConver.INSTANCT::getAdminNationVO).collect(Collectors.toList());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list", collect);
        jsonObject.put("currentPage", vo.getPage());
        jsonObject.put("totalCount", list.getTotal());
        PageBean pageBean = new PageBean(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage()), Integer.parseInt(String.valueOf(list.getTotal())));
        jsonObject.put("pageBean", pageBean);
        return jsonObject.toString();
    }

    @PostMapping("/registerForm.do")
    public String registForm(Model model, @ModelAttribute("vo") AdminNodeVO vo) {
        vo.setPage("1");
        model.addAttribute("contIU", "I");
        return "admin/basic/node/registerForm";
    }


    @PostMapping("/register.do")
    public String register(HttpServletRequest request, @ModelAttribute("vo") AdminNodeVO vo) {
        vo.setNodeCd(vo.getNodeCd().trim());
        AdminNodeVO detail = quoteNodeServiceImpl.getDetail(vo);
        if (null != detail) {
            ActionMessageUtil.setActionMessage(request, "已存在，请重新输入cd", null, null, null, true);
            return "admin/basic/node/registerForm";
        }
        try {
            quoteNodeServiceImpl.regist(vo);
        } catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"), null, null, null, true);
            return "admin/basic/node/registerForm";
        }
        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
        return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/node/list.do";
    }


    @PostMapping("/updateForm.do")
    public String updateForm(Model model, @ModelAttribute("vo") AdminNodeVO vo) {
        AdminSessionForm adminSessionForm = SessionManager.getAdminSessionForm();
        vo.setUpdPersonId(adminSessionForm.getAdminId());
        AdminNodeVO detailVO = quoteNodeServiceImpl.getDetail(vo);
        if (!Objects.isNull(detailVO)) {
            if (StringUtils.isNotEmpty(detailVO.getSearchKeyWord())) {
                String[] split = detailVO.getSearchKeyWord().split(",");
                detailVO.setListSearchKeyWord(split);
            }
        }
        model.addAttribute("detail", detailVO);
        model.addAttribute("contIU", "U");
        return "admin/basic/node/registerForm";
    }

    @PostMapping("/update.do")
    @Transactional
    public String doUpdate(HttpServletRequest request, Model model, @ModelAttribute("vo") AdminNodeVO vo) {
        quoteNodeServiceImpl.doUpdate(vo);
        Map<String, String> hmParam = new HashMap<>();
        hmParam.put("nodeCd", vo.getNodeCd());
        hmParam.put("continentCd", vo.getContinentCd());
        hmParam.put("searchType1", vo.getSearchType1());
        model.addAttribute("msg_type", ":submit");
        model.addAttribute("method", "post");
        model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
        model.addAttribute("url", "./detail.do");
        model.addAttribute("hmParam", hmParam);
        return "admin/common/message";
    }

    /**
     * 상세보기
     *
     * @param request
     * @param response
     * @param vo
     * @param model
     * @return
     */
    @PostMapping("/detail.do")
    public String detail(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("vo") AdminNodeVO vo) {
        AdminNodeVO detailVO = quoteNodeServiceImpl.getDetail(vo);
        model.addAttribute("detail", detailVO);
        return "admin/basic/node/detail";
    }


    @PostMapping("/doDelete.do")
    @Transactional
    public String delete(HttpServletRequest request, Model model, @ModelAttribute("vo") AdminNodeVO vo) {
        quoteNodeServiceImpl.delete(vo);
        Map<String, String> hmParam = new HashMap<String, String>();
        hmParam.put("searchType1", vo.getSearchType1());
        hmParam.put("continentCd", vo.getContinentCd());
        return CommonMessageModel.setModel(XmlMessageManager.getMessageValue("message.common.delete.success"), "./list.do", "get", model
                , hmParam);
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
    public void download(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("vo") AdminNodeVO vo) throws Exception {
        quoteNodeServiceImpl.excelDownLoad(request, response, vo);
    }

    @ResponseBody
    @GetMapping("/downloadCount.do")
    public int downloadCount(@ModelAttribute("vo") AdminNodeVO vo) {
        return quoteNodeServiceImpl.downloadCount(vo);
    }

    @PostMapping("/setHotOrNot.do")
    public String setHotOrNot(Model model, @ModelAttribute("vo") AdminNodeVO vo) {
        model.addAttribute("param", new HashMap() {
            {
                put("searchValue", vo.getSearchValue());
                put("searchType", vo.getSearchType());
            }
        });
        HashMap hashMap = new HashMap() {
            {
                put("searchType1", vo.getSearchType1());
                put("productMode", vo.getProductMode());
                put("continentCd", vo.getContinentCd());
            }
        };
        quoteNodeServiceImpl.setHotOrNot(vo);
        return CommonMessageModel.setModel(model, hashMap);
    }

    @PostMapping("/doSortOrder.do")
    public String doSortOrder(Model model, @ModelAttribute("vo") AdminNodeVO vo) {
        List<AdminNodeVO> adminNationVOList = new ArrayList<>();
        for (int i = 0; i < vo.getListIds().length; i++) {
            AdminNodeVO adminNodeVO = new AdminNodeVO();
            adminNodeVO.setId(vo.getListIds()[i]);
            adminNodeVO.setOrdb(vo.getListSortOrder()[i]);
            adminNationVOList.add(adminNodeVO);
        }
        quoteNodeServiceImpl.doSortOrder(adminNationVOList);
        model.addAttribute("param", new HashMap() {
            {
                put("searchValue", vo.getSearchValue());
                put("searchType", vo.getSearchType());
            }
        });
        return CommonMessageModel.setModel(model, new HashMap() {
            {
                put("searchType1", vo.getSearchType1());
                put("productMode", vo.getProductMode());
                put("continentCd", vo.getContinentCd());
            }
        });
    }
}

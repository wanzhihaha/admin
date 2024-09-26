package com.cellosquare.adminapp.admin.quote.controller;


import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.cellosquare.adminapp.admin.quote.cover.QuoteVoConver;
import com.cellosquare.adminapp.admin.quote.entity.QuoteReturnReason;
import com.cellosquare.adminapp.admin.quote.entity.vo.QuoteReturnReasonVo;
import com.cellosquare.adminapp.admin.quote.service.IQuoteReturnReasonService;
import com.cellosquare.adminapp.common.util.CommonMessageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hugo
 * @since 2023-06-157 09:11:30
 */
@Controller
@RequestMapping("/celloSquareAdmin/returnReason")
@Slf4j
public class QuoteReturnReasonController {

    @Autowired
    private IQuoteReturnReasonService quoteReturnReasonServiceImpl;

    /**
     * 查询list
     *
     * @param model
     * @param vo
     * @return
     */
    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") QuoteReturnReasonVo vo) {
        quoteReturnReasonServiceImpl.getList(model, vo);
        return "admin/basic/returnReason/list";
    }

    @PostMapping("/detail.do")
    public String detail(Model model, @ModelAttribute("vo") QuoteReturnReasonVo vo) {
        quoteReturnReasonServiceImpl.detail(model, vo);
        return "admin/basic/returnReason/detail";
    }


    @PostMapping("/registerForm.do")
    public String registerForm(Model model, @ModelAttribute("vo") QuoteReturnReasonVo vo) {
        return "admin/basic/returnReason/registerForm";
    }

    /**
     * 新增数据接口
     *
     * @param request
     * @param vo
     * @return
     */
    @PostMapping("/register.do")
    public String doWrite(HttpServletRequest request, @ModelAttribute("vo") QuoteReturnReasonVo vo) {
        quoteReturnReasonServiceImpl.doWrite(vo);
        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
        return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/returnReason/list.do";

    }


    @PostMapping("/updateForm.do")
    public String updateForm(Model model, @ModelAttribute("vo") QuoteReturnReasonVo vo) {
        QuoteReturnReasonVo detailVO = QuoteVoConver.INSTANCT.getQuoteReturnReasonVo(quoteReturnReasonServiceImpl.lambdaQuery().eq(QuoteReturnReason::getId, Long.valueOf(vo.getId())).one());
        model.addAttribute("detail", detailVO);
        model.addAttribute("contIU", "U");
        return "admin/basic/returnReason/registerForm";
    }

    /**
     * 修改
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/update.do")
    public String doUpdate(HttpServletRequest request, Model model, @ModelAttribute("vo") QuoteReturnReasonVo vo) {
        quoteReturnReasonServiceImpl.doUpdate(request, vo);
        Map<String, Object> hmParam = new HashMap<>();
        hmParam.put("id", vo.getId());
        model.addAttribute("msg_type", ":submit");
        model.addAttribute("method", "post");
        model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
        model.addAttribute("url", "./detail.do");
        model.addAttribute("hmParam", hmParam);
        return "admin/common/message";
    }

    /**
     * 删除
     *
     * @param vo
     * @return
     */
    @PostMapping("/doDelete.do")
    public String delete(Model model, @ModelAttribute("vo") QuoteReturnReasonVo vo) {
        quoteReturnReasonServiceImpl.doDelete(vo);
        Map<String, String> hmParam = new HashMap<String, String>();
        model.addAttribute("msg_type", ":submit");
        model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
        model.addAttribute("url", "./list.do");
        model.addAttribute("hmParam", hmParam);
        return "admin/common/message";

    }

    /**
     * 自定义排序
     *
     * @param request
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/doSortOrder.do")
    public String doSortOrder( Model model, @ModelAttribute("vo") QuoteReturnReasonVo vo) {
        List<QuoteReturnReasonVo> quoteReturnReasonVos = new ArrayList<>();
        for (int i = 0; i < vo.getListId().length; i++) {
            QuoteReturnReasonVo quoteReturnReasonVo = new QuoteReturnReasonVo();
            quoteReturnReasonVo.setId(vo.getListId()[i]);
            quoteReturnReasonVo.setOrdb(vo.getListOrdb()[i]);
            quoteReturnReasonVos.add(quoteReturnReasonVo);
        }
        quoteReturnReasonServiceImpl.doSortOrder(quoteReturnReasonVos);
        return CommonMessageModel.setModel(model, new HashMap() {
            {
            }
        });
    }

}

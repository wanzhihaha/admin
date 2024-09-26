package com.cellosquare.adminapp.admin.helpSupportMenu.controller;

import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.goods.vo.AdminProductsVO;
import com.cellosquare.adminapp.admin.helpSupportMenu.entity.HelpSupportMenu;
import com.cellosquare.adminapp.admin.helpSupportMenu.service.IHelpSupportMenuService;
import com.cellosquare.adminapp.admin.helpSupportMenu.vo.HelpSupportMenuVo;
import com.cellosquare.adminapp.common.adminAnnotion.CleanCacheAnnotion;
import com.cellosquare.adminapp.common.util.CommonMessageModel;
import com.cellosquare.adminapp.common.util.ExcetionUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
 * @since 2023-03-65 09:48:42
 */
@Controller
@Slf4j
@RequestMapping("/celloSquareAdmin/helpSupportMenu")
public class HelpSupportMenuController {
    @Autowired
    private IHelpSupportMenuService helpSupportMenuServiceImpl;

    @GetMapping("/getSMenu.do")
    @ResponseBody
    public List<HelpSupportMenu> getSMenu(Model model, @ModelAttribute("pId") String pId) {
        return helpSupportMenuServiceImpl.getSMenu(pId);
    }

    /**
     * 查询list
     *
     * @param model
     * @param vo
     * @return
     */
    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") HelpSupportMenuVo vo) {
        helpSupportMenuServiceImpl.getList(model, vo);
        return "admin/basic/helpSupportMenu/list";
    }

    /**
     * 详情
     *
     * @param model
     * @param vo
     * @return
     */
    // @PostMapping("/detail.do")
    @RequestMapping(value = "/detail.do", method = {RequestMethod.GET, RequestMethod.POST})
    public String detail(Model model, @ModelAttribute("vo") HelpSupportMenuVo vo) {
        helpSupportMenuServiceImpl.detail(model, vo);
        return "admin/basic/helpSupportMenu/detail";
    }


    /**
     * 跳转到新增页
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/registerForm.do")
    public String registerForm(Model model, @ModelAttribute("vo") HelpSupportMenuVo vo) {
        model.addAttribute("siblingNodes", helpSupportMenuServiceImpl.getAllMenuTop());
        model.addAttribute("detail", vo);
        return "admin/basic/helpSupportMenu/registerForm";
    }

    /**
     * 新增数据接口
     *
     * @param request
     * @param vo
     * @return
     */
    @PostMapping("/register.do")
    @CleanCacheAnnotion
    public String register(HttpServletRequest request, Model model, HttpServletResponse response, @ModelAttribute("vo") HelpSupportMenuVo vo) {
        String msg = XmlMessageManager.getMessageValue("message.common.write.success");
        String console = XmlMessageManager.getMessageValue("message.common.write.success");
        try {
            helpSupportMenuServiceImpl.register(request, response, vo);
        } catch (Exception e) {
            console = ExcetionUtil.geterrorinfofromexception(e);
            msg = XmlMessageManager.getMessageValue("message.common.write.fail");//+ ExcetionUtil.geterrorinfofromexception(e);
        }
        Map<String, String> hmParam = Maps.newHashMap();
        model.addAttribute("msg_type", ":submit");
        model.addAttribute("msg", msg);
        model.addAttribute("console", console);
        model.addAttribute("url", "./list.do");
        model.addAttribute("hmParam", hmParam);
        return "admin/common/message";
    }


    /**
     * 跳转到修改页
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/updateForm.do")
    public String updateForm(Model model, @ModelAttribute("vo") HelpSupportMenuVo vo) {
        helpSupportMenuServiceImpl.updateForm(model, vo);
        return "admin/basic/helpSupportMenu/registerForm";
    }

    /**
     * 修改
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/update.do")
    @CleanCacheAnnotion
    public String doUpdate(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("vo") HelpSupportMenuVo vo) {
        try {
            helpSupportMenuServiceImpl.doUpdate(request, response, vo);
            Map<String, Object> hmParam = new HashMap<>();
            hmParam.put("id", vo.getId());
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("method", "post");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
            model.addAttribute("url", "./detail.do");
            model.addAttribute("hmParam", hmParam);
            return "admin/common/message";
        } catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.modify.fail"),
                    null, null, null, true);
            return "admin/basic/helpSupportMenu/registerForm";
        }
    }

    /**
     * 删除
     *
     * @param request
     * @param vo
     * @return
     */
    @PostMapping("/doDelete.do")
    @CleanCacheAnnotion
    public String delete(HttpServletRequest request, Model model, @ModelAttribute("vo") HelpSupportMenuVo vo) {
        String msg = XmlMessageManager.getMessageValue("message.common.delete.success");
        try {
            helpSupportMenuServiceImpl.doDelete(vo);
        } catch (Exception e) {
            e.printStackTrace();
            msg = XmlMessageManager.getMessageValue("message.common.delete.fail") + e.getMessage();
        }
        Map<String, String> hmParam = new HashMap<String, String>();
//            hmParam.put("topId", vo.getTopId());
        model.addAttribute("msg_type", ":submit");
        model.addAttribute("msg", msg);
        model.addAttribute("url", "./list.do");
        model.addAttribute("hmParam", hmParam);
        return "admin/common/message";
    }

    @PostMapping("/doSortOrder.do")
    public String doSortOrder(Model model, @ModelAttribute("vo") HelpSupportMenuVo vo) {
        List<HelpSupportMenuVo> helpSupportMenuVos = new ArrayList<>();
        for (int i = 0; i < vo.getListId().length; i++) {
            HelpSupportMenuVo helpSupportMenuVo = new HelpSupportMenuVo();
            helpSupportMenuVo.setId(vo.getListId()[i]);
            helpSupportMenuVo.setOrdb(vo.getListOrdb()[i]);
            helpSupportMenuVos.add(helpSupportMenuVo);
        }
        helpSupportMenuServiceImpl.doSortOrder(helpSupportMenuVos);
        return CommonMessageModel.setModel(model, Maps.newHashMap());
    }


}

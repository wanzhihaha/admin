package com.cellosquare.adminapp.admin.helpSupport.controller;


import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.cellosquare.adminapp.admin.helpSupport.service.IHelpSupportService;
import com.cellosquare.adminapp.admin.helpSupport.vo.HelpSupportVo;
import com.cellosquare.adminapp.admin.helpSupportMenu.service.IHelpSupportMenuService;
import com.cellosquare.adminapp.common.adminAnnotion.CleanCacheAnnotion;
import com.cellosquare.adminapp.common.util.CommonMessageModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
 * @since 2023-03-65 09:27:10
 */
@Controller
@Slf4j
@RequestMapping("/celloSquareAdmin/helpSupport")
public class HelpSupportController {

    @Autowired
    private IHelpSupportService helpSupportServiceImpl;
    @Autowired
    private IHelpSupportMenuService helpSupportMenuServiceImpl;

    /**
     * 查询list
     *
     * @param model
     * @param vo
     * @return
     */
    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") HelpSupportVo vo) {
        helpSupportServiceImpl.getList(model, vo);
        return "admin/basic/helpSupport/list";
    }

    /**
     * 详情
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/detail.do")
    public String detail(Model model, @ModelAttribute("vo") HelpSupportVo vo) {
        helpSupportServiceImpl.detail(model, vo);
        return "admin/basic/helpSupport/detail";
    }


    /**
     * 跳转到新增页
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/registerForm.do")
    public String registerForm(Model model, @ModelAttribute("vo") HelpSupportVo vo) {
        model.addAttribute("siblingNodes", helpSupportMenuServiceImpl.getSMenu(vo.getTopId()));
        model.addAttribute("detail", vo);
        return "admin/basic/helpSupport/registerForm";
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
    public String register(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("vo") HelpSupportVo vo) {
        try {
            helpSupportServiceImpl.register(request, response, vo);
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/helpSupport/list.do?topId=" + vo.getTopId();
        } catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"),
                    null, null, null, true);
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/helpSupport/registerForm.do";
        }
    }


    /**
     * 跳转到修改页
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/updateForm.do")
    public String updateForm(Model model, @ModelAttribute("vo") HelpSupportVo vo) {
        helpSupportServiceImpl.updateForm(model, vo);
        return "admin/basic/helpSupport/registerForm";
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
    public String doUpdate(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("vo") HelpSupportVo vo) {
        try {
            helpSupportServiceImpl.doUpdate(request, response, vo);
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
            return "admin/basic/helpSupport/registerForm";
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
    public String delete(HttpServletRequest request, Model model, @ModelAttribute("vo") HelpSupportVo vo) {
        try {
            helpSupportServiceImpl.doDelete(vo);
            Map<String, String> hmParam = new HashMap<String, String>();
            hmParam.put("topId", vo.getTopId());
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
            model.addAttribute("url", "./list.do");
            model.addAttribute("hmParam", hmParam);
            return "admin/common/message";
        } catch (Exception e) {
            e.printStackTrace();
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/helpSupport/detail.do";
        }
    }

    @ResponseBody
    @GetMapping("/downloadCount.do")
    public int downloadCount(@ModelAttribute("vo") HelpSupportVo vo) {
        return helpSupportServiceImpl.downloadCount(vo);
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
    public void download(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("vo") HelpSupportVo vo) throws Exception {
        helpSupportServiceImpl.excelDownLoad(request, response, vo);
    }

    /**
     * 批量导入
     *
     * @param request
     * @param response
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/upload.do")
    public String upload(HttpServletRequest request, HttpServletResponse response, MultipartFile file, String topId) throws Exception {
        helpSupportServiceImpl.saveImportWord(file, request, response, topId);
        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
        return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/helpSupport/list.do?topId=" + topId;
    }

    /**
     * 下载模板
     */
    @GetMapping("/downloadTemplete.do")
    public void downloadTemplete(HttpServletRequest request, HttpServletResponse response) {
        helpSupportServiceImpl.downloadTemplete(request, response);
    }

    @PostMapping("/doSortOrder.do")
    public String doSortOrder(Model model, @ModelAttribute("vo") HelpSupportVo vo) {
        List<HelpSupportVo> helpSupportVos = new ArrayList<>();
        for (int i = 0; i < vo.getListId().length; i++) {
            HelpSupportVo helpSupportVo = new HelpSupportVo();
            helpSupportVo.setId(vo.getListId()[i]);
            helpSupportVo.setOrdb(vo.getListOrdb()[i]);
            helpSupportVos.add(helpSupportVo);
        }
        helpSupportServiceImpl.doSortOrder(helpSupportVos);
        return CommonMessageModel.setModel(model, new HashMap() {
            {
                put("topId", vo.getTopId());
            }
        });
    }

    @PostMapping("/batchDelete.do")
    @CleanCacheAnnotion
    public String batchDelete(Model model, @ModelAttribute("vo") HelpSupportVo vo) {
        helpSupportServiceImpl.batchDelete(vo);
        return CommonMessageModel.setModel(model, new HashMap() {
            {
                put("topId", vo.getTopId());
            }
        });
    }
}

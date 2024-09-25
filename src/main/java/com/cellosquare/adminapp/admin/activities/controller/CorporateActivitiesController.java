package com.cellosquare.adminapp.admin.activities.controller;


import cn.hutool.core.util.ObjectUtil;
import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.cellosquare.adminapp.admin.activities.entity.CorporateActivities;
import com.cellosquare.adminapp.admin.activities.service.ICorporateActivitiesService;
import com.cellosquare.adminapp.admin.activities.vo.CorporateActivitiesVo;
import com.cellosquare.adminapp.common.adminAnnotion.CleanCacheAnnotion;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.owasp.esapi.SafeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hugo
 * @since 2023-03-67 14:44:39
 */
@Controller
@Slf4j
@RequestMapping("/celloSquareAdmin/corporateActivities")
public class CorporateActivitiesController {
    @Autowired
    private ICorporateActivitiesService corporateActivitiesServiceImpl;


    /**
     * 查询list
     *
     * @param model
     * @param vo
     * @return
     */
    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") CorporateActivitiesVo vo) {
        corporateActivitiesServiceImpl.getList(model, vo);
        return "admin/basic/corporateActivities/list";
    }

    /**
     * 详情
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/detail.do")
    public String detail(Model model, @ModelAttribute("vo") CorporateActivitiesVo vo) {
        corporateActivitiesServiceImpl.detail(model, vo);
        return "admin/basic/corporateActivities/detail";
    }


    /**
     * 跳转到新增页
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/registerForm.do")
    public String registerForm(Model model, @ModelAttribute("vo") CorporateActivitiesVo vo) {
        model.addAttribute("detail", vo);
        return "admin/basic/corporateActivities/registerForm";
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
    public String register(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("vo") CorporateActivitiesVo vo, MultipartHttpServletRequest muServletRequest) {
        try {
            corporateActivitiesServiceImpl.register(request, response, vo, muServletRequest);
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/corporateActivities/list.do";
        } catch (Exception e) {
            e.printStackTrace();
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"),
                    null, null, null, true);
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/corporateActivities/registerForm.do";
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
    public String updateForm(Model model, @ModelAttribute("vo") CorporateActivitiesVo vo) {
        corporateActivitiesServiceImpl.updateForm(model, vo);
        return "admin/basic/corporateActivities/registerForm";
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
    public String doUpdate(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("vo") CorporateActivitiesVo vo, MultipartHttpServletRequest muServletRequest) {
        try {
            corporateActivitiesServiceImpl.doUpdate(request, response, vo,muServletRequest);
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
            return "admin/basic/corporateActivities/registerForm";
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
    public String delete(HttpServletRequest request, Model model, @ModelAttribute("vo") CorporateActivitiesVo vo) {
        try {
            corporateActivitiesServiceImpl.doDelete(vo);
            Map<String, String> hmParam = new HashMap<String, String>();
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
            model.addAttribute("url", "./list.do");
            model.addAttribute("hmParam", hmParam);
            return "admin/common/message";
        } catch (Exception e) {
            e.printStackTrace();
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/corporateActivities/detail.do";
        }
    }


    @ResponseBody
    @GetMapping("/imgView.do")
    public String imgView(HttpServletRequest request, HttpServletResponse response, Model model,
                          @ModelAttribute("vo") CorporateActivitiesVo vo) {
        CorporateActivities corporateActivities = corporateActivitiesServiceImpl.getById(Long.valueOf(vo.getId()));
        try {
            if (ObjectUtil.isNotEmpty(corporateActivities)) {
                FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
                SafeFile pcListFile = new SafeFile(corporateActivities.getListImgPath(), FilenameUtils.getName(corporateActivities.getListImgFileNm()));
                if (pcListFile.isFile()) {
                    fileDownLoadManager.fileFlush(pcListFile, corporateActivities.getListImgOrgFileNm());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.cellosquare.adminapp.admin.logisticsQa.controller;


import cn.hutool.core.util.ObjectUtil;
import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.cellosquare.adminapp.admin.goods.vo.AdminProductsVO;
import com.cellosquare.adminapp.admin.logisticsQa.entity.LogisticsQa;
import com.cellosquare.adminapp.admin.logisticsQa.vo.LogisticsQaVo;
import com.cellosquare.adminapp.admin.logisticsQa.service.ILogisticsQaService;
import com.cellosquare.adminapp.common.adminAnnotion.CleanCacheAnnotion;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.owasp.esapi.SafeFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hugo
 * @since 2023-03-67 09:03:09
 */
@Controller
@Slf4j
@RequestMapping("/celloSquareAdmin/logisticsQa")
public class LogisticsQaController {

    @Autowired
    private ILogisticsQaService logisticsQaServiceImpl;


    /**
     * 查询list
     *
     * @param model
     * @param vo
     * @return
     */
    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") LogisticsQaVo vo) {
        logisticsQaServiceImpl.getList(model, vo);
        return "admin/basic/logisticsQa/list";
    }

    /**
     * 详情
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/detail.do")
    public String detail(Model model, @ModelAttribute("vo") LogisticsQaVo vo) {
        logisticsQaServiceImpl.detail(model, vo);
        return "admin/basic/logisticsQa/detail";
    }


    /**
     * 跳转到新增页
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/registerForm.do")
    public String registerForm(Model model, @ModelAttribute("vo") LogisticsQaVo vo) {
        model.addAttribute("detail", vo);
        return "admin/basic/logisticsQa/registerForm";
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
    public String register(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("vo") LogisticsQaVo vo, MultipartHttpServletRequest muServletRequest) {
        try {
            logisticsQaServiceImpl.register(request, response, vo, muServletRequest);
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/logisticsQa/list.do";
        } catch (Exception e) {
            e.printStackTrace();
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.fail"),
                    null, null, null, true);
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/logisticsQa/registerForm.do";
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
    public String updateForm(Model model, @ModelAttribute("vo") LogisticsQaVo vo) {
        logisticsQaServiceImpl.updateForm(model, vo);
        return "admin/basic/logisticsQa/registerForm";
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
    public String doUpdate(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("vo") LogisticsQaVo vo, MultipartHttpServletRequest muServletRequest) {
        try {
            logisticsQaServiceImpl.doUpdate(request, response, vo,muServletRequest);
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
            return "admin/basic/logisticsQa/registerForm";
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
    public String delete(HttpServletRequest request, Model model, @ModelAttribute("vo") LogisticsQaVo vo) {
        try {
            logisticsQaServiceImpl.doDelete(vo);
            Map<String, String> hmParam = new HashMap<String, String>();
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
            model.addAttribute("url", "./list.do");
            model.addAttribute("hmParam", hmParam);
            return "admin/common/message";
        } catch (Exception e) {
            e.printStackTrace();
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/logisticsQa/detail.do";
        }
    }


    @ResponseBody
    @GetMapping("/imgView.do")
    public String imgView(HttpServletRequest request, HttpServletResponse response, Model model,
                          @ModelAttribute("vo") LogisticsQaVo vo) {
        LogisticsQa logisticsQa = logisticsQaServiceImpl.getById(Long.valueOf(vo.getId()));
        try {
            if (ObjectUtil.isNotEmpty(logisticsQa)) {
                FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
                SafeFile pcListFile = new SafeFile(logisticsQa.getListImgPath(), FilenameUtils.getName(logisticsQa.getListImgFileNm()));
                if (pcListFile.isFile()) {
                    fileDownLoadManager.fileFlush(pcListFile, logisticsQa.getListImgOrgFileNm());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 批量导入
     * @param request
     * @param response
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/upload.do")
    public String upload(HttpServletRequest request, HttpServletResponse response, MultipartFile file) throws Exception {
        logisticsQaServiceImpl.importWord(file,request,response);
        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
        return "redirect:"+XmlPropertyManager.getPropertyValue("system.admin.path")+"/logisticsQa/list.do";
    }
}
    



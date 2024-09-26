package com.cellosquare.adminapp.admin.registerStatistics.controller;


import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.cellosquare.adminapp.admin.registerStatistics.conver.RegisterAppSuccessConver;
import com.cellosquare.adminapp.admin.registerStatistics.entity.FromSource;
import com.cellosquare.adminapp.admin.registerStatistics.service.IFromSourceService;
import com.cellosquare.adminapp.admin.registerStatistics.vo.FromSourceUploadVO;
import com.cellosquare.adminapp.admin.registerStatistics.vo.FromSourceVo;
import com.cellosquare.adminapp.common.config.SpinnerWriteHandler;
import com.cellosquare.adminapp.common.enums.FromSourceType;
import com.cellosquare.adminapp.common.ex.UploadException;
import com.cellosquare.adminapp.common.listener.FromSourceExcelUploadListener;
import com.cellosquare.adminapp.common.util.CommonMessageModel;
import com.cellosquare.adminapp.common.util.EasyExcelUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@Slf4j
@RequestMapping("/celloSquareAdmin/fromSource")
public class FromSourceController {

    @Autowired
    private IFromSourceService fromSourceServiceImpl;

    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") FromSourceVo vo) {
        fromSourceServiceImpl.getList(model, vo);
        return "admin/basic/fromSource/list";
    }

    @PostMapping("/detail.do")
    public String detail(Model model, @ModelAttribute("vo") FromSourceVo vo) {
        fromSourceServiceImpl.detail(model, vo);
        return "admin/basic/fromSource/detail";
    }


    @PostMapping("/registerForm.do")
    public String registerForm(Model model, @ModelAttribute("vo") FromSourceVo vo) {
        return "admin/basic/fromSource/registerForm";
    }


    @PostMapping("/register.do")
    public String doWrite(HttpServletRequest request, Model model, @ModelAttribute("vo") FromSourceVo vo) {
        Long count = fromSourceServiceImpl.lambdaQuery()
                .eq(FromSource::getType, "1").and(fromSourceLambdaQueryWrapper ->
                        fromSourceLambdaQueryWrapper.eq(FromSource::getIdentifier, vo.getIdentifier()).
                                or().eq(FromSource::getAppSuccessIdentifier, vo.getAppSuccessIdentifier())).count();
        if (count > 0) {
            model.addAttribute("detail", vo);
            model.addAttribute("contIU", "U");
            ActionMessageUtil.setActionMessage(request, "标识符重复，请检查重试");
            return "admin/basic/fromSource/registerForm";
        }
        Long count_gw = fromSourceServiceImpl.lambdaQuery()
                .eq(FromSource::getType, "3").eq(FromSource::getName, vo.getName()).eq(FromSource::getRemark, vo.getRemark()).count();
        if (count_gw > 0) {
            model.addAttribute("detail", vo);
            model.addAttribute("contIU", "U");
            ActionMessageUtil.setActionMessage(request, "名称+渠道 唯一，请检查重试");
            return "admin/basic/fromSource/registerForm";
        }

        fromSourceServiceImpl.doWrite(request, vo);
        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
        return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/fromSource/list.do";

    }


    @PostMapping("/updateForm.do")
    public String updateForm(Model model, @ModelAttribute("vo") FromSourceVo vo) {
        FromSourceVo detailVO = RegisterAppSuccessConver.INSTANCT.getFromSourceVo(fromSourceServiceImpl.lambdaQuery().eq(FromSource::getId, Long.valueOf(vo.getId())).one());
        model.addAttribute("detail", detailVO);
        model.addAttribute("contIU", "U");
        return "admin/basic/fromSource/registerForm";
    }


    @PostMapping("/update.do")
    public String doUpdate(HttpServletRequest request, Model model, @ModelAttribute("vo") FromSourceVo vo) {
        Long count = fromSourceServiceImpl.lambdaQuery().eq(FromSource::getType, "1")
                .and(r -> r.ne(FromSource::getId, Long.parseLong(vo.getId())))
                .and(r -> r.eq(FromSource::getIdentifier, vo.getIdentifier())
                        .or()
                        .eq(FromSource::getAppSuccessIdentifier, vo.getAppSuccessIdentifier()))
                .count();
        if (count > 0) {
            FromSourceVo detailVO = RegisterAppSuccessConver.INSTANCT.getFromSourceVo(fromSourceServiceImpl.lambdaQuery().eq(FromSource::getId, Long.valueOf(vo.getId())).one());
            model.addAttribute("detail", detailVO);
            model.addAttribute("contIU", "U");
            ActionMessageUtil.setActionMessage(request, "标识符重复，请检查重试");
            return "admin/basic/fromSource/registerForm";
        }
        Long count_gw = fromSourceServiceImpl.lambdaQuery()
                .eq(FromSource::getType, "3").eq(FromSource::getName, vo.getName()).eq(FromSource::getRemark, vo.getRemark()).count();
        if (count_gw > 0) {
            model.addAttribute("detail", vo);
            model.addAttribute("contIU", "U");
            ActionMessageUtil.setActionMessage(request, "名称+渠道 唯一，请检查重试");
            return "admin/basic/fromSource/registerForm";
        }
        fromSourceServiceImpl.doUpdate(request, vo);
        Map<String, String> objectObjectHashMap = Maps.newHashMap();
        objectObjectHashMap.put("id", vo.getId());
        return CommonMessageModel.setModel(XmlMessageManager.getMessageValue("message.common.modify.success"), "./detail.do", "post", model
                , objectObjectHashMap);
    }

    @PostMapping("/doDelete.do")
    public String delete(Model model, @ModelAttribute("vo") FromSourceVo vo) {
        fromSourceServiceImpl.doDelete(vo);
        return CommonMessageModel.setModel(XmlMessageManager.getMessageValue("message.common.delete.success"), "./list.do", "get", model
                , Maps.newHashMap());
    }

    @PostMapping("/upload.do")
    public String upload(Model model, MultipartFile file, String type) throws IOException {
        UploadException uploadException = new UploadException();
        EasyExcel.read(file.getInputStream(), FromSourceUploadVO.class, new FromSourceExcelUploadListener(fromSourceServiceImpl, type, uploadException)).sheet().doRead();
        if (StrUtil.isNotEmpty(uploadException.getMessage())) {
            return CommonMessageModel.setModel(uploadException.getMessage(), "./list.do", "get", model
                    , Maps.newHashMap());
        }
        return CommonMessageModel.setModel(XmlMessageManager.getMessageValue("message.common.write.success"), "./list.do", "get", model
                , Maps.newHashMap());
    }

    @GetMapping("/downloadMB.do")
    public void download(HttpServletResponse response) throws Exception {
        EasyExcelUtils.writeExcelImp(response, data(), "来源维护模板", "来源维护模板",
                FromSourceUploadVO.class, new SpinnerWriteHandler(Arrays.stream(FromSourceType.values()).filter(fromSourceType ->
                        !Objects.equals(fromSourceType.getCode(), FromSourceType.growing_io.getCode()) && !Objects.equals(fromSourceType.getCode(), FromSourceType.baidu.getCode())
                ).map(FromSourceType::getDesc).toArray(String[]::new)));
    }

    private List<FromSourceUploadVO> data() {
        List<FromSourceUploadVO> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            FromSourceUploadVO data = new FromSourceUploadVO();
            list.add(data);
        }
        return list;
    }


    @GetMapping("/download.do")
    public void download(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("vo") FromSourceVo vo) throws Exception {
        fromSourceServiceImpl.excelDownLoad(request, response, vo);
    }

    @ResponseBody
    @GetMapping("/downloadCount.do")
    public int downloadCount(@ModelAttribute("vo") FromSourceVo vo) {
        return fromSourceServiceImpl.downloadCount(vo);
    }
}

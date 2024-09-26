package com.cellosquare.adminapp.admin.bargainProduct.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.bluewaves.lab.util.StringUtil;
import com.cellosquare.adminapp.admin.bargain.entity.Bargain;
import com.cellosquare.adminapp.admin.bargain.service.impl.BargainServiceImpl;
import com.cellosquare.adminapp.admin.bargainProduct.entity.BargainProduct;
import com.cellosquare.adminapp.admin.bargainProduct.service.impl.BargainProductServiceImpl;
import com.cellosquare.adminapp.admin.bargainProduct.vo.BargainProductVO;
import com.cellosquare.adminapp.admin.mvbanner.vo.AdminMvBannerVO;
import com.cellosquare.adminapp.common.adminAnnotion.CleanCacheAnnotion;
import com.cellosquare.adminapp.common.download.FileDownLoadManager;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.owasp.esapi.SafeFile;
import org.owasp.esapi.logging.java.ESAPICustomJavaLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/celloSquareAdmin/bargainProduct")
public class BargainProductController {
    @Autowired
    private BargainProductServiceImpl bargainProductService;
    @Autowired
    private BargainServiceImpl bargainService;

    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") BargainProductVO vo) {
        Page<BargainProduct> page = bargainProductService.lambdaQuery()
                .like(StrUtil.isNotEmpty(vo.getProductName()), BargainProduct::getProductName, vo.getProductName())
                .orderByDesc(BargainProduct::getId)
                .page(new Page<BargainProduct>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        model.addAttribute("totalCount", page.getTotal());
        model.addAttribute("list", page.getRecords());
        return "admin/basic/bargainProduct/list";
    }

    /**
     * 详情
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/detail.do")
    public String detail(Model model, @ModelAttribute("vo") BargainProductVO vo) {
        bargainProductService.detail(model, vo);
        return "admin/basic/bargainProduct/detail";
    }


    /**
     * 跳转到新增页
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/registerForm.do")
    public String registerForm(Model model, @ModelAttribute("vo") BargainProductVO vo) {
        model.addAttribute("detail", vo);
        return "admin/basic/bargainProduct/registerForm";
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
    public String register(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("vo") BargainProductVO vo, MultipartHttpServletRequest muServletRequest) {
        try {
            bargainProductService.register(request, response, vo, muServletRequest);
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/bargainProduct/list.do";
        } catch (Exception e) {
            e.printStackTrace();
            ActionMessageUtil.setActionMessage(request, e.getMessage(),
                    null, null, null, true);
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/bargainProduct/registerForm.do";
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
    public String updateForm(Model model, @ModelAttribute("vo") BargainProductVO vo) {
        bargainProductService.updateForm(model, vo);
        return "admin/basic/bargainProduct/registerForm";
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
    public String doUpdate(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("vo") BargainProductVO vo, MultipartHttpServletRequest muServletRequest) {
        try {
            bargainProductService.doUpdate(request, response, vo, muServletRequest);
            Map<String, Object> hmParam = new HashMap<>();
            hmParam.put("id", vo.getId());
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("method", "post");
            model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
            model.addAttribute("url", "./detail.do");
            model.addAttribute("hmParam", hmParam);
            return "admin/common/message";
        } catch (Exception e) {
            ActionMessageUtil.setActionMessage(request, e.getMessage(),
                    null, null, null, true);
            return "admin/basic/bargainProduct/registerForm";
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
    public String delete(HttpServletRequest request, Model model, @ModelAttribute("vo") BargainProductVO vo) {
        try {
            Long count = bargainService.lambdaQuery().eq(Bargain::getProductId, vo.getId()).count();
            if (count > 0) {
                model.addAttribute("msg", XmlMessageManager.getMessageValue("产品下有关联线路,删除失败"));
            } else {
                bargainProductService.doDelete(vo);
                model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
            }
            Map<String, String> hmParam = new HashMap<String, String>();
            model.addAttribute("msg_type", ":submit");
            model.addAttribute("url", "./list.do");
            model.addAttribute("hmParam", hmParam);
            return "admin/common/message";
        } catch (Exception e) {
            e.printStackTrace();
            ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.delete.fail"), null, null, null, true);
            return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/bargainProduct/detail.do";
        }
    }

    @ResponseBody
    @GetMapping("/imgView.do")
    public String imgView(HttpServletRequest request, HttpServletResponse response, Model model,
                          @ModelAttribute("vo") BargainProductVO vo) {
        BargainProduct product = bargainProductService.getById(Long.valueOf(vo.getId()));
        try {
            if (ObjectUtil.isNotEmpty(product)) {
                if (Objects.equals(vo.getImgKinds(), "mobileList")) {
                    FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
                    SafeFile pcListFile = new SafeFile(product.getMobileListImgPath(), FilenameUtils.getName(product.getMobileListImgFileNm()));
                    if (pcListFile.isFile()) {
                        fileDownLoadManager.fileFlush(pcListFile, product.getMobileListImgOrgFileNm());
                    }
                } else {
                    FileDownLoadManager fileDownLoadManager = new FileDownLoadManager(request, response);
                    SafeFile pcListFile = new SafeFile(product.getListImgPath(), FilenameUtils.getName(product.getListImgFileNm()));
                    if (pcListFile.isFile()) {
                        fileDownLoadManager.fileFlush(pcListFile, product.getListImgOrgFileNm());
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 详情
     *
     * @return
     */
    @GetMapping("/productList.do")
    @ResponseBody
    public List<BargainProduct> productList() {

        return bargainProductService.list();
    }

    /**
     * 详情
     *
     * @return
     */
    @GetMapping("/getMultiFlag.do")
    @ResponseBody
    public String productList(String id) {
        BargainProduct byId = bargainProductService.getById(Long.parseLong(id));
        if (Objects.nonNull(byId)) {
            return byId.getMultiFlag();
        }
        return "N";
    }

    @PostMapping({"/doSortOrder.do"})
    @CleanCacheAnnotion
    public String doSortOrder(HttpServletRequest request, HttpServletResponse response, Model model,
                              @ModelAttribute("vo") BargainProductVO vo) {
        String[] ids = vo.getIds();
        String[] listOrdb = vo.getListOrdb();
        for (int i = 0; i < ids.length; i++) {
            bargainProductService.lambdaUpdate()
                    .eq(BargainProduct::getId,Long.parseLong(ids[i]))
                    .set(BargainProduct::getOrdb,listOrdb[i]).update();
        }
        Map<String, String> hmParam = new HashMap<String, String>();
        model.addAttribute("msg_type", ":submit");
        model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.modify.success"));
        model.addAttribute("url", "./list.do");
        model.addAttribute("hmParam", hmParam);
        return "admin/common/message";

    }
}

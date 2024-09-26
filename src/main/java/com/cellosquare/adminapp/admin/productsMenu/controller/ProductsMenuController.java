package com.cellosquare.adminapp.admin.productsMenu.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.cellosquare.adminapp.admin.goods.service.AdminProductsService;
import com.cellosquare.adminapp.admin.goods.vo.AdminProductsVO;
import com.cellosquare.adminapp.admin.goods.vo.Products;
import com.cellosquare.adminapp.admin.productsMenu.entity.ProductsMenu;
import com.cellosquare.adminapp.admin.productsMenu.service.IProductsMenuService;
import com.cellosquare.adminapp.admin.productsMenu.vo.ProductsMenuVo;
import com.cellosquare.adminapp.common.adminAnnotion.CleanCacheAnnotion;
import com.cellosquare.adminapp.common.util.CommonMessageModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * @author walker
 * @since 2023-10-286 16:07:23
 */
@Controller
@Slf4j
@RequestMapping("/celloSquareAdmin/productsMenu")
public class ProductsMenuController {

    @Autowired
    private IProductsMenuService productsMenuServiceImpl;
    @Autowired
    private AdminProductsService adminProductsServiceImpl;

    /**
     * 查询list
     *
     * @param model
     * @param vo
     * @return
     */
    @GetMapping("/list.do")
    public String list(Model model, @ModelAttribute("vo") ProductsMenuVo vo) {
        productsMenuServiceImpl.getList(model, vo);
        return "admin/basic/productsMenu/list";
    }

    @GetMapping("/ajaxList.do")
    @ResponseBody
    public List<String> ajaxList() {
        return adminProductsServiceImpl.lambdaQuery().list().stream().map(Products::getProductNm).collect(Collectors.toList());
    }

    /**
     * 详情
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/detail.do")
    public String detail(Model model, @ModelAttribute("vo") ProductsMenuVo vo) {
        productsMenuServiceImpl.detail(model, vo);
        return "admin/basic/productsMenu/detail";
    }


    /**
     * 跳转到新增页
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/registerForm.do")
    public String registerForm(Model model, @ModelAttribute("vo") ProductsMenuVo vo) {
        model.addAttribute("detail", vo);
        return "admin/basic/productsMenu/registerForm";
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
    public String register(HttpServletRequest request, HttpServletResponse response,
                           @ModelAttribute("vo") ProductsMenuVo vo) {
        productsMenuServiceImpl.register(request, response, vo);
        ActionMessageUtil.setActionMessage(request, XmlMessageManager.getMessageValue("message.common.write.success"));
        return "redirect:" + XmlPropertyManager.getPropertyValue("system.admin.path") + "/productsMenu/list.do";
    }


    /**
     * 跳转到修改页
     *
     * @param model
     * @param vo
     * @return
     */
    @PostMapping("/updateForm.do")
    public String updateForm(Model model, @ModelAttribute("vo") ProductsMenuVo vo) {
        productsMenuServiceImpl.updateForm(model, vo);
        return "admin/basic/productsMenu/registerForm";
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
    public String doUpdate(HttpServletRequest request, HttpServletResponse response, Model model, @ModelAttribute("vo") ProductsMenuVo vo) {
        productsMenuServiceImpl.doUpdate(request, response, vo);
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
    @CleanCacheAnnotion
    public String delete(Model model, @ModelAttribute("vo") ProductsMenuVo vo) {
        ProductsMenu one = productsMenuServiceImpl.lambdaQuery().eq(ProductsMenu::getId, Long.valueOf(vo.getId())).one();
        AdminProductsVO adminProductsVO = new AdminProductsVO();
        adminProductsVO.setProductCtgry(one.getProductCtgry());
        adminProductsVO.setLangCd("cn-zh");
        int adminProductsServiceImplTotalCount = adminProductsServiceImpl.getTotalCount(adminProductsVO);
        if (adminProductsServiceImplTotalCount > 0) {
            return CommonMessageModel.setModel("该菜单下存在产品", "./list.do", "get", model
                    , new HashMap());
        }
        productsMenuServiceImpl.doDelete(vo);

        Map<String, String> hmParam = new HashMap<String, String>();
        model.addAttribute("msg_type", ":submit");
        model.addAttribute("msg", XmlMessageManager.getMessageValue("message.common.delete.success"));
        model.addAttribute("url", "./list.do");
        model.addAttribute("hmParam", hmParam);
        return "admin/common/message";
    }

    @PostMapping("/doSortOrder.do")
    @CleanCacheAnnotion
    public String doSortOrder(Model model, @ModelAttribute("vo") ProductsMenuVo vo) {
        List<ProductsMenuVo> ProductsMenuVos = new ArrayList<>();
        for (int i = 0; i < vo.getListId().length; i++) {
            ProductsMenuVo ProductsMenuVo = new ProductsMenuVo();
            ProductsMenuVo.setId(vo.getListId()[i]);
            ProductsMenuVo.setOrdb(vo.getListOrdb()[i]);
            ProductsMenuVos.add(ProductsMenuVo);
        }
        if (Arrays.asList(vo.getListOrdb()).contains(StringUtils.EMPTY) || Arrays.asList(vo.getListOrdb()).contains(null)) {
            return CommonMessageModel.setModel("序号不能为空", "./list.do", "get", model
                    , new HashMap());
        }
        productsMenuServiceImpl.doSortOrder(ProductsMenuVos);
        return CommonMessageModel.setModel(model, new HashMap() {
        });
    }
}

package com.cellosquare.adminapp.admin.productsMenu.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.productsMenu.entity.ProductsMenu;
import com.cellosquare.adminapp.admin.productsMenu.vo.ProductsMenuVo;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author walker
 * @since 2023-10-286 16:07:23
 */
public interface IProductsMenuService extends IService<ProductsMenu> {

    void getList(Model model, ProductsMenuVo vo);

    void detail(Model model, ProductsMenuVo vo);

    void register(HttpServletRequest request, HttpServletResponse response, ProductsMenuVo vo);

    void updateForm(Model model, ProductsMenuVo vo);

    void doUpdate(HttpServletRequest request, HttpServletResponse response, ProductsMenuVo vo);

    void doDelete(ProductsMenuVo vo);

    void doSortOrder(List<ProductsMenuVo> productsMenuVos);
}

package com.cellosquare.adminapp.admin.productsMenu.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cellosquare.adminapp.admin.manager.mapper.AdminManagerMapper;
import com.cellosquare.adminapp.admin.productsMenu.conver.ProductsMenuVoConver;
import com.cellosquare.adminapp.admin.productsMenu.entity.ProductsMenu;
import com.cellosquare.adminapp.admin.productsMenu.mapper.ProductsMenuMapper;
import com.cellosquare.adminapp.admin.productsMenu.service.IProductsMenuService;
import com.cellosquare.adminapp.admin.productsMenu.vo.ProductsMenuVo;
import com.cellosquare.adminapp.common.constant.UseEnum;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author walker
 * @since 2023-10-286 16:07:23
 */
@Service
public class ProductsMenuServiceImpl extends ServiceImpl<ProductsMenuMapper, ProductsMenu> implements IProductsMenuService {
    @Autowired
    private AdminManagerMapper adminManagerMapper;


    /**
     * 处理数据
     *
     * @param vo
     */
    public void dealData(ProductsMenuVo vo) {
        if (Objects.isNull(vo)) {
            return;
        }
        vo.setUseYnNm(UseEnum.getEnumByCode(vo.getUseYn()).getCnValue());
        //查询用户
        if (StringUtils.isNotBlank(vo.getInsPersonId())) {
            vo.setInsPersonNm(adminManagerMapper.getByUserId(vo.getInsPersonId()));
        }
        if (StringUtils.isNotBlank(vo.getUpdPersonId())) {
            vo.setUpdPersonNm(adminManagerMapper.getByUserId(vo.getUpdPersonId()));
        }
    }

    /**
     * 设置信息
     *
     * @param request
     * @param vo
     */
    private void setMsg(HttpServletRequest request, ProductsMenu vo) {
        AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
        if (vo.getId() == null) {//新增
            vo.setInsPersonId(sessionForm.getAdminId());
            vo.setUpdPersonId(sessionForm.getAdminId());
            vo.setInsDtm(new Timestamp(new Date().getTime()));
            vo.setUpdDtm(new Timestamp(new Date().getTime()));
        } else {//修改
            vo.setUpdPersonId(sessionForm.getAdminId());
            vo.setUpdDtm(new Timestamp(new Date().getTime()));
        }
    }

    @Override
    public void register(HttpServletRequest request, HttpServletResponse response, ProductsMenuVo vo) {
        //自动生成 stry
        ProductsMenu ProductsMenu = ProductsMenuVoConver.INSTANCT.getProductsMenu(vo);
        setMsg(request, ProductsMenu);
        ProductsMenu.setProductCtgry("GOODS_LIST_" + (int) ((Math.random() * 9 + 1) * 100000));
        save(ProductsMenu);
    }

    @Override
    public void doUpdate(HttpServletRequest request, HttpServletResponse response, ProductsMenuVo vo) {
        ProductsMenu ProductsMenu = ProductsMenuVoConver.INSTANCT.getProductsMenu(vo);
        setMsg(request, ProductsMenu);
        updateById(ProductsMenu);
    }

    @Override
    public void doDelete(ProductsMenuVo vo) {
        removeById(Long.valueOf(vo.getId()));
    }

    @Override
    public void getList(Model model, ProductsMenuVo vo) {
        LambdaQueryChainWrapper<ProductsMenu> ProductsMenuLambdaQueryChainWrapper = lambdaQuery()
                .like(StrUtil.isNotEmpty(vo.getSearchValue()), ProductsMenu::getName, vo.getSearchValue())
                .orderByAsc(ProductsMenu::getOrdb).orderByDesc(ProductsMenu::getInsDtm);
        Page<ProductsMenu> page = ProductsMenuLambdaQueryChainWrapper
                .page(new Page<>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        List<ProductsMenuVo> ProductsMenuVos = page.getRecords().stream().map(ProductsMenuVoConver.INSTANCT::getProductsMenuVo).collect(Collectors.toList());
        //所有菜单分类
        List<ProductsMenuVo> collect = ProductsMenuVos.stream().map(ProductsMenuVo -> {
            dealData(ProductsMenuVo);
            return ProductsMenuVo;
        }).collect(Collectors.toList());
        model.addAttribute("totalCount", page.getTotal());
        model.addAttribute("list", collect);
    }


    @Override
    public void detail(Model model, ProductsMenuVo vo) {
        ProductsMenu result = lambdaQuery().eq(ProductsMenu::getId, Long.valueOf(vo.getId())).one();
        ProductsMenuVo ProductsMenuVo = ProductsMenuVoConver.INSTANCT.getProductsMenuVo(result);
        dealData(ProductsMenuVo);
        model.addAttribute("detail", ProductsMenuVo);
        model.addAttribute("vo", vo);
    }

    @Override
    public void updateForm(Model model, ProductsMenuVo vo) {
        ProductsMenu detailVO = lambdaQuery().eq(ProductsMenu::getId, Long.valueOf(vo.getId())).one();
        ProductsMenuVo ProductsMenuVo = ProductsMenuVoConver.INSTANCT.getProductsMenuVo(detailVO);
        model.addAttribute("detail", ProductsMenuVo);
        model.addAttribute("contIU", "U");
    }

    @Override
    public void doSortOrder(List<ProductsMenuVo> ProductsMenuVos) {
        ProductsMenuVos.forEach(ProductsMenuVo -> {
            lambdaUpdate().set(ProductsMenu::getOrdb,
                    !Objects.isNull(ProductsMenuVo.getOrdb()) ? ProductsMenuVo.getOrdb() : null).
                    eq(ProductsMenu::getId, Long.valueOf(ProductsMenuVo.getId())).update();
        });
    }

}

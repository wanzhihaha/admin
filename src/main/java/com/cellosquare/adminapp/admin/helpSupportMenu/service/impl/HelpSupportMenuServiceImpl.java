package com.cellosquare.adminapp.admin.helpSupportMenu.service.impl;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cellosquare.adminapp.admin.helpSupport.conver.HelpSupportVoConver;
import com.cellosquare.adminapp.admin.helpSupport.entity.HelpSupport;
import com.cellosquare.adminapp.admin.helpSupport.service.IHelpSupportService;
import com.cellosquare.adminapp.admin.helpSupport.vo.HelpSupportVo;
import com.cellosquare.adminapp.admin.helpSupportMenu.conver.HelpSupportMenuVoConver;
import com.cellosquare.adminapp.admin.helpSupportMenu.entity.HelpSupportMenu;
import com.cellosquare.adminapp.admin.helpSupportMenu.mapper.HelpSupportMenuMapper;
import com.cellosquare.adminapp.admin.helpSupportMenu.service.IHelpSupportMenuService;
import com.cellosquare.adminapp.admin.helpSupportMenu.vo.HelpSupportMenuVo;
import com.cellosquare.adminapp.admin.manager.mapper.AdminManagerMapper;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.common.constant.UseEnum;
import com.cellosquare.adminapp.common.enums.HelpSupprtoMenuEnum;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.util.DateUtils;
import com.cellosquare.adminapp.common.util.XssUtils;
import com.nhncorp.lucy.security.xss.XssPreventer;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hugo
 * @since 2023-03-65 09:48:42
 */
@Service
public class HelpSupportMenuServiceImpl extends ServiceImpl<HelpSupportMenuMapper, HelpSupportMenu> implements IHelpSupportMenuService {
    @Autowired
    private AdminManagerMapper adminManagerMapper;
    @Autowired
    private IHelpSupportService helpSupportServiceImpl;

    @Override
    public List<HelpSupportMenu> getSMenu(String pId) {
        return lambdaQuery().
                eq(HelpSupportMenu::getPId, Long.valueOf(pId)).list();
    }

    @Override
    public List<HelpSupportMenu> getAllMenu() {
        return lambdaQuery().list();
    }

    @Override
    public List<HelpSupportMenuVo> getAllMenuTop() {
        return lambdaQuery().eq(HelpSupportMenu::getPId, Long.valueOf(0)).eq(HelpSupportMenu::getIsChild, Long.valueOf(0)).list()
                .stream().map(HelpSupportMenuVoConver.INSTANCT::getHelpSupportMenuVo).collect(Collectors.toList());
    }

    @Override
    public void getList(Model model, HelpSupportMenuVo vo) {
        LambdaQueryChainWrapper<HelpSupportMenu> helpSupportLambdaQueryChainWrapper = lambdaQuery()
                .like(StrUtil.isNotEmpty(vo.getSearchValue()), HelpSupportMenu::getMenuName, vo.getSearchValue())
                .orderByDesc(HelpSupportMenu::getId);
        if (StrUtil.isNotEmpty(vo.getStartDate()) && StrUtil.isNotEmpty(vo.getEndDate())) {
            helpSupportLambdaQueryChainWrapper.ge(HelpSupportMenu::getInsDtm, DateUtils.getDateForMatt(0, vo.getStartDate()))
                    .le(HelpSupportMenu::getInsDtm, DateUtils.getDateForMatt(1, vo.getEndDate()));
        }
        if (StrUtil.isNotEmpty(vo.getParentId())) {
            helpSupportLambdaQueryChainWrapper.eq(HelpSupportMenu::getPId, Long.valueOf(vo.getParentId()));
        }
        Page<HelpSupportMenu> page = helpSupportLambdaQueryChainWrapper
                .page(new Page<>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        List<HelpSupportMenuVo> helpSupportMenuVos = HelpSupportMenuVoConver.INSTANCT.getHelpSupportMenuVos(page.getRecords());
        List<HelpSupportMenu> allMenu = getAllMenu();
        helpSupportMenuVos.forEach(helpSupportMenuVo -> dealData(helpSupportMenuVo, allMenu));
        model.addAttribute("totalCount", page.getTotal());
        model.addAttribute("list", helpSupportMenuVos);
        model.addAttribute("siblingNodes", getAllMenuTop());

    }

    @Override
    public void detail(Model model, HelpSupportMenuVo vo) {
        HelpSupportMenu helpSupportMenu = lambdaQuery().eq(HelpSupportMenu::getId, Long.valueOf(vo.getId())).one();
        HelpSupportMenuVo helpSupportMenuVo = HelpSupportMenuVoConver.INSTANCT.getHelpSupportMenuVo(helpSupportMenu);
        List<HelpSupportMenu> allMenu = getAllMenu();
        dealData(helpSupportMenuVo, allMenu);
        model.addAttribute("detail", helpSupportMenuVo);
        model.addAttribute("vo", vo);
    }

    @Override
    public void register(HttpServletRequest request, HttpServletResponse response, HelpSupportMenuVo vo) {
        HelpSupportMenu helpSupportMenu = HelpSupportMenuVoConver.INSTANCT.getHelpSupportMenu(vo);
        setMsg(helpSupportMenu);
        helpSupportMenu.setIsChild(Long.valueOf(1));
        helpSupportMenu.setUseYn(UseEnum.USE.getCode());
        save(helpSupportMenu);
    }

    @Override
    public void updateForm(Model model, HelpSupportMenuVo vo) {
        HelpSupportMenu helpSupportMenu = lambdaQuery().eq(HelpSupportMenu::getId, Long.valueOf(vo.getId())).one();
        HelpSupportMenuVo helpSupportMenuVo = HelpSupportMenuVoConver.INSTANCT.getHelpSupportMenuVo(helpSupportMenu);
        model.addAttribute("siblingNodes", getAllMenuTop());
        model.addAttribute("detail", helpSupportMenuVo);
    }

    @Override
    public void doUpdate(HttpServletRequest request, HttpServletResponse response, HelpSupportMenuVo vo) {
        HelpSupportMenu helpSupportMenu = HelpSupportMenuVoConver.INSTANCT.getHelpSupportMenu(vo);
        setMsg(helpSupportMenu);
        updateById(helpSupportMenu);
    }

    @Override
    public void doDelete(HelpSupportMenuVo vo) {
        //查询是否关联了帮助与支持
        List<HelpSupport> helpSupports = helpSupportServiceImpl.queryHelpSupportByMenu(Long.valueOf(vo.getId()));
        if (CollectionUtils.isNotEmpty(helpSupports)) {
            throw new RuntimeException("该菜单下存在文章，请先删除文章");
        }
        HelpSupportMenu helpSupportMenu = lambdaQuery().eq(HelpSupportMenu::getId, Long.valueOf(vo.getId())).one();
        if (0 == helpSupportMenu.getPId()) {
            throw new RuntimeException("父节点不能删除");
        }
        removeById(Long.valueOf(vo.getId()));
    }

    @Override
    public void doSortOrder(List<HelpSupportMenuVo> helpSupportMenuVos) {
        helpSupportMenuVos.forEach(helpSupportMenuVo -> {
            lambdaUpdate().set(HelpSupportMenu::getOrdb, helpSupportMenuVo.getOrdb()).
                    eq(HelpSupportMenu::getId, Long.valueOf(helpSupportMenuVo.getId())).update();
        });
    }

    public void dealData(HelpSupportMenuVo vo, List<HelpSupportMenu> allMenu) {
        if (Objects.isNull(vo)) {
            return;
        }
        Map<Long, HelpSupportMenu> collect = allMenu.stream().collect(Collectors.toMap(HelpSupportMenu::getId, Function.identity()));
        vo.setUseYnNm(UseEnum.getEnumByCode(vo.getUseYn()).getCnValue());
        //查询用户
        if (StringUtils.isNotBlank(vo.getInsPersonId())) {
            vo.setInsPersonNm(adminManagerMapper.getByUserId(vo.getInsPersonId()));
        }
        if (StringUtils.isNotBlank(vo.getUpdPersonId())) {
            vo.setUpdPersonNm(adminManagerMapper.getByUserId(vo.getUpdPersonId()));
        }
        HelpSupportMenu helpSupportMenu = collect.get(Long.valueOf(vo.getPId()));
        vo.setParentMenuName(Objects.isNull(helpSupportMenu) ? StringUtils.EMPTY : helpSupportMenu.getMenuName());
    }

    /**
     * 设置信息
     *
     * @param vo
     */
    private void setMsg(HelpSupportMenu vo) {
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
}

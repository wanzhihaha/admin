package com.cellosquare.adminapp.admin.continent.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cellosquare.adminapp.admin.continent.conver.ContinentVoConver;
import com.cellosquare.adminapp.admin.continent.entity.Continent;
import com.cellosquare.adminapp.admin.continent.mapper.ContinentMapper;
import com.cellosquare.adminapp.admin.continent.service.IContinentService;
import com.cellosquare.adminapp.admin.continent.vo.ContinentVo;
import com.cellosquare.adminapp.admin.manager.mapper.AdminManagerMapper;
import com.cellosquare.adminapp.admin.productsMenu.entity.ProductsMenu;
import com.cellosquare.adminapp.admin.productsMenu.vo.ProductsMenuVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author walker
 * @since 2023-11-314 14:07:21
 */
@Service
public class ContinentServiceImpl extends ServiceImpl<ContinentMapper, Continent> implements IContinentService {

    @Autowired
    private AdminManagerMapper adminManagerMapper;

    /**
     * 处理数据
     *
     * @param vo
     */
    public void dealData(ContinentVo vo) {
        if (Objects.isNull(vo)) {
            return;
        }
        //查询用户
        if (StringUtils.isNotBlank(vo.getUpdPersonId())) {
            vo.setUpdPersonNm(adminManagerMapper.getByUserId(vo.getUpdPersonId()));
        }
    }

    @Override
    public void getList(Model model, ContinentVo vo) {
        LambdaQueryChainWrapper<Continent> ContinentLambdaQueryChainWrapper = lambdaQuery()
                .orderByAsc(Continent::getFclOrdb).orderByAsc(Continent::getId).orderByDesc(Continent::getInsDtm);
        Page<Continent> page = ContinentLambdaQueryChainWrapper
                .page(new Page<>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        List<ContinentVo> ContinentVos = page.getRecords().stream().map(ContinentVoConver.INSTANCT::getContinentVo).collect(Collectors.toList());
        //所有菜单分类
        List<ContinentVo> collect = ContinentVos.stream().map(ContinentVo -> {
            dealData(ContinentVo);
            return ContinentVo;
        }).collect(Collectors.toList());
        model.addAttribute("totalCount", page.getTotal());
        model.addAttribute("list", collect);
    }

    @Override
    public void doSortOrder(List<ContinentVo> continentVos) {
        continentVos.forEach(continentVo -> {
            lambdaUpdate()
            .set(Continent::getFclOrdb, continentVo.getFclOrdb())
//            .set(Continent::getLclOrdb, continentVo.getLclOrdb())
//            .set(Continent::getAirOrdb, continentVo.getAirOrdb())
//            .set(Continent::getExpressOrdb, continentVo.getExpressOrdb())
            .eq(Continent::getId, Long.valueOf(continentVo.getId())).update();
        });
    }
}

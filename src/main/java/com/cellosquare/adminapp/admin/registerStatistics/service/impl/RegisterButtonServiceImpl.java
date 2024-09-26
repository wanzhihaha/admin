package com.cellosquare.adminapp.admin.registerStatistics.service.impl;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cellosquare.adminapp.admin.registerStatistics.conver.RegisterButtonVoConver;
import com.cellosquare.adminapp.admin.registerStatistics.entity.RegisterButton;
import com.cellosquare.adminapp.admin.registerStatistics.mapper.RegisterButtonMapper;
import com.cellosquare.adminapp.admin.registerStatistics.service.IRegisterButtonService;
import com.cellosquare.adminapp.admin.registerStatistics.vo.RegisterButtonExportVo;
import com.cellosquare.adminapp.admin.registerStatistics.vo.RegisterButtonVo;
import com.cellosquare.adminapp.common.constant.RegisterStatisticsSourceEnum;
import com.cellosquare.adminapp.common.util.DateUtils;
import com.cellosquare.adminapp.common.util.EasyExcelUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hugo
 * @since 2023-05-146 08:47:10
 */
@Service
public class RegisterButtonServiceImpl extends ServiceImpl<RegisterButtonMapper, RegisterButton> implements IRegisterButtonService {

    public LambdaQueryChainWrapper<RegisterButton> downloadCommom(RegisterButtonVo vo) {
        LambdaQueryChainWrapper<RegisterButton> helpSupportLambdaQueryChainWrapper = lambdaQuery();
        if (StrUtil.isNotEmpty(vo.getStartDate()) && StrUtil.isNotEmpty(vo.getEndDate())) {
            helpSupportLambdaQueryChainWrapper.ge(RegisterButton::getCreateTime, DateUtils.getDateForMatt(0, vo.getStartDate()))
                    .le(RegisterButton::getCreateTime, DateUtils.getDateForMatt(1, vo.getEndDate()));
        }
        if (StrUtil.isNotEmpty(vo.getSource())) {
            helpSupportLambdaQueryChainWrapper.eq(RegisterButton::getSource, vo.getSource());
        }
        if (StrUtil.isNotEmpty(vo.getIp())) {
            helpSupportLambdaQueryChainWrapper.and(registerAppSuccessLambdaQueryWrapper -> {
                registerAppSuccessLambdaQueryWrapper.
                        like(RegisterButton::getIp, vo.getIp()).or().
                        like(RegisterButton::getIdentifier, vo.getIp()).or().
                        like(RegisterButton::getChannel, vo.getIp()).or().
                        like(RegisterButton::getUrl, vo.getIp()).or().
                        like(RegisterButton::getUniqueId, vo.getIp()).or().
                        like(RegisterButton::getName, vo.getIp()).or().
                        like(RegisterButton::getShareLink, vo.getIp()).or().
                        like(RegisterButton::getSource, vo.getIp());
            });
        }
        return helpSupportLambdaQueryChainWrapper;
    }

    @Override
    public void excelDownLoad(HttpServletRequest request, HttpServletResponse response, RegisterButtonVo vo) throws Exception {
        List<RegisterButton> list = downloadCommom(vo).orderByDesc(RegisterButton::getCreateTime).list();
        //转换
        List<RegisterButtonExportVo> registerButtonExportVos =
                list.stream().map(RegisterButtonVoConver.INSTANCT::getRegisterButtonExportVo).map(registerButtonExportVo -> {
                    dealData(registerButtonExportVo);
                    return registerButtonExportVo;
                }).collect(Collectors.toList());
        String fileName = "注册按钮统计_" + DateUtil.formatTime(new Date());
        EasyExcelUtils.writeExcel(response, registerButtonExportVos, fileName, "统计", RegisterButtonExportVo.class);
    }

    @Override
    public int downloadCount(RegisterButtonVo vo) {
        return downloadCommom(vo).count().intValue();
    }

    @Override
    public void getList(Model model, RegisterButtonVo vo) {
        LambdaQueryChainWrapper<RegisterButton> registerButtonLambdaQueryChainWrapper = downloadCommom(vo).orderByDesc(RegisterButton::getCreateTime);
        Page<RegisterButton> page = registerButtonLambdaQueryChainWrapper
                .page(new Page<>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        List<RegisterButtonVo> registerButtonVos = RegisterButtonVoConver.INSTANCT.getRegisterButtonVos(page.getRecords());
        //所有菜单分类
        List<RegisterButtonVo> collect = registerButtonVos.stream().map(registerButtonVo -> {
            registerButtonVo.setSource(getSource(registerButtonVo.getSource()));
            return registerButtonVo;
        }).collect(Collectors.toList());
        model.addAttribute("totalCount", page.getTotal());
        model.addAttribute("list", collect);

    }

    private void dealData(RegisterButtonExportVo registerButtonExportVo) {
        registerButtonExportVo.setSource(getSource(registerButtonExportVo.getSource()));
    }

    private String getSource(String source) {
        RegisterStatisticsSourceEnum enumByCode = RegisterStatisticsSourceEnum.getEnumByCode(source);
        if (Objects.isNull(enumByCode)) {
            return "";
        }
        return enumByCode.getCnValue();
    }
}

package com.cellosquare.adminapp.admin.registerStatistics.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cellosquare.adminapp.admin.registerStatistics.conver.RegisterAppSuccessConver;
import com.cellosquare.adminapp.admin.registerStatistics.entity.RegisterAppSuccess;
import com.cellosquare.adminapp.admin.registerStatistics.mapper.RegisterAppSuccessMapper;
import com.cellosquare.adminapp.admin.registerStatistics.service.IRegisterAppSuccessService;
import com.cellosquare.adminapp.admin.registerStatistics.vo.RegisterAppSuccessExportVo;
import com.cellosquare.adminapp.admin.registerStatistics.vo.RegisterAppSuccessVo;
import com.cellosquare.adminapp.common.constant.RegisterStatisticsSourceEnum;
import com.cellosquare.adminapp.common.util.DateUtils;
import com.cellosquare.adminapp.common.util.EasyExcelUtils;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author walker
 * @since 2023-07-184 15:36:54
 */
@Service
public class RegisterAppSuccessServiceImpl extends ServiceImpl<RegisterAppSuccessMapper, RegisterAppSuccess> implements IRegisterAppSuccessService {

    public LambdaQueryChainWrapper<RegisterAppSuccess> downloadCommom(RegisterAppSuccessVo vo) {
        LambdaQueryChainWrapper<RegisterAppSuccess> helpSupportLambdaQueryChainWrapper = lambdaQuery();
        if (StrUtil.isNotEmpty(vo.getStartDate()) && StrUtil.isNotEmpty(vo.getEndDate())) {
            helpSupportLambdaQueryChainWrapper.ge(RegisterAppSuccess::getCreateTime, DateUtils.getDateForMatt(0, vo.getStartDate()))
                    .le(RegisterAppSuccess::getCreateTime, DateUtils.getDateForMatt(1, vo.getEndDate()));
        }
        if (StrUtil.isNotEmpty(vo.getFromSource())) {
            helpSupportLambdaQueryChainWrapper.eq(RegisterAppSuccess::getFromSource, vo.getFromSource());
        }
        if (StrUtil.isNotEmpty(vo.getIp())) {
            helpSupportLambdaQueryChainWrapper.and(registerAppSuccessLambdaQueryWrapper -> {
                registerAppSuccessLambdaQueryWrapper.
                        like(RegisterAppSuccess::getIp, vo.getIp()).or().
                        like(RegisterAppSuccess::getAppSuccessIdentifier, vo.getIp()).or().
                        like(RegisterAppSuccess::getChannel, vo.getIp()).or().
                        like(RegisterAppSuccess::getOrganizationId, vo.getIp()).or().
                        like(RegisterAppSuccess::getSourceVal, vo.getIp()).or().
                        like(RegisterAppSuccess::getUrl, vo.getIp()).or().
                        like(RegisterAppSuccess::getName, vo.getIp()).or().
                        like(RegisterAppSuccess::getShareLink, vo.getIp()).or().
                        like(RegisterAppSuccess::getStatus, vo.getIp());
            });
        }
        return helpSupportLambdaQueryChainWrapper;
    }

    @Override
    public void getList(Model model, RegisterAppSuccessVo vo) {
        LambdaQueryChainWrapper<RegisterAppSuccess> registerButtonLambdaQueryChainWrapper = downloadCommom(vo).orderByDesc(RegisterAppSuccess::getCreateTime);
        Page<RegisterAppSuccess> page = registerButtonLambdaQueryChainWrapper
                .page(new Page<>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        List<RegisterAppSuccessVo> registerButtonVos = page.getRecords().stream().map(RegisterAppSuccessConver.INSTANCT::getRegisterAppSuccessVo).collect(Collectors.toList());
        //所有菜单分类
        List<RegisterAppSuccessVo> collect = registerButtonVos.stream().map(registerAppSuccessVo -> {
            registerAppSuccessVo.setFromSource(getSource(registerAppSuccessVo.getFromSource()));
            return registerAppSuccessVo;
        }).collect(Collectors.toList());
        model.addAttribute("totalCount", page.getTotal());
        model.addAttribute("list", collect);
    }

    @Override
    public void excelDownLoad(HttpServletRequest request, HttpServletResponse response, RegisterAppSuccessVo vo) throws Exception {
        List<RegisterAppSuccess> list = downloadCommom(vo).orderByDesc(RegisterAppSuccess::getCreateTime).list();
        //转换
        List<RegisterAppSuccessExportVo> registerButtonExportVos =
                list.stream().map(RegisterAppSuccessConver.INSTANCT::getRegisterAppSuccessExportVo).map(registerAppSuccessExportVo -> {
                    dealData(registerAppSuccessExportVo);
                    return registerAppSuccessExportVo;
                }).collect(Collectors.toList());
        String fileName = "注册成功信息_" + DateUtil.formatTime(new Date());
        EasyExcelUtils.writeExcel(response, registerButtonExportVos, fileName, "注册成功信息", RegisterAppSuccessExportVo.class);
    }

    @Override
    public int downloadCount(RegisterAppSuccessVo vo) {
        return downloadCommom(vo).count().intValue();
    }


    private void dealData(RegisterAppSuccessExportVo registerButtonExportVo) {
        registerButtonExportVo.setFromSource(getSource(registerButtonExportVo.getFromSource()));
    }

    private String getSource(String source) {
        return RegisterStatisticsSourceEnum.getEnumByCode(source).getCnValue();
    }
}

package com.cellosquare.adminapp.admin.clickStatistics.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cellosquare.adminapp.admin.clickStatistics.conver.ClickStatisticsVoConver;
import com.cellosquare.adminapp.admin.clickStatistics.entity.ClickStatistics;
import com.cellosquare.adminapp.admin.clickStatistics.mapper.ClickStatisticsMapper;
import com.cellosquare.adminapp.admin.clickStatistics.service.IClickStatisticsService;
import com.cellosquare.adminapp.admin.clickStatistics.vo.ClickStatisticsExportVo;
import com.cellosquare.adminapp.admin.clickStatistics.vo.ClickStatisticsVo;
import com.cellosquare.adminapp.common.constant.ClickStatisticsEnum;
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
 *  服务实现类
 * </p>
 *
 * @author walker
 * @since 2024-03-71 09:13:54
 */
@Service
public class ClickStatisticsServiceImpl extends ServiceImpl<ClickStatisticsMapper, ClickStatistics> implements IClickStatisticsService {


    public LambdaQueryChainWrapper<ClickStatistics> downloadCommom(ClickStatisticsVo vo) {
        LambdaQueryChainWrapper<ClickStatistics> helpSupportLambdaQueryChainWrapper = lambdaQuery();
        if (StrUtil.isNotEmpty(vo.getStartDate()) && StrUtil.isNotEmpty(vo.getEndDate())) {
            helpSupportLambdaQueryChainWrapper.ge(ClickStatistics::getCreateTime, DateUtils.getDateForMatt(0, vo.getStartDate()))
                    .le(ClickStatistics::getCreateTime, DateUtils.getDateForMatt(1, vo.getEndDate()));
        }
        if (StrUtil.isNotEmpty(vo.getSource())) {
            helpSupportLambdaQueryChainWrapper.eq(ClickStatistics::getSource, vo.getSource());
        }
        if (StrUtil.isNotEmpty(vo.getIp())) {
            helpSupportLambdaQueryChainWrapper.and(registerAppSuccessLambdaQueryWrapper -> {
                registerAppSuccessLambdaQueryWrapper.
//                        like(ClickStatistics::getIp, vo.getIp()).or().
                        like(ClickStatistics::getUrl, vo.getIp()).or().
                        like(ClickStatistics::getSource, vo.getIp());
            });
        }
        return helpSupportLambdaQueryChainWrapper;
    }

    @Override
    public void excelDownLoad(HttpServletRequest request, HttpServletResponse response, ClickStatisticsVo vo) throws Exception {
        List<ClickStatistics> list = downloadCommom(vo).orderByDesc(ClickStatistics::getCreateTime).list();
        //转换
        List<ClickStatisticsExportVo> registerButtonExportVos =
                list.stream().map(ClickStatisticsVoConver.INSTANCT::getClickStatisticsExportVo).map(registerButtonExportVo -> {
                    dealData(registerButtonExportVo);
                    return registerButtonExportVo;
                }).collect(Collectors.toList());
        String fileName = "按钮点击统计_" + DateUtil.formatTime(new Date());
        EasyExcelUtils.writeExcel(response, registerButtonExportVos, fileName, "统计", ClickStatisticsExportVo.class);
    }

    @Override
    public int downloadCount(ClickStatisticsVo vo) {
        return downloadCommom(vo).count().intValue();
    }

    @Override
    public void getList(Model model, ClickStatisticsVo vo) {
        LambdaQueryChainWrapper<ClickStatistics> registerButtonLambdaQueryChainWrapper = downloadCommom(vo).orderByDesc(ClickStatistics::getCreateTime);
        Page<ClickStatistics> page = registerButtonLambdaQueryChainWrapper
                .page(new Page<>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        List<ClickStatisticsVo> registerButtonVos = ClickStatisticsVoConver.INSTANCT.getClickStatisticsVos(page.getRecords());
        List<ClickStatisticsVo> collect = registerButtonVos.stream().map(registerButtonVo -> {
            registerButtonVo.setSource(getSource(registerButtonVo.getSource()));
            return registerButtonVo;
        }).collect(Collectors.toList());
        model.addAttribute("totalCount", page.getTotal());
        model.addAttribute("list", collect);

    }

    private void dealData(ClickStatisticsExportVo registerButtonExportVo) {
        registerButtonExportVo.setSource(getSource(registerButtonExportVo.getSource()));
    }

    private String getSource(String source) {
        ClickStatisticsEnum enumByCode = ClickStatisticsEnum.getEnumByCode(source);
        if (Objects.isNull(enumByCode)) {
            return "";
        }
        return enumByCode.getCnValue();
    }
    
}

package com.cellosquare.adminapp.admin.activities.service.impl;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.converters.bigdecimal.BigDecimalStringConverter;
import com.alibaba.excel.converters.date.DateStringConverter;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cellosquare.adminapp.admin.activities.conver.ActivitiesRegistrationDEConver;
import com.cellosquare.adminapp.admin.activities.conver.ActivitiesRegistrationVoConver;
import com.cellosquare.adminapp.admin.activities.entity.ActivitiesRegistration;
import com.cellosquare.adminapp.admin.activities.mapper.ActivitiesRegistrationMapper;
import com.cellosquare.adminapp.admin.activities.service.IActivitiesRegistrationService;
import com.cellosquare.adminapp.admin.activities.service.ICorporateActivitiesService;
import com.cellosquare.adminapp.admin.activities.vo.ActivitiesRegistrationExportVo;
import com.cellosquare.adminapp.admin.activities.vo.ActivitiesRegistrationVo;
import com.cellosquare.adminapp.admin.activities.vo.CorporateActivitiesVo;
import com.cellosquare.adminapp.admin.helpSupport.vo.HelpSupportVo;
import com.cellosquare.adminapp.common.constant.ActivitiesEnum;
import com.cellosquare.adminapp.common.util.DateUtils;
import com.cellosquare.adminapp.common.util.EasyExcelUtils;
import com.cellosquare.adminapp.common.util.ExcelDownloadUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hugo
 * @since 2023-03-68 14:14:19
 */
@Service
public class ActivitiesRegistrationServiceImpl extends ServiceImpl<ActivitiesRegistrationMapper, ActivitiesRegistration> implements IActivitiesRegistrationService {
    @Autowired
    private ICorporateActivitiesService corporateActivitiesServiceImpl;

    /**
     * 通用lambda
     *
     * @param vo
     * @return
     */
    public LambdaQueryChainWrapper<ActivitiesRegistration> downloadCommom(ActivitiesRegistrationVo vo) {
        LambdaQueryChainWrapper<ActivitiesRegistration> helpSupportLambdaQueryChainWrapper = lambdaQuery();
        if (StrUtil.isNotEmpty(vo.getStartDate()) && StrUtil.isNotEmpty(vo.getEndDate())) {
            helpSupportLambdaQueryChainWrapper.ge(ActivitiesRegistration::getCreateDate, DateUtils.getDateForMatt(0, vo.getStartDate()))
                    .le(ActivitiesRegistration::getCreateDate, DateUtils.getDateForMatt(1, vo.getEndDate()));
        }
        if (StrUtil.isNotEmpty(vo.getActivitiesId())) {
            helpSupportLambdaQueryChainWrapper.eq(ActivitiesRegistration::getActivitiesId, Long.valueOf(vo.getActivitiesId()));
        }
        return helpSupportLambdaQueryChainWrapper;
    }

    /**
     * 解密
     *
     * @param activitiesRegistrationVo
     * @return
     */
    public ActivitiesRegistrationVo getActivitiesRegistrationVo(ActivitiesRegistrationVo activitiesRegistrationVo) {
        return ActivitiesRegistrationDEConver.INSTANCT.getDecodedActivitiesRegistration(activitiesRegistrationVo);
    }

    /**
     * 转换vo
     *
     * @param list
     * @return
     */
    public List<ActivitiesRegistrationVo> convertList(List<ActivitiesRegistration> list) {
        List<ActivitiesRegistrationVo> result = new ArrayList<>();
        list.forEach(temp -> {
                    ActivitiesRegistrationVo activitiesRegistrationVo = ActivitiesRegistrationVoConver.INSTANCT.getActivitiesRegistrationVo(temp);
                    //解密
                    result.add(getActivitiesRegistrationVo(activitiesRegistrationVo));
                }
        );
        return result;
    }

    /**
     * 处理数据
     *
     * @param list
     * @return
     */
    public List<ActivitiesRegistrationVo> dealData(List<ActivitiesRegistrationVo> list) {
        //查询全部活动
        Map<String, CorporateActivitiesVo> CorporateActivitiesVoMap = corporateActivitiesServiceImpl.allList().stream().collect(Collectors.toMap(CorporateActivitiesVo::getId, corporateActivitiesVo -> corporateActivitiesVo));
        list.forEach(activitiesRegistrationVo -> {
            CorporateActivitiesVo corporateActivitiesVo = CorporateActivitiesVoMap.get(activitiesRegistrationVo.getActivitiesId());
            if (ObjectUtil.isNotEmpty(corporateActivitiesVo)) {
                activitiesRegistrationVo.setActivitiesName(corporateActivitiesVo.getName());
                //转换类型
                activitiesRegistrationVo.setActivitiesType(ActivitiesEnum.getEnumByCode(corporateActivitiesVo.getType()).getCnValue());
            }
        });
        return list;
    }


    @Override
    public void getList(Model model, ActivitiesRegistrationVo vo) {
        Page<ActivitiesRegistration> page = downloadCommom(vo).orderByDesc(ActivitiesRegistration::getId).page(new Page<>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        List<CorporateActivitiesVo> corporateActivitiesVos = corporateActivitiesServiceImpl.allList();
        model.addAttribute("totalCount", page.getTotal());
        model.addAttribute("list", dealData(convertList(page.getRecords())));
        model.addAttribute("corporateActivitiesList", corporateActivitiesVos.size() > 20 ? corporateActivitiesVos.subList(0, 19) : corporateActivitiesVos);
    }

    @Override
    public int downloadCount(ActivitiesRegistrationVo vo) {
        return downloadCommom(vo).count().intValue();
    }

    @Override
    public void excelDownLoad(HttpServletRequest request, HttpServletResponse response, ActivitiesRegistrationVo vo) throws IOException {
        try {
            List<ActivitiesRegistration> list = downloadCommom(vo).orderByDesc(ActivitiesRegistration::getId).list();
            //转换
            List<ActivitiesRegistrationVo> activitiesRegistrationVos = dealData(convertList(list));
            List<ActivitiesRegistrationExportVo> activitiesRegistrationExportVos = activitiesRegistrationVos.stream().
                    map(ActivitiesRegistrationVoConver.INSTANCT::getActivitiesRegistrationExportVo).collect(Collectors.toList());
            String fileName = "活动报名表_" + DateUtil.formatTime(new Date());
            EasyExcelUtils.writeExcel(response, activitiesRegistrationExportVos, fileName, "activities", ActivitiesRegistrationExportVo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

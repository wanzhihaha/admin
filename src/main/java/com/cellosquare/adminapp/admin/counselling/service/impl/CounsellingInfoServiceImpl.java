package com.cellosquare.adminapp.admin.counselling.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cellosquare.adminapp.admin.activities.vo.ActivitiesRegistrationExportVo;
import com.cellosquare.adminapp.admin.advertising.conver.EloquaConver;
import com.cellosquare.adminapp.admin.counselling.conver.CounsellingInfoExportVoConver;
import com.cellosquare.adminapp.admin.counselling.mapper.CounsellingInfoMapper;
import com.cellosquare.adminapp.admin.counselling.service.CounsellingInfoService;
import com.cellosquare.adminapp.admin.counselling.vo.CounsellingInfoExportVO;
import com.cellosquare.adminapp.admin.counselling.vo.CounsellingInfoVO;
import com.cellosquare.adminapp.common.constant.ContactSourceEnum;
import com.cellosquare.adminapp.common.util.DateUtils;
import com.cellosquare.adminapp.common.util.EasyExcelUtils;
import com.cellosquare.adminapp.common.util.ExcelDownloadUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CounsellingInfoServiceImpl extends ServiceImpl<CounsellingInfoMapper, CounsellingInfoVO> implements CounsellingInfoService {

    @Autowired
    private CounsellingInfoMapper counsellingInfoMapper;

    @Override
    public List<CounsellingInfoVO> selectUserInfo(CounsellingInfoVO vo) {
        List<CounsellingInfoVO> counsellingInfoVOs = counsellingInfoMapper.selectUserInfo(vo);
        return counsellingInfoVOs;
    }

    @Override
    public int selectUserInfoCount(CounsellingInfoVO vo) {
        return counsellingInfoMapper.selectUserInfoCount(vo);
    }

    @Override
    public int doWriteLog(CounsellingInfoVO vo) {
        return counsellingInfoMapper.doWriteLog(vo);
    }


    @Override
    public void excelDownLoad(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String category = request.getParameter("category");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String source = request.getParameter("source");
        CounsellingInfoVO counsellingInfoVO = new CounsellingInfoVO();
        counsellingInfoVO.setCategory(category);
        counsellingInfoVO.setStartDate(startDate + " 00:00:00");
        counsellingInfoVO.setEndDate(endDate + " 23:59:59");
        counsellingInfoVO.setSource(source);
        List<CounsellingInfoVO> result = selectUserInfo(counsellingInfoVO);
        List<CounsellingInfoVO> list = new ArrayList<>();
        //数据转换
        result.forEach(temp -> {
                    //大于此日期才 解密
                    if (DateUtils.compareDate(temp.getCreateDateStr(), "2022-12-26 18:00:00"))
                        temp = EloquaConver.INSTANCT.getDecodedEloqua(temp);
                    temp.setSource(ContactSourceEnum.getEnumByCode(temp.getSource()).getCnValue());
                    temp.setName(StringUtils.isEmpty(temp.getName()) ? StringUtils.join(Arrays.asList(temp.getLastName(), temp.getFirstName()), "") : temp.getName());
                    list.add(temp);
                }
        );
        List<CounsellingInfoExportVO> counsellingInfoExportVOS = CounsellingInfoExportVoConver.INSTANCT.getActivitiesRegistrationExportVos(list);
        //excel文件名
        String fileName = "用户咨询信息导出_" + System.currentTimeMillis();
        EasyExcelUtils.writeExcel(response, counsellingInfoExportVOS, fileName, "sheet", CounsellingInfoExportVO.class);
    }

    @Override
    public void jumpList(Model model, CounsellingInfoVO vo) {
        Page<CounsellingInfoVO> page = lambdaQuery()
                .eq(StrUtil.isNotEmpty(vo.getCategory()), CounsellingInfoVO::getCategory, vo.getCategory())
                .eq(StrUtil.isNotEmpty(vo.getSource()), CounsellingInfoVO::getSource, vo.getSource()).orderByDesc(CounsellingInfoVO::getCreateDate)
                .le(CounsellingInfoVO::getCreateDate, StringUtils.isNotEmpty(vo.getEndDate()) ?
                        DateUtils.parse(vo.getEndDate() + " 23:59:59", "yyyy-MM-dd HH:mm:ss") : new Date())
                .ge(CounsellingInfoVO::getCreateDate, StringUtils.isNotEmpty(vo.getStartDate()) ?
                        DateUtils.parse(vo.getStartDate() + " 00:00:00", "yyyy-MM-dd HH:mm:ss") :
                        DateUtils.parse(org.apache.http.client.utils.DateUtils.formatDate(new Date(), "yyyy-MM-dd"), "yyyy-MM-dd"))
                .page(new Page<>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        model.addAttribute("totalCount", page.getTotal());
        List<CounsellingInfoVO> list = new ArrayList<>();
        //数据转换
        page.getRecords().forEach(temp -> {
                    //大于此日期才 解密
                    if (DateUtils.compareDate(temp.getCreateDate(), "2022-12-26 18:00:00"))
                        temp = EloquaConver.INSTANCT.getDecodedEloqua(temp);
                    temp.setSource(ContactSourceEnum.getEnumByCode(temp.getSource()).getCnValue());
                    temp.setName(StringUtils.isEmpty(temp.getName()) ? StringUtils.join(Arrays.asList(temp.getLastName(), temp.getFirstName()), "") : temp.getName());
                    list.add(temp);
                }
        );
        model.addAttribute("list", list);
    }
}

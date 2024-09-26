package com.cellosquare.adminapp.admin.registerStatistics.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.cellosquare.adminapp.admin.manager.mapper.AdminManagerMapper;
import com.cellosquare.adminapp.admin.registerStatistics.conver.RegisterAppSuccessConver;
import com.cellosquare.adminapp.admin.registerStatistics.entity.FromSource;
import com.cellosquare.adminapp.admin.registerStatistics.mapper.FromSourceMapper;
import com.cellosquare.adminapp.admin.registerStatistics.service.IFromSourceService;
import com.cellosquare.adminapp.admin.registerStatistics.vo.FromSourceExportVo;
import com.cellosquare.adminapp.admin.registerStatistics.vo.FromSourceVo;
import com.cellosquare.adminapp.common.constant.Constants;
import com.cellosquare.adminapp.common.constant.RegisterStatisticsSourceEnum;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.util.EasyExcelUtils;
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
 * @since 2023-07-184 15:36:54
 */
@Service
public class FromSourceServiceImpl extends ServiceImpl<FromSourceMapper, FromSource> implements IFromSourceService {

    @Autowired
    private AdminManagerMapper adminManagerMapper;

    public void dealData(FromSourceVo vo) {
        if (Objects.isNull(vo)) {
            return;
        }
        vo.setTypeName(getSource(vo.getType()));
        //查询用户
        if (StringUtils.isNotBlank(vo.getInsPersonId())) {
            vo.setInsPersonNm(adminManagerMapper.getByUserId(vo.getInsPersonId()));
        }
        if (StringUtils.isNotBlank(vo.getUpdPersonId())) {
            vo.setUpdPersonNm(adminManagerMapper.getByUserId(vo.getUpdPersonId()));
        }
        String celloSquareUrl = XmlPropertyManager.getPropertyValue("celloSquare.website.prefix");
        celloSquareUrl += "?from_source=" + vo.getSourceVal();
        vo.setUrl(celloSquareUrl);
    }

    private LambdaQueryChainWrapper<FromSource> downloadCommom(FromSourceVo vo) {
        return lambdaQuery().eq(StringUtils.isNotEmpty(vo.getType()), FromSource::getType, vo.getType()).and(StringUtils.isNotEmpty(vo.getSourceVal()),
                fromSourceLambdaQueryWrapper -> {
                    fromSourceLambdaQueryWrapper.
                            like(FromSource::getSourceVal, vo.getSourceVal())
                            .or().
                            like(FromSource::getIdentifier, vo.getSourceVal())
                            .or().
                            like(FromSource::getAppSuccessIdentifier, vo.getSourceVal())
                            .or().
                            like(FromSource::getRemark, vo.getSourceVal())
                            .or()
                            .like(FromSource::getShareLink, vo.getSourceVal())
                            .or().
                            like(FromSource::getName, vo.getSourceVal());
                }).notIn(FromSource::getId, Long.valueOf(4));
    }

    @Override
    public void getList(Model model, FromSourceVo vo) {
        Page<FromSource> page = downloadCommom(vo)
                .orderByDesc(FromSource::getId)
                .page(new Page<>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        List<FromSourceVo> list = page.getRecords().stream().map(RegisterAppSuccessConver.INSTANCT::getFromSourceVo).map(fromSourceVo -> {
            dealData(fromSourceVo);
            return fromSourceVo;
        }).collect(Collectors.toList());
        model.addAttribute("totalCount", page.getTotal());
        model.addAttribute("list", list);
    }

    @Override
    public void detail(Model model, FromSourceVo vo) {
        FromSource one = lambdaQuery().eq(FromSource::getId, Long.valueOf(vo.getId())).one();
        FromSourceVo fromSourceVo = RegisterAppSuccessConver.INSTANCT.getFromSourceVo(one);
        fromSourceVo.setTypeName(getSource(fromSourceVo.getType()));
        model.addAttribute("detail", fromSourceVo);
        model.addAttribute("vo", vo);
    }

    @Override
    public void doWrite(HttpServletRequest request, FromSourceVo vo) {
        FromSource fromSource = RegisterAppSuccessConver.INSTANCT.getFromSource(vo);
        setMsg(request, fromSource);
        save(fromSource);
    }

    @Override
    public void doUpdate(HttpServletRequest request, FromSourceVo vo) {
        FromSource fromSource = RegisterAppSuccessConver.INSTANCT.getFromSource(vo);
        setMsg(request, fromSource);
        updateById(fromSource);
    }

    @Override
    public void doDelete(FromSourceVo vo) {
        removeById(Long.valueOf(vo.getId()));
    }

    @Override
    public void excelDownLoad(HttpServletRequest request, HttpServletResponse response, FromSourceVo vo) throws Exception {
        List<FromSource> list = downloadCommom(vo)
                .orderByDesc(FromSource::getInsDtm).list();
        List<FromSourceExportVo> fromSourceExportVos = list.stream().map(RegisterAppSuccessConver.INSTANCT::getFromSourceVo).map(fromSourceVo -> {
            dealData(fromSourceVo);
            return fromSourceVo;
        }).map(RegisterAppSuccessConver.INSTANCT::getFromSourceExportVo).collect(Collectors.toList());
        String fileName = "来源导出_" + DateUtil.formatTime(new Date());
        EasyExcelUtils.writeExcel(response, fromSourceExportVos, fileName, "来源", FromSourceExportVo.class);
    }

    @Override
    public int downloadCount(FromSourceVo vo) {
        return downloadCommom(vo).count().intValue();
    }

    private void setMsg(HttpServletRequest request, FromSource vo) {
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
        if (StringUtils.isEmpty(vo.getShareLink())) {
            String celloSquareUrl = XmlPropertyManager.getPropertyValue("celloSquare.website.prefix");
            celloSquareUrl += "?" + Constants.FROM_SOURCE + "=" + vo.getSourceVal();
            vo.setShareLink(celloSquareUrl);
        }
    }

    private String getSource(String source) {
        return RegisterStatisticsSourceEnum.getEnumByCode(source).getCnValue();
    }
}

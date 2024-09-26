package com.cellosquare.adminapp.admin.quote.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cellosquare.adminapp.admin.helpSupport.entity.HelpSupport;
import com.cellosquare.adminapp.admin.manager.mapper.AdminManagerMapper;
import com.cellosquare.adminapp.admin.quote.cover.QuoteVoConver;
import com.cellosquare.adminapp.admin.quote.entity.QuoteReturnReason;
import com.cellosquare.adminapp.admin.quote.entity.QuoteRoute;
import com.cellosquare.adminapp.admin.quote.entity.vo.AdminRouteVO;
import com.cellosquare.adminapp.admin.quote.entity.vo.QuoteReturnReasonVo;
import com.cellosquare.adminapp.admin.quote.mapper.QuoteReturnReasonMapper;
import com.cellosquare.adminapp.admin.quote.service.IQuoteReturnReasonService;
import com.cellosquare.adminapp.common.constant.ProductModeEnum;
import com.cellosquare.adminapp.common.enums.ReturnReasonTypeEnum;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
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
 * @author hugo
 * @since 2023-06-157 09:11:30
 */
@Service
public class QuoteReturnReasonServiceImpl extends ServiceImpl<QuoteReturnReasonMapper, QuoteReturnReason> implements IQuoteReturnReasonService {
    @Autowired
    private AdminManagerMapper adminManagerMapper;

    /**
     * 处理数据
     *
     * @param vo
     */
    public void dealData(QuoteReturnReasonVo vo) {
        if (Objects.isNull(vo)) {
            return;
        }
        //查询用户
        if (org.apache.commons.lang.StringUtils.isNotBlank(vo.getInsPersonId())) {
            vo.setInsPersonNm(adminManagerMapper.getByUserId(vo.getInsPersonId()));
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(vo.getUpdPersonId())) {
            vo.setUpdPersonNm(adminManagerMapper.getByUserId(vo.getUpdPersonId()));
        }
        vo.setTypeNm(ReturnReasonTypeEnum.getEnumByCode(vo.getType()).getDesc());
    }

    /**
     * 设置信息
     *
     * @param vo
     */
    private void setMsg(QuoteReturnReason vo) {
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
    public void getList(Model model, QuoteReturnReasonVo vo) {
        Page<QuoteReturnReason> page = lambdaQuery()
                .eq(vo.getType() != null, QuoteReturnReason::getType, vo.getType())
                .like(StrUtil.isNotEmpty(vo.getReason()), QuoteReturnReason::getReason, vo.getReason())
                .orderByDesc(QuoteReturnReason::getInsDtm)
                .page(new Page<>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        model.addAttribute("totalCount", page.getTotal());
        List<QuoteReturnReasonVo> list = page.getRecords().stream().map(QuoteVoConver.INSTANCT::getQuoteReturnReasonVo).collect(Collectors.toList())
                .stream().map(quoteReturnReasonVo -> {
                    dealData(quoteReturnReasonVo);
                    return quoteReturnReasonVo;
                }).collect(Collectors.toList());
        model.addAttribute("list", list);
    }

    @Override
    public void detail(Model model, QuoteReturnReasonVo vo) {
        QuoteReturnReason one = lambdaQuery().eq(QuoteReturnReason::getId, Long.valueOf(vo.getId())).one();
        QuoteReturnReasonVo result = QuoteVoConver.INSTANCT.getQuoteReturnReasonVo(one);
        dealData(result);
        model.addAttribute("detail", result);
        model.addAttribute("vo", vo);
    }

    @Override
    public void doWrite(QuoteReturnReasonVo vo) {
        QuoteReturnReason quoteReturnReason = QuoteVoConver.INSTANCT.getQuoteReturnReason(vo);
        setMsg(quoteReturnReason);
        save(quoteReturnReason);
    }

    @Override
    public void doUpdate(HttpServletRequest request, QuoteReturnReasonVo vo) {
        QuoteReturnReason quoteReturnReason = QuoteVoConver.INSTANCT.getQuoteReturnReason(vo);
        setMsg(quoteReturnReason);
        updateById(quoteReturnReason);
    }

    @Override
    public void doDelete(QuoteReturnReasonVo vo) {
        removeById(Long.valueOf(vo.getId()));
    }

    @Override
    public void doSortOrder(List<QuoteReturnReasonVo> quoteReturnReasonVos) {
        quoteReturnReasonVos.forEach(quoteReturnReasonVo -> {
            lambdaUpdate().set(QuoteReturnReason::getOrdb, quoteReturnReasonVo.getOrdb()).
                    eq(QuoteReturnReason::getId, Long.valueOf(quoteReturnReasonVo.getId())).update();
        });
    }
}

package com.cellosquare.adminapp.admin.recommend.service.Impl;


import com.baomidou.mybatisplus.core.injector.methods.DeleteById;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bluewaves.lab.message.XmlMessageManager;
import com.bluewaves.lab.message.util.ActionMessageUtil;
import com.cellosquare.adminapp.admin.manager.mapper.AdminManagerMapper;
import com.cellosquare.adminapp.admin.recommend.mapper.HotRecommendMapper;
import com.cellosquare.adminapp.admin.recommend.service.HotRecommendService;
import com.cellosquare.adminapp.admin.recommend.vo.HotRecommend;
import com.cellosquare.adminapp.common.constant.HotRecommendEnum;
import com.cellosquare.adminapp.common.constant.UseEnum;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Service
class HotRecommendServiceImpl extends ServiceImpl<HotRecommendMapper, HotRecommend> implements HotRecommendService {

    @Autowired
    private AdminManagerMapper adminManagerMapper;

    private void setMsg(HttpServletRequest request, HotRecommend vo) {
        AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
        vo.setLangCd(sessionForm.getLangCd());
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
    public void doWrite(HttpServletRequest request, HotRecommend vo) {
        setMsg(request, vo);
        save(vo);
    }

    @Override
    public void doUpdate(HttpServletRequest request, HotRecommend vo) {
        setMsg(request, vo);
        updateById(vo);
    }

    @Override
    public void doDelete(HotRecommend vo) {
        removeById(vo.getId());
    }

    /**
     * 处理数据
     *
     * @param hotRecommend
     */
    public void dealData(HotRecommend hotRecommend) {
        if (Objects.isNull(hotRecommend)) {
            return;
        }
        hotRecommend.setType(HotRecommendEnum.getEnumByCode(hotRecommend.getType()).getCnValue());
        hotRecommend.setUseYnNm(UseEnum.getEnumByCode(hotRecommend.getUseYn()).getCnValue());
        //查询用户
        if (StringUtils.isNotBlank(hotRecommend.getInsPersonId())) {
            hotRecommend.setInsPersonNm(adminManagerMapper.getByUserId(hotRecommend.getInsPersonId()));
        }
        if (StringUtils.isNotBlank(hotRecommend.getUpdPersonId())) {
            hotRecommend.setUpdPersonNm(adminManagerMapper.getByUserId(hotRecommend.getUpdPersonId()));
        }
    }
}

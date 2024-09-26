package com.cellosquare.adminapp.admin.activities.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.activities.entity.CorporateActivities;
import com.cellosquare.adminapp.admin.activities.vo.CorporateActivitiesVo;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hugo
 * @since 2023-03-67 14:44:39
 */
public interface ICorporateActivitiesService extends IService<CorporateActivities> {

    /**
     * 列表
     *
     * @param model
     * @param vo
     */
    void getList(Model model, CorporateActivitiesVo vo);


    /**
     * \
     * 详情
     *
     * @param model
     * @param vo
     */
    void detail(Model model, CorporateActivitiesVo vo);

    /**
     * 新增
     *
     * @param request
     * @param response
     * @param vo
     */
    void register(HttpServletRequest request, HttpServletResponse response, CorporateActivitiesVo vo, MultipartHttpServletRequest muServletRequest) throws Exception;

    /**
     * 修改页
     *
     * @param model
     * @param vo
     */
    void updateForm(Model model, CorporateActivitiesVo vo);

    /**
     * 修改
     *
     * @param request
     * @param response
     * @param vo
     */
    void doUpdate(HttpServletRequest request, HttpServletResponse response, CorporateActivitiesVo vo, MultipartHttpServletRequest muServletRequest) throws Exception;

    /**
     * 删除
     *
     * @param vo
     */
    void doDelete(CorporateActivitiesVo vo);

    /**
     * 全部活动
     *
     * @param vo
     * @return
     */
    List<CorporateActivitiesVo> allList();
}

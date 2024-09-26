package com.cellosquare.adminapp.admin.logisticsQa.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.logisticsQa.entity.LogisticsQa;
import com.cellosquare.adminapp.admin.logisticsQa.vo.LogisticsQaVo;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hugo
 * @since 2023-03-67 09:03:09
 */
public interface ILogisticsQaService extends IService<LogisticsQa> {

    /**
     * 列表
     *
     * @param model
     * @param vo
     */
    void getList(Model model, LogisticsQaVo vo);

    /**
     * \
     * 详情
     *
     * @param model
     * @param vo
     */
    void detail(Model model, LogisticsQaVo vo);

    /**
     * 新增
     *
     * @param request
     * @param response
     * @param vo
     */
    void register(HttpServletRequest request, HttpServletResponse response, LogisticsQaVo vo, MultipartHttpServletRequest muServletRequest) throws Exception;

    /**
     * 修改页
     *
     * @param model
     * @param vo
     */
    void updateForm(Model model, LogisticsQaVo vo);

    /**
     * 修改
     *
     * @param request
     * @param response
     * @param vo
     */
    void doUpdate(HttpServletRequest request, HttpServletResponse response, LogisticsQaVo vo, MultipartHttpServletRequest muServletRequest) throws Exception;

    /**
     * 删除
     *
     * @param vo
     */
    void doDelete(LogisticsQaVo vo);

    /**
     * 导入
     *
     * @param file
     * @param request
     * @param response
     */
    void importWord(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception;
}

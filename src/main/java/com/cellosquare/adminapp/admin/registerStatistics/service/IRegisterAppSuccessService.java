package com.cellosquare.adminapp.admin.registerStatistics.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.registerStatistics.entity.RegisterAppSuccess;
import com.cellosquare.adminapp.admin.registerStatistics.vo.RegisterAppSuccessVo;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author walker
 * @since 2023-07-184 15:36:54
 */
public interface IRegisterAppSuccessService extends IService<RegisterAppSuccess> {

    void getList(Model model, RegisterAppSuccessVo vo);

    void excelDownLoad(HttpServletRequest request, HttpServletResponse response, RegisterAppSuccessVo vo) throws Exception;

    int downloadCount(RegisterAppSuccessVo vo);
}

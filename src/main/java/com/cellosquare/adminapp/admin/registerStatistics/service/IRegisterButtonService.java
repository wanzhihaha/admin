package com.cellosquare.adminapp.admin.registerStatistics.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.registerStatistics.entity.RegisterButton;
import com.cellosquare.adminapp.admin.registerStatistics.vo.RegisterButtonVo;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hugo
 * @since 2023-05-146 08:47:10
 */
public interface IRegisterButtonService extends IService<RegisterButton> {

    void excelDownLoad(HttpServletRequest request, HttpServletResponse response, RegisterButtonVo vo) throws Exception;

    int downloadCount(RegisterButtonVo vo);

    void getList(Model model, RegisterButtonVo vo);
}

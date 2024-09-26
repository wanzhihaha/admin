package com.cellosquare.adminapp.admin.activities.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.activities.entity.ActivitiesRegistration;
import com.cellosquare.adminapp.admin.activities.vo.ActivitiesRegistrationVo;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hugo
 * @since 2023-03-68 14:14:19
 */
public interface IActivitiesRegistrationService extends IService<ActivitiesRegistration> {

    void getList(Model model, ActivitiesRegistrationVo vo);

    int downloadCount(ActivitiesRegistrationVo vo);

    void excelDownLoad(HttpServletRequest request, HttpServletResponse response, ActivitiesRegistrationVo vo) throws IOException;
}

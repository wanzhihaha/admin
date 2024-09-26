package com.cellosquare.adminapp.admin.clickStatistics.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.clickStatistics.entity.ClickStatistics;
import com.cellosquare.adminapp.admin.clickStatistics.vo.ClickStatisticsVo;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author walker
 * @since 2024-03-71 09:13:54
 */
public interface IClickStatisticsService extends IService<ClickStatistics> {

    void getList(Model model, ClickStatisticsVo vo);

    void excelDownLoad(HttpServletRequest request, HttpServletResponse response, ClickStatisticsVo vo) throws Exception;

    int downloadCount(ClickStatisticsVo vo);
}

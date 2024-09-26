package com.cellosquare.adminapp.admin.registerStatistics.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.recommend.vo.HotRecommend;
import com.cellosquare.adminapp.admin.registerStatistics.entity.FromSource;
import com.cellosquare.adminapp.admin.registerStatistics.vo.FromSourceVo;
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
public interface IFromSourceService extends IService<FromSource> {

    void getList(Model model, FromSourceVo vo);

    void detail(Model model, FromSourceVo vo);

    void doWrite(HttpServletRequest request, FromSourceVo vo);

    void doUpdate(HttpServletRequest request, FromSourceVo vo);

    void doDelete(FromSourceVo vo);

    void excelDownLoad(HttpServletRequest request, HttpServletResponse response, FromSourceVo vo) throws Exception;

    int downloadCount(FromSourceVo vo);
}

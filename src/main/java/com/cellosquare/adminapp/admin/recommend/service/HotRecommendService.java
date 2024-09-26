package com.cellosquare.adminapp.admin.recommend.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.recommend.vo.HotRecommend;

import javax.servlet.http.HttpServletRequest;

public interface HotRecommendService extends IService<HotRecommend> {


    /**
     * 新增
     *
     * @param request
     * @param vo
     */
    void doWrite(HttpServletRequest request, HotRecommend vo);

    /**
     * 修改
     *
     * @param vo
     */
    void doUpdate(HttpServletRequest request, HotRecommend vo);

    /**
     * 删除
     *
     * @param vo
     */
    void doDelete(HotRecommend vo);

    void dealData(HotRecommend hotRecommend);
}

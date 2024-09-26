package com.cellosquare.adminapp.admin.apihistory.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.apihistory.entity.ApiHistory;
import org.springframework.ui.Model;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author walker
 * @since 2023-08-230 11:03:00
 */
public interface IApiHistoryService extends IService<ApiHistory> {

    void getList(Model model, ApiHistory vo);

    void getDetail(Model model, ApiHistory vo);
}

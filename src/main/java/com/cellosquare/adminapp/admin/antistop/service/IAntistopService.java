package com.cellosquare.adminapp.admin.antistop.service;

import com.cellosquare.adminapp.admin.antistop.entity.Antistop;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author walker
 * @since 2023-03-68 09:15:21
 */
public interface IAntistopService extends IService<Antistop> {

    void seoMatch(HttpServletRequest request, HttpServletResponse response, List<String> listId);
}

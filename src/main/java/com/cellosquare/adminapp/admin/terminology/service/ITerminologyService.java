package com.cellosquare.adminapp.admin.terminology.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.terminology.entity.Terminology;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author walker
 * @since 2023-03-61 14:43:00
 */
public interface ITerminologyService extends IService<Terminology> {

    void seoMatch(HttpServletRequest request, HttpServletResponse response, List<String> listId);
}

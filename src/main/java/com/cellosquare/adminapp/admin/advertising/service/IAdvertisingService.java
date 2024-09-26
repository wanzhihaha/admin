package com.cellosquare.adminapp.admin.advertising.service;

import com.cellosquare.adminapp.admin.advertising.entity.Advertising;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.advertising.vo.AdvertisingVO;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author walker
 * @since 2022-12-340 15:25:29
 */
public interface IAdvertisingService extends IService<Advertising> {

    Boolean register(HttpServletRequest request, HttpServletResponse response, AdvertisingVO vo, MultipartHttpServletRequest muServletRequest);

    Boolean doUpdate(HttpServletRequest request, HttpServletResponse response, AdvertisingVO vo, MultipartHttpServletRequest muServletRequest);
}

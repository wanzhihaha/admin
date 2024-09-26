package com.cellosquare.adminapp.admin.quote.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.quote.entity.QuoteOceanPort;
import com.cellosquare.adminapp.admin.quote.entity.vo.AdminOceanPortVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hugo
 * @since 2023-06-157 09:34:50
 */
public interface IQuoteOceanPortService extends IService<QuoteOceanPort> {

    AdminOceanPortVO getDetail(String nodeCd);
}

package com.cellosquare.adminapp.admin.quote.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cellosquare.adminapp.admin.quote.cover.QuoteVoConver;
import com.cellosquare.adminapp.admin.quote.entity.QuoteAirPort;
import com.cellosquare.adminapp.admin.quote.entity.vo.AdminAirPortVO;
import com.cellosquare.adminapp.admin.quote.mapper.QuoteAirPortMapper;
import com.cellosquare.adminapp.admin.quote.service.IQuoteAirPortService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hugo
 * @since 2023-06-157 09:34:50
 */
@Service
public class QuoteAirPortServiceImpl extends ServiceImpl<QuoteAirPortMapper, QuoteAirPort> implements IQuoteAirPortService {

    @Override
    public AdminAirPortVO getDetail(String nodeCd) {
        return QuoteVoConver.INSTANCT.getAdminAirPortVO(lambdaQuery().eq(QuoteAirPort::getNodeCd, nodeCd).one());
    }
}

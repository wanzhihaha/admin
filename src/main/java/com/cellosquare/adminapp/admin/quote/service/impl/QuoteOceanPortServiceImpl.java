package com.cellosquare.adminapp.admin.quote.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cellosquare.adminapp.admin.quote.cover.QuoteVoConver;
import com.cellosquare.adminapp.admin.quote.entity.QuoteAirPort;
import com.cellosquare.adminapp.admin.quote.entity.QuoteOceanPort;
import com.cellosquare.adminapp.admin.quote.entity.vo.AdminOceanPortVO;
import com.cellosquare.adminapp.admin.quote.mapper.QuoteOceanPortMapper;
import com.cellosquare.adminapp.admin.quote.service.IQuoteOceanPortService;
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
public class QuoteOceanPortServiceImpl extends ServiceImpl<QuoteOceanPortMapper, QuoteOceanPort> implements IQuoteOceanPortService {

    @Override
    public AdminOceanPortVO getDetail(String nodeCd) {
        return QuoteVoConver.INSTANCT.getAdminOceanPortVO(lambdaQuery().eq(QuoteOceanPort::getNodeCd, nodeCd).one());
    }
}

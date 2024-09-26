package com.cellosquare.adminapp.admin.quote.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.quote.entity.QuoteReturnReason;
import com.cellosquare.adminapp.admin.quote.entity.vo.QuoteReturnReasonVo;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hugo
 * @since 2023-06-157 09:11:30
 */
public interface IQuoteReturnReasonService extends IService<QuoteReturnReason> {

    void getList(Model model, QuoteReturnReasonVo vo);

    void detail(Model model, QuoteReturnReasonVo vo);

    void doWrite(QuoteReturnReasonVo vo);

    void doUpdate(HttpServletRequest request, QuoteReturnReasonVo vo);

    void doDelete(QuoteReturnReasonVo vo);

    void doSortOrder(List<QuoteReturnReasonVo> quoteReturnReasonVos);
}

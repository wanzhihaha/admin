package com.cellosquare.adminapp.admin.quote.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.code.vo.ApiCodeVO;
import com.cellosquare.adminapp.admin.quote.entity.QuoteRoute;
import com.cellosquare.adminapp.admin.quote.entity.vo.AdminRouteVO;
import com.cellosquare.adminapp.admin.quote.entity.vo.RouteApiResponseVO;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hugo
 * @since 2023-06-157 09:11:30
 */
public interface IQuoteRouteService extends IService<QuoteRoute> {

    void getList(Model model, AdminRouteVO vo);

    AdminRouteVO getDetailByCondition(AdminRouteVO vo);

    void register(AdminRouteVO vo);

    AdminRouteVO getDetail(AdminRouteVO vo);

    void doUpdate(AdminRouteVO vo);

    void delete(AdminRouteVO vo);

    void flushRouteData(RouteApiResponseVO vo, Map<String, String> productModeMapping, List<Long> exitsRouteSeqNoList);

    void callRouteData();

    void deleteBatchByNodeCd(List<String> nodeCdList);
}

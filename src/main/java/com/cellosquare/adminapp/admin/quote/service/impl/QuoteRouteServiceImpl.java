package com.cellosquare.adminapp.admin.quote.service.impl;


import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bluewaves.lab.property.XmlPropertyManager;
import com.cellosquare.adminapp.admin.code.service.ApiCodeService;
import com.cellosquare.adminapp.admin.code.vo.ApiCodeVO;
import com.cellosquare.adminapp.admin.manager.mapper.AdminManagerMapper;
import com.cellosquare.adminapp.admin.quote.cover.QuoteVoConver;
import com.cellosquare.adminapp.admin.quote.entity.QuoteNode;
import com.cellosquare.adminapp.admin.quote.entity.QuoteRoute;
import com.cellosquare.adminapp.admin.quote.entity.vo.*;
import com.cellosquare.adminapp.admin.quote.mapper.QuoteRouteMapper;
import com.cellosquare.adminapp.admin.quote.service.*;
import com.cellosquare.adminapp.common.constant.*;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.util.DateUtils;
import com.cellosquare.adminapp.common.util.EncryptU;
import com.cellosquare.adminapp.common.util.HttpClientUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hugo
 * @since 2023-06-157 09:11:30
 */
@Service
public class QuoteRouteServiceImpl extends ServiceImpl<QuoteRouteMapper, QuoteRoute> implements IQuoteRouteService {
    @Autowired
    private AdminManagerMapper adminManagerMapper;
    @Autowired
    @Lazy
    private IQuoteNodeService quoteNodeServiceImpl;
    @Autowired
    @Lazy
    private IQuoteAirPortService quoteAirPortServiceImpl;
    @Autowired
    @Lazy
    private IQuoteOceanPortService quoteOceanPortServiceImpl;
    @Autowired
    @Lazy
    private IQuoteNationService quoteNationServiceImpl;
    @Autowired
    @Lazy
    private ApiCodeService apiCodeServiceImpl;
    @Autowired
    @Lazy
    private IQuoteRouteApiHistoryService quoteRouteApiHistoryServiceImpl;

    /**
     * 处理数据
     *
     * @param vo
     */
    public void dealData(AdminRouteVO vo) {
        if (Objects.isNull(vo)) {
            return;
        }
        //查询用户
        if (org.apache.commons.lang.StringUtils.isNotBlank(vo.getInsPersonId())) {
            vo.setInsPersonNm(adminManagerMapper.getByUserId(vo.getInsPersonId()));
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(vo.getUpdPersonId())) {
            vo.setUpdPersonNm(adminManagerMapper.getByUserId(vo.getUpdPersonId()));
        }
        vo.setProductModeNm(ProductModeEnum.getEnumByCode(vo.getProductMode()).getDesc());
    }

    /**
     * 设置信息
     *
     * @param vo
     */
    private void setMsg(QuoteRoute vo) {
        AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
        if (vo.getRouteSeqNo() == null) {//新增
            vo.setInsPersonId(sessionForm.getAdminId());
            vo.setUpdPersonId(sessionForm.getAdminId());
            vo.setInsDtm(new Timestamp(new Date().getTime()));
            vo.setUpdDtm(new Timestamp(new Date().getTime()));
        } else {//修改
            vo.setUpdPersonId(sessionForm.getAdminId());
            vo.setUpdDtm(new Timestamp(new Date().getTime()));
        }

    }

    private LambdaQueryChainWrapper<QuoteRoute> commonbaseSqL(AdminRouteVO vo) {
        LambdaQueryChainWrapper<QuoteRoute> quoteRouteLambdaQueryChainWrapper = lambdaQuery();
        if (StringUtils.isNotEmpty(vo.getProductMode())) {
            quoteRouteLambdaQueryChainWrapper.eq(QuoteRoute::getProductMode, vo.getProductMode());
        }
        if (StringUtils.isNotEmpty(vo.getSearchValue())) {
            quoteRouteLambdaQueryChainWrapper.and(quoteNodeLambdaQueryWrapper -> {
                quoteNodeLambdaQueryWrapper.or().like(QuoteRoute::getFromNode, vo.getSearchValue());
                quoteNodeLambdaQueryWrapper.or().like(QuoteRoute::getToNode, vo.getSearchValue());
                quoteNodeLambdaQueryWrapper.or().like(QuoteRoute::getNationCd, vo.getSearchValue());
                quoteNodeLambdaQueryWrapper.or().like(QuoteRoute::getProductNm, vo.getSearchValue());
            });
        }
        return quoteRouteLambdaQueryChainWrapper;
    }

    private LambdaQueryChainWrapper<QuoteRoute> commonSqL(AdminRouteVO vo) {
        return commonbaseSqL(vo).orderByDesc(QuoteRoute::getInsDtm).orderByDesc(QuoteRoute::getRouteSeqNo);
    }

    @Override
    public void getList(Model model, AdminRouteVO vo) {
        Page<QuoteRoute> page = commonSqL(vo)
                .page(new Page<>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        List<AdminRouteVO> routeVOS = page.getRecords().stream().map(QuoteVoConver.INSTANCT::getAdminRouteVO).collect(Collectors.toList());
        List<AdminRouteVO> adminRouteVOS = routeVOS.stream().map(adminRouteVO -> {
            dealData(adminRouteVO);
            return adminRouteVO;
        }).collect(Collectors.toList());
        model.addAttribute("totalCount", page.getTotal());
        model.addAttribute("list", adminRouteVOS);
    }

    @Override
    public AdminRouteVO getDetailByCondition(AdminRouteVO vo) {
        LambdaQueryChainWrapper<QuoteRoute> lambdaQueryChainWrapper = lambdaQuery().eq(QuoteRoute::getRouteSource, vo.getRouteSource()).eq(QuoteRoute::getFromNode, vo.getFromNode())
                .eq(QuoteRoute::getToNode, vo.getToNode()).eq(QuoteRoute::getProductMode, vo.getProductMode());
        if (StringUtils.isNotEmpty(vo.getProductId())) {
            lambdaQueryChainWrapper.eq(QuoteRoute::getProductId, vo.getProductId());
        }
        List<QuoteRoute> list = lambdaQueryChainWrapper.list();
        List<AdminRouteVO> collect = list.stream().map(QuoteVoConver.INSTANCT::getAdminRouteVO).collect(Collectors.toList());
        return collect.stream().findFirst().orElse(null);
    }

    @Override
    public void register(AdminRouteVO vo) {
        QuoteRoute quoteRoute = QuoteVoConver.INSTANCT.getQuoteRoute(vo);
        //默认中国
        quoteRoute.setNationCd(StringUtils.isNotEmpty(vo.getNationCd()) ? vo.getNationCd() : "CN");
        quoteRoute.setRouteSeqNo(null);
        setMsg(quoteRoute);
        save(quoteRoute);
        vo.setRouteSeqNo(quoteRoute.getRouteSeqNo().toString());
    }

    @Override
    public AdminRouteVO getDetail(AdminRouteVO vo) {
        QuoteRoute one = lambdaQuery().eq(QuoteRoute::getRouteSeqNo, Long.valueOf(vo.getRouteSeqNo())).one();
        AdminRouteVO adminRouteVO = QuoteVoConver.INSTANCT.getAdminRouteVO(one);
        dealData(adminRouteVO);
        return adminRouteVO;
    }

    @Override
    public void doUpdate(AdminRouteVO vo) {
        QuoteRoute quoteRoute = QuoteVoConver.INSTANCT.getQuoteRoute(vo);
        setMsg(quoteRoute);
        updateById(quoteRoute);
    }

    @Override
    public void delete(AdminRouteVO vo) {
        removeById(Long.valueOf(vo.getRouteSeqNo()));
    }

    /**
     * 刷 rout数据
     *
     * @param vo
     * @param productModeMapping
     * @param exitsRouteSeqNoList
     */
    @Override
    public void flushRouteData(RouteApiResponseVO vo, Map<String, String> productModeMapping, List<Long> exitsRouteSeqNoList) {
        /**
         *   1. 以countryCode区分site 数据
         *   2. 本周导入的route 在之前不存在 则new_flag设置为Y, 对于已存在的部分 保持不变. 不存在的部分进行删除
         *   3. 需要新增的node 根据product mode (ocean, Truk 调用 ocean Port, Air 调用 ari port)
         *   要全部执行完 才知道 哪些能删哪些不能删 [要不然就查询接口需要更新参数] 条条比对 执行, 最后按照时间进行多余的数据删除
         *   route 数据还是使用韩国时间
         */
        AdminRouteVO selectVO = new AdminRouteVO();
        // route 不与admin 相比较
        selectVO.setRouteSource(NodeSourceEnum.A.getCd());
        // node 与admin相比较
        for (RouteApiDetailVO routeApiDetailVO : vo.getList()) {
            String productMode = productModeMapping.get(routeApiDetailVO.getMode());
            if (StringUtils.isEmpty(productMode))
                continue;
            selectVO.setProductId(routeApiDetailVO.getProductId());
            selectVO.setNationCd(routeApiDetailVO.getCountryCode());
            selectVO.setFromNode(routeApiDetailVO.getDeppNm());
            selectVO.setToNode(routeApiDetailVO.getArrpNm());
            selectVO.setProductMode(productMode);
            selectVO.setProductNm(routeApiDetailVO.getProductName());
            //route 处理逻辑
            AdminRouteVO detail = getDetailByCondition(selectVO);
            if (detail == null) {
                detail = selectVO;
                detail.setNewFlag(RoutStatusEnum.Y.getCd());
                detail.setUseYn(UseEnum.USE.getCode());
                //node 处理逻辑
                flushNodeData(1, detail);
                flushNodeData(2, detail);
                //保存rout
                register(detail);
            }
            exitsRouteSeqNoList.add(Long.valueOf(detail.getRouteSeqNo()));

        }
    }

    /**
     * 刷 node数据
     *
     * @param type
     * @param route_source
     */
    private void flushNodeData(Integer type, AdminRouteVO route_source) {
        String productMode = route_source.getProductMode();
        String nodeCd = route_source.getToNode();
        if (1 == type) {
            nodeCd = route_source.getFromNode();
        }
        AdminNodeVO nodeVO = new AdminNodeVO();
        switch (ProductModeEnum.getEnumByCode(productMode)) {
            case VSLCL:
            case VSFCL: {
                nodeVO.setProductMode(ProductModeEnum.VS.getCd());
                break;
            }
            case AR: {
                nodeVO.setProductMode(ProductModeEnum.AR.getCd());
                break;
            }
        }

        nodeVO.setNodeCd(nodeCd);
        AdminNodeVO detail = quoteNodeServiceImpl.getDetail(nodeVO);
        if (null == detail) {
            nodeVO.setNodeStatus(NodeStatusEnum.N.getCd());
            nodeVO.setNodeSource(NodeSourceEnum.A.getCd());
            String nationCd = null;
            //add操作
            if (ProductModeEnum.AR.getCd().equals(productMode)) {
                //查询 air port
                AdminAirPortVO airPortVO = quoteAirPortServiceImpl.getDetail(nodeCd);
                if (null != airPortVO) {
                    nationCd = airPortVO.getNationCd();
                    nodeVO.setCityEngNm(airPortVO.getAirPortCityNm());
                    nodeVO.setNodeEngNm(airPortVO.getAirPortNm());
                }

            } else {
                //查询 city port
                AdminOceanPortVO adminOceanPortVO = quoteOceanPortServiceImpl.getDetail(nodeCd);
                if (null != adminOceanPortVO) {
                    nationCd = adminOceanPortVO.getNationCd();
                    nodeVO.setCityEngNm(adminOceanPortVO.getCityNm());
                    nodeVO.setNodeEngNm(adminOceanPortVO.getCityNm());
                }
            }
            if (StringUtils.isNotBlank(nationCd)) {
                AdminNationVO adminNationVO = quoteNationServiceImpl.getDetailByNationCd(nationCd);
                if (null != adminNationVO)
                    nodeVO.setNationSeqNo(adminNationVO.getNationSeqNo());
            }
            quoteNodeServiceImpl.registApi(nodeVO);
            //保存节点id
            if (1 == type) {
                route_source.setFromNodeId(nodeVO.getId());
            } else {
                route_source.setToNodeId(nodeVO.getId());
            }
        }else{
            //保存节点id
            if (1 == type) {
                route_source.setFromNodeId(detail.getId());
            } else {
                route_source.setToNodeId(detail.getId());
            }
        }
    }


    /**
     * 调用接口
     */
    @Override
    public void callRouteData() {
        String url = XmlPropertyManager.getPropertyValue(SystemConstant.OPENAPI_PRODUCT_URL);
        Map<String, String> headMap = new HashMap<>(1);
        String xApiKey = EncryptU.decrypt(XmlPropertyManager.getPropertyValue(SystemConstant.OPENAPI_QUOTE_KEY), SystemConstant.AES_SECRET_KEY);
        headMap.put(SystemConstant.X_API_KEY, xApiKey);
        int firstIndex = 0;
        int index = 0;
        url += "?rowsPerPage=500&firstIndex=";
        boolean flag = true;
        Map<String, String> productModeMapping = getProductModeMapping();
        List<Long> exitsRouteSeqNoList = new ArrayList<>();
        while (flag) {
            String requestUrl = url + firstIndex;
            RouteApiResponseVO routeData = getRouteData(headMap, requestUrl, index);
            if (routeData == null || CollectionUtils.isEmpty(routeData.getList())) {
                break;
            }
            flushRouteData(routeData, productModeMapping, exitsRouteSeqNoList);
            index++;
            firstIndex += routeData.getList().size() + 1;
            flag = !(routeData.getList().size() < 500);
        }

        if (exitsRouteSeqNoList.size() > 0) {
            deleteByListSeqNo(exitsRouteSeqNoList);
        }
    }

    private void deleteByListSeqNo(List<Long> exitsRouteSeqNoList) {
        QueryWrapper<QuoteRoute> queryWrapper = new QueryWrapper();
        queryWrapper.notIn("route_seq_no", exitsRouteSeqNoList)
                .eq("route_source", NodeSourceEnum.A.getCd());
        remove(queryWrapper);
    }

    private RouteApiResponseVO getRouteData(Map<String, String> headMap, String url, int sort) {
        String sendDate = DateUtil.now();
        String apiStatus = SystemConstant.FAIL;
        String responseBody = HttpClientUtils.doGet(headMap, url);
        if (org.apache.commons.lang.StringUtils.isNotBlank(responseBody)) {
            if (responseBody.startsWith("{") && responseBody.endsWith("}")) {
                apiStatus = SystemConstant.SUCCESS;
            }
        }
        String receivedDate = DateUtil.now();
        AdminRouteApiHistoryVO adminRouteApiHistoryVO = new AdminRouteApiHistoryVO();
        adminRouteApiHistoryVO.setApiUrl(url);
        adminRouteApiHistoryVO.setApiResponseBody(responseBody);
        adminRouteApiHistoryVO.setSendDate(Timestamp.valueOf(sendDate));
        adminRouteApiHistoryVO.setReceivedDate(Timestamp.valueOf(receivedDate));
        adminRouteApiHistoryVO.setResultOrd(sort);
        adminRouteApiHistoryVO.setApiStatus(apiStatus);
        quoteRouteApiHistoryServiceImpl.save(QuoteVoConver.INSTANCT.getRouteApiHistory(adminRouteApiHistoryVO));
        if (apiStatus.equals(SystemConstant.SUCCESS)) {
            return JSONObject.parseObject(responseBody, RouteApiResponseVO.class);
        }
        return null;
    }

    private Map<String, String> getProductModeMapping() {
        List<ApiCodeVO> apiCodeByGrpCd = apiCodeServiceImpl.getApiCodeByGrpCd(SystemConstant.QUOTE_CATEGORY_GRP);
        return apiCodeByGrpCd.stream().collect(Collectors.toMap(ApiCodeVO::getCdDesc, ApiCodeVO::getCd, (o1, o2) -> o1));

    }

   /* private Map<String, String> getCountryCodeMapping() {
        List<ApiCodeVO> apiCodeByGrpCd = apiCodeServiceImpl.getApiCodeByGrpCd(SystemConstant.ROUTE_COUNTRY_CD);
        Map<String, String> map = new HashMap<>();
        for (ApiCodeVO codeVO : apiCodeByGrpCd) {
            map.put(codeVO.getCd(), codeVO.getLangCd());
        }
        return map;
    }*/

    @Override
    public void deleteBatchByNodeCd(List<String> nodeCdList) {
        LambdaQueryChainWrapper<QuoteRoute> lambdaQueryChainWrapper = lambdaQuery().
                and(quoteRouteLambdaQueryWrapper -> {
                    quoteRouteLambdaQueryWrapper.in(QuoteRoute::getFromNode, nodeCdList)
                            .or().in(QuoteRoute::getToNode, nodeCdList);
                });
        remove(lambdaQueryChainWrapper);
    }

}

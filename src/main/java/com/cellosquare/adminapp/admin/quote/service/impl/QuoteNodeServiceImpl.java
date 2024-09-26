package com.cellosquare.adminapp.admin.quote.service.impl;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cellosquare.adminapp.admin.continent.entity.Continent;
import com.cellosquare.adminapp.admin.continent.service.IContinentService;
import com.cellosquare.adminapp.admin.manager.mapper.AdminManagerMapper;
import com.cellosquare.adminapp.admin.quote.cover.QuoteVoConver;
import com.cellosquare.adminapp.admin.quote.entity.QuoteNation;
import com.cellosquare.adminapp.admin.quote.entity.QuoteNode;
import com.cellosquare.adminapp.admin.quote.entity.vo.*;
import com.cellosquare.adminapp.admin.quote.mapper.QuoteNodeMapper;
import com.cellosquare.adminapp.admin.quote.service.IQuoteAirPortService;
import com.cellosquare.adminapp.admin.quote.service.IQuoteNationService;
import com.cellosquare.adminapp.admin.quote.service.IQuoteNodeService;
import com.cellosquare.adminapp.admin.quote.service.IQuoteRouteService;
import com.cellosquare.adminapp.common.constant.ContinentCdEnum;
import com.cellosquare.adminapp.common.constant.NodeSourceEnum;
import com.cellosquare.adminapp.common.constant.NodeStatusEnum;
import com.cellosquare.adminapp.common.constant.ProductModeEnum;
import com.cellosquare.adminapp.common.session.AdminSessionForm;
import com.cellosquare.adminapp.common.session.SessionManager;
import com.cellosquare.adminapp.common.util.EasyExcelUtils;
import com.nhncorp.lucy.security.xss.XssPreventer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
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
public class QuoteNodeServiceImpl extends ServiceImpl<QuoteNodeMapper, QuoteNode> implements IQuoteNodeService {
    @Autowired
    private AdminManagerMapper adminManagerMapper;
    @Autowired
    private IContinentService continentServiceImpl;
    @Autowired
    private IQuoteNationService quoteNationServiceImpl;
    @Autowired
    private IQuoteAirPortService quoteAirPortServiceImpl;
    @Autowired
    @Lazy
    private IQuoteRouteService quoteRouteServiceImpl;

    /**
     * 处理数据
     *
     * @param vo
     */
    public void dealData(AdminNodeVO vo, List<QuoteNation> quoteNations) {
        if (Objects.isNull(vo)) {
            return;
        }
        //查询用户
        if (StringUtils.isNotBlank(vo.getInsPersonId())) {
            vo.setInsPersonNm(adminManagerMapper.getByUserId(vo.getInsPersonId()));
        }
        if (StringUtils.isNotBlank(vo.getUpdPersonId())) {
            vo.setUpdPersonNm(adminManagerMapper.getByUserId(vo.getUpdPersonId()));
        }
        Map<Long, QuoteNation> map = quoteNations.stream().collect(Collectors.toMap(QuoteNation::getNationSeqNo, Function.identity()));
        QuoteNation quoteNation = map.get(Long.valueOf(vo.getNationSeqNo()));
        vo.setNationNm(quoteNation.getNationNm());
        vo.setNationCnNm(quoteNation.getNationCnNm());
        vo.setNodeStatusVal(NodeStatusEnum.getEnumByCode(vo.getNodeStatus()).getDesc());
        vo.setProductModeNm(ProductModeEnum.getEnumByCode(vo.getProductMode()).getDesc());
        vo.setContinentName(ContinentCdEnum.getEnumByCode(quoteNation.getContinentCd()).getDesc());
    }

    @Override
    public void getList(Model model, AdminNodeVO vo) {
        Page<QuoteNode> page = commonSQL(vo)
                .page(new Page<>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        //查询全部国家
        List<QuoteNation> quoteNations = quoteNationServiceImpl.getAll();
        List<AdminNodeVO> collect = page.getRecords().stream().map(QuoteVoConver.INSTANCT::getAdminNodeVO).collect(Collectors.toList());
        List<AdminNodeVO> voList = collect.stream().map(adminNodeVO -> {
            dealData(adminNodeVO, quoteNations);
            return adminNodeVO;
        }).collect(Collectors.toList());
        List<Continent> continentList = continentServiceImpl.lambdaQuery().orderByAsc(Continent::getFclOrdb).list();
        model.addAttribute("continentList", continentList);
        model.addAttribute("totalCount", page.getTotal());
        model.addAttribute("list", voList);
    }

    private LambdaQueryChainWrapper<QuoteNode> commonSQL(AdminNodeVO vo) {
        return commonBaseSQL(vo).last("ORDER BY ordb ::integer ASC,id desc");
    }

    private LambdaQueryChainWrapper<QuoteNode> commonBaseSQL(AdminNodeVO vo) {
        List<Long> searchNationIds = quoteNationServiceImpl.getSearchNation(vo);
        LambdaQueryChainWrapper<QuoteNode> quoteNodeLambdaQueryChainWrapper = lambdaQuery();
        if (StringUtils.isNotEmpty(vo.getSearchValue())) {
            quoteNodeLambdaQueryChainWrapper.and(quoteNodeLambdaQueryWrapper -> {
                quoteNodeLambdaQueryWrapper.or().like(QuoteNode::getNodeCd, vo.getSearchValue());
                quoteNodeLambdaQueryWrapper.or().like(QuoteNode::getNodeCnNm, vo.getSearchValue());
                quoteNodeLambdaQueryWrapper.or().like(QuoteNode::getNodeEngNm, vo.getSearchValue());
                quoteNodeLambdaQueryWrapper.or().like(QuoteNode::getSearchKeyWord, vo.getSearchValue());
                if (!CollectionUtils.isEmpty(searchNationIds)) {
                    quoteNodeLambdaQueryWrapper.or().in(QuoteNode::getNationSeqNo, searchNationIds);
                }
            });
        } else {
            if (!CollectionUtils.isEmpty(searchNationIds)) {
                quoteNodeLambdaQueryChainWrapper.in(QuoteNode::getNationSeqNo, searchNationIds);
            }
        }
        if (StringUtils.isNotEmpty(vo.getSearchType1())) {
            quoteNodeLambdaQueryChainWrapper.eq(QuoteNode::getProductMode, vo.getSearchType1());
        }
        if (StringUtils.isNotEmpty(vo.getSearchType())) {
            quoteNodeLambdaQueryChainWrapper.eq(QuoteNode::getIsHot, Integer.valueOf(vo.getSearchType()));
        }
        return quoteNodeLambdaQueryChainWrapper;
    }

    @Override
    public AdminNodeVO getDetail(AdminNodeVO vo) {
        QuoteNode one = lambdaQuery().eq(QuoteNode::getNodeCd, vo.getNodeCd()).one();
        AdminNodeVO detail = QuoteVoConver.INSTANCT.getAdminNodeVO(one);
        List<QuoteNation> all = quoteNationServiceImpl.getAll();
        dealData(detail, all);
        return detail;
    }

    /**
     * 设置信息
     *
     * @param vo
     */
    private void setMsg(QuoteNode vo) {
        AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
        if (vo.getId() == null) {//新增
            vo.setInsPersonId(sessionForm.getAdminId());
            vo.setUpdPersonId(sessionForm.getAdminId());
            vo.setInsDtm(new Timestamp(new Date().getTime()));
            vo.setUpdDtm(new Timestamp(new Date().getTime()));
        } else {//修改
            vo.setUpdPersonId(sessionForm.getAdminId());
            vo.setUpdDtm(new Timestamp(new Date().getTime()));
        }

    }

    @Override
    public void regist(AdminNodeVO vo) {
        QuoteNode quoteNode = QuoteVoConver.INSTANCT.getQuoteNode(vo);
        quoteNode.setNodeStatus(NodeStatusEnum.C.getCd());
        quoteNode.setNodeSource(NodeSourceEnum.E.getCd());
        quoteNode.setNodeCd(vo.getNodeCd().trim());
        quoteNode.setSearchKeyWord(XssPreventer.unescape(vo.getSearchKeyWord()));
        getAirportName(quoteNode);
        setMsg(quoteNode);
        save(quoteNode);
    }

    @Override
    public void registApi(AdminNodeVO vo) {
        QuoteNode quoteNode = QuoteVoConver.INSTANCT.getQuoteNode(vo);
        setMsg(quoteNode);
        save(quoteNode);
        vo.setId(quoteNode.getId().toString());
    }

    @Override
    public void doUpdate(AdminNodeVO vo) {
        QuoteNode quoteNode = QuoteVoConver.INSTANCT.getQuoteNode(vo);
        setMsg(quoteNode);
        quoteNode.setNodeStatus(NodeStatusEnum.C.getCd());
        quoteNode.setSearchKeyWord(XssPreventer.unescape(vo.getSearchKeyWord()));
//        quoteRouteServiceImpl.updateNodeByAdminEnter(vo);
        updateById(quoteNode);
    }

    @Override
    public void delete(AdminNodeVO vo) {
        removeById(Long.valueOf(vo.getId()));
    }

    @Override
    public Page<QuoteNode> getInnerPage(AdminNodeVO vo) {
        LambdaQueryChainWrapper<QuoteNode> quoteNodeLambdaQueryChainWrapper = lambdaQuery().orderByDesc(QuoteNode::getInsDtm);

        if (StringUtils.isNotEmpty(vo.getSearchValue())) {
            quoteNodeLambdaQueryChainWrapper.and(quoteNodeLambdaQueryWrapper -> {
                quoteNodeLambdaQueryWrapper.or().like(QuoteNode::getNodeCd, vo.getSearchValue());
                quoteNodeLambdaQueryWrapper.or().like(QuoteNode::getNodeCnNm, vo.getSearchValue());
                quoteNodeLambdaQueryWrapper.or().like(QuoteNode::getNodeEngNm, vo.getSearchValue());
                quoteNodeLambdaQueryWrapper.or().like(QuoteNode::getSearchKeyWord, vo.getSearchValue());
            });
        }
        if (StringUtils.isNotEmpty(vo.getNationSeqNo())) {
            quoteNodeLambdaQueryChainWrapper.eq(QuoteNode::getNationSeqNo, Long.valueOf(vo.getNationSeqNo()));
        }
        Page<QuoteNode> page = quoteNodeLambdaQueryChainWrapper
                .page(new Page<>(Integer.parseInt(vo.getPage()), Integer.parseInt(vo.getRowPerPage())));
        return page;
    }

    @Override
    public void excelDownLoad(HttpServletRequest request, HttpServletResponse response, AdminNodeVO vo) throws Exception {
        List<QuoteNode> list = commonSQL(vo).list();
        //查询全部国家
        List<QuoteNation> quoteNations = quoteNationServiceImpl.getAll();
        //Map<Long, QuoteNation> map = quoteNations.stream().collect(Collectors.toMap(QuoteNation::getNationSeqNo, Function.identity()));
        List<AdminNodeVO> collect = list.stream().map(QuoteVoConver.INSTANCT::getAdminNodeVO).collect(Collectors.toList());
        List<AdminNodeVO> voList = collect.stream().map(adminNodeVO -> {
            dealData(adminNodeVO, quoteNations);
            return adminNodeVO;
        }).collect(Collectors.toList());
        List<QuoteNodeExportVo> nationExportVos = voList.stream().map(QuoteVoConver.INSTANCT::getQuoteNodeExportVo).collect(Collectors.toList());
        String fileName = "地址节点_" + DateUtil.formatTime(new Date());
        EasyExcelUtils.writeExcel(response, nationExportVos, fileName, "nation", QuoteNodeExportVo.class);
    }

    @Override
    public int downloadCount(AdminNodeVO vo) {
        return commonBaseSQL(vo).count().intValue();
    }

    @Override
    public void setHotOrNot(AdminNodeVO vo) {
        AdminSessionForm sessionForm = SessionManager.getAdminSessionForm();
        update(Wrappers.<QuoteNode>lambdaUpdate()
                .set(QuoteNode::getIsHot, vo.getIsHot())
                .set(QuoteNode::getUpdPersonId, sessionForm.getAdminId())
                .set(QuoteNode::getUpdDtm, new Timestamp(new Date().getTime()))
                .eq(QuoteNode::getId, Long.valueOf(vo.getId()))
        );
    }

    @Override
    public void doSortOrder(List<AdminNodeVO> adminNodeVOS) {
        adminNodeVOS.forEach(adminNodeVO -> {
            lambdaUpdate().set(QuoteNode::getOrdb, StringUtils.isNotEmpty(adminNodeVO.getOrdb()) ? adminNodeVO.getOrdb() : null).
                    eq(QuoteNode::getId, Long.valueOf(adminNodeVO.getId())).update();
        });
    }

    private void getAirportName(QuoteNode vo) {
        if (StringUtils.isEmpty(vo.getCityEngNm())) {
            AdminAirPortVO detail = quoteAirPortServiceImpl.getDetail(vo.getNodeCd());
            if (!Objects.isNull(detail)) {
                vo.setCityEngNm(detail.getAirPortNm());
            } else {
                vo.setCityEngNm(vo.getNodeEngNm());
            }
        }
    }
}

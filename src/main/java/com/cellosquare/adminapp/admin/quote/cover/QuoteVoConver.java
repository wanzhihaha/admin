package com.cellosquare.adminapp.admin.quote.cover;

import com.cellosquare.adminapp.admin.helpSupport.entity.HelpSupport;
import com.cellosquare.adminapp.admin.helpSupport.vo.HelpSupportExportVo;
import com.cellosquare.adminapp.admin.helpSupport.vo.HelpSupportVo;
import com.cellosquare.adminapp.admin.quote.entity.*;
import com.cellosquare.adminapp.admin.quote.entity.vo.*;
import com.cellosquare.adminapp.common.util.ConverUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ConverUtil.class)
public interface QuoteVoConver {
    QuoteVoConver INSTANCT = Mappers.getMapper(QuoteVoConver.class);


    @Mapping(target = "insDtm", source = "insDtm", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updDtm", source = "updDtm", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "useYnNm", source = "useYn", qualifiedByName = "converEnableFlag")
    AdminNodeVO getAdminNodeVO(QuoteNode quoteNode);

    @Mapping(target = "insDtm", source = "insDtm", qualifiedByName = "toTimestamp")
    @Mapping(target = "updDtm", source = "updDtm", qualifiedByName = "toTimestamp")
    @Mapping(target = "id", source = "id", qualifiedByName = "converIdToId")
    QuoteNode getQuoteNode(AdminNodeVO adminNodeVO);

    AdminAirPortVO getAdminAirPortVO(QuoteAirPort quoteAirPort);

    AdminOceanPortVO getAdminOceanPortVO(QuoteOceanPort quoteOceanPort);


    @Mapping(target = "insDtm", source = "insDtm", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updDtm", source = "updDtm", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "useYnNm", source = "useYn", qualifiedByName = "converEnableFlag")
    AdminNationVO getAdminNationVO(QuoteNation quoteNation);

    @Mapping(target = "insDtm", source = "insDtm", qualifiedByName = "toTimestamp")
    @Mapping(target = "updDtm", source = "updDtm", qualifiedByName = "toTimestamp")
    @Mapping(target = "nationSeqNo", source = "nationSeqNo", qualifiedByName = "converIdToId")
    QuoteNation getQuoteNation(AdminNationVO adminNationVO);


    QuoteNationExportVo getQuoteNationExportVo(AdminNationVO adminNationVO);

    QuoteNodeExportVo getQuoteNodeExportVo(AdminNodeVO adminNodeVO);


    @Mapping(target = "insDtm", source = "insDtm", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updDtm", source = "updDtm", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "useYnNm", source = "useYn", qualifiedByName = "converEnableFlag")
    AdminRouteVO getAdminRouteVO(QuoteRoute quoteRoute);

    @Mapping(target = "insDtm", source = "insDtm", qualifiedByName = "toTimestamp")
    @Mapping(target = "updDtm", source = "updDtm", qualifiedByName = "toTimestamp")
    @Mapping(target = "routeSeqNo", source = "routeSeqNo", qualifiedByName = "converIdToId")
    @Mapping(target = "fromNodeId", source = "fromNodeId", qualifiedByName = "converIdToId")
    @Mapping(target = "toNodeId", source = "toNodeId", qualifiedByName = "converIdToId")
    QuoteRoute getQuoteRoute(AdminRouteVO adminRouteVO);

    RouteApiHistory getRouteApiHistory(AdminRouteApiHistoryVO adminRouteApiHistoryVO);

    @Mapping(target = "insDtm", source = "insDtm", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updDtm", source = "updDtm", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "useYnNm", source = "useYn", qualifiedByName = "converEnableFlag")
    QuoteReturnReasonVo getQuoteReturnReasonVo(QuoteReturnReason quoteReturnReason);

    @Mapping(target = "insDtm", source = "insDtm", qualifiedByName = "toTimestamp")
    @Mapping(target = "updDtm", source = "updDtm", qualifiedByName = "toTimestamp")
    @Mapping(target = "id", source = "id", qualifiedByName = "converIdToId")
    QuoteReturnReason getQuoteReturnReason(QuoteReturnReasonVo quoteReturnReasonVo);
}

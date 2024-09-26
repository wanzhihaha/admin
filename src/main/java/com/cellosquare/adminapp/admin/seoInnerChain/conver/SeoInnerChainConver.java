package com.cellosquare.adminapp.admin.seoInnerChain.conver;

import com.cellosquare.adminapp.admin.seoInnerChain.entity.SeoInnerChain;
import com.cellosquare.adminapp.admin.seoInnerChain.vo.SeoInnerChainVO;
import com.cellosquare.adminapp.admin.terminology.conver.TerminologyConver;
import com.cellosquare.adminapp.admin.terminology.entity.Terminology;
import com.cellosquare.adminapp.admin.terminology.vo.TerminologyVO;
import com.cellosquare.adminapp.common.util.ConverUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ConverUtil.class)
public interface SeoInnerChainConver {
    SeoInnerChainConver INSTANCT = Mappers.getMapper(SeoInnerChainConver.class);

    List<SeoInnerChainVO> getSeoInnerChainVOs(List<SeoInnerChain> seoInnerChains);
    @Mapping(target = "insDtm",source = "insDtm",dateFormat="yyyy-MM-dd")
    @Mapping(target = "updDtm",source = "updDtm",dateFormat="yyyy-MM-dd")
    SeoInnerChainVO getSeoInnerChainVO(SeoInnerChain seoInnerChain);

    @Mapping(target = "insDtm",ignore = true)
    @Mapping(target = "updDtm",ignore = true)
    @Mapping(target = "startTime",source = "startTime",qualifiedByName = "converEffectiveTime")
    @Mapping(target = "endTime",source = "endTime",qualifiedByName = "converEffectiveTime")
    SeoInnerChain getSeoInnerChain(SeoInnerChainVO seoInnerChainVO);
}

package com.cellosquare.adminapp.admin.logisticsQa.conver;

import com.cellosquare.adminapp.admin.logisticsQa.entity.LogisticsQa;
import com.cellosquare.adminapp.admin.logisticsQa.vo.LogisticsQaVo;
import com.cellosquare.adminapp.common.util.ConverUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ConverUtil.class)
public interface LogisticsQaVoConver {
    LogisticsQaVoConver INSTANCT = Mappers.getMapper(LogisticsQaVoConver.class);


    @Mapping(target = "useYnNm", source = "useYn", qualifiedByName = "converEnableFlag")
    LogisticsQaVo getLogisticsQaVo(LogisticsQa logisticsQa);


    @Mapping(target = "id", source = "id", qualifiedByName = "converIdToId")
    LogisticsQa getLogisticsQa(LogisticsQaVo logisticsQaVo);
}

package com.cellosquare.adminapp.admin.activities.conver;

import com.cellosquare.adminapp.admin.activities.entity.CorporateActivities;
import com.cellosquare.adminapp.admin.activities.vo.CorporateActivitiesVo;
import com.cellosquare.adminapp.common.util.ConverUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ConverUtil.class)
public interface CorporateActivitiesVoConver {
    CorporateActivitiesVoConver INSTANCT = Mappers.getMapper(CorporateActivitiesVoConver.class);

    @Mapping(target = "effectiveTime",source = "effectiveTime",dateFormat="yyyy-MM-dd")
    @Mapping(target = "deadTime",source = "deadTime",dateFormat="yyyy-MM-dd")
    @Mapping(target = "useYnNm", source = "useYn", qualifiedByName = "converEnableFlag")
    CorporateActivitiesVo getCorporateActivitiesVo(CorporateActivities corporateActivities);

    @Mapping(target = "effectiveTime",source = "effectiveTime",qualifiedByName = "converEffectiveTime")
    @Mapping(target = "deadTime",source = "deadTime",qualifiedByName = "converEffectiveTime")
    @Mapping(target = "id", source = "id", qualifiedByName = "converIdToId")
    CorporateActivities getCorporateActivities(CorporateActivitiesVo corporateActivitiesVo);
}

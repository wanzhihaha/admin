package com.cellosquare.adminapp.admin.continent.conver;


import com.cellosquare.adminapp.admin.continent.entity.Continent;
import com.cellosquare.adminapp.admin.continent.vo.ContinentVo;
import com.cellosquare.adminapp.admin.registerStatistics.entity.RegisterButton;
import com.cellosquare.adminapp.admin.registerStatistics.vo.RegisterButtonVo;
import com.cellosquare.adminapp.common.util.ConverUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 加密与解密
 */
@Mapper(uses = ConverUtil.class)
public interface ContinentVoConver {
    ContinentVoConver INSTANCT = Mappers.getMapper(ContinentVoConver.class);

    @Mapping(target = "useYnNm", source = "useYn", qualifiedByName = "converEnableFlag")
    ContinentVo getContinentVo(Continent vo);


}

package com.cellosquare.adminapp.admin.helpSupport.conver;

import com.cellosquare.adminapp.admin.helpSupport.entity.HelpSupport;
import com.cellosquare.adminapp.admin.helpSupport.vo.HelpSupportExportVo;
import com.cellosquare.adminapp.admin.helpSupport.vo.HelpSupportVo;
import com.cellosquare.adminapp.common.util.ConverUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ConverUtil.class)
public interface HelpSupportVoConver {
    HelpSupportVoConver INSTANCT = Mappers.getMapper(HelpSupportVoConver.class);


    @Mapping(target = "useYnNm", source = "useYn", qualifiedByName = "converEnableFlag")
    HelpSupportVo getHelpSupportVo(HelpSupport helpSupport);


    @Mapping(target = "id", source = "id", qualifiedByName = "converIdToId")
    HelpSupport getHelpSupport(HelpSupportVo helpSupportVo);

    @Mapping(target = "insDtm", source = "insDtm", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updDtm", source = "updDtm", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "useYnNm", source = "useYn", qualifiedByName = "converEnableFlag")
    HelpSupportExportVo getHelpSupportExportVo(HelpSupportVo helpSupport);
}

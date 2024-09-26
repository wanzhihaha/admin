package com.cellosquare.adminapp.admin.registerStatistics.conver;


import com.cellosquare.adminapp.admin.registerStatistics.entity.FromSource;
import com.cellosquare.adminapp.admin.registerStatistics.entity.RegisterAppSuccess;
import com.cellosquare.adminapp.admin.registerStatistics.vo.FromSourceExportVo;
import com.cellosquare.adminapp.admin.registerStatistics.vo.FromSourceVo;
import com.cellosquare.adminapp.admin.registerStatistics.vo.RegisterAppSuccessExportVo;
import com.cellosquare.adminapp.admin.registerStatistics.vo.RegisterAppSuccessVo;
import com.cellosquare.adminapp.common.util.ConverUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(uses = ConverUtil.class)
public interface RegisterAppSuccessConver {
    RegisterAppSuccessConver INSTANCT = Mappers.getMapper(RegisterAppSuccessConver.class);

    @Mapping(target = "createTime", source = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "isPushBaidu", source = "isPushBaidu", qualifiedByName = "booleanEnum")
    @Mapping(target = "isPushGio", source = "isPushGio", qualifiedByName = "booleanEnum")
    RegisterAppSuccessVo getRegisterAppSuccessVo(RegisterAppSuccess vo);

    @Mapping(target = "createTime", source = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
//    @Mapping(target = "isPushBaidu", source = "isPushBaidu", qualifiedByName = "booleanEnum")
//    @Mapping(target = "isPushGio", source = "isPushGio", qualifiedByName = "booleanEnum")
    RegisterAppSuccessExportVo getRegisterAppSuccessExportVo(RegisterAppSuccess vo);

    FromSourceVo getFromSourceVo(FromSource vo);

    @Mapping(target = "id", source = "id", qualifiedByName = "converIdToId")
    FromSource getFromSource(FromSourceVo vo);

    @Mapping(target = "insDtm", source = "insDtm", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updDtm", source = "updDtm", dateFormat = "yyyy-MM-dd HH:mm:ss")
    FromSourceExportVo getFromSourceExportVo(FromSource vo);

    @Mapping(target = "insDtm", source = "insDtm", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updDtm", source = "updDtm", dateFormat = "yyyy-MM-dd HH:mm:ss")
    FromSourceExportVo getFromSourceExportVo(FromSourceVo vo);
}

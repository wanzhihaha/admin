package com.cellosquare.adminapp.admin.registerStatistics.conver;


import com.cellosquare.adminapp.admin.registerStatistics.entity.RegisterButton;
import com.cellosquare.adminapp.admin.registerStatistics.vo.RegisterButtonExportVo;
import com.cellosquare.adminapp.admin.registerStatistics.vo.RegisterButtonVo;
import com.cellosquare.adminapp.common.util.ConverUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 加密与解密
 */
@Mapper(uses = ConverUtil.class)
public interface RegisterButtonVoConver {
    RegisterButtonVoConver INSTANCT = Mappers.getMapper(RegisterButtonVoConver.class);

    @Mapping(target = "createTime", source = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
//    @Mapping(target = "isPushBaidu", source = "isPushBaidu", qualifiedByName = "booleanEnum")
//    @Mapping(target = "isPushGio", source = "isPushGio", qualifiedByName = "booleanEnum")
    RegisterButtonExportVo getRegisterButtonExportVo(RegisterButton vo);

    @Mapping(target = "createTime", source = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "isPushBaidu", source = "isPushBaidu", qualifiedByName = "booleanEnum")
    @Mapping(target = "isPushGio", source = "isPushGio", qualifiedByName = "booleanEnum")
    RegisterButtonVo getRegisterButtonVo(RegisterButton vo);

    List<RegisterButtonVo> getRegisterButtonVos(List<RegisterButton> registerButtons);
}

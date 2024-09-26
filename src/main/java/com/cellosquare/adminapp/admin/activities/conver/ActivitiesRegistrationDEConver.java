package com.cellosquare.adminapp.admin.activities.conver;

import com.cellosquare.adminapp.admin.activities.vo.ActivitiesRegistrationVo;
import com.cellosquare.adminapp.common.util.CounsellingUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 加密与解密
 */
@Mapper(uses = CounsellingUtil.class)
public interface ActivitiesRegistrationDEConver {
    ActivitiesRegistrationDEConver INSTANCT = Mappers.getMapper(ActivitiesRegistrationDEConver.class);

    @Mapping(target = "name", source = "name", qualifiedByName = "encodedAndDcc")
    @Mapping(target = "phone", source = "phone", qualifiedByName = "encodedAndDcc")
    @Mapping(target = "company", source = "company", qualifiedByName = "encodedAndDcc")
    @Mapping(target = "productCategory", source = "productCategory", qualifiedByName = "encodedAndDcc")
    @Mapping(target = "transportType", source = "transportType", qualifiedByName = "encodedAndDcc")
    ActivitiesRegistrationVo getEncodedActivitiesRegistration(ActivitiesRegistrationVo vo);


    @Mapping(target = "name", source = "name", qualifiedByName = "decoded")
    @Mapping(target = "phone", source = "phone", qualifiedByName = "decoded")
    @Mapping(target = "company", source = "company", qualifiedByName = "decoded")
    @Mapping(target = "productCategory", source = "productCategory", qualifiedByName = "decoded")
    @Mapping(target = "transportType", source = "transportType", qualifiedByName = "decoded")
    ActivitiesRegistrationVo getDecodedActivitiesRegistration(ActivitiesRegistrationVo vo);

}

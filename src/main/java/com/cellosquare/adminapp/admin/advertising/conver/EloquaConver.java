package com.cellosquare.adminapp.admin.advertising.conver;

import com.cellosquare.adminapp.admin.counselling.vo.CounsellingInfoVO;
import com.cellosquare.adminapp.common.util.CounsellingUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = CounsellingUtil.class)
public interface EloquaConver {
    EloquaConver INSTANCT = Mappers.getMapper(EloquaConver.class);

    @Mapping(target = "firstName",source = "firstName",qualifiedByName = "encodedAndDcc")
    @Mapping(target = "lastName",source = "lastName",qualifiedByName = "encodedAndDcc")
    @Mapping(target = "company",source = "company",qualifiedByName = "encodedAndDcc")
    @Mapping(target = "emailAddress",source = "emailAddress",qualifiedByName = "encodedAndDcc")
    @Mapping(target = "mobilePhone",source = "mobilePhone",qualifiedByName = "encodedAndDcc")
    @Mapping(target = "comment",source = "comment",qualifiedByName = "encodedAndDcc")
    @Mapping(target = "name",source = "name",qualifiedByName = "encodedAndDcc")
    CounsellingInfoVO getEncodedEloqua(CounsellingInfoVO vo);


    @Mapping(target = "firstName",source = "firstName",qualifiedByName = "decoded")
    @Mapping(target = "lastName",source = "lastName",qualifiedByName = "decoded")
    @Mapping(target = "company",source = "company",qualifiedByName = "decoded")
    @Mapping(target = "emailAddress",source = "emailAddress",qualifiedByName = "decoded")
    @Mapping(target = "mobilePhone",source = "mobilePhone",qualifiedByName = "decoded")
    @Mapping(target = "comment",source = "comment",qualifiedByName = "decoded")
    @Mapping(target = "name",source = "name",qualifiedByName = "decoded")
    CounsellingInfoVO getDecodedEloqua(CounsellingInfoVO vo);

}

package com.cellosquare.adminapp.admin.terminology.conver;

import com.cellosquare.adminapp.admin.advertising.conver.AdvertisingConver;
import com.cellosquare.adminapp.admin.advertising.entity.Advertising;
import com.cellosquare.adminapp.admin.advertising.vo.AdvertisingVO;
import com.cellosquare.adminapp.admin.terminology.entity.Terminology;
import com.cellosquare.adminapp.admin.terminology.vo.TerminologyUploadVO;
import com.cellosquare.adminapp.admin.terminology.vo.TerminologyVO;
import com.cellosquare.adminapp.common.util.ConverUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ConverUtil.class)
public interface TerminologyConver {
    TerminologyConver INSTANCT = Mappers.getMapper(TerminologyConver.class);
    List<TerminologyVO> getAdvertisingVOs(List<Terminology> terminologies);
    @Mapping(target = "insDtm",source = "insDtm",dateFormat="yyyy-MM-dd")
    @Mapping(target = "updDtm",source = "updDtm",dateFormat="yyyy-MM-dd")
    TerminologyVO getAdvertisingVO(Terminology terminologies);

    @Mapping(target = "insDtm",ignore = true)
    @Mapping(target = "updDtm",ignore = true)
    @Mapping(target = "terminologyName",source = "terminologyName",qualifiedByName = "term")
    Terminology getAdvertising(TerminologyVO terminologies);


    List<Terminology> upload(List<TerminologyUploadVO> terminologies);
    List<TerminologyUploadVO> export(List<Terminology> terminologies);
}

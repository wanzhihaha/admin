package com.cellosquare.adminapp.admin.antistop.conver;

import com.cellosquare.adminapp.admin.antistop.entity.Antistop;
import com.cellosquare.adminapp.admin.antistop.vo.AntistopUploadVO;
import com.cellosquare.adminapp.admin.antistop.vo.AntistopVO;
import com.cellosquare.adminapp.admin.terminology.entity.Terminology;
import com.cellosquare.adminapp.admin.terminology.vo.TerminologyUploadVO;
import com.cellosquare.adminapp.admin.terminology.vo.TerminologyVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AntistopConver {
    AntistopConver INSTANCT = Mappers.getMapper(AntistopConver.class);
    List<AntistopVO> getAntistopVOs(List<Antistop> terminologies);
    @Mapping(target = "insDtm",source = "insDtm",dateFormat="yyyy-MM-dd")
    @Mapping(target = "updDtm",source = "updDtm",dateFormat="yyyy-MM-dd")
    AntistopVO getAntistopVO(Antistop antistop);

    @Mapping(target = "insDtm",ignore = true)
    @Mapping(target = "updDtm",ignore = true)
    Antistop getAntistop(AntistopVO antistopVO);

    List<Antistop> upload(List<AntistopUploadVO> terminologies);
}

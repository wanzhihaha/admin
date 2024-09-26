package com.cellosquare.adminapp.admin.counselling.conver;

import com.cellosquare.adminapp.admin.counselling.vo.CounsellingInfoExportVO;
import com.cellosquare.adminapp.admin.counselling.vo.CounsellingInfoVO;
import com.cellosquare.adminapp.common.util.ConverUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ConverUtil.class)
public interface CounsellingInfoExportVoConver {
    CounsellingInfoExportVoConver INSTANCT = Mappers.getMapper(CounsellingInfoExportVoConver.class);

    @Mapping(target = "createDate",source = "createDate",dateFormat="yyyy-MM-dd HH:mm:ss")
    List<CounsellingInfoExportVO> getActivitiesRegistrationExportVos(List<CounsellingInfoVO> counsellingInfoVOS);
}

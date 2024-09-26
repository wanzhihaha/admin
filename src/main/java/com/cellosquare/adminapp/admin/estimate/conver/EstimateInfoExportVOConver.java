package com.cellosquare.adminapp.admin.estimate.conver;

import com.cellosquare.adminapp.admin.estimate.vo.AdminEstimateVO;
import com.cellosquare.adminapp.admin.estimate.vo.EstimateInfoExportVO;
import com.cellosquare.adminapp.common.util.ConverUtil;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ConverUtil.class)
public interface EstimateInfoExportVOConver {
    EstimateInfoExportVOConver INSTANCT = Mappers.getMapper(EstimateInfoExportVOConver.class);


    List<EstimateInfoExportVO> getEstimateInfoExportVOs(List<AdminEstimateVO> counsellingInfoVOS);
}

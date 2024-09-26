package com.cellosquare.adminapp.admin.clickStatistics.conver;


import com.cellosquare.adminapp.admin.clickStatistics.entity.ClickStatistics;
import com.cellosquare.adminapp.admin.clickStatistics.vo.ClickStatisticsExportVo;
import com.cellosquare.adminapp.admin.clickStatistics.vo.ClickStatisticsVo;
import com.cellosquare.adminapp.common.util.ConverUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(uses = ConverUtil.class)
public interface ClickStatisticsVoConver {
    ClickStatisticsVoConver INSTANCT = Mappers.getMapper(ClickStatisticsVoConver.class);

    @Mapping(target = "createTime", source = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    ClickStatisticsExportVo getClickStatisticsExportVo(ClickStatistics vo);

    @Mapping(target = "createTime", source = "createTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    ClickStatisticsVo getClickStatisticsVo(ClickStatistics vo);

    List<ClickStatisticsVo> getClickStatisticsVos(List<ClickStatistics> ClickStatistics);
}

package com.cellosquare.adminapp.admin.activities.conver;

import com.cellosquare.adminapp.admin.activities.entity.ActivitiesRegistration;
import com.cellosquare.adminapp.admin.activities.entity.CorporateActivities;
import com.cellosquare.adminapp.admin.activities.vo.ActivitiesRegistrationExportVo;
import com.cellosquare.adminapp.admin.activities.vo.ActivitiesRegistrationVo;
import com.cellosquare.adminapp.admin.activities.vo.CorporateActivitiesVo;
import com.cellosquare.adminapp.common.util.ConverUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ConverUtil.class)
public interface ActivitiesRegistrationVoConver {
    ActivitiesRegistrationVoConver INSTANCT = Mappers.getMapper(ActivitiesRegistrationVoConver.class);


    ActivitiesRegistrationVo getActivitiesRegistrationVo(ActivitiesRegistration activitiesRegistration);

    @Mapping(target = "createDate",source = "createDate",dateFormat="yyyy-MM-dd HH:mm:ss")
    ActivitiesRegistrationExportVo getActivitiesRegistrationExportVo(ActivitiesRegistrationVo activitiesRegistrationVos);

    List<ActivitiesRegistrationExportVo> getActivitiesRegistrationExportVos(List<ActivitiesRegistrationVo> activitiesRegistrationVos);

    ActivitiesRegistration getActivitiesRegistration(ActivitiesRegistrationVo activitiesRegistrationVo);
}

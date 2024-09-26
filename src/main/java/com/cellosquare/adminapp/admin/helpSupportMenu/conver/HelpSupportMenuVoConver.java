package com.cellosquare.adminapp.admin.helpSupportMenu.conver;

import com.cellosquare.adminapp.admin.activities.conver.CorporateActivitiesVoConver;
import com.cellosquare.adminapp.admin.activities.entity.CorporateActivities;
import com.cellosquare.adminapp.admin.activities.vo.CorporateActivitiesVo;
import com.cellosquare.adminapp.admin.helpSupportMenu.entity.HelpSupportMenu;
import com.cellosquare.adminapp.admin.helpSupportMenu.vo.HelpSupportMenuVo;
import com.cellosquare.adminapp.common.util.ConverUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = ConverUtil.class)
public interface HelpSupportMenuVoConver {
    HelpSupportMenuVoConver INSTANCT = Mappers.getMapper(HelpSupportMenuVoConver.class);

    //    @Mapping(target = "updDtm", source = "updDtm", dateFormat = "yyyy-MM-dd")
    @Mapping(target = "parentId", source = "PId")
    @Mapping(target = "useYnNm", source = "useYn", qualifiedByName = "converEnableFlag")
    HelpSupportMenuVo getHelpSupportMenuVo(HelpSupportMenu helpSupportMenu);

    @Mapping(target = "id", source = "id", qualifiedByName = "converIdToId")
    HelpSupportMenu getHelpSupportMenu(HelpSupportMenuVo helpSupportMenuVo);

    List<HelpSupportMenuVo> getHelpSupportMenuVos(List<HelpSupportMenu> helpSupportMenus);

}

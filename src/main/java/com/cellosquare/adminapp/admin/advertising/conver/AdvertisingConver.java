package com.cellosquare.adminapp.admin.advertising.conver;

import com.cellosquare.adminapp.admin.advertising.entity.Advertising;
import com.cellosquare.adminapp.admin.advertising.vo.AdvertisingVO;
import com.cellosquare.adminapp.admin.article.conver.ArticleConver;
import com.cellosquare.adminapp.common.util.ConverUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ConverUtil.class)
public interface AdvertisingConver {
    AdvertisingConver INSTANCT = Mappers.getMapper(AdvertisingConver.class);

    @Mapping(target = "effectiveTime",source = "effectiveTime",dateFormat="yyyy-MM-dd")
    @Mapping(target = "deadTime",source = "deadTime",dateFormat="yyyy-MM-dd")
    @Mapping(target = "useYn",source = "useYn",qualifiedByName = "converEnableFlag")
    @Mapping(target = "adLocation",source = "adLocation",qualifiedByName = "converAdLocation")
    AdvertisingVO getAdvertisingVO(Advertising advertising);


    @Mapping(target = "effectiveTime",source = "effectiveTime",qualifiedByName = "converEffectiveTime")
    @Mapping(target = "deadTime",source = "deadTime",qualifiedByName = "converEffectiveTime")
    Advertising getAdvertising(AdvertisingVO advertisingVO);
}

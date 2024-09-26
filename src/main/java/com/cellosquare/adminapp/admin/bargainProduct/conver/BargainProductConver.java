package com.cellosquare.adminapp.admin.bargainProduct.conver;

import com.cellosquare.adminapp.admin.antistop.conver.AntistopConver;
import com.cellosquare.adminapp.admin.antistop.entity.Antistop;
import com.cellosquare.adminapp.admin.antistop.vo.AntistopVO;
import com.cellosquare.adminapp.admin.bargainProduct.entity.BargainProduct;
import com.cellosquare.adminapp.admin.bargainProduct.vo.BargainProductVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BargainProductConver {
    BargainProductConver INSTANCT = Mappers.getMapper(BargainProductConver.class);

    @Mapping(target = "insDtm",ignore = true)
    @Mapping(target = "updDtm",ignore = true)
    BargainProduct getBargainProduct(BargainProductVO bargainProductVO);
}

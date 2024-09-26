package com.cellosquare.adminapp.admin.bargain.conver;

import cn.hutool.core.date.DateUtil;
import com.cellosquare.adminapp.admin.bargain.entity.Bargain;
import com.cellosquare.adminapp.admin.bargain.vo.BargainExportVO;
import com.cellosquare.adminapp.admin.bargain.vo.BargainUploadVO;
import com.cellosquare.adminapp.admin.bargain.vo.BargainVO;
import com.cellosquare.adminapp.admin.bargainProduct.entity.BargainProduct;
import com.cellosquare.adminapp.admin.bargainProduct.vo.BargainProductVO;
import com.cellosquare.adminapp.admin.terminology.vo.TerminologyUploadVO;
import com.cellosquare.adminapp.common.util.ConverUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.sql.Timestamp;
import java.util.List;

@Mapper(uses = ConverUtil.class)
public interface BargainConver {
    BargainConver INSTANCT = Mappers.getMapper(BargainConver.class);

    @Mapping(target = "insDtm",ignore = true)
    @Mapping(target = "updDtm",ignore = true)
    @Mapping(target = "effectiveDate",source = "effectiveDate",qualifiedByName = "converEffectiveTime")
    @Mapping(target = "expiryDate",source = "expiryDate",qualifiedByName = "converEffectiveTime")
    Bargain getBargain(BargainVO bargainProductVO);

    @Mapping(target = "insDtm",source = "insDtm",dateFormat="yyyy-MM-dd")
    @Mapping(target = "updDtm",source = "updDtm",dateFormat="yyyy-MM-dd")
    @Mapping(target = "effectiveDate",source = "effectiveDate",dateFormat="yyyy-MM-dd")
    @Mapping(target = "expiryDate",source = "expiryDate",dateFormat="yyyy-MM-dd")
    BargainVO getBargainVO(Bargain bargain);

    List<BargainExportVO> export(List<Bargain> list);
    @Mapping(target = "effectiveDate",source = "effectiveDate",dateFormat="yyyy/MM/dd")
    @Mapping(target = "expiryDate",source = "expiryDate",dateFormat="yyyy/MM/dd")
    BargainExportVO getBargainExportVO(Bargain bargain);


    @Mapping(target = "effectiveDate",source = "effectiveDate",qualifiedByName = "converEffectiveTime2")
    @Mapping(target = "expiryDate",source = "expiryDate",qualifiedByName = "converEffectiveTime2")
    Bargain upload(BargainUploadVO uploads);

}

package com.cellosquare.adminapp.admin.productsMenu.conver;

import com.cellosquare.adminapp.admin.productsMenu.entity.ProductsMenu;
import com.cellosquare.adminapp.admin.productsMenu.vo.ProductsMenuVo;
import com.cellosquare.adminapp.common.util.ConverUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = ConverUtil.class)
public interface ProductsMenuVoConver {
    ProductsMenuVoConver INSTANCT = Mappers.getMapper(ProductsMenuVoConver.class);


    @Mapping(target = "useYnNm", source = "useYn", qualifiedByName = "converEnableFlag")
    ProductsMenuVo getProductsMenuVo(ProductsMenu ProductsMenu);


    @Mapping(target = "id", source = "id", qualifiedByName = "converIdToId")
    ProductsMenu getProductsMenu(ProductsMenuVo productsMenuVo);


}

package com.cellosquare.adminapp.admin.bargainProduct.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cellosquare.adminapp.admin.bargainProduct.entity.BargainProduct;
import com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO;
import com.cellosquare.adminapp.common.vo.GeneralVO;
import lombok.Data;

@Data
public class BargainProductVO extends GeneralVO {
    private Long id;
    /**
     * 产品名称
     */
    private String productName;

    private String multiFlag;

    private String imgKinds;

    private String[] ids;
    private String[] listOrdb;
}

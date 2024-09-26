package com.cellosquare.adminapp.admin.bargain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cellosquare.adminapp.admin.bargainPrice.entity.BargainPrice;
import com.cellosquare.adminapp.common.vo.GeneralVO;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class BargainVO extends GeneralVO {
    /**
     * 主键
     */
    @TableId("id")
    private Long id;

    /**
     * 始发地
     */
    @TableField("origin")
    private String origin;

    /**
     * 目的地
     */
    @TableField("destination")
    private String destination;

    /**
     * 参考时效
     */
    @TableField("aging")
    private String aging;

    /**
     * 备注
     */
    @TableField("remark")
    private String remark;

    /**
     * 是否热卖
     */
    @TableField("hot_flag")
    private String hotFlag;

    /**
     * 币种
     */
    @TableField("currency")
    private String currency;
    /**
     * 埋点标识符
     */
    @TableField("identifier")
    private String identifier;

    /**
     * 产品类型
     */
    @TableField("product_id")
    private Long productId;
    private String productType;
    private List<BargainPrice> bargainPrices;

    private String[] unit;
    private String[] price;

    /**
     * 生效日期
     */
    private String effectiveDate;

    /**
     * 失效日期
     */
    private String expiryDate;

}

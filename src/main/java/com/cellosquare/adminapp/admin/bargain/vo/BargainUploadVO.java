package com.cellosquare.adminapp.admin.bargain.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableField;
import com.cellosquare.adminapp.admin.bargainPrice.entity.BargainPrice;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class BargainUploadVO {
    /**
     * 产品类型
     */
    @ExcelProperty("产品类型")@ColumnWidth(20)
   @NotBlank
    private String productType;

    /**
     * 是否热卖
     */
    @NotBlank
    @ExcelProperty("是否热卖")@ColumnWidth(20)
    private String hotFlag;

    /**
     * 始发地
     */
    @NotBlank
    @ExcelProperty("始发地")@ColumnWidth(20)
    private String origin;

    /**
     * 目的地
     */
    @NotBlank
    @ExcelProperty("目的地")@ColumnWidth(20)
    private String destination;

    /**
     * 参考时效
     */
    @NotBlank
    @ExcelProperty("参考时效")@ColumnWidth(20)
    private String aging;

    /**
     * 备注
     */
    @ExcelProperty("备注")@ColumnWidth(20)
    private String remark;

    /**
     * 币种
     */
    @NotBlank
    @ExcelProperty("币种")
    @ColumnWidth(20)
    private String currency;

    @ExcelProperty("单位1")
    @ColumnWidth(20)
    @NotBlank
    private String unit1;

    @ExcelProperty("价格1")
    @ColumnWidth(20)
    @NotBlank
    private String price1;

    @ExcelProperty("单位2")
    @ColumnWidth(20)
    private String unit2;

    @ExcelProperty("价格2")
    @ColumnWidth(20)
    private String price2;

    @ExcelProperty("单位3")
    @ColumnWidth(20)
    private String unit3;

    @ExcelProperty("价格3")
    @ColumnWidth(20)
    private String price3;

    /**
     * 生效日期
     */
    @ExcelProperty("生效日期格式：2023/06/14")
    @ColumnWidth(30)
    @NotBlank
    private String effectiveDate;

    /**
     * 失效日期
     */
    @ExcelProperty("失效日期：2023/06/14")
    @ColumnWidth(30)
    @NotBlank
    private String expiryDate;

    @ExcelIgnore
    private String metaSeqNo;

    @ExcelIgnore
    private Long productId;

    @ExcelIgnore
    private String multiFlag;
}

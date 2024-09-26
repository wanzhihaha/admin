package com.cellosquare.adminapp.admin.bargain.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class BargainExportVO {
    @ExcelProperty("产品类型")@ColumnWidth(20)
    private String productType;

    /**
     * 是否热卖
     */
    @ExcelProperty("是否热卖")@ColumnWidth(20)
    private String hotFlag;

    /**
     * 始发地
     */
    @ExcelProperty("始发地")@ColumnWidth(20)
    private String origin;

    /**
     * 目的地
     */
    @ExcelProperty("目的地")@ColumnWidth(20)
    private String destination;

    /**
     * 参考时效
     */
    @ExcelProperty("参考时效")@ColumnWidth(20)
    private String aging;

    /**
     * 备注
     */
    @ExcelProperty("备注")@ColumnWidth(20)
    private String remark;


    @ExcelProperty("价格")
    @ColumnWidth(60)
    private String currency;

    /**
     * 生效日期
     */
    @ExcelProperty("生效日期")
    @ColumnWidth(30)
    private String effectiveDate;

    /**
     * 失效日期
     */
    @ExcelProperty("失效日期")
    @ColumnWidth(30)
    private String expiryDate;

    @ExcelIgnore
    private Long productId;

    @ExcelIgnore
    private Long id;
}

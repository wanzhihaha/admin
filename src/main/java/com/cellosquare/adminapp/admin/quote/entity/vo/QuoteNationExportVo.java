package com.cellosquare.adminapp.admin.quote.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author hugo
 * @since 2023-03-68 14:14:19
 */
@Data
@ColumnWidth(15)
public class QuoteNationExportVo implements Serializable {
    @ExcelProperty(value = {"州/大陆"})
    private String continentNm;
    @ExcelProperty(value = {"国家（英文）"})
    private String nationNm;
    @ExcelProperty(value = {"国家（中文）"})
    private String nationCnNm;
    //    @ExcelProperty(value = {"创建人"})
//    private String insPersonNm;
//    @ExcelProperty(value = {"创建时间"})
//    private String insDtm;
//    @ExcelProperty(value = {"更新人"})
//    private String updPersonNm;
//    @ExcelProperty(value = {"更新时间"})
//    private String updDtm;
    @ExcelProperty(value = {"快递"})
    private String expressUseValue;
    @ExcelProperty(value = {"状态"})
    private String useYnNm;
    @ExcelProperty(value = {"是否热门"})
    private String isHotNm;
}

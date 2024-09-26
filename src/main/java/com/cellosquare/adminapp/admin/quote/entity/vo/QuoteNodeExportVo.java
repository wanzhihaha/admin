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
@ColumnWidth(18)
public class QuoteNodeExportVo implements Serializable {
    @ExcelProperty(value = {"节点cd"})
    private String nodeCd;
    @ExcelProperty(value = {"类型"})
    private String productModeNm;
    @ExcelProperty(value = {"节点英文"})
    private String nodeEngNm;
    @ExcelProperty(value = {"节点中文"})
    private String nodeCnNm;
    @ExcelProperty(value = {"所属国家"})
    private String nationCnNm;
//    @ExcelProperty(value = {"所属城市"})
//    private String cityCnNm;
    @ExcelProperty(value = {"状态"})
    private String useYnNm;
    @ExcelProperty(value = {"创建人"})
    private String insPersonNm;
    @ExcelProperty(value = {"创建时间"})
    private String insDtm;
    @ExcelProperty(value = {"更新人"})
    private String updPersonNm;
    @ExcelProperty(value = {"更新时间"})
    private String updDtm;
}

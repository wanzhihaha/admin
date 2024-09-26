package com.cellosquare.adminapp.admin.activities.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

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
public class ActivitiesRegistrationExportVo implements Serializable {
    @ExcelProperty(value = {"活动类型"})
    private String activitiesType;
    @ExcelProperty(value = {"活动名称"})
    private String activitiesName;
    @ExcelProperty(value = {"联系称呼"})
    private String name;
    @ExcelProperty(value = {"联系电话"})
    private String phone;
    @ExcelProperty(value = {"公司名称"})
    private String company;
    @ExcelProperty(value = {"产品品类"})
    private String productCategory;
    @ExcelProperty(value = {"运输方式"})
    private String transportType;
    @ColumnWidth(20)
    @ExcelProperty(value = {"报名时间"})
    private String createDate;
}

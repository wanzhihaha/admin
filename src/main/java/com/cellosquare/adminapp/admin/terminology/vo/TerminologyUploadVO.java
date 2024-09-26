package com.cellosquare.adminapp.admin.terminology.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class TerminologyUploadVO {
    @ExcelProperty("术语标题")
    private String terminologyName;
    @ExcelProperty("术语内容")
    private String description;
    @ExcelIgnore
    private String metaSeqNo;
}

package com.cellosquare.adminapp.admin.antistop.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class AntistopUploadVO {
    @ExcelProperty("关键词标题")
    private String terminologyName;
    @ExcelProperty("关键词内容")
    private String description;
    // 시퀀스
    @ExcelIgnore
    private String metaSeqNo;
}

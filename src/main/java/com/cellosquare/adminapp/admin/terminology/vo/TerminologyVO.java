package com.cellosquare.adminapp.admin.terminology.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cellosquare.adminapp.admin.terminology.entity.Terminology;
import com.cellosquare.adminapp.common.vo.GeneralVO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TerminologyVO extends GeneralVO {
    private Long id;
    @TableField("terminology_name")
    private String terminologyName;

    @TableField("description")
    private String description;

    @TableField("use_yn")
    private String useYn;

    @TableField("srch_cnt")
    private Integer srchCnt;

    @TableField("ordb")
    private String ordb;

    @TableField("ins_person_id")
    private String insPersonId;
    @TableField(value = "show_flag")
    private String showFlag;
    @TableField("upd_person_id")
    private String updPersonId;
    private String[] listSortOrder;
    private String[] listblogSeq;

    @TableField(exist = false)
    private List<String> terminologyTagList;
}

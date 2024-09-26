package com.cellosquare.adminapp.admin.antistop.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.cellosquare.adminapp.common.vo.GeneralVO;
import lombok.Data;

import java.util.List;

@Data
public class AntistopVO extends GeneralVO {
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

    @TableField("upd_person_id")
    private String updPersonId;
    private String[] listSortOrder;
    private String[] listblogSeq;
    @TableField(exist = false)
    private List<String> terminologyTagList;
}

package com.cellosquare.adminapp.admin.quote.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author hugo
 * @since 2023-06-157 09:11:30
 */
@Getter
@Setter
@TableName("mk_quote_route")
public class QuoteRoute implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "route_seq_no",type = IdType.AUTO)
    private Long routeSeqNo;

    @TableField("nation_cd")
    private String nationCd;

    @TableField("product_mode")
    private String productMode;

    @TableField("product_id")
    private String productId;

    @TableField("product_nm")
    private String productNm;

    @TableField("from_node")
    private String fromNode;

    @TableField("to_node")
    private String toNode;

    @TableField("use_yn")
    private String useYn;

    @TableField("route_source")
    private String routeSource;

    @TableField("new_flag")
    private String newFlag;
    @TableField("from_node_id")
    private Long fromNodeId;
    @TableField("to_node_id")
    private Long toNodeId;

    @TableField("ins_dtm")
    private Timestamp insDtm;

    @TableField("ins_person_id")
    private String insPersonId;

    @TableField("upd_dtm")
    private Timestamp updDtm;

    @TableField("upd_person_id")
    private String updPersonId;
}

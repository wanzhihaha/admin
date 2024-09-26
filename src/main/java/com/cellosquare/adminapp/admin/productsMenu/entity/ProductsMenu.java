package com.cellosquare.adminapp.admin.productsMenu.entity;

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
 * @author walker
 * @since 2023-10-286 16:07:23
 */
@Getter
@Setter
@TableName("mk_products_menu")
public class ProductsMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField(value = "product_ctgry")
    private String productCtgry;

    @TableField("name")
    private String name;

    @TableField("use_yn")
    private String useYn;

    @TableField("ordb")
    private Integer ordb;

    @TableField("ins_dtm")
    private Timestamp insDtm;

    @TableField("ins_person_id")
    private String insPersonId;

    @TableField("upd_dtm")
    private Timestamp updDtm;

    @TableField("upd_person_id")
    private String updPersonId;

    @TableField("contents")
    private String contents;

    @TableField("enable_flag")
    private String enableFlag;


}

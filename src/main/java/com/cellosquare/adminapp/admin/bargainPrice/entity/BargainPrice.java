package com.cellosquare.adminapp.admin.bargainPrice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author walker
 * @since 2023-08-234 08:56:25
 */
@Getter
@Setter
@TableName("mk_bargain_price")
@AllArgsConstructor
@NoArgsConstructor
public class BargainPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id" ,type = IdType.AUTO)
    private Long id;

    /**
     * 单位
     */
    @TableField("unit")
    private String unit;

    /**
     * 价格
     */
    @TableField("price")
    private String price;

    /**
     * 价格
     */
    @TableField("parent_id")
    private Long parentId;

}

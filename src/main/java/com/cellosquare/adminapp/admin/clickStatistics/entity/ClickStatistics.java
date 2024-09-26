package com.cellosquare.adminapp.admin.clickStatistics.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
 * @since 2024-03-71 09:13:54
 */
@Getter
@Setter
@TableName("mk_click_statistics")
public class ClickStatistics implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("ip")
    private String ip;

    /**
     * 1、chatbot点击
     */
    @TableField("source")
    private String source;

    @TableField("url")
    private String url;

    @TableField("create_time")
    private Timestamp createTime;


}

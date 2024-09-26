package com.cellosquare.adminapp.admin.quote.entity;

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
 * @author hugo
 * @since 2023-06-157 09:34:50
 */
@Getter
@Setter
@TableName("mk_quote_air_port")
public class QuoteAirPort implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("node_cd")
    private String nodeCd;

    @TableField("nation_cd")
    private String nationCd;

    @TableField("air_port_city_nm")
    private String airPortCityNm;

    @TableField("air_port_nm")
    private String airPortNm;

    @TableField("upd_dtm")
    private Timestamp updDtm;


}

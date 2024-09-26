package com.cellosquare.adminapp.admin.quote.entity;

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
 * @author hugo
 * @since 2023-06-157 09:11:30
 */
@Getter
@Setter
@TableName("mk_route_api_history")
public class RouteApiHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    @TableField("result_ord")
    private Integer resultOrd;

    @TableField("api_url")
    private String apiUrl;

    @TableField("api_request_body")
    private String apiRequestBody;

    @TableField("api_response_body")
    private String apiResponseBody;

    @TableField("send_date")
    private Timestamp sendDate;

    @TableField("received_date")
    private Timestamp receivedDate;

    @TableField("api_status")
    private String apiStatus;


}

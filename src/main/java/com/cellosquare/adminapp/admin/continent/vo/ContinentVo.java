package com.cellosquare.adminapp.admin.continent.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 * 
 * </p>
 *
 * @author walker
 * @since 2023-11-314 14:07:21
 */
@Data
public class ContinentVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String useYn;
    private Integer fclOrdb;
    private Integer lclOrdb;
    private Integer airOrdb;
    private Integer expressOrdb;
    private Timestamp updDtm;
    private String updPersonId;
    private String remark;
    private String cd;
    //修改人
    private String updPersonNm;
    //状态
    private String useYnNm;
    private String page = "1"; //초기값 설정
    private String rowPerPage = "20"; //초기값 설정

    private Integer[] listFclOrdb;
    private Integer[] listLclOrdb;
    private Integer[] listAirOrdb;
    private Integer[] listExpressOrdb;
    private String[] listId;
}

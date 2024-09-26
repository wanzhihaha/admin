package com.cellosquare.adminapp.admin.helpSupportMenu.vo;

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
 * @author hugo
 * @since 2023-03-65 09:48:42
 */
@Data
public class HelpSupportMenuVo implements Serializable {


    private String id;

    private String parentId;
    private String pId;

    private String menuName;
    private String parentMenuName;

    private Integer isChild;

    private String description;

    private String useYn;
    private String useYnNm;

    private Integer ordb;

    private Timestamp insDtm;

    private String insPersonId;

    private Timestamp updDtm;

    private String updPersonId;
    //开始时间
    private String startDate;
    //结束时间
    private String endDate;
    private String page = "1"; //초기값 설정
    private String rowPerPage = "20"; //초기값 설정
    //新增人
    private String insPersonNm;
    //修改人
    private String updPersonNm;
    private Integer[] listOrdb;
    private String[] listId;
    private String searchValue;
}

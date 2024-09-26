package com.cellosquare.adminapp.admin.productsMenu.vo;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 *
 * </p>
 *
 * @author hugo
 * @since 2023-03-65 09:44:59
 */
@Data
public class ProductsMenuVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String productCtgry;

    private String name;

    private String useYn;

    private Integer ordb;

    private Timestamp insDtm;

    private String insPersonId;

    private Timestamp updDtm;

    private String updPersonId;

    private String contents;

    //新增人
    private String insPersonNm;
    //修改人
    private String updPersonNm;
    //状态
    private String useYnNm;
    private String page = "1"; //초기값 설정
    private String rowPerPage = "20"; //초기값 설정

    //是否选中
    private Integer[] listOrdb;
    private String[] listId;
    private String searchValue;
}

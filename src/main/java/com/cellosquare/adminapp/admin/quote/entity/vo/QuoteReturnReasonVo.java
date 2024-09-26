package com.cellosquare.adminapp.admin.quote.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author hugo
 * @since 2023-06-157 09:11:30
 */
@Data
public class QuoteReturnReasonVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private Integer type;
    private String typeNm;
    private Integer ordb;

    private String reason;

    private String insDtm;

    private String insPersonId;

    private String updDtm;

    private String updPersonId;

    private String useYnNm;
    private String useYn;
    private String updPersonNm;
    private String insPersonNm;
    /**
     * 페이지 번호
     */
    private String page = "1"; //초기값 설정

    /**
     * 한 페이지에 표시될 레코드 수
     */
    private String rowPerPage = "20"; //초기값 설정

    private Integer[] listOrdb;
    private String[] listId;
}

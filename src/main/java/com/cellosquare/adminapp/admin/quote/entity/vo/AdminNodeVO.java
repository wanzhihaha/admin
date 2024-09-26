package com.cellosquare.adminapp.admin.quote.entity.vo;

import com.cellosquare.adminapp.common.vo.GeneralVO;
import lombok.Data;

import java.util.List;


@Data
public class AdminNodeVO extends GeneralVO {

    private String id;
    private String continentCd;
    private String continentName;
    private String nodeCd;
    private String updateNodeCd;
    private String nationSeqNo;
    private String nodeEngNm;
    private String searchKeyWord;
    private String nodeCnNm;
    private String useYnNm;
    private String cityEngNm;
    private String cityCnNm;
    private String productMode;
    private String productModeNm;
    private Integer isHot;
    private String ordb;
    /**
     * enum[N:New, C:Confirm], value maintained in mk_ cd_ mng
     */
    private String nodeStatus;
    private String nodeStatusVal;

    /**
     * enum[A: api Auto import, E: Admin enter], value maintained in mk_ cd_ mng
     */
    private String nodeSource;

    private String nodeSourceVal;
    private String nationNm;
    private String nationCnNm;
    private String nodeCdArrayStr;
    private List<String> nodeCdList;
    private String[] listSearchKeyWord;
    private List<Long> nationIds;
    private String[] listIds;
    private String[] listSortOrder;
}

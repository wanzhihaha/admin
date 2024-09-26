package com.cellosquare.adminapp.admin.quote.entity.vo;

import com.cellosquare.adminapp.common.vo.GeneralVO;
import lombok.Data;

@Data
public class AdminRouteVO extends GeneralVO {

    private String routeSeqNo;
    private String listRouteSeqNo;
    private String nationCd;
    private String productId;
    private String productMode;
    private String productModeNm;
    private String productNm;
    private String fromNode;
    private String toNode;
    private String useYn;
    private String newFlag;
    private String useYnNm;
    private String fromNodeId;
    private String toNodeId;

    /**
     * enum[A: api Auto import, E: Admin enter], value maintained in mk_ cd_ mng
     */
    private String routeSource;

    private String routeSourceVal;

}

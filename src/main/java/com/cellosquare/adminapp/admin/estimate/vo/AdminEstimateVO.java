package com.cellosquare.adminapp.admin.estimate.vo;

import com.cellosquare.adminapp.common.vo.GeneralVO;
import lombok.Data;

/**
 * 20220617 update
 * AdminEstimateVO
 *
 * @author juru.jia
 * @Date : 2022/06/17
 */
@Data
public class AdminEstimateVO extends GeneralVO {

    //조회날짜
    private String sendDate;

    //일련번호
    private String sqNo;

    //구분
    private String svcMedCtgryCd;

    //컨테이너 타입
    private String svcClassCd;

    //출발지
    private String deppNm;

    //도착지
    private String arrpNm;

    //화물정보
    private String itemInfo;

    //서비스
    private String carrierName;

    //조회 운임
    private String quotePrice;

    private String quoteCurrency;

    private String quoteFare;

    private Integer quoteCnt;

    //이메일 전송여부
    private String emailYn;

    //조회결과
    private String searchRecordYn;

    //일련번호
    private String recordNo;

    private String statDate;

    private String endDate;

    private String svcMedCtgryCode;

    private String langCd;
    //原因
    private String returnReason;

    private String address;
    private String accessIp;
    private String productType;
}

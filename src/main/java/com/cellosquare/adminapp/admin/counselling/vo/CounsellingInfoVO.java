package com.cellosquare.adminapp.admin.counselling.vo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@TableName("mk_user_info")
@Data
public class CounsellingInfoVO {

	private String id;

	private String type;
	// 시퀀스
	private String seqNo;
	// 이름
	private String firstName;
	// 성
	private String lastName;
	// 회사
	private String company;
	// 이메일
	private String emailAddress;
	// 핸드폰
	private String mobilePhone;
	// 제목
	private String title;
	// 부서명
	private String hqDepartment;
	// 솔루션 or 시스템(소분류)
	private String solutionOfInterest;
	// 문의사항 내용
	private String comment;
	// 문의하기 - 구분값(회원가입,사이트이용,상품문의,제휴문의,기타)
	private String category;
	// 견적문의 - 운송모드
	private String transMode;

	// 견적문의 - 운송구간 From
	private String transDeptPoint;

	// 견적문의 - 운송구간 To
	private String transDest;

	// 견적문의 - 예상 선적 일시
	private String estimatedShippingDate;

	// 견적문의 - 물동 사이즈 및 수량
	private String volAndQty;

	// 견적문의 - 상품 URL
	private String productUrl;

	// SDS 솔루션 수신 동의(선택)
	private String hq3rdOptIn;
	// SDS 해외법인 정보 공유 동의(선택), 개인정보 제3자 제공 동의
	private String hqEmailOptIn;
	// uniqueCode
	private String uniqueCode;
	@TableField(exist = false)
	private String dataNo;
	@TableField(exist = false)
	private String createDateStr;
	@TableField(exist = false)
	private String startDate;
	@TableField(exist = false)
	private String endDate;
	@TableField(exist = false)
	private String successYn;
	@TableField(exist = false)
	private String operationUser;
	private String source;
	private String name;
	/**
	 * 페이지 번호
	 */
	@TableField(exist = false)
	private String page = "1"; //초기값 설정

	/**
	 * 한 페이지에 표시될 레코드 수
	 */
	@TableField(exist = false)
	private String rowPerPage = "20"; //초기값 설정

	private Timestamp createDate;
	//预计月物流费
	private String estimatedMonthlyLogisticsCost;
}

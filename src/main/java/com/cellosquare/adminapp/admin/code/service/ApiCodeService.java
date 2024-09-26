package com.cellosquare.adminapp.admin.code.service;

import java.util.List;

import com.cellosquare.adminapp.admin.code.vo.ApiCodeVO;

public interface ApiCodeService {
	public List<ApiCodeVO> getApiCodeList(ApiCodeVO vo);
	
	// 코드중 첫번째것
	public ApiCodeVO getApiCodeFirst(ApiCodeVO vo);

	// 상품 코드 이름가져오기
	public String getCategoryNm(ApiCodeVO vo);

    List<ApiCodeVO> getApiCodeByGrpCd(String quoteCategoryGrp);
}

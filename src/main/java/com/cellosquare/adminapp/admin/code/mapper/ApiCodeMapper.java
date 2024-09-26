package com.cellosquare.adminapp.admin.code.mapper;

import java.util.List;

import com.cellosquare.adminapp.admin.code.vo.ApiCodeVO;

public interface ApiCodeMapper {

	public List<ApiCodeVO> getApiCodeList(ApiCodeVO vo);
	
	public ApiCodeVO getApiCodeFirst(ApiCodeVO vo);

	public String getCategoryNm(ApiCodeVO vo);

    List<ApiCodeVO> getApiCodeByGrpCd(String grpCd);

	void setCodeByGrpCd(ApiCodeVO apiCodeFirst);
}

package com.cellosquare.adminapp.admin.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cellosquare.adminapp.admin.code.mapper.ApiCodeMapper;
import com.cellosquare.adminapp.admin.code.service.ApiCodeService;
import com.cellosquare.adminapp.admin.code.vo.ApiCodeVO;

@Service
public class ApiCodeServiceImpl implements ApiCodeService {
	
	@Autowired
	private ApiCodeMapper apiCodeMapper;
	
	@Override
	public List<ApiCodeVO> getApiCodeList(ApiCodeVO vo) {
		List<ApiCodeVO> list = apiCodeMapper.getApiCodeList(vo);
		return list;
	}

	@Override
	public ApiCodeVO getApiCodeFirst(ApiCodeVO vo) {
		// TODO Auto-generated method stub
		return apiCodeMapper.getApiCodeFirst(vo);
	}

	@Override
	public String getCategoryNm(ApiCodeVO vo) {
		return apiCodeMapper.getCategoryNm(vo);
	}

	@Override
	public List<ApiCodeVO> getApiCodeByGrpCd(String grpCd) {
		return apiCodeMapper.getApiCodeByGrpCd(grpCd);
	}

	public void setCodeByGrpCd(ApiCodeVO apiCodeFirst) {
		apiCodeMapper.setCodeByGrpCd(apiCodeFirst);
	}
}

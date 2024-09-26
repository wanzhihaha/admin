package com.cellosquare.adminapp.admin.termsofservice.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cellosquare.adminapp.admin.termsofservice.mapper.AdminTermsOfServiceMapper;
import com.cellosquare.adminapp.admin.termsofservice.service.AdminTermsOfServiceService;
import com.cellosquare.adminapp.admin.termsofservice.vo.AdminTermsOfServiceVO;

@Service
public class AdminTermsOfServiceServiceImpl implements AdminTermsOfServiceService{

	@Autowired
	private AdminTermsOfServiceMapper adminTermsOfServiceMapper;

	/**
	 * TOS Management 리스트 토탈 카운트
	 * @param vo
	 * @return
	 */
	@Override
	public int getTotalCount(AdminTermsOfServiceVO vo) {
		return adminTermsOfServiceMapper.getTotalCount(vo);
	}

	/**
	 * TOS Management 리스트 조회
	 * @param vo
	 * @return
	 */
	@Override
	public List<AdminTermsOfServiceVO> getList(AdminTermsOfServiceVO vo) {
		return adminTermsOfServiceMapper.getList(vo);
	}

	/**
	 * TOS Management 시퀀스로 조회
	 * @param vo
	 * @return
	 */
	@Override
	public AdminTermsOfServiceVO getDetail(AdminTermsOfServiceVO vo) {
		return adminTermsOfServiceMapper.getDetail(vo);
	}

	/**
	 * TOS Management 등록
	 * @param vo
	 * @return
	 */
	@Override
	public int doWrite(AdminTermsOfServiceVO vo) throws Exception {
		return adminTermsOfServiceMapper.doWrite(vo);
	}
		
	
	/**
	 * TOS Management 수정
	 * @param vo
	 * @return
	 */
	@Override
	public int doUpdate(AdminTermsOfServiceVO vo) throws Exception {
		return adminTermsOfServiceMapper.doUpdate(vo);
	}

	/**
	 * TOS Management 삭제
	 * @param vo
	 * @return
	 */
	@Override
	public int doDelete(AdminTermsOfServiceVO vo) throws Exception {
		return adminTermsOfServiceMapper.doDelete(vo);
	}
}

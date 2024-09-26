package com.cellosquare.adminapp.admin.termsofservice.mapper;

import java.util.List;

import com.cellosquare.adminapp.admin.termsofservice.vo.AdminTermsOfServiceVO;

public interface AdminTermsOfServiceMapper {

	/**
	 * TOS Management 리스트 토탈 카운트
	 * @param vo
	 */
	public int getTotalCount(AdminTermsOfServiceVO vo);
	
	/**
	 * TOS Management 리스트 조회
	 * @param vo
	 */
	public List<AdminTermsOfServiceVO> getList(AdminTermsOfServiceVO vo);
	
	/**
	 * TOS Management 시퀀스로 조회
	 * @param vo
	 * @return 
	 */
	public AdminTermsOfServiceVO getDetail(AdminTermsOfServiceVO vo);
	
	/**
	 * TOS Management 등록
	 * @param vo
	 */
	public int doWrite(AdminTermsOfServiceVO vo) throws Exception;
	
	/**
	 * TOS Management 수정
	 * @param vo
	 */
	public int doUpdate(AdminTermsOfServiceVO vo) throws Exception;
	
	/**
	 * TOS Management 삭제
	 * @param vo
	 */
	public int doDelete(AdminTermsOfServiceVO vo) throws Exception;
	
}

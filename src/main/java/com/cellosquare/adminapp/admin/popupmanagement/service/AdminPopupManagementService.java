package com.cellosquare.adminapp.admin.popupmanagement.service;

import java.util.List;

import com.cellosquare.adminapp.admin.popupmanagement.vo.AdminPopupManagementVO;

public interface AdminPopupManagementService {

	/**
	 * Popup 리스트 토탈 카운트
	 * @param vo
	 */
	public int getTotalCount(AdminPopupManagementVO vo);
	
	/**
	 * Popup 리스트 조회
	 * @param vo
	 */
	public List<AdminPopupManagementVO> getList(AdminPopupManagementVO vo);
	
	/**
	 * Popup 시퀀스로 조회
	 * @param vo
	 */
	public AdminPopupManagementVO getDetail(AdminPopupManagementVO vo);
	
	/**
	 * Popup 등록
	 * @param vo
	 */
	public int doWrite(AdminPopupManagementVO vo) throws Exception;
	
	/**
	 * Popup 수정
	 * @param vo
	 */
	public int doUpdate(AdminPopupManagementVO vo) throws Exception;
	
	/**
	 * Popup 삭제
	 * @param vo
	 */
	public int doDelete(AdminPopupManagementVO vo) throws Exception;
}

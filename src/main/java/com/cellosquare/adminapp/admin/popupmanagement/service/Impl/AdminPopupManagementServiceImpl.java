package com.cellosquare.adminapp.admin.popupmanagement.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cellosquare.adminapp.admin.popupmanagement.mapper.AdminPopupManagementMapper;
import com.cellosquare.adminapp.admin.popupmanagement.service.AdminPopupManagementService;
import com.cellosquare.adminapp.admin.popupmanagement.vo.AdminPopupManagementVO;

@Service
public class AdminPopupManagementServiceImpl implements AdminPopupManagementService{

	@Autowired
	private AdminPopupManagementMapper adminPopupManagementMapper;
	
	/**
	 * Popup 리스트 토탈 카운트
	 * @param vo
	 * @return
	 */
	@Override
	public int getTotalCount(AdminPopupManagementVO vo) {
		return adminPopupManagementMapper.getTotalCount(vo);
	}

	/**
	 * Popup 리스트 조회
	 * @param vo
	 * @return
	 */
	@Override
	public List<AdminPopupManagementVO> getList(AdminPopupManagementVO vo) {
		return adminPopupManagementMapper.getList(vo);
	}

	/**
	 * Popup 시퀀스로 조회
	 * @param vo
	 * @return
	 */
	@Override
	public AdminPopupManagementVO getDetail(AdminPopupManagementVO vo) {
		return adminPopupManagementMapper.getDetail(vo);
	}

	/**
	 * Popup 등록
	 * @param vo
	 * @return
	 */
	@Override
	public int doWrite(AdminPopupManagementVO vo) throws Exception {
		return adminPopupManagementMapper.doWrite(vo);
	}
		
	
	/**
	 * Popup 수정
	 * @param vo
	 * @return
	 */
	@Override
	public int doUpdate(AdminPopupManagementVO vo) throws Exception {
		return adminPopupManagementMapper.doUpdate(vo);
	}

	/**
	 * Popup 삭제
	 * @param vo
	 * @return
	 */
	@Override
	public int doDelete(AdminPopupManagementVO vo) throws Exception {
		return adminPopupManagementMapper.doDelete(vo);
	}	
}

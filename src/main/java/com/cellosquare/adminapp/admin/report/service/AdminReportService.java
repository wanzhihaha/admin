package com.cellosquare.adminapp.admin.report.service;

import java.util.List;

import com.cellosquare.adminapp.admin.report.vo.AdminReportVO;


public interface AdminReportService {

	/**
	 * 리포트 리스트 토탈 카운트
	 * @param vo
	 */
	public int getTotalCount(AdminReportVO vo);
	
	/**
	 * 리포트 리스트 조회
	 * @param vo
	 */
	public List<AdminReportVO> getList(AdminReportVO vo);
	
	/**
	 * 리포트 시퀀스로 조회
	 * @param vo
	 */
	public AdminReportVO getDetail(AdminReportVO vo);
	
	/**
	 * 리포트 등록
	 * @param vo
	 */
	public int doWrite(AdminReportVO vo) throws Exception;
	
	/**
	 * 리포트 수정
	 * @param vo
	 */
	public int doUpdate(AdminReportVO vo) throws Exception;
	
	/**
	 * 리포트 삭제
	 * @param vo
	 */
	public int doDelete(AdminReportVO vo) throws Exception;
	
	/**
	 * 정렬순서 저장
	 * @param vo
	 */
	public int doSortOrder(AdminReportVO vo) throws Exception;
}

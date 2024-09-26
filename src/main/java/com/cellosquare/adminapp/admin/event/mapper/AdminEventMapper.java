package com.cellosquare.adminapp.admin.event.mapper;

import java.util.List;

import com.cellosquare.adminapp.admin.event.vo.AdminEventVO;

public interface AdminEventMapper {

	/**
	 * 이벤트 리스트 토탈 카운트
	 * @param vo
	 * @return
	 */
	public int getTotalCount(AdminEventVO vo);
	
	/**
	 * 이벤트 리스트 조회
	 * @param vo
	 * @return
	 */
	public List<AdminEventVO> getList(AdminEventVO vo);
	
	/**
	 * 이벤트 시퀀스로 조회
	 * @param vo
	 * @return
	 */
	public AdminEventVO getImg(AdminEventVO vo);

	/**
	 * 이벤트 등록
	 * @param vo
	 * @return 
	 */
	public int regist(AdminEventVO vo);

	/**
	 * 이벤트 수정
	 * @param vo
	 * @return 
	 */
	public int update(AdminEventVO vo);

	/**
	 * 이벤트 삭제
	 * @param vo
	 * @return 
	 */
	public int delete(AdminEventVO vo);

	/**
	 * 정렬순서 저장
	 * @param vo
	 * @return 
	 */
	public int doSortOrder(AdminEventVO vo);
	
}

package com.cellosquare.adminapp.admin.video.mapper;

import java.util.List;

import com.cellosquare.adminapp.admin.video.vo.AdminVideoVO;

public interface AdminVideoMapper {
	
	/**
	 * 리스트 토탈 카운트
	 * @param vo
	 * @return
	 */
	public int getTotalCount(AdminVideoVO vo);

	/**
	 * 비디오 리스트 조회
	 * @param vo
	 * @return
	 */
	public List<AdminVideoVO> getList(AdminVideoVO vo);

	/**
	 * 비디오 시퀀스로 조회
	 * @param vo
	 * @return
	 */
	public AdminVideoVO getDetail(AdminVideoVO vo);

	/**
	 * 비디오 등록
	 * @param vo
	 * @return
	 */
	public int regist(AdminVideoVO vo);

	/**
	 * 정렬순서 저장
	 * @param vo
	 * @return
	 */
	public int doSortOrder(AdminVideoVO vo);

	/**
	 * 비디오 수정
	 * @param vo
	 * @return
	 */
	public int update(AdminVideoVO vo);

	/**
	 * 비디오 삭제
	 * @param vo
	 * @return
	 */
	public int delete(AdminVideoVO vo);

	
}

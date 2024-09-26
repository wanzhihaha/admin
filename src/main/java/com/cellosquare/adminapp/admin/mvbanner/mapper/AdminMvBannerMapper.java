package com.cellosquare.adminapp.admin.mvbanner.mapper;

import java.util.List;

import com.cellosquare.adminapp.admin.mvbanner.vo.AdminMvBannerVO;


public interface AdminMvBannerMapper {

	/**
	 * 배너 리스트 토탈 카운트
	 * @param vo
	 * @return
	 */
	public int getTotalCount(AdminMvBannerVO vo);
	
	/**
	 * 배너 리스트 조회
	 * @param vo
	 * @return
	 */
	public List<AdminMvBannerVO> getList(AdminMvBannerVO vo);

	/**
	 * 배너 시퀀스로 조회
	 * @param vo
	 * @return
	 */
	public AdminMvBannerVO getDetail(AdminMvBannerVO vo);

	/**
	 * 배너 등록
	 * @param vo
	 * @return
	 */
	public int regist(AdminMvBannerVO vo);
	
	/**
	 * 정렬순서 저장
	 * @param vo
	 * @return 
	 */
	public int doSortOrder(AdminMvBannerVO vo);

	/**
	 * 배너 수정
	 * @param vo
	 * @return
	 */
	public int update(AdminMvBannerVO vo);

	/**
	 * 배너 삭제
	 * @param vo
	 * @return
	 */
	public int delete(AdminMvBannerVO vo);
	
	;
}

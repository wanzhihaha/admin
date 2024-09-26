package com.cellosquare.adminapp.admin.goods.mapper;

import java.util.List;

import com.cellosquare.adminapp.admin.goods.vo.AdminGoodsVO;

public interface AdminGoodsMapper {

	/**
	 * 상품 리스트 토탈 카운트
	 * @param vo
	 * @return
	 */
	public int getTotalCount(AdminGoodsVO vo);
	
	/**
	 * 상품 리스트 조회
	 * @param vo
	 * @return
	 */
	public List<AdminGoodsVO> getList(AdminGoodsVO vo);
	
	/**
	 * 상품 시퀀스로 조회
	 * @param vo
	 * @return
	 */
	public AdminGoodsVO getDetail(AdminGoodsVO vo);

	/**
	 * 상품 카테고리 이름 가져오기
	 * @param vo
	 * @return
	 */
	public AdminGoodsVO getCategoryNm(AdminGoodsVO vo);

	/**
	 * 정렬순서 저장
	 * @param vo
	 * @return
	 */
	public int doSortOrder(AdminGoodsVO vo);

	/**
	 * 상품 등록
	 * @param vo
	 * @return
	 */
	public int regist(AdminGoodsVO vo);

	/**
	 * 상품 팝업 리스트 조회
	 * @param vo
	 * @return
	 */
	public List<AdminGoodsVO> getPopUpList(AdminGoodsVO vo);

	/**
	 * 상품 이름 조회
	 * @param goodsNm
	 * @return
	 */
	public AdminGoodsVO getGoodsNm(AdminGoodsVO adminGoodsVO);

	/**
	 * 상품 수정
	 * @param adminGoodsVO
	 * @return
	 */
	public int doUpdate(AdminGoodsVO vo);

	/**
	 * 상품 삭제
	 * @param vo
	 * @return
	 */
	public int delete(AdminGoodsVO vo);

	/**
	 * 팝업토탈카운트
	 * @param vo
	 * @return
	 */
	public int popupTotalCount(AdminGoodsVO vo);

	


//
//	/**
//	 * 이벤트 등록
//	 * @param vo
//	 * @return 
//	 */
//	public int regist(AdminEventVO vo);
//
//	/**
//	 * 이벤트 수정
//	 * @param vo
//	 * @return 
//	 */
//	public int update(AdminEventVO vo);
//
//	/**
//	 * 이벤트 삭제
//	 * @param vo
//	 * @return 
//	 */
//	public int delete(AdminEventVO vo);
	
}

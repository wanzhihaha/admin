package com.cellosquare.adminapp.admin.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cellosquare.adminapp.admin.goods.vo.AdminProductsVO;
import com.cellosquare.adminapp.admin.goods.vo.Products;

import java.util.List;

public interface AdminProductsMapper extends BaseMapper<Products> {

	/**
	 * 상품 리스트 토탈 카운트
	 * @param vo
	 * @return
	 */
	public int getTotalCount(AdminProductsVO vo);
	
	/**
	 * 상품 리스트 조회
	 * @param vo
	 * @return
	 */
	public List<AdminProductsVO> getList(AdminProductsVO vo);
	
	/**
	 * 상품 시퀀스로 조회
	 * @param vo
	 * @return
	 */
	public AdminProductsVO getDetail(AdminProductsVO vo);


	/**
	 * 정렬순서 저장
	 * @param vo
	 * @return
	 */
	public int doSortOrder(AdminProductsVO vo);

	/**
	 * 상품 등록
	 * @param vo
	 * @return
	 */
	public int regist(AdminProductsVO vo);

	/**
	 * 상품 이름 조회
	 * @param goodsNm
	 * @return
	 */
	public AdminProductsVO getGoodsNm(AdminProductsVO AdminProductsVO);

	/**
	 * 상품 수정
	 * @param AdminProductsVO
	 * @return
	 */
	public int doUpdate(AdminProductsVO vo);

	/**
	 * 상품 삭제
	 * @param vo
	 * @return
	 */
	public int delete(AdminProductsVO vo);

	/**
	 * 重名查询
	 * @param nm
	 * @return
	 */
	public int getProductCountByNm(AdminProductsVO vo);

}

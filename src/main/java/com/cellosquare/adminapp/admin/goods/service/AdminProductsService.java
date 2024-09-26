package com.cellosquare.adminapp.admin.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cellosquare.adminapp.admin.article.vo.Article;
import com.cellosquare.adminapp.admin.goods.vo.AdminProductsVO;
import com.cellosquare.adminapp.admin.goods.vo.Products;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface AdminProductsService extends IService<Products> {

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
	 * @param User
	 * @return
	 */
	public int regist(AdminProductsVO vo);


	/**
	 * 상품 이름 조회
	 * @param AdminProductsVO
	 * @return
	 */
	public AdminProductsVO getGoodsNm(AdminProductsVO AdminProductsVO);

	/**
	 * 상품 수정
	 * @param AdminProductsVO
	 * @return
	 */
	public int update(AdminProductsVO vo);

	/**
	 * 상품 삭제
	 * @param vo
	 * @return
	 */
	public int delete(AdminProductsVO vo);


	/**
	 * 重名查询
	 * @param nm
	 */
	public int getProductCountByNm(String nm,String ignoreSeqNo);

    void productList(Model model, AdminProductsVO vo);

	String register(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest, AdminProductsVO vo) throws Exception;

	String doUpdate(HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest muServletRequest, Model model, AdminProductsVO vo) throws Exception;

    void updateForm(Model model, AdminProductsVO vo);

	void goDetail(Model model, AdminProductsVO vo);
}

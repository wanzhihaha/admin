package com.cellosquare.adminapp.admin.blog.mapper;

import java.util.List;

import com.cellosquare.adminapp.admin.blog.vo.AdminBlogVO;

public interface AdminBlogMapper {

	/**
	 * 블로그 리스트 토탈 카운트
	 * @param vo
	 */
	public int getTotalCount(AdminBlogVO vo);
	
	/**
	 * 블로그 리스트 조회
	 * @param vo
	 */
	public List<AdminBlogVO> getList(AdminBlogVO vo);
	
	/**
	 * 블로그 시퀀스로 조회
	 * @param vo
	 */
	public AdminBlogVO getDetail(AdminBlogVO vo);
	
	/**
	 * 블로그 등록
	 * @param vo
	 */
	public int doWrite(AdminBlogVO vo) throws Exception;
	
	/**
	 * 블로그 수정
	 * @param vo
	 */
	public int doUpdate(AdminBlogVO vo) throws Exception;
	
	/**
	 * 블로그 삭제
	 * @param vo
	 */
	public int doDelete(AdminBlogVO vo) throws Exception;
	
	/**
	 * ordb 정렬순서 저장
	 * @param vo
	 */
	public int doSortOrder(AdminBlogVO vo) throws Exception;
}

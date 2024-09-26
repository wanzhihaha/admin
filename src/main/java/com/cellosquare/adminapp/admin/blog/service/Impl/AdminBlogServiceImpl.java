package com.cellosquare.adminapp.admin.blog.service.Impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cellosquare.adminapp.admin.blog.mapper.AdminBlogMapper;
import com.cellosquare.adminapp.admin.blog.service.AdminBlogService;
import com.cellosquare.adminapp.admin.blog.vo.AdminBlogVO;

@Service
public class AdminBlogServiceImpl implements AdminBlogService{

	@Autowired
	private AdminBlogMapper adminBlogMapper;
	
	/**
	 * 블로그 리스트 토탈 카운트
	 * @param vo
	 * @return
	 */
	@Override
	public int getTotalCount(AdminBlogVO vo) {
		return adminBlogMapper.getTotalCount(vo);
	}

	/**
	 * 블로그 리스트 조회
	 * @param vo
	 * @return
	 */
	@Override
	public List<AdminBlogVO> getList(AdminBlogVO vo) {
		return adminBlogMapper.getList(vo);
	}

	/**
	 * 블로그 시퀀스로 조회
	 * @param vo
	 * @return
	 */
	@Override
	public AdminBlogVO getDetail(AdminBlogVO vo) {
		return adminBlogMapper.getDetail(vo);
	}

	/**
	 * 블로그 등록
	 * @param vo
	 * @return
	 */
	@Override
	public int doWrite(AdminBlogVO vo) throws Exception {
		return adminBlogMapper.doWrite(vo);
	}
		
	
	/**
	 * 블로그 수정
	 * @param vo
	 * @return
	 */
	@Override
	public int doUpdate(AdminBlogVO vo) throws Exception {
		return adminBlogMapper.doUpdate(vo);
	}

	/**
	 * 블로그 삭제
	 * @param vo
	 * @return
	 */
	@Override
	public int doDelete(AdminBlogVO vo) throws Exception {
		return adminBlogMapper.doDelete(vo);
	}

	/**
	 * ordb 정렬순서 저장
	 * @param vo
	 * @return 
	 */
	@Override
	public int doSortOrder(AdminBlogVO vo) throws Exception {
		return adminBlogMapper.doSortOrder(vo);
	}

}

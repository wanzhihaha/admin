package com.cellosquare.adminapp.admin.faq.service;

import java.util.List;

import com.cellosquare.adminapp.admin.faq.vo.AdminFaqVO;

public interface AdminFaqService {

	public int getTotalCount(AdminFaqVO vo);
	
	public List<AdminFaqVO> getList(AdminFaqVO vo);
	
	public AdminFaqVO getDetail(AdminFaqVO vo);
	
	public int doWrite(AdminFaqVO vo) throws Exception;
	
	public int doUpdate(AdminFaqVO vo) throws Exception;
	
	public int doDelete(AdminFaqVO vo) throws Exception;
	
	// 정렬순서 저장
	public int doSortOrder(AdminFaqVO vo) throws Exception;
}

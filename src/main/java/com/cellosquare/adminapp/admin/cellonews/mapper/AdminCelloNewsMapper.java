package com.cellosquare.adminapp.admin.cellonews.mapper;

import java.util.List;

import com.cellosquare.adminapp.admin.cellonews.vo.AdminCelloNewsVO;

public interface AdminCelloNewsMapper {

	public int getTotalCount(AdminCelloNewsVO vo);
	
	public List<AdminCelloNewsVO> getList(AdminCelloNewsVO vo);
	
	public AdminCelloNewsVO getDetail(AdminCelloNewsVO vo); 
	
	public int doWrite(AdminCelloNewsVO vo) throws Exception;

	public int doUpdate(AdminCelloNewsVO vo) throws Exception;
	
	public int doDelete(AdminCelloNewsVO vo) throws Exception;
	
	public int doSortOrder(AdminCelloNewsVO vo) throws Exception;
}

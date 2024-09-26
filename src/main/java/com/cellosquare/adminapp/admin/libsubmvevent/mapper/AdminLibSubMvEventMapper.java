package com.cellosquare.adminapp.admin.libsubmvevent.mapper;

import java.util.List;

import com.cellosquare.adminapp.admin.event.vo.AdminEventVO;
import com.cellosquare.adminapp.admin.libsubmvevent.vo.AdminLibSubMvEventVO;

public interface AdminLibSubMvEventMapper {

	public int getTotalCount(AdminLibSubMvEventVO vo);
	
	public List<AdminLibSubMvEventVO> getList(AdminLibSubMvEventVO vo);
	
	public AdminLibSubMvEventVO getDetail(AdminLibSubMvEventVO vo);
	
	public int doWrite(AdminLibSubMvEventVO vo) throws Exception;
	
	public int doUpdate(AdminLibSubMvEventVO vo) throws Exception;
	
	public int doDelete(AdminLibSubMvEventVO vo) throws Exception;
	
	public int doSortOrder(AdminLibSubMvEventVO vo) throws Exception;
	
	public List<AdminEventVO> getPopUpList(AdminLibSubMvEventVO vo);
	
	public int popupTotalCount(AdminLibSubMvEventVO vo);
	
}

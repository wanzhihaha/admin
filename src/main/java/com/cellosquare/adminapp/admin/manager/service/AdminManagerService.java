package com.cellosquare.adminapp.admin.manager.service;

import java.util.List;

import com.cellosquare.adminapp.admin.login.vo.AdminLoginVO;
import com.cellosquare.adminapp.admin.manager.vo.AdminManagerVO;

public interface AdminManagerService {

	public int registAdmin(AdminManagerVO vo);

	public int getChkId(AdminLoginVO vo);

	public List<AdminManagerVO> getAdminList(AdminManagerVO vo);

	public int getTotalCount(AdminManagerVO vo);

	public AdminManagerVO getDetail(AdminManagerVO vo);

	public int updateAdmin(AdminManagerVO vo);
	
	public int doDelete(AdminManagerVO vo);
	
	public int sendEmail(AdminManagerVO vo);
	
	public int updateLoginFailCntReset(AdminManagerVO vo);

//	public void useUpdate(AdminManagerVO vo);
}

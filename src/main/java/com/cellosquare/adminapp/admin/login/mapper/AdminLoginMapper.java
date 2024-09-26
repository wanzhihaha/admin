package com.cellosquare.adminapp.admin.login.mapper;

import com.cellosquare.adminapp.admin.login.vo.AdminLoginVO;
import com.cellosquare.adminapp.admin.manager.vo.AdminManagerVO;

public interface AdminLoginMapper {
	public AdminManagerVO getLogin(AdminLoginVO vo);
	
	public int updateLastLoginDt(AdminManagerVO vo);
	
	public int updatePassword(AdminManagerVO vo);
	
	public int updateLoginFailCnt(AdminLoginVO vo);
}

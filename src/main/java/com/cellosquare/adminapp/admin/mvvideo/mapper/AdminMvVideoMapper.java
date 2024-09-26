package com.cellosquare.adminapp.admin.mvvideo.mapper;

import java.util.List;

import com.cellosquare.adminapp.admin.mvvideo.vo.AdminMvVideoVO;
import com.cellosquare.adminapp.admin.video.vo.AdminVideoVO;

public interface AdminMvVideoMapper {
	
	// totalcount
	public int getTotalCount(AdminMvVideoVO vo);
	
	// 리스트 조회
	public List<AdminMvVideoVO> getList(AdminMvVideoVO vo);
	
	// 등록
	public int doWrite(AdminMvVideoVO vo) throws Exception;
	
	// 정보 조회
	public AdminMvVideoVO getDetail(AdminMvVideoVO vo);

	// 수정
	public int doUpdate(AdminMvVideoVO vo) throws Exception;
	
	// 삭제 
	public int doDelete(AdminMvVideoVO vo) throws Exception;
	
	// 순서 정렬
	public int doSortOrder(AdminMvVideoVO vo) throws Exception;
	
	public List<AdminVideoVO> getPopUpList(AdminMvVideoVO vo);
	
	public int popupTotalCount(AdminMvVideoVO vo);
	

}

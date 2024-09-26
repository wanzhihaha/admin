package com.cellosquare.adminapp.admin.libsubmv.mapper;

import java.util.List;

import com.cellosquare.adminapp.admin.libsubmv.vo.AdminLibSubMvVO;	

public interface AdminLibSubMvMapper {
	
	// totalcount
	public int getTotalCount(AdminLibSubMvVO vo);
	
	// 리스트 조회
	public List<AdminLibSubMvVO> getList(AdminLibSubMvVO vo);

	// 등록
	public int regist(AdminLibSubMvVO vo) throws Exception;
	
	// 정보 조회
	public AdminLibSubMvVO getDetail(AdminLibSubMvVO vo);

	// 수정
	public int doUpdate(AdminLibSubMvVO vo) throws Exception;
	
	// 삭제 
	public int doDelete(AdminLibSubMvVO vo) throws Exception;
	
	// 순서 정렬
	public int doSortOrder(AdminLibSubMvVO vo) throws Exception;
	
	// 팝업 리스트 조회
	public List<AdminLibSubMvVO> getPopUpList(AdminLibSubMvVO vo);
	
	// 팝업 토탈 카운트
	public int popupTotalCount(AdminLibSubMvVO vo);

}

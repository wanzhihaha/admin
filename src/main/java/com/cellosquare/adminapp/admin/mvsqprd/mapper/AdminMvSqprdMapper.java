package com.cellosquare.adminapp.admin.mvsqprd.mapper;

import java.util.List;

import com.cellosquare.adminapp.admin.goods.vo.AdminGoodsVO;
import com.cellosquare.adminapp.admin.mvsqprd.vo.AdminMvSqprdVO;

public interface AdminMvSqprdMapper {
	
	// totalcount
	public int getTotalCount(AdminMvSqprdVO vo);
	
	// 리스트 조회
	public List<AdminMvSqprdVO> getList(AdminMvSqprdVO vo);
	
	// 등록
	public int doWrite(AdminMvSqprdVO vo) throws Exception;
	
	// 정보 조회
	public AdminMvSqprdVO getDetail(AdminMvSqprdVO vo);

	// 수정
	public int doUpdate(AdminMvSqprdVO vo) throws Exception;
	
	// 삭제 
	public int doDelete(AdminMvSqprdVO vo) throws Exception;
	
	// 순서 정렬
	public int doSortOrder(AdminMvSqprdVO vo) throws Exception;
	
	public List<AdminGoodsVO> getPopUpList(AdminMvSqprdVO vo);
	
	public int popupTotalCount(AdminMvSqprdVO vo);
	
}

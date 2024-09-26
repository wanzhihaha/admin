package com.cellosquare.adminapp.admin.mainviewlibrary.mapper;

import java.util.List;

import com.cellosquare.adminapp.admin.mainviewlibrary.vo.AdminMainViewLibraryVO;

public interface AdminMainViewLibraryMapper {
	
	/**
	 * 라이브러리 리스트 토탈 카운트
	 * @param vo
	 */
	public int getTotalCount(AdminMainViewLibraryVO vo);
	
	/**
	 * 라이브러리 리스트 조회
	 * @param vo
	 */
	public List<AdminMainViewLibraryVO> getList(AdminMainViewLibraryVO vo);
	
	/**
	 * 라이브러리 시퀀스로 조회
	 * @param vo
	 * 
	 */
	public AdminMainViewLibraryVO getDetail(AdminMainViewLibraryVO vo);
	
	/**
	 * 라이브러리 등록
	 * @param vo
	 */
	public int doWrite(AdminMainViewLibraryVO vo) throws Exception;
	
	/**
	 * 라이브러리 수정
	 * @param vo
	 */
	public int doUpdate(AdminMainViewLibraryVO vo) throws Exception;
	
	/**
	 * 라이브러리 삭제
	 * @param vo
	 */
	public int doDelete(AdminMainViewLibraryVO vo) throws Exception;
	
	/**
	 * ordb 정렬순서 저장
	 * @param vo
	 */
	public int doSortOrder(AdminMainViewLibraryVO vo) throws Exception;
	
	/**
	 * 라이브러리 서치 카운트
	 * @param vo
	 */
	public int libraryTotalCount(AdminMainViewLibraryVO vo);
	
	/**
	 * 라이브러리 서치 리스트
	 * @param vo
	 */
	public List<AdminMainViewLibraryVO> librarySearch(AdminMainViewLibraryVO vo);
}

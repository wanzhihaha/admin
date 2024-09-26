package com.cellosquare.adminapp.admin.mainviewlibrary.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cellosquare.adminapp.admin.mainviewlibrary.mapper.AdminMainViewLibraryMapper;
import com.cellosquare.adminapp.admin.mainviewlibrary.service.AdminMainViewLibraryService;
import com.cellosquare.adminapp.admin.mainviewlibrary.vo.AdminMainViewLibraryVO;

@Service
public class AdminMainViewLibraryServiceImpl implements AdminMainViewLibraryService {

	@Autowired
	private AdminMainViewLibraryMapper adminMainViewLibraryMapper;
	
	/**
	 * 라이브러리 리스트 토탈 카운트
	 * @param vo
	 * @return
	 */
	@Override
	public int getTotalCount(AdminMainViewLibraryVO vo) {
		return adminMainViewLibraryMapper.getTotalCount(vo);
	}

	/**
	 * 라이브러리 시퀀스로 조회
	 * @param vo
	 * @return
	 */
	@Override
	public List<AdminMainViewLibraryVO> getList(AdminMainViewLibraryVO vo) {
		return adminMainViewLibraryMapper.getList(vo);
	}

	/**
	 * 라이브러리 시퀀스로 조회
	 * @param vo
	 * @return
	 */
	@Override
	public AdminMainViewLibraryVO getDetail(AdminMainViewLibraryVO vo) {
		return adminMainViewLibraryMapper.getDetail(vo);
	}

	/**
	 * 라이브러리 등록
	 * @param vo
	 * @return
	 */
	@Override
	public int doWrite(AdminMainViewLibraryVO vo) throws Exception {
		return adminMainViewLibraryMapper.doWrite(vo);
	}
		
	
	/**
	 * 라이브러리 수정
	 * @param vo
	 * @return
	 */
	@Override
	public int doUpdate(AdminMainViewLibraryVO vo) throws Exception {
		return adminMainViewLibraryMapper.doUpdate(vo);
	}

	/**
	 * 라이브러리 삭제
	 * @param vo
	 * @return
	 */
	@Override
	public int doDelete(AdminMainViewLibraryVO vo) throws Exception {
		return adminMainViewLibraryMapper.doDelete(vo);
	}

	/**
	 * ordb 정렬순서 저장
	 * @param vo
	 * @return 
	 */
	@Override
	public int doSortOrder(AdminMainViewLibraryVO vo) throws Exception {
		return adminMainViewLibraryMapper.doSortOrder(vo);
	}

	@Override
	public int libraryTotalCount(AdminMainViewLibraryVO vo) {
		return adminMainViewLibraryMapper.libraryTotalCount(vo);
	}

	@Override
	public List<AdminMainViewLibraryVO> librarySearch(AdminMainViewLibraryVO vo) {
		return adminMainViewLibraryMapper.librarySearch(vo);
	}
	
	
}

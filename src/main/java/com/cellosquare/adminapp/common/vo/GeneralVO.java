package main.java.com.cellosquare.adminapp.common.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class GeneralVO extends com.cellosquare.adminapp.admin.seo.vo.AdminSeoVO {

	
	/**
	 * 최초 등록 일시
	 */
	private String insDtm;
	/**
	 * 최초 등록 아이디
	 */
	private String insPersonId;
	/**
	 * 최초 등록자 명
	 */
	private String insPersonNm;
	/**
	 * 최종 수정 일시
	 */
	private String updDtm;
	/**
	 *  최종 수정 아이디
	 */
	private String updPersonId;
	/**
	 * 최종 수정자 명
	 */
	private String updPersonNm;
	
	/**
	 * 검색 타입
	 */
	private String searchType;
	
	/**
	 * 검색 타입1
	 */
	private String searchType1;
	
	/**
	 * 검색 타입2
	 */
	private String searchType2;
	
	/**
	 * 검색 타입3
	 */
	private String searchType3;
	
	/**
	 * 검색 값
	 */
	private String searchValue;
	
	/**
	 * 검사시작일자
	 */
	private String searchBeginDe;
	
	/**
	 * 검사종료일자
	 */
	private String searchEndDe;
	
	/**
	 * 페이지 번호
	 */
	private String page = "1"; //초기값 설정
	
	/**
	 * 한 페이지에 표시될 레코드 수
	 */
	private String rowPerPage = "20"; //초기값 설정
	
	private String switchUserId;
	private String switchNm;
	private String switchEntrpsSn;
	private String switchEntrpsNm;
	// input file
	private MultipartFile pcListOrginFile;
	private MultipartFile pcDetailOrginFile;
	private MultipartFile mobileListOrginFile;
	private MultipartFile mobileDetailOrginFile;
	private MultipartFile viewTypeOrginFile;
	// 파일 삭제여부
	private String pcListFileDel;
	private String pcDetailFileDel;
	private String mobileListFileDel;
	private String mobileDetailFileDel;
	private String viewTypeFileDel;

	private String listImgPath;


	private String listImgOrgFileNm;


	private String listImgFileNm;

	private MultipartFile listOrginFile;

	private String mobileListImgPath;

	private String mobileListImgOrgFileNm;

	private String mobileListImgFileNm;

	public String getInsDtm() {
		return insDtm;
	}

	public void setInsDtm(String insDtm) {
		this.insDtm = insDtm;
	}

	public String getInsPersonId() {
		return insPersonId;
	}

	public void setInsPersonId(String insPersonId) {
		this.insPersonId = insPersonId;
	}

	public String getInsPersonNm() {
		return insPersonNm;
	}

	public void setInsPersonNm(String insPersonNm) {
		this.insPersonNm = insPersonNm;
	}

	public String getUpdDtm() {
		return updDtm;
	}

	public void setUpdDtm(String updDtm) {
		this.updDtm = updDtm;
	}

	public String getUpdPersonId() {
		return updPersonId;
	}

	public void setUpdPersonId(String updPersonId) {
		this.updPersonId = updPersonId;
	}

	public String getUpdPersonNm() {
		return updPersonNm;
	}

	public void setUpdPersonNm(String updPersonNm) {
		this.updPersonNm = updPersonNm;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchType1() {
		return searchType1;
	}

	public void setSearchType1(String searchType1) {
		this.searchType1 = searchType1;
	}

	public String getSearchType2() {
		return searchType2;
	}

	public void setSearchType2(String searchType2) {
		this.searchType2 = searchType2;
	}

	public String getSearchType3() {
		return searchType3;
	}

	public void setSearchType3(String searchType3) {
		this.searchType3 = searchType3;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public String getSearchBeginDe() {
		return searchBeginDe;
	}

	public void setSearchBeginDe(String searchBeginDe) {
		this.searchBeginDe = searchBeginDe;
	}

	public String getSearchEndDe() {
		return searchEndDe;
	}

	public void setSearchEndDe(String searchEndDe) {
		this.searchEndDe = searchEndDe;
	}
	
	public String getPage() {
		
		//아래 코드는 파라미터 조작시 에러를 방지하기 위한 예외 처리 이다.
		if(page == null) {
			page = "1";
		}
		
		try {
			Integer.parseInt(page);
		} catch(NumberFormatException e) {
			page = "1";
		}
		
		if(Integer.parseInt(page) < 1) {
			page = "1";
		}
		
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRowPerPage() {
		
		//아래 코드는 파라미터 조작시 에러를 방지하기 위한 예외 처리 이다.
		if(rowPerPage == null) {
			rowPerPage = "20";
		}
		
		try {
			Integer.parseInt(rowPerPage);
		} catch(NumberFormatException e) {
			rowPerPage = "20";
		}
		
		if(Integer.parseInt(rowPerPage) < 1) {
			rowPerPage = "20";
		}
				
		return rowPerPage;
	}

	public void setRowPerPage(String rowPerPage) {
		this.rowPerPage = rowPerPage;
	}
	
	/**
	 * <pre>
	 * mybatis 페이징 사용시 사용되는 startRow
	 * #{startRow, jdbcType=VARCHAR}
	 * 
	 * 페이징 예제
	 * SELECT * FROM (
	 *     SELECT
	 *         ROW_NUMBER() OVER(ORDER BY FRST_REGIST_DT DESC) AS RNUM,
	 *         FIELD1, FIELD2, FIELD3, FIELD4, FIELD5
	 *     FROM TABLE_NAME
	 *     WHERE 1=1
	 * ) TMP
	 * WHERE RNUM BETWEEN #{startRow, jdbcType=VARCHAR} AND #{endRow, jdbcType=VARCHAR}
	 * </pre>
	 *
	 * @return
	 */
	public int getStartRow() {
		//return String.valueOf(((Integer.parseInt(getPage()) - 1) * Integer.parseInt(getRowPerPage())) + 1);
		return ((Integer.parseInt(getPage()) - 1) * Integer.parseInt(getRowPerPage()));
	}
	
	/**
	 * <pre>
	 * mybatis 페이징 사용시 사용되는 endRow
	 * #{endRow, jdbcType=VARCHAR}
	 * 
	 * 페이징 예제
	 * SELECT * FROM (
	 *     SELECT
	 *         ROW_NUMBER() OVER(ORDER BY FRST_REGIST_DT DESC) AS RNUM,
	 *         FIELD1, FIELD2, FIELD3, FIELD4, FIELD5
	 *     FROM TABLE_NAME
	 *     WHERE 1=1
	 * ) TMP
	 * WHERE RNUM BETWEEN #{startRow, jdbcType=VARCHAR} AND #{endRow, jdbcType=VARCHAR}
	 * </pre>
	 *
	 * @return
	 */
	public int getEndRow() {
		//return String.valueOf((Integer.parseInt(getPage()) * Integer.parseInt(getRowPerPage())));
		return Integer.parseInt(getRowPerPage());
	}

	public String getSwitchUserId() {
		return switchUserId;
	}

	public void setSwitchUserId(String switchUserId) {
		this.switchUserId = switchUserId;
	}

	public String getSwitchNm() {
		return switchNm;
	}

	public void setSwitchNm(String switchNm) {
		this.switchNm = switchNm;
	}

	public String getSwitchEntrpsSn() {
		return switchEntrpsSn;
	}

	public void setSwitchEntrpsSn(String switchEntrpsSn) {
		this.switchEntrpsSn = switchEntrpsSn;
	}

	public String getSwitchEntrpsNm() {
		return switchEntrpsNm;
	}

	public void setSwitchEntrpsNm(String switchEntrpsNm) {
		this.switchEntrpsNm = switchEntrpsNm;
	}
	
}

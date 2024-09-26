package main.java.com.cellosquare.adminapp.common.vo;

public class GeneralSubVO extends GeneralVO {

	/**
	 * 페이지 번호
	 */
	private String subPage = "1"; //초기값 설정
	
	/**
	 * 한 페이지에 표시될 레코드 수
	 */
	private String rowPerSubPage = "20"; //초기값 설정
	
	public String getSubPage() {
		
		//아래 코드는 파라미터 조작시 에러를 방지하기 위한 예외 처리 이다.
		if(subPage == null) {
			subPage = "1";
		}
		
		try {
			Integer.parseInt(subPage);
		} catch(NumberFormatException e) {
			subPage = "1";
		}
		
		if(Integer.parseInt(subPage) < 1) {
			subPage = "1";
		}
		
		return subPage;
	}

	public void setSubPage(String subPage) {
		this.subPage = subPage;
	}

	public String getRowPerSubPage() {
		
		//아래 코드는 파라미터 조작시 에러를 방지하기 위한 예외 처리 이다.
		if(rowPerSubPage == null) {
			rowPerSubPage = "20";
		}
		
		try {
			Integer.parseInt(rowPerSubPage);
		} catch(NumberFormatException e) {
			rowPerSubPage = "20";
		}
		
		if(Integer.parseInt(rowPerSubPage) < 1) {
			rowPerSubPage = "20";
		}
				
		return rowPerSubPage;
	}

	public void setrowPerSubPage(String rowPerSubPage) {
		this.rowPerSubPage = rowPerSubPage;
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
		//return String.valueOf(((Integer.parseInt(getsubPage()) - 1) * Integer.parseInt(getrowPerSubPage())) + 1);
		return ((Integer.parseInt(getSubPage()) - 1) * Integer.parseInt(getRowPerSubPage()));
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
		//return String.valueOf((Integer.parseInt(getsubPage()) * Integer.parseInt(getrowPerSubPage())));
		return Integer.parseInt(getRowPerSubPage());
	}
}

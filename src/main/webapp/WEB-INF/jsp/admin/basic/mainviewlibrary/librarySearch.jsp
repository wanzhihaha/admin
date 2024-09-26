<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!-- START:CONTENT -->
<form id="searchFrom" action="" method="get">
<input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
<section>
	<div class="title"><h3>Library Search</h3></div>
	<div class="Cont_place">
	<article>
	<div class="inputUI_simple">
	<table class="bd-form s-form" summary="검색 및 추가 페이지 영역입니다.">
		<caption>검색 및 추가 페이지 영역</caption>
		<colgroup>
			<col width="120px" />
			<col width="180px" />
		</colgroup>		
		<tr>
			<th scope="row">Search</th>
				<td>
					<div class="select-inner">
						<select id="searchType" name="searchType">
							<option value="">ALL</option>
						</select>
					</div>
				</td>
			<td>
				<input type="text" placeholder="Please enter your search term." class="inp-field widS mglS" id="searchValue" name="searchValue" value="<c:out value="${vo.searchValue }" escapeXml="false"/>" />
				<div class="btn-module floatR">
					<div class="rightGroup">
						<a href="javascript:;" class="search" onclick="searchClick();" id="btnSearch">Search</a>
					</div>
				</div>	
			</td>
		</tr>
		</table>
		</div>
		</article>
		<article>
		<div class="btn-module mgtL2 mgbS">
		</div>
		<table class="bd-list inputUI_simple" summary="리스트 영역 입니다.">
			<caption>NO</caption>
			<colgroup>
				<col width="14%" />
				<col width="14%" />
				<col width="" />
				<col width="8%" />
			</colgroup>
			<thead>
			<tr>
				<th scope="col">Sort1</th>
				<th scope="col">Sort2</th>
				<th scope="col">Title</th>
				<th scope="col">Add</th>
			</tr>
			</thead>
			<tbody>
			
			<c:forEach var="list" items="${list}" varStatus="stat">
					<tr>
						<td>
							<c:out value="${list.libCcd1Nm}" />	
							<input type="hidden" id="mvLibCcd" name="mvLibCcd" value="<c:out value="${list.mvLibCcd}" />">
						</td>
						<td>
							<c:out value="${list.libCcd2Nm}" />
							<input type="hidden" id="libCcd2" name="libCcd2" value="<c:out value="${list.libCcd2}" />">
						</td>
						<td>
							<c:out value="${list.titleNm}" escapeXml="false"/>
						</td>
						<td>
							<div class="btn-module useRS">
								<a href="javascript:;" onclick="useClick('${list.reltdLib }', '${list.mvLibCcd }', '${list.libCcd2 }', '<c:out value="${list.titleNm}" />');" class=tdSave  style="text-decoration: none;">Add</a>
							</div>
						</td>
					</tr>
			</c:forEach>
			<c:if test="${fn:length(list) < 1 }">
				<tr>
					<td colspan="4" class="blank">No registered data</td>
				</tr>
			</c:if>	
				
			</tbody>
		</table>
		
		<div class="btn-module mgtSM">
		</div>
		
		<!-- table paging -->
		<div class="page-module">
	      <p class="paging">
	      	<blabPaging:paging currentPage="${vo.page }" totalCount="${totalCount}" rowSize="${vo.rowPerPage }" pagingId="admin" />
	      </p>
	   </div>
	
			<div class="btn-module" style="text-align: center;">
					<a href="javascript:;" class="btnStyle01" onclick="fnClose();">Close</a>
			</div>	
	
		</article>
		</div>
	</section>
	</form>

<script type="text/javascript">

	$(document).ready(function () {	
		$("#searchValue").keypress(function(e) {
		    if (e.keyCode == 13) {
		    	e.preventDefault();
		    	searchClick();
		    }
		});	
		$.fnGetCodeSelectAjax("sGb=MVLIB_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "searchType", "${vo.searchType}", "selectbox", "");
		$("#searchType option[value='MVLIB_01']").remove();
		$("#searchType option[value='MVLIB_02']").remove();
	});

	//검색 , 페이징,
	function fncPage(page) {
		$("#page").val(page);
		$("#listForm").prop("method", "get");
		$("#searchFrom").submit();
	}
	
	function searchClick(){	
		$("#page").val("1");
		$("#listForm").prop("method", "get");
		$("#searchFrom").submit();
	}
	
	function fnSearch(){
		$("#page").val("1");
		$("#searchFrom").prop("action","librarySearch.do");
		$("#listForm").prop("method", "get");
		$("#searchFrom").submit();
	}
	
	// popUp 끄기
	function fnClose() {
		self.close();
	}
	
	function useClick(reltdLib, mvLibCcd, libCcd2, titleNm) {
		
		console.log(reltdLib);
		console.log(mvLibCcd);
		console.log(libCcd2);
		console.log(titleNm);
		
		$("#reltdLib",opener.document).val(reltdLib);
		$("#mvLibCcd",opener.document).val(mvLibCcd).prop("selected", true);;
		$("#libCcd2",opener.document).val(libCcd2);
		$("#titleNm",opener.document).val(unescapeHtml(titleNm));
		
		self.close();
	}
	
</script>
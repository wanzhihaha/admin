<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<!-- START:CONTENT -->
<form id="searchFrom" action="" method="get">
<input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
<input type="hidden" id="sqprdSeqNo" name="sqprdSeqNo" value="<c:out value=""/>">

	
<section>
	<div class="title"><h3>Library Search</h3></div>
	<div class="Cont_place">
	<article>
	<div class="inputUI_simple">
	<table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
		<caption>검색 영역</caption>
		<colgroup>
			<col width="120px" />
			<col width="" />
			<col width="100px" />
		</colgroup>
		<tr>
			<th scope="row">Search</th>
			<td>
				<div class="select-inner">
					<select name="searchType" id="searchType">
	
					</select>
				</div>
				<input type="text" class="inp-field widS mglS" id="libSearchValue" name="libSearchValue" value="<c:out value="${vo.libSearchValue}" escapeXml="false"/>"/>
			</td>
			<td>
				<div class="btn-module floatR">
					<div class="rightGroup">
						<a href="javascript:;" class="search" onclick="fnSearch();" id="btnSearch">Search</a>
					</div>
				</div>	
			</td>
		</tr>
	</table>
	</div>
	</article>
	<article>

		<table class="bd-list inputUI_simple" summary="리스트 영역 입니다.">
			<caption>No, sort1, sort2, 제목, selectBox</caption>
			<colgroup>
				<col width="5%" />
				<col width="15%" />
				<col width="15%" />
				<col width="%" />
				<col width="15%" />
			</colgroup>
			<thead>
			<tr>
				<th scope="col">No</th>
				<th scope="col">Sort1</th>
				<th scope="col">Sort2</th>
				<th scope="col">Title</th>
				<th scope="col">Add</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="list" items="${list }" varStatus="stat">
				<tr>
					<td>
						<span style="width: 50px;">
							<c:out value="${(totalCount - ((vo.page-1) * vo.rowPerPage)) - stat.index}" />
						</span>
					</td>
					<td>
						<c:out value="${list.libSubCcd1Nm }"/> 
					</td>
					<td>
						<c:out value="${list.libSubCcd2Nm }"/> 
					</td>
					<td>
						<c:out value="${list.titleNm }" escapeXml="false"/> 
					</td>
					<td>
						<div class="btn-module useRS">
							<a href="javascript:;" onclick="useClick('<c:out value="${list.reltdSeqNo }"/>', '<c:out value="${list.titleNm }" />', '<c:out value="${list.libSubCcd1 }"/>');" class=tdSave  style="text-decoration: none;">Add</a>
						</div>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${fn:length(list) < 1 }">
				<tr>
					<td colspan="5" class="blank">No registered data</td>
				</tr>
			</c:if>	
	
			</tbody>
		</table>
		
			<!-- table paging -->
		<div class="page-module">
			<p class="paging">
				<blabPaging:paging currentPage="${vo.page }" rowSize="${vo.rowPerPage }" totalCount="${totalCount}" pagingId="admin"/>
			</p>
		</div>

		<div class="btn-module" style="text-align: center;">
				<a href="javascript:;" class="btnStyle01" onclick="fnClose();" id="btnSearch">Close</a>
		</div>	
	</article>
	</div>

</section>
</form>

<script type="text/javascript">
$(document).ready(function(){
	$("#libSearchValue").keypress(function(e) {
	    if (e.keyCode == 13) {
	    	e.preventDefault();
	    	fnSearch();
	    }
	});	
	
	$.fnGetCodeSelectAjax("sGb=LIBSUBMV_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "searchType", "${vo.searchType}", "selectbox", "");
	$("#searchType option[value='LIBSUBMV_01']").remove();
	$("#searchType option[value='LIBSUBMV_02']").attr('seleted','seleted');
});
	//검색/페이징
	function fnSearch(){
		$("#page").val("1");
		$("#searchFrom").prop("method", "get");
		$("#searchFrom").attr('action', '<c:url value="./searchLibrary.do"/>').submit();
	}
	function fncPage(page) {
		$("#page").val(page);
		$("#searchFrom").prop("method", "get");
		$("#searchFrom").attr('action', '<c:url value="./searchLibrary.do"/>').submit();
	}

	function fnClose() {
		self.close();
	}
	
	
	var valueNmAdd;
	var valueSeqNoAdd;
	function useClick(reltdSeqNo, titleNm, libSubCcd1) {

		if($.trim($("#relsqprdSeqNo", opener.document).val()) == reltdSeqNo) {
			alert("This library is already selected.");
			return false;
		}
		
		
		$("#reltdLib",opener.document).val('');
		$("#titleNm",opener.document).val('');
		$("#mvLibCcd",opener.document).val('');
		
		$("#reltdLib",opener.document).val(reltdSeqNo);
		$("#titleNm",opener.document).val(unescapeHtml(titleNm));
		$("#mvLibCcd",opener.document).val(libSubCcd1);
			
		self.close();
	}
	
</script>


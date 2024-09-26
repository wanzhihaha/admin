<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="post">
<input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
<input type="hidden" id="tosSeqNo" name="tosSeqNo" value="<c:out value="${vo.tosSeqNo }"/>"  class="inp-field widL" />
<section>
	<div class="title"><h3>服务与条款</h3></div>
	<div class="Cont_place">
	<article>
	<div class="inputUI_simple">
	<table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
		<caption>상태, 답변 상태, 기간설정, 직접검색 영역</caption>
		<colgroup>
			<col width="120px" />
			<col width="" />
			<col width="300px" />
			<col width="" />
		</colgroup>		
		<tr>
			<th scope="row">Search</th>
			<td> 
				<div class="select-inner">
					<select id="searchType" name="searchType">
						<option value="">ALL</option>
					</select>
				</div>
				<input type="text" placeholder="Please enter your search term." class="inp-field widS mglS" id="searchValue" name="searchValue" value="<c:out value="${vo.searchValue }" escapeXml="false"/>" />
			</td>
			<td>
				<div class="btn-module floatR">
					<div class="rightGroup">
						<a href="javascript:;" class="search" onclick="searchClick();" id="btnSearch">搜索</a>
						<a href="javascript:;" class="refresh" onclick="resetClick();" id="btnReset">重置</a>
					</div>
				</div>	
			</td>
		</tr>
	</table>
	</div>
	</article>
	<article>
	<div class="btn-module mgtL2 mgbS">
		<div class="leftGroup">
			<span class="tb-text">
				<strong>Total <span class="colorR"><c:out value="${totalCount}"></c:out></span> Cases</strong>
			</span>
		</div>
		<div class="rightGroup">
			<select name="rowPerPage" id="rowPerPage" onchange="fnSearch();">
				<option value="10" <c:if test="${vo.rowPerPage eq '10'}">selected="selected"</c:if>>10</option>
				<option value="20" <c:if test="${vo.rowPerPage eq '20'}">selected="selected"</c:if>>20</option>
				<option value="30" <c:if test="${vo.rowPerPage eq '30'}">selected="selected"</c:if>>30</option>
				<option value="50" <c:if test="${vo.rowPerPage eq '50'}">selected="selected"</c:if>>50</option>
				<option value="100" <c:if test="${vo.rowPerPage eq '100'}">selected="selected"</c:if>>100</option>
			</select>
		</div>
	</div>
	<table class="bd-list inputUI_simple" summary="리스트 영역 입니다.">
		<caption>NO</caption>
		<colgroup>
			<col width="3%" />
			<col width="14%" />
			<col width="" />
			<col width="6%" />
			<col width="6%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
		</colgroup>
		<thead>
		<tr>
			<th scope="col">No</th>
			<th scope="col">Sort</th>
			<th scope="col">Title</th>
			<th scope="col">Version</th>
			<th scope="col">Status</th>
			<th scope="col">Registrar</th>
			<th scope="col">Registration<br>Date</th>
			<th scope="col">Modifier</th>
			<th scope="col">Modification<br>Date</th>
		</tr>
		</thead>
		<tbody>
		
		<c:forEach var="list" items="${list}" varStatus="vs">
			<tr>
				<td>										
					<c:out value="${(totalCount - ((vo.page-1) * vo.rowPerPage)) - vs.index}" />
				</td>
				<td>
					<c:out value="${list.tosCcdNm }" />
				</td>
				<td class="txtEll textL2">
					<a href="javascript:;"  onclick="detail('<c:out value="${list.tosSeqNo}" />');"><c:out value="${list.titleNm}" escapeXml="false"/></a>
				</td>
				<td>
					<c:out value="${list.tosVer}" />
				</td>
				<td>
					<c:out value="${list.useYnNm}" />
				</td>
				<td>
					<c:out value="${list.insPersonNm}" />
				</td>
				<td>
					<c:out value="${list.insDtm}" />
				</td>
				<td>
					<c:out value="${list.updPersonNm}" />
				</td>
				<td>
					<c:out value="${list.updDtm}" />
				</td>						
			</tr>
		</c:forEach>
		<c:if test="${fn:length(list) < 1 }">
			<tr>
				<td colspan="9" class="blank" >No registered data</td>
			</tr>
		</c:if>	
			
		</tbody>
	</table>
	<div class="btn-module mgtSM">
		<div class="rightGroup"><a href="javascript:;"  onclick="doRegister();" class="btnStyle01" >新增</a></div>
	</div>
	<!-- table paging -->
	
	<div class="page-module">
      <p class="paging">
      	<blabPaging:paging currentPage="${vo.page }" totalCount="${totalCount}" rowSize="${vo.rowPerPage }" pagingId="admin" />
      </p>
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
	
	$.fnGetCodeSelectAjax("sGb=TERMS_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "searchType", "${vo.searchType}", "selectbox", "");
	
});
//검색 , 페이징,
function fncPage(page) {
	$("#page").val(page);
	$("#listForm").prop("method", "get");
	$("#listForm").submit();
}

function searchClick(){	
	$("#page").val("1");
	$("#listForm").prop("method", "get");
	$("#listForm").submit();
}

function fnSearch(){
	$("#page").val("1");
	$("#listForm").prop("action","list.do");
	$("#listForm").prop("method", "get");
	$("#listForm").submit();
}

//값 초기화
function resetClick(){
	$("#searchType").val("");
	$("#searchValue").val("");
	$("#listForm").prop("action","list.do");
	$("#listForm").prop("method", "get");	
	$("#listForm").submit();
}
//등록페이지 이동
function doRegister(){	
	$("#listForm").prop("action","registerForm.do");
	$("#listForm").prop("method", "post");
	$("#listForm").submit();
}
//업데이트 이동
function detail(sn){
	$("#tosSeqNo").val(sn);
	$("#listForm").prop("action","detail.do");
	$("#listForm").prop("method", "post");
	$("#listForm").submit();
}
</script>
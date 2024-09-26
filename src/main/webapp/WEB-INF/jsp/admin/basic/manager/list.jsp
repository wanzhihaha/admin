<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<script type="text/javascript">

$(document).ready(function () {	
	$("#btnSearch").keypress(function(e) {
	    if (e.keyCode == 13) {
	    	e.preventDefault();
	    	searchClick();
	    }
	});	
});
//검색 , 페이징,
function fncPage(page) {
	$("#page").val(page);
	$("#adminForm").prop("method", "get");
	$("#adminForm").submit();
}
function fnSearch(){
	$("#page").val("1");
	$("#adminForm").prop("action","list.do");
	$("#adminForm").prop("method", "get");
	$("#adminForm").submit();
}
function searchClick(){	
	$("#page").val("1");
	$("#adminForm").prop("method", "get");
	$("#adminForm").submit();
}

//값 초기화
function resetClick(){
	$("#searchType").val("");
	$("#searchValue").val("");
	$("#adminForm").prop("action","list.do");
	$("#adminForm").prop("method", "get");	
	$("#adminForm").submit();
}

//등록페이지 이동
function doRegister(){	
	$("#adminForm").prop("action","registerForm.do");
	$("#adminForm").prop("method", "post");
	$("#adminForm").submit();
}
//업데이트 이동
function detail(sn){
	$("#admMngSeqNo").val(sn);
	$("#adminForm").prop("action","detail.do");
	$("#adminForm").prop("method", "post");
	$("#adminForm").submit();
}

</script>


<!-- START:CONTENT -->
<form id="adminForm" action="./list.do" method="get">
<input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
<input type="hidden" id="admMngSeqNo" name="admMngSeqNo">
<section>
	<div class="title"><h3>Administrator Management</h3></div>
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
					<select name="searchType" id="searchType">
						<option value="" <c:if test="${vo.searchType eq ''}">selected="selected"</c:if>>ALL</option>
						<option value="search_id" <c:if test="${vo.searchType eq 'search_id' }">selected="selected"</c:if>>ID</option>
						<option value="search_name" <c:if test="${vo.searchType eq 'search_name' }">selected="selected"</c:if>>Name</option>
					</select>
				</div>
				<input type="text" placeholder="Please enter your search term." class="inp-field widS mglS" id="searchValue" name="searchValue" value="<c:out value="${vo.searchValue }" escapeXml="false"/>" />
			</td>		
			<td>
				<div class="btn-module floatR">
					<div class="rightGroup">
						<a href="javascript:;" class="search" onclick="searchClick();" id="btnSearch">Search</a>
						<a href="javascript:;" class="refresh" onclick="resetClick();" id="btnReset">Reset</a>
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
			<span>
				<span class="tb-text"><strong>Total <span class="colorR"><c:out value="${totalCount }"/></span> Cases</strong></span>
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
		<caption>No, Password, Sort, Name, ID, Status, Regist ID/Date, Modify ID/Date</caption>
		<colgroup>
			<col width="5%" />
			<col width="11%" />
			<col width="11%" />
			<col width="11%" />
			<col width="7%" />
			<col width="11%" />
			<col width="11%" />
			<col width="11%" />
			<col width="11%" />
			<col width="11%" />
		</colgroup>
		<thead>
		<tr>
			<th scope="col">NO</th>
			<th scope="col">Sort</th>
			<th scope="col">Name</th>
			<th scope="col">ID</th>
			<th scope="col">Status</th>
			<th scope="col">Recent Login<br/>Date</th>
			<th scope="col">Registrar</th>
			<th scope="col">Registration<br/>Date</th>
			<th scope="col">Modifier</th>
			<th scope="col">Modification<br/>Date</th>
			
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
			<%-- <c:choose>
				<c:when test="${list.tempPwSts eq 'N'}">
					<td>
						Complete
					</td>
				</c:when>
				<c:otherwise>
					<td>
						Not<br/>Complete
					</td>
				</c:otherwise>
			</c:choose> --%>
			<td>
				<c:out value="${list.admAuthNm }"/>
			</td>
			<td>
				<a href="javascript:;"  onclick="detail('<c:out value="${list.admMngSeqNo}" />');"><c:out value="${list.admUserNm}" escapeXml="false"/></a>
			</td>
			<td>
				<a href="javascript:;"  onclick="detail('<c:out value="${list.admMngSeqNo}" />');"><c:out value="${list.admUserId}" escapeXml="false"/></a>
			</td>
			<td>
				<c:out value="${list.useYnNm }"/>
			</td>
			<td>
				<c:out value="${list.finalLogDtm }"/>
			</td>
			<td>
				<c:out value="${list.insPersonNm }"/>
			</td>
			<td>
				<c:out value="${list.insDtm }"/>
			</td>
			<td>
				<c:out value="${list.updPersonNm }"/>
			</td>
			<td>
				<c:out value="${list.updDtm }"/>
			</td>
		</tr>
		</c:forEach>
		<c:if test="${fn:length(list) < 1 }">
			<tr>
				<td colspan="10" class="blank">No registered data</td>
			</tr>
		</c:if>	
			
		</tbody>
	</table>
	
	
	
	
	<div class="btn-module mgtSM">
		<div class="leftGroup">		
		</div>
		<div class="rightGroup"><a href="#none"  onclick="doRegister();" class="btnStyle01">Registration</a></div>
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










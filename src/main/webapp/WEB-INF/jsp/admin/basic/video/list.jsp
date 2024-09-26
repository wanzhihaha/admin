<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="post">
	<input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
	<input type="hidden" id="videoSeqNo" name="videoSeqNo" value="<c:out value="${vo.videoSeqNo }"/>"  class="inp-field widL" />
	
<section>
	<div class="title"><h3>Videos</h3></div>
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
					</select>
				</div>
				<input type="text" placeholder="Please enter your search term." class="inp-field widS mglS" id="searchValue" name="searchValue" value="<c:out value="${vo.searchValue}" escapeXml="false"/>"/>
			</td>
			<td>
				<div class="btn-module floatR">
					<div class="rightGroup">
						<a href="javascript:;" class="search" onclick="fnSearch();" id="btnSearch"><c:out value="Search"/></a>
						<a href="javascript:;" class="refresh" onclick="resetClick();" id="btnReset">Reset</a>
					</div>
				</div>
			</td>
		</tr>
		
	</table>
	</div>
	<div class="btn-module mgtL2 mgbS">
		<div class="leftGroup">
			<span class="tb-text">
				<strong>Total <span class="colorR"><c:out value="${totalCount }"></c:out></span> Cases</strong>
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
		<caption>번호, 정렬순서, 대표 이미지, 제목, 사용 유무, 조회수, 등록자, 등록일, 최종 수정자, 수정일</caption>
		<colgroup>
			<col width="3%" />
			<col width="8%" />
			<col width="10%" />
			<col width="11%" />
			<col width="%" />
			<col width="6%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
		</colgroup>
		<thead>
		<tr>
			<th scope="col">No</th>
			<th scope="col">Sort<br/>Order</th>
			<th scope="col">Sort</th>
			<th scope="col">Image</th>
			<th scope="col">Title</th>
			<th scope="col">Status</th>
			<th scope="col">Views</th>
			<th scope="col">Registrar</th>
			<th scope="col">Registration<br/> Date</th>
			<th scope="col">Modifier</th>
			<th scope="col">Modification<br/> Date</th>
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
					<input type="text" name="listOrdb" class="inp-field widM2" value="<c:out value="${list.ordb}" />" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3" />
					<input type="hidden" name="listVideoSeqNo" value="<c:out value="${list.videoSeqNo}" />" />
				</td>
				<td>
					<c:out value="${list.videoCcdNm }"/>
				</td>
				<td style="text-align: center;">
				
					<a href="javascript:;"  onclick="detail(<c:out value='${list.videoSeqNo}'/>);">
						<img src="<blabProperty:value key="system.admin.path"/>/videoImgView.do?videoSeqNo=<c:out value='${list.videoSeqNo}'/>&imgKinds=pcList" width="100" alt="<c:out value="${list.pcListImgAlt}" escapeXml="false"/>" /><br />
					</a>
                </td>
				<td class="txtEll textL2">
					<a href="javascript:;"  onclick="detail('<c:out value='${list.videoSeqNo}'/>');"><c:out value='${list.titleNm}' escapeXml="false"/></a>
				</td>
				<td>
					<c:out value="${list.useYnNm }"/>
				</td>
				<td>
					<fmt:formatNumber value="${list.srchCnt }" pattern="#,###,###"/>
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
		<div class="leftGroup"><a href="#none"  onclick="doSortSave();" class="btnStyle01">Sort Order Save</a></div>
		<div class="rightGroup"><a href="#none"  onclick="doWrite();" class="btnStyle01">New Registration</a></div>
	</div>

	
	<!-- table paging -->
	<div class="page-module">
		<p class="paging">
			<blabPaging:paging currentPage="${vo.page }" rowSize="${vo.rowPerPage }" totalCount="${totalCount}" pagingId="admin"/>
		</p>
	</div>

	</article>
	</div>

</section>
</form>


<script type="text/javascript">
// 검색 엔터	
$(document).ready(function () {	
	$("#searchValue").keypress(function(e) {
	    if (e.keyCode == 13) {
	    	e.preventDefault();
	    	fnSearch();
	    }
	});	
	$.fnGetCodeSelectAjax("sGb=VIDEO_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "searchType", "${vo.searchType }", "selectbox", "");
});
	//검색/페이징
	function fnSearch(){
		$("#page").val("1");
		$("#listForm").prop("method", "get");
		$("#listForm").attr('action', '<c:url value="./list.do"/>').submit();
	}
	function fncPage(page) {
		$("#page").val(page);
		$("#listForm").prop("method", "get");
		$("#listForm").attr('action', '<c:url value="./list.do"/>').submit();
	}
	//값 초기화
	function resetClick(){
		location.href='<c:url value="./list.do"/>';
	}

	//등록페이지 이동
	function doWrite(){
		$("#listForm").prop("method", "post");
		$("#listForm").attr('action', '<c:url value="./registerForm.do"/>').submit();
	}
	// 상세페이지 이동
	function detail(seq){
		$("#videoSeqNo").val(seq);
		$("#listForm").prop("method", "post");
		$("#listForm").attr('action', '<c:url value="./detail.do"/>').submit();
	}
	// 정렬순서 저장
	
	function doSortSave() {
	if(confirm("Do you really want to fix it?")) {
		$("#listForm").prop("method", "post");
		$("#listForm").attr('action', '<c:url value="./doSortOrder.do"/>').submit();
	}
	return false;
}

</script>

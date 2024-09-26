<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>


<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="post">
<input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
<input type="hidden" id="sqprdSeqNo" name="id"/>
<%--<input type="hidden" id="sqprdCtgry" name="sqprdCtgry" value="<c:out value="${vo.sqprdCtgry }"/>">--%>
<%--<input type="hidden" id="sqprdCtgryNm" name="sqprdCtgryNm" value="<c:out value="${vo.sqprdCtgryNm }"/>">--%>

<section>
	<div class="title"><h3>广告</h3></div>
	<div class="Cont_place">
	<article>
	<div class="inputUI_simple">	
	<table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
		<caption>상태, 답변 상태, 기간설정, 직접검색 영역</caption>
		<colgroup>
			<col width="120px" />
			<col width="" />
			<col width="300px" />
		</colgroup>
		<tr>
			<th scope="row">搜索</th>
			<td> 
				<div class="select-inner">
					<select name="searchType" id="searchType">
						<option value="">全部</option>
					</select>
				</div>
				<input type="text" placeholder="请输入" class="inp-field widS mglS" id="searchValue" name="searchValue" value="<c:out value="${vo.searchValue }" escapeXml="false"/>" />
			</td>		
			<td>
				<div class="btn-module floatR">
					<div class="rightGroup">
						<a href="javascript:;" class="search" onclick="fnSearch();" id="btnSearch">搜索</a>
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
		<caption>NO</caption>
		<colgroup>
			<col width="3%" />
			<col width="8%" />
			<!-- <col width="13%" /> -->
			<col width="" />
			<col width="6%" />
			<col width="9%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
		</colgroup>
		<thead>
		<tr>
			<th scope="col">NO</th>
			<th scope="col">广告类型</th>
			<th scope="col">图片</th>
			<!-- <th scope="col">Image</th> -->
			<th scope="col">广告名称</th>
			<th scope="col">链接</th>
			<th scope="col">开始时间</th>
			<th scope="col">结束时间</th>
			<th scope="col">关键词</th>
			<th scope="col">状态</th>
			<th scope="col">创建人</th>
			<th scope="col">创建时间</th>
			<th scope="col">更新人</th>
			<th scope="col">更新时间</th>

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
				<td><c:out value="${list.adLocation }"/></td>
				<td>
					<c:if test="${list.adPicName ne null && list.adPicName.length() > 0}">
						<img src="<blabProperty:value key="system.admin.path"/>/advertising/blogImgView.do?id=<c:out value="${list.id }"/>&imgKinds=articlePicBig'/>"
							 style="max-width: 100px; vertical-align: middle;"/>
					</c:if>

				</td>
				<td>
					<a href="javascript:;" onclick="detail('<c:out value='${list.id}'/>');"><c:out value='${list.adName}' escapeXml="false"/></a>
				</td>
				<td><c:out value="${list.adUrl }"/></td>
				<td><c:out value="${list.effectiveTime }"/></td>
				<td><c:out value="${list.deadTime }"/></td>
				<td><c:out value="${list.adKeyword }"/></td>
				<td><c:out value="${list.useYn }"/></td>
				<td><c:out value="${list.createPersionId }"/></td>
				<td><fmt:formatDate value="${list.createDate }" pattern="yyyy-MM-dd"/></td>
				<td><c:out value="${list.updatePersionId }"/></td>
				<td><fmt:formatDate value="${list.updateDate }" pattern="yyyy-MM-dd"/></td>
			</tr>
		</c:forEach>
		<c:if test="${fn:length(list) < 1 }">
			<tr>
				<td colspan="9" class="blank">No registered data</td>
			</tr>
		</c:if>	

		</tbody>
	</table>

	<div class="btn-module mgtSM">
		<div class="leftGroup"><a href="#none"  onclick="doSortSave();" class="btnStyle01">自定义排序</a></div>
		<div class="rightGroup"><a href="#none"  onclick="doRegister();" class="btnStyle01">创建新广告</a></div>
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
$(document).ready(function () {
	$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "useYn", "${vo.useYn }", "selectbox", "");
	$.fnGetCodeSelectAjax("sGb=AD_LOCATION&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "searchType", "${vo.searchType}", "selectbox", "");
	$("#searchValue").keypress(function(e) {
	    if (e.keyCode == 13) {
	    	e.preventDefault();
	    	fnSearch();
	    }
	});	
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
		$("#searchValue").val("");
		$("[name=searchType]").val("search_sqprdNm");

		$("#listForm").prop("method", "get");
		$("#listForm").attr('action', '<c:url value="./list.do"/>').submit();
	}
	//등록페이지 이동
	function doRegister(){
		$("#listForm").prop("method", "post");
		$("#listForm").attr('action', '<c:url value="./registerForm.do"/>').submit();
	}
	// 상세페이지 이동
	function detail(seq){
		$("#sqprdSeqNo").val(seq);
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

function popup(seq) {
	var url = "./preView.do?id="+seq

	window.open(url, "ViewImg", "width=300"+", height=300"+", top=300"+", left=300");
}
</script>
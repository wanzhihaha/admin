<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>


<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="post">
<input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
<input type="hidden" id="sqprdSeqNo" name="id"/>
<%--<input type="hidden" id="sqprdCtgry" name="sqprdCtgry" value="<c:out value="${vo.sqprdCtgry }"/>">--%>
<%--<input type="hidden" id="sqprdCtgryNm" name="sqprdCtgryNm" value="<c:out value="${vo.sqprdCtgryNm }"/>">--%>

<section>
	<div class="title"><h3>车队信息管理</h3></div>
	<div class="Cont_place">
	<article>
	<div class="inputUI_simple">	
	<table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
		<caption>상태, 답변 상태, 기간설정, 직접검색 영역</caption>

		<tr>
			<th scope="row">搜索</th>
			<td> 
				<div class="select-inner">
					<select name="searchType">
						<option value="0" <c:if test="${vo.searchType eq '0'}">selected="selected"</c:if>>全部</option>
						<%-- <option value="search_goodsCode" <c:if test="${vo.searchType eq 'search_goodsCode' }">selected="selected"</c:if>>Product Code</option> --%>
						<option value="10" <c:if test="${vo.searchType eq '10' }">selected="selected"</c:if>>公司动态</option>
						<option value="20" <c:if test="${vo.searchType eq '20' }">selected="selected"</c:if>>行业资讯</option>
						<option value="30" <c:if test="${vo.searchType eq '30' }">selected="selected"</c:if>>海运资讯</option>
						<option value="40" <c:if test="${vo.searchType eq '40' }">selected="selected"</c:if>>空运资讯</option>
					</select>
				</div>
				<input type="text" placeholder="Please enter your search term." class="inp-field widS mglS" id="searchValue" name="searchValue" value="<c:out value="${vo.searchValue }" escapeXml="false"/>" />
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
		<%--<tr>
			<th >批量上传(word):</th>
			<td style="display: flex;">
				<div class="btn-module floatR">
					<div class="rightGroup" style="width: 214px;display: flex;align-items: center">
						<input type="file" name="file"  onchange="UploadFile()"/>
					</div>
				</div>
			</td>
		</tr>--%>
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
			<th scope="col">排序</th>
			<!-- <th scope="col">Image</th> -->
			<th scope="col">车辆类型</th>
			<th scope="col">置顶类型</th>
<%--			<th scope="col">文章标签</th>--%>
			<th scope="col">车队信息</th>
			<th scope="col">状态</th>
			<th scope="col">浏览量</th>
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
<%--						<c:out value="${(totalCount - ((vo.page-1) * vo.rowPerPage)) - stat.index}" />--%>
						<c:out value="${(totalCount - (totalCount-stat.index-1))+((vo.page-1) * vo.rowPerPage) }" />
					</span>
				</td>
				<td>
					<input type="text" name="listSortOrder" class="inp-field widM2" value="<c:out value="${list.ordb}" />" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3" />
					<input type="hidden" name="listblogSeq" value="<c:out value="${list.id}" />" />
				</td>
				<td><c:out value="${list.articleType }"/></td>
				<td><c:out value="${list.stickType }"/></td>
<%--				<td><c:out value="${list.articleTag }"/></td>--%>
				<td>
					<a href="javascript:;" onclick="detail('<c:out value='${list.id}'/>');"><c:out value='${list.articleTitle}' escapeXml="false"/></a>
				</td>
				<td><c:out value="${list.useYn }"/></td>
				<td><c:out value="${list.pageView }"/></td>
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
		<div class="rightGroup"><a href="#none"  onclick="doRegister();" class="btnStyle01">创建新文章</a></div>
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
	function UploadFile() {
		$("#listForm").prop("method", "post");
		$("#listForm").attr('action', '<c:url value="./upload.do"/>').attr('enctype','multipart/form-data').submit();
	}
</script>
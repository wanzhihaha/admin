<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>


<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="post">
<input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
<input type="hidden" id="productSeqNo" name="productSeqNo"/>
<input type="hidden" id="productCtgry" name="productCtgry" value="<c:out value="${vo.productCtgry }"/>">
<input type="hidden" id="productCtgryNm" name="productCtgryNm" value="<c:out value="${vo.productCtgryNm }"/>">

<section>
	<div class="title"><h3><c:out value="${vo.productCtgryNm}"/></h3></div>
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
				<input type="text" placeholder="请输入搜索产品名称" class="inp-field widS mglS" id="searchValue" name="searchValue" value="<c:out value="${vo.searchValue }" escapeXml="false"/>" />

				<div class="select-inner">
<%--					<select name="useYn" id="useYn">--%>
<%--						<option value="">状态（全部）</option>--%>
<%--					</select>--%>
					<select name="searchType" style="display: none">
						<option value="search_productNm" >产品名称</option>
						<%-- <option value="search_goodsCode" <c:if test="${vo.searchType eq 'search_goodsCode' }">selected="selected"</c:if>>Product Code</option> --%>
<%--						<option value="search_InsPersonNm" <c:if test="${vo.searchType eq 'search_InsPersonNm' }">selected="selected"</c:if>>创建者</option>--%>
					</select>
				</div>
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
			<th scope="col">排序</th>
			<!-- <th scope="col">Image</th> -->
			<th scope="col">产品名称</th>
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
						<c:out value="${stat.index + 1}" />
					</span>
				</td>
				<td>
					<input type="text" name="listOrdb" class="inp-field widM2" value="<c:out value="${list.ordb}" />" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3" />
					<input type="hidden" name="listproductSeqNo" value="<c:out value="${list.productSeqNo}" />" />
				</td>
				<%-- <td style="text-align: center;">
					<c:if test="${! empty list.pcListImgOrgFileNm}">
						<a href="javascript:;"  onclick="detail(<c:out value='${list.productSeqNo}'/>);">
							<img src="<blabProperty:value key="system.admin.path"/>/goodsImgView.do?productSeqNo=<c:out value='${list.productSeqNo}'/>&imgKinds=pcList&productCtgry=<c:out value='${vo.productCtgry }'/>" width="100" alt="<c:out value="${list.pcListImgAlt }" escapeXml="false"/>" /><br />
						</a>
					</c:if>
                </td> --%>
				<td>
					<a href="javascript:;"  onclick="detail(<c:out value='${list.productSeqNo}'/>);"><c:out value="${list.productNm } " escapeXml="false"/></a>
				</td>
				
				<td>
					<c:out value="${list.useYnNm }" escapeXml="false"/>
				</td>
				<td>
					<fmt:formatNumber value="${list.srchCnt }" pattern="#,###,###"/>
				</td>
				<td>
					<c:out value="${list.insPersonNm }" escapeXml="false"/>
				</td>
				<td>
					<c:out value="${list.insDtm }"/>
				</td>
				<td>
					<c:out value="${list.updPersonNm }" escapeXml="false"/>
				</td>
				<td>
					<c:out value="${list.updDtm }"/>
				</td>
			</tr>
		</c:forEach>
		<c:if test="${fn:length(list) < 1 }">
			<tr>
				<td colspan="9" class="blank">没有数据</td>
			</tr>
		</c:if>	

		</tbody>
	</table>

	<div class="btn-module mgtSM">
		<div class="leftGroup"><a href="#none"  onclick="doSortSave();" class="btnStyle01">自定义排序</a></div>
		<div class="rightGroup"><a href="#none"  onclick="doRegister();" class="btnStyle01">创建新产品</a></div>
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
		$("[name=searchType]").val("search_productNm");
		$("[name=useYn]").val("");

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
		$("#productSeqNo").val(seq);
		$("#listForm").prop("method", "post");
		$("#listForm").attr('action', '<c:url value="./detail.do"/>').submit();
	}
	
	// 정렬순서 저장
	function doSortSave() {
		if(confirm("确定保存排序吗？")) {
			$("#listForm").prop("method", "post");
			$("#listForm").attr('action', '<c:url value="./doSortOrder.do"/>').submit();
		}
		return false;
	}
</script>
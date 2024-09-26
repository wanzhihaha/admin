<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>


<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="post">
	<input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
	<input type="hidden" id="id" name="id" />

	<section>
		<div class="title"><h3>特价舱产品</h3></div>
		<div class="Cont_place">
			<article>
				<div class="inputUI_simple">
					<table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
						<caption>상태, 답변 상태, 기간설정, 직접검색 영역</caption>
						<tr>
							<th scope="row">搜索</th>
							<td>
								<input type="text" placeholder="请输入搜索名称"  id="productName" name="productName" value="<c:out value="${vo.productName }" escapeXml="false"/>" />
							</td>
							<td>
								<div class="btn-module floatR">
									<div class="rightGroup">
										<a href="javascript:;" class="search" onclick="fnSearch();" id="btnSearch">搜索</a>
										<a href="javascript:;" class="refresh" onclick="resetClick();" id="btnReset">重置</a>
									</div>
								</div>
							</td>
<%--							<td>--%>
<%--								<div class="btn-module floatR">--%>
<%--									<div class="rightGroup">--%>
<%--										<a href="javascript:;" class="search" onclick="excelDownLoad();" style="width:110px;" id="ExcelDownload">导出Excel</a>--%>
<%--									</div>--%>
<%--								</div>--%>
<%--							</td>--%>
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
<%--					<caption>NO</caption>--%>
<%--					<colgroup>--%>
<%--						<col width="3%" />--%>
<%--						<col width="5%" />--%>
<%--						<col width="6%" />--%>
<%--						<col width="3%" />--%>
<%--						<col width="6%" />--%>
<%--						<col width="6%" />--%>
<%--						<col width="5%" />--%>
<%--						<col width="6%" />--%>
<%--					</colgroup>--%>
					<thead>
					<tr>
						<th scope="col">序号</th>
						<th scope="col">排序</th>
						<th scope="col">特价舱产品类型</th>
						<th scope="col">图片</th>
						<th scope="col">创建人</th>
						<th scope="col">创建时间</th>
						<th scope="col">更新人</th>
						<th scope="col">更新时间</th>
						<th scope="col">状态</th>

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
								<input type="text" name="listOrdb" class="inp-field widM2"
									   value="<c:out value="${list.ordb}" />"
									   onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3"/>

								<input type="hidden" name="ids"
									   value="<c:out value="${list.id}" />"/>
							</td>
							<td>
								<a href="javascript:;" onclick="detail('<c:out value='${list.id}'/>');"><c:out value='${list.productName}' escapeXml="false"/></a>
							</td>
							<td style="text-align: center;">
								<c:if test="${! empty list.listImgOrgFileNm}">
									<img src="<blabProperty:value key="system.admin.path"/>/bargainProduct/imgView.do?id=<c:out value='${list.id }'/>"
										 style="max-width: 100px; vertical-align: middle;">
								</c:if>
							</td>
                            <td>
                                <c:out value="${list.insPersonId }" escapeXml="false"/>
                            </td>
                            <td>
							<fmt:formatDate value="${list.insDtm }" pattern="yyyy-MM-dd"/>
                            </td>
                            <td>
                                <c:out value="${list.updPersonId }" escapeXml="false"/>
                            </td>
                            <td>
								<fmt:formatDate value="${list.updDtm }" pattern="yyyy-MM-dd"/>
                            </td>
							<td>
								<c:out value="${list.useYn }" escapeXml="false"/>
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
					<div class="leftGroup"><a href="#none" onclick="doSortSave();" class="btnStyle01">排序</a></div>
					<div class="rightGroup"><a href="#none"  onclick="doRegister();" class="btnStyle01">新增</a></div>
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
<a id="excelDownd" style="display:none"></a>

<script type="text/javascript">
	$(document).ready(function () {

		//$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "useYn", "${vo.useYn }", "selectbox", "");
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
		$("#productName").val("");
		$("#listForm").prop("method", "get");
		$("#listForm").attr('action', '<c:url value="./list.do"/>').submit();
	}
	//등록페이지 이동
	function doRegister(){
		$("#listForm").prop("method", "post");
		$("#listForm").attr('action', '<c:url value="./registerForm.do"/>').submit();
	}
	// 상세페이지 이동
	function detail(id){
		$("#id").val(id);
		$("#listForm").prop("method", "post");
		$("#listForm").attr('action', '<c:url value="./detail.do"/>').submit();
	}

	function doSortSave() {
		if (confirm("你确定保存排序吗?")) {
			$("#listForm").prop("method", "post");
			$("#listForm").attr('action', '<c:url value="./doSortOrder.do"/>').submit();
		}
		return false;
	}

</script>
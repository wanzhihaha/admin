<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>
<% pageContext.setAttribute("crlf", "\r\n"); %>
<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="id" name="id" value="<c:out value="${detail.id }" />">

<section>	
	<article>
	<div class="Cont_place">
	<table class="bd-form" summary="등록, 수정하는 영역 입니다.">
			<caption>열람,내용</caption>
			<colgroup>
				<col width="130px" />
				<col width="" />
			</colgroup>
			
			<%@ include file="/WEB-INF/jsp/common/seo/seoDetailNews.jsp" %>
			
			<tr>
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">Product Information</th>
			</tr>
			<tr>
				<th scope="row">关键词</th>
				<td>
					<c:out value="${detail.terminologyName }" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">内容</th>
				<td>
					<c:out value="${detail.description }" escapeXml="false"/>
				</td>
			</tr>

		<tr>
			<th scope="row">Status</th>
			<td>
				<c:out value="${detail.useYn}" />
			</td>
		</tr>
			<tbody>
			
			</tbody>
			
		</table>
		
		<div class="btn-module mgtS">
			<div class="leftGroup">
				<a href="javascript:;" id="btnList" class="btnStyle01" >列表</a>
			</div>		
			<div class="rightGroup">
				<a href="javascript:;" id="btnUpdate" class="btnStyle01" >修改</a>
				<a href="javascript:;" id="btnDelete" class="btnStyle02">删除</a>
			</div>
		</div>	
	</div>
	</article>
</section>
</form>
					
					
<script type="text/javascript">
	
		
	$("#btnList").click(function() {
		$("#detailForm").prop("method", "get");
		$("#detailForm").attr('action', '<c:url value="./list.do"/>').submit();
	});
	
	$("#btnUpdate").click(function() {
		$("#detailForm").prop("method", "post");
		$("#detailForm").attr('action', '<c:url value="./updateForm.do"/>').submit();
	});
			
	$("#btnDelete").click(function() {
		if(confirm("Are you sure you want to delete it?")) {
			$("#detailForm").prop("method", "post");
			$("#detailForm").attr('action', '<c:url value="./doDelete.do"/>').submit();
		}
	});
	
	function detailMoveFn(SeqNo, ctgry) {
		$("#sqprdSeqNo").val(SeqNo);
		$("#sqprdCtgry").val(ctgry);
		$("#detailForm").prop("method", "post");
		$("#detailForm").attr('action', '<c:url value="./detail.do"/>').submit();
	}

	
</script>		
							
							
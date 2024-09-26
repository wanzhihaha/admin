<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="tosSeqNo" name="tosSeqNo" value="<c:out value="${detail.tosSeqNo }" />">




<section>	
	<div class="title"><h3>Terms of Service Detail Information</h3></div>
	<article>
	<div class="Cont_place">
	<table class="bd-form" summary="등록, 수정하는 영역 입니다.">
			<caption>열람,내용</caption>
			<colgroup>
				<col width="130px" />
				<col width="" />
			</colgroup>
			<tr>
				<th scope="row">Sort *</th>
				<td>
					<c:out value="${detail.tosCcdNm }" />
				</td>
			</tr>	
			<tr>
				<th scope="row">Title *</th>
				<td>
					<c:out value="${detail.titleNm }" escapeXml="false"/>
				</td>
			</tr>	
			<tr>
				<td colspan="2">
					<div>
					<c:out value="${detail.detlInfo }" escapeXml="false"/>
					</div>
				</td>
			</tr>
			<% pageContext.setAttribute("crlf", "\r\n"); %>
			<tr>
				<th scope="row">Memo</th>
				<td>
					${fn:replace(detail.memo, crlf, "<br>")}
				</td>
			</tr>
			<%-- <tr>
				<th scope="row">Require<br>Status *</th>
				<td>
					<c:out value="${detail.reqStsNm }" />
				</td>
			</tr>	 --%>
			<tr>
				<th scope="row">Version *</th>
				<td>
					<c:out value="${detail.tosVer }" />
				</td>
			</tr>	
			<tr>
				<th scope="row">Registrar</th>
				<td>
					<c:out value="${detail.insPersonNm}" />
				</td>
			</tr>
			<tr>
				<th scope="row">Registration<br>Date</th>
				<td>
					<c:out value="${detail.insDtm}" />
				</td>
			</tr>
			<tr>
				<th scope="row">Status *</th>
				<td>
					<c:out value="${detail.useYnNm}" />
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
	$(document).ready(function(){
		
		$("#btnList").click(function() {
			$("#detailForm").prop("action","list.do");
			$("#detailForm").prop("method", "get");
			$("#detailForm").submit();
		});
		
		$("#btnUpdate").click(function() {
			$("#detailForm").prop("action","updateForm.do");
			$("#detailForm").prop("method", "post");
			$("#detailForm").submit();
		});
				
		$("#btnDelete").click(function() {
			if(confirm("Are you sure you want to delete it?")) {
				$("#detailForm").prop("action","doDelete.do");
				$("#detailForm").prop("method", "post");
				$("#detailForm").submit();
			}
		});
	});
	
</script>		
							
							
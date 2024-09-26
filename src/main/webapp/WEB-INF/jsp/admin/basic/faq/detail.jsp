<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="faqSeq" name="faqSeq" value="<c:out value="${detail.faqSeq }" />">


<section>	
	<div class="title"><h3>FAQ Detail Information</h3></div>
	<article>
	<div class="Cont_place">
	<table class="bd-form" summary="등록, 수정하는 영역 입니다.">
			<caption>열람,내용</caption>
			<colgroup>
				<col width="200px" />
				<col width="" />
			</colgroup>			
			<tr>
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">FAQ Information</th>
			</tr>
			<tr>
				<th scope="row">Sort</th>
				<td>
					<c:out value="${detail.faqCcdNm }" />
				</td>
			</tr>	
			<tr>
				<th scope="row">Title</th>
				<td>
					<c:out value="${detail.titleNm }" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">FAQ Details Information</th>
				<td>
					<div class="textCont">
						<c:out value="${detail.faqDetlContent }" escapeXml="false"/>		
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">Views</th>
				<td>
					<fmt:formatNumber value="${detail.srchCnt }" pattern="#,###,###"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Registrar</th>
				<td>
					<c:out value="${detail.insPersonNm}" />
				</td>
			</tr>
			<tr>
				<th scope="row">Registration Date</th>
				<td>
					<c:out value="${detail.insDtm}" />
				</td>
			</tr>
			<tr>
				<th scope="row">Status</th>
				<td>
					<c:out value="${detail.useYnNm}" />
				</td>
			</tr>						
			<tbody>
			
			</tbody>
			
		</table>
		
		<div class="btn-module mgtS">
			<div class="leftGroup">
				<a href="javascript:;" id="btnList" class="btnStyle01" >List</a>
			</div>		
			<div class="rightGroup">
				<a href="javascript:;" id="btnUpdate" class="btnStyle01" >Modify</a> 
				<a href="javascript:;" id="btnDelete" class="btnStyle02">Delete</a>
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
				$("#detailForm").prop("method", "post");
				$("#detailForm").prop("action","doDelete.do");
				$("#detailForm").submit();
			}
		});
	});
	
</script>		
							
							
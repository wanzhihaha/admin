<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="popSeqNo" name="popSeqNo" value="<c:out value="${detail.popSeqNo }" />">




<section>	
	<div class="title"><h3>Popup Management Detail Information</h3></div>
	<article>
	<div class="Cont_place">
	<table class="bd-form" summary="등록, 수정하는 영역 입니다.">
			<caption>열람,내용</caption>
			<colgroup>
				<col width="150px" />
				<col width="" />
			</colgroup>
			<tr>
				<th scope="row">Title</th>
				<td>
					<c:out value="${detail.titleNm }" escapeXml="false"/>
				</td>
			</tr>	
			<tr>
				<th scope="row">Period</th>
				<td>
					<c:if test="${!empty detail.peridStatDate && !empty detail.peridEndDate}">
						<c:out value="${detail.peridStatDate }" /> ~ <c:out value="${detail.peridEndDate }" />
					</c:if>
				</td>
			</tr>	
			<tr>
				<th scope="row">Link URL</th>
				<td>
					<c:out value="${detail.linkUrl }" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">PC Popup Sort</th>
				<td>
					<c:out value="${detail.pcPopCcdNm }" />
				</td>
			</tr>
			<tr>
				<th scope="row">PC Popup Size * <br>(000*000)</th>
				<td>
					<span>Width : </span><c:out value="${detail.pcPopSizeWdt }" /> 
					<span>/</span>
					<span>Height : </span><c:out value="${detail.pcPopSizeHgt }" />
				</td>
			</tr>
			<tr>
				<th scope="row">PC Popup Location</th>
				<td>
					<span style="padding-right: 42px;">Top : </span><c:out value="${detail.pcPopLocTop }" />
					<span>/</span>
					<span style="padding-right: 47px;">Left : </span><c:out value="${detail.pcPopLocLeft }" />
				</td>
			</tr>
			<tr>
				<th scope="row">PC Popup <br> Image</th>
				<td>
					<c:if test="${!empty detail.pcPopImgOrgFileNm}">
						<img alt="<c:out value="${detail.pcPopImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/popupImgView.do?popSeqNo=<c:out value='${detail.popSeqNo}'/>&imgKinds=pcPop" style="max-width: 100px; vertical-align: middle;">
					</c:if>		
				</td>
			</tr>
			<tr>
				<th scope="row">Mobile Popup Size * <br>(000*000)</th>
				<td>
					<span>Width : </span><c:out value="${detail.mobilePopSizeWdt }" />
					<span>/</span>
					<span>Height : </span><c:out value="${detail.mobilePopSizeHgt }" />
				</td>
			</tr>
			<tr>
				<th scope="row">Mobile Popup <br>Image</th>
				<td>
					<c:if test="${!empty detail.mobilePopImgOrgFileNm}">
						<img alt="<c:out value="${detail.mobilePopImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/popupImgView.do?popSeqNo=<c:out value='${detail.popSeqNo}'/>&imgKinds=mobilePop" style="max-width: 100px; vertical-align: middle;">
					</c:if>		
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
				$("#detailForm").prop("action","doDelete.do");
				$("#detailForm").prop("method", "post");
				$("#detailForm").submit();
			}
		});
	});
	
</script>		
							
							
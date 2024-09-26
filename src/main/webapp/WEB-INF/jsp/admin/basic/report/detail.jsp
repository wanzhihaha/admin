<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="rptSeqNo" name="rptSeqNo" value="<c:out value="${detail.rptSeqNo }" />">


<section>	
	<div class="title"><h3>Report Detail Information</h3></div>
	<article>
	<div class="Cont_place">
	<table class="bd-form" summary="등록, 수정하는 영역 입니다.">
			<caption>열람,내용</caption>
			<colgroup>
				<col width="130px" />
				<col width="" />
			</colgroup>
			
			<%@ include file="/WEB-INF/jsp/common/seo/seoDetail.jsp" %>
			
			<tr>
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">Report Information</th>
			</tr>
			<tr>
				<th scope="row">Sort *</th>
				<td>
					<c:out value="${detail.rptCcdNm }" />
				</td>
			</tr>	
			<tr>
				<th scope="row">Title *</th>
				<td>
					<c:out value="${detail.titleNm }" escapeXml="false"/>
				</td>
			</tr>	
			<% pageContext.setAttribute("crlf", "\r\n"); %>
			<tr>
				<th scope="row">Summary<br>Information</th>
				<td>
					${fn:replace(detail.summaryInfo, crlf, "<br>")}
				</td>
			</tr>
			<tr>
				<th scope="row">PC List Image</th>
				<td>
					<c:if test="${!empty detail.pcListImgOrgFileNm}">
						<img alt="<c:out value="${detail.pcListImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/reportImgView.do?rptSeqNo=<c:out value='${detail.rptSeqNo}'/>&imgKinds=pcList" style="max-width: 100px; vertical-align: middle;">
					</c:if>		
				</td>
			</tr>
			<tr>
				<th scope="row">PC Detail Image</th>
				<td>
					<c:if test="${!empty detail.pcDetlImgOrgFileNm}">
						<img alt="<c:out value="${detail.pcDetlImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/reportImgView.do?rptSeqNo=<c:out value='${detail.rptSeqNo}'/>&imgKinds=pcDetail" style="max-width: 100px; vertical-align: middle;">
					</c:if>		
				</td>
			</tr>
			<tr>
				<th scope="row">Mobile List Image</th>
				<td>
					<c:if test="${!empty detail.mobileListImgOrgFileNm}">
						<img alt="<c:out value="${detail.mobileListImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/reportImgView.do?rptSeqNo=<c:out value='${detail.rptSeqNo}'/>&imgKinds=mobileList" style="max-width: 100px; vertical-align: middle;">
					</c:if>		
				</td>
			</tr>
			<tr>
				<th scope="row">Mobile Detail Image</th>
				<td>
					<c:if test="${!empty detail.mobileDetlImgOrgFileNm}">
						<img alt="<c:out value="${detail.mobileDetlImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/reportImgView.do?rptSeqNo=<c:out value='${detail.rptSeqNo}'/>&imgKinds=mobileDetail" style="max-width: 100px; vertical-align: middle;">
					</c:if>		
				</td>
			</tr>
			<tr>
				<th scope="row">Details<br>Information</th>
				<td>
					<c:out value="${detail.detlInfo }" escapeXml="false"/>
				</td>
			</tr>
			<c:forEach var="atthFile" items="${attachFileList }" varStatus="stat">
			<tr>
				<c:if test="${stat.index == 0 }">
					<th scope="row" rowspan="${fn:length(attachFileList)}">Attach File</th>
				</c:if>
				<td>
					<a href='<blabProperty:value key="system.admin.path"/>/fileDown.do?contentsSeqNo=${atthFile.contentsSeqNo }&contentsCcd=${atthFile.contentsCcd }&ordb=${atthFile.ordb }'style="color: blue;">
						<c:out value="${atthFile.atchOrgFileNm}" />
					</a>
				</td>
			</tr>
			</c:forEach>
			
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
							
							
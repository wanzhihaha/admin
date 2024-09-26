<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>
<% pageContext.setAttribute("crlf", "\r\n"); %>
<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="videoSeqNo" name="videoSeqNo" value="<c:out value="${detail.videoSeqNo }" />">




<section>	
	<div class="title"><h3>Video Detail Information</h3></div>
	<article>
	<div class="Cont_place">
	<table class="bd-form" summary="등록, 수정하는 영역 입니다.">
			<caption>열람,내용</caption>
			<colgroup>
				<col width="200px" />
				<col width="" />
			</colgroup>
			
			<%@ include file="/WEB-INF/jsp/common/seo/seoDetail.jsp" %>
			<tr>
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">Video Information</th>
			</tr>
			<tr>
				<th scope="row">Title</th>
				<td>
					<c:out value="${detail.titleNm }" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Summary<br/>Information</th>
				<td>
					<div class="textCont" style="white-space: pre-line;">
						${fn:replace(detail.summaryInfo, crlf, "<br>")}		
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">PC List Image</th>
				<td>
					<c:if test="${!empty detail.pcListImgPath }">
						<img alt="<c:out value="${detail.pcListImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/videoImgView.do?videoSeqNo=${detail.videoSeqNo }&imgKinds=pcList" style="max-width: 100px; vertical-align: middle;">
					</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row">PC Detail Image</th>
				<td>
					<c:if test="${!empty detail.pcDetlImgPath }">
						<img alt="<c:out value="${detail.pcDetlImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/videoImgView.do?videoSeqNo=${detail.videoSeqNo }&imgKinds=pcDetail" style="max-width: 100px; vertical-align: middle;">
					</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row">Mobile List Image</th>
				<td>
					<c:if test="${!empty detail.mobileListImgPath }">
						<img alt="<c:out value="${detail.mobileListImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/videoImgView.do?videoSeqNo=${detail.videoSeqNo }&imgKinds=mobileList" style="max-width: 100px; vertical-align: middle;">
					</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row">Mobile Detail Image</th>
				<td>
					<c:if test="${!empty detail.mobileDetlImgPath }">
						<img alt="<c:out value="${detail.mobileDetlImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/videoImgView.do?videoSeqNo=${detail.videoSeqNo }&imgKinds=mobileDetail" style="max-width: 100px; vertical-align: middle;">
					</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row">Contents</th>
				<td>
					<c:choose>
						<c:when test="${detail.contentsType eq 'HTML'}">
							<c:out value="${detail.contents }" escapeXml="false"/>
						</c:when>
						<c:otherwise>
							<div id="linkRegularExpression">
								<pre style="white-space: pre-line;"><c:out value="${detail.contents }" escapeXml="false"/></pre>
							</div>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>						
			<tr>
				<th scope="row">Details<br/>Imformation</th>
				<td>
					<c:out value="${detail.detlInfo}" escapeXml="false"/>
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
			$("#detailForm").prop("method", "get");
			$("#detailForm").attr("action","<c:url value ='./list.do'/>").submit();
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
		
	});
	
	// 링크 정규식
	
	/* if("<c:out value='${detail.contentsType}'/>" == "LINK"){
		var originalData = $.trim($("#linkRegularExpression").html());

		var expUrl = /(((http(s)?:\/\/)\S+(\.[^(\n|\t|\s,)]+)+)|((http(s)?:\/\/)?(([a-zA-z\-_]+[0-9]*)|([0-9]*[a-zA-z\-_]+)){2,}(\.[^(\n|\t|\s,)]+)+))+/gi;

	 	var linkValue = '<c:out value="${detail.contents}"/>';
	 	var aTagData = "<a href="+linkValue+" target='_blank' rel='noreferrer noopener' style='color: blue;'><c:out value='${detail.contents}'/></a>"
	 	
		if(expUrl.test(originalData)){
			$("#linkRegularExpression").html(aTagData);
		}
	} */
</script>		
							
							
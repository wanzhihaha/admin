<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>
<% pageContext.setAttribute("crlf", "\r\n"); %>
<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="evtSeqNo" name="evtSeqNo" value="<c:out value="${detail.evtSeqNo }" />">


<section>	
	<div class="title"><h3>Event Detail Information</h3></div>
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
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">Event Information</th>
			</tr>
			<tr>
				<th scope="row">sort</th>
				<td>
					<c:out value="${detail.evtCcdNm }" />
				</td>
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
					<div class="textCont">
						${fn:replace(detail.summaryInfo, crlf, "<br>")}
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">PC List Image</th>
				<td>
					<c:if test="${!empty detail.pcListImgPath }">
						<img alt="<c:out value="${detail.pcListImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/eventImgView.do?evtSeqNo=<c:out value='${detail.evtSeqNo }'/>&imgKinds=pcList" style="max-width: 100px; vertical-align: middle;">
					</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row">PC Detail Image</th>
				<td>
					<c:if test="${!empty detail.pcDetlImgPath }">
						<img alt="<c:out value="${detail.pcDetlImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/eventImgView.do?evtSeqNo=<c:out value='${detail.evtSeqNo }'/>&imgKinds=pcDetail" style="max-width: 100px; vertical-align: middle;">
					</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row">Mobile List Image</th>
				<td>
					<c:if test="${!empty detail.mobileListImgPath }">
						<img alt="<c:out value="${detail.mobileListImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/eventImgView.do?evtSeqNo=<c:out value='${detail.evtSeqNo }'/>&imgKinds=mobileList" style="max-width: 100px; vertical-align: middle;">
					</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row">Mobile Detail Image</th>
				<td>
					<c:if test="${!empty detail.mobileDetlImgPath }">				
						<img alt="<c:out value="${detail.mobileDetlImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/eventImgView.do?evtSeqNo=<c:out value='${detail.evtSeqNo }'/>&imgKinds=mobileDetail" style="max-width: 100px; vertical-align: middle;">
					</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row">Event Details<br/>Imformation</th>
				<td>
					<c:out value="${detail.evtDetlContent}" escapeXml="false"/>
				</td>
			</tr>						
			<tr>
				<th scope="row">Event Duration</th>
				<td>
					<c:out value="${detail.evtStatDtm}" /> ~ <c:out value="${detail.evtEndDtm}" />
				</td>
			</tr>	
			<tr>
				<th scope="row">New Tab Link<br/>Presence</th>
				<td>
					<c:out value="${detail.newWindowLinkYnNm}"/>
					<c:if test="${detail.newWindowLinkYnNm eq 'Use' }">
						<div id="linkRegularExpression">
							<c:out value="${detail.newLink}" escapeXml="false"/>
						</div>
					</c:if>					
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
	var originalData = $.trim($("#linkRegularExpression").html());
	console.log(originalData);
 	var expUrl = /(http|https):\/\/((\w+)[.])+(asia|biz|cc|cn|com|de|eu|in|info|jobs|jp|kr|mobi|mx|name|net|nz|org|travel|tv|tw|uk|us)(\/(\w*))*$/i;
 	var aTagData = "<a href='${detail.newLink}' target='_blank' style='color: blue;'><c:out value='${detail.newLink}'/></a>"
 	
	if(expUrl.test(originalData)){
		$("#linkRegularExpression").html(aTagData);
	}
</script>		
							
							
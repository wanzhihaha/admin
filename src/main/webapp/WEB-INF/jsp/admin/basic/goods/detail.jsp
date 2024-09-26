<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>
<% pageContext.setAttribute("crlf", "\r\n"); %>
<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="sqprdCtgry" name="sqprdCtgry" value="<c:out value="${vo.sqprdCtgry }"/>">
<input type="hidden" id="sqprdCtgryNm" name="sqprdCtgryNm" value="<c:out value="${vo.sqprdCtgryNm }"/>">
<input type="hidden" id="sqprdSeqNo" name="sqprdSeqNo" value="<c:out value="${detail.sqprdSeqNo }" />">


<section>	
	<div class="title"><h3><c:out value="${detail.sqprdCtgryNm}"/> Detail</h3></div>
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
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">Product Information</th>
			</tr>
			<tr>
				<th scope="row">Product Name</th>
				<td>
					<c:out value="${detail.sqprdNm }" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Product English<br/>Name</th>
				<td>
					<c:out value="${detail.sqprdEngNm }" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Product Manager</th>
				<td>
					<c:out value="${detail.sqprdAttnId }" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Product NAVY</th>
				<td>
					<c:out value="${detail.sqprdNavy }" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Service Validity</th>
				<td>
					<c:out value="${detail.svcValidStatDate }" /> ~ <c:out value="${detail.svcValidEndDate }" />
				</td>
			</tr>
			<tr>
				<th scope="row">Product Summary<br>Information</th>
				<td>
					${fn:replace(detail.sqprdSummaryInfo, crlf, "<br>")}
				</td>
			</tr>
			<%-- <tr>
				<th scope="row">PC List Image</th>
				<td>
					<c:if test="${! empty detail.pcListImgOrgFileNm}">
						<img alt="<c:out value="${detail.pcListImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/goodsImgView.do?sqprdSeqNo=<c:out value='${detail.sqprdSeqNo }'/>&imgKinds=pcList&sqprdCtgry=<c:out value='${vo.sqprdCtgry }'/>" style="max-width: 100px; vertical-align: middle;">
					</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row">PC Detail Image</th>
				<td>
					<c:if test="${! empty detail.pcDetlImgOrgFileNm}">
						<img alt="<c:out value="${detail.pcDetlImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/goodsImgView.do?sqprdSeqNo=<c:out value='${detail.sqprdSeqNo }'/>&imgKinds=pcDetail&sqprdCtgry=<c:out value='${vo.sqprdCtgry }'/>" style="max-width: 100px; vertical-align: middle;">
					</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row">Mobile List Image</th>
				<td>
					<c:if test="${! empty detail.mobileListImgOrgFileNm}">
						<img alt="<c:out value="${detail.mobileListImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/goodsImgView.do?sqprdSeqNo=<c:out value='${detail.sqprdSeqNo }'/>&imgKinds=mobileList&sqprdCtgry=<c:out value='${vo.sqprdCtgry }'/>" style="max-width: 100px; vertical-align: middle;">
					</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row">Mobile Detail Image</th>
				<td>
					<c:if test="${! empty detail.mobileDetlImgOrgFileNm}">
						<img alt="<c:out value="${detail.mobileDetlImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/goodsImgView.do?sqprdSeqNo=<c:out value='${detail.sqprdSeqNo }'/>&imgKinds=mobileDetail&sqprdCtgry=<c:out value='${vo.sqprdCtgry }'/>" style="max-width: 100px; vertical-align: middle;">
					</c:if>
				</td>
			</tr> --%>
			<tr>
				<th scope="row">Product Notice<br/>Contents</th>
				<td>
					<c:out value="${detail.sqprdNotiContents }" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Product Details<br/>Information</th>
				<td>
					<c:out value="${detail.sqprdDetlInfo }" escapeXml="false"/>
				</td>
			</tr>
			<%-- <c:forEach var="atthFile" items="${attachFileList }" varStatus="stat">
			<tr>
				<c:if test="${stat.index == 0 }">
				<th scope="row" rowspan="${fn:length(attachFileList)}">Attach File</th>
				</c:if>
				<td>
					<a href='<blabProperty:value key="system.admin.path"/>/fileDown.do?contentsSeqNo=<c:out value="${atthFile.contentsSeqNo }"/>&contentsCcd=<c:out value="${atthFile.contentsCcd }"/>&ordb=<c:out value="${atthFile.ordb }"/>' style="color: blue;">
						<c:out value="${atthFile.atchOrgFileNm}" />
					</a>
				</td>
			</tr>
			</c:forEach> --%>
			
			<tr>
				<th scope="row">POL</th>
				<td>
					<c:out value="${detail.depPortCd } " escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">POD</th>
				<td>
					<c:out value="${detail.arrpPotCd }" escapeXml="false"/>
				</td>
			</tr>
			<%-- <tr>
				<th scope="row">Ship-to</th>
				<td>
					<c:out value="${detail.shipTo }" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Product Option 1</th>
				<td>
					<c:out value="${detail.sqprdOpt1 }" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Product Option 2</th>
				<td>
					<c:out value="${detail.sqprdOpt2 }" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Product Option 3</th>
				<td>
					<c:out value="${detail.sqprdOpt3 }" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Product Option 4</th>
				<td>
					<c:out value="${detail.sqprdOpt4 }" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Product Option 5</th>
				<td>
					<c:out value="${detail.sqprdOpt5 }" escapeXml="false"/>
				</td>
			</tr> --%>
			<tr>
				<th scope="row">Related Product<br/>(3limitations)</th>
				<td>
					<c:forEach var="list" items="${detail.goodsList}" varStatus="stat">
						<c:choose>
							<c:when test="${fn:length(detail.goodsList) == 1}">
								<a href="javascript:;" onclick="detailMoveFn('<c:out value="${list.sqprdSeqNo }"/>', '<c:out value="${list.sqprdCtgry }"/>');" style="color: blue;">
									<c:out value="${list.sqprdNm}" escapeXml="false"/>
								</a>
							</c:when>
							<c:otherwise>
								<c:if test="${!stat.last }">
									<a href="javascript:;" onclick="detailMoveFn('<c:out value="${list.sqprdSeqNo }"/>', '<c:out value="${list.sqprdCtgry }"/>');" style="color: blue;">
										<c:out value="${list.sqprdNm}" escapeXml="false"/>,
									</a>
								</c:if>
								<c:if test="${stat.last }">
									<a href="javascript:;" onclick="detailMoveFn('<c:out value="${list.sqprdSeqNo }"/>', '<c:out value="${list.sqprdCtgry }"/>');" style="color: blue;">
										<c:out value="${list.sqprdNm}" escapeXml="false"/>
									</a>
								</c:if>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<th scope="row">View Type</th>
				<td>
					<c:out value="${detail.viewTypeImgNm }" escapeXml="false"/>
				</td>
			</tr>
			<c:choose>
				<c:when test="${detail.viewTypeImg eq 'GOODS_VW_TP_03'}">
					<tr>
						<th scope="row">View Type Color<br/>Option</th>
						<td>
							<c:out value="${detail.viewTypeImgBkgrColorOptNm }" />
						</td>
					</tr>
					<tr>
						<th scope="row">View Type Color</th>
						<td>
							<c:out value="${detail.viewTypeImgBkgrColorCcdNm }" /><c:if test="${detail.viewTypeImgBkgrColorCcd eq  GOODS_VW_BG_04}"> <c:out value="${detail.viewTypeImgBkgrColorVal }" escapeXml="false"/></c:if>
						</td>
					</tr>
					<c:choose>
						<c:when test="${detail.viewTypeImgBkgrColorOpt eq 'GOODS_VW_BG_OP_01'}">
						<tr>
							<th scope="row">View Type <br/>From - To</th>
							<td>
								<c:out value="${detail.viewTypeImgFrom }" escapeXml="false"/> - <c:out value="${detail.viewTypeImgTo }" escapeXml="false"/><br/>
								<c:out value="${detail.viewTypeImgCcdNm }" />
							</td>
						</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<th scope="row">View Type<br>HashTag</th>
								<td>
									<c:out value="${detail.viewTypeImgHashTag }" escapeXml="false"/>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<tr>
						<th scope="row">View Type Image</th>
						<td>
						<c:if test="${! empty detail.viewTypeImgOrgFileNm}">
							<img alt="<c:out value="${detail.viewTypeImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/goodsImgView.do?sqprdSeqNo=<c:out value='${detail.sqprdSeqNo }'/>&imgKinds=viewType&sqprdCtgry=<c:out value='${vo.sqprdCtgry }'/>" style="max-width: 100px; vertical-align: middle;">
						</c:if>
						</td>
					</tr>	
				</c:otherwise>
			</c:choose>
			
			<tr>
				<th scope="row">Registrar</th>
				<td>
					<c:out value="${detail.insPersonNm }" escapeXml="false"/>
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
							
							
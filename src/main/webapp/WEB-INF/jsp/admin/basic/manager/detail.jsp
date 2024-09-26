<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="admMngSeqNo" name="admMngSeqNo" value="<c:out value="${detail.admMngSeqNo }" />">



<section>	
	<div class="title"><h3>Administrator Management Detail Information</h3></div>
	<article>
	<div class="Cont_place">
	<table class="bd-form" summary="등록, 수정하는 영역 입니다.">
			<colgroup>
				<col width="200px" />
				<col width="" />
			</colgroup>
			<tr>
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">Administrator Information
</th>
			</tr>
			<tr>
				<th scope="row">Sort</th>
				<td>
					<c:out value="${detail.admAuthNm }" />
				</td>
			</tr>
			<tr>
				<th scope="row">Name</th>
				<td>
					<c:out value="${detail.admUserNm }" escapeXml="false"/>
				</td>
			</tr>
			<%-- <tr>
				<th scope="row">E-mail</th>
				<td>
					<div class="sel btn-module">
						<c:out value="${detail.admEmailAddr }" escapeXml="false"/>						
						<a href="javascript:;" id="btnSendEmail" class="btnStyle20" >Send Temporary Password</a>
						
					</div>
				</td>
			</tr> --%>
			<tr>
				<th scope="row">ID</th>
				<td>
					<c:out value="${detail.admUserId }" escapeXml="false"/>
				</td>
			</tr>
			<%-- <tr>
				<th scope="row">Password<br/>registration<br/>status</th>
					<c:choose>
					<c:when test="${detail.tempPwSts eq 'N'}">
						<td>
							Complete
						</td>
					</c:when>
					<c:otherwise>
						<td>
							Not<br/>Complete
						</td>
					</c:otherwise>
				</c:choose>
			</tr> --%>
			<tr>
				<th scope="row">Registrar</th>
				<td>
					<c:out value="${detail.insPersonNm }"/>
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
				<a href="javascript:;" id="btnCntReset" class="btnStyle01" >Reset login count</a>
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
		
		/* $("#btnSendEmail").click(function() {
			$("#detailForm").prop("action","sendEmail.do");
			$("#detailForm").prop("method", "post");
			$("#detailForm").submit();
		}); */
				
		$("#btnDelete").click(function() {
			if(confirm("Are you sure you want to delete it?")) {
				$("#detailForm").prop("action","doDelete.do");
				$("#detailForm").prop("method", "post");
				$("#detailForm").submit();
			}
		});
		
		$("#btnCntReset").click(function() {
			$.ajax({
				url : "<c:url value='/celloSquareAdmin/manager/logincntreset.do' />",
				data : {admMngSeqNo:$("#admMngSeqNo").val().trim()},
				type : "POST",
				dataType : "json",
				success : function(response) {
					if(response.res == true){
						alert("Initialization is complete.");
					} else {
						alert("Initialization failed.");
					}
				}, error : function(request, status, error){
					// alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					window.location.href = "/celloSquareAdmin/login/logout.do";
				}
			});
		});
	});
	
</script>		
							
							
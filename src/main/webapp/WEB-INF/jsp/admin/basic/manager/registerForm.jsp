<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<script type="text/javascript">
var chk = false;

$(document).ready(function() {
	$("#btnSave").on("click",function() {
		
		// form byte check
		if(formByteCheck() == false) {
			return false;
		}
		
		if(formValidate()){
				if($("#pageType").val() == 'I'){
					$("#administratorForm").prop("action","register.do");
					$("#administratorForm").submit();
			}
				if($("#pageType").val() == 'U'){
					$("#administratorForm").prop("action","update.do");
					$("#administratorForm").submit();
			}
		}
	});
	
	$("#btnList").on("click",function() {
		history.back();
	});
	
	$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
	$.fnGetCodeSelectAjax("sGb=ADM_AUTH&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "selAuthCd", "${detail.admAuth}", "selectbox", "");	
	
	
	$("#chkId").on("click",function(){
		if($("#admUserId").val().trim() == ""){
			alert("Please enter the ID.");
			return false;
		}
		
		$.ajax({
			url : "<c:url value='/celloSquareAdmin/manager/chkId.do' />",
			data : {admUserId:$("#admUserId").val().trim()},
			type : "POST",
			dataType : "json",
			success : function(response) {
					if(response.res == true){
						alert("The ID is available.");
						chk = true;
						$("#admUserId").on("propertychange change keyup paste input", function() {
							chk = false;
						});
					} else {
						alert("This ID is already in use. Please enter a new one.");
						chk = false;
						$("#admUserId").focus();
					}
				}, error : function(request, status, error){
					// alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
					window.location.href = "/celloSquareAdmin/login/logout.do";
				}
			});
		});	
});

function formValidate(){
	
	//var emailReg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	var pwPattern = /^.*(?=^.{8,16}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
	if($.trim($("#admUserNm").val()) == ""){
		alert("Please enter the name.");
		$("#admUserNm").focus();
		return false;
	} 
	
	/* if($.trim($("#admEmailAddr").val()) == ""){
		alert("Please enter the email address.");
		$("#admEmailAddr").focus();
		return false;
	} */ 
	
	/* if(!emailReg.test($("#admEmailAddr").val()) && $("#admEmailAddr").val().trim() != "") {
		alert("Please enter the right email format: test@test.com");
		$("#admEmailAddr").select();
		return false;
	} */
	if($.trim($("#admUserId").val()) == ""){
		alert("Please enter the ID.");
		$("#admUserId").focus();
		return false;
	} 
	if(!chk && $("#pageType").val() == 'I'){
		alert("Please double check ID.");
		$("#admUserId").focus();
		return false;
	}
	if($("#pageType").val() == 'I'){
		if($.trim($("#admPw").val()) == ""){
			alert("Please enter your Password.");
			$("#admPw").focus();
			return false;
		}
		if(!pwPattern.test($("#admPw").val()) && $("#admPw").val().trim() != "") {
			alert("Please write a password with a mixture of at least one uppercase/lowercase letter, number, and special symbol.");
			// 비밀번호는 8~16자 영문 대/소 문자, 숫자, 특수기호 1개 이상을 혼합하여 작성해 주세요
			$("#admPw").focus();
			return false;
		}
		if($.trim($("#ReAdmPw").val()) == ""){
			alert("Please enter your Re-Password.");
			$("#ReAdmPw").focus();
			return false;
		}
		if($.trim($("#admPw").val()) != $.trim($("#ReAdmPw").val())){
			alert("The password is wrong. Please check.");
			$("#ReAdmPw").focus();
			return false;
		}
	}
	if($("#pageType").val() == 'U'){
		if($.trim($("#admPw").val()) != "" || $.trim($("#ReAdmPw").val()) != "") {
			if($.trim($("#admPw").val()) == ""){
				alert("Please enter your Password.");
				$("#admPw").focus();
				return false;
			}
			if(!pwPattern.test($("#admPw").val()) && $("#admPw").val().trim() != "") {
				alert("Please write a password with a mixture of at least one uppercase/lowercase letter, number, and special symbol.");
				// 비밀번호는 8~16자 영문 대/소 문자, 숫자, 특수기호 1개 이상을 혼합하여 작성해 주세요
				$("#admPw").focus();
				return false;
			}
			if($.trim($("#ReAdmPw").val()) == ""){
				alert("Please enter your Re-Password.");
				$("#ReAdmPw").focus();
				return false;
			}
			if($.trim($("#admPw").val()) != $.trim($("#ReAdmPw").val())){
				alert("The password is wrong. Please check.");
				$("#ReAdmPw").focus();
				return false;
			}
		} 
	}
	
	return true;
}
</script>

<form id="administratorForm" name="administratorForm" method="post" action="./register.do">
<input type="hidden" id="pageType" value="<c:out value="${pageType }"/>">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">

<input type="hidden" id="admMngSeqNo" name="admMngSeqNo" value="<c:out value="${detail.admMngSeqNo }" />">



<section>	
	<div class="title">
		<h3>Administrator Management
			<c:choose>
				<c:when test="${pageType eq 'U' }">
					Modify
				</c:when>
				<c:otherwise>
					Registration
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<article>
	<div class="Cont_place">
	<table class="bd-form" summary="등록, 수정하는 영역 입니다.">
			<caption>열람,내용</caption>
			<colgroup>
				<col width="130px" />
				<col width="" />
			</colgroup>
			<tr>
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">Administrator Information</th>
			</tr>
			<tr>
				<th scope="row">Sort *</th>
				<td>
					<select id="selAuthCd" name="admAuth">
					</select>
				</td>
			</tr>
			<tr>
				<th scope="row">Name *</th>
				<td>
					<input id="admUserNm" name="admUserNm" type="text" value="<c:out value="${detail.admUserNm }" escapeXml="false"/>" placeholder="Please enter the name." class="inp-field widL" maxlength="35" data-vbyte="35" data-vmsg="Name"/>
				</td>
			</tr>
			<%-- <tr>
				<th scope="row">E-mail *</th>
				<td>
					<input id="admEmailAddr" name="admEmailAddr" type="text" value="<c:out value="${detail.admEmailAddr }"/>" placeholder="Please enter the email address." class="inp-field widL" maxlength="180" data-vbyte="180" data-vmsg="email address"/>
				</td>
			</tr> --%>
			<tr>
				<th scope="row">ID *</th>
					<td>
						<div class="btn-module chk">
							<input type="text" id="admUserId" name="admUserId" maxlength="20" placeholder="Please enter the ID." <c:if test="${pageType eq 'U' }">disabled</c:if> 
							value="<c:out value="${detail.admUserId }" escapeXml="false"/>"  class="inp-field widM2" maxlength="85" data-vbyte="85" data-vmsg="ID"/>
							<c:if test="${pageType eq 'I'}">							
							<a href="javascript:;" id="chkId" class="btnStyle01" >Duplicate Check</a>
							</c:if>
						</div>
					</td>
			</tr>
			<tr>
				<th scope="row">Password *</th>
				<td>
					<input id="admPw" name="admPw" type="password" value="" placeholder="Please enter the Password." class="inp-field widL" maxlength="320" data-vbyte="320" data-vmsg="Password"/>
				</td>
			</tr>	
			<tr>
				<th scope="row">Re-Password *</th>
				<td>
					<input id="ReAdmPw" name="ReAdmPw" type="password" value="" placeholder="Please enter the Re-Password." class="inp-field widL" maxlength="320" data-vbyte="320" data-vmsg="Re-Password"/>
				</td>
			</tr>	
			<tr>
				<th scope="row">Status *</th>
				<td>
					<div id="radUseCd"></div>
				</td>
			</tr>					
			<tbody>
			
			</tbody>
			
		</table>
		
		<div class="btn-module mgtS">		
			<div class="rightGroup">
				<c:choose>
					<c:when test="${pageType eq 'I' }">
						<a href="javascript:;" id="btnSave" class="btnStyle01" >Registration</a> 
					</c:when>
					<c:otherwise>
						<a href="javascript:;" id="btnSave" class="btnStyle01" >Modify</a> 
					</c:otherwise>
				</c:choose>
				<a href="javascript:;" id="btnList" class="btnStyle01">Cancel</a>
			</div>
		</div>	
		
	</div>
	</article>
</section>
</form>
							
							
							
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<!DOCTYPE html>
<html>
<head>
<meta charset=utf-8 />
<title>CelloSquare - Admin</title>
<link rel="stylesheet" type="text/css" href="/static/css/common.css"  />
<link rel="stylesheet" type="text/css" href="/static/css/jquery-ui.css"  />
<script type="text/javascript" src="/static/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/static/js/jquery-ui.js"></script>
<script type="text/javascript" src="/static/js/select.js"></script>
<script type="text/javascript" src="/static/js/dev_common.js"></script>
<script type="text/javascript" src="/static/js/vue.js"></script>
<script type="text/javascript" src="/static/js/axios.min.js"></script>

<script type="text/javascript">



$(document).ready(function () {
	var pwPattern = /^.*(?=^.{8,16}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
	$("#btnPwdUpdt").on("click", function() {
		
		if($("#adminId").val() == "") {
			alert("Invalid approach. Please try again.");
			location.href= "/";
			return false;
		}
		if($("#passwordEncpt").val() == "") {
			alert("Please enter your password.");
			$("#passwordEncpt").focus();
			return false;
		}
		if($("#passwordEncpt").val().trim() != "" && $.trim($("#passwordEncpt").val().length) < 8 && $.trim($("#passwordEncpt").val().length > 17)){
			alert("Please enter a minimum of 8 digits and a maximum of 16 digits.");
			$("#passwordEncpt").focus();
			return false;
		}
		if(!pwPattern.test($("#passwordEncpt").val()) && $("#passwordEncpt").val().trim() != "") {
			alert("Please write a password with a mixture of at least one uppercase/lowercase letter, number, and special symbol.");
			// 비밀번호는 영문 대/소 문자, 숫자, 특수기호 1개 이상을 혼합하여 작성해 주세요
			$("#passwordEncpt").focus();
			return false;
		}
		if($.trim($("#passwordEncptre").val()) == "" && $.trim($("#passwordEncpt").val()) != ""){
			alert("Password confirmation not entered.");
			// 비밀번호확인이 입력되지 않았습니다
			$("#passwordEncptre").focus();
			return false;
		}
		if($.trim($("#passwordEncpt").val()) != $.trim($("#passwordEncptre").val())){
			alert("Passwords do not match.");
			//비밀번호가 일치하지 않습니다
			$("#passwordEncptre").focus();
			return false;
		} 
		
		// form byte check
		if(formByteCheck() == false) {
			return false;
		}

		$("#updatePwdForm").prop("action","updtPwd.do");
		$("#updatePwdForm").submit();
	});


	$('input').keypress(function(e) {
	    if (e.keyCode == 13) {
	    	e.preventDefault();
	    	$("#btnPwdUpdt").click();
	    }
	});	
});

</script>

<blabMessage:actionMessage />
${GENERATE_VALIDATOR_JAVASCRIPT}
</head>
<body class="loginBody">

	<div id="idx-wrap">
		<div id="skip-nav">
			<ul>
				<li><a href="#login-box">로그인 영역으로 바로가기</a></li>
				<li><a href="#footer">푸터영역 메뉴 바로가기</a></li>
			</ul>
		</div>
		<hr />
	
		<!-- START BLOCK:CONTENT -->
		<div id="idx-container">
			<h1 class="textC"><a href="#none">&nbsp;</a></h1>
			<div id="idx-content">
				<div id="login-box">
					<h2>Change Password</h2>
					<form id="updatePwdForm" action="./updtPwd.do" method="post">
						<fieldset>
							<legend>Change Password</legend>
							<ul>
								<li>
									<label for="identity" class="identity"><span>ID</span></label>
									ID 
									<input type="text" id="adminId" name="adminId"  readonly="readonly" value="<c:out value="${vo.admUserId }" />" />
								</li>
								<li>
									<label for="passwordEncpt" class="passwd"><span>Password</span></label>
									PWD <input type="password" name="passwordEncpt" id="passwordEncpt" maxlength="16" data-vbyte="16" data-vmsg="PWD"/>
								</li>
								<li>
									<label for="passwordEncptre" class="passwd"><span>Re-enter<br/>Password</span></label>
									RE-PWD <input type="password" name="passwordEncptre" id="passwordEncptre" maxlength="16" data-vbyte="16" data-vmsg="RE-PWD" />
								</li>
							</ul>
							<a id="btnPwdUpdt" href="#" class="button">Change Password</a>
						</fieldset>
					</form>				
				</div>
			</div>
		</div>
		<!-- END BLOCK:CONTENT -->
	
	
		<hr />

	</div>
</body>
</html>

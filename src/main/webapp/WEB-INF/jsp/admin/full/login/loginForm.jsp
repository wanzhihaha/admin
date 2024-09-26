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
	

	
	$("#btnLogin").on("click", function() {
		
		if($("#adminId").val() == "") {
			alert("Please enter the ID.");
			$("#vAdmId").focus();
			return false;
		}
		if($("#passwordEncpt").val() == "") {
			alert("Please enter your password.");
			$("#passwordEncpt").focus();
			return false;
		}

		$("#loginForm").submit();
	});

	
	// $('input').keypress(function(e) {
	//     if (e.keyCode == 13) {
	//     	e.preventDefault();
	//     	$("#btnLogin").click();
	//     }
	// });


});

$(document).keyup(function(event){
	if(event.keyCode ==13){
		$("#btnLogin").trigger("click");
	}
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
					<h2>Cello Square 官网管理系统</h2>
					<form id="loginForm" action="./login.do" method="post">
						<fieldset>
							<legend>Cello Square admin system</legend>
							<ul>
								<li>
									<label for="identity" class="identity"><span>ID</span></label>
									<input type="text" name="adminId" id="adminId" />
								</li>
								<li>
									<label for="passwd" class="passwd"><span>Password</span></label>
									<input type="password" name="passwordEncpt" id="passwordEncpt" />
								</li>
							</ul>
							<a id="btnLogin" href="#" class="button">登录</a>
						</fieldset>
					</form>				
				</div>
			</div>
		</div>
		<!-- END BLOCK:CONTENT -->
	
	
		<hr />
		
		<!-- START BLOCK:FOOTER -->
		<div id="idx-footer">
			<address>
				Copyright &copy; Cello Square All Rights Reserved	</address>
		</div><!-- END BLOCK:FOOTER -->
	</div>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<div id="header-inner">
	<h1><a href="#none">物流运输系统</a></h1>
	<div class="userInfo">
		<a href="#">
			<span class="username">${SESSION_FORM_ADMIN.adminNm}</span>			
		</a>
		<a class="logout" href="<c:url value='/celloSquareAdmin/login/logout.do' />">退出</a>
	</div>
</div>

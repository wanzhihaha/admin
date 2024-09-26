<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%--
    response.setHeader("Cache-Control","no-store");  
    response.setHeader("Pragma","no-cache");  
    response.setDateHeader("Expires",0);  
    if (request.getProtocol().equals("HTTP/1.1"))
		response.setHeader("Cache-Control", "no-cache");
--%>



<%@ page errorPage="/error.jsp" %> 
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset=utf-8 />
<title>CelloSquare - Admin<tiles:insertAttribute name="title"/></title>
<link rel="stylesheet" type="text/css" href="/static/css/common.css"  />
<link rel="stylesheet" type="text/css" href="/static/css/jquery-ui.css"  />
	<link rel="stylesheet" type="text/css" href="/static/css/select2.min.css"  />
<script type="text/javascript" src="/static/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/static/js/jquery-ui.js"></script>
<script type="text/javascript" src="/static/js/select.js"></script>
<script type="text/javascript" src="/static/js/dev_common.js"></script>
<script type="text/javascript" src="/static/js/vue.js"></script>
<script type="text/javascript" src="/static/js/axios.min.js"></script>
<script type="text/javascript" src="/static/namo/js/namo_scripteditor.js"></script>
	<script type="text/javascript" src="/static/js/select2.min.js"></script>
<script>
	$(function() {
		$("#sortable").sortable();
		$("#sortable").disableSelection();
	});
</script>

<blabMessage:actionMessage />
${GENERATE_VALIDATOR_JAVASCRIPT}
</head>
<body>
<div id="wrap">
<div id="skip-nav">
	<ul>
		<li><a href="#content">본문으로 바로가기</a></li>
		<li><a href="#snb">메인메뉴로 바로가기</a></li>
		<li><a href="#pdNavi">로케이션 영역으로 바로가기</a></li>
		<li><a href="#footer">푸터영역 메뉴 바로가기</a></li>
	</ul>
</div>
<hr />

<!-- START:HEADER -->
<header id="header">
	<tiles:insertAttribute name="header"/>	
</header>
<!-- END:HEADER -->
<hr />	
<div id="container">
<!-- START:SNB  -->
<nav id="snb">
	<tiles:insertAttribute name="leftmenu"/>	
</nav>
<!-- END:SNB  -->

<!-- START:CONTENT -->
<div id="divContents">
	<tiles:insertAttribute name="body"/>
</div>
<!-- END:CONTENT -->
</div>
<hr />

<!-- START:FOOTER -->
<footer id="footer">
	<tiles:insertAttribute name="footer"/>
</footer>
<!-- END:FOOTER -->

</div>
</body>
</html>


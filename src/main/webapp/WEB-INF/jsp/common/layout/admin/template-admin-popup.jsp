<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%
	response.setHeader("Cache-Control","no-store");  
	response.setHeader("Pragma","no-cache");  
	response.setDateHeader("Expires",0);  
	if (request.getProtocol().equals("HTTP/1.1"))
		response.setHeader("Cache-Control", "no-cache");
%>

<%@ page isErrorPage="true" %>


<!DOCTYPE html>
<html>
<head>
<meta charset=utf-8 />
<title>CelloSquare - Admin</title>
<link rel="stylesheet" type="text/css" href="/static/css/common.css"  />
<link rel="stylesheet" type="text/css" href="/static/css/jquery-ui.css"  />
	<link rel="stylesheet" type="text/css" href="/static/css/select2.min.css"  />
<script type="text/javascript" src="/static/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/static/js/jquery-ui.js"></script>
<script type="text/javascript" src="/static/js/select.js"></script>
<script type="text/javascript" src="/static/js/dev_common.js"></script>
<script type="text/javascript" src="/static/js/vue.js"></script>
<script type="text/javascript" src="/static/js/axios.min.js"></script>
<script type="text/javascript" src="/static/js/select2.min.js"></script>


<blabMessage:actionMessage />
${GENERATE_VALIDATOR_JAVASCRIPT}
</head>
<body>
<div id="popup-layer">
	<tiles:insertAttribute name="body"/>
</div>
</body>
</html>


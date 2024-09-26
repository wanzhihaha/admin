<%@ page isErrorPage="true" %>
<%@ page import="java.io.StringWriter" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%=exception.getMessage()%><br>
　　<%=exception.getLocalizedMessage()%>
　　<%
    StringWriter sw=new StringWriter();
    PrintWriter pw=new PrintWriter(sw);
    exception.printStackTrace(pw);
    out.print(sw);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ERROR</title>
</head>
<body>
Error Page.
</body>
</html>
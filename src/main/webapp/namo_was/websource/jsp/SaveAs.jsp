<% request.setCharacterEncoding("utf-8"); %>  
<%@ page import="java.net.*" contentType="text/html; charset=utf-8" isErrorPage="true" %>
<%@ page import="java.io.*"%>
<%
  if (request.getParameter("save_string") != null && !"".equals(request.getParameter("save_string"))){

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=untitled.html");

		/* OutputStream outs = response.getOutputStream();
		outs.write( new byte[]{(byte)0xEF, (byte)0xBB, (byte)0xBF} );
		outs.write(request.getParameter("save_string").getBytes());
		outs.flush();
		outs.close(); */

		String sHTML = request.getParameter("save_string");
		String decodeSHTML = java.net.URLDecoder.decode(sHTML, "utf-8");
		// String decodeSHTML = java.net.URLDecoder.decode(sHTML);
		out.println(decodeSHTML);

		return;

  }else{

		return;
  }

%>
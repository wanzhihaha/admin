<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<img src="<blabProperty:value key="system.admin.path"/>/popupImgView.do?popSeqNo=<c:out value='${param.popSeqNo}'/>&imgKinds=pcPop">

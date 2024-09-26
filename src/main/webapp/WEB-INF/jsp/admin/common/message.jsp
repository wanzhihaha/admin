<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form name="message_form" method="<c:out value="${method == null ? 'get' : method }" />" action="<c:out value="${url}" />">
<%--	<input type="hidden" name="picType" value="<c:out value="${param.picType }" />" />--%>
	<input type="hidden" name="searchValue" value="<c:out value="${param.searchValue }" />" />
	<input type="hidden" name="searchType" value="<c:out value="${param.searchType }" />" />
	<input type="hidden" name="page" value="<c:out value="${param.page }" />" />
	<input type="hidden" name="rowPerPage" value="<c:out value="${param.rowPerPage }" />" />
	<c:forEach var="entry" items="${hmParam}" varStatus="status">
		<input type="hidden" name="<c:out value="${entry.key }" />" value="<c:out value="${entry.value }" />" />
	</c:forEach>
</form>

<script type="text/javascript">
<c:if test="${msg_type eq ':submit'}">
	<c:if test="${msg ne null and msg ne ''}">
	alert("<c:out value='${msg}' />");
	</c:if>
	document.message_form.submit();
</c:if>

<c:if test="${msg_type eq ':back'}">
	alert("<c:out value='${msg}' />");
	history.back();
</c:if>

<c:if test="${msg_type eq ':location'}">
	alert("<c:out value='${msg}' />");
	location.href="${url}";
</c:if>

<c:if test="${msg_type eq ':popup_close'}">
	<c:if test="${msg ne null and msg ne ''}">
		alert("<c:out value='${msg}' />");
	</c:if>
	window.close();
</c:if>

<c:if test="${msg_type eq ':popup_reload_close'}">
	<c:if test="${msg ne null and msg ne ''}">
		alert("<c:out value='${msg}' />");
	</c:if>
	opener.location.reload();
	window.close();
</c:if>

<c:if test="${msg_type eq ':popup_reload_submit'}">
	<c:if test="${msg ne null and msg ne ''}">
		alert("<c:out value='${msg}' />");
	</c:if>
	opener.location.reload();
	document.message_form.submit();
</c:if>
</script>
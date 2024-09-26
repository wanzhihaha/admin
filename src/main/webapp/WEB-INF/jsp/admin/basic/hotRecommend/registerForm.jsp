<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">
    <input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
    <input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
    <input type="hidden" name="page" value="<c:out value="${vo.page }" />">
    <input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
    <input type="hidden" id="id" name="id" value="<c:out value="${detail.id }" />">


    <section>
        <c:choose>
            <c:when test="${!empty detail.id }">
                <div class="title"><h3>修改</h3></div>
            </c:when>
            <c:otherwise>
                <div class="title"><h3>新增</h3></div>
            </c:otherwise>
        </c:choose>
        <article>
            <div class="Cont_place">
                <table class="bd-form s-form" summary="등록, 수정하는 영역 입니다.">
                    <caption>열람,내용</caption>
                    <colgroup>
                        <col width="130px"/>
                        <col width=""/>
                    </colgroup>
                    <tr>
                        <th scope="row">车辆类目</th>
                        <td>
                            <select id="type" name="type">
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">车辆标题*</th>
                        <td>
                            <input id="title" name="title" type="text" placeholder="请输入标题."
                                   class="inp-field widL" value="<c:out value="${detail.title }" escapeXml="false"/>"
                                   maxlength="128" data-vbyte="160" data-vmsg="title"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">车辆链接</th>
                        <td>
                            <div class="textCont">
                                <input id="link" name="link" type="text" placeholder="请输入有效链接."
                                       class="inp-field widL"
                                       value="<c:out value="${detail.link }" escapeXml="false"/>" maxlength="512"
                                       data-vbyte="240" data-vmsg="link"/>

                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">车辆状态*</th>
                        <td>
                            <div id="radUseCd"></div>
                        </td>
                    </tr>
                    <tbody>

                    </tbody>

                </table>

                <div class="btn-module mgtS">
                    <div class="rightGroup">
                        <a id="btnSave" href="#none" class="btnStyle01">保存</a>
                        <a href="javascript:;" id="btnList" class="btnStyle01">取消</a>
                    </div>
                </div>

            </div>
        </article>
    </section>
</form>


<script type="text/javascript">
    $(document).ready(function () {
		$.fnGetCodeSelectAjax("sGb=HOT_LIST&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "type", "${detail.type}", "selectbox", "");
        $.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");

        $("#btnSave").click(function () {

            if ($.trim($("#title").val()) == "") {
                alert("请输入标题.");
                $("#title").focus();
                return false;
            }
			// if ($.trim($("#link").val()) == "") {
			// 	alert("请输入有效链接.");
			// 	$("#link").focus();
			// 	return false;
			// }
            // form byte check
            if (formByteCheck() == false) {
                return false;
            }
            <c:choose>
            <c:when test="${detail.id == null}">
            $("#writeForm").attr('action', '<c:url value="./register.do"/>').submit();
            </c:when>
            <c:otherwise>
            $("#writeForm").attr('action', '<c:url value="./update.do"/>').submit();
            </c:otherwise>
            </c:choose>
        });
    });

    $("#btnList").on("click", function () {
        cancel_backUp("id","writeForm");
    });
</script>		
							
							
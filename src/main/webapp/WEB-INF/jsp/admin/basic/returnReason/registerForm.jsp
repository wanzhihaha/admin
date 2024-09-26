<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">
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
                        <th scope="row">类型*</th>
                        <td>
                            <select id="type" name="type">
                                <option value="1" <c:if test="${detail.type == 1}">selected="selected"</c:if>>
                                    有报价原因
                                </option>
                                <option value="2" <c:if test="${detail.type == 2}">selected="selected"</c:if>>
                                    无报价原因
                                </option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">原因*</th>
                        <td>
                            <input id="reason" name="reason" type="text" placeholder="请输入"
                                   class="inp-field widL" value="<c:out value="${detail.reason }" escapeXml="false"/>"
                                   maxlength="128" data-vbyte="160" data-vmsg="reason"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">状态*</th>
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
        $.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");

        $("#btnSave").click(function () {

            if ($.trim($("#reason").val()) == "") {
                alert("请输入原因");
                $("#reason").focus();
                return false;
            }
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
							
							
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="get" action="./register.do" enctype="multipart/form-data">
    <input type="hidden" name="page" value="<c:out value="${vo.page }" />">
    <input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
    <input type="hidden" id="id" name="id" value="<c:out value="${detail.id }" />">
    <input type="hidden" id="productCtgry" name="productCtgry" value="<c:out value="${detail.productCtgry }" />">

    <section>
        <c:choose>
            <c:when test="${!empty detail.id }">
                <div class="title"><h3>修改</h3></div>
            </c:when>
            <c:otherwise>
                <div class="title"><h3>创建</h3></div>
            </c:otherwise>
        </c:choose>
        <article>
            <div class="Cont_place">
                <table class="bd-form" summary="등록, 수정하는 영역 입니다.">
                    <caption>열람,내용</caption>
                    <colgroup>
                        <col width="130px"/>
                        <col width=""/>
                    </colgroup>

                    <tr>
                        <th colspan="2"
                            style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">
                            信息
                        </th>
                    </tr>

                    <tr>
                        <th scope="row">菜单名称 *</th>
                        <td>
                            <input id="name" name="name" type="text" placeholder="请输入菜单名称." class="inp-field widL"
                                   value="<c:out value="${detail.name }" escapeXml="false"/>" maxlength="512"
                                   data-vmsg="name"/>
                        </td>
                    </tr>

                    <tr>
                        <th scope="row">备注</th>
                        <td>
                            <div class="textCont">
                                <textarea name="contents" id="contents" class="textarea" rows="10" cols="65"
                                          placeholder="请输入备注." maxlength="210" data-vmsg="contents information"><c:out
                                        value="${detail.contents }" escapeXml="false"/></textarea>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">状态</th>
                        <td>
                            <div id="radUseCd"></div>
                        </td>
                    </tr>
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
            if ($.trim($("#name").val()) == "") {
                alert("请输入名称.");
                $("#name").focus();
                return false;
            }

            if (formByteCheck() == false) {
                return false;
            }
            <c:choose>
            <c:when test="${empty detail.id}">
            $("#writeForm").prop("method", "post");
            $("#writeForm").attr('action', '<c:url value="./register.do"/>').submit();
            </c:when>
            <c:otherwise>
            $("#writeForm").prop("method", "post");
            $("#writeForm").attr('action', '<c:url value="./update.do"/>').submit();
            </c:otherwise>
            </c:choose>
        });
    });
    //취소버튼 리스트로 이동
    $("#btnList").on("click", function () {
        cancel_backUp("id", "writeForm");
    });

</script>		
							

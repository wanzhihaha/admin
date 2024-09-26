<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="get" action="./register.do" enctype="multipart/form-data">
    <input type="hidden" name="startDate" value="<c:out value="${vo.startDate }" />">
    <input type="hidden" name="endDate" value="<c:out value="${vo.endDate }" />">
    <input type="hidden" name="page" value="<c:out value="${vo.page }" />">
    <input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
    <input type="hidden" id="id" name="id" value="<c:out value="${detail.id }" />">
    <input type="hidden" id="topId" name="topId" value="<c:out value="${detail.topId}"/>"/>
    <input type="hidden" id="searchValue" name="searchValue" value="<c:out value="${vo.searchValue}"/>"/>
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

                    <%@ include file="/WEB-INF/jsp/common/seo/seoRegisterFormNews.jsp" %>

                    <tr>
                        <th colspan="2"
                            style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">
                            信息
                        </th>
                    </tr>
                    <c:choose>
                        <c:when test="${not empty siblingNodes }">
                            <tr>
                                <th scope="row">栏目 *</th>
                                <td>
                                    <select name="childMenuId" id="childMenuId">
                                        <option value="">空</option>
                                        <c:forEach items="${siblingNodes }" var="siblingNode">
                                            <option value="${siblingNode.id }"
                                                    <c:if test="${siblingNode.id == detail.childMenuId}">selected</c:if>>${siblingNode.menuName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:choose>
                                <c:when test="${not empty detail.childMenuId }">
                                    <input type="hidden" name="childMenuId"
                                           value="<c:out value="${detail.childMenuId }" />">
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden" name="childMenuId" value="<c:out value="${detail.topId}" />">
                                </c:otherwise>
                            </c:choose>
                        </c:otherwise>
                    </c:choose>
                    <tr>
                        <th scope="row">名称 *</th>
                        <td>
                            <input id="name" name="name" type="text" placeholder="请输入名称." class="inp-field widL"
                                   value="<c:out value="${detail.name }" escapeXml="false"/>" maxlength="512"
                                   data-vmsg="name"/>
                        </td>
                    </tr>

                    <tr>
                        <th scope="row">摘要</th>
                        <td>
                            <div class="textCont">
                                <textarea name="summaryInfo" id="summaryInfo" class="textarea" rows="10" cols="65"
                                          placeholder="请输入摘要." maxlength="210" data-vmsg="summary information"><c:out
                                        value="${detail.summaryInfo }" escapeXml="false"/></textarea>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">内容 *</th>
                        <td>
                            <div class="textCont">
                                <textarea name="contents" id="contents" class="textarea" rows="10" cols="65"
                                          placeholder="请输入内容 *"><c:out value="${detail.contents }"
                                                                       escapeXml="false"/></textarea>
                                <script type="text/javascript" language="javascript">
                                    var CrossEditor = new NamoSE('contents');
                                    CrossEditor.params.Width = "100%";
                                    CrossEditor.params.UserLang = "auto";
                                    CrossEditor.params.FullScreen = false;
                                    CrossEditor.params.WebsourcePath = "/namo_was"
                                    CrossEditor.params.ServerUrl = 2;	// 서버 url제외
                                    CrossEditor.params.DeleteCommand = ["backgroundimage", "ce_imageeditor", "insertfile", "flash"];
                                    CrossEditor.params.UploadFileSizeLimit = "image:5242880,flash:2097152";
                                    CrossEditor.params.DefaultFont = "默认字体";
                                    CrossEditor.params.DefaultFontSize = "默认";
                                    CrossEditor.params.LineHeight = "normal";

                                    CrossEditor.EditorStart();

                                    function OnInitCompleted(e) {
                                        e.editorTarget.SetBodyValue(document.getElementById("contents").value);
                                    }
                                </script>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">状态</th>
                        <td>
                            <div id="radUseCd"></div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">术语</th>
                        <td>
                            <input id="terminologyTags" name="terminologyTags" type="text"
                                   placeholder="请输入术语标签,用英文逗号隔开最多五个" class="inp-field widL"
                                   value="<c:out value="${detail.terminologyTags }" escapeXml="false"/>" maxlength="128"
                                   data-vmsg="术语标签"/>
                        </td>
                    </tr>
<%--                    <tr>--%>
<%--                        <th scope="row">关键词</th>--%>
<%--                        <td>--%>
<%--                            <input id="antistopTags" name="antistopTags" type="text" placeholder="请输入关键词标签,用英文逗号隔开最多五个"--%>
<%--                                   class="inp-field widL"--%>
<%--                                   value="<c:out value="${detail.antistopTags }" escapeXml="false"/>" maxlength="128"--%>
<%--                                   data-vmsg="关键词标签"/>--%>

<%--                        </td>--%>
<%--                    </tr>--%>
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
            var siblingNodes_temp = '${siblingNodes}';

            if ($.trim($("#childMenuId").val()) == "" && siblingNodes_temp.length > 2) {
                alert("请选择类目.");
                $("#childMenuId").focus();
                return false;
            }
            if ($.trim($("#name").val()) == "") {
                alert("请输入名称.");
                $("#name").focus();
                return false;
            }

            if (CrossEditor.GetTextValue() == "") {
                alert("请输入内容.");
                CrossEditor.SetFocusEditor();
                return false;
            }
            if ($("#terminologyTags").val() != '' || $("#terminologyTags").val() != null) {
                var terminologyTags_split = $("#terminologyTags").val().split(",");
                if (terminologyTags_split.length > 5) {
                    alert("术语标签不能超出5个.");
                    return false;
                }
            }
            // if ($("#antistopTags").val() != '' || $("#antistopTags").val() != null) {
            //     var antistopTags_split = $("#antistopTags").val().split(",");
            //     if (antistopTags_split.length > 5) {
            //         alert("关键词标签不能超出5个.");
            //         return false;
            //     }
            // }
            $("#contents").val(CrossEditor.GetBodyValue());

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

    function OnInitCompleted(e) {
        if (e.editorTarget == CrossEditor) {
            CrossEditor.SetBodyValue(document.getElementById("contents").value);
        }
    }
</script>		
							

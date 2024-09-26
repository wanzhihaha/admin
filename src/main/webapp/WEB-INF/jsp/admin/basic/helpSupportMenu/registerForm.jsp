<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="get" action="./register.do" enctype="multipart/form-data">
    <input type="hidden" name="startDate" value="<c:out value="${vo.startDate }" />">
    <input type="hidden" name="endDate" value="<c:out value="${vo.endDate }" />">
    <input type="hidden" name="page" value="<c:out value="${vo.page }" />">
    <input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
    <input type="hidden" id="id" name="id" value="<c:out value="${detail.id }" />">
    <input type="hidden" id="searchValue" name="searchValue" value="<c:out value="${vo.searchValue}"/>"/>
    <input type="hidden" id="parentId" name="parentId" value="<c:out value="${vo.parentId}"/>"/>
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

                    <c:if test="${empty detail.id }">
                    <tr>
                        <th scope="row">上级菜单 *</th>
                        <td>
                            <select name="pId" id="pId">
                                <option value="">空</option>
                                <c:forEach items="${siblingNodes }" var="siblingNode">
                                    <option value="${siblingNode.id }"
                                            <c:if test="${siblingNode.id == detail.PId}">selected</c:if>>${siblingNode.menuName }</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    </c:if>
                    <tr>
                        <th scope="row">名称 *</th>
                        <td>
                            <input id="menuName" name="menuName" type="text" placeholder="请输入名称." class="inp-field widL"
                                   value="<c:out value="${detail.menuName }" escapeXml="false"/>" maxlength="64"
                                   data-vmsg="menuName"/>
                        </td>
                    </tr>

                    <tr>
                        <th scope="row">摘要</th>
                        <td>
                            <div class="textCont">
                                <textarea name="description" id="description" class="textarea" rows="10" cols="65"
                                          placeholder="请输入摘要." maxlength="210" data-vmsg="summary information"><c:out
                                        value="${detail.description }" escapeXml="false"/></textarea>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">排序</th>
                        <td>
                            <input placeholder="请输入排序." type="text" name="ordb" class="inp-field widL" value="<c:out value="${detail.ordb}" />"
                                   onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3" data-vmsg="ordb"/>
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

        $("#btnSave").click(function () {
            var detailId = '${detail.id }';
            if($.trim($("#pId").val()) == "" && !detailId){
                alert("请选择上级.");
                $("#pId").focus();
                return false;
            }
            if ($.trim($("#menuName").val()) == "") {
                alert("请输入名称.");
                $("#menuName").focus();
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
        cancel_backUp("id","writeForm");
    });

</script>
							

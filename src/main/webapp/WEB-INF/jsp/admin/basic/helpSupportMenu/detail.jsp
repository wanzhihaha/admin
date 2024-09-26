<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>
<% pageContext.setAttribute("crlf", "\r\n"); %>
<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
    <input type="hidden" name="page" value="<c:out value="${vo.page }" />">
    <input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
    <input type="hidden" id="id" name="id" value="<c:out value="${detail.id }" />">
    <input type="hidden" id="searchValue" name="searchValue" value="<c:out value="${vo.searchValue}"/>"/>
    <input type="hidden" id="parentId" name="parentId" value="<c:out value="${vo.parentId}"/>"/>
    <section>
        <div class="title"><h3>详情</h3></div>
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
                        <th scope="row">菜单名</th>
                        <td>
                            <c:out value="${detail.menuName }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">上级菜单名</th>
                        <td>
                            <c:out value="${detail.parentMenuName }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">是否子节点</th>
                        <td>
                            <c:if test="${detail.isChild == 1}">是 </c:if>
                            <c:if test="${detail.isChild == 0}">否 </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">摘要</th>
                        <td>
                            <c:out value="${detail.description }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">创建人</th>
                        <td>
                            <c:out value="${detail.insPersonNm }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">创建时间</th>
                        <td>
                            <fmt:formatDate value="${detail.insDtm }" pattern="yyyy-MM-dd"/>
                        </td>
                    </tr>

                </table>

                <div class="btn-module mgtS">
                    <div class="leftGroup">
                        <a href="javascript:;" id="btnList" class="btnStyle01">列表</a>
                    </div>
                    <div class="rightGroup">
                        <c:if test="${detail.PId != 0}">    <a href="javascript:;" id="btnUpdate" class="btnStyle01">修改</a> </c:if>
                        <c:if test="${detail.PId != 0}"> <a href="javascript:;" id="btnDelete" class="btnStyle02">删除</a></c:if>
                    </div>
                </div>
            </div>
        </article>
    </section>
</form>


<script type="text/javascript">


    $("#btnList").click(function () {
        $("#detailForm").prop("method", "get");
        $("#detailForm").attr('action', '<c:url value="./list.do"/>').submit();
    });

    $("#btnUpdate").click(function () {
        $("#detailForm").prop("method", "post");
        $("#detailForm").attr('action', '<c:url value="./updateForm.do"/>').submit();
    });

    $("#btnDelete").click(function () {
        if (confirm("确认删除此记录吗?")) {
            $("#detailForm").prop("method", "post");
            $("#detailForm").attr('action', '<c:url value="./doDelete.do"/>').submit();
        }
    });
</script>		
							
							
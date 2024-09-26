<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
    <input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
    <input type="hidden" name="type" value="<c:out value="${vo.type }" />">
    <input type="hidden" name="page" value="<c:out value="${vo.page }" />">
    <input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">

    <section>
        <div class="title"><h3>api日志详情</h3></div>
        <article>
            <div class="Cont_place">
                <table class="bd-form" summary="등록, 수정하는 영역 입니다.">
                    <caption>열람,내용</caption>
                    <tr>
                        <th scope="row">类型</th>
                        <td>
                            <c:out value="${detail.typeName }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">状态</th>
                        <td>
                            <c:out value="${detail.statusName }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">url</th>
                        <td>
                            <c:out value="${detail.url }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">请求体</th>
                        <td>
                            <c:out value="${detail.requestBody}"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">时间</th>
                        <td>
                            <fmt:formatDate value="${detail.createDate }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">响应体</th>
                        <td>
                            <c:out value="${detail.responseBody}"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">错误信息</th>
                        <td>
                            <textarea readonly = readonly style="height: 800px;width: 1200px">
                                <c:out value="${detail.exceptionMsg}"/>
                            </textarea>
                        </td>
                    </tr>
                </table>

                <div class="btn-module mgtS">
                    <div class="leftGroup">
                        <a href="javascript:;" id="btnList" class="btnStyle01">列表</a>
                    </div>
                </div>
            </div>
        </article>
    </section>
</form>


<script type="text/javascript">
    $(document).ready(function () {

        $("#btnList").click(function () {
            $("#detailForm").prop("method", "get");
            $("#detailForm").attr("action", "<c:url value ='./list.do'/>").submit();
        });

    });

</script>		
							
							
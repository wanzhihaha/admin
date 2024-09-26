<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
    <input type="hidden" name="page" value="<c:out value="${vo.page }" />">
    <input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
    <input type="hidden" id="id" name="id" value="<c:out value="${detail.id }" />">


    <section>
        <div class="title"><h3>详情</h3></div>
        <article>
            <div class="Cont_place">
                <table class="bd-form" summary="등록, 수정하는 영역 입니다.">
                    <caption>열람,내용</caption>
                    <colgroup>
                        <col width="200px"/>
                        <col width=""/>
                    </colgroup>
                    <tr>
                        <th scope="row">名称</th>
                        <td>
                            <c:out value="${detail.name}"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">渠道</th>
                        <td>
                            <c:out value="${detail.remark}"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">推送类型</th>
                        <td>
                            <c:out value="${detail.typeName }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">官网URL</th>
                        <td>
                            <c:out value="${detail.shareLink }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">值</th>
                        <td>
                            <c:out value="${detail.sourceVal }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">注册按钮标识符</th>
                        <td>
                            <c:out value="${detail.identifier}"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">app注册成功标识符</th>
                        <td>
                            <c:out value="${detail.appSuccessIdentifier}"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">创建时间</th>
                        <td>
                            <fmt:formatDate value="${detail.insDtm }" type="date" pattern="yyyy-MM-dd  HH:mm:ss"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">更新时间</th>
                        <td>
                            <fmt:formatDate value="${detail.updDtm }" type="date" pattern="yyyy-MM-dd  HH:mm:ss"/>
                        </td>
                    </tr>
                </table>

                <div class="btn-module mgtS">
                    <div class="leftGroup">
                        <a href="javascript:;" id="btnList" class="btnStyle01">列表</a>
                    </div>
                    <div class="rightGroup">
                        <a href="javascript:;" id="btnUpdate" class="btnStyle01">修改</a>
                        <a href="javascript:;" id="btnDelete" class="btnStyle02">删除</a>
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

        $("#btnUpdate").click(function () {
            $("#detailForm").prop("method", "post");
            $("#detailForm").attr('action', '<c:url value="./updateForm.do"/>').submit();
        });

        $("#btnDelete").click(function () {
            if (confirm("确认删除吗?")) {
                $("#detailForm").prop("method", "post");
                $("#detailForm").attr('action', '<c:url value="./doDelete.do"/>').submit();
            }
        });

    });

</script>		
							
							
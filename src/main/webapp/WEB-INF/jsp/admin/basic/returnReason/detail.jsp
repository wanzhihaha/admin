<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
    <input type="hidden" name="type" value="<c:out value="${vo.type }" />">
    <input type="hidden" name="reason" value="<c:out value="${vo.reason }" />">
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
                        <th scope="row">类型</th>
                        <td>
                            <c:out value="${detail.typeNm }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">原因</th>
                        <td>
                            <c:out value="${detail.reason }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">创建时间</th>
                        <td>
                            <c:out value="${detail.insDtm }" />
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">更新时间</th>
                        <td>
                            <c:out value="${detail.updDtm }" />
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">状态</th>
                        <td>
                            <c:out value="${detail.useYnNm}"/>
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
							
							
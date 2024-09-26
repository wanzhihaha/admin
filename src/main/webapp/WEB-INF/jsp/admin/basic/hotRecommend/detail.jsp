<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
    <input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
    <input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
    <input type="hidden" name="page" value="<c:out value="${vo.page }" />">
    <input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
    <input type="hidden" id="id" name="id" value="<c:out value="${detail.id }" />">


    <section>
        <div class="title"><h3>热门详情</h3></div>
        <article>
            <div class="Cont_place">
                <table class="bd-form" summary="등록, 수정하는 영역 입니다.">
                    <caption>열람,내용</caption>
                    <colgroup>
                        <col width="200px"/>
                        <col width=""/>
                    </colgroup>
                    <tr>
                        <th scope="row">类目</th>
                        <td>
                            <c:out value="${detail.type }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">标题</th>
                        <td>
                            <c:out value="${detail.title }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">链接URL</th>
                        <td>
                            <c:out value="${detail.link}"/>
                        </td>
                    </tr>

                    <tr>
                        <th scope="row">创建时间</th>
                        <td>
                            <fmt:formatDate value="${detail.insDtm }" type="date" pattern="yyyy-MM-dd"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">更新时间</th>
                        <td>
                            <fmt:formatDate value="${detail.updDtm }" type="date" pattern="yyyy-MM-dd"/>
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
							
							
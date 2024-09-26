<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
    <input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
    <input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
    <input type="hidden" name="page" value="<c:out value="${vo.page }" />">
    <input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
    <input type="hidden" id="routeSeqNo" name="routeSeqNo" value="<c:out value="${detail.routeSeqNo }" />">

    <section>
        <div class="title"><h3>线路</h3></div>
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
                            详细信息
                        </th>
                    </tr>
                    <tr>
                        <th scope="row">国家cd</th>
                        <td>
                            <c:out value="${detail.nationCd}"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">线路类型</th>
                        <td>
                            <c:out value="${detail.productModeNm}" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">产品id</th>
                        <td>
                            <c:out value="${detail.productId}" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">产品名</th>
                        <td>
                            <c:out value="${detail.productNm}" escapeXml="false"/>
                        </td>
                    </tr>

                    <tr>
                        <th scope="row">起点</th>
                        <td>
                            <c:out value="${detail.fromNode}" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">终点</th>
                        <td>
                            <c:out value="${detail.toNode}"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">状态*</th>
                        <td>
                            <c:out value="${detail.useYnNm}"/>
                        </td>
                    </tr>
                    <tbody>

                    </tbody>

                </table>

                <div class="btn-module mgtS">
                    <div class="leftGroup">
                        <a href="javascript:;" id="btnList" class="btnStyle01">列表</a>
                    </div>
                    <div class="rightGroup">
                        <a href="javascript:;" id="btnUpdate" class="btnStyle01">修改</a>
                        <c:if test="${detail.routeSource eq 'E'}">
                            <a href="javascript:;" id="btnDelete" class="btnStyle02">删除</a>
                        </c:if>
                    </div>
                </div>
            </div>
        </article>
    </section>
</form>


<script type="text/javascript">
    $(document).ready(function () {

        $("#btnList").click(function () {
            $("#detailForm").prop("action", "list.do");
            $("#detailForm").prop("method", "get");
            $("#detailForm").submit();
        });

        $("#btnUpdate").click(function () {
            $("#detailForm").prop("action", "updateForm.do");
            $("#detailForm").prop("method", "post");
            $("#detailForm").submit();
        });

        $("#btnDelete").click(function () {
            if (confirm("确认删除此线路吗")) {
                $("#detailForm").prop("action", "doDelete.do");
                $("#detailForm").prop("method", "post");
                $("#detailForm").submit();
            }
        });
    });

</script>		
							
							
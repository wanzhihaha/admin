<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>
<% pageContext.setAttribute("crlf", "\r\n"); %>
<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
    <input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<%--    <input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">--%>
    <input type="hidden" id="id" name="id" value="<c:out value="${detail.id }" />">
    <input type="hidden" id="productId" name="productId" value="<c:out value="${detail.productId }" />">
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

                    <%@ include file="/WEB-INF/jsp/common/seo/seoDetailNews.jsp" %>

                    <tr>
                        <th colspan="2"
                            style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">
                            信息
                        </th>
                    </tr>
                    <tr>
                        <th scope="row">产品类型</th>
                        <td>
                            <c:out value="${detail.productType }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">始发地</th>
                        <td>
                            <c:out value="${detail.origin }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">目的地</th>
                        <td>
                            <c:out value="${detail.destination }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">参考时效</th>
                        <td>
                            <c:out value="${detail.aging }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">备注</th>
                        <td>
                            <c:out value="${detail.remark }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">是否热卖</th>
                        <td>
                            <c:out value="${detail.hotFlag }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">币种</th>
                        <td>
                            <c:out value="${detail.currency }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">价格</th>
                        <td>
                            <c:forEach var="item" items="${detail.bargainPrices}">
                                <li class="row-input">
                                    单位:<c:out value="${item.unit}" escapeXml="false"/>
                                    价格:<c:out value="${item.price}" escapeXml="false"/>
                                </li>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">热卖标签图</th>
                        <td>
                            <c:if test="${! empty detail.listImgOrgFileNm}">
                                <img src="<blabProperty:value key="system.admin.path"/>/bargain/imgView.do?id=<c:out value='${detail.id }'/>"
                                     style="max-width: 100px; vertical-align: middle;">
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">生效时间</th>
                        <td>
                            <c:out value="${detail.effectiveDate }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">失效时间</th>
                        <td>
                            <c:out value="${detail.expiryDate }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">创建人</th>
                        <td>
                            <c:out value="${detail.insPersonId }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">创建时间</th>
                        <td>
                            <c:out value="${detail.insDtm }" />
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">状态</th>
                        <td>
                            <c:out value="${detail.useYn}"/>
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
                        <a href="javascript:;" id="btnDelete" class="btnStyle02">删除</a>
                    </div>
                </div>
            </div>
        </article>
    </section>
</form>


<script type="text/javascript">


    $("#btnList").click(function () {
        // history.back();
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
							
							
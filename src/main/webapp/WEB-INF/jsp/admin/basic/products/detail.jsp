<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>
<% pageContext.setAttribute("crlf", "\r\n"); %>
<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
    <input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
    <input type="hidden" name="page" value="<c:out value="${vo.page }" />">
    <input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
    <input type="hidden" id="productCtgry" name="productCtgry" value="<c:out value="${vo.productCtgry }"/>">
    <input type="hidden" id="productCtgryNm" name="productCtgryNm" value="<c:out value="${vo.productCtgryNm }"/>">
    <input type="hidden" id="productSeqNo" name="productSeqNo" value="<c:out value="${detail.productSeqNo }" />">


    <section>
        <div class="title"><h3><c:out value="${detail.productCtgryNm}"/> 详情</h3></div>
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
                            产品信息
                        </th>
                    </tr>
                    <tr>
                        <th scope="row">产品名称</th>
                        <td>
                            <c:out value="${detail.productNm }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">摘要</th>
                        <td>
                            ${fn:replace(detail.productSummaryInfo, crlf, "<br>")}
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">产品详情</th>
                        <td>
                            <c:out value="${detail.productContents }" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">列表图标</th>
                        <td>
                            <c:if test="${! empty detail.pcListImgOrgFileNm}">
                                <img alt="<c:out value="${detail.pcListImgAlt }" escapeXml="false"/>"
                                     src="<blabProperty:value key="system.admin.path"/>/products/goodsImgView.do?productSeqNo=<c:out value='${detail.productSeqNo }'/>&imgKinds=pcList&productCtgry=<c:out value='${vo.productCtgry }'/>"
                                     style="max-width: 100px; vertical-align: middle;">
                            </c:if>
                        </td>
                    </tr>

                    <tr>
                        <th scope="row">详情图</th>
                        <td>
                            <c:if test="${! empty detail.pcDetlImgOrgFileNm}">
                                <img alt="<c:out value="${detail.pcDetlImgAlt }" escapeXml="false"/>"
                                     src="<blabProperty:value key="system.admin.path"/>/products/goodsImgView.do?productSeqNo=<c:out value='${detail.productSeqNo }'/>&imgKinds=pcDetail&productCtgry=<c:out value='${vo.productCtgry }'/>"
                                     style="max-width: 100px; vertical-align: middle;">
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">移动端详情图</th>
                        <td>
                            <c:if test="${! empty detail.mobileDetlImgOrgFileNm}">
                                <img alt="<c:out value="${detail.mobileDetlImgAlt }" escapeXml="false"/>"
                                     src="<blabProperty:value key="system.admin.path"/>/products/goodsImgView.do?productSeqNo=<c:out value='${detail.productSeqNo }'/>&imgKinds=mobileDetail&productCtgry=<c:out value='${vo.productCtgry }'/>"
                                     style="max-width: 100px; vertical-align: middle;">
                            </c:if>
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
                            <c:out value="${detail.insDtm}"/>
                        </td>
                    </tr>

                    <tr>
                        <th scope="row">常见问题</th>
                        <td>
                            <c:out value="${ackQuestionNames}"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">Status</th>
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
                        <a href="javascript:;" id="btnDelete" class="btnStyle02">删除</a>
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
        if (confirm("确认删除此产品吗?")) {
            $("#detailForm").prop("method", "post");
            $("#detailForm").attr('action', '<c:url value="./doDelete.do"/>').submit();
        }
    });

    function detailMoveFn(SeqNo, ctgry) {
        $("#productSeqNo").val(SeqNo);
        $("#productCtgry").val(ctgry);
        $("#detailForm").prop("method", "post");
        $("#detailForm").attr('action', '<c:url value="./detail.do"/>').submit();
    }


</script>		
							
							
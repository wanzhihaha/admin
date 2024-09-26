<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
    <input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
    <input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
    <input type="hidden" name="page" value="<c:out value="${vo.page }" />">
    <input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
    <input type="hidden" id="nodeCd" name="nodeCd" value="<c:out value="${detail.nodeCd }" />">
    <input type="hidden" id="id" name="id" value="<c:out value="${detail.id }" />">
    <input type="hidden" name="continentCd" id="continentCd" value="<c:out value="${vo.continentCd }" />">
    <input type="hidden" name="productMode" id="productMode" value="<c:out value="${vo.productMode }" />">
    <input type="hidden" id="searchType1" name="searchType1" value="<c:out value="${vo.searchType1}"/>">
    <section>
        <div class="title"><h3>节点详情</h3></div>
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
                        <th scope="row">节点cd</th>
                        <td>
                            <c:out value="${detail.nodeCd}"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">类型</th>
                        <td>
                            <c:out value="${detail.productModeNm}"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">所属大陆</th>
                        <td>
                            <c:out value="${detail.continentName}"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">所属国家</th>
                        <td>
                            <c:out value="${detail.nationCnNm}" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">节点英文</th>
                        <td>
                            <c:out value="${detail.nodeEngNm}" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">节点中文</th>
                        <td>
                            <c:out value="${detail.nodeCnNm}" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">城市英文</th>
                        <td>
                            <c:out value="${detail.cityEngNm}" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">城市中文</th>
                        <td>
                            <c:out value="${detail.cityCnNm}" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">搜索选项值</th>
                        <td>
                            <c:out value="${detail.searchKeyWord}"/>
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
                        <c:if test="${detail.nodeSource eq 'E'}">
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
            if (confirm("确定要删除吗？")) {
                $("#detailForm").prop("action", "doDelete.do");
                $("#detailForm").prop("method", "post");
                $("#detailForm").submit();
            }
        });
    });

</script>		
							
							
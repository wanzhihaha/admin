<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
    <input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
    <input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
    <input type="hidden" name="page" value="<c:out value="${vo.page }" />">
    <input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
    <input type="hidden" name="continentCd" value="<c:out value="${vo.continentCd }" />">
    <input type="hidden" id="nationSeqNo" name="nationSeqNo" value="<c:out value="${detail.nationSeqNo }" />">

    <section>
        <div class="title"><h3>国家</h3></div>
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
                        <th scope="row">州/大陆</th>
                        <td>
                            <c:out value="${detail.continentNm}"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">国家（英文）</th>
                        <td>
                            <c:out value="${detail.nationNm}" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">国家（中文）</th>
                        <td>
                            <c:out value="${detail.nationCnNm}" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">国家 Code</th>
                        <td>
                            <c:out value="${detail.nationCd}" escapeXml="false"/>
                        </td>
                    </tr>
                    <tr>
                           <th scope="row">图标 </th>
                           <td>
                               <c:if test="${!empty detail.imgOrgFileNm}">
                                   <img style="max-width: 100px; vertical-align: middle;" src="<blabProperty:value key="system.admin.path"/>/country/ImgView.do?nationSeqNo=<c:out value='${detail.nationSeqNo}'/>">
                               </c:if>
                           </td>
                       </tr>

                       <tr>
                           <th scope="row">支持快递</th>
                           <td>
<%--                               <input type="radio" checked value="<c:out value="${detail.expressUseYn}" />">--%>
                               <c:out value="${detail.expressUseValue}"/>
                           </td>
                       </tr>
                    <tr>
                        <th scope="row">搜索选项值</th>
                        <td>
                            <c:out value="${detail.searchKeyWord}"/>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row">状态</th>
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
<%--                        <a href="javascript:;" id="btnDelete" class="btnStyle02">删除</a>--%>
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
            if (confirm("Are you sure you want to delete it?")) {
                $("#detailForm").prop("action", "doDelete.do");
                $("#detailForm").prop("method", "post");
                $("#detailForm").submit();
            }
        });
    });

</script>		
							
							
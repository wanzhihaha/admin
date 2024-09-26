<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>
<!-- START:CONTENT -->
<form id="searchFrom" action="" method="get">
    <input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
    <input type="hidden" id="id" name="id" value="<c:out value="${vo.id}"/>">
    <input type="hidden" id="terminologyName" name="terminologyName" value="<c:out value="${vo.terminologyName}"/>">


    <section>
        <div class="title"><h3>分词匹配库</h3></div>
        <div class="Cont_place">
            <article>
                <div class="inputUI_simple">
                    <table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
                        <caption>주유소 주소로 검색 영역</caption>
                        <colgroup>
                            <col width="120px" />
                            <col width="" />
                            <col width="100px" />
                        </colgroup>
                        <tr>
                            <th scope="row">搜索</th>
                            <td>
                                <input type="text" class="inp-field widS mglS" id="searchValue" name="searchValue" value="<c:out value="${vo.searchValue}" escapeXml="false"/>" />
                            </td>
                            <td>
                                <div class="btn-module floatR">
                                    <div class="rightGroup">
                                        <a href="javascript:;" class="search" onclick="fnSearch();" id="btnSearch">搜索</a>
                                        <a href="javascript:;" class="search" onclick="fnAdd();" id="btnAdd">新增词库</a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </article>
            <article>

                <table class="bd-list inputUI_simple" summary="리스트 영역 입니다.">
                    <caption>No, 주유소명 , 선택 여부</caption>
                    <colgroup>
                        <col width="5%" />
                        <col width="%" />
                        <col width="20%" />
                    </colgroup>
                    <thead>
                    <tr>
                        <th scope="col">No</th>
                        <th scope="col">分词</th>
                        <th scope="col">删除</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="list" items="${list }" varStatus="stat">
                        <tr>
                            <td>
                                <span style="width: 50px;">
                                    <c:out value="${(totalCount - ((vo.page-1) * vo.rowPerPage)) - stat.index}" />
                                </span>
                            </td>

                            <td>

                                <c:out value="${list.dicName }" escapeXml="false"/>
                            </td>
                            <td>
                                <div class="btn-module useRS">
                                    <a href="javascript:;" onclick="useClick('<c:out value ='${list.dicName }' escapeXml="false"/>', '<c:out value='${list.id }'/>');" class=tdSave  style="text-decoration: none;">删除</a>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${fn:length(list) < 1 }">
                        <tr>
                            <td colspan="4" class="blank">No registered data</td>
                        </tr>
                    </c:if>

                    </tbody>
                </table>

                <!-- table paging -->
                <div class="page-module">
                    <p class="paging">
                        <blabPaging:paging currentPage="${vo.page }" rowSize="${vo.rowPerPage }" totalCount="${totalCount}" pagingId="admin"/>
                    </p>
                </div>

                <div class="btn-module" style="text-align: center;">
                    <a href="javascript:;" class="btnStyle01" onclick="fnClose();" >Close</a>
                </div>
            </article>
        </div>

    </section>
</form>

<script type="text/javascript">
    //검색/페이징
    function fnSearch(){

        $("#page").val("1");
        $("#searchFrom").prop("method", "get");
        $("#searchFrom").attr('action', '<c:url value="/celloSquareAdmin/terminology/dicList.do"/>').submit();
    }
    function fnAdd(){
        $("#searchFrom").prop("method", "get");
        $("#searchFrom").attr('action', '<c:url value="/celloSquareAdmin/terminology/insertDic.do"/>').submit();
    }
    function fncPage(page) {
        $("#page").val(page);
        $("#searchFrom").prop("method", "get");
        $("#searchFrom").attr('action', '<c:url value="/celloSquareAdmin/terminology/dicList.do"/>').submit();
    }

    function fnClose() {
        self.close();
    }

    function useClick(hashTag, hashTagSeqNo) {
        $("#id").val(hashTagSeqNo);
        $("#searchFrom").prop("method", "get");
        $("#searchFrom").attr('action', '<c:url value="/celloSquareAdmin/terminology/delDic.do"/>').submit();
    }

</script>


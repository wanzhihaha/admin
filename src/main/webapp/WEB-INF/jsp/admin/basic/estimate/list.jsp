<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="listForm" action="./list.do" method="get">
    <input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">

    <section>
        <div class="title"><h3>询价历史</h3></div>
        <div class="Cont_place">
            <article>

                <div class="inputUI_simple">
                    <table class="bd-form s-form" summary="quote api request history search">
                        <tr>
                            <th scope="row">搜索</th>
                            <td>
                                <div class="select-inner">
                                    <select name="svcMedCtgryCd" id="svcMedCtgryCd">
                                        <option value="">全部类型</option>
                                        <option value="AR" <c:if test="${vo.svcMedCtgryCd == 'AR'}">SELECTED</c:if>>空运
                                        </option>
                                        <option value="VS" <c:if test="${vo.svcMedCtgryCd == 'VS'}">SELECTED</c:if>>海运
                                        </option>
                                        <option value="EXP" <c:if test="${vo.svcMedCtgryCd == 'EXP'}">SELECTED</c:if>>
                                            跨境小包
                                        </option>
                                        <option value="EXP_SINOTECH" <c:if test="${vo.svcMedCtgryCd == 'EXP_SINOTECH'}">SELECTED</c:if>>
                                            跨境小包-中技
                                        </option>
                                    </select>
                                </div>
                                <input type="text" placeholder="请输入出发地或到达地"
                                       id="address" name="address"
                                       value="<c:out value="${vo.address }" escapeXml="false"/>"/>
                            <th scope="row">日期</th>
                            <td>
                                <input class="inp-field wid100" type="text" id="statDate" name="statDate"
                                       value="<c:out value='${vo.statDate}'/>" readonly="readonly"/>
                                <span>~</span>
                                <input class="inp-field wid100" type="text" id="endDate" name="endDate"
                                       value="<c:out value='${vo.endDate}'/>" readonly="readonly"/>
                            </td>
                            </td>
                            <td>
                                <div class="btn-module floatR">
                                    <div class="rightGroup">
                                        <a href="javascript:;" class="search" onclick="fnSearch();"
                                           id="btnSearch">搜索</a>
                                        <a href="javascript:;" class="refresh" onclick="resetClick();"
                                           id="btnReset">重置</a>
                                        <a href="javascript:;" class="search" onclick="excelDownLoad();">导出</a>

                                    </div>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>

                <div class="btn-module mgtL2 mgbS">
                    <div class="leftGroup">
                        <span>
                            <span class="tb-text"><strong>Total <span class="colorR"><c:out
                                    value="${totalCount }"/></span> Cases</strong></span>
                        </span>
                    </div>
                    <div class="rightGroup">
                        <select name="rowPerPage" id="rowPerPage" onchange="fnSearch();">
                            <option value="10" <c:if test="${vo.rowPerPage eq '10'}">selected="selected"</c:if>>10
                            </option>
                            <option value="20" <c:if test="${vo.rowPerPage eq '20'}">selected="selected"</c:if>>20
                            </option>
                            <option value="30" <c:if test="${vo.rowPerPage eq '30'}">selected="selected"</c:if>>30
                            </option>
                            <option value="50" <c:if test="${vo.rowPerPage eq '50'}">selected="selected"</c:if>>50
                            </option>
                            <option value="100" <c:if test="${vo.rowPerPage eq '100'}">selected="selected"</c:if>>100
                            </option>
                        </select>
                    </div>
                </div>
                <table class="bd-list inputUI_simple" summary="quote api request history list">
                    <caption>No，조회날짜, 견적번호, 구분, 컨테이너 타입, 출발지, 도착지, 화물정보, 조회결과, 일련번호, 서비스, 조회 운임, 이메일 전송여부</caption>
                    <colgroup>
                        <col width="4%"/>
                        <col width="10%"/>
                        <col width="9%"/>
                        <col width="7%"/>
                        <col width="7%"/>
                        <col width="7%"/>
                        <col width="7%"/>
                        <col width="7%"/>
                        <col width="6%"/>
<%--                        <col width="6%"/>--%>
                        <col width=""/>
                        <col width="10%"/>
                        <%--                        <col width="8%" />--%>
                    </colgroup>
                    <thead>
                    <tr>
                        <th scope="col">序号</th>
                        <th scope="col">查询日期</th>
                        <th scope="col">报价编号</th>
                        <th scope="col">类型</th>
                        <th scope="col">货柜类型</th>
                        <th scope="col">出发地</th>
                        <th scope="col">到达地</th>
                        <th scope="col">货物信息</th>
                        <th scope="col">查询结果</th>
                        <th scope="col">顺序编号</th>
                        <th scope="col">服务</th>
                        <th scope="col">运费</th>
<%--                        <th scope="col">反馈原因</th>--%>
                        <th scope="col">产品类型</th>
                        <th scope="col">访问IP</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="list" items="${list }" varStatus="stat">
                        <tr>
                            <td>
                                <span style="width: 50px;">
                                    <c:out value="${(totalCount - (totalCount-stat.index-1))+((vo.page-1) * vo.rowPerPage) }"/>
                                </span>
                            </td>
                            <td>
                                <c:out value="${list.sendDate }"/>
                            </td>
                            <td>
                                <c:out value="${list.sqNo }"/>
                            </td>
                            <td>
                                <c:out value="${list.svcMedCtgryCd }"/>
                            </td>
                            <td>
                                <c:out value="${list.svcClassCd }"/>
                            </td>
                            <td>
                                <c:out value="${list.deppNm }"/>
                            </td>
                            <td>
                                <c:out value="${list.arrpNm }"/>
                            </td>
                            <td>
                                <c:out value="${list.itemInfo } "/>
                            </td>
                            <td>
                                <c:out value="${list.searchRecordYn } "/>
                            </td>
                            <td>
                                <c:out value="${list.recordNo } "/>
                            </td>
                            <td>
                                <c:out value="${list.carrierName } "/>
                            </td>
                            <td>
                                <c:out value="${list.quotePrice }"/>
                            </td>
<%--                            <td>--%>
<%--                                <c:out value="${list.returnReason }"/>--%>
<%--                            </td>--%>
                            <td>
                                <c:out value="${list.productType }"/>
                            </td>
                            <td>
                                <c:out value="${list.accessIp }"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${fn:length(list) < 1 }">
                        <tr>
                            <td colspan="13" class="blank">暂无数据</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
                <!-- table paging -->
                <div class="page-module">
                    <p class="paging">
                        <blabPaging:paging currentPage="${vo.page }" rowSize="${vo.rowPerPage }"
                                           totalCount="${totalCount}" pagingId="admin"/>
                    </p>
                </div>

            </article>
        </div>
    </section>
</form>

<script type="text/javascript">
    $(document).ready(function () {
        date_module();
    })

    function fncPage(page) {
        $("#page").val(page);
        $("#listForm").submit();
    }

    function excelDownLoad() {
        $("#listForm").prop("method", "post");
        $("#listForm").prop('action', '<c:url value="./excelDownLoad.do"/>').submit();
    }

</script>
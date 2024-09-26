<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>
<style rel="stylesheet">
    .ellipsis {
        width: 100px; /* 文本显示区域大小 */
        overflow: hidden; /* 隐藏文字 */
        text-overflow: ellipsis;
        white-space: nowrap; /* 强制文本不换行，这样超出的部分被截取，显示“...” */
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        date_module_new_v2_0();
    })

    function detail(seq) {
        $("#id").val(seq);
        $("#listForm").prop("method", "get");
        $("#listForm").attr('action', '<c:url value="./detail.do"/>').submit();
    }
</script>


<form id="listForm" action="./list.do" method="post">
    <input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
    <input type="hidden" id="id" name="id" value="<c:out value="${vo.id }"/>"/>
    <section>
        <div class="title"><h3>api日志</h3></div>
        <div class="Cont_place">
            <article>
                <div class="inputUI_simple">
                    <table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
                        <tr>
                            <%--                            <th scope="row">类型</th>--%>
                            <%--                            <td>--%>
                            <%--                                <div class="select-inner">--%>
                            <%--                                    <select name="type" id="type">--%>
                            <%--                                        <option value="">全部</option>--%>
                            <%--                                        <option value="1">注册成功回调</option>--%>
                            <%--                                    </select>--%>
                            <%--                                </div>--%>
                            <%--                            </td>--%>
                            <th scope="row">搜索</th>
                            <td>
                                <div class="select-inner">
                                    <select name="status">
                                        <option value="2" <c:if test="${vo.status == 2 }">selected</c:if>>全部</option>
                                        <option value="1" <c:if test="${vo.status == 1 }">selected</c:if>>成功</option>
                                        <option value="0" <c:if test="${vo.status == 0 }">selected</c:if>>失败</option>
                                    </select>
                                </div>
                                <input type="text" placeholder="请输入任意值" class="inp-field widS mglS" id="searchValue"
                                       name="searchValue"
                                       value="<c:out value="${vo.searchValue }" escapeXml="false"/>"/>
                            </td>
                            <th scope="row">日期</th>
                            <td>
                                <input class="inp-field wid100" type="text" id="startDate" name="startDate"
                                       value="${vo.startDate}" readonly="readonly"/>
                                <span>~</span>
                                <input class="inp-field wid100" type="text" id="endDate" name="endDate"
                                       value="${vo.endDate}" readonly="readonly"/>
                            </td>
                            <td>
                                <div class="btn-module floatR">
                                    <div class="rightGroup">

                                        <a href="javascript:;" class="search" onclick="fnSearch();"
                                           id="btnSearch"><c:out value="搜索"/></a>
                                        <a href="javascript:;" class="refresh" onclick="resetClick();"
                                           id="btnReset">重置</a>
                                        <a href="./siteMap.do" class="refresh" >发送请求</a>
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
                <table class="bd-list inputUI_simple" summary="리스트 영역 입니다.">
                    <caption>번호, 정렬순서, 대표 이미지, 제목, 사용 유무, 시작일, 종료일, 등록자, 등록일, 최종 수정자, 수정일</caption>
                    <colgroup>
                        <col width=""/>
                        <col width=""/>
                        <col width=""/>
                        <col width=""/>
                        <col width=""/>
                        <col width=""/>
                        <col width=""/>
                    </colgroup>
                    <thead>
                    <tr>
                        <th scope="col">序号</th>
                        <th scope="col">类型</th>
                        <th scope="col">状态</th>
                        <th scope="col">URL</th>
                        <th scope="col">请求体</th>
                        <th scope="col">响应</th>
                        <th scope="col">错误信息</th>
                        <th scope="col">创建时间</th>
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
                                <c:out value="${list.typeName }"/>
                            </td>
                            <td>
                                <c:out value="${list.statusName }"/>
                            </td>
                            <td>
                                <a href="javascript:;" onclick="detail('<c:out value='${list.id}'/>');"> <c:out
                                        value="${list.url }"/></a>
                            </td>
                            <td>
                                <c:out value="${list.requestBody }"/>
                            </td>
                            <td>
                                <c:out value="${list.responseBody }"/>
                            </td>
                            <td>
                                <div class="ellipsis" title="${list.exceptionMsg}"> ${list.exceptionMsg}</div>
                            </td>
                            <td>
                                <fmt:formatDate value="${list.createDate }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${fn:length(list) < 1 }">
                        <tr>
                            <td colspan="11" class="blank">没有数据</td>
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
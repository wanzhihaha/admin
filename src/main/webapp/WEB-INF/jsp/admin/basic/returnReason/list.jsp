<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="post">
    <input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
    <input type="hidden" id="id" name="id" value="<c:out value="${vo.id }"/>"/>
    <section>
        <div class="title"><h3>退出原因</h3></div>
        <div class="Cont_place">
            <article>
                <div class="inputUI_simple">
                    <table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
                        <caption>상태, 답변 상태, 기간설정, 직접검색 영역</caption>
                        <%--		<colgroup>--%>
                        <%--			<col width="120px" />--%>
                        <%--			<col width="" />--%>
                        <%--			<col width="300px" />--%>
                        <%--			<col width="" />--%>
                        <%--		</colgroup>--%>
                        <tr>
                            <th scope="row">搜索</th>
                            <td>
                                <div class="select-inner">
                                    <select name="type" id="type">
                                        <option value="">全部</option>
                                        <option value="1" <c:if test="${vo.type == 1}">selected="selected"</c:if>>
                                            有报价原因
                                        </option>
                                        <option value="2" <c:if test="${vo.type == 2}">selected="selected"</c:if>>
                                            无报价原因
                                        </option>
                                    </select>
                                </div>
                                <input type="text" placeholder="请输入" class="inp-field widS mglS" id="reason"
                                       name="reason" value="<c:out value="${vo.reason}" escapeXml="false"/>"/>
                            </td>
                            <td>
                                <div class="btn-module floatR">
                                    <div class="rightGroup">
                                        <a href="javascript:;" class="search" onclick="fnSearch();"
                                           id="btnSearch"><c:out value="搜索"/></a>
                                        <a href="javascript:;" class="refresh" onclick="resetClick();"
                                           id="btnReset">重置</a>
                                    </div>
                                </div>
                            </td>
                        </tr>

                    </table>
                </div>
                <div class="btn-module mgtL2 mgbS">
                    <div class="leftGroup">
			<span>
				<span class="tb-text"><strong>共计 <span class="colorR"><c:out
                        value="${totalCount }"/></span> 条</strong></span>
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
                        <col width="5%"/>
                        <col width=""/>
                        <col width=""/>
                        <col width=""/>
                        <col width=""/>
                        <col width="10%"/>
                        <col width=""/>
                        <col width="10%"/>
                    </colgroup>
                    <thead>
                    <tr>
                        <th scope="col">序号</th>
                        <th scope="col">排序</th>
                        <th scope="col">类型</th>
                        <th scope="col">原因</th>
                        <th scope="col">状态</th>
                        <th scope="col">创建人</th>
                        <th scope="col">创建时间</th>
                        <th scope="col">更新人</th>
                        <th scope="col">更新时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="list" items="${list }" varStatus="stat">
                        <tr>
                            <td>
					<span style="width: 50px;">
						<c:out value="${stat.index + 1}"/>
					</span>
                            </td>
                            <td>
                                <input type="text" name="listOrdb" class="inp-field widM2" value="<c:out value="${list.ordb}" />"
                                       onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3" />
                                <input type="hidden" name="listId" value="<c:out value="${list.id}" />" />
                            </td>
                            <td>
                                <c:out value="${list.typeNm }"/>
                            </td>
                            <td class="txtEll textL2">
                                <a href="javascript:;" onclick="detail('<c:out value='${list.id}'/>');"><c:out
                                        value='${list.reason}' escapeXml="false"/></a>
                            </td>
                            <td>
                                <c:out value="${list.useYnNm }"/>
                            </td>
                            <td>
                                <c:out value="${list.insPersonNm }"/>
                            </td>
                            <td>
                                <c:out value="${list.insDtm }"/>
                            </td>
                            <td>
                                <c:out value="${list.updPersonNm }"/>
                            </td>
                            <td>
                                <c:out value="${list.updDtm }"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${fn:length(list) < 1 }">
                        <tr>
                            <td colspan="11" class="blank">暂无数据</td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>

                <div class="btn-module mgtSM">
                    <div class="leftGroup"><a href="#none" onclick="doSortSave();" class="btnStyle01">自定义排序</a></div>
                    <div class="rightGroup"><a href="#none" onclick="doWrite();" class="btnStyle01">新建</a></div>
                </div>


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
    // 검색 엔터
    $(document).ready(function () {
        $("#searchValue").keypress(function (e) {
            if (e.keyCode == 13) {
                e.preventDefault();
                fnSearch();
            }
        });
    });

    //검색/페이징
    function fnSearch() {
        $("#page").val("1");
        $("#listForm").prop("method", "get");
        $("#listForm").attr('action', '<c:url value="./list.do"/>').submit();
    }

    function fncPage(page) {
        $("#page").val(page);
        $("#listForm").prop("method", "get");
        $("#listForm").attr('action', '<c:url value="./list.do"/>').submit();
    }

    //값 초기화
    function resetClick() {
        $("#searchType").val("");
        $("#searchValue").val("");
        $("#listForm").prop("method", "get");
        $("#listForm").attr('action', '<c:url value="./list.do"/>').submit();
        location.href = '<c:url value="./list.do"/>';
    }

    //등록페이지 이동
    function doWrite() {
        $("#listForm").prop("method", "post");
        $("#listForm").attr('action', '<c:url value="./registerForm.do"/>').submit();
    }

    // 상세페이지 이동
    function detail(seq) {
        $("#id").val(seq);
        $("#listForm").prop("method", "post");
        $("#listForm").attr('action', '<c:url value="./detail.do"/>').submit();
    }

    // 정렬순서 저장

    function doSortSave() {
        if (confirm("确定保存排序吗?")) {
            $("#listForm").prop("method", "post");
            $("#listForm").attr('action', '<c:url value="./doSortOrder.do"/>').submit();
        }
        return false;
    }
</script>

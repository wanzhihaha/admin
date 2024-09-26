<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="post">
    <input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
    <input type="hidden" id="routeSeqNo" name="routeSeqNo" value="<c:out value="${vo.routeSeqNo}"/>"/>
    <input type="hidden" id="listRouteSeqNo" name="listRouteSeqNo">
    <input type="hidden" id="useYn" name="useYn">
    <section>
        <div class="title"><h3>线路管理 </h3></div>
        <div class="Cont_place">
            <article>
                <div class="inputUI_simple">
                    <table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
                        <caption>상태, 답변 상태, 기간설정, 직접검색 영역</caption>
                        <colgroup>
                            <col width="120px"/>
                            <col width=""/>
                            <col width="300px"/>
                        </colgroup>
                        <tr>
                            <th scope="row">搜索</th>
                            <td>

                                <div class="select-inner">
                                    <select name="productMode" id="productMode">
                                        <option value="">线路类型</option>
                                    </select>
                                </div>
                                <input type="text" placeholder="请输入搜索值" class="inp-field widS mglS" id="searchValue"
                                       name="searchValue"
                                       value="<c:out value="${vo.searchValue }" escapeXml="false"/>"/>
                            </td>
                            <td>
                                <div class="btn-module floatR">
                                    <div class="rightGroup">
                                        <a href="javascript:;" class="search" onclick="fnSearch();"
                                           id="btnSearch">搜索</a>
                                        <a href="javascript:;" class="refresh" onclick="resetClick();"
                                           id="btnReset">重置</a>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </article>
            <article>
                <div class="btn-module mgtL2 mgbS">
                    <div class="leftGroup">
                        <span class="tb-text"><strong>共计 <span class="colorR"><c:out
                                value="${totalCount }"></c:out></span> 条</strong></span>
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
                    <caption>NO</caption>
                    <colgroup>
                        <col width="2%"/>
                        <col width="6%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="10%"/>
                        <col width="6%"/>
                        <col width="6%"/>
                        <col width="6%"/>
                        <col width="8%"/>
                        <col width="8%"/>
                        <col width="8%"/>
                        <col width="8%"/>
                    </colgroup>
                    <thead>
                    <tr>
                        <th scope="col">序号</th>
                        <th scope="col">国家code</th>
                        <th scope="col">线路类型</th>
                        <th scope="col">产品id</th>
                        <th scope="col">产品名</th>
                        <th scope="col">起点code</th>
                        <th scope="col">终点Node</th>
                        <th scope="col">状态</th>
                        <th scope="col">创建人</th>
                        <th scope="col">创建时间</th>
                        <th scope="col">更新人</th>
                        <th scope="col">更新时间</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="list" items="${list}" varStatus="stat">
                        <tr>
                            <td>
					<span style="width: 50px;">					
						<c:out value="${ stat.index+1}"/>
					</span>
                            </td>
                            <td>
                                <a href="javascript:;" onclick="detail('<c:out value="${list.routeSeqNo}"/>');">
                                    <c:out value="${list.nationCd }" escapeXml="false"/>
                                </a>
                            </td>
                            <td>
                                <a href="javascript:;" onclick="detail('<c:out value="${list.routeSeqNo}"/>');">
                                    <c:out value="${list.productModeNm}" escapeXml="false"/>
                                </a>
                            </td>
                            <td>
                                <a href="javascript:;" onclick="detail('<c:out value="${list.routeSeqNo}"/>');">
                                    <c:out value="${list.productId }" escapeXml="false"/>
                                </a>
                            </td>
                            <td>
                                <a href="javascript:;" onclick="detail('<c:out value="${list.routeSeqNo}"/>');">
                                    <c:out value="${list.productNm }" escapeXml="false"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${list.fromNode }" escapeXml="false"/>
                            </td>
                            <td>
                                <c:out value="${list.toNode }" escapeXml="false"/>
                            </td>
                            <td>
                                <c:out value="${list.useYnNm }" escapeXml="false"/>
                            </td>
                            <td>
                                <c:out value="${list.insPersonNm}"/>
                            </td>
                            <td>
                                <c:out value="${list.insDtm}"/>
                            </td>
                            <td>
                                <c:out value="${list.updPersonNm}"/>
                            </td>
                            <td>
                                <c:out value="${list.updDtm}"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${fn:length(list) < 1 }">
                        <tr>
                            <td colspan="12" class="blank">暂无数据</td>
                        </tr>
                    </c:if>

                    </tbody>
                </table>


                <div class="btn-module mgtSM">
<%--                    <div class="leftGroup"><a href="javascript:;" onclick="doUseSave('Y');" class="btnStyle01">save as--%>
<%--                        use</a></div>--%>
<%--                    <div class="leftGroup"><a href="javascript:;" onclick="doUseSave('N');" class="btnStyle01">save as--%>
<%--                        unused</a></div>--%>
                    <div class="rightGroup"><a href="javascript:;" onclick="doRegister();" class="btnStyle01">新增</a></div>
                </div>
                <!-- table paging -->

                <div class="page-module">
                    <p class="paging">
                        <blabPaging:paging currentPage="${vo.page }" totalCount="${totalCount}"
                                           rowSize="${vo.rowPerPage }" pagingId="admin"/>
                    </p>
                </div>

            </article>
        </div>

    </section>
</form>


<script type="text/javascript">
    var routeStatusArray = [];
    $(document).ready(function () {
        $.fnGetCodeSelectAjax("sGb=QUOTE_CATEGORY_GRP&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "productMode", "${vo.searchType1}", "selectbox", "");
        $("#searchValue").keypress(function (e) {
            if (e.keyCode == 13) {
                e.preventDefault();
                searchClick();
            }
        });
    });
    $("#statusAll").click(function () {
        var status = $(this).prop("checked");
        $("input:not(#statusAll)").prop("checked", status);
        chText();
    })

    // 反选
    $("input:not(#statusAll)").click(function () {
        var a = $("input:not(#statusAll)").length;
        var b = $("input:not(#statusAll):checked").length
        $("#statusAll").prop("checked", a == b);
        chText();
    })

    function chText() {
        routeStatusArray = [];
        $("input:not(#statusAll):checked").each(function () {
            routeStatusArray.push($(this).val());
        })
        console.log(routeStatusArray);
    }


    //검색 , 페이징,
    function fncPage(page) {
        $("#page").val(page);
        $("#listForm").prop("method", "get");
        $("#listForm").submit();
    }

    function searchClick() {
        $("#page").val("1");
        $("#listForm").prop("method", "get");
        $("#listForm").submit();
    }

    function fnSearch() {
        $("#page").val("1");
        $("#listForm").prop("method", "get");
        $("#listForm").prop("action", "list.do");
        $("#listForm").submit();
    }

    //값 초기화
    function resetClick() {
        $("#searchValue").val("");
        $("input:radio[id='resetRadio']").prop("checked", true);
        $("#listForm").prop("method", "get");
        $("#listForm").prop("action", "list.do");
        $("#listForm").submit();
    }

    //등록페이지 이동
    function doRegister() {
        $("#listForm").prop("method", "post");
        $("#listForm").prop("action", "registerForm.do");
        $("#listForm").submit();
    }

    //업데이트 이동
    function detail(sn) {
        $("#routeSeqNo").val(sn);
        $("#listForm").prop("method", "post");
        $("#listForm").prop("action", "detail.do");
        $("#listForm").submit();
    }

    //정렬순서 저장
    function doUseSave(val) {
        if (routeStatusArray.length < 1) {
            alert("Please Select Route Data")
            return false;
        }

        if (confirm("Do you really want to fix it?")) {
            var str = routeStatusArray.toString();
            console.log(str);
            $('#listRouteSeqNo').val(str);
            $('#useYn').val(val);
            $("#listForm").prop("method", "post");
            $("#listForm").prop("action", "updateStatus.do");
            $("#listForm").submit();
        }
        return false;
    }

</script>
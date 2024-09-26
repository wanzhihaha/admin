<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="post">
    <input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
    <input type="hidden" id="nodeCd" name="nodeCd" value="<c:out value="${vo.nodeCd}"/>"/>
    <input type="hidden" id="nodeCdArrayStr" name="nodeCdArrayStr">
    <input type="hidden" id="isHot" name="isHot">
    <input type="hidden" id="id" name="id">
    <section>
        <div class="title"><h3>地址节点</h3></div>
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
                            <th scope="row">查询</th>
                            <td>
                                <div class="select-inner">
                                    <select name="searchType1" id="searchType1">
                                        <option value=""
                                                <c:if test="${vo.searchType1 eq ''}">selected="selected"</c:if>>全部运输方式
                                        </option>
                                        <option value="VS"
                                                <c:if test="${vo.searchType1 eq 'VS'}">selected="selected"</c:if>>海运
                                        </option>
                                        <option value="AR"
                                                <c:if test="${vo.searchType1 eq 'AR' }">selected="selected"</c:if>>空运
                                        </option>
                                    </select>

                                    <select name="continentCd" id="continentCd">
                                        <option value=""
                                                <c:if test="${continents.cd eq ''}">selected="selected"</c:if>>全部大陆
                                        </option>
                                        <c:forEach items="${continentList}" var="continents">
                                        <option value="${continents.cd}"
                                                <c:if test="${continents.cd eq vo.continentCd}">selected="selected"</c:if>>${continents.name}
                                        </option>
                                        </c:forEach>
                                    </select>

                                 <%--   <select name="searchType" id="searchType">
                                        <option value=""
                                                <c:if test="${empty vo.searchType  }">selected="selected"</c:if>>操作
                                        </option>
                                        <option value="1"
                                                <c:if test="${vo.searchType == '1' }">selected="selected"</c:if>>热门
                                        </option>
                                        <option value="0"
                                                <c:if test="${vo.searchType == '0' }">selected="selected"</c:if>>非热门
                                        </option>
                                    </select>--%>
                                </div>
                                <input type="text" placeholder="输入搜索值" class="inp-field widS mglS" id="searchValue"
                                       name="searchValue"
                                       value="<c:out value="${vo.searchValue }" escapeXml="false"/>"/>
                            </td>
                            <td>
                                <div class="btn-module floatR">
                                    <div class="rightGroup">
                                        <a href="javascript:;" class="search" onclick="fnSearch();"
                                           id="btnSearch">查询</a>
                                        <a href="javascript:;" class="refresh" onclick="resetClick();"
                                           id="btnReset">重置</a>
                                        <a href="javascript:;" class="search" onclick="excelDownLoad();"
                                           id="ExcelDownload">导出</a>
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

                    <thead>
                    <tr>
                        <th scope="col">序号</th>
                        <%--<th scope="col">Check<br>
                            <label>
                                <input type="checkbox" id="statusAll">
                            </label>
                        </th>--%>
                        <th scope="col">排序</th>
                        <th scope="col">所属大陆</th>
                        <th scope="col">所属国家</th>
                        <th scope="col">类型</th>
                        <th scope="col">中文节点</th>
                        <th scope="col">英文节点</th>
                        <th scope="col">节点cd</th>
                        <th scope="col">状态</th>
                        <th scope="col">创建人</th>
                        <th scope="col">创建时间</th>
                        <th scope="col">更新人</th>
                        <th scope="col">更新时间</th>
                        <%--                        <th scope="col">操作</th>--%>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="list" items="${list}" varStatus="stat">
                        <tr>
                            <td>
					<span style="width: 50px;">					
						<c:out value="${stat.index+1}"/>
					</span>
                            </td>
                            <td>
                                <input type="text" name="listSortOrder" class="inp-field widM2"
                                       value="<c:out value="${list.ordb}" />"
                                       onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3"/>
                                <input type="hidden" name="listIds"
                                       value="<c:out value="${list.id}" />"/>
                            </td>
                            <td>
                                <c:out value="${list.continentName }" escapeXml="false"/>
                            </td>
                            <td>
                                <c:out value="${list.nationCnNm }" escapeXml="false"/>
                            </td>
                            <td>
                                <c:out value="${list.productModeNm }" escapeXml="false"/>
                            </td>
                                <%--<td>
                                    <input type="checkbox" value="<c:out value="${list.nodeCd}" />">
                                </td>--%>
                            <td>
                                <c:out value="${list.nodeCnNm }" escapeXml="false"/>
                            </td>
                            <td>
                                <a href="javascript:;" onclick="detail('<c:out value="${list.nodeCd}"/>');">
                                    <c:out value="${list.nodeEngNm }" escapeXml="false"/>
                                </a>
                            </td>

                            <td>
                                <a href="javascript:;" onclick="detail('<c:out value="${list.nodeCd}"/>');">
                                    <c:out value="${list.nodeCd}" escapeXml="false"/>
                                </a>
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
                                <%--                            <td>--%>
                                <%--                                <c:choose>--%>
                                <%--                                    <c:when test="${list.isHot == 0 }">--%>
                                <%--                                        <a href="javascript:;" onclick="setHotOrNot(${list.id} ,1)">设置为热门</a>--%>
                                <%--                                    </c:when>--%>
                                <%--                                    <c:otherwise>--%>
                                <%--                                        <a href="javascript:;" onclick="setHotOrNot(${list.id} ,0)">去除热门</a>--%>
                                <%--                                    </c:otherwise>--%>
                                <%--                                </c:choose>--%>
                                <%--                            </td>--%>
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
                    <div class="leftGroup"><a href="javascript:;" onclick="doSortSave();" class="btnStyle01">保存排序</a>
                    </div>
                    <div class="rightGroup"><a href="javascript:;" onclick="doRegister();" class="btnStyle01">新建</a>
                    </div>
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
<a id="excelDownd" style="display:none"></a>

<script type="text/javascript">
    var nodeStatusArray = [];
    $(document).ready(function () {
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
        nodeStatusArray = [];
        $("input:not(#statusAll):checked").each(function () {
            nodeStatusArray.push($(this).val());
        })
        console.log(nodeStatusArray);
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


    //등록페이지 이동
    function doRegister() {
        $("#listForm").prop("method", "post");
        $("#listForm").prop("action", "registerForm.do");
        $("#listForm").submit();
    }

    //업데이트 이동
    function detail(sn) {
        $("#nodeCd").val(sn);
        console.log(sn);
        console.log($("#nodeCd").val())
        $("#listForm").prop("method", "post");
        $("#listForm").prop("action", "detail.do");
        $("#listForm").submit();
    }

    //정렬순서 저장
    function doConfirmSave() {
        if (nodeStatusArray.length < 1) {
            alert("Please Select Node Data")
            return false;
        }

        if (confirm("Do you really want to fix it?")) {
            var str = nodeStatusArray.toString();
            console.log(str);
            $('#nodeCdArrayStr').val(str);

            console.log($('#nodeCdArrayStr').val());
            $("#listForm").prop("method", "post");
            $("#listForm").prop("action", "updateStatus.do");
            $("#listForm").submit();
        }
        return false;
    }

    function excelDownLoad() {
        export_Excel("#listForm", "node");
    }

    function setHotOrNot(sn, isHot) {
        if (confirm("确定设置吗?")) {
            $("#id").val(sn);
            $("#isHot").val(isHot);
            $("#listForm").prop("method", "post");
            $("#listForm").prop("action", "setHotOrNot.do");
            $("#listForm").submit();
        }
    }

    function doSortSave() {
        var arr_order = new Array();//定义数组对象
        var arr_listSortOrder = $('input[name=listSortOrder]');
        arr_listSortOrder.each(function () {
            arr_order.push($(this).val());//遍历存入数组
        })
        console.log("arr_order", arr_order)
        let number = ordb_p_x(arr_order);
        console.log("number", number)
        if (number > 0) {
            alert("输入了重复的排序号: " + number)
            return;
        }

        if (confirm("确定保存排序吗?")) {
            $("#listForm").prop("method", "post");
            $("#listForm").prop("action", "doSortOrder.do");
            $("#listForm").submit();
        }
        return false;
    }
</script>
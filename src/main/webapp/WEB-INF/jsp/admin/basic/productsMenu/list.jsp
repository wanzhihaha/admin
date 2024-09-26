<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>


<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="post">
    <input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
    <input type="hidden" id="id" name="id"/>

    <section>
        <div class="title"><h3>产品菜单</h3></div>
        <div class="Cont_place">
            <article>
                <div class="inputUI_simple">
                    <table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
                        <caption>상태, 답변 상태, 기간설정, 직접검색 영역</caption>
                        <tr>
                            <%--		<th scope="row">搜索</th>
                                    <td>
                                        <input type="text" placeholder="请输入搜索标题"  id="searchValue" name="searchValue" value="<c:out value="${vo.searchValue }" escapeXml="false"/>" />
                                    </td>

                                    <td>
                                        <div class="btn-module floatR">
                                            <div class="rightGroup">
                                                <a href="javascript:;" class="search" onclick="fnSearch();" id="btnSearch">搜索</a>
                                                <a href="javascript:;" class="refresh" onclick="resetClick();" id="btnReset">重置</a>
                                            </div>
                                        </div>
                                    </td>--%>

                        </tr>
                    </table>
                </div>
            </article>
            <article>
                <div class="btn-module mgtL2 mgbS">
                    <div class="leftGroup">
			<span>
				<span class="tb-text"><strong>总计 <span class="colorR"><c:out value="${totalCount }"/></span> 条</strong></span>
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
                    <caption>NO</caption>

                    <thead>
                    <tr>
                        <th scope="col">序号</th>
                        <th scope="col">排序</th>
                        <th scope="col">菜单名</th>
                        <th scope="col">创建人</th>
                        <th scope="col">创建时间</th>
                        <th scope="col">更新人</th>
                        <th scope="col">更新时间</th>
                        <th scope="col">状态</th>
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
                                <input type="text" name="listOrdb" class="inp-field widM2"
                                       value="<c:out value="${list.ordb}" />"
                                       onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3"/>
                                <input type="hidden" name="listId" value="<c:out value="${list.id}" />"/>
                            </td>
                            <td>
                                <a href="javascript:;" onclick="detail('${list.id}');"><c:out value="${list.name } "
                                                                                              escapeXml="false"/></a>
                            </td>
                            <td>
                                <c:out value="${list.insPersonNm }" escapeXml="false"/>
                            </td>
                            <td>
                                <fmt:formatDate value="${list.insDtm }" pattern="yyyy-MM-dd"/>
                            </td>
                            <td>
                                <c:out value="${list.updPersonNm }" escapeXml="false"/>
                            </td>
                            <td>
                                <fmt:formatDate value="${list.updDtm }" pattern="yyyy-MM-dd"/>
                            </td>
                            <td>
                                <c:out value="${list.useYnNm }" escapeXml="false"/>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${fn:length(list) < 1 }">
                        <tr>
                            <td colspan="9" class="blank">暂无数据</td>
                        </tr>
                    </c:if>

                    </tbody>
                </table>

                <div class="btn-module mgtSM">
                    <div class="leftGroup"><a href="#none" onclick="doSortSave();" class="btnStyle01">自定义排序</a></div>
                    <div class="rightGroup"><a href="#none" onclick="doRegister();" class="btnStyle01">新增</a></div>
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
<a id="excelDownd" style="display:none"></a>

<script type="text/javascript">
    $(document).ready(function () {
        $("#searchValue").keypress(function (e) {
            if (e.keyCode == 13) {
                e.preventDefault();
                fnSearch();
            }
        });
    });

    //등록페이지 이동
    function doRegister() {
        $("#listForm").prop("method", "post");
        $("#listForm").attr('action', '<c:url value="./registerForm.do"/>').submit();
    }

    // 상세페이지 이동
    function detail(id) {
        $("#id").val(id);
        $("#listForm").prop("method", "post");
        $("#listForm").attr('action', '<c:url value="./detail.do"/>').submit();
    }



    function doSortSave() {
        var arr_order = new Array();//定义数组对象
        var arr_listSortOrder = $('input[name=listOrdb]');
        arr_listSortOrder.each(function () {
            let val = $(this).val();
            arr_order.push(val);//遍历存入数组
        })
        console.log("arr_order", arr_order)
        let number = ordb_p_x(arr_order);
        console.log("number", number)
        if (number > 0) {
            alert("输入了重复的排序号: " + number)
            return;
        }
        if (confirm("确定保存排序吗？")) {
            $("#listForm").prop("method", "post");
            $("#listForm").attr('action', '<c:url value="./doSortOrder.do"/>').submit();
        }
        return false;
    }
</script>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="post">
    <input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
    <input type="hidden" id="nationSeqNo" name="nationSeqNo" value="<c:out value="${vo.nationSeqNo }"/>"/>
    <input type="hidden" id="metaSeqNo" name="metaSeqNo">
    <input type="hidden" id="isHot" name="isHot">
    <section>
        <div class="title"><h3>国家管理</h3></div>
        <div class="Cont_place">
            <article>
                <div class="inputUI_simple">
                    <table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
                        <caption>상태, 답변 상태, 기간설정, 직접검색 영역</caption>

                        <tr>
                            <th scope="row">搜索</th>
                            <td>
                                <div class="select-inner">
                                    <select name="continentCd" id="continentCd">
                                        <option value="">州/大陆</option>
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

                                <input type="text" placeholder="请输入搜索值（模糊搜索）"
                                       id="searchValue" name="searchValue"
                                       value="${vo.searchValue }"/>
                            </td>
                            <td>
                                <div class="btn-module floatR">
                                    <div class="rightGroup">
                                        <a href="javascript:;" class="search" onclick="fnSearch();"
                                           id="btnSearch">搜索</a>
                                        <a href="javascript:;" class="refresh" onclick="resetClick();"
                                           id="btnReset">重置</a>
                                        <a href="javascript:;" class="search" onclick="excelDownLoad();"
                                           id="ExcelDownload">导出</a>
                                    </div>
                                </div>
                            </td>
                            <td>

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
                        <th scope="col">排序</th>
                        <th scope="col">图标</th>
                        <th scope="col">州/大陆</th>
                        <th scope="col">国家(英文)</th>
                        <th scope="col">国家(中文)</th>
                        <th scope="col">快递</th>
                        <th scope="col">状态</th>
                        <%--                        <th scope="col">创建人</th>--%>
                        <%--                        <th scope="col">创建日期</th>--%>
                        <th scope="col">修改人</th>
                        <th scope="col">修改日期</th>
<%--                        <th scope="col">操作</th>--%>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="list" items="${list}" varStatus="stat">
                        <tr>
                            <td>
					<span style="width: 50px;">					
						<c:out value="${(totalCount - (totalCount-stat.index-1))+((vo.page-1) * vo.rowPerPage) }"/>
					</span>
                            </td>
                            <td>
                                <input type="text" name="listSortOrder" class="inp-field widM2"
                                       value="<c:out value="${list.ordb}" />"
                                       onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3"/>
                                <input type="hidden" name="listNationSeqNo"
                                       value="<c:out value="${list.nationSeqNo}" />"/>
                            </td>
                            <td>
                                <c:if test="${list.imgFilePath ne null && list.imgFilePath.length() > 0}">
                                    <img src="<blabProperty:value key="system.admin.path"/>/country/ImgView.do?nationSeqNo=<c:out value='${list.nationSeqNo}'/>"
                                         style="max-width: 100px; vertical-align: middle;"/>
                                </c:if>

                            </td>
                            <td>
                                <c:out value="${list.continentNm }" escapeXml="false"/>
                            </td>
                            <td>
                                <a href="javascript:;" onclick="detail('<c:out value="${list.nationSeqNo}"/>');">
                                    <c:out value="${list.nationNm}" escapeXml="false"/>
                                </a>
                            </td>
                            <td>
                                <a href="javascript:;" onclick="detail('<c:out value="${list.nationSeqNo}"/>');">
                                    <c:out value="${list.nationCnNm }" escapeXml="false"/>
                                </a>
                            </td>
                            <td>
                                <c:out value="${list.expressUseValue}"/>
                            </td>
                            <td>
                                <c:out value="${list.useYnNm}"/>
                            </td>
                                <%--                            <td>--%>
                                <%--                                <c:out value="${list.insPersonNm}"/>--%>
                                <%--                            </td>--%>
                                <%--                            <td>--%>
                                <%--                                <c:out value="${list.insDtm}"/>--%>
                                <%--                            </td>--%>
                            <td>
                                <c:out value="${list.updPersonNm}"/>
                            </td>
                            <td>
                                <c:out value="${list.updDtm}"/>
                            </td>
                            <%--<td>
                                <c:choose>
                                    <c:when test="${list.isHot == 0 }">
                                        <a href="javascript:;" onclick="setHotOrNot(${list.nationSeqNo} ,1)">设置为热门</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="javascript:;" onclick="setHotOrNot(${list.nationSeqNo} ,0)">去除热门</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>--%>
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
                    <div class="rightGroup"><a href="javascript:;" onclick="doRegister();" class="btnStyle01">新增</a>
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
<script type="text/javascript">

    $(document).ready(function () {
        $.fnGetCodeSelectAjax("sGb=CONTINENT_CD&langCd=A", "continentCd", "${vo.continentCd}", "selectbox", "");
        $("#searchValue").keypress(function (e) {
            if (e.keyCode == 13) {
                e.preventDefault();
                fnSearch();
            }
        });
    });


    //업데이트 이동
    function detail(sn) {
        $("#nationSeqNo").val(sn);
        $("#listForm").prop("method", "post");
        $("#listForm").prop("action", "detail.do");
        $("#listForm").submit();
    }

    function setHotOrNot(sn, isHot) {
        if (confirm("确定设置吗?")) {
            $("#nationSeqNo").val(sn);
            $("#isHot").val(isHot);
            $("#listForm").prop("method", "post");
            $("#listForm").prop("action", "setHotOrNot.do");
            $("#listForm").submit();
        }
    }

    //정렬순서 저장
    function doSortSave() {
        if ($('#continentCd').val()) {
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
        }
        if (confirm("确定保存排序吗?")) {
            $("#listForm").prop("method", "post");
            $("#listForm").prop("action", "doSortOrder.do");
            $("#listForm").submit();
        }
        return false;
    }

</script>
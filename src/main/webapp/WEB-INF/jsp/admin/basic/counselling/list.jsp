<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<a id="excelDownd" style="display:none"></a>
<form id="listForm" action="./list.do" method="post">
    <input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
    <section>
        <div class="title"><h3>信息导出</h3></div>
        <div class="Cont_place">
            <article>
                <div class="inputUI_simple">
                    <table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
                        <tr>
                            <th scope="row">类型</th>
                            <td>
                                <div class="select-inner">
                                    <select name="category" id="category">
                                        <option value="">全部</option>
                                        <option value="加入会员">加入会员</option>
                                        <option value="服务咨询">服务咨询</option>
                                        <option value="产品咨询">产品咨询</option>
                                        <option value="报价咨询">报价咨询</option>
                                        <option value="合作咨询">合作咨询</option>
                                        <option value="其他咨询">其他咨询</option>
                                        <option value="whpp">白皮书</option>
                                        <option value="market">市场趋势</option>
                                    </select>
                                </div>
                            </td>
                            <th scope="row">来源</th>
                            <td>
                                <div class="select-inner">
                                    <select name="source" id="source">
                                        <option value="">全部</option>
                                    </select>
                                </div>
                            </td>
                            <th scope="row">Date</th>
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
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="btn-module floatR">
                                    <div class="rightGroup">
                                        <a href="javascript:;" class="search" onclick="excelDownLoad();"
                                           style="width:110px;" id="ExcelDownload">Excel 导出</a>
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
                        <col width=""/>
                        <col width=""/>
                        <col width=""/>
                        <col width=""/>
                    </colgroup>
                    <thead>
                    <tr>
                        <th scope="col">序号</th>
                        <th scope="col">日期</th>
                        <th scope="col">来源</th>
                        <th scope="col">类别</th>
                        <th scope="col">类型</th>
                        <th scope="col">称呼</th>
                        <th scope="col">公司邮箱</th>
                        <th scope="col">联系电话</th>
                        <th scope="col">公司名称</th>
                        <th scope="col">预计月物流费</th>
                        <th scope="col">咨询内容</th>
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
                                <fmt:formatDate value="${list.createDate }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>
                            </td>
                            <td>
                                <c:out value="${list.source }"/>
                            </td>
                            <td>
                                <c:out value="${list.category }"/>
                            </td>
                            <td>
                                <c:out value="${list.type }"/>
                            </td>
                            <td>
                                <c:out value="${list.name }"/>
                            </td>
                            <td>
                                <c:out value="${list.emailAddress }"/>
                            </td>
                            <td>
                                <c:out value="${list.mobilePhone }"/>
                            </td>
                            <td>
                                <c:out value="${list.company }"/>
                            </td>
                            <td>
                                <c:out value="${list.estimatedMonthlyLogisticsCost }"/>
                            </td>
                            <td>
                                <c:out value="${list.comment }"/>
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

<script type="text/javascript">
    $(document).ready(function () {
        $("#startDate, #endDate").datepicker({
            dateFormat: "yy-mm-dd"
            , showOn: 'button'
            , buttonImage: '/static/images/cal.png'
            , buttonImageOnly: true
            , buttonText: "달력"
            , changeYear: true
            , maxDate: new Date()
            , defaultDate: new Date()
        });

        var da = new Date();
        var year = da.getFullYear();
        var month = da.getMonth() + 1;
        var date = da.getDate();
        var today = [year, month, date].join('-');
        $("#startDate").val($("#startDate").val() == '' ? today : $("#startDate").val());
        $("#endDate").val($("#endDate").val() == '' ? today : $("#endDate").val());

        $("#startDate").on("change keyup paste", function () {
            var stDt = $("#startDate").val();
            var stDtArr = stDt.split("-");
            var stDtObj = new Date(stDtArr[0], Number(stDtArr[1]) - 1, stDtArr[2]);
            var enDt = $("#endDate").val();
            var enDtArr = enDt.split("-");
            var enDtObj = new Date(enDtArr[0], Number(enDtArr[1]) - 1, enDtArr[2]);
            var betweenDay = (enDtObj.getTime() - stDtObj.getTime()) / 1000 / 60 / 60 / 24;

            if (betweenDay < 0) {
                alert("The start date is less than the end date. Please re-enter.");
                $("#startDate").focus();
                $("#startDate").val("");
                return false;
            }
        });

        $("#endDate").on("change keyup paste", function () {
            var stDt = $("#startDate").val();
            var stDtArr = stDt.split("-");
            var stDtObj = new Date(stDtArr[0], Number(stDtArr[1]) - 1, stDtArr[2]);
            var enDt = $("#startDate").val();
            var enDtArr = enDt.split("-");
            var enDtObj = new Date(enDtArr[0], Number(enDtArr[1]) - 1, enDtArr[2]);
            var betweenDay = (enDtObj.getTime() - stDtObj.getTime()) / 1000 / 60 / 60 / 24;

            if (betweenDay < 0) {
                alert("The end date is greater than the start date. Please re-enter.");
                $("#endDate").focus();
                $("#endDate").val("");
                return false;
            }
        });
        $.fnGetCodeSelectAjax("sGb=contact_source&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "source", "${vo.source}", "selectbox", "");

    })

    function excelDownLoad() {
        export_Excel("#listForm", "counselling");
    }

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
        $("#category").val("");
        $("#searchValue").val("");
        $("#listForm").prop("method", "get");
        $("#listForm").attr('action', '<c:url value="./list.do"/>').submit();
        location.href = '<c:url value="./list.do"/>';
    }

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
        $("#category").val("");
        $("#searchValue").val("");
        $("#listForm").prop("method", "get");
        $("#listForm").attr('action', '<c:url value="./list.do"/>').submit();
        location.href = '<c:url value="./list.do"/>';
    }
</script>
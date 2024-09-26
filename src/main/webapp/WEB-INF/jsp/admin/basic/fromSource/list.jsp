<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<a id="excelDownd" style="display:none"></a>
<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="post">
    <input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
    <input type="hidden" id="id" name="id" value="<c:out value="${vo.id }"/>" class="inp-field widL"/>

    <section>
        <div class="title"><h3>来源</h3></div>
        <div class="Cont_place">
            <article>
                <div class="inputUI_simple">
                    <table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
                        <caption>상태, 답변 상태, 기간설정, 직접검색 영역</caption>
                        <tr>
                            <th scope="row">搜索</th>
                            <td>
                                <div class="select-inner">
                                    <select id="type" name="type">
                                        <option value="">全部类型</option>
                                    </select>
                                </div>
                                <input type="text" placeholder="请输入" class="inp-field widS mglS" id="sourceVal"
                                       name="sourceVal" value="<c:out value="${vo.sourceVal}" escapeXml="false"/>"/>
                            </td>


                            <td>
                                <div class="btn-module floatR">
                                    <div class="rightGroup">
                                        <a href="javascript:;" class="search" onclick="fnSearch();"
                                           id="btnSearch"><c:out value="搜索"/></a>
                                        <a href="javascript:;" class="refresh" onclick="resetClick();"
                                           id="btnReset">重置</a>
                                        <%--javascript:alert('哪有这么快做好，快别点了！！')--%>
                                        <a href="./downloadMB.do" class="search" style="width:110px;" id="downTemplete">模板下载</a>
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
                        <tr>
                            <th>批量上传:</th>
                            <td style="display: flex;">
                                <div class="btn-module floatR">
                                    <div class="rightGroup" style="width: 214px;display: flex;align-items: center">
                                        <input type="file" name="file" onchange="UploadFile()"/>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="btn-module mgtL2 mgbS">
                    <div class="leftGroup">
			<span>
				<span class="tb-text"><strong>总共 <span class="colorR"><c:out value="${totalCount }"/></span> 条</strong></span>
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
                    <thead>
                    <tr>
                        <th scope="col">序号</th>
                        <th scope="col">名称</th>
                        <th scope="col">渠道</th>
                        <th scope="col">推送类型</th>
                        <th scope="col">fromsource值</th>
<%--                        <th scope="col">注册按钮标识符</th>--%>
<%--                        <th scope="col">app注册成功标识符</th>--%>
                        <th scope="col">创建人</th>
                        <th scope="col">创建时间</th>
                        <th scope="col">更新人</th>
                        <th scope="col">更新时间</th>
                        <th scope="col">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="list" items="${list }" varStatus="stat">
                        <tr>
                            <td>
					<span style="width: 50px;">
						<c:out value="${ stat.index+1}"/>
					</span>
                            </td>
                            <td>
                                <a href="javascript:;" onclick="detail('<c:out value='${list.id}'/>');"> <c:out
                                        value="${list.name }"/></a>
                            </td>
                            <td>
                                <a href="javascript:;" onclick="detail('<c:out value='${list.id}'/>');"> <c:out
                                        value="${list.remark }"/></a>
                            </td>
                            <td>
                                <a href="javascript:;" onclick="detail('<c:out value='${list.id}'/>');"> <c:out
                                        value="${list.typeName }"/> </a>
                            </td>
                            <td class="txtEll textL2">
                                <a href="javascript:;" onclick="detail('<c:out value='${list.id}'/>');"><c:out
                                        value='${list.sourceVal}' escapeXml="false"/></a>
                            </td>
<%--                            <td>--%>
<%--                                <c:out value="${list.identifier }"/>--%>
<%--                            </td>--%>
<%--                            <td>--%>
<%--                                <c:out value="${list.appSuccessIdentifier }"/>--%>
<%--                            </td>--%>
                            <td>
                                <c:out value="${list.insPersonNm }"/>
                            </td>
                            <td>
                                <fmt:formatDate value="${list.insDtm }" type="date" pattern="yyyy-MM-dd"/>
                            </td>
                            <td>
                                <c:out value="${list.updPersonNm }"/>
                            </td>
                            <td>
                                <fmt:formatDate value="${list.updDtm }" type="date" pattern="yyyy-MM-dd"/>
                            </td>
                            <td>
                                <a href="#none" onclick="urlCopy('${list.shareLink }');">复制链接</a>
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

                <div class="btn-module mgtSM">
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
        $.fnGetCodeSelectAjax("sGb=REGISTER_STATISTICS_CODE&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "type", "${vo.type}", "selectbox", "");

        $("#searchValue").keypress(function (e) {
            console.log("버튼이 눌렸습니다.");
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
        $("#sourceVal").val("");
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

    function UploadFile() {
        /*alert('哪有这么快做好，快别点了！！')
        return*/
        $("#listForm").prop("method", "post");
        $("#listForm").attr('action', '<c:url value="./upload.do"/>').attr('enctype', 'multipart/form-data').submit();
    }

    function excelDownLoad() {
        export_Excel("#listForm", "fromSource");
    }
</script>

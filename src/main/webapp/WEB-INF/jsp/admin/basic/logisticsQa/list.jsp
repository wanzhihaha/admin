<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>


<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="post">
	<input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
	<input type="hidden" id="id" name="id" />

	<section>
		<div class="title"><h3>SIM卡管理</h3></div>
		<div class="Cont_place">
			<article>
				<div class="inputUI_simple">
					<table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
						<caption>상태, 답변 상태, 기간설정, 직접검색 영역</caption>
						<tr>
							<th scope="row">搜索</th>
							<td>
								<input type="text" placeholder="请输入搜索标题"  id="name" name="name" value="<c:out value="${vo.name }" escapeXml="false"/>" />
							</td>
							<th scope="row">创建时间</th>
							<td>
								<input class="inp-field wid100" type="text" id="startDate" name="startDate" value="${vo.startDate}" readonly="readonly"/>
								<span>~</span>
								<input class="inp-field wid100" type="text" id="endDate" name="endDate" value="${vo.endDate}" readonly="readonly"/>
							</td>
							<td>
								<div class="btn-module floatR">
									<div class="rightGroup">
										<a href="javascript:;" class="search" onclick="fnSearch();" id="btnSearch">搜索</a>
										<a href="javascript:;" class="refresh" onclick="resetClick();" id="btnReset">重置</a>
									</div>
								</div>
							</td>
						<%--	<td>
								<div class="btn-module floatR">
									<div class="rightGroup">
										<a href="javascript:;" class="search" onclick="excelDownLoad();" style="width:110px;" id="ExcelDownload">导出Excel</a>
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
				<span class="tb-text"><strong>Total <span class="colorR"><c:out value="${totalCount }"/></span> Cases</strong></span>
			</span>
					</div>
					<div class="rightGroup">
						<select name="rowPerPage" id="rowPerPage" onchange="fnSearch();">
							<option value="10" <c:if test="${vo.rowPerPage eq '10'}">selected="selected"</c:if>>10</option>
							<option value="20" <c:if test="${vo.rowPerPage eq '20'}">selected="selected"</c:if>>20</option>
							<option value="30" <c:if test="${vo.rowPerPage eq '30'}">selected="selected"</c:if>>30</option>
							<option value="50" <c:if test="${vo.rowPerPage eq '50'}">selected="selected"</c:if>>50</option>
							<option value="100" <c:if test="${vo.rowPerPage eq '100'}">selected="selected"</c:if>>100</option>
						</select>
					</div>
				</div>
				<table class="bd-list inputUI_simple" summary="리스트 영역 입니다.">
					<caption>NO</caption>
					<colgroup>
						<col width="3%" />
						<col width="9%" />
						<col width="6%" />
						<col width="8%" />
						<col width="8%" />
						<col width="8%" />
						<col width="8%" />
						<col width="3%" />
						<col width="3%" />
					</colgroup>
					<thead>
					<tr>
						<th scope="col">序号</th>
						<th scope="col">SIM卡标题</th>
						<th scope="col">SIM卡管理流量</th>
						<th scope="col">创建人</th>
						<th scope="col">创建时间</th>
						<th scope="col">更新人</th>
						<th scope="col">更新时间</th>
						<th scope="col">点赞</th>
						<th scope="col">状态</th>

					</tr>
					</thead>
					<tbody>
					<c:forEach var="list" items="${list }" varStatus="stat">
						<tr>
							<td>
							<span style="width: 50px;">
								<c:out value="${stat.index + 1}" />
							</span>
							</td>
							<td>
								<a href="javascript:;"  onclick="detail('${list.id}');"><c:out value="${list.name } " escapeXml="false"/></a>
							</td>
                            <td>
                                <fmt:formatNumber value="${list.srchCnt }" pattern="#,###,###"/>
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
                                <c:out value="${list.isNice }"/>
                            </td>
							<td>
								<c:out value="${list.useYnNm }" escapeXml="false"/>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${fn:length(list) < 1 }">
						<tr>
							<td colspan="9" class="blank">没有数据</td>
						</tr>
					</c:if>

					</tbody>
				</table>

				<div class="btn-module mgtSM">
					<div class="rightGroup"><a href="#none"  onclick="doRegister();" class="btnStyle01">新增</a></div>
				</div>


				<!-- table paging -->
				<div class="page-module">
					<p class="paging">
						<blabPaging:paging currentPage="${vo.page }" rowSize="${vo.rowPerPage }" totalCount="${totalCount}" pagingId="admin"/>
					</p>
				</div>

			</article>
		</div>

	</section>
</form>
<a id="excelDownd" style="display:none"></a>

<script type="text/javascript">
	$(document).ready(function () {
		$("#startDate, #endDate").datepicker({
			dateFormat: "yy-mm-dd"
			, showOn: 'button'
			, buttonImage: '/static/images/cal.png'
			, buttonImageOnly: true
			, buttonText: "日期"
			, changeYear: true
			, maxDate : new Date()
			, defaultDate :null
		});

		var da = new Date();
		var year = da.getFullYear();
		var month = da.getMonth()+1;
		var date = da.getDate();
		var today=[year,month,date].join('-');
		$("#startDate").val( $("#startDate").val()==''? today:$("#startDate").val());
		$("#endDate").val($("#endDate").val()==''?today:$("#endDate").val());

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
//清空日期
		if("${vo.endDate}"==null || "${vo.endDate}"==''){
			$("#startDate").val("");
			$("#endDate").val("");
		}
		//$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "useYn", "${vo.useYn }", "selectbox", "");
		$("#searchValue").keypress(function(e) {
			if (e.keyCode == 13) {
				e.preventDefault();
				fnSearch();
			}
		});
	});
	//검색/페이징
	function fnSearch(){
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
	function resetClick(){
		$("#name").val("");
		$("#startDate").val("");
		$("#endDate").val("");
		$("#listForm").prop("method", "get");
		$("#listForm").attr('action', '<c:url value="./list.do"/>').submit();
	}
	//등록페이지 이동
	function doRegister(){
		$("#listForm").prop("method", "post");
		$("#listForm").attr('action', '<c:url value="./registerForm.do"/>').submit();
	}
	// 상세페이지 이동
	function detail(id){
		console.log(id)
		$("#id").val(id);
		$("#listForm").prop("method", "post");
		$("#listForm").attr('action', '<c:url value="./detail.do"/>').submit();
	}
	function excelDownLoad(){
		var childMenuId=$("#childMenuId").val();
		var topId=$("#topId").val();
		var name=$("#name").val();
		var startDate=$("#startDate").val();
		var endDate=$("#endDate").val();

		$.ajax({
			url : "<c:url value='/celloSquareAdmin/helpSupport/downloadCount.do' />"
			,type : "GET"
			,dataType : "json"
			, data : {
				topId:topId,
				name:name,
				startDate:startDate,
				endDate:endDate
			}
			, contentType: "application/json"
			, success : function(data) {
				if(data==0)  {
					alert("没有数据，请更换搜索条件再试.");
					return ;
				}else{
					$("#excelDownd").attr("href","/celloSquareAdmin/helpSupport/download.do?startDate="+startDate+"&endDate="+endDate+"&topId="+topId+"&name="+name);
					$("#excelDownd")[0].click();
				}

			}
		});

	}

</script>
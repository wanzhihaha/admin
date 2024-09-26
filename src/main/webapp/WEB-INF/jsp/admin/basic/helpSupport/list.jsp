<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>


<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="post">
	<input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
	<input type="hidden" id="id" name="id"/>
	<input type="hidden" id="topId" name="topId" value="<c:out value="${vo.topId}"/>"/>

	<section>
		<div class="title"><h3>${topname}</h3></div>
		<div class="Cont_place">
			<article>
				<div class="inputUI_simple">
					<table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
						<caption>상태, 답변 상태, 기간설정, 직접검색 영역</caption>
						<tr>
							<th scope="row">搜索</th>
							<td>
								<input type="text" placeholder="请输入搜索标题"  id="searchValue" name="searchValue" value="<c:out value="${vo.searchValue }" escapeXml="false"/>" />
							</td>
							<c:if test="${ not empty siblingNodes }">
							<th scope="row">栏目</th>
							<td>
								<div class="select-inner">
										<select name="childMenuId" id="childMenuId">
											<option value="">全部</option>
											<c:forEach items="${siblingNodes }" var="siblingNode">
												<option value="${siblingNode.id }" <c:if test="${siblingNode.id == vo.childMenuId}">selected</c:if>>${siblingNode.menuName }</option>
											</c:forEach>
										</select>
								</div>
							</td>
							</c:if>
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
							<td>
								<div class="btn-module floatR">
									<div class="rightGroup">
										<a href="javascript:;" class="search" onclick="excelDownLoad();" id="ExcelDownload">导出</a>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th >模板(word):</th>
							<td style="display: flex;">
								<div class="btn-module floatR">
									<div class="rightGroup">
										<a href="./downloadTemplete.do" class="search" id="downTemplete">下载</a>
									</div>
								</div>
							</td>
							<th >批量上传(word):</th>
							<td style="display: flex;">
								<div class="btn-module floatR">
									<div class="rightGroup" style="width: 214px;display: flex;align-items: center">
										<input type="file" name="file"  onchange="UploadFile()"/>
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
						<col width="5%" />
						<col width="9%" />
						<col width="9%" />
						<col width="6%" />
						<col width="8%" />
						<col width="8%" />
						<col width="8%" />
						<col width="8%" />
						<col width="3%" />
						<col width="3%" />
						<col width="3%" />
					</colgroup>
					<thead>
					<tr>
						<th scope="col">序号</th>
						<th scope="col">排序</th>
						<th scope="col">栏目</th>
						<th scope="col">标题</th>
						<th scope="col">浏览量</th>
						<th scope="col">创建人</th>
						<th scope="col">创建时间</th>
						<th scope="col">更新人</th>
						<th scope="col">更新时间</th>
						<th scope="col">点赞</th>
						<th scope="col">点踩</th>
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
								<input type="text" name="listOrdb" class="inp-field widM2" value="<c:out value="${list.ordb}" />" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3" />
								<input type="hidden" name="listId" value="<c:out value="${list.id}" />" />
							</td>
							<td>
								<c:out value="${list.childMenuName }" escapeXml="false"/>
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
                                <c:out value="${list.unHelp }"/>
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
					<div class="leftGroup"><a href="#none"  onclick="doSortSave();" class="btnStyle01">自定义排序</a></div>
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
		$("#searchValue").val("");
        $("#childMenuId").val("");
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
		$("#id").val(id);
		$("#listForm").prop("method", "post");
		$("#listForm").attr('action', '<c:url value="./detail.do"/>').submit();
	}
	function excelDownLoad(){
		export_Excel("#listForm","helpSupport");
	}
	function UploadFile() {
		$("#listForm").prop("method", "post");
		$("#listForm").attr('action', '<c:url value="./upload.do"/>').attr('enctype','multipart/form-data').submit();
	}
	function doSortSave() {
		if(confirm("确定保存排序吗？")) {
			$("#listForm").prop("method", "post");
			$("#listForm").attr('action', '<c:url value="./doSortOrder.do"/>').submit();
		}
		return false;
	}
</script>
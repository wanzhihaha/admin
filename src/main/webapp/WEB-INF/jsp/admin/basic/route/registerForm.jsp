<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" name="routeSeqNo" id="routeSeqNo" value="<c:out value="${vo.routeSeqNo }" />">
<section>	
	<div class="title">
		<h3>Route
			<c:choose>
				<c:when test="${contIU eq 'I' }">
					新增
				</c:when>
				<c:otherwise>
					修改
				</c:otherwise>
			</c:choose>
		</h3>
	</div>
	<article>
		<div class="Cont_place">
			<table class="bd-form" summary="등록, 수정하는 영역 입니다.">
					<caption>열람,내용</caption>
					<colgroup>
						<col width="200px" />
						<col width="" />
					</colgroup>


					<tr>
						<th colspan="10" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">线路信息</th>
					</tr>
<%--					<tr>--%>
<%--						<th scope="row">国家 Code*</th>--%>
<%--						<td>--%>
<%--							<div class="btn-module chk">--%>
<%--							&lt;%&ndash;	<c:if test="${SESSION_FORM_ADMIN.langCd ne 'eu-en'}">--%>
<%--									<input id="nationCd" name="nationCd" readonly="readonly" style="background-color: #E6E6E6;" type="text" class="inp-field widSM" placeholder="Please enter the node code" value="<c:out value="${nationCd }" escapeXml="false"/>" maxlength="30" data-vbyte="30" data-vmsg="Sort2"/>--%>
<%--								</c:if>&ndash;%&gt;--%>
<%--								<select id="euNationCd" name="nationCd" style="display: none">--%>
<%--								</select>--%>
<%--							</div>--%>
<%--						</td>--%>

<%--					</tr>--%>
					<tr>
						<th scope="row">线路类型*</th>
						<td>
							<div class="btn-module chk">
								<select id="productMode" name="productMode">
								</select>
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row">产品id</th>
						<td>
							<div class="btn-module chk">
								<input id="productId" name="productId" type="text" class="inp-field widSM" value="<c:out value="${detail.productId }" escapeXml="false"/>" maxlength="100" data-vbyte="100" data-vmsg="Sort2"/>
							</div>
						</td>
					</tr>

					<tr>
						<th scope="row">产品名</th>
						<td>
							<div class="btn-module chk">
								<input id="productNm" name="productNm" type="text" class="inp-field widSM" placeholder="" value="<c:out value="${detail.productNm }" escapeXml="false"/>" maxlength="100" data-vbyte="100" data-vmsg="Sort2"/>
							</div>
						</td>
					</tr>

					<tr>
						<th scope="row">起点 *</th>
						<td>
							<div class="btn-module chk">
								<input readonly="readonly" style="background-color: #E6E6E6;" id="fromNode" name="fromNode" type="text" class="inp-field widSM" placeholder="请选择节点" value="<c:out value="${detail.fromNode }" escapeXml="false"/>" />
								<input  id="fromNodeId" name="fromNodeId" type="hidden" value="<c:out value="${detail.fromNodeId }" />" />
								<a href="javascript:;" id="fromNodeSearch" class="btnStyle01" >节点搜索</a>
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row">终点 *</th>
						<td>
							<div class="btn-module chk">
								<input readonly="readonly" style="background-color: #E6E6E6;" id="toNode" name="toNode" type="text" class="inp-field widSM" placeholder="请选择节点" value="<c:out value="${detail.toNode }" escapeXml="false"/>" />
								<input  id="toNodeId" name="toNodeId" type="hidden" value="<c:out value="${detail.toNodeId }" />" />
								<a href="javascript:;" id="toNodeSearch" class="btnStyle01" >节点搜索</a>
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row">状态 *</th>
						<td>
							<div id="radUseCd"></div>
						</td>
					</tr>
					<tbody>

					</tbody>

			</table>

			<div class="btn-module mgtS">
				<div class="rightGroup">
					<c:choose>
						<c:when test="${contIU eq 'I' }">
							<a href="javascript:;" id="btnSave" class="btnStyle01" >新增</a>
						</c:when>
						<c:otherwise>
							<a href="javascript:;" id="btnSave" class="btnStyle01">修改</a>
						</c:otherwise>
					</c:choose>
					<a href="javascript:;" id="btnList" class="btnStyle01">取消</a>
				</div>
			</div>
		</div>
	</article>
</section>
</form>
<div class="modal" id="countryDataDiv" style="display:none;">
	<form id="nodeListAjaxForm">
		<input type="hidden" id = "pageAjax" name="page" value="${vo.page}"/>
		<input type="hidden" name="rowPerPage" value="10" />
		<input type="hidden" id="clickId">
		<div class="Cont_place modal-content">
			<i class="modal-close" id="closeCountry"></i>
			<div class="inputUI_simple">
				<table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
					<caption>상태, 답변 상태, 기간설정, 직접검색 영역</caption>
					<colgroup>
						<col width="120px" />
						<col width="300px" />
						<col width="" />
					</colgroup>
					<tr>
						<th scope="row">搜索</th>
						<td style="width: 500px">
							<div class="select-inner">
								<select name="nationSeqNo" id="nationSearch">
								</select>
							</div>
							<input type="text" style="display:none">
							<input id="searchValue" onkeydown='if (event.keyCode==13) { fncPage(1) }' type="text" style="width: 260px" placeholder="请输入搜索值." class="inp-field mglS" name="searchValue"  />
						</td>
						<td>
							<div class="btn-module floatR">
								<div class="rightGroup">
									<a href="javascript:;" class="search" onclick="fncPage(1);" id="btnSearch">搜索</a>
								</div>
							</div>
						</td>
					</tr>
				</table>
			</div>

			<table class="bd-list inputUI_simple" summary="리스트 영역 입니다.">
				<caption>NO</caption>
				<colgroup>
					<col width="10%" />
					<col width="" />
					<col width="20%" />
					<col width="20%" />
				</colgroup>
				<thead>
				<tr>
					<th scope="col">序号</th>
					<th scope="col">国家</th>
					<th scope="col">节点</th>
					<th scope="col">添加</th>
				</tr>
				</thead>
				<!-- js动态添加在这里 -->
				<tbody id="nationDataTb">

				</tbody>
			</table>

			<div class="page-module">
				<p class="paging" id="pageNationAjax">

				</p>
			</div>
		</div>
	</form>
</div >
<script type="text/javascript">
	var IU = "${contIU}";
	function removeParent(v){
		$(v).parent().remove();
	}
	function nodeSelect(node, id,nodeId){
		$('#'+id).val(node);
		$('#'+id+'Id').val(nodeId);
		$('#nationSearch').val("");
		/*$("#nationDataTb").children().remove();
		$("#pageNationAjax").children().remove();*/
		$('#countryDataDiv').hide();

	}

	function initFront(){

		$.fnGetCodeSelectAjax("sGb=QUOTE_CATEGORY_GRP&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "productMode", "${detail.productMode}", "selectbox", "");
		if(IU === 'U'){
			$('#nationCd').val("${detail.nationCd}")
			var source  = "${detail.routeSource}";
			if(source === "A"){
				$('#nationCd').attr("readonly", "readonly");
				$("#nationCd").css("backgroundColor", "#E6E6E6");
				$('#productMode').attr("readonly", "readonly");
				$('#productMode').attr("disabled", "disabled");
				$("#productMode").css("backgroundColor", "#E6E6E6");
				$('#productId').attr("readonly", "readonly");
				$("#productId").css("backgroundColor", "#E6E6E6");
				$('#productNm').attr("readonly", "readonly");
				$("#productNm").css("backgroundColor", "#E6E6E6");
				$('#fromNode').attr("readonly", "readonly");
				$("#fromNode").css("backgroundColor", "#E6E6E6");
				$('#toNode').attr("readonly", "readonly");
				$("#toNode").css("backgroundColor", "#E6E6E6");
				$('#fromNodeSearch').hide();
				$('#toNodeSearch').hide();
			}
		}
	}

	function countryEuSelect(){
		var langCd = "${SESSION_FORM_ADMIN.langCd}";
		if(langCd === 'eu-en'){
			$.fnGetCodeSelectAjax("sGb=EU_COUNTRY_CD&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "euNationCd", "${detail.nationCd}", "selectbox", "");
			$('#euNationCd').show();
		}
	}
	var nodeSearchFlag = "";
	$(document).ready(function(){

		initFront();
		countryEuSelect();
		$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");

		$("#fromNodeSearch").on("click",function(){
			if("" === nodeSearchFlag || "from" === nodeSearchFlag){
				$('#searchValue').val("${vo.searchValue}")
			}else {
				$('#searchValue').val("")
			}
			nodeSearchFlag = "from";
			$('#clickId').val('fromNode');
			ajaxNodeList();
			$('#countryDataDiv').show();
		});

		$("#toNodeSearch").on("click",function(){
			if("" === nodeSearchFlag || "to" === nodeSearchFlag){
				$('#searchValue').val("${vo.searchValue}")
			}else {
				$('#searchValue').val("")
			}
			nodeSearchFlag = "to";
			$('#clickId').val('toNode');
			ajaxNodeList();

			$('#countryDataDiv').show();
		});


		$("#btnSave").click(function() {

			if($("#fromNode").val().trim() == ""){
				alert("请选择起点");
				$("#fromNode").focus();
				return false;
			}

			if($('#toNode').val().trim() == ''){
				alert("请选择终点");
				$("#toNode").focus();
				return false;
			}
			if($("#fromNode").val().trim() === $('#toNode').val().trim()){
				alert("起点和终点不能相同");
				$("#toNode").focus();
				return false;
			}
			$('#productMode').removeAttr("disabled");
			<c:choose>
				<c:when test="${contIU eq 'I' }">
					$("#writeForm").prop("action","register.do");
					$("#writeForm").submit();
				</c:when>
				<c:otherwise>
					$("#writeForm").prop("action","update.do");
					$("#writeForm").submit();
				</c:otherwise>
			</c:choose>
		});
		
		$("#btnList").on("click",function() {
			cancel_backUp("routeSeqNo","writeForm");
		});
	});

	$('#closeCountry').click(function (){

		$("#nationDataTb").children().remove();
		$("#pageNationAjax").children().remove();
		$('#nationSearch').children().remove();
		$('#countryDataDiv').hide();
	})
	function checkNotEnglishChar(str) {
		for(i=0; i<str.length; i++) {
			if(((str.charCodeAt(i) > 0x3130 && str.charCodeAt(i) < 0x318F) || (str.charCodeAt(i) >= 0xAC00 && str.charCodeAt(i) <= 0xD7A3)|| (str.charCodeAt(i) >= 0x4e00 && str.charCodeAt(i) <= 0x9fa5)|| (str.charCodeAt(i) >= 0x0800 && str.charCodeAt(i) <= 0x4e00))) {
				return true;
			}
		}
		return false;
	}
	function fncPage(page) {
		$("#pageAjax").val(page);
		ajaxNodeList();
	}

	function ajaxNodeList(){
		var id= $('#clickId').val();
		console.log(id);
		var formData = $('#nodeListAjaxForm').serialize();
		var deafaultSelVal = $('#nationSearch').val();

		$.ajax({
			url: "./list-node-ajaxList.do"
			, dataType: 'json'
			, data: formData
			, type: "GET"
			, contentType: "application/x-www-form-urlencoded; charset=UTF-8"
			, success: function (json) {
				$("#nationDataTb").children().remove();
				$("#pageNationAjax").children().remove();
				$('#nationSearch').children().remove();
				console.log(json);
				var resultText = ""; //html 삽입값
				var selectTypeText = "<option value=''>全部</option>"
				$.each(json.list, function (index, list) {
					resultText += "<tr>";
					resultText += 	"<td>" + index + "</td>";
					resultText += 	"<td>" + list.nodeEngNm +'-'+list.nodeCnNm + "</td>";
					resultText += 	"<td>" + list.nodeCd + "</td>";
					resultText += 	"<td>"
					resultText += 		"<div class='btn-module chk'>";
					resultText += 			'  <a class="btnStyle01" href="javascript:nodeSelect(\''+list.nodeCd+'\', \''+id+'\', \''+list.id+'\');" role="button"> Select </a>';
					resultText += 		"</div>"
					resultText += 	"</td>"
					resultText += "/<tr>";
				});
				$.each(json.nationList, function (index, list){
					selectTypeText += '<option value="' + list.nationSeqNo + '">' + list.nationNm +'-'+list.nationCnNm+'</option>';
				})
				/**
				 *  循环次数时 Math.cell((total - page*size)/10) 向上取整
				 *
				 * @type {string}
				 */
				var pageBean = json.pageBean;
				var pageText = "";
				if(pageBean.prePageListIndex > 0){
					pageText += '  <a class="prev10" href="javascript:fncPage(\''+pageBean.prePageListIndex+'\');"><<</a>';
				}else{
					pageText += '<a clas="prev10"><<</a>'
				}
				if(pageBean.prePage > 0){
					pageText += '  <a clas="prev" href="javascript:fncPage(\''+pageBean.prePage+'\');"><</a>';
				}else {
					pageText += '<a clas="prev"><</a>'
				}

				for (let i = pageBean.beginPageIndex; i <= pageBean.endPageIndex; i++) {
					if(i === pageBean.currentPage) {
						pageText += '  <a class = "current" href="javascript:fncPage(\''+i+'\');">' + i + '</a>';
					}else{
						pageText += '  <a href="javascript:fncPage(\''+i+'\');">' + i + '</a>';
					}
				}
				if(pageBean.nextPage > 0){
					pageText += '  <a clas="prev" href="javascript:fncPage(\''+pageBean.nextPage+'\');">></a>';
				}else {
					pageText += '<a clas="prev">></a>'
				}
				if(pageBean.nextPageListIndex > 0){
					pageText += '  <a class="prev10" href="javascript:fncPage(\''+pageBean.nextPageListIndex+'\');">>></a>';
				}else{
					pageText += '<a clas="prev10">>></a>'
				}


				$("#nationSearch").append(selectTypeText);
				$('#nationSearch').find('option[value=' + deafaultSelVal + ']').attr("selected", true);
				$("#nationDataTb").append(resultText);
				$("#pageNationAjax").append(pageText);
			}, error: function (xhr) {
				alert("Network Error");
			}
		});
	}
</script>		
							

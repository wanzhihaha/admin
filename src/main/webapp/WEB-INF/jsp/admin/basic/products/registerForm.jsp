<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="get" action="./register.do" enctype="multipart/form-data">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="productCtgry" name="productCtgry" value="<c:out value="${vo.productCtgry }"/>">
<input type="hidden" id="productCtgryNm" name="productCtgryNm" value="<c:out value="${vo.productCtgryNm }"/>">
<input type="hidden" id="productSeqNo" name="productSeqNo" value="<c:out value="${detail.productSeqNo }" />">


<section>	
	<c:choose>
		<c:when test="${!empty detail.productSeqNo }">
			<div class="title"><h3><c:out value="${vo.productCtgryNm}"/> 修改</h3></div>
		</c:when>
		<c:otherwise>
			<div class="title"><h3><c:out value="${vo.productCtgryNm}"/> 创建</h3></div>
		</c:otherwise>
	</c:choose>
	<article>
	<div class="Cont_place">
	<table class="bd-form" summary="등록, 수정하는 영역 입니다.">
			<caption>열람,내용</caption>
			<colgroup>
				<col width="130px" />
				<col width="" />
			</colgroup>
			
			<%@ include file="/WEB-INF/jsp/common/seo/seoRegisterFormNews.jsp" %>
			
			<tr>
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">产品信息</th>
			</tr>
			<tr>
				<th scope="row">产品名称 *</th>
				<td>
					<input id="productNm" name="productNm" type="text" placeholder="请输入产品名称." onblur="productCountByNm(this.value,${detail.productSeqNo })" class="inp-field widL" value="<c:out value="${detail.productNm }" escapeXml="false"/>" maxlength="64" data-vmsg="product name"/>
				</td>
			</tr>
	
			<tr>
				<th scope="row">摘要 *</th>
				<td>
					<div class="textCont">
						<textarea name="productSummaryInfo" id="productSummaryInfo" class="textarea" rows="10" cols="65" placeholder="请输入摘要." maxlength="210" data-vmsg="summary information"><c:out value="${detail.productSummaryInfo }" escapeXml="false"/></textarea>
						
					</div>
				</td>
			</tr>

			<tr>
				<th scope="row">列表图标 *</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="pcListOrginFile" name="pcListOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.pcListImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/products/goodsDownload.do?productSeqNo=<c:out value="${detail.productSeqNo }"/>&imgKinds=pcList&productCtgry=<c:out value='${vo.productCtgry }'/>' style="color: blue;"><c:out value="${detail.pcListImgOrgFileNm}" escapeXml="false"/></a></span>
<%--								<span><input type="checkbox" name="pcListFileDel" id="pcListFileDel" value="Y" /> <label for="pcListFileDel">Delete</label></span>--%>
							</c:if>
						</div>
						<div>
							<input type="text" id="pcListImgAlt" name="pcListImgAlt" value="<c:out value="${detail.pcListImgAlt }" escapeXml="false"/>" placeholder="请输入图片描述（可不填）" class="inp-field widSM" maxlength="210" data-vbyte="210" data-vmsg="alt value" />
							<span style="line-height: 28px;"> ※请点击上传图片.</span>
						</div>
					</div>
				</td>
			</tr>
			<tr >
				<th scope="row">详情顶图</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="pcDetailOrginFile" name="pcDetailOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.pcDetlImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/products/goodsDownload.do?productSeqNo=<c:out value="${detail.productSeqNo }"/>&imgKinds=pcDetail&productCtgry=<c:out value='${vo.productCtgry }'/>' style="color: blue;"><c:out value="${detail.pcDetlImgOrgFileNm}" escapeXml="false"/></a></span>
								<span><input type="checkbox" name="pcDetailFileDel" id="pcDetailFileDel" value="Y" /> <label for="pcDetailFileDel">Delete</label></span>
							</c:if>
						</div>
						<div>
							<input type="text" id="pcDetlImgAlt" name="pcDetlImgAlt" value="<c:out value="${detail.pcDetlImgAlt }" escapeXml="false"/>" placeholder="请输入图片描述（可不填）" class="inp-field widSM" maxlength="210" data-vbyte="210" data-vmsg="alt value" />
							<span style="line-height: 28px;"> ※请点击上传图片.</span>
						</div>
					</div>
				</td>
			</tr>

			<tr >
				<th scope="row">移动端详情顶图</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="mobileDetailOrginFile" name="mobileDetailOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.mobileDetlImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/products/goodsDownload.do?productSeqNo=<c:out value="${detail.productSeqNo }"/>&imgKinds=mobileDetail&productCtgry=<c:out value='${vo.productCtgry }'/>' style="color: blue;"><c:out value="${detail.mobileDetlImgOrgFileNm}" escapeXml="false"/></a></span>
								<span><input type="checkbox" name="mobileDetailFileDel" id="mobileDetailFileDel" value="Y" /> <label for="mobileDetailFileDel">Delete</label></span>
							</c:if>
						</div>
						<div>
							<input type="text" id="mobileDetlImgAlt" name="mobileDetlImgAlt" value="<c:out value="${detail.mobileDetlImgAlt }" escapeXml="false"/>" placeholder="请输入图片描述（可不填）" class="inp-field widSM" maxlength="210" data-vbyte="210" data-vmsg="alt value" />
							<span style="line-height: 28px;"> ※请点击上传图片.</span>
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<th scope="row">产品详情 *</th>
				<td>
					<div class="textCont">
						<textarea name="productContents" id="productContents" class="textarea" rows="10" cols="65" placeholder="내용입력 *" ><c:out value="${detail.productContents }"  escapeXml="false"/></textarea>			
						<script type="text/javascript" language="javascript">
							var CrossEditor = new NamoSE('productContents');
							CrossEditor.params.Width = "100%";
							CrossEditor.params.UserLang = "auto";
							CrossEditor.params.FullScreen = false;
							CrossEditor.params.WebsourcePath = "/namo_was"
							CrossEditor.params.ServerUrl = 2;	// 서버 url제외
							CrossEditor.params.DeleteCommand = ["backgroundimage","ce_imageeditor","insertfile","flash"];
							CrossEditor.params.UploadFileSizeLimit = "image:5242880,flash:2097152";

							CrossEditor.params.DefaultFont = "默认字体";
							CrossEditor.params.DefaultFontSize = "默认";
							CrossEditor.params.LineHeight = "normal";

							CrossEditor.EditorStart();
							
							function OnInitCompleted(e){
								e.editorTarget.SetBodyValue(document.getElementById("productContents").value);
							}
						</script>
					</div>
				</td>
			</tr>
		<tr>
			<th scope="row">常见问题</th>
			<td>
				<select name="commonSelect2" id="commonSelect2" multiple>
					<c:forEach items="${ackQuestions }" var="siblingNode">
						<option value="${siblingNode.id }" <c:if test="${siblingNode.isSelect == 1 }"> selected</c:if>>${siblingNode.name }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
			<tr>
				<th scope="row">Status*</th>
				<td>
					<div id="radUseCd"></div>
				</td>
			</tr>
		</table>
		
		<div class="btn-module mgtS">		
			<div class="rightGroup">
				<a id="btnSave" href="#none" class="btnStyle01" >保存</a>
				<a href="javascript:;" id="btnList" class="btnStyle01">取消</a>
			</div>
		</div>	
	</div>
	</article>
</section>
</form>
					
					
<script type="text/javascript">
$(document).ready(function(){
	$("#commonSelect2").select2({
		placeholder: "请选择",
		allowClear: true,
        maximumSelectionLength:5,
	})
	$("#svcValidStatDate, #svcValidEndDate").datepicker({
		dateFormat:"yy-mm-dd"
		,showOn : 'button'
		,buttonImage : '/static/images/cal.png'
		,buttonImageOnly : true
		,buttonText : "달력"

	});
  	<%--$.fnGetCodeSelectAjax("sGb=GOODS_VW_TP&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radGoodsViewTypeImg", "${detail.viewTypeImg == null ? 'GOODS_VW_TP_01' : detail.viewTypeImg}", "radio", "viewTypeImg");--%>
	<%--$.fnGetCodeSelectAjax("sGb=GOODS_VW_BG_OP&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "selGoodsVmBgOp", "${detail.viewTypeImgBkgrColorOpt}", "selectbox", "");--%>
	<%--$.fnGetCodeSelectAjax("sGb=GOODS_VW_BG&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radGoodsVwBgCd", "${detail.viewTypeImgBkgrColorCcd == null ? 'GOODS_VW_BG_01' : detail.viewTypeImgBkgrColorCcd}", "radio", "viewTypeImgBkgrColorCcd");--%>
	<%--$.fnGetCodeSelectAjax("sGb=GOODS_VW_BG_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "selVwTyImgCd", "${detail.viewTypeImgCcd}", "selectbox", "");--%>

	$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
	
	//View Type 옵션값에 따라서 요소 추가
	var appendText;
	
	
	$("#btnSave").click(function() {

		if($.trim($("#productNm").val()) == ""){	
			alert("请输入产品名称.");
			$("#productNm").focus();
			return false;
		}
		//파일 업로드 수만큼 반복해서 파일을 선택했다면 select에 Y값을 준다
		$("input[name=fileUpload]").each(function(k,v) {	
			if($("#fileUpload_" + k).val() != "") {			
				$("#fileUploadSelect_" + k).val("Y"); 		 
			}
		});

		if(CrossEditor.GetTextValue () == ""){
			alert("请输入产品详情.");
			CrossEditor.SetFocusEditor();
			return false;
		}

		$("#productContents").val(CrossEditor.GetBodyValue());

		var pcListOrginFile = $.trim($("#pcListOrginFile").val());
		var pcListImgOrgFileNm = '${ detail.pcListImgOrgFileNm}';
		if(pcListOrginFile == "" && pcListImgOrgFileNm==''){
			alert("请上传PC列表图标");
			$("#pcListOrginFile").focus();
			return false;
		}

		var pcDetailOrginFile = $.trim($("#pcDetailOrginFile").val());
		var pcDetlImgOrgFileNm = '${ detail.pcDetlImgOrgFileNm}';
		if(pcDetailOrginFile == "" && pcDetlImgOrgFileNm==''){
			alert("请上传PC详情顶图");
			$("#pcDetailOrginFile").focus();
			return false;
		}

      // form byte check
      if(formByteCheck() == false) {
        return false;
      }
		
		<c:choose>
			<c:when test="${detail.productSeqNo == null}">
				$("#writeForm").prop("method", "post");
				$("#writeForm").attr('action', '<c:url value="./register.do"/>').submit();
			</c:when>
			<c:otherwise>
				$("#writeForm").prop("method", "post");
				$("#writeForm").attr('action', '<c:url value="./update.do"/>').submit();
			</c:otherwise>
		</c:choose>
	});
});	


//취소버튼 리스트로 이동		
$("#btnList").on("click",function() {
	cancel_backUp("productSeqNo","writeForm");
});

//검색 팝업창 띄우기
function fncSearchGoods(idx) {
	
	win_pop("./searchGoods.do?productCtgry=<c:out value='${vo.productCtgry}'/>&productSeqNo=<c:out value ='${detail.productSeqNo}'/>&index="+idx , 'detail', '930', '700', 'yes');
}	
// RelValue값 지우기	
function fnValueDel(idx) {
	$("#reltdSqprd"+idx).val('');
	$("#relproductSeqNo"+idx).val('');
}	
	
//삭제 여부
function fnDelChk(i) {
	if($("#atthFileDel_"+i).is(":checked") == true) {
		$("#fileUploadDel_" + i).val("Y");
	} else {
		$("#fileUploadDel_" + i).val("N");
	}
}

//날짜 선택 밸리데이션
	
$("#svcValidEndDate").on("change keyup paste", function(){
	var stDt = $("#svcValidStatDate").val();
	var stDtArr = stDt.split("-");
	var stDtObj = new Date(stDtArr[0], Number(stDtArr[1])-1, stDtArr[2]);
	
	var enDt = $("#svcValidEndDate").val();
	var enDtArr = enDt.split("-");
	var enDtObj = new Date(enDtArr[0], Number(enDtArr[1])-1, enDtArr[2]);
	
	var betweenDay = (enDtObj.getTime() - stDtObj.getTime())/1000/60/60/24;

	if(stDt == "" && enDt != ""){
		alert("Please enter the start date.");
		$("#svcValidStatDate").focus();
		$("#svcValidEndDate").val("");
		return false;
	}
	
	if(betweenDay < 0) {
		alert("The end date is greater than the start date. Please re-enter.");
		$("#svcValidEndDate").focus();
		$("#svcValidEndDate").val("");
		return false;
	} 
});
	
$("#svcValidStatDate").on("change keyup paste", function(){	
	var stDt = $("#svcValidStatDate").val();
	var stDtArr = stDt.split("-");
	var stDtObj = new Date(stDtArr[0], Number(stDtArr[1])-1, stDtArr[2]);
	
	var enDt = $("#svcValidEndDate").val();
	var enDtArr = enDt.split("-");
	var enDtObj = new Date(enDtArr[0], Number(enDtArr[1])-1, enDtArr[2]);
	
	var betweenDay = (enDtObj.getTime() - stDtObj.getTime())/1000/60/60/24;
	if(betweenDay < 0) {
		alert("The start date is less than the end date. Please re-enter.");
		$("#svcValidStatDate").focus();
		$("#svcValidStatDate").val("");
		return false;
	} 
});

function OnInitCompleted(e){
	 if(e.editorTarget == CrossEditor){
		CrossEditor.SetBodyValue(document.getElementById("productContents").value);
	}
}

function productCountByNm(nm,ignoreSeqNo){
	//发送请求
	$.ajax({
		url : "/celloSquareAdmin/products/getProductCountByNm.do"
		, dataType : 'json'
		, data : {'nm':nm,'ignoreSeqNo':ignoreSeqNo}
		, async : false
		, success : function(json) {
			if(json.productCountByNm>0){
				alert(json.productCountByNmMsg);
				//$("#productNm").focus();
			}
		}
		, error : function() {
			return "";
		}
	});

}
</script>		
							

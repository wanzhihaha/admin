<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<%--<input type="hidden" id="sqprdCtgry" name="sqprdCtgry" value="<c:out value="${vo.sqprdCtgry }"/>">--%>
<%--<input type="hidden" id="sqprdCtgryNm" name="sqprdCtgryNm" value="<c:out value="${vo.sqprdCtgryNm }"/>">--%>
<input type="hidden" id="id" name="id" value="<c:out value="${detail.id }" />">


<section>
	<c:choose>
		<c:when test="${!empty detail.id }">
			<div class="title"><h3> 修改</h3></div>
		</c:when>
		<c:otherwise>
			<div class="title"><h3> 新增</h3></div>
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
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">车队信息</th>
			</tr>
			<tr>
				<th scope="row">车队类型*</th>
				<td>
					<select name="articleType" id="articleType">
<%--						<option value="10" selected="selected">公司动态</option>--%>
<%--						<option value="20">跨境资讯</option>--%>
<%--						<option value="30">物流动态</option>--%>
<%--						<option value="40">物流政策</option>--%>
					</select>
				</td>
			</tr>
			<tr>
				<th scope="row">车队信息</th>
				<td>
					<input id="reArticleTitle" name="articleTitle" type="text" placeholder="请输入文章标题" class="inp-field widL" value="<c:out value="${detail.articleTitle }" escapeXml="false"/>" maxlength="95" data-vmsg="product name in English"/>
				</td>
			</tr>
		<tr>
			<th scope="row">车队摘要</th>
			<td>
				<div class="textCont">
					<textarea name="articleDigest" id="reArticleDigest" class="textarea" rows="10" cols="65" placeholder="请输入摘要" maxlength="475" data-vmsg="summary information"><c:out value="${detail.articleDigest }" escapeXml="false"/></textarea>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row">车队内容*</th>
			<td>
				<div class="textCont">
					<textarea name="articleContent" id="reArticleContent" class="textarea" rows="10" cols="65" placeholder="내용입력 *" ><c:out value="${detail.articleContent }"  escapeXml="false"/></textarea>
					<script type="text/javascript" language="javascript">
						var CrossEditor = new NamoSE('reArticleContent');
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
							e.editorTarget.SetBodyValue(document.getElementById("reArticleContent").value);
						}
					</script>
				</div>
			</td>
		</tr>

		<tr>
			<th scope="row">置顶类型</th>
			<td>
				<select name="stickType" id="stickType" onchange="hideOrShowPic(this.value)">

				</select>
			</td>
		</tr>
		<tr class="isShowOrHide">
			<th scope="row">PC置顶图</th>
			<td>
				<div class="textCont">
					<div style="margin-bottom: 7px;">
						<input id="pcListOrginFile" name="pcListOrginFile" type="file" class="inp-field widSM"/>
						<c:if test="${!empty detail.articlePicBig}">
							<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/article/blogImgView.do?id=<c:out value="${detail.id }"/>&imgKinds=articlePicBig' style="color: blue;"><c:out value="${detail.articlePicBigName} " escapeXml="false"/></a></span>
							<span><input type="checkbox" name="pcListFileDel" id="pcListFileDel" value="Y" /> <label for="pcListFileDel">Delete</label></span>
						</c:if>
					</div>
				</div>
			</td>
		</tr>
		<tr class="isShowOrHide">
			<th scope="row">移动端置顶图<br />(618px - 346px)</th>
			<td>
				<div class="textCont">
					<div style="margin-bottom: 7px;">
						<input id="mobileListOrginFile" name="mobileListOrginFile" type="file" class="inp-field widSM"/>
						<c:if test="${!empty detail.articlePicAs}">
							<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/article/blogImgView.do?id=<c:out value="${detail.id }"/>&imgKinds=articlePicAs' style="color: blue;"><c:out value="${detail.articlePicAsName} " escapeXml="false"/></a></span>
							<span><input type="checkbox" name="mobileListOrginFileDel" id="mobileListOrginFileDel" value="Y" /> <label for="mobileListOrginFileDel">Delete</label></span>
						</c:if>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row">列表图<br />(908px - )</th>
			<td>
				<div class="textCont">
					<div style="margin-bottom: 7px;">
						<input id="pcDetailOrginFile" name="pcDetailOrginFile" type="file" class="inp-field widSM"/>
						<c:if test="${!empty detail.articlePicTb}">
							<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/article/blogImgView.do?id=<c:out value="${detail.id }"/>&imgKinds=articlePicTb' style="color: blue;"><c:out value="${detail.articlePicTbName}" escapeXml="false"/></a></span>
							<span><input type="checkbox" name="pcDetailFileDel" id="pcDetailFileDel" value="Y" /> <label for="pcDetailFileDel">Delete</label></span>
						</c:if>
					</div>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row">术语</th>
			<td>
				<input id="terminologyTags" name="terminologyTags" type="text" placeholder="请输入术语标签,用英文逗号隔开最多五个" class="inp-field widL" value="<c:out value="${detail.terminologyTags }" escapeXml="false"/>" maxlength="128" data-vmsg="术语标签"/>
			</td>
		</tr>
<%--		<tr>--%>
<%--			<th scope="row">关键词</th>--%>
<%--			<td>--%>
<%--				<input id="antistopTags" name="antistopTags" type="text" placeholder="请输入关键词标签,用英文逗号隔开最多五个" class="inp-field widL" value="<c:out value="${detail.antistopTags }" escapeXml="false"/>" maxlength="128" data-vmsg="关键词标签"/>--%>
<%--			</td>--%>
<%--		</tr>--%>
		<tr>
			<th scope="row">Status*</th>
			<td>
				<div id="radUseCd"></div>
			</td>
		</tr>
		</table>

		<div class="btn-module mgtS">
			<div class="rightGroup">
				<a id="btnSave" href="javascript:;" class="btnStyle01" >保存</a>
				<a href="javascript:;" id="btnList" class="btnStyle01">取消</a>
			</div>
		</div>
	</div>
	</article>
</section>
</form>


<script type="text/javascript">
$(document).ready(function(){
	hideOrShowPic('${detail.stickType}')

	$.fnGetCodeSelectAjax("sGb=ARTICLE_TYPE&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "articleType","${detail.articleType}", "selectbox", "");
	$.fnGetCodeSelectAjax("sGb=STICK_TYPE&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "stickType", "${detail.stickType}", "selectbox", "");
	$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
	$("#btnSave").click(function() {

		<%--if(!metaValidation('<c:out value="${contIU}"/>')) {--%>
		<%--	return false;--%>
		<%--}--%>
		if($.trim($("#reArticleTitle").val()) == ""){
			alert("文章标题不能为空");
			$("#reArticleTitle").focus();
			return false;
		}

		// if($.trim($("#terminologyTags").val()) == ""){
		// 	alert("文章标签不能为空");
		// 	$("#terminologyTags").focus();
		// 	return false;
		// }
		//파일 업로드 수만큼 반복해서 파일을 선택했다면 select에 Y값을 준다
		$("input[name=fileUpload]").each(function(k,v) {
			if($("#fileUpload_" + k).val() != "") {
				$("#fileUploadSelect_" + k).val("Y");
			}
		});
		$("#reArticleContent").val(CrossEditor.GetBodyValue());

		if(CrossEditor.GetTextValue () == ""){
			alert("文章内容不能为空");
			CrossEditor.SetFocusEditor();
			return false;
		}

		if ($("#viewTypeOrginFile").val()){
			if(!/\.(gif|jpg|peg|png|jpeg)$/.test($("#viewTypeOrginFile").val().toLowerCase())){
				alert("Please select the image file (gif, jpg, bmp, png, jpeg)");
				$("#viewTypeOrginFile").focus();
				return false;
			}
		}
		if($("#terminologyTags").val()!='' || $("#terminologyTags").val()!=null){
			var articleTag_split = $("#terminologyTags").val().split(",");
			if(articleTag_split.length>5){
				alert("术语标签不能超出5个.");
				return false;
			}
		}
		// if($("#antistopTags").val()!='' || $("#antistopTags").val()!=null){
		// 	var antistopTags_split = $("#antistopTags").val().split(",");
		// 	if(antistopTags_split.length>5){
		// 		alert("关键词标签不能超出5个.");
		// 		return false;
		// 	}
		// }
      // form byte check
      if(formByteCheck() == false) {
        return false;
      }

		<c:choose>
			<c:when test="${detail.id == null}">
				$("#writeForm").attr('action', '<c:url value="./register.do"/>').submit();
			</c:when>
			<c:otherwise>
				$("#writeForm").attr('action', '<c:url value="./update.do"/>').submit();
			</c:otherwise>
		</c:choose>
	});
});


//취소버튼 리스트로 이동
$("#btnList").on("click",function() {
	cancel_backUp("id","writeForm");
});

//검색 팝업창 띄우기
function fncSearchGoods(idx) {

	<%--win_pop("./searchGoods.do?sqprdCtgry=<c:out value='${vo.sqprdCtgry}'/>&sqprdSeqNo=<c:out value ='${detail.sqprdSeqNo}'/>&index="+idx , 'detail', '930', '700', 'yes');--%>
}
// RelValue값 지우기
function fnValueDel(idx) {
	$("#reltdSqprd"+idx).val('');
	$("#relsqprdSeqNo"+idx).val('');
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
	 if(e.editorTarget == CrossEditor){ //객체명으로 확인가능
		 CrossEditor.SetBodyValue(document.getElementById("reArticleContent").value);
	}else if(e.editorTarget == CrossEditor1){
		CrossEditor1.SetBodyValue(document.getElementById("reArticleContent").value);
	}
}

function hideOrShowPic(val){
	if(val == '40'){
		$(".isShowOrHide").hide()
	}else{
		$(".isShowOrHide").show()
	}
}
</script>		
							

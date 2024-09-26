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
<%--	<c:choose>--%>
<%--		<c:when test="${!empty detail.sqprdSeqNo }">--%>
<%--			<div class="title"><h3><c:out value="${vo.sqprdCtgryNm}"/> Modify</h3></div>--%>
<%--		</c:when>--%>
<%--		<c:otherwise>--%>
<%--			<div class="title"><h3><c:out value="${vo.sqprdCtgryNm}"/> Registration</h3></div>--%>
<%--		</c:otherwise>--%>
<%--	</c:choose>--%>
	<div class="title"><h3>资源 创建新广告</h3></div>
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
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">广告信息</th>
			</tr>
			<tr>
				<th scope="row">广告名</th>
				<td>
					<input id="adName" name="adName" type="text" placeholder="请输入广告名" class="inp-field widL" value="<c:out value="${detail.adName }" escapeXml="false"/>" maxlength="20" data-vmsg="product name in English"/>
				</td>
			</tr>
		<tr>
			<th scope="row">广告位置*</th>
			<td>
				<select name="adLocation" id="adLocation">
					<option value="10" selected="selected">菜单栏</option>
					<option value="20">产品页</option>
					<option value="30">资源页</option>
				</select>
			</td>
		</tr>
		<tr>
			<th scope="row">跳转链接</th>
			<td>
				<input id="adUrl" name="adUrl" type="text" placeholder="请输入跳转链接" class="inp-field widL" value="<c:out value="${detail.adUrl }" escapeXml="false"/>" maxlength="20" data-vmsg="product name in English"/>
			</td>
		</tr>
		<tr>
			<th scope="row">关键词</th>
			<td>
				<input id="adKeyword" name="adKeyword" type="text" placeholder="请输入关键词" class="inp-field widL" value="<c:out value="${detail.adKeyword }" escapeXml="false"/>" maxlength="20" data-vmsg="product name in English"/>
			</td>
		</tr>
		<tr>
			<th scope="row">图片<br />(618px - 346px)</th>
			<td>
				<div class="textCont">
					<div style="margin-bottom: 7px;">
						<input id="pcListOrginFile" name="pcListOrginFile" type="file" class="inp-field widSM"/>
						<c:if test="${!empty detail.adPicUrl}">
							<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/advertising/blogImgView.do?id=<c:out value="${detail.id }"/>&imgKinds=articlePicBig' style="color: blue;"><c:out value="${detail.adPicName} " escapeXml="false"/></a></span>
							<span><input type="checkbox" name="pcListFileDel" id="pcListFileDel" value="Y" /> <label for="pcListFileDel">Delete</label></span>
						</c:if>
					</div>
				</div>
			</td>
		</tr>

			<th scope="row">Status*</th>
			<td>
				<div id="radUseCd"></div>
			</td>
		</tr>
		</table>
		
		<div class="btn-module mgtS">		
			<div class="rightGroup">
				<a id="btnSave" href="javascript:;" class="btnStyle01" >Registration</a>
				<a href="javascript:;" id="btnList" class="btnStyle01">Cancel</a>
			</div>
		</div>	
	</div>
	</article>
</section>
</form>
					
					
<script type="text/javascript">
$(document).ready(function(){
	$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
	$("#btnSave").click(function() {
	
		<%--if(!metaValidation('<c:out value="${contIU}"/>')) {--%>
		<%--	return false;--%>
		<%--}--%>
		//파일 업로드 수만큼 반복해서 파일을 선택했다면 select에 Y값을 준다
		$("input[name=fileUpload]").each(function(k,v) {	
			if($("#fileUpload_" + k).val() != "") {			
				$("#fileUploadSelect_" + k).val("Y"); 		 
			}
		});
		if ($("#viewTypeOrginFile").val()){
			if(!/\.(gif|jpg|peg|png|jpeg)$/.test($("#viewTypeOrginFile").val().toLowerCase())){
				alert("Please select the image file (gif, jpg, bmp, png, jpeg)");
				$("#viewTypeOrginFile").focus();
				return false;
			}
		}

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
	history.back();
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
</script>		
							

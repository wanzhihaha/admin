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
	<div class="title"><h3>创建新术语</h3></div>
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
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">术语信息</th>
			</tr>
		<tr>
			<th scope="row">术语*</th>
			<td>
				<input id="terminologyName" name="terminologyName" type="text" required="required" placeholder="请输入术语" class="inp-field widL" value="<c:out value="${detail.terminologyName }" escapeXml="false"/>" maxlength="500" data-vmsg="product name in English"/>
			</td>
		</tr>

		<tr>
			<th scope="row">术语内容*</th>
			<td>
				<div class="textCont">
					<textarea name="description" id="description" class="textarea" rows="10" cols="65" placeholder="请输入术语" maxlength="475" data-vmsg="summary information"><c:out value="${detail.description }" escapeXml="false"/></textarea>
				</div>
			</td>
		</tr>
		<tr>
			<th scope="row">前端展示*</th>
			<td>
				<div id="showFlag"></div>
			</td>
		</tr>
		<tr>
			<th scope="row">是否开启匹配*</th>
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
	$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
	$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "showFlag", "${detail.showFlag == null ? 'N' : detail.showFlag}", "radio", "showFlag");
	$("#btnSave").click(function() {
	
		<%--if(!metaValidation('<c:out value="${contIU}"/>')) {--%>
		<%--	return false;--%>
		<%--}--%>
		if($.trim($("#terminologyName").val()) == ""){
			alert("文章标题不能为空");
			$("#terminologyName").focus();
			return false;
		}

		if($.trim($("#description").val()) == ""){
			alert("文章标题不能为空");
			$("#description").focus();
			return false;
		}

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

</script>		
							

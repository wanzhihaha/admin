<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>


<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">

<input type="hidden" id="mvSqprdSeqNo" name="mvSqprdSeqNo" value="<c:out value="${detail.mvSqprdSeqNo }"/>"/>
<input type="hidden" name="page" value="<c:out value="${vo.page}"/>">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">


<section>
	
	<div class="title"><h3>Home Product 
	<c:choose>
		<c:when test="${contIU eq 'I' }">
			Registration
		</c:when>
		<c:otherwise>
			Modify
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
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">Product Information</th>
			</tr>
			<tr>
				<th scope="row">Products *</th>
				<td>
					<div id="searchButton" class="btn-module mgtTB">
						<input id="reltdSqprd" name="reltdSqprd" type="text"  class="inp-field" value="<c:out value="${detail.sqprdNm}" escapeXml="false"/>" maxlength="110" readonly="readonly" style="background-color: #E6E6E6; width: 83%;"/>
						<input id="relsqprdSeqNo" name="relsqprdSeqNo" type="hidden" value="<c:out value="${detail.reltdSqprd }"/>">
						<input type="hidden" id="mvSqprdCcd" name="mvSqprdCcd" value="<c:out value="${detail.mvSqprdCcd }"/>"/>
						<a href="javascript:;" id="goodsDel" onclick="fnValueDel()" class="btnStyleGoods05">X</a>
						<a href="javascript:;" id="goodsSearch" onclick="fncSearchGoods();" class="btnStyle01" style="margin-left:10px; width: 10%">Product Search</a>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">Status *</th>
				<td>
					<div id="radUseCd"></div>
				</td>
			</tr>		
		</table>
	
	<div class="btn-module mgtS">		
			<div class="rightGroup">
				<c:choose>
					<c:when test="${contIU eq 'I' }">
						<a href="javascript:;" id="btnSave" class="btnStyle01" >Registration</a> 
					</c:when>
					<c:otherwise>
						<a href="javascript:;" id="btnSave" class="btnStyle01" >Modify</a> 
					</c:otherwise>
				</c:choose>
				<a href="javascript:;" id="btnList" class="btnStyle01">Cancel</a>
			</div>
		</div>	

	</div>
	</article>
</section>
</form>

<script type="text/javascript">
	
$(document).ready(function(){
	
	$("#insDtm").datepicker({
		dateFormat:"yy-mm-dd"
		,showOn : 'button'
		,buttonImage : '/static/images/cal.png'
		,buttonImageOnly : true
		,buttonText : "달력"

	});
	$("#btnSave").click(function() {
		
		if($.trim($("#reltdSqprd").val()) == ""){
			alert("Please select a product.");
			$("#reltdSqprd").focus();
			return false;
		}

		// form byte check
		if(formByteCheck() == false) {
			return false;
		}
		
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
		history.back();
	});
		// 삭제
	function fncDelete(){
		if(confirm("Are you sure you want to delete?")){
			$("#writeForm").attr('action', '<c:url value="./doDelete.do"/>').submit();
		}
	}

		$.fnGetCodeSelectAjax("sGb=MV_SQPRD_CCD&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "selGoodsCcd", "${detail.mvSqprdCcd }", "selectbox", "");
		$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
	});
	
	function fncSearchGoods() {
		win_pop("./searchGoods.do?mvSqprdSeqNo=<c:out value='${vo.mvSqprdSeqNo}'/>", 'detail', '930', '700', 'yes');
	}	
	
	function fnValueDel() {
		$("#reltdSqprd").val('');
		$("#relsqprdSeqNo").val('');
		$("#mvSqprdCcd").val('');
	}	
	
	
	
</script>

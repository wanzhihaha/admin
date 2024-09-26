<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>


<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">
<input type="hidden" id="libSubMvSeqNo" name="libSubMvSeqNo" value="<c:out value="${detail.libSubMvSeqNo }"/>"/>
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">

<section>
	
	<div class="title"><h3>
	<c:choose>
		<c:when test="${contIU eq 'I' }">
			Library Main Registration
		</c:when>
		<c:otherwise>
			Library Main Modify
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
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">Library Information</th>
			</tr>
			<tr>
				<th scope="row">Library*</th>
				<td>
					<div id="searchButton" class="btn-module mgtTB">
						<input id="titleNm" name="titleNm" type="text"  class="inp-field" value="<c:out value="${detail.titleNm}" escapeXml="false"/>" readonly="readonly" style="background-color: #E6E6E6; width: 83%;"/>
						<input id="reltdLib" name="reltdLib" type="hidden" value="<c:out value="${detail.reltdLib }"/>">
						<input type="hidden" id="mvLibCcd" name="mvLibCcd" value="<c:out value="${detail.mvLibCcd }"/>"/>
						<a href="javascript:;" onclick="fnValueDel()" class="btnStyleGoods05">X</a>
						<a href="javascript:;" onclick="fncSearchLibrary();" class="btnStyle01" style="margin-left:10px; width: 10%">Lib Search</a>
					</div>
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
	
	$("#btnSave").click(function() {
		
		if($.trim($("#titleNm").val()) == ""){
			alert("Please select a library.");
			$("#titleNm").focus();
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

		$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
	});
	
	function fncSearchLibrary() {
		win_pop("./searchLibrary.do?libSubMvSeqNo=<c:out value='${vo.libSubMvSeqNo}'/>", 'detail', '930', '700', 'yes');
	}	
	
	function fnValueDel() {
		$("#titleNm").val('');
		$("#reltdLib").val('');
		$("#mvLibCcd").val('');
	}	
	
	
	
</script>

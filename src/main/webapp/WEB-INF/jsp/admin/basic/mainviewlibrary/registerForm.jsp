<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="mvLibSeqNo" name="mvLibSeqNo" value="<c:out value="${detail.mvLibSeqNo }" />">

<section>	
	<div class="title"><h3>Home Library
	
	<c:choose>
		<c:when test="${contIU eq 'I' }">
			Registration
		</c:when>
		<c:otherwise>
			Modify
		</c:otherwise>
	</c:choose>
	
	</h3></div>
	<article>
	<div class="Cont_place">
	<table class="bd-form" summary="등록, 수정하는 영역 입니다.">
			<caption>열람,내용</caption>
			<colgroup>
				<col width="200px" />
				<col width="" />
			</colgroup>
			<tr>
				<th scope="row">Library *</th>
				<td>
					<div id="searchButton" class="btn-module mgtTB">
						<input id="titleNm" name=titleNm type="text" value='<c:out value="${detail.titleNm}" escapeXml="false"></c:out>' class="inp-field" readonly="readonly" style="background-color: #E6E6E6; width: 83%;" />
						<input type="hidden" id="reltdLib" name="reltdLib" value='<c:out value="${detail.reltdLib }"></c:out>'> 
						<input type="hidden" id="mvLibCcd" name="mvLibCcd" value='<c:out value="${detail.mvLibCcd }"></c:out>'>
						<input type="hidden" id="libCcd2" name="libCcd2" value='<c:out value="${detail.libCcd2 }"></c:out>'>
						<a href="javascript:;" id="pcListFileDel" onclick="fnValueDel()" class="btnStyleGoods05">X</a>
						<a href="javascript:;" onclick="fncSearchLibrary();" class="btnStyle01" style="margin-left:10px; width: 10%">Select</a>
					</div>
				</td>
			</tr>	
			<tr>
				<th scope="row">Status *</th>
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
						<a href="javascript:;" id="btnSave" class="btnStyle01" >Registration</a> 
					</c:when>
					<c:otherwise>
						<a href="javascript:;" id="btnSave" class="btnStyle01" >Registration</a> 
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
			
			if($.trim($("#reltdLib").val()) == ""){
				alert("Please select a library.");
				$("#reltdLib").focus();
				return false;
			}
			
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
/* 			$("#writeForm").prop("action","list.do");
			$("#writeForm").submit(); */
		});
		
		$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
	});
	
	//검색 팝업창 띄우기
	function fncSearchLibrary() {
		var ccdval = $("#mvLibCcd").val();
		//console.log(ccdval);
 		var seqval = $("#reltdLib").val();
		//console.log(seqval);
		var searchVal = encodeURIComponent($("#titleNm").val());
		//console.log(searchVal);
		var mvLibSeqNoVal = $("#mvLibSeqNo").val();
		var ccd2val = $("#libCcd2").val();
		//console.log(ccd2val);
		win_pop("./librarySearch.do?mvLibCcd=" + ccdval + "&reltdLib=" + seqval + "&mvLibSeqNo=" + mvLibSeqNoVal, 'detail', '930', '700', 'yes');
	}	
	// popup 삭제			
	function fnValueDel() {
		$("#mvLibCcd").val('');
		$("#reltdLib").val('');
		$("#titleNm").val('');
		$("#libCcd2").val('');
	}	
</script>		
							
							
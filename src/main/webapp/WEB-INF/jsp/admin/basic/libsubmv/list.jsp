<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="post">
<input type="hidden" id="libSubMvSeqNo" name="libSubMvSeqNo" />
<input type="hidden" id="langCd" name="langCd" value="<c:out value="${vo.langCd }" />">
<section>
	<div class="title"><h3>Library Main</h3></div>
	<div class="Cont_place">
	<article>
	<div class="inputUI_simple">
	<table class="bd-form s-form" summary="직접검색 영역 입니다.">
		<caption>직접검색 영역</caption>
		<colgroup>
			<col width="120px" />
			<col width="" />
			<col width="300px" />
			<col width="" />
		</colgroup>		
		<tr>
			<th scope="row">Search</th>
			<td> 
				<div class="select-inner">
					<select name="searchType" id="searchType">
	
					</select>
				</div>
				<input type="text" placeholder="Please enter your search term." class="inp-field widS mglS" id="searchValue" name="searchValue" value="<c:out value="${vo.searchValue }" escapeXml="false"/>" />
			</td>
				<td>
				<div class="btn-module floatR">
					<div class="rightGroup">
						<a href="javascript:;" class="search" onclick="searchClick();" id="btnSearch">Search</a>
						<a href="javascript:;" class="refresh" onclick="resetClick();" id="btnReset">Reset</a>
					</div>
				</div>	
			</td>
		</tr>
	</table>
	</div>
	</article>
	<article>
	
	<table class="bd-list inputUI_simple" summary="리스트 영역 입니다.">
		<caption>NO, Sort1, Sort2, Image, Title, Status, Registar, Registration Date, Modifier, ModificationDate</caption>
		<colgroup>
			<col width="3%" />
			<col width="8%" />
			<col width="9%" />
			<col width="8%" />
			<col width="13%" />
			<col width="" />
			<col width="6%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
		</colgroup>
		<thead>
		<tr>
			<th scope="col">No</th>
			<th scope="col">Sort<br/>Order</th>
			<th scope="col">Sort1</th>
			<th scope="col">Sort2</th>
			<th scope="col">Image</th>
			<th scope="col">Title</th>
			<th scope="col">Status</th>
			<th scope="col">Registrar</th>
			<th scope="col">Registration<br>Date</th>
			<th scope="col">Modifier</th>
			<th scope="col">Modification<br>Date</th>
		</tr>
		</thead>
		<tbody>
		
		<c:forEach var="list" items="${list}" varStatus="vs">
			<tr>
				<td>										
					<c:out value="${totalCount - vs.index}" />
				</td>
				<td>
					<input type="text" name="listSortOrder" class="inp-field widM2" value="<c:out value="${list.ordb}" />" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3" />
					<input type="hidden" name="listMvSqprdSeq" value="<c:out value="${list.libSubMvSeqNo}" />" />
				</td>	
				<td>
					<c:out value="${list.mvLibCcdNm }" />
				</td>
				<td>
					<c:out value="${list.ccdsNm }" />
				</td>
				<td>
					<a href="javascript:;"  onclick="detail('<c:out value="${list.libSubMvSeqNo}" />');">
						<img alt="<c:out value="${list.pcListImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/libSubMvImgView.do?libSubMvSeqNo=<c:out value="${ list.libSubMvSeqNo }"/>&imgKinds=pcList" style="max-width: 100px;" />
					</a>
				</td>
				<td class="txtEll textL2">
					<a href="javascript:;"  onclick="detail('<c:out value="${list.libSubMvSeqNo}" />');"><c:out value="${list.titleNm}" escapeXml="false"/></a>
				</td>
				<td>
					<c:out value="${list.useYnNm}" />
				</td>
				<td>
					<c:out value="${list.insPersonNm}" />
				</td>
				<td>
					<c:out value="${list.insDtm}" />
				</td>
				<td>
					<c:out value="${list.updPersonNm}" />
				</td>
				<td>
					<c:out value="${list.updDtm}" />
				</td>						
			</tr>
		</c:forEach>
		<c:if test="${fn:length(list) < 1 }">
			<tr>
				<td colspan="11" class="blank" >No registered data</td>
			</tr>
		</c:if>	
			
		</tbody>
	</table>
	
	
	
	<div class="btn-module mgtSM">
		<div class="leftGroup">		
			<a href="javascript:;"  onclick="doSortSave();" class="btnStyle01">Sort Order Save</a>
		</div>
		<div class="rightGroup">
			<a href="javascript:;"  onclick="doRegister();" class="btnStyle01" >New Registration</a>
		</div>
	</div>
	

	</article>
	</div>
		
</section>
</form>


<script type="text/javascript">

$(document).ready(function () {	
	$("#searchValue").keypress(function(e) {
	    if (e.keyCode == 13) {
	    	e.preventDefault();
	    	searchClick();
	    }
	});	
	
	$.fnGetCodeSelectAjax("sGb=LIBSUBMV_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "searchType", "${vo.searchType}", "selectbox", "");
	$("#searchType option[value='LIBSUBMV_01']").remove();
	$("#searchType option[value='LIBSUBMV_02']").attr('seleted','seleted');
});
//검색 , 페이징,
function fncPage(page) {
	$("#page").val(page);
	$("#listForm").prop("method", "get");
	$("#listForm").submit();
}

function searchClick(){	
	$("#page").val("1");
	$("#listForm").prop("method", "get");
	$("#listForm").submit();
}

function fnSearch(){
	$("#page").val("1");
	$("#listForm").prop("action","list.do");
	$("#listForm").prop("method", "get");
	$("#listForm").submit();
}
function doSortSave() {
	if(confirm("Do you really want to fix it?")) {
		$("#listForm").prop("method", "post");
		$("#listForm").prop("action","doSortOrder.do");
		$("#listForm").submit();
	}
	return false;
}

//값 초기화
function resetClick(){
	$("#searchType").val("");
	$("#searchValue").val("");
	$("#listForm").prop("action","list.do");
	$("#listForm").prop("method", "get");	
	$("#listForm").submit();
}
//등록페이지 이동
function doRegister(){	
	$("#listForm").prop("action","registerForm.do");
	$("#listForm").prop("method", "post");
	$("#listForm").submit();
}
//업데이트 이동
function detail(sn){
	$("#libSubMvSeqNo").val(sn);
	$("#listForm").prop("action","detail.do");
	$("#listForm").prop("method", "post");
	$("#listForm").submit();
}

</script>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="post">
<input type="hidden" id="mvLibSeqNo" name="mvLibSeqNo" value="<c:out value="${vo.mvLibSeqNo}" />">

<section>
	<div class="title"><h3>Home Library</h3></div>
	<div class="Cont_place">
	<article>
	<div class="inputUI_simple">
	<table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
		<caption>상태, 답변 상태, 기간설정, 직접검색 영역</caption>
		<colgroup>
			<col width="120px" />
			<col width="" />
			<col width="180px" />
			<col width="" />
		</colgroup>		
		<tr>
			<th scope="row">Search</th>
			<td> 
				<div class="select-inner">
					<select id="searchType" name="searchType" onchange="searchCode(this)">
						<option value="">ALL</option>
					</select>
				</div>
				<div class="select-inner">
					<select id="searchType2" name="searchType2">
					<option value="">ALL</option>
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
		<caption>NO</caption>
		<colgroup>
			<col width="3%" />
			<col width="8%" />
			<col width="10%" />
			<col width="10%" />
			<col width="" />
			<col width="" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
		</colgroup>
		<thead>
		<tr>
			<th scope="col">No</th> 
			<th scope="col">Sort<br>Order</th>
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
		
		<c:forEach var="list" items="${list}" varStatus="stat">
			<tr>
				<td>										
					<c:out value="${totalCount - stat.index}" />
				</td>
				<td>
					<input type="text" name="listSortOrder" class="inp-field widM2" value="<c:out value="${list.ordb}" />" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3" />
					<input type="hidden" name="listMvLibSeq" value="<c:out value="${list.mvLibSeqNo}" />" />
				</td>
				<td>
					<c:out value="${list.libCcd1Nm}" />		
				</td>
				<td>
					<c:out value="${list.libCcd2Nm}" escapeXml="false"/>
				</td>
				<td>
					<a href="javascript:;"  onclick="detail('<c:out value="${list.mvLibSeqNo}" />');">
						<img alt="<c:out value="${list.pcListImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/mainViewLibraryImgView.do?mvLibSeqNo=<c:out value='${list.mvLibSeqNo}'/>&imgKinds=pcList" style="max-width: 100px; vertical-align: middle;"/>
						
						<c:if test="${list.mvLibCcd eq 'MVLIB_01'} }">
							<img alt="<c:out value="${list.pcListImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/mainViewLibraryImgView.do?mvLibSeqNo=<c:out value='${list.mvLibSeqNo}'/>&imgKinds=mobileList" style="max-width: 100px; vertical-align: middle;"/>
						</c:if>
					</a>
				</td>
				<td class="txtEll textL2">
					<a href="javascript:;"  onclick="detail('<c:out value="${list.mvLibSeqNo}" />');"><c:out value="${list.titleNm}" escapeXml="false"/></a>
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
				<td colspan="11" class="blank">No registered data</td>
			</tr>
		</c:if>	
			
		</tbody>
	</table>
	
	<div class="btn-module mgtSM">
		<div class="leftGroup"><a href="javascript:;"  onclick="doSortSave();" class="btnStyle01" >Sort Order Save</a></div>
		<div class="rightGroup"><a href="javascript:;"  onclick="doRegister();" class="btnStyle01">New Registration</a></div>
	</div>
	
	<!-- table paging -->

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
	
	$.fnGetCodeSelectAjax("sGb=MVLIB_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "searchType", "${vo.searchType}", "selectbox", "");

	$("#searchType option[value='MVLIB_01']").remove();
	$("#searchType option[value='MVLIB_02']").remove();

	if($("#searchType option:selected").val() != "") {
		if($("#searchType option:selected").val() == "MVLIB_01") {
			$.fnGetCodeSelectAjax("sGb=EVT_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "searchType2", "${vo.searchType2}", "selectbox", "");
		} else if($("#searchType option:selected").val() == "MVLIB_02") {
			$.fnGetCodeSelectAjax("sGb=CELLO_NEWS&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "searchType2", "${vo.searchType2}", "selectbox", "");
		} else if($("#searchType option:selected").val() == "MVLIB_03") {
			$.fnGetCodeSelectAjax("sGb=BLOG_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "searchType2", "${vo.searchType2}", "selectbox", "");
		} else if($("#searchType option:selected").val() == "MVLIB_04") {
			$.fnGetCodeSelectAjax("sGb=REPORT_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "searchType2", "${vo.searchType2}", "selectbox", "");
		}
	}


});

// 온채인지 검색1이 체인지 되었을때 함수 실행
function searchCode(e) {
	if(e.value == "MVLIB_01") {
		$.fnGetCodeSelectAjax("sGb=EVT_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "searchType2", "${vo.searchType2}", "selectbox", "");
	} else if(e.value == "MVLIB_02") {
		$.fnGetCodeSelectAjax("sGb=CELLO_NEWS&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "searchType2", "${vo.searchType2}", "selectbox", "");
	} else if(e.value == "MVLIB_03") {
		$.fnGetCodeSelectAjax("sGb=BLOG_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "searchType2", "${vo.searchType2}", "selectbox", "");
	} else if(e.value == "MVLIB_04") {
		$.fnGetCodeSelectAjax("sGb=REPORT_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "searchType2", "${vo.searchType2}", "selectbox", "");
	} else {
		$("#searchType2").html("<option value=''>ALL</option>");
	}
	
	/* if(e.value == "MVLIB_01") var code_nm = 이벤트;
	else if(e.value == "MVLIB_02") var code_nm = 첼로스퀘어_소식;
	else if(e.value == "MVLIB_03") var code_nm = 블로그;
	else if(e.value == "MVLIB_04") var code_nm = 리포트;
	
	target.options.length = 0;
	
	for(key in code_nm){
		
		var opt = document.createElement("option");
		// value값 입력
		opt.value = code_nm[key];
		// key값 입력
		opt.innerHTML = key;
		opt.selected = "selected";
		target.appendChild(opt);
	} */
	
} 

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

//값 초기화
function resetClick(){
	$("#searchType").val("");
	$("#searchType2").val("");
	$("#searchValue").val("");
	$("input:radio[id='resetRadio']").prop("checked", true);
	$("#listForm").prop("method", "get");
	$("#listForm").prop("action","list.do");
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
	$("#mvLibSeqNo").val(sn);
	$("#listForm").prop("action","detail.do");
	$("#listForm").prop("method", "post");
	$("#listForm").submit();
}

//정렬순서 저장
function doSortSave() {
	if(confirm("Do you really want to fix it?")) {
		$("#listForm").prop("action","doSortOrder.do");
		$("#listForm").prop("method", "post");
		$("#listForm").submit();
	}
	return false;
}

</script>
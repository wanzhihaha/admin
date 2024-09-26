<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="post">
<input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
<input type="hidden" id="blogSeqNo" name="blogSeqNo" value="<c:out value="${vo.blogSeqNo }"/>" />
<input type="hidden" id="metaSeqNo" name="metaSeqNo">
<section>
	<div class="title"><h3>Blog</h3></div>
	<div class="Cont_place">
	<article>
	<div class="inputUI_simple">
	<table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
		<caption>상태, 답변 상태, 기간설정, 직접검색 영역</caption>
		<colgroup>
			<col width="120px" />
			<col width="400px" />
			<col width="180px" />
			<col width="" />
		</colgroup>		
		<tr>
			<th scope="row">Search</th>
			<td  colspan="4"> 
				<input type="text" placeholder="Please enter your search term." class="inp-field widS mglS" id="searchValue" name="searchValue" value="<c:out value="${vo.searchValue }" escapeXml="false"/>" />
			
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
	<div class="btn-module mgtL2 mgbS">
		<div class="leftGroup">
			<span class="tb-text"><strong>Total <span class="colorR"><c:out value="${totalCount }"></c:out></span> Cases</strong></span>
		</div>
		<div class="rightGroup">
			<select name="rowPerPage" id="rowPerPage" onchange="fnSearch();">
				<option value="10" <c:if test="${vo.rowPerPage eq '10'}">selected="selected"</c:if>>10</option>
				<option value="20" <c:if test="${vo.rowPerPage eq '20'}">selected="selected"</c:if>>20</option>
				<option value="30" <c:if test="${vo.rowPerPage eq '30'}">selected="selected"</c:if>>30</option>
				<option value="50" <c:if test="${vo.rowPerPage eq '50'}">selected="selected"</c:if>>50</option>
				<option value="100" <c:if test="${vo.rowPerPage eq '100'}">selected="selected"</c:if>>100</option>
			</select>
		</div>
	</div>
	<table class="bd-list inputUI_simple" summary="리스트 영역 입니다.">
		<caption>NO</caption>
		<colgroup>
			<col width="3%" />
			<col width="8%" />
			<col width="10%" />
			<col width="10%" />
			<col width="" />
			<col width="" />
			<col width="6%" />
			<col width="6%" />
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
			<th scope="col">Views</th>
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
					<span style="width: 50px;">					
						<c:out value="${(totalCount - ((vo.page-1) * vo.rowPerPage)) - stat.index}" />
					</span>	
				</td>
				<td>
					<input type="text" name="listSortOrder" class="inp-field widM2" value="<c:out value="${list.ordb}" />" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3" />
					<input type="hidden" name="listblogSeq" value="<c:out value="${list.blogSeqNo}" />" />
				</td>
				<td>
					<c:out value="${list.blogCcd1Nm }" />
				</td>
				<td>
					<c:out value="${list.blogCcd2 }" escapeXml="false"/>
				</td>
				<td>
					<a href="javascript:;"  onclick="detail('<c:out value="${list.blogSeqNo}" />');">
						<img alt="<c:out value="${list.pcListImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/blogImgView.do?blogSeqNo=<c:out value='${list.blogSeqNo}'/>&imgKinds=pcList" style="max-width: 100px; vertical-align: middle;"/>
					</a>
				</td>
				<td class="txtEll textL2">
					<a href="javascript:;"  onclick="detail('<c:out value="${list.blogSeqNo}" />');"><c:out value="${list.titleNm}" escapeXml="false"/></a>
				</td>
				<td>
					<c:out value="${list.useYnNm}" />
				</td>
				<td>
					<fmt:formatNumber value="${list.srchCnt }" pattern="#,###,###"/>
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
				<td colspan="12" class="blank" >No registered data</td>
			</tr>
		</c:if>	
			
		</tbody>
	</table>
	
	
	
	
	<div class="btn-module mgtSM">
		<div class="leftGroup"><a href="javascript:;"  onclick="doSortSave();" class="btnStyle01" >Sort Order Save</a></div>
		<div class="rightGroup"><a href="javascript:;"  onclick="doRegister();" class="btnStyle01" >New Registration</a></div>
	</div>
	<!-- table paging -->
	
	<div class="page-module">
      <p class="paging">
      	<blabPaging:paging currentPage="${vo.page }" totalCount="${totalCount}" rowSize="${vo.rowPerPage }" pagingId="admin" />
      </p>
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
	$("#listForm").prop("method", "get");
	$("#listForm").prop("action","list.do");
	$("#listForm").submit();
}

//값 초기화
function resetClick(){
	$("#searchValue").val("");
	$("input:radio[id='resetRadio']").prop("checked", true);
	$("#listForm").prop("method", "get");
	$("#listForm").prop("action","list.do");
	$("#listForm").submit();
}
//등록페이지 이동
function doRegister(){	
	$("#listForm").prop("method", "post");
	$("#listForm").prop("action","registerForm.do");
	$("#listForm").submit();
}
//업데이트 이동
function detail(sn){
	$("#blogSeqNo").val(sn);
	$("#listForm").prop("method", "post");
	$("#listForm").prop("action","detail.do");
	$("#listForm").submit();
}

//정렬순서 저장
function doSortSave() {
	if(confirm("Do you really want to fix it?")) {
		$("#listForm").prop("method", "post");
		$("#listForm").prop("action","doSortOrder.do");
		$("#listForm").submit();
	}
	return false;
}

</script>
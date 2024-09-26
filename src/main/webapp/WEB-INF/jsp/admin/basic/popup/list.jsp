<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="get">
<input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
<input type="hidden" id="popSeqNo" name="popSeqNo" value="<c:out value="${vo.popSeqNo }"/>"  class="inp-field widL" />

<section>
	<div class="title"><h3>Popup Management</h3></div>
	<div class="Cont_place">
	<article>
	<div class="inputUI_simple">
	<table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
		<caption>상태, 답변 상태, 기간설정, 직접검색 영역</caption>
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
					<select id="searchType" name="searchType">
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
	<div class="btn-module mgtL2 mgbS">
		<div class="leftGroup">
			<span class="tb-text">
				<strong>Total <span class="colorR"><c:out value="${totalCount}"></c:out></span> Cases</strong>
			</span>
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
			<col width="9%" />
			<col width="6%" />
			<col width="" />
			<col width="6%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
		</colgroup>
		<thead>
		<tr>
			<th scope="col">No</th>
			<th scope="col">Sort</th>
			<th scope="col">View</th>
			<th scope="col">Title</th>
			<th scope="col">Status</th>
			<th scope="col">Start<br>Date</th>
			<th scope="col">End<br>Date</th>
			<th scope="col">Size<br>(W/H)</th>
			<th scope="col">Location<br>(T/L)</th>
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
					<c:out value="${(totalCount - ((vo.page-1) * vo.rowPerPage)) - vs.index}" />
				</td>
				<td>
					<c:out value="${list.pcPopCcdNm }" />
				</td>
				<td>
					<button type="button" class="but-field widM2" onclick="popup('<c:out value="${list.pcPopSizeWdt}" />', '<c:out value="${list.pcPopSizeHgt}" />', '<c:out value="${list.pcPopLocTop}" />', '<c:out value="${list.pcPopLocLeft}" />', '<c:out value="${list.popSeqNo}" />')">View</button>
				</td>
				<td class="txtEll textL2">
					<a href="javascript:;"  onclick="detail('<c:out value="${list.popSeqNo}" />');"><c:out value="${list.titleNm}" escapeXml="false"/></a>
				</td>
				<td>
					<c:out value="${list.useYnNm}" />
				</td>
				
				<td>
					<c:out value="${list.peridStatDate}" />
				</td>
				<td>
					<c:out value="${list.peridEndDate}" />
				</td>
				
				<td>
					<c:out value="${list.pcPopSizeWdt}" /> / <c:out value="${list.pcPopSizeHgt}" /> <br>(px)
				</td>
				<td>
					<c:out value="${list.pcPopLocTop}" /> / <c:out value="${list.pcPopLocLeft}" /> <br>(px)
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
				<td colspan="13" class="blank" >No registered data</td>
			</tr>
		</c:if>	
			
		</tbody>
	</table>
	<div class="btn-module mgtSM">
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
		
		$.fnGetCodeSelectAjax("sGb=POPUP_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "searchType", "${vo.searchType}", "selectbox", "");
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

 	//값 초기화
	function resetClick(){
		$("#searchType").val("");
		$("#searchValue").val("");
		$("#listForm").prop("method", "get");
		$("#listForm").prop("action","list.do");
		$("#listForm").submit();
		/* location.href="./list.do"; */
	}
	//등록페이지 이동
	function doRegister(){	
		$("#listForm").prop("action","registerForm.do");
		$("#listForm").prop("method", "post");
		$("#listForm").submit();
	}
	//업데이트 이동
	function detail(sn){
		$("#popSeqNo").val(sn);
		$("#listForm").prop("action","detail.do");
		$("#listForm").prop("method", "post");
		$("#listForm").submit();
	}

 	// papup page
	function popup(wt,ht,tp,lt,seq) {
		var url = "./popupPreView.do?popSeqNo="+seq
	
		window.open(url, "ViewImg", "width="+wt+", height="+ht+", top="+tp+", left="+lt);
	}
	
</script>
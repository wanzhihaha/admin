<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<!-- START:CONTENT -->
<form id="searchFrom" action="" method="post">
<input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
<input type="hidden" id="sqprdCtgry" name="sqprdCtgry" value="<c:out value="${vo.sqprdCtgry }"/>">
<input type="hidden" id="sqprdCtgryNm" name="sqprdCtgryNm" value="<c:out value="${vo.sqprdCtgryNm }"/>">	
	
	
<section>
	<div class="title"><h3><c:out value="${vo.sqprdCtgryNm }" escapeXml="false"/> Product Search</h3></div>
	<div class="Cont_place">
	<article>
	<div class="inputUI_simple">
	<table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다.">
		<caption>주유소 주소로 검색 영역</caption>
		<colgroup>
			<col width="120px" />
			<col width="" />
			<col width="100px" />
		</colgroup>
		<tr>
			<th scope="row">Search</th>
			<td>
			<input type="text" class="inp-field widS mglS" id="goodsSearchValue" name="goodsSearchValue" value="<c:out value="${vo.goodsSearchValue}" escapeXml="false"/>" />
			</td>
			<td>
				<div class="btn-module floatR">
					<div class="rightGroup">
						<a href="javascript:;" class="search" onclick="fnSearch();" id="btnSearch">Search</a>
					</div>
				</div>	
			</td>
		</tr>
	</table>
	</div>
	</article>
	<article>

		<table class="bd-list inputUI_simple" summary="리스트 영역 입니다.">
			<caption>No, 주유소명 , 선택 여부</caption>
			<colgroup>
				<col width="5%" />
				<col width="25%" />
				<col width="%" />
				<col width="20%" />
			</colgroup>
			<thead>
			<tr>
				<th scope="col">No</th>
				<th scope="col">Sort</th>
				<th scope="col">Product Name</th>
				<th scope="col">Add</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="list" items="${list }" varStatus="stat">
				<tr>
					<td>
						<span style="width: 50px;">
							<c:out value="${(totalCount - ((vo.page-1) * vo.rowPerPage)) - stat.index}" />
						</span>
					</td>
					<td>
						<c:out value="${list.sqprdCtgryNm }" escapeXml="false"/> 
					</td>
					<td>
						<c:out value="${list.sqprdNm }" escapeXml="false"/> 
					</td>
					<td>
						<div class="btn-module useRS">
							<a href="javascript:;" onclick="useClick('<c:out value ='${list.sqprdNm }' escapeXml="false"/>', '<c:out value='${list.sqprdSeqNo }'/>');" class=tdSave  style="text-decoration: none;">Select</a>
						</div>
					</td>
				</tr>
			</c:forEach>
			<c:if test="${fn:length(list) < 1 }">
				<tr>
					<td colspan="4" class="blank">No registered data</td>
				</tr>
			</c:if>	
	
			</tbody>
		</table>
		
			<!-- table paging -->
		<div class="page-module">
			<p class="paging">
				<blabPaging:paging currentPage="${vo.page }" rowSize="${vo.rowPerPage }" totalCount="${totalCount}" pagingId="admin"/>
			</p>
		</div>

		<div class="btn-module" style="text-align: center;">
				<a href="javascript:;" class="btnStyle01" onclick="fnClose();" id="btnSearch">Close</a>
		</div>	
	</article>
	</div>

</section>
</form>

<script type="text/javascript">

	//검색/페이징
	function fnSearch(){
		if($.trim($("#goodsSearchValue").val()) == "") {
			alert("상품명을 입력하세요.");
			$("#goodsSearchValue").focus();
			return false;
		}
		
		$("#page").val("1");
		$("#searchFrom").prop("method", "get");
		$("#searchFrom").attr('action', '<c:url value="./searchGoods.do"/>').submit();
	}
	function fncPage(page) {
		$("#page").val(page);
		$("#searchFrom").prop("method", "get");
		$("#searchFrom").attr('action', '<c:url value="./searchGoods.do"/>').submit();
	}

	function fnClose() {
		self.close();
	}
	
	
	var valueNmAdd;
	var valueSeqNoAdd;
	var index = "<c:out value='${relIndex}'/>"
	function useClick(sqprdNm, sqprdSeqNo) {

		if($.trim($("#relsqprdSeqNo1", opener.document).val()) == sqprdSeqNo || $.trim($("#relsqprdSeqNo2", opener.document).val()) == sqprdSeqNo || $.trim($("#relsqprdSeqNo3", opener.document).val()) == sqprdSeqNo) {
			alert("该产品已添加，不可重复选择。");
			return false;
		}
		
		
		$("#reltdSqprd"+index,opener.document).val('');
		$("#relsqprdSeqNo"+index,opener.document).val('');
		
		$("#reltdSqprd"+index,opener.document).val(sqprdNm);
		$("#relsqprdSeqNo"+index,opener.document).val(sqprdSeqNo);
			
		self.close();
	}
	
</script>


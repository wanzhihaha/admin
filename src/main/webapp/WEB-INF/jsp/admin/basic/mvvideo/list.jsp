<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="post">
<input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
<input type="hidden" id="mvVideoSeqNo" name="mvVideoSeqNo" />
<section>
	<div class="title"><h3>视频</h3></div>
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
			<th scope="row">搜索</th>
			<td> 
				<div class="select-inner">
					<select name="searchType" id="searchType">
						<option value="">全部</option>
					</select>
				</div>
	
				<input type="text" placeholder="Please enter your search term." class="inp-field widS mglS" id="searchValue" name="searchValue" value="<c:out value="${vo.searchValue }" escapeXml="false"/>" />
				</td>
				<td>
				<div class="btn-module floatR">
					<div class="rightGroup">
						<a href="javascript:;" class="search" onclick="searchClick();" id="btnSearch">搜索</a>
						<a href="javascript:;" class="refresh" onclick="resetClick();" id="btnReset">重置</a>
					</div>
				</div>	
			</td>
			<td>
				<div class="btn-module floatR">
					<a href="javascript:;" onclick="fncSearchVideoHistory();" class="search" style="width:110px;" id="dicList">视频上传记录</a>
				</div>
			</td>
		</tr>
	</table>
	</div>
	</article>
	<article>
	
	<table class="bd-list inputUI_simple" summary="리스트 영역 입니다.">
		<caption>NO, Sort, Image, Status, Regist, Modify</caption>
		<colgroup>
			<col width="3%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
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
			<th scope="col">排序</th>
			<th scope="col">类型</th>
			<th scope="col">标题</th>
			<th scope="col">长视频链接</th>
			<th scope="col">短视频链接</th>
			<th scope="col">状态</th>
			<th scope="col">创建人</th>
			<th scope="col">创建时间</th>
			<th scope="col">更新人</th>
			<th scope="col">更新时间</th>
		</tr>
		</thead>
		<tbody>
		
		<c:forEach var="list" items="${list}" varStatus="vs">
			<tr>
				<td>										
					<c:out value="${ vs.index+1}" />
				</td>
				<td>
					<input type="text" name="listSortOrder" class="inp-field widM2" value="<c:out value="${list.ordb}" />" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3" />
					<input type="hidden" name="listMvVideoSeq" value="<c:out value="${list.mvVideoSeqNo}" />" />
				</td>
				<td>
					<c:out value="${list.mvVideoCcdNm }" />
				</td>
<%--				<td>--%>
<%--					<a href="javascript:;"  onclick="detail('<c:out value="${list.mvVideoSeqNo}" />');">--%>
<%--						<img alt="<c:out value="${list.pcListImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/mvVideoImgView.do?mvVideoSeqNo=<c:out value="${ list.mvVideoSeqNo }"/>&imgKinds=pcList" style="max-width: 100px;" />--%>
<%--					</a>--%>
<%--				</td>--%>
				<td class="txtEll textL2">
					<a href="javascript:;"  onclick="detail('<c:out value="${list.mvVideoSeqNo}" />');"><c:out value="${list.mvTitleNm}" escapeXml="false" /></a>
				</td>
				<td>
					<c:out value="${list.summaryInfo }" />
				</td>
				<td>
					<c:out value="${list.shortInfo }" />
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
				<td colspan="10" class="blank" >No registered data</td>
			</tr>
		</c:if>	
			
		</tbody>
	</table>
	
	
	
	<div class="btn-module mgtSM">
		<div class="leftGroup">
			<a href="javascript:;"  onclick="doSortSave();" class="btnStyle01">排序</a>
		</div>
		<div class="rightGroup">
			<a href="javascript:;"  onclick="doRegister();" class="btnStyle01" >新建</a>
		</div>
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
	
	$.fnGetCodeSelectAjax("sGb=MVVIDEO_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "searchType", "${vo.searchType }", "selectbox", "");
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

function fncSearchVideoHistory() {
	win_pop("/celloSquareAdmin/mvVideo/dicList.do", 'detail', '930', '700', 'yes');
}
//업데이트 이동
function detail(sn){
	$("#mvVideoSeqNo").val(sn);
	$("#listForm").prop("action","detail.do");
	$("#listForm").prop("method", "post");
	$("#listForm").submit();
}

</script>
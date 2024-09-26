<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>


<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">

<input type="hidden" id="libSubMevSeqNo" name="libSubMevSeqNo" value="<c:out value="${detail.libSubMevSeqNo }"/>"/>
<input type="hidden" name="page" value="<c:out value="${vo.page}"/>">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">


<section>
	
	<div class="title"><h3>Library Event
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
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">Library Information</th>
			</tr>
			<tr>
				<th scope="row">Library *</th>
				<td>
					<div id="searchButton" class="btn-module mgtTB">
						<input id="reltdEvt" name="reltdEvt" type="text"  class="inp-field" value="<c:out value="${detail.titleNm}" escapeXml="false"/>" maxlength="110" readonly="readonly" style="background-color: #E6E6E6; width: 83%;"/>
						<input id="relEvtSeqNo" name="relEvtSeqNo" type="hidden" value="<c:out value="${detail.reltdEvt }"/>">
						<input type="hidden" id="evtCcd" name="evtCcd" value="<c:out value="${detail.evtCcd }"/>"/>
						<a href="javascript:;" id="evtDel" onclick="fnValueDel()" class="btnStyleGoods05">X</a>
						<a href="javascript:;" id="evtSearch" onclick="fncSearchEvent();" class="btnStyle01" style="margin-left:10px; width: 10%">Event Search</a>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">PC List Image *<br/>(840px - 400px)</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="pcListOrginFile" name="pcListOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.pcListImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/mvEvtFileDown.do?libSubMevSeqNo=<c:out value="${detail.libSubMevSeqNo }"/>&imgKinds=pcList' style="color: blue;"><c:out value="${detail.pcListImgOrgFileNm}" escapeXml="false"/></a></span>
								<span><input type="checkbox" name="pcListFileDel" id="pcListFileDel" value="Y" /> <label for="pcListFileDel">Delete</label></span>
							</c:if>
						</div>
						<div>
							<input type="text" id="pcListImgAlt" name="pcListImgAlt" value="<c:out value="${detail.pcListImgAlt }" escapeXml="false"/>" placeholder="Please enter the Alt value for the image." class="inp-field widSM" maxlength="200" data-vbyte="200" data-vmsg="alt value" />
							<span style="line-height: 28px;"> ※Plase enter an 'alt' value for Image.</span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">Mobile List Image *<br/>(720px - 344px)</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="mobileListOrginFile" name="mobileListOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.mobileListImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/mvEvtFileDown.do?libSubMevSeqNo=<c:out value="${detail.libSubMevSeqNo }"/>&imgKinds=mobileList' style="color: blue;"><c:out value="${detail.mobileListImgOrgFileNm}" escapeXml="false"/></a></span>
								<span><input type="checkbox" name="mobileListFileDel" id="mobileListFileDel" value="Y" /> <label for="mobileListFileDel">Delete</label></span>
							</c:if>
						</div>
						<div>
							<input type="text" id="mobileListImgAlt" name="mobileListImgAlt" value="<c:out value="${detail.mobileListImgAlt }" escapeXml="false"/>" placeholder="Please enter the Alt value for the image." class="inp-field widSM" maxlength="200" data-vbyte="200" data-vmsg="alt value" />
							<span style="line-height: 28px;"> ※Plase enter an 'alt' value for Image.</span>
						</div>
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
		
		if($.trim($("#reltdEvt").val()) == ""){
			alert("Please select an event.");
			$("#reltdEvt").focus();
			return false;
		}
 
		if("${contIU}" == "I") {
			if($.trim($("#pcListOrginFile").val()) == ""){
				alert("Please select a PC List image file.");
				$("#pcListOrginFile").focus();
				return false;
			}
			if($.trim($("#mobileListOrginFile").val()) == ""){
				alert("Please select a Mobile List image file.");
				$("#mobileListOrginFile").focus();
				return false;
			}
		} else { // 수정일 경우
			if($("#pcListFileDel").is(":checked")) {	// 삭제를 체크했을시 입력 필수이므로 반드시 업로드 해야한다.
				if($.trim($("#pcListOrginFile").val()) == ""){
					alert("Pc List 이미지 파일을 선택해 주세요.Please select a PC List image file.");
					$("#pcListOrginFile").focus();
					return false;
				}
			}
			if($("#mobileListFileDel").is(":checked")) {	// 삭제를 체크했을시 입력 필수이므로 반드시 업로드 해야한다.
				if($.trim($("#mobileListOrginFile").val()) == ""){
					alert("Please select a Mobile List image file.");
					$("#mobileListOrginFile").focus();
					return false;
				}
			}
		}

		if ($("#pcListOrginFile").val() != ""){
			if(!/\.(gif|jpg|peg|png|jpeg)$/.test($("#pcListOrginFile").val().toLowerCase())){
				alert("Please select the image file (gif, jpg, bmp, png, jpeg)");
				$("#pcListOrginFile").focus();
				return false;
			}
		}

		if ($("#mobileListOrginFile").val() != ""){
			if(!/\.(gif|jpg|peg|png|jpeg)$/.test($("#mobileListOrginFile").val().toLowerCase())){
				alert("Please select the image file (gif, jpg, bmp, png, jpeg)");
				$("#mobileListOrginFile").focus();
				return false;
			}
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

		$.fnGetCodeSelectAjax("sGb=EVT_CCD&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "selEvtCcd", "${detail.evtCcd }", "selectbox", "");
		$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
	});
	
	function fncSearchEvent() {
		win_pop("./searchEvent.do?libSubMevSeqNo=<c:out value='${vo.libSubMevSeqNo}'/>", 'detail', '930', '700', 'yes');
	}	
	
	function fnValueDel() {
		$("#reltdEvt").val('');
		$("#relEvtSeqNo").val('');
		//$("#libSubMevSeqNo").val('');
	}	
	
	
	
</script>

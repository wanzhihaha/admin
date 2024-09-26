<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="evtSeqNo" name="evtSeqNo" value="<c:out value="${detail.evtSeqNo }" />">


<section>	
	<c:choose>
		<c:when test="${!empty detail.evtSeqNo }">
			<div class="title"><h3>Event Modify</h3></div>
		</c:when>
		<c:otherwise>
			<div class="title"><h3>Event Registration</h3></div>
		</c:otherwise>
	</c:choose>
	<article>
	<div class="Cont_place">
	<table class="bd-form s-form" summary="등록, 수정하는 영역 입니다.">
			<caption>열람,내용</caption>
			<colgroup>
				<col width="150px" />
				<col width="" />
			</colgroup>
			
			<%@ include file="/WEB-INF/jsp/common/seo/seoRegisterForm.jsp" %>
			
			<tr>
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">Event Information</th>
			</tr>
			<tr>
				<th scope="row">Sort*</th>
				<td>
					<select id="selEvtCd" name="evtCcd">
					</select>
				</td>
			</tr>
			<tr>
				<th scope="row">Title*</th>
				<td>
					<input id="titleNm" name="titleNm" type="text" placeholder="Please enter the title." class="inp-field widL" value="<c:out value="${detail.titleNm }" escapeXml="false"/>" maxlength="160" data-vbyte="160" data-vmsg="title"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Summary<br/>Information</th>
				<td>
					<div class="textCont">
						<textarea name="summaryInfo" id="summaryInfo" class="textarea" rows="10" cols="65" placeholder="Please enter the summary information." data-vbyte="1000" data-vmsg="summary information"><c:out value="${detail.summaryInfo}" escapeXml="false"/></textarea>			
						
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">PC List Image<br />(618px - 346px)</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="pcListOrginFile" name="pcListOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.pcListImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/eventDownload.do?evtSeqNo=<c:out value="${detail.evtSeqNo }"/>&imgKinds=pcList' style="color: blue;"><c:out value="${detail.pcListImgOrgFileNm} " escapeXml="false"/></a></span>
								<span><input type="checkbox" name="pcListFileDel" id="pcListFileDel" value="Y" /> <label for="pcListFileDel">Delete</label></span>
							</c:if>
						</div>
						<div>
							<input type="text" id="pcListImgAlt" name="pcListImgAlt" value="<c:out value="${detail.pcListImgAlt }" escapeXml="false"/>" placeholder="Please enter the Alt value for the image." class="inp-field widSM" maxlength="210" data-vbyte="210" data-vmsg="alt value" />
							<span style="line-height: 28px;"> ※Plase enter an 'alt' value for Image.</span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">PC Detail Image<br />(908px - )</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="pcDetailOrginFile" name="pcDetailOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.pcDetlImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/eventDownload.do?evtSeqNo=<c:out value="${detail.evtSeqNo }"/>&imgKinds=pcDetail' style="color: blue;"><c:out value="${detail.pcDetlImgOrgFileNm}" escapeXml="false"/></a></span>
								<span><input type="checkbox" name="pcDetailFileDel" id="pcDetailFileDel" value="Y" /> <label for="pcDetailFileDel">Delete</label></span>
							</c:if>
						</div>
						<div>
							<input type="text" id="pcDetlImgAlt" name="pcDetlImgAlt" value="<c:out value="${detail.pcDetlImgAlt }" escapeXml="false"/>" placeholder="Please enter the Alt value for the image." class="inp-field widSM" maxlength="210" data-vbyte="210" data-vmsg="alt value" />
							<span style="line-height: 28px;"> ※Plase enter an 'alt' value for Image.</span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">Mobile List Image<br />(624px - 350px)</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="mobileListOrginFile" name="mobileListOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.mobileListImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/eventDownload.do?evtSeqNo=<c:out value="${detail.evtSeqNo }"/>&imgKinds=mobileList' style="color: blue;"><c:out value="${detail.mobileListImgOrgFileNm}" escapeXml="false"/></a></span>
								<span><input type="checkbox" name="mobileListFileDel" id="mobileListFileDel" value="Y" /> <label for="mobileListFileDel">Delete</label></span>
							</c:if>
						</div>
						<div>
							<input type="text" id="mobileListImgAlt" name="mobileListImgAlt" value="<c:out value="${detail.mobileListImgAlt }" escapeXml="false"/>" placeholder="Please enter the Alt value for the image." class="inp-field widSM" maxlength="210" data-vbyte="210" data-vmsg="alt value" />
							<span style="line-height: 28px;"> ※Plase enter an 'alt' value for Image.</span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">Mobile Detail Image<br/>(908px - )</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="mobileDetailOrginFile" name="mobileDetailOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.mobileDetlImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/eventDownload.do?evtSeqNo=<c:out value="${detail.evtSeqNo }"/>&imgKinds=mobileDetail' style="color: blue;"><c:out value="${detail.mobileDetlImgOrgFileNm}" escapeXml="false"/></a></span>
								<span><input type="checkbox" name="mobileDetailFileDel" id="mobileDetailFileDel" value="Y" /> <label for="mobileDetailFileDel">Delete</label></span>
							</c:if>
						</div>
						<div>
							<input type="text" id="mobileDetlImgAlt" name="mobileDetlImgAlt" value="<c:out value="${detail.mobileDetlImgAlt }" escapeXml="false"/>" placeholder="Please enter the Alt value for the image." class="inp-field widSM" maxlength="210" data-vbyte="210" data-vmsg="alt value" />
							<span style="line-height: 28px;"> ※Plase enter an 'alt' value for Image.</span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">Event Details<br/>Imformation</th>
				<td>
					<div class="textCont">
						<textarea name="evtDetlContent" id="evtDetlContent" class="textarea" rows="10" cols="65" placeholder="내용입력 *" ><c:out value ="${detail.evtDetlContent }" escapeXml="false"/></textarea>			
						<script type="text/javascript" language="javascript">
							var CrossEditor = new NamoSE('evtDetlContent');
							CrossEditor.params.Width = "100%";
							CrossEditor.params.UserLang = "auto";
							CrossEditor.params.FullScreen = false;
							CrossEditor.params.WebsourcePath = "/namo_was"
							CrossEditor.params.ServerUrl = 2;	// 서버 url제외
							CrossEditor.params.DeleteCommand = ["backgroundimage","ce_imageeditor","insertfile","flash"];
							CrossEditor.params.UploadFileSizeLimit = "image:5242880,flash:2097152";

							CrossEditor.EditorStart();
							function OnInitCompleted(e){
								e.editorTarget.SetBodyValue(document.getElementById("evtDetlContent").value);
							}
						</script>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">Event Duration</th>
				<td>
					<c:choose>
						<c:when test="${empty detail.evtSeqNo}">
							<input class="inp-field wid100" type="text" id="evtStatDtm" name="evtStatDtm" value="" readonly="readonly"/>
							<span>~</span>
							<input class="inp-field wid100" type="text" id="evtEndDtm" name="evtEndDtm" value=""readonly="readonly"/>
						</c:when>
						<c:otherwise>
							<input class="inp-field wid100" type="text" id="evtStatDtm" name="evtStatDtm" value="<c:out value='${detail.evtStatDtm}'/>" readonly="readonly"/>
							<span>~</span>
							<input class="inp-field wid100" type="text" id="evtEndDtm" name="evtEndDtm" value="<c:out value='${detail.evtEndDtm}'/>" readonly="readonly"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th scope="row">New Tab Link<br/>Presence*</th>
				<td>
					<div id="radNewWindowLinkYn"></div>
					<input type="text" id="newLink" name="newLink" value="<c:out value="${detail.newLink }" escapeXml="false"/>" placeholder="링크를 입력해 주세요." class="inp-field widSM" style="margin-bottom: 5px;" maxlength="250" data-vbyte="250" data-vmsg="link"/>
					<span style="line-height: 28px; color: #b7b7b7;">※Plase enter an address to move to a new Tap.</span>				
					<br>※Use is an external link and is displayed in a new window. (ex) Please enter http://~)
				</td>
			</tr>
			<tr>
				<th scope="row">Status*</th>
				<td>
					<div id="radUseCd"></div>
				</td>
			</tr>						
			<tbody>
			
			</tbody>
			
		</table>
		
		<div class="btn-module mgtS">		
			<div class="rightGroup">
				<a id="btnSave" href="#none" class="btnStyle01" >Registration</a> 
				<a href="javascript:;" id="btnList" class="btnStyle01">Cancel</a>
			</div>
		</div>	
	</div>
	</article>
</section>
</form>
					
					
<script type="text/javascript">
$(document).ready(function(){
	$("#evtStatDtm, #evtEndDtm").datepicker({
		dateFormat:"yy-mm-dd"
		,showOn : 'button'
		,buttonImage : '/static/images/cal.png'
		,buttonImageOnly : true
		,buttonText : "달력"

	});
	
	$.fnGetCodeSelectAjax("sGb=EVT_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "selEvtCd", "${detail.evtCcd}", "selectbox", "");
	$.fnGetCodeSelectAjax("sGb=NEW_TAB_LNK&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radNewWindowLinkYn", "${detail.newWindowLinkYn == null ? 'N' : detail.newWindowLinkYn}", "radio", "newWindowLinkYn");
	if($("input[name=newWindowLinkYn]:checked").val() == "N") {
		$("#newLink").attr("disabled", true);
		$("#newLink").css("backgroundColor", "#E6E6E6");
		$("#newLink").removeAttr("placeholder");
	} 
	$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYnNm == null ? 'Y' : detail.useYn}", "radio", "useYn");
	
	
	// 새창 링크 사용유무
	$("input[name=newWindowLinkYn]").change(function(){
		if($("input[name=newWindowLinkYn]:checked").val() == "N") {
			$("#newLink").attr("disabled", true);
			$("#newLink").css("backgroundColor", "#E6E6E6");
			$("#newLink").removeAttr("placeholder");
		} else {
			$("#newLink").removeAttr("disabled");
			$("#newLink").css("backgroundColor", "white");
			$("#newLink").attr("placeholder", "Please enter a link.");
		}
	});
	$("#btnSave").click(function() {
	 	if(!metaValidation('<c:out value="${contIU}"/>')) {
			return false;
		}
	
		if($.trim($("#titleNm").val()) == ""){
			alert("Please enter the title.");
			$("#titleNm").focus();
			return false;
		}
		$("#evtDetlContent").val(CrossEditor.GetBodyValue());
		/* if(CrossEditor.GetTextValue () == ""){
		alert("이벤트 상세내용을 입력해 주세요.");
		CrossEditor.SetFocusEditor();
		return false;
		} */
	
		/*
		if($.trim($("#evtStatDtm").val()) == ""){
			alert("이벤트 시작날짜를 입력해 주세요.");
			$("#evtStatDtm").focus();
			return false;
		}
		if($.trim($("#evtEndDtm").val()) == ""){
			alert("이벤트 종료날짜를 입력해 주세요.");
			$("#evtEndDtm").focus();
			return false;
		}
		if($("input[name=newWindowLinkYn]:checked").val() == "Y"){
			if($.trim($("#newLink").val()) == ""){
				alert("새창 링크를 입력해 주세요.");
				$("#newLink").focus();
				return false;
			}
		}
		 */
		// form byte check
			if(formByteCheck() == false) {
				return false;
		}

		if ($("#pcListOrginFile").val() != ""){
			if(!/\.(gif|jpg|peg|png|jpeg)$/.test($("#pcListOrginFile").val().toLowerCase())){
				alert("Please select the image file (gif, jpg, bmp, png, jpeg)");
				$("#pcListOrginFile").focus();
				return false;
			}
		}

		if ($("#pcDetailOrginFile").val() != ""){
			if(!/\.(gif|jpg|peg|png|jpeg)$/.test($("#pcDetailOrginFile").val().toLowerCase())){
				alert("Please select the image file (gif, jpg, bmp, png, jpeg)");
				$("#pcDetailOrginFile").focus();
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

		if ($("#mobileDetailOrginFile").val() != ""){
			if(!/\.(gif|jpg|peg|png|jpeg)$/.test($("#mobileDetailOrginFile").val().toLowerCase())){
				alert("Please select the image file (gif, jpg, bmp, png, jpeg)");
				$("#mobileDetailOrginFile").focus();
				return false;
			}
		}

		<c:choose>
			<c:when test="${contIU eq 'I' }">
				$("#writeForm").attr('action', '<c:url value="./register.do"/>').submit();
			</c:when>
			<c:otherwise>
				$("#writeForm").attr('action', '<c:url value="./update.do"/>').submit();
			</c:otherwise>
		</c:choose>
	});
});	

//날짜 밸리데이션
$("#evtEndDtm").on("change keyup paste", function(){
var stDt = $("#evtStatDtm").val();
var stDtArr = stDt.split("-");
var stDtObj = new Date(stDtArr[0], Number(stDtArr[1])-1, stDtArr[2]);
var enDt = $("#evtEndDtm").val();
var enDtArr = enDt.split("-");
var enDtObj = new Date(enDtArr[0], Number(enDtArr[1])-1, enDtArr[2]);
var betweenDay = (enDtObj.getTime() - stDtObj.getTime())/1000/60/60/24;

	if(stDt == "" && enDt != ""){
		alert("이벤트 시작날짜를 입력해 주세요.");
		$("#evtStatDtm").focus();
		$("#evtEndDtm").val("");
		return false;
	}
	if(betweenDay < 0) {
		alert("이벤트 종료날짜가 시작날짜보다 이릅니다. 다시 입력해 주세요.");
		$("#evtEndDtm").focus();
		$("#evtEndDtm").val("");
		return false;
	}
});
	
$("#evtStatDtm").on("change keyup paste", function(){	
var stDt = $("#evtStatDtm").val();
var stDtArr = stDt.split("-");
var stDtObj = new Date(stDtArr[0], Number(stDtArr[1])-1, stDtArr[2]);
var enDt = $("#evtEndDtm").val();
var enDtArr = enDt.split("-");
var enDtObj = new Date(enDtArr[0], Number(enDtArr[1])-1, enDtArr[2]);
var betweenDay = (enDtObj.getTime() - stDtObj.getTime())/1000/60/60/24;

	if(betweenDay < 0) {
		alert("이벤트 시작날짜가 종료날짜보다 후일입니다. 다시 입력해 주세요.");
		$("#evtStatDtm").focus();
		$("#evtStatDtm").val("");
		return false;
	}
});	
		
$("#btnList").on("click",function() {
	history.back();
});
		
	

	
</script>		
							

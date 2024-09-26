<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="videoSeqNo" name="videoSeqNo" value="<c:out value="${detail.videoSeqNo }" />">



<section>	
	<c:choose>
		<c:when test="${!empty detail.videoSeqNo }">
			<div class="title"><h3>Video Modify</h3></div>
		</c:when>
		<c:otherwise>
			<div class="title"><h3>Video Registration</h3></div>
		</c:otherwise>
	</c:choose>
	<article>
	<div class="Cont_place">
	<table class="bd-form" summary="등록, 수정하는 영역 입니다.">
			<caption>열람,내용</caption>
			<colgroup>
				<col width="200px" />
				<col width="" />
			</colgroup>
			
			<%@ include file="/WEB-INF/jsp/common/seo/seoRegisterForm.jsp" %>
			
			<tr>
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">Video Information</th>
			</tr>
			<tr>
				<th scope="row">Sort*</th>
				<td>
					<select id="selVideoCd" name="videoCcd">
					</select>
				</td>
			</tr>
			<tr>
				<th scope="row">Title*</th>
				<td>
					<input id="titleNm" name="titleNm" type="text" placeholder="Please enter the title." class="inp-field widL" value="<c:out value="${detail.titleNm }" escapeXml="false"/>" maxlength="100" data-vmsg="title"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Summary<br/>Information</th>
				<td>
					<div class="textCont">
						<textarea name="summaryInfo" id="summaryInfo" class="textarea" rows="10" cols="65" placeholder="Please enter the summary information." data-vbyte="1000" data-vmsg="summary information"><c:out value="${detail.summaryInfo }" escapeXml="false"/></textarea>			
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">PC List Image<br/>(618px - 346px)</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="pcListOrginFile" name="pcListOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.pcListImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/videoDownload.do?videoSeqNo=<c:out value="${detail.videoSeqNo }"/>&imgKinds=pcList' style="color: blue;"><c:out value="${detail.pcListImgOrgFileNm}" escapeXml="false"/></a></span>
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
				<th scope="row">PC Detail Image<br/>(1200px –)</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="pcDetailOrginFile" name="pcDetailOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.pcDetlImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/videoDownload.do?videoSeqNo=<c:out value="${detail.videoSeqNo }"/>&imgKinds=pcDetail' style="color: blue;"><c:out value="${detail.pcDetlImgOrgFileNm}" escapeXml="false"/></a></span>
								<span><input type="checkbox" name="pcDetailOrginFile" id="pcDetailOrginFile" value="Y" /> <label for="pcDetailOrginFile">Delete</label></span>
							</c:if>
						</div>
						<div>
							<input type="text" id="pcDetlImgAlt" name="pcDetlImgAlt" value="<c:out value="${detail.pcDetlImgAlt }" escapeXml="false"/>" placeholder="이미지에 대한 Alt 값을 입력해주세요." class="inp-field widSM" maxlength="210" data-vbyte="210" data-vmsg="alt value" />
							<span style="line-height: 28px;"> ※Plase enter an 'alt' value for Image.</span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">Mobile List Image<br/>(720px - 403px)</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="mobileListOrginFile" name="mobileListOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.mobileListImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/videoDownload.do?videoSeqNo=<c:out value="${detail.videoSeqNo }"/>&imgKinds=mobileList' style="color: blue;"><c:out value="${detail.mobileListImgOrgFileNm}" escapeXml="false"/></a></span>
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
				<th scope="row">Mobile Detail Image<br/>(720px -)</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="mobileDetailOrginFile" name="mobileDetailOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.mobileDetlImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/videoDownload.do?videoSeqNo=<c:out value="${detail.videoSeqNo }"/>&imgKinds=mobileDetail' style="color: blue;"><c:out value="${detail.mobileDetlImgOrgFileNm}" escapeXml="false"/></a></span>
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
				<th scope="row">Contents*</th>
				<td>
					<div id="radContentsType"></div>
					
					<div class="textCont">
						<textarea name="contents" id="contents" class="textarea" rows="10" cols="65" placeholder="Please enter the address, link, and video html." data-vbyte="1000" data-vmsg="address, link, and video html"><c:out value="${detail.contents }" escapeXml="false"/></textarea>			
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">Details<br/>Imformation</th>
				<td>
					
					<div class="textCont">
						<textarea name="detlInfo" id="detlInfo" class="textarea" rows="10" cols="65" placeholder="" ><c:out value="${detail.detlInfo } " escapeXml="false"/></textarea>		
						<script type="text/javascript" language="javascript">
							var CrossEditor = new NamoSE('detlInfo');
							CrossEditor.params.Width = "100%";
							CrossEditor.params.UserLang = "auto";
							CrossEditor.params.FullScreen = false;
							CrossEditor.params.WebsourcePath = "/namo_was"
							CrossEditor.params.ServerUrl = 2;	// 서버 url제외
							CrossEditor.params.DeleteCommand = ["backgroundimage","ce_imageeditor","insertfile","flash"];
							CrossEditor.params.UploadFileSizeLimit = "image:5242880,flash:2097152";

							CrossEditor.EditorStart();
							function OnInitCompleted(e){
								e.editorTarget.SetBodyValue(document.getElementById("detlInfo").value);
							}
						</script>	
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
	
	
	//라디오 체크박스 인풋 사용유무
	$.fnGetCodeSelectAjax("sGb=VIDEO_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "selVideoCd", "${detail.videoCcd}", "selectbox", "");
	$.fnGetCodeSelectAjax("sGb=VIDEO_CON_TP&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radContentsType", "${detail.contentsType == null ? 'HTML' : detail.contentsType}", "radio", "contentsType");
	$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYnNm == null ? 'Y' : detail.useYn}", "radio", "useYn");
	
	
	
	var strUrl = null;
	$("#btnSave").click(function() {
		
		//링크값 띄워쓰기 안되게끔 설정
		if($("input[name='radioName']:checked").val()  == 'LINK'){
		strUrl = $("#contents").val().replace(/\s/g, "");
		$("#contents").val(strUrl);
		}
		
		if(!metaValidation('<c:out value="${contIU}"/>')) {
			return false;
		}
		if($.trim($("#titleNm").val()) == ""){
			alert("Please enter the title.");
			$("#titleNm").focus();
			return false;
		}
		
		if($.trim($("#contents").val()) == ""){
			alert("Please enter the address, link, and video html.");
			$("#contents").focus();
			return false;
		}
		$("#detlInfo").val(CrossEditor.GetBodyValue());

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
			<c:when test="${detail.videoSeqNo == null}">
				$("#writeForm").attr('action', '<c:url value="./register.do"/>').submit();
			</c:when>
			<c:otherwise>
				$("#writeForm").attr('action', '<c:url value="./update.do"/>').submit();
			</c:otherwise>
		</c:choose>
	});
});	

		
$("#btnList").on("click",function() {
	history.back();
	//$("#writeForm").attr('action', '<c:url value="./list.do"/>').submit();
});
		
	

	
</script>		
							

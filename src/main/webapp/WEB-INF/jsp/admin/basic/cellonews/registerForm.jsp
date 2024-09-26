<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>


<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">

<input type="hidden" id="newsSeqNo" name="newsSeqNo" value="<c:out value="${detail.newsSeqNo }"/>"/>
<input type="hidden" name="page" value="<c:out value="${vo.page}"/>">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">

<section>
	
	<div class="title"><h3>Cello Square Newsroom 
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
			
			<%@ include file="/WEB-INF/jsp/common/seo/seoRegisterForm.jsp" %>

			<tr>
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">Cello News</th>
			</tr>
			<tr>
				<th scope="row">Sort1 *</th>
				<td>
					<select id="selNewsCd" name="newsCcd1">
					</select>
				</td>
			</tr>
			<tr>
				<th scope="row">Sort2(view)</th>
				<td>
					<input type="text" id="newsCcd2" name="newsCcd2" placeholder="Please enter the Sort2(view)." value="<c:out value="${detail.newsCcd2}" escapeXml="false"/>"  class="inp-field widL" maxlength="200" data-vbyte="200" data-vmsg="Sort2"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Title *</th>
				<td>
					<input type="text" id="titleNm" name="titleNm" placeholder="Please enter the title."  value="<c:out value="${detail.titleNm}" escapeXml="false"/>"  class="inp-field widL" maxlength="160" data-vbyte="160" data-vmsg="title" />
				</td>
			</tr>
			<tr>
				<th scope="row">Summary<br>Information</th>
				<td>
					<div class="textCont">
						<textarea name="summaryInfo" id="summaryInfo" class="textarea" rows="5" cols="65" placeholder="Please enter the summary information." data-vbyte="850" data-vmsg="summary information" ><c:out value="${detail.summaryInfo }" escapeXml="false"/></textarea>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">PC List Image<br/>(200px - 200px)</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="pcListOrginFile" name="pcListOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.pcListImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/newsFileDown.do?newsSeqNo=<c:out value="${detail.newsSeqNo }"/>&imgKinds=pcList' style="color: blue;"><c:out value="${detail.pcListImgOrgFileNm}" escapeXml="false"/></a></span>
								<span><input type="checkbox" name="pcListFileDel" id="pcListFileDel" value="Y" /> <label for="pcListFileDel">Delete</label></span>
							</c:if>
						</div>
						<div>
							<input type="text" id="pcListImgAlt" name="pcListImgAlt" value="<c:out value="${detail.pcListImgAlt }" escapeXml="false"/>" placeholder="Please enter the Alt value for the image." class="inp-field widSM" maxlength="200" data-vbyte="200" data-vmsg="alt value"/>
							<span style="line-height: 28px;"> ※Plase enter an 'alt' value for Image.</span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">PC Detail Image<br/>(908px - )</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="pcDetailOrginFile" name="pcDetailOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.pcDetlImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/newsFileDown.do?newsSeqNo=<c:out value="${detail.newsSeqNo }"/>&imgKinds=pcDetail' style="color: blue;"><c:out value="${detail.pcDetlImgOrgFileNm}" escapeXml="false"/></a></span>
								<span><input type="checkbox" name="pcDetailFileDel" id="pcDetailFileDel" value="Y" /> <label for="pcDetailFileDel">Delete</label></span>
							</c:if>
						</div>
						<div>
							<input type="text" id="pcDetlImgAlt" name="pcDetlImgAlt" value="<c:out value="${detail.pcDetlImgAlt }" escapeXml="false"/>" placeholder="Please enter the Alt value for the image." class="inp-field widSM" maxlength="200" data-vbyte="200" data-vmsg="alt value"/>
							<span style="line-height: 28px;"> ※Plase enter an 'alt' value for Image.</span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">Mobile List Image<br/>(200px - 200px)</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="mobileListOrginFile" name="mobileListOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.mobileListImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/newsFileDown.do?newsSeqNo=<c:out value="${detail.newsSeqNo }"/>&imgKinds=mobileList' style="color: blue;"><c:out value="${detail.mobileListImgOrgFileNm}" escapeXml="false"/></a></span>
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
				<th scope="row">Mobile Detail Image<br/>(908px - )</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="mobileDetailOrginFile" name="mobileDetailOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.mobileDetlImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/newsFileDown.do?newsSeqNo=<c:out value="${detail.newsSeqNo }"/>&imgKinds=mobileDetail' style="color: blue;"><c:out value="${detail.mobileDetlImgOrgFileNm}" escapeXml="false"/></a></span>
								<span><input type="checkbox" name="mobileDetailFileDel" id="mobileDetailFileDel" value="Y" /> <label for="mobileDetailFileDel">Delete</label></span>
							</c:if>
						</div>
						<div>
							<input type="text" id="mobileDetlImgAlt" name="mobileDetlImgAlt" value="<c:out value="${detail.mobileDetlImgAlt }" escapeXml="false"/>" placeholder="Please enter the Alt value for the image." class="inp-field widSM" maxlength="200" data-vbyte="200" data-vmsg="alt value"/>
							<span style="line-height: 28px;"> ※Plase enter an 'alt' value for Image.</span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">News Details<br/>Information</th>
				<td>
					<div class="textCont">
					<textarea name="newsDetlInfo" id="newsDetlInfo" class="textarea" rows="10" cols="65"  placeholder="내용입력 입력하세요." ><c:out value="${detail.newsDetlInfo}" escapeXml="false"/></textarea>
					<script type="text/javascript" language="javascript">
							var CrossEditor = new NamoSE('newsDetlInfo');
							CrossEditor.params.Width = "100%";
							CrossEditor.params.UserLang = "auto";
							CrossEditor.params.FullScreen = false;
							CrossEditor.params.WebsourcePath = "/namo_was"
							CrossEditor.params.ServerUrl = 2;	// 서버 url제외
							CrossEditor.params.DeleteCommand = ["backgroundimage","ce_imageeditor","insertfile","flash"];
							CrossEditor.params.UploadFileSizeLimit = "image:5242880,flash:2097152";

							CrossEditor.EditorStart();
							function OnInitCompleted(e){
								e.editorTarget.SetBodyValue(document.getElementById("newsDetlInfo").value);
							}
						</script>					
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
		if(!metaValidation('<c:out value="${contIU}"/>')) {
			return false;
		}
		
		if($.trim($("#titleNm").val()) == ""){
			alert("Please enter the title.");
			$("#titleNm").focus();
			return false;
		}
		
		$("#newsDetlInfo").val(CrossEditor.GetBodyValue());
		
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
		//$("#writeForm").prop("action","list.do");
		//$("#writeForm").prop("method","get");
		//$("#writeForm").submit();
	});

		$.fnGetCodeSelectAjax("sGb=CELLO_NEWS&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "selNewsCd", "${detail.newsCcd1 }", "selectbox", "");
		$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
	});
	
	
</script>

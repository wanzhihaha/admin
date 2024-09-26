<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="blogSeqNo" name="blogSeqNo" value="<c:out value="${detail.blogSeqNo }" />">


<section>	
	<div class="title"><h3>Blog
	
	<c:choose>
		<c:when test="${contIU eq 'I' }">
			Registration
		</c:when>
		<c:otherwise>
			Modify
		</c:otherwise>
	</c:choose>
	
	</h3></div>
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
				<th colspan="10" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">Blog Information</th>
			</tr>
			<tr>
				<th scope="row">Sort *</th>
				<td>
					<select id="selBlogCd" name="blogCcd1">
					</select>
				</td>
			</tr>
			<tr>
				<th scope="row">Sort2</th>
				<td>
					<input id="BlogCcd2" name="blogCcd2" type="text" class="inp-field widL" placeholder="Please enter the Sort2." value="<c:out value="${detail.blogCcd2 }" escapeXml="false"/>" maxlength="20" data-vbyte="20" data-vmsg="Sort2"/>
				</td>
			</tr>		
			<tr>
				<th scope="row">Title *</th>
				<td>
					<input id="titleNm" name="titleNm" type="text" class="inp-field widL" placeholder="Please enter the title." value="<c:out value="${detail.titleNm }" escapeXml="false"/>" maxlength="100" data-vmsg="title"/>
				</td>
			</tr>
			<tr>`
				<th scope="row">Summary<br>Information</th>
				<td>
					<div class="textCont">
						<textarea name="summaryInfo" id="summaryInfo" class="textarea" rows="5" cols="65" placeholder="Please enter the summary information." data-vbyte="1000" data-vmsg="summary information"><c:out value="${detail.summaryInfo }" escapeXml="false"/></textarea>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">PC List Image</th>
				<td>
					<div style="margin-bottom: 7px;">
						<input type="file" id="pcListImgUpload" name="pcListImgUpload" class="inp-field widSM"/>
							<c:if test="${contIU eq 'U' }">
							<span style="line-height: 28px;">
								<a href="<blabProperty:value key="system.admin.path"/>/blogImgpDown.do?blogSeqNo=<c:out value='${detail.blogSeqNo}'/>&imgKinds=pcList"><font color="blue"><c:out value="${detail.pcListImgOrgFileNm}" /></font></a>
							</span>
							<c:if test="${!empty detail.pcListImgOrgFileNm}">
							<span><input type="checkbox" name="pcListFileDel" id="pcListFileDel" value="Y" /><label for="pcListFileDel">Delete</label></span>
							</c:if>
							</c:if>
					</div>
					<input type="text" id="pcListImgAlt" name="pcListImgAlt" placeholder="Please enter the Alt value for the image." value="<c:out value="${detail.pcListImgAlt }" escapeXml="false"/>" class="inp-field widSM" maxlength="200" data-vbyte="200" data-vmsg="alt value"/>
					<span style="line-height: 28px;"> ※Plase enter an 'alt' value for Image.</span>
				</td>
			</tr>
			<tr>
				<th scope="row">PC Detail Image</th>
				<td>
					<div style="margin-bottom: 7px;">
						<input type="file" id="pcDetlImgUpload" name="pcDetlImgUpload" class="inp-field widSM"/>
							<c:if test="${contIU eq 'U' }">
							<span style="line-height: 28px;">
								<a href="<blabProperty:value key="system.admin.path"/>/blogImgpDown.do?blogSeqNo=<c:out value='${detail.blogSeqNo}'/>&imgKinds=pcDetail" ><font color="blue"><c:out value="${detail.pcDetlImgOrgFileNm}" /></font></a>
							</span>
							<c:if test="${!empty detail.pcDetlImgOrgFileNm }">
							<span><input type="checkbox" name="pcDetlFileDel" id="pcDetlFileDel" value="Y" /><label for="pcDetlFileDel">Delete</label></span>
							</c:if>
							</c:if>
					</div>
					<input type="text" id="pcDetlImgAlt" name="pcDetlImgAlt" placeholder="Please enter the Alt value for the image." value="<c:out value="${detail.pcDetlImgAlt }" escapeXml="false"/>" class="inp-field widSM" maxlength="200" data-vbyte="200" data-vmsg="alt value"/>
					<span style="line-height: 28px;"> ※Plase enter an 'alt' value for Image.</span>
				</td>
			</tr>
			<tr>
				<th scope="row">Mobile List Image</th>
				<td>
					<div style="margin-bottom: 7px;">
						<input type="file" id="mobileListImgUpload" name="mobileListImgUpload" class="inp-field widSM"/>
							<c:if test="${contIU eq 'U' }">
							<span style="line-height: 28px;">
								<a href="<blabProperty:value key="system.admin.path"/>/blogImgpDown.do?blogSeqNo=<c:out value='${detail.blogSeqNo}'/>&imgKinds=mobileList" ><font color="blue"><c:out value="${detail.mobileListImgOrgFileNm}" /></font></a>
							</span>
							<c:if test="${!empty detail.mobileListImgOrgFileNm}">
							<span><input type="checkbox" name="mobileListFileDel" id="mobileListFileDel" value="Y" /><label for="mobileListFileDel">Delete</label></span>
							</c:if>
							</c:if>
					</div>
					<input type="text" id="mobileListImgAlt" name="mobileListImgAlt" placeholder="Please enter the Alt value for the image." value="<c:out value="${detail.mobileListImgAlt }" escapeXml="false"/>" class="inp-field widSM" maxlength="200" data-vbyte="200" data-vmsg="alt value"/>
					<span style="line-height: 28px;"> ※Plase enter an 'alt' value for Image.</span>
				</td>
			</tr>
			<tr>
				<th scope="row">Mobile Detail Image</th>
				<td>
					<div style="margin-bottom: 7px;">
						<input type="file" id="mobileDetlImgUpload" name="mobileDetlImgUpload" class="inp-field widSM"/>
							<c:if test="${contIU eq 'U' }">
							<span style="line-height: 28px;">
								<a href="<blabProperty:value key="system.admin.path"/>/blogImgpDown.do?blogSeqNo=<c:out value='${detail.blogSeqNo}'/>&imgKinds=mobileDetail" ><font color="blue"><c:out value="${detail.mobileDetlImgOrgFileNm}" /></font></a>
							</span>
							<c:if test="${!empty detail.mobileDetlImgOrgFileNm}">
							<span><input type="checkbox" name="mobileDetlFileDel" id="mobileDetlFileDel" value="Y" /><label for="mobileDetlFileDel">Delete</label></span>
							</c:if>
							</c:if>
					</div>
					<input type="text" id="mobileDetlImgAlt" name="mobileDetlImgAlt" placeholder="Please enter the Alt value for the image." value="<c:out value="${detail.mobileDetlImgAlt }" escapeXml="false"/>" class="inp-field widSM" maxlength="200" data-vbyte="200" data-vmsg="alt value"/>
					<span style="line-height: 28px;"> ※Plase enter an 'alt' value for Image.</span>
				</td>
			</tr>
			<tr>
				<th scope="row">Details<br>Information *</th>
				<td>
					<div class="textCont">
						<textarea name="detlInfo" id="detlInfo" class="textarea" rows="10" cols="65" placeholder="내용을 입력하세요."><c:out value="${detail.detlInfo }" escapeXml="false"/></textarea>			
						<script type="text/javascript" language="javascript">
							var CrossEditor = new NamoSE('detlInfo');
							CrossEditor.params.Width = "100%";
							CrossEditor.params.UserLang = "zh-cn";
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
				<th scope="row">To inquire</th>
				<td>
					<div id="radIqrCd"></div>
				</td>
			</tr>
			<tr>
				<th scope="row">Status *</th>
				<td>
					<div id="radUseCd"></div>
				</td>
			</tr>						
			<tbody>
			
			</tbody>
			
		</table>
		
		<div class="btn-module mgtS">		
			<div class="rightGroup">
				<c:choose>
					<c:when test="${contIU eq 'I' }">
						<a href="javascript:;" id="btnSave" class="btnStyle01" >Registration</a> 
					</c:when>
					<c:otherwise>
						<a href="javascript:;" id="btnSave" class="btnStyle01" >Registration</a> 
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
		
		$("#btnSave").click(function() {
			
			if(!metaValidation('<c:out value="${contIU}"/>')) {
				return false;
			}
			
			if($.trim($("#titleNm").val()) == ""){
				alert("Please enter the title.");
				$("#titleNm").focus();
				return false;
			}
			
			$("#detlInfo").val(CrossEditor.GetBodyValue());			
			if(CrossEditor.GetTextValue () == ""){
				//console.log($.trim(CrossEditor.GetTextValue() == ""));
				alert("Please enter your details with text.");
				CrossEditor.SetFocusEditor();
				return false;
			} 
			
			if(formByteCheck() == false) {
				return false;
			}

			if ($("#pcListImgUpload").val() != ""){
				if(!/\.(gif|jpg|peg|png|jpeg)$/.test($("#pcListImgUpload").val().toLowerCase())){
					alert("Please select the image file (gif, jpg, bmp, png, jpeg)");
					$("#pcListImgUpload").focus();
					return false;
				}
			}

			if ($("#pcDetlImgUpload").val() != ""){
				if(!/\.(gif|jpg|peg|png|jpeg)$/.test($("#pcDetlImgUpload").val().toLowerCase())){
					alert("Please select the image file (gif, jpg, bmp, png, jpeg)");
					$("#pcDetlImgUpload").focus();
					return false;
				}
			}

			if ($("#mobileListImgUpload").val() != ""){
				if(!/\.(gif|jpg|peg|png|jpeg)$/.test($("#mobileListImgUpload").val().toLowerCase())){
					alert("Please select the image file (gif, jpg, bmp, png, jpeg)");
					$("#mobileListImgUpload").focus();
					return false;
				}
			}

			if ($("#mobileDetlImgUpload").val() != ""){
				if(!/\.(gif|jpg|peg|png|jpeg)$/.test($("#mobileDetlImgUpload").val().toLowerCase())){
					alert("Please select the image file (gif, jpg, bmp, png, jpeg)");
					$("#mobileDetlImgUpload").focus();
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
			//$("#writeForm").submit();
		});
		$.fnGetCodeSelectAjax("sGb=BLOG_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "selBlogCd", "${detail.blogCcd1}", "selectbox", "");
		$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
		$.fnGetCodeSelectAjax("sGb=IQR_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radIqrCd", "${detail.iqrYn == null ? 'N' : detail.iqrYn}", "radio", "iqrYn");
	});
	
</script>		
							

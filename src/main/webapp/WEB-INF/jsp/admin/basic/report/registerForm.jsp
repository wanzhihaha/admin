<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="rptSeqNo" name="rptSeqNo" value="<c:out value="${detail.rptSeqNo }" />">


<section>	
	<div class="title"><h3>Report
	
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
				<th colspan="10" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">Report Information</th>
			</tr>
			<tr>
				<th scope="row">Sort *</th>
				<td>
					<select id="selRptCd" name="rptCcd">
					</select>
				</td>
			</tr>
			<tr>
				<th scope="row">Title *</th>
				<td>
					<input id="titleNm" name="titleNm" type="text" placeholder="Please enter the title." class="inp-field widL" value="<c:out value="${detail.titleNm }" escapeXml="false"/>"  maxlength="100" data-vmsg="title"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Summary<br>Information</th>
				<td>
					<div class="textCont">
						<textarea name="summaryInfo" id="summaryInfo" class="textarea" rows="5" cols="65" placeholder="Please enter the summary information." maxlength="1800" data-vbyte="1000" data-vmsg="summary information"><c:out value="${detail.summaryInfo }" escapeXml="false"/></textarea>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">PC List Image<br/>(384px - 384px)</th>
				<td>
					<div style="margin-bottom: 7px;">
						<input type="file" id="pcListImgUpload" name="pcListImgUpload" class="inp-field widSM"/>
							<c:if test="${contIU eq 'U' }">
							<span style="line-height: 28px;">
								<a href="<blabProperty:value key="system.admin.path"/>/reportImgDown.do?rptSeqNo=<c:out value='${detail.rptSeqNo}'/>&imgKinds=pcList"style="color: blue;"><c:out value="${detail.pcListImgOrgFileNm}" /></a>
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
				<th scope="row">PC Detail Image<br/>(260px – 375px)</th>
				<td>
					<div style="margin-bottom: 7px;">
						<input type="file" id="pcDetlImgUpload" name="pcDetlImgUpload" class="inp-field widSM"/>
							<c:if test="${contIU eq 'U' }">
							<span style="line-height: 28px;">
								<a href="<blabProperty:value key="system.admin.path"/>/reportImgDown.do?rptSeqNo=<c:out value='${detail.rptSeqNo}'/>&imgKinds=pcDetail" style="color: blue;"><c:out value="${detail.pcDetlImgOrgFileNm}" /></a>
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
				<th scope="row">Mobile List Image<br/>(768px - 768px)</th>
				<td>
					<div style="margin-bottom: 7px;">
						<input type="file" id="mobileListImgUpload" name="mobileListImgUpload" class="inp-field widSM"/>
							<c:if test="${contIU eq 'U' }">
							<span style="line-height: 28px;">
								<a href="<blabProperty:value key="system.admin.path"/>/reportImgDown.do?rptSeqNo=<c:out value='${detail.rptSeqNo}'/>&imgKinds=mobileList" style="color: blue;"><c:out value="${detail.mobileListImgOrgFileNm}" /></a>
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
				<th scope="row">Mobile Detail Image<br/>(520px – 750px)</th>
				<td>
					<div style="margin-bottom: 7px;">
						<input type="file" id="mobileDetlImgUpload" name="mobileDetlImgUpload" class="inp-field widSM"/>
							<c:if test="${contIU eq 'U' }">
							<span style="line-height: 28px;">
								<a href="<blabProperty:value key="system.admin.path"/>/reportImgDown.do?rptSeqNo=<c:out value='${detail.rptSeqNo}'/>&imgKinds=mobileDetail" style="color: blue;"><c:out value="${detail.mobileDetlImgOrgFileNm}" /></a>
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
				<th scope="row">Details<br>Information</th>
				<td>
					<div class="textCont">
						<textarea name="detlInfo" id="detlInfo" class="textarea" rows="10" cols="65" placeholder="내용을 입력하세요." ><c:out value ="${detail.detlInfo }" escapeXml="false"/></textarea>			
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
				<th scope="row" rowspan="2">Attach File *</th>
				<td></td>
			</tr>
				<c:forEach var="atthFile" items="${attachFileList }" varStatus="stat">
			<tr>
				<td>
					<input type="file" id="fileUpload_<c:out value="${stat.index}"/>" name="fileUpload" >
					<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/fileDown.do?contentsSeqNo=${atthFile.contentsSeqNo }&contentsCcd=${atthFile.contentsCcd }&ordb=${atthFile.ordb }'style="color: blue;"><c:out value="${atthFile.atchOrgFileNm}" /></a></span>
					<span><input type="checkbox" id="atthFileDel_<c:out value="${stat.index}"/>" onclick="fnDelChk('<c:out value="${stat.index}"/>');" /> <label for="atthFileDel_<c:out value="${stat.index}"/>">Delete</label></span>
					<input type="hidden" id="fileUploadDel_<c:out value="${stat.index}"/>" name="fileUploadDel" value="N" />
					<input type="hidden" id="fileUploadSelect_<c:out value="${stat.index}"/>" name="fileUploadSelect" value="N" />
					<input type="hidden" name="fileUploadVal" id="fileUploadVal_<c:out value="${stat.index}"/>" value="Y" />
				</td>
			</tr>
			</c:forEach>
			
			<c:forEach var="i" begin="${fn:length(attachFileList)}" end="0" >
			<tr>
				<td>
					<input type="file" id="fileUpload_<c:out value="${i}"/>" name="fileUpload" >
					<input type="hidden" id="fileUploadDel_<c:out value="${i}"/>" name="fileUploadDel" value="N" />
					<input type="hidden" id="fileUploadSelect_<c:out value="${i}"/>" name="fileUploadSelect" value="N" />
					<input type="hidden" name="fileUploadVal" id="fileUploadVal_<c:out value="${i}"/>" value="N" />
				</td>
			</tr>
			</c:forEach>
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
			
			// 파일 삭제시 필수값으로 입력이 되어야 한다
			var attchChk = false;
		      $("input[name=fileUpload]").each(function(k,v) {
		         if($("#fileUpload_" + k).val() != "") {   // 파일을 선택시 (수정시 삭제값 Y)
		            $("#fileUploadSelect_" + k).val("Y");
		            attchChk = true;
		         }
		      });
			      
		      $("input[name=fileUploadVal]").each(function(k,v) {
		   		// 삭제버튼 체크 안 했을 때 && 파일 업로드 했을 때 -> true
		         if($(this).val() == "Y" && $("#fileUploadDel_" + k).val() == "N") {
		            attchChk = true;
		         }
		      });
		      if(!attchChk){
				  alert("Please select the attach file.");
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
			
			$.fnGetCodeSelectAjax("sGb=REPORT_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "selRptCd", "${detail.rptCcd}", "selectbox", "");
			$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
		});
		
		// 삭제 여부
		function fnDelChk(i) {
			if($("#atthFileDel_"+i).is(":checked") == true) {
				$("#fileUploadDel_" + i).val("Y");
			} else {
				$("#fileUploadDel_" + i).val("N");
			}
		}
			
</script>		
							

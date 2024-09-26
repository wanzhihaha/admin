<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="tosSeqNo" name="tosSeqNo" value="<c:out value="${detail.tosSeqNo }" />">




<section>	
	<div class="title"><h3>Terms of Service
	
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
			<tr>
				<th scope="row">Sort *</th>
				<td>
					<select id="selTosCcd" name="tosCcd">
					</select>
				</td>
			</tr>
			<tr>
				<th scope="row">Title *</th>
				<td>
					<input id="titleNm" name="titleNm" type="text" placeholder="Please enter the title." class="inp-field widL" value="<c:out value="${detail.titleNm }" escapeXml="false"/>" maxlength="160" data-vbyte="160" data-vmsg="title"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Details<br>Information *</th>
				<td>
					<div class="textCont">
						<textarea name="detlInfo" id="detlInfo" class="textarea" rows="10" cols="65" placeholder="내용을 입력하세요." ><c:out value="${detail.detlInfo}" escapeXml="false"/></textarea>			
						<script type="text/javascript" language="javascript">
							var CrossEditor = new NamoSE('detlInfo');
							CrossEditor.params.Width = "100%";
							CrossEditor.params.UserLang = "auto";
							CrossEditor.params.FullScreen = false;
							CrossEditor.params.WebsourcePath = "/namo_was"
							CrossEditor.params.ServerUrl = 2;	// 서버 url제외
							CrossEditor.params.DeleteCommand = ["backgroundimage","ce_imageeditor","insertfile","flash"];
							CrossEditor.params.UploadFileSizeLimit = "image:5242880,flash:2097152";
							CrossEditor.params.DefaultFont = "默认字体";
							CrossEditor.params.DefaultFontSize = "默认";
							CrossEditor.params.LineHeight = "normal";

							CrossEditor.EditorStart();
							function OnInitCompleted(e){
								e.editorTarget.SetBodyValue(document.getElementById("detlInfo").value);
							}
						</script>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">Memo</th>
				<td>
					<div class="textCont">
						<textarea name="memo" id="memo" class="textarea" rows="5" cols="65" placeholder="Please enter your changes." data-vbyte="1000" data-vmsg="memo" ><c:out value="${detail.memo }" escapeXml="false"/></textarea>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">Version *</th>
				<td style="line-height: 28px;">
					<input id="tosVer" name="tosVer" type="text" placeholder="Please enter the appropriate version." class="inp-field widM2" data-vbyte="30" data-vmsg="tosVer" value="<c:out value="${detail.tosVer }" />" maxlength="30"/>
				</td>
			</tr>	
			<tr style="display:none">
				<th scope="row">Require *<br>Status</th>
				<td>
					<div id="reqStsChk"></div>
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
						<a href="javascript:;" id="btnSave" class="btnStyle01" >保存</a>
					</c:when>
					<c:otherwise>
						<a href="javascript:;" id="btnSave" class="btnStyle01" >保存</a>
					</c:otherwise>
				</c:choose>
				<a href="javascript:;" id="btnList" class="btnStyle01">取消</a>
			</div>
		</div>	
	</div>
	</article>
</section>
</form>
					
					
<script type="text/javascript">
	$(document).ready(function(){

		$("#btnSave").click(function() {
			
			if($.trim($("#titleNm").val()) == ""){
				alert("Please enter the title.");
				$("#titleNm").focus();
				return false;
			}
		
			$("#detlInfo").val(CrossEditor.GetBodyValue());			
			if(CrossEditor.GetTextValue () == ""){
				console.log($.trim(CrossEditor.GetTextValue() == ""));
				alert("Please enter your details with text.");
				CrossEditor.SetFocusEditor();
				return false;
			} 	

			if($.trim($("#tosVer").val()) == ""){
				alert("Please enter the appropriate version.");
				$("#tosVer").focus();
				return false;
			}
			
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
			cancel_backUp("tosSeqNo","writeForm");
/* 			$("#writeForm").prop("action","list.do");
			$("#writeForm").submit(); */
		});
		$.fnGetCodeSelectAjax("sGb=TERMS_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "selTosCcd", "${detail.tosCcd}", "selectbox", "");
		$.fnGetCodeSelectAjax("sGb=REQUIRED_ST&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "reqStsChk", "${detail.reqSts == null ? 'Y' : detail.reqSts}", "radio", "reqSts");
		$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
	});
</script>		
							

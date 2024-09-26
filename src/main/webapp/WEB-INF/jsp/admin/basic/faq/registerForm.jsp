<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="faqSeq" name="faqSeq" value="<c:out value="${detail.faqSeq }" />">


<section>	
	<div class="title"><h3>FAQ 
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
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">FAQ Information</th>
			</tr>
			<tr>
				<th scope="row">Sort*</th>
				<td>
					<select id="selFaqCd" name="faqCcd">
					</select>
				</td>
			</tr>	
			<tr>
				<th scope="row">Title*</th>
				<td>
					<input id="titleNm" name="titleNm" type="text" placeholder="Please enter the title." class="inp-field widL" value="<c:out value="${detail.titleNm }" escapeXml="false"/>" maxlength="50" data-vmsg="title"/>
				</td>
			</tr>
			<tr>
				<th scope="row">FAQ Details Information*</th>
				<td>
					<div class="textCont">
						<textarea name="faqDetlContent" id="faqDetlContent" class="textarea" rows="10" cols="65" placeholder="내용입력 *" ><c:out value="${detail.faqDetlContent }" escapeXml="false"/></textarea>
						<script type="text/javascript" language="javascript">
							var CrossEditor = new NamoSE('faqDetlContent');
							CrossEditor.params.Width = "100%";
							CrossEditor.params.UserLang = "auto";
							CrossEditor.params.FullScreen = false;
							CrossEditor.params.WebsourcePath = "/namo_was"
							CrossEditor.params.ServerUrl = 2;	// 서버 url제외
							CrossEditor.params.DeleteCommand = ["backgroundimage","ce_imageeditor","insertfile","flash"];
							CrossEditor.params.UploadFileSizeLimit = "image:5242880,flash:2097152";

							CrossEditor.EditorStart();
							function OnInitCompleted(e){
								e.editorTarget.SetBodyValue(document.getElementById("faqDetlContent").value);
							}
						</script>
					</div>
				</td>
			</tr>
			<%-- <tr>
				<th scope="row">Registration Date</th>
				<td>
					<jsp:useBean id="toDay" class="java.util.Date" />
					<c:choose>
						<c:when test="${detail.insDtm == null}">
							<input class="inp-field wid100" type="text" id="insDtm" name="insDtm" value="<fmt:formatDate value="${toDay}" pattern="yyyy-MM-dd" />" />
						</c:when>
						<c:otherwise>
							<input class="inp-field wid100" type="text" id="insDtm" name="insDtm" value="<c:out value="${detail.insDtm}" />" />
						</c:otherwise>
					</c:choose>
					
				</td>
			</tr> --%>
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
		
		$("#insDtm").datepicker({
			dateFormat:"yy-mm-dd"
			,showOn : 'button'
			,buttonImage : '/static/images/cal.png'
			,buttonImageOnly : true
			,buttonText : "달력"

		});
		
				
		$("#btnSave").click(function() {
			
			if($.trim($("#titleNm").val()) == ""){
				alert("Please enter the title.");
				$("#titleNm").focus();
				return false;
			}
			
			$("#faqDetlContent").val(CrossEditor.GetBodyValue());
			if(CrossEditor.GetTextValue () == ""){
				alert("Please enter Detail Information with text.");
				$("#faqDetlContent").focus();
				return false;
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
			
			//$("#writeForm").prop("action","detail.do");
			//$("#writeForm").submit();
		});
		
		$.fnGetCodeSelectAjax("sGb=FAQ_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "selFaqCd", "${detail.faqCcd}", "selectbox", "");
		$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
		
		
	});
	
</script>		


<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="popSeqNo" name="popSeqNo" value="<c:out value="${detail.popSeqNo }" />">




<section>	
	<div class="title"><h3>Popup Management
	
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
				<th scope="row">Title *</th>
				<td>
					<input id="titleNm" name="titleNm" type="text" placeholder="Please enter the title." class="inp-field widL" value="<c:out value="${detail.titleNm }" escapeXml="false"/>" maxlength="160" data-vbyte="160" data-vmsg="title"/>
				</td>
			</tr>
			
			<tr>
				<th scope="row">Period</th>
				<td>
					<c:choose>
						<c:when test="${empty detail.popSeqNo}">
							<input class="inp-field wid100" type="text" id="peridStatDate" name="peridStatDate" value="" readonly="readonly"/>
							<span>~</span>
							<input class="inp-field wid100" type="text" id="peridEndDate" name="peridEndDate" value="" readonly="readonly"/>
						</c:when>
						<c:otherwise>
							<input class="inp-field wid100" type="text" id="peridStatDate" name="peridStatDate" value="<c:out value='${detail.peridStatDate}'/>" />
							<span>~</span>
							<input class="inp-field wid100" type="text" id="peridEndDate" name="peridEndDate" value="<c:out value='${detail.peridEndDate}'/>" />
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th scope="row">Link URL</th>
				<td>
					<input id="linkUrl" name="linkUrl" type="text" placeholder="Please enter the link." class="inp-field widL" value="<c:out value="${detail.linkUrl }" escapeXml="false"/>" maxlength="240" data-vbyte="240" data-vmsg="link"/>
				</td>
			</tr>
			<tr>
				<th scope="row">PC Popup Sort</th>
				<td>
					<div id="PCPopupChk"></div>
				</td>
			</tr>	
			<tr>
				<th scope="row">PC Popup Size *<br>(600px - 500px)</th>
				<td>
					<div class="textCont">
							<span>Width</span><input class="inp-field wid100" id="pcPopSizeWdt" name="pcPopSizeWdt" type="number" placeholder="width" class="inp-field widL" value="<c:out value="${detail.pcPopSizeWdt }" />" maxlength="5"/>
							<span>Height</span><input class="inp-field wid100" id="pcPopSizeHgt" name="pcPopSizeHgt" type="number" placeholder="height" class="inp-field widL" value="<c:out value="${detail.pcPopSizeHgt }" />" maxlength="5"/>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">PC Popup Location</th>
				<td>
					<div class="textCont">
							<span style="padding-right: 41px;">Top</span><input class="inp-field wid100" id="pcPopLocTop" name="pcPopLocTop" type="number" placeholder="width" class="inp-field widL" value="<c:out value="${detail.pcPopLocTop }" />" maxlength="5"/>
							<span style="padding-right: 48px;">Left</span><input class="inp-field wid100" id="pcPopLocLeft" name="pcPopLocLeft" type="number" placeholder="height" class="inp-field widL" value="<c:out value="${detail.pcPopLocLeft }" />" maxlength="5"/>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">PC Popup Image</th>
				<td>
					<div style="margin-bottom: 7px;">
						<input type="file" id="pcPopImgUpload" name="pcPopImgUpload" class="inp-field widSM"/>
							<c:if test="${contIU eq 'U' }">
							<span style="line-height: 28px;">
								<a href="<blabProperty:value key="system.admin.path"/>/popupImgDown.do?popSeqNo=<c:out value='${detail.popSeqNo}'/>&imgKinds=pcPop" style="color: blue;"><c:out value="${detail.pcPopImgOrgFileNm}" /></a>
							</span>
							<c:if test="${!empty detail.pcPopImgOrgFileNm}">
							<span><input type="checkbox" name="pcPopFileDel" id="pcPopFileDel" value="Y" /><label for="pcPopFileDel">Delete</label></span>
							</c:if>
							</c:if>
					</div>
					<input type="text" id="pcPopImgAlt" name="pcPopImgAlt" placeholder="Please enter the Alt value for the image." value="<c:out value="${detail.pcPopImgAlt }" escapeXml="false"/>" class="inp-field widSM" maxlength="200" data-vbyte="200" data-vmsg="alt value"/>
					<span style="line-height: 28px;"> ※Plase enter an 'alt' value for Image.</span>
				</td>
			</tr>
			<tr>
				<th scope="row">Mobile Popup Size <br>(720px - 720px)</th>
				<td>
					<div class="textCont">
							<span>Width</span><input class="inp-field wid100" id="mobilePopSizeWdt" name="mobilePopSizeWdt" type="number" placeholder="width" class="inp-field widL" value="<c:out value="${detail.mobilePopSizeWdt }" />" maxlength="5"/>
							<span>Height</span><input class="inp-field wid100" id="mobilePopSizeHgt" name="mobilePopSizeHgt" type="number" placeholder="height" class="inp-field widL" value="<c:out value="${detail.mobilePopSizeHgt }" />" maxlength="5"/>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">Mobile Popup Image</th>
				<td>
					<div style="margin-bottom: 7px;">
						<input type="file" id="mobilePopImgUpload" name="mobilePopImgUpload" class="inp-field widSM"/>
							<c:if test="${contIU eq 'U' }">
							<span style="line-height: 28px;">
								<a href="<blabProperty:value key="system.admin.path"/>/popupImgDown.do?popSeqNo=<c:out value='${detail.popSeqNo}'/>&imgKinds=mobilePop" style="color: blue;"><c:out value="${detail.mobilePopImgOrgFileNm}" /></a>
							</span>
							<c:if test="${!empty detail.mobilePopImgOrgFileNm }">
							<span><input type="checkbox" name="mobilePopFileDel" id="mobilePopFileDel" value="Y" /><label for="mobilePopFileDel">Delete</label></span>
							</c:if>
							</c:if>
					</div>
					<input type="text" id="mobilePopImgAlt" name="mobilePopImgAlt" placeholder="Please enter the Alt value for the image." value="<c:out value="${detail.mobilePopImgAlt }" escapeXml="false"/>" class="inp-field widSM" maxlength="200" data-vbyte="200" data-vmsg="alt value"/>
					<span style="line-height: 28px;"> ※Plase enter an 'alt' value for Image.</span>
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
		
		$("#peridStatDate, #peridEndDate").datepicker({
			dateFormat:"yy-mm-dd"
			,showOn : 'button'
			,buttonImage : '/static/images/cal.png'
			,buttonImageOnly : true
			,buttonText : "달력"

		});

		$("#peridStatDate").on("change keyup paste", function(){
			var stDt = $("#peridStatDate").val();
			var stDtArr = stDt.split("-");
			var stDtObj = new Date(stDtArr[0], Number(stDtArr[1])-1, stDtArr[2]);
			var enDt = $("#peridEndDate").val();
			var enDtArr = enDt.split("-");
			var enDtObj = new Date(enDtArr[0], Number(enDtArr[1])-1, enDtArr[2]);
			var betweenDay = (enDtObj.getTime() - stDtObj.getTime())/1000/60/60/24;
					
			if(betweenDay < 0) {
				alert("The start date is less than the end date. Please re-enter.");
				$("#peridStatDate").focus();
				$("#peridStatDate").val("");
				return false;
			}
		});
		
		$("#peridEndDate").on("change keyup paste", function(){	
			var stDt = $("#peridStatDate").val();
			var stDtArr = stDt.split("-");
			var stDtObj = new Date(stDtArr[0], Number(stDtArr[1])-1, stDtArr[2]);
			var enDt = $("#peridEndDate").val();
			var enDtArr = enDt.split("-");
			var enDtObj = new Date(enDtArr[0], Number(enDtArr[1])-1, enDtArr[2]);
			var betweenDay = (enDtObj.getTime() - stDtObj.getTime())/1000/60/60/24;
				
			if(betweenDay < 0) {
				alert("The end date is greater than the start date. Please re-enter.");
				$("#peridEndDate").focus();
				$("#peridEndDate").val("");
				return false;
			} 
		});	
		
		
		$("#btnSave").click(function() {
			
			if($.trim($("#titleNm").val()) == ""){			
				alert("Please enter the title.");
				$("#titleNm").focus();
				return false;
			}
			
			if($.trim($("#pcPopSizeWdt").val()) == ""){
				alert("Please enter image width.");
				$("#pcPopSizeWdt").focus();
				return false;
			} 

			if($.trim($("#pcPopSizeHgt").val()) == ""){
				alert("Please enter image height.");
				$("#pcPopSizeHgt").focus();
				return false;
			}
			if($.trim($("#pcPopSizeHgt").val())<=200){
				$("#pcPopSizeHgt").val("200");
			}

			if($.trim($("#pcPopSizeWdt").val())<=300){
				$("#pcPopSizeWdt").val("300");
			}

			if($.trim($("#mobilePopSizeHgt").val())<=200){
				$("#mobilePopSizeHgt").val("200");
			}

			if($.trim($("#mobilePopSizeWdt").val())<=300){
				$("#mobilePopSizeWdt").val("300");
			}

          if($.trim($("#pcPopLocTop").val())<0){
            $("#pcPopLocTop").val("0");
          }

          if($.trim($("#pcPopLocLeft").val())<0){
            $("#pcPopLocLeft").val("0");
          }

			if ($("#pcPopImgUpload").val() != ""){
				if(!/\.(gif|jpg|peg|png|jpeg)$/.test($("#pcPopImgUpload").val().toLowerCase())){
					alert("Please select the image file (gif, jpg, bmp, png, jpeg)");
					$("#pcPopImgUpload").focus();
					return false;
				}
			}

			if ($("#mobilePopImgUpload").val() != ""){
				if(!/\.(gif|jpg|peg|png|jpeg)$/.test($("#mobilePopImgUpload").val().toLowerCase())){
					alert("Please select the image file (gif, jpg, bmp, png, jpeg)");
					$("#mobilePopImgUpload").focus();
					return false;
				}
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
			history.back();
/* 			$("#writeForm").prop("action","list.do");
			$("#writeForm").submit(); */
		});
		$.fnGetCodeSelectAjax("sGb=POPUP_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "PCPopupChk", "${detail.pcPopCcd == null ? 'LAYER_P' : detail.pcPopCcd}", "radio", "pcPopCcd");
		$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
	});
</script>		
							

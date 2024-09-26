<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="sqprdCtgry" name="sqprdCtgry" value="<c:out value="${vo.sqprdCtgry }"/>">
<input type="hidden" id="sqprdCtgryNm" name="sqprdCtgryNm" value="<c:out value="${vo.sqprdCtgryNm }"/>">
<input type="hidden" id="sqprdSeqNo" name="sqprdSeqNo" value="<c:out value="${detail.sqprdSeqNo }" />">


<section>	
	<c:choose>
		<c:when test="${!empty detail.sqprdSeqNo }">
			<div class="title"><h3><c:out value="${vo.sqprdCtgryNm}"/> Modify</h3></div>
		</c:when>
		<c:otherwise>
			<div class="title"><h3><c:out value="${vo.sqprdCtgryNm}"/> Registration</h3></div>
		</c:otherwise>
	</c:choose>
	<article>
	<div class="Cont_place">
	<table class="bd-form" summary="등록, 수정하는 영역 입니다.">
			<caption>열람,내용</caption>
			<colgroup>
				<col width="130px" />
				<col width="" />
			</colgroup>
			
			<%@ include file="/WEB-INF/jsp/common/seo/seoRegisterForm.jsp" %>
			
			<tr>
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">Product Information</th>
			</tr>
			<tr>
				<th scope="row">Product Name*</th>
				<td>
					<input id="sqprdNm" name="sqprdNm" type="text" placeholder="Please enter the product name." class="inp-field widL" value="<c:out value="${detail.sqprdNm }" escapeXml="false"/>" maxlength="20" data-vmsg="product name"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Product English<br/>Name</th>
				<td>
					<input id="sqprdEngNm" name="sqprdEngNm" type="text" placeholder="Please enter the product name in English." class="inp-field widL" value="<c:out value="${detail.sqprdEngNm }" escapeXml="false"/>" maxlength="20" data-vmsg="product name in English"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Product Manager</th>
				<td>
					<input id="sqprdAttnId" name="sqprdAttnId" type="text" placeholder="Please enter the product manager name." class="inp-field widL" value="<c:out value="${detail.sqprdAttnId }" escapeXml="false"/>" maxlength="40" data-vbyte="40" data-vmsg="product manager name"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Product NAVY</th>
				<td>
					<input id="sqprdNavy" name="sqprdNavy" type="text" placeholder="Please enter the name of your NAVY" class="inp-field widL" value="<c:out value="${detail.sqprdEngNm }" escapeXml="false"/>" maxlength="210" data-vbyte="210" data-vmsg="name of your NAVY"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Product Summary<br/>Information</th>
				<td>
					<div class="textCont">
						<textarea name="sqprdSummaryInfo" id="sqprdSummaryInfo" class="textarea" rows="10" cols="65" placeholder="Please enter the summary information." maxlength="210" data-vmsg="summary information"><c:out value="${detail.sqprdSummaryInfo }" escapeXml="false"/></textarea>
						
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">Service Validity</th>
				<td>
					<c:choose>
						<c:when test="${empty detail.sqprdSeqNo}">
							<input class="inp-field wid100" type="text" id="svcValidStatDate" name="svcValidStatDate" value="" readonly="readonly"/>
							<span>~</span>
							<input class="inp-field wid100" type="text" id="svcValidEndDate" name="svcValidEndDate" value="" readonly="readonly"/>
						</c:when>
						<c:otherwise>
							<input class="inp-field wid100" type="text" id="svcValidStatDate" name="svcValidStatDate" value="<c:out value='${detail.svcValidStatDate}'/>" readonly="readonly"/>
							<span>~</span>
							<input class="inp-field wid100" type="text" id="svcValidEndDate" name="svcValidEndDate" value="<c:out value='${detail.svcValidEndDate}'/>" readonly="readonly"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr style="display:none">
				<th scope="row">PC List Image</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="pcListOrginFile" name="pcListOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.pcListImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/goodsDownload.do?sqprdSeqNo=<c:out value="${detail.sqprdSeqNo }"/>&imgKinds=pcList&sqprdCtgry=<c:out value='${vo.sqprdCtgry }'/>' style="color: blue;"><c:out value="${detail.pcListImgOrgFileNm}" escapeXml="false"/></a></span>
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
			<tr style="display:none">
				<th scope="row">PC Detail Image</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="pcDetailOrginFile" name="pcDetailOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.pcDetlImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/goodsDownload.do?sqprdSeqNo=<c:out value="${detail.sqprdSeqNo }"/>&imgKinds=pcDetail&sqprdCtgry=<c:out value='${vo.sqprdCtgry }'/>' style="color: blue;"><c:out value="${detail.pcDetlImgOrgFileNm}" escapeXml="false"/></a></span>
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
			<tr style="display:none">
				<th scope="row">Mobile List Image</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="mobileListOrginFile" name="mobileListOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.mobileListImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/goodsDownload.do?sqprdSeqNo=<c:out value="${detail.sqprdSeqNo }"/>&imgKinds=mobileList&sqprdCtgry=<c:out value='${vo.sqprdCtgry }'/>' style="color: blue;"><c:out value="${detail.mobileListImgOrgFileNm}" escapeXml="false"/></a></span>
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
			<tr style="display:none">
				<th scope="row">Mobile Detail Image</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="mobileDetailOrginFile" name="mobileDetailOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.mobileDetlImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/goodsDownload.do?sqprdSeqNo=<c:out value="${detail.sqprdSeqNo }"/>&imgKinds=mobileDetail&sqprdCtgry=<c:out value='${vo.sqprdCtgry }'/>' style="color: blue;"><c:out value="${detail.mobileDetlImgOrgFileNm}" escapeXml="false"/></a></span>
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
				<th scope="row">Product Notice<br/>Contents</th>
				<td>
					<div class="textCont">
						<textarea name="sqprdNotiContents" id="sqprdNotiContents" class="textarea" rows="10" cols="65" placeholder="내용입력 *" ><c:out value="${detail.sqprdNotiContents }"  escapeXml="false"/></textarea>			
						<script type="text/javascript" language="javascript">
							var CrossEditor1 = new NamoSE('sqprdNotiContents');
							CrossEditor1.params.Width = "100%";
							CrossEditor1.params.UserLang = "auto";
							CrossEditor1.params.FullScreen = false;
							CrossEditor1.params.WebsourcePath = "/namo_was"
							CrossEditor1.params.ServerUrl = 2;	// 서버 url제외
							CrossEditor1.params.DeleteCommand = ["backgroundimage","ce_imageeditor","insertfile","flash"];
							CrossEditor1.params.UploadFileSizeLimit = "image:5242880,flash:2097152";

							CrossEditor1.EditorStart();
							
							function OnInitCompleted(e){
								e.editorTarget.SetBodyValue(document.getElementById("sqprdNotiContents").value);
							}
						</script>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">Product Details<br/>Information*</th>
				<td>
					<div class="textCont">
						<textarea name="sqprdDetlInfo" id="sqprdDetlInfo" class="textarea" rows="10" cols="65" placeholder="내용입력 *" ><c:out value="${detail.sqprdDetlInfo }" escapeXml="false"/></textarea>			
						<script type="text/javascript" language="javascript">
							var CrossEditor = new NamoSE('sqprdDetlInfo');
							CrossEditor.params.Width = "100%";
							CrossEditor.params.UserLang = "auto";
							CrossEditor.params.FullScreen = false;
							CrossEditor.params.WebsourcePath = "/namo_was"
							CrossEditor.params.ServerUrl = 2;	// 서버 url제외
							CrossEditor.params.DeleteCommand = ["backgroundimage","ce_imageeditor","insertfile","flash"];
							CrossEditor.params.UploadFileSizeLimit = "image:5242880,flash:2097152";

							CrossEditor.EditorStart();
							
							function OnInitCompleted(e){
								e.editorTarget.SetBodyValue(document.getElementById("sqprdDetlInfo").value);
							}
						</script>
					</div>
				</td>
			</tr>
			<%-- <tr>
				<th scope="row" rowspan="4">Attach File</th>
				<td></td>
			</tr>
			<c:forEach var="atthFile" items="${attachFileList }" varStatus="stat">	<!-- 파일이 이미 있는 경우 반복문 -->
			<tr>
				<td>
					<input type="file" id="fileUpload_<c:out value="${stat.index}"/>" name="fileUpload" >
					<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/fileDown.do?contentsSeqNo=<c:out value="${atthFile.contentsSeqNo }"/>&contentsCcd=<c:out value="${atthFile.contentsCcd }"/>&ordb=<c:out value="${atthFile.ordb }"/>' style="color: blue;"><c:out value="${atthFile.atchOrgFileNm}" escapeXml="false"/></a></span>
					<span><input type="checkbox" id="atthFileDel_<c:out value="${stat.index}"/>" onclick="fnDelChk('<c:out value="${stat.index}"/>');" /> <label for="atthFileDel_<c:out value="${stat.index}"/>">Delete</label></span>
					<input type="hidden" id="fileUploadDel_<c:out value="${stat.index}"/>" name="fileUploadDel" value="N" />	<!-- 삭제 체크를 위한 hidden 값 -->
					<input type="hidden" id="fileUploadSelect_<c:out value="${stat.index}"/>" name="fileUploadSelect" value="N" />	<!-- 파일선택 hidden 값 -->
					<input type="hidden" name="fileUploadVal" id="fileUploadVal_<c:out value="${stat.index}"/>" value="Y" /> 	<!-- 업로드체크를 위한 hidden 값 -->
				</td>
			</tr>
			</c:forEach>
			<c:forEach var="i" begin="${fn:length(attachFileList)}" end="2" > <!-- 파일이 없는 경우 -->
			<tr>
				<td>
					<input type="file" id="fileUpload_<c:out value="${i}"/>" name="fileUpload" >
					<input type="hidden" id="fileUploadDel_<c:out value="${i}"/>" name="fileUploadDel" value="N" />
					<input type="hidden" id="fileUploadSelect_<c:out value="${i}"/>" name="fileUploadSelect" value="N" />
					<input type="hidden" name="fileUploadVal" id="fileUploadVal_<c:out value="${i}"/>" value="N" />
				</td>
			</tr>
			</c:forEach> --%>
			<tr>
				<th scope="row">POL</th>
				<td>
					<input id="depPortCd" name="depPortCd" type="text" placeholder="Please enter the POL." class="inp-field widL" value="<c:out value="${detail.depPortCd }" escapeXml="false"/>" maxlength="210" data-vbyte="210" data-vmsg="POL"/>
				</td>
			</tr>
			<tr>
				<th scope="row">POD</th>
				<td>
					<input id="arrpPotCd" name="arrpPotCd" type="text" placeholder="Please enter the POD." class="inp-field widL" value="<c:out value="${detail.arrpPotCd }" escapeXml="false"/>" maxlength="210" data-vbyte="210" data-vmsg="POD"/>
				</td>
			</tr>
			<tr style="display:none">
				<th scope="row">Ship-to</th>
				<td>
					<input id="shipTo" name="shipTo" type="text" placeholder="Please enter the ship-to address." class="inp-field widL" value="<c:out value="${detail.shipTo }" escapeXml="false"/>" maxlength="15" data-vmsg="ship-to address"/>
				</td>
			</tr>
			<tr style="display:none">
				<th scope="row">Product Option1</th>
				<td>
					<input id="sqprdOpt1" name="sqprdOpt1" type="text" placeholder="Please enter Option1." class="inp-field widL" value="<c:out value="${detail.sqprdOpt1 }" escapeXml="false"/>" maxlength="210" data-vbyte="210" data-vmsg="Option1"/>
				</td>
			</tr>
			<tr style="display:none">
				<th scope="row">Product Option2</th>
				<td>
					<input id="sqprdOpt2" name="sqprdOpt2" type="text" placeholder="Please enter Option2." class="inp-field widL" value="<c:out value="${detail.sqprdOpt2 }" escapeXml="false"/>" maxlength="210" data-vbyte="210" data-vmsg="Option2"/>
				</td>
			</tr>
			<tr style="display:none">
				<th scope="row">Product Option3</th>
				<td>
					<input id="sqprdOpt3" name="sqprdOpt3" type="text" placeholder="Please enter Option3." class="inp-field widL" value="<c:out value="${detail.sqprdOpt3 }" escapeXml="false"/>" maxlength="210" data-vbyte="210" data-vmsg="Option3"/>
				</td>
			</tr>
			<tr style="display:none">
				<th scope="row">Product Option4</th>
				<td>
					<input id="sqprdOpt4" name="sqprdOpt4" type="text" placeholder="Please enter Option4." class="inp-field widL" value="<c:out value="${detail.sqprdOpt4 }" escapeXml="false"/>" maxlength="210" data-vbyte="210" data-vmsg="Option4"/>
				</td>
			</tr>
			<tr style="display:none">
				<th scope="row">Product Option5</th>
				<td>
					<input id="sqprdOpt5" name="sqprdOpt5" type="text" placeholder="Please enter Option5." class="inp-field widL" value="<c:out value="${detail.sqprdOpt5 }" escapeXml="false"/>" maxlength="210" data-vbyte="210" data-vmsg="Option5"/>
				</td>
			</tr>
			
			<tr>
				<th scope="row">Related Products<br/>(3limitations)</th>
				<td>
					<div id="searchButton" class="btn-module mgtTB">
						<input id="reltdSqprd1" name="reltdSqprd1" type="text"  class="inp-field" value="<c:out value="${goodsNm1}" escapeXml="false"/>" maxlength="110" readonly="readonly" style="background-color: #E6E6E6; width: 83%;"/>
						<input id="relsqprdSeqNo1" name="relsqprdSeqNo1" type="hidden" value="<c:out value="${detail.reltdSqprd1 }"/>"/>
						<a href="javascript:;" id="goodsDel_1" onclick="fnValueDel(1)" class="btnStyleGoods05">X</a>
						<a href="javascript:;" id="goodsSearch1" onclick="fncSearchGoods(1);" class="btnStyle01" style="margin-left:10px; width: 10%">Product Search</a>
					</div>
					<div id="searchButton" class="btn-module mgtTB" style="margin-top: 10px">
						<input id="reltdSqprd2" name="reltdSqprd2" type="text"  class="inp-field" value="<c:out value="${goodsNm2}" escapeXml="false"/>" maxlength="110" readonly="readonly" style="background-color: #E6E6E6; width: 83%;"/>
						<input id="relsqprdSeqNo2" name="relsqprdSeqNo2" type="hidden" value="<c:out value="${detail.reltdSqprd2 }"/>"/>
						<a href="javascript:;" id="goodsDel_2" onclick="fnValueDel(2)" class="btnStyleGoods05">X</a>
						<a href="javascript:;" id="goodsSearch2" onclick="fncSearchGoods(2);" class="btnStyle01" style="margin-left:10px; width: 10%">Product Search</a>
					</div>
					<div id="searchButton" class="btn-module mgtTB" style="margin-top: 10px">
						<input id="reltdSqprd3" name="reltdSqprd3" type="text"  class="inp-field" value="<c:out value="${goodsNm3}" escapeXml="false"/>" maxlength="110" readonly="readonly" style="background-color: #E6E6E6; width: 83%;"/>
						<input id="relsqprdSeqNo3" name="relsqprdSeqNo3" type="hidden" value="<c:out value="${detail.reltdSqprd3 }"/>"/>
						<a href="javascript:;" id="goodsDel_3" onclick="fnValueDel(3)" class="btnStyleGoods05">X</a>
						<a href="javascript:;" id="goodsSearch3" onclick="fncSearchGoods(3);" class="btnStyle01" style="margin-left:10px; width: 10%">Product Search</a>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"></th>
				<td>
					1. Half Image : (420px - 248px)<br />
					2. Full Image : (420px - 550px)
				</td>
			</tr>
			<tr id="viewTr">
				<th id="thRowspan" rowspan="2" scope="row">View Type*</th>
				<td>
					<div id="radGoodsViewTypeImg" style="float: left;"></div><select id="selGoodsVmBgOp" name="viewTypeImgBkgrColorOpt"></select>
					
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
	
	$("#svcValidStatDate, #svcValidEndDate").datepicker({
		dateFormat:"yy-mm-dd"
		,showOn : 'button'
		,buttonImage : '/static/images/cal.png'
		,buttonImageOnly : true
		,buttonText : "달력"

	});
	$.fnGetCodeSelectAjax("sGb=GOODS_VW_TP&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radGoodsViewTypeImg", "${detail.viewTypeImg == null ? 'GOODS_VW_TP_01' : detail.viewTypeImg}", "radio", "viewTypeImg");
	$.fnGetCodeSelectAjax("sGb=GOODS_VW_BG_OP&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "selGoodsVmBgOp", "${detail.viewTypeImgBkgrColorOpt}", "selectbox", "");
	$.fnGetCodeSelectAjax("sGb=GOODS_VW_BG&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radGoodsVwBgCd", "${detail.viewTypeImgBkgrColorCcd == null ? 'GOODS_VW_BG_01' : detail.viewTypeImgBkgrColorCcd}", "radio", "viewTypeImgBkgrColorCcd");
	$.fnGetCodeSelectAjax("sGb=GOODS_VW_BG_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "selVwTyImgCd", "${detail.viewTypeImgCcd}", "selectbox", "");
	$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
	
	//View Type 옵션값에 따라서 요소 추가
	var appendText;
	if($("input[name=viewTypeImg]:checked").val() == "GOODS_VW_TP_01" || $("input[name=viewTypeImg]:checked").val() == "GOODS_VW_TP_02") {
		$("#thRowspan").attr('rowspan','2');
		$('#selGoodsVmBgOp').hide();
 		appendText = "<tr id='addViewImg'>";
		appendText += "<td>";
		appendText += "<div class='textCont'>";
		appendText += "<div style='margin-bottom: 7px;'>";
		appendText += '<input id="viewTypeOrginFile" name="viewTypeOrginFile" type="file" class="inp-field widSM"/>';
		appendText += '<c:if test="${!empty detail.viewTypeImgOrgFileNm}">';
		appendText += '<span style="line-height: 28px;"><a href="<blabProperty:value key="system.admin.path"/>/goodsDownload.do?sqprdSeqNo=<c:out value="${detail.sqprdSeqNo }"/>&imgKinds=viewType&sqprdCtgry=<c:out value="${vo.sqprdCtgry }"/>" style="color: blue;"><c:out value="${detail.viewTypeImgOrgFileNm}" escapeXml="false"/></a></span>';
		appendText += '<span><input type="checkbox" name="viewTypeFileDel" id="viewTypeFileDel" value="Y" /> <label for="pcListFileDel">Delete</label></span>';
		appendText += '</c:if>';
		appendText += '</div>';
		appendText += '<div>';
		appendText += '<input type="text" id="viewTypeImgAlt" name="viewTypeImgAlt" value="<c:out value="${detail.viewTypeImgAlt }" escapeXml="false"/>" placeholder="Please enter the Alt value for the image." class="inp-field widSM" maxlength="210" data-vbyte="210" data-vmsg="alt value" />';
		appendText += '<span style="line-height: 28px;"> ※Plase enter an &#39;alt&#39; value for Image.</span>';
		appendText += '</div>';
		appendText += '</div>';
		appendText += '</td>';
		appendText += '</tr>';
		$("#viewTr").after(appendText);	
	} else {
		$("#thRowspan").attr('rowspan','3');
		$('#selGoodsVmBgOp').show();
		appendText =  '<tr id="addViewBackGr">';
		appendText += '<td>';
		appendText += '<div id="radGoodsVwBgCd" style="float: left;"></div>';
		appendText += '<span style="line-height: 26px; padding-left: 5px;"># <input type="text" id="viewTypeImgBkgrColorVal" name="viewTypeImgBkgrColorVal" value="<c:out value="${detail.viewTypeImgBkgrColorVal }" escapeXml="false"/>" placeholder="Please enter a background color." class="inp-field wid200" maxlength="6" data-vbyte="6" data-vmsg="background color"/></span>';
		appendText += '</td>';
		appendText += '</tr>';
		$("#viewTr").after(appendText);
		$.fnGetCodeSelectAjax("sGb=GOODS_VW_BG&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radGoodsVwBgCd", "${detail.viewTypeImgBkgrColorCcd == null ? 'GOODS_VW_BG_01' : detail.viewTypeImgBkgrColorCcd}", "radio", "viewTypeImgBkgrColorCcd");
		// 옵션값 대한 요소 추가
		var bkgrColorOptVal = $("#selGoodsVmBgOp").val();		
		var appendText2;
		if(bkgrColorOptVal == "GOODS_VW_BG_OP_01") {
			appendText2 = '<tr id="Addoption"><td>';
			appendText2 += '<sapn style="padding: 0px 22px;vertical-align: middle;">From</sapn><input type="text" id="viewTypeImgFrom" name="viewTypeImgFrom" placeholder="Form을 입력해주세요" value="<c:out value="${detail.viewTypeImgFrom }" escapeXml="false"/>"  class="inp-field widSM" maxlength="15" data-vmsg="From"/>';
			appendText2 += '<div style="padding-left: 76px; margin: 10px 0px;"><select id="selVwTyImgCd" name="viewTypeImgCcd"></select></div>';
			appendText2 += '<span>To</span><input type="text" id="viewTypeImgTo" name="viewTypeImgTo" placeholder="To를 입력해주세요" value="<c:out value="${detail.viewTypeImgTo }" escapeXml="false"/>"  class="inp-field widSM" maxlength="15" data-vmsg="To"/>';
			appendText2 += '</td></tr>';
			
		} else {
			appendText2 = '<tr id="Addoption"><td>';
			appendText2 += '<input id="viewTypeImgHashTag" name="viewTypeImgHashTag" type="text" class="inp-field widL" placeholder="Please enter a hashtag." value="<c:out value="${detail.viewTypeImgHashTag }" escapeXml='false' />" maxlength="24" data-vbyte="24" data-vmsg="hashtag"/><br>'
			appendText2 += '※ When entering multiple hashtags, you can distinguish them by ‘,'
			appendText2 += '</td></tr>';
		}
		$("#addViewBackGr").after(appendText2);
		$.fnGetCodeSelectAjax("sGb=GOODS_VW_BG_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "selVwTyImgCd", "<c:out value='${detail.viewTypeImgCcd}' escapeXml='false' />", "selectbox", "");
		if($("input[name=viewTypeImgBkgrColorCcd]:checked").val() == "GOODS_VW_BG_04") {
			$("#viewTypeImgBkgrColorVal").removeAttr("disabled");
			$("#viewTypeImgBkgrColorVal").css("backgroundColor", "white");
			$("#viewTypeImgBkgrColorVal").attr("placeholder", "배경색을 입력해주세요.");
		} else {
			$("#viewTypeImgBkgrColorVal").attr("disabled", true);
			$("#viewTypeImgBkgrColorVal").css("backgroundColor", "#E6E6E6");
			$("#viewTypeImgBkgrColorVal").removeAttr("placeholder");
		}
	}
	
	$("input[name=viewTypeImg]").change(function(){
		if($("input[name=viewTypeImg]:checked").val() == "GOODS_VW_TP_01" || $("input[name=viewTypeImg]:checked").val() == "GOODS_VW_TP_02") {
			//ID값이 있는지 체크
			if ($("#addViewImg").length > 0) { return false; }
			$("#addViewBackGr").remove();
			$("#Addoption").remove();
			$("#thRowspan").attr('rowspan','2');
			$('#selGoodsVmBgOp').hide();
	 		appendText = "<tr id='addViewImg'>";
			appendText += "<td>";
			appendText += "<div class='textCont'>";
			appendText += "<div style='margin-bottom: 7px;'>";
			appendText += '<input id="viewTypeOrginFile" name="viewTypeOrginFile" type="file" class="inp-field widSM"/>';
			appendText += '<c:if test="${!empty detail.viewTypeImgOrgFileNm}">';
			appendText += '<span style="line-height: 28px;"><a href="<blabProperty:value key="system.admin.path"/>/goodsDownload.do?sqprdSeqNo=<c:out value="${detail.sqprdSeqNo }"/>&imgKinds=viewType&sqprdCtgry=<c:out value="${vo.sqprdCtgry }"/>" style="color: blue;"><c:out value="${detail.viewTypeImgOrgFileNm}" /></a></span>';
			appendText += '<span><input type="checkbox" name="viewTypeFileDel" id="viewTypeFileDel" value="Y" /> <label for="pcListFileDel">Delete</label></span>';
			appendText += '</c:if>';
			appendText += '</div>';
			appendText += '<div>';
			appendText += '<input type="text" id="viewTypeImgAlt" name="viewTypeImgAlt" value="<c:out value="${detail.viewTypeImgAlt }" />" placeholder="Please enter the Alt value for the image." class="inp-field widSM" maxlength="210" data-vbyte="210" data-vmsg="alt value" />';
			appendText += '<span style="line-height: 28px;"> ※Plase enter an &#39;alt&#39; value for Image.</span>';
			appendText += '</div>';
			appendText += '</div>';
			appendText += '</td>';
			appendText += '</tr>';
			$("#viewTr").after(appendText);	
		} else {
			$("#addViewImg").remove();
			$("#thRowspan").attr('rowspan','3');
			$('#selGoodsVmBgOp').show();
			appendText =  '<tr id="addViewBackGr">';
			appendText += '<td>';
			appendText += '<div id="radGoodsVwBgCd" style="float: left;"></div>';
			appendText += '<span style="line-height: 26px; padding-left: 5px;"># <input type="text" id="viewTypeImgBkgrColorVal" name="viewTypeImgBkgrColorVal" value="<c:out value="${detail.viewTypeImgBkgrColorVal }"/>" placeholder="Please enter a background color." class="inp-field wid200" maxlength="6" data-vbyte="6" data-vmsg="background color"/></span>';
			appendText += '</td>';
			appendText += '</tr>';
			$("#viewTr").after(appendText);	
			$.fnGetCodeSelectAjax("sGb=GOODS_VW_BG&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radGoodsVwBgCd", "${detail.viewTypeImgBkgrColorCcd == null ? 'GOODS_VW_BG_01' : detail.viewTypeImgBkgrColorCcd}", "radio", "viewTypeImgBkgrColorCcd");
			// 옵션값 대한 요소 추가
			var bkgrColorOptVal = $("#selGoodsVmBgOp").val();		
			var appendText2;
			if(bkgrColorOptVal == "GOODS_VW_BG_OP_01") {
				appendText2 = '<tr id="Addoption"><td>';
				appendText2 += '<sapn style="padding: 0px 22px;vertical-align: middle;">From</sapn><input type="text" id="viewTypeImgFrom" name="viewTypeImgFrom" placeholder="Please enter the ship-to address." value="<c:out value="${detail.viewTypeImgFrom }"/>"  class="inp-field widSM" maxlength="15" data-vmsg="From"/>';
				appendText2 += '<div style="padding-left: 76px; margin: 10px 0px;"><select id="selVwTyImgCd" name="viewTypeImgCcd"></select></div>';
				appendText2 += '<span>To</span><input type="text" id="viewTypeImgTo" name="viewTypeImgTo" placeholder="Please enter the ship-to address." value="<c:out value="${detail.viewTypeImgTo }"/>"  class="inp-field widSM" maxlength="15" data-vmsg="To"/>';
				appendText2 += '</td></tr>';
				
			} else {
				appendText2 = '<tr id="Addoption"><td>';
				appendText2 += '<input id="viewTypeImgHashTag" name="viewTypeImgHashTag" type="text" class="inp-field widL" placeholder="Please enter a hashtag." value="<c:out value="${detail.viewTypeImgHashTag }"  escapeXml='false' />" maxlength="24" data-vbyte="24" data-vmsg="hashtag"/><br>'
				appendText2 += '※ When entering multiple hashtags, you can distinguish them by ‘,'
				appendText2 += '</td></tr>';
			}
			$("#addViewBackGr").after(appendText2);
			$.fnGetCodeSelectAjax("sGb=GOODS_VW_BG_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "selVwTyImgCd", "${detail.viewTypeImgCcd}", "selectbox", "");
			if($("input[name=viewTypeImgBkgrColorCcd]:checked").val() == "GOODS_VW_BG_04") {
				$("#viewTypeImgBkgrColorVal").removeAttr("disabled");
				$("#viewTypeImgBkgrColorVal").css("backgroundColor", "white");
				$("#viewTypeImgBkgrColorVal").attr("placeholder", "배경색을 입력해주세요.");
			} else {
				$("#viewTypeImgBkgrColorVal").attr("disabled", true);
				$("#viewTypeImgBkgrColorVal").css("backgroundColor", "#E6E6E6");
				$("#viewTypeImgBkgrColorVal").removeAttr("placeholder");
			}
		}
	});
	
	//옵션 1,2 change함수
	$("#selGoodsVmBgOp").change(function(){
		$("#Addoption").remove();
		var bkgrColorOptVal = $("#selGoodsVmBgOp").val();
		var appendText2;
		if(bkgrColorOptVal == "GOODS_VW_BG_OP_01") {
			appendText2 = '<tr id="Addoption"><td>';
			appendText2 += '<sapn style="padding: 0px 22px;vertical-align: middle;">From</sapn><input type="text" id="viewTypeImgFrom" name="viewTypeImgFrom" placeholder="Please enter the ship-from address." value="<c:out value="${detail.viewTypeImgFrom }"/>"  class="inp-field widSM" maxlength="15" data-vmsg="From"/>';
			appendText2 += '<div style="padding-left: 76px; margin: 10px 0px;"><select id="selVwTyImgCd" name="viewTypeImgCcd"></select></div>';
			appendText2 += '<span>To</span><input type="text" id="viewTypeImgTo" name="viewTypeImgTo" placeholder="Please enter the ship-to address." value="<c:out value="${detail.viewTypeImgTo }"/>"  class="inp-field widSM" maxlength="15" data-vmsg="To"/>';
			appendText2 += '</td></tr>';
		} else {
			appendText2 = '<tr id="Addoption"><td>';
			appendText2 += '<input id="viewTypeImgHashTag" name="viewTypeImgHashTag" type="text" class="inp-field widL" placeholder="Please enter a hashtag." value="<c:out value="${detail.viewTypeImgHashTag }" escapeXml='false' />" maxlength="24" data-vbyte="24" data-vmsg="hashtag"/><br>'
			appendText2 += '※ When entering multiple hashtags, you can distinguish them by ‘,´'
			appendText2 += '</td></tr>';
		}
		$("#addViewBackGr").after(appendText2);
		$.fnGetCodeSelectAjax("sGb=GOODS_VW_BG_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "selVwTyImgCd", "${detail.viewTypeImgCcd}", "selectbox", "");
	});
	
	// Color Table disabled 및 색상변경 처리
	$(document).on("change","input[name=viewTypeImgBkgrColorCcd]",function(){
		if($("input[name=viewTypeImgBkgrColorCcd]:checked").val() == "GOODS_VW_BG_04") {
			$("#viewTypeImgBkgrColorVal").removeAttr("disabled");
			$("#viewTypeImgBkgrColorVal").css("backgroundColor", "white");
			$("#viewTypeImgBkgrColorVal").attr("placeholder", "Please enter a background color.");
		} else {
			$("#viewTypeImgBkgrColorVal").attr("disabled", true);
			$("#viewTypeImgBkgrColorVal").css("backgroundColor", "#E6E6E6");
			$("#viewTypeImgBkgrColorVal").removeAttr("placeholder");
		}
	});
	
	$("#btnSave").click(function() {
	
		if(!metaValidation('<c:out value="${contIU}"/>')) {
			return false;
		}
		if($.trim($("#sqprdNm").val()) == ""){	
			alert("Please enter the product name.");
			$("#sqprdNm").focus();
			return false;
		}
		//파일 업로드 수만큼 반복해서 파일을 선택했다면 select에 Y값을 준다
		$("input[name=fileUpload]").each(function(k,v) {	
			if($("#fileUpload_" + k).val() != "") {			
				$("#fileUploadSelect_" + k).val("Y"); 		 
			}
		});
		$("#sqprdDetlInfo").val(CrossEditor.GetBodyValue());
		
		if(CrossEditor.GetTextValue () == ""){
			alert("Please enter the detailed information with text about the product.");
			CrossEditor.SetFocusEditor();
			return false;
		}
		$("#sqprdNotiContents").val(CrossEditor1.GetBodyValue());
		
		if($("input[name=viewTypeImg]:checked").val() == "GOODS_VW_TP_01" || $("input[name=viewTypeImg]:checked").val() == "GOODS_VW_TP_02") {
			
			if("<c:out value='${contIU}'/>" == "I") {
				if($.trim($("#viewTypeOrginFile").val()) == ""){
					alert("Please select the view type image file.");
					$("#viewTypeOrginFile").focus();
					return false;
				}
			} else { // 수정일 경우
				if($("#pcListFileDel").is(":checked")) {	// 삭제를 체크했을시 입력 필수이므로 반드시 업로드 해야한다.
					if($.trim($("#viewTypeOrginFile").val()) == ""){
						alert("Please select the view type image file.");
						$("#viewTypeOrginFile").focus();
						return false;
					}
				}
			}

		} else {
			if($("input[name=viewTypeImgBkgrColorCcd]:checked").val() == "GOODS_VW_BG_04") {
				if($.trim($("#viewTypeImgBkgrColorVal").val()) == ""){
					alert("View Type Color Table Enter the background color.");
					$("#viewTypeImgBkgrColorVal").focus();
					return false;
				}
			}
			if($("#selGoodsVmBgOp").val() == "GOODS_VW_BG_OP_01") {
				if($.trim($("#viewTypeImgFrom").val()) == ""){
					alert("Please enter the View Type ship-from address.");
					$("#viewTypeImgFrom").focus();
					return false;
				}
				if($.trim($("#viewTypeImgTo").val()) == ""){
					alert("Please enter the View Type ship-to address.");
					$("#viewTypeImgTo").focus();
					return false;
				}
			} else {
				var viewTypeImgHashTagVal = $.trim($("#viewTypeImgHashTag").val());
				if(viewTypeImgHashTagVal == ""){
					alert("Please enter View Type HashTag");
					$("#viewTypeImgHashTag").focus();
					return false;
				}
				if(fnChkByteChk('viewTypeImgHashTag', 24) == false) {
					alert("The hashTag can enter up to three and totalling 24 bytes");
					$("#viewTypeImgHashTag").focus();
					return false;
				}
				if(viewTypeImgHashTagVal.split(",").length >3){
					alert("The hashTag can enter up to three and totalling 24 bytes");
					$("#viewTypeImgHashTag").focus();
					return false;
				}
				viewTypeImgHashTagVal=viewTypeImgHashTagVal.replace(",,",",");
				if(viewTypeImgHashTagVal.endsWith(",")){
					viewTypeImgHashTagVal=viewTypeImgHashTagVal.substring(0,viewTypeImgHashTagVal.length-1);
				}
				if(viewTypeImgHashTagVal.startsWith(",")){
					viewTypeImgHashTagVal=viewTypeImgHashTagVal.substring(1,viewTypeImgHashTagVal.length);
				}
				$("#viewTypeImgHashTag").val(viewTypeImgHashTagVal);
			}
			
		}

		if ($("#viewTypeOrginFile").val()){
			if(!/\.(gif|jpg|peg|png|jpeg)$/.test($("#viewTypeOrginFile").val().toLowerCase())){
				alert("Please select the image file (gif, jpg, bmp, png, jpeg)");
				$("#viewTypeOrginFile").focus();
				return false;
			}
		}

      // form byte check
      if(formByteCheck() == false) {
        return false;
      }
		
		<c:choose>
			<c:when test="${detail.sqprdSeqNo == null}">
				$("#writeForm").attr('action', '<c:url value="./register.do"/>').submit();
			</c:when>
			<c:otherwise>
				$("#writeForm").attr('action', '<c:url value="./update.do"/>').submit();
			</c:otherwise>
		</c:choose>
	});
});	


//취소버튼 리스트로 이동		
$("#btnList").on("click",function() {
	history.back();
});

//검색 팝업창 띄우기
function fncSearchGoods(idx) {
	
	win_pop("./searchGoods.do?sqprdCtgry=<c:out value='${vo.sqprdCtgry}'/>&sqprdSeqNo=<c:out value ='${detail.sqprdSeqNo}'/>&index="+idx , 'detail', '930', '700', 'yes');
}	
// RelValue값 지우기	
function fnValueDel(idx) {
	$("#reltdSqprd"+idx).val('');
	$("#relsqprdSeqNo"+idx).val('');
}	
	
//삭제 여부
function fnDelChk(i) {
	if($("#atthFileDel_"+i).is(":checked") == true) {
		$("#fileUploadDel_" + i).val("Y");
	} else {
		$("#fileUploadDel_" + i).val("N");
	}
}

//날짜 선택 밸리데이션
	
$("#svcValidEndDate").on("change keyup paste", function(){
	var stDt = $("#svcValidStatDate").val();
	var stDtArr = stDt.split("-");
	var stDtObj = new Date(stDtArr[0], Number(stDtArr[1])-1, stDtArr[2]);
	
	var enDt = $("#svcValidEndDate").val();
	var enDtArr = enDt.split("-");
	var enDtObj = new Date(enDtArr[0], Number(enDtArr[1])-1, enDtArr[2]);
	
	var betweenDay = (enDtObj.getTime() - stDtObj.getTime())/1000/60/60/24;

	if(stDt == "" && enDt != ""){
		alert("Please enter the start date.");
		$("#svcValidStatDate").focus();
		$("#svcValidEndDate").val("");
		return false;
	}
	
	if(betweenDay < 0) {
		alert("The end date is greater than the start date. Please re-enter.");
		$("#svcValidEndDate").focus();
		$("#svcValidEndDate").val("");
		return false;
	} 
});
	
$("#svcValidStatDate").on("change keyup paste", function(){	
	var stDt = $("#svcValidStatDate").val();
	var stDtArr = stDt.split("-");
	var stDtObj = new Date(stDtArr[0], Number(stDtArr[1])-1, stDtArr[2]);
	
	var enDt = $("#svcValidEndDate").val();
	var enDtArr = enDt.split("-");
	var enDtObj = new Date(enDtArr[0], Number(enDtArr[1])-1, enDtArr[2]);
	
	var betweenDay = (enDtObj.getTime() - stDtObj.getTime())/1000/60/60/24;
	if(betweenDay < 0) {
		alert("The start date is less than the end date. Please re-enter.");
		$("#svcValidStatDate").focus();
		$("#svcValidStatDate").val("");
		return false;
	} 
});

function OnInitCompleted(e){
	 if(e.editorTarget == CrossEditor){ //객체명으로 확인가능
		 CrossEditor.SetBodyValue(document.getElementById("sqprdDetlInfo").value);
	}else if(e.editorTarget == CrossEditor1){
		CrossEditor1.SetBodyValue(document.getElementById("sqprdNotiContents").value);
	}
}
</script>		
							

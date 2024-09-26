<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>


<form id="writeForm" name="writeForm" method="post" action="./register.do" enctype="multipart/form-data">

<input type="hidden" id="mvVideoSeqNo" name="mvVideoSeqNo" value="<c:out value="${detail.mvVideoSeqNo }"/>"/>
<input type="hidden" name="page" value="<c:out value="${vo.page}"/>">
<input type="hidden" name="searchValue" value="<c:out value="${vo.searchValue }" />">
<input type="hidden" name="searchType" value="<c:out value="${vo.searchType }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" name="summaryInfo" value="<c:out value="${detail.summaryInfo }" />">
<input type="hidden" name="shortInfo" value="<c:out value="${detail.shortInfo }" />">


<section>
	
	<div class="title"><h3>视频
	<c:choose>
		<c:when test="${contIU eq 'I' }">
			新增
		</c:when>
		<c:otherwise>
			修改
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
		<%@ include file="/WEB-INF/jsp/common/seo/seoRegisterFormNews.jsp" %>
			<tr>
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">基础信息</th>
			</tr>
			<tr>
				<th scope="row">类型 *</th>
				<td>
					<select id="selVideoCcd" name="mvVideoCcd"onchange="selVideoCcdOnchange(this.value)">
					</select>
				</td>
			</tr>
<%--			<tr>--%>
<%--				<th scope="row">Video *</th>--%>
<%--				<td>--%>
<%--					<div id="searchButton" class="btn-module mgtTB">--%>
<%--						<input id="reltdVideo" name="reltdVideo" type="text"  class="inp-field" value="<c:out value="${detail.titleNm}" escapeXml="false"/>" maxlength="110" readonly="readonly" style="background-color: #E6E6E6; width: 83%;"/>--%>
<%--						<input id="relVideoSeqNo" name="relVideoSeqNo" type="hidden" value="<c:out value="${detail.reltdVideo }"/>">--%>
<%--						<a href="javascript:;" id="videoDel" onclick="fnValueDel()" class="btnStyleGoods05">X</a>--%>
<%--						<a href="javascript:;" id="videoSearch" onclick="fncSearchVideo();" class="btnStyle01" style="margin-left:10px; width: 10%">Video Search</a>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--			</tr>--%>
			<input id="relVideoSeqNo" name="relVideoSeqNo" type="hidden" value="<c:out value="1"/>">
			<tr>
				<th scope="row">标题 *</th>
				<td>
					<input type="text" id="titleNm" name="titleNm" placeholder="Please enter the title." value="<c:out value="${detail.mvTitleNm}" escapeXml="false" />"  class="inp-field widL" maxlength="100" data-vmsg="title" />
				</td>
			</tr>
		<tr>
			<th scope="row">内容*</th>
			<td>
				<div class="textCont">
					<textarea name="content" id="content" class="textarea" rows="10" cols="65" placeholder="내용입력 *" ><c:out value="${detail.content }"  escapeXml="false"/></textarea>
					<script type="text/javascript" language="javascript">
						var CrossEditor = new NamoSE('content');
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
							e.editorTarget.SetBodyValue(document.getElementById("content").value);
						}
					</script>
				</div>
			</td>
		</tr>
			<tr>
				<th scope="row">PC封面图*<br/>(1920px - 700px)</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="pcListOrginFile" name="pcListOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.pcListImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/mvVideoFileDown.do?mvVideoSeqNo=<c:out value="${detail.mvVideoSeqNo }"/>&imgKinds=pcList' style="color: blue;"><c:out value="${detail.pcListImgOrgFileNm}" escapeXml="false"/></a></span>
								<span><input type="checkbox" name="pcListFileDel" id="pcListFileDel" value="Y" /> <label for="pcListFileDel">Delete</label></span>
							</c:if>
						</div>

					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">移动端封面图*<br/>(720px - 720px)</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="mobileListOrginFile" name="mobileListOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.mobileListImgOrgFileNm}">
								<span style="line-height: 28px;"><a href='<blabProperty:value key="system.admin.path"/>/mvVideoFileDown.do?mvVideoSeqNo=<c:out value="${detail.mvVideoSeqNo }"/>&imgKinds=mobileList' style="color: blue;"><c:out value="${detail.mobileListImgOrgFileNm}" escapeXml="false"/></a></span>
								<span><input type="checkbox" name="mobileListFileDel" id="mobileListFileDel" value="Y" /> <label for="mobileListFileDel">Delete</label></span>
							</c:if>
						</div>

					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">长视频*(小于50M,保存后2分钟后生效)<br/></th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="video" name="video" type="file" class="inp-field widSM"/>

						</div>

					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">短视频(小于50M,保存后2分钟后生效)<<br/></th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="shortVideo" name="shortVideo" type="file" class="inp-field widSM"/>

						</div>

					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">状态 *</th>
				<td>
					<div id="radUseCd"></div>
				</td>
			</tr>		
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
	selVideoCcdOnchange( '${detail.mvVideoCcd }');
	$("#insDtm").datepicker({
		dateFormat:"yy-mm-dd"
		,showOn : 'button'
		,buttonImage : '/static/images/cal.png'
		,buttonImageOnly : true
		,buttonText : "달력"

	});
	$("#btnSave").click(function() {
		
		// if($.trim($("#reltdVideo").val()) == ""){
		// 	alert("Please select a video.");
		// 	$("#reltdVideo").focus();
		// 	return false;
		// }

		if($.trim($("#titleNm").val()) == ""){
			alert("请输入标题");
			$("#titleNm").focus();
			return false;
		}


		var listPath = $("#pcListOrginFile").get(0).files[0];
		var listImgOrgFileNm = '${ detail.pcListImgOrgFileNm}';
		if(!listPath && !listImgOrgFileNm){
			alert("请上传pc封面图.");
			return false;
		}

		var videoPath = $("#video").get(0).files[0];
		var videoImgOrgFileNm = '${ detail.summaryInfo}';
		if(!videoPath && !videoImgOrgFileNm){
			alert("请上传长视频.");
			return false;
		}
		if ($("#video").val() != ""){
			if(!/\.(mp4|wmv|avi|mov)$/.test($("#video").val().toLowerCase())){
				alert("长视频上传的必须是视频 (mp4,wmv,avi,mov)");
				$("#video").focus();
				return false;
			}
			const videoPathfileSize = videoPath.size / 1024 / 1024; // 单位转换为MB
			if (videoPathfileSize > 50) {
				alert('文件大小不能超过5MB！');
			}
		}

		if ($("#shortVideo").val() != ""){
			if(!/\.(mp4|wmv|avi|mov)$/.test($("#shortVideo").val().toLowerCase())){
				alert("短上传的必须是视频 (mp4,wmv,avi,mov)");
				$("#shortVideo").focus();
				return false;
			}
			var shortVideoPath = $("#shortVideo").get(0).files[0];
			const shortVideoPathfileSize = shortVideoPath.size / 1024 / 1024; // 单位转换为MB
			if (shortVideoPathfileSize > 50) {
				alert('文件大小不能超过5MB！');
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
		//有输入内容才设置
		if(CrossEditor.GetTextValueLength()!=0){
			$("#content").val(CrossEditor.GetBodyValue());
		}else{
			$("#content").val("");
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
		cancel_backUp("mvVideoSeqNo","writeForm");
	});

		$.fnGetCodeSelectAjax("sGb=MVVIDEO_DIV&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "selVideoCcd", "${detail.mvVideoCcd }", "selectbox", "");
		$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
	});

function selVideoCcdOnchange(val){
	if(val=='MVVIDEO_03' || val=='MVVIDEO_02' || val=='MVVIDEO_01'){
		$("#short_video_tr_id").show()
	}else{
		$("#short_video_tr_id").hide()
	}
}
function OnInitCompleted(e){
	if(e.editorTarget == CrossEditor){
		CrossEditor.SetBodyValue(document.getElementById("content").value);
	}
}
</script>

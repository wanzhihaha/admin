<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="get" action="./register.do" enctype="multipart/form-data">
<input type="hidden" name="startDate" value="<c:out value="${vo.startDate }" />">
<input type="hidden" name="endDate" value="<c:out value="${vo.endDate }" />">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="id" name="id" value="<c:out value="${detail.id }" />">

<section>	
	<c:choose>
		<c:when test="${!empty detail.id }">
			<div class="title"><h3>修改</h3></div>
		</c:when>
		<c:otherwise>
			<div class="title"><h3>创建</h3></div>
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
			
			<%@ include file="/WEB-INF/jsp/common/seo/seoRegisterFormNews.jsp" %>
			
			<tr>
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">驾驶员信息</th>
			</tr>
			<tr>
				<th scope="row">驾驶员类型 *</th>
				<td>
					<select name="type" id="type">
						<option value="" >未选择</option>
						<option value="1" <c:if test="${1 == detail.type}">selected</c:if>>线上驾驶员</option>
						<option value="2" <c:if test="${2 == detail.type}">selected</c:if>>线下驾驶员</option>
					</select>
				</td>
			</tr>
			<tr>
				<th scope="row">驾驶员有效期 *</th>
				<td>
					<c:choose>
						<c:when test="${empty detail.id}">
							<input class="inp-field wid100" type="text" id="effectiveTime" name="effectiveTime" value="" readonly="readonly"/>
							<span>~</span>
							<input class="inp-field wid100" type="text" id="deadTime" name="deadTime" value="" readonly="readonly"/>
						</c:when>
						<c:otherwise>
							<input class="inp-field wid100" type="text" id="effectiveTime" name="effectiveTime" value="${detail.effectiveTime}" readonly="readonly"/>
							<span>~</span>
							<input class="inp-field wid100" type="text" id="deadTime" name="deadTime" value="${detail.deadTime}" readonly="readonly"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
				<th scope="row">驾驶员名称 *</th>
				<td>
					<input id="name" name="name" type="text" placeholder="请输入名称."  class="inp-field widL" value="<c:out value="${detail.name }" escapeXml="false"/>" maxlength="512" data-vmsg="name"/>
				</td>
			</tr>
	
			<tr>
				<th scope="row">驾驶员摘要</th>
				<td>
					<div class="textCont">
						<textarea name="summaryInfo" id="summaryInfo" class="textarea" rows="10" cols="65" placeholder="请输入摘要." maxlength="512" data-vmsg="summary information"><c:out value="${detail.summaryInfo }" escapeXml="false"/></textarea>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">活动列表图 *</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="listOrginFile" name="listOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.listImgOrgFileNm}">
								<span style="line-height: 28px;"><a href="<blabProperty:value key="system.admin.path"/>/corporateActivities/imgView.do?id=<c:out value='${detail.id }'/>" style="color: blue;"><c:out value="${detail.listImgOrgFileNm}" escapeXml="false"/></a></span>
							</c:if>
						</div>
						<div>
<%--							<input type="text" id="pcListImgAlt" name="pcListImgAlt" value="<c:out value="${detail.pcListImgAlt }" escapeXml="false"/>" placeholder="请输入图片描述（可不填）" class="inp-field widSM" maxlength="210" data-vbyte="210" data-vmsg="alt value" />--%>
							<span style="line-height: 28px;"> ※点击上传图片.</span>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">驾驶员内容 *</th>
				<td>
					<div class="textCont">
						<textarea name="contents" id="contents" class="textarea" rows="10" cols="65" placeholder="请输入内容 *" ><c:out value="${detail.contents }"  escapeXml="false"/></textarea>			
						<script type="text/javascript" language="javascript">
							var CrossEditor = new NamoSE('contents');
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
								e.editorTarget.SetBodyValue(document.getElementById("contents").value);
							}
						</script>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row">驾驶员状态</th>
				<td>
					<div id="radUseCd"></div>
				</td>
			</tr>
		<tr>
			<th scope="row">术语</th>
			<td>
				<input id="terminologyTags" name="terminologyTags" type="text" placeholder="请输入术语标签,用英文逗号隔开最多五个" class="inp-field widL" value="<c:out value="${detail.terminologyTags }" escapeXml="false"/>" maxlength="128" data-vmsg="术语标签"/>
			</td>
		</tr>
<%--		<tr>--%>
<%--			<th scope="row">关键词</th>--%>
<%--			<td>--%>
<%--				<input id="antistopTags" name="antistopTags" type="text" placeholder="请输入关键词标签,用英文逗号隔开最多五个" class="inp-field widL" value="<c:out value="${detail.antistopTags }" escapeXml="false"/>" maxlength="128" data-vmsg="关键词标签"/>--%>
<%--			</td>--%>
<%--		</tr>--%>
		</table>
		
		<div class="btn-module mgtS">		
			<div class="rightGroup">
				<a id="btnSave" href="#none" class="btnStyle01" >保存</a>
				<a href="javascript:;" id="btnList" class="btnStyle01">取消</a>
			</div>
		</div>	
	</div>
	</article>
</section>
</form>
					
					
<script type="text/javascript">
$(document).ready(function(){

	$("#effectiveTime, #deadTime").datepicker({
		dateFormat:"yy-mm-dd"
		,showOn : 'button'
		,buttonImage : '/static/images/cal.png'
		,buttonImageOnly : true
		,buttonText : "달력"

	});

	$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
	
	
	$("#btnSave").click(function() {

		if($.trim($("#name").val()) == ""){	
			alert("请输入名称.");
			$("#name").focus();
			return false;
		}

		if($.trim($("#type").val()) == ""){
			alert("请选择活动类型.");
			$("#type").focus();
			return false;
		}
		var listPath = $("#listOrginFile").get(0).files[0];
		var listImgOrgFileNm = '${ detail.listImgOrgFileNm}';
		if(!listPath && !listImgOrgFileNm){
			alert("请上传活动列表图.");
			return false;
		}

		if(CrossEditor.GetTextValue () == ""){
			alert("请输入内容.");
			CrossEditor.SetFocusEditor();
			return false;
		}
		if($("#terminologyTags").val()!='' || $("#terminologyTags").val()!=null){
			var terminologyTags_split = $("#terminologyTags").val().split(",");
			if(terminologyTags_split.length>5){
				alert("术语标签不能超出5个.");
				return false;
			}
		}
		// if($("#antistopTags").val()!='' || $("#antistopTags").val()!=null){
		// 	var antistopTags_split = $("#antistopTags").val().split(",");
		// 	if(antistopTags_split.length>5){
		// 		alert("关键词标签不能超出5个.");
		// 		return false;
		// 	}
		// }
		$("#contents").val(CrossEditor.GetBodyValue());

      if(formByteCheck() == false) {
        return false;
      }
		<c:choose>
			<c:when test="${empty detail.id}">
				$("#writeForm").prop("method", "post");
				$("#writeForm").attr('action', '<c:url value="./register.do"/>').submit();
			</c:when>
			<c:otherwise>
				$("#writeForm").prop("method", "post");
				$("#writeForm").attr('action', '<c:url value="./update.do"/>').submit();
			</c:otherwise>
		</c:choose>
	});
});	


//취소버튼 리스트로 이동		
$("#btnList").on("click",function() {
	cancel_backUp("id","writeForm");
});

$("#deadTime").on("change keyup paste", function(){
	var stDt = $("#effectiveTime").val();
	var stDtArr = stDt.split("-");
	var stDtObj = new Date(stDtArr[0], Number(stDtArr[1])-1, stDtArr[2]);

	var enDt = $("#deadTime").val();
	var enDtArr = enDt.split("-");
	var enDtObj = new Date(enDtArr[0], Number(enDtArr[1])-1, enDtArr[2]);

	var betweenDay = (enDtObj.getTime() - stDtObj.getTime())/1000/60/60/24;

	if(stDt == "" && enDt != ""){
		alert("Please enter the start date.");
		$("#effectiveTime").focus();
		$("#deadTime").val("");
		return false;
	}

	if(betweenDay < 0) {
		alert("The end date is greater than the start date. Please re-enter.");
		$("#deadTime").focus();
		$("#deadTime").val("");
		return false;
	}
});

$("#effectiveTime").on("change keyup paste", function(){
	var stDt = $("#effectiveTime").val();
	var stDtArr = stDt.split("-");
	var stDtObj = new Date(stDtArr[0], Number(stDtArr[1])-1, stDtArr[2]);

	var enDt = $("#deadTime").val();
	var enDtArr = enDt.split("-");
	var enDtObj = new Date(enDtArr[0], Number(enDtArr[1])-1, enDtArr[2]);

	var betweenDay = (enDtObj.getTime() - stDtObj.getTime())/1000/60/60/24;
	if(betweenDay < 0) {
		alert("The start date is less than the end date. Please re-enter.");
		$("#effectiveTime").focus();
		$("#effectiveTime").val("");
		return false;
	}
});

function OnInitCompleted(e){
	 if(e.editorTarget == CrossEditor){
		CrossEditor.SetBodyValue(document.getElementById("contents").value);
	}
}
</script>		
							

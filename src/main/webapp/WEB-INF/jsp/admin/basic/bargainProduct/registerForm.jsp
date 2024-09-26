<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="writeForm" name="writeForm" method="get" action="./register.do" enctype="multipart/form-data">
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
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">信息</th>
			</tr>

			<tr>
				<th scope="row">特价舱产品名称*</th>
				<td>
					<select name="productName" id="productName">

					</select>
				</td>
			</tr>

			<tr>
				<th scope="row">特价舱列表图 *</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="listOrginFile" name="listOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.listImgOrgFileNm}">
								<span style="line-height: 28px;"><a href="<blabProperty:value key="system.admin.path"/>/bargainProduct/imgView.do?id=<c:out value='${detail.id }'/>" style="color: blue;"><c:out value="${detail.listImgOrgFileNm}" escapeXml="false"/></a></span>
							</c:if>
						</div>
						<div>
<%--							<input type="text" id="pcListImgAlt" name="pcListImgAlt" value="<c:out value="${detail.pcListImgAlt }" escapeXml="false"/>" placeholder="请输入图片描述（可不填）" class="inp-field widSM" maxlength="210" data-vbyte="210" data-vmsg="alt value" />--%>
							<span style="line-height: 28px;"> ※点击上传图片.</span>
						</div>
					</div>
				</td>
			</tr>

		<tr >
			<th scope="row">特价舱移动端列表图标 *</th>
			<td>
				<div class="textCont">
					<div style="margin-bottom: 7px;">
						<input id="mobileListOrginFile" name="mobileListOrginFile" type="file" class="inp-field widSM"/>
						<c:if test="${!empty detail.mobileListImgOrgFileNm}">
							<span style="line-height: 28px;"><a href="<blabProperty:value key="system.admin.path"/>/bargainProduct/imgView.do?id=<c:out value='${detail.id }'/>&imgKinds=mobileList" style="color: blue;"><c:out value="${detail.mobileListImgOrgFileNm}" escapeXml="false"/></a></span>
							<span><input type="checkbox" name="mobileListFileDel" id="mobileListFileDel" value="Y" /> <label for="mobileListFileDel">Delete</label></span>
						</c:if>
					</div>
					<div>
<%--						<input type="text" id="mobileListImgAlt" name="mobileListImgAlt" value="<c:out value="${detail.mobileListImgAlt }" escapeXml="false"/>" placeholder="请输入图片描述（可不填）" class="inp-field widSM" maxlength="210" data-vbyte="210" data-vmsg="alt value" />--%>
						<span style="line-height: 28px;"> ※请点击上传图片.</span>
					</div>
				</div>
			</td>
		</tr>
			<tr>
				<th scope="row">状态</th>
				<td>
					<div id="radUseCd"></div>
				</td>
			</tr>

			<tr>
				<th scope="row">是否多个价格</th>
				<td>
					<div id="multiFlag"></div>
				</td>
			</tr>

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
	window.history.replaceState(null,null,window.location.href)
	$.commonSelectAjax("/celloSquareAdmin/productsMenu/ajaxList.do","", "productName","${detail.productName}", "selectbox", "productName");
	$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
	$.fnGetCodeSelectAjax("sGb=HOT_FLAG_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "multiFlag", "${detail.multiFlag == null ? 'Y' : detail.multiFlag}", "radio", "multiFlag");
	
	$("#btnSave").click(function() {

		if($.trim($("#productName").val()) == ""){
			alert("请输入名称.");
			$("#productName").focus();
			return false;
		}

		var listPath = $("#listOrginFile").get(0).files[0];
		var listImgOrgFileNm = '${ detail.listImgOrgFileNm}';
		if(!listPath && !listImgOrgFileNm){
			alert("请上传活动列表图.");
			return false;
		}

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

function OnInitCompleted(e){
	 if(e.editorTarget == CrossEditor){
		CrossEditor.SetBodyValue(document.getElementById("contents").value);
	}
}
</script>		
							

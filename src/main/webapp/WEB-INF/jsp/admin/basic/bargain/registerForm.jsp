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
				<th scope="row">舱位类型*</th>
				<td>
					<select name="productId" id="bargainType" onchange="hideOrShowPic(this.value)">
						<%--						<option value="10" selected="selected">公司动态</option>--%>
						<%--						<option value="20">跨境资讯</option>--%>
						<%--						<option value="30">物流动态</option>--%>
						<%--						<option value="40">物流政策</option>--%>
					</select>
				</td>
			</tr>

			<tr>
				<th scope="row">始发地*</th>
				<td>
					<input id="origin" name="origin" type="text" placeholder="始发地."  class="inp-field widL" value="<c:out value="${detail.origin }" escapeXml="false"/>" maxlength="512" data-vmsg="origin"/>
				</td>
			</tr>

			<tr>
				<th scope="row">目的地*</th>
				<td>
					<input id="destination" name="destination" type="text" placeholder="目的地."  class="inp-field widL" value="<c:out value="${detail.destination }" escapeXml="false"/>" maxlength="512" data-vmsg="destination"/>
				</td>
			</tr>

			<tr>
				<th scope="row">参考时效*</th>
				<td>
					<input id="aging" name="aging" type="text" placeholder="参考时效."  class="inp-field widL" value="<c:out value="${detail.aging }" escapeXml="false"/>" maxlength="512" data-vmsg="aging"/>
				</td>
			</tr>

			<tr>
				<th scope="row">备注*</th>
				<td>
					<input id="remark" name="remark" type="text" placeholder="备注."  class="inp-field widL" value="<c:out value="${detail.remark }" escapeXml="false"/>" maxlength="512" data-vmsg="remark"/>
				</td>
			</tr>

		<tr>
			<th scope="row">有效期 *</th>
			<td>
				<c:choose>
					<c:when test="${empty detail.id}">
						<input class="inp-field wid100" type="text" id="effectiveDate" name="effectiveDate" value="" readonly="readonly"/>
						<span>~</span>
						<input class="inp-field wid100" type="text" id="expiryDate" name="expiryDate" value="" readonly="readonly"/>
					</c:when>
					<c:otherwise>
						<input class="inp-field wid100" type="text" id="effectiveDate" name="effectiveDate" value="${detail.effectiveDate}" readonly="readonly"/>
						<span>~</span>
						<input class="inp-field wid100" type="text" id="expiryDate" name="expiryDate" value="${detail.expiryDate}" readonly="readonly"/>
					</c:otherwise>
				</c:choose>
			</td>
		</tr>

			<tr>
				<th scope="row">是否热卖</th>
				<td>
					<div id="hotFlag"></div>
				</td>
			</tr>

			<tr>
				<th scope="row">币种*</th>
				<td>
					<input id="currency" name="currency" type="text" placeholder="币种."  class="inp-field widL" value="<c:out value="${detail.currency }" escapeXml="false"/>" maxlength="512" data-vmsg="currency"/>
				</td>
			</tr>

			<tr>
				<th scope="row">热卖标签图片 *</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input id="listOrginFile" name="listOrginFile" type="file" class="inp-field widSM"/>
							<c:if test="${!empty detail.listImgOrgFileNm}">
								<span style="line-height: 28px;"><a href="<blabProperty:value key="system.admin.path"/>/bargainProduct/imgView.do?id=<c:out value='${detail.id }'/>" style="color: blue;"><c:out value="${detail.listImgOrgFileNm}" escapeXml="false"/></a></span>
							</c:if>
                            <span><input type="checkbox" name="pcListFileDel" id="pcListFileDel" value="Y"/> <label
                                    for="pcListFileDel">Delete</label></span>
						</div>
						<div>
<%--							<input type="text" id="pcListImgAlt" name="pcListImgAlt" value="<c:out value="${detail.pcListImgAlt }" escapeXml="false"/>" placeholder="请输入图片描述（可不填）" class="inp-field widSM" maxlength="210" data-vbyte="210" data-vmsg="alt value" />--%>
							<span style="line-height: 28px;"> ※点击上传图片.</span>
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<th scope="row">价格</th>
				<td>
					<div class="btn-module chk" id="addRowDiv">
						<ul class="country-row-ul">
							<c:choose>
								<c:when test="${not empty detail.bargainPrices}">
									<c:forEach var="item" items="${detail.bargainPrices}">
										<li class="row-input">
											单位:<input type="text" name="unit" value="<c:out value="${item.unit}" escapeXml="false"/>"
													  class="inp-field widSM searchKeyWord" style="width: 39% !important;"
													  placeholder="Please enter the key word" maxlength="200"
													  data-vbyte="200" data-vmsg="Sort2"/>
											价格:<input type="text" name="price" value="<c:out value="${item.price}" escapeXml="false"/>"
													  class="inp-field widSM searchKeyWord" style="width: 39% !important;"
													  placeholder="Please enter the key word" maxlength="200"
													  data-vbyte="200" data-vmsg="Sort2"/>
											<a href="javascript:;" class="btnStyle01"
											   style="text-indent: 0; height: 28px"
											   onclick="removeParent(this)">删除</a>
										</li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<li class="row-input">
										单位:<input type="text" name="unit" class="inp-field widSM searchKeyWord" style="width: 39% !important;"
												  placeholder="Please enter the key word" maxlength="200"
												  data-vbyte="200" data-vmsg="Sort2"/>
										价格:<input type="text" name="price" class="inp-field widSM searchKeyWord" style="width: 39% !important;"
												  placeholder="Please enter the key word" maxlength="200"
												  data-vbyte="200" data-vmsg="Sort2"/>
										<a href="javascript:;" class="btnStyle01"
										   style="text-indent: 0; height: 28px"
										   onclick="removeParent(this)">删除</a>
									</li>
								</c:otherwise>
							</c:choose>
						</ul>
						<a href="javascript:;" id="addRow" class="btnStyle01">添加</a>
					</div>

				</td>
			</tr>
			<tr>
				<th scope="row">状态</th>
				<td>
					<div id="radUseCd"></div>
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
	$("#effectiveDate, #expiryDate").datepicker({
		dateFormat:"yy-mm-dd"
		,showOn : 'button'
		,buttonImage : '/static/images/cal.png'
		,buttonImageOnly : true
		,buttonText : "달력"

	});
	initBargainType("${detail.productId }");
	hideOrShowPic($("#bargainType").val());
	$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "radUseCd", "${detail.useYn == null ? 'Y' : detail.useYn}", "radio", "useYn");
	$.fnGetCodeSelectAjax("sGb=HOT_FLAG_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "hotFlag", "${detail.hotFlag == null ? 'Y' : detail.hotFlag}", "radio", "hotFlag");
	
	$("#btnSave").click(async function() {
		if($.trim($("#origin").val()) == ""){
			alert("请输入始发地");
			$("#origin").focus();
			return false;
		}
		if($.trim($("#destination").val()) == ""){
			alert("请输入目的地");
			$("#destination").focus();
			return false;
		}
		if($.trim($("#aging").val()) == ""){
			alert("请输入参考时效");
			$("#aging").focus();
			return false;
		}
		if($.trim($("#currency").val()) == ""){
			alert("请输入币种");
			$("#currency").focus();
			return false;
		}
		if($.trim($("#effectiveDate").val()) == ""){
			alert("请输入有效期");
			$("#currency").focus();
			return false;
		}
		if($.trim($("#expiryDate").val()) == ""){
			alert("请输入有效期");
			$("#currency").focus();
			return false;
		}
		checkPrice = false
		$('input[name=unit]').each(function () {
			var val = $(this).val();
			if ($.trim(val) == ""){
				alert("请输入单位");
				checkPrice = true;
			}
		})

		$('input[name=price]').each(function () {
			var val = $(this).val();
			if ($.trim(val) == ""){
				alert("请输入价格");
				checkPrice = true;
			}
		})

		if (checkPrice){
			return false;
		}

		<%--var listPath = $("#listOrginFile").get(0).files[0];--%>
		<%--var listImgOrgFileNm = '${ detail.listImgOrgFileNm}';--%>
		<%--if(!listPath && !listImgOrgFileNm){--%>
		<%--	alert("请上传活动列表图.");--%>
		<%--	return false;--%>
		<%--}--%>

      if(formByteCheck() == false) {
        return false;
      }
		var cc = await checkLinkName()
		console.log(cc)
      if (cc == false) {
      	alert("线路名重复")
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

	$("#addRow").click(function () {
		var elHtml = `<li class="row-input">
                      单位:<input type="text" name="unit" class="inp-field widSM searchKeyWord" style="width: 39% !important;"
                       placeholder="Please enter the key word" maxlength="200"
                        data-vbyte="200" data-vmsg="Sort2"/>
                      价格:<input type="text" name="price" class="inp-field widSM searchKeyWord" style="width: 39% !important;"
                       placeholder="Please enter the key word" maxlength="200"
                         data-vbyte="200" data-vmsg="Sort2"/>
                       <a href="javascript:;" class="btnStyle01"
                            style="text-indent: 0; height: 28px"
                         onclick="removeParent(this)">删除</a>
                       </li>`;
		$("#addRowDiv ul").append(elHtml)
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


function removeParent(v) {
	$(v).parent().remove();
}

async function checkLinkName() {

	var check = true
	var id = $("#id").val();
	if (id == ''){
		id = 0
	}
	await axios.get('/celloSquareAdmin/bargain/checkLinkName.do', {
		params:{
			start:$("#origin").val(),
			end:$("#destination").val(),
			productId:$("#bargainType").val(),
			id:id
		}
	}).then(function (response) {
		var data = response.data;
		console.log(data)
		if (data == "N") {
			check = false
		}

	})
	.catch(function (error) {
		console.log(error);
	});
	return check
}

function initBargainType(productId){
	$.ajax({
		url : "/celloSquareAdmin/bargainProduct/productList.do"
		, dataType : 'json'
		, data : null
		, async : false
		, contentType: "application/x-www-form-urlencoded; charset=UTF-8"
		, success : function(json) {
			console.log(json);
			var resultText = "";
			if($("#bargainType"+" option").length){
				var defaultStr = $.trim($("#bargainType"+" option:eq(0)").text());
				resultText += "<option value=''>"+defaultStr+"</option>";
			}
			$.each(json, function (index, value) {
				var cd = $.trim(value.id);
				var nm = $.trim(value.productName);
				var selected = "";
				if(cd == productId) {
					selected = "selected='selected'";
				}
				resultText += "<option value='"+cd+"' "+selected+">"+nm+"</option>\r\n";
			});
			$("#bargainType").html(resultText);
			//return resultText;
		}
		, error : function() {
			return "";
		}
	});
}

function hideOrShowPic(val){
	$.ajax({
		url : "/celloSquareAdmin/bargainProduct/getMultiFlag.do"
		, data : "id="+val
		, async : false
		, contentType: "application/x-www-form-urlencoded; charset=UTF-8"
		, success : function(res) {
			console.log("结果"+res)
			if(res == 'N'){
				$("#addRow").hide()
			}else{
				$("#addRow").show()
			}
		}
		, error : function() {
			return "";
		}
	});
}

$("#expiryDate").on("change keyup paste", function(){
	var stDt = $("#effectiveDate").val();
	var stDtArr = stDt.split("-");
	var stDtObj = new Date(stDtArr[0], Number(stDtArr[1])-1, stDtArr[2]);

	var enDt = $("#expiryDate").val();
	var enDtArr = enDt.split("-");
	var enDtObj = new Date(enDtArr[0], Number(enDtArr[1])-1, enDtArr[2]);

	var betweenDay = (enDtObj.getTime() - stDtObj.getTime())/1000/60/60/24;

	if(stDt == "" && enDt != ""){
		alert("Please enter the start date.");
		$("#effectiveDate").focus();
		$("#expiryDate").val("");
		return false;
	}

	if(betweenDay < 0) {
		alert("The end date is greater than the start date. Please re-enter.");
		$("#expiryDate").focus();
		$("#expiryDate").val("");
		return false;
	}
});

$("#effectiveDate").on("change keyup paste", function(){
	var stDt = $("#effectiveDate").val();
	var stDtArr = stDt.split("-");
	var stDtObj = new Date(stDtArr[0], Number(stDtArr[1])-1, stDtArr[2]);

	var enDt = $("#expiryDate").val();
	var enDtArr = enDt.split("-");
	var enDtObj = new Date(enDtArr[0], Number(enDtArr[1])-1, enDtArr[2]);

	var betweenDay = (enDtObj.getTime() - stDtObj.getTime())/1000/60/60/24;
	if(betweenDay < 0) {
		alert("The start date is less than the end date. Please re-enter.");
		$("#effectiveDate").focus();
		$("#effectiveDate").val("");
		return false;
	}
});
</script>		
							

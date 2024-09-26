<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>


<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="post">
	<input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
	<input type="hidden" id="id" name="id" />

	<section>
		<div class="title"><h3>特价舱线路</h3></div>
		<div class="Cont_place" id="vueBarList">
			<article>
				<div class="inputUI_simple">
					<table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다." id="bargainExcelUpload">
						<caption>상태, 답변 상태, 기간설정, 직접검색 영역</caption>
						<tr>
							<th scope="row">搜索</th>
							<td>
								<input type="text" placeholder="请输入始发地或目的地" class="inp-field widS mglS" id="searchValue" name="searchValue" value="<c:out value="${vo.searchValue }" escapeXml="false"/>" />
							</td>
							<th scope="row">产品类型</th>
							<td>
								<select name="productId" id="bargainType" >

								</select>
							</td>

							<td>
								<div class="btn-module floatR">
									<div class="rightGroup">
										<a href="javascript:;" class="search" onclick="fnSearch();" id="btnSearch">搜索</a>
										<a href="javascript:;" class="refresh" onclick="resetClick();" id="btnReset">重置</a>
										<a href="javascript:;" class="refresh" onclick="bargainSwitch();" id="switch">页面展示开关</a>
										开关状态:<c:out value="${bargainSwitchData}" escapeXml="false"/>
									</div>
								</div>
							</td>
							<td>
								<div class="btn-module floatR">
									<div class="rightGroup">
										<a href="javascript:;" class="search" @click="termExcelDownload" style="width:110px;" id="ExcelDownload">模板下载</a>
									</div>
								</div>
							</td>
<%--							<td>--%>
<%--								<div class="btn-module floatR">--%>
<%--									<div class="rightGroup">--%>
<%--										<a href="javascript:;" class="search" onclick="excelDownLoad();" style="width:110px;" id="ExcelDownload">导出Excel</a>--%>
<%--									</div>--%>
<%--								</div>--%>
<%--							</td>--%>
						</tr>
						<tr>
							<th >批量上传:</th>
							<td style="display: flex;">
								<div class="btn-module floatR">
									<div class="rightGroup" style="width: 214px;display: flex;align-items: center">
										<input type="file" name="file"  @change="termExcelUpload"/>
									</div>
								</div>
							</td>
							<td>
								<div class="btn-module floatR">
									<div class="rightGroup">
										<a href="javascript:;" class="search" @click="exportTerm" style="width:110px;" id="exportTerm">批量导出</a>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</article>
			<article>
				<div class="btn-module mgtL2 mgbS">
					<div class="leftGroup">
			<span>
				<span class="tb-text"><strong>Total <span class="colorR"><c:out value="${totalCount }"/></span> Cases</strong></span>
			</span>
					</div>
					<div class="rightGroup">
						<select name="rowPerPage" id="rowPerPage" onchange="fnSearch();">
							<option value="10" <c:if test="${vo.rowPerPage eq '10'}">selected="selected"</c:if>>10</option>
							<option value="20" <c:if test="${vo.rowPerPage eq '20'}">selected="selected"</c:if>>20</option>
							<option value="30" <c:if test="${vo.rowPerPage eq '30'}">selected="selected"</c:if>>30</option>
							<option value="50" <c:if test="${vo.rowPerPage eq '50'}">selected="selected"</c:if>>50</option>
							<option value="100" <c:if test="${vo.rowPerPage eq '100'}">selected="selected"</c:if>>100</option>
						</select>
					</div>
				</div>
				<table class="bd-list inputUI_simple" summary="리스트 영역 입니다.">
<%--					<caption>NO</caption>--%>
<%--					<colgroup>--%>
<%--						<col width="3%" />--%>
<%--						<col width="5%" />--%>
<%--						<col width="6%" />--%>
<%--						<col width="3%" />--%>
<%--						<col width="6%" />--%>
<%--						<col width="6%" />--%>
<%--						<col width="5%" />--%>
<%--						<col width="6%" />--%>
<%--					</colgroup>--%>
					<thead>
					<tr>
						<th scope="col">是否热卖</th>
						<th scope="col">序号</th>
						<th scope="col">产品类型</th>
						<th scope="col">起运地</th>
						<th scope="col">目的地</th>
						<th scope="col">参考时效</th>
						<th scope="col">备注</th>
						<th scope="col">热卖标签</th>
						<th scope="col">创建人</th>
						<th scope="col">创建时间</th>
						<th scope="col">更新人</th>
						<th scope="col">更新时间</th>
						<th scope="col">状态</th>

					</tr>
					</thead>
					<tbody>
					<c:forEach var="list" items="${list }" varStatus="stat">
						<tr>
							<td>
								<input id="ischeckedVue" v-model="fruits" type="checkbox" name="ischecked" value="${list.id}">
							</td>
							<td>
							<span style="width: 50px;">
								<c:out value="${stat.index + 1}" />
							</span>
							</td>
							<td>
								<a href="javascript:;" onclick="detail('<c:out value='${list.id}'/>');"><c:out value='${list.productType}' escapeXml="false"/></a>
							</td>
							<td>
								<a href="javascript:;" onclick="detail('<c:out value='${list.id}'/>');"><c:out value='${list.origin}' escapeXml="false"/></a>
							</td>
							<td>
								<a href="javascript:;" onclick="detail('<c:out value='${list.id}'/>');"><c:out value='${list.destination}' escapeXml="false"/></a>
							</td>
							<td>
								<c:out value="${list.aging }" escapeXml="false"/>
							</td>

							<td>
								<c:out value="${list.remark }" escapeXml="false"/>
							</td>
							<td>
								<c:out value="${list.hotFlag }" escapeXml="false"/>
							</td>
<%--							<td style="text-align: center;">--%>
<%--								<c:if test="${! empty list.listImgOrgFileNm}">--%>
<%--									<img src="<blabProperty:value key="system.admin.path"/>/bargainProduct/imgView.do?id=<c:out value='${list.id }'/>"--%>
<%--										 style="max-width: 100px; vertical-align: middle;">--%>
<%--								</c:if>--%>
<%--							</td>--%>
                            <td>
                                <c:out value="${list.insPersonId }" escapeXml="false"/>
                            </td>
                            <td>
							<c:out value="${list.insDtm }" />
                            </td>
                            <td>
                                <c:out value="${list.updPersonId }" escapeXml="false"/>
                            </td>
                            <td>
								<c:out value="${list.updDtm }"/>
                            </td>
							<td>
								<c:out value="${list.useYn }" escapeXml="false"/>
							</td>
						</tr>
					</c:forEach>
					<c:if test="${fn:length(list) < 1 }">
						<tr>
							<td colspan="9" class="blank">没有数据</td>
						</tr>
					</c:if>

					</tbody>
				</table>

				<div class="btn-module mgtSM">
					<div class="rightGroup"><a href="#none"  onclick="doRegister();" class="btnStyle01">新增</a></div>
					<div class="rightGroup"><a href="#none"  @click="showHot" class="btnStyle01">显示热卖</a></div>
					<div class="rightGroup"><a href="#none"  @click="blankHot" class="btnStyle01">不显示热卖</a></div>
					<div class="rightGroup"><a href="#none"  @click="batchDel" class="btnStyle01">批量删除</a></div>
				</div>


				<!-- table paging -->
				<div class="page-module">
					<p class="paging">
						<blabPaging:paging currentPage="${vo.page }" rowSize="${vo.rowPerPage }" totalCount="${totalCount}" pagingId="admin"/>
					</p>
				</div>

			</article>
		</div>

	</section>
</form>
<a id="excelDownd" style="display:none"></a>

<script type="text/javascript">
	$(document).ready(function () {
		var vue = new Vue({
			el:'#vueBarList',
			data:{
				fruits:[]
			},
			methods:{
				showHot(){
					{
						var r=confirm("你确定显示热卖标签吗")
						if (r){
							axios.post('/celloSquareAdmin/bargain/showHot.do', {
								ids: this.fruits    // 参数 lastName
							})
									.then(function (response) {
										alert(response.data)
										location.reload()
									})
									.catch(function (error) {
										console.log(error);
									});
						}
					}
				},
				batchDel(){
					{
						var r=confirm("你确定要批量删除吗")
						if (r){
							axios.post('/celloSquareAdmin/bargain/batchDel.do', {
								ids: this.fruits    // 参数 lastName
							})
									.then(function (response) {
										alert(response.data)
										location.reload()
									})
									.catch(function (error) {
										console.log(error);
									});
						}
					}
				},
				blankHot(){
					{
						var r=confirm("你确定不显示热卖标签吗")
						if (r){
							axios.post('/celloSquareAdmin/bargain/blankHot.do', {
								ids: this.fruits    // 参数 lastName
							})
									.then(function (response) {
										alert(response.data)
										location.reload()
									})
									.catch(function (error) {
										console.log(error);
									});
						}
					}
				},
				exportTerm(){
					axios({
						method: "get",
						url: "/celloSquareAdmin/bargain/exportTerm.do",
						responseType: "blob", // 表明返回服务器返回的数据类型
						params: {
							searchValue:$("#searchValue").val(),
							productId:$("#bargainType").val()
						}
					}).then((res) => {
						// 处理返回的文件流
						let blob = new Blob([res.data], { type: res.data.type });
						//获取fileName,截取content-disposition的filename；按=分割，取最后一个
						const fileName = decodeURI(res.headers['content-disposition'].split("=")[1], "UTF-8");
						let downloadElement = document.createElement("a");
						let href = window.URL.createObjectURL(blob); //创建下载的链接
						downloadElement.href = href;
						downloadElement.download = fileName; //下载后文件名
						document.body.appendChild(downloadElement);
						downloadElement.click(); //点击下载
						document.body.removeChild(downloadElement); //下载完成移除元素
						window.URL.revokeObjectURL(href); //释放blob
						this.$message.success("[图书信息]已成功导出!");
					}).catch(function(error) {
						// 请求失败处理
						console.log(error);
					});

				},
				termExcelUpload(event){
					let file = event.target.files;
					let formData = new FormData();
					formData.append("file",file[0])
					axios.post('/celloSquareAdmin/bargain/upload.do',formData,{
						headers:{
							"Content-Type":"multipart/form-data"
						}
					}).then((res)=>{
						alert(res.data)
						location.reload()
					}).catch((e)=>{
						alert('上传失败')
						location.reload()
					})
				},
				termExcelDownload(){
					axios({
						method: "get",
						url: "/celloSquareAdmin/bargain/download.do",
						responseType: "blob", // 表明返回服务器返回的数据类型
					}).then((res) => {
						// 处理返回的文件流
						let blob = new Blob([res.data], { type: res.data.type });
						//获取fileName,截取content-disposition的filename；按=分割，取最后一个
						const fileName = decodeURI(res.headers['content-disposition'].split("=")[1], "UTF-8");
						let downloadElement = document.createElement("a");
						let href = window.URL.createObjectURL(blob); //创建下载的链接
						downloadElement.href = href;
						downloadElement.download = fileName; //下载后文件名
						document.body.appendChild(downloadElement);
						downloadElement.click(); //点击下载
						document.body.removeChild(downloadElement); //下载完成移除元素
						window.URL.revokeObjectURL(href); //释放blob
						this.$message.success("[图书信息]已成功导出!");
					}).catch(function(error) {
						// 请求失败处理
						console.log(error);
					});
				}
			}
		});
		initBargainType('${bargainTypeVal}');
		//$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "useYn", "${vo.useYn }", "selectbox", "");
		$("#searchValue").keypress(function(e) {
			if (e.keyCode == 13) {
				e.preventDefault();
				fnSearch();
			}
		});
	});
	//검색/페이징
	function fnSearch(){
		$("#page").val("1");
		$("#listForm").prop("method", "get");
		$("#listForm").attr('action', '<c:url value="./list.do"/>').submit();
	}
	function fncPage(page) {
		$("#page").val(page);
		$("#listForm").prop("method", "get");
		$("#listForm").attr('action', '<c:url value="./list.do"/>').submit();
	}

	//값 초기화
	function resetClick(){
		$("#searchValue").val("");
		$("#bargainType").val(-1);
		$("#listForm").prop("method", "get");
		$("#listForm").attr('action', '<c:url value="./list.do"/>').submit();
	}
	//등록페이지 이동
	function doRegister(){
		$("#listForm").prop("method", "post");
		$("#listForm").attr('action', '<c:url value="./registerForm.do"/>').submit();
	}
	// 상세페이지 이동
	function detail(id){
		$("#id").val(id);
		$("#listForm").prop("method", "post");
		$("#listForm").attr('action', '<c:url value="./detail.do"/>').submit();
	}

	function bargainSwitch(){
		if (confirm("确认开关特价舱页面吗?")) {
			$("#listForm").prop("method", "post");
			$("#listForm").attr('action', '<c:url value="./bargainSwitch.do"/>').submit();
		}
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
				resultText += "<option value='"+'-1'+"' "+"selected='selected'"+">"+'全部'+"</option>\r\n";
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

</script>
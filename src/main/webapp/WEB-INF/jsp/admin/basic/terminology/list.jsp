<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>


<!-- START:CONTENT -->
<form id="listForm" action="./list.do" method="post">
<input type="hidden" id="page" name="page" value="<c:out value="${vo.page}"/>">
<input type="hidden" id="sqprdSeqNo" name="id"/>
<%--<input type="hidden" id="sqprdCtgry" name="sqprdCtgry" value="<c:out value="${vo.sqprdCtgry }"/>">--%>
<%--<input type="hidden" id="sqprdCtgryNm" name="sqprdCtgryNm" value="<c:out value="${vo.sqprdCtgryNm }"/>">--%>

<section>
	<div class="title"><h3>术语表</h3></div>
	<div class="Cont_place">
		<article>
			<div class="inputUI_simple">
				<table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다." id = "TermExcelUpload">
					<caption>상태, 답변 상태, 기간설정, 직접검색 영역</caption>
					<tr>
						<th scope="row">搜索</th>
						<td>
							<input type="text" placeholder="请输入搜索名称"  id="searchValue" name="searchValue" value="<c:out value="${vo.searchValue }" escapeXml="false"/>" />
						</td>
						<td>
							<div class="btn-module floatR">
								<div class="rightGroup">
									<a href="javascript:;" class="search" onclick="fnSearch();" id="btnSearch">搜索</a>
									<a href="javascript:;" class="refresh" onclick="resetClick();" id="btnReset">重置</a>
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
					<tr>
						<th >新增词库</th>
						<td>
							<input type="text" placeholder="请输入词库名"  id="dicName" name="dicName" escapeXml="false"/>
						</td>
						<td>
							<div class="btn-module floatR">
								<a href="javascript:;" class="search" @click="addDic" style="width:110px;" id="addDic">新增词库</a>
							</div>
						</td>
						<td>
							<div class="btn-module floatR">
								<a href="javascript:;" onclick="fncSearchHashTag();" class="search" style="width:110px;" id="dicList">分词匹配库</a>
							</div>
						</td>
					</tr>
				</table>
			</div>
		</article>
<%--	<article>--%>

<%--	<div class="inputUI_simple">	--%>
<%--	<table class="bd-form s-form" summary="상태, 답변 상태, 기간설정, 직접검색 영역 입니다." id="TermExcelUpload">--%>
<%--		<caption>상태, 답변 상태, 기간설정, 직접검색 영역</caption>--%>
<%--		<colgroup>--%>
<%--			<col width="120px" />--%>
<%--			<col width="" />--%>
<%--			<col width="300px" />--%>
<%--		</colgroup>--%>
<%--		<tr>--%>
<%--			<th scope="row">搜索</th>--%>
<%--			<td>--%>
<%--				<input type="text" placeholder="请输入标题"  id="searchValue" name="searchValue" value="<c:out value="${vo.searchValue }" escapeXml="false"/>" />--%>
<%--			</td>--%>
<%--			<td>--%>
<%--				<div class="btn-module floatR">--%>
<%--					<div class="rightGroup">--%>
<%--						<a href="javascript:;" class="search" onclick="fnSearch();" id="btnSearch">搜索</a>--%>
<%--						<a href="javascript:;" class="refresh" onclick="resetClick();" id="btnReset">重置</a>--%>
<%--					</div>--%>
<%--				</div>	--%>
<%--			</td>--%>
<%--		</tr>--%>
<%--		<tr>--%>
<%--			<td style="display: flex;">--%>
<%--				<div class="btn-module floatR">--%>
<%--					<div class="rightGroup" style="width: 214px;display: flex;align-items: center">--%>
<%--						<input type="file" class="search" @change="termExcelUpload" style="width:110px;margin-right: 10px;" >批量上传</input>--%>
<%--					</div>--%>
<%--				</div>--%>

<%--				<input type="button" value="模板下载" @click="termExcelDownload" style="width:110px;height: 30px;color: #fff;padding: 0 15px;text-align: center;line-height: 28px;background: #1a97c5;border: none;">--%>
<%--			</td>--%>
<%--		</tr>--%>
<%--	</table>--%>
<%--	</div>--%>
<%--	</article>--%>
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
		<caption>NO</caption>
		<colgroup>
			<col width="3%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8" />
			<!-- <col width="13%" /> -->
			<col width="6%" />
			<col width="9%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
			<col width="8%" />
		</colgroup>
		<thead>
		<tr>
			<th scope="col">序号</th>
			<th scope="col">排序</th>
			<th scope="col">标题</th>
			<th scope="col">内容</th>
			<!-- <th scope="col">Image</th> -->
			<th scope="col">浏览量</th>
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
					<span style="width: 50px;">
						<c:out value="${(totalCount - ((vo.page-1) * vo.rowPerPage)) - stat.index}" />
					</span>
				</td>
				<td>
					<input type="text" name="listSortOrder" class="inp-field widM2" value="<c:out value="${list.ordb}" />" onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" maxlength="3" />
					<input type="hidden" name="listblogSeq" value="<c:out value="${list.id}" />" />
				</td>
				<td>
					<a href="javascript:;" onclick="detail('<c:out value='${list.id}'/>');"><c:out value='${list.terminologyName}' escapeXml="false"/></a>
				</td>
				<td><c:out value="${list.description }"/></td>
				<td><c:out value="${list.srchCnt }"/></td>
				<td><c:out value="${list.insPersonId }"/></td>
				<td><c:out value="${list.insDtm }"/></td>
				<td><c:out value="${list.updPersonId }"/></td>
				<td><c:out  value="${list.updDtm }"/></td>
				<td><c:out value="${list.useYn }"/></td>
			</tr>
		</c:forEach>
		<c:if test="${fn:length(list) < 1 }">
			<tr>
				<td colspan="9" class="blank">No registered data</td>
			</tr>
		</c:if>	

		</tbody>
	</table>

	<div class="btn-module mgtSM">
		<div class="leftGroup"><a href="#none"  onclick="doSortSave();" class="btnStyle01">自定义排序</a></div>
		<div class="rightGroup"><a href="#none"  onclick="doRegister();" class="btnStyle01">创建新术语</a></div>
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


<script type="text/javascript">
$(document).ready(function () {

	var vue = new Vue({
		el:'#TermExcelUpload',
		data:{
		},
		methods:{
			addDic(){
				axios.get('/celloSquareAdmin/terminology/addDic.do',{
					params:{
						"dicName":$("#dicName").val()
					}
				},{

				}).then((res)=>{
					alert(res.data)
					location.reload()
				}).catch((e)=>{
					alert('新增失败')
					location.reload()
				})

			},
			exportTerm(){
				axios({
					method: "get",
					url: "/celloSquareAdmin/terminology/exportTerm.do",
					responseType: "blob", // 表明返回服务器返回的数据类型
					params: {searchValue:$("#searchValue").val()}
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
				axios.post('/celloSquareAdmin/terminology/upload.do',formData,{
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
					url: "/celloSquareAdmin/terminology/download.do",
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

	$.fnGetCodeSelectAjax("sGb=USE_YN&langCd=<c:out value="${SESSION_FORM_ADMIN.langCd}"/>", "useYn", "${vo.useYn }", "selectbox", "");
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
	function fncSearchHashTag() {
		win_pop("/celloSquareAdmin/terminology/dicList.do", 'detail', '930', '700', 'yes');
	}
	//값 초기화
	function resetClick(){
		$("#searchValue").val("");
		$("[name=searchType]").val("search_sqprdNm");

		$("#listForm").prop("method", "get");
		$("#listForm").attr('action', '<c:url value="./list.do"/>').submit();
	}
	//등록페이지 이동
	function doRegister(){
		$("#listForm").prop("method", "post");
		$("#listForm").attr('action', '<c:url value="./registerForm.do"/>').submit();
	}
	// 상세페이지 이동
	function detail(seq){
		$("#sqprdSeqNo").val(seq);
		$("#listForm").prop("method", "post");
		$("#listForm").attr('action', '<c:url value="./detail.do"/>').submit();
	}
	
	// 정렬순서 저장
	function doSortSave() {
		if(confirm("Do you really want to fix it?")) {
			$("#listForm").prop("method", "post");
			$("#listForm").attr('action', '<c:url value="./doSortOrder.do"/>').submit();
		}
		return false;
	}

function popup(seq) {
	var url = "./preView.do?id="+seq

	window.open(url, "ViewImg", "width=300"+", height=300"+", top=300"+", left=300");
}
</script>
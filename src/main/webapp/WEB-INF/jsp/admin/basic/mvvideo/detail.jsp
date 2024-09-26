<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/tag/taglib.jspf" %>

<form id="detailForm" name="detailForm" method="post" action="./updateForm.do">
<input type="hidden" name="page" value="<c:out value="${vo.page }" />">
<input type="hidden" name="rowPerPage" value="<c:out value="${vo.rowPerPage }" />">
<input type="hidden" id="mvVideoSeqNo" name="mvVideoSeqNo" value="<c:out value="${detail.mvVideoSeqNo }" />">


<section>	
	<div class="title"><h3>Home Video Detail</h3></div>
	<article>
	<div class="Cont_place">
	<table class="bd-form" summary="등록, 수정하는 영역 입니다.">
			<caption>열람,내용</caption>
			<colgroup>
				<col width="130px" />
				<col width="" />
			</colgroup>
		<%@ include file="/WEB-INF/jsp/common/seo/seoDetailNews.jsp" %>
			<tr>
				<th colspan="2" style="text-align: center; font-weight: bold; font-size: large; border-top: 1px solid; border-bottom: 1px solid;">信息</th>
			</tr>
			<tr>
				<th scope="row">类型</th>
				<td>
					<c:out value="${detail.mvVideoCcdNm }" />
				</td>
			</tr>	
<%--			<tr>--%>
<%--				<th scope="row">Video</th>--%>
<%--				<td>--%>
<%--					<c:out value="${detail.titleNm}" escapeXml="false"/>--%>
<%--				</td>--%>
<%--			</tr>	--%>
			<tr>
				<th scope="row">标题</th>
				<td>
					<c:out value="${detail.mvTitleNm }" escapeXml="false" />
				</td>
			</tr>
		<tr>
			<th scope="row">内容</th>
			<td>
				<c:out value="${detail.content }" escapeXml="false" />
			</td>
		</tr>
			<%
				pageContext.setAttribute("crlf", "\r\n");
			%>
			<tr>
				<th scope="row">长视频链接</th>
				<td>
					${detail.summaryInfo }
				</td>
			</tr>
		<tr>
			<th scope="row">短视频链接</th>
			<td>
				${detail.shortInfo }
			</td>
		</tr>
		<%--<tr>
            <th scope="row">Writer Information</th>
            <td>
                <c:out value="${detail.wrtInfo1}" escapeXml="false"/>
                &nbsp;<c:out value="${detail.wrtInfo2}" escapeXml="false"/>
            </td>
        </tr>--%>
			<tr>
				<th scope="row">PC图片</th>
				<td>
					<c:if test="${! empty detail.pcListImgOrgFileNm}">
						<img alt="<c:out value="${detail.pcListImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/mvVideoImgView.do?mvVideoSeqNo=<c:out value='${detail.mvVideoSeqNo }'/>&imgKinds=pcList" style="max-width: 100px; vertical-align: middle;">
					</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row">移动端图片</th>
				<td>
					<c:if test="${! empty detail.mobileListImgOrgFileNm}">
						<img alt="<c:out value="${detail.mobileListImgAlt }" escapeXml="false"/>" src="<blabProperty:value key="system.admin.path"/>/mvVideoImgView.do?mvVideoSeqNo=<c:out value='${detail.mvVideoSeqNo }'/>&imgKinds=mobileList" style="max-width: 100px; vertical-align: middle;">
					</c:if>
				</td>
			</tr>
			<tr>
				<th scope="row">新增人</th>
				<td>
					<c:out value="${detail.insPersonNm }"/>
				</td>
			</tr>
			<tr>
				<th scope="row">新增时间</th>
				<td>
					<c:out value="${detail.insDtm}" />
				</td>
			</tr>
			<tr>
				<th scope="row">状态</th>
				<td>
					<c:out value="${detail.useYnNm}" />
				</td>
			</tr>						
			<tbody>
			
			</tbody>
			
		</table>
		
		<div class="btn-module mgtS">
			<div class="leftGroup">
				<a href="javascript:;" id="btnList" class="btnStyle01" >列表</a>
			</div>		
			<div class="rightGroup">
				<a href="javascript:;" id="btnUpdate" class="btnStyle01" >修改</a>
				<a href="javascript:;" id="btnDelete" class="btnStyle02">删除</a>
			</div>
		</div>	
	</div>
	</article>
</section>
</form>
					
					
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#btnList").click(function() {
			$("#detailForm").prop("action","list.do");
			$("#detailForm").prop("method", "get");
			$("#detailForm").submit();
		});
		
		$("#btnUpdate").click(function() {
			$("#detailForm").prop("action","updateForm.do");
			$("#detailForm").prop("method", "post");
			$("#detailForm").submit();
		});
				
		$("#btnDelete").click(function() {
			if(confirm("Are you sure you want to delete it?")) {
				$("#detailForm").prop("action","doDelete.do");
				$("#detailForm").prop("method", "post");
				$("#detailForm").submit();
			}
		});
	});
	
</script>		
							
							
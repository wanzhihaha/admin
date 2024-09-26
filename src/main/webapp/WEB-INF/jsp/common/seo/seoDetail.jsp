<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<input type="hidden" id="metaSeqNo" name="metaSeqNo" value="<c:out value="${adminSeoVO.metaSeqNo }" />">
		
			<tr>
				<th scope="row">title</th>
				<td>
					<c:out value="${adminSeoVO.metaTitleNm }" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">meta description</th>
				<td>
					<c:out value="${adminSeoVO.metaDesc }" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">og image</th>
				<td>
					<img src="<blabProperty:value key="system.admin.path"/>/seoImgView.do?metaSeqNo=<c:out value="${adminSeoVO.metaSeqNo }" />" width="100" /><br />
				</td>
			</tr>
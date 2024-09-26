<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<input type="hidden" id="metaSeqNo" name="metaSeqNo" value="<c:out value="${adminSeoVO.metaSeqNo }" />">
		
			<tr>
				<th scope="row" class="tr100">title</th>
				<td>
					<c:out value="${adminSeoVO.metaTitleNm }" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">Description</th>
				<td>
					<c:out value="${adminSeoVO.metaDesc }" escapeXml="false"/>
				</td>
			</tr>
			<tr>
				<th scope="row">key words</th>
				<td>
					<c:out value="${adminSeoVO.keyWords }" escapeXml="false"/>
				</td>
			</tr>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<input type="hidden" id="metaSeqNo" name="metaSeqNo" value="<c:out value="${adminSeoVO.metaSeqNo }" />">
		
			<tr>
				<th scope="row">title</th>
				<td>
					<input id="metaTitleNm" name="metaTitleNm" type="text" placeholder="可不填写，保存后自动按照规则填充" class="inp-field widL" value="<c:out value="${adminSeoVO.metaTitleNm }" escapeXml="false"/>" maxlength="100" data-vbyte="100" data-vmsg="meta title"/>
					<br />※ 限制100字

				</td>
			</tr>			
			<tr>
				<th scope="row">Description</th>
				<td>
					<input id="metaDesc" name="metaDesc" dedtype="text" placeholder="可不填写，保存后自动按照规则填充" class="inp-field widL" value="<c:out value="${adminSeoVO.metaDesc }"  escapeXml="false"/>" maxlength="120" data-vbyte="160" data-vmsg="meta description"/>
					<br />※ 限制100字
				</td>
			</tr>
		<tr>
		<th scope="row">key words</th>
		<td>
			<input id="keyWords" name="keyWords" type="text" placeholder="可不填写，保存后自动按照规则填充" class="inp-field widL" value="<c:out value="${adminSeoVO.keyWords }"  escapeXml="false"/>" maxlength="120" data-vbyte="160" data-vmsg="keyWords"/>
			<br />※ 限制100字
		</td>
	</tr>
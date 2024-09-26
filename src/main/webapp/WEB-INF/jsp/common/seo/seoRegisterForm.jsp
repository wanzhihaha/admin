<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<input type="hidden" id="metaSeqNo" name="metaSeqNo" value="<c:out value="${adminSeoVO.metaSeqNo }" />">
		
			<tr>
				<th scope="row">title</th>
				<td>
					<input id="metaTitleNm" name="metaTitleNm" type="text" placeholder="Please enter the title." class="inp-field widL" value="<c:out value="${adminSeoVO.metaTitleNm }" escapeXml="false"/>" maxlength="70" data-vbyte="70" data-vmsg="meta title"/>
					<br />※ 65-70 words recommended for English / Within 35 characters (70 bytes) recommended for Korean, Chinese, and Japanese

				</td>
			</tr>			
			<tr>
				<th scope="row">meta description</th>
				<td>
					<input id="metaDesc" name="metaDesc" type="text" placeholder="Please enter a meta description." class="inp-field widL" value="<c:out value="${adminSeoVO.metaDesc }"  escapeXml="false"/>" maxlength="160" data-vbyte="160" data-vmsg="meta description"/>
					<br />※ 150-160 words recommended for English / Within 80 characters (160 bytes) recommended for Korean, Chinese, and Japanese
				</td>
			</tr>
			<tr>
				<th scope="row">og image
				<br />(1200px - 627px)
				</th>
				<td>
					<div class="textCont">
						<div style="margin-bottom: 7px;">
							<input type="file" id="ogFile" name="ogFile" class="inp-field widSM"/>
							<c:if test="${!empty adminSeoVO.ogImgOrgFileNm}">
								<span style="line-height: 28px;"><a href="<blabProperty:value key="system.admin.path"/>/seoDownload.do?metaSeqNo=<c:out value="${adminSeoVO.metaSeqNo }"  escapeXml="false"/>" ><font color="blue"><c:out value="${adminSeoVO.ogImgOrgFileNm}" /></font></a></span>
								<span><input type="checkbox" name="ogFileDel" id="ogFileDel" value="Y" /> <label for="ogFileDel">Delete</label></span>
							</c:if>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row" >The description<br /> of the meta tag</th>
				<td>				
					1. The values entered here should match the three fields: title, og:title, and twitter:title<br />
					2. The values entered here should match the three fields: meta description, og:description, and twitter:description<br />
					3. Choose an image that best represents the page (e.g Logo of Cello Square, Representative image of services, and etc.)<br />
					4. OG size: must be 1200px - 627px					
				</td>
			</tr>
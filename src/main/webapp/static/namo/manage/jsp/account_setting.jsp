<%@page contentType="text/html;charset=utf-8" %>
<%@include file = "./include/session_check.jsp"%>
<%@include file = "manager_util.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>Namo CrossEditor : Admin</title>
	<script type="text/javascript">var pe_zV="pe_Sh"; </script>
	<script type="text/javascript" src="../../lib/jquery-1.7.2.min.js"> </script>
	<script type="text/javascript">var ce$=namo$.noConflict(true); </script>
	<script type="text/javascript" src="../manage_common.js"> </script>
	<script type="text/javascript" language="javascript" src="../../js/namo_cengine.js"> </script>
	<script type="text/javascript" language="javascript" src="../manager.js"> </script>
	<link href="../css/common.css" rel="stylesheet" type="text/css">
</head>

<body>

<%@include file = "../include/top.html"%>

<div id="pe_aKy" class="pe_kK">	
	<table class="pe_xK">
	  <tr>
		<td class="pe_kK">
		
			<table id="Info">
				<tr>
					<td style="padding:0 0 0 10px;height:30px;text-align:left">
					<font style="font-size:14pt;color:#3e77c1;font-weight:bold;text-decoration:none;"><span id="pe_Gg"></span></font></td>
					<td id="InfoText"><span id="pe_zg"></span></td>
				</tr>
				<tr>
					<td colspan="2"><img id="pe_Iu" src="../images/title_line.jpg" alt="" /></td>
				</tr>
			</table>
		
		</td>
	  </tr>
	  <tr>
		<td class="pe_kK">
			
				<form method="post" id="pe_aOD" action="account_proc.jsp" onsubmit="return pe_ai(this);">
				<table class="pe_qv" >
				  <tr>
					<td>

						<table class="pe_eX">
						  <tr><td class="pe_iq" colspan="3"></td></tr>
						</table>
						 
						<table class="pe_eX" >
						  <tr>
							<td class="pe_eH">&nbsp;&nbsp;&nbsp;&nbsp;<b><span id="pe_EW"></span></b></td>
							<td class="pe_eP"></td>
							<td class="pe_fh">
								<input type="hidden" name="u_id" id="u_id" value="<%=detectXSSEx(session.getAttribute("memId").toString())%>" autocomplete="off"/>
								<input type="password" name="passwd" id="passwd" value="" class="pe_qq" autocomplete="off"/>
							</td>
						  </tr>
						  <tr>
							<td class="pe_eR" colspan="3"></td>
						  </tr>
						  <tr>
							<td class="pe_eH">&nbsp;&nbsp;&nbsp;&nbsp;<b><span id="pe_Nm"></span></b></td>
							<td class="pe_eP"></td>
							<td class="pe_fh">
								<input type="password" name="newPasswd" id="newPasswd" value="" class="pe_qq" autocomplete="off"/>
							</td>
						  </tr>
						  <tr>
							<td class="pe_eR" colspan="3"></td>
						  </tr>
						  <tr>
							<td class="pe_eH">&nbsp;&nbsp;&nbsp;&nbsp;<b><span id="pe_Nn"></span></b></td>
							<td class="pe_eP"></td>
							<td class="pe_fh">
								<input type="password" name="newPasswdCheck" id="newPasswdCheck" value="" class="pe_qq" autocomplete="off"/>
							</td>
						  </tr>
						</table>
					
						<table class="pe_eX">
						  <tr><td class="pe_iq" colspan="3"></td></tr>
						</table>
								
					</td>
				  </tr>
				  <tr id="pe_VX">
					<td id="pe_VY">
						<ul style="margin:0 auto;width:170px;">
							<li class="pe_iy">
								<input type="submit" id="pe_Sj" value="" class="pe_ie pe_hy" style="width:66px;height:26px;" />
							</li>
							<li class="pe_iy"><input type="button" id="pe_Db" value="" class="pe_ie pe_hy" style="width:66px;height:26px;"></li>
						</ul>
					</td>
				  </tr>
				</table>
				</form>
		
		</td>
	  </tr>
	</table>

</div>

<%@include file = "../include/bottom.html"%>

</body>
<script>var webPageKind='<%=detectXSSEx(session.getAttribute("webPageKind").toString())%>';topInit();pe_aG(); </script>

</html>

	
	


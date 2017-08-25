<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
       <%@ taglib prefix="shiro"
    uri="http://shiro.apache.org/tags" %>
<head>
<link href="<s:url value="resources/layout.css"/>"rel="stylesheet" type="text/css"/>
</head>
    
<div id="fedora-nav">
<div id="fedora-side-nav-label">Menu :</div>	
<table class="bar-status" width="750"  border="1" cellspacing="1" cellpadding="2" align="left" id="bar-status">
<tr>
	<td align="center" bgcolor="#555" width="14%" >
		<b>
		<font face="arial bolder" size="1" color="#ffffff">
			<b><a href="<s:url action="listTrusted"/>">Trusted</a></b>
		</font>
		</b>
	</td>
	<td align="center" bgcolor="#555" width="14%" >
		<b>
		<font face="arial bolder" size="1" color="#ffffff">
			<b><a href="<s:url action="listBlocked"/>">Blocked</a></b>
		</font>
		</b>
	</td>
	<td align="center" bgcolor="#555" width="14%" >
		<b>
		<font face="arial bolder" size="1" color="#ffffff">
			<b><a href="<s:url action="listQuota"/>">Quotas</a></b>
		</font>
		</b>
	</td>
	<td align="center" bgcolor="#555" width="14%">
		<font face="arial bolder" size="1" color="#ffffff">
			<a href="<s:url action="listDialplan"/>">Dialplan</a>
		</font>
	</td>
	<shiro:hasAnyRoles name="unlim-user,lim-manager,unlim-manager,admin">
	<td align="center" bgcolor="#555" width="14%">
		<font face="arial bolder" size="1" color="#ffffff">
			<a href="<s:url action="listDRRules"/>">DR Rules</a>
		</font>
	</td>
	<td align="center" bgcolor="#555" width="14%">
		<font face="arial bolder" size="1" color="#ffffff">
			<a href="<s:url action="listDRGateways"/>">DR Gateways</a>
		</font>
	</td>
	<td align="center" bgcolor="#555" width="14%">
		<font face="arial bolder" size="1" color="#ffffff">
			<a href="<s:url action="listDRGroups"/>">DR Groups</a>
		</font>
	</td>
	</shiro:hasAnyRoles>
	<shiro:hasRole name="admin">
	<td align="center" bgcolor="#555" width="14%">
		<font face="arial bolder" size="1" color="#ffffff">
			<a href="<s:url action="listUsers"/>">Create account</a>
		</font>
	</td>
	</shiro:hasRole>
	<td align="center" bgcolor="#555" width="14%">
		<font face="arial bolder" size="1" color="#ffffff">
			<a href="<s:url action="doLogout"/>">Logout</a>
		</font>
	</td>
</tr>
</table>
</div>
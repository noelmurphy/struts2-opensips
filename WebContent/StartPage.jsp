<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro"
    uri="http://shiro.apache.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">    
<head>
<link rel="stylesheet" href="resources/layout.css">
<title>Menu</title>
</head>

<div id="fedora-header">
<div id="fedora-header-logo">
<!--  Welltel Header logo -->
<%@ include file="/WEB-INF/views/headerscript.jsp" %>
</div></div>


<!-- content BEGIN -->
<div id="fedora-middle-two">&nbsp;</div>

<shiro:authenticated>
<div id="fedora-side-left">
<div id="fedora-side-nav-label">Menu :</div>	
<ul id="fedora-side-nav">
	<li><a href="<s:url action="listTrusted"/>">Trusted</a></li>
	<li><a href="<s:url action="listBlocked"/>">Blocked</a></li>
	<li><a href="<s:url action="listQuota"/>">Quotas</a></li>
	<li><a href="<s:url action="listDialplan"/>">Dialplan</a></li>
	<shiro:hasAnyRoles name="unlim-user,lim-manager,unlim-manager,admin">
	<li><a href="<s:url action="listDRRules"/>">DR Rules</a></li>
	<li><a href="<s:url action="listDRGateways"/>">DR Gateways</a></li>
	<li><a href="<s:url action="listDRGroups"/>">DR Groups</a></li>
	</shiro:hasAnyRoles> 
	<shiro:hasRole name="admin"> 
	<li><a href="<s:url action="listUsers"/>">Create user</a></li>
	</shiro:hasRole>
	<li><s:property value="email" /></li>
	<li><a href="<s:url action="doLogout"/>">Logout</a></li>
</ul>
</div>
	</shiro:authenticated>
	  <shiro:guest>
 <p>You must be logged in to view this page.</p>
 </shiro:guest>


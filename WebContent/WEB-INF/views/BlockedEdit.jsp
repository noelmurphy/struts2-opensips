<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="shiro"
    uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="<s:url value="resources/layout.css"/>"rel="stylesheet" type="text/css"/>
<title>Blocked</title>
<style type="text/css">
      .odd td { background-color: #fff; }
      .even td { background-color: #eee; }
    </style>
</head>
<body>
             <shiro:authenticated>
<div id="fedora-header">
<div id="fedora-header-logo">
<!--  Welltel Header logo -->
<%@ include file="headerscript.jsp" %>
</div></div>

<!-- Left Side Menu Bar -->
<%@ include file="menu.jsp" %>

<div id="fedora-middle-two">&nbsp;</div>
<div class="fedora-corner-tr">&nbsp;</div>
<div class="fedora-corner-tl">&nbsp;</div>

<div id="fedora-content">&nbsp;

<h2>Blocked</h2>

<table>
		<s:form action="updateBlocked" method="post" cssClass="form-horizontal" theme="simple">
		<s:push value="blocked">
		<tr>
	  		<th align="left">&nbsp;&nbsp;</th>
	    	<th align="left">Username&nbsp;&nbsp;</th>
	    	<th align="left">Domain&nbsp;&nbsp;</th>
	    	<th align="left">Group&nbsp;&nbsp;</th>
	    	<!--  <th align="left">Modified&nbsp;&nbsp;</th> -->
	    </tr>
		<tr>
			<td align="left" width="5%">
				<span style="font-weight: bold; font-size: 12.0pt; font-family: Times New Roman">
					<!-- <s:hidden name="id" value="%{id}"></s:hidden> -->
					<s:hidden name="id" value="%{id}"></s:hidden>
					<!-- <s:textfield name="id" value="%{id}"></s:textfield> -->
					Update&nbsp;&nbsp;
				</span>
			</td>
			<td align="left" width="5%">
				<span style="font-size: 10.0pt; font-family: Times New Roman">
					<s:textfield name="username"></s:textfield>
					<s:fielderror fieldName="username" key="blocked.username"/>
				</span>
			</td>
			<td align="left" width="5%">
				<span style="font-size: 10.0pt; font-family: Times New Roman">
					<s:textfield name="domain" ></s:textfield>
					<s:fielderror fieldName="domain" key="blocked.domain" />
				</span>
			</td>
			<td align="left" width="5%">
				<span style="font-size: 10.0pt; font-family: Times New Roman">
					<s:textfield name="grp"></s:textfield>
					<s:fielderror fieldName="grp" key="blocked.grp" />
				</span>
			</td>
			<!--
			<td align="left" width="5%">
				<span style="font-size: 10.0pt; font-family: Times New Roman">
					<s:textfield name="last_modified"></s:textfield>
					<s:fielderror fieldName="last_modified" key="blocked.last_modified" />
				</span>
			</td>
			-->
			<td align="left" width="5%">
				<span style="font-size: 10.0pt; font-family: Times New Roman">
					<s:submit ></s:submit>
				</span>
			</td>
		</tr>
		</s:push>
		</s:form>	
</table>

</div>
 </shiro:authenticated>
   <shiro:guest>
 <p>You must be logged in to view this page. Please  <a href="Login.jsp">Login</a></p>
 </shiro:guest>
</body>
</html>
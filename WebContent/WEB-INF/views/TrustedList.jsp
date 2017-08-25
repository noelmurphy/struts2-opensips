<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="shiro"
    uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link href="<s:url value="resources/layout.css"/>"rel="stylesheet" type="text/css"/>
<title>Trusted</title>
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

<h2>
Trusted
</h2>

<s:bean name="net.codejava.framework.bo.RowComparator" var="rowComparator" />
<s:bean name="net.codejava.framework.bo.Src_IpComparator" var="srcIpComparator" />

<!--  <h2>Add Trusted</h2> -->

<table>
<shiro:hasAnyRoles name="lim-manager,unlim-manager,admin">
	<s:form action="saveOrUpdateTrusted" method="post" cssClass="form-horizontal" theme="simple">
	<s:push value="trusted">
	<tr>
  		<th align="left">&nbsp;&nbsp;</th>
    	<th align="left">SRC IP&nbsp;&nbsp;</th>
    	<th align="left">Protocol&nbsp;&nbsp;</th>
    	<th align="left">From Pattern&nbsp;&nbsp;</th>
    	<th align="left">TAG&nbsp;&nbsp;</th>
    </tr>
	<tr>
		<td align="left" width="5%">
			<span style="font-weight: bold; font-size: 10.0pt; font-family: Times New Roman">
				<!-- <s:hidden name="id"></s:hidden> -->
				Insert&nbsp;&nbsp;
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="src_ip"></s:textfield>
				<s:fielderror fieldName="src_ip" key="trusted.src_ip"/>
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="proto" ></s:textfield>
				<s:fielderror fieldName="proto" key="trusted.proto" />
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="from_pattern"></s:textfield>
				<s:fielderror fieldName="from_pattern" />
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="tag"></s:textfield>
				<s:fielderror fieldName="tag" key="trusted.tag"/>
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">	
				
				<s:if test="listActivate.get(0).getValue().equals(\"0\")">
				<s:submit ></s:submit>
				</s:if>
				<s:if test="request != null || listActivate.get(0).getValue().equals(\"0\")">
				<s:submit action="reloadTrusted" value="ACTIVATE" />
				</s:if>
			</span>
		</td>
		
	</tr>
	</s:push>
	</s:form>
</shiro:hasAnyRoles>
</table>

<s:if test="listTrusted.size() > 0">
<!-- content BEGIN -->
	<table>
	  <tr>
  	<th align="left">Row&nbsp;&nbsp;</th>
    <th align="left">SRC IP&nbsp;&nbsp;</th>
    <th align="left">Protocol&nbsp;&nbsp;</th>
    <th align="left">From Pattern&nbsp;&nbsp;</th>
    <th align="left">TAG&nbsp;&nbsp;</th>
    <!-- // -->
    <th align="left">&nbsp;&nbsp;</th>
	<th align="left">&nbsp;&nbsp;</th>
	<!-- // -->
    </tr>
    <s:sort comparator="#srcIpComparator" source="listTrusted">
		<s:iterator value="listTrusted" status="rowstatus">
				<tr <s:if test="#rowstatus.odd">class="odd"</s:if><s:else>class="even"</s:else>>
				<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="id" />
     				</span>
     			</td>
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="src_ip" />
     				</span>
     			</td>
     			
				<td align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="proto" />
     				</span>
     			</td>
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="from_pattern" />
     				</span>
     			</td>
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="tag" />
     				</span>
     			</td>
     			<!-- // -->
     			<td align="left" width="5%">
     				<s:url id="editURL" action="editTrusted">
						<s:param name="id" value="%{id}"></s:param>
					</s:url> 
					<s:a href="%{editURL}">Edit</s:a>
				</td>
				<td align="left" width="5%">
					<s:url id="deleteURL" action="deleteTrusted">
						<s:param name="id" value="%{id}"></s:param>
					</s:url> 
					<s:a href="%{deleteURL}">Delete</s:a>
				</td>
				<!-- // -->
				</tr>
		</s:iterator>
		</s:sort>
	</table>
</s:if>

</div>
</shiro:authenticated>
  <shiro:guest>
 <p>You must be logged in to view this page. Please  <a href="Login.jsp">Login</a></p>
 </shiro:guest>
</body>
</html>
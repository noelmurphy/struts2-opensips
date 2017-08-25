<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

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

<h2>Add Trusted</h2>
<table>
	<s:form action="listTrusted" method="post" cssClass="form-horizontal" theme="simple">	
	<tr>
  		<th align="left">Row&nbsp;&nbsp;</th>
    	<th align="left">SRC IP&nbsp;&nbsp;</th>
    	<th align="left">Protocol&nbsp;&nbsp;</th>
    	<th align="left">From Pattern&nbsp;&nbsp;</th>
    	<th align="left">TAG&nbsp;&nbsp;</th>
    </tr>
   <!-- <s:push value="trusted"> -->
	<tr>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="id" id="id" value="" ></s:textfield>
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="src_ip" id="src_ip" value=""></s:textfield>
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="proto" id="proto" value="" ></s:textfield>
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="from_pattern" id="from_pattern" value="" ></s:textfield>
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="tag" id="tag" value="" ></s:textfield>
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:submit ></s:submit>
			</span>
		</td>
	</tr>
	<!-- </s:push> -->
	</s:form>
</table>


<!-- content BEGIN -->
	<!-- <s:if test="listTrusted.size() > 0">  -->
	<table>
	  <tr>
  	<th align="left">Row&nbsp;&nbsp;</th>
    <th align="left">SRC IP&nbsp;&nbsp;</th>
    <th align="left">Protocol&nbsp;&nbsp;</th>
    <th align="left">From Pattern&nbsp;&nbsp;</th>
    <th align="left">TAG&nbsp;&nbsp;</th>
    <!--
    <th align="left">Edit&nbsp;&nbsp;</th>
	<th align="left">Delete&nbsp;&nbsp;</th>
	-->
    </tr>
    
		<s:iterator value="listTrusted" status="rowstatus">
				<tr <s:if test="#rowstatus.odd">class="odd"</s:if>
					<s:else>class="even"</s:else>
				>
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
     			<!--
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
				-->
				</tr>
		</s:iterator>
	</table>
	<!-- </s:if> -->
</div>
</body>
</html>

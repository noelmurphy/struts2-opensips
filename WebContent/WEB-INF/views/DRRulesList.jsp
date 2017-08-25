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
<title>DR Rules</title>
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

<h2>DR Rules</h2>

<s:bean name="net.codejava.framework.bo.RowComparator" var="rowComparator" />
<s:bean name="net.codejava.framework.bo.Src_IpComparator" var="srcIpComparator" />

<table>
	<shiro:hasAnyRoles name="unlim-manager,admin">
	<s:form action="saveOrUpdateDRRules" method="post" cssClass="form-horizontal" theme="simple">
	<s:push value="drrules">
	<tr>
  		<th align="left">&nbsp;&nbsp;</th>
    	<th align="left">groupid&nbsp;&nbsp;</th>
    	<th align="left">prefix&nbsp;&nbsp;</th>
    	<th align="left">timerec&nbsp;&nbsp;</th>
    	<th align="left">priority&nbsp;&nbsp;</th>
    	<th align="left">routeid&nbsp;&nbsp;</th>
    	<th align="left">gwlist&nbsp;&nbsp;</th>
    	<th align="left">description&nbsp;&nbsp;</th>
    </tr>
	<tr>
		<td align="left" width="5%">
			<span style="font-weight: bold; font-size: 12.0pt; font-family: Times New Roman">
				Insert&nbsp;&nbsp;
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="groupid"></s:textfield>
				<s:fielderror fieldName="groupid" key="drrules.groupid"/>
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="prefix" ></s:textfield>
				<s:fielderror fieldName="prefix" key="drrules.prefix" />
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="timerec"></s:textfield>
				<s:fielderror fieldName="timerec" />
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="priority"></s:textfield>
				<s:fielderror fieldName="priority" key="drrules.priority"/>
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="routeid" ></s:textfield>
				<s:fielderror fieldName="routeid" key="drrules.routeid" />
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="gwlist"></s:textfield>
				<s:fielderror fieldName="gwlist" />
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="description"></s:textfield>
				<s:fielderror fieldName="description" key="drrules.description"/>
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:if test="listActivate.get(0).getValue().equals(\"0\")">
				<s:submit ></s:submit>
				</s:if>
				<s:if test="request != null || listActivate.get(0).getValue().equals(\"1\")">
				<s:submit action="drRulesReload" value="ACTIVATE" />
				</s:if>
			</span>
		</td>
	</tr>
	</s:push>
	</s:form>
	</shiro:hasAnyRoles>
</table>

<s:if test="listDRRules.size() > 0">
<!-- content BEGIN -->
	<table>
	  <tr>
  	<th align="left">Row&nbsp;&nbsp;</th>
    <th align="left">groupid&nbsp;&nbsp;</th>
    <th align="left">prefix&nbsp;&nbsp;</th>
    <th align="left">timerec&nbsp;&nbsp;</th>
    <th align="left">priority&nbsp;&nbsp;</th>
    <th align="left">routeid&nbsp;&nbsp;</th>
    <th align="left">gwlist&nbsp;&nbsp;</th>
    <th align="left">descriptiony&nbsp;&nbsp;</th>
    <!-- // -->
    <th align="left">&nbsp;&nbsp;</th>
	<th align="left">&nbsp;&nbsp;</th>
	<!-- // -->
    </tr>
    <s:sort comparator="#srcIpComparator" source="listDRRules">
		<s:iterator value="listDRRules" status="rowstatus">
				<tr <s:if test="#rowstatus.odd">class="odd"</s:if><s:else>class="even"</s:else>>
				<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="ruleid" />
     				</span>
     			</td>
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="groupid" />
     				</span>
     			</td>
				<td align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="prefix" />
     				</span>
     			</td>
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="timerec" />
     				</span>
     			</td>
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="priority" />
     				</span>
     			</td>
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="routeid" />
     				</span>
     			</td>
				<td align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="gwlist" />
     				</span>
     			</td>
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="descriptiony" />
     				</span>
     			</td>
     			<!-- // -->
     			<td align="left" width="5%">
     				<s:url id="editURL" action="editDRRules">
						<s:param name="ruleid" value="%{ruleid}"></s:param>
					</s:url> 
					<s:a href="%{editURL}">Edit</s:a>
				</td>
				<td align="left" width="5%">
					<s:url id="deleteURL" action="deleteDRRules">
						<s:param name="ruleid" value="%{ruleid}"></s:param>
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
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
<title>DR Gateways</title>
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

<h2>DR Gateways</h2>

<s:bean name="net.codejava.framework.bo.RowComparator" var="rowComparator" />
<s:bean name="net.codejava.framework.bo.Src_IpComparator" var="srcIpComparator" />

<table>
	<shiro:hasAnyRoles name="unlim-manager,admin">
	<s:form action="saveOrUpdateDRGateways" method="post" cssClass="form-horizontal" theme="simple">
	<s:push value="drgateways">
	<tr>
  		<th align="left">&nbsp;&nbsp;</th>
    	<th align="left">type&nbsp;&nbsp;</th>
    	<th align="left">address&nbsp;&nbsp;</th>
    	<th align="left">strip&nbsp;&nbsp;</th>
    	<th align="left">pri_prefix&nbsp;&nbsp;</th>
    	<th align="left">attrs&nbsp;&nbsp;</th>
    	<th align="left">description&nbsp;&nbsp;</th>
    </tr>
	<tr>
		<td align="left" width="5%">
			<span style="font-weight: bold; font-size: 12.0pt; font-family: Times New Roman">
				<!-- <s:hidden name="gwid"></s:hidden> -->
				Insert&nbsp;&nbsp;
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="type"></s:textfield>
				<s:fielderror fieldName="type" key="drgateways.type"/>
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="address" ></s:textfield>
				<s:fielderror fieldName="address" key="drgateways.address" />
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="strip"></s:textfield>
				<s:fielderror fieldName="strip" key="drgateways.strip" />
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="pri_prefix"></s:textfield>
				<s:fielderror fieldName="pri_prefix" key="drgateways.pri_prefix" />
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="attrs"></s:textfield>
				<s:fielderror fieldName="attrs" key="drgateways.attrs" />
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="description"></s:textfield>
				<s:fielderror fieldName="description" key="drgateways.description" />
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:if test="listActivate.get(0).getValue().equals(\"0\")">
				<s:submit ></s:submit>
				</s:if>
				<s:if test="request != null || listActivate.get(0).getValue().equals(\"1\")">
				<s:submit action="drGatewaysReload" value="ACTIVATE" />
				</s:if>
			</span>
		</td>
	</tr>
	</s:push>
	</s:form>
	</shiro:hasAnyRoles>
</table>

<s:if test="listDRGateways.size() > 0">
<!-- content BEGIN -->
	<table>
	  <tr>
  		<th align="left">Row&nbsp;&nbsp;</th>
    	<th align="left">type&nbsp;&nbsp;</th>
    	<th align="left">address&nbsp;&nbsp;</th>
    	<th align="left">strip&nbsp;&nbsp;</th>
    	<th align="left">pri_prefix&nbsp;&nbsp;</th>
    	<th align="left">attrs&nbsp;&nbsp;</th>
    	<th align="left">description&nbsp;&nbsp;</th>
    <!-- // -->
    <th align="left">&nbsp;&nbsp;</th>
	<th align="left">&nbsp;&nbsp;</th>
	<!-- // -->
    </tr>
    <s:sort comparator="#srcIpComparator" source="listDRGateways">
		<s:iterator value="listDRGateways" status="rowstatus">
				<tr <s:if test="#rowstatus.odd">class="odd"</s:if><s:else>class="even"</s:else>>
				<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="gwid" />
     				</span>
     			</td>
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="type" />
     				</span>
     			</td>
				<td align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="address" />
     				</span>
     			</td>
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="strip" />
     				</span>
     			</td>
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="pri_prefix" />
     				</span>
     			</td>
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="attrs" />
     				</span>
     			</td>
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="description" />
     				</span>
     			</td>
     			<!-- // -->
     			<td align="left" width="5%">
     				<s:url id="editURL" action="editDRGateways">
						<s:param name="gwid" value="%{gwid}"></s:param>
					</s:url> 
					<s:a href="%{editURL}">Edit</s:a>
				</td>
				<td align="left" width="5%">
					<s:url id="deleteURL" action="deleteDRGateways">
						<s:param name="gwid" value="%{gwid}"></s:param>
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
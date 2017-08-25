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
<title>Quota</title>
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

<h2>Quota</h2>

<s:bean name="net.codejava.framework.bo.RowComparator" var="rowComparator" />
<s:bean name="net.codejava.framework.bo.Src_IpComparator" var="srcIpComparator" />

<table>
	<shiro:hasAnyRoles name="lim-manager,unlim-manager,admin">
	<s:form action="saveOrUpdateQuota" method="post" cssClass="form-horizontal" theme="simple">
	<s:push value="quota">
	<tr>
  		<th align="left">&nbsp;&nbsp;</th>
    	<!--  <th align="left">reseller_id&nbsp;&nbsp;</th> -->
    	<!--  <th align="left">datasource&nbsp;&nbsp;</th> -->
    	<th align="left">account&nbsp;&nbsp;</th>
    	<!--  <th align="left">domain&nbsp;&nbsp;</th> -->
    	<th align="left">quota&nbsp;&nbsp;</th>
    	<th align="left">Over 80%&nbsp;&nbsp;</th>
    	<th align="left">blocked&nbsp;&nbsp;</th>
    	<!--  <th align="left">notified&nbsp;&nbsp;</th> -->
    	<th align="left">calls&nbsp;&nbsp;</th>
    	<!--  <th align="left">duration&nbsp;&nbsp;</th> -->
    	<th align="left">cost&nbsp;&nbsp;</th>
    	<!--  <th align="left">traffic&nbsp;&nbsp;</th> -->
    	<!--  <th align="left">change_date&nbsp;&nbsp;</th> -->
    </tr>
	<tr>
		<td align="left" width="5%">
			<span style="font-weight: bold; font-size: 12.0pt; font-family: Times New Roman">
				<!-- <s:hidden name="id"></s:hidden> -->
				Insert&nbsp;&nbsp;
			</span>
		</td>
		<!-- 
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="reseller_id"></s:textfield>
				<s:fielderror fieldName="reseller_id" key="quota.reseller_id"/>
			</span>
		</td>
		-->
		<!--
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="datasource" ></s:textfield>
				<s:fielderror fieldName="datasource" key="quota.datasource" />
			</span>
		</td>
		-->
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="account"></s:textfield>
				<s:fielderror fieldName="account" key="quota.account" />
			</span>
		</td>
		<!--
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="domain"></s:textfield>
				<s:fielderror fieldName="domain" key="quota.domain" />
			</span>
		</td>
		-->
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="quota"></s:textfield>
				<s:fielderror fieldName="quota" key="quota.quota"/>
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="percent" ></s:textfield>
				<s:fielderror fieldName="percent" key="quota.percent" />
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="blocked" ></s:textfield>
				<s:fielderror fieldName="blocked" key="quota.blocked" />
			</span>
		</td>
		<!--
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="notified"></s:textfield>
				<s:fielderror fieldName="notified" key="quota.notified" />
			</span>
		</td>
		-->
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="calls"></s:textfield>
				<s:fielderror fieldName="calls" key="quota.calls" />
			</span>
		</td>
		<!--
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="duration"></s:textfield>
				<s:fielderror fieldName="duration" key="quota.duration" />
			</span>
		</td>
		-->
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="cost"></s:textfield>
				<s:fielderror fieldName="cost" key="quota.cost" />
			</span>
		</td>
		<!--
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="traffic" ></s:textfield>
				<s:fielderror fieldName="traffic" key="quota.traffic" />
			</span>
		</td>
		-->
		<!--
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="change_date"></s:textfield>
				<s:fielderror fieldName="change_date" key="quota.change_date" />
			</span>
		</td>
		-->
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:if test="listActivate.get(0).getValue().equals(\"0\")">
				<s:submit ></s:submit>
				</s:if>
				<s:if test="request != null || listActivate.get(0).getValue().equals(\"1\")">
				<s:submit action="quotaReload" value="ACTIVATE" />
				</s:if>
			</span>
		</td>
	</tr>
	</s:push>
	</s:form>
	</shiro:hasAnyRoles>
</table>

<s:if test="listQuota.size() > 0">
<!-- content BEGIN -->
	<table>
	  <tr>
  		<th align="left">Row&nbsp;&nbsp;</th>
    	<!--  <th align="left">reseller_id&nbsp;&nbsp;</th> -->
    	<!--  <th align="left">datasource&nbsp;&nbsp;</th> -->
    	<th align="left">account&nbsp;&nbsp;</th>
    	<!--  <th align="left">domain&nbsp;&nbsp;</th> -->
    	<th align="left">quota&nbsp;&nbsp;</th>
    	<th align="left">Over 80%&nbsp;&nbsp;</th>
    	<th align="left">blocked&nbsp;&nbsp;</th>
    	<!--  <th align="left">notified&nbsp;&nbsp;</th> -->
    	<th align="left">calls&nbsp;&nbsp;</th>
    	<!--  <th align="left">duration&nbsp;&nbsp;</th> -->
    	<th align="left">cost&nbsp;&nbsp;</th>
    	<!--  <th align="left">traffic&nbsp;&nbsp;</th> -->
    	<!--  <th align="left">change_date&nbsp;&nbsp;</th> -->
    	
    <!-- // -->
    <th align="left">&nbsp;&nbsp;</th>
	<th align="left">&nbsp;&nbsp;</th>
	<!-- // -->
    </tr>
    <s:sort comparator="#srcIpComparator" source="listQuota">
		<s:iterator value="listQuota" status="rowstatus">
				<tr <s:if test="#rowstatus.odd">class="odd"</s:if><s:else>class="even"</s:else>>
				<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="id" />
     				</span>
     			</td>
     			<!--
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="reseller_id" />
     				</span>
     			</td>
     			-->
     			<!--
				<td align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="datasource" />
     				</span>
     			</td>
     			-->
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="account" />
     				</span>
     			</td>
     			<!--
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="domain" />
     				</span>
     			</td>
     			-->
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="quota" />
     				</span>
     			</td>
     			<td align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="percent" />
     				</span>
     			</td>
				<td align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="blocked" />
     				</span>
     			</td>
     			<!--
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="notified" />
     				</span>
     			</td>
     			-->
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="calls" />
     				</span>
     			</td>
     			<!--
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="duration" />
     				</span>
     			</td>
     			-->
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="cost" />
     				</span>
     			</td>
     			<!--
				<td align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="traffic" />
     				</span>
     			</td>
     			-->
     			<!--
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="change_date" />
     				</span>
     			</td>
     			-->
     			<!-- // -->
     			<td align="left" width="5%">
     				<s:url id="editURL" action="editQuota">
						<s:param name="id" value="%{id}"></s:param>
					</s:url> 
					<s:a href="%{editURL}">Edit</s:a>
				</td>
				<td align="left" width="5%">
					<s:url id="deleteURL" action="deleteQuota">
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
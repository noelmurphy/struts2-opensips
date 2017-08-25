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
<title>Dialplan</title>
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
Dialplan
</h2>

<s:bean name="net.codejava.framework.bo.RowComparator" var="rowComparator" />
<s:bean name="net.codejava.framework.bo.Src_IpComparator" var="srcIpComparator" />

<!--  <h2>Add Dialplan</h2> -->
<table>
<s:form action="searchDialplan" method="post" cssClass="form-horizontal" theme="simple">
		<s:push value="dialplan">
		<tr>
  		<th align="left">&nbsp;&nbsp;</th>
  		<!--  <th align="left">Id&nbsp;&nbsp;</th> -->
    	<th align="left">Dialplan Id&nbsp;&nbsp;</th>
    	<th align="left">Priority&nbsp;&nbsp;</th>
    	<th align="left">Calling Line Number&nbsp;&nbsp;</th>
    	<th align="left">Subaddresses&nbsp;&nbsp;</th>
    	<th align="left">Replacement&nbsp;&nbsp;</th>
    	<th align="left">Attributes&nbsp;&nbsp;</th>
    </tr>
	<tr>
		<td align="left" width="5%">
			<span style="font-weight: bold; font-size: 12.0pt; font-family: Times New Roman">
				<!-- <s:hidden name="id"></s:hidden> -->
				Search&nbsp;&nbsp;
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="dpid"></s:textfield>
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="pr" ></s:textfield>
			</span>
		</td>
		<!--
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="match_po"></s:textfield>
				<s:fielderror fieldName="match_po" key="dialplan.match_po" />
			</span>
		</td>
		-->
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="match_exp"></s:textfield>
			</span>
		</td>
		<!--
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="match_len"></s:textfield>
				<s:fielderror fieldName="match_len" key="dialplan.match_len" />
			</span>
		</td>
		-->
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="subst_exp"></s:textfield>
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="repl_exp"></s:textfield>
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="attrs"></s:textfield>
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:submit ></s:submit>
			</span>
		</td>
	</tr>
	</s:push>
		</s:form>

<shiro:hasAnyRoles name="lim-manager,unlim-manager,admin">

	<s:form action="saveOrUpdateDialplan" method="post" cssClass="form-horizontal" theme="simple">
	<s:push value="dialplan">
	<tr>
		<td align="left" width="5%">
			<span style="font-weight: bold; font-size: 12.0pt; font-family: Times New Roman">
				<!-- <s:hidden name="id"></s:hidden> -->
				Insert&nbsp;&nbsp;
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="dpid"></s:textfield>
				<s:fielderror fieldName="dpid" key="dialplan.dpid"/>
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="pr" ></s:textfield>
				<s:fielderror fieldName="pr" key="dialplan.pr" />
			</span>
		</td>
		<!--
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="match_po"></s:textfield>
				<s:fielderror fieldName="match_po" key="dialplan.match_po" />
			</span>
		</td>
		-->
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="match_exp"></s:textfield>
				<s:fielderror fieldName="match_exp" key="dialplan.match_exp" />
			</span>
		</td>
		<!--
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="match_len"></s:textfield>
				<s:fielderror fieldName="match_len" key="dialplan.match_len" />
			</span>
		</td>
		-->
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="subst_exp"></s:textfield>
				<s:fielderror fieldName="subst_exp" key="dialplan.subst_exp" />
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="repl_exp"></s:textfield>
				<s:fielderror fieldName="repl_exp" key="dialplan.repl_exp" />
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:textfield name="attrs"></s:textfield>
				<s:fielderror fieldName="attrs" key="dialplan.attrs"/>
			</span>
		</td>
		<td align="left" width="5%">
			<span style="font-size: 10.0pt; font-family: Times New Roman">
				<s:if test="listActivate.get(0).getValue().equals(\"0\")">
				<s:submit ></s:submit>
				</s:if>
				<s:if test="request != null || listActivate.get(0).getValue().equals(\"1\")">			
				<s:submit action="dialplanReload" value="ACTIVATE" />
				</s:if>
			</span>
		</td>
	</tr>
	</s:push>
	</s:form>
	</shiro:hasAnyRoles>
</table>

<s:if test="listDialplan.size() > 0">
<!-- content BEGIN -->
	<table>
	  <tr>
  	<th align="left">Row&nbsp;&nbsp;</th>
   <th align="left">Dialplan Id&nbsp;&nbsp;</th>
    	<th align="left">Priority&nbsp;&nbsp;</th>
    	<!-- <th align="left">Reg&nbsp;&nbsp;</th> -->
    	<th align="left">Match Pattern&nbsp;&nbsp;</th>
    	<!-- <th align="left">Len&nbsp;&nbsp;</th> -->
    	<th align="left">Submatch Pattern&nbsp;&nbsp;</th>
    	<th align="left">Replacement&nbsp;&nbsp;</th>
    	<th align="left">Attributes&nbsp;&nbsp;</th>
    <!-- // -->
    <th align="left">&nbsp;&nbsp;</th>
	<th align="left">&nbsp;&nbsp;</th>
	<!-- // -->
    </tr>
    <s:sort comparator="#srcIpComparator" source="listDialplan">
		<s:iterator value="listDialplan" status="rowstatus">
				<tr <s:if test="#rowstatus.odd">class="odd"</s:if><s:else>class="even"</s:else>>
				<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="id" />
     				</span>
     			</td>
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="dpid" />
     				</span>
     			</td>
				<td align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="pr" />
     				</span>
     			</td>
     			<!--
     			<td align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="match_op" />
     				</span>
     			</td>
     			-->
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="match_exp" />
     				</span>
     			</td>
     			<!--
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="match_len" />
     				</span>
     			</td>
     			-->
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="subst_exp" />
     				</span>
     			</td>
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="repl_exp" />
     				</span>
     			</td>
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="attrs" />
     				</span>
     			</td>
     			<!-- // -->
     			<td align="left" width="5%">
     				<s:url id="editURL" action="editDialplan">
						<s:param name="id" value="%{id}"></s:param>
					</s:url> 
					<s:a href="%{editURL}">Edit</s:a>
				</td>
				<td align="left" width="5%">
					<s:url id="deleteURL" action="deleteDialplan">
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
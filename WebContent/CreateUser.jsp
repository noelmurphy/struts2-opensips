<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro"
    uri="http://shiro.apache.org/tags" %>


<html>
<head>
<link href="<s:url value="resources/layout.css"/>"rel="stylesheet" type="text/css"/>
</head>
<body>
<div>
<%@ include file="/WEB-INF/views/headerscript.jsp" %>
</div>

<div>
	<s:form action="createUser" method="post">
	E-Mail
	<s:textfield name="email" key="user.email"/>
	Password
	<s:textfield name="password" key="user.password"/>
	Role name
	<s:textfield name="roleName" key="user.rolename"/>
	<br/>
	<s:submit />
	</s:form>
	</div>
	<table>
	  <tr>
  	<th align="left">Email&nbsp;&nbsp;</th>
    <th align="left">Usergroup&nbsp;&nbsp;</th>
 
    <s:iterator value="listUsers" status="rowstatus">
				<tr <s:if test="#rowstatus.odd">class="odd"</s:if><s:else>class="even"</s:else>>
     			<td  align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="username" />
     				</span>
     			</td>
				<td align="left" width="5%">
					<span style="font-size: 10.0pt; font-family: Times New Roman">
        				<s:property value="roleName" />
     				</span>
     			</td>
     			
		<td align="left" width="5%">
					<s:url id="deleteURL" action="deleteUser">
						<s:param name="id" value="%{id}"></s:param>
					</s:url> 
					<s:a href="%{deleteURL}">Delete</s:a>
				</td>
		</s:iterator>
    </tr>
	</table>
	</body>
	</html>
	
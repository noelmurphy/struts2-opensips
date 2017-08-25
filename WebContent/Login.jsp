
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
   <link href="<s:url value="resources/layout.css"/>"rel="stylesheet" type="text/css"/>
</head>
<body>
<%@ include file="/WEB-INF/views/headerscript.jsp" %>

<div class="loginform">
<h2>Please Log in</h2>
<s:form action="doLogin" method="post" cssClass="form-horizontal" theme="simple">
	<div>
	E-Mail
	<s:textfield name="email" label="Email" />
	<s:fielderror fieldName="email" key="user.email"/>
	</div>
	<div>
	Password
	<s:password name="password" label="Password" />
	<s:fielderror fieldName="password" key="user.password"/>
	</div>
	<s:submit />
	</s:form>
</div>
</body>
</html>

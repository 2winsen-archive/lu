<%@include file="/WEB-INF/jsp/include.jsp"%>
<%@ page import="org.springframework.security.ui.webapp.AuthenticationProcessingFilter"%>
<%@ page import="org.springframework.security.ui.AbstractProcessingFilter"%>
<%@ page import="org.springframework.security.AuthenticationException"%>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/transitional.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Login screen</title>
<link rel="shortcut icon" href="favicon.ico">
<link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
<link href="css/custom.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>

<div id="menu"></div>
<div id="logo">
<h1><a href="#">eHRS</a></h1>
</div>
<hr />
<div id="page">
<div id="content">
<div class="post"><c:if test="${not empty param.login_error}">
	<span class="error">Incorrect username or/and password!</span>
</c:if>
<form name="f" action="<c:url value='j_spring_security_check'/>" method="POST">
<table>
	<tr>
		<td>Username:</td>
		<td><input type='text' name='j_username'
			value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' />
		</td>
	</tr>
	<tr>
		<td>Password:</td>
		<td><input type='password' name='j_password'></td>
	</tr>
	<tr>
		<td><input id="rememberCookie" type="checkbox" name="_spring_security_remember_me"></td>
		<td><label for="rememberCookie">Remember me on this computer</label></td>
	</tr>
	<tr>
		<td colspan='2'><input name="submit" type="submit" value="Sign In"></td>
	</tr>
</table>
</form>
</div>
</div>
</div>
<!-- end #page -->
<div id="footer">
<p>(c) 2007 Website Name. Designed by <a href="http://www.freecsstemplates.org/">Free CSS Templates</a>.</p>
</div>

</body>
</html>


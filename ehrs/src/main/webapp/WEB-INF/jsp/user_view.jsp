<%@include file="include.jsp"%>

<security:authorize ifAnyGranted="ROLE_POWER, ROLE_ADMIN">
<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/transitional.dtd">
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>Main menu: view users</title>
	<link rel="shortcut icon" href="favicon.ico">
	<link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
	<link href="css/custom.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
<div id="menu">
	<ul>
		<li><a href="http://www.lu.lv/dn"><fmt:message key="startup.homepage">Homepage</fmt:message></a></li>
		<li><a href="action.do?_flowId=about-flow"><fmt:message key="startup.about">About</fmt:message></a></li>
		<li><a href="action.do?_flowId=contact-flow"><fmt:message key="startup.contact">Contact</fmt:message></a></li>
	</ul>
</div>
<div id="logo">
	<h1><a href="action.do?_flowId=startup-flow"><fmt:message key="startup.title">eHRS</fmt:message></a></h1>
	<h2>
		<p class="greeting">
		<span><fmt:message key="startup.hallo">Hallo, </fmt:message><security:authentication property="principal.username"/></span>
		<br/>
		<span id="logout"><a id="logout" href="<c:url value='/j_spring_security_logout'/>"><fmt:message key="startup.logout">Logout</fmt:message></a></span>
		</p>
	</h2>
</div>
<div id="splash">
	<img src="css/images/img05.jpg" alt="" />
</div>
<hr />
<div id="page">
	<div id="content">
		<div class="post">
			<h2 class="title"><fmt:message key="sidebar.view.user">View users</fmt:message></h2>
			<div class="entry">
				<form:form commandName="user" method="POST">
				<fieldset >
				<legend style="font-weight: bold;"><fmt:message key="users.view.criteria">View users criteria</fmt:message></legend>
				<table>
					<tr>
						<td><fmt:message key="users.view.name">Name*:</fmt:message></td>
						<td><form:input path="name"/></td>
					</tr>
					<tr>
						<td><fmt:message key="users.view.surname">Surname*:</fmt:message></td>
						<td><form:input path="surname"/></td>
					</tr>
					<tr>
						<td><fmt:message key="users.view.username">Username*:</fmt:message></td>
						<td><form:input path="username"/></td>
					</tr>
					<tr>
						<td><fmt:message key="users.view.email">Email*:</fmt:message></td>
						<td><form:input path="email"/></td>
					</tr>
				</table>
				</fieldset>
				<br/>
				<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
				<input type="submit" name="_eventId_search" value='<fmt:message key="general.search">Search</fmt:message>' />
			</form:form>
			</div>
			<br/>
			<c:if test="${users != null}">
				<div class="results">
					<table>
						<tr class="header">
							<th><fmt:message key="users.result.name">Name</fmt:message></th>
							<th><fmt:message key="users.result.surname">Surname</fmt:message></th>
							<th><fmt:message key="users.result.username">Username</fmt:message></th>
							<th><fmt:message key="users.result.email">Email</fmt:message></th>
							<th><fmt:message key="general.view">View</fmt:message></th>
						</tr>
						<c:forEach var="user" items="${users}">
							<tr>
								<td>${user.name}</td>
								<td>${user.surname}</td>
								<td>${user.username}</td>
								<td>${user.email}</td>
								<td>
									<a href="action.do?_flowExecutionKey=${flowExecutionKey}&_eventId=select&id=${user.id}">
										<img alt="" src="css/images/more.png">
									</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:if>
		</div>
	</div>
	<!-- end #content -->
	<div id="sidebar">
		<%@ include file="sidebar.jsp" %>
	</div>
	<!-- end #sidebar -->
</div>
<!-- end #page -->
<div id="footer">
	<p>(c) 2007 Website Name. Designed by <a href="http://www.freecsstemplates.org/">Free CSS Templates</a>.</p>
</div>
</body>
</html>
</security:authorize>
<security:authorize ifAnyGranted="ROLE_USER">
	<c:redirect url="/error_403.html" />
</security:authorize>

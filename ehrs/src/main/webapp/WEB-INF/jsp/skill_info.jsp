<%@include file="include.jsp"%>

<security:authorize ifAnyGranted="ROLE_ADMIN, ROLE_POWER">
<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/transitional.dtd">
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>Main menu: skill info</title>
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
			<h2 class="title">${skill.name} <fmt:message key="general.information">information</fmt:message></h2>
			<div class="entry">
				<table class="actions">
					<tr>
						<th colspan="2"><fmt:message key="user.more.actions">Actions</fmt:message></th>
					</tr>
					<tr>
						<td><a href="action.do?_flowExecutionKey=${flowExecutionKey}&_eventId=change"><img src="css/images/change.png" /></a></td>
						<td><fmt:message key="general.change">Change</fmt:message></td>
					</tr>
					<tr>
						<td><a href="action.do?_flowExecutionKey=${flowExecutionKey}&_eventId=delete"><img src="css/images/delete.png" /></a></td>
						<td><fmt:message key="general.delete">Delete</fmt:message></td>
					</tr>
					<c:if test="${delete == 1}">
					<tr>
						<td colspan="2" style="color: red;">
							<fmt:message key="general.confirm.delete">Confirm delete action? </fmt:message>
								<a href="action.do?_flowExecutionKey=${flowExecutionKey}&_eventId=delete-confirmed">OK</a>
						</td>
					</tr>
					</c:if>
				</table>
				<table>
					<tr>
						<td><fmt:message key="general.id">ID</fmt:message></td>
						<td class="info" >${skill.id}</td>
					</tr>
					<tr>
						<td><fmt:message key="general.name">Name</fmt:message></td>
						<td class="info" >${skill.name}</td>
					</tr>
					<tr>
						<td><fmt:message key="skill.result.group">Group</fmt:message></td>
						<td class="info" >${group.name}</td>
					</tr>
					<tr>
						<td><fmt:message key="skill.result.difficulty">Difficulty</fmt:message></td>
						<td class="info" >${skill.difficulty}</td>
					</tr>			
					<tr>
						<td><fmt:message key="general.comments">Comments</fmt:message></td>
						<td class="info" >${skill.comments}</td>
					</tr>
					<tr>
						<td><fmt:message key="general.timestamp">Timestamp</fmt:message></td>
						<td class="info" >${skill.timestamp}</td>
					</tr>					
				</table>
			</div>
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

<%@include file="include.jsp"%>

<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/transitional.dtd">
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>Main menu: message info</title>
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
			<h2 class="title">${message.title}</h2>
			<div class="entry">
				<table class="actions">
					<tr>
						<th colspan="2"><fmt:message key="user.more.actions">Actions</fmt:message></th>
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
						<td style="color: #3399FF;"><fmt:message key="message.info.date">Date:</fmt:message></td>
						<td class="info" >${message.timestamp}</td>
					</tr>
					<tr>
						<td style="color: #3399FF;"><fmt:message key="message.info.from">From:</fmt:message></td>
						<td class="info" >${message.senderUsername}</td>
					</tr>
				</table>	
				<br/>										
				<div id="message_text">
					${message.entry}
				</div>
				<br/>
				<br/>
				<div>
					<a href="action.do?_flowExecutionKey=${flowExecutionKey}&_eventId=return">
						<fmt:message key="general.return">Return</fmt:message>
					</a>
				</div>
				<c:if test="${res == 0}">
					<!-- Removing session variable message -->
					<c:set var="sessionMessageResult" value="0" scope="session"/>
				</c:if>
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

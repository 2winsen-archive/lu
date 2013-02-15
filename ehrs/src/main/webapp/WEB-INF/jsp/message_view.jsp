<%@include file="include.jsp"%>

<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/transitional.dtd">
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>Main menu: view messages</title>
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
			<h2 class="title"><fmt:message key="sidebar.view.message">View messages</fmt:message></h2>
			<div class="entry">
				<br/>
				<c:if test="${messages != null}">
					<div class="results">
						<table>
							<tr class="header">
								<th><fmt:message key="message.view.from">From</fmt:message></th>
								<th><fmt:message key="message.view.title">Title</fmt:message></th>
								<th><fmt:message key="message.view.entry">Message</fmt:message></th>
								<th><fmt:message key="message.view.date">Date</fmt:message></th>
								<th><fmt:message key="general.view">View</fmt:message></th>
							</tr>
							<c:forEach var="message" items="${messages}">
								<tr style='<c:if test="${message.readFlag == false}">font-weight: bold;</c:if>'>
									<td>${message.senderUsername}</td>
									<td>${message.title}</td>
									<td>${message.shortEntry}</td>
									<td>${message.timestamp}</td>
									<td>
										<a href="action.do?_flowExecutionKey=${flowExecutionKey}&_eventId=select&id=${message.id}">
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

<%@include file="include.jsp"%>

<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/transitional.dtd">
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>Main menu: view user skills</title>
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
			<h2 class="title"><fmt:message key="general.view">View</fmt:message> ${user.username}'s <fmt:message key="user.change.skills">skills</fmt:message></h2>
			<div class="entry">
				<div class="results">
					<table>
						<tr class="header">
							<th><fmt:message key="skill.result.name">Name</fmt:message></th>
							<th><fmt:message key="skill.result.group">Group</fmt:message></th>
							<th><fmt:message key="skill.result.difficulty">Difficulty</fmt:message></th>
							<th><fmt:message key="user.view.skills.experience">Experience</fmt:message></th>
							<th><fmt:message key="general.comments">Comments</fmt:message></th>
							<th><fmt:message key="general.timestamp">Timestamp</fmt:message></th>
							<th><fmt:message key="general.delete">Delete</fmt:message></th>
						</tr>
						<c:forEach var="element" items="${userSkillsData}">
							<tr>
								<td>${element.skill.name}</td>
								<td>
									<c:forEach var="group" items="${groups}">										 
										 <c:if test="${group.id == element.skill.groupId}">
										 	${group.name}
										 </c:if>
									</c:forEach>
								</td>
								<td>${element.skill.difficulty}</td>
								<td>${element.experience}</td>
								<td>${element.skill.comments}</td>
								<td>${element.timestamp}</td>
								<td>
									<a href="action.do?_flowExecutionKey=${flowExecutionKey}&_eventId=deleteSkill&id=${element.id}">
										<img alt="" src="css/images/delete.png">
									</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<br/>
				<br/>
				<a href="action.do?_flowExecutionKey=${flowExecutionKey}&_eventId=return">
					<fmt:message key="general.return">Return</fmt:message>
				</a>
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

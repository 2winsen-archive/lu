<%@include file="include.jsp"%>

<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/transitional.dtd">
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>Main menu: user info</title>
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
			<h2 class="title">${user.username}'s <fmt:message key="user.info.title">personal information</fmt:message></h2>
			<div class="entry">
				<table class="actions">
					<tr>
						<th colspan="2"><fmt:message key="user.more.actions">Actions</fmt:message></th>
					</tr>
					<security:authorize ifAnyGranted="ROLE_ADMIN, ROLE_POWER">
					<tr>
						<td><a href="action.do?_flowExecutionKey=${flowExecutionKey}&_eventId=change"><img src="css/images/change.png" /></a></td>
						<td><fmt:message key="general.change">Change</fmt:message></td>
					</tr>
					</security:authorize>
					<security:authorize ifAnyGranted="ROLE_POWER">
					<tr>
						<td><a href="action.do?_flowExecutionKey=${flowExecutionKey}&_eventId=changeAdd"><img src="css/images/change+.png" /></a></td>
						<td><fmt:message key="user.more.change.additional">Change additional</fmt:message></td>
					</tr>
					</security:authorize>
					<tr>
						<td><a href="action.do?_flowExecutionKey=${flowExecutionKey}&_eventId=viewSkills"><img src="css/images/view_skills.png" /></a></td>
						<td><fmt:message key="user.more.view.skills">View skills</fmt:message></td>
					</tr>
					<security:authorize ifAnyGranted="ROLE_POWER, ROLE_USER">
					<tr>
						<td><a href="action.do?_flowExecutionKey=${flowExecutionKey}&_eventId=changeSkill"><img src="css/images/change_skills.png" /></a></td>
						<td><fmt:message key="user.more.change.skills">Add skills</fmt:message></td>
					</tr>
					</security:authorize>
					<security:authorize ifAnyGranted="ROLE_ADMIN">
					<tr>
						<td><a href="action.do?_flowExecutionKey=${flowExecutionKey}&_eventId=delete"><img src="css/images/delete.png" /></a></td>
						<td><fmt:message key="general.delete">Delete</fmt:message></td>
					</tr>
					</security:authorize>
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
						<th class="table_title" colspan="2"><fmt:message key="user.identification">Identification information</fmt:message></th>
					</tr>
					<tr>
						<td><fmt:message key="general.id">ID</fmt:message></td>
						<td class="info" >${user.id}</td>
					</tr>
					<tr>
						<td><fmt:message key="user.more.enabled">Enabled</fmt:message></td>
						<td class="info" >${user.enabled}</td>
					</tr>
					<tr>
						<td><fmt:message key="general.name">Name</fmt:message></td>
						<td class="info" >${user.name}</td>
					</tr>
					<tr>
						<td><fmt:message key="user.more.surname">Surname</fmt:message></td>
						<td class="info" >${user.surname}</td>
					</tr>
					<tr>
						<td><fmt:message key="user.more.username">Username</fmt:message></td>
						<td class="info" >${user.username}</td>
					</tr>
					<tr>
						<td><fmt:message key="user.more.email">Email</fmt:message></td>
						<td class="info" >${user.email}</td>
					</tr>
					<tr>
						<td><fmt:message key="general.timestamp">Timestamp</fmt:message></td>
						<td class="info" >${user.timestamp}</td>
					</tr>
					<tr>
						<th class="table_title" colspan="2"><fmt:message key="user.address.info">Address information</fmt:message></th>
					</tr>
					<tr>
						<td><fmt:message key="user.more.country">Country</fmt:message></td>
						<td class="info" >${country.name}</td>
					</tr>
					<tr>
						<td><fmt:message key="user.more.city">City</fmt:message></td>
						<td class="info" >${user.city}</td>
					</tr>
					<tr>
						<td><fmt:message key="user.more.zip">Zip</fmt:message></td>
						<td class="info" >${user.zip}</td>
					</tr>
					<tr>
						<td><fmt:message key="user.more.address">Address</fmt:message></td>
						<td class="info" >${user.address}</td>
					</tr>
					<tr>
						<th class="table_title" colspan="2"><fmt:message key="user.position.info">Position information</fmt:message></th>
					</tr>
					<tr>
						<td><fmt:message key="user.more.position">Position</fmt:message></td>
						<td class="info" >${position.name}</td>
					</tr>
					<tr>
						<td><fmt:message key="user.more.salary">Salary</fmt:message></td>
						<td class="info" >${user.salary}</td>
					</tr>
					<tr>
						<th class="table_title" colspan="2"><fmt:message key="user.office.info">Office information</fmt:message></th>
					</tr>
					<tr>
						<td><fmt:message key="user.more.office.name">Office name</fmt:message></td>
						<td class="info" >${office.name}</td>
					</tr>
					<tr>
						<td><fmt:message key="user.more.office.country">Office country</fmt:message></td>
						<td class="info" >${officeCountry.name}</td>
					</tr>
					<tr>
						<td><fmt:message key="user.more.office.city">Office city</fmt:message></td>
						<td class="info" >${office.city}</td>
					</tr>
					<tr>
						<td><fmt:message key="user.more.office.zip">Office zip</fmt:message></td>
						<td class="info" >${office.zip}</td>
					</tr>
					<tr>
						<td><fmt:message key="user.more.office.address">Office address</fmt:message></td>
						<td class="info" >${office.address}</td>
					</tr>
					<tr>
						<th class="table_title" colspan="2"><fmt:message key="user.project.info">Project information</fmt:message></th>
					</tr>
					<tr>
						<td><fmt:message key="user.more.project.name">Project name</fmt:message></td>
						<td class="info" >${project.name}</td>
					</tr>
					<tr>
						<td><fmt:message key="user.more.project.manager.name">Project manager name</fmt:message></td>
						<td class="info" >${project.managerName}</td>
					</tr>
					<tr>
						<td><fmt:message key="user.more.project.manager.surname">Project manager surname</fmt:message></td>
						<td class="info" >${project.managerSurname}</td>
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

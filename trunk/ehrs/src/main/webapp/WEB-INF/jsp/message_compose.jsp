<%@include file="include.jsp"%>

<%@ page language="java" contentType="text/html; charset=utf8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/transitional.dtd">
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>Main menu: compose message</title>
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
			<h2 class="title"><fmt:message key="sidebar.new.message">Compose new message</fmt:message></h2>
			<div class="entry">
				<form:form commandName="message" method="POST">
				<p>
					<form:errors cssClass="error" />
				</p>
				<c:if test="${result == '1'}">
					<p class="confirm"><fmt:message key="message.sucess">Message has sent successfully.</fmt:message></p>
					<p><span style="color: red;"><fmt:message key="message.compose.another">=Compose another message? </fmt:message></span>
						<a href="action.do?_flowId=message-compose-flow"><fmt:message key="general.yes">Yes.</fmt:message></a>
						<span> / </span>
						<a href="action.do?_flowId=startup-flow"><fmt:message key="general.no">No.</fmt:message></a>
					</p>
				</c:if>
				<c:if test="${result != 1}">			
				<fieldset >
				<table>
					<tr>
						<td><fmt:message key="message.username">To*:</fmt:message></td>
						<td>
							<form:select path="user.id" size="7">
								<c:forEach var="element" items="${users}">
									<form:option value="${element.id}">${element.name}</form:option>
								</c:forEach>
							</form:select>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="message.title">Title*:</fmt:message></td>
						<td><form:input path="title"/></td>
					</tr>
					<tr>
						<td><fmt:message key="message.text">Message text*:</fmt:message></td>
						<td><form:textarea path="entry" rows="10" cols="40" /></td>
					</tr>
				</table>
				</fieldset>
				<br/>
				<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
				<input type="submit" name="_eventId_send" value='<fmt:message key="message.send">Send</fmt:message>' />
				</c:if>
			</form:form>
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

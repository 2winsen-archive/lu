<%@include file="include.jsp"%>

<ul>
	<li>
		<c:if test="${res == 1 || sessionMessageResult == 1}">
			<!-- Also setting session variable message -->
			<c:set var="sessionMessageResult" value="1" scope="session"  />
			<a href="action.do?_flowId=message-view-flow"><img src="css/images/message.png" /></a> 
			<fmt:message key="general.new.message">You have unread messages</fmt:message>
		</c:if>
	</li>
	<li>		
		<h2><fmt:message key="sidebar.functions.main">Main Functions</fmt:message></h2>
		<ul>
			<security:authorize ifAnyGranted="ROLE_ADMIN">
				<li><a href="action.do?_flowId=user-add-flow"><fmt:message key="sidebar.new.user">Add new user</fmt:message></a></li>
			</security:authorize>
			<security:authorize ifAnyGranted="ROLE_ADMIN, ROLE_POWER">
				<li><a href="action.do?_flowId=user-view-flow"><fmt:message key="sidebar.view.user">View users</fmt:message></a></li>
				<br/>
			</security:authorize>
			<security:authorize ifAnyGranted="ROLE_USER">
				<li><a href="action.do?_flowId=user-personal-flow"><fmt:message key="sidebar.view.user.personal">View personal information</fmt:message></a></li>
			</security:authorize>
			<security:authorize ifAnyGranted="ROLE_ADMIN, ROLE_POWER">
				<li><a href="action.do?_flowId=group-add-flow"><fmt:message key="sidebar.new.group">Create new skill group</fmt:message></a></li>
				<li><a href="action.do?_flowId=group-view-flow"><fmt:message key="sidebar.view.group">View skills groups</fmt:message></a></li>
				<br/>
			</security:authorize>
			<security:authorize ifAnyGranted="ROLE_ADMIN, ROLE_POWER">
				<li><a href="action.do?_flowId=skill-add-flow"><fmt:message key="sidebar.new.skill">Create new skill</fmt:message></a></li>
				<li><a href="action.do?_flowId=skill-view-flow"><fmt:message key="sidebar.view.skill">View skills</fmt:message></a></li>
				<br/>
			</security:authorize>
			<security:authorize ifAnyGranted="ROLE_ADMIN, ROLE_POWER">
				<li><a href="action.do?_flowId=project-add-flow"><fmt:message key="sidebar.new.project">Create new project</fmt:message></a></li>
				<li><a href="action.do?_flowId=project-view-flow"><fmt:message key="sidebar.view.project">View projects</fmt:message></a></li>
				<br/>
			</security:authorize>
			<security:authorize ifAnyGranted="ROLE_ADMIN, ROLE_POWER">
				<li><a href="action.do?_flowId=office-add-flow"><fmt:message key="sidebar.new.office">Create new office</fmt:message></a></li>
				<li><a href="action.do?_flowId=office-view-flow"><fmt:message key="sidebar.view.office">View offices</fmt:message></a></li>
				<br/>
			</security:authorize>
			<security:authorize ifAnyGranted="ROLE_ADMIN, ROLE_POWER">
				<li><a href="action.do?_flowId=position-add-flow"><fmt:message key="sidebar.new.position">Create new employee position</fmt:message></a></li>
				<li><a href="action.do?_flowId=position-view-flow"><fmt:message key="sidebar.view.position">View employees' positions</fmt:message></a></li>
			</security:authorize>
		</ul>
		<h2><fmt:message key="sidebar.functions.specific">User specific functions</fmt:message></h2>
		<ul>
			<security:authorize ifAnyGranted="ROLE_ADMIN">
				<li><a href="#"><fmt:message key="sidebar.view.audit">View audit</fmt:message></a></li>
				<br/>
			</security:authorize>
			<security:authorize ifAnyGranted="ROLE_POWER">
				<li><a href="#"><fmt:message key="sidebar.new.news">Create news</fmt:message></a></li>
				<li><a href="#"><fmt:message key="sidebar.view.news">View news</fmt:message></a></li>
				<br/>
			</security:authorize>
			<!-- User is viewing news without search inputs -->
			<security:authorize ifAnyGranted="ROLE_USER">
				<li><a href="#"><fmt:message key="sidebar.view.news">View news</fmt:message></a></li>
				<br/>
			</security:authorize>
			<security:authorize ifAnyGranted="ROLE_ADMIN, ROLE_POWER, ROLE_USER">
				<li><a href="action.do?_flowId=message-compose-flow"><fmt:message key="sidebar.new.message">Compose new message</fmt:message></a></li>					
				<li><a href="action.do?_flowId=message-view-flow"><fmt:message key="sidebar.view.message">View messages</fmt:message></a></li>
			</security:authorize>
		</ul>
	</li>
</ul>
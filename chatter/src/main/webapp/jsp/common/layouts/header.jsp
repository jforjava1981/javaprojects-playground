<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="navbar navbar-fixed-top">
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-4"></div>
			<div class="col-xs-4 text-center"><h3>Chatter Application</h3></div>
			<div class="col-xs-4">
				<sec:authorize access="hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')">
				<span class="pull-right"> <sec:authentication var="principal"
						property="principal" /> <c:out
						value="${principal.firstName} ${principal.lastName}"></c:out> <a
					href="${pageContext.request.contextPath}/auth/logout" id="logoutAnchor">Logout</a>
				</span>
			</sec:authorize>
			</div>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-left">
				<!-- <li><a href="#">Register</a></li> -->
			</ul>
		</div>
	</div>
</div>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div id="registerFormContainer">
	<div id="registerFormBox">
		<form:form action="/chatter/auth/register" method="post"
			 id="registerForm" commandName="user" class="form-signin" role="form">
			<h1 class="form-signin-heading">Regiser</h1> 
			<div id="registerActionMessage" class="actionMessage">
			</div>
			<div id="registerActionError" class="actionError">
			</div>
			<div>
				<form:input path="userId" id="userId" class="form-control" placeholder="User ID"  autofocus="autofocus"></form:input>
			</div>
			<div>
				<form:password path="userPassword"  id="userPassword" class="form-control" placeholder="Password" required="required"></form:password>
			</div>
			<div>
				<form:input path="userFirstName"  id="userFirstName" class="form-control" placeholder="First Name" required="required"></form:input>
			</div>
			<div>
				<form:input path="userLastName"  id="userLastName" class="form-control" placeholder="Last Name" required="required"></form:input>
			</div>
			<div>
				<input type="button" value="Register" id="registerBtn" class="btn btn-lg btn-primary">
			</div>
		</form:form>
	</div>
</div>
<script src="${pageContext.request.contextPath}/js/auth/register.js"></script>
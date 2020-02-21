<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div id="loginFormContainer">
	<div id="loginFormBox">
		<form:form action="/chatter/auth/login" method="post"
			 id="loginForm" commandName="user" class="form-signin" role="form">
			<h1 class="form-signin-heading">Please sign in</h1> 
			<div id="loginActionMessage" class="actionMessage">
				
			</div>
			<div id="loginActionError" class="actionError">
				<c:if test='${param.error == "1"}'>
					<span>Invalid UserName or Password</span>
				</c:if>
			</div>
			<div>
				<form:input path="userId" id="userId" class="form-control" placeholder="User ID"  autofocus="autofocus"></form:input>
			</div>
			<div>
				<form:password path="userPassword"  id="userPassword" class="form-control" placeholder="Password" required="required"></form:password>
			</div>
			<div>
				<input type="button" value="Login" id="loginSubmitBtn" class="btn btn-lg btn-primary">
				<input type="button" value="Register" id="loginRegistertBtn" class="btn btn-lg btn-primary">
			</div>
		</form:form>
	</div>
</div>
<script src="${pageContext.request.contextPath}/js/auth/login.js"></script>
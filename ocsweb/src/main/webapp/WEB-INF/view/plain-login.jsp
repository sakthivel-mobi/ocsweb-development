<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<html>

		<head>
			<title>Custom Login Page</title>

			<style>
				.failed {
					color: red;
				}
			</style>

		</head>

		<body class="login-body">

			<h3>My Custom Login Page</h3>

			<form:form accept-charset="utf-8" action="${pageContext.request.contextPath}/authenticateUser"
				method="POST">

				<!-- Check for login error -->

				<c:if test="${param.error != null}">

					<i class="failed">Sorry! You entered invalid username/password.</i>

				</c:if>

				<p>
					User name: <input type="text" name="username" />
				</p>

				<p>
					Password: <input type="password" name="password" />
				</p>

				<input type="submit" value="Login" />
			</form:form>

		</body>

		<style>
			#login-body {
				background: url("https://images.unsplash.com/photo-1633144135893-742767f36e56?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1033&q=80");
				background-repeat: no-repeat;
				background-size: cover;
			}
		</style>

		</html>
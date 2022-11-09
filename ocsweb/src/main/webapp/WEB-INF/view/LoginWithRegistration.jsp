<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">

<head>

<title>Login Page</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath}/resources/images/favicon/favicon.ico">

<link rel="manifest" href="/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage"
	content="${pageContext.request.contextPath}/resources/images/favicon/ms-icon-144x144.png">
<meta name="theme-color" content="#f1f5fa">


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap5.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/fonts/fonts.css">


<meta id="_csrf" name="_csrf" content="${_csrf.token}" />
<meta id="_csrf_header" name="_csrf_header"
	content="${_csrf.headerName}" />


<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap5.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/fonts/fonts.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css">

<!-- CDN moved to local -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/js/fontawesome/css/fontawesome.min.css">
<script src="${pageContext.request.contextPath}/resources/js/axios.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/moment.js"
	type="text/javascript"></script>
<!-- CDN moved to local -->


<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap5.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>

<%@ page isELIgnored="false"%>
</head>

<body>
	<div class="login-content">
		<div class="card" style="">
			<div class="card-body">
				<h5 class="card-title" style="text-align: center;">Login</h5>
				<div>
					<form:form
						action="${pageContext.request.contextPath}/authenticateTheUser"
						method="POST" class="form-horizontal">

						<label for="login-id">Login ID</label>
						<div class="input-group mb-3">
							<span class="input-group-text" id="basic-addon1">60</span> <input
								type="text" class="form-control" name="username"
								placeholder="Eg. 1xxxxxx" aria-label="Username"
								aria-describedby="basic-addon1">
						</div>
						<div class="helper-text" style="font-size: x-small;">Enter
							mobile number without country code and without any space or dash.</div>




						<div class="mt-2">
							<div class="d-flex flex-row align-items-center">
								<div for="login-password mr-auto" style="flex: 1;">Password</div>
								<a href="${pageContext.request.contextPath}/ForgotPassword">Forgot
									Password?</a>
							</div>
							<input id="login-password" class="form-control" name="password"
								placeholder="Enter your Password *" type="password" />
						</div>

						<!-- Check for login error -->

						<c:if test="${param.error != null}">

							<div class="alert alert-danger col-xs-offset-1 col-xs-10">
								${param.error} Invalid Credentials</div>

						</c:if>

						<c:if test="${param.error == true}">
							<div
								class="alert alert-danger col-xs-offset-1 col-xs-10 mt-2 mb-2">
								Invalid Credentials</div>

						</c:if>
						<button class="btn btn-primary mt-2" type="submit">Login</button>

					</form:form>

					<div class="d-flex flex-row align-items-center">
						<div style="flex: 1;">
							<hr>
						</div>
						<div>
							<p style="margin: 1rem !important">or</p>
						</div>
						<div style="flex: 1;">
							<hr />
						</div>
					</div>

					<a class="btn btn-primary mt-2 w-100"
						href="${pageContext.request.contextPath}/signup">Register as a
						new user</a>

				</div>
			</div>
		</div>
	</div>



</body>

<style>
/* Extra small devices (phones, 600px and down) */
@media only screen and (max-width: 600px) {
	body {
		background:
			url("${pageContext.request.contextPath}/resources/images/login_bg.jpg");
		background-repeat: no-repeat;
		background-size: cover;
		display: flex;
		padding-right: 1rem;
		padding-left: 1rem;
		place-items: center;
		height: 100vh;
	}
	.login-content {
		flex: 1;
		text-align: -webkit-center;
	}
	.card {
		width: 90%;
		text-align: left;
		padding: 1rem;
	}
}

/* Small devices (portrait tablets and large phones, 600px and up) */
@media only screen and (min-width: 600px) {
	body {
		background:
			url("${pageContext.request.contextPath}/resources/images/login_bg.jpg");
		background-repeat: no-repeat;
		background-size: cover;
		display: flex;
		padding-right: 6rem;
		padding-left: 6rem;
		place-items: center;
		height: 100vh;
	}
	.login-content {
		flex: 1;
		text-align: -webkit-center;
	}
	.card {
		width: 90%;
		text-align: left;
		padding: 2rem;
	}
}

/* Medium devices (landscape tablets, 768px and up) */
@media only screen and (min-width: 768px) {
	body {
		background:
			url("${pageContext.request.contextPath}/resources/images/login_bg.jpg");
		background-repeat: no-repeat;
		background-size: cover;
		display: flex;
		padding-right: 12rem;
		padding-left: 12rem;
		place-items: center;
		height: 100vh;
	}
	.login-content {
		flex: 1;
		text-align: -webkit-center;
		text-align: -moz-right;
	}
	.card {
		width: 90%;
		text-align: left;
		padding: 2rem;
	}
}

/* Large devices (laptops/desktops, 992px and up) */
@media only screen and (min-width: 992px) {
	body {
		background:
			url("${pageContext.request.contextPath}/resources/images/login_bg.jpg");
		background-repeat: no-repeat;
		background-size: cover;
		display: flex;
		padding-right: 17rem;
		padding-left: 17rem;
		place-items: center;
		height: 100vh;
	}
	.login-content {
		flex: 1;
		text-align: -webkit-center;
		text-align: -moz-right;
	}
	.card {
		width: 70%;
		text-align: left;
		padding: 2rem;
	}
}

/* Extra large devices (large laptops and desktops, 1200px and up) */
@media only screen and (min-width: 1200px) {
	body {
		background:
			url("${pageContext.request.contextPath}/resources/images/login_bg.jpg");
		background-repeat: no-repeat;
		background-size: cover;
		display: flex;
		padding-right: 4rem;
		padding-left: 4rem;
		place-items: center;
		height: 100vh;
	}
	.login-content {
		flex: 1;
		text-align: -webkit-right;
		text-align: -moz-right;
	}
	.card {
		width: 30%;
		text-align: left;
		padding: 2rem;
	}
}

input {
	width: 100%;
	height: 40px;
	padding: 0.5rem;
	border-radius: 5px;
	border: solid 1px #6e8dc7;
	background-color: #fff !important;
}

button {
	margin-top: 10px;
	width: 100%;
}

.mt-2 {
	margin-top: 1.5rem !important;
}
</style>

</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="utf-8" />
			<meta name="viewport" content="width=device-width, initial-scale=1" />
			<meta name="description" content="" />
			<meta name="author" content="" />
			<title>MOBI</title>

			<meta id="_csrf" name="_csrf" content="${_csrf.token}" />
			<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}" />

			<link rel="apple-touch-icon" sizes="57x57"
				href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-57x57.png" />
			<link rel="apple-touch-icon" sizes="60x60"
				href="${pageContext.request.contextPath}/resourcess/images/favicon/apple-icon-60x60.png" />
			<link rel="apple-touch-icon" sizes="72x72"
				href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-72x72.png" />
			<link rel="apple-touch-icon" sizes="76x76"
				href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-76x76.png" />
			<link rel="apple-touch-icon" sizes="114x114"
				href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-114x114.png" />
			<link rel="apple-touch-icon" sizes="120x120"
				href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-120x120.png" />
			<link rel="apple-touch-icon" sizes="144x144"
				href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-144x144.png" />
			<link rel="apple-touch-icon" sizes="152x152"
				href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-152x152.png" />
			<link rel="apple-touch-icon" sizes="180x180"
				href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-180x180.png" />
			<link rel="icon" type="image/png" sizes="192x192"
				href="${pageContext.request.contextPath}/resources/images/favicon/android-icon-192x192.png" />
			<link rel="icon" type="image/png" sizes="32x32"
				href="${pageContext.request.contextPath}/resources/images/favicon/favicon-32x32.png" />
			<link rel="icon" type="image/png" sizes="96x96"
				href="${pageContext.request.contextPath}/resources/images/favicon/favicon-96x96.png" />
			<link rel="icon" type="image/png" sizes="16x16"
				href="${pageContext.request.contextPath}/resources/images/favicon/favicon-16x16.png" />
			<link rel="manifest" href="/manifest.json" />
			<meta name="msapplication-TileColor" content="#ffffff" />
			<meta name="msapplication-TileImage"
				content="${pageContext.request.contextPath}/resources/images/favicon/ms-icon-144x144.png" />
			<meta name="theme-color" content="#f1f5fa" />

			<!-- CDN moved to local -->
			<script src="${pageContext.request.contextPath}/resources/js/signaturePad.js"
				type="text/javascript"></script>
			<link rel="stylesheet" type="text/css"
				href="${pageContext.request.contextPath}/resources/js/fontawesome/css/fontawesome.min.css" />
			<script src="${pageContext.request.contextPath}/resources/js/axios.js" type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/resources/js/moment.js" type="text/javascript"></script>
			<!-- CDN moved to local -->

			<link rel="stylesheet" type="text/css"
				href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
			<link rel="stylesheet" type="text/css"
				href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" />
			<link rel="stylesheet" type="text/css"
				href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap5.min.css" />
			<link rel="stylesheet" type="text/css"
				href="${pageContext.request.contextPath}/resources/fonts/fonts.css" />

			<script src="${pageContext.request.contextPath}/resources/js/loading.js"></script>

			<%@ page isELIgnored="false" %>
		</head>

		<body>
			<main class="container mt110">
				<div id="card-placeholder-mobile" style="margin-top: 5rem; margin-bottom: 5rem;">
					<div class="title">Web portal credentials</div>
					<div class="mobi-card mt-4">
						<div class="mobi-card-body">
							<div class="row">
								<div class="col-12">
									<div class="caption">Username</div>
									<c:if test="${merchantRegistration.webPortalUsername != null }">
										<h5>${merchantRegistration.webPortalUsername}</h5>
									</c:if>
									<c:if test="${merchantRegistration.webPortalUsername == null}">
										<div class="title">Not available</div>
									</c:if>
								</div>
							</div>
							<div class="d-flex flex-row align-items-end">
								<div class="flex-grow-1">
									<div class="caption mt-3">Password</div>

									<c:if test="${merchantRegistration.webPortalPassword != null }">
										<div class="d-flex flex-row align-items-center">
											<div class="title flex-grow-0"
												style="font-size: large; margin-right: 1rem;">
												<input type="password" class="title" style="border: 0px !important;"
													value="${merchantRegistration.webPortalPassword}"
													id='input-password-web-${merchantRegistration.id}' />
											</div>
											<div class="flex-grow-0">
												<img src="${pageContext.request.contextPath}/resources/images/visiblity_on.svg"
													alt="" srcset=""
													onclick='togglePasswordWeb("${merchantRegistration.id}")' />
											</div>
										</div>
									</c:if>
									<c:if test="${merchantRegistration.webPortalPassword == null}">
										<div class="title">Not available</div>
									</c:if>

								</div>
								<c:if test="${merchantRegistration.webPortalPassword != null }">
									<div class="flex-grow-0">
										<img src="${pageContext.request.contextPath}/resources/images/copy_content.svg"
											width="" alt="" srcset=""
											onclick="copyToClipboard('${merchantRegistration.webPortalPassword}')" />
									</div>
								</c:if>

							</div>
						</div>
					</div>
					<div class="mt-4">
						<div class="title">App credentials</div>
						<div class="mobi-card mt-4">
							<div class="mobi-card-body">
								<div class="row">
									<div class="col-12">
										<div class="caption">Username</div>
										<c:if test="${merchantRegistration.appPassword != null }">
											<h5>${merchantRegistration.appUsername}</h5>
										</c:if>
										<c:if test="${merchantRegistration.appPassword == null }">
											<div class="title">Not Available</div>
										</c:if>


									</div>
								</div>
								<div class="d-flex flex-row align-items-end">
									<div class="flex-grow-1">
										<div class="caption mt-3">Password</div>
										<div class="d-flex flex-row align-items-center">
											<c:if test="${merchantRegistration.appPassword != null }">
												<div class="flex-grow-0" style="font-size: large; margin-right: 1rem;">
													<input type="password" class="title" style="border: 0px !important;"
														value='${merchantRegistration.appPassword}'
														id="input-password-app-${merchantRegistration.id}" />
												</div>
												<div class="flex-grow-0">
													<img src="${pageContext.request.contextPath}/resources/images/visiblity_on.svg"
														alt="" srcset=""
														onclick="togglePasswordApp('${merchantRegistration.id}')" />
												</div>
											</c:if>
											<c:if test="${merchantRegistration.appPassword == null }">
												<div class="title">Not Available</div>
											</c:if>
										</div>
									</div>
									<c:if test="${merchantRegistration.appPassword != null }">
										<div class="flex-grow-0">
											<img src="${pageContext.request.contextPath}/resources/images/copy_content.svg"
												width="" alt="" srcset=""
												onclick="copyToClipboard('${merchantRegistration.appPassword}')" />
										</div>
									</c:if>
								</div>
							</div>
						</div>
					</div>
					<div class="mt-4">
						<div class="title">Transaction credentials</div>
						<div class="mobi-card mt-4">
							<div class="mobi-card-body">
								<div>
									<div class="caption">Login ID</div>
									<c:if test="${merchantRegistration.appUsername != null }">
										<h5>${merchantRegistration.appUsername}</h5>
									</c:if>
									<c:if test="${merchantRegistration.appUsername == null }">
										<div class="title">Not Available</div>
									</c:if>
								</div>
								<div class="mt-2">
									<div class="caption">Api Key</div>
									<c:if test="${merchantRegistration.apiKey != null }">
										<h5>${merchantRegistration.apiKey}</h5>
									</c:if>
									<c:if test="${merchantRegistration.apiKey == null }">
										<div class="title">Not Available</div>
									</c:if>

								</div>
							</div>
						</div>
					</div>
				</div>
			</main>
		</body>
		<script>

			var contextPath = " ${pageContext.request.contextPath}"
			var token = $("meta[name='_csrf' ]").attr("content")
			var header = $("meta[name='_csrf_header' ]").attr("content")
			var ipAddress = null
			const quotationId = "${quotationId}"
			window.onload = function () {
				getIpAddress();
				// getCredentials()
			}
			function getIpAddress() {
				var config = {
					method: 'get',
					url: "https://api.ipify.org?format=json",
				}
				axios(config).then(response => {
					return response.data
				})
					.then(response => {
						ipAddress = response.ip
					})
					.catch(error => {

					})
					.finally(() => {

					})
			}

			function getCredentials() {
				startLoadingScreen("Loading, please wait")
				var config = {
					method: 'post',
					url: contextPath + "/merchant/credentials/" + 86,
					headers: {
						'X-CSRF-TOKEN': token,
						'Content-Type': 'application/json'
					}
				}


				axios(config)
					.then(response => {
						return response.data
					})
					.then(response => {
						console.log(response)
						if (response.responseStatus === "0000") {
							showCredentials(response.responseData)
						} else {
							alert(response.responseMessage)
						}
					})
					.catch(error => {

					})
					.finally(() => {
						stopLoadingScreen()
					})
			}


			function togglePasswordApp(elementId) {
				const element =
					document.getElementById("input-password-app-"
						+ elementId);
				if (element.type === "password") {
					element.type = "text";
				} else {
					element.type = "password";
				}
			}

			function togglePasswordWeb(elementId) {
				const element =
					document.getElementById("input-password-web-"
						+ elementId);
				if (element.type === "password") {
					element.type = "text";
				} else {
					element.type = "password";
				}
			}

			function copyToClipboard(content) {
				navigator.clipboard.writeText(content);
				alert("Copied to Clipboard")
			}
		</script>

		<style type="text/css">
			#welcome-letter-body {
				margin-top: 2rem !important;
				padding: 4rem;
			}

			.welcome-letter {
				padding: 5rem;
				background-color: white;
			}

			.welcome-letter-heading {
				color: #005baa;
				font-weight: 600;
			}

			.signature-view {
				background-color: #f7f7f7;
				padding: 2rem !important;
			}

			.mr-2 {
				margin-right: 1rem;
			}

			p {
				margin: 0;
			}

			.btn-accept {
				color: #005baa;
				margin: 34.2px 10px 0 1324px;
				border-radius: 4px;
				border: solid 1px #005baa;
				margin: 34.2px 10px 0 1324px;
			}
		</style>

		</html>
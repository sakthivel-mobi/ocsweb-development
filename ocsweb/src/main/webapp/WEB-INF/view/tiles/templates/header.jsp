<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

		<!doctype html>
		<html lang="en">

		<head>
			<%@ page isELIgnored="false" %>
				<meta charset="utf-8">
				<meta name="viewport" content="width=device-width, initial-scale=1">
				<meta name="description" content="">
				<meta name="author" content="">
				<title>Products</title>
				<link rel="icon" type="image/x-icon" href="../resources/images/favicon/favicon.ico">

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
				<script type="text/javascript"
					src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>

				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/resources/css/style.css">

				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap5.min.css">
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/resources/fonts/fonts.css">
		</head>


		<body>
			<nav class="navbar nav-mobile navbar-light bg-light fixed-top"
				style="background-color: #005baa !important; border-bottom-left-radius: 14px; border-bottom-right-radius: 14px;"
				id="mobile-view">
				<div class="container-fluid">
					<a class="navbar-brand" href="#" style="width: 100%; text-align: center; color: white;">mobi</a>
					<button class="navbar-toggler" type="button" data-bs-toggle="offcanvas"
						data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar">
						<img src="${pageContext.request.contextPath}/resources/images/menu_white_24dp.svg" alt="">
					</button>
					<div class="offcanvas offcanvas-start" tabindex="-1" id="offcanvasNavbar"
						aria-labelledby="offcanvasNavbarLabel">
						<div class="offcanvas-header" style="padding-left: 5rem;">
							<button type="button" class="btn text-reset" data-bs-dismiss="offcanvas" aria-label="Close">
								<img src="${pageContext.request.contextPath}/resources/images/menu_close.svg"
									style="width: 24px; height: 24px;" alt="">
							</button>
						</div>
						<div class="offcanvas-body">
							<sec:authorize access="hasRole('MERCHANT')">
								<ul class="navbar-nav justify-content-start flex-grow-1 pe-3">
									<li class="nav-item"><a class="nav-link" id="merchant-quotation-mobile"
											href="${pageContext.request.contextPath}/MerchantOrder">Quotations</a>
									</li>
									<li class="nav-item"><a class="nav-link" id="merchant-order-mobile"
											href="${pageContext.request.contextPath}/MerchantOrder/Order">Orders</a>
									</li>
									<li class="nav-item"><a class="nav-link" id="merchant-credentials-mobile"
											href="${pageContext.request.contextPath}/MerchantOrder/Credential">Credentials</a>
									</li>
								</ul>
							</sec:authorize>

							<!-- Sales -->
							<sec:authorize access="hasAnyRole('SALES','SALES-MANAGER')">
								<ul class="navbar-nav me-auto mb-2 mb-md-0">
									<li class="nav-item"><a id="link-products-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/Product/productList">Products</a>
									</li>
									<li class="nav-item"><a id="link-quotation-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/Quotation/list">Quotation</a>
									</li>
									<li class="nav-item"><a id="link-myAction-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/MyAction/list">My
											Action</a></li>
									<li class="nav-item"><a id="link-orders-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/Order/list">Orders</a></li>

									<li class="nav-item"><a id="link-signature-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/Signature">Signature</a></li>
								</ul>
							</sec:authorize>

							<!-- Finance -->
							<sec:authorize access="hasAnyRole('FINANCE','FINANCE-MANAGER')">
								<ul class="navbar-nav me-auto mb-2 mb-md-0">
									<li class="nav-item"><a id="link-products-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/Product/productList">Products</a>
									</li>
									<li class="nav-item"><a id="link-quotation-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/Quotation/list">Quotation</a>
									</li>
									<li class="nav-item"><a id="link-myAction-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/MyAction/list">My
											Action</a></li>
									<li class="nav-item"><a id="link-orders-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/Order/list">Orders</a></li>

									<li class="nav-item"><a id="link-signature-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/Signature">Signature</a></li>
								</ul>
							</sec:authorize>

							<sec:authorize access="hasAnyRole('PROCESSING', 'RISK', 'CEO')">
								<ul class="navbar-nav me-auto mb-2 mb-md-0">
									<li class="nav-item"><a class="nav-link " id="link-products-mobile"
											aria-current="page"
											href="${pageContext.request.contextPath}/Product/productList">Products</a>
									</li>
									<li class="nav-item"><a id="link-quotation-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/Quotation/list">Quotation</a>
									</li>
									<li class="nav-item"><a id="link-myAction-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/MyAction/list">My
											Action</a></li>
									<li class="nav-item"><a id="link-orders-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/Order/list">Orders</a></li>
									<li class="nav-item"><a id="link-tpaFile-mobile" class="nav-link "
											aria-current="page" href="${pageContext.request.contextPath}/TPA/list">TPA
											File</a></li>
									<li class="nav-item"><a id="link-merchantRegistration-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/merchantReg/list">Merchant
											Registration</a></li>
									<li class="nav-item"><a id="link-callback-mobile" class="nav-link"
											aria-current="page"
											href="${pageContext.request.contextPath}/CallBack/list">Callback</a>
									</li>
									<li class="nav-item"><a id="link-merchant-old-data-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/MerchantData/list">Merchant
											Data</a></li>
									<li class="nav-item"><a id="link-experian-search-mobile" class="nav-link"
											aria-current="page"
											href="${pageContext.request.contextPath}/Experian/Search">Experian
											Report</a>
									</li>
								</ul>
							</sec:authorize>

							<sec:authorize
								access="hasAnyRole('SYNERGY-VETDOC', 'SYNERGY-RISK', 'SYNERGY-APPROVAL','SYNERGY-WELCOME')">
								<ul class="navbar-nav me-auto mb-2 mb-md-0">
									<li class="nav-item"><a id="link-myAction-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/MyAction/list">My
											Action</a></li>
								</ul>
							</sec:authorize>


							<sec:authorize access="hasRole('ADMIN')">
								<ul class="navbar-nav me-auto mb-2 mb-md-0">
									<li class="nav-item"><a id="link-products-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/Product/productList">Products</a>
									</li>
									<li class="nav-item"><a id="link-quotation-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/Quotation/list">Quotation</a>
									</li>
									<li class="nav-item"><a id="link-myAction-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/MyAction/list">My
											Action</a></li>
									<li class="nav-item"><a id="link-orders-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/Order/list">Orders</a></li>
									<li class="nav-item"><a id="link-tpaFile-mobile" class="nav-link "
											aria-current="page" href="${pageContext.request.contextPath}/TPA/list">TPA
											File</a></li>
									<li class="nav-item"><a id="link-merchantRegistration-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/merchantReg/list">Merchant
											Registration</a></li>
									<li class="nav-item"><a id="link-signature-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/Signature">Signature</a></li>
									<li class="nav-item"><a id="link-merchant-old-data-mobile" class="nav-link "
											aria-current="page"
											href="${pageContext.request.contextPath}/MerchantData/list">Merchant
											Data</a></li>
									<li class="nav-item"><a id="link-userApproval-mobile" class="nav-link "
											aria-current="page" href="${pageContext.request.contextPath}/Approvals">User
											Approval</a></li>
									<li class="nav-item"><a id="link-experian-search-mobile" class="nav-link"
											aria-current="page"
											href="${pageContext.request.contextPath}/Experian/Search">Experian
											Report</a>
									</li>
								</ul>
							</sec:authorize>
						</div>
						<ul class="navbar-nav justify-content-start flex-grow-0">
							<a href="${pageContext.request.contextPath}/logout"
								style="margin: 5rem; border-radius: 4px; background-color: #f4f4f4; font-family: Inter; font-size: 14px; font-weight: 500; font-stretch: normal; font-style: normal; line-height: 1.33; letter-spacing: normal; text-align: center; color: #005baa;"
								class="btn btn-logout">Logout</a>
						</ul>

					</div>
				</div>
			</nav>



			<nav id="desktop-view" class="navbar navbar-expand-lg navbar-light bg-light fixed-top bg-white shadow">
				<div class="container">
					<a class="navbar-brand" href="#"> <img height="40"
							src="${pageContext.request.contextPath}/resources/images/logo.svg">
					</a>
					<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
						data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
						aria-expanded="false" aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse" id="navbarSupportedContent">
						<!-- Merchant -->
						<sec:authorize access="hasRole('MERCHANT')">
							<ul class="navbar-nav justify-content-start flex-grow-1 pe-3">
								<li class="nav-item"><a class="nav-link " id="merchant-quotation" aria-current="page"
										href="${pageContext.request.contextPath}/MerchantOrder">Quotations</a>
								</li>
								<li class="nav-item"><a class="nav-link " id="merchant-order" aria-current="page"
										href="${pageContext.request.contextPath}/MerchantOrder/Order">Orders</a>
								</li>
								<li class="nav-item"><a class="nav-link " id="merchant-credentials" aria-current="page"
										href="${pageContext.request.contextPath}/MerchantOrder/Credential">Credentials</a>
								</li>
							</ul>
						</sec:authorize>

						<!-- Sales -->
						<sec:authorize access="hasAnyRole('SALES','SALES-MANAGER')">
							<ul class="navbar-nav me-auto mb-2 mb-md-0">
								<li class="nav-item"><a id="link-products" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/Product/productList">Products</a>
								</li>
								<li class="nav-item"><a id="link-quotation" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/Quotation/list">Quotation</a>
								</li>
								<li class="nav-item"><a id="link-myAction" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/MyAction/list">My
										Action</a></li>
								<li class="nav-item"><a id="link-orders" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/Order/list">Orders</a></li>
								<li class="nav-item"><a id="link-signature" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/Signature">Signature</a></li>
							</ul>
						</sec:authorize>

						<!-- Finance -->
						<sec:authorize access="hasAnyRole('FINANCE','FINANCE-MANAGER')">
							<ul class="navbar-nav me-auto mb-2 mb-md-0">
								<li class="nav-item"><a id="link-products" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/Product/productList">Products</a>
								</li>
								<li class="nav-item"><a id="link-quotation" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/Quotation/list">Quotation</a>
								</li>
								<li class="nav-item"><a id="link-myAction" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/MyAction/list">My
										Action</a></li>
								<li class="nav-item"><a id="link-orders" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/Order/list">Orders</a></li>

								<li class="nav-item"><a id="link-signature-mobile" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/Signature">Signature</a></li>
							</ul>
						</sec:authorize>

						<sec:authorize access="hasAnyRole('PROCESSING', 'RISK', 'CEO')">
							<ul class="navbar-nav me-auto mb-2 mb-md-0" style="font-size: smaller;">
								<li class=" nav-item"><a class="nav-link " id="link-products" aria-current="page"
										href="${pageContext.request.contextPath}/Product/productList">Products</a>
								</li>
								<li class="nav-item"><a id="link-quotation" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/Quotation/list">Quotation</a>
								</li>
								<li class="nav-item"><a id="link-myAction" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/MyAction/list">My
										Action</a></li>
								<li class="nav-item"><a id="link-orders" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/Order/list">Orders</a></li>
								<li class="nav-item"><a id="link-tpaFile" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/TPA/list">TPA File</a></li>
								<li class="nav-item"><a id="link-merchantRegistration" class="nav-link "
										aria-current="page"
										href="${pageContext.request.contextPath}/merchantReg/list">Merchant
										Registration</a></li>
								<li class="nav-item"><a id="link-callback" class="nav-link" aria-current="page"
										href="${pageContext.request.contextPath}/CallBack/list">Callback</a>
								</li>
								<li class="nav-item"><a id="link-merchant-old-data" class="nav-link "
										aria-current="page"
										href="${pageContext.request.contextPath}/MerchantData/list">Merchant
										Data</a></li>
								<li class="nav-item"><a id="link-experian-search" class="nav-link" aria-current="page"
										href="${pageContext.request.contextPath}/Experian/Search">Experian
										Report</a>
							</ul>
						</sec:authorize>

						<sec:authorize
							access="hasAnyRole('SYNERGY-VETDOC', 'SYNERGY-RISK', 'SYNERGY-APPROVAL','SYNERGY-WELCOME')">
							<ul class="navbar-nav me-auto mb-2 mb-md-0">
								<li class="nav-item"><a id="link-myAction" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/MyAction/list">My
										Action</a></li>
							</ul>
						</sec:authorize>


						<sec:authorize access="hasRole('ADMIN')">
							<ul class="navbar-nav me-auto mb-2 mb-md-0" style="font-size: smaller;">
								<li class="nav-item"><a id="link-products" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/Product/productList">Products</a>
								</li>
								<li class="nav-item"><a id="link-quotation" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/Quotation/list">Quotation</a>
								</li>
								<li class="nav-item"><a id="link-myAction" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/MyAction/list">My
										Action</a></li>
								<li class="nav-item"><a id="link-orders" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/Order/list">Orders</a></li>
								<li class="nav-item"><a id="link-tpaFile" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/TPA/list">TPA File</a></li>
								<li class="nav-item"><a id="link-merchantRegistration" class="nav-link "
										aria-current="page"
										href="${pageContext.request.contextPath}/merchantReg/list">Merchant
										Registration</a></li>
								<li class="nav-item"><a id="link-signature" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/Signature">Signature</a></li>
								<li class="nav-item"><a id="link-merchant-old-data" class="nav-link "
										aria-current="page"
										href="${pageContext.request.contextPath}/MerchantData/list">Merchant
										Data</a></li>
								<li class="nav-item"><a id="link-userApproval" class="nav-link " aria-current="page"
										href="${pageContext.request.contextPath}/Approvals">User
										Approval</a></li>
								<li class="nav-item"><a id="link-experian-search" class="nav-link" aria-current="page"
										href="${pageContext.request.contextPath}/Experian/Search">Experian
										Report</a>
							</ul>
						</sec:authorize>
						<form class="d-flex">
							<a href="${pageContext.request.contextPath}/logout"
								class="btn btn-sm btn-primary">Logout</a>
							<!-- <a href="<c:url value=" /logout" />">Logout</a> -->
						</form>
					</div>
				</div>
			</nav>
		</body>
		<style>
			/* Extra small devices (phones, 600px and down) */
			@media only screen and (max-width: 600px) {
				.navbar-nav .nav-link {
					padding-right: 10px !important;
					padding-left: 10px !important;
				}

				#desktop-view {
					display: none;
				}

				#mobile-view {
					display: block;
				}
			}

			/* Small devices (portrait tablets and large phones, 600px and up) */
			@media only screen and (min-width: 600px) {
				.navbar-nav .nav-link {
					padding-right: 0px;
					padding-left: 0px;
				}

				#desktop-view {
					display: none;
				}

				#mobile-view {
					display: block;
				}
			}

			/* Medium devices (landscape tablets, 768px and up) */
			@media only screen and (min-width: 768px) {
				#desktop-view {
					display: block;
				}

				#mobile-view {
					display: none;
				}
			}

			/* Large devices (laptops/desktops, 992px and up) */
			@media only screen and (min-width: 992px) {
				#desktop-view {
					display: block;
				}

				#mobile-view {
					display: none;
				}
			}

			/* Extra large devices (large laptops and desktops, 1200px and up) */
			@media only screen and (min-width: 1200px) {
				#desktop-view {
					display: block;
				}

				#mobile-view {
					display: none;
				}
			}

			.link-active {
				padding-left: 10px;
				padding-right: 10px;
				width: fit-content;
				color: #fff !important;
				border-radius: 50px;
				background-color: #2e5caa !important;
			}
		</style>

		<script>
			$(document)
				.ready(function () {
					const currentURL = "${ requestScope['javax.servlet.forward.request_uri']}"

					switch (currentURL) {
						case "${pageContext.request.contextPath}/Product/productList":
							const link = document
								.getElementById("link-products")
							link.className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/Quotation/list":
							document.getElementById("link-quotation").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/Quotation/QuotationView":
							document.getElementById("link-quotation").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/MyAction/list":
							document.getElementById("link-myAction").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/Order/list":
							document.getElementById("link-orders").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/Order/OrderViewPaydee":
							document.getElementById("link-orders").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/Order/OrderView":
							document.getElementById("link-orders").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/TPA/list":
							document.getElementById("link-tpaFile").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/merchantReg/list":
							document
								.getElementById("link-merchantRegistration").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/merchantReg/registrationView":
							document
								.getElementById("link-merchantRegistration").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/Signature":
							document.getElementById("link-signature").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/MerchantOrder":
							document.getElementById("link-merchantOrder").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/Approvals":
							document.getElementById("link-userApproval").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/merchantData/list":
							document.getElementById("link-merchant-old-data").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/MerchantData/OrderView":
							document.getElementById("link-merchant-order-data").className = "nav-link link-active"
							break;
						case "${pageContext.request.contextPath}/Experian/Search":
							document.getElementById("link-experian-search").className = "nav-link link-active"
							break;
						default:
							break;
					}
				});
		</script>

		</html>
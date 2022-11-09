<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


		<!doctype html>
		<html lang="en">

		<head>

			<%@ page isELIgnored="false" %>
				<meta charset="utf-8">
				<meta name="viewport" content="width=device-width, initial-scale=1">
				<meta name="description" content="">
				<meta name="author" content="">
				<title>MOBI</title>


				<link rel="apple-touch-icon" sizes="57x57"
					href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-57x57.png">
				<link rel="apple-touch-icon" sizes="60x60"
					href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-60x60.png">
				<link rel="apple-touch-icon" sizes="72x72"
					href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-72x72.png">
				<link rel="apple-touch-icon" sizes="76x76"
					href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-76x76.png">
				<link rel="apple-touch-icon" sizes="114x114"
					href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-114x114.png">
				<link rel="apple-touch-icon" sizes="120x120"
					href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-120x120.png">
				<link rel="apple-touch-icon" sizes="144x144"
					href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-144x144.png">
				<link rel="apple-touch-icon" sizes="152x152"
					href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-152x152.png">
				<link rel="apple-touch-icon" sizes="180x180"
					href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-180x180.png">
				<link rel="icon" type="image/png" sizes="192x192"
					href="${pageContext.request.contextPath}/resources/images/favicon/android-icon-192x192.png">
				<link rel="icon" type="image/png" sizes="32x32"
					href="${pageContext.request.contextPath}/resources/images/favicon/favicon-32x32.png">
				<link rel="icon" type="image/png" sizes="96x96"
					href="${pageContext.request.contextPath}/resources/images/favicon/favicon-96x96.png">
				<link rel="icon" type="image/png" sizes="16x16"
					href="${pageContext.request.contextPath}/resources/images/favicon/favicon-16x16.png">
				<link rel="manifest" href="/manifest.json">
				<meta name="msapplication-TileColor" content="#ffffff">
				<meta name="msapplication-TileImage"
					content="${pageContext.request.contextPath}/resources/images/favicon/ms-icon-144x144.png">
				<meta name="theme-color" content="#f1f5fa">


				<!-- CDN moved to local -->
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/resources/js/fontawesome/css/fontawesome.min.css">
				<script src="${pageContext.request.contextPath}/resources/js/axios.js" type="text/javascript"></script>
				<script src="${pageContext.request.contextPath}/resources/js/moment.js" type="text/javascript"></script>
				<!-- CDN moved to local -->

				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap5.min.css">
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/resources/fonts/fonts.css">



		</head>

		<body>

			<main class="container mt110">
				<div class="row">
					<div class="col-md-12 col-lg-12">
						<div class="bg-white p-5 rounded">


							<form:form method="POST" action="walletRateEdit" modelAttribute="walletRate"
								accept-charset="utf-8">

								<div class="col-md-12 col-lg-12">
									<div class="bg-white  rounded form">
										<h5 class="mb-3  mt-5">Wallet Rates</h5>

										<div>
											<form:input type="hidden" path="id" />
											<form:input type="hidden" path="createdOn" />
										</div>

										<div class="row g-3">
											<div class="col-sm-3  form-floating mb-3">
												<div class="mb-3">
													<form:label path="name" class="form-label">Ref Name</form:label>
													<form:input path="name" class="form-control" placeholder="" />
												</div>
											</div>

											<div class="col-sm-3  form-floating mb-3">
												<div class="mb-3">
													<form:label path="productType" class="form-label">Product Type
													</form:label>
													<form:select class="form-select" name="productType"
														path="productType" id="productType_HostRate">

														<c:forEach items="${productTypes}" var="productTypes">
															<form:option value="${productTypes.value}">
																${productTypes.name}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>
										</div>


										<!-- PAYMENT ICONS FOLLOWED BY LOCAL DEBIT MERCHANT-->


										<div>

											<div class="row g-3 walletData">
												<h5 class="mb-3  mt-3 fw-bold">Wallet Details</h5>
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="boostMDREcomm" class="form-label">Boost MDR
															Ecomm</form:label>
														<form:input path="boostMDREcomm" class="form-control"
															placeholder="" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="boostMDRQR" class="form-label">Boost MDR QR
														</form:label>
														<form:input path="boostMDRQR" class="form-control"
															placeholder="" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="grabMDREcomm" class="form-label">Grab MDR
															Ecomm</form:label>
														<form:input path="grabMDREcomm" class="form-control"
															placeholder="" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="grabMDRQR" class="form-label">Grab MDR QR
														</form:label>
														<form:input path="grabMDRQR" class="form-control"
															placeholder="" />
													</div>
												</div>


												<!--                 rk added -->
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="tngMDREcomm" class="form-label">Tng MDR Ecomm
														</form:label>
														<form:input path="tngMDREcomm" class="form-control"
															placeholder="" />
													</div>
												</div>


												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="tngMDRQR" class="form-label">Tng MDR QR
														</form:label>
														<form:input path="tngMDRQR" class="form-control"
															placeholder="" />
													</div>
												</div>


												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="shopeepayMDREcomm" class="form-label">
															Shopeepay MDR Ecomm
														</form:label>
														<form:input path="shopeepayMDREcomm" class="form-control"
															placeholder="" />
													</div>
												</div>


												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="shopeepayMDRQR" class="form-label">Shopeepay
															MDR QR
														</form:label>
														<form:input path="shopeepayMDRQR" class="form-control"
															placeholder="" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="tngSettlement" class="form-label">Tng
															Settlement</form:label>
														<form:input path="TngSettlement" class="form-control"
															placeholder="" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="shopeepaySettlement" class="form-label">
															Shopeepay
															Settlement</form:label>
														<form:input path="shopeepaySettlement" class="form-control"
															placeholder="" />
													</div>
												</div>

												<!-- 												rk added -->



												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="fPXMDR_RM" class="form-label">FPX MDR (RM)
														</form:label>
														<form:input path="fPXMDR_RM" class="form-control"
															placeholder="" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="fPXMDR_Percent" class="form-label">FPX MDR (%)
														</form:label>
														<form:input path="fPXMDR_Percent" class="form-control"
															placeholder="" />
													</div>
												</div>




												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="boostSettlement" class="form-label">Boost
															Settlement</form:label>
														<form:input path="boostSettlement" class="form-control"
															placeholder="" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="grabSettlement" class="form-label">Grab
															Settlement</form:label>
														<form:input path="grabSettlement" class="form-control"
															placeholder="" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="fpxSettlement" class="form-label">FPX
															Settlement</form:label>
														<form:input path="fpxSettlement" class="form-control"
															placeholder="" />
													</div>
												</div>



											</div>
										</div>

										<!-- PAYMENT ICONS FOLLOWED BY LOCA DEBIT MERCHANT -->
										<div class="row g-3 mb-5">
											<div class="col-sm-6  form-floating mb-3">
												<div class="mb-3"></div>
											</div>

											<div class="col-sm-6  form-floating mb-3">
												<div class="mb-3 ">
													<label for="email" class="form-label col-12">&nbsp;</label> <input
														type="submit" value="Edit WalletRate" id="walletRateEditBtn"
														class="btn btn-mobi btn-alt float-end">
												</div>
											</div>
										</div>
									</div>
								</div>
						</div>
					</div>
					</form:form>
				</div>
				</div>
				</div>
				</div>

			</main>


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

			<script type="text/javascript">
				window.onload = function () {

					//Disable Addition of Product & Host Rate for the Sales Access
					var role = "${loggedUserRole }";

					if (role != 'finance-manager') {
						$('#walletRateEditBtn').prop('disabled', true);
					}
				}
			</script>


		</body>

		</html>
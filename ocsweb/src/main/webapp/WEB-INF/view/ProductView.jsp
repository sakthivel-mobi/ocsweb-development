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
			<main class="container mt50">
				<div class="row">
					<div class="col-md-12 col-lg-12">
						<div class="bg-white p-5 rounded">

							<div class="col-md-12 col-lg-12">
								<form:form id="productDetailsForm" method="POST" action="" modelAttribute="product"
									accept-charset="utf-8">
									<div class="bg-white  rounded form">
										<h5 class="mb-3  mt-3 fw-bold">Product Details</h5>

										<div class="row g-3">
											<div class="col-sm-3  form-floating mb-3">
												<div>
													<form:input type="hidden" path="id" value="${product.id }" />
													<form:input type="hidden" path="createdOn"
														value="${product.createdOn }" />
												</div>
												<div class="mb-3">
													<form:label path="productName" class="form-label">Product Name
													</form:label>
													<form:input path="productName" class="form-control" placeholder=""
														id="productName" />
												</div>
											</div>

											<div class="col-sm-3  form-floating mb-3">
												<div class="mb-3">
													<form:label path="productType" class="form-label">Product Type
													</form:label>
													<form:select class="form-select" name="productType"
														path="productType" id="productType">
														<form:option value="0" selected="true">----Select----
														</form:option>
														<c:forEach items="${productTypes}" var="productTypes">
															<form:option value="${productTypes.value}">
																${productTypes.name}</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>

											<div class="col-sm-3  form-floating mb-3">
												<div class="mb-3">
													<form:label path="hostType" class="form-label">Host Type
													</form:label>
													<form:select class="form-select" name="hostType" path="hostType"
														id="hostType">
														<form:option value="0" selected="true">----Select----
														</form:option>
														<c:forEach items="${hostTypes}" var="hostTypes">
															<form:option value="${hostTypes.value}">${hostTypes.name}
															</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>


											<div class="col-sm-3  form-floating mb-3">
												<div class="mb-3">
													<form:label path="includeWallet" class="form-label">Include Wallet
													</form:label>
													<form:select class="form-select" name="includeWallet"
														path="includeWallet" id="includeWallet">
														<form:option value="Yes">Yes</form:option>
														<form:option value="No">No</form:option>
													</form:select>
												</div>
											</div>
											<div class="col-sm-3  form-floating mb-3">
												<div class="mb-3">
													<form:label path="showInQuote" class="form-label">Show In Quote
													</form:label>
													<form:select class="form-select" name="showInQuote"
														path="showInQuote" id="showInQuote">
														<form:option value="Yes">Yes</form:option>
														<form:option value="No">No</form:option>
													</form:select>
												</div>
											</div>

											<div class="col-sm-3  form-floating mb-3">
												<div class="mb-3">
													<form:label path="payLater" class="form-label">Pay Later
													</form:label>
													<form:select class="form-control" name="payLater" path="payLater"
														id="payLater">
														<form:option value="Yes">Yes</form:option>
														<form:option value="No">No</form:option>
													</form:select>
												</div>
											</div>


											<div class="col-sm-3  form-floating mb-3">
												<div class="mb-3">
													<form:label path="subscription" class="form-label">Subscription
													</form:label>
													<form:select class="form-select" name="subscription"
														path="subscription" id="subscription">
														<form:option value="365">1 Year</form:option>
														<form:option value="730">2 Years</form:option>
														<form:option value="1095">3 Years</form:option>
														<form:option value="1825">5 Years</form:option>
														<form:option value="36500">Lifetime</form:option>
													</form:select>
												</div>
											</div>

											<div class="col-sm-3  form-floating mb-3">
												<div class="mb-3">
													<form:label path="productSettlement" class="form-label">Product
														Settlement(in days)</form:label>
													<form:select class="form-select" name="productSettlement"
														path="productSettlement" id="productSettlement">
														<form:option value="1">T+1</form:option>
														<form:option value="3">T+3</form:option>
														<form:option value="5">T+5</form:option>
													</form:select>
												</div>
											</div>


											<div class="col-sm-3  form-floating mb-3">
												<div class="mb-3">
													<form:label path="unitPrice" class="form-label">Unit Price
													</form:label>
													<form:input path="unitPrice" class="form-control"
														placeholder="Unit Price" />
												</div>
											</div>


											<div class="col-sm-3  form-floating mb-3">
												<div class="mb-3">
													<label class="form-label">
														Host Rate</label>
													<select class="form-control" id="hostRate">

													</select>
												</div>
											</div>

											<div class="col-sm-3  form-floating mb-3">
												<div class="mb-3">
													<label class="form-label">Wallet Rate</label>
													<select class="form-control" id="walletRate"></select>
												</div>
											</div>

										</div>
										<div id=ezySplitWalletData>

											<div class="row g-3 walletData">
												<h5 class="mb-3  mt-3 fw-bold">Wallet Details</h5>
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardEzySplitMDRRate.boostMDREcomm"
															class="form-label">Boost MDR Ecomm</form:label>
														<form:input path="standardEzySplitMDRRate.boostMDREcomm"
															id="boostMDREcomm_Ezysplit" class="form-control"
															placeholder="" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardEzySplitMDRRate.boostMDRQR"
															class="form-label">Boost MDR QR</form:label>
														<form:input path="standardEzySplitMDRRate.boostMDRQR"
															id="boostMDRQR_Ezysplit" class="form-control"
															placeholder="" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardEzySplitMDRRate.grabMDREcomm"
															class="form-label">Grab MDR Ecomm</form:label>
														<form:input path="standardEzySplitMDRRate.grabMDREcomm"
															id="grabMDREcomm_Ezysplit" class="form-control"
															placeholder="" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardEzySplitMDRRate.grabMDRQR"
															class="form-label">Grab MDR QR</form:label>
														<form:input path="standardEzySplitMDRRate.grabMDRQR"
															id="grabMDRQR_Ezysplit" class="form-control"
															placeholder="" />
													</div>
												</div>
												
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardEzySplitMDRRate.tngMDREcomm"
															class="form-label">Tng MDR Ecomm</form:label>
														<form:input path="standardEzySplitMDRRate.tngMDREcomm"
															id="tngMDREcomm_Ezysplit" class="form-control"
															placeholder="" />
													</div>
												</div>
												
												
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardEzySplitMDRRate.tngMDRQR"
															class="form-label">Tng MDR QR</form:label>
														<form:input path="standardEzySplitMDRRate.tngMDRQR"
															id="tngMDRQR_Ezysplit" class="form-control"
															placeholder="" />
													</div>
												</div>
												
												
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardEzySplitMDRRate.shopeepayMDREcomm"
															class="form-label">Shopeepay MDR Ecomm</form:label>
														<form:input path="standardEzySplitMDRRate.shopeepayMDREcomm"
															id="shopeepayMDREcomm_Ezysplit" class="form-control"
															placeholder="" />
													</div>
												</div>
												
												
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardEzySplitMDRRate.shopeepayMDRQR"
															class="form-label">Shopeepay MDR QR</form:label>
														<form:input path="standardEzySplitMDRRate.shopeepayMDRQR"
															id="shopeepayMDRQR_Ezysplit" class="form-control"
															placeholder="" />
													</div>
												</div>
												
												

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardEzySplitMDRRate.fPXMDR_RM"
															class="form-label">FPX MDR (RM)</form:label>
														<form:input path="standardEzySplitMDRRate.fPXMDR_RM"
															id="fPXMDR_RM_Ezysplit" class="form-control"
															placeholder="" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardEzySplitMDRRate.fPXMDR_Percent"
															class="form-label">FPX MDR (%)</form:label>
														<form:input path="standardEzySplitMDRRate.fPXMDR_Percent"
															id="fPXMDR_Percent_Ezysplit" class="form-control"
															placeholder="" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardEzySplitMDRRate.boostSettlement"
															class="form-label">Boost Settlement</form:label>
														<form:input path="standardEzySplitMDRRate.boostSettlement"
															id="boostSettlement_Ezysplit" class="form-control"
															placeholder="" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardEzySplitMDRRate.grabSettlement"
															class="form-label">Grab Settlement</form:label>
														<form:input path="standardEzySplitMDRRate.grabSettlement"
															id="grabSettlement_Ezysplit" class="form-control"
															placeholder="" />
													</div>
												</div>


	<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardEzySplitMDRRate.tngSettlement"
															class="form-label">Tng Settlement</form:label>
														<form:input path="standardEzySplitMDRRate.tngSettlement"
															id="tngSettlement_Ezysplit" class="form-control"
															placeholder="" />
													</div>
												</div>
												
												
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardEzySplitMDRRate.shopeepaySettlement"
															class="form-label">Shopeepay Settlement</form:label>
														<form:input path="standardEzySplitMDRRate.shopeepaySettlement"
															id="shopeepaySettlement_Ezysplit" class="form-control"
															placeholder="" />
													</div>
												</div>






												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardEzySplitMDRRate.fpxSettlement"
															class="form-label">FPX Settlement</form:label>
														<form:input path="standardEzySplitMDRRate.fpxSettlement"
															id="fpxSettlement_Ezysplit" class="form-control"
															placeholder="" />
													</div>
												</div>
											</div>
										</div>

										<div id=nonEzySplitWalletData>
											<div class="row g-3 walletData">
												<h5 class="mb-3  mt-3 fw-bold">Wallet Details</h5>
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardmdrRate.boostMDREcomm"
															class="form-label">Boost MDR Ecomm</form:label>
														<form:input path="standardmdrRate.boostMDREcomm"
															id="boostMDREcomm" class="form-control" placeholder="" />
													</div>
													<div class="mb-3">
														<form:label path="standardmdrRate.boostMDRQR"
															class="form-label">Boost MDR QR</form:label>
														<form:input path="standardmdrRate.boostMDRQR" id="boostMDRQR"
															class="form-control" placeholder="" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardmdrRate.grabMDREcomm"
															class="form-label">Grab MDR Ecomm</form:label>
														<form:input path="standardmdrRate.grabMDREcomm"
															id="grabMDREcomm" class="form-control" placeholder="" />
													</div>
													<div class="mb-3">
														<form:label path="standardmdrRate.grabMDRQR" class="form-label">
															Grab MDR QR</form:label>
														<form:input path="standardmdrRate.grabMDRQR" id="grabMDRQR"
															class="form-control" placeholder="" />
													</div>
												</div>
<!-- 												rk added -->

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardmdrRate.tngMDREcomm"
															class="form-label">Tng MDR Ecomm</form:label>
														<form:input path="standardmdrRate.tngMDREcomm"
															id="tngMDREcomm" class="form-control" placeholder="" />
													</div>
													<div class="mb-3">
														<form:label path="standardmdrRate.tngMDRQR" class="form-label">
															Tng MDR QR</form:label>
														<form:input path="standardmdrRate.tngMDRQR" id="tngMDRQR"
															class="form-control" placeholder="" />
													</div>
												</div>
												
												
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardmdrRate.shopeepayMDREcomm"
															class="form-label">Shopeepay MDR Ecomm</form:label>
														<form:input path="standardmdrRate.shopeepayMDREcomm"
															id="shopeepayMDREcomm" class="form-control" placeholder="" />
													</div>
													<div class="mb-3">
														<form:label path="standardmdrRate.shopeepayMDRQR" class="form-label">
															Shopeepay MDR QR</form:label>
														<form:input path="standardmdrRate.shopeepayMDRQR" id="shopeepayMDRQR"
															class="form-control" placeholder="" />
													</div>
												</div>
												
                                  <!-- rk added -->
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardmdrRate.fPXMDR_RM" class="form-label">
															FPX MDR (RM)</form:label>
														<form:input path="standardmdrRate.fPXMDR_RM" id="fPXMDR_RM"
															class="form-control" placeholder="" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardmdrRate.fPXMDR_Percent"
															class="form-label">FPX MDR (%)</form:label>
														<form:input path="standardmdrRate.fPXMDR_Percent"
															id="fPXMDR_Percent" class="form-control" placeholder="" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardmdrRate.boostSettlement"
															class="form-label">Boost Settlement</form:label>
														<form:input path="standardmdrRate.boostSettlement"
															id="boostSettlement" class="form-control" placeholder="" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardmdrRate.grabSettlement"
															class="form-label">Grab Settlement</form:label>
														<form:input path="standardmdrRate.grabSettlement"
															id="grabSettlement" class="form-control" placeholder="" />
													</div>
												</div>
												
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardmdrRate.tngSettlement"
															class="form-label">Tng Settlement</form:label>
														<form:input path="standardmdrRate.tngSettlement"
															id="tngSettlement" class="form-control" placeholder="" />
													</div>
												</div>
												
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardmdrRate.shopeepaySettlement"
															class="form-label">Shopeepay Settlement</form:label>
														<form:input path="standardmdrRate.shopeepaySettlement"
															id="shopeepaySettlement" class="form-control" placeholder="" />
													</div>
												</div>
												

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:label path="standardmdrRate.fpxSettlement"
															class="form-label">FPX Settlement</form:label>
														<form:input path="standardmdrRate.fpxSettlement"
															id="fpxSettlement" class="form-control" placeholder="" />
													</div>
												</div>
											</div>
										</div>

										<div id="nonEzysplitProductDiv">


											<!-- PAYMENT ICONS FOLLOWED BY LOCAL DEBIT MERCHANT-->

											<div class="row g-3">

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3"></div>
												</div>

												<div class="col-sm-2  col-4 form-floating mb-3">
													<div class="mb-3 text-center">
														<img height="40"
															src="${pageContext.request.contextPath}/resources/images/visa.png">
													</div>
												</div>


												<div class="col-sm-2  col-4 form-floating mb-3">
													<div class="mb-3 text-center">
														<img height="40"
															src="${pageContext.request.contextPath}/resources/images/mastercard.png">
													</div>
												</div>


												<div class="col-sm-2  col-4 form-floating mb-3">
													<div class="mb-3 text-center">
														<img height="40"
															src="${pageContext.request.contextPath}/resources/images/union_pay.png">
													</div>
												</div>

											</div>


											<div class="row g-3">
												<div
													class="col-sm-3  form-floating mb-3 d-flex align-items-center justify-content-center">
													<div class="mb-3 small ">Local Debit Merchant MDR (%)</div>
												</div>


												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<form:input id="loc_Debit_Merch_Visa"
															path="standardmdrRate.loc_Debit_Merch_Visa"
															class="form-control" type="number" min="0" max="5"
															step="0.001" placeholder="Local Debit Merchant MDR (%)" />
													</div>
												</div>


												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<form:input id="loc_Debit_Merch_Master"
															path="standardmdrRate.loc_Debit_Merch_Master"
															class="form-control" type="number" min="0" max="5"
															step="0.001" placeholder="Local Debit Merchant MDR (%)" />
													</div>
												</div>

												<div class="col-sm-2 form-floating mb-3">
													<div class="mb-3">
														<form:input id="loc_Debit_Merch_Union"
															path="standardmdrRate.loc_Debit_Merch_Union"
															class="form-control" type="number" min="0" max="5"
															step="0.001" placeholder="Local Debit Merchant MDR (%)" />
													</div>
												</div>

											</div>



											<div class="row g-3">
												<div
													class="col-sm-3  form-floating mb-3 d-flex align-items-center justify-content-center">
													<div class="mb-3 small ">Local Credit Merchant MDR
														(%)</div>
												</div>


												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<form:input id="loc_Credit_Merch_Visa"
															path="standardmdrRate.loc_Credit_Merch_Visa"
															class="form-control" type="number" min="0" max="5"
															step="0.001" placeholder="Local Credit Merchant MDR (%)" />
													</div>
												</div>


												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<form:input id="loc_Credit_Merch_Master"
															path="standardmdrRate.loc_Credit_Merch_Master"
															class="form-control" type="number" min="0" max="5"
															step="0.001" placeholder="Local Credit Merchant MDR (%)" />
													</div>
												</div>

												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<form:input id="loc_Credit_Merch_Union"
															path="standardmdrRate.loc_Credit_Merch_Union"
															class="form-control" type="number" min="0" max="5"
															step="0.001" placeholder="Local Credit Merchant MDR (%)" />
													</div>
												</div>

											</div>



											<div class="row g-3">
												<div
													class="col-sm-3  form-floating mb-3 d-flex align-items-center justify-content-center">
													<div class="mb-3 small ">Foreign Debit Merchant MDR
														(%)</div>
												</div>


												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<form:input id="for_Debit_Merch_Visa"
															path="standardmdrRate.for_Debit_Merch_Visa"
															class="form-control" type="number" min="0" max="5"
															step="0.001" placeholder="Foreign Debit Merchant MDR (%)" />
													</div>
												</div>


												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<form:input id="for_Debit_Merch_Master"
															path="standardmdrRate.for_Debit_Merch_Master"
															class="form-control" type="number" min="0" max="5"
															step="0.001" placeholder="Foreign Debit Merchant MDR (%)" />
													</div>
												</div>

												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<form:input id="for_Debit_Merch_Union"
															path="standardmdrRate.for_Debit_Merch_Union"
															class="form-control" type="number" min="0" max="5"
															step="0.001" placeholder="Foreign Debit Merchant MDR (%)" />
													</div>
												</div>

											</div>



											<div class="row g-3">
												<div
													class="col-sm-3  form-floating mb-3 d-flex align-items-center justify-content-center">
													<div class="mb-3 small ">Foreign Credit Merchant MDR
														(%)</div>
												</div>


												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<form:input id="for_Credit_Merch_Visa"
															path="standardmdrRate.for_Credit_Merch_Visa"
															class="form-control" type="number" min="0" max="5"
															step="0.001"
															placeholder="Foreign Credit Merchant MDR (%)" />
													</div>
												</div>


												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<form:input id="for_Credit_Merch_Master"
															path="standardmdrRate.for_Credit_Merch_Master"
															class="form-control" type="number" min="0" max="5"
															step="0.001"
															placeholder="Foreign Credit Merchant MDR (%)" />
													</div>
												</div>

												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<form:input id="for_Credit_Merch_Union"
															path="standardmdrRate.for_Credit_Merch_Union"
															class="form-control" type="number" min="0" max="5"
															step="0.001"
															placeholder="Foreign Credit Merchant MDR (%)" />
													</div>
												</div>

											</div>
											<div id="HostMDRDiv" style="display: none;">

												<div class="row g-3">
													<div
														class="col-sm-3  form-floating mb-3 d-flex align-items-center justify-content-center">
														<div class="mb-3 small ">Local Debit Host MDR (%)</div>
													</div>


													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="loc_Debit_Host_Visa" readOnly="true"
																path="standardmdrRate.loc_Debit_Host_Visa"
																class="form-control" type="number" min="0" max="4"
																step="0.001" placeholder="Local Debit Host MDR (%)" />
														</div>
													</div>


													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="loc_Debit_Host_Master" readOnly="true"
																path="standardmdrRate.loc_Debit_Host_Master"
																class="form-control" type="number" min="0" max="4"
																step="0.001" placeholder="Local Debit Host MDR (%)" />
														</div>
													</div>

													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="loc_Debit_Host_Union" readOnly="true"
																path="standardmdrRate.loc_Debit_Host_Union"
																class="form-control" type="number" min="0" max="4"
																step="0.001" placeholder="Local Debit Host MDR (%)" />
														</div>
													</div>

												</div>


												<div class="row g-3">
													<div
														class="col-sm-3  form-floating mb-3 d-flex align-items-center justify-content-center">
														<div class="mb-3 small ">Local Credit Host MDR (%)</div>
													</div>


													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="loc_Credit_Host_Visa" readOnly="true"
																path="standardmdrRate.loc_Credit_Host_Visa"
																class="form-control" type="number" min="0" max="4"
																step="0.001" placeholder="Local Credit Host MDR (%)" />
														</div>
													</div>


													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="loc_Credit_Host_Master" readOnly="true"
																path="standardmdrRate.loc_Credit_Host_Master"
																class="form-control" type="number" min="0" max="4"
																step="0.001" placeholder="Local Credit Host MDR (%)" />
														</div>
													</div>

													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="loc_Credit_Host_Union" readOnly="true"
																path="standardmdrRate.loc_Credit_Host_Union"
																class="form-control" type="number" min="0" max="4"
																step="0.001" placeholder="Local Credit Host MDR (%)" />
														</div>
													</div>

												</div>

												<div class="row g-3">
													<div
														class="col-sm-3  form-floating mb-3 d-flex align-items-center justify-content-center">
														<div class="mb-3 small ">Foreign Debit Host MDR (%)</div>
													</div>


													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="for_Debit_Host_Visa" readOnly="true"
																path="standardmdrRate.for_Debit_Host_Visa"
																class="form-control" type="number" min="0" max="4"
																step="0.001" placeholder="Foreign Debit Host MDR (%)" />
														</div>
													</div>


													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="for_Debit_Host_Master" readOnly="true"
																path="standardmdrRate.for_Debit_Host_Master"
																class="form-control" type="number" min="0" max="4"
																step="0.001" placeholder="Foreign Debit Host MDR (%)" />
														</div>
													</div>

													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="for_Debit_Host_Union" readOnly="true"
																path="standardmdrRate.for_Debit_Host_Union"
																class="form-control" type="number" min="0" max="4"
																step="0.001" placeholder="Foreign Debit Host MDR (%)" />
														</div>
													</div>

												</div>

												<div class="row g-3">
													<div
														class="col-sm-3  form-floating mb-3 d-flex align-items-center justify-content-center">
														<div class="mb-3 small ">Foreign Credit Host MDR (%)</div>
													</div>


													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="for_Credit_Host_Visa" readOnly="true"
																path="standardmdrRate.for_Credit_Host_Visa"
																class="form-control" type="number" min="0" max="4"
																step="0.001"
																placeholder="Foreign Credit Host MDR (%)" />
														</div>
													</div>


													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="for_Credit_Host_Master" readOnly="true"
																path="standardmdrRate.for_Credit_Host_Master"
																class="form-control" type="number" min="0" max="4"
																step="0.001"
																placeholder="Foreign Credit Host MDR (%)" />
														</div>
													</div>

													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="for_Credit_Host_Union" readOnly="true"
																path="standardmdrRate.for_Credit_Host_Union"
																class="form-control" type="number" min="0" max="4"
																step="0.001"
																placeholder="Foreign Credit Host MDR (%)" />
														</div>
													</div>

												</div>
											</div>

											<div id="MobiMDRDiv" style="display: none;">

												<div class="row g-3">
													<div
														class="col-sm-3  form-floating mb-3 d-flex align-items-center justify-content-center">
														<div class="mb-3 small ">Local Debit Mobi MDR (%)</div>
													</div>


													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="loc_Debit_Mobi_Visa" readOnly="true"
																path="standardmdrRate.loc_Debit_Mobi_Visa"
																class="form-control"
																placeholder="Local Debit Mobi MDR (%)" />
														</div>
													</div>


													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="loc_Debit_Mobi_Master" readOnly="true"
																path="standardmdrRate.loc_Debit_Mobi_Master"
																class="form-control"
																placeholder="Local Debit Mobi MDR (%)" />
														</div>
													</div>

													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="loc_Debit_Mobi_Union" readOnly="true"
																path="standardmdrRate.loc_Debit_Mobi_Union"
																class="form-control"
																placeholder="Local Debit Mobi MDR (%)" />
														</div>
													</div>

												</div>

												<div class="row g-3">
													<div
														class="col-sm-3  form-floating mb-3 d-flex align-items-center justify-content-center">
														<div class="mb-3 small ">Local Credit Mobi MDR (%)</div>
													</div>


													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="loc_Credit_Mobi_Visa" readOnly="true"
																path="standardmdrRate.loc_Debit_Mobi_Visa"
																class="form-control"
																placeholder="Local Credit Mobi MDR (%)" />
														</div>
													</div>


													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="loc_Credit_Mobi_Master" readOnly="true"
																path="standardmdrRate.loc_Debit_Mobi_Master"
																class="form-control"
																placeholder="Local Credit Mobi MDR (%)" />
														</div>
													</div>

													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="loc_Credit_Mobi_Union" readOnly="true"
																path="standardmdrRate.loc_Debit_Mobi_Union"
																class="form-control"
																placeholder="Local Credit Mobi MDR (%)" />
														</div>
													</div>

												</div>

												<div class="row g-3">
													<div
														class="col-sm-3  form-floating mb-3 d-flex align-items-center justify-content-center">
														<div class="mb-3 small ">Foreign Debit Mobi MDR (%)</div>
													</div>


													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="for_Debit_Mobi_Visa" readOnly="true"
																path="standardmdrRate.for_Debit_Mobi_Visa"
																class="form-control"
																placeholder="Foreign Debit Mobi MDR (%)" />
														</div>
													</div>


													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="for_Debit_Mobi_Master" readOnly="true"
																path="standardmdrRate.for_Debit_Mobi_Master"
																class="form-control"
																placeholder="Foreign Debit Mobi MDR (%)" />
														</div>
													</div>

													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="for_Debit_Mobi_Union" readOnly="true"
																path="standardmdrRate.for_Debit_Mobi_Union"
																class="form-control"
																placeholder="Foreign Debit Mobi MDR (%)" />
														</div>
													</div>

												</div>

												<div class="row g-3">
													<div
														class="col-sm-3  form-floating mb-3 d-flex align-items-center justify-content-center">
														<div class="mb-3 small ">Foreign Credit Mobi MDR (%)</div>
													</div>


													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="for_Credit_Mobi_Visa" readOnly="true"
																path="standardmdrRate.for_Credit_Mobi_Visa"
																class="form-control"
																placeholder="Foreign Credit Mobi MDR (%)" />
														</div>
													</div>


													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="for_Credit_Mobi_Master" readOnly="true"
																path="standardmdrRate.for_Credit_Mobi_Master"
																class="form-control"
																placeholder="Foreign Credit Mobi MDR (%)" />
														</div>
													</div>

													<div class="col-sm-2  form-floating mb-3">
														<div class="mb-3">
															<form:input id="for_Credit_Mobi_Union" readOnly="true"
																path="standardmdrRate.for_Credit_Mobi_Union"
																class="form-control"
																placeholder="Foreign Credit Mobi MDR (%)" />
														</div>
													</div>

												</div>
											</div>

										</div>

										<div id="ezysplitProductDiv">
											<!-- PAYMENT ICONS FOLLOWED BY LOCAL DEBIT MERCHANT-->

											<div class="row g-3" style="display: none;">
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3"></div>
												</div>

												<div class="col-sm-2  col-4 form-floating mb-3">
													<div class="mb-3 text-center">
														<img height="40"
															src="${pageContext.request.contextPath}/resources/images/mastercard.png">
													</div>
												</div>
												<div class="col-sm-2  col-4 form-floating mb-3">
													<div class="mb-3 text-center">
														<img height="40"
															src="${pageContext.request.contextPath}/resources/images/mastercard.png">
													</div>
												</div>
											</div>

											<div class="row">
												<div class="col-6">

													<div style="padding-right: 125px;
													text-align: right;
													text-align-last: right;
													-ms-text-align-last: right;
													-moz-text-align-last: right;">
														<img height="40"
															src="${pageContext.request.contextPath}/resources/images/mastercard.png">

													</div>

													<h5 class="mb-3  mt-3 fw-bold">Instalment 3</h5>


													<div class="row g-3">
														<div
															class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
															<div class="mb-3 small ">Merchant MDR (%)</div>
														</div>

														<div class="col-sm-4  form-floating mb-3">
															<div class="mb-3">
																<form:input id="loc_Credit_Merch_Master_Insta3"
																	path="standardEzySplitMDRRate.loc_Credit_Merch_Master_Insta3"
																	class="form-control" type="number" min="0" max="5"
																	step="0.001"
																	placeholder="Local Credit Merchant MDR (%)" />
															</div>
														</div>

													</div>

													<div class="row g-3">
														<div
															class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
															<div class="mb-3 small ">Customer MDR (%)</div>
														</div>

														<div class="col-sm-4  form-floating mb-3">
															<div class="mb-3">
																<form:input id="loc_Credit_Cus_Master_Insta3"
																	path="standardEzySplitMDRRate.loc_Credit_Cus_Master_Insta3"
																	class="form-control"
																	placeholder="Local Credit Customer MDR (%)" />
															</div>
														</div>
													</div>

													<div class="row g-3" style="display: none;">
														<div
															class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
															<div class="mb-3 small ">Host MDR (%)</div>
														</div>


														<div class="col-sm-4  form-floating mb-3">
															<div class="mb-3">
																<form:input id="loc_Credit_Host_Master_Insta3"
																	path="standardEzySplitMDRRate.loc_Credit_Host_Master_Insta3"
																	class="form-control" type="number" min="0" max="4"
																	step="0.001" placeholder="Local Credit Host MDR (%)"
																	readonly="true" />
															</div>
														</div>

													</div>
													<div class="row g-3" style="display: none;">
														<div
															class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
															<div class="mb-3 small ">Mobi MDR (%)</div>
														</div>
														<div class="col-sm-4  form-floating mb-3">
															<div class="mb-3">
																<form:input id="loc_Credit_Mobi_Master_Insta3"
																	path="standardEzySplitMDRRate.loc_Credit_Mobi_Master_Insta3"
																	class="form-control"
																	placeholder="Local Credit Mobi MDR (%)"
																	readonly="true" />
															</div>
														</div>
													</div>
												</div>

												<div class="col-6">

													<div style="padding-right: 125px;
													text-align: right;
													text-align-last: right;
													-ms-text-align-last: right;
													-moz-text-align-last: right;">
														<img height="40"
															src="${pageContext.request.contextPath}/resources/images/mastercard.png">

													</div>

													<h5 class="mb-3  mt-3 fw-bold">Instalment 6</h5>

													<div class="row g-3">
														<div
															class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
															<div class="mb-3 small ">Merchant MDR (%)</div>
														</div>


														<div class="col-sm-4  form-floating mb-3">
															<div class="mb-3">
																<form:input id="loc_Credit_Merch_Master_Insta6"
																	path="standardEzySplitMDRRate.loc_Credit_Merch_Master_Insta6"
																	class="form-control" type="number" min="0" max="5"
																	step="0.001"
																	placeholder="Local Credit Merchant MDR (%)" />
															</div>
														</div>


													</div>

													<div class="row g-3">
														<div
															class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
															<div class="mb-3 small ">Customer MDR (%)</div>
														</div>




														<div class="col-sm-4  form-floating mb-3">
															<div class="mb-3">
																<form:input id="loc_Credit_Cus_Master_Insta6"
																	path="standardEzySplitMDRRate.loc_Credit_Cus_Master_Insta6"
																	class="form-control"
																	placeholder="Local Credit Customer MDR (%)" />
															</div>
														</div>


													</div>

													<div class="row g-3" style="display: none;">
														<div
															class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
															<div class="mb-3 small ">Host MDR (%)</div>
														</div>

														<div class="col-sm-4  form-floating mb-3">
															<div class="mb-3">
																<form:input id="loc_Credit_Host_Master_Insta6"
																	path="standardEzySplitMDRRate.loc_Credit_Host_Master_Insta6"
																	class="form-control" type="number" min="0" max="4"
																	step="0.001" placeholder="Local Credit Host MDR (%)"
																	readonly="true" />
															</div>
														</div>

													</div>
													<div class="row g-3" style="display: none;">
														<div
															class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
															<div class="mb-3 small ">Mobi MDR (%)</div>
														</div>


														<div class="col-sm-4  form-floating mb-3">
															<div class="mb-3">
																<form:input id="loc_Credit_Mobi_Master_Insta6"
																	path="standardEzySplitMDRRate.loc_Credit_Mobi_Master_Insta6"
																	class="form-control"
																	placeholder="Local Credit Mobi MDR (%)"
																	readonly="true" />
															</div>
														</div>


													</div>
												</div>
											</div>



											<div class="row">
												<div class="col-6">
													<h5 class="mb-3  mt-3 fw-bold">Instalment 9</h5>

													<div class="row g-3">
														<div
															class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
															<div class="mb-3 small ">Merchant MDR (%)</div>
														</div>

														<div class="col-sm-4  form-floating mb-3">
															<div class="mb-3">
																<form:input id="loc_Credit_Merch_Master_Insta9"
																	path="standardEzySplitMDRRate.loc_Credit_Merch_Master_Insta9"
																	class="form-control" type="number" min="0" max="5"
																	step="0.001"
																	placeholder="Local Credit Merchant MDR (%)" />
															</div>
														</div>


													</div>

													<div class="row g-3">
														<div
															class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
															<div class="mb-3 small ">Customer MDR (%)</div>
														</div>



														<div class="col-sm-4  form-floating mb-3">
															<div class="mb-3">
																<form:input id="loc_Credit_Cus_Master_Insta9"
																	path="standardEzySplitMDRRate.loc_Credit_Cus_Master_Insta9"
																	class="form-control"
																	placeholder="Local Credit Customer MDR (%)" />
															</div>
														</div>

													</div>

													<div class="row g-3" style="display: none;">
														<div
															class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
															<div class="mb-3 small ">Host MDR (%)</div>
														</div>



														<div class="col-sm-4  form-floating mb-3">
															<div class="mb-3">
																<form:input id="loc_Credit_Host_Master_Insta9"
																	path="standardEzySplitMDRRate.loc_Credit_Host_Master_Insta9"
																	class="form-control" type="number" min="0" max="4"
																	step="0.001" placeholder="Local Credit Host MDR (%)"
																	readonly="true" />
															</div>
														</div>

													</div>
													<div class="row g-3" style="display: none;">
														<div
															class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
															<div class="mb-3 small ">Mobi MDR (%)</div>
														</div>



														<div class="col-sm-4  form-floating mb-3">
															<div class="mb-3">
																<form:input id="loc_Credit_Mobi_Master_Insta9"
																	path="standardEzySplitMDRRate.loc_Credit_Mobi_Master_Insta9"
																	class="form-control"
																	placeholder="Local Credit Mobi MDR (%)"
																	readonly="true" />
															</div>
														</div>

													</div>
												</div>

												<div class="col-6">
													<h5 class="mb-3  mt-3 fw-bold">Instalment 12</h5>

													<div class="row g-3">
														<div
															class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
															<div class="mb-3 small ">Merchant MDR (%)</div>
														</div>




														<div class="col-sm-4  form-floating mb-3">
															<div class="mb-3">
																<form:input id="loc_Credit_Merch_Master_Insta12"
																	path="standardEzySplitMDRRate.loc_Credit_Merch_Master_Insta12"
																	class="form-control" type="number" min="0" max="5"
																	step="0.001"
																	placeholder="Local Credit Merchant MDR (%)" />
															</div>
														</div>


													</div>

													<div class="row g-3">
														<div
															class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
															<div class="mb-3 small ">Customer MDR (%)</div>
														</div>



														<div class="col-sm-4  form-floating mb-3">
															<div class="mb-3">
																<form:input id="loc_Credit_Cus_Master_Insta12"
																	path="standardEzySplitMDRRate.loc_Credit_Cus_Master_Insta12"
																	class="form-control"
																	placeholder="Local Credit Customer MDR (%)" />
															</div>
														</div>


													</div>

													<div class="row g-3" style="display: none;">
														<div
															class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
															<div class="mb-3 small ">Host MDR (%)</div>
														</div>



														<div class="col-sm-4  form-floating mb-3">
															<div class="mb-3">
																<form:input id="loc_Credit_Host_Master_Insta12"
																	path="standardEzySplitMDRRate.loc_Credit_Host_Master_Insta12"
																	class="form-control" type="number" min="0" max="4"
																	step="0.001" placeholder="Local Credit Host MDR (%)"
																	readonly="true" />
															</div>
														</div>

													</div>
													<div class="row g-3" style="display: none;">
														<div
															class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
															<div class="mb-3 small ">Mobi MDR (%)</div>
														</div>



														<div class="col-sm-4  form-floating mb-3">
															<div class="mb-3">
																<form:input id="loc_Credit_Mobi_Master_Insta12"
																	path="standardEzySplitMDRRate.loc_Credit_Mobi_Master_Insta12"
																	class="form-control"
																	placeholder="Local Credit Mobi MDR (%)"
																	readonly="true" />
															</div>
														</div>

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
													<label for="email" class="form-label col-12">&nbsp;</label>
													<input id=btnSubmit type="submit" value="Submit Changes"
														class="btn btn-mobi btn-alt float-end">
													<!-- 	<button id="btnCalc" value="calc" name="calc"
														class="btn btn-mobi btn-alt float-end me-2">Calculate Mobi
														Rate</button> -->
												</div>
											</div>

											<h5 class="mb-3  mt-3 fw-bold">Product History</h5>
										</div>

										<div class="table-responsive">
											<table id="table" class="table">
												<thead>
													<tr class="bcf4f4f4 c617075 fw500">
														<th>Product ID</th>
														<th>Product Name</th>
														<th>Product Type</th>
														<th>Host Type</th>
														<th>Unit Price (RM)</th>
														<th>Rate ID</th>
													</tr>
												</thead>
												<tbody class="c3f3f3f fw500">
													<c:forEach var="product" items="${productHistoryList}">

														<c:url var="viewLink" value="productHistoryView">
															<c:param name="id" value="${product.id}"></c:param>
														</c:url>

														<tr>
															<td>${product.id }</td>
															<td><a href="${viewLink} ">${product.productName }</a></td>
															<td>${product.productType }</td>
															<td>${product.hostType }</td>
															<td style="text-align: center;">${product.unitPrice }</td>
															<td>${product.standardmdrRate.id }</td>

														</tr>
													</c:forEach>

												</tbody>
											</table>
										</div>

									</div>
								</form:form>
							</div>

						</div>
					</div>
			</main>



			<!-- <div class="input-group">
                <span class="input-group-prepend">
                    <div class="input-group-text bg-transparent border-right-0"><i class="fa fa-search"></i></div>
                </span>
                <input class="form-control py-2 border-left-0 border" type="search" value="..." id="example-search-input">
                <span class="input-group-append"></span>
            </div>-->


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


			<script>

				window.onload = function () {
					
				

					loadHostRates();
				   loadWalletRates();


					//Disable Addition of Product & Host Rate for the Sales Access
					var role = "${loggedUserRole}";

					if (role != 'sales-manager') {
						$('#btnSubmit').prop('disabled', true);
						$('#btnCalc').prop('disabled', true);
					}


					//Make Wallet Div ReadOnly
					$('#ezySplitWalletData :input').attr('readonly', true);
					$('#nonEzySplitWalletData :input').attr('readonly', true);

					var productType_HostRate = $('#productType_HostRate').val();
					var productType = $('#productType').val();

					//Check for the Product Types and Display the respective MDR DIV	
					if (productType_HostRate == "EZYSPLIT") {
						$('#ezysplitHostRateDiv').show();
						$('#nonEzysplitHostRateDiv').hide();

						$('#ezySplitWalletData').show();
						$('#nonEzySplitWalletData').hide();
					}
					else {
						$('#nonEzysplitHostRateDiv').show();
						$('#ezysplitHostRateDiv').hide();

						$('#nonEzySplitWalletData').show();
						$('#ezySplitWalletData').hide();
					}


					if (productType == "EZYSPLIT") {
						$('#ezysplitProductDiv').show();
						$('#nonEzysplitProductDiv').hide();

						$('#ezySplitWalletData').show();
						$('#nonEzySplitWalletData').hide();
					}
					else {
						$('#nonEzysplitProductDiv').show();
						$('#ezysplitProductDiv').hide();

						$('#nonEzySplitWalletData').show();
						$('#ezySplitWalletData').hide();
					}

					var hostType = "${hostType}";
					var productType = $('#productType').val();

					console.log(hostType, productType);

					if (productType == 'EZYSPLIT') {
						ezySplitHostRates(hostType)
					} else {
						nonEzySplitHostRates(hostType)
					}
				}


				document.getElementById("btnSubmit").addEventListener('click', (e) => {
					$('#productDetailsForm').attr('action', 'productEdit');
				})

				$(document).ready(function () {
					$(document).on('click', '.edit', function (e) {
						e.preventDefault();
						var productId = $(this).attr('data-id');

						$('#editModal').modal('show');

						$.ajax({
							type: 'GET',
							url: "getProductById?productId=" + productId,
							success: function (data) {
								console.log(data)
								$('#productNameModal').val(data.productName);
								$('#productTypeModal').val(data.productType);
								$('#productSettlementModal').val(data.productSettlement);
							},
						});
					});

					$("#btnCalc").click(function (e) {
						e.preventDefault();
						var productType = $('#productType').val();

						if (productType == 'EZYSPLIT') {

							$.ajax({
								type: "GET",
								url: "calculateMobiMDR_EZYSPLIT",
								data: {
									access_token: $("#access_token").val(),
									loc_Credit_Merch_Master_Insta3: $('#loc_Credit_Merch_Master_Insta3').val(),
									loc_Credit_Cus_Master_Insta3: $('#loc_Credit_Cus_Master_Insta3').val(),
									loc_Credit_Host_Master_Insta3: $('#loc_Credit_Host_Master_Insta3').val(),

									loc_Credit_Merch_Master_Insta6: $('#loc_Credit_Merch_Master_Insta6').val(),
									loc_Credit_Cus_Master_Insta6: $('#loc_Credit_Cus_Master_Insta6').val(),
									loc_Credit_Host_Master_Insta6: $('#loc_Credit_Host_Master_Insta6').val(),

									loc_Credit_Merch_Master_Insta9: $('#loc_Credit_Merch_Master_Insta9').val(),
									loc_Credit_Cus_Master_Insta9: $('#loc_Credit_Cus_Master_Insta9').val(),
									loc_Credit_Host_Master_Insta9: $('#loc_Credit_Host_Master_Insta9').val(),

									loc_Credit_Merch_Master_Insta12: $('#loc_Credit_Merch_Master_Insta12').val(),
									loc_Credit_Cus_Master_Insta12: $('#loc_Credit_Cus_Master_Insta12').val(),
									loc_Credit_Host_Master_Insta12: $('#loc_Credit_Host_Master_Insta12').val()


								},
								success: function (result) {
									$('#loc_Credit_Mobi_Master_Insta3').val(result.loc_Credit_Mobi_Master_Insta3);
									$('#loc_Credit_Mobi_Master_Insta6').val(result.loc_Credit_Mobi_Master_Insta6);
									$('#loc_Credit_Mobi_Master_Insta9').val(result.loc_Credit_Mobi_Master_Insta9);
									$('#loc_Credit_Mobi_Master_Insta12').val(result.loc_Credit_Mobi_Master_Insta12);

								},
								error: function (result) {
									alert('error');
								}
							});
						}
						else {
							$.ajax({
								type: "GET",
								url: "calculateMobiMDR",
								data: {
									access_token: $("#access_token").val(),
									loc_Debit_Merch_Visa: $('#loc_Debit_Merch_Visa').val(),
									loc_Debit_Merch_Master: $('#loc_Debit_Merch_Master').val(),
									loc_Debit_Merch_Union: $('#loc_Debit_Merch_Union').val(),
									loc_Credit_Merch_Visa: $('#loc_Credit_Merch_Visa').val(),
									loc_Credit_Merch_Master: $('#loc_Credit_Merch_Master').val(),
									loc_Credit_Merch_Union: $('#loc_Credit_Merch_Union').val(),
									for_Debit_Merch_Visa: $('#for_Debit_Merch_Visa').val(),
									for_Debit_Merch_Master: $('#for_Debit_Merch_Master').val(),
									for_Debit_Merch_Union: $('#for_Debit_Merch_Union').val(),
									for_Credit_Merch_Visa: $('#for_Credit_Merch_Visa').val(),
									for_Credit_Merch_Master: $('#for_Credit_Merch_Master').val(),
									for_Credit_Merch_Union: $('#for_Credit_Merch_Union').val(),



									loc_Debit_Host_Visa: $('#loc_Debit_Host_Visa').val(),
									loc_Debit_Host_Master: $('#loc_Debit_Host_Master').val(),
									loc_Debit_Host_Union: $('#loc_Debit_Host_Union').val(),
									loc_Credit_Host_Visa: $('#loc_Credit_Host_Visa').val(),
									loc_Credit_Host_Master: $('#loc_Credit_Host_Master').val(),
									loc_Credit_Host_Union: $('#loc_Credit_Host_Union').val(),
									for_Debit_Host_Visa: $('#for_Debit_Host_Visa').val(),
									for_Debit_Host_Master: $('#for_Debit_Host_Master').val(),
									for_Debit_Host_Union: $('#for_Debit_Host_Union').val(),
									for_Credit_Host_Visa: $('#for_Credit_Host_Visa').val(),
									for_Credit_Host_Master: $('#for_Credit_Host_Master').val(),
									for_Credit_Host_Union: $('#for_Credit_Host_Union').val()
								},
								success: function (result) {
									$('#loc_Debit_Mobi_Visa').val(result.loc_Debit_Mobi_Visa);
									$('#loc_Debit_Mobi_Master').val(result.loc_Debit_Mobi_Master);
									$('#loc_Debit_Mobi_Union').val(result.loc_Debit_Mobi_Union);

									$('#loc_Credit_Mobi_Visa').val(result.loc_Credit_Mobi_Visa);
									$('#loc_Credit_Mobi_Master').val(result.loc_Credit_Mobi_Master);
									$('#loc_Credit_Mobi_Union').val(result.loc_Credit_Mobi_Union);

									$('#for_Debit_Mobi_Visa').val(result.for_Debit_Mobi_Visa);
									$('#for_Debit_Mobi_Master').val(result.for_Debit_Mobi_Master);
									$('#for_Debit_Mobi_Union').val(result.for_Debit_Mobi_Union);

									$('#for_Credit_Mobi_Visa').val(result.for_Credit_Mobi_Visa);
									$('#for_Credit_Mobi_Master').val(result.for_Credit_Mobi_Master);
									$('#for_Credit_Mobi_Union').val(result.for_Credit_Mobi_Union);
								},
								error: function (result) {
									alert('error');
								}
							});
						}


					});

					$('#table')
						.DataTable(
							{
								"dom": "<'row'<'col-sm-12 col-md-6'f><'col-sm-12 col-md-6'l>>"
									+ "<'row'<'col-sm-12'tr>>"
									+ "<'row'<'col-sm-12 col-md-6 mt-2'i><'col-sm-12 col-md-6 mt-2'p>>",
							});
				});

				$('#hostType').change(function () {
					var hostType = this.value;
					var productType = $('#productType').val();

					if (productType == 'EZYSPLIT') {
						ezySplitHostRates(hostType)
					} else {
						nonEzySplitHostRates(hostType)
					}
				});

				
				

				function getHostRates() {
					var hostRate = $('#hostRate').val();
					if (hostRate != null) {
						$.ajax({
							type: 'GET',
							url: "getHostRateValues?hostRate=" + hostRate,
							success: function (data) {

								$('#loc_Debit_Host_Visa').val(data.loc_Debit_Host_Visa);
								$('#loc_Debit_Host_Master').val(data.loc_Debit_Host_Master);
								$('#loc_Debit_Host_Union').val(data.loc_Debit_Host_Union);


								$('#loc_Credit_Host_Visa').val(data.loc_Credit_Host_Visa);
								$('#loc_Credit_Host_Master').val(data.loc_Credit_Host_Master);
								$('#loc_Credit_Host_Union').val(data.loc_Credit_Host_Union);


								$('#for_Debit_Host_Visa').val(data.for_Debit_Host_Visa);
								$('#for_Debit_Host_Master').val(data.for_Debit_Host_Master);
								$('#for_Debit_Host_Union').val(data.for_Debit_Host_Union);

								$('#for_Credit_Host_Visa').val(data.for_Credit_Host_Visa);
								$('#for_Credit_Host_Master').val(data.for_Credit_Host_Master);
								$('#for_Credit_Host_Union').val(data.for_Credit_Host_Union);

// 								document.getElementById("loc_Debit_Merch_Visa").min = data.loc_Debit_Host_Visa
// 								document.getElementById("loc_Debit_Merch_Master").min = data.loc_Debit_Host_Master
// 								document.getElementById("loc_Debit_Merch_Union").min = data.loc_Debit_Host_Union

// 								document.getElementById("loc_Credit_Merch_Visa").min = data.loc_Credit_Host_Visa
// 								document.getElementById("loc_Credit_Merch_Master").min = data.loc_Credit_Host_Master
// 								document.getElementById("loc_Credit_Merch_Union").min = data.loc_Credit_Host_Union

// 								document.getElementById("for_Debit_Merch_Visa").min = data.for_Debit_Host_Visa
// 								document.getElementById("for_Debit_Merch_Master").min = data.for_Debit_Host_Master
// 								document.getElementById("for_Debit_Merch_Union").min = data.for_Debit_Host_Union

// 								document.getElementById("for_Credit_Merch_Visa").min = data.for_Credit_Host_Visa
// 								document.getElementById("for_Credit_Merch_Master").min = data.for_Credit_Host_Master
// 								document.getElementById("for_Credit_Merch_Union").min = data.for_Credit_Host_Union

	                             document.getElementById("loc_Debit_Merch_Visa").min = data.loc_Debit_Host_Visa + 0.01;
	                                document.getElementById("loc_Debit_Merch_Master").min = data.loc_Debit_Host_Master + 0.01;
	                                document.getElementById("loc_Debit_Merch_Union").min = data.loc_Debit_Host_Union + 0.01;

	                               document.getElementById("loc_Credit_Merch_Visa").min = data.loc_Credit_Host_Visa + 0.01;
	                                document.getElementById("loc_Credit_Merch_Master").min = data.loc_Credit_Host_Master + 0.01;
	                                document.getElementById("loc_Credit_Merch_Union").min = data.loc_Credit_Host_Union + 0.01;

	                               document.getElementById("for_Debit_Merch_Visa").min = data.for_Debit_Host_Visa+ 0.01;
	                                document.getElementById("for_Debit_Merch_Master").min = data.for_Debit_Host_Master + 0.01;
	                                document.getElementById("for_Debit_Merch_Union").min = data.for_Debit_Host_Union + 0.01;

	                               document.getElementById("for_Credit_Merch_Visa").min = data.for_Credit_Host_Visa + 0.01;
	                                document.getElementById("for_Credit_Merch_Master").min = data.for_Credit_Host_Master + 0.01;
	                                document.getElementById("for_Credit_Merch_Union").min = data.for_Credit_Host_Union + 0.01;					
							
							
							},
						});
					}
					else {
						$('#loc_Debit_Host_Visa').val("0.00");
						$('#loc_Debit_Host_Master').val("0.00");
						$('#loc_Debit_Host_Union').val("0.00");

						$('#loc_Credit_Host_Visa').val("0.00");
						$('#loc_Credit_Host_Master').val("0.00");
						$('#loc_Credit_Host_Union').val("0.00");

						$('#for_Debit_Host_Visa').val("0.00");
						$('#for_Debit_Host_Master').val("0.00");
						$('#for_Debit_Host_Union').val("0.00");

						$('#for_Credit_Host_Visa').val("0.00");
						$('#for_Credit_Host_Master').val("0.00");
						$('#for_Credit_Host_Union').val("0.00");
					}
				}

				function nonEzySplitHostRates(hostType) {
					var productType = $('#productType').val();

					$.ajax({
						type: 'GET',
						url: "getHostRates?hostType=" + hostType + "&productType=" + productType,
						success: function (data) {
							console.log(data);
							var hostRate = $('#hostRate'), option = "";
							hostRate.empty();

							for (var i = 0; i < data.length; i++) {
								option = option + "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
							}

							hostRate.append(option);
							getHostRates()
						},
						error: function () {
							alert("error");
						}

					});
				}

				function ezySplitHostRates(hostType) {
					var productType = $('#productType').val();
					$.ajax({
						type: 'GET',
						url: "getHostRates?hostType=" + hostType + "&productType=" + productType,
						success: function (data) {
							console.log(data);
							var hostRate = $('#hostRate'), option = "";
							hostRate.empty();

							for (var i = 0; i < data.length; i++) {
								option = option + "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
							}

							hostRate.append(option);
							var hostRate = $('#hostRate').val();

							if (hostRate != null) {
								$.ajax({
									type: 'GET',
									url: "getHostRateValueEZYSPLIT?hostRate=" + hostRate,
									success: function (data) {

										$('#loc_Credit_Host_Master_Insta3').val(data.loc_Credit_Host_Master_EZYSPLIT);
										$('#loc_Credit_Host_Master_Insta6').val(data.loc_Credit_Host_Master_EZYSPLIT);
										$('#loc_Credit_Host_Master_Insta9').val(data.loc_Credit_Host_Master_EZYSPLIT);
										$('#loc_Credit_Host_Master_Insta12').val(data.loc_Credit_Host_Master_EZYSPLIT);

									},
								});
							}
							else {
								$('#loc_Credit_Host_Master_Insta3').val("0.00");
								$('#loc_Credit_Host_Master_Insta6').val("0.00");
								$('#loc_Credit_Host_Master_Insta9').val("0.00");
								$('#loc_Credit_Host_Master_Insta12').val("0.00");
							}
						},
						error: function () {
							alert("error");
						}

					});
				}

				$('#productType_HostRate').change(function () {
					var productType_HostRate = this.value;

					if (productType_HostRate == "EZYSPLIT") {
						$('#ezysplitHostRateDiv').show();
						$('#nonEzysplitHostRateDiv').hide();
					}
					else {
						$('#nonEzysplitHostRateDiv').show();
						$('#ezysplitHostRateDiv').hide();
					}
				});

				$('#productType').change(function () {
					var productType = this.value;
					var hostType = $('#hostType').val();

					loadWalletRates();

					if (productType == "EZYSPLIT") {
						$('#ezysplitProductDiv').show();
						$('#nonEzysplitProductDiv').hide();

						$('#ezySplitWalletData').show();
						$('#nonEzySplitWalletData').hide();
					}
					else {
						$('#nonEzysplitProductDiv').show();
						$('#ezysplitProductDiv').hide();

						$('#nonEzySplitWalletData').show();
						$('#ezySplitWalletData').hide();
					}

					$.ajax({
						type: 'GET',
						url: "getHostRates?hostType=" + hostType + "&productType=" + productType,
						success: function (data) {
							console.log(data);
							var hostRate = $('#hostRate'), option = "";
							hostRate.empty();

							for (var i = 0; i < data.length; i++) {
								option = option + "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
							}

							hostRate.append(option);

							var hostRate = $('#hostRate').val();
							if (hostRate != null) {
								$.ajax({
									type: 'GET',
									url: "getHostRateValues?hostRate=" + hostRate,
									success: function (data) {
										$("#loc_Debit_Host_Visa").val(data.loc_Debit_Host_Visa);
										$('#loc_Debit_Host_Master').val(data.loc_Debit_Host_Master);
										$('#loc_Debit_Host_Union').val(data.loc_Debit_Host_Union);

										$('#loc_Credit_Host_Visa').val(data.loc_Credit_Host_Visa);
										$('#loc_Credit_Host_Master').val(data.loc_Credit_Host_Master);
										$('#loc_Credit_Host_Union').val(data.loc_Credit_Host_Union);

										$('#for_Debit_Host_Visa').val(data.for_Debit_Host_Visa);
										$('#for_Debit_Host_Master').val(data.for_Debit_Host_Master);
										$('#for_Debit_Host_Union').val(data.for_Debit_Host_Union);

										$('#for_Credit_Host_Visa').val(data.for_Credit_Host_Visa);
										$('#for_Credit_Host_Master').val(data.for_Credit_Host_Master);
										$('#for_Credit_Host_Union').val(data.for_Credit_Host_Union);
										
										
		                                document.getElementById("loc_Debit_Merch_Visa").min = data.loc_Debit_Host_Visa + 0.01;
		                                document.getElementById("loc_Debit_Merch_Master").min = data.loc_Debit_Host_Master + 0.01;
		                                document.getElementById("loc_Debit_Merch_Union").min = data.loc_Debit_Host_Union + 0.01;



		                               document.getElementById("loc_Credit_Merch_Visa").min = data.loc_Credit_Host_Visa + 0.01;
		                                document.getElementById("loc_Credit_Merch_Master").min = data.loc_Credit_Host_Master + 0.01;
		                                document.getElementById("loc_Credit_Merch_Union").min = data.loc_Credit_Host_Union + 0.01;



		                               document.getElementById("for_Debit_Merch_Visa").min = data.for_Debit_Host_Visa + 0.01;
		                                document.getElementById("for_Debit_Merch_Master").min = data.for_Debit_Host_Master + 0.01;
		                                document.getElementById("for_Debit_Merch_Union").min = data.for_Debit_Host_Union + 0.01;



		                               document.getElementById("for_Credit_Merch_Visa").min = data.for_Credit_Host_Visa + 0.01;
		                                document.getElementById("for_Credit_Merch_Master").min = data.for_Credit_Host_Master + 0.01;
		                                document.getElementById("for_Credit_Merch_Union").min = data.for_Credit_Host_Union + 0.01;

									},
									error: function () {
										alert("error");
									}
								});
							}
							else {
								$('#loc_Debit_Host_Visa').val("0.00");
								$('#loc_Debit_Host_Master').val("0.00");
								$('#loc_Debit_Host_Union').val("0.00");

								$('#loc_Credit_Host_Visa').val("0.00");
								$('#loc_Credit_Host_Master').val("0.00");
								$('#loc_Credit_Host_Union').val("0.00");

								$('#for_Debit_Host_Visa').val("0.00");
								$('#for_Debit_Host_Master').val("0.00");
								$('#for_Debit_Host_Union').val("0.00");

								$('#for_Credit_Host_Visa').val("0.00");
								$('#for_Credit_Host_Master').val("0.00");
								$('#for_Credit_Host_Union').val("0.00");
							}


						},
						error: function () {
							alert("error");
						}

					});
				});


				function loadHostRates() {
					var hostType = $('#hostType').val();
					var productType = $('#productType').val();
					$.ajax({
						type: 'GET',
						url: "getHostRates?hostType=" + hostType + "&productType=" + productType,
						success: function (data) {
							var hostRate = $('#hostRate'), option = "";
							hostRate.empty();

							for (var i = 0; i < data.length; i++) {
								option = option + "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
							}
							hostRate.append(option);
						},
						error: function () {
							this.alert("error");
						}

					});
				};

				$('#hostRate').change(function () {
					var hostRate = $('#hostRate').val();
					var productType = $('#productType').val();

					if (hostRate != null) {
						if (productType == 'EZYSPLIT') {
							$.ajax({
								type: 'GET',
								url: "getHostRateValueEZYSPLIT?hostRate=" + hostRate,
								success: function (data) {
									$('#loc_Credit_Host_Master_Insta3').val(data);
									$('#loc_Credit_Host_Master_Insta6').val(data);
									$('#loc_Credit_Host_Master_Insta9').val(data);
									$('#loc_Credit_Host_Master_Insta12').val(data);

								},
							});
						}
						else {
							$.ajax({
								type: 'GET',
								url: "getHostRateValues?hostRate=" + hostRate,
								success: function (data) {
									$('#loc_Debit_Host_Visa').val(data.loc_Debit_Host_Visa);
									$('#loc_Debit_Host_Master').val(data.loc_Debit_Host_Master);
									$('#loc_Debit_Host_Union').val(data.loc_Debit_Host_Union);

									$('#loc_Credit_Host_Visa').val(data.loc_Credit_Host_Visa);
									$('#loc_Credit_Host_Master').val(data.loc_Credit_Host_Master);
									$('#loc_Credit_Host_Union').val(data.loc_Credit_Host_Union);

									$('#for_Debit_Host_Visa').val(data.for_Debit_Host_Visa);
									$('#for_Debit_Host_Master').val(data.for_Debit_Host_Master);
									$('#for_Debit_Host_Union').val(data.for_Debit_Host_Union);

									$('#for_Credit_Host_Visa').val(data.for_Credit_Host_Visa);
									$('#for_Credit_Host_Master').val(data.for_Credit_Host_Master);
									$('#for_Credit_Host_Union').val(data.for_Credit_Host_Union);

								},

							});
						}
					}
					else {
						$('#loc_Credit_Host_Master_Insta3').val("0.00");
						$('#loc_Credit_Host_Master_Insta6').val("0.00");
						$('#loc_Credit_Host_Master_Insta9').val("0.00");
						$('#loc_Credit_Host_Master_Insta12').val("0.00");

					}

				});
				
				
				
// 				function getWalletRateValues() {
					
// 					var walletRate = $('#walletRate').val();
// 					var productType = $('#productType').val();

// 					if (walletRate != null) {
// 						$.ajax({
// 							type: 'GET',
// 							url: "getWalletRateValues?walletRate=" + walletRate,
// 							success: function (data) {

// 								if (productType == 'EZYSPLIT') {
// 									$('#boostMDREcomm_Ezysplit').val(data.boostMDREcomm);
// 									$('#boostMDRQR_Ezysplit').val(data.boostMDRQR);
// 									$('#grabMDREcomm_Ezysplit').val(data.grabMDREcomm);
// 									$('#grabMDRQR_Ezysplit').val(data.grabMDRQR);
									
// 									$('#tngMDREcomm_Ezysplit').val(data.tngMDREcomm);
// 									$('#tngMDRQR_Ezysplit').val(data.tngMDRQR);
// 									$('#shopeepayMDREcomm_Ezysplit').val(data.shopeepayMDREcomm);
// 									$('#shopeepayMDRQR_Ezysplit').val(data.shopeepayMDRQR);
// 									$('#tngSettlement_Ezysplit').val(data.tngSettlement);
// 									$('#shopeepaySettlement_Ezysplit').val(data.shopeepaySettlement);

// 									$('#fPXMDR_RM_Ezysplit').val(data.fPXMDR_RM);
// 									$('#fPXMDR_Percent_Ezysplit').val(data.fPXMDR_Percent);
// 									$('#boostSettlement_Ezysplit').val(data.boostSettlement);
// 									$('#grabSettlement_Ezysplit').val(data.grabSettlement);
// 									$('#fpxSettlement_Ezysplit').val(data.fpxSettlement);
// 								}
// 								else {
// 									$('#boostMDREcomm').val(data.boostMDREcomm);
// 									$('#boostMDRQR').val(data.boostMDRQR);
// 									$('#grabMDREcomm').val(data.grabMDREcomm);
// 									$('#grabMDRQR').val(data.grabMDRQR);

// 									$('#tngMDREcomm').val(data.tngMDREcomm);
// 									$('#tngMDRQR').val(data.tngMDRQR);
// 									$('#shopeepayMDREcomm').val(data.shopeepayMDREcomm);
// 									$('#shopeepayMDRQR').val(data.shopeepayMDRQR);
// 									$('#tngSettlement').val(data.tngSettlement);
// 									$('#shopeepaySettlement').val(data.shopeepaySettlement);
									
									
									
									
// 									$('#fPXMDR_RM').val(data.fPXMDR_RM);
// 									$('#fPXMDR_Percent').val(data.fPXMDR_Percent);
// 									$('#boostSettlement').val(data.boostSettlement);
// 									$('#grabSettlement').val(data.grabSettlement);
// 									$('#fpxSettlement').val(data.fpxSettlement);
// 								}
// 							},
// 						});

// 					}
// 					else {
// 						$('#loc_Credit_Host_Master_Insta3').val("0.00");
// 						$('#loc_Credit_Host_Master_Insta6').val("0.00");
// 						$('#loc_Credit_Host_Master_Insta9').val("0.00");
// 						$('#loc_Credit_Host_Master_Insta12').val("0.00");

// 					}
// 				}


// 				$('#walletRate').change(function () {
// 					getWalletRateValues();
// 				});

				
				
				
				

				$('#includeWallet').change(function () {
					var includeWallet = $('#includeWallet').val();
					if (includeWallet == "No") {
						$(".walletData").hide();

						//EZYSPLIT Wallet Fields
						$('#boostMDREcomm_Ezysplit').val("0.00");
						$('#boostMDRQR_Ezysplit').val("0.00");
						$('#grabMDREcomm_Ezysplit').val("0.00");
						$('#grabMDRQR_Ezysplit').val("0.00");
						$('#fPXMDR_RM_Ezysplit').val("0.00");
						$('#fPXMDR_Percent_Ezysplit').val("0.00");
						$('#boostSettlement_Ezysplit').val("0.00");
						$('#grabSettlement_Ezysplit').val("0.00");
						$('#fpxSettlement_Ezysplit').val("0.00");
						
						$('#tngMDREcomm_Ezysplit').val("0.00");
						
						$('#tngMDRQR_Ezysplit').val("0.00");
						
						$('#shopeepayMDREcomm_Ezysplit').val("0.00");
						$('#shopeepayMDRQR_Ezysplit').val("0.00");
						$('#tngSettlement_Ezysplit').val("0.00");
						$('#shopeepaySettlement_Ezysplit').val("0.00");


						//Non-EZYSPLIT Wallet Fields
						$('#boostMDREcomm').val("0.00");
						$('#boostMDRQR').val("0.00");
						$('#grabMDREcomm').val("0.00");
						$('#grabMDRQR').val("0.00");
						$('#fPXMDR_RM').val("0.00");
						$('#fPXMDR_Percent').val("0.00");
						$('#boostSettlement').val("0.00");
						$('#grabSettlement').val("0.00");
						$('#fpxSettlement').val("0.00");
						
						$('#tngMDREcomm').val("0.00");
						$('#tngMDRQR').val("0.00");
						$('#shopeepayMDREcomm').val("0.00");
						$('#shopeepayMDRQR').val("0.00");
						$('#tngSettlement').val("0.00");
						$('#shopeepaySettlement').val("0.00");
					}
					else {
						$(".walletData").show();
						getWalletRateValues()
					}
				});

				
				
				function loadWalletRates() {

					var productType = $('#productType').val();
					$.ajax({
						type: 'GET',
						url: "getWalletRates?productType=" + productType,
						success: function (data) {
							var walletRateDropDown = $('#walletRate'), option = "";
							walletRateDropDown.empty();

							for (var i = 0; i < data.length; i++) {
								option = option + "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
							}

							walletRateDropDown.append(option);

							var walletRate = $('#walletRate').val();
							if (walletRate != null) {
								$.ajax({
									type: 'GET',
									url: "getWalletRateValues?walletRate=" + walletRate,
									success: function (data) {
										console.log(data)
										if (data.productType == 'EZYSPLIT') {
											console.log(data.productType);
											$('#boostMDREcomm_Ezysplit').val(data.boostMDREcomm);
											$('#boostMDRQR_Ezysplit').val(data.boostMDRQR);
											$('#grabMDREcomm_Ezysplit').val(data.grabMDREcomm);
											
											
											
											$('#tngMDREcomm_Ezysplit').val(data.tngMDREcomm);
											$('#tngMDRQR_Ezysplit').val(data.tngMDRQR);
											$('#shopeepayMDREcomm_Ezysplit').val(data.shopeepayMDREcomm);
											$('#shopeepayMDRQR_Ezysplit').val(data.shopeepayMDRQR);
											$('#tngSettlement_Ezysplit').val(data.tngSettlement);
											$('#shopeepaySettlement_Ezysplit').val(data.shopeepaySettlement);

											$('#grabMDRQR_Ezysplit').val(data.grabMDRQR);
											$('#fPXMDR_RM_Ezysplit').val(data.fPXMDR_RM);
											$('#fPXMDR_Percent_Ezysplit').val(data.fPXMDR_Percent);

											$('#boostSettlement_Ezysplit').val(data.boostSettlement);
											$('#grabSettlement_Ezysplit').val(data.grabSettlement);
											$('#fpxSettlement_Ezysplit').val(data.fpxSettlement);


										}
										else {
											$("#boostMDREcomm").val(data.boostMDREcomm);
											$('#boostMDRQR').val(data.boostMDRQR);
											$('#grabMDREcomm').val(data.grabMDREcomm);
											
											
											$('#tngMDREcomm').val(data.tngMDREcomm);
											$('#tngMDRQR').val(data.tngMDRQR);
											$('#shopeepayMDREcomm').val(data.shopeepayMDREcomm);
											$('#shopeepayMDRQR').val(data.shopeepayMDRQR);
											$('#tngSettlement').val(data.tngSettlement);
											$('#shopeepaySettlement').val(data.shopeepaySettlement);
											

											$('#grabMDRQR').val(data.grabMDRQR);
											$('#fPXMDR_RM').val(data.fPXMDR_RM);
											$('#fPXMDR_Percent').val(data.fPXMDR_Percent);

											$('#boostSettlement').val(data.boostSettlement);
											$('#grabSettlement').val(data.grabSettlement);
											$('#fpxSettlement').val(data.fpxSettlement);

										}

									},
									error: function () {
										alert("error");
									}
								});
							}
							else {
								$('#boostMDREcomm').val("0.00");
								$('#boostMDRQR').val("0.00");
								$('#grabMDREcomm').val("0.00");

								$('#grabMDRQR').val("0.00");
								$('#fPXMDR_RM').val("0.00");
								$('#fPXMDR_Percent').val("0.00");

								$('#boostSettlement').val("0.00");
								$('#grabSettlement').val("0.00");
								$('#fpxSettlement').val("0.00");
								
								
								$('#tngMDREcomm').val("0.00");
								$('#tngMDRQR').val("0.00");
								$('#shopeepayMDREcomm').val("0.00");
								$('#shopeepayMDRQR').val("0.00");
								$('#tngSettlement').val("0.00");
								$('#shopeepaySettlement').val("0.00");
								
								
							}


						},
						error: function () {
							alert("error");
						}
					});
				}



			</script>

			<!-- Modal -->
			<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-scrollable  modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Amend Quotation
								MDR</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<form:form action="">
								<div class="row g-3">
									<div class="col-sm-3  form-floating mb-3">
										<div class="mb-3">

											<label class="form-label">Product</label> <input id="productName"
												class="form-control" placeholder="" />

										</div>
									</div>

									<div class="col-sm-3  form-floating mb-3">
										<div class="mb-3">

											<label class="form-label">Settlement</label> <input id="productSettlement"
												class="form-control" placeholder="" />

										</div>
									</div>

									<div class="col-sm-3  form-floating mb-3">
										<div class="mb-3">

											<label class="form-label">Amount</label> <input id="amount"
												class="form-control" placeholder="" />

										</div>
									</div>
									<div class="col-sm-3  form-floating mb-3">
										<div class="mb-3">

											<label class="form-label">Host Rate ID</label> <input id="hostRateId"
												class="form-control" placeholder="" readonly="readonly" />

										</div>
									</div>
									<div>
										<input type="hidden" id="productId" />
										<input type="hidden" id="orderLineId" />
										<input type="hidden" id="quotationMdrRateId" />

									</div>


								</div>
								<label><b>Wallet Details</b></label>
								<br>
								<label><b>MDR</b></label>

								<div class="row g-3">
									<div class="col-sm-2  form-floating mb-3">
										<div class="mb-3">

											<label class="form-label">Boost</label> <input id="boostMDR"
												class="form-control" placeholder="" />

										</div>
									</div>

									<div class="col-sm-2  form-floating mb-3">
										<div class="mb-3">

											<label class="form-label">Grab</label> <input id="grabMDR"
												class="form-control" placeholder="" />

										</div>
									</div>

									<div class="col-sm-2  form-floating mb-3">
										<div class="mb-3">

											<label class="form-label">FPX (RM)</label> <input id="fPXMDR_RM"
												class="form-control" placeholder="" />

										</div>
									</div>
									<div class="col-sm-2  form-floating mb-3">
										<div class="mb-3">

											<label class="form-label">FPX (%)</label> <input id="fPXMDR_Percent"
												class="form-control" placeholder="" />

										</div>
									</div>
								</div>
								<label><b>Settlement</b></label>
								<div class="row g-3">
									<div class="col-sm-2  form-floating mb-3">
										<div class="mb-3">

											<label class="form-label">Boost</label> <input id="boostSettlement"
												class="form-control" placeholder="" />

										</div>
									</div>

									<div class="col-sm-2  form-floating mb-3">
										<div class="mb-3">

											<label class="form-label">Grab</label> <input id="grabSettlement"
												class="form-control" placeholder="" />

										</div>
									</div>

									<div class="col-sm-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">FPX </label> <input id="fpxSettlement"
												class="form-control" placeholder="" />

										</div>
									</div>
								</div>


								<div class="row g-3">

									<div class="col-sm-3  form-floating mb-3">
										<div class="mb-3"></div>
									</div>

									<div class="col-sm-2  col-4 form-floating mb-3">
										<div class="mb-3 text-center">
											<img height="40"
												src="${pageContext.request.contextPath}/resources/images/visa.png">
										</div>
									</div>


									<div class="col-sm-2  col-4 form-floating mb-3">
										<div class="mb-3 text-center">
											<img height="40"
												src="${pageContext.request.contextPath}/resources/images/mastercard.png">
										</div>
									</div>


									<div class="col-sm-2  col-4 form-floating mb-3">
										<div class="mb-3 text-center">
											<img height="40"
												src="${pageContext.request.contextPath}/resources/images/union_pay.png">
										</div>
									</div>

								</div>
								<label><b>Merchant Rates</b></label>
								<div class="row g-3">
									<div class="col-sm-3  form-floating mb-2">
										<div class="mb-2">
											<label class="form-label">Local Debit (%)</label>
										</div>
									</div>
									<div class="col-sm-2  form-floating mb-1">
										<div class="mb-2">
											<input id="loc_Debit_Merch_Visa" class="form-control" placeholder="" />
										</div>
									</div>
									<div class="col-sm-2  form-floating mb-2">
										<div class="mb-2">
											<input id="loc_Debit_Merch_Master" class="form-control" placeholder="" />
										</div>
									</div>
									<div class="col-sm-2  form-floating mb-3">
										<div class="mb-3">
											<input id="loc_Debit_Merch_Union" class="form-control" placeholder="" />
										</div>
									</div>
								</div>

								<div class="row g-3">
									<div class="col-sm-3  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Local Credit (%)</label>
										</div>
									</div>
									<div class="col-sm-2  form-floating mb-3">
										<div class="mb-3">
											<input id="loc_Credit_Merch_Visa" class="form-control" placeholder="" />
										</div>
									</div>
									<div class="col-sm-2  form-floating mb-3">
										<div class="mb-3">
											<input id="loc_Credit_Merch_Master" class="form-control" placeholder="" />
										</div>
									</div>
									<div class="col-sm-2  form-floating mb-3">
										<div class="mb-3">
											<input id="loc_Credit_Merch_Union" class="form-control" placeholder="" />
										</div>
									</div>
								</div>

								<div class="row g-3">
									<div class="col-sm-3  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Foreign Debit (%)</label>
										</div>
									</div>
									<div class="col-sm-2  form-floating mb-3">
										<div class="mb-3">
											<input id="for_Debit_Merch_Visa" class="form-control" placeholder="" />
										</div>
									</div>
									<div class="col-sm-2  form-floating mb-3">
										<div class="mb-3">
											<input id="for_Debit_Merch_Master" class="form-control" placeholder="" />
										</div>
									</div>
									<div class="col-sm-2  form-floating mb-3">
										<div class="mb-3">
											<input id="for_Debit_Merch_Union" class="form-control" placeholder="" />
										</div>
									</div>
								</div>

								<div class="row g-3">
									<div class="col-sm-3  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Foreign Credit (%)</label>
										</div>
									</div>
									<div class="col-sm-2  form-floating mb-3">
										<div class="mb-3">
											<input id="for_Credit_Merch_Visa" class="form-control" placeholder="" />
										</div>
									</div>
									<div class="col-sm-2  form-floating mb-3">
										<div class="mb-3">
											<input id="for_Credit_Merch_Master" class="form-control" placeholder="" />
										</div>
									</div>
									<div class="col-sm-2  form-floating mb-3">
										<div class="mb-3">
											<input id="for_Credit_Merch_Union" class="form-control" placeholder="" />
										</div>
									</div>
								</div>


							</form:form>

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary saveMDR">Save
								changes</button>
						</div>
					</div>
				</div>
			</div>
		</body>

		</html>
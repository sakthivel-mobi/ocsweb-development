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


							<form:form method="POST" action="hostRateEdit" modelAttribute="hostRate"
								accept-charset="utf-8">


								<div class="col-md-12 col-lg-12">
									<div class="bg-white  rounded form">
										<h5 class="mb-3  mt-5">Host Rates</h5>

										<form:hidden path="id" />
										<form:hidden path="createdOn" />

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

											<div class="col-sm-3  form-floating mb-3">
												<div class="mb-3">
													<form:label path="hostType" class="form-label">Host Type
													</form:label>
													<form:select class="form-select" name="hostType" path="hostType"
														id="hostType">

														<c:forEach items="${hostTypes}" var="hostTypes">
															<form:option value="${hostTypes.value}">${hostTypes.name}
															</form:option>
														</c:forEach>
													</form:select>
												</div>
											</div>

										</div>


										<!-- PAYMENT ICONS FOLLOWED BY LOCAL DEBIT MERCHANT-->

										<div id="nonEzysplitHostRateDiv">

											<div class="row g-3">

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3"></div>
												</div>

												<div class="col-sm-3  col-4 form-floating mb-3">
													<div class="mb-3">
														<img height="40"
															src="${pageContext.request.contextPath}/resources/images/visa.png">
													</div>
												</div>


												<div class="col-sm-3  col-4 form-floating mb-3">
													<div class="mb-3">
														<img height="40"
															src="${pageContext.request.contextPath}/resources/images/mastercard.png">
													</div>
												</div>


												<div class="col-sm-3  col-4 form-floating mb-3">
													<div class="mb-3">
														<img height="40"
															src="${pageContext.request.contextPath}/resources/images/union_pay.png">
													</div>
												</div>

											</div>


											<div class="row g-3">
												<div
													class="col-sm-3  form-floating mb-3 d-flex align-items-center justify-content-center">
													<div class="mb-3 small ">Local Debit Host MDR (%)</div>
												</div>


												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:input path="loc_Debit_Host_Visa" type="number" min="0"
															max="4" step="0.001" class="form-control"
															placeholder="Local Debit Host" />
													</div>
												</div>


												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:input path="loc_Debit_Host_Master" type="number" min="0"
															max="4" step="0.001" class="form-control"
															placeholder="Local Debit Host" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:input path="loc_Debit_Host_Union" type="number" min="0"
															max="4" step="0.001" class="form-control"
															placeholder="Local Debit Host" />
													</div>
												</div>

											</div>



											<div class="row g-3">
												<div
													class="col-sm-3  form-floating mb-3 d-flex align-items-center justify-content-center">
													<div class="mb-3 small ">Local Credit Host MDR (%)</div>
												</div>


												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:input path="loc_Credit_Host_Visa" class="form-control"
															type="number" min="0" max="4" step="0.001"
															placeholder="Local Credit Host" />
													</div>
												</div>


												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:input path="loc_Credit_Host_Master" class="form-control"
															type="number" min="0" max="4" step="0.001"
															placeholder="Local Credit Host" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:input path="loc_Credit_Host_Union" class="form-control"
															type="number" min="0" max="4" step="0.001"
															placeholder="Local Credit Host" />
													</div>
												</div>

											</div>



											<div class="row g-3">
												<div
													class="col-sm-3  form-floating mb-3 d-flex align-items-center justify-content-center">
													<div class="mb-3 small ">Foreign Debit Host MDR (%)</div>
												</div>


												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:input path="for_Debit_Host_Visa" class="form-control"
															type="number" min="0" max="4" step="0.001"
															placeholder="Foreign Debit Host" />
													</div>
												</div>


												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:input path="for_Debit_Host_Master" class="form-control"
															type="number" min="0" max="4" step="0.001"
															placeholder="Foreign Debit Host" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:input path="for_Debit_Host_Union" class="form-control"
															type="number" min="0" max="4" step="0.001"
															placeholder="Foreign Debit Host" />
													</div>
												</div>

											</div>



											<div class="row g-3">
												<div
													class="col-sm-3  form-floating mb-3 d-flex align-items-center justify-content-center">
													<div class="mb-3 small ">Foreign Credit Host MDR (%)</div>
												</div>


												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:input path="for_Credit_Host_Visa" class="form-control"
															type="number" min="0" max="5" step="0.001"
															placeholder="Local Debit Merchant" />
													</div>
												</div>


												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:input path="for_Credit_Host_Master" class="form-control"
															type="number" min="0" max="5" step="0.001"
															placeholder="Local Debit Merchant" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:input path="for_Credit_Host_Union" class="form-control"
															type="number" min="0" max="5" step="0.001"
															placeholder="Local Debit Merchant" />
													</div>
												</div>

											</div>

										</div>

										<div id="ezysplitHostRateDiv">

											<div class="row g-3">

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3"></div>
												</div>
												<div class="col-sm-3  col-4 form-floating mb-3">
													<div class="mb-3">
														<img height="40"
															src="${pageContext.request.contextPath}/resources/images/mastercard.png">
													</div>
												</div>

											</div>

											<div class="row g-3">
												<div
													class="col-sm-3  form-floating mb-3 d-flex align-items-center justify-content-center">
													<div class="mb-3 small ">Local Credit Host MDR (%)</div>
												</div>


												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<form:input path="loc_Credit_Host_Master_EZYSPLIT"
															class="form-control" type="number" min="0" max="4" step="0.001"
															placeholder="Local Credit Host EZYSPLIT" />
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
													<input type="submit" value="Edit HostRate" id="hostRateEditBtn"
														class="btn btn-mobi btn-alt float-end">
												</div>
											</div>


											<h5 class="mb-3  mt-3 fw-bold">Host Rate History</h5>
										</div>

										<div class="table-responsive">
											<table id="table" class="table">
												<thead>
													<tr class="bcf4f4f4 c617075 fw500">
														<th>ID</th>
														<th>Host Rate</th>
														<th>Modified Date</th>

														<th>Product Type</th>
														<th>Host Type</th>

													</tr>
												</thead>
												<tbody class="c3f3f3f fw500">
													<c:forEach var="hostRate" items="${hostRateHistoryList}">

														<c:url var="viewLink" value="hostRateHistoryView">
															<c:param name="id" value="${hostRate.id}"></c:param>
														</c:url>

														<tr>
															<td>${hostRate.id }</td>
															<td><a href="${viewLink}">${hostRate.name }</a></td>
															<td>${hostRate.createdOn }</td>
															<td>${hostRate.productType }</td>
															<td>${hostRate.hostType }</td>


														</tr>
													</c:forEach>

												</tbody>
											</table>
										</div>


									</div>
								</div>


							</form:form>
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
						$('#hostRateEditBtn').prop('disabled', true);
					}

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

			</script>

		</body>

		</html>
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

				<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
					integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
					crossorigin="anonymous">
				<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
					integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
					crossorigin="anonymous"></script>
				<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
					integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
					crossorigin="anonymous"></script>

				<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
				<link rel="icon" type="image/png" sizes="16x16"
					href="${pageContext.request.contextPath}/resources/images/favicon/favicon.ico">
				<link rel="manifest" href="/manifest.json">
				<meta name="msapplication-TileColor" content="#ffffff">
				<meta name="msapplication-TileImage"
					content="${pageContext.request.contextPath}/resources/images/favicon/ms-icon-144x144.png">
				<meta name="theme-color" content="#f1f5fa">


				<link rel="stylesheet" type="text/css"
					href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap5.min.css">
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/resources/fonts/fonts.css">


				<meta id="_csrf" name="_csrf" content="${_csrf.token}" />
				<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}" />


				<link rel="stylesheet" type="text/css"
					href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap5.min.css">
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/resources/fonts/fonts.css">
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css">

				<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js" type="text/javascript"></script>

				<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"
					integrity="sha512-qTXRIMyZIFb8iQcfjXWCO8+M5Tbc38Qi5WzdPOYZHIlZpzBHG3L3by84BBBOiRGiEb7KKtAOAs5qYdUiZiQNNQ=="
					crossorigin="anonymous" referrerpolicy="no-referrer" type="text/javascript"></script>

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
				<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js" type="text/javascript"></script>

				<script type="text/javascript"
					src="${pageContext.request.contextPath}/resources/js/loading.js"></script>
				<style>
					.form label {
						color: #092540;
					}

					.form textarea {
						min-height: 240px;
					}

					.form h5 {
						font-size: 30px;
						font-weight: 600;
						font-stretch: normal;
						font-style: normal;
						line-height: 1.6;
						letter-spacing: normal;
						text-align: left;
						color: #425466;
					}

					.h-divider {
						margin-top: 5px;
						margin-bottom: 5px;
						height: 1px;
						width: 100%;
						border-top: 1px solid #e2e2e2;
					}

					.item_img {
						background-color: #005baa;
						color: #fff;
						padding: 3px;
						opacity: 0.3;
					}

					.item_img_1 {
						background-color: #f9d2a8;
						color: #fff;
						padding: 3px;
						opacity: 0.3;
					}

					.too_small {
						font-size: .575em
					}

					.btn-alt {
						display: block;
						padding: .375rem .75rem;
						font-size: 1rem;
						font-weight: 400;
						line-height: 1.5;
						background-clip: padding-box;
						appearance: none;
					}

					.c005baa {
						color: #005baa;
					}

					.c425466 {
						color: #425466;
					}

					.btn-white {
						border-radius: 4px;
						box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.16);
						background-color: #ffffff;
						color: #7f8eab;
					}

					.sticky-div {
						background-color: white;
						position: sticky;
						top: 5px;
						padding: 10px 0px;
						z-index: 1000
					}

					.tab-pane {
						margin-top: 2rem;
						margin-bottom: 5rem;
					}
				</style>

		</head>

		<body class="container-fluid">
			<main>
				<div class="sticky-top p-4" style="margin-top: 80px;">
					<h5 class="c425466 fw-bold">${reg.order.businessName}</h5>
				</div>
				<div class="p-4">
					<form:form method="POST" action="" id="MerchantRegForm" modelAttribute="reg">
						<input type="hidden" name="stage" value="${order.quotation.stage}">
						<input type="hidden" name="appPassword" value="${reg.appPassword}">
						<input type="hidden" name="appUsername" value="${reg.appUsername}">
						<input type="hidden" name="webPortalPassword" value="${reg.webPortalPassword}">
						<input type="hidden" name="webPortalUsername" value="${reg.webPortalUsername}">
						<input type="hidden" name="isSuccess" value="${reg.isSuccess}">
						<input type="hidden" name="activationCode" value="${reg.activationCode}">
						<input type="hidden" name="apiKey" value="${reg.apiKey}">
						<input type="hidden" name="eWalletSyncSuccess" value="${reg.eWalletSyncSuccess}">

						<ul class="nav nav-tabs" id="myTab" role="tablist">
							<li class="nav-item" role="presentation">
								<button class="nav-link active" id="order-tab" data-bs-toggle="tab"
									data-bs-target="#order-tab-pane" type="button" role="tab"
									aria-controls="order-tab-pane" aria-selected="true">Order Details</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="other-details-tab" data-bs-toggle="tab"
									data-bs-target="#other-details-tab-pane" type="button" role="tab"
									aria-controls="other-details-tab-pane" aria-selected="false">Other Details</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="paydee-tab" data-bs-toggle="tab"
									data-bs-target="#paydee-tab-pane" type="button" role="tab"
									aria-controls="paydee-tab-pane" aria-selected="false">Paydee</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="umobile-tab" data-bs-toggle="tab"
									data-bs-target="#umobile-tab-pane" type="button" role="tab"
									aria-controls="umobile-tab-pane" aria-selected="false">UMobile</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="wallet-tab" data-bs-toggle="tab"
									data-bs-target="#wallet-tab-pane" type="button" role="tab"
									aria-controls="wallet-tab-pane" aria-selected="false">Wallet</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="director-tab" data-bs-toggle="tab"
									data-bs-target="#director-tab-pane" type="button" role="tab"
									aria-controls="director-tab-pane" aria-selected="false">Director</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="items-tab" data-bs-toggle="tab"
									data-bs-target="#items-tab-pane" type="button" role="tab"
									aria-controls="items-tab-pane" aria-selected="false">Items</button>
							</li>
						</ul>
						<div class="tab-content" id="myTabContent">
							<div class="tab-pane fade show active" id="order-tab-pane" role="tabpanel"
								aria-labelledby="order-tab" tabindex="0">
								<div class="row">
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<form:label path="id" class="form-label">Registration ID</form:label>
											<form:input path="id" class="form-control" placeholder="Registration ID"
												readonly="true" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<form:label path="createdOn" class="form-label">Created On</form:label>
											<div class="input-group mb-3">
												<form:input id="createdOn" path="createdOn" type="text" readonly="true"
													class="form-control" />
												<span class="input-group-text"><i class="fa fa-calendar"></i></span>
											</div>
										</div>
									</div>

									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<form:label path="order.quotation.userId" class="form-label">User ID
											</form:label>
											<div class="input-group mb-3">
												<span class="input-group-text" id="basic-addon1">60</span>
												<form:input path="order.quotation.userId" class="form-control"
													placeholder="User ID" readonly="true" />
											</div>
										</div>
									</div>

									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<form:label path="order.quotation.id" class="form-label">Quotation ID
											</form:label>
											<form:input path="order.quotation.id" id="quotationId" class="form-control"
												readonly="true" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<form:label path="order.id" class="form-label">Order ID</form:label>
											<form:input path="order.id" id="orderId" class="form-control"
												readonly="true" />
										</div>
									</div>
								</div>
								<hr>
								<div id=orderDetailDiv>
									<div class="row g-3">
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.salutation" class="form-label">Salutation
												</form:label>
												<form:input path="order.salutation" class="form-control"
													placeholder="Salutation" />
											</div>
										</div>

<!-- 										<div class="col-sm-6 col-md-2  form-floating mb-3"> -->
<!-- 											<div class="mb-3"> -->
<%-- 												<form:label path="order.quotation.salesPerson.id" class="form-label"> --%>
<%-- 													Agent Name</form:label> --%>
<%-- 												<form:select class="form-select" name="salesPerson" --%>
<%-- 													path="order.quotation.salesPerson.id" id="salesPerson"> --%>
<%-- 													<form:option value="0">-----------Select----------- --%>
<%-- 													</form:option> --%>
<%-- 													<c:forEach items="${salesPersons}" var="salesPersons"> --%>
<%-- 														<form:option value="${salesPersons.id}"> --%>
<%-- 															${salesPersons.email} --%>
<%-- 														</form:option> --%>
<%-- 													</c:forEach> --%>
<%-- 												</form:select> --%>
<!-- 											</div> -->
<!-- 										</div> -->

<div class="col-sm-6 col-md-2  form-floating mb-3">
                                            <div class="mb-3">
                                                <form:label path="order.quotation.salesPerson.id" class="form-label">
                                                    Agent Name</form:label>
                                                <form:select class="form-select" name="salesPerson"
                                                    path="order.quotation.salesPerson.id" id="salesPerson">
                                                    <form:option value="0">-----------Select-----------
                                                    </form:option>
                                                    <c:forEach items="${salesPersons}" var="salesPersons">
                                                        <form:option value="${salesPersons.id}">
                                                            ${salesPersons.email}
                                                        </form:option>
                                                    </c:forEach>
                                                </form:select>
                                                <span id="salesPerson_error" style="color:red;display:none;">Agent name is required</span>
                                            </div>
                                        </div>



										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.masterMerchant" class="form-label">Master
													Merchant</form:label>
												<form:input path="order.masterMerchant" class="form-control"
													placeholder="Master Merchant" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.merchantTradingName" class="form-label">
													Merchant Trading Name</form:label>
												<form:input path="order.merchantTradingName" class="form-control"
													placeholder="Merchant Trading Name" />
											</div>
										</div>

										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.businessName" class="form-label">Business
													Name</form:label>
												<form:input path="order.businessName" class="form-control"
													placeholder="Business Name" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.quotation.contact.email" class="form-label">
													Email</form:label>
												<form:input path="order.quotation.contact.email" class="form-control"
													placeholder="Email" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.addressLine" class="form-label">Address
												</form:label>
												<form:input path="order.addressLine" class="form-control"
													placeholder="Address" />
											</div>
										</div>

										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.registrationId" class="form-label">Business
													Registration No</form:label>
												<form:input path="order.registrationId" class="form-control"
													placeholder="Business Registration No" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.authContactPersonName" class="form-label">
													Name</form:label>
												<form:input path="order.authContactPersonName" class="form-control"
													placeholder="Name" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.authContactPersonPhone" class="form-label">
													Phone</form:label>
												<form:input path="order.authContactPersonPhone" class="form-control"
													placeholder="Phone" />
											</div>
										</div>

										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.websiteUrl" class="form-label">Website URL
												</form:label>
												<form:input path="order.websiteUrl" class="form-control"
													placeholder="Website URL" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.bankName" class="form-label">Bank Name
												</form:label>
												<form:input path="order.bankName" class="form-control"
													placeholder="Bank Name" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.accountNo" class="form-label">Bank Account
													No</form:label>
												<form:input path="order.accountNo" class="form-control"
													placeholder="Bank Account No" />
											</div>
										</div>

										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.accountType" class="form-label">Account Type
												</form:label>
												<form:input path="order.accountType" class="form-control"
													placeholder="Account Type" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2 form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.state" class="form-label">State</form:label>
												<form:select path="order.state" class="form-select">
													<form:option value="0">-----------Select-----------</form:option>
													<c:forEach var="state" items="${StateList}">
														<form:option value="${state.value}">${state.name}</form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>
										<div class="col-sm-6 col-md-2 form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.city" class="form-label">City</form:label>
												<form:select path="order.city" class="form-select">
													<form:option value="0">-----------Select-----------</form:option>
													<c:forEach var="city" items="${CityList}">
														<form:option value="${city.value}">${city.name}</form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.postCode" class="form-label">Post Code
												</form:label>
												<form:input path="order.postCode" class="form-control"
													placeholder="Post Code" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.quotation.refreeMID" class="form-label">
													Referral ID</form:label>
												<form:input path="order.quotation.refreeMID" class="form-control"
													placeholder="Referral ID" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.officeEmail" class="form-label">Office Email
												</form:label>
												<form:input path="order.officeEmail" class="form-control"
													placeholder="Office Email" />
											</div>
										</div>


										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.dateIncorporated" class="form-label">Date
													Incorporated</form:label>
												<form:input path="order.dateIncorporated" class="form-control"
													placeholder="Date Incorporated" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.businessType" class="form-label">Business
													Type</form:label>
												<form:select path="order.businessType" class="form-select"
													name="order.businessType">
													<form:option value="0">-----------Select-----------
													</form:option>
													<c:forEach var="nob" items="${businessTypeList}">
														<form:option value="${nob.value}">${nob.name}</form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>

										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.companyType" class="form-label">Company Type
												</form:label>
												<form:select path="order.companyType" class="form-select"
													name="companyType">
													<form:option value="0">-----------Select-----------
													</form:option>
													<c:forEach var="type" items="${companyTypeList}">
														<form:option value="${type.value}">${type.name}
														</form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>

										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="order.natureOfBusiness" class="form-label">Nature
													Of Business
												</form:label>
												<form:select path="order.natureOfBusiness" class="form-select"
													name="natureOfBusiness">
													<form:option value="0">-----------Select-----------
													</form:option>
													<c:forEach var="nob" items="${natureOfBusinessList}">
														<form:option value="${nob.value}">${nob.name}</form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="tab-pane fade" id="other-details-tab-pane" role="tabpanel"
								aria-labelledby="other-details-tab" tabindex="0">

								<div class="d-flex flex-row justify-content-end">
									<button class="btn btn-sm btn-primary action-button" id="button-edit-details"
										type="button">
										Edit Details</button>
									<button class="btn btn-sm btn-primary action-button" id="button-save-details"
										type="submit">
										Save Details</button>
								</div>

								<hr>

								<div id="other-details">
									<div class="row g-3">
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="signedPackage" class="form-label">Signed Package
												</form:label>
												<form:input path="signedPackage" class="form-control"
													placeholder="Signed Package" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="noOfReader" class="form-label">No of Reader
												</form:label>
												<form:input path="noOfReader" class="form-control"
													placeholder="No of Reader" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="documents" class="form-label">Documents</form:label>
												<form:input path="documents" class="form-control"
													placeholder="Documents" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="waiverMonth" class="form-label">Waiver Month
												</form:label>
												<form:input path="waiverMonth" class="form-control"
													placeholder="Waiver Month" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="statusRemarks" class="form-label">Status Remarks
												</form:label>
												<form:input path="statusRemarks" class="form-control"
													placeholder="Status Remarks" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="mdr" class="form-label">MDR</form:label>
												<form:input path="mdr" class="form-control" placeholder="MDR" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="ownerCount" class="form-label">Owner Count
												</form:label>
												<form:input path="ownerCount" class="form-control"
													placeholder="Owner Count" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="reRegister" class="form-label">Reactivated Date
												</form:label>
												<form:input path="reRegister" id="reRegister" type="date"
													class="form-control" placeholder="Re-Register" />
											</div>
										</div>
									</div>
									<hr>
									<div class="row">
										<div class="col-sm-6 col-md-2   form-floating mb-3">
											<div class="mb-3">
												preAuth
												<form:checkbox class="form-check-input" path="preAuth" value="Yes" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												Bank OTP
												<form:checkbox class="form-check-input" path="bankOTP" value="Yes" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2   form-floating mb-3">
											<div class="mb-3">
												Auto Settled
												<form:checkbox class="form-check-input" path="autoSettled"
													value="Yes" />
											</div>
										</div>

										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												iSwitch Discount
												<form:checkbox class="form-check-input" path="iSwitchDiscount"
													value="Yes" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2   form-floating mb-3">
											<div class="mb-3">
												iSwitch2Mobi
												<form:checkbox id="iSwitchEnable" class="form-check-input"
													path="iSwitchEnable" value="Yes" />
											</div>
										</div>

										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												EZYLINK & EZYWAY
												<form:checkbox id="enableBoth" class="form-check-input" path="enblBoth"
													value="Yes" />
											</div>
										</div>

										<div class="col-sm-6 col-md-2   form-floating mb-3">
											<div class="mb-3">
												EZYCOMBO
												<form:checkbox id="ezyComboEnable" class="form-check-input"
													path="ezyComboEnable" value="Yes" />
											</div>
										</div>

										<div class="col-sm-6 col-md-2   form-floating mb-3">
											<div class="mb-3">
												EZYMOTO VCC
												<form:checkbox id="ezyMotoVcc" class="form-check-input"
													path="ezyMotoVcc" value="Yes" />
											</div>
										</div>

										<c:if test="${reg.umEzywireMID != ''}">
											<div class="col-sm-2  form-floating mb-3">
												<div class="mb-3">
													EZYWIRE PLUS
													<form:checkbox id="checkBox-isEzywirePlus" class="form-check-input"
														path="isEzywirePlus" value="Plus" />
												</div>
											</div>
										</c:if>
									</div>

									<div class="h-divider"></div>
									<h5 class="mb-3  mt-3">Payment Methods</h5>

									<div class="mt-2">
										<h6 style="color: red;">Note : Minimum one payment method is required!</h6>
									</div>
									<div class="row">
										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												Card
												<form:checkbox id="checkBox-card" class="form-check-input" path="card"
													value="Yes" />
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												E-Wallets
												<form:checkbox id="checkBox-eWallet" class="form-check-input"
													path="eWallet" value="Yes" />
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												FPX
												<form:checkbox id="checkBox-fpx" class="form-check-input" path="fPX"
													value="Yes" />
											</div>
										</div>


									</div>
								</div>
							</div>
							<div class="tab-pane fade" id="paydee-tab-pane" role="tabpanel" aria-labelledby="paydee-tab"
								tabindex="0">

								<div class="d-flex flex-row justify-content-end">
									<c:if
										test="${reg.eWalletSyncSuccess == '' ||  reg.eWalletSyncSuccess == null || reg.eWalletSyncSuccess == 'false'}">
										<button class="btn btn-sm btn-primary" type="button" id="button-edit-paydee"
											style="display: none;" disabled>Edit Paydee
											MDR</button>
										<button type="button" class="btn btn-sm btn-secondary" disabled readonly>Edit
											Paydee MDR
											(Merchant
											Registration Not Completed)</button>
									</c:if>
									<c:if test="${reg.eWalletSyncSuccess == true}">
										<button class="btn btn-sm btn-primary action-button" type="button"
											id="button-edit-paydee">Edit Paydee MDR</button>
									</c:if>

									<button class="btn btn-sm btn-primary action-button" type="button"
										style="display: none;" id="button-update-paydee">Update Paydee MDR</button>
								</div>
								<hr>
								<div id="paydee-details">
									<div class="row g-3">
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="ezywireMID" class="form-label">EZYWIRE</form:label>
												<form:input path="ezywireMID" class="form-control"
													placeholder="EZYWIRE MID" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="ezylinkMID" class="form-label">EZYLINK</form:label>
												<form:input path="ezylinkMID" class="form-control"
													placeholder="EZYLINK MID" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="ezysplitMID" class="form-label">EZYSPLIT</form:label>
												<form:input path="ezysplitMID" class="form-control"
													placeholder="EZYSPLIT MID" />
											</div>
										</div>

										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="ezyrecplusMID" class="form-label">EZYREC+</form:label>
												<form:input path="ezyrecplusMID" class="form-control"
													placeholder="EZYREC MID" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="ezywayMID" class="form-label">EZYWAY</form:label>
												<form:input path="ezywayMID" class="form-control"
													placeholder="EZYWAY MID" />
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="tab-pane fade" id="umobile-tab-pane" role="tabpanel"
								aria-labelledby="umobile-tab" tabindex="0">
								<div class="d-flex flex-row justify-content-end">

									<c:if
										test="${reg.eWalletSyncSuccess == '' ||  reg.eWalletSyncSuccess == null || reg.eWalletSyncSuccess == 'false'}">
										<button type="button" id="button-edit-umobile" class="btn btn-sm btn-secondary"
											disabled readonly>Edit
											UMobile MDR
											(Merchant
											Registration Not Completed)</button>
										<button class="btn btn-sm btn-primary  disabled-button" type="button"
											id="button-edit-umobile" style="display: none;" disabled>Edit UMobile
											MDR</button>
									</c:if>
									<c:if test="${reg.eWalletSyncSuccess == true}">
										<button class="btn btn-sm btn-primary action-button" type="button"
											id="button-edit-umobile">Edit UMobile MDR</button>
									</c:if>



									<button class="btn btn-sm btn-primary action-button" type="button"
										style="display: none;" id="button-update-umobile">Update UMobile MDR</button>
								</div>
								<hr>
								<div id="umobile-details">
									<div class="row g-3">
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="umEzywireMID" class="form-label">UM EZYWIRE
												</form:label>
												<form:input path="umEzywireMID" class="form-control"
													placeholder="UMobile EZYWIRE MID" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="umEzylinkMID" class="form-label">UM EZYLINK
												</form:label>
												<form:input path="umEzylinkMID" class="form-control"
													placeholder="UMobile EZYLINK MID" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="umEzysplitMID" class="form-label">UM EZYSPLIT
												</form:label>
												<form:input path="umEzysplitMID" class="form-control"
													placeholder="UMobile EZYSPLIT MID" />
											</div>
										</div>

										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="umEzyrecplusMID" class="form-label">UM EZYREC+
												</form:label>
												<form:input path="umEzyrecplusMID" class="form-control"
													placeholder="UMobile EZYREC MID" />
											</div>
										</div>
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<form:label path="umEzywayMID" class="form-label">UM EZYWAY</form:label>
												<form:input path="umEzywayMID" class="form-control"
													placeholder="UMobile EZYWAY MID" />
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="tab-pane fade" id="wallet-tab-pane" role="tabpanel" aria-labelledby="wallet-tab"
								tabindex="0">

								<h6 style="margin: 0 !important; padding: 0 !important;">Click to view MDR details
									for Boost, Grab Pay and FPX.</h6>
								<!-- Button trigger modal -->
								<button type="button" class="btn btn-sm btn-primary action-button"
									data-bs-toggle="modal" data-bs-target="#ewalletDialog"
									style="width: auto; margin-top: 2rem;">
									View E-Wallet Details
								</button>

								<!-- Modal -->
								<div class="modal fade" id="ewalletDialog" tabindex="-1"
									aria-labelledby="ewalletDetails" aria-hidden="true">
									<div class="modal-dialog modal-xl">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="ewalletDetails">E-Wallet Details</h5>
												<button type="button" class="btn-close action-button"
													data-bs-dismiss="modal" aria-label="Close"></button>
											</div>
											<div class="modal-body">
												<div class="row">
													<div class="col-sm-12 col-md-6">
														<form:label path="boostMid" class="form-label">Boost MID
														</form:label>
														<form:input path="boostMid" class="form-control"
															placeholder="Boost MID" readonly="true" />
													</div>
													<div class="col-sm-12 col-md-6">
														<form:label path="fpxMid" class="form-label">FPX MID
														</form:label>
														<form:input path="fpxMid" class="form-control"
															placeholder="FPX MID" readonly="true" />

													</div>

												</div>
												
												
												<div class="row">
														<div class="col-sm-12 col-md-6">
															<form:label path="tngMid" class="form-label">Tng MID
															</form:label>
															<form:input path="tngMid" class="form-control"
																placeholder="Tng MID" readonly="true" />
														</div>
														
														<div class="col-sm-12 col-md-6">
															<form:label path="shopeepayMid" class="form-label">Shopeepay MID
															</form:label>
															<form:input path="shopeepayMid" class="form-control"
																placeholder="Shopeepay MID" readonly="true" />

														</div>

													</div>
												
												
												
												
												

												<div class="row">
													<div class="col-sm-12 col-md-6">
														<form:label path="boostTid" class="form-label">Boost TID
														</form:label>
														<form:input path="boostTid" class="form-control"
															placeholder="Boost TID" readonly="true" />
													</div>
													<div class="col-sm-12 col-md-6">
														<form:label path="fpxTid" class="form-label">FPX TID
														</form:label>
														<form:input path="fpxTid" class="form-control"
															placeholder="FPX TID" readonly="true" />

													</div>


												</div>
												
												
												
																<div class="row">
														<div class="col-sm-12 col-md-6">
															<form:label path="tngTid" class="form-label">Tng TID
															</form:label>
															<form:input path="tngTid" class="form-control"
																placeholder="Tng TID" readonly="true" />
														</div>
														<div class="col-sm-12 col-md-6">
															<form:label path="shopeepayTid" class="form-label">Shopeepay TID
															</form:label>
															<form:input path="shopeepayTid" class="form-control"
																placeholder="Shopeepay TID" readonly="true" />

														</div>

													</div>							
																					
																					
																					
										
													
													<hr>
													<div class="row mt-4">
														<div class="col-sm-12 col-md-6">
															<label class="form-label">Boost MDR E-Commerce</label>
															<input value="${quotationMDR.boostMDREcomm}"
																class="form-control" placeholder="Grab MID"
																readonly="true" />
														</div>
														<div class="col-sm-12 col-md-6">
															<label class="form-label">Boost MDR QR Code</label>
															<input value="${quotationMDR.boostMDRQR}"
																class="form-control" placeholder="Grab MID"
																readonly="true" />
														</div>

														<div class="col-sm-12 col-md-6">
															<label class="form-label">FPX MDR RM</label>
															<input value="${quotationMDR.fPXMDR_RM}"
																class="form-control" placeholder="Grab MID"
																readonly="true" />
														</div>
														<div class="col-sm-12 col-md-6">
															<label class="form-label">FPX MDR Percentage</label>
															<input value="${quotationMDR.fPXMDR_Percent}"
																class="form-control" placeholder="Grab MID"
																readonly="true" />
														</div>
														
														
														
														
														<div class="col-sm-12 col-md-6">
															<label class="form-label">Tng MDR E-Commerce</label>
															<input value="${quotationMDR.tngMDREcomm}"
																class="form-control" placeholder="Tng Ecomm"
																readonly="true" />
														</div>
														
														
														<div class="col-sm-12 col-md-6">
															<label class="form-label">Tng MDR QR Code</label>
															<input value="${quotationMDR.tngMDRQR}"
																class="form-control" placeholder="Tng QR"
																readonly="true" />
														</div>
														
														
														
														<div class="col-sm-12 col-md-6">
															<label class="form-label">Shopeepay MDR E-Commerce</label>
															<input value="${quotationMDR.shopeepayMDREcomm}"
																class="form-control" placeholder="Shopeepay Ecomm"
																readonly="true" />
														</div>
														
														<div class="col-sm-12 col-md-6">
															<label class="form-label">Shopeepay MDR QR Code</label>
															<input value="${quotationMDR.shopeepayMDRQR}"
																class="form-control" placeholder="Shopeepay QR"
																readonly="true" />
														</div>
														
														
														
														
														
														

													</div>
												</div>
											</div>
											<!-- On Merchant registration completed  -->
											<!-- <div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-bs-dismiss="modal">Close</button>

												<c:if
													test="${reg.eWalletSyncSuccess == '' ||  reg.eWalletSyncSuccess == null || reg.eWalletSyncSuccess == 'false'}">
													<button type="button" class="btn btn-primary"
														onclick="subMitEwalletMDR()" data-bs-dismiss="modal">Submit
														MDR</button>
												</c:if>
												<c:if test="${reg.eWalletSyncSuccess == 'true'}">
													<button type="button" class="btn btn-primary"
														data-bs-dismiss="modal" disabled>MDR Submitted</button>
												</c:if>
											</div> -->
										</div>
									</div>
								


								<hr>
								<h5 class="mb-3  mt-3">Grab Pay</h5>

								<div class="row">
									<div class="col-sm-12 col-md-4">
										<form:label path="grabMidAcquired" class="form-label">Grab MID Acquired
										</form:label>
										<form:input path="grabMidAcquired" id="id-grabMidAcquired" class="form-control action-input"
											placeholder="Grab MID Acquired" />
									</div>

									<div class="col-sm-12 col-md-4">
										<form:label path="grabMid" class="form-label">Grab MID
										</form:label>
										<form:input path="grabMid" class="form-control" placeholder="Grab MID"
											readonly="true" />
									</div>

									<div class="col-sm-12 col-md-4">
										<form:label path="grabTid" class="form-label">Grab TID
										</form:label>
										<form:input path="grabTid" class="form-control" placeholder="Grab TID" />
									</div>
								</div>

								<div class="row mt-3">

									<div class="col-sm-12 col-md-4">
										<label class="form-label">Grab Pay MDR E-Commerce</label>
										<input value="${quotationMDR.grabMDREcomm}" class="form-control"
											placeholder="GGrab Pay MDR E-Commerce" readonly="true" />
									</div>

									<div class="col-sm-12 col-md-4">
										<label class="form-label">Grab Pay MDR QR Code</label>
										<input value="${quotationMDR.grabMDRQR}" class="form-control"
											placeholder="Grab Pay MDR QR Code" readonly="true" />
									</div>
								</div>

								<div class="d-flex flex-column bd-highlight justify-content-center">
									<div style="width: fit-content;">
										<h6 class="m-2">Sync Grab Pay Details with payment server</h6>
										<c:if
											test="${reg.eWalletSyncSuccess == '' ||  reg.eWalletSyncSuccess == null || reg.eWalletSyncSuccess == 'false'}">
											<button class="btn btn-secondary btn-sm" readonly="true">Update Grab Pay MDR
												(Merchant
												Registration Not Completed)</button>
											<button class="btn btn-primary btn-sm" id="submitGrabPayMDR"
												style="display: none;" disabled>Update Grab Pay
												MDR</button>
										</c:if>
										<c:if test="${reg.eWalletSyncSuccess == true}">
											<button class="btn btn-primary btn-sm action-button"
												id="submitGrabPayMDR">Update Grab Pay
												MDR</button>
										</c:if>
									</div>

								</div>
							</div>
							<div class="tab-pane fade" id="director-tab-pane" role="tabpanel"
								aria-labelledby="director-tab" tabindex="0">

								<div class="row g-3">
									<div class="table-responsive">
										<table class="table" id="DirectorTable">
											<thead>
												<tr class="bcf4f4f4 c617075 fw500">
													<th>ID</th>
													<th>Name</th>
													<th>Designation</th>
													<th>Contact No</th>
													<th>Nationality</th>
												</tr>
											</thead>
											<tbody class="c3f3f3f fw500 tblOrderLineBody">
												<c:forEach var="tempDirector" items="${reg.order.directors}"
													varStatus="count">
													<tr>
														<td>${tempDirector.id}</td>
														<td>${tempDirector.name}</td>
														<td>${tempDirector.designation}</td>
														<td>${tempDirector.contactNo}</td>
														<td>${tempDirector.nationality}</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<div class="tab-pane fade" id="items-tab-pane" role="tabpanel" aria-labelledby="items-tab"
								tabindex="0">

								<div class="row g-3">
									<div class="table-responsive">
										<table class="table" id="OrderLineTable">
											<thead>
												<tr class="bcf4f4f4 c617075 fw500">
													<th>Line ID</th>
													<th>Description</th>
													<th>Product</th>
													<th>Quantity</th>
													<th>Amount (RM)</th>
													<th style="text-align: right">Actions</th>
												</tr>
											</thead>
											<tbody class="c3f3f3f fw500 tblOrderLineBody">

												<c:forEach var="tempOrderLine" items="${orderLineList}"
													varStatus="count">
													<c:url var="viewLink" value="">
														<c:param name="id" value="${tempOrderLine.id}"></c:param>
													</c:url>

													<tr>
														<td>${tempOrderLine.id}</td>
														<td>${tempOrderLine.product.productName}</td>
														<td>${tempOrderLine.product.productType}</td>
														<td>${tempOrderLine.quantity}</td>
														<td style="text-align: center;">
															${tempOrderLine.product.unitPrice}</td>
														<td style="text-align: right">
															<c:if test="${reg.eWalletSyncSuccess == true}">
																<a class="btn btn-primary btn-sm edit"
																	data-id="${tempOrderLine.id}"
																	data-backdrop="false">View</a>

															</c:if>

															<c:if
																test="${reg.eWalletSyncSuccess == '' ||  reg.eWalletSyncSuccess == null || reg.eWalletSyncSuccess == 'false'}">
																<button class="btn btn-sm btn-secondary" disabled>View
																	(Merchant Registration not completed)</button>

															</c:if>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>

						<div class="bottom-fixed">
							<c:if
								test="${reg.eWalletSyncSuccess == '' ||  reg.eWalletSyncSuccess == null || reg.eWalletSyncSuccess == 'false'}">
								<button type="submit" class="btn btn-sm btn-primary action-button"
									id="button-register-merchant">Register Merchant</button>

							</c:if>
							<c:if test="${reg.eWalletSyncSuccess == true}">
								<button type="submit" class="btn btn-sm btn-primary action-button"
									id="button-register-merchant" style="display: none;">Register Merchant</button>

								<p class="btn btn-sm btn-secondary action-button" readonly>
									Merchant Registration Completed</p>
							</c:if>

							<!-- <button type="button" class="btn btn-sm btn-primary action-button"
								id="btnMerchantRegUpdate">Update Merchant Details</button> -->
						</div>
					</form:form>
				</div>
			</main>

			<!-- MDR response Message Modal -->
			<div class="modal fade" id="mdr-response" tabindex="-1" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Message</h5>
							<button type="button" class="btn-close action-button" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<p id="mdr-response-data">Modal body text goes here.</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary action-button"
								data-bs-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>

			<!-- Modal -->
			<div class="modal fade" id="mdrSubmitModalNonEzysplit" tabindex="-1" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog modal-dialog-scrollable  modal-lg">
					<div class="modal-content verify-mdr">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Verify MDR</h5>
							<button type="button" class="btn-close action-button" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<form:form action="">
								<div>
									<input type="hidden" id="quotationId" /> <input type="hidden" id="productId" />
									<input type="hidden" id="orderLineId" /> <input type="hidden"
										id="quotationMdrRateId" /> <input type="hidden"
										id="quotationEzysplitMdrRateId" /> <input type="hidden" id="createdOn" />
								</div>
								<div class="row g-3">
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">MID</label> <input id="mid" class="form-control"
												placeholder="MID" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">TID</label> <input id="tid" class="form-control"
												placeholder="TID" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">DTL</label> <input id="dtl" class="form-control"
												placeholder="DTL" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Hashkey</label> <input id="hashkey"
												class="form-control" placeholder="Hashkey" />
										</div>
									</div>

								</div>
								<div class="row g-3">
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Callback URL</label> <input id="callbackURL"
												class="form-control" placeholder="Callback URL" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">EZYWIRE Device ID</label> <input
												id="ezywireDeviceId" class="form-control"
												placeholder="EZYWIRE Device ID" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">EZYWIRE Ref No</label> <input id="ezywireRefNo"
												class="form-control" placeholder="EZYWIRE Ref No" />
										</div>
									</div>


								</div>
								<div class="row g-3">
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Product</label> <input id="productName"
												class="form-control" placeholder="Product" readonly="readonly" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Settlement</label> <input id="productSettlement"
												class="form-control" placeholder="Settlement" readonly="readonly" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Amount</label> <input id="amount"
												class="form-control" placeholder="Amount" readonly="readonly" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Host Type</label> <input id="hostType"
												class="form-control" placeholder="Host Type" readonly="readonly" />
										</div>
									</div>

								</div>

								<div class="row g-3">
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Subscription</label> <input id="subscription"
												class="form-control" placeholder="Product" readonly="readonly" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Pay Later</label> <input id="payLater"
												class="form-control" placeholder="Settlement" readonly="readonly" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Product Type</label> <input id="productType"
												class="form-control" placeholder="Product Type" readonly="readonly" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Quantity</label> <input id="quantity"
												class="form-control" placeholder="Quantity" readonly="readonly" />
										</div>
									</div>

								</div>
								<div class="row g-3">
									<div class="col-sm-2  form-floating mb-3">
										<div class="mb-3">
											Update MDR <input type="checkbox" id="chkUpdateMDR" name="chkUpdateMDR" />
										</div>
									</div>
								</div>
								<label><b>Merchant Rates</b></label>
								<div class="nonEzysplitMerchantRate">
									<div class="row g-3">
										<div class="col-sm-6 col-md-2  form-floating mb-2">
											<div class="mb-2">
												<label class="form-label">Local Debit (%)</label>
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-1">
											<div class="mb-2">
												<input id="loc_Debit_Merch_Visa" class="form-control" type="number"
													min="0" max="5" step="0.001" readonly="readonly" placeholder="" />
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-2">
											<div class="mb-2">
												<input id="loc_Debit_Merch_Master" class="form-control" type="number"
													min="0" max="5" step="0.001" readonly="readonly" placeholder="" />
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												<input id="loc_Debit_Merch_Union" class="form-control" type="number"
													min="0" max="5" step="0.001" readonly="readonly" placeholder="" />
											</div>
										</div>
									</div>

									<div class="row g-3">
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<label class="form-label">Local Credit (%)</label>
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												<input id="loc_Credit_Merch_Visa" class="form-control" type="number"
													min="0" max="5" step="0.001" readonly="readonly" placeholder="" />
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												<input id="loc_Credit_Merch_Master" class="form-control" type="number"
													min="0" max="5" step="0.001" readonly="readonly" placeholder="" />
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												<input id="loc_Credit_Merch_Union" class="form-control" type="number"
													min="0" max="5" step="0.001" readonly="readonly" placeholder="" />
											</div>
										</div>
									</div>

									<div class="row g-3">
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<label class="form-label">Foreign Debit (%)</label>
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												<input id="for_Debit_Merch_Visa" class="form-control" type="number"
													min="0" max="5" step="0.001" readonly="readonly" placeholder="" />
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												<input id="for_Debit_Merch_Master" class="form-control" type="number"
													min="0" max="5" step="0.001" readonly="readonly" placeholder="" />
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												<input id="for_Debit_Merch_Union" class="form-control" type="number"
													min="0" max="5" step="0.001" readonly="readonly" placeholder="" />
											</div>
										</div>
									</div>
									<div class="row g-3">
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<label class="form-label">Foreign Credit (%)</label>
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												<input id="for_Credit_Merch_Visa" class="form-control" type="number"
													min="0" max="5" step="0.001" readonly="readonly" placeholder="" />
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												<input id="for_Credit_Merch_Master" class="form-control" type="number"
													min="0" max="5" step="0.001" readonly="readonly" placeholder="" />
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												<input id="for_Credit_Merch_Union" class="form-control" type="number"
													min="0" max="5" step="0.001" readonly="readonly" placeholder="" />
											</div>
										</div>
									</div>
								</div>


							</form:form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary submitMDR" id="submitMDR">Submit MDR</button>
						</div>
					</div>
				</div>
			</div>

			<!-- Modal -->
			<div class="modal fade" id="mdrSubmitModalEzysplit" tabindex="-1" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog modal-dialog-scrollable  modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Verify MDR</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<form:form action="">
								<div>
									<input type="hidden" id="quotationId_EzysplitMDRView" /> <input type="hidden"
										id="productId_EzysplitMDRView" /> <input type="hidden"
										id="orderLineId_EzysplitMDRView" /> <input type="hidden"
										id="quotationMdrRateId_EzysplitMDRView" /> <input type="hidden"
										id="quotationEzysplitMdrRateId_EzysplitMDRView" />
									<input type="hidden" id="createdOn_EzysplitMDRView" />
								</div>
								<div class="row g-3">
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">MID</label> <input id="mid_EzysplitMDRView"
												class="form-control" placeholder="MID" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">TID</label> <input id="tid_EzysplitMDRView"
												class="form-control" placeholder="TID" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">DTL</label> <input id="dtl_EzysplitMDRView"
												class="form-control" placeholder="DTL" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Hashkey</label> <input
												id="hashkey_EzysplitMDRView" class="form-control"
												placeholder="Hashkey" />
										</div>
									</div>

								</div>
								<div class="row g-3">
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Callback URL</label> <input
												id="callbackURL_EzysplitMDRView" class="form-control"
												placeholder="Callback URL" />
										</div>
									</div>


									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">EZYWAY TID</label> <input
												id="ezywayTID_EzysplitMDRView" class="form-control"
												placeholder="(Only For EZYSPLIT)" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">EZYLINK TID</label> <input
												id="ezylinkTID_EzysplitMDRView" class="form-control"
												placeholder="(Only For EZYSPLIT)" />
										</div>
									</div>

								</div>
								<div class="row g-3">
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Product</label> <input
												id="productName_EzysplitMDRView" class="form-control"
												placeholder="Product" readonly="readonly" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Settlement</label> <input
												id="productSettlement_EzysplitMDRView" class="form-control"
												placeholder="Settlement" readonly="readonly" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Amount</label> <input id="amount_EzysplitMDRView"
												class="form-control" placeholder="Amount" readonly="readonly" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Host Type</label> <input
												id="hostType_EzysplitMDRView" class="form-control"
												placeholder="Host Type" readonly="readonly" />
										</div>
									</div>

								</div>

								<div class="row g-3">
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Subscription</label> <input
												id="subscription_EzysplitMDRView" class="form-control"
												placeholder="Product" readonly="readonly" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Pay Later</label> <input
												id="payLater_EzysplitMDRView" class="form-control"
												placeholder="Settlement" readonly="readonly" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Product Type</label> <input
												id="productType_EzysplitMDRView" class="form-control"
												placeholder="Product Type" readonly="readonly" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Quantity</label> <input
												id="quantity_EzysplitMDRView" class="form-control"
												placeholder="Quantity" readonly="readonly" />
										</div>
									</div>
								</div>
								<div class="row g-3">
									<div class="col-sm-2  form-floating mb-3">
										<div class="mb-3">
											Update MDR <input type="checkbox" id="chkUpdateMDR"
												name="chkUpdateMDR_EzysplitMDRView" />
										</div>
									</div>
								</div>
								<label><b>Merchant Rates</b></label>

								<div class="ezysplitMerchantRate">
									<label><b>Ezysplit Part</b></label>

									<div class="row g-3">
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<label class="form-label">Insta 03(%)</label>
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												<input id="loc_Credit_Merch_Master_Ezysplit_Insta3" class="form-control"
													type="number" min="0" max="5" step="0.001" readonly="readonly"
													placeholder="" />
											</div>
										</div>

										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												<input id="loc_Credit_Cus_Master_Ezysplit_Insta3" class="form-control"
													readonly="readonly" placeholder="" />
											</div>
										</div>
									</div>
									<div class="row g-3">
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<label class="form-label">Insta 06(%)</label>
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												<input id="loc_Credit_Merch_Master_Ezysplit_Insta6" class="form-control"
													type="number" min="0" max="5" step="0.001" readonly="readonly"
													placeholder="" />
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												<input id="loc_Credit_Cus_Master_Ezysplit_Insta6" class="form-control"
													readonly="readonly" placeholder="" />
											</div>
										</div>
									</div>
									<div class="row g-3">
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<label class="form-label">Insta 09(%)</label>
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												<input id="loc_Credit_Merch_Master_Ezysplit_Insta9" class="form-control"
													type="number" min="0" max="5" step="0.001" readonly="readonly"
													placeholder="" />
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												<input id="loc_Credit_Cus_Master_Ezysplit_Insta9" class="form-control"
													readonly="readonly" placeholder="" />
											</div>
										</div>
									</div>
									<div class="row g-3">
										<div class="col-sm-6 col-md-2  form-floating mb-3">
											<div class="mb-3">
												<label class="form-label">Insta 12(%)</label>
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												<input id="loc_Credit_Merch_Master_Ezysplit_Insta12" type="number"
													min="0" max="5" step="0.001" class="form-control"
													readonly="readonly" placeholder="" />
											</div>
										</div>
										<div class="col-sm-2  form-floating mb-3">
											<div class="mb-3">
												<input id="loc_Credit_Cus_Master_Ezysplit_Insta12" class="form-control"
													readonly="readonly" placeholder="" />
											</div>
										</div>
									</div>
								</div>
							</form:form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary submitMDREzysplit"
								id="submitMDREzysplit">Submit MDR</button>
						</div>
					</div>
				</div>
			</div>

		</body>

		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap5.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>

		<script>

			var token = $("meta[name='_csrf']").attr("content")
			var header = $("meta[name='_csrf_header']").attr("content")
			var contextPath = "${pageContext.request.contextPath}"

			const merchantRegistrationId = "${merchantRegistrationId}"

			const buttonEditDetails = document.getElementById("button-edit-details")
			const buttonSaveDetails = document.getElementById("button-save-details")
			const buttonRegisterMerchant = document.getElementById("button-register-merchant")
			const buttonEditPaydee = document.getElementById("button-edit-paydee")
			const buttonSavePaydee = document.getElementById("button-update-paydee")
			const buttonEditUMobile = document.getElementById("button-edit-umobile")
			const buttonSaveUMobile = document.getElementById("button-update-umobile")

			buttonEditPaydee.addEventListener('click', (event) => {
				blockAllClickEvent(buttonSavePaydee)
				event.target.style.display = "none";
				const paydeeDetails = document.querySelector("#paydee-details")
				const paydeeInpuFields = paydeeDetails.querySelectorAll("input")
				paydeeInpuFields.forEach(element => {
					$(element).removeAttr('readonly')
				});
				buttonSavePaydee.style.display = "block"
			});

			buttonSavePaydee.addEventListener('click', (event) => {
				event.target.style.display = "none";
				const paydeeDetails = document.querySelector("#paydee-details")
				const paydeeInpuFields = paydeeDetails.querySelectorAll("input")
				paydeeInpuFields.forEach(element => {
					$(element).attr('readonly', true)
				});
				merchantRegistrationUpdate(event);
				buttonEditPaydee.style.display = "block"
			});

			buttonEditUMobile.addEventListener('click', (event) => {
				blockAllClickEvent(buttonSaveUMobile)
				event.target.style.display = "none"

				const umobileDetails = document.querySelector("#umobile-details")
				const umobileInputFields = umobileDetails.querySelectorAll("input")
				umobileInputFields.forEach(element => {
					$(element).removeAttr('readonly')
				});

				buttonSaveUMobile.style.display = "block"
			});

			buttonSaveUMobile.addEventListener('click', (event) => {
				event.target.style.display = "none"

				const umobileDetails = document.querySelector("#umobile-details")
				const umobileInputFields = umobileDetails.querySelectorAll("input")
				umobileInputFields.forEach(element => {
					$(element).attr('readonly', true)
				});
				merchantRegistrationUpdate(event);
				buttonEditUMobile.style.display = "block"
			});

			buttonEditDetails.addEventListener('click', (event) => {
				const divElem = document.querySelector("#other-details");
				const inputElements = divElem.querySelectorAll("input, select, checkbox, textarea")
				inputElements.forEach(element => {
					$(element).attr('readonly', false);
				});
				event.target.style.display = 'none'
				buttonSaveDetails.style.display = 'block'
			})


			buttonSaveDetails.addEventListener('click', (event) => {

				var card = document.getElementById("checkBox-card").checked
				var eWallet = document.getElementById("checkBox-eWallet").checked
				var fpx = document.getElementById("checkBox-fpx").checked

				if (card === true || eWallet === true || fpx === true) {
					startLoadingScreen("Processing, please wait...");
					$('#MerchantRegForm').attr('action', 'updateRegistration');
				} else {
					event.preventDefault();
					alert("Any one of the payment is mandatory")
				}

			})

			function blockAllClickEvent(element) {
				const inputs = document.querySelectorAll("input, select, checkbox")
				inputs.forEach(element => {
					$(element).attr('readonly', true)
				});

				const buttons = document.querySelectorAll("button")
				buttons.forEach(element => {
					$(element).attr('disabled', true)
				});

				$(element).removeAttr('disabled')
			}

			var mdrSubmitModalNonEzysplit = new bootstrap.Modal(document
				.getElementById('mdrSubmitModalNonEzysplit'), {
				keyboard: false
			})
			var mdrSubmitModalEzysplit = new bootstrap.Modal(document
				.getElementById('mdrSubmitModalEzysplit'), {
				keyboard: false
			})

			document.getElementById('submitMDR').addEventListener("click", function () {
				mdrSubmitModalNonEzysplit.hide()
				startLoadingScreen("Loading, please wait...")
			});

			document.getElementById('submitMDREzysplit').addEventListener("click",
				function () {
					mdrSubmitModalEzysplit.hide()
					startLoadingScreen("Loading, please wait...")
				});

			window.onload = function () {
				buttonSaveDetails.style.display = 'none'

				var urlParams = new URLSearchParams(location.search);
				if (parseInt(urlParams.get("registerMessage")) !== 0) {
					$('#mdr-response').modal({
						backdrop: 'static',
						keyboard: false
					});
					$('#mdr-response').modal('show');
					document.getElementById("mdr-response-data").innerText = urlParams
						.get("registerMessage")
				}


				document.getElementById("reRegister").value = "${reg.reRegister}".split(" ")[0]
				document.getElementById("reRegister").min = moment().format("YYYY-MM-DD")

				$('#orderDetailDiv :input').attr('disabled', true);

				var token = $("meta[name='_csrf']").attr("content");
				var header = $("meta[name='_csrf_header']").attr("content");

				console.log("token-> ", token)
				console.log("header-> ", token)

				$(document).ajaxSend(function (e, xhr, options) {
					xhr.setRequestHeader(header, token);
				});

				$.ajaxSetup({
					headers: {
						'X-CSRF-TOKEN': $('meta[name="csrf-token"]')
							.attr('content')
					}
				});

				$('input').attr('readonly', true);
				$('button').attr('disabled', true);

				$('button.action-button').removeAttr('disabled');
				$('.action-input').removeAttr('readonly');
				$('.nav-link').removeAttr('disabled');
				$('input.readOnly').attr('readonly', true);

				$('.verify-mdr input').removeAttr('readonly');
				$('.verify-mdr button').removeAttr('disabled');
				$('.verify-mdr select').removeAttr('disabled');

				$('select').removeAttr('disabled');

			}



			buttonRegisterMerchant.addEventListener('click', (event) => {

				var card = document.getElementById("checkBox-card").checked
				var eWallet = document.getElementById("checkBox-eWallet").checked
				var fpx = document.getElementById("checkBox-fpx").checked
				
				

// 				if (card === true || eWallet === true || fpx === true) {
// 					startLoadingScreen("loading, please wait")
// 					$('#MerchantRegForm').attr('action', 'registerMerchant');
// 				} else {
// 					event.preventDefault();
// 					alert("Any one of the payment is mandatory")
// 				}
				
				//SALEPERSON REQUIRED
                var salesPerson = document.getElementById("salesPerson").value;
                
                
               if ((card === true || eWallet === true || fpx === true) && salesPerson!=0) {
                    
                    startLoadingScreen("loading, please wait")
                    $('#MerchantRegForm').attr('action', 'registerMerchant');
                } else {
                    //SALEPERSON REQUIRED START
                    event.preventDefault();
                    if(salesPerson==0){
                        
                        document.getElementById("salesPerson_error").style.display="block";
                    
                        
                    }else{
                        document.getElementById("salesPerson_error").style.display="none";
                        
                    }
                    
                    //SALEPERSON REQUIRED
                    
                    if((card === true || eWallet === true || fpx === true) ) {
                        
                    }else{
                        alert("Any one of the payment is mandatory");
                    }
                    
                }
				
				
				

			});

			$('#btnMerchantRegUpdate').click(function (event) {
				merchantRegistrationUpdate(event);
			});

			function merchantRegistrationUpdate(event) {
				var card = document.getElementById("checkBox-card").checked;
				var eWallet = document.getElementById("checkBox-eWallet").checked;
				var fpx = document.getElementById("checkBox-fpx").checked;

				if (card === true || eWallet === true || fpx === true) {
					$('#MerchantRegForm').attr('action', 'registerMerchantUpdate');
					$('#MerchantRegForm').submit();
					startLoadingScreen("loading, please wait");
				} else {
					event.preventDefault();
					alert("Any one of the payment is mandatory");
				}

			}

			$(function () {
				$(".datepick").datepicker();
			});

			$(document).on('click', '.edit', function (e) {
				e.preventDefault();
				var orderLineId = $(this).attr(
					'data-id');

				/* $('#mdrSubmitModal').modal('show'); */

				var productId = $('#productId')
					.val();

				$ .ajax({
						type: 'GET',
						url: "getOrderLineDetails?orderLineId="
							+ orderLineId,
						success: function (data) {
							console.log(data)

							if (data.productType == 'EZYSPLIT') {
								$(
									'#mdrSubmitModalEzysplit')
									.modal(
										'show');

								$(
									'#quotationMdrRateId_EzysplitMDRView')
									.val(
										data.quotationMdrRateId);
								$(
									'#quotationEzysplitMdrRateId_EzysplitMDRView')
									.val(
										data.quotationEzysplitMdrRateId);
								$(
									'#quotationId_EzysplitMDRView')
									.val(
										data.quotationId);
								$(
									'#productId_EzysplitMDRView')
									.val(
										data.productId);
								$(
									'#orderLineId_EzysplitMDRView')
									.val(
										data.orderLineId);

								$(
									'#mid_EzysplitMDRView')
									.val(
										data.mid);
								$(
									'#tid_EzysplitMDRView')
									.val(
										data.tid);
								$(
									'#dtl_EzysplitMDRView')
									.val(
										data.dtl);
								$(
									'#hashkey_EzysplitMDRView')
									.val(
										data.hashkey);

								$(
									'#callbackURL_EzysplitMDRView')
									.val(
										data.callbackURL);

								$(
									'#productName_EzysplitMDRView')
									.val(
										data.productName);
								$(
									'#productSettlement_EzysplitMDRView')
									.val(
										data.productSettlement);
								$(
									'#amount_EzysplitMDRView')
									.val(
										data.unitPrice);
								$(
									'#hostType_EzysplitMDRView')
									.val(
										data.hostType);
								$(
									'#subscription_EzysplitMDRView')
									.val(
										data.subscription);
								$(
									'#payLater_EzysplitMDRView')
									.val(
										data.payLater);
								$(
									'#productType_EzysplitMDRView')
									.val(
										data.productType);
								$(
									'#quantity_EzysplitMDRView')
									.val(
										data.quantity);
								$(
									'#ezywayTid_EzysplitMDRView')
									.val(
										data.ezywayTid);
								$(
									'#ezylinkTid_EzysplitMDRView')
									.val(
										data.ezylinktid);

								//EZYSPLIT Part
								$(
									'#loc_Credit_Merch_Master_Ezysplit_Insta3')
									.val(
										data.loc_Credit_Merch_Master_Ezysplit_Insta3);
								$(
									'#loc_Credit_Merch_Master_Ezysplit_Insta6')
									.val(
										data.loc_Credit_Merch_Master_Ezysplit_Insta6);
								$(
									'#loc_Credit_Merch_Master_Ezysplit_Insta9')
									.val(
										data.loc_Credit_Merch_Master_Ezysplit_Insta9);
								$(
									'#loc_Credit_Merch_Master_Ezysplit_Insta12')
									.val(
										data.loc_Credit_Merch_Master_Ezysplit_Insta12);

								$(
									'#loc_Credit_Cus_Master_Ezysplit_Insta3')
									.val(
										data.loc_Credit_Cus_Master_Ezysplit_Insta3);
								$(
									'#loc_Credit_Cus_Master_Ezysplit_Insta6')
									.val(
										data.loc_Credit_Cus_Master_Ezysplit_Insta6);
								$(
									'#loc_Credit_Cus_Master_Ezysplit_Insta9')
									.val(
										data.loc_Credit_Cus_Master_Ezysplit_Insta9);
								$(
									'#loc_Credit_Cus_Master_Ezysplit_Insta12')
									.val(
										data.loc_Credit_Cus_Master_Ezysplit_Insta12);

							} else {
								$(
									'#mdrSubmitModalNonEzysplit')
									.modal(
										'show');

								$(
									'#quotationMdrRateId')
									.val(
										data.quotationMdrRateId);
								$(
									'#quotationEzysplitMdrRateId')
									.val(
										data.quotationEzysplitMdrRateId);
								$(
									'#quotationId')
									.val(
										data.quotationId);
								$('#productId')
									.val(
										data.productId);
								$(
									'#orderLineId')
									.val(
										data.orderLineId);

								$('#mid')
									.val(
										data.mid);
								$('#tid')
									.val(
										data.tid);
								$('#dtl')
									.val(
										data.dtl);
								$('#hashkey')
									.val(
										data.hashkey);

								$(
									'#callbackURL')
									.val(
										data.callbackURL);
								$(
									'#ezywireDeviceId')
									.val(
										data.ezywireDeviceId);
								$(
									'#ezywireRefNo')
									.val(
										data.ezywireRefNo);

								$(
									'#productName')
									.val(
										data.productName);
								$(
									'#productSettlement')
									.val(
										data.productSettlement);
								$('#amount')
									.val(
										data.unitPrice);
								$('#hostType')
									.val(
										data.hostType);
								$(
									'#subscription')
									.val(
										data.subscription);
								$('#payLater')
									.val(
										data.payLater);
								$(
									'#productType')
									.val(
										data.productType);
								$('#quantity')
									.val(
										data.quantity);

								$(
									'#loc_Debit_Merch_Visa')
									.val(
										data.loc_Debit_Merch_Visa);
								$(
									'#loc_Debit_Merch_Master')
									.val(
										data.loc_Debit_Merch_Master);
								$(
									'#loc_Debit_Merch_Union')
									.val(
										data.loc_Debit_Merch_Union);
								$(
									'#for_Debit_Merch_Visa')
									.val(
										data.for_Debit_Merch_Visa);
								$(
									'#for_Debit_Merch_Master')
									.val(
										data.for_Debit_Merch_Master);
								$(
									'#for_Debit_Merch_Master')
									.val(
										data.for_Debit_Merch_Master);
								$(
									'#for_Debit_Merch_Union')
									.val(
										data.for_Debit_Merch_Union);

								$(
									'#loc_Credit_Merch_Visa')
									.val(
										data.loc_Credit_Merch_Visa);
								$(
									'#loc_Credit_Merch_Master')
									.val(
										data.loc_Credit_Merch_Master);
								$(
									'#loc_Credit_Merch_Union')
									.val(
										data.loc_Credit_Merch_Union);
								$(
									'#for_Credit_Merch_Visa')
									.val(
										data.for_Credit_Merch_Visa);
								$(
									'#for_Credit_Merch_Master')
									.val(
										data.for_Credit_Merch_Master);
								$(
									'#for_Credit_Merch_Master')
									.val(
										data.for_Credit_Merch_Master);
								$(
									'#for_Credit_Merch_Union')
									.val(
										data.for_Credit_Merch_Union);
							}
						},
					});
			});


			document.getElementById("submitGrabPayMDR").addEventListener('click', (event) => {
				event.preventDefault();

				const grabMidAcquired = document.getElementById("id-grabMidAcquired").value.trim()

				startLoadingScreen("Processing, please wait...")
				var config = {
					method: 'post',
					url: contextPath + "/merchant/registration/grabPay/mdr/sync",
					headers: {
						'X-CSRF-TOKEN': token,
						'Content-Type': 'application/json'
					},
					params: {
						registrationID: merchantRegistrationId,
						grabPayAccquired: grabMidAcquired
					}
				};

				axios(config)
					.then(function (response) {
						return response.data;
					})
					.then(function (response) {
						alert(response.responseMessage)
						window.location.reload()
					})
					.catch(function (error) {
						console.log(error);
					})
					.finally(() => {
						stopLoadingScreen()
					})
			})

			$('.submitMDR').click(function (e) {
				var updateMDR = '';
				if ($('#chkUpdateMDR').prop('checked')) {
					updateMDR = 'Yes';
				} else {
					updateMDR = 'No';
				}

				$.post("submitMDRRates", {
					orderId: $('#orderId').val(),
					merchantRegistrationId: merchantRegistrationId,
					quotationMdrRateId: $(
						'#quotationMdrRateId')
						.val(),
					quotationEzysplitMdrRateId: $(
						'#quotationEzysplitMdrRateId')
						.val(),
					quotationId: $('#quotationId')
						.val(),
					productId: $('#productId').val(),
					productType: $('#productType')
						.val(),
					orderLineId: $('#orderLineId')
						.val(),

					/* loc_Credit_Merch_Master_Ezysplit_Insta3: $('#loc_Credit_Merch_Master_Ezysplit_Insta3').val(),
					loc_Credit_Merch_Master_Ezysplit_Insta6: $('#loc_Credit_Merch_Master_Ezysplit_Insta6').val(),
					loc_Credit_Merch_Master_Ezysplit_Insta9: $('#loc_Credit_Merch_Master_Ezysplit_Insta9').val(),
					loc_Credit_Merch_Master_Ezysplit_Insta12: $('#loc_Credit_Merch_Master_Ezysplit_Insta12').val(),
					
					loc_Credit_Cus_Master_Ezysplit_Insta3: $('#loc_Credit_Cus_Master_Ezysplit_Insta3').val(),
					loc_Credit_Cus_Master_Ezysplit_Insta6: $('#loc_Credit_Cus_Master_Ezysplit_Insta6').val(),
					loc_Credit_Cus_Master_Ezysplit_Insta9: $('#loc_Credit_Cus_Master_Ezysplit_Insta9').val(),
					loc_Credit_Cus_Master_Ezysplit_Insta12: $('#loc_Credit_Cus_Master_Ezysplit_Insta12').val(),
					 */
					mid: $('#mid').val(),
					tid: $('#tid').val(),
					dtl: $('#dtl').val(),
					hashkey: $('#hashkey').val(),
					callbackURL: $('#callbackURL').val(),
					ezywireDeviceId: $('#ezywireDeviceId').val(),
					ezywireRefNo: $('#ezywireRefNo').val(),

					quantity: $('#quantity').val(),
					chkUpdateMDR: updateMDR

					/* productName : $('#productName').val(),
					productSettlement : $('#productSettlement').val(),
					amount : $('#amount').val(),
					hostType : $('#hostType').val(),
					subscription : $('#subscription').val(),
					payLater : $('#payLater').val(),
					productType : $('#productType').val() */

				},
					function (data, status) {
						mdrSubmitModalNonEzysplit.hide();
						$('#mdr-response').modal({
							backdrop: 'static',
							keyboard: false
						});
						$('#mdr-response').modal('show');
						document
							.getElementById("mdr-response-data").innerText = data
						stopLoadingScreen()
					});

			});

			$('.submitMDREzysplit').click(function (e) {
				e.preventDefault();
				var updateMDR = '';
				if ($('#chkUpdateMDR').prop('checked')) {
					updateMDR = 'Yes';
				} else {
					updateMDR = 'No';
				}
				console.log("submitMDR >> 2")
				$.post("submitMDRRates", {
					orderId: $('#orderId').val(),
					merchantRegistrationId: merchantRegistrationId,
					quotationMdrRateId: $(
						'#quotationMdrRateId_EzysplitMDRView')
						.val(),
					quotationEzysplitMdrRateId: $(
						'#quotationEzysplitMdrRateId_EzysplitMDRView')
						.val(),
					quotationId: $(
						'#quotationId_EzysplitMDRView')
						.val(),
					productId: $(
						'#productId_EzysplitMDRView')
						.val(),
					productType: $(
						'#productType_EzysplitMDRView')
						.val(),
					orderLineId: $(
						'#orderLineId_EzysplitMDRView')
						.val(),

					loc_Credit_Merch_Master_Ezysplit_Insta3: $(
						'#loc_Credit_Merch_Master_Ezysplit_Insta3')
						.val(),
					loc_Credit_Merch_Master_Ezysplit_Insta6: $(
						'#loc_Credit_Merch_Master_Ezysplit_Insta6')
						.val(),
					loc_Credit_Merch_Master_Ezysplit_Insta9: $(
						'#loc_Credit_Merch_Master_Ezysplit_Insta9')
						.val(),
					loc_Credit_Merch_Master_Ezysplit_Insta12: $(
						'#loc_Credit_Merch_Master_Ezysplit_Insta12')
						.val(),

					loc_Credit_Cus_Master_Ezysplit_Insta3: $(
						'#loc_Credit_Cus_Master_Ezysplit_Insta3')
						.val(),
					loc_Credit_Cus_Master_Ezysplit_Insta6: $(
						'#loc_Credit_Cus_Master_Ezysplit_Insta6')
						.val(),
					loc_Credit_Cus_Master_Ezysplit_Insta9: $(
						'#loc_Credit_Cus_Master_Ezysplit_Insta9')
						.val(),
					loc_Credit_Cus_Master_Ezysplit_Insta12: $(
						'#loc_Credit_Cus_Master_Ezysplit_Insta12')
						.val(),

					mid: $('#mid_EzysplitMDRView').val(),
					tid: $('#tid_EzysplitMDRView').val(),
					dtl: $('#dtl_EzysplitMDRView').val(),
					hashkey: $('#hashkey_EzysplitMDRView').val(),
					callbackURL: $('#callbackURL_EzysplitMDRView').val(),
					ezywayTid: $('#ezywayTID_EzysplitMDRView').val(),
					ezylinkTid: $('#ezylinkTID_EzysplitMDRView').val(),
					quantity: $('#quantity_EzysplitMDRView').val(),
					chkUpdateMDR: updateMDR

					/* productName : $('#productName').val(),
					productSettlement : $('#productSettlement').val(),
					amount : $('#amount').val(),
					hostType : $('#hostType').val(),
					subscription : $('#subscription').val(),
					payLater : $('#payLater').val(),
					productType : $('#productType').val() */

				}, function (data, status) {
					mdrSubmitModalEzysplit.hide();
					/* $('#mdrSubmitModalEzysplit').modal(
						{
							backdrop: 'static', keyboard: false
						});
					$('#mdrSubmitModalEzysplit').modal('hide'); */

					$('#mdr-response').modal({
						backdrop: 'static',
						keyboard: false
					});
					$('#mdr-response').modal('show');
					document
						.getElementById("mdr-response-data").innerText = data
					stopLoadingScreen()
				});

			});


			function subMitEwalletMDR() {
				startLoadingScreen("Processing, please wait...")
				var config = {
					method: 'post',
					url: contextPath + "/merchant/registration/ewallet/mdr/sync",
					headers: {
						'X-CSRF-TOKEN': token,
						'Content-Type': 'application/json'
					},
					params: {
						registrationID: merchantRegistrationId
					}
				};

				axios(config)
					.then(function (response) {
						return response.data;
					})
					.then(function (response) {
						alert(response.responseMessage)
						window.location.reload()
					})
					.catch(function (error) {
						console.log(error);
					})
					.finally(() => {
						stopLoadingScreen()
					})
			}

			function reloadPage() {
				window.location.reload();
			}
		</script>

		</html>
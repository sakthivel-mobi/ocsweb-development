<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

		<!doctype html>
		<html lang="en">

		<head>
			<meta charset="utf-8">
			<meta name="viewport" content="width=device-width, initial-scale=1">
			<meta name="description" content="">
			<meta name="author" content="">
			<title>MOBI</title>

			<link rel="manifest" href="/manifest.json">
			<meta name="msapplication-TileColor" content="#ffffff">
			<meta name="msapplication-TileImage"
				content="${pageContext.request.contextPath}/resources/images/favicon/ms-icon-144x144.png">
			<meta name="theme-color" content="#f1f5fa">
			<meta id="_csrf" name="_csrf" content="${_csrf.token}" />
			<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}" />`

			<!-- CDN moved to local -->
			<link rel="stylesheet" type="text/css"
				href="${pageContext.request.contextPath}/resources/js/fontawesome/css/fontawesome.min.css">
			<script src="${pageContext.request.contextPath}/resources/js/axios.js" type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/resources/js/moment.js" type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/resources/js/vfs_fonts.min.js"
				type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/resources/js/pdfMake.min.js"
				type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/resources/js/roboto.min.js" type="text/javascript"></script>

			<!-- CDN moved to local -->

			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css">
			<link rel="stylesheet" type="text/css"
				href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
			<link rel="stylesheet" type="text/css"
				href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap5.min.css">
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/fonts/fonts.css">
			<link rel="stylesheet" type="text/css"
				href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css">


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


			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/loading.js"></script>

			<%@ page isELIgnored="false" %>
		</head>
		<style>
			.form label {
				color: #092540;
			}

			.form .form-control {
				/*background-color: #f7f7f7; */
				border-radius: 4px;
				border: solid 1px #dedede;
			}

			/* .form textarea {
				min-height: 240px;
			} */

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

			.form .form-select {
				background-color: #f7f7f7;
				border-radius: 4px;
				border: solid 1px #dedede;
			}

			.sticky-div {
				background-color: white;
				position: sticky;
				top: 5px;
				padding: 10px 0px;
				z-index: 10
			}

			.error-text {
				font-size: medium;
				color: red;
			}

			.signature-view {
				padding: .5rem;
				width: 100%;
				height: fit-content;
				text-align: center;
				text-align: -webkit-center;
				border-radius: 4px;
				background-color: #f1f5fa;
				box-shadow: 3px 3px 12px 0px rgba(0, 0, 0, 0.1);
				-webkit-box-shadow: 3px 3px 12px 0px rgba(0, 0, 0, 0.1);
				-moz-box-shadow: 3px 3px 12px 0px rgba(0, 0, 0, 0.1);
			}
		</style>

		<body class="container-fluid">
			<main>
				<div class="sticky-top p-4">
					<h5 class="c425466 fw-bold">${order.businessName}</h5>

					<div class="d-flex flex-row w-100" style="gap:10px">
						<button id="btnMMADownload" class="btn btn-sm btn-white enableForAllRoles action-button">View
							MMA</button>
						<button id="btnWelcomeLetterDownload"
							class="btn btn-sm btn-white enableForAllRoles action-button">Welcome
							Letter</button>
						<button class="btn btn-sm btn-white enableForAllRoles action-button" id="experianECIReport"
							style="display: block;">Experian ECI Report</button>
						<button class="btn btn-sm btn-white enableForAllRoles action-button" id="experianReport"
							style="display: block;">Experian EBI Report</button>
					</div>
				</div>

				<div class="p-5">
					<form:form method="POST" action="" id="orderForm" modelAttribute="order">
						<div>
							<input type="hidden" name="quotation.stage" value="${order.quotation.stage }">
							<input type="hidden" name="quotation.acquirer" value="${order.quotation.acquirer }">
						</div>

						<ul class="nav nav-tabs" id="myTab" role="tablist">
							<li class="nav-item" role="presentation">
								<button class="nav-link active" id="home-tab" data-bs-toggle="tab"
									data-bs-target="#home-tab-pane" type="button" role="tab"
									aria-controls="home-tab-pane" aria-selected="true">Business Details</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="profile-tab" data-bs-toggle="tab"
									data-bs-target="#profile-tab-pane" type="button" role="tab"
									aria-controls="profile-tab-pane" aria-selected="false">Order Details</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="contact-tab" data-bs-toggle="tab"
									data-bs-target="#contact-tab-pane" type="button" role="tab"
									aria-controls="contact-tab-pane" aria-selected="false">Directors</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="risk-tab" data-bs-toggle="tab"
									data-bs-target="#risk-tab-pane" type="button" role="tab"
									aria-controls="risk-tab-pane" aria-selected="false">Risk</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="document-tab" data-bs-toggle="tab"
									data-bs-target="#document-tab-pane" type="button" role="tab"
									aria-controls="document-tab-pane" aria-selected="false">Documents</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="signature-tab" data-bs-toggle="tab"
									data-bs-target="#signature-tab-pane" type="button" role="tab"
									aria-controls="signature-tab-pane" aria-selected="false">Signature</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="items-tab" data-bs-toggle="tab"
									data-bs-target="#items-tab-pane" type="button" role="tab"
									aria-controls="items-tab-pane" aria-selected="false">Order Items</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="stage-tab" data-bs-toggle="tab"
									data-bs-target="#stage-tab-pane" type="button" role="tab"
									aria-controls="stage-tab-pane" aria-selected="false">Stages</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="notes-tab" data-bs-toggle="tab"
									data-bs-target="#notes-tab-pane" type="button" role="tab"
									aria-controls="notes-tab-pane" aria-selected="false">Notes</button>
							</li>
						</ul>

						<div class="tab-content" id="myTabContent" style="margin-bottom: 100px;">
							<!-- Business Details -->
							<div class="tab-pane fade show active" id="home-tab-pane" role="tabpanel"
								aria-labelledby="home-tab" tabindex="0">

								<div class="row" style="padding-top: 1rem;">
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="id" class="form-label">Order ID</form:label>
											<form:input path="id" class="form-control readOnly" placeholder="Order ID"
												readonly="true" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="createdOn" class="form-label">Created On</form:label>
											<div class="input-group mb-3">
												<form:input id="createdOn" path="createdOn" type="text" disabled="true"
													class="form-control" />
												<span class="input-group-text"><i class="fa fa-calendar"></i></span>
											</div>
										</div>
									</div>

									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<form:label path="userId" class="form-label">User ID</form:label>
											<div class="input-group mb-3">
												<span class="input-group-text" id="basic-addon1">60</span>
												<form:input path="userId" class="form-control" placeholder="User ID"
													readonly="true" />
											</div>
										</div>
									</div>

									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="merchantIdType" class="form-label">Merchant ID Type
											</form:label>
											<form:select path="merchantIdType" class="form-select" name="merchantIdType"
												id="merchantIdType">
												<form:option value="0">-----------Select-----------</form:option>
												<c:forEach var="merchantIdTypeList" items="${merchantIdTypeList}">
													<form:option value="${merchantIdTypeList.value}">
														${merchantIdTypeList.name}</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>

									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="businessName" class="form-label">Business Name
											</form:label>
											<form:input path="businessName" id="input-business-name"
												class="form-control" placeholder="Business Name" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">

											<form:label path="merchantTradingName" class="form-label">Merchant Trading
												Name</form:label>
											<form:input path="merchantTradingName" id="input-merchant-trading-name"
												class="form-control" placeholder="Merchant Trading Name" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="natureOfBusiness" class="form-label">Nature Of Business
											</form:label>
											<form:select path="natureOfBusiness" class="form-select"
												name="natureOfBusiness">

												<c:forEach var="nob" items="${natureOfBusinessList}">
													<form:option value="${nob.value}">${nob.name}</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>

									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">

											<form:label path="registrationId" class="form-label">Business Registration
												ID/No</form:label>
											<form:input path="registrationId" id="input-business-registration-no"
												class="form-control" placeholder="Business Registration ID/No" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="companyType" class="form-label">Company Type</form:label>
											<form:select path="companyType" class="form-select" name="companyType">
												<form:option value="O">-----------Select-----------</form:option>
												<c:forEach var="type" items="${companyTypeList}">
													<form:option value="${type.value}">${type.name}</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>

									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="dateIncorporated" class="form-label">Date Incorporated
											</form:label>
											<div class="input-group mb-3">
												<form:input id="dateIncorporated" path="dateIncorporated" type="text"
													class="form-control datepick" />
												<span class="input-group-text"><i class="fa fa-calendar"></i></span>
											</div>
										</div>
									</div>

									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<form:label path="visaMCC" class="form-label">MCC</form:label>
											<form:select path="visaMCC" class="form-select" name="visaMCC">
												<form:option value="0">-----------Select-----------</form:option>
												<c:forEach var="mcc" items="${umobileMCCList}">
													<form:option value="${mcc.value}">${mcc.value} - ${mcc.name}
													</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>

									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="businessType" class="form-label">Business Type
											</form:label>
											<form:select path="businessType" class="form-select" name="businessType">
												<form:option value="0">-----------Select-----------</form:option>
												<c:forEach var="type" items="${businessTypeList}">
													<form:option value="${type.value}">${type.name}</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>

									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="eCommIndustry" class="form-label">E-Commerce Industry
											</form:label>
											<form:select path="eCommIndustry" class="form-select" name="eCommIndustry">
												<form:option value="0">-----------Select-----------</form:option>
												<c:forEach var="ecom" items="${eCommIndustryList}">
													<form:option value="${ecom.value}">${ecom.name}</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>

									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="merchantSector" class="form-label">Merchant Sector
											</form:label>
											<form:select path="merchantSector" class="form-select"
												placeholder="Merchant Sector">
												<form:option value="">-----------Select-----------</form:option>
												<form:option value="Manufacturing">Manufacturing</form:option>
												<form:option value="Service">Service</form:option>
												<form:option value="Others">Others</form:option>
											</form:select>
										</div>
									</div>

									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="merchantCategory" class="form-label">Merchant Category
											</form:label>
											<form:select path="merchantCategory" class="form-select"
												placeholder="Merchant Category">
												<form:option value="">-----------Select-----------</form:option>
												<form:option value="Corporate">Corporate</form:option>
												<form:option value="SMEs - Medium">SMEs - Medium</form:option>
												<form:option value="SMEs - Small">SMEs - Small</form:option>
												<form:option value="SMEs - Micro">SMEs - Micro</form:option>
												<form:option value="Others">Others</form:option>
											</form:select>
										</div>
									</div>

									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="quotation.id" class="form-label">Quotation</form:label>
											<form:input path="quotation.id" class="form-control readOnly"
												placeholder="Quotation ID" readonly="true" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="businessStartTime" class="form-label">Business Start Time
											</form:label>
											<form:input path="businessStartTime" class="form-control"
												placeholder="Business Start Time" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="businessEndTime" class="form-label">Business End Time
											</form:label>
											<form:input path="businessEndTime" class="form-control"
												placeholder="Business End Time" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="previousAcquirer" class="form-label">Previous Acquirer
											</form:label>
											<form:input path="previousAcquirer" class="form-control" placeholder="" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="previousAcquirerCeasedDate" class="form-label">
												Previous Acquirer Ceased Date</form:label>
											<div class="input-group mb-3">
												<form:input id="previousAcquirerCeasedDate"
													path="previousAcquirerCeasedDate" type="text"
													class="form-control datepick" />
												<span class="input-group-text"><i class="fa fa-calendar"></i></span>
											</div>
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="websiteUrl" class="form-label">Website URL</form:label>
											<form:input path="websiteUrl" class="form-control"
												placeholder="Website URL" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2 mb-3">
										<div class="mb-3">
											<form:label path="noOfMPOS" class="form-label">No. of MPOS</form:label>
											<form:input path="noOfMPOS" class="form-control"
												placeholder="No. of MPOS" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2 mb-3">
										<div class="mb-3">
											<form:label path="mPOSModel" class="form-label">MPOS Model</form:label>
											<form:input path="mPOSModel" class="form-control"
												placeholder="MPOS Model" />
										</div>
									</div>
								</div>

								<h5 class="mb-3  mt-3">Address</h5>
								<div class="row g-3">
									<div class="col-sm-4  form-floating mb-3">
										<div class="mb-3">
											<form:label path="addressLine" class="form-label">Address line</form:label>
											<form:textarea cols="5" rows="2" path="addressLine" class="form-control"
												placeholder="Address line" />
										</div>
									</div>
								</div>

								<div class="row g-3">
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="state" class="form-label">State</form:label>
											<form:select path="state" class="form-select" name="state">
												<form:option value="0">-----------Select-----------</form:option>
												<c:forEach var="state" items="${StateList}">
													<form:option value="${state.value}">${state.name}</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="city" class="form-label">City</form:label>
											<form:select path="city" class="form-select" name="city">
												<form:option value="0">-----------Select-----------</form:option>
												<c:forEach var="city" items="${CityList}">
													<form:option value="${city.value}">${city.name}</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="country" class="form-label">Country</form:label>
											<form:select path="country" class="form-select" name="country">
												<form:option value="458">MALAYSIA</form:option>
												<c:forEach var="country" items="${CountryList}">
													<form:option value="${country.value}">${country.name}</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="postCode" class="form-label">Post Code</form:label>
											<form:input path="postCode" class="form-control" placeholder="Post Code" />
										</div>
									</div>
								</div>
							</div>
							<!-- Business Details -->

							<!-- Order Details -->
							<div class="tab-pane fade" id="profile-tab-pane" role="tabpanel"
								aria-labelledby="profile-tab" tabindex="0">
								<div class="row g-3">
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">

											<form:label path="salutation" class="form-label">Salutation</form:label>
											<form:select path="salutation" class="form-select" name="salutation">
												<form:option value="0">-----------Select-----------</form:option>
												<c:forEach var="sal" items="${salutationList}">
													<form:option value="${sal.value}">${sal.name}</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="authContactPersonName" class="form-label">Authorized
												Contact
											</form:label>
											<form:input path="authContactPersonName" class="form-control"
												placeholder="Authorized 	Contact" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="authContactPersonPhone" class="form-label">Authorized
												Contact Phone
											</form:label>
											<form:input path="authContactPersonPhone" class="form-control"
												placeholder="Authorized Contact Phone" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">

											<form:label path="authContactPersonId" class="form-label">Authorized Contact
												NRIC
											</form:label>
											<form:input path="authContactPersonId" class="form-control"
												placeholder="Authorized Contact NRIC" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="officeNo" class="form-label">Office No</form:label>
											<form:input path="officeNo" class="form-control" id="input-office-number"
												placeholder="Office No" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="officeEmail" class="form-label">Office Email</form:label>
											<form:input path="officeEmail" class="form-control" id="input-office-email"
												placeholder="Office Email" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="accountType" class="form-label">Account Type</form:label>
											<form:select path="accountType" class="form-select" name="accountType">
												<form:option value="0">-----------Select-----------</form:option>
												<c:forEach var="type" items="${accountTypeList}">
													<form:option value="${type.value}">${type.name}</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="bankName" class="form-label">Bank Name</form:label>
											<form:select path="bankName" class="form-select" name="bankName">
												<form:option value="0">-----------Select-----------</form:option>
												<c:forEach var="bank" items="${bankList}">
													<form:option value="${bank.value}">${bank.name}</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="accountNo" class="form-label">Account No</form:label>
											<form:input path="accountNo" class="form-control"
												placeholder="Account No" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="sizeOfPremise" class="form-label">Size Of Premise
											</form:label>
											<form:input path="sizeOfPremise" class="form-control"
												placeholder="Size Of Premise" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">

											<form:label path="noOfEmployee" class="form-label">No Of Employees
											</form:label>
											<form:input path="noOfEmployee" class="form-control"
												placeholder="Employees in Organization" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="estimatedStockValue" class="form-label">Estimated Stock
												Value
											</form:label>
											<form:input path="estimatedStockValue" class="form-control"
												placeholder="Estimated Stock Value" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="noOfDailyTransaction" class="form-label">No of Daily
												Transaction
											</form:label>
											<form:input path="noOfDailyTransaction" class="form-control"
												placeholder="No of Daily Transaction" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="averageTransactionSize" class="form-label">
												Avg.Transaction Size</form:label>
											<form:input path="averageTransactionSize" class="form-control"
												placeholder="Avg.Transaction Size" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="remarks" class="form-label">Remarks</form:label>
											<form:input path="remarks" class="form-control" placeholder="Remarks" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="taxIdUS" class="form-label">US Tax ID</form:label>
											<form:input path="taxIdUS" class="form-control" placeholder="US Tax ID" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="masterMerchant" class="form-label">Master Merchant
											</form:label>
											<form:select class="form-select" name="masterMerchant" path="masterMerchant"
												id="masterMerchant">
												<form:option value="0">-----------Select-----------</form:option>
												<c:forEach items="${masterMerchants}" var="masterMerchant">
													<form:option value="${masterMerchant.value}">
														${masterMerchant.name}
													</form:option>
												</c:forEach>
											</form:select>
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="quotation.salesPerson.name" class="form-label">Sales
												Person
											</form:label>
											<form:input path="quotation.salesPerson.name" class="form-control"
												placeholder="" readonly="true" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="grabPayLatitude" class="form-label">Latitude
											</form:label>
											<form:input path="grabPayLatitude" class="form-control"
												placeholder="Latitude" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="grabPayLongitude" class="form-label">Longitude
											</form:label>
											<form:input path="grabPayLongitude" class="form-control"
												placeholder="Longitude" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="countryOfBirth" class="form-label">Country Of Birth
											</form:label>
											<form:input path="countryOfBirth" class="form-control" placeholder="" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2  form-floating mb-3">
										<div class="mb-3">
											<form:checkbox path="isUSResident" value="Yes" />
											Is US Resident
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:checkbox path="isUSCitizen" value="Yes" />
											Is US Citizen
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:checkbox path="isGreenCardHolder" value="Yes" />
											Is GreenCard Holder
										</div>
									</div>
								</div>
							</div>
							<!-- Order Details -->

							<!-- Directors Details -->
							<div class="tab-pane fade" id="contact-tab-pane" role="tabpanel"
								aria-labelledby="contact-tab" tabindex="0">

								<div class="mt-3 d-flex w-100 justify-content-end">
									<button type="button" data-bs-toggle="modal" data-bs-target="#directorModal"
										class="btn btn-sm btn-primary action-button">Add
										Directors</button>
								</div>

								<table class="table mt-3" id="DirectorTable">
									<thead>
										<tr class="bcf4f4f4 c617075 fw500">
											<th>ID</th>
											<th>Name</th>
											<th>Designation</th>
											<th>Contact No</th>
											<th>Nationality</th>
											<th style="text-align: right;">Action</th>
										</tr>
									</thead>
									<tbody class="c3f3f3f fw500 tblOrderLineBody" id="table-director-body">
									</tbody>
								</table>
							</div>
							<!-- Directors Details -->

							<!-- Finance tab -->
							<div class="tab-pane fade" id="risk-tab-pane" role="tabpanel" aria-labelledby="risk-tab"
								tabindex="0">
								<h5 class="mb-3  mt-3 ">DTL Information</h5>

								<div class="row g-3 dtlInfo">
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="requstedDTL" class="form-label">Requested DTL
											</form:label>
											<form:input path="requstedDTL" class="form-control" placeholder="" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="riskApprovedDTL" class="form-label">Risk Approved DTL
											</form:label>
											<form:input path="riskApprovedDTL" class="form-control" placeholder="" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="riskReason" class="form-label">Risk Reason
											</form:label>
											<form:input path="riskReason" class="form-control" placeholder="" />
										</div>
									</div>

									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="highRiskMark" class="form-label">High Risk Mark
											</form:label>
											<form:input path="highRiskMark" class="form-control" placeholder="" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="highRiskStatus" class="form-label">High Risk Status
											</form:label>
											<form:input path="highRiskStatus" class="form-control" placeholder="" />
										</div>
									</div>
									<div class="col-sm-6 col-md-2 form-floating mb-3">
										<div class="mb-3">
											<form:label path="highRiskMarkedOn" class="form-label">High Risk Marked
												On
											</form:label>
											<div class="input-group mb-3">
												<form:input id="highRiskMarkedOn" path="highRiskMarkedOn" type="text"
													class="form-control datepick" />
												<span class="input-group-text"><i class="fa fa-calendar"></i></span>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- Finance tab -->

							<!-- Document tab -->
							<div class="tab-pane fade" id="document-tab-pane" role="tabpanel"
								aria-labelledby="document-tab" tabindex="0">
								<div id="document-available">
									<table class="table table-responsive bcf4f4f4 c617075 fw500">
										<thead>
											<tr>
												<th>File Name</th>
												<th>Category</th>
												<th>Action</th>
												<th>View</th>
											</tr>
										</thead>
										<tbody id="document-available-table-body"></tbody>
									</table>
								</div>

								<button class="btn btn-sm btn-primary action-button" onclick="addDocument()">Add
									Documents</button>
								<div id="document-upload" style="margin-top: 20px; display: none;">
									<div class="row">
										<div class="col">
											<label for="select-category" class="form-label">Select
												a file type</label> <select name="" class="form-select action-select"
												id="select-category" placeholder="Select category">
												<option selected value="0">Select file type</option>
											</select>
										</div>
										<div class="col">
											<div class="mb-3" id="input-file-block">
												<label for="input-file-upload" class="form-label">Select
													file to upload</label> <input class="form-control action-input"
													type="file" id="input-file-upload" multiple
													placeholder="Select one or more file to upload"
													accept="application/*,image/*" disabled>
												<p class="error-text">Select file type to choose a file</p>
											</div>
										</div>
									</div>

									<table class="table table-responsive bcf4f4f4 c617075 fw500"
										id="table-document-upload">
										<thead>
											<tr>
												<th>S.No</th>
												<th>File Name</th>
												<th>File Size</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody id="document-table-body"></tbody>
									</table>

									<div style="margin-top: 20px; display: none;" id="document-footer">
										<span><button type="button"
												class="btn btn-sm btn-mobi btn-primary btn-sm action-button"
												id="upload-all-document">Upload Documents</button></span> <span><button
												class="btn btn-sm btn-sm btn-danger action-button"
												onclick="removeAllUploadedDocuments()">Remove
												All
												Documents</button></span>
									</div>
								</div>
							</div>
							<!-- Document tab -->

							<!-- Signature tab -->
							<div class="tab-pane fade" id="signature-tab-pane" role="tabpanel"
								aria-labelledby="signature-tab" tabindex="0">
								<div class="mt-4 mb-4">
									<div class="row">
										<div class="col-sm-12 col-lg-2">
											<h6>${order.quotation.salesPerson.aliasName}</h6>
											<div class="signature-view">
												<img style="border-radius: 8px; border: .2px solid #00000021; width: 128px; height: 128px;"
													id="sales-signature"
													src="${pageContext.request.contextPath}/resources/images/signature_not_available.png"
													alt="" sizes="">
											</div>

										</div>
										<div class="col-sm-12 col-lg-2">
											<h6>Merchant Signature</h6>
											<div class="signature-view">
												<img id="merchant-signature"
													style="border-radius: 8px; border: .2px solid #00000021; width: 128px; height: 128px;"
													alt="" sizes=""
													src="${pageContext.request.contextPath}/resources/images/signature_not_available.png">
											</div>

										</div>
									</div>
								</div>

							</div>
							<!-- Signature tab -->

							<!-- Items tab -->
							<div class="tab-pane fade" id="items-tab-pane" role="tabpanel" aria-labelledby="items-tab"
								tabindex="0">

								<div class="table-responsive">
									<table class="table" id="OrderLineTable">
										<thead>
											<tr class="bcf4f4f4 c617075 fw500">
												<th>Line ID</th>
												<th>Description</th>
												<th>Product</th>
												<th>Quantity</th>
												<th style="text-align: right;">Amount (RM)</th>
											</tr>
										</thead>
										<tbody class="c3f3f3f fw500 tblOrderLineBody">

											<c:forEach var="tempOrderLine" items="${orderLineList}" varStatus="count">
												<c:url var="viewLink" value="">
													<c:param name="id" value="${tempOrderLine.id}"></c:param>
												</c:url>

												<tr>
													<td>${tempOrderLine.id}</td>
													<td>${tempOrderLine.product.productName}</td>
													<td>${tempOrderLine.product.productType}</td>
													<td>${tempOrderLine.quantity}</td>
													<td style="text-align: right;">
														${tempOrderLine.product.unitPrice}</td>

												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>

							</div>
							<!-- Items tab -->

							<!-- Stage tab -->
							<div class="tab-pane fade" id="stage-tab-pane" role="tabpanel" aria-labelledby="stage-tab"
								tabindex="0">
								<table class="table table-responsive bcf4f4f4 c617075 fw500">
									<thead>
										<tr>
											<th>From Stage</th>
											<th>To Stage</th>
											<th>Date</th>
											<th>Authorized By</th>
										</tr>
									</thead>
									<tbody id="stage-movement-table-body"></tbody>
								</table>
							</div>
							<!-- Stage tab -->

							<!-- Notes tab -->
							<div class="tab-pane fade" id="notes-tab-pane" role="tabpanel" aria-labelledby="notes-tab"
								tabindex="0">

								<div class="mt-3 d-flex w-100 justify-content-end"><button type="button"
										data-bs-toggle="modal" data-bs-target="#notesModal"
										class="btn btn-sm btn-primary action-button">Add Notes</button></div>

								<table class="table table-responsive mt-3" id="NotesTable">
									<thead>
										<tr class="bcf4f4f4 c617075 fw500">
											<th>ID</th>
											<th>Send Date</th>
											<th>From</th>
											<th>Notify To</th>
											<th>Subject</th>
											<th>Notes</th>
										</tr>
									</thead>
									<tbody class="c3f3f3f fw500 tblOrderLineBody" id="table-notes-body">
									</tbody>
								</table>
							</div>
							<!-- Notes tab -->
						</div>

						<div class="bottom-fixed">
							<button type="button" class="btn btn-sm btn-primary action-button" id="button-edit">Edit
								Details</button>
							<button id="button-save-details" type="submit" class="btn btn-sm btn-primary"
								style="display: none;">Save Details</button>
							<button type="submit" class="btn btn-sm btn-primary" id="button-action">Move
								to Merchant Registration</button>
						</div>

					</form:form>
				</div>
			</main>
			<!-- Director Modal -->
			<div class="modal fade" id="directorModal" tabindex="-1" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Add Director</h5>
							<button type="button" class="btn-close action-button" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<form:form action="">

								<div class="row">
									<div class="col-sm-12 col-md-6   form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">ID Type</label> <select
												class="form-control action-select" name="idType" id="idType">
												<option value="0">---------Select--------</option>
												<c:forEach var="idType" items="${merchantIdTypeList}">
													<option value="${idType.value}">${idType.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<div class="col-sm-12 col-md-6   form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">ID No</label> <input id="idNo"
												class="form-control action-input" placeholder="ID No" />
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-12 col-md-6   form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Name</label> <input id="name"
												class="form-control action-input" placeholder="Director Name" />
										</div>
									</div>

									<div class="col-sm-12 col-md-6  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Designation</label> <input id="designation"
												class="form-control action-input" placeholder="Designation" />
										</div>
									</div>
								</div>

								<div>
									<p style="text-transform: uppercase;">
										<span><img
												src="${pageContext.request.contextPath}/resources/images/info_icon.svg"
												alt=""></span> Additional Information
									</p>
								</div>

								<div class="row">
									<div class="col-sm-12 col-md-6  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Contact No</label> <input id="contactNo"
												class="form-control action-input" placeholder="Contact No" />
										</div>
									</div>

									<div class="col-sm-12 col-md-6 form-floating mb-3">
										<div class="col-sm-12  form-floating mb-3">
											<p class="form-label">Nationality</p>
											<div class="input-group mb-3">
												<select class="form-select action-select" name="nationality"
													id="nationality">
													<option value="MALAYSIA">MALAYSIA</option>
													<c:forEach var="country" items="${CountryList}">
														<option value="${country.name}">${country.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>

									</div>
								</div>

								<div class="row">
									<div class="col-sm-12  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Address</label>
											<textarea rows="4" cols="20" id="address" class="form-control"
												placeholder="Address"></textarea>
										</div>
									</div>
								</div>

							</form:form>
						</div>
						<div>
							<hr>
							<div class="d-flex bd-highlight align-items-center p-4" style="background-color: #f7f7f7;">
								<div class="flex-grow-1" id="director-current-date"></div>
								<button type="button" class="btn btn-sm btn-secondary btn-sm m-2 action-button"
									data-bs-dismiss="modal">Close</button>
								<button type="button"
									class="btn btn-sm btn-primary btn-sm saveDirector action-button">Add
									Director</button>
							</div>
						</div>

					</div>
				</div>
			</div>
			<!-- Director Modal -->
			<!-- Director Modal -->
			<div class="modal fade" id="editDirectorModal" tabindex="-1" aria-labelledby="editDirectorModelLabel"
				aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="editDirectorModelLabel">Edit Director</h5>
							<button type="button" class="btn-close action-button" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<form:form action="">
								<div class="row">
									<div class="col-sm-12 col-md-6   form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">ID Type</label>
											<select class="form-control action-select" name="merchantIdType"
												id="director-edit-merchantIdType">
												<option value="0">---------Select--------</option>
												<c:forEach var="idType" items="${merchantIdTypeList}">
													<option value="${idType.value}">${idType.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<div class="col-sm-12 col-md-6   form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">ID No</label> <input id="director-edit-idNo"
												class="form-control" placeholder="ID No" />
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-sm-12 col-md-6   form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Name</label> <input id="director-edit-name"
												class="form-control" placeholder="Director Name" />
										</div>
									</div>

									<div class="col-sm-12 col-md-6  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Designation</label> <input
												id="director-edit-designation" class="form-control"
												placeholder="Designation" />
										</div>
									</div>
								</div>

								<div>
									<p style="text-transform: uppercase;">
										<span>
											<img src="${pageContext.request.contextPath}/resources/images/info_icon.svg"
												alt="">
										</span> Additional Information
									</p>
								</div>

								<div class="row">
									<div class="col-sm-12 col-md-6  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Contact No</label> <input
												id="director-edit-contactNo" class="form-control"
												placeholder="Contact No" />
										</div>
									</div>

									<div class="col-sm-12 col-md-6 form-floating mb-3">
										<div class="col-sm-12  form-floating mb-3">
											<p class="form-label">Nationality</p>
											<div class="input-group mb-3">
												<select class="form-select" name="nationality"
													id="director-edit-nationality">
													<option value="MALAYSIA">MALAYSIA</option>
													<c:forEach var="country" items="${CountryList}">
														<option value="${country.name}">${country.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>

									</div>
								</div>

								<div class="row">
									<div class="col-sm-12  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Address</label>
											<textarea rows="4" cols="20" id="director-edit-address" class="form-control"
												placeholder="Address"></textarea>
										</div>
									</div>
								</div>

							</form:form>
						</div>
						<div>
							<hr>
							<div class="d-flex bd-highlight align-items-center p-4" style="background-color: #f7f7f7;">
								<div class="flex-grow-1" id="director-current-date"></div>
								<button type="button" class="btn btn-sm btn-secondary btn-sm m-2"
									data-bs-dismiss="modal">Close</button>
								<button type="button" class="btn btn-sm btn-primary btn-sm action-button" value=""
									id="button-update-director" onclick="updateDirectory()">Edit
									Director</button>
							</div>
						</div>

					</div>
				</div>
			</div>
			<!-- Director Modal -->
			<!--Notes Modal -->
			<div class="modal fade" id="notesModal" tabindex="-1" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-body">
							<div class="d-flex flex-row bd-highlight align-self-center">
								<div class="flex-grow-1 bd-highlight">
									<h5 class="modal-title" id="exampleModalLabel">Add Notes</h5>
								</div>
								<div>
									<button type="button" class="btn btn-sm btn-close action-button"
										data-bs-dismiss="modal" aria-label="Close"></button>
								</div>
							</div>
							<form:form action="" class="mt-5">

								<div class="col  form-floating mb-3">
									<div class="mb-3">
										<label class="form-label">Notify To</label> <input id="notifyTo"
											class="form-control action-input" placeholder="Notify To" />
									</div>
									<p>
										To notify multiple users please separate email id with <b>(
											&comma; ) (Comma)</b>
									</p>
								</div>

								<div class="col  form-floating mb-3">
									<div class="mb-3">
										<label class="form-label">Subject</label> <input id="subject"
											class="form-control action-input" placeholder="Subject" />
									</div>
								</div>

								<div class="col form-floating mb-3">
									<div class="mb-3">
										<label class="form-label">Notes</label>
										<textarea rows="6" cols="40" id="notes" class="form-control"
											placeholder="Notes"></textarea>
									</div>
								</div>

							</form:form>
						</div>

						<div class="d-flex bd-highlight align-items-center p-4" style="background-color: #f7f7f7;">
							<div class="flex-grow-1 bd-highlight">
								<p style="margin: 0px;" id="notes-current-date"></p>
							</div>
							<div class="bd-highlight m-2">
								<button type="button" class="btn btn-sm btn-secondary btn-sm action-button"
									data-bs-dismiss="modal">Close</button>
							</div>
							<div class="bd-highlight m-2">
								<button type="button" id="saveNotes"
									class="btn btn-sm btn-primary btn-sm action-button">Add
									Notes</button>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- Modal -->
			<div class="modal fade" id="paymentConfirmationModal" tabindex="-1" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="ModalLabel">Verify Payment</h5>
							<button type="button" class="btn-close action-button" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<label>New Order has been Created!</label>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-sm btn-secondary" data-bs-dismiss="modal">No</button>

							<button type="button" class="btn btn-sm btn-primary"
								href="/Quotation/QuotationView?id=${quotation.id}">
								<a href="QuotationView?id=${quotation.id}&">Ok</a>
							</button>
						</div>
					</div>
				</div>
			</div>
			<!-- ECI Modal -->
			<div class="modal fade" id="modal-ECI-report" tabindex="-1" data-bs-backdrop="static"
				aria-labelledby="ECIReport" aria-hidden="true">
				<div class="modal-dialog modal-xl modal-dialog-centered">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="ECIReport">Enhanced Company Intelligence ECI</h5>
							<button type="button" class="btn-close enableForAllRoles action-button"
								data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<h6>SUBJECT</h6>
							<div class="d-flex flex-column">
								<div class=""><span style="font-size: large; font-weight: bold;">Name of Subject</span>
									<span id="eci-nameOfSubject" style="color: black !important;"></span>
								</div>
								<div class=""><span style="font-size: large; font-weight: bold;">Local Number</span>
									<span id="eci-localNumber" style="color: black !important;"></span>
								</div>
							</div>

							<hr>

							<h6>Directors / Officers</h6>
							<table class="table">
								<thead>
									<th>Name</th>
									<th>Address</th>
									<th>IC/PP NO</th>
									<th>Designation</th>
									<th></th>
								</thead>
								<tbody id="table-eci-body-directors">
								</tbody>
							</table>


							<hr>
							<button class="btn btn-sm btn-primary btn-sm enableForAllRoles action-button"
								onclick="generateECIReports()">Generate ECI
								Report PDF</button>
						</div>
					</div>
				</div>
			</div>
			<!-- EBI Modal -->
			<div class="modal fade" id="modal-EBI-report" tabindex="-1" data-bs-backdrop="static"
				aria-labelledby="EBIReport" aria-hidden="true">
				<div class="modal-dialog modal-xl modal-dialog-centered">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="EBIReport">EBI Report</h5>
							<button type="button" class="btn-close enableForAllRoles action-button"
								data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<h6>SUBJECT</h6>
							<div class="d-flex flex-column">
								<div class=""><span style="font-size: large; font-weight: bold;">Name of Subject</span>
									<span id="ebi-nameOfSubject" style="color: black !important;"></span>
								</div>
								<div class=""><span style="font-size: large; font-weight: bold;">Local Number</span>
									<span id="ebi-localNumber" style="color: black !important;"></span>
								</div>
							</div>

							<hr>

							<h6>Owners</h6>
							<table class="table">
								<thead>
									<th>Name</th>
									<th>Address</th>
									<th>IC/PP NO</th>
									<th>Designation</th>
									<th></th>
								</thead>
								<tbody id="table-ebi-body-directors">

								</tbody>
							</table>

							<hr>
							<button class="btn btn-sm btn-primary btn-sm enableForAllRoles action-button"
								onclick="generateEBIReport()">Generate EBI
								Report PDF</button>
						</div>
					</div>
				</div>
			</div>
			<!-- CDDI Modal -->
			<div class="modal fade" id="modal-CDDI-report" data-bs-backdrop="static" tabindex="-1"
				aria-labelledby="CDDIReport" aria-hidden="true">
				<div class="modal-dialog modal-xl modal-dialog-centered">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="CDDIReport">CDDI Report</h5>
							<button type="button" class="btn-close enableForAllRoles action-button"
								data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<table class="table table-striped table-responsive">
								<thead>
									<tr>
										<th scope="col">Profile Id</th>
										<th scope="col">Risk Category</th>
										<th scope="col">DOB</th>
										<th scope="col">Match Name</th>
										<th scope="col">Gender</th>
										<th scope="col">Country</th>
										<th scope="col">Match</th>
									</tr>
								</thead>
								<tbody id="cddi-report-table-body">
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<!-- CRI Modal -->
			<div class="modal fade" id="modal-CRI-report" tabindex="-1" aria-labelledby="CRIReport" aria-hidden="true">
				<div class="modal-dialog modal-xl modal-dialog-centered">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="CRIReport">CRI Report</h5>
							<button type="button" class="btn-close enableForAllRoles action-button"
								data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<div class="mb-3">
								<label for="cri-personalDetails_name" class="form-label">Name of Subject</label>
								<input disabled type="text" class="form-control" id="cri-personalDetails_name" value=""
									placeholder="" />
							</div>
							<div class="row">
								<div class="col-sm-12 col-md-6">
									<div class="mb-3">
										<label for="cri-old_ic" class="form-label">IC No</label>
										<input readonly type="text" class="form-control" id="cri-old_ic"
											placeholder="" />
									</div>
								</div>
								<div class="col-sm-12 col-md-6">
									<div class="mb-3">
										<label for="cri-new_ic" class="form-label">New IC No</label>
										<input readonly type="text" class="form-control" id="cri-new_ic"
											placeholder="" />
									</div>
								</div>
							</div>
							<h4>Address Details</h4>
							<table class="table table-light table-striped">
								<thead>
									<th>S.No</th>
									<th>Address</th>
									<th>Date</th>
								</thead>
								<tbody id="table-cri-address-list"></tbody>
							</table>
							<hr>
							<button class="btn btn-sm btn-primary btn-sm enableForAllRoles action-button"
								onclick="generateCRIReport()">Generate CRI
								Report PDF</button>
						</div>
					</div>
				</div>
			</div>
		</body>

		<script type="text/javascript">
			var contextPath = "${pageContext.request.contextPath}"
			var orderId = "${order.id}";
			var isQuotationAccepted = "${order.quotation.quotationAccepted}";

			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");

			var uploadedFile = []
			document.getElementById('notesModal').addEventListener('show.bs.modal', function (event) {
				document.getElementById("notes-current-date").innerText = moment().format('MMMM DD YYYY, h:mm A');
			})

			document.getElementById('directorModal').addEventListener('show.bs.modal', function (event) {
				document.getElementById("director-current-date").innerText = moment().format('MMMM DD YYYY, h:mm A');

			})

			var editDirectorModal = new bootstrap.Modal(document.getElementById('editDirectorModal'), {
				keyboard: false
			})

			/* Experian Modal */
			var experianCRIModal = new bootstrap.Modal(document.getElementById('modal-CRI-report'), {
				keyboard: false
			});
			var experianECIModal = new bootstrap.Modal(document.getElementById('modal-ECI-report'), {
				keyboard: false
			});
			var experianEBIModal = new bootstrap.Modal(document.getElementById('modal-EBI-report'), {
				keyboard: false
			});

			/* Experian Modal */

			var inputFileUpload = document.getElementById("input-file-upload")
			var uploadDocumentTableBody = document.getElementById("document-table-body")
			var selectFileType = document.getElementById("select-category")
			var inputFileBlock = document.getElementById("input-file-block")
			const documentAvailableBody = document.getElementById("document-available-table-body")

			inputFileUpload.addEventListener("change", function (event) {
				for (let index = 0; index < inputFileUpload.files.length; index++) {
					inputFileUpload.files[index].fileType = selectFileType.value
					uploadedFile.push(inputFileUpload.files[index])
				}

				uploadedFile.forEach(element => {
					console.log("uploadedFile --> " + element.fileType)
					console.log("uploadedFile --> " + element.name)
					console.log("uploadedFile --> " + element.size)
				});

				inputFileUpload.value = ""
				displayUploadedDocuments(uploadedFile)
			})

			function removeAllUploadedDocuments() {
				event.preventDefault();
				uploadedFile = []
				document.getElementById("document-upload").style.display = "none"
				document.getElementById("document-table-body").innerHTML = ""
				document.getElementById("document-footer").style.display = "none";
			}


			var stage = "${order.quotation.stage}";
			var role = "${loggedUserRole}";

			const buttonEdit = document.getElementById("button-edit")
			const buttonSaveDetails = document.getElementById("button-save-details")
			const buttonAction = document.getElementById("button-action")


			window.onload = function () {
				getAvailableDocuments()
				initialFunction()
				getOrderNotes()
				getOrderDirector()
				//Restrict non-stage user to edit the form
				console.log(stage);
				console.log(role);
				console.log(stage != role)

				console.log("isQuotationAccepted -> " + isQuotationAccepted)

				if (parseInt(isQuotationAccepted) === 1) {
					// Show merchant Signature
					var signatureId = "${order.quotation.quotationAcceptance.signature.id}"
					console.log("signatureId >> " + signatureId)
					getSignature(signatureId)
				} else {
					console.log("Quotation not accepted")
				}
				var salesSignatureId = ${ order.quotation.salesPerson.signature.id }
				console.log("Sales signatureId >> " + salesSignatureId)
				getSignatureByUserName(salesSignatureId)

				/* if (stage != role) {
					$('input').attr('readonly', 'readonly');
					// $('select').attr('disabled', true);
					$('button').attr('disabled', true);
					$('.btn').attr('disabled', true);
				} */

				// else if (role !== 'risk') {
				// 	console.log('In Risk test');
				// }

				// $('.enableForAllRoles').attr('disabled', false);
				// $('.nav-link').attr('disabled', false);



				$('input').attr('readonly', true);
				// $('select').attr('disabled', true);
				$('button').attr('disabled', true);
				$('.readOnly').attr('readonly', true);

				$('.nav-link').removeAttr('disabled');
				$('button.action-button').removeAttr('disabled');
				$('input.action-input').removeAttr('readonly');

				$('.action-select').removeAttr('readonly');
				$('.action-select').removeAttr('disabled');


				if (stage === 'processing') {
					buttonAction.innerHTML = "Move to Risk"
					buttonAction.disabled = false
				} else if (stage === 'risk') {
					buttonAction.innerHTML = "Move to Merchant Registration"
					buttonAction.disabled = false
				} else {
					buttonAction.innerHTML = "Move to Merchant Registration"
					buttonAction.disabled = true
				}
			};

			buttonSaveDetails.addEventListener('click', (event) => {
				startLoadingScreen("Processing, please wait...");
				$('#orderForm').attr('action', 'updateOrder');
			});

			document.getElementById('button-action').addEventListener('click', (event) => {
				startLoadingScreen("Processing, please wait...")
				$('#orderForm').attr('action', 'submitOrder');
			});

			buttonEdit.addEventListener('click', (event) => {
				checkUserRole(event)
			});

			function checkUserRole(event) {
				if (role === 'processing' && stage === "processing") {
					$('input').removeAttr('readonly');
					$('select').removeAttr('disabled');
					$('button').removeAttr('disabled');
					$('.readOnly').attr('readonly', true);
					buttonSaveDetails.style.display = "block"
					$('.dtlInfo input').attr("readonly", true);
				} else if (role === 'risk' && stage === "risk") {
					$('.dtlInfo input').attr("readonly", false);
					buttonSaveDetails.style.display = "block"
					buttonSaveDetails.disabled = false
				} else if (role === 'admin') {
					$('input').removeAttr('readonly');
					$('select').removeAttr('disabled');
					$('button').removeAttr('disabled');
					$('.readOnly').attr('readonly', true);
					$('.dtlInfo input').attr("readonly", true);
					buttonSaveDetails.style.display = "block"
				} else {
					alert("Your role does not have access to the order!")
				}
				event.target.style.display = "none"
			}

			function getOrderNotes() {
				const config = {
					method: "post",
					url: contextPath + "/order/" + orderId + "/notes",
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
						if (response.responseStatus === "0000") {
							loadOrderNotes(response.responseData)
						}
					})
					.catch(error => {
						console.log(error)
					})
			}

			function getOrderDirector() {
				event.preventDefault();
				const config = {
					method: "post",
					url: contextPath + "/order/" + orderId + "/director",
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
						if (response.responseStatus === "0000") {
							loadOrderDirector(response.responseData)
						}
					})
					.catch(error => {
						console.log(error)
					})
			}

			var directorData = null;

			function loadOrderDirector(data) {
				const tableDirectorBody = document.getElementById("table-director-body")
				tableDirectorBody.innerHTML = ""
				let count = 1

				directorData = data;

				for (const [index, item] of data.entries()) {

					const tableRow = document.createElement("tr")
					const id = document.createElement("td")
					id.innerText = count
					const name = document.createElement("td")
					name.innerText = item.name
					const designation = document.createElement("td")
					designation.innerText = item.designation
					const contactNo = document.createElement("td")
					contactNo.innerText = item.contactNo
					const nationality = document.createElement("td")
					nationality.innerText = item.nationality
					const action = document.createElement("td")
					action.style.textAlign = "right"

					const deleteIcon = document.createElement("img")
					deleteIcon.src = contextPath + "/resources/images/delete_icon.svg"
					deleteIcon.width = "15"

					const editIcon = document.createElement("img")
					editIcon.src = contextPath + "/resources/images/edit_icon.svg"
					editIcon.width = "15"

					const documentIcon = document.createElement("img")
					documentIcon.src = contextPath + "/resources/images/document_icon.svg"
					documentIcon.width = "15"

					const deleteButton = document.createElement("button")
					deleteButton.className = "btn btn-sm btn-danger btn-sm m-1"
					deleteButton.appendChild(deleteIcon)
					deleteButton.value = item.id
					deleteButton.setAttribute("onclick", "deleteOrderDirector(" + item.id + ")");


					const editButton = document.createElement("button")
					editButton.className = "btn btn-sm btn-primary btn-sm m-1"
					editButton.appendChild(editIcon)
					editButton.value = item.id
					editButton.setAttribute("onclick", "loadOrderDirectorEditView(" + index + ")");

					const criReport = document.createElement("button")
					criReport.className = "btn btn-sm btn-primary btn-sm m-1 enableForAllRoles"
					criReport.appendChild(documentIcon)
					criReport.value = item.id
					criReport.setAttribute("onclick", "criExperianReport('" + item.name + "','" + item.idNo + "')");

					action.appendChild(editButton)
					action.appendChild(deleteButton)
					action.appendChild(criReport)

					tableRow.appendChild(id)
					tableRow.appendChild(name)
					tableRow.appendChild(designation)
					tableRow.appendChild(contactNo)
					tableRow.appendChild(nationality)
					tableRow.appendChild(action)

					tableDirectorBody.appendChild(tableRow)
					count++
				}
			}

			function viewExperianCRIReport() {

			}

			function getSignatureByUserName(signatureId) {
				const config = {
					method: "post",
					/* url: contextPath + "/signature/get", */

					url: contextPath + "/signature/" + signatureId,
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
						if (response.responseStatus === "0000") {
							loadUserSignature(response.responseData)
						}
					})
					.catch(error => {
						console.log(error)
					})
			}


			function getSignature(signatureId) {
				const config = {
					method: "post",
					url: contextPath + "/signature/" + signatureId,
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
						if (response.responseStatus === "0000") {
							loadMerchantSignature(response.responseData)
						}
					})
					.catch(error => {
						console.log(error)
					})

			}

			function loadMerchantSignature(data) {
				document.getElementById("merchant-signature").src = contextPath + data.imageUrl
			}

			function loadUserSignature(data) {
				document.getElementById("sales-signature").src = contextPath + data.imageUrl
			}

			function loadOrderNotes(data) {
				const tableNotesBody = document.getElementById("table-notes-body")
				tableNotesBody.innerHTML = ""
				let count = 1

				for (var item of data) {
					const tableRow = document.createElement("tr")
					const id = document.createElement("td")
					id.innerText = count
					const sendDate = document.createElement("td")
					sendDate.innerText = moment(item.sendDate).format("DD/MM/YYYY HH:mm:ss")
					const from = document.createElement("td")
					from.innerText = item.from
					const notifyTo = document.createElement("td")
					notifyTo.innerText = item.notifyTo
					const subject = document.createElement("td")
					subject.innerText = item.subject
					const notes = document.createElement("td")
					notes.innerText = item.notes

					tableRow.appendChild(id)
					tableRow.appendChild(sendDate)
					tableRow.appendChild(from)
					tableRow.appendChild(notifyTo)
					tableRow.appendChild(subject)
					tableRow.appendChild(notes)

					tableNotesBody.appendChild(tableRow)
					count++
				}
			}


			function initialFunction() {
				console.log("token-> ", token)
				console.log("header-> ", token)

				$(document).ajaxSend(function (e, xhr, options) {
					xhr.setRequestHeader(header, token);
				});

				$.ajaxSetup({
					headers: {
						'X-CSRF-TOKEN': $(
							'meta[name="csrf-token"]').attr(
								'content')
					}
				});
				getDocumentTypeList()
				getStageList()
			}

			function getStageList() {
				const config = {
					method: "post",
					url: contextPath + "/order/" + orderId + "/stage",
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
						if (response.responseStatus === "0000") {
							listAllStage(response.responseData)
						} else {
							alert(response.responseMessage)
						}
					})
					.catch(error => {
						console.log(error)
					})
			}

			function listAllStage(data) {

				const tableBody = document.getElementById("stage-movement-table-body")
				if (data.length === 0) {
					const noDataAvailable = document.createElement("p")
					noDataAvailable.innerText = "No Data Available"
					tableBody.appendChild(noDataAvailable)
				}


				data.forEach(item => {
					const row = document.createElement("tr")

					const fromStage = document.createElement("td")
					fromStage.innerText = item.fromStage
					fromStage.style.textTransform = "uppercase"


					const toStage = document.createElement("td")
					toStage.innerText = item.toStage
					toStage.style.textTransform = "uppercase"

					const createdAt = document.createElement("td")
					createdAt.innerText = moment(item.createdAt).format("DD/MM/YYYY HH:mm:ss")

					const userName = document.createElement("td")
					userName.innerText = item.userName

					row.appendChild(fromStage)
					row.appendChild(toStage)
					row.appendChild(createdAt)
					row.appendChild(userName)

					tableBody.appendChild(row)

				});
			}

			function getDocumentTypeList() {
				const config = {
					method: "post",
					url: contextPath + "/document/category",
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
						if (response.responseStatus === "0000") {
							listDocumentCategory(response.responseData)
						} else {
							alert(response.responseMessage)
						}
					})
					.catch(error => {
						console.log(error)
					})
			}


			selectFileType.addEventListener("change", function (event) {
				var errorBloc = inputFileBlock.querySelector(".error-text")
				if (event.target.value !== 0) {
					inputFileUpload.removeAttribute("disabled")
					errorBloc.style.display = "none"
				} else {
					var disabled = document.createAttribute('disabled');
					disabled.value = "true"
					inputFileUpload.setAttributeNode(disabled);
					errorBloc.style.display = "block"
				}
			})

			function listDocumentCategory(data) {

				data.forEach(element => {
					var option = document.createElement("option")
					option.innerHTML = element.display
					option.value = element.display
					selectFileType.appendChild(option)
				});

			}

			$(function () {
				$(".datepick").datepicker();
			});

			$('.saveDirector').click(function (e) {
				e.preventDefault();
				$.post("saveDirector", {
					name: $('#name').val(),
					designation: $('#designation').val(),
					idType: $('#idType').val(),
					idNo: $('#idNo').val(),
					contactNo: $('#contactNo').val(),
					nationality: $('#nationality').val(),
					address: $('#address').val(),
					orderId: $('#id').val()

				}, function (data, status) {
					$('#directorModal').modal({
						backdrop: 'static',
						keyboard: false
					});
					$('#directorModal').modal('hide');
					getOrderDirector()
				});

			});

			function loadOrderDirectorEditView(item) {
				document.getElementById("director-edit-name").value = directorData[item].name
				document.getElementById("director-edit-designation").value = directorData[item].designation
				document.getElementById("director-edit-contactNo").value = directorData[item].contactNo
				document.getElementById("director-edit-nationality").value = directorData[item].nationality
				document.getElementById("director-edit-idNo").value = directorData[item].idNo
				document.getElementById("director-edit-merchantIdType").value = directorData[item].idType
				document.getElementById("director-edit-address").value = directorData[item].address
				document.getElementById("button-update-director").value = directorData[item].id
				editDirectorModal.show()
			}

			function updateDirectory() {
				const name = document.getElementById("director-edit-name").value
				const designation = document.getElementById("director-edit-designation").value
				const contactNo = document.getElementById("director-edit-contactNo").value
				const nationality = document.getElementById("director-edit-nationality").value
				const idNo = document.getElementById("director-edit-idNo").value
				const idType = document.getElementById("director-edit-merchantIdType").value
				const address = document.getElementById("director-edit-address").value
				const id = document.getElementById("button-update-director").value

				const config = {
					method: 'PUT',
					url: contextPath + '/order/' + orderId + "/director",
					headers: {
						'X-CSRF-TOKEN': token,
						'Content-Type': 'application/json'
					},
					data: JSON.stringify({
						name: name,
						designation: designation,
						contactNo: contactNo,
						nationality: nationality,
						idNo: idNo,
						idType: idType,
						address: address,
						id: id
					})
				}

				this.axios(config)
					.then(response => {
						return response.data
					})
					.then(response => {
						alert(response.responseMessage)
						getOrderDirector()
					})
					.catch(error => {
						console.log('error -> ', error)
					})
					.finally(() => {
						editDirectorModal.hide()
					})
			}


			document.getElementById("saveNotes").addEventListener('click', function () {
				startLoadingScreen("Processing, please wait...");

				const config = {
					method: 'PUT',
					url: contextPath + '/order/' + orderId + "/notes",
					headers: {
						'X-CSRF-TOKEN': token,
						'Content-Type': 'application/json'
					},
					data: JSON.stringify({
						notifyTo: $('#notifyTo').val(),
						subject: $('#subject').val(),
						notes: $('#notes').val(),
						companyName: $('#input-merchant-trading-name').val(),
						orderId: $('#id').val()
					})
				};

				axios(config)
					.then(response => {
						return response.data
					})
					.then(response => {
						alert(response.responseMessage)
						window.location.reload();
					})
					.catch(error => {
						console.log('error -> ', error)
					})
					.finally(() => {
					});
			});


			$("#btnWelcomeLetterDownload").click(function (e) {
				e.preventDefault();
				$.ajax({
					type: "GET",
					url: "/ocsweb/merchantReg/generateWL",
					data: {
						orderId: $('#id').val()
					},
					success: function (result) {
						alert(result.responseMessage);
						if (result.responseStatus === "0000") {
							const urlWl = "${pageContext.request.contextPath}" + result.responseData.welcomeLetterPath;
							window.open(urlWl)
						}
					},
					error: function (result) {
						alert('error');
					}
				});
			});

			$("#btnMMADownload").click(function (e) {
				e.preventDefault();
				$.ajax({
					type: "GET",
					url: "/ocsweb/merchantReg/generateMMA",
					data: {
						orderId: $('#id').val()
					},
					success: function (result) {
						if (result.responseStatus === "0000") {
							const mmaURL = "${pageContext.request.contextPath}" + result.responseData.mmaPath;
							window.open(mmaURL)
						} else {
							alert(result.responseMessage);
						}
					},
					error: function (result) {
						alert('error');
					}
				});
			});

			function getAvailableDocuments() {
				const config = {
					method: 'post',
					url: contextPath + '/document/order/' + orderId,
					headers: {
						'X-CSRF-TOKEN': token,
						'Content-Type': 'application/json'
					}
				}

				this.axios(config)
					.then(response => {
						return response.data
					})
					.then(response => {
						if (response.responseStatus === "0000") {
							loadAvailableDocument(response.responseData)
						} else {
							alert(response.responseMessage)
						}
					})
					.catch(error => {
						console.log('error -> ', error)
					})
					.finally(() => {
					})
			}

			document.getElementById("upload-all-document").addEventListener("click", function () {
				uploadUserDocuments()
			})

			async function uploadUserDocuments() {
				var selectedDocuments = [];
				var documentsFiles = document.getElementById('document-file-upload')

				var documentData = []
				var documentName = []
				var documentFileType = []
				var documentFileCategory = []

				uploadedFile.forEach(element => {
					var documentBase64 = getBase64(element).then(response => { return response.split(",")[1] })
					documentData.push(documentBase64)
					documentName.push(element.name)
					documentFileType.push(element.type)
					documentFileCategory.push(element.fileType)
				});

				var requestData = {
					"orderId": orderId,
					"documentData": await Promise.all(documentData),
					"documentName": documentName,
					"documentFileCategory": documentFileCategory,
					"documentFileType": documentFileType
				}

				const config = {
					method: 'post',
					url: contextPath + '/document/upload',
					headers: {
						'X-CSRF-TOKEN': token,
						'Content-Type': 'application/json'
					},
					data: JSON.stringify(requestData)
				}

				startLoadingScreen("Processing, please wait...");

				this.axios(config)
					.then(response => {
						return response.data
					})
					.then(response => {
						alert(response.responseMessage)
						if (response.responseStatus === "0000") {
							document.getElementById("document-upload").style.display = "none"
							document.getElementById("document-upload").style.display = "none"
							document.getElementById("document-footer").style.display = "none"
							uploadDocumentTableBody.innerHTML = ""
							uploadedFile = []
							getAvailableDocuments()
						}

						console.log(response);
					})
					.catch(error => {
						console.log('error -> ', error);
					})
					.finally(() => {
						stopLoadingScreen();
					})
			}

			function addDocument() {
				event.preventDefault();
				document.getElementById("document-upload").style.display = "block"
			}

			function getBase64(file) {
				return new Promise((resolve, reject) => {
					const reader = new FileReader();
					reader.readAsDataURL(file);
					reader.onload = () => resolve(reader.result);
					reader.onerror = error => reject(error);
				});
			}

			function loadAvailableDocument(data) {
				documentAvailableBody.innerHTML = ""
				if (data.length > 0) {
					data.forEach(documentFile => {
						const tableRow = document.createElement('tr')

						const documentName = document.createElement('td')
						const documentStatus = document.createElement('td')
						const documentCategory = document.createElement('td')
						const buttonDeleteAction = document.createElement('td')
						const buttonViewAction = document.createElement('td')

						const deleteIcon = document.createElement("img")
						deleteIcon.src = contextPath + "/resources/images/delete_icon.svg"
						deleteIcon.style.width = "15"

						const deleteButton = document.createElement("button")
						deleteButton.classList = "btn btn-sm btn-danger btn-sm"
						deleteButton.appendChild(deleteIcon)
						deleteButton.value = documentFile.id
						deleteButton.addEventListener('click', () => {
							removeUploadedFile(documentFile.id, orderId)
						}, false)

						if ((stage === 'pending-deployment') || (stage === 'deployed')) {
							deleteButton.disabled = true
						}

						const viewIcon = document.createElement("img")
						viewIcon.src = contextPath + "/resources/images/view_icon.svg"
						viewIcon.style.width = "15"


						const action = document.createElement('button')
						action.className = "btn btn-sm btn-primary btn-sm"
						action.type = "button"
						action.value = contextPath + documentFile.documentData
						action.id = "document-action-view"
						action.appendChild(viewIcon)
						action.addEventListener('click', () => {
							window.open(contextPath + documentFile.documentData)
						}, false)

						documentCategory.innerHTML = documentFile.documentCategory === null ? "Not Available" : documentFile.documentCategory
						documentName.innerHTML = documentFile.documentName
						documentStatus.innerHTML = documentFile.status === 0 ? 'Not Approved' : 'Approved'

						buttonDeleteAction.appendChild(deleteButton)
						buttonViewAction.appendChild(action)

						tableRow.appendChild(documentName)
						tableRow.appendChild(documentCategory)
						tableRow.appendChild(buttonDeleteAction)
						tableRow.appendChild(buttonViewAction)

						documentAvailableBody.appendChild(tableRow)
					});


				} else {
					const tableRow = document.createElement('tr')
					const noDocumentAvailable = document.createElement('td')
					noDocumentAvailable.innerHTML = "No Document Available"
					tableRow.appendChild(noDocumentAvailable)
					documentAvailableBody.appendChild(tableRow)
				}
			}


			function removeUploadedFile(value, orderId) {
				event.preventDefault()
				const requestData = {
					orderId: orderId
				}
				const config = {
					method: "POST",
					url: contextPath + "/document/" + value + "/remove",
					headers: {
						'X-CSRF-TOKEN': token,
						'Content-Type': 'application/json'
					},
					data: JSON.stringify(requestData)
				}

				console.log("removeUploadedFile >> " + JSON.stringify(config))

				axios(config)
					.then(response => {
						return response.data
					})
					.then(response => {
						alert(response.responseMessage)
						if (response.responseStatus === "0000") {
							getAvailableDocuments()
						}
					})
					.catch(error => {
						console.log(error)
					})
			}

			function formatFileSize(bytes, decimalPoint) {
				if (bytes == 0) return '0 Bytes';
				var k = 1000,
					dm = decimalPoint || 2,
					sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
					i = Math.floor(Math.log(bytes) / Math.log(k));
				return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
			}

			function displayUploadedDocuments(files) {
				uploadDocumentTableBody.innerHTML = ""
				if (files.length > 0) {
					document.getElementById("document-footer").style.display = "block"
				}

				for (let tableBodyIndex = 0; tableBodyIndex < files.length; tableBodyIndex++) {
					const file = files[tableBodyIndex]
					const tableRow = document.createElement('tr')
					tableRow.className = "file-" + tableBodyIndex
					const sNo = document.createElement('td')
					const documentName = document.createElement('td')
					const documentSize = document.createElement('td')
					const actionButton = document.createElement('td')

					const action = document.createElement('button')
					action.className = "btn btn-sm btn-sm btn-outline-danger"
					action.type = "button"
					action.innerHTML = "Remove"
					action.value = tableBodyIndex
					action.id = "document-action-remove"

					action.addEventListener('click', () => {
						// var tableRows = document.getElementById("").rows
						console.log("selectedFiles --> " + JSON.stringify(files[tableBodyIndex]))
						files.splice(tableBodyIndex, 1);
						tableRow.remove()
					}, false)

					sNo.innerHTML = tableBodyIndex + 1
					documentName.innerHTML = file.name
					documentSize.innerHTML = formatFileSize(file.size);

					actionButton.appendChild(action);

					tableRow.appendChild(sNo);
					tableRow.appendChild(documentName);
					tableRow.appendChild(documentSize);
					tableRow.appendChild(actionButton);

					uploadDocumentTableBody.appendChild(tableRow)
				}

				/* restore the select to 0 and disable the file input container */
				var errorBloc = inputFileBlock.querySelector(".error-text")
				selectFileType.value = 0
				var disabled = document.createAttribute('disabled')
				disabled.value = "true"
				inputFileUpload.setAttributeNode(disabled);
				errorBloc.style.display = "block"

			}

			function deleteOrderDirector(directorId) {
				event.preventDefault();
				const config = {
					method: 'delete',
					url: contextPath + '/order/' + orderId + "/director/" + directorId,
					headers: {
						'X-CSRF-TOKEN': token,
						'Content-Type': 'application/json'
					}
				}

				this.axios(config)
					.then(response => {
						return response.data
					})
					.then(response => {
						alert(response.responseMessage)
						if (response.responseStatus === "0000") {
							getOrderDirector()
						}

						console.log(response)
					})
					.catch(error => {
						console.log('error -> ', error)
					})
					.finally(() => {

					})
			}

			document.getElementById("experianReport").onclick = function (event) {
				experianReport(event)
			}

			document.getElementById("experianECIReport").onclick = function (event) {
				experianECIReport(event)
			}


			function experianECIReport(event) {
				event.preventDefault();

				const tableBody = document.getElementById("table-eci-body-directors");
				tableBody.innerHTML = ""

				startLoadingScreen("Loading, please wait...");

				// var data = JSON.stringify({
				// 	"entityName": document.getElementById("input-merchant-trading-name").value,
				// 	"entityId": document.getElementById("input-business-registration-no").value,
				// 	"entityId2": "",
				// 	"mobileNo": document.getElementById("input-office-number").value,
				// 	"emailAddress": document.getElementById("input-office-email").value,
				// 	"lastKnownAddress": "Malaysia",
				// 	"ref1": "",
				// 	"ref2": "",
				// 	"ref3": "",
				// 	"ref4": ""
				// });


				var data = JSON.stringify({
					"entityName": "NOVA NUTRITIONAL SUPPLIES SDN.  BHD.",
					"entityId": "326176K",
					"entityId2": "199401011077",
					"mobileNo": "",
					"emailAddress": "",
					"lastKnownAddress": "Malaysia",
					"ref1": "",
					"ref2": "",
					"ref3": "",
					"ref4": ""
				});

				var config = {
					method: 'POST',
					url: 'https://demoexperian.gomobi.io/reports/api/experian/search/ECI',
					auth: {
						username: 'mobi',
						password: 'LvYFndLqxU455CzvSVqbGub2aTyUrBm89Y2UTnDWzC9KpFU5FckFBCRaadf5tq5Y7EeesHU2LrF5pbS8dkz2XPn8QMJsRYGdQ6zRmFJKnCnMBzMHBJPph6jCgfaryPX6'
					},
					headers: {
						'Content-Type': 'application/json',
					},
					data: data
				};

				axios(config)
					.then(function (response) {
						return response.data;
					})
					.then((response) => {
						if (response.responseCode === "0000" && response.responseData !== null) {

							experianECIModal.show()

							eciJsonData = response.responseData;

							const company_details = response.responseData.company_details;

							document.getElementById("eci-nameOfSubject").value = company_details.company_name;
							document.getElementById("eci-localNumber").value = company_details.local_no;

							const directors = response.responseData.directors;

							directors.forEach((item) => {
								const tr = document.createElement("tr");

								const name = document.createElement("td");
								name.innerHTML = item.name

								const address = document.createElement("td");
								address.innerHTML = item.address

								const new_ic = document.createElement("td");
								new_ic.innerHTML = item.new_ic

								const designation = document.createElement("td");
								designation.innerHTML = item.designation

								const viewIcon = document.createElement("img")
								viewIcon.src = contextPath + "/resources/images/view_icon.svg"
								viewIcon.style.width = "15"

								const viewReport = document.createElement("button");
								viewReport.className = "btn btn-sm btn-primary btn-sm";
								viewReport.appendChild(viewIcon)
								viewReport.setAttribute("onclick", "criExperianReport('" + item.name + "','" + item.new_ic + "')")

								tr.appendChild(name);
								tr.appendChild(address);
								tr.appendChild(new_ic);
								tr.appendChild(designation);
								tr.appendChild(viewReport);

								tableBody.appendChild(tr);
							});
						}
					})
					.catch(function (error) {
						console.log(error);
					})
					.finally(() => {
						stopLoadingScreen();
					});
			}


			function experianReport(event) {
				event.preventDefault();

				const tableBody = document.getElementById("table-ebi-body-directors");
				tableBody.innerHTML = ""

				startLoadingScreen("Loading, please wait...");

				// var data = JSON.stringify({
				// 	"entityName": document.getElementById("input-business-name").value,
				// 	"entityId": document.getElementById("input-business-registration-no").value,
				// 	"entityId2": "",
				// 	"mobileNo": document.getElementById("input-office-number").value,
				// 	"emailAddress": document.getElementById("input-office-email").value,
				// 	"lastKnownAddress": "Malaysia",
				// 	"ref1": "",
				// 	"ref2": "",
				// 	"ref3": "",
				// 	"ref4": ""
				// });

				var data = JSON.stringify({
					"entityName": "Y.S.TAN ENTERPRISE",
					"entityId": "AS0186886M",
					"entityId2": "199603060185",
					"mobileNo": "",
					"emailAddress": "",
					"lastKnownAddress": "Malaysia",
					"eastMalaysia": "N",
					"ref1": "",
					"ref2": "",
					"ref3": "",
					"ref4": ""
				});

				var config = {
					method: 'POST',
					url: 'https://demoexperian.gomobi.io/reports/api/experian/search/EBI',
					auth: {
						username: 'mobi',
						password: 'LvYFndLqxU455CzvSVqbGub2aTyUrBm89Y2UTnDWzC9KpFU5FckFBCRaadf5tq5Y7EeesHU2LrF5pbS8dkz2XPn8QMJsRYGdQ6zRmFJKnCnMBzMHBJPph6jCgfaryPX6'
					},
					headers: {
						'Content-Type': 'application/json',
					},
					data: data
				};

				axios(config)
					.then(function (response) {
						return response.data;
					})
					.then((response) => {
						if (response.responseCode === "0000" && response.responseData !== null) {
							experianEBIModal.show()

							ebiJsonData = response.responseData;

							const business_details = response.responseData.business_details;

							document.getElementById("ebi-nameOfSubject").value = business_details.name;
							document.getElementById("ebi-localNumber").value = business_details.registration_no;

							const owners = response.responseData.owner;


							owners.forEach((item) => {
								const tr = document.createElement("tr");

								const name = document.createElement("td");
								name.innerHTML = item.name

								const address = document.createElement("td");
								address.innerHTML = item.address

								const new_ic = document.createElement("td");
								new_ic.innerHTML = item.new_ic

								const designation = document.createElement("td");
								designation.innerHTML = item.position

								const viewIcon = document.createElement("img")
								viewIcon.src = contextPath + "/resources/images/view_icon.svg"
								viewIcon.style.width = "15"

								const viewReport = document.createElement("button");
								viewReport.className = "btn btn-sm btn-primary btn-sm";
								viewReport.appendChild(viewIcon)
								viewReport.setAttribute("onclick", "criExperianReport('" + item.name + "','" + item.new_ic + "')")

								const buttonRow = document.createElement("tr");
								buttonRow.appendChild(viewReport);

								tr.appendChild(name);
								tr.appendChild(address);
								tr.appendChild(new_ic);
								tr.appendChild(designation);
								tr.appendChild(buttonRow);

								tableBody.appendChild(tr);
							});
						}
					})
					.catch(function (error) {
						console.log(error);
					})
					.finally(() => {
						stopLoadingScreen();
					});
			}


			function criExperianReport(name, registrationNo) {
				event.preventDefault();
				experianCRIModal.hide();
				experianECIModal.hide();
				experianEBIModal.hide();

				const tableBody = document.getElementById("table-cri-address-list");
				tableBody.innerHTML = "";

				startLoadingScreen("Loading, please wait...");

				// var data = JSON.stringify({
				// 	"entityName": name,
				// 	"entityId": registrationNo,
				// 	"entityId2": "",
				// 	"mobileNo": "",
				// 	"emailAddress": "",
				// 	"lastKnownAddress": "Malaysia",
				// 	"ref1": "",
				// 	"ref2": "",
				// 	"ref3": "",
				// 	"ref4": ""
				// });		


				var data = JSON.stringify({
					"entityName": "XXXXXXX XXXTI ISMAIL",
					"entityId": "720223-09-2020",
					"entityId2": "A0111018",
					"mobileNo": "",
					"emailAddress": "",
					"lastKnownAddress": "Malaysia",
					"ref1": "",
					"ref2": "",
					"ref3": "",
					"ref4": ""
				});

				var config = {
					method: 'POST',
					url: 'https://demoexperian.gomobi.io/reports/api/experian/search/CRI',
					auth: {
						username: 'mobi',
						password: 'LvYFndLqxU455CzvSVqbGub2aTyUrBm89Y2UTnDWzC9KpFU5FckFBCRaadf5tq5Y7EeesHU2LrF5pbS8dkz2XPn8QMJsRYGdQ6zRmFJKnCnMBzMHBJPph6jCgfaryPX6'
					},
					headers: {
						'Content-Type': 'application/json',
					},
					data: data
				};

				axios(config)
					.then(function (response) {
						return response.data;
					})
					.then((response) => {
						if (response.responseCode === "0000") {
							// document.getElementById("search-details").style.display = "none";
							// document.getElementById("search-result").style.display = "block";

							experianCRIModal.show()

							criJsonData = response.responseData;

							const personalDetails = response.responseData.person_details;

							document.getElementById("cri-personalDetails_name").value =
								personalDetails.name;
							document.getElementById("cri-old_ic").value = personalDetails.old_ic;
							document.getElementById("cri-new_ic").value = personalDetails.new_ic;

							const addresses = response.responseData.person_details.addresses;


							addresses.forEach((item) => {
								const tr = document.createElement("tr");

								const indexNo = document.createElement("td");
								indexNo.innerHTML = addresses.indexOf(item) + 1;

								const addressLine = document.createElement("td");
								addressLine.innerHTML = item.address;

								const datechanged = document.createElement("td");
								datechanged.innerHTML = item.datechanged;

								tr.appendChild(indexNo);
								tr.appendChild(addressLine);
								tr.appendChild(datechanged);

								tableBody.appendChild(tr);
							});
						}
					})
					.catch(function (error) {
						console.log(error);
					})
					.finally(() => {
						stopLoadingScreen();
					});
			}
			var ebiJsonData = null;
			var eciJsonData = null;
			var criJsonData = null;
			var eciDocumentDefinition = {
				header: {
					style: "header",
					text: "Mobi Experian ECI Report",
				},

				footer: {
					style: "header",
					text: "Mobi Experian ECI Report",
				},

				content: [
        /* 0 */ {
						text: "Enhanced Company Intelligence (ECI)",
						style: "title",
					},
        /* 1 */ {
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Particular Of The Subject Provided By You"]],
						},
					},
        /* 2 */ {
						style: "tableContent",
						table: {
							widths: [100, "*"],
							body: [
								[
									"Name Of Subject",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Local No",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Your Ref. No",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
							],
						},
					},

        //   Data Bank
        /* 3 */ {
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Information In Our Databank"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: [100, "*"],
							body: [
								[
									"Name Of Subject",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Local No",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
							],
						},
					},

					//   Address in bank

					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Address(s) In Our Databank"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: ["*", 120],
							body: [
								[
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
									"Name Of Subject",
								],
								[
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
									"Local No",
								],
							],
						},
					},

					//   Company Details

					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Company Details"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: [100, "*"],
							body: [
								[
									"Local No",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Company Name",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Incorporation Date",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Type",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Market",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Listing date",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Status",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Private Exempt Company",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Business Address",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Registered Address",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Total Issued and Paid Up Capital",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Business Sector",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Nature of Business",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Auditor",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Auditor's Address",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Last Financial Filed",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Last Updated by Experian",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
							],
						},
					},

					//   Summary Of Share

					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Summary Of Share Capital"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: ["*", "*", "*"],
							body: [
								["Total Issued (RM) 500,000.00", "Cash", "Otherwise Than Cash"],
								["Ordinary", "0.00", "0.00"],
								["Preference", "0.00", "0.00"],
								["Others", "0.00", "0.00"],
							],
						},
					},

					//   Directors / Officers

					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Directors / Officers"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: ["auto", "*", 80, 80],
							body: [
								["Name", "Address", "IC/PP No", "Designation"],
								["Ordinary", "0.00", "0.00", "Otherwise Than Cash"],
								["Preference", "0.00", "0.00", "Otherwise Than Cash"],
								["Others", "0.00", "0.00", "Otherwise Than Cash"],
							],
						},
					},

					//   Shareholders / Members

					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Shareholders / Members"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: ["auto", "*", 120, 60, 40],
							body: [
								["Name", "Address", "IC/PP No", "Shareholding", "%"],
								["Ordinary", "0.00", "0.00", "Otherwise Than Cash", ""],
								["Preference", "0.00", "0.00", "Otherwise Than Cash", ""],
								["Others", "0.00", "0.00", "Otherwise Than Cash", ""],
							],
						},
					},

					//   Company Financial Statement

					{
						style: "title",
						text: "Company Financial Statement",
					},
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Financial Summary"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: ["*", 80, 80, 80, 80],
							body: [
								["Consolidated Account", "", "", "", ""],
								["Auditor's Name", "", "", "", ""],
								["UnQualified Reports (Y/N)", "", "", "", ""],
								["Date of Tabling", "", "", "", ""],
								["Units", "", "", "", ""],
							],
						},
					},

					// Balance Sheet
					{
						style: "title",
						text: "Company Financial Statement",
					},
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Balance Sheet"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: ["*", 80, 80, 80, 80],
							body: [
								["Full Audited Financial Availability", "", "", "", ""],
								["Financial Year End", "", "", "", ""],
								["Non Current Assets", "", "", "", ""],
								["Current Assets", "", "", "", ""],
								["Total Assets", "", "", "", ""],

								["Paid-up Capital", "", "", "", ""],
								["Share Application Account", "", "", "", ""],
								["Share Premium & Other Reserves", "", "", "", ""],
								["Accumulated Profit Carried Forward", "", "", "", ""],
								["Minority Interest", "", "", "", ""],
								["Total Equity", "", "", "", ""],
								["Non Current Liabilities", "", "", "", ""],
								["Current Liabilities", "", "", "", ""],
								["Total Liabilities", "", "", "", ""],
								["Total Equity & Liabilities", "", "", "", ""],
							],
						},
					},

					// Profit And Loss Account
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Profit And Loss Account"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: ["*", 80, 80, 80, 80],
							body: [
								["Financial Year End", "", "", "", ""],
								["Revenue", "", "", "", ""],
								["Profit/(Loss) Before Tax", "", "", "", ""],
								["Profit/(Loss) After Tax", "", "", "", ""],
								["Monthly Interest", "", "", "", ""],
								["Net Dividend", "", "", "", ""],
							],
						},
					},

					// Key Financial Ratio
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Key Financial Ratio"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: ["*", 80, 80, 80, 80],
							body: [
								["Financial Year End", "", "", "", ""],
								["Profitability Ratio", "", "", "", ""],
								["Profit / Loss Before Tax Margin[%]", "", "", "", ""],
								["Profit / Loss After Tax Margin[%]", "", "", "", ""],
								["Return on Assets[%]", "", "", "", ""],
								["Return on Equity[%]", "", "", "", ""],
								["Return on Sales[%]", "", "", "", ""],
								["Return on Capital[Times]", "", "", "", ""],
								["Liquidity Ratio", "", "", "", ""],
								["Current Ration[Times]", "", "", "", ""],
								["Efficiency Ratio", "", "", "", ""],
								["Current Assets Turnover", "", "", "", ""],
								["Non-Current Assets Turnover", "", "", "", ""],
								["Total Assets Turnover", "", "", "", ""],
								["Working Capital Days", "", "", "", ""],
								["Leverage Ratio", "", "", "", ""],
								["Gearing Ratio (Times)", "", "", "", ""],
								["Debt Ration (Times)", "", "", "", ""],
								["Shareholders Equity Ratio (Times)", "", "", "", ""],
								["Debt to Capital Ratio (Times)", "", "", "", ""],
								["Growth Trend Ratio", "", "", "", ""],
								["Revenue Trend [%]", "", "", "", ""],
								["Net Income Trend [%]", "", "", "", ""],
								["Total Assets Trend [%]", "", "", "", ""],
								["Net Liabilities Trend [%]", "", "", "", ""],
								["Net Worth Trend [%]", "", "", "", ""],
								["Other Ratio", "", "", "", ""],
								["Earning Per Share [RM]", "", "", "", ""],
								["Dividend Per Share [RM]", "", "", "", ""],
								["Dividend Pay Out Ratio", "", "", "", ""],
							],
						},
					},

					// Interest In Other Companies
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Interest In Other Companies"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: ["*", 80, 80, 80, 80],
							body: [["Financial Year End", "", "", "", ""]],
						},
					},

					// Industry Indicator
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Interest In Other Companies"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [[""]],
						},
					},

					// Industry Debts Turned Cash
					{
						style: "title",
						text: "Industry Debts Turned Cash",
					},

					// Days Count
					{
						style: "title",
						text: "Date Count",
					},

					//  Legal Action
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Legal Action"]],
						},
					},

					//  Legal Suits - Subject As Defendant
					{
						style: "tableHeading",
						table: {
							widths: ["*", 80],
							body: [["Legal Suits - Subject As Defendant", "Total : 0"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [[""]],
						},
					},

					//  Legal Suits - Subject As Defendant
					{
						style: "tableHeading",
						table: {
							widths: ["*", 80],
							body: [["Legal Suits - Subject As Defendant", "Total : 0"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  Legal Suits - Subject As Plaintiff
					{
						style: "tableHeading",
						table: {
							widths: ["*", 80],
							body: [["Legal Suits - Subject As Plaintiff", "Total : 0"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  Winding Up Action - Subject As Defendant
					{
						style: "tableHeading",
						table: {
							widths: ["*", 80],
							body: [["Winding Up Action - Subject As Defendant", "Total : 0"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  Winding Up Action - Subject As Petitioner
					{
						style: "tableHeading",
						table: {
							widths: ["*", 80],
							body: [["Winding Up Action - Subject As Petitioner", "Total : 0"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},
					//  Trade Bureau
					{
						style: "title",
						text: "Trade Bureau",
					},

					//  Account Payment Profile
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Account Payment Profile"], [""]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  Account Payment Profile
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Account Trend (12 Months Period)"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  Non-Bank Lender Credit Information (NLCI)
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Non-Bank Lender Credit Information (NLCI)"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  Trade / Credit Reference (CR)
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Trade / Credit Reference (CR)"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  AML / Sanction List
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["AML / Sanction List"]],
						},
					},

					{
						style: "tableHeading",
						table: {
							widths: ["*", "*", "*", "*", "*", "*"],
							body: [
								[
									"No.",
									"Registration No.",
									"Company Name",
									"Trace Case",
									"Date Listed By Sources(s)",
									"Name Match",
								],
							],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: ["*", "*", "*", "*", "*", "*"],
							body: [["", "", "", "", "", ""]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*", "auto", "auto"],
							body: [
								[
									{ style: "testCenter", text: "Trace Case" },
									"Date Listed by Source(s)",
									"Name Match",
								],
							],
						},
					},

					//  AML / Sanction List
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Financial Related Search Count"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: [
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
							],
							body: [
								[
									"Years",
									"Total",
									"Jan",
									"Feb",
									"Mar",
									"Apr",
									"May",
									"Jun",
									"Jul",
									"Aug",
									"Sep",
									"Oct",
									"Nov",
									"Dec",
								],
							],
						},
					},

					//  Commercial Related Search Count
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Commercial Related Search Count"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: [
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
							],
							body: [
								[
									"Years",
									"Total",
									"Jan",
									"Feb",
									"Mar",
									"Apr",
									"May",
									"Jun",
									"Jul",
									"Aug",
									"Sep",
									"Oct",
									"Nov",
									"Dec",
								],
							],
						},
					},
				],
				styles: {
					tableContent: {
						fontSize: 8,
						margin: [0, 0, 0, 20],
					},
					testCenter: {
						fontSize: 8,
						alignment: "center",
					},
					tableHeading: {
						fontSize: 12,
					},
					title: {
						fontSize: 12,
						margin: [0, 0, 0, 10],
					},

					header: {
						fontSize: 12,
						margin: [10, 10, 10, 10],
					},

					footer: {
						fontSize: 12,
						margin: [10, 10, 10, 10],
					},
				},
			};
			var ebiDocumentDefinition = {
				header: {
					style: "header",
					text: "Mobi Experian EBI Report",
				},
				footer: {
					style: "header",
					text: "Mobi Experian EBI Report",
				},

				content: [
					{
						text: "Enhanced Business Intelligence (EBI)",
						style: "title",
					},
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Particular Of The Subject Provided By You"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: [100, "*"],
							body: [
								[
									"Name Of Subject",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Local No",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Your Ref. No",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
							],
						},
					},

					//   Data Bank

					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Information In Our Databank"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: [100, "*"],
							body: [
								[
									"Name Of Subject",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Local No",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
							],
						},
					},

					//   Address in bank

					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Address(s) In Our Databank"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: ["*", 120],
							body: [
								[
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
									"Name Of Subject",
								],
								[
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
									"Local No",
								],
							],
						},
					},

					//   Business Details

					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Business Details"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: [100, "*"],
							body: [
								[
									"Reg. no",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Business Name",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Date of Commencement",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Date Of Registration",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Amendment Date",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Date Of Expiry",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Business Status",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Business Sector",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Principal Activity",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Business Address",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Last Update By Experian",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
							],
						},
					},

					//   Owner Details

					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Owner(S)"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: [100, "*"],
							body: [
								[
									"Name",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"New IC No.",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Gender",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Address",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Date Of Birth / Inc.",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Nationality",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Entry Date",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Withdraw Date",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Remarks",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
							],
						},
					},
					// Industry Indicator
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Industry Indicator"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["Industry : Education"]],
						},
					},

					// Industry Debts Turned Cash
					{
						style: "title",
						text: "Industry Debts Turned Cash",
					},

					// Days Count
					{
						style: "title",
						text: "Date Count",
					},

					//  Legal Action
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Legal Action"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  Legal Suits - Subject As Defendant
					{
						style: "tableHeading",
						table: {
							widths: ["*", 80],
							body: [["Legal Suits - Subject As Defendant", "Total : 0"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  Legal Suits - Subject As Plaintiff
					{
						style: "tableHeading",
						table: {
							widths: ["*", 80],
							body: [["Legal Suits - Subject As Plaintiff", "Total : 0"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  Trade Bureau
					{
						style: "title",
						text: "Trade Bureau",
					},

					//  Account Payment Profile
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Account Payment Profile"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  Account Payment Profile
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Account Trend (12 Months Period)"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  Non-Bank Lender Credit Information (NLCI)
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Non-Bank Lender Credit Information (NLCI)"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  Trade / Credit Reference (CR)
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Trade / Credit Reference (CR)"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  AML / Sanction List
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["AML / Sanction List"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*", "*", "*", "*", "*", "*"],
							body: [
								[
									"No.",
									"Registration No.",
									"Business Name",
									"Trace Case",
									"Date Listed By Sources(s)",
									"Name Match",
								],
							],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: ["*", "*", "*", "*", "*", "*"],
							body: [["", "", "", "", "", ""]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*", "auto", "auto"],
							body: [
								[
									{ style: "testCenter", text: "Trace Case" },
									"Date Listed by Source(s)",
									"Name Match",
								],
							],
						},
					},

					//  AML / Sanction List
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Financial Related Search Count"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: [
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
							],
							body: [
								[
									"Years",
									"Total",
									"Jan",
									"Feb",
									"Mar",
									"Apr",
									"May",
									"Jun",
									"Jul",
									"Aug",
									"Sep",
									"Oct",
									"Nov",
									"Dec",
								],
								[
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
								],
								[
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
								],
							],
						},
					},

					//  Commercial Related Search Count
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Commercial Related Search Count"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: [
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
							],
							body: [
								[
									"Years",
									"Total",
									"Jan",
									"Feb",
									"Mar",
									"Apr",
									"May",
									"Jun",
									"Jul",
									"Aug",
									"Sep",
									"Oct",
									"Nov",
									"Dec",
								],
								[
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
								],
								[
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
								],
							],
						},
					},
				],
				styles: {
					tableContent: {
						fontSize: 8,
						margin: [0, 0, 0, 0],
					},
					testCenter: {
						fontSize: 8,
						alignment: "center",
					},
					tableHeading: {
						fontSize: 12,
						margin: [0, 10, 0, 0],
					},
					title: {
						fontSize: 12,
						margin: [0, 0, 0, 10],
					},

					header: {
						fontSize: 12,
						margin: [10, 10, 10, 10],
					},

					footer: {
						fontSize: 12,
						margin: [10, 10, 10, 10],
					},
				},
			};
			var criDocumentDefinition = {
				header: {
					style: "header",
					text: "Mobi Experian CRI Report",
				},

				footer: {
					style: "header",
					text: "Mobi Experian CRI Report",
				},

				content: [
					{
						text: "Individual Credit Intelligence (CRI)",
						style: "title",
					},
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Particular Of The Subject Provided By You"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: [100, "*"],
							body: [
								[
									"Name Of Subject",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Local No",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Your Ref. No",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
							],
						},
					},

					//   Data Bank

					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Information In Our Databank"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: [100, "*"],
							body: [
								[
									"Name Of Subject",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
								[
									"Local No",
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
								],
							],
						},
					},

					//   Address in bank

					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Address(s) In Our Databank"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: ["*", 120],
							body: [
								[
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
									"Name Of Subject",
								],
								[
									"This is a star-sized column. The next column over, an auto-sized column, will wrap to accomodate all the text in this cell.",
									"Local No",
								],
							],
						},
					},

					//  Share Holding Interest

					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Share Holding Interest"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["Interest In Company / Business"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: [33, "*", "*", "*", "*", "*", "*", "*", "*"],
							body: [
								[
									"No",
									"Name",
									"Position",
									"Appointed",
									"Business Expiry Date",
									"Shareholding",
									"%",
									"Remark",
									"Last Updated by Experian",
								],
							],
						},
					},
					//  Previous Known Companies / Business

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["Previous Known Companies / Business"]],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: [33, "*", "*", "*", "*", "*", "*"],
							body: [
								[
									"No",
									"Registration No",
									"Name",
									"Incorporated Date",
									"Status",
									"Position",
									"Cessation Date",
								],
							],
						},
					},

					//  Legal Action
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Legal Action"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [[""]],
						},
					},

					//  Legal Suits - Subject As Defendant
					{
						style: "tableHeading",
						table: {
							widths: ["*", 80],
							body: [["Legal Suits - Subject As Defendant", "Total : 0"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  Legal Suits - Subject As Plaintiff
					{
						style: "tableHeading",
						table: {
							widths: ["*", 80],
							body: [["Legal Suits - Subject As Plaintiff", "Total : 0"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  Winding Up Action - Subject As Defendant
					{
						style: "tableHeading",
						table: {
							widths: ["*", 80],
							body: [["Bankruptcy Action", "Total : 0"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  Trade Bureau
					{
						style: "title",
						text: "Trade Bureau",
					},

					//  Account Payment Profile
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Account Payment Profile"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  Account Trend (12 Months Period)
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Account Trend (12 Months Period)"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  Non-Bank Lender Credit Information (NLCI)
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Non-Bank Lender Credit Information (NLCI)"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  Trade / Credit Reference (CR)
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Trade / Credit Reference (CR)"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*"],
							body: [["No Information Available"]],
						},
					},

					//  AML / Sanction List
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["AML / Sanction List"]],
						},
					},

					{
						style: "tableHeading",
						table: {
							widths: ["*", "*", "*", "*", "*", "*"],
							body: [
								[
									"No.",
									"Registration No.",
									"Company Name",
									"Trace Case",
									"Date Listed By Sources(s)",
									"Name Match",
								],
							],
						},
					},
					{
						style: "tableContent",
						table: {
							widths: ["*", "*", "*", "*", "*", "*"],
							body: [["", "", "", "", "", ""]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: ["*", "auto", "auto"],
							body: [
								[
									{ style: "testCenter", text: "Trace Case" },
									"Date Listed by Source(s)",
									"Name Match",
								],
							],
						},
					},

					//  AML / Sanction List
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Financial Related Search Count"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: [
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
							],
							body: [
								[
									"Years",
									"Total",
									"Jan",
									"Feb",
									"Mar",
									"Apr",
									"May",
									"Jun",
									"Jul",
									"Aug",
									"Sep",
									"Oct",
									"Nov",
									"Dec",
								],
								[
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
								],
								[
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
								],
							],
						},
					},

					//  Commercial Related Search Count
					{
						style: "tableHeading",
						table: {
							widths: ["*"],
							body: [["Commercial Related Search Count"]],
						},
					},

					{
						style: "tableContent",
						table: {
							widths: [
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
								"*",
							],
							body: [
								[
									"Years",
									"Total",
									"Jan",
									"Feb",
									"Mar",
									"Apr",
									"May",
									"Jun",
									"Jul",
									"Aug",
									"Sep",
									"Oct",
									"Nov",
									"Dec",
								],
								[
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
								],
								[
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
									"0",
								],
							],
						},
					},
				],
				styles: {
					tableContent: {
						fontSize: 8,
						margin: [0, 0, 0, 20],
					},
					testCenter: {
						fontSize: 8,
						alignment: "center",
					},
					tableHeading: {
						fontSize: 12,
					},
					title: {
						fontSize: 12,
						margin: [0, 0, 0, 10],
					},

					header: {
						fontSize: 12,
						margin: [10, 10, 10, 10],
					},

					footer: {
						fontSize: 12,
						margin: [10, 10, 10, 10],
					},
				},
			};

			function generateEBIReport() {
				startLoadingScreen("Generating EBI Report");
				ebiDocumentDefinition.content[2].table.body[0][1] =
					ebiJsonData.input_request.search_name;
				ebiDocumentDefinition.content[2].table.body[1][1] =
					ebiJsonData.input_request.reg_no;
				ebiDocumentDefinition.content[2].table.body[2][1] = "";

				ebiDocumentDefinition.content[4].table.body[0][1] =
					ebiJsonData.business_title.name;
				ebiDocumentDefinition.content[4].table.body[1][1] =
					ebiJsonData.business_title.reg_no;

				ebiDocumentDefinition.content[6].table.body = [];

				for (let index = 0; index < ebiJsonData.addresses.length; index++) {
					const address = ebiJsonData.addresses[index];

					ebiDocumentDefinition.content[6].table.body[index] = [
						address.address,
						address.date_capture,
					];
				}

				ebiDocumentDefinition.content[8].table.body[0][1] =
					ebiJsonData.business_details.new_reg_no +
					"(" +
					ebiJsonData.business_details.registration_no +
					")";
				ebiDocumentDefinition.content[8].table.body[1][1] =
					ebiJsonData.business_details.name;
				ebiDocumentDefinition.content[8].table.body[2][1] =
					ebiJsonData.business_details.date_commenced;
				ebiDocumentDefinition.content[8].table.body[3][1] =
					ebiJsonData.business_details.date_registered;
				ebiDocumentDefinition.content[8].table.body[4][1] =
					ebiJsonData.business_details.date_closing;
				ebiDocumentDefinition.content[8].table.body[5][1] =
					ebiJsonData.business_details.date_license_expired;
				ebiDocumentDefinition.content[8].table.body[6][1] = "ACTIVE";
				ebiDocumentDefinition.content[8].table.body[7][1] =
					ebiJsonData.business_details.business_type;
				ebiDocumentDefinition.content[8].table.body[7][1] = "";
				ebiDocumentDefinition.content[8].table.body[8][1] =
					ebiJsonData.business_details.business_activity[0];
				ebiDocumentDefinition.content[8].table.body[9][1] =
					ebiJsonData.business_details.business_address;
				ebiDocumentDefinition.content[8].table.body[10][1] =
					ebiJsonData.business_details.last_updated;

				ebiDocumentDefinition.content[10].table.body = [];

				for (let index = 0; index < ebiJsonData.owner.length; index++) {
					const element = ebiJsonData.owner[index];
					ebiDocumentDefinition.content[10].table.body.push(
						["Name", element.name],
						["New IC No.", element.new_ic],
						["Gender", element.sex],
						["Address", element.address],
						["Date Of Birth / Inc.", element.birthdate],
						["Nationality", element.nationality],
						["Entry Date", element.date_entered],
						[
							"Withdrawn Date",
							element.date_withdrawed == undefined ? "" : element.date_withdrawed,
						],
						["Remark", element.remark == undefined ? "" : element.remark],
						["", ""]
					);
				}

				ebiDocumentDefinition.content[17].table.body[0][1] =
					"Total : " + ebiJsonData.legal_suit_by_regno.length;
				ebiDocumentDefinition.content[19].table.body[0][1] =
					"Total : " + ebiJsonData.legal_suit_by_plaintiff.total;

				for (
					let index = 0;
					index < ebiJsonData.previous_enquiry.finance.length;
					index++
				) {
					const element = ebiJsonData.previous_enquiry.finance[index];

					ebiDocumentDefinition.content[35].table.body[index + 1] = [
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
					];

					ebiDocumentDefinition.content[35].table.body[index + 1][0] = element.year;
					ebiDocumentDefinition.content[35].table.body[index + 1][1] =
						element.yearly_count;

					for (let y = 0; y < element.month.length; y++) {
						const childElement1 = element.month[y];
						ebiDocumentDefinition.content[35].table.body[index + 1][
							y + 2
						] = childElement1;
					}
				}

				for (
					let index = 0;
					index < ebiJsonData.previous_enquiry.commercial.length;
					index++
				) {
					const element = ebiJsonData.previous_enquiry.commercial[index];

					ebiDocumentDefinition.content[37].table.body[index + 1] = [
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
					];

					ebiDocumentDefinition.content[37].table.body[index + 1][0] = element.year;
					ebiDocumentDefinition.content[37].table.body[index + 1][1] =
						element.yearly_count;

					for (let y = 0; y < element.month.length; y++) {
						const childElement1 = element.month[y];
						ebiDocumentDefinition.content[37].table.body[index + 1][
							y + 2
						] = childElement1;
					}
				}

				console.log(ebiDocumentDefinition);

				pdfMake.createPdf(ebiDocumentDefinition).download("EBI Report.pdf");
				stopLoadingScreen();
			}
			function generateECIReports() {
				startLoadingScreen("Generating ECI Report");

				eciDocumentDefinition.content[2].table.body[0][1] =
					eciJsonData.company_info.company_name;
				eciDocumentDefinition.content[2].table.body[1][1] =
					eciJsonData.company_info.local_no;
				eciDocumentDefinition.content[2].table.body[2][1] = "";

				eciDocumentDefinition.content[4].table.body[0][1] =
					eciJsonData.company_details.company_name;

				eciDocumentDefinition.content[4].table.body[1][1] =
					eciJsonData.company_details.local_no;

				eciDocumentDefinition.content[6].table.body = [];

				for (
					let index = 0;
					index < eciJsonData.company_info.addresses.length;
					index++
				) {
					const element = eciJsonData.company_info.addresses[index];

					eciDocumentDefinition.content[6].table.body[index] = [
						element.address,
						element.date_capture,
					];
				}

				eciDocumentDefinition.content[8].table.body[0][1] =
					eciJsonData.company_details.local_no;
				eciDocumentDefinition.content[8].table.body[1][1] =
					eciJsonData.company_details.company_name;
				eciDocumentDefinition.content[8].table.body[2][1] =
					eciJsonData.company_details.incorporation_date;
				eciDocumentDefinition.content[8].table.body[3][1] =
					eciJsonData.company_details.company_type;
				eciDocumentDefinition.content[8].table.body[4][1] = "N/A";
				eciDocumentDefinition.content[8].table.body[5][1] = "N/A";
				eciDocumentDefinition.content[8].table.body[6][1] =
					eciJsonData.company_details.company_status;
				eciDocumentDefinition.content[8].table.body[7][1] = "N/A";
				eciDocumentDefinition.content[8].table.body[8][1] =
					eciJsonData.company_details.addresses.business_address;
				eciDocumentDefinition.content[8].table.body[9][1] =
					eciJsonData.company_details.addresses.registered_address;
				eciDocumentDefinition.content[8].table.body[10][1] =
					eciJsonData.company_details.total_authorised_capital;
				eciDocumentDefinition.content[8].table.body[11][1] =
					eciJsonData.company_details.business_category;
				eciDocumentDefinition.content[8].table.body[12][1] =
					eciJsonData.company_details.nature_businesses;
				eciDocumentDefinition.content[8].table.body[11][1] = "";
				eciDocumentDefinition.content[8].table.body[12][1] = "";
				eciDocumentDefinition.content[8].table.body[13][1] = "";
				eciDocumentDefinition.content[8].table.body[14][1] = "";
				eciDocumentDefinition.content[8].table.body[15][1] = "";
				eciDocumentDefinition.content[8].table.body[16][1] =
					eciJsonData.company_details.last_ramci_update;

				eciDocumentDefinition.content[10].table.body[1][1] = "";
				eciDocumentDefinition.content[10].table.body[1][2] = "";

				eciDocumentDefinition.content[10].table.body[2][1] = "";
				eciDocumentDefinition.content[10].table.body[2][2] = "";

				eciDocumentDefinition.content[10].table.body[3][1] = "";
				eciDocumentDefinition.content[10].table.body[3][2] = "";

				eciDocumentDefinition.content[12].table.body[1][0] = "";
				eciDocumentDefinition.content[12].table.body[1][1] = "";
				eciDocumentDefinition.content[12].table.body[1][2] = "";
				eciDocumentDefinition.content[12].table.body[1][3] = "";

				eciDocumentDefinition.content[12].table.body[2][0] = "";
				eciDocumentDefinition.content[12].table.body[2][1] = "";
				eciDocumentDefinition.content[12].table.body[2][2] = "";
				eciDocumentDefinition.content[12].table.body[2][3] = "";

				eciDocumentDefinition.content[12].table.body[3][0] = "";
				eciDocumentDefinition.content[12].table.body[3][1] = "";
				eciDocumentDefinition.content[12].table.body[3][2] = "";
				eciDocumentDefinition.content[12].table.body[3][3] = "";

				eciDocumentDefinition.content[14].table.body[1][0] = "";
				eciDocumentDefinition.content[14].table.body[1][1] = "";
				eciDocumentDefinition.content[14].table.body[1][2] = "";
				eciDocumentDefinition.content[14].table.body[1][3] = "";
				eciDocumentDefinition.content[14].table.body[1][4] = "";

				eciDocumentDefinition.content[14].table.body[2][0] = "";
				eciDocumentDefinition.content[14].table.body[2][1] = "";
				eciDocumentDefinition.content[14].table.body[2][2] = "";
				eciDocumentDefinition.content[14].table.body[2][3] = "";
				eciDocumentDefinition.content[14].table.body[2][4] = "";

				eciDocumentDefinition.content[14].table.body[3][0] = "";
				eciDocumentDefinition.content[14].table.body[3][1] = "";
				eciDocumentDefinition.content[14].table.body[3][2] = "";
				eciDocumentDefinition.content[14].table.body[3][3] = "";
				eciDocumentDefinition.content[14].table.body[3][4] = "";

				for (
					let index = 0;
					index < eciJsonData.previous_enquiry.finance.length;
					index++
				) {
					const element = eciJsonData.previous_enquiry.finance[index];

					eciDocumentDefinition.content[56].table.body[index + 1] = [
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
					];

					eciDocumentDefinition.content[56].table.body[index + 1][0] = element.year;
					eciDocumentDefinition.content[56].table.body[index + 1][1] =
						element.yearly_count;

					for (let y = 0; y < element.month.length; y++) {
						const childElement1 = element.month[y];
						eciDocumentDefinition.content[56].table.body[index + 1][
							y + 2
						] = childElement1;
					}
				}

				for (
					let index = 0;
					index < eciJsonData.previous_enquiry.commercial.length;
					index++
				) {
					const element = eciJsonData.previous_enquiry.commercial[index];

					eciDocumentDefinition.content[58].table.body[index + 1] = [
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
					];

					eciDocumentDefinition.content[58].table.body[index + 1][0] = element.year;
					eciDocumentDefinition.content[58].table.body[index + 1][1] =
						element.yearly_count;

					for (let y = 0; y < element.month.length; y++) {
						const childElement1 = element.month[y];
						eciDocumentDefinition.content[58].table.body[index + 1][
							y + 2
						] = childElement1;
					}
				}

				console.log(eciDocumentDefinition);

				pdfMake.createPdf(eciDocumentDefinition).download("ECI Report.pdf");

				stopLoadingScreen();
			}
			function generateCRIReport() {
				startLoadingScreen("Generating Report");
				criDocumentDefinition.content[2].table.body[0][1] =
					criJsonData.input_request.search_name;
				criDocumentDefinition.content[2].table.body[1][1] =
					criJsonData.input_request.new_ic;
				criDocumentDefinition.content[2].table.body[2][1] = "";

				criDocumentDefinition.content[4].table.body[0][1] =
					criJsonData.person_details.name;
				criDocumentDefinition.content[4].table.body[1][1] =
					criJsonData.person_details.new_ic;

				criDocumentDefinition.content[6].table.body = [];

				for (
					let index = 0;
					index < criJsonData.person_details.addresses.length;
					index++
				) {
					const address = criJsonData.person_details.addresses[index];

					criDocumentDefinition.content[6].table.body[index] = [
						address.address,
						address.datechanged,
					];
				}

				for (
					let index = 0;
					index < criJsonData.person_company_interests.length;
					index++
				) {
					const interest = criJsonData.person_company_interests[index];

					criDocumentDefinition.content[9].table.body[index + 1] = [
						index + 1,
						interest.name,
						interest.position,
						interest.incorp_date,
						"",
						interest.shares,
						interest.paid_up_capital,
						interest.remark_bafia.remark,
						interest.last_updated_date,
					];
				}

				for (
					let index = 0;
					index < criJsonData.previous_company_interests.length;
					index++
				) {
					const interest = criJsonData.previous_company_interests[index];

					criDocumentDefinition.content[11].table.body[index + 1] = [
						index + 1,
						interest.reg_no,
						interest.name,
						interest.incorporated_date,
						interest.status,
						interest.position,
						interest.cessation_date == null ? "" : interest.cessation_date,
					];
				}

				criDocumentDefinition.content[14].table.body[0][1] =
					"Total : " + criJsonData.legal_suit_by_regno.length;
				criDocumentDefinition.content[16].table.body[0][1] =
					"Total : " + criJsonData.legal_suit_by_plaintiff.length;
				criDocumentDefinition.content[18].table.body[0][1] =
					"Total : " + criJsonData.person_bankruptcy.case.length;

				for (
					let index = 0;
					index < criJsonData.previous_enquiry.finance.length;
					index++
				) {
					const element = criJsonData.previous_enquiry.finance[index];

					criDocumentDefinition.content[34].table.body[index + 1] = [
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
					];

					criDocumentDefinition.content[34].table.body[index + 1][0] = element.year;
					criDocumentDefinition.content[34].table.body[index + 1][1] =
						element.yearly_count;

					for (let y = 0; y < element.month.length; y++) {
						const childElement1 = element.month[y];
						criDocumentDefinition.content[34].table.body[index + 1][
							y + 2
						] = childElement1;
					}
				}

				for (
					let index = 0;
					index < criJsonData.previous_enquiry.commercial.length;
					index++
				) {
					const element = criJsonData.previous_enquiry.commercial[index];

					criDocumentDefinition.content[36].table.body[index + 1] = [
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
					];

					criDocumentDefinition.content[36].table.body[index + 1][0] = element.year;
					criDocumentDefinition.content[36].table.body[index + 1][1] =
						element.yearly_count;

					for (let y = 0; y < element.month.length; y++) {
						const childElement1 = element.month[y];
						criDocumentDefinition.content[36].table.body[index + 1][
							y + 2
						] = childElement1;
					}
				}

				console.log(criDocumentDefinition);
				pdfMake.createPdf(criDocumentDefinition).download("CRI Report.pdf");
				stopLoadingScreen();
			}

		</script>

		</html>
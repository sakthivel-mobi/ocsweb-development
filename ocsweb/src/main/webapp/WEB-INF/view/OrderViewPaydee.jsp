<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!doctype html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>MOBI</title>

<link rel="apple-touch-icon" sizes="57x57"
	href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60"
	href="${pageContext.request.contextPath}/resourcess/images/favicon/apple-icon-60x60.png">
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

<script src="${pageContext.request.contextPath}/resources/js/axios.js"
	type="text/javascript"></script>


<%@ page isELIgnored="false"%>
</head>

<body>


	<style>
.form label {
	color: #092540;
}

.form .form-control {
	/*background-color: #f7f7f7; */
	border-radius: 4px;
	border: solid 1px #dedede;
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

	<main class="container mt110">

		<div class="row g-5 mb-5 sticky-div">
			<div class="col-md-4 col-lg-4">
				<h5 class="c425466 fw-bold">${order.businessName }</h5>
			</div>
			<div class="col-md-8 col-lg-7 ">
				<button type="button" data-bs-toggle="modal"
					data-bs-target="#directorModal" class="btn btn-white ">Add
					Directors</button>
				<button type="button" data-bs-toggle="modal"
					data-bs-target="#notesModal" class="btn btn-white">Add
					Notes</button>
				<button id="btnMMADownload" class="btn btn-white enableForAllRoles">Download
					MMA</button>
				<button id="btnWelcomeLetterDownload"
					class="btn btn-white enableForAllRoles">Welcome Letter</button>
			</div>
		</div>


		<div class="row-fluid g-5">
			<div class="col-md-12 col-lg-12">
				<div class="bg-white p-5 rounded form">
					<h5 class="mb-3  mt-3">Business Details</h5>


					<form:form method="POST" action="" modelAttribute="order"
						id="orderForm">
						<div>
							<input type="hidden" name="quotation.stage"
								value="${order.quotation.stage }"> <input type="hidden"
								name="quotation.acquirer" value="${order.quotation.acquirer }">
						</div>

						<div class="row g-3" style="display: none;">
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="createdOn" class="form-label">Created On</form:label>
									<div class="input-group mb-3">
										<form:input id="createdOn" path="createdOn" type="text"
											disabled="true" class="form-control" />
										<span class="input-group-text"><i
											class="fa fa-calendar"></i></span>
									</div>
								</div>
							</div>

							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="userId" class="form-label">User ID</form:label>
									<div class="input-group mb-3">
										<span class="input-group-text" id="basic-addon1">60</span>
										<form:input path="userId" class="form-control" placeholder=""
											readonly="true" />
									</div>
								</div>
							</div>

							<%-- <div class="col-sm-3  form-floating mb-3">
										<div class="mb-3">
											<form:label path="merchantIdType" class="form-label">Merchant Id Type
											</form:label>
											<form:select path="merchantIdType" class="form-control"
												name="merchantIdType" id="merchantIdType">
												<form:option value="0">-----------Select-----------</form:option>
												<c:forEach var="merchantIdTypeList" items="${merchantIdTypeList}">
													<form:option value="${merchantIdTypeList.value}">
														${merchantIdTypeList.name}</form:option>
												</c:forEach>
											</form:select>
										</div>
								</div> --%>
						</div>

						<div class="row g-3">
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="businessName" class="form-label">Business Name
									</form:label>
									<form:input path="businessName" class="form-control"
										placeholder="Business Name" />
								</div>
							</div>


							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="merchantTradingName" class="form-label">Merchant Trading Name
									</form:label>
									<form:input path="merchantTradingName" class="form-control"
										placeholder="Merchant Trading Name" />
								</div>
							</div>




							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="registrationId" class="form-label">Business Registration ID/No
									</form:label>
									<form:input path="registrationId" class="form-control"
										placeholder="Business Registration ID/No" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="gstNo" class="form-label">GST No</form:label>
									<form:input path="gstNo" class="form-control"
										placeholder="GST No" />
								</div>
							</div>
						</div>

						<div class="row g-3">

							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="websiteUrl" class="form-label">Website URL</form:label>
									<form:input path="websiteUrl" class="form-control"
										placeholder="Website URL" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="officeNo" class="form-label">Office No</form:label>
									<form:input path="officeNo" class="form-control"
										placeholder="Office No" />
								</div>
							</div>

							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="officeEmail" class="form-label">Office Email</form:label>
									<form:input path="officeEmail" class="form-control"
										placeholder="Office Email" />
								</div>
							</div>

							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="bankName" class="form-label">Bank Name</form:label>
									<form:select path="bankName" class="form-select"
										name="bankName">
										<form:option value="0">-----------Select-----------</form:option>
										<c:forEach var="bank" items="${bankList}">
											<form:option value="${bank.value}">${bank.name}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>

							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="accountNo" class="form-label">Account No</form:label>
									<form:input path="accountNo" class="form-control"
										placeholder="Account No" />
								</div>
							</div>

						</div>


						<div class="h-divider"></div>

						<h5 class="mb-3  mt-3">Address</h5>
						<div class="row g-3">
							<div class="col-sm-4  form-floating mb-3">
								<div class="mb-3">
									<form:label path="addressLine" class="form-label">Address line</form:label>
									<form:input path="addressLine" class="form-control"
										placeholder="Address line" />
								</div>
							</div>


							<div class="col-sm-3  form-floating mb-3">
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
							<div class="col-sm-3  form-floating mb-3">
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
						</div>

						<div class="row g-3">
							<div class="col-sm-3  form-floating mb-3">
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
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="postCode" class="form-label">Post Code</form:label>
									<form:input path="postCode" class="form-control"
										placeholder="Post Code" />
								</div>
							</div>
						</div>



						<div class="h-divider"></div>

						<h5 class="mb-3  mt-3">Business Details</h5>
						<div class="row g-3">


							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="authContactPersonName" class="form-label">Authorized Contact Name
									</form:label>
									<form:input path="authContactPersonName" class="form-control"
										placeholder="Authorized Contact Name" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="authContactPersonPhone" class="form-label">Authorized Contact
										Phone</form:label>
									<form:input path="authContactPersonPhone" class="form-control"
										placeholder="Authorized Contact Phone" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="authContactPersonId" class="form-label">Authorized Contact ID
									</form:label>
									<form:input path="authContactPersonId" class="form-control"
										placeholder="Authorized Contact ID" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="authContactPersonNationality"
										class="form-label">Authorized
										Contact Nationality</form:label>
									<form:input path="authContactPersonNationality"
										class="form-control"
										placeholder="Authorized Contact Nationality" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="salutation" class="form-label">Salutation</form:label>
									<form:select path="salutation" class="form-select"
										name="salutation">
										<form:option value="0">-----------Select-----------</form:option>
										<c:forEach var="sal" items="${salutationList}">
											<form:option value="${sal.value}">${sal.name}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="companyType" class="form-label">Company Type</form:label>
									<form:select path="companyType" class="form-select"
										name="companyType">
										<form:option value="0">-----------Select-----------</form:option>
										<c:forEach var="type" items="${companyTypeList}">
											<form:option value="${type.value}">${type.name}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
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




							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="businessType" class="form-label">Business Type
									</form:label>
									<form:select path="businessType" class="form-select"
										name="businessType">
										<form:option value="0">-----------Select-----------</form:option>
										<c:forEach var="type" items="${businessTypeList}">
											<form:option value="${type.value}">${type.name}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="dateIncorporated" class="form-label">Date Incorporated
									</form:label>
									<div class="input-group mb-3">
										<form:input id="dateIncorporated" path="dateIncorporated"
											type="text" class="form-control datepick" />
										<span class="input-group-text"><i
											class="fa fa-calendar"></i></span>
									</div>
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="whoSigned" class="form-label">Who Signed?</form:label>
									<form:input path="whoSigned" class="form-control"
										placeholder="Name of the Person Signed?" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="whoSignedNric" class="form-label">Who Signed NRIC
									</form:label>
									<form:input path="whoSignedNric" class="form-control"
										placeholder="NRIC of the Person Signed?" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="whoSignedMobile" class="form-label">Who Signed Mobile
									</form:label>
									<form:input path="whoSignedMobile" class="form-control"
										placeholder="UserID of the Person Signed?" />
								</div>
							</div>
						</div>




						<div class="h-divider"></div>
						<h5 class="mb-3  mt-3">Business Premise Details</h5>

						<div class="row g-3">

							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="premiseType" class="form-label">Premise Type
									</form:label>
									<form:input path="premiseType" class="form-control"
										placeholder="Premise Type" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="propertyType" class="form-label">Property Type
									</form:label>
									<form:input path="propertyType" class="form-control"
										placeholder="Property Type" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="yearsOccupied" class="form-label">Years Occupied
									</form:label>
									<form:input path="yearsOccupied" class="form-control"
										placeholder="Years Occupied" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="sizeOfPremise" class="form-label">Size Of Premise
									</form:label>
									<form:input path="sizeOfPremise" class="form-control"
										placeholder="Size Of Premise" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="estimatedStockValue" class="form-label">Estimated Stock Value
									</form:label>
									<form:input path="estimatedStockValue" class="form-control"
										placeholder="Estimated Stock Value" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="noOfEmployee" class="form-label">No Of Employees
									</form:label>
									<form:input path="noOfEmployee" class="form-control"
										placeholder="No Of Employees" />
								</div>
							</div>

						</div>

						<div class="h-divider"></div>
						<h5 class="mb-3  mt-3">Transaction Details & FATCA</h5>

						<div class="row g-3">
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="noOfDailyTransaction" class="form-label">No of Daily Transaction
									</form:label>
									<form:input path="noOfDailyTransaction" class="form-control"
										placeholder="No of Daily Transaction" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="averageTransactionSize" class="form-label">Avg.Transaction Size
									</form:label>
									<form:input path="averageTransactionSize" class="form-control"
										placeholder="Avg.Transaction Size	" />
								</div>
							</div>

							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="taxIdUS" class="form-label">US Tax ID</form:label>
									<form:input path="taxIdUS" class="form-control" placeholder="" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="countryOfBirth" class="form-label">Country Of Birth
									</form:label>
									<form:input path="countryOfBirth" class="form-control"
										placeholder="Country Of Birth" />
								</div>
							</div>
							<div class="col-sm-2  form-floating mb-3">
								<div class="mb-3">
									<form:checkbox path="isUSResident" value="Yes" />
									Is US Resident
								</div>
							</div>
							<div class="col-sm-2  form-floating mb-3">
								<div class="mb-3">
									<form:checkbox path="isUSCitizen" value="Yes" />
									Is US Citizen
								</div>
							</div>
							<div class="col-sm-2  form-floating mb-3">
								<div class="mb-3">
									<form:checkbox path="isGreenCardHolder" value="Yes" />
									Is GreenCard Holder
								</div>
							</div>
						</div>


						<div class="row g-3" style="display: none;">

							<div class="row g-3">
								<div class="col-sm-3  form-floating mb-3">
									<div class="mb-3">
										<form:label path="currentAcquirer" class="form-label">Acquirer</form:label>
										<form:select path="currentAcquirer" class="form-select"
											name="currentAcquirer">
											<form:option value="0">-----------Select-----------</form:option>
											<c:forEach var="acquirer" items="${acquirerList}">
												<form:option value="${acquirer.value}">${acquirer.name}
												</form:option>
											</c:forEach>
										</form:select>
									</div>
								</div>
								<div class="col-sm-3  form-floating mb-3">
									<div class="mb-3">
										<form:label path="eCommIndustry" class="form-label">E-Commerce Industry
										</form:label>
										<form:select path="eCommIndustry" class="form-select"
											name="eCommIndustry">
											<form:option value="0">-----------Select-----------</form:option>
											<c:forEach var="ecom" items="${eCommIndustryList}">
												<form:option value="${ecom.value}">${ecom.name}</form:option>
											</c:forEach>
										</form:select>
									</div>
								</div>

								<div class="col-sm-3  form-floating mb-3">
									<div class="mb-3">
										<form:label path="noOfMPOS" class="form-label">No.Of MPOS</form:label>
										<form:input path="noOfMPOS" class="form-control"
											placeholder="" />
									</div>
								</div>

								<div class="col-sm-3  form-floating mb-3">
									<div class="mb-3">
										<form:label path="mPOSModel" class="form-label">MPOS Model</form:label>
										<form:input path="mPOSModel" class="form-control"
											placeholder="" />
									</div>
								</div>
							</div>

							<div class="row g-3">



								<div class="col-sm-3  form-floating mb-3">
									<div class="mb-3">

										<form:label path="businessStartTime" class="form-label">Business Start Time
										</form:label>
										<form:input path="businessStartTime" class="form-control"
											placeholder="" />
									</div>
								</div>

								<div class="col-sm-3  form-floating mb-3">
									<div class="mb-3">

										<form:label path="businessEndTime" class="form-label">Business End Time
										</form:label>
										<form:input path="businessEndTime" class="form-control"
											placeholder="" />
									</div>
								</div>
								<div class="col-sm-3  form-floating mb-3">
									<div class="mb-3">

										<form:label path="quotation.id" class="form-label">Quotation</form:label>
										<form:input path="quotation.id" class="form-control"
											placeholder="" readonly="true" />
									</div>
								</div>
								<div class="row g-3">

									<div class="col-sm-3  form-floating mb-3">
										<div class="mb-3">
											<form:label path="previousAcquirer" class="form-label">Previous Acquirer
											</form:label>
											<form:input path="previousAcquirer" class="form-control"
												placeholder="" />
										</div>
									</div>

									<div class="col-sm-3  form-floating mb-3">
										<div class="mb-3">
											<form:label path="previousAcquirerCeasedDate"
												class="form-label">
												Previous Acquirer Ceased Date</form:label>
											<div class="input-group mb-3">
												<form:input id="previousAcquirerCeasedDate"
													path="previousAcquirerCeasedDate" type="text"
													class="form-control datepick" />
												<span class="input-group-text"><i
													class="fa fa-calendar"></i></span>
											</div>
										</div>
									</div>

								</div>


							</div>

							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="quotation.salesPerson" class="form-label">Sales Person
									</form:label>
									<form:select class="form-select" name="salesPerson"
										path="quotation.salesPerson.id" id="salesPerson">
										<form:option value="0">-----------Select-----------</form:option>
										<c:forEach items="${salesPersons}" var="salesPersons">
											<form:option value="${salesPersons.id}">
												${salesPersons.aliasName}
											</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="accountType" class="form-label">Account Type</form:label>
									<form:select path="accountType" class="form-select"
										name="accountType">
										<form:option value="0">-----------Select-----------</form:option>
										<c:forEach var="type" items="${accountTypeList}">
											<form:option value="${type.value}">${type.name}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="grabPayLatitude" class="form-label">Latitude
									</form:label>
									<form:input path="grabPayLatitude" class="form-control"
										placeholder="" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="grabPayLongitude" class="form-label">Longitude
									</form:label>
									<form:input path="grabPayLongitude" class="form-control"
										placeholder="" />
								</div>
							</div>
						</div>


						<div class="h-divider"></div>
						<h5 class="mb-3  mt-3">To be Filled by Mobi</h5>

						<div class="row g-3">
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="requestType" class="form-label">Request Type</form:label>
									<form:input path="requestType" class="form-control"
										placeholder="" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="mdrCredit" class="form-label">MDR Credit
									</form:label>
									<form:input path="mdrCredit" class="form-control"
										placeholder="" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="mdrDebit" class="form-label">MDR Debit</form:label>
									<form:input path="mdrDebit" class="form-control" placeholder="" />
								</div>
							</div>
						</div>
						<div class="row g-3">
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="remarks" class="form-label">Remarks</form:label>
									<form:input path="remarks" class="form-control" placeholder="" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="id" class="form-label">Order ID</form:label>
									<form:input path="id" class="form-control" placeholder="" />
								</div>
							</div>

							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="quotation.payment.collectedOn"
										class="form-label">Payment Received
										Date
									</form:label>
									<div class="input-group mb-3">
										<form:input path="quotation.payment.collectedOn" type="text"
											class="form-control datepick" />
										<span class="input-group-text"><i
											class="fa fa-calendar"></i></span>
									</div>
								</div>
							</div>
						</div>

						<div class="h-divider"></div>
						<h5 class="mb-3  mt-3">To be Filled by Paydee</h5>

						<div class="row g-3" id="paydee">
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="paydeeDTL" class="form-label">DTL</form:label>
									<form:input path="paydeeDTL" id="paydee1" class="form-control"
										placeholder="" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="paydeeMID" class="form-label">MID</form:label>
									<form:input path="paydeeMID" id="paydee2" class="form-control"
										placeholder="" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="quotation.salesPerson.name"
										class="form-label">SalesPerson
									</form:label>
									<form:input path="quotation.salesPerson.name" id="paydee3"
										class="form-control" placeholder="" readonly="true" />
								</div>
							</div>
						</div>


						<div class="row g-3">
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="paydeeTID" class="form-label">TID</form:label>
									<form:input path="paydeeTID" id="paydee5" class="form-control"
										placeholder="" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="paydeeMCC" class="form-label">MCC</form:label>
									<form:input path="paydeeMCC" id="paydee6" class="form-control"
										placeholder="" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="masterMerchant" class="form-label">Master Merchant
									</form:label>
									<form:input path="masterMerchant" id="paydee4"
										class="form-control" placeholder="" readonly="true" />
								</div>
							</div>

						</div>

						<div class="col-sm-6  form-floating mb-3">
							<div class="mb-3 ">
								<label for="email" class="form-label col-12">&nbsp;</label> <input
									type="submit" value="Submit" id="btnSubmit"
									class="btn btn-mobi btn-alt float-end">
								<button id="btnUpdate" value="Update" name="Update"
									class="btn btn-mobi btn-alt float-end me-2">Update</button>
							</div>
						</div>

					</form:form>

					<h5 class="mb-3  mt-3">Directors</h5>


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
										<th>Action</th>
									</tr>
								</thead>
								<tbody class="c3f3f3f fw500 tblOrderLineBody"
									id="table-director-body">
								</tbody>
							</table>
						</div>
					</div>


					<div class="h-divider"></div>
					<h5 class="mb-3  mt-3">Signature</h5>

					<div class="mt-4 mb-4">
						<div class="row">
							<div class="col-sm-12 col-lg-2">
								<h6>${order.quotation.salesPerson.aliasName}</h6>
								<div class="signature-view">
									<img
										style="border-radius: 8px; border: .2px solid #00000021; width: 128px; height: 128px;"
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
					<div class="h-divider"></div>
					<h5 class="mb-3  mt-3">Documents</h5>

					<p>Available Document</p>
					<div id="document-available">
						<table class="table table-responsive bcf4f4f4 c617075 fw500">
							<thead>
								<tr>
									<th>File Name</th>
									<th>Category</th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody id="document-available-table-body"></tbody>
						</table>
					</div>

					<button class="btn btn-primary" onclick="addDocument()">Add
						Documents</button>
					<div id="document-upload" style="margin-top: 20px; display: none;">
						<div class="row">
							<div class="col">
								<label for="select-category" class="form-label">Select a
									file type</label> <select name="" class="form-select"
									id="select-category" placeholder="Select category">
									<option selected value="0">Select file type</option>
								</select>
							</div>
							<div class="col">
								<div class="mb-3" id="input-file-block">
									<label for="input-file-upload" class="form-label">Select
										file to upload</label> <input class="form-control" type="file"
										id="input-file-upload"
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
									class="btn btn-mobi btn-primary btn-sm"
									id="upload-all-document">Upload Documents</button></span> <span><button
									class="btn btn-sm btn-danger"
									onclick="removeAllUploadedDocuments()">Remove All
									Documents</button></span>
						</div>
					</div>
					<div class="h-divider"></div>
					<h5 class="mb-3  mt-3">Items</h5>

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

										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>



					<div>
						<br>
						<div class="h-divider"></div>
						<h5 class="mb-3  mt-3">Notes</h5>
						<div class="row g-3">
							<div class="table-responsive">
								<table class="table" id="NotesTable">
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
									<tbody class="c3f3f3f fw500 tblOrderLineBody">

										<c:forEach var="tempNotes" items="${order.notes}"
											varStatus="count">

											<tr>
												<td>${tempNotes.id}</td>
												<%-- <td>${tempNotes.createdOn}</td> --%>
												<fmt:parseDate value="${tempNotes.createdOn}"
													pattern="yyyy-MM-dd HH:mm:ss" var="myDate" />
												<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss"
														value="${myDate}" /></td>
												<td>${tempNotes.fromUser}</td>
												<td>${tempNotes.notifyTo}</td>
												<td>${tempNotes.subject}</td>
												<td>${tempNotes.notes}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>

					</div>
					<div>
						<div class="h-divider"></div>
						<h5 class="mb-3  mt-3">Stage</h5>
						<div>
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

	<!-- Director Modal -->
	<div class="modal fade" id="directorModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Add Director</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form:form action="">

						<div class="row">
							<div class="col-sm-12 col-md-6   form-floating mb-3">
								<div class="mb-3">
									<label class="form-label">ID Type</label> <select
										class="form-control" name="merchantIdType" id="idType">
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
										class="form-control" placeholder="ID No" />
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-12 col-md-6   form-floating mb-3">
								<div class="mb-3">
									<label class="form-label">Name</label> <input id="name"
										class="form-control" placeholder="Director Name" />
								</div>
							</div>

							<div class="col-sm-12 col-md-6  form-floating mb-3">
								<div class="mb-3">
									<label class="form-label">Designation</label> <input
										id="designation" class="form-control"
										placeholder="Designation" />
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
									<label class="form-label">Contact No</label> <input
										id="contactNo" class="form-control" placeholder="Contact No" />
								</div>
							</div>

							<div class="col-sm-12 col-md-6 form-floating mb-3">
								<div class="col-sm-12  form-floating mb-3">
									<p class="form-label">Nationality</p>
									<div class="input-group mb-3">
										<select class="form-control" name="nationality"
											id="nationality">
											<option value="MALAYSIA">MALAYSIA</option>
											<option value="-1">-----------Select-----------</option>
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
									<textarea rows="2" cols="40" id="address" class="form-control"
										placeholder="Address"></textarea>
								</div>
							</div>
						</div>

					</form:form>
				</div>
				<div>
					<hr>
					<div class="d-flex bd-highlight align-items-center p-4"
						style="background-color: #f7f7f7;">
						<div class="flex-grow-1" id="director-current-date"></div>
						<button type="button" class="btn btn-secondary btn-sm m-2"
							data-bs-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary btn-sm saveDirector">Add
							Director</button>
					</div>
				</div>

			</div>
		</div>
	</div>
	<!--Notes Modal -->
	<div class="modal fade" id="notesModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-body">
					<div class="d-flex flex-row bd-highlight align-self-center">
						<div class="flex-grow-1 bd-highlight">
							<h5 class="modal-title" id="exampleModalLabel">Add Notes</h5>
						</div>
						<div>
							<button type="button" class="btn btn-close"
								data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
					</div>
					<form:form action="" class="mt-5">

						<div class="col  form-floating mb-3">
							<div class="mb-3">
								<label class="form-label">Notify To</label> <input id="notifyTo"
									class="form-control" placeholder="Notify To" />
							</div>
						</div>

						<div class="col  form-floating mb-3">
							<div class="mb-3">
								<label class="form-label">Subject</label> <input id="subject"
									class="form-control" placeholder="Subject" />
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

				<div class="d-flex bd-highlight align-items-center p-4"
					style="background-color: #f7f7f7;">
					<div class="flex-grow-1 bd-highlight">
						<p style="margin: 0px;" id="notes-current-date"></p>
					</div>
					<div class="bd-highlight m-2">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
					</div>
					<div class="bd-highlight m-2">
						<button type="button" class="btn btn-primary saveNotes">Add
							Notes</button>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- Modal -->
	<div class="modal fade" id="paymentConfirmationModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="ModalLabel">Verify Payment</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<label>New Order has been Created!</label>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">No</button>

					<button type="button" class="btn btn-primary"
						href="/Quotation/QuotationView?id=${quotation.id}">
						<a href="QuotationView?id=${quotation.id}&" />Ok
					</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
			var contextPath = "${pageContext.request.contextPath}"
			var orderId = ${ order.id }
			var isQuotationAccepted = ${ order.quotation.quotationAccepted }

			var token = $("meta[name='_csrf']").attr("content")
			var header = $("meta[name='_csrf_header']").attr("content")

			var uploadedFile = []
			document.getElementById('notesModal').addEventListener('show.bs.modal', function (event) {
				document.getElementById("notes-current-date").innerText = moment().format('MMMM DD YYYY, h:mm A');
			})

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


			var stage = "${order.quotation.stage }";
			var role = "${loggedUserRole }";

			window.onload = function () {
				getAvailableDocuments()
				initialFunction()
				getOrderDirector()

				//Restrict non-stage user to edit the form
				var stage = "${order.quotation.stage }";
				var role = "${loggedUserRole }";
				console.log(stage);
				console.log(role);
				console.log(stage != role)


				if (stage != role) {
					console.log("Role Check");
					$('input').attr('readonly', true);
					$('select').attr('readonly', true);

					$('button').attr('disabled', true);
					$('.btn').attr('disabled', true);


				} else if (stage.includes("synergy")) {
					console.log("Inside Synergy Condition");
					//	paydeediv enabled
					$('input').attr('readonly', true)
					$('select').attr('readonly', true)

					$('button').attr('disabled', false);
					$('.btn').attr('disabled', false);

					setTimeout(() => {
						document.getElementById("paydee1").removeAttribute("readonly")
						document.getElementById("paydee2").removeAttribute("readonly")
						//document.getElementById("paydee3").removeAttribute("readonly")
						//document.getElementById("paydee4").removeAttribute("readonly")
						document.getElementById("paydee5").removeAttribute("readonly")
						document.getElementById("paydee6").removeAttribute("readonly")
					}, 2000);
				}


				console.log("isQuotationAccepted -> " + isQuotationAccepted)

				if (parseInt(isQuotationAccepted) === 1) {
					// Show merchant Signature
					var signatureId = ${ order.quotation.quotationAcceptance.signature.id }
					console.log("signatureId >> " + signatureId)
					getSignature(signatureId)
				} else {
					console.log("Quotation not accepted")
				}
				var salesSignatureId = ${order.quotation.salesPerson.signature.id}
				console.log("Sales signatureId >> " + salesSignatureId)
					getSignatureByUserName(salesSignatureId)


				if (stage != role) {
					$('input').attr('readonly', 'readonly');
					$('select').attr('disabled', true);
					$('button').attr('disabled', true);
					$('.btn').attr('disabled', true);
				}
				else if (role !== 'risk') {
					console.log('In Risk test');
					$('.dtlInfo input').attr("disabled", true);
				}

				$('.enableForAllRoles').attr('disabled', false);

				if ((stage != 'pending-deployment') || (stage != 'deployed')) {
					$('#btnWelcomeLetterDownload').attr("disabled", true);
				}
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


			function loadOrderDirector(data) {
				const tableDirectorBody = document.getElementById("table-director-body")
				tableDirectorBody.innerHTML = ""
				let count = 1

				for (var item of data) {
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

					const deleteIcon = document.createElement("img")
					deleteIcon.src = contextPath + "/resources/images/delete_icon.svg"
					deleteIcon.width = "15"

					const deleteButton = document.createElement("button")
					deleteButton.className = "btn btn-danger btn-sm"
					deleteButton.appendChild(deleteIcon)
					deleteButton.value = item.id
					deleteButton.setAttribute("onclick", "deleteOrderDirector(" + item.id + ")");
					action.appendChild(deleteButton)

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

			$(document).ready(function () {
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

				$(function () {
					$(".datepick").datepicker({
						dateFormat: 'dd-mm-yy'
					});
				});

				$('.saveDirector').click(function (e) {
					e.preventDefault();
					$.post("saveDirector",
						{
							name: $('#name').val(),
							designation: $('#designation').val(),
							idType: $('#idType').val(),
							idNo: $('#idNo').val(),
							contactNo: $('#contactNo').val(),
							nationality: $('#nationality').val(),
							address: $('#address').val(),
							orderId: $('#id').val()

						},

						function (data, status) {

							$('#directorModal').modal(
								{
									backdrop: 'static',
									keyboard: false
								});
							$('#directorModal')
								.modal('hide');
							location.reload();

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
							alert("MMA Downloaded Successfully!");
						},
						error: function (result) {
							alert('error');
						}
					});
				});
			});


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
			$('#btnUpdate').click(function () {
				$('#orderForm').attr('action', 'updateOrder');
			});

			$('#btnSubmit').click(function () {
				$('#orderForm').attr('action', 'submitOrder');
			});

			$(function () {
				$(".datepick").datepicker();
			});

			/* $('.saveDirector').click(function (e) {
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

				},

					function (data, status) {

						$('#directorModal').modal({
							backdrop: 'static',
							keyboard: false
						});
						$('#directorModal').modal('hide');
						location.reload();

					});

			});
 */
			/* 			$('.saveNotes').click(function (e) {
							e.preventDefault();
							$.post("saveNotes",
								{
									notifyTo: $('#notifyTo').val(),
									subject: $('#subject').val(),
									notes: $('#notes').val(),
									orderId: $('#id').val()
								},
			
								function (data, status) {
			
									$('#notesModal').modal(
										{
											backdrop: 'static',
											keyboard: false
										});
									// $('#notesModal')
									// 	.modal('hide');
									// location.reload();
			
								});
						}); */

			$('.saveNotes').click(function (e) {
				e.preventDefault();
				$.post("saveNotes", {
					notifyTo: $('#notifyTo').val(),
					subject: $('#subject').val(),
					notes: $('#notes').val(),
					companyName: $('#merchantTradingName').val(),
					orderId: $('#id').val()

				},

					function (data, status) {

						$('#notesModal').modal({
							backdrop: 'static',
							keyboard: false
						});
						$('#notesModal').modal('hide');
						location.reload();
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

			function removeSelectedDocument(value) {

			}

			document.getElementById("upload-all-document").addEventListener("click", function () {
				uploadUserDocuments()
			})

			// document.getElementById("document-action-remove").addEventListener("click", function () {
			// 	alert(event.target.value)
			// })

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

						console.log(response)
					})
					.catch(error => {
						console.log('error -> ', error)
					})
					.finally(() => {

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
						const buttonAction = document.createElement('td')

						const deleteIcon = document.createElement("img")
						deleteIcon.src = contextPath + "/resources/images/delete_icon.svg"
						deleteIcon.style.width = "15"

						const deleteButton = document.createElement("button")
						deleteButton.classList = "btn btn-danger btn-sm"
						// deleteButton.innerHTML = "Remove"
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
						action.className = "btn btn-primary btn-sm"
						action.type = "button"
						// action.innerHTML = "View Document"
						action.value = contextPath + documentFile.documentData
						action.id = "document-action-view"
						action.appendChild(viewIcon)
						action.addEventListener('click', () => {
							window.open(contextPath + documentFile.documentData)
						}, false)

						documentCategory.innerHTML = documentFile.documentCategory === null ? "Not Available" : documentFile.documentCategory
						documentName.innerHTML = documentFile.documentName
						documentStatus.innerHTML = documentFile.status === 0 ? 'Not Approved' : 'Approved'

						tableRow.appendChild(documentName)
						tableRow.appendChild(documentCategory)
						buttonAction.appendChild(action)
						buttonAction.appendChild(deleteButton)
						tableRow.appendChild(buttonAction)

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
					const action = document.createElement('button')
					action.className = "btn btn-sm btn-outline-danger"
					action.type = "button"
					action.innerHTML = "Remove"
					action.value = tableBodyIndex
					action.id = "document-action-remove"
					action.addEventListener('click', () => {
						console.log("selectedFiles --> " + JSON.stringify(files[tableBodyIndex]))
						files.splice(tableBodyIndex, 1);
						tableRow.remove()
					}, false)

					sNo.innerHTML = tableBodyIndex + 1
					documentName.innerHTML = file.name
					documentSize.innerHTML = formatFileSize(file.size)

					tableRow.appendChild(sNo)
					tableRow.appendChild(documentName)
					tableRow.appendChild(documentSize)
					tableRow.appendChild(action)

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
		</script>

</html>
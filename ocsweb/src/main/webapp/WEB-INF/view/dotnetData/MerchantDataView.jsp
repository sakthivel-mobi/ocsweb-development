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
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/fonts/fonts.css">


			<meta id="_csrf" name="_csrf" content="${_csrf.token}" />
			<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}" />


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


			<%@ page isELIgnored="false" %>
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
						<button type="button" data-bs-toggle="modal" data-bs-target="#directorModal"
							class="btn btn-white ">Add
							Directors</button>
						<button type="button" data-bs-toggle="modal" data-bs-target="#notesModal"
							class="btn btn-white">Add
							Notes</button>
						<button id="btnMMADownload" class="btn btn-white enableForAllRoles"
							onclick="viewDocument('${schedule.FILE_NAME}', '${schedule.fileType}', '${schedule.userName}')">View
							MMA</button>
						<button id="btnWelcomeLetterDownload" class="btn btn-white enableForAllRoles"
							onclick="viewDocument('${welcomeLetter.FILE_NAME}', '${welcomeLetter.fileType}', '${welcomeLetter.userName}')">Welcome
							Letter</button>
					</div>
				</div>

				<div class="row g-5 bg-white">
					<div class="col-md-12 col-lg-12">
						<div class="p-5 rounded form">
							<h5 class="mb-3  mt-3">Business Details</h5>


							<form:form method="POST" action="" id="orderForm" modelAttribute="merchantDetail">

								<div class="row g-3">
									<div class="col-sm-3  form-floating mb-3">
										<div class="mb-3">
											<form:label path="orderId" class="form-label">Order ID</form:label>
											<form:input path="orderId" class="form-control" placeholder="Order ID"
												readonly="true" />
										</div>
									</div>

									<div class="col-sm-3 offset-sm-1  form-floating mb-3">
										<div class="mb-3">
											<form:label path="createdOn" class="form-label">Created On</form:label>
											<div class="input-group mb-3">
												<form:input id="createdOn" path="createdOn" type="text" disabled="true"
													class="form-control" />
												<span class="input-group-text"><i class="fa fa-calendar"></i></span>
											</div>
										</div>
									</div>

									<div class="col-sm-3 offset-sm-1  form-floating mb-3">
										<div class="mb-3">

											<form:label path="userId" class="form-label">User ID</form:label>
											<div class="input-group mb-3">
												<span class="input-group-text" id="basic-addon1">60</span>
												<form:input path="userId" class="form-control" placeholder="User ID"
													readonly="true" />
											</div>

										</div>
									</div>
								</div>
								<div class="row g-3">

									<div class="col-sm-3  form-floating mb-3">
										<div class="mb-3">
											<form:label path="uMIDType" class="form-label">Merchant ID Type
											</form:label>
											<form:input path="uMIDType" class="form-control"
												placeholder="Merchant ID Type" />
										</div>
									</div>


									<div class="col-sm-3 offset-sm-1  form-floating mb-3">
										<div class="mb-3">
											<form:label path="businessName" class="form-label">Business Name
											</form:label>
											<form:input path="businessName" class="form-control"
												placeholder="Business Name" />
										</div>
									</div>


									<div class="col-sm-3 offset-sm-1  form-floating mb-3">
										<div class="mb-3">

											<form:label path="RegisteredName" class="form-label">Merchant Trading
												Name</form:label>
											<form:input path="RegisteredName" class="form-control"
												placeholder="Merchant Trading Name" />
										</div>
									</div>
								</div>
								<div class="row g-3">

									<div class="col-sm-3  form-floating mb-3">
										<div class="mb-3">
											<form:label path="NOB" class="form-label">Nature Of Business
											</form:label>
											<form:input path="NOB" class="form-control"
												placeholder="Nature Of Business" />
										</div>
									</div>

									<div class="col-sm-3 offset-sm-1  form-floating mb-3">
										<div class="mb-3">

											<form:label path="RegistrationNumber" class="form-label">Business
												Registration
												ID/No</form:label>
											<form:input path="RegistrationNumber" class="form-control"
												placeholder="Business Registration ID/No" />
										</div>
									</div>


									<div class="col-sm-3  offset-sm-1 form-floating mb-3">
										<div class="mb-3">
											<form:label path="companyType" class="form-label">Company Type</form:label>
											<form:input path="companyType" class="form-control"
												placeholder="Company Type" />
										</div>
									</div>
								</div>
								<div class="row g-3">

									<div class="col-sm-3  form-floating mb-3">
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


									<%-- <div class="col-sm-3  form-floating mb-3">
										<div class="mb-3">
											<form:label path="masterCardMCC" class="form-label">Mastercard MCC
											</form:label>
											<form:select path="masterCardMCC" class="form-select" name="masterCardMCC">
												<form:option value="0">-----------Select-----------</form:option>
												<c:forEach var="mcc" items="${umobileMCCList}">
													<form:option value="${mcc.value}">${mcc.value} - ${mcc.name}
													</form:option>
												</c:forEach>
											</form:select>
										</div>
								</div> --%>


								<div class="col-sm-3 offset-sm-1  form-floating mb-3">
									<div class="mb-3">
										<form:label path="UMVisaMCC" class="form-label">MCC</form:label>
										<form:input path="UMVisaMCC" class="form-control" placeholder="MCC" />
									</div>
								</div>

								<div class="col-sm-3 offset-sm-1 form-floating mb-3">
									<div class="mb-3">
										<form:label path="CompanyType" class="form-label">Business Type
										</form:label>
										<form:input path="CompanyType" class="form-control"
											placeholder="Business Type" />
									</div>
								</div>

						</div>
						<div class="row g-3">

							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="UMobileEcommerceIndustry" class="form-label">E-Commerce Industry
									</form:label>
									<form:input path="UMobileEcommerceIndustry" class="form-control"
										placeholder="E-Commerce Industry" />
								</div>
							</div>

							<div class="col-sm-3 offset-sm-1 form-floating mb-3">
								<div class="mb-3">
									<form:label path="UMNumberOfMPOS" class="form-label">No. of MPOS</form:label>
									<form:input path="UMNumberOfMPOS" class="form-control" placeholder="No. of MPOS" />
								</div>
							</div>

							<div class="col-sm-3 offset-sm-1 form-floating mb-3">
								<div class="mb-3">
									<form:label path="UMMPOSModel" class="form-label">MPOS Model</form:label>
									<form:input path="UMMPOSModel" class="form-control" placeholder="MPOS Model" />
								</div>
							</div>
						</div>
						<div class="row g-3">

							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="UMBusinessStartTime" class="form-label">Business Start Time
									</form:label>
									<form:input path="UMBusinessStartTime" class="form-control"
										placeholder="Business Start Time" />
								</div>
							</div>

							<div class="col-sm-3 offset-sm-1 form-floating mb-3">
								<div class="mb-3">

									<form:label path="UMBusinessEndTime" class="form-label">Business End Time
									</form:label>
									<form:input path="UMBusinessEndTime" class="form-control"
										placeholder="Business End Time" />
								</div>
							</div>
							<div class="col-sm-3 offset-sm-1 form-floating mb-3">
								<div class="mb-3">
									<form:label path="Website" class="form-label">Website URL</form:label>
									<form:input path="Website" class="form-control" placeholder="Website URL" />
								</div>
							</div>

						</div>

						<div class="row g-3">
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="UMPreviousAcquirer" class="form-label">Previous Acquirer
									</form:label>
									<form:input path="UMPreviousAcquirer" class="form-control" placeholder="" />
								</div>
							</div>

							<div class="col-sm-3 offset-sm-1 form-floating mb-3">
								<div class="mb-3">
									<form:label path="UMPreviousAcquirerCeasedDate" class="form-label">
										Previous Acquirer Ceased Date</form:label>
									<div class="input-group mb-3">
										<form:input id="UMPreviousAcquirerCeasedDate"
											path="UMPreviousAcquirerCeasedDate" type="text"
											class="form-control datepick" />
										<span class="input-group-text"><i class="fa fa-calendar"></i></span>
									</div>
								</div>
							</div>
						</div>



						<div class="h-divider"></div>

						<h5 class="mb-3  mt-3">Address</h5>
						<div class="row g-3">
							<div class="col-sm-4  form-floating mb-3">
								<div class="mb-3">
									<form:label path="BusinessAddress" class="form-label">Address line</form:label>
									<form:textarea cols="5" rows="2" path="BusinessAddress" class="form-control"
										placeholder="Address line" />
								</div>
							</div>

						</div>

						<div class="row g-3">
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="BusinessAddrState" class="form-label">State</form:label>
									<form:input path="BusinessAddrState" class="form-control" placeholder="State" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="BusinessAddrCity" class="form-label">City</form:label>
									<form:input path="BusinessAddrCity" class="form-control" placeholder="City" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="UMobileCountry" class="form-label">Country</form:label>
									<form:input path="UMobileCountry" class="form-control" placeholder="Country" />
								</div>
							</div>
							<div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">
									<form:label path="BusinessAddrZip" class="form-label">Post Code</form:label>
									<form:input path="BusinessAddrZip" class="form-control" placeholder="Post Code" />
								</div>
							</div>
						</div>
						<!-- </div>
						</div> -->

						<div class="h-divider"></div>

						<h5 class="mb-3  mt-3">Other Information</h5>
						<div class="row g-3">
							<%-- <div class="col-sm-3  form-floating mb-3">
								<div class="mb-3">

									<form:label path="salutation" class="form-label">Salutation</form:label>
									<form:input path="salutation" class="form-control" placeholder="Salutation" />
								</div>
						</div> --%>
						<div class="col-sm-3 form-floating mb-3">
							<div class="mb-3">
								<form:label path="AuthContactPersonName" class="form-label">Authorized Contact
								</form:label>
								<form:input path="AuthContactPersonName" class="form-control"
									placeholder="Authorized 	Contact" />
							</div>
						</div>
						<div class="col-sm-3 offset-sm-1 form-floating mb-3">
							<div class="mb-3">

								<form:label path="AuthContactPersonNumber" class="form-label">Authorized Contact Phone
								</form:label>
								<form:input path="AuthContactPersonNumber" class="form-control"
									placeholder="Authorized Contact Phone" />
							</div>
						</div>

					</div>
					<div class="row g-3">
						<div class="col-sm-3  form-floating mb-3">
							<div class="mb-3">

								<form:label path="AuthContactPersonID" class="form-label">Authorized Contact NRIC
								</form:label>
								<form:input path="AuthContactPersonID" class="form-control"
									placeholder="Authorized Contact NRIC" />
							</div>
						</div>


						<div class="col-sm-3 offset-sm-1 form-floating mb-3">
							<div class="mb-3">
								<form:label path="OfficeNo" class="form-label">Office No</form:label>
								<form:input path="OfficeNo" class="form-control" placeholder="Office No" />
							</div>
						</div>
						<div class="col-sm-3 offset-sm-1 form-floating mb-3">
							<div class="mb-3">
								<form:label path="Email" class="form-label">Office Email</form:label>
								<form:input path="Email" class="form-control" placeholder="Office Email" />
							</div>
						</div>


					</div>
					<div class="row g-3">

						<div class="col-sm-3 form-floating mb-3">
							<div class="mb-3">
								<form:label path="accountType" class="form-label">Account Type</form:label>
								<form:input path="accountType" class="form-control" placeholder="Account Type" />
							</div>
						</div>

						<div class="col-sm-3 offset-sm-1 form-floating mb-3">
							<div class="mb-3">
								<form:label path="bankName" class="form-label">Bank Name</form:label>
								<form:input path="bankName" class="form-control" placeholder="Bank Name" />

							</div>
						</div>

						<div class="col-sm-3 offset-sm-1 form-floating mb-3">
							<div class="mb-3">
								<form:label path="AccountNumber" class="form-label">Account No</form:label>
								<form:input path="AccountNumber" class="form-control" placeholder="Account No" />
							</div>
						</div>
					</div>
					<div class="row g-3">



						<div class="col-sm-3  form-floating mb-3">
							<div class="mb-3">
								<form:label path="SizeOfPremise" class="form-label">Size Of Premise
								</form:label>
								<form:input path="SizeOfPremise" class="form-control" placeholder="Size Of Premise" />
							</div>
						</div>
						<div class="col-sm-3 offset-sm-1 form-floating mb-3">
							<div class="mb-3">

								<form:label path="noOfEmployee" class="form-label">No Of Employees
								</form:label>
								<form:input path="noOfEmployee" class="form-control"
									placeholder="Employees in Organization" />
							</div>
						</div>


						<div class="col-sm-3 offset-sm-1 form-floating mb-3">
							<div class="mb-3">

								<form:label path="estimatedStockValue" class="form-label">Estimated Stock Value
								</form:label>
								<form:input path="estimatedStockValue" class="form-control"
									placeholder="Estimated Stock Value" />
							</div>
						</div>


					</div>
					<div class="row g-3">


						<div class="col-sm-3  form-floating mb-3">
							<div class="mb-3">
								<form:label path="NoOfDailyTxn" class="form-label">No of Daily Transaction</form:label>
								<form:input path="NoOfDailyTxn" class="form-control"
									placeholder="No of Daily Transaction" />
							</div>
						</div>
						<div class="col-sm-3 offset-sm-1 form-floating mb-3">
							<div class="mb-3">

								<form:label path="AvgTxnSize" class="form-label">
									Avg.Transaction Size</form:label>
								<form:input path="AvgTxnSize" class="form-control" placeholder="Avg.Transaction Size" />
							</div>
						</div>

						<div class="col-sm-3 offset-sm-1 form-floating mb-3">
							<div class="mb-3">

								<form:label path="remarks" class="form-label">Remarks</form:label>
								<form:input path="remarks" class="form-control" placeholder="Remarks" />
							</div>
						</div>

					</div>
					<div class="row g-3">
						<div class="col-sm-3  form-floating mb-3">
							<div class="mb-3">

								<form:label path="USTaxID" class="form-label">US Tax ID</form:label>
								<form:input path="USTaxID" class="form-control" placeholder="US Tax ID" />
							</div>
						</div>



						<div class="col-sm-3 offset-sm-1 form-floating mb-3">
							<div class="mb-3">

								<form:label path="MasterMerchant" class="form-label">Master Merchant
								</form:label>
								<form:input path="MasterMerchant" class="form-control" placeholder="Master Merchant" />
							</div>
						</div>
						<div class="col-sm-3 offset-sm-1 form-floating mb-3">
							<div class="mb-3">
								<form:label path="salesPersonId" class="form-label">Sales Person
								</form:label>
								<form:input path="salesPersonId" class="form-control" placeholder="Sales Person" />
							</div>
						</div>
					</div>
					<div class="row g-3">


						<div class="col-sm-3 form-floating mb-3">
							<div class="mb-3">

								<form:label path="GrabPayLatitude" class="form-label">Latitude
								</form:label>
								<form:input path="GrabPayLatitude" class="form-control" placeholder="Latitude" />
							</div>
						</div>
						<div class="col-sm-3 offset-sm-1 form-floating mb-3">
							<div class="mb-3">

								<form:label path="GrabPayLongitude" class="form-label">Longitude
								</form:label>
								<form:input path="GrabPayLongitude" class="form-control" placeholder="Longitude" />
							</div>
						</div>
						<div class="col-sm-3 offset-sm-1 form-floating mb-3">
							<div class="mb-3">

								<form:label path="CountryOfBirth" class="form-label">Country Of Birth
								</form:label>
								<form:input path="CountryOfBirth" class="form-control" placeholder="" />
							</div>
						</div>
					</div>
					<%-- <div class="row g-3">
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
				</div> --%>

				<div class="h-divider"></div>
				<h5 class="mb-3  mt-3 ">DTL Information</h5>

				<div class="row g-3 dtlInfo">
					<div class="col-sm-3  form-floating mb-3">
						<div class="mb-3">

							<form:label path="RequestedDTL" class="form-label">Requested DTL
							</form:label>
							<form:input path="RequestedDTL" class="form-control" placeholder="" />
						</div>
					</div>
					<div class="col-sm-3  form-floating mb-3">
						<div class="mb-3">

							<form:label path="RiskApprovedDTL" class="form-label">Risk Approved DTL
							</form:label>
							<form:input path="RiskApprovedDTL" class="form-control" placeholder="" />
						</div>
					</div>
					<div class="col-sm-3  form-floating mb-3">
						<div class="mb-3">

							<form:label path="RiskReason" class="form-label">Risk Reason
							</form:label>
							<form:input path="RiskReason" class="form-control" placeholder="" />
						</div>
					</div>
				</div>
				<div class="row g-3 dtlInfo">
					<div class="col-sm-3  form-floating mb-3">
						<div class="mb-3">

							<form:label path="HighRiskMark" class="form-label">High Risk Mark
							</form:label>
							<form:input path="HighRiskMark" class="form-control" placeholder="" />
						</div>
					</div>
					<div class="col-sm-3  form-floating mb-3">
						<div class="mb-3">

							<form:label path="highRiskStatus" class="form-label">High Risk Status
							</form:label>
							<form:input path="highRiskStatus" class="form-control" placeholder="" />
						</div>
					</div>
					<div class="col-sm-3  form-floating mb-3">
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

				<!-- <div class="col-sm-6  form-floating mb-3">
							<div class="mb-3 ">
								<label for="email" class="form-label col-12">&nbsp;</label> <input
									type="submit" value="Submit" id="btnSubmit"
									class="btn btn-mobi btn-alt float-end">
								<button id="btnUpdate" value="Update" name="Update"
									class="btn btn-mobi btn-alt float-end me-2">Update</button>
							</div>
						</div> -->
				</form:form>

				<div class="h-divider"></div>
				<h5 class="mb-3  mt-3">Directors</h5>

				<div class="row g-3">
					<div class="table-responsive">
						<table class="table" id="DirectorTable">
							<thead>
								<tr class="bcf4f4f4 c617075 fw500">
									<th>Name</th>
									<th>NRIC</th>
									<th>UserId</th>
									<th>Designation</th>
									<th>Address</th>
								</tr>
							</thead>
							<tbody class="c3f3f3f fw500 tblOrderLineBody">

								<c:forEach var="director" items="${directors}" varStatus="count">
									<tr>
										<td>${director.dirName}</td>
										<td>${director.dirNric}</td>
										<td>${director.userAppl}</td>
										<td>${director.dirDesign}</td>
										<td>${director.dirAddress}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>





				<div class="h-divider"></div>
				<h5 class="mb-3  mt-3">Documents</h5>

				<p>Available Document</p>
				<div id="document-available">
					<table class="table table-responsive bcf4f4f4 c617075 fw500">
						<thead>
							<tr>
								<th>ID</th>
								<th>File Name</th>
								<th>Category</th>
								<th>View</th>
							</tr>
						</thead>
						<tbody class="c3f3f3f fw500 tblOrderLineBody">

							<c:forEach var="document" items="${documents}" varStatus="count">

								<tr>
									<td>${document.ID}</td>
									<td>${document.FILE_NAME}</td>
									<td>${document.FILE_CATEGORY}</td>
									<td><button class="btn btn-sm btn-primary"
											onclick="viewDocument('${document.FILE_NAME}', '${document.fileType}', '${merchantDetail.orderId}')">View</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="h-divider"></div>
				<h5 class="mb-3  mt-3">Items</h5>
				<div class="row g-3">
					<div class="table-responsive">
						<table class="table" id="OrderLineTable">
							<thead>
								<tr class="bcf4f4f4 c617075 fw500">
									<th>Line ID</th>
									<th>Product</th>
									<th>Description</th>
									<th>Quantity</th>
									<th>Amount (RM)</th>
								</tr>
							</thead>
							<tbody class="c3f3f3f fw500 tblOrderLineBody">
								<c:forEach var="item" items="${oldOrderDetails}">
									<tr>
										<td>${item.orderDetailId}</td>
										<td>${item.productId.productName}</td>
										<td>${item.productId.description}</td>
										<td>${item.quantity}</td>
										<td>${item.unitPrice}</td>
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
								<tbody class="c3f3f3f fw500 tblOrderLineBody" id="table-notes-body">
									<c:forEach var="item" items="${oldNotesDetails}">
										<td>${item.id}</td>
										<td>${item.senddate}</td>
										<td>${item.from}</td>
										<td>${item.toMail}</td>
										<td>${item.subject}</td>
										<td>${item.mailBody}</td>
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
									<th>Date</th>
									<th>User</th>
									<th>Submitted By</th>
									<th>Role</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody class="c3f3f3f fw500 tblOrderLineBody">

								<c:forEach var="stageMovement" items="${stageMovement}" varStatus="count">

									<tr>
										<td>${stageMovement.date}</td>
										<td>${stageMovement.merchantID}</td>
										<td>${stageMovement.userId}</td>
										<td>${stageMovement.roleId}</td>
										<td>${stageMovement.status}</td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				</div>
				</div>
				</div>

			</main>



			<!-- Director Modal -->
			<div class="modal fade" id="directorModal" tabindex="-1" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Add Director</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<form:form action="">

								<div class="row">
									<div class="col-sm-12 col-md-6   form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">ID Type</label> <select class="form-control"
												name="merchantIdType" id="merchantIdType">
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
											<label class="form-label">Name</label> <input id="name" class="form-control"
												placeholder="Director Name" />
										</div>
									</div>

									<div class="col-sm-12 col-md-6  form-floating mb-3">
										<div class="mb-3">
											<label class="form-label">Designation</label> <input id="designation"
												class="form-control" placeholder="Designation" />
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
												class="form-control" placeholder="Contact No" />
										</div>
									</div>

									<div class="col-sm-12 col-md-6 form-floating mb-3">
										<div class="col-sm-12  form-floating mb-3">
											<p class="form-label">Nationality</p>
											<div class="input-group mb-3">
												<select class="form-select" name="nationality" id="nationality">
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
									<button type="button" class="btn btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
							</div>
							<form:form action="" class="mt-5">

								<div class="col  form-floating mb-3">
									<div class="mb-3">
										<label class="form-label">Notify To</label> <input id="notifyTo"
											class="form-control" placeholder="Notify To" />
									</div>
									<p>
										To notify multiple users please separate email id with <b>(
											&comma; ) (Comma)</b>
									</p>
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

						<div class="d-flex bd-highlight align-items-center p-4" style="background-color: #f7f7f7;">
							<div class="flex-grow-1 bd-highlight">
								<p style="margin: 0px;" id="notes-current-date"></p>
							</div>
							<div class="bd-highlight m-2">
								<button type="button" class="btn btn-secondary btn-sm"
									data-bs-dismiss="modal">Close</button>
							</div>
							<div class="bd-highlight m-2">
								<button type="button" class="btn btn-primary btn-sm saveNotes">Add
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
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<label>New Order has been Created!</label>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>

							<button type="button" class="btn btn-primary"
								href="/Quotation/QuotationView?id=${quotation.id}">
								<a href="QuotationView?id=${quotation.id}&" />Ok
							</button>
						</div>
					</div>
				</div>
			</div>
		</body>

		<script>
			var contextPath = "${pageContext.request.contextPath}"

			function viewDocument(fileName, fileType, pathId) {
				console.log("fileType >> " + fileType)
				const url = contextPath + "/documents/old/files" + fileType + "/" + pathId + "/" + fileName
				window.open(url)
			}

			const quotationFileList = "${quotationFileList}"
			window.onload = function () {
				console.log("quotationFileList >> " + quotationFileList)
			}

		</script>

		</html>
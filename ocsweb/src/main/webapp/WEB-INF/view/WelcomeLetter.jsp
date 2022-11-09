<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<!DOCTYPE html>
		<html>

		<head>
			<meta charset="ISO-8859-1">
			<title>Welcome Letter</title>

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

			<!-- CDN moved to local -->
			<link rel="stylesheet" type="text/css"
				href="${pageContext.request.contextPath}/resources/js/fontawesome/css/fontawesome.min.css">
			<script src="${pageContext.request.contextPath}/resources/js/axios.js" type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/resources/js/moment.js" type="text/javascript"></script>
			<!-- CDN moved to local -->

			<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/loading.js"></script>

			<%@ page isELIgnored="false" %>
		</head>

		<body id="welcome-letter-body">
			<div id="desktop-view">
				<div class="p-4">
					<div class="">
						<div>Welcome Letter</div>
						<div>Document Reference: (Mobi /
							U0${orderId})</div>
					</div>
				</div>

				<div class="welcome-letter">
					<div class="d-flex flex-row" style="align-items: center">
						<div class="flex-grow-1">
							<img alt="" style="width: 163px !important;"
								src="${pageContext.request.contextPath}/resources/images/logo.svg">
						</div>
						<div>
							<p id="currentDate"></p>
						</div>
					</div>
					<div class="mt-4" style="color: #525252; font-weight: 500">
						<div>${quotationDataSet.address}</div>
						<div>${quotationDataSet.city}</div>
						<div>${quotationDataSet.state}</div>
						<div>
							Post Code : <span>${quotationDataSet.postalCode}</span>
						</div>
					</div>

					<div class="mt-4">
						<p>Re : Cards Payment Acceptance Programme We are pleased to
							inform you that your application for Mobi Asia Sdn, Bhd, (formerly
							known as Mobiversa Sdn, Bhd),Cards Payment Acceptance program has
							been approved based on information provided to us in Schedule 1 and
							Merchant Agreement executed by you. Pleas find the following card
							acceptance Procedures and fulfil of the following proposals.</p>
					</div>

					<div class="mt-4">
						<h5 class="welcome-letter-heading">Merchant Information</h5>
						<div class="row">
							<div class="col">
								<label for="TID">TID</label> <input name="" id="TID" placeholder="" class="form-control"
									disabled value="${tid}" />

							</div>
							<div class="col">
								<label for="MID">MID</label> <input name="" value="${mid}" id="TID" placeholder=""
									class="form-control" disabled />
							</div>
							<div class="col">
								<label for="DTL">DTL</label> <input name="" value="${dtl}" id="DTL" placeholder=""
									class="form-control" disabled />
							</div>
							<div class="col">
								<label for="Merchant DBA Name">Merchant DBA Name</label> <input disabled name=""
									id="Merchant DBA Name" value="${quotationDataSet.companyName}" placeholder=""
									class="form-control" />
							</div>
						</div>
					</div>


					<!-- MDR Table Region for NON-EZYSPLIT Products -->
					<div class="mt-4">
						<h5 class="welcome-letter-heading">MDR Table</h5>
						<table class="table table-borderless">
							<thead style="background-color: #f4f4f4">
								<tr>
									<th scope="col">Product</th>
									<th scope="col">Card Brand</th>
									<th scope="col">Domestic Debit</th>
									<th scope="col">Domestic Credit</th>
									<th scope="col">Foreign Debit</th>
									<th scope="col">Foreign Credit</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="tempOrderLine" items="${quotationDataSet.orderLines}" varStatus="count">
									<c:if test="${tempOrderLine.quotationEzysplitMDRRate == null }">
										<c:forEach var="i" begin="1" end="3">

											<c:if test="${i == 1 }">
												<tr>
													<th scope="row">${tempOrderLine.quotationMDRRate.productName }</th>
													<td>VISA</td>
													<td>${tempOrderLine.quotationMDRRate.loc_Debit_Merch_Visa }</td>
													<td>${tempOrderLine.quotationMDRRate.loc_Credit_Merch_Visa }</td>
													<td>${tempOrderLine.quotationMDRRate.for_Debit_Merch_Visa }</td>
													<td>${tempOrderLine.quotationMDRRate.for_Credit_Merch_Visa }</td>
												</tr>
											</c:if>

											<c:if test="${i == 2 }">
												<tr>
													<th scope="row">${tempOrderLine.quotationMDRRate.productName }</th>
													<td>MASTERCARD</td>
													<td>${tempOrderLine.quotationMDRRate.loc_Debit_Merch_Master }</td>
													<td>${tempOrderLine.quotationMDRRate.loc_Credit_Merch_Master }</td>
													<td>${tempOrderLine.quotationMDRRate.for_Debit_Merch_Master }</td>
													<td>${tempOrderLine.quotationMDRRate.for_Credit_Merch_Master }</td>
												</tr>
											</c:if>

											<c:if test="${i == 3 }">
												<tr>
													<th scope="row">${tempOrderLine.quotationMDRRate.productName }</th>
													<td>UNIONPAY</td>
													<td>${tempOrderLine.quotationMDRRate.loc_Debit_Merch_Union }</td>
													<td>${tempOrderLine.quotationMDRRate.loc_Credit_Merch_Union }</td>
													<td>${tempOrderLine.quotationMDRRate.for_Debit_Merch_Union }</td>
													<td>${tempOrderLine.quotationMDRRate.for_Credit_Merch_Union }</td>
												</tr>

											</c:if>

										</c:forEach>
									</c:if>
								</c:forEach>


							</tbody>
						</table>
					</div>

					<!-- MDR Table Region for NON-EZYSPLIT Products -->


					<!-- MDR Table Region for EZYSPLIT Products -->

					<div class="mt-4">
						<h5 class="welcome-letter-heading">EZYSPLIT MDR Table</h5>
						<table class="table table-borderless">
							<thead style="background-color: #f4f4f4">
								<tr>
									<th scope="col">Installments</th>
									<th scope="col">Merchant (%)</th>
									<th scope="col">Customer (%)</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="tempOrderLine" items="${quotationDataSet.orderLines}" varStatus="count">
									<c:if test="${tempOrderLine.quotationMDRRate == null }">
										<c:forEach var="i" begin="1" end="4">

											<c:if test="${i == 1 }">
												<tr>
													<th scope="row">3 Installments</th>
													<td>${tempOrderLine.quotationEzysplitMDRRate.loc_Credit_Merch_Master_Insta3
														}</td>
													<td>${tempOrderLine.quotationEzysplitMDRRate.loc_Credit_Cus_Master_Insta3
														}</td>
												</tr>
											</c:if>

											<c:if test="${i == 2 }">
												<tr>
													<th scope="row">6 Installments</th>
													<td>${tempOrderLine.quotationEzysplitMDRRate.loc_Credit_Merch_Master_Insta6
														}</td>
													<td>${tempOrderLine.quotationEzysplitMDRRate.loc_Credit_Cus_Master_Insta6
														}</td>
												</tr>
											</c:if>

											<c:if test="${i == 3 }">
												<tr>
													<th scope="row">9 Installments</th>
													<td>${tempOrderLine.quotationEzysplitMDRRate.loc_Credit_Merch_Master_Insta9
														}</td>
													<td>${tempOrderLine.quotationEzysplitMDRRate.loc_Credit_Cus_Master_Insta9
														}</td>
												</tr>
											</c:if>

											<c:if test="${i == 4 }">
												<tr>
													<th scope="row">12 Installments</th>
													<td>${tempOrderLine.quotationEzysplitMDRRate.loc_Credit_Merch_Master_Insta12
														}</td>
													<td>${tempOrderLine.quotationEzysplitMDRRate.loc_Credit_Cus_Master_Insta12
														}</td>
												</tr>
											</c:if>

										</c:forEach>
									</c:if>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<!-- MDR Table Region for EZYSPLIT Products -->

					<div class="mt-4">
						<h5 class="welcome-letter-heading">Merchant Payment Information</h5>
						<p>Content You are required to maintain the same bank current
							account for the purpose of payment crediting.</p>

						<div class="row">
							<div class="col">
								<label>Business Payee Name</label> <input name="" id="TerminalID"
									placeholder="Terminal ID" value="${businessName}" class="form-control" disabled />
							</div>
							<div class="col">
								<label>Bank Name</label> <input name="" id="APIKey" placeholder="API Key"
									value="${bankName}" class="form-control" disabled />
							</div>
							<div class="col">
								<label>Account Number</label> <input name="" id="ActivationCode"
									placeholder="Activation Code" value="${accountNo}" class="form-control" disabled />
							</div>

						</div>
					</div>

					<div class="mt-4">
						<h5 class="welcome-letter-heading">Merchant Serving</h5>

						<div class="d-flex">
							<p class="mr-2">For any queries</p>
							<p class="mr-2">
								<b>Contact Number : 0122902076</b>
							</p>
							<p class="mr-2">
								<b>Email: CSMOBI@gomobi.io</b>
							</p>
						</div>
					</div>

					<div class="mt-4">
						<h5 class="welcome-letter-heading">Terminal Setup Information</h5>

						<div class="row">
							<div class="col">
								<label for="Terminal ID">Terminal ID</label> <input name="" id="TerminalID"
									placeholder="Terminal ID" value="${tid}" class="form-control" disabled />
							</div>
							<div class="col">
								<label for="API Key">API Key</label> <input name="" id="APIKey" placeholder="API Key"
									value="${apiKey}" class="form-control" disabled />
							</div>
							<div class="col">
								<label for="Activation Code">Activation Code</label> <input name="" id="ActivationCode"
									placeholder="Activation Code" value="${activationCode}" class="form-control"
									disabled />
							</div>

						</div>

						<br>
						<p>If the above terms are acceptable to you, kindly signify your agreement by acknowledge this
							"Terminal Setup Information."</p>
						<p> Thank you and we look forward to establish a mutually beneficial business relationship with
							you.
						</p>
						<p> I/ We hereby confirm our acceptance and agree to the terms and conditions of this letter.
						</p>
					</div>

					<!-- 		<div class="mt-4">
			<div class="row">
				<div class="col-6">
					<img alt="" style="width: 100%" height="180"
						src="https://images.unsplash.com/photo-1633144135893-742767f36e56?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1033&q=80">
				</div>
				<div class="col-6 d-flex align-items-end">
					<p>Witness By</p>
				</div>
			</div>
		</div> -->


					<div class="mt-4">
						<p>Remarks from Mobi Asia Sdn Bhd ( Formerly known as Mobiversa Sdn Bhd) : </p>
						<p>As a Security precaution the Daily Transaction Limit (DTL) has been set up to RM 10,000.</p>
						<p> Please contact Mobi Asia Sdn Bhd(Formerly known as Mobiversa Sdn Bhd) after you have
							received
							the
							terminal to update the DTL for Credit card number.</p>
						<p>Contact : 0122902076.</p>
					</div>

					<hr class="mt-4">
					<div class="mt-4">
						<div class="d-flex flex-row justify-content-end">
							<c:if test="${welcomeLetterAccepted == false}">
								<button class="btn btn-accept m-2" onclick="acceptWelcomeLetter()">Accept</button>
							</c:if>
							<c:if test="${welcomeLetterAccepted == true}">
								<button class="btn btn-accept m-2" disabled>Accepted</button>
							</c:if>
							<button id="btnWelcomeLetterDownload" class="btn btn-primary m-2">Download</button>
						</div>
					</div>

				</div>
			</div>

			<div id="mobile-view">
				<div class="title">
					welcome letter
				</div>
				<div class="title">
					Tuesday, March 03, 2022
				</div>

				<p>
					Postal Code : 55200
				</p>
				<p>Re : Cards Payment Acceptance Programme We are pleased to
					inform you that your application for Mobi Asia Sdn, Bhd, (formerly
					known as Mobiversa Sdn, Bhd),Cards Payment Acceptance program has
					been approved based on information provided to us in Schedule 1 and
					Merchant Agreement executed by you. Pleas find the following card
					acceptance Procedures and fulfil of the following proposals.</p>

				<div class="title-accent">
					Merchant Information
				</div>

				<div class="row">
					<div class="col-6">
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label caption">TID</label>
							<input type="email" class="form-control" id="exampleFormControlInput1" readonly
								value="${tid}">
						</div>
					</div>
					<div class="col-6">
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label caption">MID</label>
							<input type="email" class="form-control" id="exampleFormControlInput1" readonly
								value="${mid}">
						</div>
					</div>
					<div class="col-6">
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label caption">DTL</label>
							<input type="email" class="form-control" id="exampleFormControlInput1" readonly
								value="${dtl}">
						</div>
					</div>
					<div class="col-6">
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label caption">Merchant DBA Name</label>
							<input type="email" class="form-control" id="exampleFormControlInput1" readonly
								value="${quotationDataSet.companyName}">
						</div>
					</div>
				</div>

				<div class="title-accent">
					Merchant Payment Information
				</div>

				<p>Content You are required to maintain the same bank current
					account for the purpose of payment crediting.</p>

				<div class="row">
					<div class="col-6">
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label caption">Business Payee Name</label>
							<input type="email" class="form-control" id="exampleFormControlInput1" readonly
								value="${businessName}">
						</div>
					</div>
					<div class="col-6">
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label caption">Bank Name</label>
							<input type="email" class="form-control" id="exampleFormControlInput1" readonly
								value="${bankName}">
						</div>
					</div>
					<div class="col-12">
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label caption">Account Number</label>
							<input type="email" class="form-control" id="exampleFormControlInput1" readonly
								value="${accountNo}">
						</div>
					</div>
				</div>

				<div class="title-accent">Merchant Serving</div>
				<div class="d-flex align-items-center">
					<div class="mr-4" style="margin-right: 1.5rem;">
						<div class="caption">
							Contact Number:
						</div>
						<div class="title">
							0122902076
						</div>
					</div>
					<div class="">
						<div class="caption">
							Email Id:
						</div>
						<div class="title" style="text-transform: none !important;">
							CSMobi@gomobi.io
						</div>
					</div>
				</div>
				<div class="title-accent">Terminal Setup Information</div>
				<div class="row">
					<div class="col-6">
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label caption">Terminal Id</label>
							<input type="email" class="form-control" id="exampleFormControlInput1" readonly
								value="${tid}">
						</div>
					</div>
					<div class="col-6">
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label caption">API Key</label>
							<input type="email" class="form-control" id="exampleFormControlInput1" readonly
								value="${apiKey}">
						</div>
					</div>
					<div class="col-12">
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label caption">Activation Code</label>
							<input type="email" class="form-control" id="exampleFormControlInput1" readonly
								value="${activationCode}">
						</div>
					</div>
				</div>

				<div class="mt-4">
					<p>Remarks from Mobi Asia Sdn Bhd ( Formerly known as Mobiversa Sdn Bhd) : </p>
					<p>As a Security precaution the Daily Transaction Limit (DTL) has been set up to RM 10,000.</p>
					<p> Please contact Mobi Asia Sdn Bhd(Formerly known as Mobiversa Sdn Bhd) after you have
						received
						the
						terminal to update the DTL for Credit card number.</p>
					<p>Contact : 0122902076.</p>
				</div>

				<div class="d-flex flex-row">
					<button class="btn btn-primary flex-grow-1 m-1 align-items-center"
						onclick="downloadWelcomeLetter()"><img
							src="${pageContext.request.contextPath}/resources/images/download.svg" width="17" alt=""
							srcset="">Download</button>
					<c:if test="${welcomeLetterAccepted == false}">
						<button class="btn btn-primary flex-grow-1 m-1 align-items-center"
							onclick="acceptWelcomeLetter()"><img
								src="${pageContext.request.contextPath}/resources/images/accept.svg" width="17" alt=""
								srcset="">Accept</button>
					</c:if>
					<c:if test="${welcomeLetterAccepted == true}">
						<button class="btn btn-primary flex-grow-1 m-1 align-items-center" disabled><img
								src="${pageContext.request.contextPath}/resources/images/accept.svg" width="17" alt=""
								srcset="">Accepted</button>
					</c:if>
				</div>
			</div>

			<!-- Modal -->
			<div class="modal fade" id="accept-welcome-letter-modal" tabindex="-1" aria-labelledby="exampleModalLabel"
				aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Quotation
								Acceptance</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<p>Do you want to copy your signature to this quotation?</p>
							<div
								style="background-color: #ededed; width: 100% !important; height: 300px !important; text-align: center !important;">
								<img width="300" height="300" style="width: 300px !important; height: 300px !important;"
									id="signature-view"></img>
							</div>

							<div class="mt-4">
								<label for="namePerIc">Enter your Name as Per IC*</label> <input type="text" name=""
									value="" id="namePerIc" class="form-control">
							</div>

							<div class="mt-4">
								<label for="icNumber">Enter the IC NO*</label> <input type="text" name="" value=""
									id="icNumber" class="form-control">
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary"
								onclick="validateWelcomeLetterData()">Accept</button>
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

		<script>

			var contextPath = "${pageContext.request.contextPath}"
			var token = $("meta[name='_csrf']").attr("content")
			var header = $("meta[name='_csrf_header']").attr("content")
			var ipAddress = null

			var quotationId = null
			const orderId = "${orderId}"

			var acceptWelcomeLetterModal = new bootstrap.Modal(document.getElementById('accept-welcome-letter-modal'), {
				keyboard: false
			})


			window.onload = function () {
				const params = new URLSearchParams(window.location.search)
				quotationId = params.get('quotationId')


				document.getElementById("currentDate").innerText = moment().format('dddd, MMMM DD,yyyy')
				getIpAddress()

				// getQuotationById(quotationId)
			}

			function getIpAddress() {
				var config = {
					method: 'get',
					url: "https://api.ipify.org?format=json",
				}


				axios(config)
					.then(response => {
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

			function getQuotationById(orderId) {
				const config = {
					method: 'post',
					url: contextPath + '/merchant/quotation/' + quotationId,
					headers: {
						'X-CSRF-TOKEN': token,
						'Content-Type': 'application/json'
					}
				}


				axios(config)
					.then(resposne => {
						return resposne.data
					}).then(response => {
						console.log(response)
					}).catch(error => {
						console.log(error)
					}).finally(() => {

					})
			}

			function acceptWelcomeLetter() {

				const config = {
					method: 'post',
					url: contextPath + "/merchant/signature/" + quotationId,
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
							document.getElementById("signature-view").src = contextPath + response.responseData.imageUrl
						} else {
							alert(response.responseMessage)
						}
					})
					.catch(error => {
						console.log(error)
					})
					.finally(() => {
						acceptWelcomeLetterModal.show()
					})


			}

			function validateWelcomeLetterData() {
				const namePerIc = document.getElementById("namePerIc").value
				const icNumber = document.getElementById("icNumber").value

				if (namePerIc !== null && icNumber !== "" && icNumber !== null && icNumber !== "") {
					acceptWelcomeLetterData(namePerIc, icNumber)
				} else {
					alert("Please provide both Name Per IC and IC Number")
				}

			}

			function acceptWelcomeLetterData(namePerIc, icNumber) {
				acceptWelcomeLetterModal.hide()
				startLoadingScreen("Loading, please wait...")
				const config = {
					method: "POST",
					url: contextPath + '/merchant/wl/' + quotationId + '/accept',
					headers: {
						'X-CSRF-TOKEN': token,
						'Content-Type': 'application/json'
					},
					data: JSON.stringify({
						orderId: orderId,
						namePerIc: namePerIc,
						icNumber: icNumber,
						ipAddress: ipAddress
					})
				}


				axios(config)
					.then(response => {
						return response.data
					})
					.then(response => {
						alert(response.responseMessage)
					})
					.catch(error => {
						console.log(error)
					})
					.finally(() => {
						window.location.replace(contextPath + "/MerchantOrder")
					})
			}

			document.getElementById("btnWelcomeLetterDownload").addEventListener('click', (event) => {
				downloadWelcomeLetter()
			})

			document.getElementById("viewWelcomeLetter").addEventListener('click', (event) => {
				alert()
			})

			function downloadWelcomeLetter() {
				startLoadingScreen("Loading, please wait...")
				$.ajax({
					type: "GET",
					url: "/ocsweb/merchantReg/generateWL",
					data: {
						orderId: orderId
					},
					success: function (result) {
						stopLoadingScreen()
						if (result.responseStatus === "0000") {
							const urlWl = "${pageContext.request.contextPath}" + result.responseData.welcomeLetterPath;
							window.open(urlWl)
						}
					},
					error: function (result) {
						stopLoadingScreen()
						alert('error');
					}
				});
			}
		</script>

		<style type="text/css">
			#welcome-letter-body {
				margin-top: 2rem !important;
				padding: 4rem;
			}

			.welcome-letter {
				padding: 5rem;
				background-color: white
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
				margin-right: 1rem
			}

			p {
				margin: 0
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
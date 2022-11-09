<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!doctype html>
	<html lang="en">

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
		<title>MOBI</title>
		<meta id="_csrf" name="_csrf" content="${_csrf.token}" />
		<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}" />

		<!-- CDN moved to local -->
		<script src="${pageContext.request.contextPath}/resources/js/signaturePad.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/js/fontawesome/css/fontawesome.min.css" />
		<script src="${pageContext.request.contextPath}/resources/js/axios.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/resources/js/moment.js" type="text/javascript"></script>
		<!-- CDN moved to local -->

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.css" />

		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />

		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" />
		<link rel="stylesheet" type="text/css"
			href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap5.min.css" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/fonts/fonts.css" />

		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap5.min.js"></script>

		<script src="${pageContext.request.contextPath}/resources/js/loading.js"></script>

		<%@ page isELIgnored="false" %>
	</head>
	<style>
		body {
			background-color: #f1f5fa;
		}

		.bcf4f4f4 {
			background-color: #f4f4f4;
		}

		.c3f3f3f {
			color: #3f3f3f;
		}

		.c617075 {
			color: #617075;
		}

		.fw500 {
			font-weight: 500;
		}

		.table {
			font-size: 12px;
		}

		.mt110 {
			margin-top: 110px;
		}

		.btn-mobi {
			color: #fff;
			background-color: #005baa;
			border-color: #005baa;
		}

		/* Extra small devices (phones, 600px and down) */
		@media only screen and (max-width: 600px) {
			#desktop-view {
				display: none;
			}

			#mobile-view {
				display: block;
			}

			.mobile-card {
				border-radius: 12px;
				box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.16);
				border: solid 1px #ddd;
				background-color: #fff;
			}

			.mobile-card-body {
				padding: 1.5rem;
			}

			.caption {
				font-family: Inter;
				font-size: 12px;
				font-weight: 500;
				font-stretch: normal;
				font-style: normal;
				line-height: 1.25;
				letter-spacing: -0.16px;
				text-align: left;
				color: #969696;
			}

			.content {
				margin: 7px 0 0;
				font-family: Inter;
				font-size: 14px;
				font-weight: 700;
				font-stretch: normal;
				font-style: normal;
				line-height: 1.71;
				letter-spacing: normal;
				text-align: left;
				color: #151931;
			}

			.mobile-card-footer {
				background-color: #005baa;
				border-bottom-left-radius: 12px;
				border-bottom-right-radius: 12px;
				text-align: center;
				text-align: -webkit-center;
				padding: 0.4rem;
				color: white;
				text-transform: uppercase;
			}
		}

		/* Small devices (portrait tablets and large phones, 600px and up) */
		@media only screen and (min-width: 600px) {
			#desktop-view {
				display: none;
			}

			#mobile-view {
				display: block;
			}

			.mobile-card {
				border-radius: 12px;
				box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.16);
				border: solid 1px #ddd;
				background-color: #fff;
			}

			.mobile-card-body {
				padding: 1.5rem;
			}

			.caption {
				font-family: Inter;
				font-size: 12px;
				font-weight: 500;
				font-stretch: normal;
				font-style: normal;
				line-height: 1.25;
				letter-spacing: -0.16px;
				text-align: left;
				color: #969696;
			}

			.content {
				margin: 7px 0 0;
				font-family: Inter;
				font-size: 14px;
				font-weight: 700;
				font-stretch: normal;
				font-style: normal;
				line-height: 1.71;
				letter-spacing: normal;
				text-align: left;
				color: #151931;
			}

			.mobile-card-footer {
				background-color: #005baa;
				border-bottom-left-radius: 12px;
				border-bottom-right-radius: 12px;
				text-align: center;
				text-align: -webkit-center;
				padding: 0.4rem;
				color: white;
				text-transform: uppercase;
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
		@media only screen and (min-width: 992px) {}

		/* Extra large devices (large laptops and desktops, 1200px and up) */
		@media only screen and (min-width: 1200px) {}
	</style>

	<body>
		<main style="margin-top: 3rem;">
			<div class="p-4">

				<c:if test="${empty quotations.orderLines}">
					<div class="mobi-card">
						<div class="mobi-card-body" style="text-align: center;">
							<h6>No Order Lines Available</h6>
						</div>
					</div>
				</c:if>

				<c:if test="${not empty quotations.orderLines}">
					<c:forEach var="item" items="${quotations.orderLines}">

						<c:if test="${not empty item.quotationMDRRate}">
							<div class="mobi-card" style="margin-top: 1rem;">
								<div class="mobi-card-body">
									<div class="row">
										<div class="col-8">
											<div class="caption">Product Name</div>
											<div class="title">${item.product.productName}</div>
										</div>

										<div class="col-4">
											<div class="caption">Price</div>
											<div class="title-accent">RM ${item.product.unitPrice}</div>
										</div>
									</div>

									<hr />

									<div class="row">
										<div class="col col-4">
											<p class="title-accent">Card</p>
											<p class="body-1 text-small">LOCAL DEBIT</p>
											<p class="body-1 text-small">LOCAL CREDIT</p>
											<p class="body-1 text-small">FOREIGN DEBIT</p>
											<p class="body-1 text-small">FOREIGN CREDIT</p>
										</div>
										<div class="col" style="text-align: center;">
											<div>
												<img width="40"
													src="${pageContext.request.contextPath}/resources/images/mastercard/mastercard@3x.png"
													alt="" />
											</div>
											<p>${item.quotationMDRRate.loc_Debit_Merch_Master }</p>
											<p>${item.quotationMDRRate.loc_Credit_Merch_Master }</p>
											<p>${item.quotationMDRRate.for_Debit_Mobi_Master }</p>
											<p>${item.quotationMDRRate.for_Credit_Mobi_Master }</p>
										</div>
										<div class="col" style="text-align: center;">
											<div>
												<img width="40"
													src="${pageContext.request.contextPath}/resources/images/union_pay/union_pay@3x.png"
													alt="" />
											</div>
											<p>${item.quotationMDRRate.loc_Debit_Merch_Union }</p>
											<p>${item.quotationMDRRate.loc_Credit_Merch_Union }</p>
											<p>${item.quotationMDRRate.for_Debit_Mobi_Union }</p>
											<p>${item.quotationMDRRate.for_Credit_Mobi_Union }</p>
										</div>
										<div class="col" style="text-align: center;">
											<div>
												<img width="40"
													src="${pageContext.request.contextPath}/resources/images/visa/visa@3x.png"
													alt="" />
											</div>
											<p>${item.quotationMDRRate.loc_Debit_Merch_Visa }</p>
											<p>${item.quotationMDRRate.loc_Credit_Merch_Visa }</p>
											<p>${item.quotationMDRRate.for_Debit_Mobi_Visa }</p>
											<p>${item.quotationMDRRate.for_Credit_Mobi_Visa }</p>
										</div>
									</div>

									<div class="headline">Wallet Details</div>

									<div class="row">
										<div class="col">
											<p class="content">BOOST ECOMM</p>
											<p class="content">GRAB ECOMM</p>
											
											<p class="content">TNG ECOMM</p>
											<p class="content">SHOPEEPAY ECOMM</p>
											
											<p class="content">FPX (RM)</p>
										</div>
										<div class="col-2" style="text-align: end;">
											<p>${item.quotationMDRRate.boostMDREcomm}</p>
											<p>${item.quotationMDRRate.grabMDREcomm}</p>
											<p>${item.quotationMDRRate.tngMDREcomm}</p>
											<p>${item.quotationMDRRate.shopeepayMDREcomm}</p>
											<p>${item.quotationMDRRate.fPXMDR_RM}</p>
										</div>
										<div class="col">
											<p>BOOST QR</p>
											<p>GRAB QR</p>
											<p>TNG QR</p>
											<p>SHOPEEPAY QR</p>
											<p>FPX (%)</p>
										</div>
										<div class="col-2" style="text-align: end;">
											<p>${item.quotationMDRRate.boostMDRQR}</p>
											<p>${item.quotationMDRRate.grabMDRQR}</p>
											<p>${item.quotationMDRRate.tngMDRQR}</p>
											<p>${item.quotationMDRRate.shopeepayMDRQR}</p>
											<p>${item.quotationMDRRate.fPXMDR_Percent}</p>
										</div>
									</div>

									<div class="headline">Settlement</div>

									<div class="row">
										<div class="col">
											<p class="content">BOOST</p>
										</div>
										<div class="col-1" style="text-align: end;">
											<p class="content">${item.quotationMDRRate.boostSettlement}</p>
										</div>
										<div class="col" style="text-align: center;">
											<p class="content">GRAB</p>
										</div>
										<div class="col-1" style="text-align: end;">
											<p class="content">${item.quotationMDRRate.grabSettlement}</p>
										</div>
										<div class="col" style="text-align: center;">
											<p class="content">FPX</p>
										</div>
										<div class="col-1" style="text-align: end;">
											<p class="content">${item.quotationMDRRate.fpxSettlement}</p>
										</div>
									</div>
									
									
									
									<div class="row">
										<div class="col">
											<p class="content">TNG</p>
										</div>
										<div class="col-1" style="text-align: end;">
											<p class="content">${item.quotationMDRRate.tngSettlement}</p>
										</div>
										<div class="col" style="text-align: center;">
											<p class="content">SHOPPEEPAY</p>
										</div>
										<div class="col-1" style="text-align: end;">
											<p class="content">${item.quotationMDRRate.shopeepaySettlement}</p>
										</div>
										
										<div class="col" style="text-align: center;">
											<p class="content"></p>
										</div>
										<div class="col-1" style="text-align: end;">
											<p class="content"></p>
										</div>
										
									</div>
									
									
									
									
									
									
									
									
									
									
								</div>
							</div>
						</c:if>
						<c:if test="${not empty item.quotationEzysplitMDRRate}">
							<div class="mobi-card" style="margin-top: 24px;">
								<div class="mobi-card-body">
									<div class="d-flex flex-row">
										<div class="flex-grow-1">
											<div class="caption">Product Name</div>
											<div class="title">${item.product.productName}</div>
										</div>

										<div class="flex-grow-1">
											<div class="caption">Price</div>
											<div class="title-accent">RM ${item.product.unitPrice}</div>
										</div>
									</div>

									<hr />

									<div class="headline">Wallet Details</div>

									<div class="row">
										<div class="col">
											<p class="content">BOOST ECOMM</p>
											<p class="content">GRAB ECOMM</p>
											<p class="content">FPX (RM)</p>
										</div>
										<div class="col-2" style="text-align: end;">
											<p>${item.quotationEzysplitMDRRate.boostMDREcomm}</p>
											<p>${item.quotationEzysplitMDRRate.grabMDREcomm}</p>
											<p>${item.quotationEzysplitMDRRate.fPXMDR_RM}</p>
										</div>
										<div class="col">
											<p>BOOST QR</p>
											<p>GRAB QR</p>
											<p>FPX (%)</p>
										</div>
										<div class="col-2" style="text-align: end;">
											<p>${item.quotationEzysplitMDRRate.boostMDRQR}</p>
											<p>${item.quotationEzysplitMDRRate.grabMDRQR}</p>
											<p>${item.quotationEzysplitMDRRate.fPXMDR_Percent}</p>
										</div>
									</div>

									<div class="headline">Settlement</div>

									<div class="row">
										<div class="col">
											<p class="content">BOOST</p>
										</div>
										<div class="col-1" style="text-align: end;">
											<p class="content">${item.quotationEzysplitMDRRate.boostSettlement}</p>
										</div>
										<div class="col" style="text-align: center;">
											<p class="content">GRAB</p>
										</div>
										<div class="col-1" style="text-align: end;">
											<p class="content">${item.quotationEzysplitMDRRate.grabSettlement}</p>
										</div>
										<div class="col" style="text-align: center;">
											<p class="content">FPX</p>
										</div>
										<div class="col-1" style="text-align: end;">
											<p class="content">${item.quotationEzysplitMDRRate.fpxSettlement}</p>
										</div>
									</div>

									<div class="headline">3 Month Instalment</div>
									<div class="row">
										<div class="col">MERCHANT MDR (%)</div>
										<div class="col-2" style="text-align: end;">
											${item.quotationEzysplitMDRRate.loc_Credit_Merch_Master_Insta3}</div>
										<div class="col">CUSTOMER MDR (%)</div>
										<div class="col-2" style="text-align: end;">
											${item.quotationEzysplitMDRRate.loc_Credit_Cus_Master_Insta3}</div>
									</div>

									<div class="headline">6 Month Instalment</div>
									<div class="row">
										<div class="col">MERCHANT MDR (%)</div>
										<div class="col-2" style="text-align: end;">
											${item.quotationEzysplitMDRRate.loc_Credit_Merch_Master_Insta6}</div>
										<div class="col">CUSTOMER MDR (%)</div>
										<div class="col-2" style="text-align: end;">
											${item.quotationEzysplitMDRRate.loc_Credit_Cus_Master_Insta6}</div>
									</div>

									<div class="headline">9 Month Instalment</div>
									<div class="row">
										<div class="col">MERCHANT MDR (%)</div>
										<div class="col-2" style="text-align: end;">
											${item.quotationEzysplitMDRRate.loc_Credit_Merch_Master_Insta9}</div>
										<div class="col">CUSTOMER MDR (%)</div>
										<div class="col-2" style="text-align: end;">
											${item.quotationEzysplitMDRRate.loc_Credit_Cus_Master_Insta9}</div>
									</div>

									<div class="headline">12 Month Instalment</div>
									<div class="row">
										<div class="col">MERCHANT MDR (%)</div>
										<div class="col-2" style="text-align: end;">
											${item.quotationEzysplitMDRRate.loc_Credit_Merch_Master_Insta12}</div>
										<div class="col">CUSTOMER MDR (%)</div>
										<div class="col-2" style="text-align: end;">
											${item.quotationEzysplitMDRRate.loc_Credit_Cus_Master_Insta12}</div>
									</div>
								</div>
							</div>
						</c:if>
					</c:forEach>

					<div class="mt-3">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" value="" id="checkbox-agree" /> <label
								class="form-check-label" for="flexCheckDefault"> I hereby agree and accept the
								Terms and Conditions </label>
						</div>
					</div>
					<hr />

					<div class="d-flex flex-row justify-content-between align-items-center">
						<div class="caption m-1">Sub Total Amount</div>
						<h3 class="title-accent m-1">RM ${subTotal}</h3>
					</div>
					<div class="d-flex flex-row justify-content-between align-items-center">
						<div class="caption m-1">Discount Amount</div>
						<h3 class="title-accent m-1">RM ${discount}</h3>
					</div>
					<div class="d-flex flex-row justify-content-between align-items-center">
						<div class="caption m-1">Total Amount</div>
						<h3 class="title-accent m-1">RM ${totalAmount}</h3>
					</div>

					<div class="d-flex flex-row">
						<button class="btn btn-primary flex-grow-1 m-1 align-items-center"
							onclick="downloadQuotation()">
							<img src="${pageContext.request.contextPath}/resources/images/download.svg" width="17"
								alt="" srcset="" /> Download Quotation
						</button>
						<button class="btn btn-primary flex-grow-1 m-1 align-items-center" id="button-accept-quotation">
							<img src="${pageContext.request.contextPath}/resources/images/accept.svg" width="17" alt=""
								srcset="" /> Accept Quotation
						</button>
					</div>
				</c:if>
			</div>
		</main>

		<!-- Modal -->
		<div class="modal fade" id="quotationAcceptanceModal" tabindex="-1" aria-labelledby="exampleModalLabel"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Quotation Acceptance</h5>
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
						<button type="button" class="btn btn-primary" onclick="acceptQuotation()">Accept
							Quotation</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Modal -->
		<div class="modal fade" id="newQuotationAcceptModal" tabindex="-1" aria-labelledby="exampleModalLabel"
			aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Quotation Acceptance</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<p>Please add your signature and enter the below details.</p>
						<div style="background-color: #ededed; width: 100% !important; text-align: center !important;">
							<canvas id="signature-canvas" width="300" height="300" style="
									width: 300px !important;
									height: 300px !important;
									touch-action: none;
									margin: 1rem;
									border: 1px solid #0000002e;
									border-radius: 4px;
									padding: 8px;"></canvas>
						</div>
						<button class="btn btn-primary" style="width: 100% !important;" onclick="clearSignature()">Clear
							Signature</button>
						<div class="mt-4">
							<label for="namePerIc-new">Enter your Name as Per IC*</label> <input type="text" name=""
								value="" id="namePerIc-new" class="form-control">
						</div>

						<div class="mt-4">
							<label for="icNumber-new">Enter the IC NO*</label> <input type="text" name="" value=""
								id="icNumber-new" class="form-control">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
						<button type="button" class="btn btn-primary" onclick="acceptNewQuotation()">Accept
							Quotation</button>
					</div>
				</div>
			</div>
		</div>


	</body>



	<script>
		const quotationID = "${quotationId}"
		const quotations = "${quotations}"
		var ipAddress = null

		const quotationAccepted = "${quotations.quotationAccepted}"

		var token = $("meta[name='_csrf']").attr("content")
		var header = $("meta[name='_csrf_header']").attr("content")
		var contextPath = "${pageContext.request.contextPath}"

		var quotationAcceptanceModal = new bootstrap.Modal(document.getElementById('quotationAcceptanceModal'), {
			keyboard: false
		})
		var newQuotationAcceptModal = new bootstrap.Modal(document.getElementById('newQuotationAcceptModal'), {
			keyboard: false
		})

		const checkBoxTermsAndConditions = document.getElementById("checkbox-agree")

		checkBoxTermsAndConditions.addEventListener('change', () => {
			if (event.target.checked && quotationAccepted !== "1") {
				document.getElementById("button-accept-quotation").disabled = false
			} else {
				document.getElementById("button-accept-quotation").disabled = true
			}
		})

		var signatureCanvas = document.getElementById("signature-canvas")
		var signaturePad = new SignaturePad(signatureCanvas, {})
		var context = signatureCanvas.getContext('2d');

		var canvasImageLoader = new Image();
		canvasImageLoader.onload = function () {
			context.drawImage(this, 0, 0);
		};

		window.onload = function () {
			document.getElementById("button-accept-quotation").disabled = true

			if (quotationAccepted === "1") {
				checkBoxTermsAndConditions.checked = true
				checkBoxTermsAndConditions.disabled = true
				document.getElementById("button-accept-quotation").innerHTML = "Quotation Accepted"
			}
			getIpAddress()
		};


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


		document.getElementById("button-accept-quotation").onclick = function () {

			const config = {
				method: 'post',
				url: contextPath + "/merchant/signature/" + quotationID,
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
						if (response.responseData.imageUrl === null) {
							document.getElementById("signature-view").style.display = "none"
							newQuotationAcceptModal.show()
						} else {
							document.getElementById("signature-view").src = contextPath + response.responseData.imageUrl
							quotationAcceptanceModal.show()
						}
					} else {
						document.getElementById("signature-view").style.display = "none"
						newQuotationAcceptModal.show()
					}
				})
				.catch(error => {
					console.log(error)
				})
				.finally(() => {

				})


		}

		function acceptQuotation() {
			const namePerIc = document.getElementById("namePerIc").value
			const icNumber = document.getElementById("icNumber").value

			if (namePerIc !== "" && icNumber !== "") {
				acceptQuotationWithSignature(quotationID, namePerIc, icNumber)
			} else {
				alert()
			}
		}

		function acceptQuotationWithSignature(quotationID, namePerIc, icNumber) {
			startLoadingScreen("Loading, please wait")
			const config = {
				method: 'post',
				url: contextPath + '/merchant/quotation/' + quotationID + '/accept',
				headers: {
					'X-CSRF-TOKEN': token,
					'Content-Type': 'application/json'
				},
				data: JSON.stringify({
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
					if (response.responseStatus === "0000") {
						alert("Quotation Acceptance successful")
					} else {
						alert(response.responseMessage)
					}
				})
				.catch(error => {
					console.log(error)
				})
				.finally(() => {
					quotationAcceptanceModal.hide()
					window.location.reload()
				})
		}

		function acceptNewQuotation() {
			const namePerIc = document.getElementById("namePerIc-new").value
			const icNumber = document.getElementById("icNumber-new").value

			if (namePerIc !== "" && icNumber !== "") {
				updateSignature(namePerIc, icNumber)
			} else {
				alert("Please enter all details")
			}

		}

		function updateSignature(namePerIc, icNumber) {
			var imageString = signaturePad.toDataURL();
			const data = signaturePad.toData();
			if (signaturePad.isEmpty()) {
				alert("Please draw your signature to upload")
			} else {
				updateSignatureString(imageString, namePerIc, icNumber)
			}
		}


		function updateSignatureString(imageString, namePerIc, icNumber) {
			startLoadingScreen("Processing, please wait")
			var data = JSON.stringify({
				"imageBaseString": imageString,
				"username": "123456"
			})

			var config = {
				method: 'post',
				url: contextPath + '/signature/add',
				headers: {
					'X-CSRF-TOKEN': token,
					'Content-Type': 'application/json'
				},
				data: data
			}

			this.axios(config)
				.then(function (response) {
					return response.data
				})
				.then(function (response) {
					if (response.responseStatus === "0000") {
						acceptQuotationWithSignature(quotationID, namePerIc, icNumber)
					}
				})
				.catch(function (error) {
					console.log(error);
				})
				.finally(function () {
					stopLoadingScreen()
				})

		}

		function getToken() {
			return document.getElementsByTagName('meta')['csrf-token'].getAttribute("content");
		}

		function downloadQuotation() {
			startLoadingScreen("Loading, please wait...")

			const config = {
				method: 'post',
				url: contextPath + "/quotation/download/" + quotationID,
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
						if (response.responseData !== null) {
							downloadQuotationPDF(response.responseData)
						} else {
							alert(response.responseMessage)
						}
					} else {
						alert(response.responseMessage)
					}
				})
				.catch(error => {
					console.log(error)
				})
				.finally(() => {
					stopLoadingScreen()
				})
		}

		function downloadQuotationPDF(data) {
			var url = contextPath + data.documentPath
			window.open(url)
		}

		function clearSignature() {
			signaturePad.clear();
		}

		function getQuotationOrderLines() {
			startLoadingScreen("Loading, please wait...")
			var config = {
				method: 'post',
				url: contextPath + '/merchant/quotation/' + quotationID,
				headers: {
					'X-CSRF-TOKEN': token,
					'Content-Type': 'application/json'
				}
			}

			this.axios(config)
				.then(function (response) {
					return response.data
				})
				.then(function (response) {
					stopLoadingScreen();
					console.log(response);
				})
				.catch(function (error) {
					console.log(error);
				})
				.finally(function () {

				})
		}
	</script>

	</html>
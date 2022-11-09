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
		<script src="${pageContext.request.contextPath}/resources/js/signaturePad.js" type="text/javascript"></script>
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

		<script src="${pageContext.request.contextPath}/resources/js/loading.js"></script>

		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap5.min.js"></script>


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
		<main class="container mt110" id="desktop-view">
			<div class="row">
				<div class="col-md-12 col-lg-12">
					<div class="bg-white p-5 rounded">
						<form action="addQuotation" method="post">
							<div class="row">
								<div class="col-md-6 col-lg-8 col-xs-12 c5d5d5d">Quotations</div>
								<div class="col-md-6 col-lg-4 col-xs-12 ">
									<div class="row"></div>
								</div>
							</div>
						</form>
						<hr />
						<div class="table-responsive">
							<table id="table" class="table">
								<thead>
									<tr class="bcf4f4f4 c617075 fw500">
										<th>Quotation ID</th>
										<th>Type</th>
										<th>Date</th>
										<th>Company Name</th>
										<th>Quotation Acceptance</th>
									</tr>
								</thead>
								<tbody class="c3f3f3f fw500">
									<c:forEach var="item" items="${quotations}">

										<c:url var="viewLink" value="OrderView">
											<c:param name="id" value="${item.id}"></c:param>
										</c:url>

										<tr>
											<td>${item.id}</td>
											<td>${item.orderType}</td>
											<td>${item.createdOn}</td>
											<td>${item.companyName}</td>
											<td>
												<button class="btn btn-mobi btn-sm" style="width: 100%"
													onclick="getQuotationId(${item.getId()})">View
													Quotation</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div id="overlay" style="display: none; pointer-events: none; cursor: default; text-decoration: none;">
			</div>
			<div class="product-container" id="product-container" style="visibility: collapse;">
				<div class="product-header">
					<div class="d-flex flex-row">
						<h5 class="flex-grow-1" style="color: #535353" id="product-order-id"></h5>
						<button type="button" class="btn-close" aria-label="Close"
							onclick="onCloseProductContainer()"></button>
					</div>
				</div>
				<div class="product-body row mt-3 flex-grow-1" id="product-container-row">
				</div>
				<div class="product-footer">
					<div id="quotation-tearms-conditions" class="d-flex flex-row align-self-center">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" value=""
								id="checkbox-accept-terms-conditions">
							<label class="form-check-label" for="checkbox-accept-terms-conditions">
								I hereby agree and accept the <a href="https://gomobi.io/merchant-tnc"
									target="_blank">Terms and
									Conditions</a> and Quotations.
							</label>
						</div>
					</div>
					<hr />
					<div class="d-flex flex-row align-self-center">
						<div class="d-flex flex-row" style="width: -webkit-fill-available;">
							<div class="flex-grow-1">
								<div>Sub Total</div>
								<div class="h6" id="subtotalPrice">RM 1234567</div>
							</div>
							<div class="flex-grow-1">
								<div>Discount</div>
								<div class="h6" id="discountPrice">RM 1234567</div>
							</div>
							<div class="flex-grow-1">
								<div>Total</div>
								<div class="h6" id="totalPrice">RM 1234567</div>
							</div>
						</div>
						<button class="btn btn-outline-primary btn-sm m-2" id="button-download-quotation"
							style="height: min-content; width: auto; align-self: center;">Download
							Quotation</button>
						<button class="btn btn-primary btn-sm m-2" id="button-accept-quotation"
							style="height: min-content; width: auto; align-self: center;">Accept
							Quotation</button>

					</div>
				</div>
			</div>


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
							<div
								style="background-color: #ededed; width: 100% !important; text-align: center !important;">
								<canvas id="signature-canvas" width="300" height="300" style="
									width: 300px !important;
									height: 300px !important;
									touch-action: none;
									margin: 1rem;
									border: 1px solid #0000002e;
									border-radius: 4px;
									padding: 8px;"></canvas>
							</div>
							<button class="btn btn-primary" style="width: 100% !important;"
								onclick="clearSignature()">Clear Signature</button>
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

		</main>


		<div class="container" style="margin-top: 5rem;" id="mobile-view">
			<h4>
				Quotations
			</h4>
			<div id="mobile-quotationList">
			</div>

		</div>


		<script>

			var contextPath = "${pageContext.request.contextPath}"
			var token = $("meta[name='_csrf']").attr("content")
			var header = $("meta[name='_csrf_header']").attr("content")
			var ipAddress = null
			var selectedQuotationId = null

			const productContainer = document.getElementById("product-container")
			const overlay = document.getElementById("overlay")

			var signatureCanvas = document.getElementById("signature-canvas")
			var signaturePad = new SignaturePad(signatureCanvas, {})
			var context = signatureCanvas.getContext('2d');

			var canvasImageLoader = new Image();
			canvasImageLoader.onload = function () {
				context.drawImage(this, 0, 0);
			};

			const checkBoxTermsAndConditions = document.getElementById("checkbox-accept-terms-conditions")

			checkBoxTermsAndConditions.addEventListener('change', () => {
				if (event.target.checked) {
					document.getElementById("button-accept-quotation").disabled = false
				} else {
					document.getElementById("button-accept-quotation").disabled = true
				}
			})

			var quotationAcceptanceModal = new bootstrap.Modal(document.getElementById('quotationAcceptanceModal'), {
				keyboard: false
			})
			var newQuotationAcceptModal = new bootstrap.Modal(document.getElementById('newQuotationAcceptModal'), {
				keyboard: false
			})


			$(document).ready(function () {
				document.getElementById("button-accept-quotation").disabled = true
				$('#table').DataTable({
					"order": [[0, 'desc']],
					"dom": "<'row'<'col-sm-12 col-md-6'f><'col-sm-12 col-md-6'l>>"
						+ "<'row'<'col-sm-12'tr>>"
						+ "<'row'<'col-sm-12 col-md-6 mt-2'i><'col-sm-12 col-md-6 mt-2'p>>",
				});
			});

			window.onload = function () {
				getIpAddress()
				getMerchantQuotationList()
			}

			function getMerchantQuotationList() {
				startLoadingScreen("Loading, please wait")

				var config = {
					method: 'post',
					url: contextPath + "/merchant/quotation",
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
							loadQuotationList(response.responseData)
						} else {
							alert(response.responseMessage)
						}
					})
					.catch(error => {

					})
					.finally(() => {
						stopLoadingScreen()
					})
			}

			function loadQuotationList(data) {

				const mobileQuotationContainer = document.getElementById("mobile-quotationList")
				mobileQuotationContainer.innerHTML = ""
				if (data.length > 0) {
					data.forEach(item => {

						const mobiCard = document.createElement("div")
						mobiCard.className = "mobile-card mt-4"

						const mobiCardContainer = document.createElement("div")
						mobiCardContainer.className = "mobile-card-body"


						const section1 = document.createElement("div")
						section1.className = "d-flex flex-wrap"


						const quotation = document.createElement("div")
						quotation.className = "flex-grow-1"

						const quotationTitle = document.createElement("div")
						quotationTitle.className = "caption"
						quotationTitle.innerHTML = "Quotation ID"

						const quotationValue = document.createElement("div")
						quotationValue.className = "content"
						quotationValue.innerHTML = "#" + item.id

						quotation.appendChild(quotationTitle)
						quotation.appendChild(quotationValue)


						const createdAt = document.createElement("div")
						createdAt.className = "flex-grow-1"

						const createdAtTitle = document.createElement("div")
						createdAtTitle.className = "caption"
						createdAtTitle.innerHTML = "Date & Time"

						const createdAtValue = document.createElement("div")
						createdAtValue.className = "content"
						createdAtValue.innerHTML = item.createdOn

						createdAt.appendChild(createdAtTitle)
						createdAt.appendChild(createdAtValue)



						const product = document.createElement("div")
						product.className = "flex-grow-1"

						const productTitle = document.createElement("div")
						productTitle.className = "caption"
						productTitle.innerHTML = "Product"

						const productValue = document.createElement("div")
						productValue.className = "content"
						productValue.innerHTML = item.productName

						product.appendChild(productTitle)
						product.appendChild(productValue)

						section1.appendChild(quotation)
						section1.appendChild(createdAt)
						section1.appendChild(product)

						const sectionFooter = document.createElement("div")
						sectionFooter.className = "mobile-card-footer"



						const buttonViewQuotation = document.createElement("a")
						buttonViewQuotation.className = "btn mobile-card-footer-content"

						buttonViewQuotation.innerText = "View Quotation"
						buttonViewQuotation.style.color = "white"
						buttonViewQuotation.style.width = "100%"
						buttonViewQuotation.href = contextPath + "/MerchantOrder/Quotation/" + item.id

						sectionFooter.appendChild(buttonViewQuotation)

						mobiCardContainer.appendChild(section1)
						mobiCard.appendChild(mobiCardContainer)
						mobiCard.appendChild(sectionFooter)

						mobileQuotationContainer.appendChild(mobiCard)
					});
				}
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


			function getQuotationId(quotationId) {
				getQuotationDetails(quotationId)
				startLoadingScreen("Loading, please wait")
				selectedQuotationId = quotationId
				var config = {
					method: 'post',
					url: contextPath + "/merchant/quotation/" + quotationId,
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
							showOrderLines(response.responseData)
						} else {
							alert(response.responseMessage)
						}
					})
					.catch(error => {

					})
					.finally(() => {
						stopLoadingScreen()

					})


			}


			function getQuotationDetails(quotationId) {
				selectedQuotationId = quotationId
				var config = {
					method: 'post',
					url: contextPath + "/quotation/" + quotationId,
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
							const data = response.responseData;

							const totalPrice = parseFloat(data.subTotalPrice - data.discountPrice).toFixed(2)

							document.getElementById("discountPrice").innerHTML = "RM " + parseFloat(data.discountPrice).toFixed(2)
							document.getElementById("totalPrice").innerHTML = "RM " + parseFloat(totalPrice).toFixed(2)


						} else {

						}
					})
					.catch(error => {

					})
					.finally(() => {
					})
			}


			function showOrderLines(data) {

				console.log("showOrderLines >> " + JSON.stringify(data))

				productContainer.style.visibility = "visible"
				overlay.style.display = "block"

				//document.getElementById("product-order-id").innerText = "Order ID: " + orderId
				document.getElementById("product-order-id").innerText = "Order Details"
				const productContainerRow = document.getElementById("product-container-row")
				productContainerRow.innerHTML = ""

				let totalPrice = 0

				data.orderLines.forEach(item => {

					totalPrice += parseFloat(item.product.unitPrice * item.quantity)

					var orderItem = document.createElement("div")
					orderItem.className = "col-md-6 col-sm-12"


					var productCard = document.createElement("div")
					productCard.className = "product-card"

					var firstRow = document.createElement("div")
					firstRow.className = "row"

					var priceColumn = document.createElement("div")
					priceColumn.className = "col-sm-8 col-md-8"

					var totalLable = document.createElement("p")
					totalLable.className = "title-sm"
					totalLable.innerText = "Price"

					var totalvalue = document.createElement("h6")
					totalvalue.className = "product-content"
					totalvalue.innerText = "RM  " + parseFloat(item.quantity * item.product.unitPrice) + " (RM " + parseFloat(item.product.unitPrice).toFixed(2) + " Per Unit)"// Order

					priceColumn.appendChild(totalLable)
					priceColumn.appendChild(totalvalue)

					var lineIdColumn = document.createElement("div")
					lineIdColumn.className = "col"

					var lineIdLabel = document.createElement("p")
					lineIdLabel.className = "title-sm"
					lineIdLabel.innerText = "Line ID"
					var lineIdValue = document.createElement("h6")
					lineIdValue.className = "product-content"
					lineIdValue.innerText = item.id

					lineIdColumn.appendChild(lineIdLabel)
					lineIdColumn.appendChild(lineIdValue)

					firstRow.appendChild(priceColumn)
					firstRow.appendChild(lineIdColumn)


					var secondRow = document.createElement("div")
					secondRow.className = "row mt-1"

					var productName = document.createElement("div")
					productName.className = "col-sm-8 col-md-8"

					var productNameLabel = document.createElement("p")
					productNameLabel.className = "title-sm"
					productNameLabel.innerText = "Product Name"
					var productNameValue = document.createElement("h6")
					productNameValue.className = "product-content"
					productNameValue.innerText = item.product.productName

					productName.appendChild(productNameLabel)
					productName.appendChild(productNameValue)

					var quantity = document.createElement("div")
					quantity.className = "col"

					var quantityLabel = document.createElement("p")
					quantityLabel.className = "title-sm"
					quantityLabel.innerText = "Quantity"
					var QuantityValue = document.createElement("h6")
					QuantityValue.className = "product-content"
					QuantityValue.innerText = item.quantity

					quantity.appendChild(quantityLabel)
					quantity.appendChild(QuantityValue)


					secondRow.appendChild(productName)
					secondRow.appendChild(quantity)


					productCard.appendChild(firstRow)
					productCard.appendChild(secondRow)


					orderItem.appendChild(productCard)

					productContainerRow.appendChild(orderItem)
				});
				// quotationAccepted
				if (data.quotationAccepted === 1) {
					document.getElementById("checkbox-accept-terms-conditions").disabled = true
					document.getElementById("checkbox-accept-terms-conditions").checked = true
					document.getElementById("button-accept-quotation").innerHTML = "Quotation Accepted"
				} else {
					document.getElementById("checkbox-accept-terms-conditions").disabled = false
					document.getElementById("checkbox-accept-terms-conditions").checked = false
					document.getElementById("button-accept-quotation").innerHTML = "Accept Quotation"
				}

				document.getElementById("subtotalPrice").innerText = "RM " + parseFloat(totalPrice).toFixed(2)
			}

			document.getElementById("button-download-quotation").onclick = function () {
				if (selectedQuotationId !== null) {
					downloadQuotation(selectedQuotationId)
				}
			}

			function downloadQuotation(quotationID) {
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

					})
			}

			function downloadQuotationPDF(data) {
				var url = contextPath + data.documentPath
				window.open(url)
			}

			function onCloseProductContainer() {
				productContainer.style.visibility = "collapse"
				overlay.style.display = "none"
			}

			document.getElementById("button-accept-quotation").onclick = function () {
				productContainer.style.visibility = "collapse"
				overlay.style.display = "none"

				const config = {
					method: 'post',
					url: contextPath + "/merchant/signature/" + selectedQuotationId,
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
					acceptQuotationWithSignature(selectedQuotationId, namePerIc, icNumber)
				} else {
					alert()
				}
			}

			function acceptQuotationWithSignature(selectedQuotationId, namePerIc, icNumber) {
				startLoadingScreen("Loading, please wait")
				const config = {
					method: 'post',
					url: contextPath + '/merchant/quotation/' + selectedQuotationId + '/accept',
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
				var data = JSON.stringify({
					"imageBaseString": imageString,
					"username": "123456"
				})

				var config = {
					method: 'post',
					url: 'signature/add',
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
							acceptQuotationWithSignature(selectedQuotationId, namePerIc, icNumber)
						}
					})
					.catch(function (error) {
						console.log(error);
					})
					.finally(function () {

					})

			}

			function getToken() {
				return document.getElementsByTagName('meta')['csrf-token'].getAttribute("content");
			}

			function clearSignature() {
				signaturePad.clear();
			}



		</script>
	</body>
	<style>
		/* Extra small devices (phones, 600px and down) */
		@media only screen and (max-width: 600px) {
			.product-container {
				position: fixed;
				right: 0;
				top: 0;
				z-index: 2001;
				width: 100vw;
				min-height: 100vh !important;
				height: 100vh !important;
				background-color: #fafafa;
				padding: 1.3rem;
				display: flex;
				flex-direction: column;
				box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.16);
				-webkit-box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.16);
				-moz-box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.16);
				border-radius: .2rem;
			}

			.product-footer {
				background-color: #fafafa !important;
				padding-right: 20px;
				padding-left: 20px;
				padding-top: 20px;
				padding-bottom: 30px;
				z-index: 99;
				width: 100vw;
				flex-grow: 0;
				position: fixed;
				display: block;
				bottom: 0;
				right: 0;
			}

			.title-sm {
				font-size: small;
				color: #c1c1c1;
				margin: 0px !important;
			}

			.product-content {
				font-size: medium;
				color: #5e5e5e;
			}
		}

		/* Small devices (portrait tablets and large phones, 600px and up) */
		@media only screen and (min-width: 600px) {
			.product-container {
				position: fixed;
				right: 0;
				top: 0;
				z-index: 2001;
				width: 600px;
				min-height: 100vh !important;
				height: 100vh !important;
				background-color: #fafafa;
				padding: 1.3rem;
				display: flex;
				flex-direction: column;
				box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.16);
				-webkit-box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.16);
				-moz-box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.16);
				border-radius: .2rem;
			}

			.product-footer {
				background-color: #fafafa !important;
				padding-right: 20px;
				padding-left: 20px;
				padding-top: 20px;
				padding-bottom: 30px;
				z-index: 99;
				width: inherit;
				flex-grow: 0;
				position: fixed;
				display: block;
				bottom: 0;
				right: 0;
			}

			.title-sm {
				font-size: xx-small;
				color: #c1c1c1;
				margin: 0px !important;
			}

			.product-content {
				font-size: x-small;
				color: #5e5e5e;
			}
		}



		/* Medium devices (landscape tablets, 768px and up) */
		@media only screen and (min-width: 768px) {}

		/* Large devices (laptops/desktops, 992px and up) */
		@media only screen and (min-width: 992px) {}

		/* Extra large devices (large laptops and desktops, 1200px and up) */
		@media only screen and (min-width: 1200px) {}


		#overlay {
			position: fixed;
			display: none;
			width: 100%;
			height: 100%;
			top: 0;
			left: 0;
			right: 0;
			bottom: 0;
			background-color: rgba(0, 0, 0, 0.5);
			z-index: 2000;
			cursor: pointer;
		}

		/* .product-container {
			position: absolute;
			right: 0;
			top: 0;
			z-index: 2001;
			width: auto;
			min-width: 600px;
			min-height: 100% !important;
			height: 100% !important;
			background-color: #fafafa;
			padding: 1.3rem;
			box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.16);
			-webkit-box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.16);
			-moz-box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.16);
			border-radius: .2rem;
		} */

		.product-header {
			flex-grow: 0;
		}


		.product-body {
			flex-grow: 1 !important;
			overflow-y: scroll !important;
			height: fit-content !important;
			padding-bottom: 180px;
		}


		.product-card {
			width: 100%;
			margin: 5px;
			padding: 10px;
			border-radius: 4px;
			box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.16);
			background-color: #fff;
		}

		.btn {
			border: 0px !important;
		}

		.btn-secondary {
			background-color: #b0afaf !important;
		}
	</style>

	</html>
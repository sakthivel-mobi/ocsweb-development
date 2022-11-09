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

		<%@ page isELIgnored="false" %>
	</head>


	<body>
		<main class="container mt110" id="desktop-view">
			<div class="row">
				<div class="col-md-12 col-lg-12">
					<div class="bg-white p-5 rounded">
						<form action="addQuotation" method="post">
							<div class="row">
								<div class="col-md-6 col-lg-8 col-xs-12 c5d5d5d">Credentials</div>
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
										<th>Order ID</th>
										<th>Type</th>
										<th>Date</th>
										<th>Company Name</th>
										<th>Credentials</th>
									</tr>
								</thead>
								<tbody class="" id="desktop-credentials-list">

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</main>

		<div class="container" style="margin-top: 5rem;" id="mobile-view">
			<h4>Credentials</h4>
			<div id="mobile-orderList"></div>
		</div>


	</body>

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
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
		var selectedQuotationId = null

		const productContainer = document.getElementById("product-container")
		const overlay = document.getElementById("overlay")



		window.onload = function () {
			getCredentialsList()
		}

		$(document).ready(function () {
			// document.getElementById("button-accept-orderItem").disabled = true
			$('#table').DataTable({
				"order": [[0, 'desc']],
				"dom": "<'row'<'col-sm-12 col-md-6'f><'col-sm-12 col-md-6'l>>"
					+ "<'row'<'col-sm-12'tr>>"
					+ "<'row'<'col-sm-12 col-md-6 mt-2'i><'col-sm-12 col-md-6 mt-2'p>>",
			});
		});



		function getCredentialsList() {
			startLoadingScreen("Loading, please wait")
			var config = {
				method: 'post',
				url: contextPath + "/merchant/credentials",
				headers: {
					'X-CSRF-TOKEN': token,
					'Content-Type': 'application/json'
				}
			}

			axios(config)
				.then(response => {
					console.log(response.data)
					return response.data
				})
				.then(response => {
					if (response.responseStatus === "0000") {
						showCredentialsList(response.responseData)
						loadOrderList(response.responseData)
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


		function showCredentialsList(credentialsList) {

			const tableBody = document.getElementById("desktop-credentials-list")
			tableBody.innerHTML = ""

			credentialsList.forEach(element => {

				const row = document.createElement("tr")


				const orderId = document.createElement("td")
				orderId.innerText = element.orderId


				const type = document.createElement("td")
				type.innerText = element.type

				const date = document.createElement("td")
				date.innerText = element.date


				const companyName = document.createElement("td")
				companyName.innerText = element.companyName

				row.append(orderId)
				row.append(type)
				row.append(date)
				row.append(companyName)


				const buttonData = document.createElement("td")

				const viewCredentials = document.createElement("a")

				if (element.showCredentials === true) {
					viewCredentials.href = (contextPath + "/MerchantOrder/Credential/" + element.orderId + "?quotationId=" + element.quotationId)

					const button = document.createElement("button")
					button.className = "btn btn-mobi btn-sm"
					button.innerText = "View Credentials"
					button.style.width = "100%"

					viewCredentials.append(button)

					buttonData.append(viewCredentials)
				} else {
					const button = document.createElement("button")
					button.className = "btn btn-not-available btn-sm"
					button.disabled
					button.innerHTML = "Credentials Not Available"
					viewCredentials.append(button)

					buttonData.append(viewCredentials)
				}

				row.append(buttonData)
				tableBody.appendChild(row)
			});

		}
		function loadOrderList(data) {
			console.log(data)
			const mobileOrderContainer = document.getElementById("mobile-orderList")
			mobileOrderContainer.innerHTML = ""
			if (data.length > 0) {
				data.forEach(item => {
					const mobiCard = document.createElement("div")
					mobiCard.className = "mobile-card mt-4"

					const mobiCardContainer = document.createElement("div")
					mobiCardContainer.className = "mobile-card-body"


					const section1 = document.createElement("div")
					section1.className = "d-flex flex-wrap"


					const orderItem = document.createElement("div")
					orderItem.className = "flex-grow-1"

					const orderTitle = document.createElement("div")
					orderTitle.className = "caption"
					orderTitle.innerHTML = "Quotation ID"

					const orderValue = document.createElement("div")
					orderValue.className = "content"
					orderValue.innerHTML = "#" + item.orderId

					orderItem.appendChild(orderTitle)
					orderItem.appendChild(orderValue)


					const createdAt = document.createElement("div")
					createdAt.className = "flex-grow-1"

					const createdAtTitle = document.createElement("div")
					createdAtTitle.className = "caption"
					createdAtTitle.innerHTML = "Date & Time"

					const createdAtValue = document.createElement("div")
					createdAtValue.className = "content"
					createdAtValue.innerHTML = item.date

					createdAt.appendChild(createdAtTitle)
					createdAt.appendChild(createdAtValue)


					const product = document.createElement("div")
					product.className = "flex-grow-1"

					const productTitle = document.createElement("div")
					productTitle.className = "caption"
					productTitle.innerHTML = "Company Name"

					const productValue = document.createElement("div")
					productValue.className = "content"
					productValue.innerHTML = item.companyName

					product.appendChild(productTitle)
					product.appendChild(productValue)

					section1.appendChild(orderItem)
					section1.appendChild(createdAt)
					section1.appendChild(product)

					const sectionFooter = document.createElement("div")
					sectionFooter.className = "mobile-card-footer"



					const buttonViewOrder = document.createElement("a")
					buttonViewOrder.className = "btn mobile-card-footer-content"

					const disabled = document.createAttribute('disabled')

					if (item.showCredentials === true) {
						buttonViewOrder.href = (contextPath + "/MerchantOrder/Credential/" + item.orderId + "?quotationId=" + item.quotationId)
						buttonViewOrder.innerText = "View Credentials"
					} else {
						buttonViewOrder.setAttribute('readonly', true);
						buttonViewOrder.innerText = "Credentials Not Available"
					}


					buttonViewOrder.style.color = "white"
					buttonViewOrder.style.width = "100%"

					sectionFooter.appendChild(buttonViewOrder)

					mobiCardContainer.appendChild(section1)
					mobiCard.appendChild(mobiCardContainer)
					mobiCard.appendChild(sectionFooter)

					mobileOrderContainer.appendChild(mobiCard)
				});
			}
		}



		function viewCredentials(orderId, quotationId) {
			location.replace(contextPath + "/MerchantOrder/Credential/" + orderId + "?quotationId=" + quotationId);
		}


	</script>
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

		.btn:hover {
			color: white;
		}

		.btn-mobi {
			color: #fff;
			background-color: #005baa !important;
			border-color: #005baa !important;
		}

		.btn-not-available {
			color: #fff !important;
			border: 0 !important;
			background-color: #617075 !important;
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
		@media only screen and (min-width: 992px) {
			#desktop-view {
				display: block;
			}

			#mobile-view {
				display: none;
			}
		}

		/* Extra large devices (large laptops and desktops, 1200px and up) */
		@media only screen and (min-width: 1200px) {
			#desktop-view {
				display: block;
			}

			#mobile-view {
				display: none;
			}
		}
	</style>

	</html>
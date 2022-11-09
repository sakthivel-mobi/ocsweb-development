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

			<!-- CDN moved to local -->
			<link rel="stylesheet" type="text/css"
				href="${pageContext.request.contextPath}/resources/js/fontawesome/css/fontawesome.min.css">
			<script src="${pageContext.request.contextPath}/resources/js/axios.js" type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/resources/js/moment.js" type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/resources/js/xlsx.js" type="text/javascript"></script>
			<script src="${pageContext.request.contextPath}/resources/js/loading.js" type="text/javascript"></script>
			<!-- CDN moved to local -->
			<link rel="stylesheet" type="text/css"
				href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
			<link rel="stylesheet" type="text/css"
				href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap5.min.css">
			<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/fonts/fonts.css">

			<meta id="_csrf" name="_csrf" content="${_csrf.token}" />
			<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}" />

			<%@ page isELIgnored="false" %>
		</head>

		<body>

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
			</style>

			<main class="container mt110">
				<div class="row">
					<div class="col-md-12 col-lg-12">
						<div class="bg-white p-5 rounded">

							<div class="row">
								<div class="col-md-3 col-lg-12 col-xs-12 c5d5d5d">Merchant Registration</div>
								<div class="col-md-9 col-lg-12 col-xs-12 ">
									<div class="row">
										<div class="col-4">
											<select class="form-select" name="orderId" id="orderId">
												<option value="0">---------Select----------</option>
												<c:forEach items="${pendingDeployementOrders}" var="orders">
													<option value="${orders.id}">${orders.id} -
														${orders.merchantTradingName}</option>
												</c:forEach>
											</select>
										</div>
										<div class="col-8">
											<input type="button" onclick="addMDROrder()" class="btn btn-primary"
												value="Add">

											<span>|</span>

											<button class="btn btn-primary" onclick="getGrabIdReport()">Get Grab Pay
												Report</button>
										</div>
									</div>
								</div>
							</div>

							<hr />

							<div class="table-responsive">
								<table id="table" class="table">
									<thead>
										<tr class="bcf4f4f4 c617075 fw500">
											<th>Registration ID</th>
											<th>Date</th>
											<th>Company Name</th>
											<th>Stage</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody class="c3f3f3f fw500">
										<c:forEach var="reg" items="${registrationOrders}">

											<c:url var="viewLink" value="registrationView">
												<c:param name="regId" value="${reg.id}"></c:param>
												<c:param name="registerMessage" value="0"></c:param>
											</c:url>

											<tr>
												<td>${reg.id}</td>
												<td>${reg.createdOn}</td>
												<td>${reg.order.merchantTradingName}</td>
												<td>${reg.order.quotation.stage}</td>
												<td><a href="${viewLink}" class="btn btn-mobi btn-sm">View</a></td>
											</tr>

										</c:forEach>
									</tbody>
								</table>
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



			<script>
				var token = $("meta[name='_csrf']").attr("content")
				var header = $("meta[name='_csrf_header']").attr("content")
				var contextPath = "${pageContext.request.contextPath}"

				$(document).ready(function () {
					$('#table').DataTable({
						"order": [[0, 'desc']],
						"dom": "<'row'<'col-sm-12 col-md-6'f><'col-sm-12 col-md-6'l>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-12 col-md-6 mt-2'i><'col-sm-12 col-md-6 mt-2'p>>",
					}
					);

				});

				function addMDROrder() {
					// addRegOrder
					const orderId = document.getElementById("orderId").value

					if (parseInt(orderId) !== 0) {

						var config = {
							method: 'get',
							url: contextPath + "/merchantReg/addRegOrder",
							headers: {
								'X-CSRF-TOKEN': token,
								'Content-Type': 'application/json'
							},
							params: {
								"orderId": orderId
							}
						}


						axios(config)
							.then(response => {
								return response.data
							})
							.then(response => {
								alert(response.responseMessage)
							})
							.catch(error => {

							})
							.finally(() => {
								window.location.reload()
							})
					}
				}

				function getGrabIdReport() {
					startLoadingScreen("Generating Excel File, please wait...")

					var config = {
						method: 'POST',
						url: contextPath + "/merchant/registration/grabpay/report",
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
							console.log(JSON.stringify(response))
							if (response.responseStatus === "0000") {
								if (response.responseData.length > 0) {
									exportJsonToExcel(response.responseData)
								}
							}
						})
						.catch(error => {
							console.log(error)
						})
						.finally(() => {
							stopLoadingScreen()
						})
				}

				function exportJsonToExcel(jsonDataSet) {
					let filename = 'GrapPayReport_' + moment().format('DD_MM_YYYY') + '.xlsx';
					var ws = XLSX.utils.json_to_sheet(jsonDataSet);

					var wb = XLSX.utils.book_new();

					// let Heading = [
					// 	["ORDER ID", "Merchant Registered Name", "Merchant Trading Name",
					// 		"Business Registration Number*", "Business Category (MCC)*",
					// 		"MCC", "Trading Address Line 1 *", "Province*", "City*",
					// 		"Postal Code*", "Geolocation Latitude*", "Geolocation Longitude*",
					// 		"Terminal id required", "TID"]
					// ];

					// XLSX.utils.sheet_add_aoa(ws, Heading);

					XLSX.utils.book_append_sheet(wb, ws, "GrabPay");
					XLSX.writeFile(wb, filename);
				}
			</script>

		</body>

		</html>
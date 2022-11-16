<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<!-- CDN moved to local -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/js/fontawesome/css/fontawesome.min.css">
<script src="${pageContext.request.contextPath}/resources/js/axios.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/moment.js"
	type="text/javascript"></script>
<!-- CDN moved to local -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap5.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/fonts/fonts.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap5.min.js"></script>


<%@ page isELIgnored="false"%>
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
					<form action="addQuotation" method="post">
						<div class="row">
							<div class="col-md-6 col-lg-8 col-xs-12 c5d5d5d">Orders</div>
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
									<th>Date</th>
									<th>Type</th>
									<th>Company Name</th>
									<th>Sales Person</th>
									<th>Phone</th>
									<th>Product</th>
									<th>Stage</th>
									<!-- <th>Price</th>	 -->
									<th style="text-align: center;">Action</th>
								</tr>
							</thead>
							<tbody class="c3f3f3f fw500">
								<c:forEach var="tempOrder" items="${orders}">
									<c:if test="${tempOrder.quotation.acquirer =='Paydee'}">
										<c:url var="viewLink" value="/Order/OrderViewPaydee">
											<c:param name="id" value="${tempOrder.id}"></c:param>
										</c:url>

									</c:if>

									<c:if test="${tempOrder.quotation.acquirer =='U-Mobile'}">
										<c:url var="viewLink" value="/Order/OrderView">
											<c:param name="id" value="${tempOrder.id}"></c:param>
										</c:url>
									</c:if>

									<c:choose>
										<c:when
											test="${tempOrder.quotation.orderLines[1].product.productType == 'EZYSPLIT'}">
											<c:set var="price" scope="session"
												value="${tempOrder.quotation.orderLines[0].quotationEzysplitMDRRate.unitPrice}" />
										</c:when>
										<c:otherwise>
											<c:set var="price" scope="session"
												value="${tempOrder.quotation.orderLines[0].quotationMDRRate.unitPrice}" />
										</c:otherwise>
									</c:choose>


									<tr>
										<td>${tempOrder.id}</td>
										<fmt:parseDate value="${tempOrder.createdOn}"
											pattern="yyyy-MM-dd HH:mm:ss" var="myDate" />
										<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss"
												value="${myDate}" /></td>
										<%-- <td>${tempOrder.createdOn}</td> --%>
										<td>${tempOrder.quotation.orderType}</td>
										<td>${tempOrder.quotation.companyName}</td>
										<td>${tempOrder.quotation.salesPerson.aliasName}</td>
										<td>${tempOrder.quotation.userId}</td>
										<td>${tempOrder.quotation.orderLines[0].product.productType}</td>
										<td>${tempOrder.quotation.stage}</td>
										<!-- <td>
													<fmt:formatNumber type="number" maxFractionDigits="2"
														minFractionDigits="2" value="${price}" />
													</td> -->
										<td style="text-align: center;"><c:if
												test="${tempOrder.quotation.acquirer == 'Paydee'}">
												<a
													href="${pageContext.request.contextPath}/Order/OrderViewPaydee?id=${tempOrder.id}"
													class="btn btn-mobi btn-sm">View</a>
											</c:if> <c:if test="${tempOrder.quotation.acquirer == 'U-Mobile'}">
												<a
													href="${pageContext.request.contextPath}/Order/OrderView?id=${tempOrder.id}"
													class="btn btn-mobi btn-sm">View</a>
											</c:if> <c:if
												test="${tempOrder.quotation.acquirer != 'U-Mobile' && tempOrder.quotation.acquirer != 'Paydee'}">
														Acquirer Details not available
													</c:if></td>
									</tr>

								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</main>
</body>
<script>
			var contextPath = "${pageContext.request.contextPath}"
			var token = $("meta[name='_csrf']").attr("content")
			var header = $("meta[name='_csrf_header']").attr("content")

			$(document)
				.ready(
					function () {
						$('#table')
							.DataTable(
								{
									"order": [[0, 'desc']],
									"dom": "<'row'<'col-sm-12 col-md-6'f><'col-sm-12 col-md-6'l>>"
										+ "<'row'<'col-sm-12'tr>>"
										+ "<'row'<'col-sm-12 col-md-6 mt-2'i><'col-sm-12 col-md-6 mt-2'p>>",
								});
					});

			window.onload = function () {
				getOrderList()
			}

			function getOrderList() {
				const config = {
					method: "post",
					url: contextPath + "/order/list",
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
							console.log(response)
						}
					})
					.catch(error => {
						console.log(error)
					})
			}
		</script>

</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
		<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
				<link rel="stylesheet" type="text/css"
					href="${pageContext.request.contextPath}/resources/fonts/fonts.css">

				<script type="text/javascript"
					src="${pageContext.request.contextPath}/resources/js/loading.js"></script>



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
								<div class="hubspotForm">

									<div class="row">
										<div class="col-md-6 col-lg-8 col-xs-12 c5d5d5d">Quotation</div>
										<div class="col-md-6 col-lg-4 col-xs-12 ">
											<div class="row">
												<div class="col-7"><input type="text" id="dealId"
														class="bf7f7f7 form-control form-control-sm " name="dealId"
														placeholder="Enter Deal ID"></div>
												<div class="col-5"><input type="submit"
														class="btn btn-mobi btn-sm float-end addQuotation"
														value="Fetch Quotation"></div>
											</div>
										</div>
									</div>
								</div>


								<%-- <div class="kanbanForm">
									<form:form action="addQuotationFromKanban" method="post">
										<div class="row">
											<div class="col-md-6 col-lg-8 col-xs-12 c5d5d5d">Quotation</div>
											<div class="col-md-6 col-lg-4 col-xs-12 ">
												<div class="row">
													<div class="col-7"><input type="text" id="boardId"
															class="bf7f7f7 form-control form-control-sm "
															placeholder="Enter Board ID"></div>
													<div class="col-7"><input type="text" id="cardId"
															class="bf7f7f7 form-control form-control-sm "
															placeholder="Enter Card ID"></div>
													<div class="col-5"><input type="submit"
															class="btn btn-mobi btn-sm float-end addQuotationFromKanban"
															value="Fetch Quotation"></div>
												</div>
											</div>
										</div>
									</form:form>
							</div> --%>


							<hr />

							<div class="table-responsive">
								<table id="table" class="table">
									<thead>
										<tr class="bcf4f4f4 c617075 fw500">
											<th>Quote ID</th>
											<th>Deal ID</th>
											<th>Date</th>
											<th>Type</th>
											<th>Company Name</th>
											<th>Sales Person</th>
											<th>Phone</th>
											<th>Description</th>
											<th>Stage</th>
											<!-- <th>Total</th> -->
											<th>Action</th>
										</tr>
									</thead>
									<tbody class="c3f3f3f fw500">
										<c:forEach var="tempQuotation" items="${quotations}">

											<c:url var="viewLink" value="/Quotation/QuotationView">
												<c:param name="id" value="${tempQuotation.id}"></c:param>
											</c:url>

											<c:choose>
												<c:when
													test="${tempOrder.quotation.orderLines[0].product.productType == 'EZYSPLIT'}">
													<c:set var="price" scope="session"
														value="${tempOrder.quotation.orderLines[0].quotationEzysplitMDRRate.unitPrice}" />
												</c:when>
												<c:otherwise>
													<c:set var="price" scope="session"
														value="${tempOrder.quotation.orderLines[0].quotationMDRRate.unitPrice}" />
												</c:otherwise>
											</c:choose>

											<tr>
												<td>${tempQuotation.id}</td>
												<td>${tempQuotation.dealID}</td>
												<td>${tempQuotation.createdOn}</td>
												<%-- <td>${tempQuotation.orderType.name}</td> --%>
													<td>${tempQuotation.orderType}</td>
													<td>${tempQuotation.companyName}</td>
													<td>${tempQuotation.salesPerson.aliasName}</td>
													<td>${tempQuotation.userId}</td>


													<td>${tempQuotation.orderLines[0].product.productType}</td>
													<td>${tempQuotation.stage}</td>
													<%-- <td>
														<fmt:formatNumber type="number" maxFractionDigits="2"
															minFractionDigits="2" value="${price}" />
														</td> --%>
														<td><a href="${viewLink}" class="btn btn-mobi btn-sm">View</a>
														</td>
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
					var contextPath = "${pageContext.request.contextPath}"

					$(document).ready(function () {
						$('#table').DataTable({
							"order": [[0, 'desc']],
							"dom": "<'row'<'col-sm-12 col-md-6'f><'col-sm-12 col-md-6'l>>" + "<'row'<'col-sm-12'tr>>" + "<'row'<'col-sm-12 col-md-6 mt-2'i><'col-sm-12 col-md-6 mt-2'p>>",
						}
						);
					});

					$(".addQuotation").click(function (e) {
						startLoadingScreen("Loading, please wait...");
						e.preventDefault();
						$.ajax({
							type: "GET",
							url: "addQuotation",
							data: {
								//access_token: $("#access_token").val(),
								dealId: $('#dealId').val()
							},
							success: function (result) {
								console.log(result);
								stopLoadingScreen()
								alert(result);
								location.reload(true);
							},
							error: function (result) {
								stopLoadingScreen()
								alert('error');
							}
						});

					});

					$(".addQuotationFromKanban").click(function (e) {
						startLoadingScreen("Loading, please wait...");
						e.preventDefault();
						$.ajax({
							type: "GET",
							url: "addQuotationFromKanban",
							data: {
								boardId: $('#boardId').val(),
								cardId: $('#cardId').val()
							},
							success: function (result) {
								console.log(result);
								stopLoadingScreen()
								alert(result);
								location.reload(true);
							},
							error: function (result) {
								stopLoadingScreen()
								alert('error');
							}
						});

					});
				</script>

			</body>

			</html>
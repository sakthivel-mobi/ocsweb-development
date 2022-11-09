<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!doctype html>
	<html lang="en">

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="description" content="">
		<meta name="author" content="">
		<title>Products</title>


		<link rel="apple-touch-icon" sizes="57x57"
			href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-57x57.png">
		<link rel="apple-touch-icon" sizes="60x60"
			href="${pageContext.request.contextPath}/resources/images/favicon/apple-icon-60x60.png">
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
	</head>

	<body>





		<nav class="navbar navbar-expand-md navbar-dark b005baa">
			<div class="container-fluid">
				<a class="navbar-brand" href="#"> <img height="40"
						src="${pageContext.request.contextPath}/resources/images/mobi_logo.svg">
				</a>
				<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
					aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
			</div>
		</nav>


		<nav class="navbar navbar-expand-md navbar-dark ffixed-top bg-white shadow ">
			<div class="container-fluid">
				<div class="collapse navbar-collapse" id="navbarCollapse">
					<ul class="navbar-nav me-auto mb-2 mb-md-0">
						<li class="nav-item"><a class="nav-link c787f8c" aria-current="page" href="#">Dashboard</a></li>
						<li class="nav-item"><a class="nav-link c787f8c" aria-current="page" href="#">Admin</a></li>
						<li class="nav-item"><a class="nav-link c787f8c" aria-current="page" href="#">Leads</a></li>
						<li class="nav-item"><a class="nav-link c787f8c" aria-current="page" href="#">Opportunities</a>
						</li>
						<li class="nav-item"><a class="nav-link c787f8c" aria-current="page" href="#">Quotation</a></li>
						<li class="nav-item"><a class="nav-link c787f8c" aria-current="page" href="#">My Actions</a>
						</li>

						<li class="nav-item"><a class="nav-link c787f8c" aria-current="page" href="#">Schedule 1</a>
						</li>
						<li class="nav-item"><a class="nav-link c787f8c" aria-current="page" href="#">TPA File</a></li>
						<li class="nav-item"><a class="nav-link c787f8c" aria-current="page" href="#">Merchant
								Registration</a></li>


					</ul>




				</div>
			</div>
		</nav>
		<main class="container mt50">
			<div class="row">
				<div class="col-md-12 col-lg-12">
					<div class="bg-white p-5 rounded">

						<div class="row">
							<div class="col-md-6 col-lg-8 col-xs-12 c5d5d5d">Quotation</div>
							<div class="col-md-6 col-lg-4 col-xs-12 ">
								<div class="row">
									<div class="col-12">
										<input type="submit" class="btn btn-mobi btn-sm float-end"
											placeholder="Enter Deal ID" value="Add Product">
									</div>
								</div>
							</div>
						</div>
						<hr />
						<ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
							<li class="nav-item" role="presentation">
								<button class="nav-link active" id="pills-Products-tab" data-bs-toggle="pill"
									data-bs-target="#pills-Products" type="button" role="tab"
									aria-controls="pills-Products" aria-selected="true">Products</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="pills-Host-tab" data-bs-toggle="pill"
									data-bs-target="#pills-Host" type="button" role="tab" aria-controls="pills-Host"
									aria-selected="false">Host
									Rate</button>
							</li>
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="pills-Pending-tab" data-bs-toggle="pill"
									data-bs-target="#pills-Pending" type="button" role="tab"
									aria-controls="pills-Pending" aria-selected="false">Pending Approvals</button>
							</li>
						</ul>
						<div class="tab-content" id="pills-tabContent">
							<div class="tab-pane fade show active" id="pills-Products" role="tabpanel"
								aria-labelledby="pills-Products-tab">


								<div class="table-responsive">
									<table id="table" class="table">
										<thead>
											<tr class="bcf4f4f4 c617075 fw500">
												<th>Product ID</th>
												<th>Product Name</th>
												<th>Product Type</th>
												<th>Host Type</th>
												<th>Unit Price</th>
												<th>Profit (Mobi/Merch)</th>
												<th>Valid From</th>
												<th>Valid To</th>
											</tr>
										</thead>
										<tbody class="c3f3f3f fw500">

											<c:forEach var="tempProduct" items="${productList}">

												<c:url var="viewLink" value="ProductView">
													<c:param name="id" value="${tempProduct.id}"></c:param>
												</c:url>
												<tr>
													<td>${tempProduct.id}</td>
													<td>${tempProduct.productName}</td>
													<td>${tempProduct.id}</td>
													<td>27/8/2021 20:03:54</td>
													<td>ADMIN</td>
													<td>0.00%</td>
													<td>1/1/0001</td>
													<td>1/1/0001</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>

							</div>
							<div class="tab-pane fade" id="pills-Host" role="tabpanel" aria-labelledby="pills-Host-tab">
								CONTENT GOES HERE</div>
							<div class="tab-pane fade" id="pills-Pending" role="tabpanel"
								aria-labelledby="pills-Pending-tab">CONTENT GOES HERE</div>
						</div>




					</div>
				</div>
			</div>
		</main>



		<!-- <div class="input-group">
                <span class="input-group-prepend">
                    <div class="input-group-text bg-transparent border-right-0"><i class="fa fa-search"></i></div>
                </span>
                <input class="form-control py-2 border-left-0 border" type="search" value="..." id="example-search-input">
                <span class="input-group-append"></span>
            </div>-->


		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript"
			src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap5.min.js"></script>

		<script>
			$(document)
				.ready(
					function () {
						$('#table')
							.DataTable(
								{
									"dom": "<'row'<'col-sm-12 col-md-6'f><'col-sm-12 col-md-6'l>>"
										+ "<'row'<'col-sm-12'tr>>"
										+ "<'row'<'col-sm-12 col-md-6 mt-2'i><'col-sm-12 col-md-6 mt-2'p>>",
								});
					});
		</script>


	</body>

	</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>Approvals</title>

		<link rel="icon" type="image/x-icon"
			href="${pageContext.request.contextPath}/resources/images/favicon/favicon.ico">

		<script src="${pageContext.request.contextPath}/resources/js/signaturePad.js" type="text/javascript"></script>


		<link rel="manifest" href="/manifest.json">
		<meta name="msapplication-TileColor" content="#ffffff">
		<meta name="msapplication-TileImage" content="../resources/images/favicon/ms-icon-144x144.png">
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
		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>


		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/loading.js"></script>

		<%@ page isELIgnored="false" %>
	</head>

	<body id="approval-body" class="container">
		<main class="container mt110"
			style="margin-top: 5rem; background-color: white; min-height: 60vh; height: 100%; width: 100%; padding: 1rem;">


			<ul class="nav nav-tabs" id="myTab" role="tablist">
				<li class="nav-item" role="presentation">
					<button class="nav-link active" id="approval-tab" data-bs-toggle="tab"
						data-bs-target="#approval-tab-pane" type="button" role="tab" aria-controls="approval-tab-pane"
						aria-selected="true">Approvals</button>
				</li>
				<li class="nav-item" role="presentation">
					<button class="nav-link" id="quotation-tab" data-bs-toggle="tab"
						data-bs-target="#quotation-tab-pane" type="button" role="tab" aria-controls="quotation-tab-pane"
						aria-selected="false">Quotation</button>
				</li>
				<!-- <li class="nav-item" role="presentation">
					<button class="nav-link" id="contact-tab" data-bs-toggle="tab" data-bs-target="#contact-tab-pane"
						type="button" role="tab" aria-controls="contact-tab-pane" aria-selected="false">Contact</button>
				</li>
				<li class="nav-item" role="presentation">
					<button class="nav-link" id="disabled-tab" data-bs-toggle="tab" data-bs-target="#disabled-tab-pane"
						type="button" role="tab" aria-controls="disabled-tab-pane" aria-selected="false"
						disabled>Disabled</button>
				</li> -->
			</ul>
			<hr>

			<div class="tab-content" id="myTabContent">
				<div class="tab-pane fade show active" id="approval-tab-pane" role="tabpanel"
					aria-labelledby="approval-tab" tabindex="0">
					<div class="m-3">
						<h4>Add new sales person</h4>
						<div class="error-message">All filed are required!</div>
						<form onsubmit="requestApproval()">
							<div class="row">
								<div class="col-sm-12 col-md-3">
									<div class="mb-3">
										<label for="input-name" class="form-label">Name</label>
										<input type="name" class="form-control" value="" id="input-name" placeholder="">
									</div>
								</div>
								<div class="col-sm-12 col-md-3">
									<div class="mb-3">
										<label for="input-alias" class="form-label">Alias</label>
										<input type="name" class="form-control" value="" id="input-alias"
											placeholder="">
									</div>
								</div>
								<div class="col-sm-12 col-md-3">
									<div class="mb-3">
										<label for="input-email" class="form-label">Email</label>
										<input type="email" class="form-control" value="" id="input-email"
											placeholder="">
									</div>
								</div>
								<div class="col-sm-12 col-md-3">
									<div class="mb-3">
										<label for="input-phone" class="form-label">Phone</label>
										<input class="form-control" list="input-phone-list" type="tel"
											oninput="onPhoneNumberChange(1)" id="input-phone" value=""
											placeholder="Type to search...">
										<datalist id="input-phone-list">
										</datalist>
									</div>
								</div>
								<div class="col-sm-12 col-md-3">
									<div class="mb-3">
										<label for="input-nric" class="form-label">NRIC</label>
										<input class="form-control" type="text" value="" id="input-nric" placeholder="">
									</div>
								</div>
								<div class="col-sm-12 col-md-3">
									<div class="mb-3">
										<label for="input-access-required" class="form-label">Access Required</label>
										<select class="form-select" id="input-access-required"
											aria-label="Default select example">
											<option selected value="">Select an Request</option>
											<option value="Sales">Sales</option>
											<option value="Finance">Finance</option>
											<option value="Risk">Risk</option>
											<option value="Processing">Processing</option>
										</select>
									</div>
								</div>
							</div>
							<input class="btn btn-primary btn-sm" type="submit" value="Submit Request">
						</form>
					</div>

					<hr>
					<div class="m-3">
						<h4>Change user access</h4>
						<form onsubmit="updateAccess()">
							<div class="row">

								<div class="col sm-12 col-md-3">
									<div class="mb-3">
										<label for="input-authority-phone" class="form-label">Phone</label>
										<input class="form-control" list="input-authority-phone-list" type="tel"
											oninput="onPhoneNumberChange(2)" id="input-authority-phone" value=""
											placeholder="Type to search...">
										<datalist id="input-authority-phone-list">
										</datalist>
									</div>
								</div>

								<div class="col-sm-12 col-md-3">
									<div class="mb-3">
										<label for="input-access-required" class="form-label">Access Required</label>
										<select class="form-select" id="input-authority-access-required"
											aria-label="Default select example">
											<option selected value="">Select an Request</option>
											<option value="ROLE_SALES">Sales</option>
											<option value="ROLE_SALES-MANAGER">Sales Manager</option>
											<option value="ROLE_FINANCE">Finance</option>
											<option value="ROLE_FINANCE-MANAGER">Finance Manager</option>
											<option value="ROLE_RISK">Risk</option>
											<option value="ROLE_PROCESSING">Processing</option>
											<option value="ROLE_MERCHANT">Merchant</option>
										</select>
									</div>
								</div>
							</div>
							<button class="btn btn-primary btn-sm" type="submit">Update Access</button>
						</form>
					</div>
				</div>
				<div class="tab-pane fade" id="quotation-tab-pane" role="tabpanel" aria-labelledby="quotation-tab"
					tabindex="0">
					<div class="m-3">
						<h4>Change sales person for quotation</h4>
						<div class="row">
							<div class="col-sm-12 col-md-3">
								<div class="mb-3">
									<input type="number" class="form-control" placeholder="Quotation ID"
										id="quotationId">
								</div>
							</div>
							<div class="col-sm-12 col-md-3">
								<div class="mb-3">
									<select name="" class="form-select" id="sales-select-person-list"
										placeholder="Sale Person">
										<option value="0" selected>Select an sales person</option>
									</select>
								</div>
							</div>
							<div class="col-sm-12 col-md-3">
								<div class="mb-3">
									<button class="btn btn-primary btn-sm" onclick="changeSalesPerson()">Change sales
										person</button>
								</div>
							</div>
						</div>

						<hr>
						<h4>Change quotation stage</h4>

						<div class="row mt-3">
							<div class="col-3">
								<input type="text" class="form-control" placeholder="Quotation ID" id="id-quotation-id">
							</div>
							<div class="col-3">
								<select name="" class="form-select" id="id-stage">
									<option value="sales">Sales</option>
									<option value="finance">Finance</option>
									<option value="processing">Processing</option>
									<option value="risk">Risk</option>
									<option value="umobile">U Mobile</option>
								</select>
							</div>
							<div class="col-3">
								<button class="btn btn-primary btn-sm" onclick="changeQuotationStage()">
									Search Quotation
								</button>
							</div>
						</div>
					</div>

				</div>
				<div class="tab-pane fade" id="contact-tab-pane" role="tabpanel" aria-labelledby="contact-tab"
					tabindex="0">...</div>
				<div class="tab-pane fade" id="disabled-tab-pane" role="tabpanel" aria-labelledby="disabled-tab"
					tabindex="0">...</div>
			</div>

		</main>
	</body>
	<style>
		.error-message {
			font-size: small;
			color: red;
		}
	</style>

	<script>
		var contextPath = "${pageContext.request.contextPath}"
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");


		function changeQuotationStage() {
			const quotationId = document.getElementById("id-quotation-id").value
			const stage = document.getElementById("id-stage").value

			startLoadingScreen("Processing, please wait...")
			var config = {
				method: 'PUT',
				url: contextPath + '/quotation/stage',
				headers: {
					'X-CSRF-TOKEN': token,
					'Content-Type': 'application/json'
				},
				data: JSON.stringify({
					"quotationId": quotationId,
					"stage": stage
				})
			}

			axios(config)
				.then(response => {
					return response.data
				})
				.then(response => {
					alert(response.responseMessage)
				})
				.catch((error) => {
					console.log(error)
				})

		}

		function onPhoneNumberChange(flag) {
			var phoneNumber = event.target.value
			if (phoneNumber.length >= 3) {
				getPhoneAutoComplete(phoneNumber, flag)
			}
		}

		window.onload = function () {
			getSalesperson()
		}

		function getSalesperson() {
			event.preventDefault();

			var config = {
				method: 'POST',
				url: contextPath + '/quotation/salesPerson/list',
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
						showSalesPersonList(response.responseData)
					} else {

					}
				})
				.catch((error) => {
					console.log(error)
				})
		}


		function changeSalesPerson() {
			event.preventDefault();
			const quotationId = document.getElementById("quotationId").value
			const salesPersonId = document.getElementById("sales-select-person-list").value

			if (quotationId !== null && quotationId !== '' && salesPersonId !== 0) {
				startLoadingScreen("Updating, please wait...")
				var config = {
					method: 'POST',
					url: contextPath + '/quotation/salesPerson/update',
					headers: {
						'X-CSRF-TOKEN': token,
						'Content-Type': 'application/json'
					},
					params: {
						quotationId: quotationId,
						salesPersonId: salesPersonId
					}
				}

				axios(config)
					.then(response => {
						return response.data
					})
					.then(response => {
						alert(response.responseMessage)
					})
					.catch((error) => {
						console.log(error)
					})
					.finally(() => {
						window.location.reload();
						stopLoadingScreen()
					})
			}
		}

		function showSalesPersonList(salesPersonList) {

			const salesSelectPersonList = document.getElementById("sales-select-person-list")

			salesPersonList.forEach((item) => {

				const option = document.createElement("option")
				option.value = item.id
				option.innerText = item.aliasName

				salesSelectPersonList.appendChild(option)
			})
		}

		function updateAccess() {
			event.preventDefault()
			const userName = document.getElementById("input-authority-phone").value.trim()
			const userRole = document.getElementById("input-authority-access-required").value.trim()

			if (userName === null || userName === '' || userRole === null || userRole === '') {
				alert("Enter User name and select user role")
			} else {
				startLoadingScreen("Processing, please wait...")
				var config = {
					method: 'POST',
					url: contextPath + '/user/authorities/update',
					headers: {
						'X-CSRF-TOKEN': token,
						'Content-Type': 'application/json'
					},
					data: {
						userName: userName,
						userRole: userRole
					}
				}

				axios(config)
					.then(response => {
						return response.data
					})
					.then(response => {
						alert(response.responseMessage)
					})
					.catch((error) => {
						console.log(error)
					})
					.finally(() => {
						stopLoadingScreen()
						window.location.reload()
					})
			}

		}

		function getPhoneAutoComplete(mobile, flag) {

			var config = {
				method: 'POST',
				url: contextPath + '/user/approval/phone/search',
				headers: {
					'X-CSRF-TOKEN': token,
					'Content-Type': 'application/json'
				},
				data: {
					phoneNumber: mobile
				}
			}

			this.axios(config)
				.then(function (response) {
					return response.data
				})
				.then(function (response) {
					console.log(response)
					if (response.responseStatus === "0000") {
						if (response.responseData.length > 0) {
							loadAutoFill(response.responseData, flag)
						} else {
							// No Data available
						}
					}
				})
				.catch(function (error) {
					console.log(error);
				})
				.finally(function () {
				})

		}

		function loadAutoFill(data, flag) {
			const list1 = document.getElementById("input-phone-list")
			const list2 = document.getElementById("input-authority-phone-list")
			list.innerHTML = ""
			data.forEach(element => {
				const parentNode = document.createElement("option")
				parentNode.value = element.phone
				if (flag === 1) {
					list1.appendChild(parentNode)
				} else {
					list2.appendChild(parentNode)
				}

			});
		}

		function requestApproval() {
			event.preventDefault()

			const name = document.getElementById("input-name")
			const alias = document.getElementById("input-alias")
			const email = document.getElementById("input-email")
			const phone = document.getElementById("input-phone")
			const nric = document.getElementById("input-nric")
			const accessRequired = document.getElementById("input-access-required")

			const list = [name, alias, email, phone, nric, accessRequired]
			let flag = true

			list.forEach(element => {
				if (element.value.trim() === "") {
					flag = false
				}
			});

			if (!flag) {
				alert("All fields are mandatory")
			} else {

				startLoadingScreen("Processing, please wait...")

				var config = {
					method: 'POST',
					url: contextPath + '/user/approval/submit',
					headers: {
						'X-CSRF-TOKEN': token,
						'Content-Type': 'application/json'
					},
					data: {
						name: name.value.trim(),
						alias: alias.value.trim(),
						email: email.value.trim(),
						phone: phone.value.trim(),
						nric: nric.value.trim(),
						accessRequired: accessRequired.value.trim()
					}
				}

				this.axios(config)
					.then(function (response) {
						return response.data
					})
					.then(function (response) {
						alert(response.responseMessage)
					})
					.catch(function (error) {
						console.log(error);
					})
					.finally(function () {
						stopLoadingScreen()
						window.location.reload()
					})



			}
		}
	</script>

	</html>
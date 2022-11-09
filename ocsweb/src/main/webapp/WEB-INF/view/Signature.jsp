<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="ISO-8859-1">

		<meta id="_csrf" name="_csrf" content="${_csrf.token}" />
		<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}" />

		<title>Signature</title>
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



		<%@ page isELIgnored="false" %>
	</head>

	<body id="signature-body">
		<main class="container mt110" style="margin-top: 5rem">

			<div class="d-flex align-items-center" style="text-align: center">

				<div class="flex-grow-1">
					<div class="card">
						<div class="card-body">
							<h4 style="text-align: left">Signature</h4>
							<hr />
							<canvas id="signature-canvas" width="300" height="300"
								style="border: 0.2px solid rgb(203, 203, 203); touch-action: none; border-radius: 8px;"></canvas>

							<div style="margin-top: 1rem">
								<hr>
								<span><button class="btn btn-outline-secondary"
										onclick="clearSignature()">Clear</button></span> <span><button
										class="btn btn-primary" onclick="updateSignature()">Confirm</button></span>
							</div>
						</div>
					</div>
				</div>
			</div>

		</main>
	</body>

	<script type="text/javascript">
		var token = $("meta[name='_csrf']").attr("content")
		var header = $("meta[name='_csrf_header']").attr("content")
		var contextPath = "${pageContext.request.contextPath}"

		var signatureCanvas = document.getElementById("signature-canvas")
		var signaturePad = new SignaturePad(signatureCanvas, {})
		var context = signatureCanvas.getContext('2d');

		var canvasImageLoader = new Image();
		canvasImageLoader.onload = function () {
			context.drawImage(this, 0, 0);
		};

		// window.onload = function(){
		// 	signaturePad.fromDataURL("http://localhost:8080/ocsweb/document/signature/vignesh.png");
		// }

		window.onload = function () {
			var config = {
				method: 'get',
				url: 'signature/get',
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

					canvasImageLoader.src = contextPath + response.responseData.imageUrl;
				})
				.catch(function (error) {
					console.log(error);
				})
				.finally(function () {

				})
		}


		function clearSignature() {
			signaturePad.clear();
		}


		function deleteSignature() {

		}


		function updateSignature() {
			var imageString = signaturePad.toDataURL();

			const data = signaturePad.toData();
			console.log("imageString -> ", data)

			if (signaturePad.isEmpty()) {
				alert("Please draw your signature to upload")
			} else {
				updateSignatureString(imageString)
			}
		}


		function updateSignatureString(imageString) {

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
					alert(response.responseMessage)
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
	</script>

	<style type="text/css">
		#signature-body {
			background-color: #f1f5fa;
		}
	</style>

	</html>
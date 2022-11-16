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


<!-- CDN moved to local -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/js/fontawesome/css/fontawesome.min.css">
<script src="${pageContext.request.contextPath}/resources/js/axios.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/moment.js"
	type="text/javascript"></script>
<!-- CDN moved to local -->

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

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap5.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/fonts/fonts.css">

<meta id="_csrf" name="_csrf" content="${_csrf.token}" />
<meta id="_csrf_header" name="_csrf_header"
	content="${_csrf.headerName}" />

<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/loading.js"></script>

<%@ page isELIgnored="false"%>
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

.h-100 {
	height: 100% !important;
}
</style>

<body>
	<main class="container mt110">
		<div class="row">
			<div class="col-md-12 col-lg-12">
				<div class="bg-white p-5 rounded">

					<div class="row">
						<div class="col-md-6 col-lg-8 col-xs-12 c5d5d5d">TPA List</div>
						<div>
							<button id="btnDownloadTPA"
								class="btn btn-mobi btn-alt float-end">Download</button>
						</div>
						<div class="col-md-6 col-lg-4 col-xs-12 ">
							<div class="row"></div>
						</div>
					</div>

					<hr />

					<div class="table-responsive">
						<table id="table" class="table">
							<thead>
								<tr class="bcf4f4f4 c617075 fw500">
									<th></th>
									<th>Order ID</th>
									<th>Date</th>
									<th>Type</th>
									<th>Company Name</th>
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

									<tr>
										<td><input class="checkbox" type="checkbox"
											value="${tempOrder.id}"></td>
										<td>${tempOrder.id}</td>
										<%-- <td>${tempOrder.createdOn}</td> --%>
										<fmt:parseDate value="${tempOrder.createdOn}"
											pattern="yyyy-MM-dd HH:mm:ss" var="myDate" />
										<td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss"
												value="${myDate}" /></td>
										<td>${tempOrder.quotation.orderType}</td>
										<td><a href="${viewLink} ">${tempOrder.quotation.companyName}</a></td>
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

	<style>
#loading-dialog {
	width: max-content;
	background: white;
	margin-top: 1 rem !important;
	text-align: -webkit-center;
	z-index: 9999;
	top: 0;
	left: 0;
	width: 100vw;
	height: 100vh;
	position: absolute;
	padding: 1rem;
	background: #1c1c1c75;
	color: white;
}
</style>

	<script type="text/javascript">

			var token = $("meta[name='_csrf']").attr("content")
			var contextPath = "${pageContext.request.contextPath}"

			document.getElementById("btnDownloadTPA").onclick = function () {

				startLoadingScreen("Loading, please wait...");

				var checkedValues = $('.checkbox:checked').map(function () {
					return this.value.toString();
				}).get();

				const config = {
					method: 'post',
					url: contextPath + "/document/tpa/download",
					headers: {
						'X-CSRF-TOKEN': token,
					},
					data: {
						orderIds: checkedValues
					}
				}
				//  console.log("axios request data -> ", config);

				axios(config)
					.then(response => {
						return response.data
					})
					.then(response => {
						// //  console.log("response -> ", JSON.stringify(response.responseData))
						if (response.responseStatus === "0000") {
							startLoadingScreen("Loading, please wait...");
							convertToCSVFormat(response.responseData)
						}
						else
							alert(response.responseMessage)
					})
					.catch(error => {
						//  console.log("error -> ", error)
					})
					.finally(() => {
					})

				stopLoadingScreen()
			}

			window.onload = function () {

				$('#table')
					.DataTable(
						{
							"order": [[1, 'desc']],
							"dom": "<'row'<'col-sm-12 col-md-6'f><'col-sm-12 col-md-6'l>>"
								+ "<'row'<'col-sm-12'tr>>"
								+ "<'row'<'col-sm-12 col-md-6 mt-2'i><'col-sm-12 col-md-6 mt-2'p>>",
						});

			}

			function removeNull(data) {
				Object.keys(data).map(function (key, index) {
					if (data[key] instanceof Object) {
						removeNull(data)
					} else if (data[key] instanceof Array) {
						data[key].forEach(element => {
							removeNull(element)
						});
					} else
						if (data[key] == null) {
							data[key] = "";
						}
				});
				return data;
			}

			function convertToCSVFormat(data) {
				var headers = {
					operationCode: "H",
					tpaReferenceNo: moment(new Date()).format('YYYYMMDD'),
					productCode: "146",
					mid: data.length,
					chainNo: "",
					rootMerchantNo: "",
					merchantIdType: "",
					businessRegistrationId: "",
					businessName: "",
					merchantTradingName: "",
					businessStartTime: "",
					businessEndTime: "",
					natureOfBusiness: "",
					businessAddressLine1: "",
					businessAddressLine2: "",
					businessAddressLine3: "",
					businessAddressLine4: "",
					country: "",
					state: "",
					town: "",
					postCode: "",

					businessDirector1Name: "",
					businessDirector1IdType: "",
					businessDirector1Country: "",
					businessDirector1IdNumber: "",
					businessDirector1MobileNo: "",

					businessDirector2Name: "",
					businessDirector2IdType: "",
					businessDirector2Country: "",
					businessDirector2IdNumber: "",
					businessDirector2MobileNo: "",

					businessDirector3Name: "",
					businessDirector3IdType: "",
					businessDirector3Country: "",
					businessDirector3IdNumber: "",
					businessDirector3MobileNo: "",

					businessDirector4Name: "",
					businessDirector4IdType: "",
					businessDirector4Country: "",
					businessDirector4IdNumber: "",
					businessDirector4MobileNo: "",

					businessDirector5Name: "",
					businessDirector5IdType: "",
					businessDirector5Country: "",
					businessDirector5IdNumber: "",
					businessDirector5MobileNo: "",

					eCommerceIndicator: "",
					eCommerceTerminalFlag: "",
					eCommerceIndustry: "",
					webSiteURL: "",
					numberOfEDC: "",
					EDCModel: "",
					numberOfMPOS: "",
					mPOSModel: "",
					merchantSize: "",
					mastercardMCC: "",
					visaMCC: "",
					unionPayMCC: "",
					previousAcquirerIndicator: "",
					previousAcquirer: "",
					ceasedDateWithPreviousAcquirer: "",
					emailAddress: "",
					companyType: ""
				};
				var footer = {
					operationCode: "F",
					tpaReferenceNo: data.length,
					productCode: "",
					mid: "",
					chainNo: "",
					rootMerchantNo: "",
					merchantIdType: "",
					businessRegistrationId: "",
					businessName: "",
					merchantTradingName: "",
					businessStartTime: "",
					businessEndTime: "",
					natureOfBusiness: "",
					businessAddressLine1: "",
					businessAddressLine2: "",
					businessAddressLine3: "",
					businessAddressLine4: "",
					country: "",
					state: "",
					town: "",
					postCode: "",

					businessDirector1Name: "",
					businessDirector1IdType: "",
					businessDirector1Country: "",
					businessDirector1IdNumber: "",
					businessDirector1MobileNo: "",

					businessDirector2Name: "",
					businessDirector2IdType: "",
					businessDirector2Country: "",
					businessDirector2IdNumber: "",
					businessDirector2MobileNo: "",

					businessDirector3Name: "",
					businessDirector3IdType: "",
					businessDirector3Country: "",
					businessDirector3IdNumber: "",
					businessDirector3MobileNo: "",

					businessDirector4Name: "",
					businessDirector4IdType: "",
					businessDirector4Country: "",
					businessDirector4IdNumber: "",
					businessDirector4MobileNo: "",

					businessDirector5Name: "",
					businessDirector5IdType: "",
					businessDirector5Country: "",
					businessDirector5IdNumber: "",
					businessDirector5MobileNo: "",

					eCommerceIndicator: "",
					eCommerceTerminalFlag: "",
					eCommerceIndustry: "",
					webSiteURL: "",
					numberOfEDC: "",
					EDCModel: "",
					numberOfMPOS: "",
					mPOSModel: "",
					merchantSize: "",
					mastercardMCC: "",
					visaMCC: "",
					unionPayMCC: "",
					previousAcquirerIndicator: "",
					previousAcquirer: "",
					ceasedDateWithPreviousAcquirer: "",
					emailAddress: "",
					companyType: ""
				};

				var validateDataSet = [
				];
				validateDataSet.push(headers)

				// data = Object.keys(data).map(function (key, index) {
				// 	if (typeof data[key] === 'object') {

				// 	} else {
				// 		if (data[key] === null) {
				// 			//  console.log("object key --> " + JSON.stringify(data[key]))
				// 			data[key] = ""
				// 		}
				// 	}

				// });


				data.forEach((item) => {
					validateDataSet.push({
						operationCode: item.operationCode,
						tpaReferenceNo: fillZero(item.tpaReferenceNo, 29),
						productCode: item.productCode,
						mid: item.mid,
						chainNo: fillZero(item.chainNo, 15),
						rootMerchantNo: fillZero(item.rootMerchantNo, 15),
						merchantIdType: item.merchantIdType,
						businessRegistrationId: item.businessRegistrationNo,

						businessName: (item.businessName !== null ? item.businessName.replace(/,|-/g, '') : ""),
						merchantTradingName: (item.merchantTradingName !== null ? item.merchantTradingName.replace(/,|-/g, '') : ""),

						businessStartTime: item.businessStartTime,
						businessEndTime: item.businessEndTime,

						natureOfBusiness: item.natureOfBusiness,

						businessAddressLine1: (item.businessAddressLine1 !== null ? item.businessAddressLine1.replace(/,/g, '') : ""),
						businessAddressLine2: (item.businessAddressLine2 !== null ? item.businessAddressLine2.replace(/,/g, '') : ""),
						businessAddressLine3: (item.businessAddressLine3 !== null ? item.businessAddressLine3.replace(/,/g, '') : ""),
						businessAddressLine4: (item.businessAddressLine4 !== null ? item.businessAddressLine4.replace(/,/g, '') : ""),

						country: item.country,
						state: item.state,
						town: item.town,
						postCode: item.postCode,

						businessDirector1Name: (item.director1Name === null ? '' : item.director1Name.replace(/,/g, '')),
						businessDirector1IdType: (item.director1IdType === null ? '' : item.director1IdType.replace(/,/g, '')),
						businessDirector1Country: (item.director1Country === null ? '' : item.director1Country.replace(/,/g, '')),
						businessDirector1IdNumber: (item.director1IdNumber === null ? '' : item.director1IdNumber.replace(/,/g, '')),
						businessDirector1MobileNo: (item.director1MobileNo === null ? '' : item.director1MobileNo.replace(/,|-/g, '')),

						businessDirector2Name: (item.director2Name === null ? '' : item.director2Name.replace(/,/g, '')),
						businessDirector2IdType: (item.director2IdType === null ? '' : item.director2IdType.replace(/,/g, '')),
						businessDirector2Country: (item.director2Country === null ? '' : item.director2Country.replace(/,/g, '')),
						businessDirector2IdNumber: (item.director2IdNumber === null ? '' : item.director2IdNumber.replace(/,/g, '')),
						businessDirector2MobileNo: (item.director2MobileNo === null ? '' : item.director2MobileNo.replace(/,|-/g, '')),

						businessDirector3Name: (item.director3Name === null ? '' : item.director3Name.replace(/,/g, '')),
						businessDirector3IdType: (item.director3IdType === null ? '' : item.director3IdType.replace(/,/g, '')),
						businessDirector3Country: (item.director3Country === null ? '' : item.director3Country.replace(/,/g, '')),
						businessDirector3IdNumber: (item.director3IdNumber === null ? '' : item.director3IdNumber.replace(/,/g, '')),
						businessDirector3MobileNo: (item.director3MobileNo === null ? '' : item.director3MobileNo.replace(/,|-/g, '')),

						businessDirector4Name: (item.director4Name === null ? '' : item.director4Name.replace(/,/g, '')),
						businessDirector4IdType: (item.director4IdType === null ? '' : item.director4IdType.replace(/,/g, '')),
						businessDirector4Country: (item.director4Country === null ? '' : item.director4Country.replace(/,/g, '')),
						businessDirector4IdNumber: (item.director4IdNumber === null ? '' : item.director4IdNumber.replace(/,/g, '')),
						businessDirector4MobileNo: (item.director4MobileNo === null ? '' : item.director4MobileNo.replace(/,|-/g, '')),

						businessDirector5Name: (item.director5Name === null ? '' : item.director5Name.replace(/,/g, '')),
						businessDirector5IdType: (item.director5IdType === null ? '' : item.director5IdType.replace(/,/g, '')),
						businessDirector5Country: (item.director5Country === null ? '' : item.director5Country.replace(/,/g, '')),
						businessDirector5IdNumber: (item.director5IdNumber === null ? '' : item.director5IdNumber.replace(/,/g, '')),
						businessDirector5MobileNo: (item.director5MobileNo === null ? '' : item.director5MobileNo.replace(/,|-/g, '')),

						eCommerceIndicator: item.eCommerceIndicator,
						eCommerceTerminalFlag: item.eCommerceTerminalFlag,
						eCommerceIndustry: item.eCommerceIndustry,
						webSiteURL: item.websiteUrl,
						numberOfEDC: item.numberOfEdc,
						EDCModel: item.edcModel,
						numberOfMPOS: item.numberOfMpos,
						mPOSModel: item.mposModel,
						merchantSize: item.merchantSize,
						mastercardMCC: item.masterCardMCC,
						visaMCC: item.visaMCC,
						unionPayMCC: item.unionPayMCC,
						previousAcquirerIndicator: item.previousAcquirerIndicator,
						previousAcquirer: item.previousAcquirer,
						ceasedDateWithPreviousAcquirer: (item.previousAcquirerIndicator === 'Y' ? moment(item.ceasedDateWithPreviousAcquirer).format("YYYYMMDD") : ''),
						emailAddress: item.email,
						companyType: item.companyType
					})
				})

				validateDataSet.push(footer)


				//  console.log("validateDataSet -> ", JSON.stringify(validateDataSet))

				var fileTitle = 'TPAFile-CSV'; // or 'my-unique-title'

				exportCSVFile(headers, validateDataSet, fileTitle); // call the exportCSVFile() function to process the JSON and trigger the download
			}

			function exportCSVFile(headers, items, fileTitle) {

				// Convert Object to JSON
				var jsonObject = JSON.stringify(items);
				var csv = this.convertToCSV(jsonObject);

				var exportedFilenmae = fileTitle + '.csv' || 'export.csv';

				var blob = new Blob([csv], { type: 'text/csv;charset=utf-8;' });
				if (navigator.msSaveBlob) { // IE 10+
					navigator.msSaveBlob(blob, exportedFilenmae);
				} else {
					var link = document.createElement("a");
					if (link.download !== undefined) { // feature detection
						// Browsers that support HTML5 download attribute
						var url = URL.createObjectURL(blob);
						link.setAttribute("href", url);
						link.setAttribute("download", exportedFilenmae);
						link.style.visibility = 'hidden';
						document.body.appendChild(link);
						link.click();
						document.body.removeChild(link);
					}
				}
				stopLoadingScreen()
			}

			function convertToCSV(objArray) {
				var array = typeof objArray != 'object' ? JSON.parse(objArray) : objArray;
				var str = '';

				for (var i = 0; i < array.length; i++) {
					var line = '';
					for (var index in array[i]) {
						if (line != '') line += ','
						line += array[i][index];
					}
					str += line + '\r\n';
				}
				return str;
			}

			function fillZero(your_number, length) {
				var num = '' + your_number;
				while (num.length < length) {
					num = '0' + num;
				}

				console.log("fillZero >> " + num)
				return num;
			}

		// 	$(document).ready(function () {

		// 		$('#btnDownloadTPA').click(
		// 			function (e) {
		// 				e.preventDefault();

		// 				//  console.log(checkedValues);

		// 				$.ajax({
		// 					type: 'POST',
		// 					url: "downloadTPA",
		// 					data: { orderIds: checkedValues.toString() },
		// 					success: function (data) {
		// 						//  console.log(data);
		// 						location.reload();
		// 					}
		// 				});
		// 			});

		// 	});
		</script>

</body>

</html>
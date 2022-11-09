<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
		<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
			<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
				<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

					<!doctype html>
					<html lang="en">

					<head>

						<%@ page isELIgnored="false" %>
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
							<meta id="_csrf" name="_csrf" content="${_csrf.token}" />
							<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}" />`

							<!-- CDN moved to local -->
							<link rel="stylesheet" type="text/css"
								href="${pageContext.request.contextPath}/resources/js/fontawesome/css/fontawesome.min.css">
							<script src="${pageContext.request.contextPath}/resources/js/axios.js"
								type="text/javascript"></script>
							<script src="${pageContext.request.contextPath}/resources/js/moment.js"
								type="text/javascript"></script>
							<!-- CDN moved to local -->

							<link rel="stylesheet" type="text/css"
								href="${pageContext.request.contextPath}/resources/css/style.css">
							<link rel="stylesheet" type="text/css"
								href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
							<link rel="stylesheet" type="text/css"
								href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap5.min.css">
							<link rel="stylesheet" type="text/css"
								href="${pageContext.request.contextPath}/resources/fonts/fonts.css">
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
							<script type="text/javascript"
								src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>


							<script type="text/javascript"
								src="${pageContext.request.contextPath}/resources/js/loading.js"></script>


					</head>
					<style>
						.form label {
							color: #092540;
						}

						.form .form-control {
							/*background-color: #f7f7f7; */
							border-radius: 4px;
							border: solid 1px #dedede;
						}

						.form h6 {
							font-size: 30px;
							font-weight: 600;
							font-stretch: normal;
							font-style: normal;
							line-height: 1.6;
							letter-spacing: normal;
							text-align: left;
							color: #425466;
						}

						.h-divider {
							margin-top: 5px;
							margin-bottom: 5px;
							height: 1px;
							width: 100%;
							border-top: 1px solid #e2e2e2;
						}

						.item_img {
							background-color: #005baa;
							color: #fff;
							padding: 3px;
							opacity: 0.3;
						}

						.item_img_1 {
							background-color: #f9d2a8;
							color: #fff;
							padding: 3px;
							opacity: 0.3;
						}

						.too_small {
							font-size: .575em
						}

						.btn-alt {
							display: block;
							padding: .375rem .75rem;
							font-size: 1rem;
							font-weight: 400;
							line-height: 1.5;
							background-clip: padding-box;
							appearance: none;
						}

						.c005baa {
							color: #005baa;
						}

						.c425466 {
							color: #425466;
						}

						.btn-white {
							border-radius: 4px;
							box-shadow: 0 3px 6px 0 rgba(0, 0, 0, 0.16);
							background-color: #ffffff;
							color: #7f8eab;
						}

						.mt110 {
							margin-top: 110px;
						}

						.sticky-div {
							background-color: white;
							position: sticky;
							top: 50px;
							padding: 10px 0px;
							z-index: 1000
						}

						.mobi-spinner {
							border-radius: 0px;
							background-color: #005baa;
							color: #FFFFFF;
						}

						.mobi-model {
							position: fixed;
							top: 0;
							left: 0;
							background-color: rgba(0, 0, 0, 0.4);
							display: flex;
							flex-direction: row;
							padding: 8rem;
							visibility: collapse;
							width: 100%;
							min-height: 100vh;
							place-content: center;
							height: 100%;
							align-content: center;
							align-items: center;
							z-index: 1030 !important;
							color: #000;
							align-items: center;
							align-content: center;
						}

						.mobi-model-container {
							max-width: 500px;
							width: 100%;
						}
					</style>

					<body class="container-fluid">

						<main id="desktop-view" style="margin-top: 50px; background-color: #fff;">
							<div class="sticky-top p-4">
								<h6 class="">${quotation.companyName}</h6>

								<div class="d-flex flex-row " style="width: 100%; gap: 5px;">

									<button id="btnPDFDownload" class="btn-sm btn btn-white action-button">View
										Quotation</button>
									<button class="btn btn-white action-button btn-sm" id="button-issue-quotation">Issue
										Quotation</button>
									<button class="btn btn-white action-button" id="SendProfoma"
										onclick="viewProfomaInvoice()">View
										Profoma</button>

									<button class="btn btn-white action-button" id="SendProfomaEmail">Send
										Profoma</button>

									<sec:authorize access="hasAnyRole('SALES,SALES-MANAGER')">
										<button type="button" data-bs-toggle="modal"
											data-bs-target="#paymentCollectedModal"
											class="btn btn-white btn-sm action-button">Payment
											Collected</button>
										<button class="btn btn-white action-button btn-sm"
											onclick="viewReceipt()">Receipt</button>
									</sec:authorize>

									<button class="btn btn-white action-button btn-sm" id="button-modify-userid">Modify
										UserID</button>

									<sec:authorize access="hasAnyRole('PROCESSING , FINANCE, RISK')">
										<button class="btn btn-white btn-sm action-button" data-bs-toggle="modal"
											id="button-rollback" data-bs-target="#rollback-modal">Rollback</button>
									</sec:authorize>

									<button class="btn btn-white action-button" id="btnMoveToProcessing">Rollback
										to Processing</button>


								</div>
							</div>

							<div class="row">
								<div class="col-md-12 col-lg-12">
									<div class="p-4">
										<h6 class="mb-3  mt-3"> Quotation / Detail</h6>

										<form:form id="quotationForm" method="POST" action="updateQuotation"
											enctype="multipart/form-data" modelAttribute="quotation">

											<div>
												<form:input type="hidden" path="contact.id" name="contact.id" />
												<form:input type="hidden" path="order.id" name="order.id" />
												<form:input type="hidden" path="createdOn" name="createdOn" />
												<form:input type="hidden" path="dealID" name="dealID" />
												<form:input type="hidden" path="stage" name="stage" />
												<form:input type="hidden" path="boardID" name="boardID" />
												<form:input type="hidden" path="cardID" name="cardID" />

												<c:if test="${payment.id != '' && payment.id != null}">
													<form:input type="hidden" path="payment.id" name="payment.id" />
												</c:if>

												<c:if
													test="${quotationAcceptance.id != '' && quotationAcceptance.id != null}">
													<form:input type="hidden" path="quotationAcceptance.id"
														name="quotationAcceptance.id"></form:input>
												</c:if>

												<form:input type="hidden" path="quotationAccepted"
													name="quotationAccepted" />
											</div>

											<ul class="nav nav-tabs" id="myTab" role="tablist">
												<li class="nav-item" role="presentation">
													<button class="nav-link active" id="quotation-tab"
														data-bs-toggle="tab" data-bs-target="#quotation-tab-pane"
														type="button" role="tab" aria-controls="quotation-tab-pane"
														aria-selected="true">Quotation</button>
												</li>
												<li class="nav-item" role="presentation">
													<button class="nav-link" id="items-tab" data-bs-toggle="tab"
														data-bs-target="#items-tab-pane" type="button" role="tab"
														aria-controls="items-tab-pane"
														aria-selected="false">Items</button>
												</li>
												<li class="nav-item" role="presentation">
													<button class="nav-link" id="finance-tab" data-bs-toggle="tab"
														data-bs-target="#finance-tab-pane" type="button" role="tab"
														aria-controls="finance-tab-pane"
														aria-selected="false">Finance</button>
												</li>
												<li class="nav-item" role="presentation">
													<button class="nav-link" id="document-tab" data-bs-toggle="tab"
														data-bs-target="#document-tab-pane" type="button" role="tab"
														aria-controls="document-tab-pane"
														aria-selected="false">Document</button>
												</li>
											</ul>
											<div class="tab-content" id="myTabContent"
												style="margin-bottom: 4rem !important;">

												<!-- Quotation Details -->
												<div class="tab-pane fade show active" id="quotation-tab-pane"
													role="tabpanel" aria-labelledby="quotation-tab" tabindex="0">
													<div class="row g-3 mt-3">
														<div class="col-sm-3 col-md-2 form-floating mb-3">
															<div class="mb-3">
																<form:label path="id" class="form-label">Quotation ID
																</form:label>
																<form:input path="id" class="form-control"
																	placeholder="" id="quotationId" readonly="true" />
															</div>
														</div>

														<div class="col-sm-3 col-md-2 form-floating mb-3">
															<div class="mb-3">
																<form:label path="createdOn" class="form-label">Created
																	On
																</form:label>
																<div class="input-group mb-3">
																	<form:input id="createdOn" path="createdOn"
																		type="text" disabled="true"
																		class="form-control" />
																	<span class="input-group-text"><i
																			class="fa fa-calendar"></i></span>
																</div>

															</div>
														</div>

														<div class="col-sm-3 col-md-2 form-floating mb-3">
															<div class="mb-3">
																<form:label path="lastModified" class="form-label">Last
																	Modified
																</form:label>
																<div class="input-group mb-3">
																	<form:input path="lastModified" class="form-control"
																		disabled="true" id="lastModified"
																		placeholder="" />
																	<span class="input-group-text"><i
																			class="fa fa-calendar"></i></span>
																</div>

															</div>
														</div>

														<div class="col-sm-3 col-md-2 form-floating mb-3">
															<div class="mb-3">
																<form:label path="companyName" class="form-label">
																	Company Name
																</form:label>
																<form:input path="companyName" id="id-company-name"
																	class="form-control" placeholder="Company Name" />
															</div>
														</div>

														<div class="col-sm-3 col-md-2 form-floating mb-3">
															<div class="mb-3">
																<form:label path="orderType" class="form-label">Order
																	Type
																</form:label>
																<form:select class="form-select" name="orderType"
																	path="orderType" id="orderType">
																	<form:option value="0">---------Select----------
																	</form:option>
																	<c:forEach items="${orderTypes}" var="orderTypes">
																		<form:option value="${orderTypes.value}">
																			${orderTypes.name}
																		</form:option>
																	</c:forEach>
																</form:select>


															</div>
														</div>
														<div class="col-sm-3 col-md-2 form-floating mb-3">
															<div class="mb-3">

																<form:label path="paymentType" class="form-label">
																	Payment Type
																</form:label>
																<form:select class="form-select" name="paymentType"
																	path="paymentType" id="paymentType">
																	<form:option value="0">---------Select----------
																	</form:option>
																	<c:forEach items="${paymentTypes}"
																		var="paymentTypes">
																		<form:option value="${paymentTypes.value}">
																			${paymentTypes.name}</form:option>
																	</c:forEach>
																</form:select>
															</div>
														</div>

														<div class="col-sm-3 col-md-2 form-floating mb-3">
															<div class="mb-3">
																<form:label path="contact.email" class="form-label">
																	Email
																</form:label>
																<form:input path="contact.email" class="form-control"
																	placeholder="" />
															</div>
														</div>
														<div class="col-sm-3 col-md-2 form-floating mb-3">
															<div class="mb-3">
																<form:label path="contact.firstName" class="form-label">
																	Contact
																	Name
																</form:label>
																<form:input path="contact.firstName"
																	class="form-control" placeholder="Contact Name" />
															</div>
														</div>

														<div class="col-sm-3 col-md-2 form-floating mb-3">
															<div class="mb-3">
																<form:label path="expiryDate" class="form-label">Expiry
																	Date
																</form:label>
																<div class="input-group mb-3">
																	<form:input id="expiryDate" path="expiryDate"
																		type="text" class="form-control datepick" />
																	<span class="input-group-text"><i
																			class="fa fa-calendar"></i></span>
																</div>
															</div>
														</div>
														<!-- I Switch 2 Mobi Discount removed -->
													</div>

													<div class="h-divider"></div>
													<h6 class="mb-3  mt-3">Business Details</h6>

													<div class="row g-3">
														<div class="col-sm-3 col-md-2 form-floating mb-3">
															<div class="mb-3">
																<form:label path="registrationNumber"
																	class="form-label">
																	Registration No</form:label>
																<form:input path="registrationNumber"
																	class="form-control"
																	placeholder="Business Registration No" />
															</div>
														</div>

														<div class="col-sm-3 col-md-2 form-floating mb-3">
															<div class="mb-3">
																<form:label path="userId" class="form-label">OCS
																	UserID
																</form:label>
																<div class="input-group mb-3">
																	<span class="input-group-text"
																		id="basic-addon1">60</span>
																	<form:input path="userId" class="form-control"
																		placeholder="OCS User ID" />
																</div>
															</div>
														</div>

														<div class="col-sm-3 col-md-2 form-floating mb-3">
															<div class="mb-3">
																<form:label path="acquirer" class="form-label">Acquirer
																</form:label>
																<form:select class="form-select" name="acquirer"
																	path="acquirer" id="acquirer">
																	<form:option value="0">---------Select----------
																	</form:option>
																	<c:forEach items="${acquirers}" var="acquirers">
																		<form:option value="${acquirers.value}">
																			${acquirers.name}
																		</form:option>
																	</c:forEach>
																</form:select>
															</div>
														</div>

														<div class="col-sm-3 col-md-2 form-floating mb-3">
															<div class="mb-3">

																<form:label path="refreePhone" class="form-label">
																	Referrer Phone
																	No
																</form:label>
																<form:input path="refreePhone" class="form-control"
																	placeholder="Phone Number of Merchant Referred" />

															</div>
														</div>

														<div class="col-sm-3 col-md-2 form-floating mb-3">
															<div class="mb-3">
																<form:label path="salesPerson" class="form-label">Sales
																	Person
																</form:label>
																<form:select class="form-select" name="salesPerson"
																	path="salesPerson.id" id="salesPerson">

																	<c:forEach items="${salesPersons}"
																		var="salesPersons">
																		<form:option value="${salesPersons.id}">
																			${salesPersons.aliasName}</form:option>
																	</c:forEach>
																</form:select>
															</div>
														</div>

														<div class="col-sm-3 col-md-2 form-floating mb-3">
															<div class="mb-3">

																<form:label path="remarks" class="form-label">Remarks
																</form:label>
																<form:input path="remarks" class="form-control"
																	placeholder="" />

															</div>
														</div>
														<div class="col-sm-3 col-md-2 form-floating mb-3">
															<div class="mb-3">

																<form:label path="refreeMID" class="form-label">Referrer
																	MID
																</form:label>
																<form:input path="refreeMID" class="form-control"
																	placeholder="MID of Merchant Referred" />

															</div>
														</div>
													</div>

													<div class="h-divider"></div>
													<h6 class="mb-3  mt-3">Address</h6>

													<div class="row g-3">
														<div class="col-md-2  form-floating mb-3">
															<div class="mb-3">
																<form:label path="address" class="form-label">Address
																	line 1
																</form:label>
																<form:input path="address" class="form-control"
																	placeholder="" />

															</div>
														</div>

														<div class="col-md-2 form-floating mb-3">
															<div class="mb-3">
																<form:label path="country" class="form-label">Country
																</form:label>
																<form:select path="country" class="form-select"
																	name="country">
																	<form:option value="458">MALAYSIA</form:option>
																	<c:forEach var="country" items="${CountryList}">
																		<form:option value="${country.value}">
																			${country.name}
																		</form:option>
																	</c:forEach>
																</form:select>
															</div>
														</div>

														<div class="col-md-2 form-floating mb-3">
															<div class="mb-3">
																<form:label path="city" class="form-label">City
																</form:label>
																<form:input path="city" class="form-control"
																	placeholder="" />
															</div>
														</div>

														<div class="col-md-2 form-floating mb-3">
															<div class="mb-3">
																<form:label path="state" class="form-label">State
																</form:label>
																<form:input path="state" class="form-control"
																	placeholder="" />
															</div>
														</div>

														<div class="col-md-2  form-floating mb-3">
															<div class="mb-3">
																<form:label path="postalCode" class="form-label">Postal
																	Code
																</form:label>
																<form:input path="postalCode" class="form-control"
																	placeholder="" />
															</div>
														</div>
													</div>

													<div class="h-divider"></div>


													<div class="row g-3">
														<div class="col-12">
															<div class="row">
																<div class="col-sm-6  form-floating mb-3">
																	<div class="mb-3">
																		<form:label path="notes" class="form-label">
																			Notes
																		</form:label>
																		<form:textarea class="form-control" id="notes"
																			path="notes"
																			placeholder="to be shown in Quotation"
																			rows="5" cols="30" />
																	</div>
																</div>
															</div>
														</div>
													</div>
													<input type="hidden" value="${quotation.id}" name="quotationId">
												</div>
												<!-- Quotation Details -->

												<!-- Items -->
												<div class="tab-pane fade" id="items-tab-pane" role="tabpanel"
													aria-labelledby="items-tab" tabindex="0">
													<h6 class="mb-3  mt-3">Quotation Template</h6>

													<div class="row g-3">
														<div class="col-sm-3  form-floating mb-3">
															<div class="mb-3">
																<label class="form-label">Product</label> <select
																	class="form-select action-select"
																	name="productTypes" id="productTypes">
																	<c:forEach items="${productTypes}"
																		var="productType">
																		<option value="${productType.value}">
																			${productType.name}
																		</option>
																	</c:forEach>
																</select>
															</div>
														</div>

														<div class="col-sm-3  form-floating mb-3">
															<div class="mb-3">
																<label class="form-label">Quotation Templates</label>
																<select class="form-select action-select"
																	name="products" id="products">
																	<c:forEach items="${products}" var="product">
																		<option value="${product.id}">
																			${product.productName}
																		</option>
																	</c:forEach>
																</select>
															</div>
														</div>
														<div class="col-sm-3  form-floating mb-3">
															<div class="mb-3">
																<label for="email"
																	class="form-label col-12">&nbsp;</label>
																<button type="submit"
																	class="btn btn-primary btn-alt add action-button">Add</button>
															</div>
														</div>
													</div>

													<input type="hidden" id="quotationId" name="quotationId"
														value="${quotation.id }">

													<div class="h-divider"></div>
													<h6 class="mb-3  mt-3">
														Items
														<button id="btnDeleteOrderLine"
															class="btn btn-danger btn-sm float-end action-button">Delete</button>
													</h6>

													<div class="table-responsive"
														style="margin-top: 1.5rem !important;">
														<table class="table" id="table">
															<thead>
																<tr class="bcf4f4f4 c617075 fw500">
																	<th></th>
																	<th>Line ID</th>
																	<th>Description</th>
																	<th>Product</th>
																	<th style="text-align: center">Quantity</th>
																	<th style="text-align: right">Unit Amount (RM)</th>
																	<th style="text-align: right">Actions</th>
																</tr>
															</thead>
															<tbody class="c3f3f3f fw500 tblOrderLineBody">

																<c:forEach var="tempOrderLine" items="${orderLineList}"
																	varStatus="count">
																	<c:url var="viewLink" value="">
																		<c:param name="id"
																			value="${tempOrderLine.id}" />
																	</c:url>

																	<c:choose>
																		<c:when
																			test="${tempOrderLine.product.productType == 'EZYSPLIT'}">
																			<c:set var="price" scope="session"
																				value="${tempOrderLine.quotationEzysplitMDRRate.unitPrice}" />
																		</c:when>
																		<c:otherwise>
																			<c:set var="price" scope="session"
																				value="${tempOrderLine.quotationMDRRate.unitPrice}" />
																		</c:otherwise>
																	</c:choose>


																	<tr>
																		<td><input
																				class="checkbox form-check-input action-input"
																				type="checkbox"
																				value="${tempOrderLine.id}">
																		</td>
																		<td>
																			<form:input type="hidden"
																				value="${tempOrderLine.id}"
																				name="orderLines[${count.index}].id"
																				path="orderLines[${count.index}].id" />
																			${tempOrderLine.id}
																		</td>
																		<td>${tempOrderLine.product.productName}</td>
																		<td>${tempOrderLine.product.productType}</td>
																		<td>
																			<div class="number-spinner">
																				<div class="input-group"
																					style="place-content: center;">
																					<span class="input-group-btn">
																						<button type="button"
																							class="btn btn-default mobi-spinner btn-spinner-minus action-button"
																							data-dir="dwn"
																							onclick="decrementQuantity(${tempOrderLine.id})">-</button>
																					</span>
																					<form:input
																						id="item-quantity-${tempOrderLine.id}"
																						type="text"
																						class=" text-center valid action-input"
																						value="${tempOrderLine.quantity}"
																						name="orderLines[${count.index}].quantity"
																						path="orderLines[${count.index}].quantity"
																						aria-invalid="false" />
																					<span class="input-group-btn">
																						<button type="button"
																							class="btn btn-default mobi-spinner btn-spinner-plus action-button"
																							data-dir="up"
																							onclick="incrementQuantity(${tempOrderLine.id})">+</button>
																					</span>
																				</div>
																			</div>
																		</td>

																		<td style="text-align: right">
																			<fmt:formatNumber type="number"
																				maxFractionDigits="2"
																				minFractionDigits="2"
																				value="${tempOrderLine.quantity * price}" />
																		</td>

																		<td style="text-align: right"><a
																				href="OrderView?id=${tempOrderLine.id}"
																				class="btn btn-mobi btn-sm edit action-button"
																				data-id="${tempOrderLine.id}"
																				data-backdrop="false">View</a>
																		</td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>

													<h6>Discount</h6>
													<div class="row">
														<div class="col-md-8">
															<form:textarea type="text" class="form-control"
																path="discountReason" placeholder="Discount Reason"
																id="id-discount-reason"></form:textarea>
														</div>
														<div class="col-md-4">
															<div class="d-flex flex-column align-items-end">
																<div class="input-group mb-3">
																	<span class="input-group-text" id="basic-addon2">SUB
																		TOTAL</span>
																	<input type="number" class="form-control readOnly"
																		pattern="([0-9]{1,3}).([0-9]{1,3})"
																		style="text-align: end;" step="0.01"
																		placeholder="Sub Total" value="${subtotal}"
																		id="id-sub-total" readonly>

																</div>

																<div class="input-group mb-3">
																	<span class="input-group-text"
																		id="basic-addon2">DISCOUNT</span>
																	<input type="number"
																		class="form-control action-input"
																		pattern="([0-9]{1,3}).([0-9]{1,3})"
																		style="text-align: end;"
																		value="${discountPrice}" step="0.01"
																		max="${subtotal}" min="0.00"
																		placeholder="Discount" id="id-discount-price">
																	</input>

																</div>

																<div class="input-group mb-3">
																	<span class="input-group-text"
																		id="basic-addon2">AMOUNT</span>
																	<input type="number" class="form-control readOnly"
																		pattern="([0-9]{1,3}).([0-9]{1,3})"
																		style="text-align: end;" step="0.01" min="0.00"
																		value="0.00" placeholder="Total" readonly
																		id="id-total-price">

																</div>
																<button
																	class="btn btn-primary btn-sm mt-3 action-button"
																	style="width: fit-content;"
																	id="button-apply-discount">Apply
																	Discount</button>
															</div>
														</div>
													</div>
												</div>
												<!-- Items -->


												<!-- Finance -->
												<div class="tab-pane fade" id="finance-tab-pane" role="tabpanel"
													aria-labelledby="finance-tab" tabindex="0">

													<h6 class="mb-3 mt-3">Online Acceptance</h6>
													<div class="row">
														<div class="col-sm-12 col-md-8">
															<div class="row">
																<div class="col-3">
																	<div class="mb-3">
																		<label for="quotationAcceptance"
																			class="form-label">Acceptance
																			ID</label>
																		<input type="text" class="form-control"
																			value="${quotation.quotationAcceptance.id}"
																			id="quotationAcceptance"
																			placeholder="Quotation Acceptance Id"
																			readonly="true">
																	</div>
																</div>
																<div class="col-3">
																	<div class="mb-3">
																		<label for="create_on"
																			class="form-label">Username in
																			OCS</label>
																		<div class="input-group mb-3">
																			<span class="input-group-text"
																				id="basic-addon1">60</span>
																			<input type="text" class="form-control"
																				value="${quotation.userId }"
																				id="create_on"
																				placeholder="OCS Username" readonly>
																		</div>
																	</div>
																</div>
																<div class="col-3">
																	<div class="mb-3">
																		<label for="email" class="form-label">IC
																			Number</label>
																		<input type="text" class="form-control"
																			id="email"
																			value="${quotationAcceptance.icNumber }"
																			placeholder="NRIC No" readonly>
																	</div>
																</div>
															</div>
															<div class="row">
																<div class="col-3">
																	<div class="mb-3">
																		<label for="create_on"
																			class="form-label">Accepted
																			On</label>
																		<input type="text" class="form-control"
																			id="create_on"
																			value="${quotationAcceptance.createdOn }"
																			placeholder="Accepted On" readonly>
																	</div>
																</div>
																<div class="col-3">
																	<div class="mb-3">
																		<label for="email" class="form-label">Name as
																			per
																			IC</label>
																		<input type="text" class="form-control"
																			value="${quotationAcceptance.nameAsPerIC }"
																			id="email" placeholder="Name as per IC"
																			readonly>
																	</div>
																</div>
																<div class="col-3">
																	<div class="mb-3">
																		<label for="create_on" class="form-label">IP
																			address</label>
																		<input type="text" class="form-control"
																			id="create_on" placeholder="IP Address"
																			value="${quotationAcceptance.ipAddress }"
																			readonly>
																	</div>
																</div>
															</div>
														</div>
														<div class="col-sm-12 col-md-4">
															<div class="mb-3">
																<label for="email" class="form-label">Digital
																	Signature</label> <img alt="" id="digital-signature"
																	width="128" height="128"
																	src="${pageContext.request.contextPath}/resources/images/signature_not_available.png"
																	style="border: 1px solid #ddd; border-radius: 4px;">
																<textarea class="form-control" id="email"
																	style="display: none !important" cols=""
																	rows=""></textarea>
															</div>
														</div>
													</div>
													<br>

													<div class="h-divider"></div>
													<h6 class="mb-3  mt-3">Payment Data</h6>
													<div class="row g-3">
														<div class="col-sm-3  form-floating mb-3">
															<div class="mb-3">
																<label for="collectedOn" class="form-label">Payment
																	Collected On</label>
																<div class="input-group mb-3">
																	<form:input id="paymentCollected"
																		path="payment.collectedOn" readonly="true"
																		type="text" class="form-control datepick" />
																	<span class="input-group-text"><i
																			class="fa fa-calendar"></i></span>
																</div>
															</div>
														</div>
													</div>

													<div class="h-divider"></div>
													<h6>Finance</h6>

													<div class="row g-3">
														<div class="col-sm-3  form-floating mb-3">
															<div class="mb-3">
																<label for="verifiedOn" class="form-label">Payment
																	Verified on</label>
																<div class="input-group mb-3">
																	<form:input id="paymentVerifiedOn"
																		path="payment.verifiedOn" type="text"
																		class="form-control datepick" readonly="true" />
																	<span class="input-group-text"><i
																			class="fa fa-calendar"></i></span>
																</div>
															</div>
														</div>

														<div class="col-sm-3  form-floating mb-3">
															<div class="mb-3">
																<label for="create_on" class="form-label">Payment
																	Verified By</label>
																<form:input type="text" class="form-control"
																	path="payment.verifiedByUserName"
																	id="verifiedByUserName"
																	placeholder="Payment Verified By" readonly="true" />
															</div>
														</div>

														<div class="col-sm-3  form-floating mb-3">
															<div class="mb-3">
																<label for="invoiceNo" class="form-label">QB Invoice
																	No</label>
																<form:input for="invoiceNo" type="text"
																	class="form-control" path="payment.invoiceNo"
																	placeholder="Invoice No" readonly="true" />
															</div>
														</div>


														<div class="col-sm-3  form-floating mb-3">
															<div class="mb-3">
																<label for="modeOfPayment" class="form-label">Mode of
																	Payment</label>
																<form:input for="modeOfPayment" type="text"
																	class="form-control" path="payment.modeOfPayment"
																	placeholder="Mode Of Payment" readonly="true" />
															</div>
														</div>
													</div>
												</div>
												<!-- Finance -->

												<!-- Document History -->
												<div class="tab-pane fade" id="document-tab-pane" role="tabpanel"
													aria-labelledby="document-tab" tabindex="0">
													<h6 class="mb-3 mt-3">Quotation History</h6>
													<table class="table">
														<thead>
															<tr>
																<th>S.No</th>
																<th>Document Name</th>
																<th>Sent Date</th>
																<th>Action</th>
															</tr>
														</thead>
														<tbody id="table-body-quotation-history">
														</tbody>
													</table>
												</div>
												<!-- Document History -->
											</div>


											<div class="bottom-fixed">
												<button class="btn btn-primary btn-sm" type="button"
													id="button-edit-details">Edit
													Details</button>
												<button id="button-save-details" style="display: none;" type="submit"
													class="btn btn-primary btn-sm">Save
													Details</button>

												<button class="btn btn-primary btn-sm" id="btnMoveToFinance">
													Move to Finance</button>

												<sec:authorize access="hasRole('FINANCE')">
													<button type="button" data-bs-toggle="modal"
														data-bs-target="#paymentVerifiedModal"
														class="btn btn-primary btn-sm action-button">
														Verify Payment</button>
												</sec:authorize>
											</div>
										</form:form>
									</div>
								</div>
							</div>
						</main>

						<!-- Rollback Modal -->
						<div class="modal fade" id="rollback-modal" tabindex="-1" aria-labelledby="rollback-modal-label"
							aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h6 class="modal-title" id="rollback-modal-label">Rollback</h6>
										<button type="button" id ="rollbackwrong" class="btn-close" data-bs-dismiss="modal"
											aria-label="Close"></button>
									</div>
									<div class="modal-body">
										<div class="d-flex">
											<div class="m-3 flex-grow-1">
												<label for="rollback-stage" class="form-label">Rollback
													back to</label> <select type="text" id="rollback-stage"
													style="text-transform: uppercase;" value="" class="form-select"
													placeholder="">
													<sec:authorize access="hasRole('FINANCE')">
														<option value="-1" style="text-transform: uppercase;" selected>
															Select One</option>
														<option value="sales" style="text-transform: uppercase;">sales
														</option>
													</sec:authorize>
													<sec:authorize access="hasRole('PROCESSING')">
														<option value="-1" style="text-transform: uppercase;" selected>
															Select One</option>
														<option value="sales" style="text-transform: uppercase;">sales
														</option>
														<option value="finance" style="text-transform: uppercase;">
															finance</option>
													</sec:authorize>

													<sec:authorize access="hasRole('RISK')">
														<option value="-1" style="text-transform: uppercase;" selected>
															Select One</option>
														<option value="sales" style="text-transform: uppercase;">sales
														</option>
														<option value="finance" style="text-transform: uppercase;">
															finance</option>
														<option value="processing" style="text-transform: uppercase;">
															processing</option>
													</sec:authorize>

													<sec:authorize access="hasRole('U-MOBILE')">
														<option value="-1" style="text-transform: uppercase;" selected>
															Select One</option>
														<option value="sales" style="text-transform: uppercase;">sales
														</option>
														<option value="finance" style="text-transform: uppercase;">
															finance</option>
														<option value="processing" style="text-transform: uppercase;">
															processing</option>
														<option value="risk" style="text-transform: uppercase;">risk
														</option>
													</sec:authorize>

													<!-- <option value="sales-manager" style="text-transform: uppercase;">
														sales-manager</option>
													<option value="finance-manager" style="text-transform: uppercase;">
														finance-manager</option>
													<option value="ceo" style="text-transform: uppercase;">ceo</option>
													<option value="synergy-vetdoc" style="text-transform: uppercase;">
														synergy-vetdoc</option>
													<option value="synergy-risk" style="text-transform: uppercase;">
														synergy-risk</option>
													<option value="synergy-approval" style="text-transform: uppercase;">
														synergy-approval</option>
													<option value="synergy-welcome" style="text-transform: uppercase;">
														synergy-welcome</option> -->
												</select>
											</div>
											<div class="m-3 flex-grow-1">
												<label for="rollback-reason" class="form-label">Rollback
													Reason</label> <input type="text" id="rollback-reason" value=""
													class="form-control" placeholder="Enter the reason for rollback">
											</div>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" id ="rollbackclose" class="btn btn-secondary"
											data-bs-dismiss="modal">Close</button>
										<button type="button" id ="rollbackbutton" class="btn btn-primary"
											onclick="rollbackStage()">Rollback</button>
									</div>
								</div>
							</div>
						</div>

						<!-- Mobile Responsive -->
						<div style="padding: 2rem; margin-top: 3rem;" id="mobile-view">
							<div class="mobi-card">
								<div class="mobi-card-body">
									<div class="d-flex flex-row">
										<div class="flex-grow-1">
											<div class="caption">
												Product Name
											</div>
											<div class="title">
												iSwitch2mobi Campaign ( EZYWAY)
											</div>
										</div>

										<div class="flex-grow-1">
											<div class="caption">
												Price
											</div>
											<div class="title-accent">
												RM 200.00
											</div>
										</div>
									</div>

									<hr>

									<div class="row">
										<div class="col col-4">
											<p class="title-accent">Card</p>
											<p class="body-1 text-small">LOCAL DEBIT</p>
											<p class="body-1 text-small">LOCAL CREDIT</p>
											<p class="body-1 text-small">FOREIGN DEBIT</p>
											<p class="body-1 text-small">FOREIGN CREDIT</p>
										</div>
										<div class="col" style="text-align: center;">
											<div><img width="40" src="../../assets/images/mastercard/mastercard@3x.png"
													alt=""></div>
											<p>1.6</p>
											<p>1.6</p>
											<p>2.5</p>
											<p>2.5</p>
										</div>
										<div class="col" style="text-align: center;">
											<div><img width="40" src="../../assets/images/union_pay/union_pay@3x.png"
													alt=""></div>
											<p>1.6</p>
											<p>1.6</p>
											<p>2.5</p>
											<p>2.5</p>
										</div>
										<div class="col" style="text-align: center;">
											<div><img width="40" src="../../assets/images/visa/visa@3x.png" alt="">
											</div>
											<p>1.6</p>
											<p>1.6</p>
											<p>2.5</p>
											<p>2.5</p>
										</div>
									</div>

									<div class="headline">
										Wallet Details
									</div>

									<div class="row">
										<div class="col">
											<p class="content">BOOST ECOMM</p>
											<p class="content">GRAB ECOMM</p>
											<p class="content">FPX (RM)</p>
										</div>
										<div class="col-2" style="text-align: end;">
											<p>1.2</p>
											<p>1.2</p>
											<p>1.2</p>
										</div>
										<div class="col">
											<p>BOOST QR</p>
											<p>GRAB QR</p>
											<p>FPX (%)</p>
										</div>
										<div class="col-2" style="text-align: end;">
											<p>1.2</p>
											<p>1.2</p>
											<p>1.2</p>
										</div>
									</div>

									<div class="headline">
										Settlement
									</div>

									<div class="row">
										<div class="col">
											<p class="content">BOOST</p>
										</div>
										<div class="col-1" style="text-align: end;">
											<p class="content">1.0</p>
										</div>
										<div class="col" style="text-align: center;">
											<p class="content">GRAB</p>
										</div>
										<div class="col-1" style="text-align: end;">
											<p class="content">1.0</p>
										</div>
										<div class="col" style="text-align: center;">
											<p class="content">FPX</p>
										</div>
										<div class="col-1" style="text-align: end;">
											<p class="content">1.0</p>
										</div>
									</div>
								</div>

							</div>

							<div class="mobi-card" style="margin-top: 24px;">
								<div class="mobi-card-body">
									<div class="d-flex flex-row">
										<div class="flex-grow-1">
											<div class="caption">
												Product Name
											</div>
											<div class="title">
												iSwitch2mobi Campaign ( EZYWAY)
											</div>
										</div>

										<div class="flex-grow-1">
											<div class="caption">
												Price
											</div>
											<div class="title-accent">
												RM 200.00
											</div>
										</div>
									</div>

									<hr>

									<div class="headline">
										Wallet Details
									</div>

									<div class="row">
										<div class="col">
											<p class="content">BOOST ECOMM</p>
											<p class="content">GRAB ECOMM</p>
											<p class="content">FPX (RM)</p>
										</div>
										<div class="col-2" style="text-align: end;">
											<p>1.2</p>
											<p>1.2</p>
											<p>1.2</p>
										</div>
										<div class="col">
											<p>BOOST QR</p>
											<p>GRAB QR</p>
											<p>FPX (%)</p>
										</div>
										<div class="col-2" style="text-align: end;">
											<p>1.2</p>
											<p>1.2</p>
											<p>1.2</p>
										</div>
									</div>

									<div class="headline">
										Settlement
									</div>

									<div class="row">
										<div class="col">
											<p class="content">BOOST</p>
										</div>
										<div class="col-1" style="text-align: end;">
											<p class="content">1.0</p>
										</div>
										<div class="col" style="text-align: center;">
											<p class="content">GRAB</p>
										</div>
										<div class="col-1" style="text-align: end;">
											<p class="content">1.0</p>
										</div>
										<div class="col" style="text-align: center;">
											<p class="content">FPX</p>
										</div>
										<div class="col-1" style="text-align: end;">
											<p class="content">1.0</p>
										</div>
									</div>

									<div class="headline">
										3 Month Instalment
									</div>
									<div class="row">
										<div class="col">MERCHANT MDR (%)</div>
										<div class="col-2" style="text-align: end;">5</div>
										<div class="col">CUSTOMER MDR (%)</div>
										<div class="col-2" style="text-align: end;">1.2</div>
									</div>

									<div class="headline">
										6 Month Instalment
									</div>
									<div class="row">
										<div class="col">MERCHANT MDR (%)</div>
										<div class="col-2" style="text-align: end;">5</div>
										<div class="col">CUSTOMER MDR (%)</div>
										<div class="col-2" style="text-align: end;">1.2</div>
									</div>

									<div class="headline">
										9 Month Instalment
									</div>
									<div class="row">
										<div class="col">MERCHANT MDR (%)</div>
										<div class="col-2" style="text-align: end;">5</div>
										<div class="col">CUSTOMER MDR (%)</div>
										<div class="col-2" style="text-align: end;">1.2</div>
									</div>

									<div class="headline">
										12 Month Instalment
									</div>
									<div class="row">
										<div class="col">MERCHANT MDR (%)</div>
										<div class="col-2" style="text-align: end;">5</div>
										<div class="col">CUSTOMER MDR (%)</div>
										<div class="col-2" style="text-align: end;">1.2</div>
									</div>
								</div>

							</div>

							<div class="mt-3">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
									<label class="form-check-label" for="flexCheckDefault">
										I hereby agree and accept the Terms and Conditions
									</label>
								</div>
							</div>
							<hr>

							<div class="d-flex flex-row justify-content-between align-items-center">
								<div class="caption m-1">Total Amount</div>
								<h3 class="title-accent m-1">RM 500.00</h3>
							</div>

							<div class="d-flex flex-row">
								<button class="btn btn-primary flex-grow-1 m-1 align-items-center"><img
										src="../../assets/images/download.svg" width="17" alt="" srcset=""> View
									Quotation</button>
								<button class="btn btn-primary flex-grow-1 m-1 align-items-center"><img
										src="../../assets/images/accept.svg" width="17" alt="" srcset=""> Accept
									Quotation</button>
							</div>

						</div>

						<!-- Mobile Responsive -->

						<!--EZYSPLIT Edit Modal -->
						<div class="modal fade" id="editModalEZYSPLIT" tabindex="-1" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog modal-dialog-scrollable  modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<h6 class="modal-title" id="exampleModalLabel">Amend Quotation
											MDR</h6>
										<button type="button" class="btn-close enableForAllRoles action-button"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
										<form:form action="">
											<div class="row g-3">
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">

														<label class="form-label">Product</label> <input
															id="productName_EzysplitModal" class="form-control"
															placeholder="" />

													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">

														<label class="form-label">Settlement</label> <input
															id="productSettlement_EzysplitModal" class="form-control"
															placeholder="" />

													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">

														<label class="form-label">Amount</label> <input
															id="amount_EzysplitModal" readonly="true"
															class="form-control" placeholder="" />

													</div>
												</div>
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">

														<label class="form-label">Host Rate ID</label> <input
															id="hostRateId_EzysplitModal" class="form-control"
															placeholder="" readonly="readonly" />

													</div>
												</div>
												<div>
													<input type="hidden" id="productId_EzysplitModal" /> <input
														type="hidden" id="quotationId_EzysplitModal" /> <input
														type="hidden" id="orderLineId_EzysplitModal" /> <input
														type="hidden" id="quotationEzysplitMdrRateId_EzysplitModal" />

													<input type="hidden" id="subscription_EzysplitModal" /> <input
														type="hidden" id="showInQuote_EzysplitModal" /> <input
														type="hidden" id="productType_EzysplitModal" /> <input
														type="hidden" id="includeWallet_EzysplitModal" /> <input
														type="hidden" id="payLater_EzysplitModal" /> <input
														type="hidden" id="hostType_EzysplitModal" /> <input
														type="hidden" id="createdOn_EzysplitModal" /> <input
														type="hidden" id="loc_Credit_Host_Master_Insta3" /> <input
														type="hidden" id="loc_Credit_Host_Master_Insta6" /> <input
														type="hidden" id="loc_Credit_Host_Master_Insta9" /> <input
														type="hidden" id="loc_Credit_Host_Master_Insta12" />

												</div>


											</div>
											<label><b>Wallet Details</b></label>
											<br>
											<label><b>MDR</b></label>

											<div class="row g-3">
												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<label class="form-label">Boost EComm</label> <input
															id="boostMDREcomm_EzysplitModal" class="form-control"
															placeholder="" />
													</div>
												</div>

												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<label class="form-label">Boost QR</label> <input
															id="boostMDRQR_EzysplitModal" class="form-control"
															placeholder="" />
													</div>
												</div>

												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<label class="form-label">Grab EComm</label> <input
															id="grabMDREcomm_EzysplitModal" class="form-control"
															placeholder="" />
													</div>
												</div>
												
												
												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<label class="form-label">GrabQR</label> <input
															id="grabMDRQR_EzysplitModal" class="form-control"
															placeholder="" />
													</div>
												</div>
												
												
												
												
											</div>
											
											
											
												<div class="row g-3">
												
												
												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">

														<label class="form-label">FPX (RM)</label> <input
															id="fPXMDR_RM_EzysplitModal" class="form-control"
															placeholder="" />

													</div>
												</div>
												
												
												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">

														<label class="form-label">FPX (%)</label> <input
															id="fPXMDR_Percent_EzysplitModal" class="form-control"
															placeholder="" />

													</div>
												</div>
												
												

													<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<label class="form-label">Tng Ecomm</label> <input
															id="tngMDREcomm_EzysplitModal" class="form-control"
															placeholder="" />
													</div>
												</div>

												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">

														<label class="form-label">Tng QR</label> <input
															id="tngMDRQR_EzysplitModal" class="form-control"
															placeholder="" />

													</div>
												</div>
												
											
												
											</div>
											
											
											<div class="row g-3">
											
											
											<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">

														<label class="form-label">ShopeepayEcom</label> <input
															id="shopeepayMDREcomm_EzysplitModal" class="form-control"
															placeholder="" />

													</div>
												</div>
												
												
												
												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">

														<label class="form-label">ShopeepayQR</label> <input
															id="shopeepayMDRQR_EzysplitModal" class="form-control"
															placeholder="" />

													</div>
												</div>
												
												
											
											
											
											</div>
											
											
											
											
											<label><b>Settlement</b></label>
											<div class="row g-3">
												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">

														<label class="form-label">Boost</label> <input
															id="boostSettlement_EzysplitModal" class="form-control"
															placeholder="" />

													</div>
												</div>

												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">

														<label class="form-label">Grab</label> <input
															id="grabSettlement_EzysplitModal" class="form-control"
															placeholder="" />

													</div>
												</div>

												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<label class="form-label">FPX </label> <input
															id="fpxSettlement_EzysplitModal" class="form-control"
															placeholder="" />

													</div>
												</div>
											</div>
											
												<div class="row g-3">
												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">

														<label class="form-label">Tng</label> <input
															id="tngSettlement_EzysplitModal" class="form-control"
															placeholder="" />

													</div>
												</div>

												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">

														<label class="form-label">Shopeepay</label> <input
															id="shopeepaySettlement_EzysplitModal" class="form-control"
															placeholder="" />

													</div>
												</div>

												
											</div>
											
											

											<div id="ezysplitProductDiv">
												<!-- PAYMENT ICONS FOLLOWED BY LOCAL DEBIT MERCHANT-->

												<div class="row g-3" style="display: none;">

													<div class="col-sm-3  form-floating mb-3">
														<div class="mb-3"></div>
													</div>

													<div class="col-sm-2  col-4 form-floating mb-3">
														<div class="mb-3 text-center">
															<img height="40"
																src="${pageContext.request.contextPath}/resources/images/mastercard.png"
																alt="">
														</div>
													</div>
													<div class="col-sm-2  col-4 form-floating mb-3">
														<div class="mb-3 text-center">
															<img height="40"
																src="${pageContext.request.contextPath}/resources/images/mastercard.png"
																alt="">
														</div>
													</div>

												</div>

												<div class="row">
													<div class="col-6">

														<div
															style="padding-right: 125px; text-align: right; text-align-last: right; -ms-text-align-last: right; -moz-text-align-last: right;">
															<img height="40"
																src="${pageContext.request.contextPath}/resources/images/mastercard.png">

														</div>

														<h6 class="mb-3  mt-3 fw-bold">Instalment 3</h6>

														<div class="row g-3">
															<div
																class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
																<div class="mb-3 small ">Merchant MDR (%)</div>
															</div>

															<div class="col-sm-4  form-floating mb-3">
																<div class="mb-3">
																	<input id="loc_Credit_Merch_Master_Insta3"
																		class="form-control" type="number" step="0.001"
																		min="0" max="5"
																		placeholder="Local Credit Merchant MDR (%)" />
																</div>
															</div>
														</div>

														<div class="row g-3">
															<div
																class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
																<div class="mb-3 small ">Customer MDR (%)</div>
															</div>

															<div class="col-sm-4  form-floating mb-3">
																<div class="mb-3">
																	<input id="loc_Credit_Cus_Master_Insta3"
																		class="form-control" type="number" step="0.001"
																		min="0" max="5"
																		placeholder="Local Credit Customer MDR (%)" />
																</div>
															</div>
														</div>
													</div>

													<div class="col-6">

														<div
															style="padding-right: 125px; text-align: right; text-align-last: right; -ms-text-align-last: right; -moz-text-align-last: right;">
															<img height="40"
																src="${pageContext.request.contextPath}/resources/images/mastercard.png">

														</div>
														<h6 class="mb-3  mt-3 fw-bold">Instalment 6</h6>

														<div class="row g-3">
															<div
																class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
																<div class="mb-3 small ">Merchant MDR (%)</div>
															</div>

															<div class="col-sm-4  form-floating mb-3">
																<div class="mb-3">
																	<input id="loc_Credit_Merch_Master_Insta6"
																		class="form-control" type="number" step="0.001"
																		min="0" max="5"
																		placeholder="Local Credit Merchant MDR (%)" />
																</div>
															</div>
														</div>

														<div class="row g-3">
															<div
																class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
																<div class="mb-3 small ">Customer MDR (%)</div>
															</div>

															<div class="col-sm-4  form-floating mb-3">
																<div class="mb-3">
																	<input id="loc_Credit_Cus_Master_Insta6"
																		class="form-control"
																		placeholder="Local Credit Customer MDR (%)" />
																</div>
															</div>
														</div>
													</div>
												</div>

												<div class="row">
													<div class="col-6">
														<h6 class="mb-3  mt-3 fw-bold">Instalment 9</h6>

														<div class="row g-3">
															<div
																class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
																<div class="mb-3 small ">Merchant MDR (%)</div>
															</div>

															<div class="col-sm-4  form-floating mb-3">
																<div class="mb-3">
																	<input id="loc_Credit_Merch_Master_Insta9"
																		class="form-control" type="number" step="0.001"
																		min="0" max="5"
																		placeholder="Local Credit Merchant MDR (%)" />
																</div>
															</div>


														</div>

														<div class="row g-3">
															<div
																class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
																<div class="mb-3 small ">Customer MDR (%)</div>
															</div>



															<div class="col-sm-4  form-floating mb-3">
																<div class="mb-3">
																	<input id="loc_Credit_Cus_Master_Insta9"
																		class="form-control"
																		placeholder="Local Credit Customer MDR (%)" />
																</div>
															</div>
														</div>
													</div>

													<div class="col-6">
														<h6 class="mb-3  mt-3 fw-bold">Instalment 12</h6>

														<div class="row g-3">
															<div
																class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
																<div class="mb-3 small ">Merchant MDR (%)</div>
															</div>

															<div class="col-sm-4  form-floating mb-3">
																<div class="mb-3">
																	<input id="loc_Credit_Merch_Master_Insta12"
																		class="form-control" type="number" step="0.001"
																		min="0" max="5"
																		placeholder="Local Credit Merchant MDR (%)" />
																</div>
															</div>
														</div>

														<div class="row g-3">
															<div
																class="col-sm-6  form-floating mb-3 d-flex align-items-center justify-content-center">
																<div class="mb-3 small ">Customer MDR (%)</div>
															</div>
															<div class="col-sm-4  form-floating mb-3">
																<div class="mb-3">
																	<input id="loc_Credit_Cus_Master_Insta12"
																		class="form-control"
																		placeholder="Local Credit Customer MDR (%)" />
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</form:form>

									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary enableForAllRoles"
											data-bs-dismiss="modal">Close</button>
										<button type="button" class="btn btn-primary saveEzysplitMDR">Save
											changes</button>
									</div>
								</div>
							</div>
						</div>

						<!-- Modal -->
						<div class="modal fade" id="editModal" tabindex="-1" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog modal-dialog-scrollable  modal-lg">
								<div class="modal-content amend-quotation">
									<div class="modal-header">
										<h6 class="modal-title" id="exampleModalLabel">Amend Quotation
											MDR</h6>
										<button type="button" class="btn-close enableForAllRoles"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
										<form:form action="">
											<div class="row g-3">
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<label class="form-label">Product</label> <input
															id="productName" class="form-control" placeholder="" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<label class="form-label">Settlement</label> <input
															id="productSettlement" class="form-control"
															placeholder="" />
													</div>
												</div>

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<label class="form-label">Amount</label> <input id="amount"
															class="form-control" readonly="true" placeholder="" />
													</div>
												</div>
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<label class="form-label">Host Rate ID</label> <input
															id="hostRateId" class="form-control" placeholder=""
															readonly="readonly" />

													</div>
												</div>
												<div>
													<input type="hidden" id="productId" /> <input type="hidden"
														id="orderLineId" /> <input type="hidden"
														id="quotationMdrRateId" /> <input type="hidden"
														id="subscription" /> <input type="hidden" id="showInQuote" />
													<input type="hidden" id="productType" /> <input type="hidden"
														id="includeWallet" /> <input type="hidden" id="payLater" />
													<input type="hidden" id="hostType" />
													<input type="hidden" id="createdOn" />
													<input type="hidden" type="number" step="0.001" min="0" max="4"
														id="loc_Debit_Host_Visa" />
													<input type="hidden" type="number" step="0.001" min="0" max="4"
														id="loc_Debit_Host_Master" />
													<input type="hidden" type="number" step="0.001" min="0" max="4"
														id="loc_Debit_Host_Union" />
													<input type="hidden" type="number" step="0.001" min="0" max="4"
														id="for_Debit_Host_Visa" />
													<input type="hidden" type="number" step="0.001" min="0" max="4"
														id="for_Debit_Host_Master" />
													<input type="hidden" type="number" step="0.001" min="0" max="4"
														id="for_Debit_Host_Union" />
													<input type="hidden" type="number" step="0.001" min="0" max="4"
														id="loc_Credit_Host_Visa" />
													<input type="hidden" type="number" step="0.001" min="0" max="4"
														id="loc_Credit_Host_Master" />
													<input type="hidden" type="number" step="0.001" min="0" max="4"
														id="loc_Credit_Host_Union" />
													<input type="hidden" type="number" step="0.001" min="0" max="4"
														id="for_Credit_Host_Visa" />
													<input type="hidden" type="number" step="0.001" min="0" max="4"
														id="for_Credit_Host_Master" />
													<input type="hidden" type="number" step="0.001" min="0" max="4"
														id="for_Credit_Host_Union" />
												</div>
											</div>
											<label><b>Wallet Details</b></label>
											<br>
											<label><b>MDR</b></label>

											<div class="row g-3">
												<div class="col-sm-2  form-floating mb-3" id="id-boost-ecomm">
													<div class="mb-3">

														<label class="form-label">Boost EComm</label> <input
															id="boostMDREcomm" class="form-control" placeholder="" />

													</div>
												</div>
												<div class="col-sm-2  form-floating mb-3" id="id-boost-qr">
													<div class="mb-3">

														<label class="form-label">Boost QR</label> <input
															id="boostMDRQR" class="form-control" placeholder="" />

													</div>
												</div>

												<div class="col-sm-2  form-floating mb-3" id="id-grab-ecomm">
													<div class="mb-3">

														<label class="form-label">Grab EComm</label> <input
															id="grabMDREcomm" class="form-control" placeholder="" />

													</div>
												</div>
												
												
												<div class="col-sm-2  form-floating mb-3" id="id-grab-qr">
													<div class="mb-3">

														<label class="form-label">Grab QR</label> <input id="grabMDRQR"
															class="form-control" placeholder="" />

													</div>
												</div>

												
												
											</div>
											
											
											
											
											
											
											
											
													<div class="row g-3">
												
												
												

												<div class="col-sm-2  form-floating mb-3" id="id-fpx-mdr-rm">
													<div class="mb-3">

														<label class="form-label">FPX (RM)</label> <input id="fPXMDR_RM"
															class="form-control" placeholder="" />

													</div>
												</div>
												<div class="col-sm-2  form-floating mb-3" id="id-fpx-mdr-percentage">
													<div class="mb-3">

														<label class="form-label">FPX (%)</label> <input
															id="fPXMDR_Percent" class="form-control" placeholder="" />

													</div>
												</div>
												
												
												<div class="col-sm-2  form-floating mb-3" id="id-tng-ecomm">
													<div class="mb-3">

														<label class="form-label">Tng Ecomm</label> <input id="tngMDREcomm"
															class="form-control" placeholder="" />

													</div>
												</div>
												
												
												<div class="col-sm-2  form-floating mb-3" id="id-tng-qr">
													<div class="mb-3">

														<label class="form-label">Tng QR</label> <input id="tngMDRQR"
															class="form-control" placeholder="" />

													</div>
												</div>
												
												
												
											</div>
											
											
											
											
											
											<div class="row g-3">
												
												

												
												<div class="col-sm-2  form-floating mb-3" id="id-shopeepay-ecomm">
													<div class="mb-3">

														<label class="form-label">ShopeepayEcom</label> <input
															id="shopeepayMDREcomm" class="form-control" placeholder="" />

													</div>
												</div>
												
												
												<div class="col-sm-2  form-floating mb-3" id="id-shopeepay-qr">
													<div class="mb-3">

														<label class="form-label">Shopeepay QR</label> <input id="shopeepayMDRQR"
															class="form-control" placeholder="" />

													</div>
												</div>
												
												
												
												
											</div>
											
										
											
											
											<label><b>Settlement</b></label>
											<div class="row g-3">
												<div class="col-sm-2  form-floating mb-3" id="id-boost-settlements">
													<div class="mb-3">

														<label class="form-label">Boost</label> <input
															id="boostSettlement" class="form-control" placeholder="" />

													</div>
												</div>

												<div class="col-sm-2  form-floating mb-3" id="id-grab-settlements">
													<div class="mb-3">

														<label class="form-label">Grab</label> <input
															id="grabSettlement" class="form-control" placeholder="" />

													</div>
												</div>

												<div class="col-sm-2  form-floating mb-3" id="id-fpx-settlements">
													<div class="mb-3">
														<label class="form-label">FPX </label> <input id="fpxSettlement"
															class="form-control" placeholder="" />

													</div>
												</div>
											</div>
											
											
											<div class="row g-3">
												<div class="col-sm-2  form-floating mb-3" id="id-tng-settlements">
													<div class="mb-3">

														<label class="form-label">Tng</label> <input
															id="tngSettlement" class="form-control" placeholder="" />

													</div>
												</div>

												<div class="col-sm-2  form-floating mb-3" id="id-shopeepay-settlements">
													<div class="mb-3">

														<label class="form-label">Shopeepay</label> <input
															id="shopeepaySettlement" class="form-control" placeholder="" />

													</div>
												</div>

												
											</div>


											<div class="row g-3">

												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3"></div>
												</div>

												<div class="col-sm-2  col-4 form-floating mb-3">
													<div class="mb-3 text-center">
														<img height="40"
															src="${pageContext.request.contextPath}/resources/images/visa.png"
															alt="">
													</div>
												</div>


												<div class="col-sm-2  col-4 form-floating mb-3">
													<div class="mb-3 text-center">
														<img height="40"
															src="${pageContext.request.contextPath}/resources/images/mastercard.png"
															alt="">
													</div>
												</div>


												<div class="col-sm-2  col-4 form-floating mb-3">
													<div class="mb-3 text-center">
														<img height="40"
															src="${pageContext.request.contextPath}/resources/images/union_pay.png"
															alt="">
													</div>
												</div>

											</div>
											<label><b>Merchant Rates</b></label>
											<div class="row g-3">
												<div class="col-sm-3  form-floating mb-2">
													<div class="mb-2">
														<label class="form-label">Local Debit (%)</label>
													</div>
												</div>
												<div class="col-sm-2  form-floating mb-1">
													<div class="mb-2">
														<input id="loc_Debit_Merch_Visa" class="form-control"
															type="number" step="0.001" min="0" max="5" placeholder="" />
													</div>
												</div>
												<div class="col-sm-2  form-floating mb-2">
													<div class="mb-2">
														<input id="loc_Debit_Merch_Master" class="form-control"
															type="number" step="0.001" min="0" max="5" placeholder="" />
													</div>
												</div>
												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<input id="loc_Debit_Merch_Union" class="form-control"
															type="number" step="0.001" min="0" max="5" placeholder="" />
													</div>
												</div>
											</div>

											<div class="row g-3">
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<label class="form-label">Local Credit (%)</label>
													</div>
												</div>
												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<input id="loc_Credit_Merch_Visa" class="form-control"
															type="number" step="0.001" min="0" max="5" placeholder="" />
													</div>
												</div>
												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<input id="loc_Credit_Merch_Master" class="form-control"
															type="number" step="0.001" min="0" max="5" placeholder="" />
													</div>
												</div>
												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<input id="loc_Credit_Merch_Union" class="form-control"
															type="number" step="0.001" min="0" max="5" placeholder="" />
													</div>
												</div>
											</div>

											<div class="row g-3">
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<label class="form-label">Foreign Debit (%)</label>
													</div>
												</div>
												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<input id="for_Debit_Merch_Visa" class="form-control"
															type="number" step="0.001" min="0" max="5" placeholder="" />
													</div>
												</div>
												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<input id="for_Debit_Merch_Master" class="form-control"
															type="number" step="0.001" min="0" max="5" placeholder="" />
													</div>
												</div>
												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<input id="for_Debit_Merch_Union" class="form-control"
															type="number" step="0.001" min="0" max="5" placeholder="" />
													</div>
												</div>
											</div>

											<div class="row g-3">
												<div class="col-sm-3  form-floating mb-3">
													<div class="mb-3">
														<label class="form-label">Foreign Credit (%)</label>
													</div>
												</div>
												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<input id="for_Credit_Merch_Visa" class="form-control"
															type="number" step="0.001" min="0" max="5" placeholder="" />
													</div>
												</div>
												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<input id="for_Credit_Merch_Master" class="form-control"
															type="number" step="0.001" min="0" max="5" placeholder="" />
													</div>
												</div>
												<div class="col-sm-2  form-floating mb-3">
													<div class="mb-3">
														<input id="for_Credit_Merch_Union" class="form-control"
															type="number" step="0.001" min="0" max="5" placeholder="" />
													</div>
												</div>
											</div>


										</form:form>

									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary enableForAllRoles"
											data-bs-dismiss="modal">Close</button>
										<button type="button" class="btn btn-primary saveMDR">Save
											changes</button>
									</div>
								</div>
							</div>
						</div>

						<!--Payment Collected Modal -->
						<div class="modal fade" id="paymentCollectedModal" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<h6 class="modal-title" id="exampleModalLabel">Payment Info</h6>
										<button type="button" class="btn-close enableForAllRoles action-button"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
										<div>
											<input type="hidden" name="quotationId" value="${quotation.id }">
											<input type="hidden" name="acquirer" value="${quotation.acquirer }">
										</div>
										<div class="row g-3">
											<div class="col-sm-6  form-floating mb-3">
												<div class="mb-3">
													<label for="collectedOn" class="form-label">
														Payment Collected On</label>
													<div class="input-group mb-3">
														<input id="payment-collect-date" type="date"
															class="form-control action-input" /> <span
															class="input-group-text"><i
																class="fa fa-calendar"></i></span>
													</div>
													<div id="payment-collect-date-error"
														style="color: red; font-size: small; display: none;">
														Date Required</div>
												</div>
											</div>


											<div class="col-sm-6  form-floating mb-3">
												<div class="mb-3">
													<label for="payment-collect-file" class="form-label">Select
														a file to upload</label> <input type="file"
														id="payment-collect-file" accept=".jpg, .jpeg, .png, .pdf"
														name="file" class="form-control action-input" placeholder="" />
													<div id="payment-collect-file-error"
														style="color: red; font-size: small; display: none;">Please
														upload payment receipt</div>
												</div>
											</div>

										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary enableForAllRoles action-button"
											data-bs-dismiss="modal">Close</button>
										<button type="button" onclick="moveToFinance()"
											class="btn btn-primary paymentCollected action-button">Submit</button>
									</div>
								</div>
							</div>
						</div>


						<!--Verify Payment Modal -->
						<div class="modal fade" id="paymentVerifiedModal" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog modal-lg">
								<div class="modal-content">
									<div class="modal-header">
										<h6 class="modal-title" id="exampleModalLabel">Verify Payment</h6>
										<button type="button" class="btn-close enableForAllRoles action-button"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
										<div>
											<input type="hidden" id="paymentId" value="${quotation.payment.id }"> <input
												type="hidden" name="quotationId" value="${quotation.id }"> <input
												type="hidden" name="acquirer" value="${quotation.acquirer }">
										</div>
										<div class="row g-3">

											<div class="col-sm-3  form-floating mb-3">
												<div class="mb-3">
													<label class="form-label">QB Invoice No</label> <input type="text"
														class="form-control action-input" placeholder="QB Invoice No"
														id="invoiceNo" />
												</div>
											</div>
											<div class="col-sm-3  form-floating mb-3">
												<div class="mb-3">
													<label class="form-label">Mode of Payment</label>
													<select type="text" class="form-control action-select"
														id="modeOfPayment" placeholder="Mode of Payment">
														<option value="Cash" selected>Cash</option>
														<option value="Cheque">Cheque</option>
														<option value="Online Transfer">Online Transfer</option>
													</select>
												</div>
											</div>

										</div>
									</div>
									<div class="modal-footer">

										<button type="button" class="btn btn-secondary enableForAllRoles action-button"
											data-bs-dismiss="modal">Close</button>
										<button type="button" class="btn btn-primary paymentVerified action-button">Move
											To Processing</button>
									</div>
								</div>
							</div>
						</div>


						<!-- Modal -->
						<div class="modal fade" id="paymentConfirmationModal" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h6 class="modal-title" id="ModalLabel">Verify Payment</h6>
										<button type="button" class="btn-close" data-bs-dismiss="modal"
											aria-label="Close"></button>
									</div>
									<div class="modal-body">
										<label>New Order has been Created!</label>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">No</button>
										<a href="QuotationView?id=${quotation.id}&amp;" class="btn btn-primary"></a>
										<%-- <a class="btn btn-primary" href="QuotationView?id=${quotation.id}&amp;">
											Ok</a> --%>

									</div>
								</div>
							</div>
						</div>

						<!-- issueQuotation Confirm Dialog -->
						<div class="modal fade" id="issueQuotationModal" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h6 class="modal-title" id="exampleModalLabel">Alert</h6>
										<button type="button" class="btn-close enableForAllRoles action-button"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
										<p>Please confirm to issue quotation</p>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary enableForAllRoles action-button"
											data-bs-dismiss="modal">Close</button>
										<button type="button" class="btn btn-primary enableForAllRoles action-button"
											onclick="issueNewQuotation()">Confirm</button>
									</div>
								</div>
							</div>
						</div>

						<!-- rk added -->

						<div class="modal fade" id="SendProfomaEmailModal" tabindex="-1"
							aria-labelledby="exampleModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h6 class="modal-title" id="exampleModalLabel">Alert</h6>
										<button type="button" class="btn-close enableForAllRoles action-button"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
										<p>Please confirm to send Profoma</p>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary enableForAllRoles action-button"
											data-bs-dismiss="modal">Close</button>
										<button type="button" class="btn btn-primary enableForAllRoles action-button"
											onclick="issueNewProfoma()">Confirm</button>
									</div>
								</div>
							</div>
						</div>


						<!-- Modify User ID Confirm Dialog -->
						<div class="modal fade" id="modifyUserIdModal" tabindex="-1" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h6 class="modal-title">Modify OCS UserID</h6>
										<button type="button" class="btn-close enableForAllRoles action-button"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">
										<div class="row g-3">
											<div class="col-sm-8  form-floating mb-3">
												<div class="mb-3">
													<label class="form-label"> User ID</label> <input
														class="form-control enableForAllRoles action-input"
														id="newUserId" placeholder="New Mobile Number" />
												</div>
											</div>
										</div>


										<p>Note: Modifying User ID of Merchant will deliver an email
											with modified Phone Number to signup</p>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary enableForAllRoles action-button"
											data-bs-dismiss="modal">Close</button>
										<button type="button" class="btn btn-primary enableForAllRoles action-button"
											onclick="modifyUserId()">Confirm</button>
									</div>
								</div>
							</div>
						</div>


						<div class="mobi-model" id="mobi-model" style="display: none;">
							<div class="mobi-model-container"
								style="width: 100%; background-color: white; padding: 1rem; align-items: center; z-index: 1; border-radius: 8px;">
								<div class="mobi-model-title" id="mobi-model-title"
									style="font-size: 30px; font-weight: 500;">Note!</div>
								<div class="mobi-model-body" id="mobi-model-body">
									<p>Is this merchant iSwitch2Mobi</p>
								</div>
								<div class="mobi-model-footer" id="mobi-model-footer"
									style="display: flex; flex-direction: row-reverse; width: 100%;">
									<button class="btn btn-sm btn-danger m-1">No</button>
									<button class="btn btn-sm btn-primary m-1"
										onclick="makeNewISwitch2Mobi()">Yes</button>
								</div>
							</div>
						</div>

					</body>

					<script type="text/javascript">
						var contextPath = "${pageContext.request.contextPath}"

						var token = $("meta[name='_csrf']").attr("content");
						var header = $("meta[name='_csrf_header']").attr("content");


						const mobiAlertDialog = document.getElementById("mobi-model")

						const quotationId = $('#quotationId').val()

						const productRateDialog = document.getElementById("")

						var paymentCollectedModal = new bootstrap.Modal(document.getElementById('paymentCollectedModal'), { keyboard: false })

						const issueQuotationModal = new bootstrap.Modal(document.getElementById('issueQuotationModal'), {
							keyboard: false
						})

						const SendProfomaEmailModal = new bootstrap.Modal(document.getElementById('SendProfomaEmailModal'), {
							keyboard: false
						})

						const modifyUserIdModal = new bootstrap.Modal(document.getElementById('modifyUserIdModal'), {
							keyboard: false
						})


						const buttonSaveDetails = document.getElementById("button-save-details")
						const buttonEditDetails = document.getElementById("button-edit-details")

						const buttonMoveToProcessing = document.getElementById("btnMoveToProcessing")
						buttonMoveToProcessing.addEventListener('click', (e) => {
							moveToProcessingStage()
						})

						function onCloseProductContainer() {
							productRateDialog.style.display = "none"
						}

						$(function () {
							$(".datepick").datepicker();
						});


						function closeModel() {
							mobiAlertDialog.style.visibility = "collapse"
						}

						document.getElementById("id-discount-price").addEventListener('input', (event) => {
							const subtotal = "${subtotal}";
							const discountPrice = event.target.value
							document.getElementById("id-total-price").value = parseFloat(parseFloat(subtotal) - parseFloat(discountPrice)).toFixed(2)
						})


						document.getElementById("button-apply-discount").addEventListener("click", (event) => {
							event.preventDefault();
							const discount = document.getElementById("id-discount-price").value
							const discountReason = document.getElementById("id-discount-reason").value
							const subtotal = "${subtotal}"
							const total = document.getElementById("id-total-price").value

							if (total >= 0) {
								applyDiscount(discount, discountReason)
							} else {
								alert("Please enter valid discount Rate")
								document.getElementById("id-discount-price").value = "0.00"
								document.getElementById("id-total-price").value = subtotal
							}
						})

						const role = "${loggedUserRole}";

						window.onload = function () {

							console.log("token-> ", token)
							console.log("header-> ", token)

							$(document).ajaxSend(function (e, xhr, options) {
								xhr.setRequestHeader(header, token);
							});

							$.ajaxSetup({
								headers: {
									'X-CSRF-TOKEN': $('meta[name="csrf-token"]').attr('content')
								}
							});

							getQuotationHistory();
							LoadProducts();

							var stage = "${quotation.stage }";

							var userId = "${loggedUserId }";
							var salespersonPhone = "${quotation.salesPerson.phone }";
							console.log(stage);
							console.log(role);
							console.log(stage != role)

							$('input').attr('readonly', true);
							// $('select').attr('disabled', true);
							$('button').attr('disabled', true);

							$('.action-button').removeAttr('disabled');
							$('.action-select').removeAttr('disabled');
							$('.action-input').removeAttr('readonly');
							$('.nav-link').removeAttr('disabled');
							$('#button-edit-details').removeAttr('disabled');
							$('#button-save-details').removeAttr('disabled');
							
							$('#rollbackclose').removeAttr('disabled');
							$('#rollbackbutton').removeAttr('disabled');
							$('#rollbackwrong').removeAttr('disabled');
							$('#rollback-reason').removeAttr('readonly');
							
							

							$('.amend-quotation input').removeAttr('readonly');
							$('.amend-quotation button').removeAttr('disabled');

							const isQuotationAccepted = "${quotation.quotationAccepted}"
							const digitalSignature = document.getElementById("digital-signature")
							if (isQuotationAccepted === "1") {
								const signatureData = contextPath + "${quotation.quotationAcceptance.signature.imageUrl}"
								digitalSignature.src = signatureData


							} else {
								digitalSignature.src = contextPath + "/resources/images/signature_not_available.png"
							}

							//  apply Discount Price

							const subtotal = "${subtotal}";
							const discountPrice = "${discountPrice}"
							document.getElementById("id-discount-price").value = discountPrice
							document.getElementById("id-discount-reason").value = "${discountReason}"
							document.getElementById("id-total-price").value = parseFloat((subtotal - discountPrice)).toFixed(2)


							if (stage === 'finance') {
								$("#btnMoveToFinance").attr('disabled', true);
							}

							if (stage === 'sales') {
								if (isQuotationAccepted === "1") {
									$("#btnMoveToFinance").removeAttr('disabled');
								} else {
									$("#btnMoveToFinance").attr('disabled', true);
								}
							}



						}


						function applySeparator(event) {
							const formattedString = numberWithCommas(event.target.value);
							console.log(formattedString);
							event.target.value = formattedString;
						}

						function numberWithCommas(x) {
							return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
						}

						buttonEditDetails.addEventListener('click', (event) => {
							if (role === 'sales' || role === 'finance' || role === 'admin' ) {
								event.preventDefault();
								event.target.style.display = "none";
								buttonSaveDetails.style.display = "block";
								$('input').attr('readonly', false);
								$('select').attr('disabled', false);
								$('button').attr('disabled', false);

								$('.readOnly').attr('readonly', true);
							} else {
								event.preventDefault();
								alert("You do not have access to change the details!")
							}
						})

						function getQuotationHistory() {
							const config = {
								method: "POST",
								url: contextPath + "/quotation/history",
								headers: {
									'X-CSRF-TOKEN': token,
									'Content-Type': 'application/json'
								},
								data: {
									quotationId: quotationId
								}
							}

							axios(config)
								.then(response => {
									return response.data
								})
								.then(response => {
									console.log("response --> ", response)
									if (response.responseStatus === "0000") {
										listIssueQuotation(response.responseData)
									} else {
										alert(response.responseMessage)
									}
								})
								.catch(error => {
									console.log("error --> ", error)
								})
								.finally(() => { })
						}

						function issueNewQuotation() {
							startLoadingScreen("Loading, please wait...");
							event.preventDefault()

							issueQuotationModal.hide()

							const config = {
								method: "POST",
								url: contextPath + "/quotation/issue",
								headers: {
									'X-CSRF-TOKEN': token,
									'Content-Type': 'application/json'
								},
								data: {
									quotationId: quotationId
								}
							}

							axios(config)
								.then(response => {
									return response.data
								})
								.then(response => {
									if (response.responseStatus === "0000") {
										alert("Quotation has been sent successfully")
									} else {
										alert(response.responseMessage)
									}
								})
								.catch(error => {
									console.log(error)
								})
								.finally(() => {
									getQuotationHistory()
									stopLoadingScreen()
								})

						}


						//rk added	

						function issueNewProfoma() {
							startLoadingScreen("Loading, please wait...");
							event.preventDefault()

							SendProfomaEmailModal.hide()

							const config = {
								method: "POST",
								url: contextPath + "/quotation/profoma/issue",
								headers: {
									'X-CSRF-TOKEN': token,
									'Content-Type': 'application/json'
								},
								data: {
									quotationId: quotationId
								}
							}

							axios(config)
								.then(response => {
									return response.data
								})
								.then(response => {
									if (response.responseStatus === "0000") {
										alert("Profoma has been sent successfully")
									} else {
										alert(response.responseMessage)
									}
								})
								.catch(error => {
									console.log(error)
								})
								.finally(() => {
									getQuotationHistory()
									stopLoadingScreen()
								})

						}


						function modifyUserId() {
							startLoadingScreen("Loading, please wait...");
							event.preventDefault()

							modifyUserIdModal.hide()

							const config = {
								method: "POST",
								url: contextPath + "/quotation/modifyOCSUserId",
								headers: {
									'X-CSRF-TOKEN': token,
									'Content-Type': 'application/json'
								},
								params: {
									newUserId: $('#newUserId').val(),
									quotationId: quotationId
								}
							}

							axios(config)
								.then(response => {
									return response.data
								})
								.then(response => {
									console.log("response --> ", response)
									alert(response.responseMessage)

								})
								.catch(error => {
									console.log("error --> ", error)
								})
								.finally(() => {
									stopLoadingScreen()
									window.location.reload()
								})
						}

						function viewProfomaInvoice() {
							event.preventDefault();
							startLoadingScreen("Loading, please wait...");
							modifyUserIdModal.hide()

							const config = {
								method: "POST",
								url: contextPath + "/quotation/profoma/view",
								headers: {
									'X-CSRF-TOKEN': token,
									'Content-Type': 'application/json'
								},
								params: {

									quotationId: quotationId
								}
							}

							axios(config)
								.then(response => {
									return response.data
								})
								.then(response => {
									if (response.responseStatus === "0000") {
										window.open(contextPath + response.responseData.profomapath, '_blank');
									}

								})
								.catch(error => {
									console.log("error --> ", error)
								})
								.finally(() => {
									stopLoadingScreen()
								})
						}

						function issueNewQuotation() {
							event.preventDefault();
							startLoadingScreen("Loading, please wait...");

							issueQuotationModal.hide()

							const config = {
								method: "POST",
								url: contextPath + "/quotation/issue",
								headers: {
									'X-CSRF-TOKEN': token,
									'Content-Type': 'application/json'
								},
								data: {
									quotationId: quotationId
								}
							}

							axios(config)
								.then(response => {
									return response.data
								})
								.then(response => {
									if (response.responseStatus === "0000") {
										alert("Quotation has been sent successfully")
									} else {
										alert(response.responseMessage)
									}
								})
								.catch(error => {
									console.log(error)
								})
								.finally(() => {
									getQuotationHistory()
									stopLoadingScreen()
								})

						}

						function listIssueQuotation(data) {
							const documentTableBody = document.getElementById("table-body-quotation-history");
							documentTableBody.innerHTML = "";


							if (data.length > 0) {
								data.forEach((item, index) => {
									const tableRow = document.createElement('tr')

									const sNo = document.createElement('td')
									sNo.innerHTML = index + 1

									const quotationName = document.createElement('td')
									quotationName.innerHTML = item.documentName

									const createdAt = document.createElement('td')
									createdAt.innerHTML = moment(item.createdAt).format("DD/MM/YYYY hh:mm A")

									const action = document.createElement('td')

									const viewButton = document.createElement('button')
									viewButton.className = "btn btn-primary"
									viewButton.innerText = "View Document"
									viewButton.value = item.documentPath
									viewButton.onclick = function () {
										const documentUrl = contextPath + event.target.value
										window.open(documentUrl)
									}
									action.appendChild(viewButton)

									tableRow.appendChild(sNo)
									tableRow.appendChild(quotationName)
									tableRow.appendChild(createdAt)
									tableRow.appendChild(action)

									documentTableBody.appendChild(tableRow)
								});
							} else {
								const tableRow = document.createElement('tr')
								const noDocumentAvailable = document.createElement('td')
								noDocumentAvailable.innerText = "No Documents Available"
								tableRow.appendChild(noDocumentAvailable)
								documentTableBody.appendChild(tableRow)
							}
						}

						$("#btnPDFDownload").click(function (e) {
							e.preventDefault();
							$.ajax({
								type: "GET",
								url: "generateQuotationMobi",
								data: {
									access_token: $("#access_token").val(),
									quotationId: $('#quotationId').val()
								},
								success: function (result) {
									console.log(result)
									if (result.responseStatus === "0000") {
										if (result.responseData !== null) {
											openDocumentInNewTap(result.responseData)
										} else {
											alert("No quotation available to view")
										}
									} else {
										alert(result.responseMessage)
									}
								},
								error: function (result) {
									alert('error');
								}
							});
						});

						function openDocumentInNewTap(data) {
							console.log("openDocumentInNewTap -> ", data)
							const pdfUrl = contextPath + data.documentPath
							window.open(pdfUrl);
						}

						document.getElementById("button-issue-quotation").onclick = function () {
							issueQuotationModal.show()
						}

						document.getElementById("SendProfomaEmail").onclick = function () {
							SendProfomaEmailModal.show()
						}


						document.getElementById("button-modify-userid").addEventListener('click', (e) => {
							e.preventDefault();
							modifyUserIdModal.show();
						});

						buttonSaveDetails.addEventListener('click', (event) => {
							startLoadingScreen("Processing, please wait...")
							$('#quotationForm').attr('action', 'updateQuotation');
						});


						$('#btnSubmit').click(function () {
							startLoadingScreen("Processing, please wait...")
							$('#quotationForm').attr('action', 'submitQuotation');
						});

						function LoadProducts() {
							var productType = $('#productTypes').val();

							productType = productType === "EZYREC+" ? "EZYREC_P" : productType

							$.ajax({
								type: 'GET',
								url: "getProducts?type="
									+ productType,
								success: function (
									data) {
									console
										.log(data);
									var products = $('#products'), option = "";
									products
										.empty();

									for (var i = 0; i < data.length; i++) {
										option = option
											+ "<option value='" + data[i].id + "'>"
											+ data[i].productName
											+ "</option>";
									}

									products
										.append(option);
								},
								error: function () {
									alert("error");
								}
							});
						}

						$('#productTypes').change(function () {
							LoadProducts();
						});

						$('.add').click(function (e) {
							var count = 0;
							var productId = $('#products').val();
							var quotationId = $('#quotationId').val();

							startLoadingScreen("Loading, please wait...")
							$.ajax({
								type: 'GET',
								url: "addOrderLine?productId="
									+ productId
									+ "&quotationId="
									+ quotationId,
								success: function (data) {
									window.location.reload;
								},
								error: function () {
									alert("error");
									stopLoadingScreen()
								}

							});
						});

						$('#btnDeleteOrderLine').click(
							function (e) {
								e.preventDefault();
								var quotationId = $('#quotationId').val();

								var checkedValues = $('.checkbox:checked').map(function () {
									return this.value;
								}).get();

								console.log(checkedValues);

								$.ajax({
									type: 'POST',
									url: "deleteOrderLines",
									data: { orderLineIds: checkedValues.toString(), quotationId: quotationId },
									success: function (data) {
										console.log(data);
										location.reload();
									}
								});
							});

						$(document).on('click', '.edit', function (e) {
							e.preventDefault();

							var orderLineId = $(this).attr('data-id');
							var productType = "";

							var productId = $('#productId').val();

							$.ajax({
								type: 'GET',
								url: "getQuotationMDR?orderLineId="
									+ orderLineId,
								success: function (data) {
									console.log(data)
									productType = data.productType;

									if (productType == 'EZYSPLIT') {
										$('#editModalEZYSPLIT').modal('show');
										//productRateEzySplitDialog.style.display = "block"
										$('#quotationEzysplitMdrRateId_EzysplitModal').val(data.quotationEzysplitMdrRateId);
										$('#quotationId_EzysplitModal').val(data.quotationId);
										$('#productId_EzysplitModal').val(data.productId);
										$('#orderLineId_EzysplitModal').val(data.orderLineId);
										$('#productType_EzysplitModal').val(data.productType);
										$('#productName_EzysplitModal').val(data.productName);
										$('#productSettlement_EzysplitModal').val(data.productSettlement);
										$('#amount_EzysplitModal').val(data.amount);
										$('#hostRateId_EzysplitModal').val(data.hostRateId);
										$('#subscription_EzysplitModal').val(data.subscription);
										$('#showInQuote_EzysplitModal').val(data.showInQuote);
										$('#includeWallet_EzysplitModal').val(data.includeWallet);
										$('#payLater_EzysplitModal').val(data.payLater);
										$('#hostType_EzysplitModal').val(data.hostType);
										$('#createdOn_EzysplitModal').val(data.createdOn);
										$('#boostMDREcomm_EzysplitModal').val(data.boostMDREcomm);
										$('#grabMDREcomm_EzysplitModal').val(data.grabMDREcomm);
										$('#boostMDRQR_EzysplitModal').val(data.boostMDRQR);
										$('#grabMDRQR_EzysplitModal').val(data.grabMDRQR);
										
										$('#tngMDREcomm_EzysplitModal').val(data.tngMDREcomm);
										$('#tngMDRQR_EzysplitModal').val(data.tngMDRQR);
										$('#shopeepayMDREcomm_EzysplitModal').val(data.shopeepayMDREcomm);
										$('#shopeepayMDRQR_EzysplitModal').val(data.shopeepayMDRQR);
										$('#tngSettlement_EzysplitModal').val(data.tngSettlement);
										$('#shopeepaySettlement_EzysplitModal').val(data.shopeepaySettlement);
										
										
										
										
										$('#fPXMDR_RM_EzysplitModal').val(data.fPXMDR_RM);
										$('#fPXMDR_Percent_EzysplitModal').val(data.fPXMDR_Percent);
										$('#boostSettlement_EzysplitModal').val(data.boostSettlement);
										$('#grabSettlement_EzysplitModal').val(data.grabSettlement);
										$('#fpxSettlement_EzysplitModal').val(data.fpxSettlement);
										$('#loc_Credit_Merch_Master_Insta3').val(data.loc_Credit_Merch_Master_Insta3);
										$('#loc_Credit_Cus_Master_Insta3').val(data.loc_Credit_Cus_Master_Insta3);
										$('#loc_Credit_Host_Master_Insta3').val(data.loc_Credit_Host_Master_Insta3);
										$('#loc_Credit_Merch_Master_Insta6').val(data.loc_Credit_Merch_Master_Insta6);
										$('#loc_Credit_Cus_Master_Insta6').val(data.loc_Credit_Cus_Master_Insta6);
										$('#loc_Credit_Host_Master_Insta6').val(data.loc_Credit_Host_Master_Insta6);
										$('#loc_Credit_Merch_Master_Insta9').val(data.loc_Credit_Merch_Master_Insta9);
										$('#loc_Credit_Cus_Master_Insta9').val(data.loc_Credit_Cus_Master_Insta9);
										$('#loc_Credit_Host_Master_Insta9').val(data.loc_Credit_Host_Master_Insta9);
										$('#loc_Credit_Merch_Master_Insta12').val(data.loc_Credit_Merch_Master_Insta12);
										$('#loc_Credit_Cus_Master_Insta12').val(data.loc_Credit_Cus_Master_Insta12);
										$('#loc_Credit_Host_Master_Insta12').val(data.loc_Credit_Host_Master_Insta12);
										getPendingQuotationMDRRatesEzySplit()
									}
									else {
										$('#editModal').modal('show');
										//productRateDialog.style.display = "block"
										$('#quotationMdrRateId').val(data.quotationMdrRateId);
										$('#quotationId').val(data.quotationId);
										$('#productId').val(data.productId);
										$('#orderLineId').val(data.orderLineId);
										$('#productType').val(data.productType);
										$('#productName').val(data.productName);
										$('#productSettlement').val(data.productSettlement);
										$('#amount').val(data.amount);
										$('#hostRateId').val(data.hostRateId);
										$('#subscription').val(data.subscription);
										$('#showInQuote').val(data.showInQuote);
										$('#includeWallet').val(data.includeWallet);
										$('#payLater').val(data.payLater);
										$('#hostType').val(data.hostType);
										$('#createdOn').val(data.createdOn);


										$('#boostMDREcomm').val(data.boostMDREcomm);
										$('#grabMDREcomm').val(data.grabMDREcomm);
										$('#boostMDRQR').val(data.boostMDRQR);
										$('#grabMDRQR').val(data.grabMDRQR);
										$('#fPXMDR_RM').val(data.fPXMDR_RM);
										$('#fPXMDR_Percent').val(data.fPXMDR_Percent);
										$('#boostSettlement').val(data.boostSettlement);
										$('#grabSettlement').val(data.grabSettlement);
										$('#fpxSettlement').val(data.fpxSettlement);
										
										
										$('#tngMDREcomm').val(data.tngMDREcomm);
										$('#tngMDRQR').val(data.tngMDRQR);
										$('#shopeepayMDREcomm').val(data.shopeepayMDREcomm);
										$('#shopeepayMDRQR').val(data.shopeepayMDRQR);
										$('#tngSettlement').val(data.tngSettlement);
										$('#shopeepaySettlement').val(data.shopeepaySettlement);
										
										

										if (data.productType === 'EZYWIRE') {
											$('#id-boost-ecomm').hide()
											
											$('#id-shopeepay-ecomm').hide()
											$('#id-tng-ecomm').hide()
											$('#id-grab-ecomm').hide()
											$('#id-fpx-mdr-rm').hide()
											$('#id-fpx-mdr-percentage').hide()
											$('#id-fpx-settlements').hide()
										} else {
											$('#id-boost-ecomm').show()
											$('#id-grab-ecomm').show()
											$('#id-shopeepay-ecomm').show()
											$('#id-tng-ecomm').show()
											$('#id-fpx-mdr-rm').show()
											$('#id-fpx-mdr-percentage').show()
											$('#id-fpx-settlements').show()
										}


										$('#loc_Debit_Merch_Visa').val(data.loc_Debit_Merch_Visa);
										$('#loc_Debit_Merch_Master').val(data.loc_Debit_Merch_Master);
										$('#loc_Debit_Merch_Union').val(data.loc_Debit_Merch_Union);
										$('#for_Debit_Merch_Visa').val(data.for_Debit_Merch_Visa);
										$('#for_Debit_Merch_Master').val(data.for_Debit_Merch_Master);
										$('#for_Debit_Merch_Master').val(data.for_Debit_Merch_Master);
										$('#for_Debit_Merch_Union').val(data.for_Debit_Merch_Union);
										$('#loc_Credit_Merch_Visa').val(data.loc_Credit_Merch_Visa);
										$('#loc_Credit_Merch_Master').val(data.loc_Credit_Merch_Master);
										$('#loc_Credit_Merch_Union').val(data.loc_Credit_Merch_Union);
										$('#for_Credit_Merch_Visa').val(data.for_Credit_Merch_Visa);
										$('#for_Credit_Merch_Master').val(data.for_Credit_Merch_Master);
										$('#for_Credit_Merch_Master').val(data.for_Credit_Merch_Master);
										$('#for_Credit_Merch_Union').val(data.for_Credit_Merch_Union);

										$('#loc_Debit_Host_Visa').val(data.loc_Debit_Host_Visa);
										$('#loc_Debit_Host_Master').val(data.loc_Debit_Host_Master);
										$('#loc_Debit_Host_Union').val(data.loc_Debit_Host_Union);
										$('#for_Debit_Host_Visa').val(data.for_Debit_Host_Visa);
										$('#for_Debit_Host_Master').val(data.for_Debit_Host_Master);
										$('#for_Debit_Host_Master').val(data.for_Debit_Host_Master);
										$('#for_Debit_Host_Union').val(data.for_Debit_Host_Union);

										$('#loc_Credit_Host_Visa').val(data.loc_Credit_Host_Visa);
										$('#loc_Credit_Host_Master').val(data.loc_Credit_Host_Master);
										$('#loc_Credit_Host_Union').val(data.loc_Credit_Host_Union);
										$('#for_Credit_Host_Visa').val(data.for_Credit_Host_Visa);
										$('#for_Credit_Host_Master').val(data.for_Credit_Host_Master);
										$('#for_Credit_Host_Master').val(data.for_Credit_Host_Master);
										$('#for_Credit_Host_Union').val(data.for_Credit_Host_Union);

									}

								},
							});
						});


						function getPendingQuotationMDRRates() {
							const config = {
								method: "POST",
								url: contextPath + "/quotation/" + quotationId + "/mdr/pending",
								headers: {
									'X-CSRF-TOKEN': token,
									'Content-Type': 'application/json'
								}
							}

							axios(config)
								.then((response) => {
									return response.data
								})
								.then((response) => {
									console.log(response)
								})
								.finally(() => {

								})
						}
						function getPendingQuotationMDRRatesEzySplit() {
							const config = {
								method: "POST",
								url: contextPath + "/quotation/" + quotationId + "/mdr/ezysplit/pending",
								headers: {
									'X-CSRF-TOKEN': token,
									'Content-Type': 'application/json'
								}
							}

							axios(config)
								.then((response) => {
									return response.data
								})
								.then((response) => {
									console.log(response)
								})
								.finally(() => {

								})
						}


						$('.saveMDR').click(function (e) {
							e.preventDefault();

							$.post("updateQuotationMDR",
								{
									hostRateId: $('#hostRateId').val(),
									quotationMdrRateId: $('#quotationMdrRateId').val(),
									productId: $('#productId').val(),
									quotationId: $('#quotationId').val(),
									orderLineId: $('#orderLineId').val(),

									productName: $('#productName').val(),
									subscription: $('#subscription').val(),
									amount: $('#amount').val(),
									showInQuote: $('#showInQuote').val(),
									productType: $('#productType').val(),
									includeWallet: $('#includeWallet').val(),
									payLater: $('#payLater').val(),
									hostType: $('#hostType').val(),
									createdOn: $('#createdOn').val(),


									loc_Debit_Merch_Visa: $('#loc_Debit_Merch_Visa').val(),
									loc_Debit_Merch_Master: $('#loc_Debit_Merch_Master').val(),
									loc_Debit_Merch_Union: $('#loc_Debit_Merch_Union').val(),
									for_Debit_Merch_Visa: $('#for_Debit_Merch_Visa').val(),
									for_Debit_Merch_Master: $('#for_Debit_Merch_Master').val(),
									for_Debit_Merch_Union: $('#for_Debit_Merch_Union').val(),

									loc_Credit_Merch_Visa: $('#loc_Credit_Merch_Visa').val(),
									loc_Credit_Merch_Master: $('#loc_Credit_Merch_Master').val(),
									loc_Credit_Merch_Union: $('#loc_Credit_Merch_Union').val(),
									for_Credit_Merch_Visa: $('#for_Credit_Merch_Visa').val(),
									for_Credit_Merch_Master: $('#for_Credit_Merch_Master').val(),
									for_Credit_Merch_Union: $('#for_Credit_Merch_Union').val(),

									loc_Debit_Host_Visa: $('#loc_Debit_Host_Visa').val(),
									loc_Debit_Host_Master: $('#loc_Debit_Host_Master').val(),
									loc_Debit_Host_Union: $('#loc_Debit_Host_Union').val(),
									for_Debit_Host_Visa: $('#for_Debit_Host_Visa').val(),
									for_Debit_Host_Master: $('#for_Debit_Host_Master').val(),
									for_Debit_Host_Union: $('#for_Debit_Host_Union').val(),

									loc_Credit_Host_Visa: $('#loc_Credit_Host_Visa').val(),
									loc_Credit_Host_Master: $('#loc_Credit_Host_Master').val(),
									loc_Credit_Host_Union: $('#loc_Credit_Host_Union').val(),
									for_Credit_Host_Visa: $('#for_Credit_Host_Visa').val(),
									for_Credit_Host_Master: $('#for_Credit_Host_Master').val(),
									for_Credit_Host_Union: $('#for_Credit_Host_Union').val(),

									boostMDREcomm: $('#boostMDREcomm').val(),
									grabMDREcomm: $('#grabMDREcomm').val(),
									boostMDRQR: $('#boostMDRQR').val(),
									grabMDRQR: $('#grabMDRQR').val(),
									fPXMDR_RM: $('#fPXMDR_RM').val(),
									fPXMDR_Percent: $('#fPXMDR_Percent').val(),
									productSettlement: $('#productSettlement').val(),
									boostSettlement: $('#boostSettlement').val(),
									grabSettlement: $('#grabSettlement').val(),
									fpxSettlement: $('#fpxSettlement').val(),
									
									    tngMDREcomm : $('#tngMDREcomm').val(),
										tngMDRQR :$('#tngMDRQR').val(),    
										shopeepayMDREcomm :$('#shopeepayMDREcomm').val(),
										shopeepayMDRQR : $('#shopeepayMDRQR').val(),
										tngSettlement : $('#tngSettlement').val(),
										shopeepaySettlement : $('#tngSettlement').val()

								},

								function (data, status) {

									$('#editModal').modal(
										{
											backdrop: 'static',
											keyboard: false
										});
									$('#editModal').modal('hide');

								});
						})


						$('.saveEzysplitMDR').click(function (e) {
							e.preventDefault();

							$.post("updateQuotationMDR",
								{
									hostRateId: $('#hostRateId_EzysplitModal').val(),
									quotationEzysplitMdrRateId: $('#quotationEzysplitMdrRateId_EzysplitModal').val(),
									productId: $('#productId_EzysplitModal').val(),
									quotationId: $('#quotationId_EzysplitModal').val(),
									orderLineId: $('#orderLineId_EzysplitModal').val(),

									productName: $('#productName_EzysplitModal').val(),
									subscription: $('#subscription_EzysplitModal').val(),
									amount: $('#amount_EzysplitModal').val(),
									showInQuote: $('#showInQuote_EzysplitModal').val(),
									productType: $('#productType_EzysplitModal').val(),
									includeWallet: $('#includeWallet_EzysplitModal').val(),
									payLater: $('#payLater_EzysplitModal').val(),
									hostType: $('#hostType_EzysplitModal').val(),
									createdOn: $('#createdOn_EzysplitModal').val(),


									loc_Credit_Merch_Master_Insta3: $('#loc_Credit_Merch_Master_Insta3').val(),
									loc_Credit_Cus_Master_Insta3: $('#loc_Credit_Cus_Master_Insta3').val(),
									loc_Credit_Host_Master_Insta3: $('#loc_Credit_Host_Master_Insta3').val(),

									loc_Credit_Merch_Master_Insta6: $('#loc_Credit_Merch_Master_Insta6').val(),
									loc_Credit_Cus_Master_Insta6: $('#loc_Credit_Cus_Master_Insta6').val(),
									loc_Credit_Host_Master_Insta6: $('#loc_Credit_Host_Master_Insta6').val(),

									loc_Credit_Merch_Master_Insta9: $('#loc_Credit_Merch_Master_Insta9').val(),
									loc_Credit_Cus_Master_Insta9: $('#loc_Credit_Cus_Master_Insta9').val(),
									loc_Credit_Host_Master_Insta9: $('#loc_Credit_Host_Master_Insta9').val(),

									loc_Credit_Merch_Master_Insta12: $('#loc_Credit_Merch_Master_Insta12').val(),
									loc_Credit_Cus_Master_Insta12: $('#loc_Credit_Cus_Master_Insta12').val(),
									loc_Credit_Host_Master_Insta12: $('#loc_Credit_Host_Master_Insta12').val(),

									boostMDREcomm: $('#boostMDREcomm_EzysplitModal').val(),
									grabMDREcomm: $('#grabMDREcomm_EzysplitModal').val(),
									boostMDRQR: $('#boostMDRQR_EzysplitModal').val(),
									grabMDRQR: $('#grabMDRQR_EzysplitModal').val(),
									fPXMDR_RM: $('#fPXMDR_RM_EzysplitModal').val(),
									fPXMDR_Percent: $('#fPXMDR_Percent_EzysplitModal').val(),
									productSettlement: $('#productSettlement_EzysplitModal').val(),
									boostSettlement: $('#boostSettlement_EzysplitModal').val(),
									grabSettlement: $('#grabSettlement_EzysplitModal').val(),
									fpxSettlement: $('#fpxSettlement_EzysplitModal').val(),
									
									
									tngMDREcomm : $('#tngMDREcomm_EzysplitModal').val(),
									tngMDRQR :$('#tngMDRQR_EzysplitModal').val(),    
									shopeepayMDREcomm :$('#shopeepayMDREcomm_EzysplitModal').val(),
									shopeepayMDRQR : $('#shopeepayMDRQR_EzysplitModal').val(),
									tngSettlement : $('#tngSettlement_EzysplitModal').val(),
									shopeepaySettlement : $('#shopeepaySettlement_EzysplitModal').val()
									
									
									

								},

								function (data,
									status) {

									$('#editModalEZYSPLIT').modal(
										{
											backdrop: 'static',
											keyboard: false
										});
									$('#editModalEZYSPLIT').modal('hide');

								});
						});

						$('.paymentVerified').click(function (e) {
							e.preventDefault();

							startLoadingScreen("Loading, please wait...")

							$.post("paymentVerified",
								{
									paymentId: $('#paymentId').val(),
									invoiceNo: $('#invoiceNo').val(),
									acquirer: $('#acquirer').val(),
									quotationId: $('#quotationId').val(),
									modeOfPayment: $('#modeOfPayment').val()
								},

								function (data, status) {

									$('#paymentVerifiedModal').modal(
										{
											backdrop: 'static',
											keyboard: false
										});
									$('#paymentVerifiedModal').modal('hide');

									var redirectURL = '<%=request.getContextPath()%>/MyAction/list';
									window.location.replace(redirectURL);
									stopLoadingScreen()
								});
						});

						$('#btnMoveToFinance').click(function (e) {
							e.preventDefault();
							const acquirer = document.getElementById("acquirer").value

							if (acquirer !== '0') {
								startLoadingScreen("Loading, please wait...")

								const config = {
									method: "POST",
									url: contextPath + "/quotation/stage/update/finance",
									headers: {
										'X-CSRF-TOKEN': token,
										'Content-Type': 'application/json'
									},
									params: {
										acquirer: $('#acquirer').val(),
										quotationId: $('#quotationId').val(),
									}
								}

								axios(config)
									.then((response) => {
										return response.data
									})
									.then((response) => {
										// var redirectURL = '<%=request.getContextPath()%>/MyAction/list';
										// window.location.replace(redirectURL);

										alert("Quotation Moved to Finance");
										window.location.reload();
									})
									.finally(() => {
										stopLoadingScreen()
									})
							} else {
								alert("Acquirer not selected")
							}


						});


						function moveToProcessingStage() {
							const config = {
								method: "POST",
								url: contextPath + "/quotation/stage/update/processing",
								headers: {
									'X-CSRF-TOKEN': token,
									'Content-Type': 'application/json'
								},
								params: {
									acquirer: $('#acquirer').val(),
									quotationId: $('#quotationId').val(),
								}
							}

							axios(config)
								.then((response) => {
									return response.data
								})
								.then((response) => {
									var redirectURL = '<%=request.getContextPath()%>/MyAction/list';
									window.location.replace(redirectURL);
								})
								.finally(() => {
								})
						}


						function makeNewISwitch2Mobi() {
							event.preventDefault()

							const currentDate = moment().format('YYYY-MM-DD')

							var requestData = {
								"collectedOn": currentDate,
								"quotationId": quotationId,
								"documentData": null,
								"documentName": null,
								"documentType": null,
								"paymentType": 2
							}

							const config = {
								method: "post",
								url: contextPath + "/quotation/payment/collect",
								headers: {
									'X-CSRF-TOKEN': token,
									'Content-Type': 'application/json'
								},
								data: JSON.stringify(requestData)
							}


							axios(config)
								.then(response => {
									return response.data
								})
								.then(response => {
									console.log(response.responseMessage)
									// alert(response.responseMessage)
								})
								.catch(error => {
									console.log(error)

								})
								.finally(() => {
								})
						}

						async function moveToFinance() {
							const paymentCollectedDateString = document.getElementById("payment-collect-date").value
							const uploadedDocument = document.getElementById('payment-collect-file')


							if (paymentCollectedDateString !== null && paymentCollectedDateString !== "") {
								document.getElementById("payment-collect-date-error").style.display = "none"


								if (uploadedDocument.files[0] != null) {

									startLoadingScreen("Processing, please wait...");
									var documentsFiles = uploadedDocument.files[0]
									// var documentBase64 = getBase64(documentsFiles.files[0])
									// 	.then(response => { return response.split(",")[1] })
									// console.log(documentBase64)

									var documentBase64 = await toBase64(documentsFiles)

									var requestData = {
										"collectedOn": paymentCollectedDateString,
										"quotationId": quotationId,
										"documentData": documentBase64,
										"documentName": documentsFiles.name,
										"documentType": documentsFiles.type

									}



									const config = {
										method: "post",
										url: contextPath + "/quotation/payment/collect",
										headers: {
											'X-CSRF-TOKEN': token,
											'Content-Type': 'application/json'
										},
										data: JSON.stringify(requestData)
									}


									axios(config)
										.then(response => {
											return response.data
										})
										.then(response => {
											alert(response.responseMessage)

										})
										.catch(error => {
											console.log(error)

										})
										.finally(() => {
											stopLoadingScreen();
											paymentCollectedModal.hide()
											window.location.reload;
										})

								} else {
									document.getElementById("payment-collect-file-error").innerText = "Please upload payment receipt"
									document.getElementById("payment-collect-file-error").style.display = "block"
								}
							} else {
								document.getElementById("payment-collect-date-error").innerText = "Payment Collected Date Required!"
								document.getElementById("payment-collect-date-error").style.display = "block"
							}
						}

						const toBase64 = file => new Promise((resolve, reject) => {
							const reader = new FileReader();
							reader.readAsDataURL(file);
							reader.onload = () => resolve(reader.result);
							reader.onerror = error => reject(error);
						});


						function viewReceipt() {
							const config = {
								method: "post",
								url: contextPath + "/quotation/payment/" + quotationId + "/receipt",
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
										const url = contextPath + response.responseData.receiptPath
										window.open(url)
									} else {
										alert(response.responseMessage)
									}
								})
								.catch(error => {
									console.log(error)
								})
						}


						function decrementQuantity(id) {
							var inputField = document.getElementById("item-quantity-" + id)
							let currentValue = parseInt(inputField.value)
							if (currentValue > 1) {
								inputField.value = parseInt((currentValue - 1))
							}
							console.log("decrementQuantity --> " + inputField.value)
						}
						function incrementQuantity(id) {
							var inputField = document.getElementById("item-quantity-" + id)
							let currentValue = parseInt(inputField.value)
							if (currentValue > 0) {
								inputField.value = parseInt((currentValue + 1))
							}
							console.log("incrementQuantity --> " + inputField.value)
						}

						function getFixedFloat(value, count) {
							return parseFloat(value).toFixed(count)
						}

						function rollbackStage() {
							const rollbackStage = document.getElementById("rollback-stage").value
							const rollbackReason = document.getElementById("rollback-reason").value

							
							if (rollbackStage == -1) {
								alert("Select Rollback Stage")
							} else {
								startStageRollback(rollbackStage, rollbackReason)
							}
						}

						function startStageRollback(rollbackStage, rollbackReason) {
							const config = {
								method: "POST",
								url: contextPath + "/quotation/" + quotationId + "/rollback",
								headers: {
									'X-CSRF-TOKEN': token,
									'Content-Type': 'application/json'
								},
								data: JSON.stringify({
									"rollbackStage": rollbackStage,
									"rollbackReason": rollbackReason
								})
							}

							axios(config)
								.then(response => {
									return response.data
								})
								.then(response => {

									if (response.responseStatus === "0000")
										alert("Quotation Rollback Success!")
									else
										alert("Something went wrong, please try again!")

									window.location.reload()
								})
								.catch(error => {
									console.log(error)
								})
						}

						function applyDiscount(discount, discountReason) {

							startLoadingScreen("Processing, please wait...")
							const config = {
								method: "POST",
								url: contextPath + "/quotation/" + quotationId + "/discount",
								headers: {
									'X-CSRF-TOKEN': token,
									'Content-Type': 'application/json'
								},
								data: JSON.stringify({
									"discount": discount,
									"discountReason": discountReason
								})
							}

							axios(config)
								.then(response => {
									return response.data
								})
								.then(response => {

									if (response.responseStatus === "0000")
										alert("Discount Applied")
									else
										alert("Unable to apply discount!")

									window.location.reload()
								})
								.catch(error => {
									console.log(error)
								})
								.finally(() => {
									stopLoadingScreen()
								})
						}
					</script>

					</html>
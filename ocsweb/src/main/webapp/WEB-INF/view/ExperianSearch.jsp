<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="ISO-8859-1" />

        <meta id="_csrf" name="_csrf" content="${_csrf.token}" />
        <meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}" />

        <title>Experian Search</title>
        <link rel="icon" type="image/x-icon"
            href="${pageContext.request.contextPath}/resources/images/favicon/favicon.ico" />

        <script src="${pageContext.request.contextPath}/resources/js/signaturePad.js" type="text/javascript"></script>

        <link rel="manifest" href="/manifest.json" />
        <meta name="msapplication-TileColor" content="#ffffff" />
        <meta name="msapplication-TileImage" content="../resources/images/favicon/ms-icon-144x144.png" />
        <meta name="theme-color" content="#f1f5fa" />

        <!-- CDN moved to local -->
        <link rel="stylesheet" type="text/css"
            href="${pageContext.request.contextPath}/resources/js/fontawesome/css/fontawesome.min.css" />
        <script src="${pageContext.request.contextPath}/resources/js/axios.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/moment.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/loading.js" type="text/javascript"></script>
        <!-- CDN moved to local -->

        <link rel="stylesheet" type="text/css"
            href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css"
            href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap5.min.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/fonts/fonts.css" />

        <meta id="_csrf" name="_csrf" content="${_csrf.token}" />
        <meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}" />

        <link rel="stylesheet" type="text/css"
            href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css"
            href="${pageContext.request.contextPath}/resources/css/dataTables.bootstrap5.min.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/fonts/fonts.css" />
        <link rel="stylesheet" type="text/css"
            href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css" />

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
        <main class="container mt110" style="margin-top: 6rem;">
            <div class="card" id="search-details">
                <div class="card-body">
                    <form class="needs-validation" novalidate>
                        <h4>Search Entity Details</h4>
                        <hr />
                        <div class="row mt-4">
                            <div class="col-sm-12 col-md-12">
                                <label for="entityName" class="form-label">Entity Name</label>
                                <input type="text" class="form-control" id="entityName" value=""
                                    placeholder="Entity Name" required aria-describedby="inputGroupPrepend" />
                                <div class="invalid-feedback">
                                    Please provide an entity name
                                </div>
                            </div>
                        </div>
                        <div class="row mt-3">
                            <div class="col-sm-12 col-md-6">
                                <label for="entityId" class="form-label">Entity ID</label>
                                <input type="text" class="form-control" id="entityId" value=""
                                    placeholder="Place provide entity id" required
                                    aria-describedby="inputGroupPrepend" />
                                <div id="passwordHelpBlock" class="form-text">
                                    Required Details: New IC No / Passport / Business No / New Business No / Company ID
                                    / New Company ID /
                                    Registration ID / New Registration ID
                                </div>
                                <div class="invalid-feedback">
                                    Please provide an entity id 1.
                                </div>
                            </div>
                            <div class="col-sm-12 col-md-6">
                                <label for="entityId2" class="form-label">Entity ID 2</label>
                                <input type="text" class="form-control" id="entityId2" value=""
                                    placeholder="Place provide entity id 2" required
                                    aria-describedby="inputGroupPrepend" />
                                <div id="passwordHelpBlock" class="form-text">
                                    Required Details: Old IC No/ Police ID / Business No / New Business No / Company ID
                                    / New Company ID /
                                    Registration ID / New Registration ID
                                </div>
                                <div class="invalid-feedback">
                                    Please provide an entity id 2.
                                </div>
                            </div>
                        </div>

                        <div class="mt-3">
                            <h6>Note: Any one of the below details is required</h6>
                            <div class="row">
                                <div class="col-sm-6 col-md-4">
                                    <label for="emailId" class="form-label">Email ID</label>
                                    <input type="email" class="form-control" id="emailId" value=""
                                        placeholder="sample@domain.com" />
                                </div>
                                <div class="col-sm-6 col-md-4">
                                    <label for="mobileNumber" class="form-label">Mobile Number</label>
                                    <input type="tel" class="form-control" id="mobileNumber" value=""
                                        placeholder="01xxxxxxx" />
                                </div>
                                <div class="col-sm-6 col-md-4">
                                    <label for="lastKnownAddress" class="form-label">Last Known Address</label>
                                    <input type="text" class="form-control" id="lastKnownAddress" value="Malaysia"
                                        placeholder="Address" required />
                                </div>
                            </div>
                        </div>

                        <div class="mt-3">
                            <h6>Note: The below details is Optional</h6>
                            <div class="row">
                                <div class="col-sm-6 col-md-6">
                                    <label for="reference1" class="form-label">Reference 1</label>
                                    <input type="text" class="form-control" id="reference1" value=""
                                        placeholder="Any Reference Details" />
                                </div>
                                <div class="col-sm-6 col-md-6">
                                    <label for="reference2" class="form-label">Reference 2</label>
                                    <input type="text" class="form-control" id="reference2" value=""
                                        placeholder="Any Reference Details" />
                                </div>
                                <div class="col-sm-6 col-md-6">
                                    <label for="reference3" class="form-label">Reference 3</label>
                                    <input type="text" class="form-control" id="reference3" value=""
                                        placeholder="Any Reference Details" />
                                </div>
                                <div class="col-sm-6 col-md-6">
                                    <label for="reference4" class="form-label">Reference 4</label>
                                    <input type="text" class="form-control" id="reference4" value=""
                                        placeholder="Any Reference Details" />
                                </div>
                            </div>
                        </div>

                        <div class="mt-3" style="text-align: end;">
                            <button class="btn btn-outline-primary" type="submit">
                                Search Details
                            </button>
                            <button class="btn btn-outline-secondary" type="button" onclick="clearAllInputFields()">
                                Clear
                            </button>
                        </div>
                    </form>
                </div>
            </div>

            <div id="search-result" class="card" style="display: none;">
                <div class="card-body">
                    <h4>Search Results</h4>
                    <hr />
                    <div class="mb-3">
                        <label for="personalDetails_name" class="form-label">Name of Subject</label>
                        <input disabled type="text" class="form-control" id="personalDetails_name" value=""
                            placeholder="" />
                    </div>

                    <div class="row">
                        <div class="col-sm-12 col-md-6">
                            <div class="mb-3">
                                <label for="old_ic" class="form-label">IC No</label>
                                <input readonly type="text" class="form-control" id="old_ic" placeholder="" />
                            </div>
                        </div>
                        <div class="col-sm-12 col-md-6">
                            <div class="mb-3">
                                <label for="new_ic" class="form-label">New IC No</label>
                                <input readonly type="text" class="form-control" id="new_ic" placeholder="" />
                            </div>
                        </div>
                    </div>

                    <h4>Address Details</h4>
                    <table class="table table-light table-striped">
                        <thead>
                            <th>S.No</th>
                            <th>Address</th>
                            <th>Date</th>
                        </thead>
                        <tbody id="address-list"></tbody>
                    </table>
                </div>
            </div>
        </main>
    </body>

    <script>
        (function () {
            "use strict";

            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.querySelectorAll(".needs-validation");

            // Loop over them and prevent submission
            Array.prototype.slice.call(forms).forEach(function (form) {
                form.addEventListener(
                    "submit",
                    function (event) {
                        let flag = true;
                        if (!form.checkValidity()) {
                            event.preventDefault();
                            event.stopPropagation();
                            flag = false;
                        }

                        if (flag) {
                            searchDetails(event);
                        }

                        form.classList.add("was-validated");
                    },
                    false
                );
            });
        })();

        function clearAllInputFields() {
            var inputs = document.querySelectorAll("input");
            Array.prototype.slice.call(inputs).forEach(function (input) {
                input.value = "";
            });
        }

        function searchDetails(event) {
            event.preventDefault();

            startLoadingScreen("Loading, please wait...");

            const entityName = document.getElementById("entityName").value.trim();
            const entityId = document.getElementById("entityId").value.trim();
            const entityId2 = document.getElementById("entityId2").value.trim();
            const emailId = document.getElementById("emailId").value.trim();
            const mobileNumber = document.getElementById("mobileNumber").value.trim();
            const lastKnownAddress = document
                .getElementById("lastKnownAddress")
                .value.trim();
            const reference1 = document.getElementById("reference1").value.trim();
            const reference2 = document.getElementById("reference2").value.trim();
            const reference3 = document.getElementById("reference3").value.trim();
            const reference4 = document.getElementById("reference4").value.trim();

            var data = JSON.stringify({
                entityName: entityName,
                entityId: entityId,
                entityId2: entityId2,
                mobileNo: mobileNumber,
                emailAddress: emailId,
                lastKnownAddress: lastKnownAddress,
                ref1: reference1,
                ref2: reference2,
                ref3: reference3,
                ref4: reference4,
            });

            var config = {
                method: "post",
                url: "http://149.129.139.7:9095/reports/api/experian/search/CRI",
                headers: {
                    "Content-Type": "application/json",
                },
                data: data,
            };

            axios(config)
                .then(function (response) {
                    return response.data;
                })
                .then((response) => {
                    if (response.responseCode === "0000") {
                        document.getElementById("search-details").style.display = "none";
                        document.getElementById("search-result").style.display = "block";

                        const personalDetails = response.responseData.person_details;

                        document.getElementById("personalDetails_name").value =
                            personalDetails.name;
                        document.getElementById("old_ic").value = personalDetails.old_ic;
                        document.getElementById("new_ic").value = personalDetails.new_ic;

                        const addresses = response.responseData.person_details.addresses;

                        const tableBody = document.getElementById("address-list");

                        addresses.forEach((item) => {
                            const tr = document.createElement("tr");

                            const indexNo = document.createElement("td");
                            indexNo.innerHTML = addresses.indexOf(item) + 1;

                            const addressLine = document.createElement("td");
                            addressLine.innerHTML = item.address;

                            const datechanged = document.createElement("td");
                            datechanged.innerHTML = item.datechanged;

                            tr.appendChild(indexNo);
                            tr.appendChild(addressLine);
                            tr.appendChild(datechanged);

                            tableBody.appendChild(tr);
                        });
                    }
                })
                .catch(function (error) {
                    console.log(error);
                })
                .finally(() => {
                    stopLoadingScreen();
                });
        }
    </script>

    </html>
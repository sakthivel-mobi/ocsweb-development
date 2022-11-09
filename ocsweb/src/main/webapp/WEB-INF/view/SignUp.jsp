<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



        <!doctype html>
        <html lang="en">

        <head>

            <title>Register as a new user</title>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

            <link rel="icon" type="image/x-icon"
                href="${pageContext.request.contextPath}/resources/images/favicon/favicon.ico">

            <link rel="manifest" href="/manifest.json">
            <meta name="msapplication-TileColor" content="#ffffff">
            <meta name="msapplication-TileImage"
                content="${pageContext.request.contextPath}/resources/images/favicon/ms-icon-144x144.png">
            <meta name="theme-color" content="#f1f5fa">


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

            <!-- CDN moved to local -->
            <link rel="stylesheet" type="text/css"
                href="${pageContext.request.contextPath}/resources/js/fontawesome/css/fontawesome.min.css">
            <script src="${pageContext.request.contextPath}/resources/js/axios.js" type="text/javascript"></script>
            <script src="${pageContext.request.contextPath}/resources/js/moment.js" type="text/javascript"></script>
            <!-- CDN moved to local -->
            <%@ page isELIgnored="false" %>
        </head>

        <body class="login-background">
            <div class="login-content">
                <div class="card" style="">
                    <div class="card-body">
                        <h5 class="card-title" style="text-align: center;">Register as
                            a new user</h5>
                        <div>

                            <div id="input-mobile-number" class="mb-3">
                                <label for="login-mobile-number  mt-2">Mobile Number</label>
                                <div class="input-group">
                                    <span class="input-group-text" id="basic-addon1">+60</span><input
                                        id="login-mobile-number" class="form-control" value="${userId }"
                                        placeholder="Eg. 1xxxxxx" type="tel" aria-describedby="basic-addon1" readonly />
                                </div>
                                <div class="error-message" style="display: none;">Please
                                    provide a valid mobile number.</div>
                                <br>
                                <div class="helper-text" style="font-size: small;">
                                    <p>
                                        Above is the Mobile Number Registered in our Records.<br>
                                        Please Proceed if you'd like to continue with the above number.
                                    </p>
                                    <p>
                                        Note: <b>This mobile number is your Login ID</b>.
                                    </p>
                                </div>
                            </div>

                            <button class="btn btn-primary mt-2" id="button-send-otp" style="display: block;">Send
                                OTP</button>

                            <div class="mt-2" id="input-OTP" style="display: none;">
                                <label for="login-mobile-otp">OTP</label> <input id="login-mobile-otp"
                                    class="form-control" type="password" placeholder="Enter received OTP" />
                                <div class="error-message" style="display: none;">Please
                                    provide a valid OTP.</div>
                            </div>

                            <button class="btn btn-link mt-2" id="button-resend-otp" style="display: none;">Resend OTP
                                in 01 : 30</button>
                            <button class="btn btn-primary mt-2" id="button-verify-otp" style="display: none;">Verify
                                OTP</button>

                            <div class="mt-2" id="input-login-id" style="display: none;">
                                <label for="login-id">Login ID</label> <input id="login-id" class="form-control"
                                    type="text" placeholder="Enter your Login ID *" />
                                <div class="error-message" style="display: none;">Please
                                    provide a valid Login ID.</div>
                            </div>


                            <div class="mt-2" id="input-password" style="display: none;">
                                <label for="login-password">Password</label>

                                <div class="input-group">
                                    <input id="login-password" class="form-control" value=""
                                        placeholder="Enter your Password *" maxlength="16" type="password" />
                                    <span id="password-strong" style="display: none;" class="input-group-text"><img
                                            id="password-numerals" width="15"
                                            src="${pageContext.request.contextPath}/resources/images/ic_password_checked.svg"
                                            alt=""></span>
                                </div>

                                <div class="error-message" style="display: none;">Please
                                    provide a valid Password.</div>

                                <div id="password-helper-text" style="font-size: xx-small; display: none;">
                                    <p style="margin-top: 5px">Password must contain a
                                        combination of at least one of the following types of characters
                                        each:</p>
                                    <ul>
                                        <li class="m-1"><img id="password-min-length" width="15"
                                                src="${pageContext.request.contextPath}/resources/images/ic_password_unchecked.svg"
                                                alt=""> Password length minimum (8) and maximum (16)
                                            characters.</li>
                                        <li class="m-1"><img id="password-characters" width="15"
                                                src="${pageContext.request.contextPath}/resources/images/ic_password_unchecked.svg"
                                                alt=""> Upper case (A to Z) or Lower case characters (a
                                            to z)</li>
                                        <li class="m-1"><img id="password-numerals" width="15"
                                                src="${pageContext.request.contextPath}/resources/images/ic_password_unchecked.svg"
                                                alt=""> At least 1 Numerals (0 to 9)</li>
                                        <li class="m-1"><img id="password-special-characters" width="15"
                                                src="${pageContext.request.contextPath}/resources/images/ic_password_unchecked.svg"
                                                alt=""> At least 1 Special Characters (! @ # $ % ^ & *)</li>
                                    </ul>
                                </div>
                            </div>

                            <div class="mt-2" id="input-confirm-password" style="display: none;">
                                <label for="login-confirm-password">Confirm Password</label>

                                <div class="input-group">
                                    <input id="login-confirm-password" class="form-control"
                                        placeholder="Enter your Password *" value="" type="password" />
                                    <span id="password-match" style="display: none;" class="input-group-text"><img
                                            id="password-numerals" width="15"
                                            src="${pageContext.request.contextPath}/resources/images/ic_password_checked.svg"
                                            alt=""></span>
                                </div>


                                <div class="error-message" style="display: none;">Password
                                    and Confirm Password does not match.</div>
                            </div>



                            <button class="btn btn-primary mt-2" id="button-signup" style="display: none;">Sign
                                Up</button>
                            <br>
                            <div class="helper-text" style="font-size: small;">
                                <p>
                                    If you like to Modify the Above Phone Number<br> Request a
                                    Call Back from our Team.
                                </p>
                            </div>

                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                data-bs-target="#callbackModal">
                                Get a Call Back</button>


                            <div class="d-flex flex-row align-items-center">
                                <div style="flex: 1;">
                                    <hr>
                                </div>

                                <div>
                                    <p style="margin: 1rem !important">or</p>
                                </div>
                                <div style="flex: 1;">
                                    <hr />
                                </div>
                            </div>

                            <a class="btn btn-primary mt-2 w-100" href="${pageContext.request.contextPath}/login">I
                                already have account</a>

                        </div>
                    </div>
                </div>
            </div>

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

            <!-- Modal -->
            <div class="modal fade" id="callbackModal" tabindex="-1" aria-labelledby="exampleModalLabel"
                aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Want us to help
                                you with First Time Login? Request a Call back</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">

                            <div class="row g-3">
                                <div class="col-sm-6  form-floating mb-3">
                                    <div class="mb-3">

                                        <label class="form-label">Business Name</label> <input id="businessName"
                                            class="form-control" placeholder="Business Name" />

                                    </div>
                                </div>

                                <div class="col-sm-6  form-floating mb-3">
                                    <div class="mb-3">

                                        <label class="form-label">Email</label> <input id="email" class="form-control"
                                            placeholder="Email" />

                                    </div>
                                </div>
                            </div>
                            <div class="row g-3">

                                <div class="col-sm-6  form-floating mb-3">

                                    <div class="input-group mb-3">
                                        <label class="form-label">Phone</label>
                                        <div class="input-group">
                                            <span class="input-group-text" id="basic-addon1">+60</span><input
                                                id="mobileNumber" class="form-control" placeholder="Phone" />
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-6  form-floating mb-3">
                                    <div class="mb-3">

                                        <label class="form-label">POC</label> <input id="poc" class="form-control"
                                            placeholder="Point of Contact" />

                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button id="submitCallBackRequest" type="button" class="btn btn-primary">Submit</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </body>

        <script>
            const contextPath = "${pageContext.request.contextPath}"
            var token = $("meta[name='_csrf']").attr("content")
            const saltRounds = 10;


            var mobilePattern = /^[0-9]([0-9]{6,10}$)/;
            var passwordPattern = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,16}$/;
            var selectedLoginId = "";

            const callBackRequestBtn = document.getElementById("submitCallBackRequest")
            const inputMobileNumber = document.getElementById("input-mobile-number")
            const inputOTP = document.getElementById("input-OTP")
            const inputLoginId = document.getElementById("input-login-id")
            const inputPassword = document.getElementById("input-password")
            const inputConfirmPassword = document.getElementById("input-confirm-password")

            const passwordHelperNote = document.getElementById("password-helper-text")

            const checkPasswordMinLength = document.getElementById("password-min-length")
            const checkPasswordCharacters = document.getElementById("password-characters")
            const checkPasswordNumerals = document.getElementById("password-numerals")
            const checkPasswordSpecialCharacters = document.getElementById("password-special-characters")

            const loginMobileNumber = document.getElementById("login-mobile-number")
            const loginMobileOTP = document.getElementById("login-mobile-otp")
            const loginLoginId = document.getElementById("login-id")
            const loginPassword = document.getElementById("login-password")
            const loginConfirmPassword = document.getElementById("login-confirm-password")

            const buttonSendOTP = document.getElementById("button-send-otp")
            const buttonResendOTP = document.getElementById("button-resend-otp")
            const buttonVerifyOTP = document.getElementById("button-verify-otp")
            const buttonSignUp = document.getElementById("button-signup")


            window.onload = function () {
                console.log("token --> ", token)
            }

            loginConfirmPassword.addEventListener('input', (event) => {
                const password = document.getElementById("login-password").value.trim()
                const confirmPassword = event.target.value.trim()

                if (password === confirmPassword) {
                    document.getElementById("password-match").style.display = "block"
                } else {
                    document.getElementById("password-match").style.display = "none"
                }
            })

            callBackRequestBtn.onclick = function () {
                console.log("Call Back Request Button Clicked!")
                saveCallBackRequest()
            }

            function saveCallBackRequest() {
                // startLoadingScreen("Loading, please wait...")
                const requestData = JSON.stringify({

                    businessName: $('#businessName').val(),
                    email: $('#email').val(),
                    mobileNumber: $('#mobileNumber').val(),
                    poc: $('#poc').val()
                })

                const config = {
                    method: "post",
                    url: contextPath + "/CallBack/saveCallBackRequest",
                    headers: {
                        'X-CSRF-TOKEN': token,
                        'Content-Type': 'application/json'
                    },
                    data: requestData
                }

                this.axios(config)
                    .then(response => { return response.data })
                    .then(response => {
                        if (response.responseStatus === "0000") {
                            alert(response.responseMessage)
                            window.location.replace("${pageContext.request.contextPath}/login");
                        } else {
                            alert(response.responseMessage)
                        }
                    })
                    .catch(error => {
                        console.log("error --> ", error)
                    })
                    .finally(() => {
                        //   stopLoadingScreen()
                    })
            }


            buttonSendOTP.onclick = function () {

                console.log("loginMobileNumber --> ", mobilePattern.test(loginMobileNumber.value))

                if (mobilePattern.test(loginMobileNumber.value) !== true) {
                    inputMobileNumber.querySelector(".error-message").style.display = "block"
                } else {
                    inputMobileNumber.querySelector(".error-message").style.display = "none"
                    var fiveMinutes = 90
                    buttonResendOTP.disabled = true
                    startTimer(fiveMinutes, buttonResendOTP);

                    requestNewOTP(loginMobileNumber.value);
                }
            }

            buttonResendOTP.onclick = function () {
                inputMobileNumber.querySelector(".error-message").style.display = "none"
                var fiveMinutes = 90
                buttonResendOTP.disabled = true
                startTimer(fiveMinutes, buttonResendOTP);

                requestNewOTP(loginMobileNumber.value);
            }

            buttonVerifyOTP.onclick = function () {
                const mobile = selectedLoginId = loginMobileNumber.value;
                const otp = document.getElementById("login-mobile-otp").value

                if (otp === "" || otp === null) {
                    inputOTP.querySelector(".error-message").style.display = "block"
                } else if (otp.length !== 6) {
                    inputOTP.querySelector(".error-message").innerText = "Invalid OTP"
                    inputOTP.querySelector(".error-message").style.display = "block"
                } else {
                    const requestData = {
                        mobile: mobile,
                        otp: otp
                    }

                    const config = {
                        method: "post",
                        url: contextPath + "/sms/validate/otp",
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
                            console.log(response)
                            alert(response.responseMessage)
                            if (response.responseStatus === "0000") {
                                selectedLoginId = loginMobileNumber.value;

                                loginMobileNumber.style.display = "none"
                                inputOTP.querySelector(".error-message").style.display = "none"
                                inputOTP.style.display = "none"
                                buttonResendOTP.style.display = "none"
                                buttonVerifyOTP.style.display = "none"
                                inputMobileNumber.style.display = "none"

                                inputPassword.style.display = "block"
                                inputConfirmPassword.style.display = "block"
                                buttonSignUp.style.display = "block"
                                passwordHelperNote.style.display = "block"
                            } else {
                                passwordHelperNote.style.display = "none"
                                inputOTP.querySelector(".error-message").style.display = "block"
                            }
                        })
                        .catch(error => {
                            console.log(error)
                        })
                }
            }

            buttonSignUp.onclick = function () {
                event.preventDefault();

                var flag = true

                console.log("passwordPattern --> ", passwordPattern.test(loginPassword.value))

                if (!passwordPattern.test(loginPassword.value)) {
                    flag = false
                    inputPassword.querySelector(".error-message").style.display = "block"
                } else {
                    inputPassword.querySelector(".error-message").style.display = "none"
                }

                if (loginConfirmPassword.value.length === 0 || loginPassword.value !== loginConfirmPassword.value) {
                    flag = false
                    inputConfirmPassword.querySelector(".error-message").style.display = "block"
                } else {
                    inputConfirmPassword.querySelector(".error-message").style.display = "none"
                }

                console.log("Flag --> ", flag)

                if (flag) {
                    signUpUser();
                }
            }

            function signUpUser() {


                startLoadingScreen("Loading, please wait...")
                const requestData = JSON.stringify({
                    username: selectedLoginId.trim(),
                    password: loginPassword.value
                })

                const config = {
                    method: "post",
                    url: contextPath + "/signup",
                    headers: {
                        'X-CSRF-TOKEN': token,
                        'Content-Type': 'application/json'
                    },
                    data: requestData
                }

                this.axios(config)
                    .then(response => { return response.data })
                    .then(response => {
                        if (response.responseStatus === "0000") {
                            alert(response.responseMessage)
                            window.location.replace("${pageContext.request.contextPath}/login");
                        } else {
                            alert(response.responseMessage)
                        }
                    })
                    .catch(error => {
                        console.log("error --> ", error)
                    })
                    .finally(() => {
                        stopLoadingScreen()
                    })
            }


            function startTimer(duration, display) {
                var timer = duration, minutes, seconds;
                const countdownTimer = setInterval(function () {
                    minutes = parseInt(timer / 60, 10);
                    seconds = parseInt(timer % 60, 10);

                    minutes = minutes < 10 ? "0" + minutes : minutes;
                    seconds = seconds < 10 ? "0" + seconds : seconds;

                    display.textContent = "Resend OTP in " + minutes + " : " + seconds
                    timer--;
                    if (timer < 0) {
                        clearInterval(countdownTimer)
                        display.textContent = "Resend OTP"
                        display.disabled = false
                    }
                }, 1000);
            }


            function requestNewOTP(mobileNumber) {

                startLoadingScreen("Loading, please wait...")
                const config = {
                    method: "post",
                    url: contextPath + "/sms/send/otp",
                    headers: {
                        'X-CSRF-TOKEN': token,
                        'Content-Type': 'application/json'
                    },
                    data: JSON.stringify({ mobile: mobileNumber, type: 1 })
                }

                axios(config)
                    .then(response => {
                        return response.data
                    })
                    .then(response => {
                        /* alert(response.responseMessage) */

                        if (response.responseStatus === "0000") {

                            alert(response.responseMessage)

                            inputMobileNumber.querySelector(".error-message").style.display = "none"
                            buttonSendOTP.style.display = "none"
                            buttonResendOTP.style.display = "block"
                            inputOTP.style.display = "block"
                            buttonVerifyOTP.style.display = "block"

                            var fiveMinutes = 90
                            buttonResendOTP.disabled = true
                            startTimer(fiveMinutes, buttonResendOTP);
                        }
                    })
                    .catch(error => {
                        console.log(error)
                    })
                    .finally(() => {
                        stopLoadingScreen()
                    })
            }

            var passwordMinLength = /^.{8,16}$/
            var passwordSpecialCharacters = /[!@#$%^&*]+/
            var passwordNumerals = /[0-9]+/
            var passwordCharacters = /[a-zA-Z]+/

            loginPassword.addEventListener('keyup', () => {
                const password = event.target.value

                if (passwordMinLength.test(password)) {
                    checkPasswordMinLength.src = "${pageContext.request.contextPath}/resources/images/ic_password_checked.svg"
                } else {
                    checkPasswordMinLength.src = "${pageContext.request.contextPath}/resources/images/ic_password_unchecked.svg"
                }

                if (passwordSpecialCharacters.test(password)) {
                    checkPasswordSpecialCharacters.src = "${pageContext.request.contextPath}/resources/images/ic_password_checked.svg"
                }
                else {
                    checkPasswordSpecialCharacters.src = "${pageContext.request.contextPath}/resources/images/ic_password_unchecked.svg"
                }

                if (passwordNumerals.test(password)) {
                    checkPasswordNumerals.src = "${pageContext.request.contextPath}/resources/images/ic_password_checked.svg"
                }
                else {
                    checkPasswordNumerals.src = "${pageContext.request.contextPath}/resources/images/ic_password_unchecked.svg"
                }

                if (passwordCharacters.test(password)) {
                    checkPasswordCharacters.src = "${pageContext.request.contextPath}/resources/images/ic_password_checked.svg"
                }
                else {
                    checkPasswordCharacters.src = "${pageContext.request.contextPath}/resources/images/ic_password_unchecked.svg"
                }

                if (passwordMinLength.test(password) && passwordCharacters.test(password) && passwordNumerals.test(password) && passwordSpecialCharacters.test(password)) {
                    document.getElementById("password-strong").style.display = "block"
                    document.getElementById("password-helper-text").style.display = "none"
                } else {
                    document.getElementById("password-strong").style.display = "none"
                    document.getElementById("password-helper-text").style.display = "block"
                }
            })


            var progressDialog = null;
            var body = document.getElementsByTagName("BODY")[0];

            function startLoadingScreen(message) {

                const loadingDialog = document.createElement("div");
                loadingDialog.id = "loading-dialog";
                loadingDialog.style.textAlign = "-webkit-center";
                loadingDialog.style.zIndex = "9999";
                loadingDialog.style.top = "0";
                loadingDialog.style.left = "0";
                loadingDialog.style.width = "100vw";
                loadingDialog.style.height = "100vh";
                loadingDialog.style.position = "fixed";
                loadingDialog.style.padding = "1rem";
                loadingDialog.style.background = "#1c1c1c75";
                loadingDialog.style.display = "block";
                loadingDialog.style.transition = "0.25s all ease-in-out";
                loadingDialog.style.webkitTransition = "0.25s all ease-in-out";

                const dialogBody = document.createElement("div");
                dialogBody.className = "d-flex align-items-center";
                dialogBody.style.width = "100%";
                dialogBody.style.height = "100%";

                const flexDiv = document.createElement("div");
                flexDiv.className = "flex-grow-1";

                const card = document.createElement("div");
                card.className = "card";
                card.style.alignItems = "center";
                card.style.backgroundColor = "#005baa"
                card.style.color = "#fff";
                card.style.width = "fit-content";
                card.style.padding = "2rem";

                const dialogMessage = document.createElement("p");
                dialogMessage.id = "loading-message";
                dialogMessage.innerHTML = message;

                const role = document.createAttribute("role");
                role.value = "status";

                const loadingImage = document.createElement("img");
                loadingImage.width = "100"
                loadingImage.src = contextPath + "/resources/images/loader.gif"

                const span = document.createElement("span");
                span.className = "visually-hidden";
                span.innerHTML = "Loading...";

                loadingImage.appendChild(span);

                card.appendChild(loadingImage);
                card.appendChild(dialogMessage);

                flexDiv.appendChild(card);
                dialogBody.appendChild(flexDiv);
                loadingDialog.appendChild(dialogBody);

                body.appendChild(loadingDialog);
                body.style.overflowY = "hidden"

                progressDialog = document.getElementById("loading-dialog");
            }

            function stopLoadingScreen() {
                if (progressDialog !== null) {
                    setTimeout(() => {
                        progressDialog.style.display = "none";
                        body.removeChild(progressDialog)
                        body.style.overflowY = "auto"
                    }, 100);
                }
            }



        </script>

        <style>
            /* Extra small devices (phones, 600px and down) */
            @media only screen and (max-width: 600px) {
                body {
                    background: url("${pageContext.request.contextPath}/resources/images/login_bg.jpg");
                    background-repeat: no-repeat;
                    background-size: cover;
                    display: flex;
                    padding-right: 1rem;
                    padding-left: 1rem;
                    place-items: center;
                    height: 100vh;
                }

                .login-content {
                    flex: 1;
                    text-align: -webkit-center;
                    text-align: -moz-right;
                }

                .card {
                    width: 90%;
                    text-align: left;
                    padding: 1rem;
                }
            }

            /* Small devices (portrait tablets and large phones, 600px and up) */
            @media only screen and (min-width: 600px) {
                body {
                    background: url("${pageContext.request.contextPath}/resources/images/login_bg.jpg");
                    background-repeat: no-repeat;
                    background-size: cover;
                    display: flex;
                    padding-right: 6rem;
                    padding-left: 6rem;
                    place-items: center;
                    height: 100vh;
                }

                .login-content {
                    flex: 1;
                    text-align: -webkit-center;
                    text-align: -moz-right;
                }

                .card {
                    width: 90%;
                    text-align: left;
                    padding: 2rem;
                }
            }

            /* Medium devices (landscape tablets, 768px and up) */
            @media only screen and (min-width: 768px) {
                body {
                    background: url("${pageContext.request.contextPath}/resources/images/login_bg.jpg");
                    background-repeat: no-repeat;
                    background-size: cover;
                    display: flex;
                    padding-right: 12rem;
                    padding-left: 12rem;
                    place-items: center;
                    height: 100vh;
                }

                .login-content {
                    flex: 1;
                    text-align: -webkit-center;
                    text-align: -moz-right;
                }

                .card {
                    width: 90%;
                    text-align: left;
                    padding: 2rem;
                }
            }

            /* Large devices (laptops/desktops, 992px and up) */
            @media only screen and (min-width: 992px) {
                body {
                    background: url("${pageContext.request.contextPath}/resources/images/login_bg.jpg");
                    background-repeat: no-repeat;
                    background-size: cover;
                    display: flex;
                    padding-right: 17rem;
                    padding-left: 17rem;
                    place-items: center;
                    height: 100vh;
                }

                .login-content {
                    flex: 1;
                    text-align: -webkit-center;
                    text-align: -moz-right;
                }

                .card {
                    width: 70%;
                    text-align: left;
                    padding: 2rem;
                }
            }

            /* Extra large devices (large laptops and desktops, 1200px and up) */
            @media only screen and (min-width: 1200px) {
                body {
                    background: url("${pageContext.request.contextPath}/resources/images/login_bg.jpg");
                    background-repeat: no-repeat;
                    background-size: cover;
                    display: flex;
                    padding-right: 4rem;
                    padding-left: 4rem;
                    place-items: center;
                    height: 100vh;
                }

                .login-content {
                    flex: 1;
                    text-align: -webkit-right;
                    text-align: -moz-right;
                }

                .card {
                    width: 30%;
                    text-align: left;
                    padding: 2rem;
                }
            }

            input {
                width: 100%;
                height: 40px;
                padding: 0.5rem;
                border-radius: 5px;
                border: solid 1px #6e8dc7;
                background-color: #fff !important;
            }

            button {
                margin-top: 10px;
                width: 100%;
            }

            .mt-2 {
                margin-top: 1.5rem !important;
            }

            .error-message {
                color: red;
                margin-top: 9 px;
                font-size: 12px;
                text-align: left;
            }
        </style>

        </html>
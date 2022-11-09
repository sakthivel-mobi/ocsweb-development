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

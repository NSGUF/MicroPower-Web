window.onload = init;
function init() {
	if (document.getElementById) {
		var oImg = document.getElementById("imgHead");
		var oSelect = document.getElementById("nav-little");
		oImg.onmouseover = function() {
			oSelect.style.display = "block";
		}
		oImg.onmouseout = function() {
			oSelect.style.display = "none";
		}
	} else {
		alert("Your brower not support this script");
	}
}
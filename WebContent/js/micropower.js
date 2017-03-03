window.onload = function() {
	var oA = document.getElementsByClassName("aActive");
	for ( var i = 0; i < oA.length; i++) {
		oA[i].onclick = changeColor;
	}
}
function changeColor() {
	this.backgroundColor = "#43AC43";
	this.color = "#fff";
}
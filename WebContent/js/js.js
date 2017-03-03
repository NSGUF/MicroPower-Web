function block(x) {
	var oi = document.getElementById(x);
	oi.style.display = 'block';
	var flag = true;
	document.body.onclick = function() {
		if (flag) {
			document.getElementById(x).style.display = 'none';

		} else {
			document.getElementById(x).style.display = 'block';
		}
	};
}
function onlyNum() {
	if (!((event.keyCode >= 48 && event.keyCode <= 57)
			|| (event.keyCode >= 96 && event.keyCode <= 105) || (event.keyCode == 8)))
		event.returnValue = false;
}
function check1() {
	var v1 = document.getElementById("raisefundsNumber").value;
	var v2 = document.getElementById("supportContent").value;
	var v3 = document.getElementById("keyword").value;
	if (!(v1 && v2 && v3)) {
		alert("填写内容不能为空！");
		return false;
	}
	return true;
}
function check2() {

	var v4 = document.getElementById("raiseGoods").value;
	var v5 = document.getElementById("Address").value;
	var v6 = document.getElementById("keyword").value;
	if (!(v4 && v5 && v6)) {
		alert("填写内容不能为空！");
		return false;
	}
	return true;
}
function show() {
	var hideobj = document.getElementById("hidebg");
	hidebg.style.display = "block";
	hidebg.style.height = document.body.clientHeight + "px";
	document.getElementById("hidebox").style.display = "block";
	document.getElementById("agreementtitle").style.display = "block";

	document.getElementById("agreement").style.display = "block";
	document.getElementById("hidebutton").style.display = "block";
}
function hide() {
	document.getElementById("hidebox").style.display = "none";
	document.getElementById("agreementtitle").style.display = "none";

	document.getElementById("hidebg").style.display = "none";
	document.getElementById("agreement").style.display = "none";
	document.getElementById("hidebutton").style.display = "none";
}
function doAddImgOne() {
	var file = document.getElementById("addImgOne");
	var showImg = document.getElementById("showImgOne");
	if (window.FileReader) {// chrome,firefox7+,opera,IE10+
		oFReader = new FileReader();
		oFReader.readAsDataURL(file.files[0]);
		oFReader.onload = function(oFREvent) {
			showImg.src = oFREvent.target.result;// .src = oFREvent.target.result;
		}
	} else if (document.all) {// IE9-//IE使用滤镜，实际测试IE6设置src为物理路径发布网站通过http协议访问时还是没有办法加载图片
		file.select();
		file.blur();// 要添加这句，要不会报拒绝访问错误（IE9或者用ie9+默认ie8-都会报错，实际的IE8-不会报错）
		var reallocalpath = document.selection.createRange().text;// IE下获取实际的本地文件路径
		// if (window.ie6) pic.src = reallocalpath;
		// //IE6浏览器设置img的src为本地路径可以直接显示图片
		// else {
		// //非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现，IE10浏览器不支持滤镜，需要用FileReader来实现，所以注意判断FileReader先
		// pic.style.filter =
		// "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\""
		// + reallocalpath + "\")";
		// pic.src =
		// 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';//设置img的src为base64编码的透明图片，要不会显示红xx
		// }
		showImg.src = reallocalpath;// .src =
		// oFREvent.target.result;
	} else if (file.files) {// firefox6-
		url = file.files.item(0).getAsDataURL();
		showImg.src = url;
	}
	if (showImg.src != "images/do/addImg.png") {
		document.getElementById("addImgShowTwo").style.display = "block";
	}
}function doAddImg() {
	var file = document.getElementById("addImgOne");
	var showImg = document.getElementById("showImgOne");
	if (window.FileReader) {// chrome,firefox7+,opera,IE10+
		oFReader = new FileReader();
		oFReader.readAsDataURL(file.files[0]);
		oFReader.onload = function(oFREvent) {
			showImg.src = oFREvent.target.result;// .src = oFREvent.target.result;
		}
	} else if (document.all) {// IE9-//IE使用滤镜，实际测试IE6设置src为物理路径发布网站通过http协议访问时还是没有办法加载图片
		file.select();
		file.blur();// 要添加这句，要不会报拒绝访问错误（IE9或者用ie9+默认ie8-都会报错，实际的IE8-不会报错）
		var reallocalpath = document.selection.createRange().text;// IE下获取实际的本地文件路径
		// if (window.ie6) pic.src = reallocalpath;
		// //IE6浏览器设置img的src为本地路径可以直接显示图片
		// else {
		// //非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现，IE10浏览器不支持滤镜，需要用FileReader来实现，所以注意判断FileReader先
		// pic.style.filter =
		// "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\""
		// + reallocalpath + "\")";
		// pic.src =
		// 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';//设置img的src为base64编码的透明图片，要不会显示红xx
		// }
		showImg.src = reallocalpath;// .src =
		// oFREvent.target.result;
	} else if (file.files) {// firefox6-
		url = file.files.item(0).getAsDataURL();
		showImg.src = url;
	}
	if (showImg.src != "images/do/addImg.png") {
		document.getElementById("addImgShowTwo").style.display = "block";
	}
}function doAddImgTwo() {
	var file = document.getElementById("addImgTwo");
	var showImg = document.getElementById("showImgTwo");
	if (window.FileReader) {// chrome,firefox7+,opera,IE10+
		oFReader = new FileReader();
		oFReader.readAsDataURL(file.files[0]);
		oFReader.onload = function(oFREvent) {
			showImg.src = oFREvent.target.result;// .src = oFREvent.target.result;
		}
	} else if (document.all) {// IE9-//IE使用滤镜，实际测试IE6设置src为物理路径发布网站通过http协议访问时还是没有办法加载图片
		file.select();
		file.blur();// 要添加这句，要不会报拒绝访问错误（IE9或者用ie9+默认ie8-都会报错，实际的IE8-不会报错）
		var reallocalpath = document.selection.createRange().text;// IE下获取实际的本地文件路径
		// if (window.ie6) pic.src = reallocalpath;
		// //IE6浏览器设置img的src为本地路径可以直接显示图片
		// else {
		// //非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现，IE10浏览器不支持滤镜，需要用FileReader来实现，所以注意判断FileReader先
		// pic.style.filter =
		// "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\""
		// + reallocalpath + "\")";
		// pic.src =
		// 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';//设置img的src为base64编码的透明图片，要不会显示红xx
		// }
		showImg.src = reallocalpath;// .src =
		// oFREvent.target.result;
	} else if (file.files) {// firefox6-
		url = file.files.item(0).getAsDataURL();
		showImg.src = url;
	}
	if (showImg.src != "images/do/addImg.png") {
		document.getElementById("addImgShowThree").style.display = "block";
	}
}function doAddImgThree() {
	var file = document.getElementById("addImgThree");
	var showImg = document.getElementById("showImgThree");
	if (window.FileReader) {// chrome,firefox7+,opera,IE10+
		oFReader = new FileReader();
		oFReader.readAsDataURL(file.files[0]);
		oFReader.onload = function(oFREvent) {
			showImg.src = oFREvent.target.result;// .src = oFREvent.target.result;
		}
	} else if (document.all) {// IE9-//IE使用滤镜，实际测试IE6设置src为物理路径发布网站通过http协议访问时还是没有办法加载图片
		file.select();
		file.blur();// 要添加这句，要不会报拒绝访问错误（IE9或者用ie9+默认ie8-都会报错，实际的IE8-不会报错）
		var reallocalpath = document.selection.createRange().text;// IE下获取实际的本地文件路径
		// if (window.ie6) pic.src = reallocalpath;
		// //IE6浏览器设置img的src为本地路径可以直接显示图片
		// else {
		// //非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现，IE10浏览器不支持滤镜，需要用FileReader来实现，所以注意判断FileReader先
		// pic.style.filter =
		// "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\""
		// + reallocalpath + "\")";
		// pic.src =
		// 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';//设置img的src为base64编码的透明图片，要不会显示红xx
		// }
		showImg.src = reallocalpath;// .src =
		// oFREvent.target.result;
	} else if (file.files) {// firefox6-
		url = file.files.item(0).getAsDataURL();
		showImg.src = url;
	}
	if (showImg.src != "images/do/addImg.png") {
		document.getElementById("addImgShowFour").style.display = "block";
	}
}function doAddImgFour() {
	var file = document.getElementById("addImgFour");
	var showImg = document.getElementById("showImgFour");
	if (window.FileReader) {// chrome,firefox7+,opera,IE10+
		oFReader = new FileReader();
		oFReader.readAsDataURL(file.files[0]);
		oFReader.onload = function(oFREvent) {
			showImg.src = oFREvent.target.result;// .src = oFREvent.target.result;
		}
	} else if (document.all) {// IE9-//IE使用滤镜，实际测试IE6设置src为物理路径发布网站通过http协议访问时还是没有办法加载图片
		file.select();
		file.blur();// 要添加这句，要不会报拒绝访问错误（IE9或者用ie9+默认ie8-都会报错，实际的IE8-不会报错）
		var reallocalpath = document.selection.createRange().text;// IE下获取实际的本地文件路径
		showImg.src = reallocalpath;// .src =
		// oFREvent.target.result;
	} else if (file.files) {// firefox6-
		url = file.files.item(0).getAsDataURL();
		showImg.src = url;
	}
	if (showImg.src != "images/do/addImg.png") {
		document.getElementById("addImgShowFive").style.display = "block";
	}
}function doAddImgFive() {
	var file = document.getElementById("addImgFive");
	var showImg = document.getElementById("showImgFive");
	if (window.FileReader) {// chrome,firefox7+,opera,IE10+
		oFReader = new FileReader();
		oFReader.readAsDataURL(file.files[0]);
		oFReader.onload = function(oFREvent) {
			showImg.src = oFREvent.target.result;// .src = oFREvent.target.result;
		}
	} else if (document.all) {// IE9-//IE使用滤镜，实际测试IE6设置src为物理路径发布网站通过http协议访问时还是没有办法加载图片
		file.select();
		file.blur();// 要添加这句，要不会报拒绝访问错误（IE9或者用ie9+默认ie8-都会报错，实际的IE8-不会报错）
		var reallocalpath = document.selection.createRange().text;// IE下获取实际的本地文件路径
		// if (window.ie6) pic.src = reallocalpath;
		// //IE6浏览器设置img的src为本地路径可以直接显示图片
		// else {
		// //非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现，IE10浏览器不支持滤镜，需要用FileReader来实现，所以注意判断FileReader先
		// pic.style.filter =
		// "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\""
		// + reallocalpath + "\")";
		// pic.src =
		// 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';//设置img的src为base64编码的透明图片，要不会显示红xx
		// }
		showImg.src = reallocalpath;// .src =
		// oFREvent.target.result;
	} else if (file.files) {// firefox6-
		url = file.files.item(0).getAsDataURL();
		showImg.src = url;
	}
	if (showImg.src != "images/do/addImg.png") {
		document.getElementById("addImgShowSix").style.display = "block";
	}
}function doAddImgSix() {
	var file = document.getElementById("addImgSix");
	var showImg = document.getElementById("showImgSix");
	if (window.FileReader) {// chrome,firefox7+,opera,IE10+
		oFReader = new FileReader();
		oFReader.readAsDataURL(file.files[0]);
		oFReader.onload = function(oFREvent) {
			showImg.src = oFREvent.target.result;// .src = oFREvent.target.result;
		}
	} else if (document.all) {// IE9-//IE使用滤镜，实际测试IE6设置src为物理路径发布网站通过http协议访问时还是没有办法加载图片
		file.select();
		file.blur();// 要添加这句，要不会报拒绝访问错误（IE9或者用ie9+默认ie8-都会报错，实际的IE8-不会报错）
		var reallocalpath = document.selection.createRange().text;// IE下获取实际的本地文件路径
		showImg.src = reallocalpath;// .src =
		// oFREvent.target.result;
	} else if (file.files) {// firefox6-
		url = file.files.item(0).getAsDataURL();
		showImg.src = url;
	}
	if (showImg.src != "images/do/addImg.png") {
		document.getElementById("addImgShowSeven").style.display = "block";
	}
}function doAddImgSeven() {
	var file = document.getElementById("addImgSeven");
	var showImg = document.getElementById("showImgSeven");
	if (window.FileReader) {// chrome,firefox7+,opera,IE10+
		oFReader = new FileReader();
		oFReader.readAsDataURL(file.files[0]);
		oFReader.onload = function(oFREvent) {
			showImg.src = oFREvent.target.result;// .src = oFREvent.target.result;
		}
	} else if (document.all) {// IE9-//IE使用滤镜，实际测试IE6设置src为物理路径发布网站通过http协议访问时还是没有办法加载图片
		file.select();
		file.blur();// 要添加这句，要不会报拒绝访问错误（IE9或者用ie9+默认ie8-都会报错，实际的IE8-不会报错）
		var reallocalpath = document.selection.createRange().text;// IE下获取实际的本地文件路径
		showImg.src = reallocalpath;// .src =
		// oFREvent.target.result;
	} else if (file.files) {// firefox6-
		url = file.files.item(0).getAsDataURL();
		showImg.src = url;
	}
	if (showImg.src != "images/do/addImg.png") {
		document.getElementById("addImgShowEight").style.display = "block";
	}
}function doAddImgEight() {
	var file = document.getElementById("addImgEight");
	var showImg = document.getElementById("showImgEight");
	if (window.FileReader) {// chrome,firefox7+,opera,IE10+
		oFReader = new FileReader();
		oFReader.readAsDataURL(file.files[0]);
		oFReader.onload = function(oFREvent) {
			showImg.src = oFREvent.target.result;// .src = oFREvent.target.result;
		}
	} else if (document.all) {// IE9-//IE使用滤镜，实际测试IE6设置src为物理路径发布网站通过http协议访问时还是没有办法加载图片
		file.select();
		file.blur();// 要添加这句，要不会报拒绝访问错误（IE9或者用ie9+默认ie8-都会报错，实际的IE8-不会报错）
		var reallocalpath = document.selection.createRange().text;// IE下获取实际的本地文件路径
		showImg.src = reallocalpath;// .src =
		// oFREvent.target.result;
	} else if (file.files) {// firefox6-
		url = file.files.item(0).getAsDataURL();
		showImg.src = url;
	}
}
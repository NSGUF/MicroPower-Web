<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	//使用IE条件注释来判断是否IE6，通过判断userAgent不一定准确
	if (document.all)
		document
				.write('<!--[if lte IE 6]><script type="text/javascript">window.ie6= true<\/script><![endif]-->');
	// var ie6 = /msie 6/i.test(navigator.userAgent);//不推荐，有些系统的ie6 userAgent会是IE7或者IE8
	function change(picId, fileId) {
		var pic = document.getElementById(picId);
		var file = document.getElementById(fileId);
		if (window.FileReader) {//chrome,firefox7+,opera,IE10+
			oFReader = new FileReader();
			for ( var i = 0; i < file.files.length; i++) {
				oFReader.readAsDataURL(file.files[0]);
				oFReader.onload = function(oFREvent) {
					pic.innerHTML += "<img src="+"'"+oFREvent.target.result+"'"+"/>";//.src = oFREvent.target.result;
				}
			}
		} else if (document.all) {//IE9-//IE使用滤镜，实际测试IE6设置src为物理路径发布网站通过http协议访问时还是没有办法加载图片
			file.select();
			file.blur();//要添加这句，要不会报拒绝访问错误（IE9或者用ie9+默认ie8-都会报错，实际的IE8-不会报错）
			var reallocalpath = document.selection.createRange().text//IE下获取实际的本地文件路径
			//if (window.ie6) pic.src = reallocalpath; //IE6浏览器设置img的src为本地路径可以直接显示图片
			//else { //非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现，IE10浏览器不支持滤镜，需要用FileReader来实现，所以注意判断FileReader先
			//pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\""
			//		+ reallocalpath + "\")";
			//pic.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';//设置img的src为base64编码的透明图片，要不会显示红xx
			// }
			pic.innerHTML += "<img src="+reallocalpath+"/>";//.src = oFREvent.target.result;
		} else if (file.files) {//firefox6-
			if (file.files.item(0)) {
				url = file.files.item(0).getAsDataURL();
				pic.innerHTML += "<img src="+url+"/>";
			}
		}
	}
</script>
</head>
<body>
<body>
	<form name="form1" enctype="multipart/form-data">
		<table>
			<tr>
				<td>草图1：</td>
				<td><input type="file" name="file1" id="file1"
					onchange="change('pic1','file1')"></td>
			<tr>
				<td>草图浏览1：</td>
				<td id="pic1"></td>
			</tr>
		</table>
	</form>
</body>
</html>
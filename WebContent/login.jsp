<%@ page language="java" contentType="text/html; charset=utf-8"
	import="com.login.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>微助力-微信登录</title>
<meta name="keywords" content="社交众筹,轻众筹,众筹空间,微捐赠,微信众筹,众筹">
<meta name="description" content="微捐赠引入“社交众筹”和“轻众筹”概念，将众筹带入微信朋友圈、社交圈。">
<link rel="stylesheet" type="text/css" href="css/login.css">
</head>
<body>
	<%
		session.removeAttribute("user");
		session.invalidate();

		Cookie[] myCookie = request.getCookies();//创建一个Cookie对象数组
		for (int i = 0; i < myCookie.length; i++) {
			if (myCookie[i].getName().equals("username")) {
				myCookie[i].setMaxAge(0);
				response.addCookie(myCookie[i]);
			}
		}
	%>

	<div id="Login">
		<div class="Logo">
			<a href="index.jsp"> <img src="images/logo.png"></a>
		</div>
		<div class="Login_weixin">
			<h2>微信登录</h2>
			<img src="images/login/erweima.png">
			<p>请使用微信扫描二维码登录</p>
			<p>“微捐赠”</p>
		</div>
		<div class="Login_other">
			<p>————————————或————————————</p>
			<ul>
				<li><a href="phone.jsp" target="_self">使用手机号码登录</a></li>
				<li><a href="">使用微博账号登录</a></li>
			</ul>
		</div>
	</div>
</body>
</html>
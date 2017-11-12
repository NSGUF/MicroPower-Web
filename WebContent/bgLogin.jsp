<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="assets/css/style.css" rel='stylesheet' type='text/css' />
<script type="application/x-javascript">
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); 
	 function hideURLbar(){ window.scrollTo(0,1); } 

</script>
<title>用户登录</title>
</head>
<body>
	<!-----start-main---->
	<div class="main">
		<div class="login-form">
			<h1>登录</h1>
			<div class="head">
				<img src="images/user.png" alt="登录" />
			</div>
			<form method="post" action="BgLoginServlet">
				<input type="text" class="text" name="userName" placeholder="用户名">
				<input name="userPwd" type="text" placeholder="密码">
				<div class="submit">
					<input type="submit" value="登陆">
				</div>

			</form>
		</div>
	</div>
	<!-----//end-main---->
</body>
</html>
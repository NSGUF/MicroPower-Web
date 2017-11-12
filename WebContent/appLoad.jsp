<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.login.*" import="com.mircolove.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微助力-APP下载</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/appLoad.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/head.css" rel="stylesheet" type="text/css" media="all" />
<script src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript" src="js/select.js"></script>
<!-- //end-smoth-scrolling -->
</head>
<body>
	<%
		User user = (User) session.getAttribute("user");
		if (user == null) {
			UserDao userDao = new UserDao();
			boolean isLogin = false;
			String cell_phone_num = "";
			Cookie[] myCookie = request.getCookies();//创建一个Cookie对象数组
			if (myCookie != null)
			for (int i = 0; i < myCookie.length; i++) {//设立一个循环，来访问Cookie对象数组的每一个元素
				Cookie newCookie = myCookie[i];
				if (newCookie.getName().equals("username")) {//判断元素的值是否为username中的
					isLogin = true;
					cell_phone_num = newCookie.getValue();
					user = userDao.login(cell_phone_num);
				}
			}
		}
	%>
	<!--header-->
	<!--header-->
	<div class="header-top">
		<!--container-->
		<div class="container">
			<div class="top-nav">
				<div class="logo">
					<a href="index.jsp"> <img src="images/index/logo.png"
						class="img-responsive" alt="" /></a>
				</div>
				<ul id="nav">
					<li><a href="showBetterProjects.jsp">浏览项目</a></li>
					<li><a href="initProject.jsp">发起项目</a></li>
					<li><a href="appLoad.jsp">APP下载</a></li>
					<li><a href="footHelp.jsp">帮助中心</a></li>
					<%
						if (user != null) {
					%>
					<li id="imgHead"><img class="img-circle"
						src="<%=user.getHead_portrait()%>" alt="" width="50px"
						height="50px">
						<ul id="nav-little">
							<li><a href="myProjects.jsp">我的项目</a></li>
							<li><a href="myWallet.jsp">我的钱包</a></li>
							<li><a href="setting.jsp">个人设置</a></li>
							<li><a href="login.jsp">退出</a></li>
						</ul></li>
					<%
						} else {
					%>
					<li><a href="login.jsp">登录</a></li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
	</div>
	<div class="box"></div>
	<div class="main">
		<div class="box"></div>
		<h1>更多精彩，等你发现</h1>
		<p>用心打造全民公益众筹APP</p>
		<div class="main_class">
			<img src="images/app/erweima.png">
		</div>
	</div>
	<hr>
	<!--main end here-->
	<!--footer start here-->
	<div class="footer">
        <div class="container">
            <div class="col-md-3 ftr-grd">
                <h3>信息</h3>
                <p>江西师范大学软件学院14移动班</p>
                <!-- <ul class="ftr-icons">
                	<li><a href="#"><span class="cr-fa"> </span></a></li>
                </ul>-->
            </div>
            <div class="col-md-3 ftr-grd">
                <h3>关于微捐助</h3>
                <ul class="ftr-links">
                    <li><a href="footAbout.jsp">微捐助介绍</a></li>
                    <li><a href="footConnect.jsp">联系我们</a></li>
                    <li><a href="footJoin.jsp">加入我们</a></li>
                </ul>
            </div>
            <div class="col-md-3 ftr-grd">
                <h3>用户服务</h3>
                <ul class="ftr-categ">
                    <li><a href="footHelp.jsp">帮助中心</a></li>
                    <li><a href="footTerms.jsp">使用条款</a></li>
                    <li><a href="footPrivacy.jsp">隐私政策</a></li>
                </ul>
            </div>
            <div class="col-md-3 ftr-grd">
                <h3>关注我们</h3>
                <ul class="ftr-categ">
                    <li><a href="http://weibo.com/weikirin?topnav=1&wvr=6&topsug=1">新浪微博</a></li>
                    <li><a href="footMedia.jsp">媒体报道</a></li>
                </ul>
            </div>
            <div class="col-md-3 ftr-grd">
                <div class="col-md-6 ftr-gd4-1 text-center">
                    <img src="images/index/foot1.png" alt="" class="img-responsive">下载APP
                </div>
                <div class="col-md-6 ftr-gd4-1 text-center">
                    <img src="images/index/foot2.png" alt="" class="img-responsive">微信公众号
                </div>
            </div>
        </div>
    </div>
	<!--footer end here-->
	<!--copyright start here-->
	<div class="copyright">
		<div class="container">
			<div class="copy-main">
				<p>
					Copyright &copy; 2016微捐赠 - 江西师范大学 <a href="#"> <img
						src="images/index/copyright.png" alt="" class="img-responsive"></a>
				</p>
			</div>
			<script type="text/javascript">
				$(document).ready(function() {
					$().UItoTop({
						easingType : 'easeOutQuart'
					});

				});
			</script>
			<a href="#" id="toTop" style="display: block;"><span
				id="toTopHover" style="opacity: 1;"></span></a>

		</div>
	</div>
</body>
</html>
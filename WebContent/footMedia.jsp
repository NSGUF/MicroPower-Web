<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.login.*" import="com.mircolove.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微助力-媒体报道</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all">
<link href="css/style1.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/footMedia.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/head.css" rel="stylesheet" type="text/css" media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript" src="js/select.js"></script>
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
	 <!--banner start here-->
    <div class="banner">
        <div class="container">
            <div class="box">
            </div>
        </div>
        <!-----------------content-box-1-------------------->
        <div class="container">
            <h2 class="text-center">隐私政策</h2>
            <div class="media">
                <h3>
                    <a target="_blank" href="http://36kr.com/p/5047664.html">社交众筹平台轻松筹完成近2000万美元B+轮融资，腾讯参投</a>
                    <small>
                        <br>
                        / 2016-06-06
                    </small>
                </h3>
                <p>6月1日，社交众筹平台轻松筹向 36 氪确认完成近 2000 万美元 B+ 轮融资，本轮融资由腾讯、IDG、德同资本、以及同道资本共同投资。目前，轻松筹估值 3.5 亿美元。</p>
                <p>
                    <a target="_blank" href="http://36kr.com/p/5047664.html">继续阅读 →</a>
                </p>
                <hr>
            </div>
            <div class="media">
                <h3>
                    <a target="_blank" href="http://36kr.com/p/5047664.html">社交众筹平台轻松筹完成近2000万美元B+轮融资，腾讯参投</a>
                    <small>
                        <br>
                        / 2016-06-06
                    </small>
                </h3>
                <p>6月1日，社交众筹平台轻松筹向 36 氪确认完成近 2000 万美元 B+ 轮融资，本轮融资由腾讯、IDG、德同资本、以及同道资本共同投资。目前，轻松筹估值 3.5 亿美元。</p>
                <p>
                    <a target="_blank" href="http://36kr.com/p/5047664.html">继续阅读 →</a>
                </p>
                <hr>
            </div>
            <div class="media">
                <h3>
                    <a target="_blank" href="http://36kr.com/p/5047664.html">社交众筹平台轻松筹完成近2000万美元B+轮融资，腾讯参投</a>
                    <small>
                        <br>
                        / 2016-06-06
                    </small>
                </h3>
                <p>6月1日，社交众筹平台轻松筹向 36 氪确认完成近 2000 万美元 B+ 轮融资，本轮融资由腾讯、IDG、德同资本、以及同道资本共同投资。目前，轻松筹估值 3.5 亿美元。</p>
                <p>
                    <a target="_blank" href="http://36kr.com/p/5047664.html">继续阅读 →</a>
                </p>
                <hr>
            </div>
            <div class="media">
                <h3>
                    <a target="_blank" href="http://36kr.com/p/5047664.html">社交众筹平台轻松筹完成近2000万美元B+轮融资，腾讯参投</a>
                    <small>
                        <br>
                        / 2016-06-06
                    </small>
                </h3>
                <p>6月1日，社交众筹平台轻松筹向 36 氪确认完成近 2000 万美元 B+ 轮融资，本轮融资由腾讯、IDG、德同资本、以及同道资本共同投资。目前，轻松筹估值 3.5 亿美元。</p>
                <p>
                    <a target="_blank" href="http://36kr.com/p/5047664.html">继续阅读 →</a>
                </p>
                <hr>
            </div>
            <div class="media">
                <h3>
                    <a target="_blank" href="http://36kr.com/p/5047664.html">社交众筹平台轻松筹完成近2000万美元B+轮融资，腾讯参投</a>
                    <small>
                        <br>
                        / 2016-06-06
                    </small>
                </h3>
                <p>6月1日，社交众筹平台轻松筹向 36 氪确认完成近 2000 万美元 B+ 轮融资，本轮融资由腾讯、IDG、德同资本、以及同道资本共同投资。目前，轻松筹估值 3.5 亿美元。</p>
                <p>
                    <a target="_blank" href="http://36kr.com/p/5047664.html">继续阅读 →</a>
                </p>
                <hr>
            </div>
            <div class="media">
                <h3>
                    <a target="_blank" href="http://36kr.com/p/5047664.html">社交众筹平台轻松筹完成近2000万美元B+轮融资，腾讯参投</a>
                    <small>
                        <br>
                        / 2016-06-06
                    </small>
                </h3>
                <p>6月1日，社交众筹平台轻松筹向 36 氪确认完成近 2000 万美元 B+ 轮融资，本轮融资由腾讯、IDG、德同资本、以及同道资本共同投资。目前，轻松筹估值 3.5 亿美元。</p>
                <p>
                    <a target="_blank" href="http://36kr.com/p/5047664.html">继续阅读 →</a>
                </p>
                <hr>
            </div>
            <div class="media">
                <h3>
                    <a target="_blank" href="http://36kr.com/p/5047664.html">社交众筹平台轻松筹完成近2000万美元B+轮融资，腾讯参投</a>
                    <small>
                        <br>
                        / 2016-06-06
                    </small>
                </h3>
                <p>6月1日，社交众筹平台轻松筹向 36 氪确认完成近 2000 万美元 B+ 轮融资，本轮融资由腾讯、IDG、德同资本、以及同道资本共同投资。目前，轻松筹估值 3.5 亿美元。</p>
                <p>
                    <a target="_blank" href="http://36kr.com/p/5047664.html">继续阅读 →</a>
                </p>
                <hr>
            </div>
            <div class="media">
                <h3>
                    <a target="_blank" href="http://36kr.com/p/5047664.html">社交众筹平台轻松筹完成近2000万美元B+轮融资，腾讯参投</a>
                    <small>
                        <br>
                        / 2016-06-06
                    </small>
                </h3>
                <p>6月1日，社交众筹平台轻松筹向 36 氪确认完成近 2000 万美元 B+ 轮融资，本轮融资由腾讯、IDG、德同资本、以及同道资本共同投资。目前，轻松筹估值 3.5 亿美元。</p>
                <p>
                    <a target="_blank" href="http://36kr.com/p/5047664.html">继续阅读 →</a>
                </p>
                <hr>
            </div>
            <div class="media">
                <h3>
                    <a target="_blank" href="http://36kr.com/p/5047664.html">社交众筹平台轻松筹完成近2000万美元B+轮融资，腾讯参投</a>
                    <small>
                        <br>
                        / 2016-06-06
                    </small>
                </h3>
                <p>6月1日，社交众筹平台轻松筹向 36 氪确认完成近 2000 万美元 B+ 轮融资，本轮融资由腾讯、IDG、德同资本、以及同道资本共同投资。目前，轻松筹估值 3.5 亿美元。</p>
                <p>
                    <a target="_blank" href="http://36kr.com/p/5047664.html">继续阅读 →</a>
                </p>
                <hr>
            </div>
            <div class="media">
                <h3>
                    <a target="_blank" href="http://36kr.com/p/5047664.html">社交众筹平台轻松筹完成近2000万美元B+轮融资，腾讯参投</a>
                    <small>
                        <br>
                        / 2016-06-06
                    </small>
                </h3>
                <p>6月1日，社交众筹平台轻松筹向 36 氪确认完成近 2000 万美元 B+ 轮融资，本轮融资由腾讯、IDG、德同资本、以及同道资本共同投资。目前，轻松筹估值 3.5 亿美元。</p>
                <p>
                    <a target="_blank" href="http://36kr.com/p/5047664.html">继续阅读 →</a>
                </p>
                <hr>
            </div>
        </div>
        <!-----------------content-box-2-------------------->
        <!--start-blog-pagenate-->
        <div class="blog-pagenat">
            <ul>
                <li><a class="frist" href="#">«</a></li>
                <li><a href="#">‹</a></li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">›</a></li>
                <li><a class="last" href="#">»</a></li>
            </ul>
        </div>
        <!--//End-blog-pagenate-->
        <div class="box">
        </div>
    </div>
    </div>
    <!--banner end here-->
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
					<li><a
						href="http://weibo.com/weikirin?topnav=1&wvr=6&topsug=1">新浪微博</a></li>
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
						src="images/index/copyright.png" alt=""></a>
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
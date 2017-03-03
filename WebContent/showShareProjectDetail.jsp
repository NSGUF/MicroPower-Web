<%@page import="com.mircolove.MircoLoveDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	import="java.text.*" import="com.share.*" import="com.login.*"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String witness_id = request.getParameter("witness_id");
	SharerDao sharerDao = new SharerDao();
	Sharer sharer = new Sharer();
	sharer = sharerDao.getSharer(witness_id);
%>
<title><%=sharer.getWitness_title()%></title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all">
<link href="css/style1.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/head.css" rel="stylesheet" type="text/css" media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript" src="js/select.js"></script>
</head>
<body>
	<!--header-->
	<%
		User user = (User) session.getAttribute("user");
		UserDao userDao = new UserDao();
		if (user == null) {
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
			<div class="box"></div>
			<div class="box1">
				<h2><%=sharer.getWitness_title()%></h2>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="mydetails">
						<img class="img-circle"
							src="<%=userDao.getUser(sharer.getUser_id()).getHead_portrait()%>"
							alt="Generic placeholder image" width="70" height="70">
						<%=userDao.getUser(sharer.getUser_id()).getPet_name()%>
						<span><%=MircoLoveDao.getTime(sharer.getWitness_open_date())%></span>
						<hr>
						<div class="item col-md-6">
							<strong>0<small>次</small>
							</strong>
							<p>评论次数</p>
							<hr>
						</div>
						<div class="item col-md-6">
							<strong>&nbsp;<small></small>
							</strong>
							<p>&nbsp;</p>
							<hr>
						</div>
						<p class="text-center">分享到</p>
						<div class="social-icons">
							<a href="#"><span class="facebook"></span></a> <a href="#"><span
								class="twitter"></span></a> <a href="#"><span class="linkedin"></span></a>
							<a href="#"><span class="googleplus"></span></a>
						</div>
					</div>
				</div>
				<div class="col-md-8">
					<div class="myskills">
						<a href="#1F" name="1F">Ta的评论者</a>
						<div class="content-text">
							&nbsp;&nbsp;<%=sharer.getWitness_describe()%></div>
						<%
							String[] images = sharer.getWitness_image().split(" ");
							for (int i = 0; i < images.length; i++) {
								String image = images[i];
						%>
						<img src="<%=image%>">
						<%
							}
						%>
						<hr id="1F">
						<div class="itemskills">
							<p>评论动态</p>
							<hr>
							<div class="helper">
								<img class="img-circle" src="images/detail/d1.jpg"
									alt="Generic placeholder image">
								<div class="bigger">支持者</div>
								<div class="time">3小时前</div>
								<div class="speak">支持</div>
								<hr>
							</div>
							<p class="text-center">没有了更多了~~~</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--banner end here-->
	<div id="hidebg"></div>
	<div class="hidebox" id="hidebox">
		<div id="agreementtitle" class="agreementtitle">
			<h3>微捐助项目发起条款</h3>
		</div>
		<div class="publish-agreement" id="agreement"></div>
		<div class="hidebutton" id="hidebutton">
			<input type="button" value="已阅读并同意" onClick="hide()">
		</div>
	</div>
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
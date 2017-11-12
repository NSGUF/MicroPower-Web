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
<%
	UserDao userDao = new UserDao();
	User user = userDao.getUser(request.getParameter("user_id"));
%>
<body>
	<div class="banner">
		<div class="container">
			<div class="box1">
				<h2>审核信息</h2>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="mydetails volunteer-left">
						<img class="img-circle" src="<%=user.getHead_portrait()%>" alt=""
							width="70" height="70">
						<%=user.getPet_name()%>
						<span></span>
						<hr>
					</div>
				</div>
				<div class="col-md-8">
					<div class="myskills">
						<div class="content-text">
							&nbsp;&nbsp;<%=user.getVerify_info()%></div>
						<div class="content-text">身份证：</div>
						<%
							String[] images1 = user.getID_card().split(" ");
							for (int i = 0; i < images1.length; i++) {
								String image = images1[i];
						%>
						<img src="<%=image%>">
						<%
							}
						%>
						<div class="content-text">支付宝或微信支付二维码:</div>
						<%
							String image1 = user.getWallet_id();
						%>
						<img src="<%=image1%>">

						<div class="content-text">申请图片:</div>
						<%
							String[] images = user.getVerify_image().split(" ");
							for (int i = 0; i < images.length; i++) {
								String image = images[i];
						%>
						<img src="<%=image%>">
						<%
							}
						%>

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
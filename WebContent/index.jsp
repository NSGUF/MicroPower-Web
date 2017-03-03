<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" import="com.login.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微助力</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/head.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style-setting.css" rel="stylesheet" media="all" />
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
	<div style="height: 80px; width: 100%;"></div>
	<div class="banner">
		<div class="container">
			<div class="banner-main">
				<h2>
					<b>微助力，用爱点燃希望的火炬</b>
				</h2>
				<p>让爱在互动中照亮世界的各个角落</p>
			</div>
		</div>
	</div>
	<!--banner end here-->
	<!--about start here-->
	<div class="about" id="about">
		<div class="container">
			<div class="about-main">
				<div class="about-bottom">
					<div class="col-md-6 about-left">
						<h3>浏览项目</h3>
						<h5>唯有爱，让感动常在</h5>
						<p>伸出您的援手，爱心接力，让希望点亮生命；携手同行，让真情无处不在</p>
						<div class="about-grid">
							<div class="ab-sub-gd">
								<span class="glyphicon glyphicon-star-empty ab-gd-img"
									aria-hidden="true"></span>
								<div class="ab-gd-text">
									<h6>大病救助</h6>
									<p>或许，您的一点力量，就可以为他们延续生命。爱心，可以传递，也可以凝聚，以爱之名，帮助他们脱离困境。</p>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="ab-sub-gd">
								<span class="glyphicon glyphicon-cloud ab-gd-img"
									aria-hidden="true"></span>
								<div class="ab-gd-text">
									<h6>留守儿童</h6>
									<p>也许您的资助会改变他们的命运。请伸出您关爱的双手，让同一片阳光下的孩子拥有幸福的童年和美好的明天。</p>
								</div>
								<div class="clearfix"></div>
							</div>
							<div class="ab-sub-gd">
								<span class="glyphicon glyphicon-leaf ab-gd-img"
									aria-hidden="true"></span>
								<div class="ab-gd-text">
									<h6>野生动物</h6>
									<p>野生动物是人类的朋友，是大自然赋予人类的宝贵自然资源。让野生动物与我们在同一片蓝天下平安地生活。</p>
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
					<div class="col-md-6 about-right">
						<img src="images/index/ab.png" alt="" class="img-responsive">
						<p>微助力-基于社交的全民众筹平台，提供大病救助、留守儿童关爱、野生动物保护三大频道。为助力发起者提供快速的发起通道，多样的社交传播途径，完善的资金管理；为项目支持者提供急需帮助的众筹项目，透明的项目信息，便捷的支付方式，打造高效、便捷的全民众筹平台，帮助更多的资金上急需帮助的人摆脱困境。</p>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<!--about end here-->
	<!--testimonal strat here-->
	<div class="testimo" id="let">
		<div class="container">
			<div class="testimo-main">
				<h3>点点爱心接力，网聚无限实力，让我们共同创造生命的奇迹。</h3>
				<p>如果此时的你深陷困境需要他人的帮助，请再次发起助力，让更多的人帮助你，让更多微小的力量助你渡过难关。</p>
				<a href="#">发起助力</a>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!--testimonal end here-->
	<!--dreams start here-->
	<div class="dreams">
		<div class="container">
			<div class="dream-main">
				<div class="dream-top">
					</br> </br>
					<h3>微助力</h3>
					<p>与爱同行，凝聚力量，共渡难关</p>
				</div>
				<div class="dream-bottom">
					<div class="col-md-2 dream-grid">
						<h3>78346</h3>
						<h4>注册用户</h4>
					</div>
					<div class="col-md-2 dream-grid">
						<h3>343564</h3>
						<h4>助力清单</h4>
					</div>
					<div class="col-md-2 dream-grid">
						<h3>55663251</h3>
						<h4>支持次数</h4>
					</div>
					<div class="col-md-2 dream-grid">
						<h3>897536</h3>
						<h4>分享次数</h4>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<!--dreams end here-->
	<!--ab-info start here-->
	<div class="ab-info">
		<div class="container">
			<div class="ab-info-main">
				<div class="ab-info-bott">
					<div class="col-md-6 ab-info-left">
						<h3>
							我们的使命<span class="ab-info-clr">凝聚爱心，传递阳光</span>
						</h3>
						<p>微捐赠旨在帮助身处困境的人摆脱困难，拥抱生活的希望。</p>
						<span class="ab-line"></span>
					</div>
					<div class="col-md-6 ab-info-right">
						<p>微捐赠可以汇聚更多人的力量，支持者的支持金额通常较小，不会对支持者的生活带来很大的影响，容易等到朋友间的反馈和支持，所以更容易召集大家的参与。</p>
						<a href="">更多</a>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	<!--ab-info end here-->
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
	<!--copyright end here-->
</body>
</html>
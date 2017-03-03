<%@ page language="java" contentType="text/html; charset=utf-8" import="com.login.*"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>微助力-个人设置-添加银行卡</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
    <link href="css/style1.css" rel="stylesheet" type="text/css" media="all" />
    <link href="css/style-setting.css" rel="stylesheet" media="all" />
    <link href="css/style-bank.css" rel="stylesheet" media="all" />
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
	<!--item start here-->
	<div class="item">
		<div class="container">
			<h2 class="text-center">添加银行卡</h2>
			<div class="top-nav">
				<div class="menu">
					<ul id="nav">
						<li><a href="setting.jsp">个人资料</a></li>
						<li><a href="settingReceAddr.jsp">收件地址</a></li>
						<li><a href="settingCard.jsp" class="active">银行卡</a></li>
					</ul>
				</div>
				<hr>
				<div class="detail">
					<ul id="nav" class="nav">
						<li><a href="settingPersonCard.jsp">个人银行卡</a></li>
						<li><a href="settingBankCard.jsp" class="active">对公银行卡</a></li>
					</ul>
				</div>
			</div>
			<p>绑卡需知：平台只支持储蓄卡提现，请勿绑定信用卡、存折等以免影响提现</p>
			<form class="form-horizontal publishForm" name="publishForm"
				id="publishForm">
				<div id="baseform" class="form-container">
					<div class="form-group form-group-lg">
						<label for="" class="col-md-1 control-label">开户单位</label>
						<div class="col-md-9">
							<input type="text" name="name" id="name" value=""
								class="form-control text_validata" placeholder="填写开户单位名称">
						</div>
					</div>
					<div class="form-group form-group-lg">
						<label for="" class="col-md-1 control-label">银行卡号</label>
						<div class="col-md-9">
							<input type="text" name="banknum" id="banknum" value=""
								class="form-control text_validata" placeholder="输入银行卡号">
						</div>
					</div>
					<div class="form-group form-group-lg">
						<label for="" class="col-md-1 control-label">开户行</label>
						<div class="col-md-9">
							<select name="bank" id="bank" class="select">
								<option value="">请选择</option>
							</select>
						</div>
					</div>
					<div class="form-group form-group-lg">
						<div class="col-md-12 text-center form-btn">
							<div class="left-btn">
								<input type="submit" name="submit" class="btn" value="保存">
							</div>
							<div class="right-btn">
								<a href="card.html"><input type="button" name="button"
									value="返回" class="back" /></a>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<hr>
	<!--item end here-->
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
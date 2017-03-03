<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.login.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微助力-个人设置-个人资料</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all">
<link href="css/style1.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style-setting.css" rel="stylesheet" media="all" />
<script src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript" src="js/select.js"></script>
<script>
	function doAddImg() {
		var file = document.getElementById("change_head_portrait");
		var showImg = document.getElementById("head_portrait");
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
	}
</script>
</head>
<body>
	<%
		User user = (User) session.getAttribute("user");
		if (user == null) {
			UserDao userDao = new UserDao();
			boolean isLogin = false;
			String cell_phone_id = "";
			Cookie[] myCookie = request.getCookies();//创建一个Cookie对象数组
			if (myCookie != null)
				for (int i = 0; i < myCookie.length; i++) {//设立一个循环，来访问Cookie对象数组的每一个元素
					Cookie newCookie = myCookie[i];
					if (newCookie.getName().equals("username")) {//判断元素的值是否为username中的
						isLogin = true;
						cell_phone_id = newCookie.getValue();
						user = userDao.login(cell_phone_id);
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
			<h2 class="text-center">个人设置</h2>
			<div class="top-nav">
				<div class="menu">
					<ul id="nav">
						<li><a href="setting.jsp" class="active">个人资料</a></li>
						<li><a href="settingReceAddr.jsp">收件地址</a></li>
						<li><a href="settingCard.jsp">银行卡</a></li>
					</ul>
				</div>
			</div>
			<hr>
			<form action="UserSettingServlet" method="post"
				enctype="multipart/form-data">
				<table>
					<tr>
						<td class="col-md-3 head">头像</td>
						<td class="col-md-7">
							<div class="user-avatar">
								<img id="head_portrait" alt=""
									src="<%=user.getHead_portrait()%>"><span>点击修改头像</span>
							</div>
							<div class="changeHead">
								<input type="file" accept="image/jpeg,image/png,image/bmp" 
									name="fileupload" onChange="doAddImg()"
									id="change_head_portrait">
							</div>
						</td>
					</tr>
					<tr>
						<td class="col-md-3 pet_cell">用户昵称</td>
						<td class="col-md-7"><input class="form-control" type="text"
							name="pet_name" value="<%=user.getPet_name()%>"></td>
					</tr>
					<tr>
						<td class="col-md-3 pet_cell">绑定手机号</td>
						<td class="col-md-7"><strong><%=user.getCell_phone_id()%></strong></td>
					</tr>
					<tr>
						<td class="col-md-3 pet_cell"></td>
						<td class="col-md-3 pet_cell"><input class="login"
							type="submit" value="保 存" style="float: left;"></td>
					</tr>
				</table>
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
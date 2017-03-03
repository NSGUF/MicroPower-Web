<%@ page language="java" contentType="text/html; charset=utf-8"
	import="java.text.*" import="java.sql.ResultSet.*"
	import="com.DataBase.*" import="java.sql.*" import="com.login.*"
	import="com.mircolove.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>微助力-我的项目-发起的项目</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all">
<link href="css/style1.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style-setting.css" rel="stylesheet" media="all" />
<link href="css/style-myproject.css" rel="stylesheet" media="all" />
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
			<h2 class="text-center">我的项目</h2>
			<div class="top-nav">
				<div class="menu">
					<ul id="nav">
						<li><a href="myProjects.jsp" class="active">发起的项目</a></li>
						<li><a href="supportProjects.jsp">支持的项目</a></li>
					</ul>
				</div>
				<hr>
				<div class="detail">
					<ul id="nav" class="nav">
						<li><a href="myProjects.jsp" class="active">全部</a></li>
						<li><a href="mySuccessfulProjects.jsp">已成功</a></li>
						<li><a href="myOngoingProjects.jsp">进行中</a></li>
						<li><a href="myFailProjects.jsp">失败</a></li>
					</ul>
				</div>
				<%
					Connection conn = DataBaseUtil.getConnection();
					String sql = "select * from T_MIRCOLOVE_CHILDREN where is_delete=0 and user_id='"
							+ user.getUser_id() + "'";
					try {
						Statement stm = conn.createStatement();
						ResultSet rs = stm.executeQuery(sql);
						while (rs.next()) {
				%>
				<div class="detail">
					<img class="user-info"
						src="<%=rs.getString("mircolove_list_image").split(" ")[0]%>"
						alt="Generic placeholder image">
					<div class="user-info">
						<div class="bigger"><%=rs.getString("mircolove_list_title")%></div>
						<div class="time"><%=MircoLoveDao.getTime(rs
							.getString("mircolove_open_date"))%></div>
						<div class="time">
							目标金额：<%=rs.getString("mircolove_target_amount")%>&nbsp;已筹金额：<%=rs.getString("mircolove_raise_amount")%>&nbsp;支持次数<%=rs.getString("mircolove_list_support_time")%>&nbsp;
						</div>
					</div>
					<form class="form-horizontal publishForm user-info"
						name="publishForm" method="post" id="publishForm"
						action="DeleteMircoloveServlet" onSubmit="return check1()">
						<input type="hidden" name="mircolove_id"
							value="<%=rs.getString("mircolove_id")%>"> <input
							class="btn btn-primary btn-lg large btn-delete" type="submit"
							value="删除"><a
							class="btn btn-primary btn-lg large btn-delete"
							href="showProjectDetail.jsp?mircolove_id=<%=rs.getString("mircolove_id")%>">查看项目</a>
					</form>
				</div>
				<%
					}
				%>
				<%
					String sql1 = "select * from T_DONATIONINFO where is_delete=0 and user_id='"
								+ user.getUser_id() + "'";
						Statement stm1 = conn.createStatement();
						ResultSet rs1 = stm.executeQuery(sql1);
						while (rs1.next()) {
				%>
				<div class="detail">
					<img class="user-info"
						src="<%=rs1.getString("donation_image").split(" ")[0]%>"
						alt="Generic placeholder image">
					<div class="user-info">
						<div class="bigger"><%=rs1.getString("donation_title")%></div>
						<div class="time"><%=MircoLoveDao.getTime(rs1
							.getString("donation_open_date"))%></div>
						<div class="time">
							<%
								if (rs1.getInt("donation_select_need_or_dona") == 1) {
							%>求助捐赠
							<%
								} else {
							%>我要捐赠<%
								}
							%>
						</div>
					</div>
					<form class="form-horizontal publishForm user-info"
						name="publishForm" method="post" id="publishForm"
						action="DeleteMircoloveServlet" onSubmit="return check1()">
						<input type="hidden" name="mircolove_id"
							value="<%=rs1.getString("donation_id")%>"> <input
							class="btn btn-primary btn-lg large btn-delete" type="submit"
							value="删除"><a
							class="btn btn-primary btn-lg large btn-delete"
							href="showDonationProjectDetail.jsp?donation_id=<%=rs1.getString("donation_id")%>">查看项目</a>
					</form>
				</div>
				<%
					}
				%>
				<%
					String sql2 = "select * from T_WITNESSINFO where is_delete=0 and user_id='"
								+ user.getUser_id() + "'";
						Statement stm2 = conn.createStatement();
						ResultSet rs2 = stm2.executeQuery(sql2);
						while (rs2.next()) {
				%><div class="detail">
					<img class="user-info"
						src="<%=rs2.getString("witness_image").split(" ")[0]%>"
						alt="Generic placeholder image">
					<div class="user-info">
						<div class="bigger"><%=rs2.getString("witness_title")%></div>
						<div class="time"><%=MircoLoveDao.getTime(rs2
							.getString("witness_open_date"))%></div>
						<div class="time">分享见证</div>
					</div>
					<form class="form-horizontal publishForm user-info"
						name="publishForm" method="post" id="publishForm"
						action="DeleteMircoloveServlet" onSubmit="return check1()">
						<input type="hidden" name="mircolove_id"
							value="<%=rs2.getString("witness_id")%>"> <input
							class="btn btn-primary btn-lg large btn-delete" type="submit"
							value="删除"><a
							class="btn btn-primary btn-lg large btn-delete"
							href="showShareProjectDetail.jsp?witness_id=<%=rs2.getString("witness_id")%>">查看项目</a>
					</form>
				</div>
				<%
					}
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						DataBaseUtil.closeConnection(conn);
					}
				%>
				<br> <br>
			</div>
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
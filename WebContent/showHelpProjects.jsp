<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.text.*" import="java.sql.ResultSet.*"
	import="com.DataBase.*" import="java.sql.*" import="com.login.*"
	import="com.mircolove.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微助力-助力儿童</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all">
<link href="css/style1.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/head.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/doHelp.css" rel="stylesheet" type="text/css" media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript" src="js/select.js"></script>
</head>
<body>
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
	<%
		MircoLoveDao mircoLoveDao = new MircoLoveDao();
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from T_MIRCOLOVE_CHILDREN where is_delete=0 and mircolove_verify_state=3";
		ResultSet rs = null;
		int page1;
		String integer = request.getParameter("page");
		if (integer == null) {
			integer = "1";
		}
		try {
			page1 = Integer.parseInt(integer);
		} catch (NumberFormatException e) {
			page1 = 1;
		}
		long count = mircoLoveDao.listCount();
		int list = (int) count / 6;
		try {
			Statement stm = conn.createStatement(
					ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stm.executeQuery(sql);
	%>
	<!--banner start here-->
	<div class="banner">
		<div class="container">
			<div class="box"></div>
			<div class="top-nav">
				<div class="menu">
					<ul id="nav">
						<li><a href="showBetterProjects.jsp">精选项目</a></li>
						<li><a href="showHelpProjects.jsp" class="active">助力儿童</a></li>
						<li><a href="showDonationProjects.jsp">微捐赠</a></li>
						<li><a href="showShareProjects.jsp">分享见证</a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-----------------content-box-1-------------------->
		<div class="container">
			<div class="content-box boxstyle-1 box-1">
				<div class="zerogrid">
					<div class="row wrap-box">
						<!--Start Box-->
						<%
							int num;//用来存储最后一页的项目个数
								if (page1 <= (list + 1)) {
									if ((count % 6) < 6 && page1 == (list + 1))
										num = ((int) count % 6);
									else
										num = 6;
									for (int i = 1; i <= num; i++) {
										//如果要显示第page1页，那么游标应该移动到的position的值是：
										int position = (page1 - 1) * 6 + i;
										//设置游标的位置
										rs.absolute(position);
						%>
						<%
							String images = rs.getString("mircolove_list_image");
										String image = images.split(" ")[0];
						%>
						<div class="col-1-3">
							<div class="wrap-col">
								<div class="post">
									<a
										href="showProjectDetail.jsp?mircolove_id=<%=rs.getString("mircolove_id")%>"
										class="mask"> <img src="<%=image%>"
										alt="<%=rs.getString("mircolove_list_title")%>">
										<div class="upload">
											<div class="mypeople">
												<img class="img-circle"
													src="<%=userDao.getUser(rs.getString("user_id"))
								.getHead_portrait()%>"
													alt="Generic placeholder image" width="70" height="70">
												<p>
													<%=userDao.getUser(rs.getString("user_id"))
								.getPet_name()%><span><%=MircoLoveDao.getTime(rs
								.getString("mircolove_open_date"))%> </span>
												</p>
											</div>
											<h4><%=rs.getString("mircolove_list_title")%></h4>
											<p>
												筹款目标....................................................<%=rs.getDouble("mircolove_target_amount")%>元
											</p>
											<p>
												已筹金额....................................................<%=rs.getDouble("mircolove_raise_amount")%>元
											</p>
											<p>
												已有<strong><%=rs.getString("mircolove_list_support_time")%></strong>人支持
											</p>
										</div>
									</a>
								</div>
							</div>
						</div>
						<%
							}
								}
							} catch (SQLException e) {
								e.printStackTrace();
							} finally {
								DataBaseUtil.closeConnection(conn);
							}
						%>
					</div>
				</div>
				<!--start-blog-pagenate-->
				<div class="blog-pagenat">
					<ul>
						<li><a class="frist" href="showHelpProjects.jsp?page=1">«</a></li>
						<%
							if (page1 > 1) {
						%>
						<li><a href="showHelpProjects.jsp?page=<%=(page1 - 1)%>">‹</a></li>
						<%
							}
						%>
						<%
							if ((int) count % 6 != 0)
								list++;
							if ((list - page1) <= 6) {
								for (int i = page1; i <= list; i++) {
									if (i == page1) {
						%>
						<li><a href="showHelpProjects.jsp?page=<%=i%>" class="active"><%=i%></a></li>
						<%
							} else {
						%>
						<li><a href="showHelpProjects.jsp?page=<%=i%>"><%=i%></a></li>
						<%
							}
								}
							} else {
								for (int i = page1; i <= page1 + 6; i++) {
									if (i == page1) {
						%>
						<li><a href="showHelpProjects.jsp?page=<%=i%>" class="active"><%=i%></a></li>
						<%
							} else {
						%>
						<li><a href="showHelpProjects.jsp?page=<%=i%>"><%=i%></a></li>
						<%
							}
								}
							}
							if (page1 < list) {
						%>
						<li><a href="showHelpProjects.jsp?page=<%=(page1 + 1)%>">›</a></li>

						<%
							}
						%><li><a class="last"
							href="showHelpProjects.jsp?page=<%=list%>">»</a></li>
					</ul>
				</div>

				<!--//End-blog-pagenate-->
				<div class="box"></div>
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
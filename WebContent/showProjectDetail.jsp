<%@ page language="java" contentType="text/html; charset=utf-8"
	import="java.text.*" import="java.sql.ResultSet.*"
	import="com.DataBase.*" import="java.sql.*" import="com.login.*"
	import="com.mircolove.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String mircolove_id = request.getParameter("mircolove_id");
	MircoLoveDao mircoLoveDao = new MircoLoveDao();
	MircoLove mircoLove = new MircoLove();
	mircoLove = mircoLoveDao.getMircoLove(mircolove_id);
%>
<title><%=mircoLove.getMircolove_list_title()%></title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all">
<link href="css/style1.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/head.css" rel="stylesheet" type="text/css" media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/js.js"></script>
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
	<%
		if (user == null) {
			response.sendRedirect("login.jsp");
	%>

	<%
		} else {
	%>
	<%
		DoMircoLoveDao doMircoLoveDao = new DoMircoLoveDao();
			Connection conn = DataBaseUtil.getConnection();
			String sql = "select * from T_DO_MIRCOLOVE_CHILDREN where mircolove_id="
					+ "'" + mircolove_id + "'";
			ResultSet rs = null;
			ResultSet rsContent = null;
			int mircoloveCount = (int) doMircoLoveDao
					.listNowCount(mircolove_id);
	%>
	<!--header-->
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
				<h2><%=mircoLove.getMircolove_list_title()%></h2>
			</div>
			<div class="row">
				<div class="col-md-4">
					<div class="mydetails">
						<img class="img-circle"
							src="<%=userDao.getUser(mircoLove.getUser_id())
						.getHead_portrait()%>"
							alt="Generic placeholder image" width="70" height="70">
						<%=userDao.getUser(mircoLove.getUser_id()).getPet_name()%>
						<span><%=MircoLoveDao.getTime(mircoLove
						.getMircolove_open_date())%></span>
						<hr>
						<div class="item col-md-6">
							<strong><%=mircoLove.getMircolove_target_amount()%><small>元</small>
							</strong>
							<p>目标金额</p>
						</div>
						<div class="item col-md-6">
							<strong><%=mircoLove.getMircolove_raise_amount()%><small>元</small>
							</strong>
							<p>已筹金额</p>
						</div>
						<div class="item col-md-6">
							<strong><%=mircoloveCount%><small>人</small> </strong>
							<p>支持人数</p>
							<hr>
						</div>
						<div class="item col-md-6">
							<strong>&nbsp;<small></small>
							</strong>
							<p>&nbsp;</p>
							<hr>
						</div>
						<div class="item1">
							<img src="images/detail/d1.jpg" alt="Generic placeholder image"
								width="130" height="130">
							<div class="itemsun">
								<h3>
									扫描左侧二维码 <strong><br> <a href="javascript:show()"
										class="text-success" data-toggle="modal"
										data-target="#publish-agreement">帮助TA</a>
								</h3>
							</div>
						</div>
						
					</div>
				</div>
				<div class="col-md-8">
					<div class="myskills">
						<a href="#1F" name="1F">Ta的支持者</a>
						<div class="content-text">
							&nbsp;&nbsp;<%=mircoLove.getMircolove_list_describe()%></div>
						<%
							String[] images = mircoLove.getMircolove_list_image()
										.split(" ");
								for (int i = 0; i < images.length; i++) {
									String image = images[i];
						%>
						<img src="<%=image%>">
						<%
							}
						%>
						<hr id="1F">
						<div class="itemskills">
							<p>筹款动态</p>
							<hr>

							<%
								try {
										Statement stm = conn.createStatement(
												ResultSet.TYPE_SCROLL_SENSITIVE,
												ResultSet.CONCUR_READ_ONLY);
										Statement stm2 = conn.createStatement(
												ResultSet.TYPE_SCROLL_SENSITIVE,
												ResultSet.CONCUR_READ_ONLY);
										rs = stm.executeQuery(sql);
										//System.out.println(mircoloveCount + "0123456");
										for (int i = 1; i <= mircoloveCount; i++) {
											rs.absolute(i);
											String sqlContent = "select * from T_DO_MIRCOLOVE_CHILDREN,T_MIRCOLOVE_CHILDREN_COMMENT,T_MIRCOLOVE_CHILDREN_COMMENT_CONTENT where T_DO_MIRCOLOVE_CHILDREN.mircolove_comment_id=T_MIRCOLOVE_CHILDREN_COMMENT.mircolove_comment_id and T_MIRCOLOVE_CHILDREN_COMMENT.mircolove_comment_id=T_MIRCOLOVE_CHILDREN_COMMENT_CONTENT.mircolove_comment_id and T_DO_MIRCOLOVE_CHILDREN.mircolove_comment_id='"
													+ rs.getString("mircolove_comment_id")
													+ "' order by mircolove_comment_rank,mircolove_comment_content_rank asc"; //System.out.println(sqlContent);
											//System.out.println(sqlContent);
											rsContent = stm2.executeQuery(sqlContent);
							%>
							<div class="helper">
								<img class="img-circle"
									src="<%=userDao.getUser(rs.getString("user_id"))
								.getHead_portrait()%>"
									alt="Generic placeholder image">
								<div class="bigger"><%=userDao.getUser(rs.getString("user_id"))
								.getPet_name()%></div>
								支持了 <span><%=rs.getString("do_mircolove_amount")%></span> 元
								<div class="time"><%=MircoLoveDao.getTime(rs
								.getString("do_mircolove_time"))%></div>
								<div class="speak">
									<%
										rsContent.last();
													for (int j = 1; j <= rsContent.getRow(); j++) {
														rsContent.absolute(j);
									%>
									<%=rsContent
									.getString("mircolove_comment_content")%><Br>
									<%
										}
									%>
								</div>
								<hr>
							</div>
							<%
								}
									} catch (SQLException e) {
										e.printStackTrace();
									} finally {
										DataBaseUtil.closeConnection(conn);
									}
							%>
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
			<h3>帮助TA</h3>
		</div>
		<div class="publish-agreement" id="agreement">
			<form class="form-horizontal publishForm" name="publishForm"
				method="post" id="publishForm" action="DoMircoLoveServlet"
				onSubmit="return check1()">
				<div id="baseform" class="form-container">
					<br>
					<div class="form-group form-group-lg do-help">
						<label for="" class="col-md-1 control-label">帮助金额</label>
						<div class="col-md-9">
							<div class="input-group">

								<input type="text" pattern="[0-9]*" name="do_mircolove_amount"
									class="form-control	text_validata" maxlength="3"
									placeholder="帮助总金额" onKeyDown="onlyNum()"><input
									type="hidden" value="<%=mircoLove.getMircolove_id()%>"
									name="mircolove_id" /> <input type="hidden"
									value="<%=user.getUser_id()%>" name="from_user_id" /> <input
									type="hidden" value="<%=mircoLove.getUser_id()%>"
									name="to_user_id" />
								<%
									}
								%>
							</div>
						</div>
					</div>
					<br>
					<div class="form-group form-group-lg">
						<div class="col-md-12">
							<textarea cols="60" rows="10" name="mircolove_comment_content"
								placeholder="给小伙伴一些鼓励吧~~~（默认：加油噢！！！）"></textarea>
						</div>
					</div>
				</div>
				<div class="hidebutton" id="hidebutton">
					<input class="btn-primary" type="submit" value="帮助TA">&nbsp;&nbsp;<input
						type="button" class="btn-primary" value="返回" onClick="hide()">
				</div>
			</form>
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
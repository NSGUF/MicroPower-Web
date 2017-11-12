<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>微助力后台管理系统</title>
<link rel="stylesheet" href="assets/materialize/css/materialize.min.css"
	media="screen,projection" />
<!-- Bootstrap Styles-->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FontAwesome Styles-->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- Morris Chart Styles-->
<link href="assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
<!-- Custom Styles-->
<link href="assets/css/custom-styles.css" rel="stylesheet" />
<!-- Google Fonts-->
</head>
<body>
	<div id="wrapper">
		<nav class="navbar navbar-default top-navbar" role="navigation">
		<div class="navbar-header">
			<a class="navbar-brand waves-effect waves-dark" href="BgDonationServlet">
				<strong>后台管理系统</strong>
			</a>
			<div id="sideNav" href=""></div>
		</div>
		</nav>
		<!--/. NAV TOP  -->
		<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">
				<li><a href="#" class="waves-effect waves-dark"> <i
						class="fa fa-sitemap"></i>项目管理 <span class="fa arrow"></span>
				</a>
					<ul class="nav nav-second-level">
						<li><a href="BgHelpServlet">一对一助力儿童</a></li>
						<li><a href="BgDonationServlet">微捐赠</a></li>
						<li><a href="BgShareServlet">分享见证</a></li>
						<li><a href="bgBetter.jsp">精选项目</a></li>
					</ul></li>
				<li><a href="VolunteerServlet" class="waves-effect waves-dark"><i
						class="fa fa-dashboard"></i>志愿者管理</a></li>
				<li><a href="bgReport.jsp" class="waves-effect waves-dark"><i
						class="fa fa-bar-chart-o"></i>举报管理</a></li>
				<li><a href="beSetting.jsp" class="waves-effect waves-dark"><i
						class="fa fa-desktop"></i>系统设置管理</a></li>
			</ul>
		</div>
		</nav>
		<!-- /. NAV SIDE  -->
		<div id="page-wrapper">
			<div class="header">
				<h1 class="page-header">微捐赠管理</h1>
			</div>
			<div id="page-inner">
				<div class="row">
					<div class="col-md-12">
						<!-- Advanced Tables -->
						<div class="card">
							<div class="card-content">
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example">
										<thead>
											<tr>
												<th>标题</th>
												<th>日期</th>
												<th>详情</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${donations}" var="donation">
												<tr>
													<td>${donation.donation_title }</td>
													<td>${donation.donation_open_date }</td>
													<td><a
														href="showDonationProjectDetail.jsp?donation_id=${donation.donation_id }">点击查看</a></td>
													<td>
														<form class="left" method="post"
															action="VirifyDonationServlet">
															<input type="hidden" value=${donation.donation_id }
																name="donation" /> <input type="hidden" value="1"
																name="virify" /><input type="submit"
																class="btn btn-primary" value="通过" />
														</form>
														<form class="left" method="post"
															action="VirifyDonationServlet">
															<input type="hidden" value=${donation.donation_id }
																name="donation" /><input type="hidden" value="0"
																name="virify" /> <input type="submit"
																class="btn btn-primary" value="不通过" />
														</form>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<!--End Advanced Tables -->
					</div>
				</div>
				<!-- /. ROW  -->
			</div>
			<!-- /. ROW  -->
			<footer>
			<p>Copyright &copy; 2017.微捐赠 - 江西师范大学.</p>
			</footer>
		</div>
		<!-- /. PAGE INNER  -->
	</div>
	<!-- /. PAGE WRAPPER  -->
	<!-- jQuery Js -->
	<script src="assets/js/jquery-1.10.2.js"></script>
	<!-- Bootstrap Js -->
	<script src="assets/js/bootstrap.min.js"></script>
	<!-- Metis Menu Js -->
	<script src="assets/js/jquery.metisMenu.js"></script>
	<!-- Custom Js -->
	<script src="assets/js/custom-scripts.js"></script>
</body>
</html>
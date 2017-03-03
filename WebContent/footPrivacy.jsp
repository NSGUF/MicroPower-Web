<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.login.*" import="com.mircolove.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微助力-隐私政策</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all">
<link href="css/style1.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/footPrivacy.css" rel="stylesheet" type="text/css"
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
            <h2 class="text-center">隐私政策</h2>
            <div class="secret">
                <h5>一、隐私权政策适用范围</h5>
                <ol>
                    <li>包括轻松筹如何收集、处理、保护。</li>
                    <li>用户在登录本网站和服务器时留下的个人身份信息。</li>
                    <li>用户通过本网站和服务器与其他用户或非用户之间传送的各种资讯。</li>
                    <li>轻松筹与商业伙伴共享的其他用户或非用户的各种信息。</li>
                </ol>
                <h5>二、信息和资讯收集和使用</h5>
                <p>你提供的信息和资讯在你登记注册轻松筹帐户，或使用该帐户，或参加其他轻松筹及与之相关需要注册的服务或推广活动时，我们会要求你提供个人信息(包括但不限于你的电子邮件地址、帐户密码以及昵称等)。这些信息会以加密方式保存在安全的服务器上。我们会将从你的帐户下采集的个人信息和资讯与其他从轻松筹服务中或从第三方获得的信息和资讯进行整合，以便向你提供更好的用户体验和改善我们的服务质量。在某些服务中，我们会给予提示，由你亲自决定是否参与上 述信息和资讯的整合。</p>
                <h6>饼干(cookie)</h6>
                <p>当你访问轻松筹时，我们会向你的电脑发送一个或多个饼干(cookie)：包含有一串字符的小文件，它能够对你的浏览器进行辨识。我们通过饼干技术来记录用户的使用偏好和习惯并跟踪用户倾向(诸如用户常用的搜索方式等)，以具有针对性的改善我们的服务质量。大多数浏览器都能在默认设置的状态下接受 cookie，但是你也可以重新设置浏览器来拒绝所有cookie，或者让浏览器在是否接受cookie时进行提示。需要注意的是，如果你将浏览器设置为拒绝接受cookie，则一些轻松筹站的特色功能或服务可能会无法正常运行。我们允许那些在轻松筹站网页上发布广告的公司在用户电脑上设定或取用Cookie。</p>
                <h6>日志资讯</h6>
                <p>当你使用轻松筹的服务时，我们的主机会自动记录你的浏览器在访问网站时所发送的信息和资讯。主机日志资讯包括但不限于你的网路请求、IP地址、浏览器类型、浏览器使用的语言、请求的日期和时间，以及一个或多个可以对你的浏览器进行辨识的cookie。</p>
                <h6>用户交流</h6>
                <p>当你与轻松筹通过电子邮件或其他方式进行交流时，我们可能会记录这些交流内容用以处理你的问题以及改善我们的服务。 轻松筹仅对本隐私权政策和/或具体服务的隐私声明中允许的目的而对用户的个人信息和资讯进行处理。除上述已列明部分外，这些目的还包括：向用户提供 产品或服务，包括列明定制的内容和广告;审计、调研和分析，以维持、保护和改善轻松筹的服务;确保网站的技术运作;开发新服务;以及其他轻松筹运营所需要的目的。</p>
                <h5>三、资讯公开与共享</h5>
                <p>轻松筹不会将你的个人信息和资讯故意透露、出租或出售给任何第三方。但以下情况除外： 用户本人同意与第三方共享信息和资讯; 只有透露用户的个人信息和资讯，才能提供用户所要求的某种产品和服务; 应代表轻松筹提供产品或服务的主体的要求提供(除非我们另行通知，否则该等主体无权将相关用户个人信息和资讯用于提供产品和服务之外的其他用途) 根据法律法规或行政命令的要求提供;因外部审计需要而提供;用户违反了轻松筹服务条款或任何其他产品及服务的使用规定;经轻松筹评估，用户的帐户存在风险，需要加以保护。</p>
                <h5>四、编辑和删除个人帐户资料的权限</h5>
                <p>你有权在任何时候编辑你在轻松筹的帐户信息和资讯，你也可以填写相关申请表格，要求删除个人帐户，但是你无条件同意在你的帐户删除后，该帐户内及与该帐户相关的信息和资讯仍然保留在轻松筹档案记录中，除上述第三条规定的情况外，我们将为你保密。</p>
                <h5>五、安全保障</h5>
                <p>你的轻松筹帐户具有密码保护功能，以确保你的隐私及信息和资讯安全。</p>
                <h5>六、隐私权政策的修订</h5>
                <p>轻松筹会不时对隐私权政策进行修改。如有修改，我们会在修改后及时公告相关修改内容及新规定，以便你知悉和使用。</p>
                <h5>七、问题与建议</h5>
                <p>如果你有任何问题和建议，请随时联系我们。</p>
                <p>
                    <br>
                </p>
                <p>
                    <br>
                </p>
            </div>
        </div>
        <!-----------------content-box-1-------------------->
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
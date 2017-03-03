<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.login.*" import="com.mircolove.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微助力-加入我们</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all">
<link href="css/style1.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/footJoin.css" rel="stylesheet" type="text/css"
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
	<div class="banner">
        <div class="container">
            <div class="box">
            </div>
            <h2 class="text-center">加入我们</h2>
            <div class="join">
                <h3>
                    <span>别人公司的办公室</span>
                </h3>
                <p>在帝都的繁华盛世坐拥数千平米，大气敞亮</p>
                <h3>
                    <span>别人公司的考勤</span>
                </h3>
                <p>上下班从来不用排队按指纹打卡，全凭自觉</p>
                <h3>
                    <span>别人公司的会议室</span>
                </h3>
                <p>需COS外星人乘宇宙飞船入内，炫酷炸天</p>
                <h3>
                    <span>别人公司的加班</span>
                </h3>
                <p>睡袋、帐篷、脚垫甚至懒人沙发，一应俱全</p>
                <h3>
                    <span>别人公司的团队讨论</span>
                </h3>
                <p>
                    <strong>
                        <span>有任何意见你就直说，只要一言不合，直接飙车！</span>
                    </strong>
                </p>
                <p>高兴的时候，下班打开冰箱来杯香槟～</p>
                <p>零食咖啡饮料水果，瘦子可以可劲儿吃～</p>
                <p>
                    <strong>水桶腰保护协会也有24小时热水供应～</strong>
                </p>
                <p>如果喜欢Cosplay，我们还提供豪华换衣间，上班下班穿啥都随意~</p>
                <p>同事们除了正直、伟岸、美腻、英俊，还内外兼修</p>
                <p>上能逗比、杂技说相声</p>
                <p>下能文艺、弹钢琴唱小曲</p>
                <p>还有轻松筹独有的轻松企业文化</p>
                <h3>
                    <strong>
                        <span>都号召着大家来一场说污就污的征途！</span>
                    </strong>
                </h3>
                <p>
                    <br>
                </p>
                <p>
                    我们正是你心中那个
			<span>
                <strong>“别人家的公司”</strong>
            </span>，都这样牛逼了，就问你敢不敢来？！如果说我们对你还有任何的非分之想，只希望你能够把求职的邮件精准的发到这个邮箱hr@qingsongchou.com，然后用这个格式的求职邮件标题：
                </p>
                <p>
                    <strong>［应聘职位］－［姓名］－［电话号码］－［特殊说明］</strong>
                </p>
                <h4><strong>特殊说明，包括且不限于“长得帅”或者“认证老司机”，请大家随意发挥，不要控制，谢谢。</strong></h4>
                <p>
                    <br>
                </p>
                <table width="100%">
                    <tbody>
                        <tr>
                            <td>紧急招聘！</td>
                            <td>岗位要求</td>
                        </tr>
                        <tr>
                            <td>
                                <p>Web前端开发工程师 20-30k</p>
                            </td>
                            <td>
                                <p>* 有 3 年以上的前端领域开发经验，能独立完成前端开发工作，要有案例；</p>
                                <p>* 熟练使用各种 Web 前端技术，包括(X)HTML5、CSS3、JavaScript，有移动端项目经验（精通 Bootstrap 框架最好，JS 偏服务端能力要强）；</p>
                                <p>* 熟悉 PHP ，并有一定的实战经验；</p>
                                <p>* 注重设计模式、模块化开发，代码架构设计，有前端改进与优化经验优先；</p>
                                <p>* 具备自我管理能力和创业精神，能够承担一定的工作压力；</p>
                                <p>* 发送简历请告知Github或作品链接</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>PHP高级开发工程师 20-30k</p>
                            </td>
                            <td>
                                <p>* 熟悉PHP开发工作，熟练掌握LNMP开发，并具备良好的编程风格；</p>
                                <p>* 熟悉MYSQL，具备MYSQL优化经验；</p>
                                <p>* 熟悉Memcache、Redis，了解各自的优缺点以及使用场景；</p>
                                <p>* 熟悉Web开发中各类缓存设计、站点优化方案；</p>
                                <p>* 有快速学习能力和动手能力，良好的团队精神与沟通能力；</p>
                                <p>* 工作认真，责任心强，良好的沟通技能、团队合作能力。</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <p>交互设计师 15到25k</p>
                            </td>
                            <td>
                                <p>* 负责轻松筹APP的交互设计，并对产品最终的用户体验负责；</p>
                                <p>* 参与用户研究及产品需求制定的整个过程，提出设计需求及用户体验目标，能给出有效的交互解决方案，并推动方案的落地；</p>
                                <p>* 设计规范的输出，并推动规范在整个项目团队执行，让整个体验更加人性化。</p>
                                <p>任职要求：</p>
                                <p>* 大专及以上学历，工业设计、人机交互、视觉传达、心理学、计算机等相关专业；</p>
                                <p>* 具备3年以上用户体验工作经验，有网页或手持终端成功案例更佳；</p>
                                <p>* 熟悉网页、iOS和Android平台的设计规范， 熟悉用户研究常见的方法，有一定视觉设计基础；</p>
                                <p>* 工作积极主动，有持续的热情和责任心，对设计品质有执着追求，善于团队合作，具备良好的沟通协调能力。</p>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <p>
                    <br>
                </p>
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
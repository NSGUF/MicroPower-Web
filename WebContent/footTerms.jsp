<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="com.login.*" import="com.mircolove.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微助力-关于微助力</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all">
<link href="css/style1.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/footTerms.css" rel="stylesheet" type="text/css"
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
            <h2 class="text-center">使用条款</h2>
            <div class="terms">
                <p>一、轻松筹服务简介与用户要求</p>
                <p>
                    <br>
                </p>
                <p>1、用户承诺并保证自身是具有完全民事行为能力和完全民事权利能力的自然人、法人或其它组织。</p>
                <p>2、用户保证所填写的用户信息是真实、准确、完整、及时的，无任何引人误解或者虚假的陈述，保证轻松筹可以通过用户所填写的联系方式与用户取得联系。</p>
                <p>3、用户应根据轻松筹对相关服务的要求及时提供相应的身份证明等资料，否则轻松筹有权拒绝向该用户提供相关服务。</p>
                <p>4、用户承诺将及时更新其用户信息以维持该等信息的有效、完整、真实、准确。</p>
                <p>5、如果用户提供的资料或信息包含有不正确、不真实的信息，轻松筹保留取消用户会员资格并随时结束为该用户提供服务的权利。</p>
                <p>6、一旦成为轻松筹会员，轻松筹通过电子邮件的方式与用户保持联络及沟通，用户在此同意轻松筹通过电子邮件方式向其发送包括会员信息和服务信息在内的相关商业及非商业联络信息，而不视上述邮件为垃圾邮件。</p>
                <p>
                    <br>
                </p>
                <p>二、本协议的构成及效力</p>
                <p>
                    <br>
                </p>
                <p>1、所有轻松筹已经发布的或将来发布的各类规则，作为本协议不可分割的一部分，与本协议具有同等的法律效力。</p>
                <p>2、轻松筹有权根据需要适时修订本协议及各类规则，并在网站上公示。变更后的协议和规则一经在轻松筹网站上公示，即发生法律效力。</p>
                <p>3、在使用轻松筹提供的服务之前，请务必认真阅读本协议的全部内容。如您对本协议有任何疑问，应向轻松筹咨询。本协议生效后，您不应以未阅读或不接受本协议的内容为由，主张本协议无效或要求撤销本协议。</p>
                <p>4、您应该按照本协议约定行使权利并履行义务。如您不能接受本协议的约定，包括但不限于不能接受修订后的协议及各类规则，则您应立即停止使用轻松筹针对项目发起人提供的服务。如您继续使用轻松筹针对项目发起人提供的服务，则表示您同意并接受本协议及各类规则的约束。</p>
                <p>
                    <br>
                </p>
                <p>三、项目发起人的资格</p>
                <p>
                    <br>
                </p>
                <p>1、作为项目发起人，您应为轻松筹的注册用户，完全理解并接受本协议。</p>
                <p>2、作为项目发起人的单位应为依法成立并登记备案的企业法人或其他组织；作为项目发起人的个人，应为年满18周岁并具有完全民事行为能力和民事权利能力的自然人，如项目发起人未满18周岁，应由其监护人代为履行本协议权利和义务。</p>
                <p>3、您应按照轻松筹的要求，进行必要的身份认证和资质认证，包括但不限于身份证、护照、学历证明等的认证。</p>
                <p>4、用户应向轻松筹提供邮箱地址及其他联络方式，并保证联络方式的真实性、完整性、及时性。联络方式不真实、不完整、变更后未及时通知以及用户用于接受轻松筹邮件的电子邮箱安全性、稳定性不佳而导致的一切后果，用户应自行承担责任。</p>
                <p>5、用户保证其使用轻松筹服务时将遵守国家、地方法律法规、遵从行业惯例和社会公共道德，不会利用轻松筹提供的服务进行存储、发布、传播如下信息和内容：违反国家法律法规政策的任何内容（信息）；违反国家规定的政治宣传和/或新闻信息；涉及国家秘密和/或国家安全的信息；封建迷信和/或淫秽、色情、下流的信息或教唆犯罪的信息；博彩有奖、赌博游戏；违反国家民族和宗教政策的信息；防碍互联网运行安全的信息；侵害他人合法权益的信息和/或其他有损于社会秩序、社会治安、公共道德的信息或内容。用户同时承诺不得为他人发布上述不符合国家规定和/或本服务条款约定的信息内容提供任何便利，包括但不限于设置URL、BANNER链接等。用户承认轻松筹有权在用户违反上述约定时有权终止向用户提供服务并不予退还任何款项，因用户上述行为给轻松筹造成损失的，用户应予赔偿。</p>
                <p>6、您应妥善保管在轻松筹的用户名和密码，凡使用您的用户名和密码登陆轻松筹进行的一切操作，均视为您本人的行为，一切责任由您本人承担。</p>
                <p>
                    <br>
                </p>
                <p>四、您发起的众筹项目应符合以下要求</p>
                <p>
                    <br>
                </p>
                <p>1、您在轻松筹上发起的项目，应明确具体的开始时间和结束时间。截止项目结束时间，如项目众筹金额低于预定众筹金额，则项目众筹失败；如项目众筹金额到达预定众筹金额，则项目众筹成功。</p>
                <p>2、你不应抄袭、盗用他人的成果发起众筹项目。</p>
                <p>3、您发起的项目不得包含非法、色情、淫秽、暴力等内容，不得含有攻击性、侮辱性言论，不得含有违反国家法律法规、政策的内容及其他轻松筹认为不适宜的内容。</p>
                <p>4、您在轻松筹发起的项目，不得在国内外同类众筹网站同时或再次发起。</p>
                <p>5、您在轻松筹上发起的项目不得涉及种族主义、宗教极端主义、恐怖主义等内容。</p>
                <p>6、您发起的项目应内容完整、合理，具有可行性。</p>
                <p>7、您发起的项目不应与第三方存在任何权利纠纷，否则因此导致的一切损失（包括轻松筹因此被第三方权利追索而遭受的一切损失）由您本人承担，与轻松筹无关。</p>
                <p>
                    <br>
                </p>
                <p>五、项目筹集资金</p>
                <p>
                    <br>
                </p>
                <p>1、您在发起项目时，应明确项目众筹成功后的股东开会时间。</p>
                <p>2、您承诺，如项目众筹成功，及时联系股东开会并注册有限责任公司或有限合伙企业；如项目众筹失败，同意轻松筹将众筹款项及时退还项目投资人，并由您就项目众筹失败的原因等对项目投资人作出解释。</p>
                <p>3、如您与项目投资人在经营过程中发生纠纷，一切责任由您本人承担，如因此给轻松筹造成名誉损失，您应恢复本平台名誉。</p>
                <p>4、轻松筹只会将款项拨给您与投资者成立的公司账户。</p>
                <p>5、轻松筹享有在用户违反国家、地方法律法规规定或违反本注册条款的情况下终止为用户提供服务并停用用户帐号的权利，并且在任何情况下，轻松筹对由此产生的任何间接、偶然、特殊及继起的损害不负责任。</p>
                <p>6、轻松筹应在有限合伙企业（有限责任公司）取得工商行政管理机关发放的营业执照之日起10日内将投资款汇入有限合伙企业（有限责任公司）所开设的银行账户中，有限合伙企业不成立或经投资者决议过半数不同意继续投资具体的创业项目的，应将投资款原额返还给投资人注册用户。</p>
                <p>7、用户同意轻松筹在项目筹资成功后，收取项目总资金的2%做为平台服务费。</p>
                <p>
                    <br>
                </p>
                <p>六、拒绝担保条款</p>
                <p>
                    <br>
                </p>
                <p>用户对网络服务的使用承担风险。轻松筹对此不作任何类型的担保，无论明确或隐含的。但轻松筹不对商业性的隐含担保、特定目的和不违反规定的适当担保作限制。轻松筹无法保障自身服务内容满足用户的要求，也无法担保服务不会中断，对服务的及时性，安全性均不作保证。轻松筹对在轻松筹上得到的任何信息服务或交易进程均不作担保。</p>
                <p>
                    <br>
                </p>
                <p>七、轻松筹责任豁免</p>
                <p>
                    <br>
                </p>
                <p>1、轻松筹对任何直接、间接、偶然、特殊及继起的损害不负责任，这些损害可能来自：不正当使用网络服务，在网上购买商品或进行同类型服务，在网上进行交易，非法使用网络服务或用户传送的信息有所变动。这些行为都有可能会导致轻松筹的形象受损，所以轻松筹事先提出这种损害的可能性。用户同意该等责任均由其自行承担（包括但不限于疏忽责任）。</p>
                <p>2、为了网站的正常运行，轻松筹需要定期或不定期地对网站进行停机维护，因此类情况造成的正常服务中断，请用户理解，轻松筹将尽力避免服务中断或无法正常使用，导致用户在轻松筹交易无法完成或丢失有关信息、记录等，轻松筹不承担责任，但是轻松筹会尽合理的可能协助处理善后事宜，并努力使用户免受经济损失。</p>
                <p>
                    <br>
                </p>
                <p>八、协议终止</p>
                <p>
                    <br>
                </p>
                <p>1、以下任一情形出现，本协议即终止</p>
                <p>（1）您发起的项目违反了国家法律法规、政策或轻松筹的平台使用规则或本协议约定，轻松筹将立即停止为您提供服务；</p>
                <p>（2）您本人不同意接受本协议约定（含轻松筹发布的各类规则），并停止使用轻松筹针对项目发起人提供的服务；</p>
                <p>（3）您不符合本协议约定的项目发起人应具备的资格；</p>
                <p>（4）其他原因导致本协议终止的。</p>
                <p>2、本协议终止后，不影响您因在本协议终止前的行为应承担的义务和责任。</p>
                <p>
                    <br>
                </p>
                <p>九、附则</p>
                <p>
                    <br>
                </p>
                <p>用户保证已经完全阅读并理解了上述会员注册条款并自愿正式进入轻松筹会员在线注册程序，接受上述所有条款的约束。</p>
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
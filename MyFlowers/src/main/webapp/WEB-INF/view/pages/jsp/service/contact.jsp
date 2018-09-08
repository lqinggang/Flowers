<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="鲜花,销售">
<meta http-equiv="description" content="鲜花销售平台">
<link href="static/layui/css/layui.css" rel="stylesheet" media="all">
<link href="static/pages/css/service/service.css" rel="stylesheet"
	type="text/css" />
<link href="static/pages/css/common/page.css" rel="stylesheet"
	type="text/css" />

<style type="text/css">
.layui-text {
	font-size: 16px;
}

.layui-text h3 {
	color: #ff6a00;
}

body {
	height: 800px;
}
</style>
<script type="text/javascript">
	var mainbody = parent.document.getElementById("mainbody");
	mainbody.height = 1000;
</script>
</head>

<body>
	<div id="contact" class="service">
		<div class="logo">
			<img alt="logo" src="static/pages/images/page/logo.png">
			<div class="separate"></div>
		</div>

		<div class="title" style="margin-bottom:5%;">
			<h2>CONTACT US</h2>
			<h3>联系我们</h3>
		</div>
		<script src="static/layui/layui.js" type="text/javascript"></script>
		<div class="bar">
			<div class="descriptioncontent">
				<script src="static/layui/layui.js" type="text/javascript"></script>
				<ul class="layui-timeline">
					<li class="layui-timeline-item"><i
						class="layui-icon layui-timeline-axis">&#xe63f;</i>
						<div class="layui-timeline-content layui-text">
							<h3 class="layui-timeline-title">客服热线</h3>
							<p>
								免长途费，人工客服时间：xx:xx-xx:xx <br>客服热线：400-xxx-xxxx <br>
								公司传真：0775-xxxxxxxx
							</p>
						</div></li>
					<li class="layui-timeline-item"><i
						class="layui-icon layui-timeline-axis">&#xe63f;</i>
						<div class="layui-timeline-content layui-text">
							<h3 class="layui-timeline-title">包年/包月鲜花专线</h3>
							<p>
								联系人：xxx <br>联系电话：0775-xxxxxxxx <br>QQ:xxxxxxxxxx <br>微信：xxxxxx
							</p>
						</div></li>
					<li class="layui-timeline-item"><i
						class="layui-icon layui-timeline-axis">&#xe63f;</i>
						<div class="layui-timeline-content layui-text">
							<h3 class="layui-timeline-title">客服部</h3>
							<p>
								客服电话：400-xxx-xxxx <br>客服邮箱：xxxxxx@xxx.com <br>短信:1xxxxxxxxxx
								<br>客服工作时间：xx:xx-xx:xx
							</p>
						</div></li>
					<li class="layui-timeline-item"><i
						class="layui-icon layui-timeline-axis">&#xe63f;</i>
						<div class="layui-timeline-content layui-text">
							<h3 class="layui-timeline-title">网址</h3>
							<p>
								xxxxxx科技有限责任公司<br> 网址：https://www.xxxx.com<br> <img
									alt="logo" src="static/pages/images/page/logo.png"
									style="width:130px;height:130px;margin-left:5%;"> <br>
								公司地址：xxx市xxx区xxx路xxx号xxxx大厦xxx层
							</p>
						</div></li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>

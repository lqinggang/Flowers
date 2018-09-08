<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<head>
<title>花之苑</title>
<meta charset="utf-8">
<meta name="description" content="鲜花销售平台">
<meta name="keywords" content="鲜花">
<link href="static/layui/css/layui.css" rel="stylesheet" type="text/css" />
<link href="static/pages/css/pagetop.css" rel="stylesheet">

</head>
<body>
	<header class="header">
		<ul id="top_menu" class="layui-nav">
			<li class="layui-nav-item"><a href="login">登陆</a></li>
			<li class="layui-nav-item"><a href="registered">注册</a></li>
			<li class="layui-nav-item"><a href="">订单查询</a></li>
			<li class="layui-nav-item"><a href="javascript:;">客户服务</a>
				<dl class="layui-nav-child">
					<!-- 二级菜单 -->
					<dd>
						<a href="">帮助中心</a>
					</dd>
					<dd>
						<a href="">售后服务</a>
					</dd>
					<dd>
						<a href="">配送范围</a>
					</dd>
					<dd>
						<a href="">留言反馈</a>
					</dd>
				</dl></li>
		</ul>

		<ul class="layui-nav" id="navigation">
			<li class="layui-nav-item"><a href="javascript:;">商品导购</a>
				<dl class="layui-nav-child">
					<!-- 二级菜单 -->
					<dd>
						<a href="">生日鲜花</a>
					</dd>
					<dd>
						<a href="">婚礼鲜花</a>
					</dd>
					<dd>
						<a href="">爱情鲜花</a>
					</dd>
					<dd>
						<a href="">探病慰问</a>
					</dd>
					<dd>
						<a href="">问候长辈</a>
					</dd>
				</dl></li>
			<li class="layui-nav-item"><a href="index">首页</a></li>
			<li class="layui-nav-item"><a href="">鲜花</a></li>
			<li class="layui-nav-item"><a href="">包年/包月</a></li>
			<li class="layui-nav-item"><a href="">关于我们</a></li>
		</ul>
		<div id="top_sides">
			<img alt="Rose Only" src="static/pages/images/page/RoseOnly.jpeg">
		</div>
		<div class="layui-carousel" id="top_arousel">
			<div carousel-item id="entry">
				<div>
					<img alt="Images 1" src="static/pages/images/page/1.jpg">
				</div>
				<div>
					<img alt="Images  2" src="static/pages/images/page/2.jpg">
				</div>
				<div>
					<img alt="Images  3" src="static/pages/images/page/3.jpg">
				</div>
				<div>
					<img alt="Images  4" src="static/pages/images/page/4.jpg">
				</div>
			</div>
		</div>
	</header>
</body>
</html>
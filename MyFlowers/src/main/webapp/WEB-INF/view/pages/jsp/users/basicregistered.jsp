<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>用户注册</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="shortcut icon" href="static/pages/images/page/logo.png"
	type="image/x-icon" />
<link href="static/pages/css/users/registered.css" rel="stylesheet" />
<link rel="stylesheet" href="static/layui/css/layui.css" />
<link rel="shortcut icon" href="static/pages/images/page/logo.png"
	type="image/x-icon" />
<script type="text/javascript" src="static/layui/layui.js"></script>
<script type="text/javascript">
	layui.use('carousel', function() {
		var carousel = layui.carousel;
		//建造实例
		carousel.render({
			elem : '#carousel',
			width : 500,
			height : 500,
			arrow : 'always' //始终显示箭头
		//,anim: 'updown' //切换动画方式
		});
	});
</script>
<style type="text/css">
.layui-form .layui-form-item {
	padding: 0% 3%;
	padding-top: 1%;
}

a {
	text-decoration: none;
	color: #C0C0C0;
}

a:hover {
	color: #FF8000;
}
</style>
</head>

<body>
	<div
		style="float:left;padding-left:10%;padding-top:5%;height:500px;width:500px;">
		<div class="layui-carousel" id="carousel">
			<div carousel-item>
				<c:choose>
					<c:when test="${!empty requestScope.flowers }">
						<c:forEach items="${requestScope.flowers }" var="flower">
							<a href="mainPage/flowers/search?id=${flower.flower_id }"><img
								style="width:500px;height:500px;" alt="${flower.name }"
								title="${flower.name } -- ${flower.description }"
								src="${flower.image }"></a>
						</c:forEach>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
	<div style="float:left;width:40%;margin-left:2%;margin-top:5%;">
		<h1 style="text-align:center;font-size:50px;margin-bottom:5%;">Sign
			Up</h1>
		<form id="formregistered" class="layui-form layui-form-pane"
			action="users/users/registered" method="post" target="_top"
			style="border:1px solid #C0C0C0;">
			<div class="layui-form-item">
				<label class="layui-form-label">用户名：</label>
				<div class="layui-input-block">
					<input type="text" name="name" id="name" placeholder="请输入用户名"
						autocomplete="off" class="layui-input"
						value="${requestScope.name }">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">密码：</label>
				<div class="layui-input-block">
					<input type="password" name="password" placeholder="请输入密码"
						autocomplete="off" class="layui-input"
						value="${requestScope.password }">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">再次输入：</label>
				<div class="layui-input-block">
					<input type="password" name="password2" placeholder="请再次输入密码"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">验证码：</label>
				<div class="layui-input-block">
					<img style="height:40px;width:35%;margin-left:1px;" id="codeImg"
						alt="验证码" title="点击更换" src="ran/random"
						onclick="this.src='ran/random?'+Math.random()" style="float:left;" />
					<input type="text" name="code" placeholder="请输入验证码"
						autocomplete="off" class="layui-input"
						style="float:left;width:60%;">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block">
					<button class="layui-btn" lay-submit id="submit">立即注册</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					<span style="margin-left:20%;margin-top:20%;font-size:16px;"><a
						href="mainPage/index" target="_top">首页>></a></span>
				</div>
			</div>
		</form>
		<div id="note" style="margin-top:2%;">
			<div class="title">用户注册需知</div>
			<div class="regulations">在您注册前，请先仔细阅读以下条款：</div>
			<div class="regulations">一、遵守中华人民共和国法律法令和其他相关法规，不可发布破坏宪法和法律、法规的信息。</div>
			<div class="regulations">二、遵守公安部关于《计算机信息网络国际联网安全保护管理办法》的规定，自觉维护计算机信息网络的安全。</div>
			<div class="regulations">三、不得在网上宣扬封建迷信、淫秽、色情、暴力、赌博等不正当行为。</div>
			<!-- 	<div class="regulations">四、遵守所有使用网站服务的网络协议、规定、程序和惯例。</div> -->
		</div>
	</div>
	<c:choose>
		<c:when test="${!empty requestScope.msg }">
			<script type="text/javascript">
				layui.use('layer', function() {
					var $ = layui.$; //重点处
					var layer = layui.layer; //获得layer模块
					layer.open({
						type : 1,
						area : [ '240px', '120px' ],
						offset : 'auto',
						title : '注册出错',
						content : '${requestScope.msg}'
					});
			
				});
			</script>
		</c:when>
	</c:choose>
</body>
</html>

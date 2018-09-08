<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/base.jspf"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">

<title>管理员登录</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" type="text/css" href="static/admin/css/login.css">
<link rel="shortcut icon" href="static/pages/images/page/logo.png"
	type="image/x-icon" />
<style type="text/css">
body, td, th {
	font-family: "Source Sans Pro", sans-serif;
}

body {
	background-color: #2B2B2B;
}

.layui-layer-page .layui-layer-title {
	font-size: 16px;
	color: red;
}

.layui-layer-page .layui-layer-content {
	font-size: 20px;
	text-align: center;
	color: #C0C0C0;
	top: 20%;
}

#code:focus {
	width: 300px;
	color: #000;
}
</style>
</head>
<body>


	<div class="wrapper">

		<div class="container">
			<h1>Welcome</h1>
			<form class="form" action="mainPage/login" method="post">
				<input type="text" placeholder="Username" name="name">
				<!-- 管理员名称 -->
				<input type="password" placeholder="Password" name="password">
				<!-- 密码 -->
				<input type="text" placeholder="Verify" name="code" id="code"
					style="background: url(ran/random) no-repeat; ">
				<!-- 验证码 -->
				<button type="submit" id="login-button">Login</button>
			</form>
		</div>

		<ul class="bg-bubbles">
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>

	</div>

	<script type="text/javascript" src="static/layui/layui.js"></script>
	<script type="text/javascript" src="static/jquery/jquery-2.1.1.min.js"></script>
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
						title : 'ERROR',
						content : '${requestScope.msg}'
					});
			
				});
			</script>
		</c:when>
	</c:choose>

</body>

</html>

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
<link rel="stylesheet" href="static/admin/css/page.css" media="all">
<style type="text/css">
.layui-nav .layui-nav-item a {
	color: #6e6e6e;
}

.layui-nav .layui-nav-item a:hover {
	color: #ff8000;
}
</style>
</head>

<body>
	<div class="layui-layout layui-layout-admin kit-layout-admin">
		<div class="layui-header" style="font-size:16px;">
			<div class="layui-logo">花之苑</div>
			<ul class="layui-nav layui-layout-left kit-nav">
				<li class="layui-nav-item"><a href="admin/homepage"
					target="mainbody">主页</a></li>
			</ul>
			<ul class="layui-nav layui-layout-right kit-nav">
				<li class="layui-nav-item"><span>上次登录时间：${sessionScope.lastlogindate }</span></li>
				<li class="layui-nav-item"><a href="javascript:;"> <img
						src="static/pages/images/page/logo.png" class="layui-nav-img">
						${sessionScope.adminName }
				</a></li>
				<li class="layui-nav-item"><a href="admin/logout">注销</a></li>
			</ul>
		</div>
	</div>
</body>
</html>

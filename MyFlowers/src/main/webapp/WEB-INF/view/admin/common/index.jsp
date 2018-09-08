<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">

<title>后台管理系统</title>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link rel="stylesheet" href="static/layui/css/layui.css" media="all">
<link rel="stylesheet" href="static/admin/css/app.css" media="all">
<link rel="stylesheet" href="static/admin/css/themes/light.css"
	media="all">
<script src="static/pages/js/common/jquery-1.7.2.js"></script>
<script src="static/layui/layui.js"></script>
<link rel="shortcut icon" href="static/pages/images/page/logo.png"
	type="image/x-icon" />
</head>

<body>
<body class="kit-theme">
	<%
		if (request.getAttribute("page") == null) {
			request.setAttribute("page", "homepage");
		}
	%>
	<div class="layui-layout layui-layout-admin kit-layout-admin">
		<!-- 顶部固定区域 -->
		<div id="header">
			<jsp:include page="/WEB-INF/view/admin/common/top.jsp"></jsp:include>
		</div>
		<!-- 左侧导航栏 -->
		<div>
			<jsp:include page="/WEB-INF/view/admin/common/left.jsp"></jsp:include>
		</div>
		<!-- 主体 -->
		<div class="layui-body" id="container" style="top:50px;">
			<iframe style="frameborder:0;border:0;width:100%;height:100%;"
				id="mainbody" name="mainbody" src="admin/${requestScope.page }"></iframe>
		</div>
		<!-- 底部固定区域 -->
		<div class="layui-footer" style="text-align:center;">
			<span> 2018 &copy; <a href="">www.xxxxxxx.com</a></span>
		</div>
	</div>
	<c:choose>
		<c:when test="${!empty requestScope.msg }">
			<!-- <script src="static/pages/js/common/jquery-1.7.2.js"></script>
			<script src="static/layui/layui.js" type="text/javascript"></script> -->
			<script type="text/javascript">
				layui.use('layer', function() {
					var $ = layui.$; //重点处
					var layer = layui.layer; //获得layer模块
					layer.open({
						type : 1,
						area : [ '240px', '120px' ],
						offset : 'auto',
						title : '${requestScope.title}',
						content : '${requestScope.msg}'
					});
			
				});
			</script>
		</c:when>
	</c:choose>
</body>
</html>

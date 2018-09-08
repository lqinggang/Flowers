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
<link rel="stylesheet" href="static/layui/css/layui.css" />
<link rel="stylesheet" type="text/css"
	href="static/font-awesome/css/font-awesome.css">
<style type="text/css">
.layui-form-item {
	margin: 5%;
}

.layui-form-item .layui-form-label {
	background-color: #d4d4d4;
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
</style>
</head>

<body>
	<div style="width:60%;margin-left:20%;margin-top:10%;">
		<div
			style="color:red;margin-bottom:5%;font-size:18px;font-weight:bold;">
			<i class="fa fa-exclamation-circle"></i><span style="margin-left:1%;">在修改前请确认您是否真的需要修改密码,密码的修改不可逆，请谨慎操作!</span>
		</div>
		<div>
			<form id="formpassword" class="layui-form layui-form-pane"
				action="admin/passwordupdate" method="post" target="mainbody"
				style="border:1px solid #C0C0C0;">
				<div class="layui-form-item">

					<label class="layui-form-label">旧密码：</label>
					<div class="layui-input-block">
						<input type="password" name="password" placeholder="请输入密码"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">新密码：</label>
					<div class="layui-input-block">
						<input type="password" name="password2" placeholder="请输入新密码"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">再次输入：</label>
					<div class="layui-input-block">
						<input type="password" name="password3" placeholder="请再次输入新密码"
							autocomplete="off" class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block" style="margin-left:40%;">
						<button class="layui-btn" lay-submit id="submit">确认修改</button>
					</div>
				</div>
			</form>
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

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
<title>管理员资料</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="keywords" content="鲜花,销售,花之苑">
<meta name="description" content="后台管理">
<link rel="stylesheet" href="static/layui/css/layui.css" />
<link rel="stylesheet" type="text/css"
	href="static/font-awesome/css/font-awesome.css">
<script src="static/pages/js/common/jquery-1.7.2.js"></script>
<script src="static/layui/layui.js" type="text/javascript"></script>
<script type="text/javascript" src="static/pages/assets/data.js"></script>
<script type="text/javascript" src="static/pages/assets/province.js"></script>

<style type="text/css">
.layui-form-item {
	margin: 5%;
}

.layui-form-item .layui-form-label {
	background-color: #d4d4d4;
}

.layui-form-item .layui-input-inline {
	width: 26.5%;
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
	<div style="margin-top:5%;margin-left:15%;width:60%;">
		<div
			style="color:red;margin-bottom:5%;font-size:18px;font-weight:bold;">
			<i class="fa fa-exclamation-circle"></i><span style="margin-left:1%;">管理员信息修改，请谨慎操作!</span>
		</div>
		<form id="" class="layui-form layui-form-pane"
			action="admin/infoupdate" method="post" target="mainbody"
			style="border:1px solid #C0C0C0;">
			<div class="layui-form-item">
				<label class="layui-form-label">用户名：</label>
				<div class="layui-input-block">
					<input type="text" name="name" id="name"
						style="background-color:#e7e7e7;color:#C0C0C0;"
						disabled="disabled" class="layui-input"
						value="${requestScope.name }">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">联系电话：</label>
				<div class="layui-input-block">
					<input type="text" name="telephone" placeholder="请输入电话号码"
						autocomplete="off" class="layui-input"
						value="${requestScope.telephone }">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">Email：</label>
				<div class="layui-input-block">
					<input type="text" name="email" placeholder="请输入Email"
						autocomplete="off" class="layui-input"
						value="${requestScope.email }">
				</div>
			</div>
			<script type="text/javascript">
					var defaults = {
						s1 : 'provid',
						s2 : 'cityid',
						s3 : 'areaid',
						v1 : ${requestScope.prov},
						v2 : ${requestScope.city},
						v3 : ${requestScope.area}
					};
				</script>

			<div class="layui-form-item">
				<label class="layui-form-label">联系地址</label>
				<div class="layui-input-inline">
					<select name="provid" id="provid" lay-filter="provid">
						<option value="">请选择省</option>
					</select>
				</div>
				<div class="layui-input-inline">
					<select name="cityid" id="cityid" lay-filter="cityid">
						<option value="">请选择市</option>
					</select>
				</div>
				<div class="layui-input-inline">
					<select name="areaid" id="areaid" lay-filter="areaid">
						<option value="">请选择县/区</option>
					</select>
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">详细地址：</label>
				<div class="layui-input-block">
					<input type="text" name="address" placeholder="请输入详细地址"
						autocomplete="off" class="layui-input"
						value="${requestScope.address }">
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block" style="margin-left:45%;">
					<button class="layui-btn" lay-submit id="submit">确认修改</button>
				</div>
			</div>
		</form>
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
						title : '${requestScope.title}',
						content : '${requestScope.msg}'
					});
			
				});
			</script>
		</c:when>
	</c:choose>
</body>
</html>

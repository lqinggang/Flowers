<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/base.jspf"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>新增订单</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" href="static/layui/css/layui.css" />
<script src="static/layui/layui.js" type="text/javascript"></script>
<style type="text/css">
.layui-form-item {
	margin: 5% 1%;
}
</style>
</head>

<body>
	<div
		style="margin-top:5% ;margin-left:20%;width:60%;border:1px solid #C0C0C0;">
		<form id="orderform" class="layui-form layui-form-pane"
			action="admin/addorder" method="post" style="margin-left:5%;">
			<div class="layui-form-item">
				<label class="layui-form-label">鲜花编号：</label>
				<div class="layui-input-block">
					<input type="text" name="id" id="id" placeholder="请输入鲜花编号"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">鲜花数量：</label>
				<div class="layui-input-block">
					<input type="text" name="amount" id="amount" placeholder="请输入鲜花数量"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">收件人：</label>
				<div class="layui-input-block">
					<input type="text" name="name" id="name" placeholder="请输入收件人名字"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">联系方式：</label>
				<div class="layui-input-block">
					<input type="text" name="contact" id="contact"
						placeholder="请输入收件人联系方式" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">收件人地址：</label>
				<div class="layui-input-block">
					<input type="text" name="address" id="address"
						placeholder="请输入收件人地址" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">备注：</label>
				<div class="layui-input-block">
					<input type="text" name="note" id="note" placeholder="备注信息"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item" style="margin-left:25%;">
				<div class="layui-input-block button">
					<button class="layui-btn" lay-submit id="submit">添加订单</button>
				</div>
			</div>
		</form>
	</div>
	<c:choose>
		<c:when test="${!empty requestScope.msg }">
			<script type="text/javascript">
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.tips('${requestScope.msg}', '#${requestScope.tip}');
				})
			</script>
		</c:when>
	</c:choose>
</body>
</html>

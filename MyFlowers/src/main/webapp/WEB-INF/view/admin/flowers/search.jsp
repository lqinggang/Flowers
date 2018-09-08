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
<style type="text/css">
.layui-form-pane .layui-input-block {
	margin-left: 0;
}
</style>

</head>
<body>
	<div style="margin:5px;">
		<form class="layui-form layui-form-pane"
			action="admin/flowers/searchpage" method="post">
			<div class="layui-form-item">
				<label class="layui-form-label">鲜花编号：</label>
				<div class="layui-input-block"
					style="float:left;width:25%;margin-right:3%;">
					<input type="text" name="flowerid" id="flowerid"
						class="layui-input" placeholder="请输入鲜花编号"
						value="${requestScope.flowerid }">
				</div>
				<button class="layui-btn" lay-submit id="submit" style="float:left;">搜索</button>
			</div>
		</form>
	</div>
	<div style="clear:both;"></div>
</body>
</html>

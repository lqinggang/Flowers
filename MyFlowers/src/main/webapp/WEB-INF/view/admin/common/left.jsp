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
<link rel="stylesheet" href="static/layui/css/layui.css" media="all">
<link rel="stylesheet" type="text/css"
	href="static/font-awesome/css/font-awesome.css">
<link rel="stylesheet" href="static/admin/css/app.css" media="all">
<link rel="stylesheet" href="static/admin/css/themes/light.css"
	media="all">

<style type="text/css">
.space {
	margin-left: 5%;
}
</style>

</head>
<body>
	<script type="text/javascript">
		layui.use('element', function() {
			var element = layui.element;
		});
	</script>
	<div class="layui-side layui-bg-black kit-side">
		<div class="layui-side-scroll">
			<ul class="layui-nav layui-nav-tree">
				<li class="layui-nav-item layui-nav-itemed"><a
					href="javascript:;"><i class="fa fa-user-circle-o"></i><span
						class="space">我的面板</span></a>
					<dl class="layui-nav-child">
						<dd>
							<a href="admin/info" target="mainbody"><i class="fa fa-user"></i><span
								class="space">个人信息</span></a>
						</dd>
						<dd>
							<a href="admin/password" target="mainbody"><i
								class="fa fa-lock"></i><span class="space">密码修改</span></a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="javascript:;"><i
						class="fa fa-user-circle"></i><span class="space">用户管理</span></a>
					<dl class="layui-nav-child">
						<dd>
							<a href="admin/usersinfoPage" target="mainbody"><i
								class="fa fa-info"></i><span class="space">用户信息</span></a>
						</dd>
						<!-- 						<dd>
							<a href="javascript:;" target="mainbody"><i
								class="fa fa-gear"></i><span class="space">联系方式管理</span></a>
						</dd> -->
						<!-- 					<dd>
							<a href="admin/userspasswordPage" target="mainbody"><i
								class="fa fa-lock"></i><span class="space">用户密码管理</span></a>
						</dd> -->
						<dd>
							<a href="admin/adduserPage" target="mainbody"><i
								class="fa fa-plus"></i><span class="space">新增用户</span></a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="javascript:;"><i
						class="fa fa-heart"></i><span class="space">鲜花管理</span></a>
					<dl class="layui-nav-child">
						<dd>
							<a href="admin/flowersinfoPage" target="mainbody"><i
								class="fa fa-heart"></i><span class="space">鲜花信息管理</span></a>
						</dd>
						<dd>
							<a href="admin/evaluationPage" target="mainbody"><i
								class="fa fa-heart"></i><span class="space">鲜花评价管理</span></a>
						</dd>
						<dd>
							<a href="admin/addflowerPage" target="mainbody"><i
								class="fa fa-plus"></i><span class="space">新增鲜花</span></a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a href="javascript:;"
					target="mainbody"><i class="fa fa-truck"></i><span
						class="space">订单管理</span></a>
					<dl class="layui-nav-child">
						<dd>
							<a href="admin/ordersinfoPage" target="mainbody"><i
								class="fa fa-star"></i><span class="space">用户订单</span></a>
						</dd>

						<dd>
							<a href="admin/pendingordersPage" target="mainbody"><i
								class="fa fa-star"></i><span class="space">待处理订单</span></a>
						</dd>
						<dd>
							<a href="admin/addorderPage" target="mainbody"><i
								class="fa fa-plus"></i><span class="space">新增订单</span></a>
						</dd>
					</dl></li>
			</ul>
		</div>
	</div>
</body>
</html>

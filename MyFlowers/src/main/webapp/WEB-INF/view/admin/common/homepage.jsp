<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" href="static/layui/css/layui.css" media="all">
<link rel="stylesheet" type="text/css"
	href="static/font-awesome/css/font-awesome.css">
<link rel="stylesheet" type="text/css"
	href="static/admin/css/homepage.css">
<!-- <script src="static/pages/js/common/jquery-1.7.2.js"></script> -->
<script type="text/javascript" src="static/jquery/jquery-1.8.3.js"></script>
<script src="static/layui/layui.js"></script>

<style type="text/css">
.layui-table, .layui-table-view {
	margin-top: 1px;
}

#info table {
	width: 100%;
	bgcolor: #00FF00;
	border: 1px solid #C0C0C0;
	margin-bottom: 10%;
}

#info  table tr {
	height: 40px;
}

#info  table tr td {
	padding-left: 5%;
}

#info  table tr td:hover {
	background-color: #e1ffff;
	color: #454545;
}
</style>
</head>
<body onload="tablerender();">

	<%-- 	<%
		int count = 0;
		if (application.getAttribute("onlineUsersList") != null) {
			List<String> onlineUsersList = (List<String>) application.getAttribute("onlineUsersList");
			count = onlineUsersList.size();
		}
	%> --%>
	<div class="welcome" id="welcome" style="background-color:">
		<div>
			<i class="fa fa-check" style="margin:0 1%;"></i> 欢迎使用<strong
				style="margin:0 5px;">花之苑·后台管理系统V1.0</strong>,我们竭尽所能，为您提供最好的服务.
			<button type="button" class="close">
				<i class="fa fa-remove  fa-lg" id="close"></i>
			</button>
		</div>
	</div>
	<div class="infodiv" style="margin-top:1%;">
		<div class="entry">
			<div class="ico">
				<i class="fa fa-users fa-3x"></i>
			</div>
			<div class="info">
				<div>
					<a href="javascript:;">在线人数</a>
				</div>
				<div class="number">
					数量：<span class="layui-badge layui-bg-blue">${applicationScope.count }</span>
				</div>
			</div>
		</div>
		<div class="entry">
			<div class="ico">
				<i class="fa fa-3x fa-shopping-cart"></i>
			</div>
			<div class="info">
				<div>
					<a href="admin/pendingordersPage">最新订单</a>
				</div>
				<div class="number">
					数量：<span class="layui-badge layui-bg-blue">${requestScope.pendingCount }</span>
				</div>
			</div>
		</div>
		<div class="entry">
			<div class="ico">
				<i class="fa fa-wechat fa-3x"></i>
			</div>
			<div class="info">
				<div>
					<a href="javascript:;">最新评价</a>
				</div>
				<div class="number">
					数量：<span class="layui-badge layui-bg-blue">${requestScope.purchasesCount }</span>
				</div>
			</div>
		</div>
	</div>
	<div style="clear:both;"></div>
	<div style="margin-left:1%;margin-top:5%;width:99%;">
		<jsp:include page="/WEB-INF/view/admin/reports/report.jsp"></jsp:include>
	</div>


	<div id="help" style="width:65%;float:left;">
		<table border="1">
			<tr>
				<th colspan="2" style="line-height:50px;"><i
					class="fa fa-heart"></i><b style="margin:0 1%;clear:both;">帮助中心</b><i
					class="fa fa-heart"></i></th>
			</tr>
			<tr>
				<td
					style="border-right:1px solid #FF8000;border-bottom:1px solid #FF8000;"><span
					class="help-title">我的面板</span>
					<ul>
						<li><span class="entry-title">个人信息</span><span
							class="entry-content">在这里您可以更新或者设置您自己的个人信息</span></li>
						<li><span class="entry-title">密码修改</span><span
							class="entry-content">在这里您可以重新设置您的登录密码</span></li>
					</ul></td>
				<td style="border-bottom:1px solid #FF8000;"><span
					class="help-title">用户管理</span>
					<ul>
						<li><span class="entry-title">用户信息</span><span
							class="entry-content">这里展示的是用户的基本信息主要包括用户名，生日，等级，联系方式等等，同时提供这些信息的修改，以及用户的删除等功能</span></li>
						<li><span class="entry-title">新增用户</span><span
							class="entry-content">新增一个用户</span></li>
					</ul></td>
			</tr>
			<tr>
				<td style="border-right:1px solid #FF8000;"><span
					class="help-title">鲜花管理</span>
					<ul>
						<li><span class="entry-title">鲜花信息管理</span><span
							class="entry-content">展示已有鲜花的基本信息，包括鲜花编号，名称，库存量，颜色等信息</span></li>
						<li><span class="entry-title">鲜花评价管理</span><span
							class="entry-content">展示鲜花评价信息</span></li>
						<li><span class="entry-title">新增鲜花</span><span
							class="entry-content">在新增一种鲜花时使用</span></li>
						<li></li>
					</ul></td>
				<td><span class="help-title">订单管理</span>
					<ul>
						<li><span class="entry-title">用户订单</span><span
							class="entry-content">已处理（已发货）的用户订单的信息</span></li>
						<li><span class="entry-title">待处理订单</span><span
							class="entry-content">待发货订单</span></li>
						<li><span class="entry-title">新增订单</span><span
							class="entry-content">针对用户非在线支付等方式购买鲜花时，新增订单</span></li>
					</ul></td>
			</tr>
		</table>
	</div>
	<div id="info"
		style="float:left;margin-top:5%;width:25%;margin-left:1%;">

		<table border="1">
			<tr>
				<th>花之苑·后台管理系统</th>
			</tr>
			<tr>
				<td>版本：1.0</td>
			</tr>
			<tr>
				<td>开发人员：LQingGang</td>
			</tr>
			<tr>
				<td>开发时间：2018-4-22 12:00:59</td>
			</tr>
			<tr>
				<td>当前系统：Window 7</td>
			</tr>
		</table>

		<table border="1">
			<tr>
				<th>网站信息</th>
			</tr>
			<tr>
				<td>域名：<a href="<%=basePath%>" target="_top"><%=basePath%></a></td>
			</tr>
			<tr>
				<td>网站目录：未定义</td>
			</tr>
			<tr>
				<td>服务器IP：127.0.0.1</td>
			</tr>
			<tr>
				<td>服务器环境：Tomcat 9.0</td>
			</tr>
			<tr>
				<td>数据库版本：MySQL 5.7.13</td>
			</tr>
		</table>
	</div>
	<div style="clear:both;"></div>


	<div style="width:80%;margin-left:10%;margin-top:2%;float:left;">
		<div style="text-align:center;margin:1% 0;font-size:20px;">
			<strong>系统日志信息</strong>
		</div>
		<table class="layui-hide" id="logs"></table>
		<!-- 分页 -->
		<div id="page" style="float:left;margin-left:15%;margin-bottom:5%;"></div>
		<!-- 当前页 -->
		<input type="hidden" name="curr" id="curr">

		<script>
			function tablerender() {
				var curr = this.document.getElementById("curr").value;
				if (curr == null || (curr != null && curr.length == 0)) {
					curr = 0;
				}
				layui.use([ 'table', 'laypage', 'layer' ], function() {
					var table = layui.table,
						layer = layui.layer,
						laypage = layui.laypage;
					table.render({
						elem : '#logs',
						url : 'admin/logs?curr=' + curr,
						page : false,
						cols : [ [
							{
								field : 'id',
								title : 'ID',
								width : 100,
								sort : true
							}
							, {
								field : 'name',
								width : 150,
								title : '用户名',
								sort : true
							}
							, {
								field : 'date',
								title : '时间',
								width : 200,
								sort : true
							}
							, {
								field : 'ip',
								width : 200,
								title : 'IP地址',
							}
							, {
								field : 'content',
								width : 382,
								title : '日志信息'
							}
						] ],
						done : function(res, curr, count) {
							laypage.render({
								layout : [ 'prev', 'page', 'next', 'skip', 'count' ], //自定义分页布局 
								elem : 'page', //注意，这里的 test1 是 ID，不用加 # 号
								count : count, //数据总数，从服务端得到
								limit : 30, //每页记录数
								curr : document.getElementById("curr").value,
								groups : 5, //显示 5 个连续页码
								jump : function(obj, first) {
		
									//首次不执行
									if (!first) {
										setCurrentPage(obj.curr); //设置当前页
										tablerender(); //重新渲染表格
									}
								}
							});
						}
					});
				});
				function setCurrentPage(curr) {
					this.document.getElementById("curr").value = curr;
				}
			}
		</script>

	</div>



</body>
<script type="text/javascript">
	$('#close').click(function() {
		$('#welcome').remove();
	})
</script>

</html>

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
<link href="static/pages/css/common/carousel.css" rel="stylesheet"
	type="text/css" />
<!-- <script src="static/pages/assets/jquery-1.12.4.js"></script> -->

<link href="static/pages/css/users/registered.css" rel="stylesheet" />
<link rel="stylesheet" href="static/layui/css/layui.css" />
<script type="text/javascript" src="static/layui/layui.js"></script>
<script type="text/javascript" src="static/pages/assets/data.js"></script>
<script type="text/javascript" src="static/pages/assets/province.js"></script>
<script type="text/javascript">
	var defaults = {
		s1 : 'provid',
		s2 : 'cityid',
		s3 : 'areaid',
		v1 : null,
		v2 : null,
		v3 : null
	};
</script>
</head>

<body>

	<div id="registered" style="margin-top:12%;">
		<div class="logo">
			<img alt="registered" src="static/pages/images/page/registered.png">
			<div class="separate"></div>
		</div>
		<form id="formregistered" class="layui-form layui-form-pane"
			action="users/registered" method="post" target="_top">
			<div class="layui-form-item">
				<label class="layui-form-label">用户名：</label>
				<div class="layui-input-block">
					<input type="text" name="name" id="name" placeholder="请输入用户名"
						autocomplete="off" class="layui-input" disabled="disabled"
						style="background-color:#e7e7e7;color:#C0C0C0;"
						value="${sessionScope.username }">
				</div>
			</div>
			<%-- <div class="layui-form-item">
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
			</div> --%>
			<div class="layui-form-item" pane>
				<label class="layui-form-label">性别：</label>
				<div class="layui-input-block">
					<input type="radio" name="gender" value="男" title="男"> <input
						type="radio" name="gender" value="女" title="女" checked>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">年龄：</label>
				<div class="layui-input-block">
					<input type="text" name="age" placeholder="请输入年龄"
						autocomplete="off" class="layui-input"
						value="${requestScope.age }">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">生日：</label>
				<div class="layui-input-block">
					<input type="text" class="layui-input" id="calendar"> <input
						type="text" class="layui-input" id="birtyday" name="birtyday"
						style=" display:none;" value="">
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

			<div class="layui-form-item">
				<label class="layui-form-label">选择地区</label>
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
					<input type="text" name="address2" placeholder="请输入详细地址"
						autocomplete="off" class="layui-input"
						value="${requestScope.address }">
				</div>
			</div>

			<div class="layui-form-item layui-form-text">
				<label class="layui-form-label">备注</label>
				<div class="layui-input-block">
					<textarea name="description" placeholder="请输入内容"
						class="layui-textarea"></textarea>
				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block button">
					<button class="layui-btn" lay-submit id="submit">立即提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</form>
		<div class="clear dividing"></div>
		<div id="note" style="margin-top:5%;margin-bottom:5%;">
			<div class="title">用户需知</div>
			<div class="readregulations">请先阅读以下条款：</div>
			<div></div>
			<div class="regulations">一、遵守中华人民共和国法律法令和其他相关法规，不可发布破坏宪法和法律、法规的信息。</div>
			<div class="regulations">二、遵守公安部关于《计算机信息网络国际联网安全保护管理办法》的规定，自觉维护计算机信息网络的安全。</div>
			<div class="regulations">三、不得在网上宣扬封建迷信、淫秽、色情、暴力、赌博等不正当行为。</div>
			<div class="regulations">四、遵守所有使用网站服务的网络协议、规定、程序和惯例。</div>
			<div class="regulations">五、用户注册成功后，请妥善保管您的用户名和密码，以备下次发布、修改信息使用。</div>
		</div>
	</div>
</body>
<!-- 日历 -->
<script>
	/* 	function calendar() { */
	layui.use('laydate', function() {
		var laydate = layui.laydate;

		//执行一个laydate实例
		laydate.render({
			elem : '#calendar', //指定元素
			done : function(value, date, endDate) {
				var birtyday = document.getElementById("birtyday");
				birtyday.value = value;
				lay('#calendar').html(value);
			}
		});
	});
	/* 	} */
</script>

<script>
	layui.use([ 'form', 'layer', 'laypage', 'laydate' ], function() {
		var form = layui.form,
			layer = layui.layer, //获得layer模块
			laypage = layui.laypage, //获得laypage模块
			laydate = layui.laydate, //获得laydate模块
			element = layui.element;

	//使用模块
	});
</script>
</html>

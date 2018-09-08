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

<title>用户资料修改</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="keywords" content="鲜花,销售,花之苑">
<meta name="description" content="用户资料修改">
<link href="static/pages/css/users/setting.css" rel="stylesheet"
	type="text/css" />
<link href="static/pages/css/users/registered.css" rel="stylesheet" />
<link rel="stylesheet" href="static/layui/css/layui.css" />
<script type="text/javascript" src="static/layui/layui.js"></script>
<script type="text/javascript" src="static/pages/assets/data.js"></script>
<script type="text/javascript" src="static/pages/assets/province.js"></script>
<style type="text/css">
a {
	text-decoration: none;
}
</style>
</head>

<body>
	<div id="basic_anchor_point"></div>
	<div class="basicdata">
		<!-- 基本资料设置 -->
		<div class="settingnavigation">
			<span><a href="users/settingpage#basic_anchor_point"
				target="mainbody">基本资料设置</a></span> <span><a
				href="users/settingpage#password_anchor_point" target="mainbody"
				class="passwordsetting">密码修改</a></span> <span><a
				href="users/settingpage#contact_anchor_point" target="mainbody">收货地址管理</a></span>
		</div>
		<div id="basic_data_setting">
			<form id="formbasic" class="layui-form layui-form-pane"
				action="users/basicsetting" method="post" target="_top">
				<div class="layui-form-item">
					<label class="layui-form-label">用户名：</label>
					<div class="layui-input-block">
						<input type="text" name="name" id="name"
							style="background-color:#e7e7e7;color:#C0C0C0;"
							disabled="disabled" autocomplete="off" class="layui-input"
							value="${sessionScope.username }">
					</div>
				</div>

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
							style=" display:none;" value="${requestScope.birtyday }">
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

				<div class="layui-form-item layui-form-text">
					<label class="layui-form-label">备注</label>
					<div class="layui-input-block">
						<textarea name="description" placeholder="请输入内容"
							class="layui-textarea"></textarea>
					</div>
				</div>
				<div class="layui-form-item">
					<div class="layui-input-block button" style="padding-left: 20%;">
						<button class="layui-btn" lay-submit id="submit">保存修改</button>
						<button type="reset" class="layui-btn layui-btn-primary">重置</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div id="password_anchor_point"></div>
	<div class="passworddata" style="margin-top: 18%;">
		<!-- 密码修改 -->
		<div class="settingnavigation">
			<span><a href="users/settingpage#basic_anchor_point"
				target="mainbody">基本资料设置</a></span> <span><a
				href="users/settingpage#password_anchor_point" target="mainbody"
				class="passwordsetting">密码修改</a></span> <span><a
				href="users/settingpage#contact_anchor_point" target="mainbody">收货地址管理</a></span>
		</div>
		<div id="password_setting">
			<form id="formpassword" class="layui-form layui-form-pane"
				action="users/passwordsetting" method="post" target="_top">
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
					<div class="layui-input-block button">
						<button class="layui-btn" lay-submit id="submit">确认修改</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div id="contact_anchor_point"></div>
	<!-- 收货地址设置 -->
	<div class="contactdata" style="margin-top: 18%;">
		<div class="settingnavigation">
			<span><a href="users/settingpage#basic_anchor_point"
				target="mainbody">基本资料设置</a></span> <span><a
				href="users/settingpage#password_anchor_point" target="mainbody"
				class="passwordsetting">密码修改</a></span> <span><a
				href="users/settingpage#contact_anchor_point" target="mainbody">收货地址管理</a></span>
		</div>
		<div id="contact_setting">
			<form id="formcontact" class="layui-form layui-form-pane"
				action="users/contactsetting" method="post" target="_top">

				<div class="layui-form-item">
					<label class="layui-form-label">联系电话：</label>
					<div class="layui-input-block">
						<input type="text" name="telephone" placeholder="请输入电话号码"
							autocomplete="off" class="layui-input"
							value="${requestScope.telephone }">
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
					<label class="layui-form-label">默认收货地址</label>
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
				<div class="layui-form-item">
					<div class="layui-input-block button">
						<button class="layui-btn" lay-submit id="submit">确认修改</button>
					</div>
				</div>
			</form>
		</div>

	</div>


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
</body>
</html>

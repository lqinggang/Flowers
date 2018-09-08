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

<title>用户登录</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="shortcut icon" href="static/pages/images/page/logo.png"
	type="image/x-icon" />
<link rel="stylesheet" href="static/pages/css/bootstrap.min.css" />
<link rel="stylesheet" href="static/pages/css/camera.css" />
<link rel="stylesheet"
	href="static/pages/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="static/pages/css/matrix-login.css" />
<link rel="stylesheet" href="static/pages/css/font-awesome.css" />
<script type="text/javascript"
	src="static/pages/js/common/jquery-1.5.1.min.js"></script>
<!-- 软键盘控件start -->
<link href="static/keypad/css/framework/form.css" rel="stylesheet"
	type="text/css" />
<!-- 软键盘控件end -->
<style type="text/css">
/*
   body{
    -webkit-transform: rotate(-3deg);
    -moz-transform: rotate(-3deg);
    -o-transform: rotate(-3deg);
	padding-top:20px;
    }
    */
.cavs {
	z-index: 1;
	position: fixed;
	width: 95%;
	margin-left: 20px;
	margin-right: 20px;
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
	<!--小键盘承载器-->
	<canvas class="cavs"></canvas>
	<div
		style="width:100%;text-align: center;margin: 0 auto;position: absolute;">
		<!-- 登录 -->
		<div id="windows1">
			<div id="loginbox">
				<form action="users/login" method="post" name="loginForm"
					id="loginForm">
					<div class="control-group normal_text">
						<h3>
							<img src="static/pages/images/page/logo.png" alt="Logo" />
						</h3>
					</div>

					<div class="control-group">
						<div class="controls">
							<div class="main_input_box">
								<span class="add-on bg_lg"> <i><img height="37"
										src="static/pages/images/page/user.png" /></i>
								</span><input type="text" name="loginname" id="loginname"
									value="${requestScope.name }" placeholder="请输入用户名" />
							</div>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<div class="main_input_box">
								<span class="add-on bg_ly"> <i><img height="37"
										src="static/pages/images/page/suo.png" /></i>
								</span><input type="password" name="password" id="password"
									placeholder="请输入密码" class="keypad" keypadMode="full"
									allowKeyboard="true" value="${requestScope.password }" />

							</div>
						</div>
					</div>

					<div></div>

					<div style="float:left;padding-left:12%;">
						<div style="float: left;padding-top:3px;">
							<input name="savepassword" id="savepassword" type="checkbox"
								value="1" />
						</div>
						<div style="float: left;margin-top:3px;margin-right:2px;">
							<div style="color:#000;font-size:16px;">记住密码</div>
						</div>

					</div>
					<div class="form-actions">
						<div style="width:80%;padding-left:8%;">
							<div>
								<span style="float: left;padding-top:2px;"> <i><img
										src="static/pages/images/page/yan.png" /></i>
								</span> <span style="float: left;" class="codediv"
									style="margin-left:0;width:80%;"> <input type="text"
									name="code" id="code" class="login_code"
									style="margin-left:0;height:100%;width:100%; padding-top:4px;" />
								</span> <span style="float: left;"> <i><img
										style="height:25px;width:100%;margin-left:5px;" id="codeImg"
										alt="验证码" title="点击更换" src="ran/random"
										onclick="this.src='ran/random?'+Math.random()" /></i>
								</span>
							</div>
						</div>
						<div style="clear:both;text-align:center;">
							<span> <input type="submit" class="flip-link btn btn-info"
								id="to-recover" value="登录"></input>
							</span> <span style="padding-right:3%;"><a href="mainPage/index"
								class="btn btn-success">首页</a></span>
						</div>
					</div>
				</form>
				<div class="controls">
					<div class="main_input_box">
						<span id="nameerr" style="color:white;">Copyright © XX科技
							2018</span>
					</div>
				</div>
			</div>
		</div>
		<c:choose>
			<c:when test="${!empty requestScope.msg }">
				<script src="static/layui/layui.js" type="text/javascript"></script>
				<script type="text/javascript">
					layui.use('layer', function() {
						var $ = layui.$; //重点处
						var layer = layui.layer; //获得layer模块
						layer.open({
							type : 1,
							area : [ '240px', '120px' ],
							offset : 'auto',
							title : '登录错误',
							content : '${requestScope.msg}'
						});
				
					});
				</script>
			</c:when>
		</c:choose>
	</div>
	<div id="templatemo_banner_slide" class="container_wapper">
		<div class="camera_wrap camera_emboss" id="camera_slide">
			<!-- 背景图片 -->
			<div data-src="static/pages/images/page/banner_slide_01.jpg"></div>
			<div data-src="static/pages/images/page/banner_slide_02.jpg"></div>
			<div data-src="static/pages/images/page/banner_slide_03.jpg"></div>
			<div data-src="static/pages/images/page/banner_slide_04.jpg"></div>
			<div data-src="static/pages/images/page/banner_slide_05.jpg"></div>
		</div>
	</div>
	<script src="static/pages/js/bootstrap.min.js"></script>
	<script src="static/pages/js/common/jquery-1.7.2.js"></script>
	<script src="static/pages/js/common/jquery.easing.1.3.js"></script>
	<script src="static/pages/js/common/jquery.mobile.customized.min.js"></script>
	<script src="static/pages/js/camera.min.js"></script>
	<script src="static/pages/js/templatemo_script.js"></script>
	<script src="static/pages/js/ban.js"></script>
	<script type="text/javascript"
		src="static/pages/js/common/jQuery.md5.js"></script>
	<script type="text/javascript"
		src="static/pages/js/common/jquery.tips.js"></script>
	<script type="text/javascript"
		src="static/pages/js/common/jquery.cookie.js"></script>

	<!-- 软键盘控件start -->
	<script type="text/javascript" src="static/keypad/js/form/keypad.js"></script>
	<script type="text/javascript" src="static/keypad/js/framework.js"></script>
	<!-- 软键盘控件end -->
</body>

</html>

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
<link href="static/pages/css/common/customerservice.css"
	rel="stylesheet" media="screen" type="text/css" />
<script src="static/jquery/jquery-1.11.0.min.js"></script>
</head>
<body>

	<!-- 客服部分 -->
	<div style="right:-230px;" class="contactusdiyou">
		<div class="hoverbtn">
			<span>联</span><span>系</span><span>我</span><span>们</span> <img
				class="hoverimg" src="static/pages/images/page/hoverbtnbg.gif"
				height="9" width="13">
		</div>
		<div class="conter">
			<div class="con1">
				<dl class="fn_cle">
					<dt>
						<img src="static/pages/images/page/tel.png" height="31" width="31">
					</dt>
					<dd class="f1">咨询热线：</dd>
					<dd class="f2">
						<span class="ph_num">400-xxxx-xxxx</span>
					</dd>
				</dl>
			</div>
			<div class="blank0"></div>
			<div class="qqcall">
				<dl class="fn_cle">
					<dt>
						<img src="static/pages/images/page/qq.png" height="31" width="31">
					</dt>
					<dd class="f1 f_s14">在线客服：</dd>
					<dd class="f2 kefuQQ">
						<span>客服一</span> <a target="_blank"
							href="http://wpa.qq.com/msgrd?v=3&amp;uin=1944058861&amp;site=qq&amp;menu=yes">

						</a> <br> <span>客服二</span> <a target="_blank"
							href="http://wpa.qq.com/msgrd?v=3&amp;uin=3244418916&amp;site=qq&amp;menu=yes"></a>
					</dd>
				</dl>
				<div class="blank0" style="height:20px;"></div>
			</div>
			<div class="blank0"></div>
			<div class="weixincall">
				<dl class="fn_cle">
					<dt>
						<img src="static/pages/images/page/weixin.png" height="31"
							width="31">
					</dt>
					<dd class="f1">官方微信：</dd>
					<dd class="f3">
						<img src="static/pages/images/page/wechat.png" height="73"
							width="73">
					</dd>
				</dl>
			</div>
			<div class="blank0"></div>
			<div class="dytimer">
				<span style="font-weight: bold;">公司官网：</span> <span><a
					href="javascript:;" style="color:#FFF;">https://www.xxxx.com</a></span>
			</div>
		</div>
	</div>

	<!-- 	<div class="diyoumask" style="height: 2000px;"></div> -->

	<script type="text/javascript">
		$(function() {
			$(".contactusdiyou").hover(function() {
				$(".hoverimg").attr("src", "static/pages/images/page/hoverbtnbg1.gif");
				$('.diyoumask').fadeIn();
				$('.contactusdiyou').animate({
					right : '0'
				}, 300);
			}, function() {
				$(".hoverimg").attr("src", "static/pages/images/page/hoverbtnbg.gif");
				$('.contactusdiyou').animate({
					right : '-230px'
				}, 300, function() {});
				$('.diyoumask').fadeOut();
			});
		});
	</script>
</body>
</html>

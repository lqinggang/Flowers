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

<title>常见问题</title>

<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="鲜花,销售">
<meta http-equiv="description" content="鲜花销售平台">
<link href="static/pages/css/service/service.css" rel="stylesheet"
	type="text/css" />
<link href="static/pages/css/common/page.css" rel="stylesheet"
	type="text/css" />

<style type="text/css">
body {
	height: 1300px;
}
</style>
</head>

<body>
	<div id="problem" class="service">
		<div class="logo">
			<img alt="logo" src="static/pages/images/page/logo.png">
			<div class="separate"></div>
		</div>

		<div class="title">
			<h2>FQA</h2>
			<h3>常见问题</h3>
		</div>
		<div style="padding-left:5%;padding-top:5%;font-size:17px;">以下是客户在购买鲜花过程中遇到的较多的问题，如果您也遇到了问题，下面的解答或许能够帮助您。</div>
		<div style="padding-left:5%;font-size:18px;color:#ff8000;">如果您是第一次在本站订购鲜花，强烈建议您浏览以下内容，可节约您在购物过程中宝贵时间：</div>


		<div class="problemlist">
			<div class="problemtitle">我怎么订购，流程是怎么样的？</div>
			<div class="problemcontent">
				<p>您在网上提交一个订单，按提示付完款后，我们将在您要求的时间送花上门。</p>
			</div>
		</div>

		<div class="problemlist">
			<div class="problemtitle">我订够的鲜花多久能送到？</div>
			<div class="problemcontent">
				<p>在您订购付款后，我们会尽量会以最快的速度审查，鲜花最快可以在3个小时左右就能送到接收人手上。</p>
			</div>
		</div>
		<div class="problemlist">
			<div class="problemtitle">我怎么样付款？</div>
			<div class="problemcontent">
				<p>可以使用支付宝、微信支付等付款，也可以使用银行汇款，我们强烈推荐您使用支付宝或者微信支付。</p>
			</div>
		</div>
		<div class="problemlist">
			<div class="problemtitle">银行汇款，你们多久能查到，会通知我吗？</div>
			<div class="problemcontent">
				<p>全国银行汇款都是即时到帐的，您汇完通知我们后，我们能马上查到，将短信通知您到款情况。</p>
			</div>
		</div>
		<div class="problemlist">
			<div class="problemtitle">我可以电话下订单吗？</div>
			<div class="problemcontent">
				<p>完全可以，您的光临是我们荣幸。</p>
				<p>订购中需填写详细收花人详细资料及贺卡留言，因此我们建议您在线下单，让留言有时间认真推敲后填写。</p>
			</div>
		</div>
		<div class="problemlist">
			<div class="problemtitle">我可以退订鲜花吗？</div>
			<div class="problemcontent">
				<p>首先为此我们感到很抱歉，没能让您感到满意。</p>
				<p>由于鲜花不易保存等因素，在您签收之后，我们将不再提供退货服务，是在抱歉。</p>
				<p>所以，我们推荐您在线订购，可以有充足的时间考虑，当然在鲜花未发货前，我们还是为您提供了取消订单服务。</p>
				<p>最后，再次向您表达歉意，同时感谢您在本站购买鲜花。</p>
			</div>
		</div>


	</div>
</body>
</html>

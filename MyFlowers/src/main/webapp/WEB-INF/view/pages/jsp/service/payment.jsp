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
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="鲜花,销售">
<meta http-equiv="description" content="鲜花销售平台">
<link href="static/pages/css/service/service.css" rel="stylesheet"
	type="text/css" />
<link href="static/pages/css/common/page.css" rel="stylesheet"
	type="text/css" />
</head>

<body>
	<div id="privacy" class="service">
		<div class="logo">
			<img alt="logo" src="static/pages/images/page/logo.png">
			<div class="separate"></div>
		</div>

		<div class="title" style="margin-bottom:5%;">
			<h2>PAYMENT</h2>
			<h3>支付说明</h3>
		</div>
		<div class="bar">
			<div class="descriptioncontent">
				<p>我们有着良好的市场口碑的和广泛的行业影响，请您放心支付，我们深知每张订单都蕴含着重要的意义，将用心把服务做到最好，不辜负您的信赖和支持。</p>
				<p>我们的流程是先提交订单然后付款，款到后订单才会执行。请您在支付前了解各付款方式的特点和到款时间，合理选择付款方式。请尽量提前订购和及时支付，并请留意查询订单状态是否确认为已付款。</p>
				<p>若出现以下情况造成订单延误或暂停配送，我们将不承担责任：</p>
				<p>a.顾客没有支付或支付被退回</p>
				<p>b.顾客货款在发货前6小时仍未到帐</p>
				<p>注意：非网上支付的用户，请务必在汇款后及时通过“电话/短信/留言/在线客服系统”中的一种方式通知我们付款情况(邮局/中行不是即时到帐，需传真凭证)。每天采用同一种方式支付的用户很多，如果您未通知我们，将无法确认到您的订单是否到款（网上在线支付不受此限制）。如果您的货款到帐，但到帐时间已超过了配送时间，我们会暂停配送，在与您联系后按要求重新服务。</p>
			</div>
		</div>

		<div class="bar">
			<div class="descriptiontitle">支付方式</div>
			<div style="margin-top:5%;">
				<table border="1" cellspacing="0" style="border-color:#f5f5f5;">
					<tr style="border-color:#f5f5f5;">
						<td colspan="3" style="height:34px;background-color:#EEE;"><strong>网上支付</strong>
						</td>
					</tr>
					<tr style="border-color:#f5f5f5;">
						<td style="width:150px;text-align:center;"><strong>支付宝</strong></td>
						<td>在线实时支付，支持支付宝帐号余额支付，支持国内各银行卡支付。</td>
						<td><img alt="支付宝" src="static/pages/images/page/alipay.jpg"></td>
					</tr>
					<tr style="border-color:#f5f5f5;">
						<td style="width:150px;text-align:center;"><strong>微信支付</strong></td>
						<td>微信支付是集成在微信客户端的支付功能，用户可以通过手机完成快速的支付流程。微信支付向用户提供安全、快捷、高效的支付服务，以绑定银行卡的快捷支付为基础。</td>
						<td><img alt="微信支付" src="static/pages/images/page/weixin.jpg"></td>
					</tr>
				</table>
			</div>
		</div>
	</div>

</body>
</html>

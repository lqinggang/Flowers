<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">

<title>订单说明</title>

<meta charset="UTF-8" />
<meta name="keywords" content="鲜花,销售,花之苑">
<meta name="description" content="我的订单">
<link href="static/layui/css/layui.css" rel="stylesheet" media="all">
<!-- <link href="static/pages/css/orders/order.css" rel="stylesheet"
	media="all"> -->
<link href="static/pages/css/common/page.css" rel="stylesheet"
	media="all">
<link href="static/pages/css/service/service.css" rel="stylesheet"
	type="text/css" />

<style type="text/css">
tr {
	color: #4d4d4d;
	height: 20%;
	width: 100%;
	border: 1px solid #C0C0C0;
}

tr td {
	height: 20%;
}

p {
	font-size: 18px;
	margin: 2% 6%;
}
</style>
</head>

<body>
	<div id="orderinstructions" class="service">
		<div class="logo">
			<img alt="logo" src="static/pages/images/page/logo.png">
			<div class="separate"></div>
		</div>

		<div class="title" style="margin-bottom:5%;">
			<h2>ORDER INSTRUCTIONS</h2>
			<h3>订单说明</h3>
		</div>
		<div style="margin:5%;width:80%;">
			<table border="1" style="border-color:#f5f5f5;cellspacing:0;">
				<tr style="background-color:#DfDFDF;">
					<td colspan="2" style="padding:2%;">订单取消方式</td>
				</tr>
				<tr>
					<td style="width:20%;text-align:center;">网页撤单</td>
					<td style="width:80%;padding:5%;">在本站中"我的订单"部分,在全部订单部分或订单查询部分找到需要撤单的订单,然后看详细信息,在详细信息的下方,会有撤单的按钮(只有在该订单能够被撤除的状态下出现),选择找个按钮,就撤掉这个订单了.我们建议您使用这种方式。</td>
				</tr>
				<tr>
					<td style="width:20%;text-align:center;">人工撤单</td>
					<td style="width:80%;padding:5%;">您可以直接电话或者传真给我们,说明您的订单号码和您的身份,在允许的情况下,该订单可被直接撤掉.(电话号码：xxxx-xxxxxxxx)</td>
				</tr>
			</table>
		</div>
		<div>
			<div style="margin-left:5%;">注：</div>
			<div>
				<p>1. 非重大节日时期，可接受送货前一天的订单修改或取消。于送货前要修改或取消订单。</p>
				<p>2. 若鲜花已送出，恕不再接受订单取消或更改（收货人联系电话或收货地址变更除外）。</p>
				<p>3.
					订单取消手续费：原路退回或退至银行帐号，扣取支付订单金额的10%作为手续费；特别注意：送货当日取消、重要节日前一天和当日取消、已预订特殊花材的订单取消、其他已有成本支出订单取消，此4种情况需要酌情收取成本费用
				</p>
				<p>4.如鲜花已送出（非重大节日）欲取消订单，需支付订单总金额的20%~50%作为订单处理及材料费用。</p>
				<br>
				<p>重大节日说明：</p>
				<p>重大节日包含且不仅仅包含以下节日：情人节、三八妇女节、母亲节、父亲节、七夕情人节、圣诞节</p>
			</div>
		</div>
	</div>
</body>
</html>

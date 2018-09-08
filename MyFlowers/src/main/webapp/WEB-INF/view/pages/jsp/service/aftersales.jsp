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

<style type="text/css">
.aftersalestable {
	font-size: 18px;
}

.case {
	background-color: #F8F8F8;
	width: 10%;
	height: 5%;
	padding: 1%;
}

.reason {
	padding: 5%;
	text-align: left;
	width: 35%;
}

.handle {
	padding: 5%;
	text-align: left;
	width: 55%;
}

}
tr {
	border: 1px;
	vertical-align: inherit;
	border-color: inherit;
	border-collapse: inherit;
}

#matter, #matter tr td {
	border: 1px solid #f3f3f3;
}
</style>
</head>

<body>
	<div id="aftersales" class="service">
		<div class="logo">
			<img alt="logo" src="static/pages/images/page/logo.png">
			<div class="separate"></div>
		</div>

		<div class="title" style="margin-bottom:5%;">
			<h2>AFTER SALES</h2>
			<h3>售后服务</h3>
		</div>
		<div class="aftersalestable"
			style="width:90%;height:80%;margin-left:5%;">
			<table style="border:1px solid #f3f3f3;text-align:center;width:100%;"
				cellspacing="0" cellpadding="0">
				<tr style="background-color:#EEE;height:40px;">
					<td style="width:49%;align:center;">投诉事件</td>
					<td style="width:50%;align:center;">处理方法</td>
				</tr>
			</table>
			<table
				style="border-color:#f3f3f3;text-align:center;width:100%;height:80%;color:#868686;"
				border="1" cellspacing="0" cellpadding="0" id="matter">
				<tr>
					<td class="case">漏单</td>
					<td class="reason">客户按时支付成功，我们没有做任何处理。</td>
					<td class="handle">100%全额退款，退回至客户指定帐户，同时，和客户另协商配送时间，我们免费重新派送原订单。
						<br> <span style="color:#DF8000;">属于200%退赔</span>
					</td>
				</tr>
				<tr>
					<td class="case">误单</td>
					<td class="reason">因我方失误，配送日期提前或延后</td>
					<td class="handle">将订单款项的50%退还,或者在客户指定时间，再配送一次与原订单鲜花。</td>
				</tr>
				<tr>
					<td class="case">花材不符</td>
					<td class="reason">没有和客户沟通，由于我方失误造成主花材鲜花颜色不符</td>
					<td class="handle">给收花人补送原订单的鲜花， 或者将订单款项的50%退还</td>
				</tr>
				<tr>
					<td class="case">花材不符</td>
					<td class="reason">花材不新鲜</td>
					<td class="handle">A、配送的鲜花不新鲜，收花人拒收。 处理方式：重新配送新鲜的鲜花，并向客户真诚道歉。
						<br> B、收花人已收下花束，后客户投诉鲜花不新鲜。 处理方法： <br>如果经核实花材是新鲜的，是部分花材需要时间才能开放等类似原因，我们将认真向客户解释清楚。
						<br>如果经核实确属花材不新鲜，重新给收花人补送原订单的新鲜鲜花，并向客户真诚道歉。 <br>1.B类情况，请务必在夏季鲜花收到2个小时内、其他季节鲜花收到4个小时内向我们投诉。同时收花人需保留花礼，我们将派工作人员调查核实，如果已将花礼损坏或丢弃，或者投诉时效逾期，不便核实，视为无效投诉。
						<br>2.B类情况，如果收花人签收了我们的签收单，且对收到货品的反馈意见没有签写为“不满意”，则不做投诉处理。
					</td>
				</tr>
				<tr>
					<td class="case">其他</td>
					<td class="reason">任何您不满的地方</td>
					<td class="handle">均可向我们反馈，我们将认真倾听，用心改进，为您提供更满意的服务。</td>
				</tr>
			</table>
			<div>
				<p>如果出现以下情况，不作为投诉处理：</p>
				<p>1.因自然灾害(台风、地震等)、交通戒严等不可抗因素，导致鲜花配送延误或不太新鲜。</p>
				<p>2.如果配送地某种花材短缺，在无法及时联系到订花人的情况下，我方将考虑该订单的时效性(如：生日、节日、结婚、纪念日），采用相似、等价值的原则决定替用花材，保证订单在客户要求的时间送到。</p>
				我们拥有以上文字的解释权。
			</div>
		</div>

	</div>
</body>
</html>

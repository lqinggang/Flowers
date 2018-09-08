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
<title>服务中心</title>

<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="鲜花,销售">
<meta http-equiv="description" content="鲜花销售平台">
<link href="static/pages/css/service/service.css" rel="stylesheet"
	type="text/css" />
<link href="static/pages/css/common/page.css" rel="stylesheet"
	type="text/css" />
<style type="text/css">
#complaints p  a:hover {
	color: #C0C0C0;
}

body {
	height: 600px;
}
</style>
</head>


<body>

	<div id="complaints" class="service">
		<div class="logo">
			<img alt="logo" src="static/pages/images/page/logo.png">
			<div class="separate"></div>
		</div>

		<div class="title" style="margin-bottom:5%;">
			<h2>COMPLAINTS</h2>
			<h3>投诉说明</h3>
		</div>
		<div class="sequence" style="float:left;" style="margin-top:5%;"></div>
		<p>当您的利益受到侵害时，请您第一时间向我们投诉，我们承诺将在收到您投诉后马上调查核实，24小时以内回复您具体情况和解决方案。有关投诉的处理依据：本站售后服务条款、《中华人民共和国消费者权益保护法》。</p>

		<div class="sequence" style="float:left;"></div>
		<p>关于投诉的时间说明：</p>
		<p style="margin-top:1%;">1.不新鲜投诉：
			因鲜花的新鲜程度均受保管/保养方法及时效因素的影响，针对不新鲜投诉做如下时间规定：</p>
		<p>鲜花：如果不新鲜，请务必在夏季收到鲜花2个小时内，其他季节收到鲜花4个小时内，向我们投诉。</p>

		<p>2.其他方面的投诉： 请务必在收货人收到商品五天内，向我们投诉。</p>

		<div>
			<p>您尽早反馈将有助于我们及时沟通加盟店及配送人员，了解具体情况，并为您提供适时满意的处理办法。
				敬请留意以上时效要求，逾期将不便核实，视为无效投诉。</p>
		</div>
		<div>
			<p>为了解决您不便在第一时间得知所送到商品质量的问题，我们采取了如下措施：在签收单中设置了“收货人对收到货品的反馈意见：
				很满意、满意、不满意”。如果收货人签署意见为“不满意”，您的投诉将不受以上时效限制。</p>
		</div>
		<div class="sequence" style="float:left;"></div>
		<p>关于投诉方式：</p>
		<p style="margin-top:1%;">
			您可以在电话（投诉电话：400-xxx-xxxx ）中进行投诉，或者向我们的&nbsp;&gt;&gt;<a href=""
				style="color:#ff8000;">&nbsp;在线客服&nbsp;</a>&lt;&lt;&nbsp;投诉,没能让您满意，为此我们感到深深的歉意，望您在投诉过程中，多给我们提更多宝贵的意见。
		</p>
	</div>
</body>
</html>

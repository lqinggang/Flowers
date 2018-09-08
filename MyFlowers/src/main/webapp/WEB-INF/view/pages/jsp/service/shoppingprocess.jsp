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
<script type="text/javascript">
	var mainbody = parent.document.getElementById("mainbody");
	mainbody.height = 1200;
</script>

<body>
	<div id="shoppingprocess" class="service">
		<div class="logo">
			<img alt="logo" src="static/pages/images/page/logo.png">
			<div class="separate"></div>
		</div>

		<div class="title" style="margin-bottom:5%;">
			<h2>PROCESS</h2>
			<h3>购物流程</h3>
		</div>
		<div style="padding-left:8%;width:80%;">
			<p>网上订花原来这么方便，只需要几分钟，您可以决定款式，配送时间和地点 轻点鼠标，在万花从中，为远方的她精选一份温馨祝福
				想像，数小时之后她微笑着手捧鲜花的幸福模样…… 那一刻，我们分享您们的快乐</p>
		</div>
		<div class="bar">
			<div class="descriptiontitle">1.选择商品</div>
			<div class="descriptioncontent">
				<p>进入网站，选择您喜欢的商品，点击商品的图片或者名称，将打开商品详情页面。</p>
			</div>
		</div>
		<div class="bar">
			<div class="descriptiontitle">2.放入购物车</div>
			<div class="descriptioncontent">
				<p>
					在商品详情页面中，点击“加入购物车”按钮，将打开购物篮页面。如果还需同时配送其他商品，您可以继续浏览，然后找到所需商品，同样点击“加入购物车”即可。</p>
			</div>
		</div>
		<div class="bar">
			<div class="descriptiontitle">3.提交我的订单</div>
			<div class="descriptioncontent">
				<p>如果您已经确定好选购的商品，点击“提交订单”。</p>
			</div>
		</div>
		<div class="bar">
			<div class="descriptiontitle">4.填写订单信息</div>
			<div class="descriptioncontent">
				<p>先填写购买人资料，下一步，填写送货资料：收货人姓名、电话，选择送达的省份－城市－所在区域，送货日期，贺卡留言内容。</p>
			</div>
		</div>
		<div class="bar">
			<div class="descriptiontitle">5.提交订单</div>
			<div class="descriptioncontent">
				<p>填写完毕后，仔细检查一下填写信息是否有错，支付方式是否选择正确，可以返回修改，如果确认无误，点击“提交订单”，提交成功后，将自动产生一个订单号码。</p>
			</div>
		</div>
		<div class="bar">
			<div class="descriptiontitle">6.支付款项</div>
			<div class="descriptioncontent">
				<p>提交完成订单后，会直接带入您到所选择的支付款项的步骤。您按提示完成付款，即完成订购的全过程。</p>
			</div>
		</div>

	</div>
</body>
</html>

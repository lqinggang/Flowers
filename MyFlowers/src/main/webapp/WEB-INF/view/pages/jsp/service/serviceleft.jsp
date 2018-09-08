<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/base.jspf"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>花之苑</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="鲜花,销售">
<meta http-equiv="description" content="鲜花销售平台">
<link href="static/pages/css/service/service.css" rel="stylesheet"
	type="text/css" />
<!-- <style type="text/css">
a:visited {
	color: #0000FF;
}

a:active {
	color: #D200D2;
	text-decoration: none;
}
</style> -->
</head>

<body>
	<div class="position">
		<div>当前位置：帮助中心</div>
		<c:choose>
			<c:when test="${!empty requestScope.positionMap }">
				<c:forEach items="${requestScope.positionMap }" var="positonMap"
					begin="0" end="3">
					&nbsp;>> &nbsp;<a href="${positonMap[url] }">${positonMap[position] }</a>
				</c:forEach>
			</c:when>
		</c:choose>
	</div>
	<div id="serviceleft">
		<div id="serivcetitle">
			<img src="static/pages/images/page/title.png"></img>帮助中心
		</div>
		<hr />
		<ul class="hd">
			<li class="category"><a href="javascript:;"> <img
					src="static/pages/images/page/problem.png" class="ico"></img><span
					class="categorytitle">常见问题</span>
			</a>
				<ul class="bd">
					<li><a href="service/problem" target="mainbody">常见问题</a></li>
				</ul></li>
			<li class="category"><a href="javascript:;"><img
					src="static/pages/images/page/orders.png" class="ico"></img><span
					class="categorytitle">订单帮助</span></a>
				<ul class="bd">
					<li><a href="service/orderssearch" target="mainbody">我的订单</a></li>
					<li><a href="service/orderinstruction" target="mainbody">订单说明</a></li>
				</ul></li>

			<li class="category"><a href="javascript:;"><img
					src="static/pages/images/page/sales.png" class="ico"></img><span
					class="categorytitle">售后服务</span></a>
				<ul class="bd">
					<li><a href="service/complaints" target="mainbody">投诉说明</a></li>
					<li><a href="service/aftersales" target="mainbody">鲜花售后</a></li>
				</ul></li>

			<li class="category"><a href="javascript:;"><img
					src="static/pages/images/page/service.png" class="ico"></img><span
					class="categorytitle">服务声明</span></a>
				<ul class="bd">
					<li><a href="service/servicedescription" target="mainbody">服务声明</a></li>
					<li><a href="service/shoppingprocess" target="mainbody">购物流程</a></li>
					<li><a href="service/privacy" target="mainbody">隐私条款</a></li>
				</ul></li>

			<li class="category"><a href="javascript:;"><img
					src="static/pages/images/page/other.png" class="ico"></img><span
					class="categorytitle">其他说明</span></a>
				<ul class="bd">
					<li><a href="service/paymentInstructions" target="mainbody">支付说明</a></li>
					<li><a href="service/safety" target="mainbody">安全条款</a></li>
					<li><a href="service/contact" target="mainbody">联系我们</a></li>
				</ul></li>
		</ul>

	</div>
</body>
</html>

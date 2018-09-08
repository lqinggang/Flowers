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

<title>订单信息</title>

<link rel="stylesheet" href="static/layui/css/layui.css" />
<link rel="stylesheet" href="static/pages/css/carts.css">
<style type="text/css">
.goods, .goodstitle {
	margin-left: 15%;
}

.goods  .col {
	border: 1px solid #cecece;
	height: 7%;
	float: left;
}

.goodstitle  .col {
	border: 1px solid #cecece;
	height: 3%;
	float: left;
}

.goods  .col .price {
	padding-top: 20%;
}

.layui-input-block {
	width: 50%;
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

body {
	height: 1500px;
}
</style>
</head>

<body>
	<div id="orders" style="width:80%;margin-left:5%;">
		<div>
			<p style="color:red;font-size:18px;margin:3%;">以下信息很重要，请务必认真填写！</p>
		</div>
		<form id="orderform" class="layui-form layui-form-pane" target="_top"
			action="javascript:confirmBuy();" method="post"
			style="margin-left:5%;">
			<div class="layui-form-item">
				<label class="layui-form-label">收件人：</label>
				<div class="layui-input-block">
					<input type="text" name="name" id="name" placeholder="请输入收件人名字"
						autocomplete="off" class="layui-input"
						value="${sessionScope.username }">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">联系方式：</label>
				<div class="layui-input-block">
					<input type="text" name="contact" id="contact"
						placeholder="请输入收件人联系方式" autocomplete="off" class="layui-input"
						value="${requestScope.contact }">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">收件人地址：</label>
				<div class="layui-input-block">
					<input type="text" name="address" id="address"
						placeholder="请输入收件人地址" autocomplete="off" class="layui-input"
						value="${requestScope.address }">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">备注：</label>
				<div class="layui-input-block">
					<input type="text" name="note" id="note" placeholder="备注信息"
						autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item" style="color:red;font-size:20px;">
				<label class="layui-form-label"
					style="background-color:#FFF;border:none;">总价： </label>
				<div class="layui-input-block" style="color:red;font-size:20px;">
					<input type="text" name="totalPrice" id="totalPrice"
						placeholder="请输入收件地址" autocomplete="off" class="layui-input"
						value="${sessionScope.totalPrice }" disabled="disabled"
						style="border:none;color:red;">
				</div>

			</div>
			<div class="layui-form-item" style="margin-bottom:5%;">
				<div class="layui-input-block button">
					<button class="layui-btn" lay-submit id="buysubmit">立即购买</button>
				</div>
			</div>
			<div style="margin-left:10%;font-size:18px;margin-bottom:10px;">购买清单：</div>
			<c:choose>
				<c:when test="${!empty sessionScope.orderFlowersList }">
					<div class="goodstitle" style="font-size:20px;font-weight:bold;">
						<span style="width:54%;text-align:center;" class="col">商品名称</span>
						<span style="width:20%;text-align:center;" class="col">单价</span> <span
							style="width:20%;text-align:center;" class="col">数量</span>
					</div>
					<c:forEach items="${sessionScope.orderFlowersList }" var="mycart"
						varStatus="id" begin="0">
						<div style="heigth:50px; align:left;" class="goods">
							<div style="width:54%;" class="col">
								<a href="flowers/searchflowers?id=${mycart.flower_id.flower_id }"><img
									alt="flowersimg" src="${mycart.flower_id.image }"
									style="width:60px;height:60px;padding:5%;"> ${mycart.flower_id.name }</a>
							</div>
							<div style="width:20%;text-align:center;" class="order_price col">
								<div style="margin-top:20%;margin-left:20%;color:red;">
									<p>￥${mycart.flower_id.price }</p>
								</div>
							</div>
							<div style="width:20%;text-align:center;"
								class="order_amount col">
								<div style="margin-top:20%;margin-left:20%;">
									<p>${mycart.amount }</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${!empty youlikeList }">
					<div id="youlike">
						<h3
							style="height:40px;background-color:#f6f6f6;text-align:center;margin-bottom:10px;padding-top:12px;">也许你会喜欢：</h3>
						<c:forEach items="${youlikeList }" var="flowers" varStatus="id">
							<div class="row flowers">
								<span class="col"> <a
									href="flowers/searchflowers?id=${flowers.flower_id }"> <span
										class="flowersimage"> <img alt="${flowers.name }"
											src="${flowers.image }" style="height:80%;">
									</span> <span class="info"> <span class="info-name">${flowers.name }</span>
											<span class="info-price">￥${flowers.price } </span>
									</span>
								</a>
								</span>
							</div>
						</c:forEach>
					</div>
				</c:when>

			</c:choose>
		</form>

	</div>
	<script type="text/javascript" src="static/layui/layui.js"></script>
	<script src="static/pages/js/common/jquery-1.7.2.js"></script>
	<script type="text/javascript">
		function confirmBuy() {
			layui.use('layer', function() {
				var layer = layui.layer;
				var confirmwindow = layer.confirm('是否确认购买？', {
					btn : [ '是', '否' ] //按钮
				}, function() {
					var orderform = document.getElementById("orderform");
					orderform.action = 'users/buy';
					orderform.submit();
				}, function() {
					layer.close(confirmwindow)
				});
			})
		}
	</script>
	<c:choose>
		<c:when test="${!empty requestScope.msg }">
			<script type="text/javascript">
				layui.use('layer', function() {
					var $ = layui.$; //重点处
					var layer = layui.layer; //获得layer模块
					layer.open({
						type : 1,
						area : [ '240px', '120px' ],
						offset : [ '500px', '200px' ],
						title : '错误',
						content : '${requestScope.msg}'
					});
			
				});
			</script>
		</c:when>
	</c:choose>
</body>
</html>

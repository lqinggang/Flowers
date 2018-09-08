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
<title>我的订单</title>

<meta charset="UTF-8" />
<meta name="keywords" content="鲜花,销售,花之苑">
<meta name="description" content="我的订单">
<link href="static/layui/css/layui.css" rel="stylesheet" media="all">
<link href="static/pages/css/orders/order.css" rel="stylesheet"
	media="all">
<link href="static/pages/css/common/page.css" rel="stylesheet"
	media="all">
<style type="text/css">
.layui-form-pane .layui-form-label {
	width: 150px;
	border: 0;
	background-color: #FFF;
}

.layui-input-block {
	padding-top: 1%;
}

#orderinfo .info {
	margin-top: 10%;
}
</style>
<script type="text/javascript">
	var mainbody = this.parent.document.getElementById("mainbody");
	mainbody.height = 600 + 'px';
</script>
</head>

<body>
	<div id="orderinfo">
		<div class="info">
			<c:choose>
				<c:when test="${!empty requestScope.orderinfo }">
					<form class="layui-form layui-form-pane" id="infoform"
						action="order/${requestScope.page }" target="_top">
						<div class="layui-form-item">
							<label class="layui-form-label">订单编号：</label>
							<div class="layui-input-block">
								<div style="display:none;">
									<input name="orderid" value="${orderinfo.order_id }">
								</div>
								<span><a href="javascript:;">${orderinfo.order_id }</a></span>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">收件人：</label>
							<div class="layui-input-block">
								<span>${orderinfo.recipient }</span>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">联系方式：</label>
							<div class="layui-input-block">
								<span>${orderinfo.contact }</span>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">收件地址：</label>
							<div class="layui-input-block">
								<span>${orderinfo.address }</span>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">下单时间：</label>
							<div class="layui-input-block">
								<span>${orderinfo.date }</span>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">商品名称：</label>
							<div class="layui-input-block goods">
								<span><a
									href="mainPage/flowers/search?id=${orderinfo.flower_id.flower_id }"
									target="_top"><img alt="${orderinfo.flower_id.name }"
										src="${orderinfo.flower_id.image }"><span
										style="margin-left:5%;">${orderinfo.flower_id.name  }</span></a></span>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">订购数量：</label>
							<div class="layui-input-block">
								<span>${orderinfo.amount }</span>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">商品总价：</label>
							<div class="layui-input-block">
								<span class="price" style="color:red;">￥${orderinfo.price }</span>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">备注信息：</label>
							<div class="layui-input-block">
								<span>${orderinfo.note }</span>
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">订单状态：</label>
							<div class="layui-input-block">
								<span>${orderinfo.status_type_id.status_name }</span>
							</div>
						</div>
						<div style="display:none;">
							<input type="text" name="orderid" value="${orderinfo.order_id }">
							<input type="text" id="content" name="content">
						</div>
					</form>
				</c:when>
			</c:choose>
			<div>
				<c:choose>
					<c:when test="${!empty requestScope.page }">
						<div class="layui-form-item">
							<div class="layui-input-block" style="text-align:center;">
								<button class="layui-btn" onclick="infosubmit();">${requestScope.info }</button>
							</div>
						</div>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
</body>
<script src="static/pages/js/common/jquery-1.7.2.js"></script>
<script src="static/layui/layui.js" type="text/javascript"></script>
<script type="text/javascript">
	function infosubmit() {
		if ("${requestScope.page }" == "cancel") { //订单取消时显示
			layui.use('layer', function() {
				var layer = layui.layer; //获得layer模块
				var index = layer.prompt({
					title : '订单取消原因',
					formType : 2,
					shadeClose : true //点击遮罩关闭
				},
					function(value, index) {
						var content = this.document.getElementById("content");
						content.value = value;
						var form = this.document.getElementById("infoform");
						form.submit();
					});
			});
		} else if ("${requestScope.page }" == "purchase") { //订单评价
			layui.use('layer', function() {
				var layer = layui.layer;
				//iframe窗
				var index = layer.open({
					type : 2,
					title : false,
					closeBtn : 1, //显示关闭按钮
					shade : [ 0 ],
					area : [ '500px', '600px' ],
					offset : 'auto',
					anim : 2,
					content : [ 'order/purchase?orderid=${requestScope.orderinfo.order_id } ', 'no' ] //iframe的url，no代表不显示滚动条

				});
			})

		} else {
			var form = this.document.getElementById("infoform");
			form.submit();
		}
	}
</script>
</html>

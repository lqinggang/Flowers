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
<link href="static/pages/css/service/service.css" rel="stylesheet"
	type="text/css" />
<link href="static/pages/css/common/page.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
	var mainbody = this.parent.document.getElementById("mainbody");
	mainbody.height = 1400 + 'px';
</script>
<style type="text/css">
body {
	height: 1400px;
}

tr td {
	text-align: center;
}

th {
	background-color: #f9f9f9;
}

.navigation span  .count {
	position: relative;
	top: -5px;
	left: 97%;
	width: 15px;
	height: 15px;
	border-radius: 45px;
	text-align: center;
	color: #FFF;
	font-size: 14px;
	margin: 0;
	background-color: red;
}
</style>
<script type="text/javascript">
	function Jump(location) {
		window.location.href = location;
	}
</script>
</head>

<body>
	<div id="orders">
		<div id="orderssearch">
			<div class="navigation">
				<span><a href="javascript:jump('#orderssearch');"
					target="mainbody">订单查询</a></span>

				<c:choose>
					<c:when test="${!empty sessionScope.username }">
						<span><a
							style="border:1px solid #C0C0C0;border-style:none solid;padding: 0 17px;"
							href="service/servicepage?page=myorders" target="_top">全部订单</a></span>
						<span><a href="service/servicepage?page=myorders&type=2"
							target="_top"
							style="border-right:1px solid #C0C0C0;padding-right: 22px;float:left;">待签收
								<c:choose>
									<c:when
										test="${!empty requestScope.receiptCount && requestScope.receiptCount != 0}">
										<span class="count">${requestScope.receiptCount }</span>
									</c:when>
								</c:choose>
						</a></span>
						<span><a href="service/servicepage?page=myorders&type=3"
							target="_top" style="padding-right: 22px;float:left;">待评价<c:choose>
									<c:when
										test="${!empty requestScope.evaluatedCount && requestScope.evaluatedCount != 0 }">
										<span class="count">${requestScope.evaluatedCount } </span>
									</c:when>
								</c:choose></a></span>
					</c:when>
					<c:otherwise>
						<span><a
							style="border:1px solid #C0C0C0;border-style:none solid;padding: 0 17px;"
							href="users/loginpage" target="_top">全部订单</a></span>
						<span><a href="users/loginpage" target="_top"
							style="border-right:1px solid #C0C0C0;padding-right: 22px;float:left;">待签收
								<c:choose>
									<c:when
										test="${!empty requestScope.receiptCount && requestScope.receiptCount != 0}">
										<span class="count">${requestScope.receiptCount }</span>
									</c:when>
								</c:choose>
						</a></span>
						<span><a href="users/loginpage" target="_top"
							style="padding-right: 22px;float:left;">待评价<c:choose>
									<c:when
										test="${!empty requestScope.evaluatedCount && requestScope.evaluatedCount != 0 }">
										<span class="count">${requestScope.evaluatedCount } </span>
									</c:when>
								</c:choose></a></span>
					</c:otherwise>
				</c:choose>


			</div>
			<form id="orderform" class="layui-form layui-form-pane"
				target="mainbody" action="order/orderinfo" method="post"
				style="margin-left:15%;margin-top:5%;">
				<div class="layui-form-item">
					<label class="layui-form-label" style="width:150px;">订单编号：</label>
					<div class="layui-input-block">
						<input type="text" name="orderid" id="orderid"
							placeholder="请输入订单编号" autocomplete="off" class="layui-input"
							style="width:70%;">
					</div>
				</div>

				<div class="layui-form-item"
					style="margin-bottom:5%;margin-left:25%;">
					<div class="layui-input-block button">
						<c:choose>
							<c:when
								test="${empty sessionScope.username || sessionScope.username.length()==0}">
								<button class="layui-btn layui-btn-disabled" lay-submit
									id="submit" disabled="disabled">确认</button>
							</c:when>
							<c:otherwise>
								<button class="layui-btn" lay-submit id="submit">确认</button>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</form>
		</div>
		<div id="allorders">
			<div class="navigation">
				<span><a href="javascript:jump('#orderssearch');"
					target="mainbody">订单查询</a></span>

				<c:choose>
					<c:when test="${!empty sessionScope.username }">
						<span><a
							style="border:1px solid #C0C0C0;border-style:none solid;padding: 0 17px;"
							href="service/servicepage?page=myorders" target="_top">全部订单</a></span>
						<span><a href="service/servicepage?page=myorders&type=2"
							target="_top"
							style="border-right:1px solid #C0C0C0;padding-right: 22px;float:left;">待签收
								<c:choose>
									<c:when
										test="${!empty requestScope.receiptCount && requestScope.receiptCount != 0}">
										<span class="count">${requestScope.receiptCount }</span>
									</c:when>
								</c:choose>
						</a></span>
						<span><a href="service/servicepage?page=myorders&type=3"
							target="_top" style="padding-right: 22px;float:left;">待评价<c:choose>
									<c:when
										test="${!empty requestScope.evaluatedCount && requestScope.evaluatedCount != 0 }">
										<span class="count">${requestScope.evaluatedCount } </span>
									</c:when>
								</c:choose></a></span>
					</c:when>
					<c:otherwise>
						<span><a
							style="border:1px solid #C0C0C0;border-style:none solid;padding: 0 17px;"
							href="users/loginpage" target="_top">全部订单</a></span>
						<span><a href="users/loginpage" target="_top"
							style="border-right:1px solid #C0C0C0;padding-right: 22px;float:left;">待签收
								<c:choose>
									<c:when
										test="${!empty requestScope.receiptCount && requestScope.receiptCount != 0}">
										<span class="count">${requestScope.receiptCount }</span>
									</c:when>
								</c:choose>
						</a></span>
						<span><a href="users/loginpage" target="_top"
							style="padding-right: 22px;float:left;">待评价<c:choose>
									<c:when
										test="${!empty requestScope.evaluatedCount && requestScope.evaluatedCount != 0 }">
										<span class="count">${requestScope.evaluatedCount } </span>
									</c:when>
								</c:choose></a></span>
					</c:otherwise>
				</c:choose>

			</div>
			<div>
				<c:choose>
					<c:when test="${!empty sessionScope.username }">
						<!-- 用户已登录 -->
						<c:choose>
							<c:when test="${!empty requestScope.orderList }">
								<table
									style="border:1px solid #C0C0C0;margin-top:1px;width:100%;cellspacing:0;"
									border="1">
									<tr style="height:40px;width:100%;">
										<th class="commoditynumber">订单编号</th>
										<th class="commodityname">商品名称</th>
										<th class="commoditydate">下单时间</th>
										<th class="commodityprice">总价</th>
										<th class="commoditystatus">状态</th>
									</tr>
									<c:forEach items="${requestScope.orderList }" var="order">
										<tr>
											<td class="commoditynumber"><a
												href="order/orderinfo?orderid=${order.order_id }">${order.order_id }</a></td>
											<td class="commodityname" style="padding:3%;"><a
												href="mainPage/flowers/search?id=${order.flower_id.flower_id }"
												target="_top"><img alt="${order.flower_id.name }"
													src="${order.flower_id.image }"><span
													style="margin-left:5%;">${order.flower_id.name  }</span></a></td>
											<td class="commoditydate"><span>${order.date }</span></td>
											<td class="commodityprice"><span style="	color: red;">￥${order.price }</span></td>
											<td class="commoditystatus"><span>${order.status_type_id.status_name }</span></td>
										</tr>
									</c:forEach>
								</table>
								<form action="service/myorders" id="myordersform">
									<input name="curr" id="curr" type="hidden"
										value="${requestScope.curr }"></input>
									<div id="page" style="text-align:center;"></div>
								</form>
								<script src="static/layui/layui.js" type="text/javascript"></script>
								<script type="text/javascript">
									layui.use('laypage', function() {
										var laypage = layui.laypage;
										var currpage = document.getElementById("curr").value;
										if (currpage == null || (currpage != null && curr.length == 0)) {
											currpage = 0;
										}
										laypage.render({
											elem : 'page', //注意，这里的 test1 是 ID，不用加 # 号
											layout : [ 'prev', 'page', 'next', 'skip', 'count' ], //自定义分页布局 
											count : '${requestScope.count}', //数据总数，从服务端得到
											curr : currpage,
											groups : 5,
											limit : 6,
											jump : function(obj, first) {
												console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
												console.log(obj.limit); //得到每页显示的条数
												//首次不执行
												if (!first) {
													setCurrentPage(obj.curr);
													var form = document.getElementById("myordersform");
													form.submit();
												}
											}
										});
									});
									function setCurrentPage(curr) {
										document.getElementById("curr").value = curr ;
									}
								</script>
							</c:when>
							<c:otherwise>
								<div style="text-align:center;margin:30%;">╰(￣▽￣)╭
									没有找到相关的订单哦</div>
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<div style="text-align:center;margin:40%;">
							╰(￣▽￣)╭ 您尚未<a href="users/loginpage" target="_top">登录</a>！
						</div>
					</c:otherwise>
				</c:choose>

			</div>
		</div>

	</div>
	<c:choose>
		<c:when test="${!empty requestScope.msg }">
			<!-- <script src="static/pages/js/common/jquery-1.7.2.js"></script> -->
			<script src="static/layui/layui.js" type="text/javascript"></script>
			<script type="text/javascript">
				layui.use('layer', function() {
					var $ = layui.$; //重点处
					var layer = layui.layer; //获得layer模块
					layer.open({
						type : 1,
						area : [ '240px', '120px' ],
						offset : [ '250px', '600px' ],
						title : '信息',
						content : '${requestScope.msg}'
					});
			
				});
			</script>
		</c:when>
	</c:choose>
</body>

<script type="text/javascript">
	function jump(location) {
		window.location.hash = location;
		location = null;
	}
</script>
</html>

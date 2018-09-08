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

<meta charset="UTF-8" />
<meta name="keywords" content="鲜花,销售,花之苑">
<meta name="description" content="用户购物车">
<link rel="stylesheet" href="static/layui/css/layui.css" />
<script src="static/layui/layui.js" type="text/javascript"></script>
<!-- <link rel="stylesheet" href="static.pages/css/reset.css"> -->
<link rel="stylesheet" href="static/pages/css/carts.css">

<style type="text/css">
body {
	height: 1600px;
}

#cartcatalog {
	font-size: 18px;
}

#cartinfo {
	height: 50%;
}

#cartinfo .goods  .col {
	border: 1px solid #cecece;
	height: 9%;
	float: left;
}

#cartinfo .goods  .col .price {
	padding-top: 20%;
}

.layui-btn-primary {
	border: none;
}

#cartinfo .goods .col {
	height: 15%;
}
</style>
</head>

<body>

	<div id="cartcatalog">
		<c:choose>
			<c:when test="${!empty requestScope.mycartList }">

				<div style="height:25px;width:50px;float:right;margin:3%;">
					<button type="button" id="edit"
						class="layui-btn layui-btn-radius layui-btn-primary"
						onclick="editbutton();"
						style="height:100%;width:100%;display:block;">编辑</button>
					<button type="button" id="fulfill"
						class="layui-btn layui-btn-radius layui-btn-primary"
						onclick="fulfillbutton();"
						style="height:100%;width:100%;display:none;">完成</button>
					<script type="text/javascript">
						function editbutton() {
							document.getElementById('edit').style.display = document.getElementById('edit').style.display == "none" ? "block" : "none";
							document.getElementById('fulfill').style.display = document.getElementById('fulfill').style.display == "block" ? "none" : "block";
							$('.reduce').show();
							$('.plus').show();
						}
					</script>
					<script type="text/javascript">
						function fulfillbutton() {
							document.getElementById('fulfill').style.display = "none";
							document.getElementById('edit').style.display = "block";
							$('.reduce').hide();
							$('.plus').hide();
							$('#cartinfo').submit();
						}
					</script>

				</div>
				<div id="cartlist">
					<table
						style="width:100%;cellspacing:0;cellpadding:0;background-color:#f3f3f3;border:1px solid #cecece;">
						<tr class="carthead" style="height:50px;">
							<td style="width:50%;padding-left:5%;">
								<!--所有商品全选--> <input type="checkbox" id="all"
								class="whole_check"> <label for="all"
								style="margin-right:25%;">全选</label> 商品名称
							</td>
							<td style="width:15%;padding-left:7%;">价格</td>
							<td style="width:20%;padding-left:7%;">数量</td>
							<td style="width:10%;padding-left:3%;">操作</td>
						</tr>
					</table>

					<div style="clear:both;"></div>


					<script type="text/javascript"
						src="static/pages/js/num-alignment.js"></script>
					<script src="static/jquery/jquery-1.11.0.min.js"></script>

					<form id="cartinfo" action="users/updatecart">
						<c:forEach items="${requestScope.mycartList }" var="mycart"
							varStatus="id" begin="0">
							<div style="align:left;" class="goods">
								<div style="width:54%;" class="col">
									<%-- 				<input type="checkbox"
										name="check${mycart.flower_id.flower_id }"
										id="check${mycart.flower_id.flower_id }" class="son_check"
										value="${mycart.flower_id.flower_id }"
										style="padding-right:5%;margin-left:10%;"> <label
										for="${mycart.flower_id.flower_id }"></label> <a
										href="flowers/searchflowers?id=${mycart.flower_id.flower_id }"><img
										alt="flowersimg" src="${mycart.flower_id.image }"
										style="width:60px;height:60px;padding:5%;">
										${mycart.flower_id.name }</a> --%>


									<c:choose>
										<c:when test="${mycart.flower_id.quantity == 0 }">

											<script type="text/javascript">
												this.document.getElementById("all").disabled = true;
											</script>

											<input type="checkbox" onclick="return false;"
												disabled="disabled"
												style="padding-right:5%;margin-left:10%;">
											<label for="${mycart.flower_id.flower_id }"></label>
											<a
												href="flowers/searchflowers?id=${mycart.flower_id.flower_id }"
												title="失效"><img alt="flowersimg"
												src="${mycart.flower_id.image }"
												style="width:60px;height:60px;padding:5%;"> <span
												style="text-decoration:line-through;">${mycart.flower_id.name }</span></a>
										</c:when>
										<c:otherwise>
											<input type="checkbox"
												name="check${mycart.flower_id.flower_id }"
												id="check${mycart.flower_id.flower_id }" class="son_check"
												value="${mycart.flower_id.flower_id }"
												style="padding-right:5%;margin-left:10%;">
											<label for="${mycart.flower_id.flower_id }"></label>
											<a
												href="flowers/searchflowers?id=${mycart.flower_id.flower_id }"><img
												alt="flowersimg" src="${mycart.flower_id.image }"
												style="width:60px;height:60px;padding:5%;">
												${mycart.flower_id.name }</a>
										</c:otherwise>
									</c:choose>


								</div>
								<div style="width:15%;" class="col">
									<div style="color:red;padding-top:25%;text-align:center;"
										class="price">￥${mycart.flower_id.price }</div>
								</div>
								<div style="width:20%;text-align:center;"
									class="order_amount col">
									<div style="margin-top:20%;margin-left:20%;">
										<input id="${mycart.flower_id.flower_id }"
											name="${mycart.flower_id.flower_id }" style="padding-top:5%;"
											class="alignment amount" value="${mycart.amount }"
											data-max="${mycart.flower_id.quantity}" />
									</div>
								</div>
								<div style="width:10%;" class="col">
									<a class="layui-btn layui-btn-primary layui-btn-sm remove"
										style="padding-left:40%;padding-top:40%;"
										onclick="deletecart('${mycart.flower_id.name }','${mycart.flower_id.flower_id }');"
										target="_top"> <i class="layui-icon">&#xe640;</i>
									</a>
								</div>

							</div>
							<script type="text/javascript">
							
								// 初始化
								alignmentFns.initialize();
							
								// 销毁
								alignmentFns.destroy();
							
								// 初始化
								alignmentFns.initialize();
							</script>
						</c:forEach>

						<input name="curr" id="curr" type="hidden"
							value="${requestScope.curr }"></input>

						<div id="page" style="text-align:center;"></div>
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
											var form = document.getElementById("cartinfo");
											form.action = "users/mycart";
											$('#cartinfo').submit();
										}
									}
								});
							});
							function setCurrentPage(curr) {
								document.getElementById("curr").value = curr ;
							}
						</script>
					</form>
				</div>
			</c:when>
			<c:otherwise>
				<div id="cartisnull"
					style="margin-top:10%;margin-bottom:10%;text-align:center;">
					<div>:)&nbsp;您的购物车里面没有宝贝哦</div>
				</div>
				<div>
					<jsp:include page="/WEB-INF/view/pages/jsp/common/recommend.jsp"></jsp:include>
				</div>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${!empty requestScope.mycartList }">
				<!--底部-->
				<script src="static/pages/js/carts.js"></script>
				<div class="bar-wrapper" style="width:99%;margin-right:5%;">
					<div class="bar-right">
						<div class="piece">
							已选商品<strong class="piece_num">0</strong>件
						</div>
						<div class="totalMoney">
							共计: <strong class="total_text">0.00</strong>
						</div>
						<div class="calBtn">
							<a href="javascript:settleAccounts();" target="mainbody">结算</a>
						</div>
					</div>
				</div>
			</c:when>

		</c:choose>

		<c:choose>
			<c:when
				test="${!empty requestScope.mycartList && requestScope.mycartList.size() <=10  }">
				<div style="clear:both;" id="mycartrecomend">
					<jsp:include page="/WEB-INF/view/pages/jsp/common/recommend.jsp"></jsp:include>

				</div>
			</c:when>
		</c:choose>

	</div>
	<div style="display:none;">
		<form action="users/deleteofcart" id="delete_form" target="_top">
			<input type="text" name="delete_flower_id" id="delete_flower_id"
				value="">
		</form>
	</div>


	<script src="static/pages/js/common/jquery-1.7.2.js"></script>

	<script type="text/javascript">
		function deletecart(flowername, flowerid) {
			layui.use('layer', function() {
				var $ = layui.$,
					form = layui.form,
					layer = layui.layer;
				layer.open({
					type : 1,
					title : 'WARNING',
					content : '<span style="font-size:18px;align:center;">确定移除<span style="color:red;">' + flowername + '？</span></span>',
					btn : [ '确定', '取消' ],
					shade : [ 0.5, '#000' ],
					maxmin : true,
					adnim : 2,
					yes : function() {
						var form = document.getElementById("delete_form");
						var delete_flower_id = document.getElementById("delete_flower_id");
						delete_flower_id.value = flowerid;
						form.submit();
	
						return true;
					},
					no : function() {
						return;
					}
				})
			})
		}
	
		function settleAccounts() {
			var form = document.getElementById("cartinfo");
			form.action = "users/buypage";
			form.submit();
		}
	</script>
</body>
</html>
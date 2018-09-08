<%@page import="com.lqinggang.entity.flowers.Flowers"%>
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
<title>花之苑</title>
<meta charset="utf-8">
<meta name="description" content="鲜花销售平台">
<meta name="keywords" content="鲜花">
<link href="static/pages/css/flowers/flowers.css" rel="stylesheet"
	type="text/css" />
<link href="static/layui/css/layui.css" rel="stylesheet" type="text/css" />
<!-- <script type="text/javascript">
	function changheight() {
		var mainbody = this.parent.document.getElementById("mainbody");
		mainbody.height = 2500 + 'px';
	}
</script> -->
<style type="text/css">
#showflowers .flowersimage img {
	float: left;
	width: 330px;
	height: 330px;
}

#showflowers .flowersinfo {
	float: left;
	margin-left: 5%;
	width: 50%;
	height: 8%;
}

.flowersdescription .description-link {
	height: 30px;
}

.flowersdescription .description {
	height: 78%;
}

.flowersdescription .evaluation {
	width: 100%;
	height: 20%;
}

.content-info {
	font: 12px/18px "宋体", arial, sans-serif;
	float: left;
	width: 40%;
	font-size: 16px;
	color: #7b7b7b !important;
	margin-top: 4%;
	margin-left: 2%;
	padding: 1%;
	border: 1px solid #C0C0C0;
}

.content-info table tr {
	height: 30px;
}

.content-info table tr>:first-child {
	width: 65px;
}

body {
	height: 2500px;
}
/* bigImg */
.bigImg {
	position: relative;
	float: left;
	/* 	width: 400px;
	height: 400px; */
	overflow: hidden;
}

.bigImg #midimg {
	width: 400px;
	height: 400px;
}

.bigImg #winSelector {
	width: 235px;
	height: 210px;
}

#winSelector {
	position: absolute;
	cursor: crosshair;
	filter: alpha(opacity = 15);
	-moz-opacity: 0.15;
	opacity: 0.15;
	background-color: #000;
	border: 1px solid #fff;
}

/* bigView */
#bigView {
	position: absolute;
	border: 1px solid #959595;
	overflow: hidden;
	z-index: 9999;
}

#bigView img {
	position: absolute;
}
</style>

</head>

<body>
	<div id="showflowers">
		<div class="position">
			当前位置：<a href="mainPage/index" target="_top" style="font-size:18px;">首页</a>
			>>
			<c:choose>
				<c:when test="${!empty searchresultList }">
					<c:forEach items="${searchresultList }" var="flower" begin="0"
						end="1">
						<a href="flowers/searchflowers?id=${flower.flower_id }">${flower.name  }</a>
					</c:forEach>
				</c:when>
			</c:choose>
		</div>
		<c:choose>
			<c:when test="${!empty searchresultList }">
				<script src="static/jquery/jquery.1.4.2-min.js"
					type="text/javascript"></script>
				<c:forEach items="${searchresultList }" var="flower" begin="0"
					end="1">
					<div class="preview">

						<div class="flowersimage bigImg" id="vertical">
							<img alt="${flower.name }" src="${flower.image }"
								style="border:1px solid #C0C0C0;" id="midimg">
							<!-- 	<div style="display:none;" id="winSelector"></div> -->
						</div>

						<!-- 						<div id="bigView" style="display:none;">
							<img style="width:800px;height:800px;" alt="" src="" />
						</div> -->

						<div class="flowersinfo">
							<span class="name">${flower.name }</span> <span class="separate"></span>
							<span class="detailed"> <span>颜色：${flower.color }</span> <span>枝数：${flower.amount }</span>
								<span>库存量：${flower.quantity }</span> <span>花语：${flower.description }</span>
								<span>产地：${flower.origin }</span>
							</span> <span class="separate"></span> <span class="price"
								style="boder:1px solid green;"> <span>价格：</span> <span
								class="pricenumber">￥${flower.price }</span> <span class="buy"
								style="margin-left:20px;"> <span> <c:choose>
											<c:when test="${flower.quantity == 0}">
												<a href="javascript:return false;" target="_top"
													title='库存量为0'>加入购物车</a>
											</c:when>
											<c:otherwise>
												<a href="users/addtocart?flower_id=${flower.flower_id}"
													target="_top">加入购物车</a>
											</c:otherwise>
										</c:choose>
								</span>
							</span>
							</span>
						</div>
						<div style="border:1px solid #C0C0C0;clear:both;"></div>

						<div class="flowersdescription" style="margin-top:1px;">
							<div class="description-link">
								<span onclick="jump('#product_details')"> 商品描述</span> <span
									onclick="jump('#product_review')">商品评价</span>
							</div>
							<div class="description" id="product_details"
								style="border-bottom:1px solid #C0C0C0;">
								<%-- <p>${flower.content }<br>这里填写商品描述信息 --%>
								<c:choose>
									<c:when test="${!empty requestScope.flowerscontentList }">
										<c:forEach items="${requestScope.flowerscontentList }"
											var="content">
											<div class="contentimage"
												style="margin-bottom:10px;float:left;">
												<img alt="${flower.name }的描述信息" src="${content }"
													style="width:54%;height:300px;border:1px solid #C0C0C0;float:left;">
												<div class="content-info" style="color:#7b7b7b;">
													${requestScope.searchresultList.get(0).content_info }</div>
											</div>
										</c:forEach>
									</c:when>

								</c:choose>
								<div>
									<img alt="关于贺卡"
										src="static/pages/images/page/greetingCards.jpg"
										style="width:100%;height:350px;">
								</div>
								<div>
									<img alt="说明" src="static/pages/images/page/instructions2.jpg"
										style="width:100%;height:580px;">
								</div>
							</div>
							<div style="clear:both;"></div>
							<div class="description-link">
								<span onclick="jump('#product_details')"> 商品描述</span> <span
									onclick="jump('#product_review')">商品评价</span>
							</div>
							<div class="evaluation" id="product_review">

								<form action="flowers/searchflowers" id="purchase">
									<c:choose>
										<c:when test="${!empty requestScope.purchasesList }">
											<c:forEach items="${requestScope.purchasesList }"
												var="purchase">
												<div class="evaluation_entry"
													style="height:10%;border-bottom:1px dashed #C0C0C0;padding:0 3% 5% 5%;color:#676767;">
													<div style="padding-top:3%;float:left;width:20%;">
														<span style="margin-right:5%;">${purchase.order_id.person_id.name }</span>
														<span>总体评价：${purchase.commodity }</span>
													</div>

													<div
														style="text-align:center;font-size:18px;float:left;width:50%;padding-top:10px;">${purchase.purchase_content }</div>
													<div style="clear:both;"></div>
													<div style="text-align:right;">${purchase.date }</div>
												</div>
											</c:forEach>

											<input name="curr" id="curr" type="hidden"
												value="${requestScope.curr }">
											<input name="id" type="hidden"
												value="${requestScope.flowerid }">

											<div id="page" style="margin-top:5%;margin-left:25%;"></div>
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
														limit : 3,
														jump : function(obj, first) {
															console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
															console.log(obj.limit); //得到每页显示的条数
															//首次不执行
															if (!first) {
																setCurrentPage(obj.curr);
																var form = document.getElementById("purchase");
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
											<div style="text-align:center;">
												<p
													style="align:center;color:#C0C0C0;font-size:18px;margin:8%;">暂无评价</p>
											</div>
										</c:otherwise>
									</c:choose>


								</form>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div id="flowersnotexists"></div>
			</c:otherwise>
		</c:choose>

	</div>
</body>

<script type="text/javascript">
	function jump(location) {
		window.location.hash = location;
		location = null;
	}
</script>
<!-- 
<script type="text/javascript">

	$(document).ready(function() {
		// 解决 ie6 select框 问题

		$.fn.decorateIframe = function(options) {

			if ($.browser.msie && $.browser.version < 7) {

				var opts = $.extend({}, $.fn.decorateIframe.defaults, options);

				$(this).each(function() {

					var $myThis = $(this);

					//创建一个IFRAME

					var divIframe = $("<iframe />");

					divIframe.attr("id", opts.iframeId);

					divIframe.css("position", "absolute");

					divIframe.css("display", "none");

					divIframe.css("display", "block");

					divIframe.css("z-index", opts.iframeZIndex);

					divIframe.css("border");

					divIframe.css("top", "0");

					divIframe.css("left", "0");

					if (opts.width == 0) {

						divIframe.css("width", $myThis.width() + parseInt($myThis.css("padding")) * 2 + "px");

					}

					if (opts.height == 0) {

						divIframe.css("height", $myThis.height() + parseInt($myThis.css("padding")) * 2 + "px");

					}

					divIframe.css("filter", "mask(color=#fff)");

					$myThis.append(divIframe);

				});

			}

		}

		$.fn.decorateIframe.defaults = {
			iframeId : "decorateIframe1",

			iframeZIndex : -1,

			width : 0,

			height : 0
		}

		//放大镜视窗

		$("#bigView").decorateIframe();

		//点击到中图

		var midChangeHandler = null;

		function midChange(src) {
			$("#midimg").attr("src", src).load(function() {

				changeViewImg();

			});

		}

		//大视窗看图

		function mouseover(e) {
			if ($("#winSelector").css("display") == "none") {

				$("#winSelector,#bigView").show();

			}

			$("#winSelector").css(fixedPosition(e));

			e.stopPropagation();

		}

		function mouseOut(e) {
			if ($("#winSelector").css("display") != "none") {

				$("#winSelector,#bigView").hide();

			}

			e.stopPropagation();

		}

		$("#midimg").mouseover(mouseover); //中图事件

		$("#midimg,#winSelector").mousemove(mouseover).mouseout(mouseOut); //选择器事件



		var $divWidth = $("#winSelector").width(); //选择器宽度

		var $divHeight = $("#winSelector").height(); //选择器高度

		var $imgWidth = $("#midimg").width(); //中图宽度

		var $imgHeight = $("#midimg").height(); //中图高度

		var $viewImgWidth = $viewImgHeight = $height = null; //IE加载后才能得到 大图宽度 大图高度 大图视窗高度



		function changeViewImg() {
			$("#bigView img").attr("src", $("#midimg").attr("src").replace("mid", "big"));

		}

		changeViewImg();

		$("#bigView").scrollLeft(0).scrollTop(0);

		function fixedPosition(e) {
			if (e == null) {

				return;

			}

			var $imgLeft = $("#midimg").offset().left; //中图左边距

			var $imgTop = $("#midimg").offset().top; //中图上边距

			X = e.pageX - $imgLeft - $divWidth / 2; //selector顶点坐标 X

			Y = e.pageY - $imgTop - $divHeight / 2; //selector顶点坐标 Y

			X = X < 0 ? 0 : X;

			Y = Y < 0 ? 0 : Y;

			X = X + $divWidth > $imgWidth ? $imgWidth - $divWidth : X;

			Y = Y + $divHeight > $imgHeight ? $imgHeight - $divHeight : Y;



			if ($viewImgWidth == null) {

				$viewImgWidth = $("#bigView img").outerWidth();

				$viewImgHeight = $("#bigView img").height();

				if ($viewImgWidth < 200 || $viewImgHeight < 200) {

					$viewImgWidth = $viewImgHeight = 800;

				}

				$height = $divHeight * $viewImgHeight / $imgHeight;

				$("#bigView").width($divWidth * $viewImgWidth / $imgWidth);

				$("#bigView").height($height);

			}

			var scrollX = X * $viewImgWidth / $imgWidth;

			var scrollY = Y * $viewImgHeight / $imgHeight;

			$("#bigView img").css({
				"left" : scrollX * -1,
				"top" : scrollY * -1
			});

			$("#bigView").css({
				"top" : 0,
				"left" : $(".preview").offset().left + $(".preview").width() + 15
			});

			return {
				left : X,
				top : Y
			};

		}

	});
</script> -->
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">

<title>订单评价</title>

<meta charset="UTF-8" />
<meta name="keywords" content="鲜花,销售,花之苑">
<meta name="description" content="我的订单">
<link href="static/pages/css/orders/star.css" rel="stylesheet"
	type="text/css">
<link rel="stylesheet" href="static/layui/css/layui.css" />
<script type="text/javascript" src="static/layui/layui.js"></script>
<script type="text/javascript" src="static/pages/js/star.js"></script>

<style type="text/css">
.evaluation_entry {
	font-size: 20px;
	padding: 3%;
}
</style>
</head>

<body>
	<div id="AddDP" style="height:100%;width:100%;">
		<form name="formappraisement" action="order/evaluate" method="post"
			class="layui-form layui-form-pane" target="_top">

			<div style="display:none;">
				<input id="orderid" name="orderid" value="${requestScope.orderid }"
					type="text">
				<!-- 订单编号 -->
				<input id="service" name="service" value="1" type="text">
				<!-- 服务评分 -->
				<input id="logistics" name="logistics" value="1" type="text">
				<!-- 物流评分 -->
				<input id="commodity" name="commodity" value="1" type="text">
				<!-- 商品评分 -->
				<!-- 				<input id="evaluation_content" name="evaluation_content" type="text">
				评语 -->
			</div>
			<div>
				<div class="Star">
					<div class="evaluation_entry" style="padding-top:5%;">
						<span>服务评价：</span><span class="number"></span><span class="Select">
							<a
							onMouseOver="javascript:setProfix('star_');showStars(1,'service');"
							onMouseOut="javascript:setProfix('star_');clearStars('service');"
							href="javascript:setProfix('star_');setStars(1,'service');">
								<img id="star_1" title="差(1)"
								src="static/pages/images/evaluate/icon_star_1.gif">
						</a> <a
							onMouseOver="javascript:setProfix('star_');showStars(2,'service');"
							onMouseOut="javascript:setProfix('star_');clearStars('service');"
							href="javascript:setProfix('star_');setStars(2,'service');">
								<img id="star_2" title="一般(2)"
								src="static/pages/images/evaluate/icon_star_1.gif">
						</a> <a
							onMouseOver="javascript:setProfix('star_');showStars(3,'service');"
							onMouseOut="javascript:setProfix('star_');clearStars('service');"
							href="javascript:setProfix('star_');setStars(3,'service');">
								<img id="star_3" title="好(3)"
								src="static/pages/images/evaluate/icon_star_1.gif">
						</a> <a
							onMouseOver="javascript:setProfix('star_');showStars(4,'service');"
							onMouseOut="javascript:setProfix('star_');clearStars('service');"
							href="javascript:setProfix('star_');setStars(4,'service');">
								<img id="star_4" title="很好(4)"
								src="static/pages/images/evaluate/icon_star_1.gif">
						</a> <a
							onMouseOver="javascript:setProfix('star_');showStars(5,'service');"
							onMouseOut="javascript:setProfix('star_');clearStars('service');"
							href="javascript:setProfix('star_');setStars(5,'service');">
								<img id="star_5" title="非常好(5)"
								src="static/pages/images/evaluate/icon_star_1.gif">
						</a>
						</span>
					</div>


					<div class="evaluation_entry">
						<span>物流评分：</span><span class="number"></span><span class="Select">
							<a
							onMouseOver="javascript:setProfix('logistics_');showStars(1,'logistics');"
							onMouseOut="javascript:setProfix('logistics_');clearStars('logistics');"
							href="javascript:setProfix('logistics_');setStars(1,'logistics');">
								<img id="logistics_1" title="差(1)"
								src="static/pages/images/evaluate/icon_star_1.gif">
						</a> <a
							onMouseOver="javascript:setProfix('logistics_');showStars(2,'logistics');"
							onMouseOut="javascript:setProfix('logistics_');clearStars('logistics');"
							href="javascript:setProfix('logistics_');setStars(2,'logistics');">
								<img id="logistics_2" title="一般(2)"
								src="static/pages/images/evaluate/icon_star_1.gif">
						</a> <a
							onMouseOver="javascript:setProfix('logistics_');showStars(3,'logistics');"
							onMouseOut="javascript:setProfix('logistics_');clearStars('logistics');"
							href="javascript:setProfix('logistics_');setStars(3,'logistics');">
								<img id="logistics_3" title="好(3)"
								src="static/pages/images/evaluate/icon_star_1.gif">
						</a> <a
							onMouseOver="javascript:setProfix('logistics_');showStars(4,'logistics');"
							onMouseOut="javascript:setProfix('logistics_');clearStars('logistics');"
							href="javascript:setProfix('logistics_');setStars(4,'logistics');">
								<img id="logistics_4" title="很好(4)"
								src="static/pages/images/evaluate/icon_star_1.gif">
						</a> <a
							onMouseOver="javascript:setProfix('logistics_');showStars(5,'logistics');"
							onMouseOut="javascript:setProfix('logistics_');clearStars('logistics');"
							href="javascript:setProfix('logistics_');setStars(5,'logistics');">
								<img id="logistics_5" title="非常好(5)"
								src="static/pages/images/evaluate/icon_star_1.gif">
						</a>
						</span>
					</div>




					<div class="evaluation_entry">
						<span>商品评价：</span><span class="number"></span><span class="Select">
							<a
							onMouseOver="javascript:setProfix('commodity_');showStars(1,'commodity');"
							onMouseOut="javascript:setProfix('commodity_');clearStars('commodity');"
							href="javascript:setProfix('commodity_');setStars(pingjiacommodity');">
								<img id="commodity_1" title="差(1)"
								src="static/pages/images/evaluate/icon_star_1.gif">
						</a> <a
							onMouseOver="javascript:setProfix('commodity_');showStars(2,'commodity');"
							onMouseOut="javascript:setProfix('commodity_');clearStars('commodity');"
							href="javascript:setProfix('commodity_');setStars(2,'commodity');">
								<img id="commodity_2" title="一般(2)"
								src="static/pages/images/evaluate/icon_star_1.gif">
						</a> <a
							onMouseOver="javascript:setProfix('commodity_');showStars(3,'commodity');"
							onMouseOut="javascript:setProfix('commodity_');clearStars('commodity');"
							href="javascript:setProfix('commodity_');setStars(3,'commodity');">
								<img id="commodity_3" title="好(3)"
								src="static/pages/images/evaluate/icon_star_1.gif">
						</a> <a
							onMouseOver="javascript:setProfix('commodity_');showStars(4,'commodity');"
							onMouseOut="javascript:setProfix('commodity_');clearStars('commodity');"
							href="javascript:setProfix('commodity_');setStars(4,'commodity');">
								<img id="commodity_4" title="很好(4)"
								src="static/pages/images/evaluate/icon_star_1.gif">
						</a> <a
							onMouseOver="javascript:setProfix('commodity_');showStars(5,'commodity');"
							onMouseOut="javascript:setProfix('commodity_');clearStars('commodity');"
							href="javascript:setProfix('commodity_');setStars(5,'commodity');">
								<img id="commodity_5" title="非常好(5)"
								src="static/pages/images/evaluate/icon_star_1.gif">
						</a>

						</span>
					</div>
					<div class="evaluation_entry" style="padding-top:15%;">
						<div class="layui-form-item layui-form-text">
							<div class="layui-input-block">
								<textarea name="evaluation_content" placeholder="请输入评语"
									class="layui-textarea" style="height:30%;"></textarea>
							</div>
						</div>
					</div>

					<div>
						<input name="evaluationimg" id="evaluationimg" type="hidden">
						<div style="padding-left:5%;">
							<span>添加图片 </span><img alt="添加图片"
								src="static/pages/images/page/addImg.png"
								style="height:80px;width:80px;" id="uploadimage">
							<div style="display:none;float:left;" id="deleteupload">
								<img alt="删除" src="static/pages/images/page/delete.png"
									onclick="javascript:deleteUpload();"
									style="height:20px;width:20px; position: relative;left: 150px;top:-5px;">
							</div>
						</div>
					</div>
					<script type="text/javascript">
					
						layui.use('upload', function() {
							var upload = layui.upload;
					
							//执行实例
							var upload = upload.render({
								elem : '#uploadimage', //绑定元素
								url : 'order/evaluate/upload', //上传接口  
								accept : 'images', //上传文件类型
								acceptMime : 'image/*',
								done : function(res) {
									layer.msg("添加成功");
									var uploadimage = document.getElementById("uploadimage");
									uploadimage.src = res.data.src + res.data.name;
									var evaluationimg = document.getElementById("evaluationimg");
									var img = '<img alt="评价图片" src="' + res.data.src + res.data.name + '" style="width:60px;height:60px;">';
									evaluationimg.value = img;
									var deleteupload = document.getElementById("deleteupload");
									deleteupload.style.display = "block";
					
								},
								error : function() {
									layer.msg("添加失败");
								//请求异常回调
								}
							});
						})
					</script>
					<script type="text/javascript">
						function deleteUpload() {
							var uploadimage = document.getElementById("uploadimage");
							uploadimage.src = "static/pages/images/page/addImg.png";
							var evaluationimg = document.getElementById("evaluationimg");
							evaluationimg.value = "";
							var deleteupload = document.getElementById("deleteupload");
							deleteupload.style.display = "none";
						}
					</script>

					<div class="layui-form-item">
						<div class="layui-input-block button">
							<button class="layui-btn" lay-submit id="submit"
								style="margin-left:20%;">立即提交</button>
						</div>
					</div>

				</div>
			</div>
			<div style="clear:both"></div>
		</form>
	</div>
</body>
</html>



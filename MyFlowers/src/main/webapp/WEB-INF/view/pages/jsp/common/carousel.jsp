<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<%@ include file="/WEB-INF/view/pages/jsp/common/pages-header.jsp"%>

<style type="text/css">
#top_carousel .carousel_images img {
	height: 350px !important;
}
</style>


</head>
<body>
	<script src="static/layui/layui.js" type="text/javascript"></script>
	<script type="text/javascript">
		layui.use('carousel', function() {
			var carousel = layui.carousel;
			//建造实例
			carousel.render({
				elem : '#top_carousel',
				width : '62%', //设置容器宽度
				height : 350,
				arrow : 'always' //始终显示箭头
			//,anim: 'updown' //切换动画方式
			});
		});
	</script>
	<div style="border:1px solid #f9f9f9;">
		<div id="carousel">
			<div id="top_sides" style="float:left;width:37%;margin-left:0.5%;">
				<img alt="Rose Only" src="static/pages/images/page/RoseOnly.jpeg"
					style="height:350px;">
			</div>
			<div class="layui-carousel" id="top_carousel" style="float:left;">
				<div carousel-item id="entry">
					<div class="carousel_images">
						<img alt="Images 1" src="static/pages/images/page/1.jpg">
					</div>
					<div class="carousel_images">
						<img alt="Images  2" src="static/pages/images/page/2.jpg">
					</div>
					<div class="carousel_images">
						<img alt="Images  3" src="static/pages/images/page/3.jpg">
					</div>
					<div class="carousel_images">
						<img alt="Images  4" src="static/pages/images/page/4.jpg">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</body>


</html>

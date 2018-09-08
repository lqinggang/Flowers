<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/base.jspf"%>
<%@ page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>">
<title>花之苑</title>

<meta charset="UTF-8" />
<meta name="keywords" content="鲜花,销售,花之苑">
<meta name="description" content="用户注册">

<%@ include file="/WEB-INF/view/pages/jsp/common/pages-header.jsp"%>

<style type="text/css">
iframe {
	width: 100%;
	/* 	height: 2300px; */
	border: none;
}

.body {
	width: 80%;
}

#users .layui-nav-tree .layui-nav-itemed:after {
	background-color: #FFF;
}

#users .layui-nav .layui-nav-item {
	line-height: 14px;
	text-decoration: none;
}

#users .layui-nav .layui-this:after, .layui-nav-bar, .layui-nav-tree .layui-nav-itemed:after
	{
	position: absolute;
	left: 0;
	top: 0;
	width: 0;
	height: 0px;
	background-color: #ffffff;
	transition: all .2s;
	-webkit-transition: all .2s
}

#users .layui-nav-child {
	top: 16px;
}

#users .layui-nav-item {
	margin: 0px;
}

#userlogin .layui-nav {
	background-color: #FFF;
	line-height: 14px;
	padding: 0px;
}

.layui-nav .layui-this:after, .layui-nav-bar, .layui-nav-tree .layui-nav-itemed:after
	{
	position: absolute;
	left: 0;
	top: 0;
	width: 0;
	height: 0px;
	background-color: #ffffff;
	transition: all .2s;
	-webkit-transition: all .2s
}

/* #content {
	width: 100vw;
	height: 100vh;
} */
</style>

</head>
<body>

	<div id="content"></div>

	<div id="layout">
		<div class="top">
			<!-- 引入pagetop.jsp -->
			<jsp:include page="/WEB-INF/view/pages/jsp/common/top.jsp" />
		</div>
		<div>
			<!-- 引入pagetop.jsp -->
			<jsp:include page="/WEB-INF/view/pages/jsp/common/carousel.jsp" />
		</div>
		<div style="float:left;" class="left">
			<!-- 引入左侧边栏1 -->
			<script type="text/javascript">
			</script>
			<jsp:include page="/WEB-INF/view/pages/jsp/common/left.jsp" />
		</div>
		<div class="body" style="float:left;">
			<iframe name="mainbody" id="mainbody"
				onload="this.height=this.contentWindow.document.body.scrollHeight"
				src="flowers/${requestScope.page }"
				style="frameborder: 0;scrolling:no;"></iframe>
		</div>
		<div style="float:left;" class="left">
			<jsp:include
				page="/WEB-INF/view/pages/jsp/common/customerservice.jsp"></jsp:include>
		</div>
		<!-- 引入pagebottom.jsp -->
		<jsp:include page="/WEB-INF/view/pages/jsp/common/bottom.jsp" />

	</div>

	<c:choose>
		<c:when test="${!empty requestScope.msg }">
			<!-- <script src="static/pages/js/common/jquery-1.7.2.js"></script>
			<script src="static/layui/layui.js" type="text/javascript"></script> -->
			<script type="text/javascript">
				layui.use('layer', function() {
					var $ = layui.$; //重点处
					var layer = layui.layer; //获得layer模块
					layer.open({
						type : 1,
						area : [ '240px', '120px' ],
						offset : [ '250px', '600px' ],
						title : '${requestScope.title}',
						content : '${requestScope.msg}'
					});
			
				});
			</script>
		</c:when>
	</c:choose>
</body>
<script>
	var a_idx = 0;
	jQuery(document).ready(function($) {
		$("body").click(function(e) {
			var a = new Array("富强", "民主", "文明", "和谐", "自由", "平等", "公正", "法治", "爱国", "敬业", "诚信", "友善");
			var $i = $("<span/>").text(a[a_idx]);
			a_idx = (a_idx + 1) % a.length;
			var x = e.pageX,
				y = e.pageY;
			$i.css({
				"z-index" : 999,
				"top" : y - 20,
				"left" : x,
				"position" : "absolute",
				"font-weight" : "bold",
				"color" : "#81c300"
			});
			$("body").append($i);
			$i.animate({
				"top" : y - 180,
				"opacity" : 0
			},
				1500,
				function() {
					$i.remove();
				});
		});
	});
</script>

</html>

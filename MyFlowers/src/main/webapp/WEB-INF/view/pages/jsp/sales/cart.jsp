<%@page import="com.lqinggang.entity.users.Cart"%>
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

<title>我的购物车</title>

<meta charset="UTF-8" />
<meta name="keywords" content="鲜花,销售,花之苑">
<meta name="description" content="用户注册">
<link rel="stylesheet" href="static/layui/css/layui.css" />
<%@ include file="/WEB-INF/view/pages/jsp/common/pages-header.jsp"%>
<style type="text/css">
iframe {
	width: 100%;
	/* height: 200%; */
	border: none;
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
</style>
<script type="text/javascript">
	var iframe = document.getElementById("mainbody");
	var iframeHeight = function() {
		var hash = window.location.hash.slice(1),
			h;
		if (hash && /height=/.test(hash)) {
			h = hash.replace("height=", "");
		}
		setTimeout(iframeHeight, 200);
	};
	iframeHeight();
</script>

</head>
<body>
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
			<!-- 引入左侧边栏 -->
			<jsp:include page="/WEB-INF/view/pages/jsp/common/left.jsp" />
		</div>
		<div class="body" style="float:left;">
			<iframe name="mainbody" id="mainbody"
				onload="this.height=this.contentWindow.document.body.scrollHeight"
				src="users/${requestScope.page }" width="100%"></iframe>
			<c:choose>
				<c:when test="${!empty requestScope.msg }">
					<script src="static/pages/js/common/jquery-1.7.2.js"></script>
					<!-- <script type="text/javascript" src="static/layui/layui.js"></script> -->
					<script type="text/javascript">
						layui.use('layer', function() {
							var $ = layui.$; //重点处
							var layer = layui.layer; //获得layer模块
							layer.open({
								type : 1,
								area : [ '240px', '120px' ],
								offset : [ '250px', '600px' ],
								title : '购买',
								content : '${requestScope.msg}'
							});
					
						});
					</script>
				</c:when>
			</c:choose>
		</div>
		<div style="float:left;" class="left">
			<jsp:include
				page="/WEB-INF/view/pages/jsp/common/customerservice.jsp"></jsp:include>
		</div>
		<!-- 引入pagebottom.jsp -->
		<jsp:include page="/WEB-INF/view/pages/jsp/common/bottom.jsp" />

	</div>
</body>
</html>

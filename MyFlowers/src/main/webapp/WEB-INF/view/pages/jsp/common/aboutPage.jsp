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
	/* 	height: 300%; */
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
</style>

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
			<!-- 引入左侧边栏1 -->
			<script type="text/javascript">
			</script>
			<jsp:include page="/WEB-INF/view/pages/jsp/common/left.jsp" />
		</div>
		<div class="body" style="float:left;">
			<iframe name="mainbody" id="mainbody"
				onload="this.height=this.contentWindow.document.body.scrollHeight"
				src="mainPage/about" style="frameborder: 0;scrolling:no;"></iframe>
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

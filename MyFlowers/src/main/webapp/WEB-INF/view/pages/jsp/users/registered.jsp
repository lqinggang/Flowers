<%@page import="org.springframework.web.context.annotation.RequestScope"%>
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
<title>用户资料补全</title>

<meta name="keywords" content="鲜花,销售,花之苑">
<meta name="description" content="用户注册">
<%@ include file="/WEB-INF/view/pages/jsp/common/pages-header.jsp"%>
<style type="text/css">
iframe {
	width: 100%;
	height: 230%;
	border: none;
}

.body {
	width: 80%;
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
		<div style="float:left;" class="body">
			<iframe name="mainbody" id="mainbody" src="users/registeredform"
				style="frameborder: 0;scrolling: no;"></iframe>
		</div>
		<c:choose>
			<c:when test="${!empty requestScope.msg }">
				<%
					Object name = request.getAttribute("name");
							request.setAttribute("name", name);
							Object password = request.getAttribute("password");
							request.setAttribute("password", password);
							Object telephone = request.getAttribute("telephone");
							request.setAttribute("telephone", telephone);
							Object email = request.getAttribute("email");
							request.setAttribute("email", email);
							Object address = request.getAttribute("address");
							request.setAttribute("address", address);
				%>
				<script src="static/pages/js/common/jquery-1.7.2.js"></script>
				<script type="text/javascript">
					layui.use('layer', function() {
						var $ = layui.$; //重点处
						var layer = layui.layer; //获得layer模块
						layer.open({
							type : 1,
							area : [ '240px', '120px' ],
							offset : 'auto',
							title : '注册出错',
							content : '${requestScope.msg}'
						});
				
					});
				</script>
			</c:when>
		</c:choose>
		<div style="float:left;" class="left">
			<jsp:include
				page="/WEB-INF/view/pages/jsp/common/customerservice.jsp"></jsp:include>
		</div>
		<div class="bottom">
			<!-- 引入pagebottom.jsp -->
			<jsp:include page="/WEB-INF/view/pages/jsp/common/bottom.jsp" />
		</div>
	</div>

</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/base.jspf"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>资料设置</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="keywords" content="鲜花,销售,花之苑">
<meta name="description" content="用户登录">
<%@ include file="/WEB-INF/view/pages/jsp/common/pages-header.jsp"%>
<style type="text/css">
iframe {
	width: 100%;
	height: 300%;
	border: none;
}

#layout .body {
	width: 80%;
	margin-left: 0px;
	margin-top: 0px;
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
			<iframe name="mainbody" id="mainbody" src="users/settingpage"
				style="frameborder: 0;scrolling: no;"> </iframe>
			<c:choose>
				<c:when test="${!empty requestScope.msg }">
					<script src="static/pages/js/common/jquery-1.7.2.js"></script>
					<script type="text/javascript">
						layui.use('layer', function() {
							var $ = layui.$;
							var layer = layui.layer; //获得layer模块
							layer.open({
								type : 1,
								area : [ '240px', '120px' ],
								offset : 'auto',
								title : '资料修改',
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
		<div class="bottom">
			<!-- 引入pagebottom.jsp -->
			<jsp:include page="/WEB-INF/view/pages/jsp/common/bottom.jsp" />
		</div>
	</div>
</body>
</html>

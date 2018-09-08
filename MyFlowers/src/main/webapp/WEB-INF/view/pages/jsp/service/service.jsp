<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>服务中心</title>

<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="鲜花,销售">
<meta http-equiv="description" content="鲜花销售平台">
<link href="static/pages/css/common/page.css" rel="stylesheet"
	type="text/css" />
<%@ include file="/WEB-INF/view/pages/jsp/common/pages-header.jsp"%>
<style type="text/css">
iframe {
	float: left;
	width: 100%;
	/* height: 1400px; */
	/* 	height: 200%; */
	border: none;
}

.body {
	margin-left: 2%;
	margin-top: 1.5%;
	margin-bottom: 5%;
	border: 1px solid #d2d2d2;
}
</style>
</head>
<body>
	<div id="content"></div>
	<div id="service">
		<div>
			<!-- 引入pagetop.jsp -->
			<jsp:include page="/WEB-INF/view/pages/jsp/common/top.jsp" />
		</div>
		<div>
			<!-- 引入pagetop.jsp -->
			<jsp:include page="/WEB-INF/view/pages/jsp/common/carousel.jsp" />
		</div>
		<div style="float:left;width:20%;">
			<!-- 引入serviceleft.jsp -->
			<jsp:include page="/WEB-INF/view/pages/jsp/service/serviceleft.jsp" />
		</div>
		<div class="body" style="float:left;width:75%;">
			<iframe name="mainbody" id="mainbody" frameborder="0" scrolling="no"
				onload="this.height=this.contentWindow.document.body.scrollHeight"
				src="service/${requestScope.page }?type=${requestScope.type } "></iframe>
		</div>
		<div style="float:left;" class="left">
			<jsp:include
				page="/WEB-INF/view/pages/jsp/common/customerservice.jsp"></jsp:include>
		</div>
		<div>
			<!-- 引入pagebottom.jsp -->
			<jsp:include page="/WEB-INF/view/pages/jsp/common/bottom.jsp" />
		</div>
	</div>
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

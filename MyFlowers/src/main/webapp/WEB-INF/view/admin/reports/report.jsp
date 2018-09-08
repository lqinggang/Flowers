<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<script type="text/javascript" src="static/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src="static/raphael/raphael.min.js"></script>
<script type="text/javascript" src="static/admin/js/script.js"></script>
<script src="static/admin/js/jquery-1.11.3.min.js"></script>
<script src="static/admin/js/jquery-ui.js"></script>
<script src="static/admin/js/select-widget-min.js"></script>
<script src="static/admin/js/jquery.animsition.min.js"></script>
<script src="static/admin/js/echarts.min.js"></script>
<script src="static/admin/js/macarons .js"></script>
<script src="static/admin/js/common.js"></script>

<style type="text/css">
.class {
	width: 99%;
	margin-bottom: 10px;
}

.data_wrap {
	margin: 10px auto;
}
/* #report .flowersreport {
	clear: both;
	width: 42%;
	height: 50%;
	border: 5px solid #C0C0C0;
	background: #efeff5;
	box-shadow: 5px 5px 2px #888888; 
} */
</style>
</head>

<body>

	<div>
		<div class="report">
			<jsp:include page="/WEB-INF/view/admin/reports/flowers.jsp"></jsp:include>
		</div>
		<div class="report">
			<jsp:include page="/WEB-INF/view/admin/reports/users.jsp"></jsp:include>
		</div>
	</div>


</body>
</html>

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

<title>用户信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" href="static/layui/css/layui.css" />
<script src="static/layui/layui.js" type="text/javascript"></script>

</head>

<body>
	<jsp:include page="/WEB-INF/view/admin/users/search.jsp"></jsp:include>

	<table class="layui-hide" id="usersinfo"></table>
	<!-- 分页 -->
	<div id="page" style="float:left;margin-left:5%;"></div>
	<!-- 当前页 -->
	<input type="hidden" name="curr" id="curr">

	<script>
		layui.use([ 'table', 'laypage', 'layer' ], function() {
			var table = layui.table,
				layer = layui.layer,
				laypage = layui.laypage;
			table.render({
				elem : '#usersinfo',
				url : 'admin/users/search?search=${requestScope.search}',
				page : false,
				cols : [ [
					{
						field : 'id',
						title : 'ID',
						width : 100,
						sort : true
					}
					, {
						field : 'name',
						width : 150,
						title : '用户名',
						sort : true
					}
					, {
						field : 'sex',
						width : 100,
						title : '性别',
						sort : true
					}
					, {
						field : 'age',
						title : '年龄',
						width : 100,
						sort : true
					}
					, {
						field : 'birtyday',
						title : '生日',
						minWidth : 150
					}
					, {
						field : 'register_date',
						title : '注册时间',
						width : 200,
						sort : true
					}
					, {
						field : 'email',
						width : 200,
						title : '电子邮件',
					}
					, {
						field : 'telephone',
						width : 150,
						title : '联系电话'
					}
					, {
						field : 'address',
						title : '联系地址',
						width : 400
					}
					, {
						field : 'rank',
						title : '等级',
						width : 100
					}
					, {
						field : 'experience',
						title : '经验',
						width : 100
					}
					, {
						field : 'discount',
						title : '折率',
						width : 100
					} ] ],
				done : function(res, curr, count) {
					laypage.render({
						layout : [ 'prev', 'page', 'next', 'skip', 'count' ], //自定义分页布局 
						elem : 'page', //注意，这里的 test1 是 ID，不用加 # 号
						count : count, //数据总数，从服务端得到
						limit : 50, //每页记录数
						curr : document.getElementById("curr").value,
						groups : 5, //显示 5 个连续页码
						jump : function(obj, first) {
	
							//首次不执行
							if (!first) {
								setCurrentPage(obj.curr); //设置当前页
							}
						}
					});
				}
			});
		});
		function setCurrentPage(curr) {
			this.document.getElementById("curr").value = curr;
		}
	</script>

</body>
</html>

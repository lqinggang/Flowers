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
</head>

<body>
	<input name="curr" id="curr" type="hidden"
		value="${requestScope.curr }">


	<div id="page"></div>
	<script type="text/javascript">
		layui.use('laypage', function() {
			var laypage = layui.laypage;
			var currpage = document.getElementById("curr").value;
			if (currpage == null || (currpage != null && curr.length == 0)) {
				currpage = 0;
			}
			laypage.render({
				elem : 'page', //注意，这里的 test1 是 ID，不用加 # 号
				layout : [ 'prev', 'page', 'next', 'skip', 'count' ], //自定义分页布局 
				count : '${requestScope.count}', //数据总数，从服务端得到
				curr : currpage,
				limit : 6,
				jump : function(obj, first) {
					console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
					console.log(obj.limit); //得到每页显示的条数
					//首次不执行
					if (!first) {
						setCurrentPage(obj.curr);
						var form = document.getElementById("cartinfo");
						form.action = "users/mycart";
						$('#cartinfo').submit();
					}
				}
			});
		});
		function setCurrentPage(curr) {
			document.getElementById("curr").value = curr ;
		}
	</script>
</body>
</html>

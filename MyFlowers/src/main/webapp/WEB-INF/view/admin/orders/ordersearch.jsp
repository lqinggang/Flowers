<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link rel="stylesheet" href="static/layui/css/layui.css" />
<script src="static/layui/layui.js" type="text/javascript"></script>
</head>

<body>
	<jsp:include page="/WEB-INF/view/admin/orders/search.jsp"></jsp:include>
	<table class="layui-hide" id="ordersinfo" lay-filter="ordersinfoEvent"></table>
	<div id="page" style="float:left;margin-left:5%;"></div>
	<input type="hidden" name="curr" id="curr">

	<script type="text/html" id="del">
		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="del" style="background-color:#62ca59;color:#000;">删除订单</a>
	</script>



	<script>
		layui.use([ 'table', 'laypage' ], function() {
			var table = layui.table,
				laypage = layui.laypage;
			table.render({
				elem : '#ordersinfo',
				url : 'admin/order/search?orderid=${requestScope.orderid}',
				page : false,
				cols : [ [
					{
						field : 'orderid',
						title : '订单编号',
						width : 200,
						sort : true
					}
					, {
						field : 'recipient',
						title : '收件人',
						width : 150
					}
					, {
						field : 'contact',
						width : 200,
						title : '收件人联系方式'
					}
					, {
						field : 'address',
						width : 200,
						title : '收件地址',
					}, {
						field : 'flowerid',
						width : 100,
						title : '鲜花编号',
						sort : true
					}
					, {
						field : 'flowername',
						width : 200,
						title : '鲜花名称'
					}
					, {
						field : 'amount',
						title : '数量',
						width : 100,
						sort : true
					}
					, {
						field : 'date',
						title : '下单时间',
						minWidth : 300,
						sort : true
					}
					, {
						field : 'note',
						title : '备注',
						width : 400
					}
					, {
						field : 'personname',
						width : 100,
						title : '用户名',
						sort : true
					}
					, {
						field : 'price',
						width : 100,
						title : '价格',
						sort : true
					}
					, {
						field : 'status',
						width : 100,
						title : '订单状态',
						sort : true
					}
					, {
						fixed : 'right',
						width : 100,
						align : 'center',
						toolbar : '#del'
					}
				] ],
				done : function(res, curr, count) {
					laypage.render({
						layout : [ 'prev', 'page', 'next', 'skip', 'count' ], //自定义分页布局 
						elem : 'page', //注意，这里的 test1 是 ID，不用加 # 号
						count : count, //数据总数，从服务端得到
						curr : document.getElementById("curr").value,
						limit : 1,
						groups : 5, //显示 5 个连续页码
						jump : function(obj, first) {
							//obj包含了当前分页的所有参数，比如：
							console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
							console.log(obj.limit); //得到每页显示的条数 
							//首次不执行
							if (!first) {
								setCurrentPage(obj.curr); //回调方法
								tablerender();
							}
						}
					});
				}
			});
	
			var $ = layui.jquery,
				layer = layui.layer;
			//监听单元格事件
			table.on('tool(ordersinfoEvent)', function(obj) {
				var data = obj.data;
				alert(JSON.stringify(data));
				if (obj.event === 'del') {
					layer.open({
						type : 1,
						title : '删除',
						content : '<span style="font-size:18px;align:center;">确定要删除订单 [' + data.orderid + ']么？</span>',
						btn : [ '确定删除', '取消' ],
						shade : [ 0.5, '#fff' ],
						maxmin : true,
						adnim : 2,
						yes : function() {
							$.ajax({
								url : 'admin/orders/delete',
								data : {
									ds : '[' + JSON.stringify(data) + ']'
								},
								type : 'post',
								dataType : "json"
							});
							location.reload();
							return true;
						},
						no : function() {
							return;
						}
					})
				}
	
			});
		})
	</script>
	<c:choose>
		<c:when test="${!empty requestScope.msg }">
			<script type="text/javascript">
				alert('${requestScope.msg}');
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.open({
						type : 1,
						skin : 'layui-layer-demo', //样式类名
						closeBtn : 0, //不显示关闭按钮
						anim : 2,
						shadeClose : true, //开启遮罩关闭
						content : '${requestScope.msg}'
					});
				})
			</script>
		</c:when>
	</c:choose>

</body>
</html>

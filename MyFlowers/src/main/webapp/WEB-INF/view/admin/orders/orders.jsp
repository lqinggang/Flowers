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

<title>用户订单</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" href="static/layui/css/layui.css" />
<script src="static/layui/layui.js" type="text/javascript"></script>
<style type="text/css">
.layui-table, .layui-table-view {
	margin-top: 0;
}

.layui-table-cell {
	height: 40px;
}

.layui-table-cell .layui-form-checkbox[lay-skin="primary"] {
	top: 50%;
	transform: translateY(-50%);
}
</style>
</head>

<body onload="tablerender();">
	<jsp:include page="/WEB-INF/view/admin/orders/search.jsp"></jsp:include>

	<table class="layui-hide" id="ordersinfo" lay-filter="ordersinfoEvent"></table>
	<div id="page" style="float:left;margin-left:5%;"></div>
	<input type="hidden" name="curr" id="curr">
	<!-- 操作 -->
	<div class="layui-btn-group ordersinfoTable"
		style="float:right;margin: 1px 2%;">
		<button class="layui-btn  layui-btn-normal" data-type="deleteInfo">删除选中行</button>
		<button class="layui-btn  layui-btn-normal" data-type="exportToExcel">导出当前页</button>
	</div>
	<script>
		function tablerender() {
		/* 	if ('${requestScope.export_msg}' != null) {
				layui.use('layer', function() {
					var $ = layui.$,
						layer = layui.layer;
					layer.open({
						type : 1,
						offset : [ '240px', '120px' ],
						title : '<i class="fa fa-exclamation"></i>INFO',
						content : '<span style="color:red;">${requestScope.export_msg}</span>'
					});
				})
			} */
	
	
			var curr = this.document.getElementById("curr").value;
			if (curr == null || (curr != null && curr.length == 0)) {
				curr = 0;
			}
			layui.use([ 'table', 'laypage' ], function() {
				var table = layui.table,
					laypage = layui.laypage;
				table.render({
					elem : '#ordersinfo',
					url : 'admin/ordersinfo?curr=' + curr,
					page : false,
					cols : [ [
						{
							type : 'checkbox'
						}
						,
						{
							field : 'orderid',
							title : '订单编号',
							width : 200,
							sort : true
						}
						, {
							field : 'personname',
							width : 100,
							title : '用户名',
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
							field : 'price',
							width : 100,
							title : '价格',
							sort : true
						}
						, {
							field : 'date',
							title : '下单时间',
							minWidth : 300,
							sort : true
						}
						, {
							field : 'status',
							width : 100,
							title : '订单状态',
							sort : true
						}
						, {
							field : 'note',
							title : '备注',
							width : 400
						}
	
					] ],
					done : function(res, curr, count) {
						laypage.render({
							layout : [ 'prev', 'page', 'next', 'skip', 'count' ], //自定义分页布局 
							elem : 'page', //注意，这里的 test1 是 ID，不用加 # 号
							count : count, //数据总数，从服务端得到
							curr : document.getElementById("curr").value,
							limit : 50,
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
	
			})
	
		}
	</script>
	<script type="text/javascript">
		function setCurrentPage(curr) {
			this.document.getElementById("curr").value = curr;
		}
		function updateflower(id, property, value) {
			$.ajax({
				cache : false,
				type : "get",
				dataType : "json",
				url : "admin/updateflower" + property,
				data : "id=" + id + "&" + property + "=" + value,
				async : false,
				error : function(request) { //请求失败之后的操作
					return;
				},
				success : function(data) { //请求成功之后的操作
					console.log("success");
				}
			});
		}
	</script>
	<script type="text/javascript">
		layui.use([ 'table', 'layer' ], function() {
			var $ = layui.$,
				layer = layui.layer,
				table = layui.table,
	
				active = {
					deleteInfo : function() { //获取选中数据
						var checkStatus = table.checkStatus('ordersinfo'),
							data = checkStatus.data;
						alert(JSON.stringify(data));
						var deleteWin = layer.open({
							type : 1,
							title : '删除',
							offset : [ '200px', '550px' ],
							content : '<span style="font-size:18px;align:center;">确定要删除这些记录？</span>',
							btn : [ '确定删除', '取消' ],
							shade : [ 0.5, '#fff' ],
							maxmin : true,
							adnim : 2,
							yes : function() {
								$.ajax({
									url : 'admin/orders/delete',
									data : {
										ds : JSON.stringify(data)
									},
									type : 'post',
									dataType : "json"
								});
								layer.close(deleteWin);
								location.reload();
								return true;
							},
							no : function() {
								return;
							}
						})
	
					},
					exportToExcel : function() { //获取选中数目
						$.ajax({
							url : 'admin/orders/export?type=pendingorders',
							data : {
								curr : document.getElementById("curr").value
							},
							type : 'post',
							dataType : "json"
						});
					}
				};
	
			$('.ordersinfoTable .layui-btn').on('click', function() {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
		})
	</script>

	<c:choose>
		<c:when test="${!empty requestScope.msg }">
			<script type="text/javascript">
				layui.use('layer', function() {
					var $ = layui.$,
						layer = layui.layer;
					layer.open({
						type : 1,
						/* area : [ '240px', '120px' ],
						offset : 'auto', */
						offset : [ '240px', '120px' ],
						title : '<i class="fa fa-exclamation"></i>Info',
						content : '<span style="color:red;">${requestScope.msg}</span>'
					});
				})
			</script>
		</c:when>
	</c:choose>
</body>
</html>

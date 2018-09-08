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

<title>用户密码</title>
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
	height: 50px;
}

.layui-table-cell .layui-form-checkbox[lay-skin="primary"] {
	top: 50%;
	transform: translateY(-50%);
}
</style>
</head>

<body onload="tablerender();">
	<jsp:include page="/WEB-INF/view/admin/users/search.jsp"></jsp:include>
	<table class="layui-hide" id="usersinfo" lay-filter="usersinfoEvent"></table>
	<div id="page" style="float:left;margin-left:5%;"></div>
	<input type="hidden" name="curr" id="curr">
	<script>
		function tablerender() {
			var curr = this.document.getElementById("curr").value;
			if (curr == null || (curr != null && curr.length == 0)) {
				curr = 0;
			}
			layui.use([ 'table', 'laypage' ], function() {
				var table = layui.table,
					laypage = layui.laypage;
				table.render({
					elem : '#usersinfo',
					url : 'admin/flowersinfo?curr=' + curr,
					page : false,
					cols : [ [
						{
							type : 'checkbox'
						}
						,
						{
							field : 'flowerid',
							title : '鲜花编号',
							width : 120,
							sort : true
						}
						, {
							field : 'name',
							title : '鲜花名称',
							width : 150,
							event : 'setflowername',
							sort : true
						}
						, {
							field : 'category',
							width : 150,
							title : '鲜花类别',
							sort : true
						}
						, {
							field : 'image',
							width : 200,
							height : 200,
							title : '鲜花图片',
							templet : '<div><img src="{{ d.image}}" style="width:50px;height:50px;margin-left:30%;"></div>'
						}
						, {
							field : 'amount',
							width : 100,
							title : '枝数',
							event : 'setamount',
							sort : true
						}
						, {
							field : 'color',
							title : '颜色',
							width : 100,
							event : 'setcolor',
							sort : true
						}
						, {
							field : 'price',
							title : '价格',
							minWidth : 100,
							event : 'setprice',
							sort : true
						}
						, {
							field : 'origin',
							title : '产地',
							width : 100,
							event : 'setorigin',
							sort : true
						}
						, {
							field : 'quantity',
							width : 100,
							title : '库存量',
							event : 'setquantity',
							sort : true
						}
						, {
							field : 'keyword',
							width : 150,
							title : '搜索关键字',
							event : 'setkeyword'
						}
						, {
							field : 'description',
							width : 300,
							title : '花语',
							event : 'setdescription'
						}
						, {
							field : 'content',
							title : '详细信息',
							width : 400
						}
					] ],
					done : function(res, curr, count) {
						laypage.render({
							layout : [ 'prev', 'page', 'next', 'skip', 'count' ], //自定义分页布局 
							elem : 'page', //注意，这里的 test1 是 ID，不用加 # 号
							count : count, //数据总数，从服务端得到
							curr : document.getElementById("curr").value,
							groups : 5, //显示 5 个连续页码
							jump : function(obj, first) {
								//obj包含了当前分页的所有参数，比如：
								console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
								console.log(obj.limit); //得到每页显示的条数 
								//首次不执行
								if (!first) {
									getFlowersInfo(obj.curr); //回调方法
									tablerender();
								}
							}
						});
					}
				});
	
				//监听单元格事件
				table.on('tool(usersinfoEvent)', function(obj) {
					var data = obj.data;
					if (obj.event === 'setflowername') {
						layer.prompt({
							formType : 2,
							title : '修改 ID 为 [' + data.flowerid + '] 的鲜花的名称',
							value : data.name
						}, function(value, index) {
							updateflower(data.flowerid, "name", value);
							layer.close(index);
							//同步更新表格和缓存对应的值
							obj.update({
								name : value
							});
						});
					} else if (obj.event == 'setamount') {
						layer.prompt({
							formType : 2,
							title : '修改 ID 为 [' + data.flowerid + '] 的鲜花的枝数',
							value : data.amount
						}, function(value, index) {
							updateflower(data.flowerid, "amount", value);
							layer.close(index);
							//同步更新表格和缓存对应的值
							obj.update({
								amount : value
							});
						});
	
					} else if (obj.event == 'setcolor') {
						layer.prompt({
							formType : 2,
							title : '修改 ID 为 [' + data.flowerid + '] 的鲜花的颜色',
							value : data.color
						}, function(value, index) {
							updateflower(data.flowerid, "color", value);
							layer.close(index);
							//同步更新表格和缓存对应的值
							obj.update({
								color : value
							});
						});
	
					} else if (obj.event == 'setorigin') {
						layer.prompt({
							formType : 2,
							title : '修改 ID 为 [' + data.flowerid + '] 的鲜花的产地',
							value : data.origin
						}, function(value, index) {
							updateflower(data.flowerid, "origin", value);
							layer.close(index);
							//同步更新表格和缓存对应的值
							obj.update({
								origin : value
							});
						});
	
					} else if (obj.event == 'setquantity') {
						layer.prompt({
							formType : 2,
							title : '修改 ID 为 [' + data.flowerid + '] 的鲜花的库存量',
							value : data.quantity
						}, function(value, index) {
							updateflower(data.flowerid, "quantity", value);
							layer.close(index);
							//同步更新表格和缓存对应的值
							obj.update({
								quantity : value
							});
						});
	
					} else if (obj.event == 'setkeyword') {
						layer.prompt({
							formType : 2,
							title : '修改 ID 为 [' + data.flowerid + '] 的鲜花的搜索关键字',
							value : data.keyword
						}, function(value, index) {
							updateflower(data.flowerid, "keyword", value);
							layer.close(index);
							//同步更新表格和缓存对应的值
							obj.update({
								keyword : value
							});
						});
	
					} else if (obj.event == 'setdescription') {
						layer.prompt({
							formType : 2,
							title : '修改 ID 为 [' + data.flowerid + '] 的鲜花的花语',
							value : data.description
						}, function(value, index) {
							updateflower(data.flowerid, "description", value);
							layer.close(index);
							//同步更新表格和缓存对应的值
							obj.update({
								description : value
							});
						});
	
					} else if (obj.event == 'setprice') {
						layer.prompt({
							formType : 2,
							title : '修改 ID 为 [' + data.flowerid + '] 的鲜花的价格',
							value : data.price
						}, function(value, index) {
							updateflower(data.flowerid, "price", value);
							layer.close(index);
							//同步更新表格和缓存对应的值
							obj.update({
								price : value
							});
						});
					}
				});
			})
	
		}
	</script>
	<script type="text/javascript">
		function getFlowersInfo(curr) {
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
</body>
</html>

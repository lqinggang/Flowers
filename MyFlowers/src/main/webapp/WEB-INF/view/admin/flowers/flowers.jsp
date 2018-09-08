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

<title>鲜花信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" href="static/layui/css/layui.css" />
<script src="static/layui/layui.js" type="text/javascript"></script>
<style type="text/css">
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
	<jsp:include page="/WEB-INF/view/admin/flowers/search.jsp"></jsp:include>
	<!-- 数据表格 -->
	<table class="layui-hide" id="flowersinfo"
		lay-filter="flowersinfoEvent">
	</table>
	<!-- 操作 -->
	<div class="layui-btn-group flowersinfoTable"
		style="float:right;margin: 1px 2%;">
		<button class="layui-btn  layui-btn-normal" data-type="deleteFlowers">删除选中行</button>
		<button class="layui-btn  layui-btn-normal" data-type="exportToExcel">导出到Excel</button>
	</div>

	<div style="width:100px;height:100px;">
		<button onclick="test();">Test</button>
	</div>
	<script type="text/javascript">
		function test() {
			layui.use([ 'layer', 'jquery', 'element' ], function() {
				var $ = layui.$,
					element = layui.element,
					layer = layui.layer;
			})
		}
	</script>

	<script type="text/javascript">
		layui.use('table', function() {
			var $ = layui.$,
				table = layui.table;
			table.render({
				elem : '#flowersinfo',
				url : 'admin/flowersinfo',
				page : false,
				cols : [ [
					{
						/* 	type : 'checkbox' */
						checkbox : true
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
						sort : true
					}
					, {
						field : 'color',
						title : '颜色',
						width : 100,
						sort : true
					}
					, {
						field : 'price',
						title : '价格',
						minWidth : 100,
						sort : true
					}
					, {
						field : 'origin',
						title : '产地',
						width : 100,
						sort : true
					}
					, {
						field : 'quantity',
						width : 100,
						title : '库存量',
						sort : true
					}
					, {
						field : 'keyword',
						width : 150,
						title : '搜索关键字'
					}
					, {
						field : 'description',
						width : 300,
						title : '花语'
					}
					, {
						field : 'content',
						title : '详细信息图片URL',
						width : 400
					}
					, {
						field : 'content_info',
						title : '详细信息描述文字',
						width : '800',
						templet : '<div><pre>{{d.content_info}}</pre></div>'
					}
				] ]
			})
		})
	</script>

	<script type="text/javascript">
		layui.use([ 'layer', 'table', 'jquery', 'element' ], function() {
			var $ = jQuery = layui.jquery,
				layer = layui.layer,
				table = layui.table
			active = {
				deleteFlowers : function() { //获取选中数据
					var checkStatus = table.checkStatus('flowersinfo'),
						data = checkStatus.data;
					/* 					alert(JSON.stringify(data)); */
					var deletemsg = layer.open({
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
								url : 'admin/deleteflowers',
								data : {
									ds : JSON.stringify(data)
								},
								type : 'post',
								dataType : "json"
							});
							layer.close(deletemsg);
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
						url : 'admin/flowers/export',
						data : {
							curr : document.getElementById("curr").value
						},
						type : 'post',
						dataType : "json"
					});
				}
			};
	
			$('.flowersinfoTable .layui-btn').on('click', function() {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
		})
	</script>

</body>
</html>

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

<title>用户信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" href="static/layui/css/layui.css" />
<script src="static/layui/layui.js" type="text/javascript"></script>
<!-- <script src="static/jquery/jquery-1.7.2.js"></script> -->
<style type="text/css">
.layui-table, .layui-table-view {
	margin-top: 1px;
}

.layui-table-cell .layui-form-checkbox[lay-skin="primary"] {
	top: 50%;
	transform: translateY(-50%);
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

<body onload="tablerender();">
	<jsp:include page="/WEB-INF/view/admin/users/search.jsp"></jsp:include>

	<table class="layui-hide" id="usersinfo"></table>
	<!-- 分页 -->
	<div id="page" style="float:left;margin-left:5%;"></div>
	<!-- 当前页 -->
	<input type="hidden" name="curr" id="curr">
	<!-- 操作 -->
	<div class="layui-btn-group usersInfoTable"
		style="float:right;margin: 1px 2%;">
		<button class="layui-btn  layui-btn-normal" data-type="deleteInfo">删除选中行</button>
		<button class="layui-btn  layui-btn-normal" data-type="exportToExcel">导出当前页</button>
	</div>

	<script>
		function tablerender() {
			/* if ('${requestScope.export_msg}' != null) {
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
			layui.use([ 'table', 'laypage', 'layer' ], function() {
				var table = layui.table,
					layer = layui.layer,
					laypage = layui.laypage;
				table.render({
					elem : '#usersinfo',
					url : 'admin/usersinfo?curr=' + curr,
					page : false,
					cols : [ [
						{
							type : 'checkbox'
						}
						, {
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
									tablerender(); //重新渲染表格
								}
							}
						});
					}
				});
			});
			function setCurrentPage(curr) {
				this.document.getElementById("curr").value = curr;
			}
		}
	</script>
	<!-- <script type="text/javascript">
		layui.use([ 'table', 'layer' ], function() {
			var $ = layui.$,
				layer = layui.layer,
				table = layui.table,
	
				active = {
					deleteUsersInfo : function() { //获取选中数据
						var checkStatus = table.checkStatus('usersinfo'),
							data = checkStatus.data;
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
						var checkStatus = table.checkStatus('usersinfo'),
							data = checkStatus.data;
						layer.msg('选中了：' + data.length + ' 个');
					}
				};
	
			$('.usersinfoTable .layui-btn').on('click', function() {
				var type = $(this).data('type');
				active[type] ? active[type].call(this) : '';
			});
		})
	</script> -->
	<script type="text/javascript">
		layui.use([ 'table', 'layer' ], function() {
			var $ = layui.$,
				layer = layui.layer,
				table = layui.table,
	
				active = {
					deleteInfo : function() { //获取选中数据
						var checkStatus = table.checkStatus('usersinfo'),
							data = checkStatus.data;
						alert(JSON.stringify(data));
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
									url : 'admin/users/delete',
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
							url : 'admin/users/export',
							data : {
								curr : document.getElementById("curr").value
							},
							type : 'post',
							dataType : "json"
						});
					}
				};
	
			$('.usersInfoTable .layui-btn').on('click', function() {
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
						/* 						area : [ '240px', '120px' ], */
						offset : [ '240px', '120px' ],
						title : '<i class="fa fa-exclamation"></i>INFO',
						content : '<span style="color:red;">${requestScope.msg}</span>'
					});
				})
			</script>
		</c:when>
	</c:choose>
</body>
</html>

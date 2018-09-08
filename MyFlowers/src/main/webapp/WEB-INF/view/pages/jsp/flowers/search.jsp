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
<script src="static/jquery/jquery-1.8.3.min.js"></script>
<link href="static/pages/css/flowers/fliter.css" rel="stylesheet" />
<script src="static/pages/js/flowers/filter.js"></script>

</head>

<body>
	<div id="classifysearch">
		<form action="flowers/classify" id="classifyform"
			style="display:none;">
			<input id="searchcolor" name="searchcolor"></input> <input
				id="searchcategory" name="searchcategory"></input> <input
				id="searchamount" name="searchamount"></input>
		</form>
		<div style="float:right;" class="searchbutton">
			<img alt="search" src="static/pages/images/page/search.jpg"
				onclick="formsubmit()" style="width:80px;height:80px;" title="点击搜索">
			<script type="text/javascript">
				function formsubmit() {
					var form = document.getElementById("classifyform");
					form.submit();
				}
			</script>
		</div>
		<div id="color"></div>

		<div id="category"></div>

		<div id="amount"></div>

		<script type="text/javascript">
			var colordata = [
				{
					"id" : '黄色',
					"text" : '黄色'
				},
				{
					"id" : '红色',
					"text" : '红色'
				},
				{
					"id" : '蓝色',
					"text" : '蓝色'
				},
				{
					"id" : '粉色',
					"text" : '粉色'
				},
				{
					"id" : '橙色',
					"text" : '橙色'
				},
				{
					"id" : '紫色',
					"text" : '紫色'
				},
				{
					"id" : '白色',
					"text" : '白色'
				} ]
			var categorydata = [
				{
					"id" : '节日鲜花',
					"text" : '节日鲜花'
				},
				{
					"id" : '婚礼鲜花',
					"text" : '婚礼鲜花'
				},
				{
					"id" : '爱情鲜花',
					"text" : '爱情鲜花'
				},
				{
					"id" : '探望慰问',
					"text" : '探望慰问'
				},
				{
					"id" : '问候长辈',
					"text" : '问候长辈'
				},
				{
					"id" : '其他鲜花',
					"text" : '其他鲜花'
				}
			]
			var amountdata = [
				{
					"id" : 1,
					"text" : '1枝'
				},
				{
					"id" : 3,
					"text" : '3枝'
				},
				{
					"id" : 5,
					"text" : '5枝'
				},
				{
					"id" : 7,
					"text" : '7枝'
				},
				{
					"id" : 12,
					"text" : '12枝'
				}
			]
		
			$('#color').comboboxfilter({
				url : 'flowers/color',
				scope : 'FilterQuery',
				text : '颜色',
				data : colordata,
				onChange : function(newValue) {
					$('#searchcolor').val(newValue);
				}
			});
			$('#category').comboboxfilter({
				url : '',
				scope : 'FilterQuery',
				text : '类别',
				data : categorydata,
				onChange : function(newValue) {
					$('#searchcategory').val(newValue);
				}
			});
			$('#amount').comboboxfilter({
				url : '',
				scope : 'FilterQuery',
				text : '枝数',
				data : amountdata,
				onChange : function(newValue) {
					$('#searchamount').val(newValue);
				}
			});
		</script>
	</div>
</body>
</html>
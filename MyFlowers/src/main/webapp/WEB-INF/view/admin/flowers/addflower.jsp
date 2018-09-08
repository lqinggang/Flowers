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

<title>新增鲜花</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" href="static/layui/css/layui.css" />
<script src="static/layui/layui.js" type="text/javascript"></script>
<style type="text/css">
.layui-form-item {
	margin: 2% 15%;
}

.layui-form-item .layui-form-label {
	width: 25%;
}

.layui-form-item .layui-input-block {
	margin-left: 25%;
	width: 80%;
}

.upload {
	background-color: #C0C0C0;
}
</style>
</head>

<body>
	<div style="width:80%;">
		<form id="flowersform" class="layui-form layui-form-pane"
			action="admin/addflower" method="post" enctype="multipart/form-data"
			style="border:1px solid #C0C0C0;margin-left:25%;margin-top:5%;">
			<div class="layui-form-item" style="margin-top:5%;">
				<label class="layui-form-label">鲜花名称：</label>
				<div class="layui-input-block">
					<input type="text" name="name" id="name" class="layui-input"
						value="">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">类别：</label>
				<div class="layui-input-block">
					<script type="text/javascript">
						layui.use('form', function() {
							var form = layui.form;
							form.on('select(category)', function(data) {
								form.render('select', 'category');
							});
						})
					</script>
					<select name="category" lay-filter="category">
						<c:choose>
							<c:when test="${!empty requestScope.categoryList }">
								<option value="">请选择类别</option>
								<c:forEach items="${requestScope.categoryList }" var="catagory">
									<option value="${catagory.category_id }"> ${catagory.name } </option>
								</c:forEach>
							</c:when>
						</c:choose>
					</select>

				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">搜索关键字：</label>
				<div class="layui-input-block">
					<input type="text" name="keyword" id="keyword" class="layui-input"
						value="">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">颜色：</label>
				<div class="layui-input-block">
					<input type="text" name="color" id="color" class="layui-input"
						value="">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">枝数：</label>
				<div class="layui-input-block">
					<input type="text" name="amount" id="amount" class="layui-input"
						value="">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">产地：</label>
				<div class="layui-input-block">
					<input type="text" name="origin" id="origin" class="layui-input"
						value="">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">鲜花图片：</label>
				<div class="layui-input-block">

					<input type="hidden" name="flowerimage" id="flowerimage" value="" />

					<div id="image" style="display:none;float:left;"></div>
					<div style="float:left;">
						<button type="button" class="layui-btn upload"
							id="uploadflowerimage">
							<i class="layui-icon">&#xe67c;</i>上传鲜花图片
						</button>
					</div>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">库存量：</label>
				<div class="layui-input-block">
					<input type="text" name="quantity" id="quantity"
						class="layui-input" value="">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">价格：</label>
				<div class="layui-input-block">
					<input type="text" name="price" id="price" class="layui-input"
						value="">
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">花语：</label>
				<div class="layui-input-block">
					<input type="text" name="description" id="description"
						class="layui-input" value="">
				</div>
			</div>

			<div class="layui-form-item">

				<input type="hidden" name="content_info" id="content_info" value="" />
				<label class="layui-form-label">商品文字描述信息：</label>
				<div class="layui-input-block"
					style="text-align:center;padding-top:10px;">
					<a href="javascript:content_info();">添加商品文字描述信息</a>
				</div>
				<div id="content_info_table" style="display:none;"></div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">商品描述信息：</label>
				<div class="layui-input-block">

					<input type="hidden" name="content1" id="content1" value="" />
					<div style="float:left;width:45%;">
						<button type="button" class="layui-btn upload" id="uploadcontent1">
							<i class="layui-icon">&#xe67c;</i>商品描述信息图片1
						</button>
						<div id="imagecontent1" style="display:none;"></div>
					</div>
					<input type="hidden" name="content2" id="content2" value="" />
					<div style="float:left;margin-left:2%;">
						<button type="button" class="layui-btn upload" id="uploadcontent2">
							<i class="layui-icon">&#xe67c;</i>商品描述信息图片2
						</button>
						<div id="imagecontent2" style="display:none;"></div>
					</div>


				</div>
			</div>
			<div class="layui-form-item">
				<div class="layui-input-block" style="margin-left:50%;">
					<button class="layui-btn" lay-submit id="submit">立即添加</button>
				</div>
			</div>
		</form>
	</div>
	<c:choose>
		<c:when test="${requestScope.msg }">
			<script type="text/javascript">
				layui.use('layer', function() {
					var layer = layui.layer;
					layer.msg('${requestScope.msg}');
				})
			</script>
		</c:when>
	</c:choose>
</body>
<script>
	layui.use('upload', function() {
		var upload = layui.upload;

		//执行实例
		var uploadaddFlower = upload.render({
			elem : '#uploadflowerimage', //绑定元素
			url : 'admin/upload', //上传接口  
			accept : 'images', //上传文件类型
			acceptMime : 'image/*',
			done : function(res) {
				layer.msg("上传成功");
				uploadSuccess(res, "flowerimage", "image"); //上传完毕回调
			},
			error : function() {
				layer.msg("上传失败");
			//请求异常回调
			}
		});
		//执行实例
		var uploadcontent1 = upload.render({
			elem : '#uploadcontent1', //绑定元素
			url : 'admin/upload', //上传接口		
			accept : 'images', //上传文件类型
			acceptMime : 'image/*',
			data : {
				type : "content"
			},
			done : function(res) {
				layer.msg("上传成功");
				uploadSuccess(res, "content1", "imagecontent1"); //上传完毕回调
			},
			error : function() {
				layer.msg("上传失败");
			//请求异常回调
			}
		});
		//执行实例
		var uploadcontent2 = upload.render({
			elem : '#uploadcontent2', //绑定元素
			url : 'admin/upload', //上传接口
			accept : 'images', //上传文件类型
			acceptMime : 'image/*',
			data : {
				type : "content"
			},
			done : function(res) {
				layer.msg("上传成功");
				uploadSuccess(res, "content2", "imagecontent2"); //上传完毕回调
			},
			error : function() {
				layer.msg("上传失败");
			//请求异常回调
			}
		});
	});

	function uploadSuccess(res, inputId, divId) {
		document.getElementById(inputId).value = res.data.src + res.data.name;
		var imageDiv = document.getElementById(divId);
		var img;

		if (imageDiv.childNodes.item(0) == null) {
			img = document.createElement("img");
		} else {
			img = imageDiv.childNodes.item(0);
		}
		imageDiv.style.display = "block";

		img.src = res.data.src + res.data.name;
		img.style.width = 120;
		img.style.height = 100;
		img.style.marginLeft = 40;
		img.style.marginRight = 20;
		img.style.marginTop = 10;
		imageDiv.insertBefore(img, imageDiv.lastChild);
	}

	function content_info() {
		layui.use('layer', function() {
			var layer = layui.layer;
			var contentIframe = layer.open({
				type : 2,
				title : '鲜花文字描述信息页',
				shadeClose : true,
				shade : 0.8,
				area : [ '480px', '500px' ],
				content : 'admin/flower/contentinfo' //iframe的url
			});

		})

	}
</script>
</html>

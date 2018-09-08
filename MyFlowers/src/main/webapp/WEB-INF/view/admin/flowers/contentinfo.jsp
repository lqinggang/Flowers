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
<link rel="stylesheet" href="static/layui/css/layui.css" />
</head>
<body>
	<form id="content_info_form" class="layui-form layui-form-pane"
		action="javascript:setContentInfo();" method="post">
		<div class="layui-form-item">
			<label class="layui-form-label">包装：</label>
			<div class="layui-input-block">
				<input type="text" name="package" id="package" class="layui-input"
					value="无">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">材料：</label>
			<div class="layui-input-block">
				<input type="text" name="material" id="material" class="layui-input"
					value="无">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">附送：</label>
			<div class="layui-input-block">
				<input type="text" name="gift" id="gift" class="layui-input"
					value="下单填写留言，即免费赠送精美贺卡！">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">配送：</label>
			<div class="layui-input-block">
				<input type="text" name="delivery" id="delivery" class="layui-input"
					value="全国 （可配送至全国2000多城市，市区免配送费）">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">说明：</label>
			<div class="layui-input-block">
				<input type="text" name="explain" id="explain" class="layui-input"
					value="因各地花艺师不同，在搭配上与图片可能不完全一致，但我们保证花材新鲜与说明一致，谢谢。">
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" lay-submit id="submit">提交</button>
			</div>
		</div>
	</form>

</body>
<script type="text/javascript">
	function setContentInfo() {
		var pack = document.getElementById("package");
		var material = document.getElementById("material");
		var gift = document.getElementById("gift");
		var delivery = document.getElementById("delivery");
		var explain = document.getElementById("explain");

		var table = "<table><tr><td>包装：</td><td>" + pack.value + "</td></tr><tr><td>材料：</td><td>" + material.value + "</td></tr><tr><td>附送：</td><td>" + gift.value + "</td></tr><tr><td>配送：</td><td>" + delivery.value + "</td></tr><tr><td>说明：</td><td>" + explain.value + "</td></tr></table>"

		var content_info = window.parent.document.getElementById("content_info");
		content_info.value = table;
		var content_info_table = window.parent.document.getElementById("content_info_table");

		var infodiv;

		if (content_info_table.childNodes.item(0) != null) {
			content_info_table.removeChild(content_info_table.childNodes[0]);
		}
		infodiv = window.parent.document.createElement("div");
		content_info_table.style.display = "block";

		var info = "包装：" + pack.value + "材料：" + material.value + "附送：" + gift.value + "配送：" + explain.value + "说明：" + explain.value;

		infodiv.append(info);
		content_info_table.insertBefore(infodiv, content_info_table.lastChild);
		window.parent.layer.close(window.parent.layer.getFrameIndex(window.name));
	}
</script>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>花之苑</title>
<meta charset="utf-8">
<meta name="description" content="鲜花销售平台">
<meta name="keywords" content="鲜花">

<style type="text/css">
.side-item div a:hover {
	color: #ff8000;
}
</style>
</head>
<body>
	<div id="left">
		<div class="panel">
			<div class="heading">
				<h3 class="title">鲜花推荐</h3>
			</div>
			<div class="panel-body">
				<c:forEach items="${recommendList }" var="recommend" begin="0"
					end="4" varStatus="id">
					<div class="side-item">
						<div class="images">
							<a href="flowers/searchflowers?id=${recommend.flower_id }"
								target="mainbody"><img alt="鲜花推荐${id.count }"
								src="${recommend.image }"> <span class="info"
								style="display:block;"> <span class="name">${recommend.name }</span>
									<span class="price">￥${recommend.price }</span>
							</span></a>
						</div>

					</div>
					<hr style="align:center" />
				</c:forEach>
			</div>
		</div>
	</div>
	<div class="clear blank"></div>
</body>

</html>
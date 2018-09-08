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
<title>花之苑</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="static/pages/css/index.css" rel="stylesheet" type="text/css" />
<link href="static/layui/css/layui.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {
	height: 2200px;
}
</style>

</head>
<body>
	<!-- <marquee>
		<p style="color:red;font-size:18px;">欢迎光临，您的满意是我们永恒的追求！</p>
	</marquee> -->
	<div style="height:18px;"></div>
	<!-- 节日鲜花 -->
	<div class="catrgory">
		<div class="categoryname">
			<div class="name">
				<a href="flowers/search?type=category&name=节日鲜花#search-condition"
					target="mainbody"><img alt="节日鲜花"
					src="static/pages/images/page/banner_festival.jpg"> 节日鲜花>>></a>
			</div>
		</div>
		<div class="category-flowers">
			<div class="category-flowers">
				<c:forEach items="${sessionScope.festivaList }" var="flowers"
					begin="0" end="3">
					<div class="flowers">
						<a href="flowers/searchflowers?id=${flowers.flower_id }"
							target="mainbody"> <span> <img class="flowersimage"
								src="${flowers.image }"></img>
						</span> <span class="info"> <span class="flowername">${flowers.name }</span>
								<span class="flowerprice">￥${flowers.price }</span>
						</span>
						</a>
					</div>

				</c:forEach>
			</div>
		</div>
	</div>
	<!-- 婚礼鲜花 -->
	<div class="catrgory">
		<div class="categoryname">

			<div class="name">
				<a href="flowers/search?name=婚礼鲜花&type=category#search-condition"
					target="mainbody"><img alt="婚礼鲜花"
					src="static/pages/images/page/banner_wedding.jpg">婚礼鲜花>>></a>
			</div>
		</div>
		<div class="category-flowers">
			<div class="category-flowers">
				<c:forEach items="${sessionScope.weddingList }" var="flowers"
					begin="0" end="3">
					<div class="flowers">
						<a href="flowers/searchflowers?id=${flowers.flower_id }"
							target="mainbody"> <span> <img class="flowersimage"
								src="${flowers.image }"></img>
						</span> <span class="info"> <span class="flowername">${flowers.name }</span>
								<span class="flowerprice">￥${flowers.price }</span>
						</span>
						</a>
					</div>

				</c:forEach>
			</div>
		</div>
	</div>
	<!-- 爱情鲜花 -->
	<div class="catrgory">
		<div class="categoryname">
			<div class="name">
				<a href="flowers/search?type=category&name=爱情鲜花#search-condition"
					target="mainbody"><img alt="爱情鲜花"
					src="static/pages/images/page/banner_love.jpg"> 爱情鲜花>>></a>
			</div>
		</div>
		<div class="category-flowers">
			<div class="category-flowers">
				<c:forEach items="${sessionScope.loveList }" var="flowers" begin="0"
					end="3">
					<div class="flowers">
						<a href="flowers/searchflowers?id=${flowers.flower_id }"
							target="mainbody"> <span> <img class="flowersimage"
								src="${flowers.image }"></img>
						</span> <span class="info"> <span class="flowername">${flowers.name }</span>
								<span class="flowerprice">￥${flowers.price }</span>
						</span>
						</a>
					</div>

				</c:forEach>
			</div>
		</div>
	</div>
	<!-- 探望慰问 -->
	<div class="catrgory">
		<div class="categoryname">
			<div class="name">
				<a href="flowers/search?type=category&name=探望慰问#search-condition"
					target="mainbody"><img alt="探望慰问"
					src="static/pages/images/page/banner_condolence.jpg"> 探望慰问>>></a>
			</div>
		</div>
		<div class="category-flowers">
			<div class="category-flowers">
				<c:forEach items="${sessionScope.condolenceList }" var="flowers"
					begin="0" end="3">
					<div class="flowers">
						<a href="flowers/searchflowers?id=${flowers.flower_id }"
							target="mainbody"> <span> <img class="flowersimage"
								src="${flowers.image }"></img>
						</span> <span class="info"> <span class="flowername">${flowers.name }</span>
								<span class="flowerprice">￥${flowers.price }</span>
						</span>
						</a>
					</div>

				</c:forEach>
			</div>
		</div>
	</div>
	<!-- 问候长辈 -->
	<div class="catrgory">
		<div class="categoryname">
			<div class="name">
				<a href="flowers/search?type=category&name=问候长辈#search-condition"
					target="mainbody"><img alt="问候长辈"
					src="static/pages/images/page/banner_elder.jpg"> 问候长辈>>></a>
			</div>
		</div>
		<div class="category-flowers">
			<div class="category-flowers">
				<c:forEach items="${sessionScope.elderList }" var="flowers"
					begin="0" end="3">
					<div class="flowers">
						<a href="flowers/searchflowers?id=${flowers.flower_id }"
							target="mainbody"> <span> <img class="flowersimage"
								src="${flowers.image }"></img>
						</span> <span class="info"> <span class="flowername">${flowers.name }</span>
								<span class="flowerprice">￥${flowers.price }</span>
						</span>
						</a>
					</div>

				</c:forEach>
			</div>
		</div>
	</div>
	<!-- 其他鲜花 -->
	<div class="catrgory">
		<div class="categoryname">
			<div class="name">
				<a href="flowers/search?type=category&name=其他鲜花#search-condition"
					target="mainbody"><img alt="其他鲜花"
					src="static/pages/images/page/banner_other.jpg"> 其他鲜花>>></a>
			</div>
		</div>
		<div class="category-flowers">
			<c:forEach items="${sessionScope.otherList }" var="flowers" begin="0"
				end="3">
				<div class="flowers">
					<a href="flowers/searchflowers?id=${flowers.flower_id }"
						target="mainbody"> <span> <img class="flowersimage"
							src="${flowers.image }"></img>
					</span> <span class="info"> <span class="flowername">${flowers.name }</span>
							<span class="flowerprice">￥${flowers.price }</span>
					</span>
					</a>
				</div>

			</c:forEach>
		</div>
	</div>

	<div class="clear"></div>
</body>
</html>

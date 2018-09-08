<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/base.jspf"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>">
<title>花之苑</title>
<meta charset="utf-8">
<meta name="description" content="鲜花销售平台">
<meta name="keywords" content="鲜花">

<link rel="stylesheet" type="text/css"
	href="static/pages/css/flowers/searchflowers.css">
<link rel="stylesheet" type="text/css"
	href="static/pages/css/common/page.css">
<style type="text/css">
.flowers a {
	font-size: 16px;
	text-decoration: none;
	color: #737373;
}

.flowers a:hover {
	color: #ff8000;
}

.flowers .info .info-price {
	font-weight: bold;
	font-style: italic;
}

a {
	font-size: 16px;
	text-decoration: none;
	color: #737373;
}

a:hover {
	color: #ff8000;
}
</style>
</head>

<body>
	<div id="searchbody">
		<div class="search-condition" id="search-condition">
			<div>
				<jsp:include page="/WEB-INF/view/pages/jsp/flowers/search.jsp"></jsp:include>
			</div>
		</div>
		<div class="search-result">
			<c:choose>
				<c:when test="${!empty searchList }">
					<c:forEach items="${searchList }" var="flowers" varStatus="id">
						<div class="row flowers">
							<span class="col"> <a
								href="flowers/searchflowers?id=${flowers.flower_id }"> <span
									class="flowersimage"> <img alt="${flowers.name }"
										src="${flowers.image }">
								</span> <span class="info"> <span class="info-name">${flowers.name }</span>
										<span class="info-price">￥${flowers.price } </span>
								</span>
							</a>
							</span>
						</div>
					</c:forEach>
					<c:choose>
						<c:when test="${searchList.size() <=25 }">

							<h1
								style="color:#C0C0C0;text-align:center;padding-top:10%;margin:10%;font-style:italic;clear:both;font-size:18px;">
								<c:choose>
									<c:when test="${empty msg }">
										Σ( ° △ °|||)︴没有了······
									</c:when>
									<c:otherwise>
										${msg }
									</c:otherwise>
								</c:choose>
							</h1>
							<c:choose>
								<c:when test="${!empty youlikeList }">
									<div id="youlike">
										<h3
											style="height:40px;background-color:#f6f6f6;text-align:center;margin-bottom:10px;padding-top:12px;">也许你会喜欢：</h3>
										<c:forEach items="${youlikeList }" var="flowers"
											varStatus="id">
											<div class="row flowers">
												<span class="col"> <a
													href="flowers/searchflowers?id=${flowers.flower_id }">
														<span class="flowersimage"> <img
															alt="${flowers.name }" src="${flowers.image }"
															style="height:80%;">
													</span> <span class="info"> <span class="info-name">${flowers.name }</span>
															<span class="info-price">￥${flowers.price } </span>
													</span>
												</a>
												</span>
											</div>
										</c:forEach>
									</div>
								</c:when>

							</c:choose>

						</c:when>
						<c:otherwise>
							<div
								style="text-align:center;font-size:16px;clear:both;margin:5%;">
								<a
									href="flowers/search?name=${name }&page=${pageEntity.firstPage } ">&lt;&lt;
									首页 </a> <a
									href="flowers/search?name=${name }&page=${pageEntity.previousPage }">
									&lt; 上一页 </a> <strong>第${pageEntity.currentPage}页/共${pageEntity.totalPages}页</strong>
								<a
									href="flowers/search?name=${name }&page=${pageEntity.nextPage}">下一页
									&gt;</a> <a
									href="flowers/search?name=${name }&page=${pageEntity.lastPage}">末页
									&gt;&gt;</a>
							</div>

						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${!empty youlikeList }">

							<div
								style="text-align:center;font-size:18px;margin:5%;color:#C0C0C0;">(ノへ￣、)
								很抱歉，没找到相关宝贝</div>
							<div id="youlike">
								<h3
									style="height:40px;background-color:#f6f6f6;text-align:center;margin-bottom:10px;padding-top:12px;">下面的也不错哦：</h3>
								<c:forEach items="${youlikeList }" var="flowers" varStatus="id">
									<div class="row flowers">
										<span class="col"> <a
											href="flowers/searchflowers?id=${flowers.flower_id }"> <span
												class="flowersimage"> <img alt="${flowers.name }"
													src="${flowers.image }" style="height:80%;">
											</span> <span class="info"> <span class="info-name">${flowers.name }</span>
													<span class="info-price">￥${flowers.price } </span>
											</span>
										</a>
										</span>
									</div>
								</c:forEach>
							</div>
						</c:when>

					</c:choose>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</body>
</html>

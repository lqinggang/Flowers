<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/base.jspf"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>
<base href="<%=basePath%>">
<style type="text/css">
#recommend {
	width: 100%;
	height: 400px;
}

#recommend .recommend-title {
	width: 100%;
	height: 40px;
	background-color: #EEE;
	color: #C0C0C0;
	margin-bottom: 5px;
}

#recommend .recommend-info {
	margin-right: 1%;
	margin-bottom: 2%;
	border: 1px solid #C0C0C0;
	width: 18.7%;
	height: 70%;
	float: left;
	text-align: center;
}

#recommend .recommend-info span {
	font-size: 16px;
	display: block;
}

#recommend .recommend-info a {
	color: #C0C0C0;
}

#recommend .recommend-info a:hover {
	color: #FF8000;
}

#recommend .recommend-info .recommend-image img {
	width: 100%;
	height: 80%;
}

#recommend .recommend-info .recommend-price {
	font-style: italic;
}
</style>
<script type="text/javascript">
	function changeHeight() {
		var parentNode = this.parent.document.getElementById("mycartrecomend");
	/* 	parentNode.height = this.document.getElementById("recommend").scrollHeight; */
	}
</script>
</head>
<body onload="changeHeight();">
	<div id="recommend" style="margin-top:10%;">
		<c:choose>
			<c:when test="${!empty recommendList }">
				<div class="recommend-title">&nbsp;&nbsp;:-D&nbsp;你可能会喜欢</div>
				<c:forEach items="${recommendList }" var="recommend">
					<div class="recommend-info">
						<a href="flowers/searchflowers?id=${recommend.flower_id }"
							target="mainbody"> <span class="recommend-image"> <img
								alt="${recommend.name }" src="${recommend.image }">
						</span> <span class="recommend-name">${recommend.name }</span> <span
							class="recommend-price">￥${recommend.price }</span>
						</a>
					</div>
				</c:forEach>
			</c:when>
		</c:choose>
	</div>
</body>
</html>

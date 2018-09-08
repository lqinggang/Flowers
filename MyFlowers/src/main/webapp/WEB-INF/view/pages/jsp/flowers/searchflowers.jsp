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
<link href="static/layui/css/layui.css" rel="stylesheet" media="all">
<style type="text/css">
body {
	height: 2400px;
}

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
<script type="text/javascript">

	/* 	function load() {
			var mainbody = window.parent.document.getElementById("mainbody");
			mainbody.height = 2800;
			alert("");
		} */
	var hostUrl = window.top.location.toString().split("#")[0];
	if (hostUrl) {
		hostUrl += "#height=" + 2800;
		window.top.location = hostUrl;
	}
</script>
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
					<%-- 				<div style="text-align:center;font-size:16px;clear:both;margin:5%;">
						<a
							href="flowers/${search }?name=${name }&type=${category }&searchcolor=${searchcolor }&searchcategory=${searchcategory }&searchamount=${searchamount }&page=${pageEntity.firstPage } ">&lt;&lt;
							首页 </a> <a
							href="flowers/${search }?name=${name }&type=${category }&searchcolor=${searchcolor }&searchcategory=${searchcategory }&searchamount=${searchamount }&page=${pageEntity.previousPage }">
							&lt; 上一页 </a> <strong>第${pageEntity.currentPage}页/共${pageEntity.totalPages}页</strong>
						<a
							href="flowers/${search }?name=${name }&type=${category }&searchcolor=${searchcolor }&searchcategory=${searchcategory }&searchamount=${searchamount }&page=${pageEntity.nextPage}">下一页
							&gt;</a> <a
							href="flowers/${search }?name=${name }&type=${category }&searchcolor=${searchcolor }&searchcategory=${searchcategory }&searchamount=${searchamount }&page=${pageEntity.lastPage}">末页
							&gt;&gt;</a>
					</div>
 --%>
					<div style="clear:both;"></div>
					<form
						<%-- action="flowers/${requestScope.search }?name=${requestScope.name }&type=${requestScope.type }&searchcolor=${requestScope.searchcolor }&searchcategory=${requestScope.searchcategory }&searchamount=${requestScope.searchamount }" --%>
						id="flowerssearchform"
						method="post">
						<input name="curr" id="curr" type="hidden"
							value="${requestScope.curr }">
						<%-- <input name="id"
							type="hidden" value="${requestScope.flowerid }"> <input
							name="name" id="name" type="hidden" value="${requestScope.name }">
						<input name="type" id="type" type="hidden"
							value="${requestScope.type }"> <input name="searchcolor"
							id="searchcolor" type="hidden"
							value="${requestScope.searchcolor }"> <input
							name="searchcategory" id="searchcategory" type="hidden"
							value="${requestScope.searchcategory }"> <input
							name="searchamount" id="searchamount" type="hidden"
							value="${requestScope.searchamount }"> --%>
						<div id="page" style="margin-top:5%;margin-left:25%;"></div>
					</form>
					<script src="static/layui/layui.js" type="text/javascript"></script>
					<script type="text/javascript">
						layui.use('laypage', function() {
							var laypage = layui.laypage;
							var currpage = document.getElementById("curr").value;
							if (currpage == null || (currpage != null && curr.length == 0)) {
								currpage = 0;
							}
							laypage.render({
								elem : 'page', //注意，这里的 test1 是 ID，不用加 # 号
								layout : [ 'prev', 'page', 'next', 'skip', 'count' ], //自定义分页布局 
								count : '${requestScope.count}', //数据总数，从服务端得到
								curr : currpage,
								groups : 5,
								limit : 30,
								jump : function(obj, first) {
									console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
									console.log(obj.limit); //得到每页显示的条数
									//首次不执行
									if (!first) {
										setCurrentPage(obj.curr);
										var form = document.getElementById("flowerssearchform");
										var action = 'flowers/${requestScope.search }?name=${requestScope.name }&type=${requestScope.type }';
										if(${ requestScope.searchcolor != null}){
											action = action +'&searchcolor=${requestScope.searchcolor }';
										}
										if(${ requestScope.searchcategory != null}){
											action = action +'&searchcategory=${requestScope.searchcategory }';
										}
										if(${ requestScope.searchamount != null}){
											action = action +'&searchamount=${requestScope.searchamount }';
										}
										
										form.action = action;
										form.submit();
									}
								}
							});
						});
						function setCurrentPage(curr) {
							document.getElementById("curr").value = curr ;
						}
					</script>


					<c:choose>
						<c:when test="${searchList.size() <=25 }">

							<h1
								style="color:#C0C0C0;text-align:center;padding-top:8%;margin:8%;font-style:italic;clear:both;font-size:18px;">
								<c:choose>
									<c:when test="${empty msg }">
										Σ( ° △ °|||)︴没有了······
									</c:when>
									<c:otherwise>
										${msg }
									</c:otherwise>
								</c:choose>
							</h1>
						</c:when>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${!empty youlikeList && empty searchList}">

							<div
								style="text-align:center;font-size:18px;margin:15%;color:#C0C0C0;">(ノへ￣、)
								很抱歉，没找到相关宝贝</div>
						</c:when>

					</c:choose>
				</c:otherwise>

			</c:choose>
			<c:choose>
				<c:when test="${!empty youlikeList }">
					<div id="youlike">
						<h3
							style="height:40px;background-color:#f6f6f6;text-align:center;margin-bottom:10px;padding-top:12px;">也许你会喜欢：</h3>
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
		</div>
	</div>
</body>
</html>

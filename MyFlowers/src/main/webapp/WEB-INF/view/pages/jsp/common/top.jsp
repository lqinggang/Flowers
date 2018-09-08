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
<%@ include file="/WEB-INF/view/pages/jsp/common/pages-header.jsp"%>
<style type="text/css">
#users .layui-nav-tree .layui-nav-itemed:after {
	background-color: #FFF;
}

#users .layui-nav .layui-nav-item {
	line-height: 24px;
	text-decoration: none;
}

#users .layui-nav .layui-this:after, .layui-nav-bar, .layui-nav-tree .layui-nav-itemed:after
	{
	position: absolute;
	left: 0;
	top: 0;
	width: 0;
	height: 0;
	background-color: #FFF;
	transition: all .2s;
	-webkit-transition: all .2s
}

.layui-nav .layui-this:after, .layui-nav-bar, .layui-nav-tree .layui-nav-itemed:after
	{
	height: 0;
	background-color: #FFF;
}

#users .layui-nav-child {
	top: 24px;
}

#users .layui-nav-item {
	margin: 0px;
}

#userlogin .layui-nav {
	background-color: #FFF;
	line-height: 14px;
	padding: 0px;
}

.header .header_r .search_form .search_text {
	border: none;
	background: none;
	width: 130px;
	padding: 0 6px;
	height: 30px;
	line-height: 30px;
	vertical-align: top;
}
</style>
</head>

<body>

	<div id="users">
		<c:choose>
			<c:when test="${empty sessionScope.username }">
				<div>
					<a href="users/loginpage" style="border-right:1px solid #C0C0C0;">登录</a><a
						href="users/users/registeredpage">注册</a>
				</div>
			</c:when>
			<c:otherwise>
				<div id="userlogin">
					<div style="float:left;margin-right:5px;">
						<ul class="layui-nav">
							<li class="layui-nav-item"><a href="javascript:;">欢迎您：${sessionScope.username }</a>
								<dl class="layui-nav-child">
									<!-- 二级菜单 -->
									<dd>
										<a href="users/completionpage">资料补全</a>
									</dd>
									<dd>
										<a href="users/datasettingpage">资料设置</a>
									</dd>
									<dd>
										<a href="service/servicepage?page=myorders" target="_top">我的订单</a>
									</dd>
									<dd>
										<a href="users/logout">退出登录</a>
									</dd>
								</dl></li>
						</ul>
					</div>
					<div
						style="float:left;border:1px solid #C0C0C0;border-style:none solid;">
						<ul class="layui-nav">
							<li class="layui-nav-item"><a href="users/cart"
								target="_top">我的购物车</a></li>
						</ul>
					</div>
					<div style="float:left;margin-left:5px;">
						<ul class="layui-nav">
							<li class="layui-nav-item"><a href="javascript:;">客户服务</a>
								<dl class="layui-nav-child">
									<!-- 二级菜单 -->
									<dd>
										<a href="service/servicepage?page=problem">常见问题</a>
									</dd>
									<dd>
										<a href="service/servicepage?page=myorders">订单帮助</a>
									</dd>
									<dd>
										<a href="service/servicepage?page=aftersales">售后服务</a>
									</dd>
									<dd>
										<a href="service/servicepage?page=contact">联系我们</a>
									</dd>
								</dl></li>
						</ul>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<div style="clear:both;"></div>
	<div class="header">
		<div class="logo">
			<a href="javascript:;"><img
				src="static/pages/images/page/logo.png"></a>
		</div>
		<div class="nav">
			<ul>
				<li><em><span>首页</span><a href="mainPage/index">HOME</a></em></li>
				<li><em><span>商品导购</span><a href="">SHOPPING</a></em>
					<ul>
						<li><a
							href="mainPage/flowers/search?name=节日鲜花&type=category#search-condition"
							target="_top">节日鲜花</a></li>
						<li><a
							href="mainPage/flowers/search?name=婚礼鲜花&type=category#search-condition"
							target="_top">婚礼鲜花</a></li>
						<li><a
							href="mainPage/flowers/search?name=爱情鲜花&type=category#search-condition"
							target="_top">爱情鲜花</a></li>
						<li><a
							href="mainPage/flowers/search?name=探望慰问&type=category#search-condition"
							target="_top">探望慰问</a></li>
						<li><a
							href="mainPage/flowers/search?name=问候长辈&type=category#search-condition"
							target="_top">问候长辈</a></li>
						<li><a
							href="mainPage/flowers/search?name=其他鲜花&type=category#search-condition"
							target="_top">其他鲜花</a></li>
					</ul></li>
				<li><em><span>鲜花</span><a href="">FLOWERS</a></em>
					<ul>
						<li><a
							href="mainPage/flowers/search?name=玫瑰&type=flower#search-condition"
							target="_top">玫瑰</a></li>
						<li><a
							href="mainPage/flowers/search?name=月季&type=flower#search-condition"
							target="_top">月季</a></li>
						<li><a
							href="mainPage/flowers/search?name=兰花&type=flower#search-condition"
							target="_top">兰花</a></li>
						<li><a
							href="mainPage/flowers/search?name=康乃馨&type=flower#search-condition"
							target="_top">康乃馨</a></li>
						<li><a
							href="mainPage/flowers/search?name=郁金香&type=flower#search-condition"
							target="_top">郁金香</a></li>
					</ul></li>
				<li><em><span>服务中心</span><a href="service/servicepage">SERVICE</a></em></li>
				<!-- <li><em><span>包年/包月</span><a href="javascript:;"
						target="_top">ANNUAL</a></em> -->
				<li><em><span>关于我们</span><a href="mainPage/aboutpage"
						target="_top">ABOUT</a></em></li>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="header_r">
			<div class="search_form fr"
				style="background-image:url(static/pages/images/page/searchbg.png);background-repeat:no-repeat;background-size:180px 30px;">
				<form action="mainPage/flowers/search?type=flower#search-condition"
					method="post" target="_top">
					<input type="text" name="name" class="search_text" placeholder="搜索"
						style="width:150px;"> <input type="submit"
						class="search_btn" value="" style="width:25px;height:27px;">
				</form>
			</div>

		</div>
		<span class="clear"></span>
	</div>
	<script src="static/layui/layui.js" type="text/javascript"></script>
	<script type="text/javascript">
		layui.use('carousel', function() {
			var carousel = layui.carousel;
			//建造实例
			carousel.render({
				elem : '#top_arousel',
				width : '65%', //设置容器宽度
				arrow : 'always' //始终显示箭头
			//,anim: 'updown' //切换动画方式
			});
		});
		//注意：导航 依赖 element 模块，否则无法进行功能性操作
		//下拉菜单
		layui.use('element', function() {
			var element = layui.element;
	
		});
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			//首页flash						   
			var ulnumli = $("ul.ulnum li");
			var fadearry = $("ul.fadearry li");
			flash(ulnumli, fadearry);
			$("ul#index-ultab li").width($(document.body).width());
	
			//导航
			$(".nav > ul > li").hover(function() {
				$(this).find("ul").animate({
					height : 'show'
				}, {
					queue : true,
					duration : 200
				});
				$(this).stop().attr('class', 'cur');
			}, function() {
				$(this).find("ul").hide();
				$(this).stop().attr('class', '');
			});
	
			$(".nav > ul > li").hover(function() {
				$(this).find("span").stop().animate({
					marginTop : "-112px"
				}, 250);
			}, function() {
				$(this).find("span").stop().animate({
					marginTop : "0"
				}, 250);
			});
	
	
			//产品导航
			$(".menulist > li").hover(function() {
				$(this).find("ul").animate({
					height : 'show'
				}, {
					queue : true,
					duration : 200
				});
				$(this).addClass('cur');
				$(this).stop().children().attr('class', 'sub_links');
			}, function() {
				$(this).find("ul").hide();
				$(this).removeClass('cur');
				$(this).stop().children().attr('class', 'sub_links');
			});
	
			$('.index_products li').hover(function() {
				$(this).find('.price_box').show();
			}, function() {
				$(this).find('.price_box').hide();
			})
	
			//产品分类展开
			$('.select_box dd a:gt(8)').hide()
			$('.select_box dd span.open').click(function() {
				if ($('.select_box dd a:gt(8)').is(':visible')) {
					$('.select_box dd a:gt(8)').hide()
					$(this).removeClass('cur');
				} else {
					$('.select_box dd a:gt(8)').show()
					$(this).addClass('cur');
				}
			})
			//选项卡
			var $tags = $('.tab_tags a');
			$tags.click(function() {
				$(this).addClass('cur').siblings().removeClass('cur');
				var index = $tags.index(this)
				$('.tab_cont > div').eq(index).show().siblings().hide();
			})
		})
	</script>



</body>
</html>

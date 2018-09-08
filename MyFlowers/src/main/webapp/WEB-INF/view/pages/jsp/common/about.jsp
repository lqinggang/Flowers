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

<title>关于我们</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="鲜花,销售">
<meta http-equiv="description" content="鲜花销售平台">
<link href="static/pages/css/common/about.css" rel="stylesheet" />
<style type="text/css">
body {
	height: 2100px;
}
</style>
</head>

<body>
	<div id="about">
		<div class="abouttitle">
			关于我们
			<div class="position">
				当前位置：<a href="mainPage/index" target="_top">首页</a>&nbsp;>>&nbsp; <a
					href="mainPage/about" target="mainbody">关于我们</a>
			</div>
		</div>
		<div class="aboutinfo">
			<div class="infotitle">关于我们</div>
			<div>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;xxx经过多年的发展与沉淀，"xxx"成为国内知名的精品花卉领导品牌，并得到国际市场的认可。xxx秉承"精诚合作，共生共赢"的理念，为客户提供优质的产品和差异化的服务。
					在鲜花销售领域一直提倡花卉产品品质的优异，得到广大市场的高度认可，成为国内鲜花优质品牌专业供应商。</p>
				<br>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;xxx的鲜花培育基地位于气候得天独厚的xxx，快速高效的生产供货渠道和完善的售后服务体系使xxx能及时满足客户的多样化需求，与广大客户建立了稳固共赢的合作关系。
					目前，xxx鲜花品牌以xx为中心，辐射大陆各级城市，在国内市场占有率达到xx%。</p>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;道生一，一生二，三生万物。土生木，木生花，花生情怀。花，使生活更美好。我们以创造更美的生活为愿景，运用国际先进的生产技术及科学管理经验，为客户创造更高的价值。
				</p>
			</div>
			<div class="aboutimage">
				<img alt="flower" src="static/pages/images/page/banner_pages_1.jpg">
				<img alt="flower" src="static/pages/images/page/banner_pages_2.jpg">
			</div>
			<div>
				<h3>经营理念</h3>
				<p>&nbsp;&nbsp;&nbsp;&nbsp;公司经营理念：以客户为中心，诚信经商，创富济世，承担社会责任。</p>
				<p style="font-style:itatic;">&nbsp;&nbsp;&nbsp;&nbsp;Company
					business philosophy: customer-centric, integrity in business world,
					wealth, social responsibility.</p>
			</div>

			<div>
				<h3>我们的位置</h3>
				<img alt="position" src="static/pages/images/page/position.jpg"
					style="width:100%;height:20%;">
			</div>
			<div style="clear:both;margin-top:10%;">
				<div style="height:3px;"></div>
				<div style="height:3px;background-color:#C0C0C0;"></div>
				<div style="height:5px;background-color:#80ffff;"></div>
			</div>
		</div>
	</div>
</body>
</html>

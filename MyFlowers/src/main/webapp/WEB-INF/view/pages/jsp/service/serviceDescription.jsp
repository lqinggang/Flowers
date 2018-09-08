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
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="鲜花,销售">
<meta http-equiv="description" content="鲜花销售平台">
<link href="static/pages/css/service/service.css" rel="stylesheet"
	type="text/css" />
<link href="static/pages/css/common/page.css" rel="stylesheet"
	type="text/css" />

</head>

<body>
	<div id="servicedesc" class="service">
		<div class="logo">
			<img alt="logo" src="static/pages/images/page/logo.png">
			<div class="separate"></div>
		</div>

		<div class="title" style="margin-bottom:5%;">
			<h2>SWEVICE STATEMENT</h2>
			<h3>服务声明</h3>
		</div>
		<div class="bar">
			<div class="descriptiontitle">关于订购</div>
			<div class="descriptioncontent">
				<p>欢迎您的惠顾，您可以24小时在线提交订单，我们的工作时间是：
					xx：xx-xx：xx，如果您是在xx：xx以后提交订单，且要求在第二天清晨送达，限于实际情况，可能无法实现，第二日下午则不在此限制范围。</p>
				<p>您可以当天提交当天配送的订单，但您需在xx：xx前提交并支付成功。如果是xx：xx后才提交和支付，将按如下原则处理：如您的订单在我们配送能力之内，将安排当天送达；否则将直接转移为第二天上午送达。</p>
				<p>上述情况，如果您想确定能否按您的时间送达，请联系我司客服人员确认。</p>
				<p>每一个订单我们最少将发出3封邮件，分别为：订单提交后通知邮件，确认到款邮件，送达通知邮件。（邮件由系统自动发送。由于网络原因，偶尔会有延迟或收不到的情况，另有部分情况邮件将出现在垃圾文件夹。）</p>
			</div>
		</div>
		<div class="bar">
			<div class="descriptiontitle">关于支付</div>
			<div class="descriptioncontent">
				<p>我们支持在线支付（支付宝、微信以及网上银行等支付方式）以及非网上支付（银行转账）。</p>
				<p>如果您选择的是非网上支付方式，请您付款后务必及时通知我们，通知途径：电话、短信、传真、在线客服任选其一。如果未收到您的付款通知，我们将无法查实您的款项，订单将不会被安排，敬请支持。</p>
			</div>
		</div>
		<div class="bar">
			<div class="descriptiontitle">关于鲜花</div>
			<div class="descriptioncontent">
				<p>我们可以保证送到您手中时鲜花是新鲜的，请收货人在收到后务必立刻进行保鲜处理，以免影响货品的品质。</p>
				<p>由于鲜花的季节性和供货渠道变化性较强，可能您选择的某种花材无法供应，对于非主要花材，我们在尽量不影响您所选的鲜花效果的情况下，按照寓意相同、类似花材价值相同替代原则代替，对于主要花材，我们将在征求您的意见后进行处理，恳请您谅解；</p>
				<p>由于鲜花的保鲜时间短，如果在您要求送达的时间内，收货人不在您所提供的地址且该地址无认识收货人的代收人，我们将与收货人和订购人联系，如果都无法联系上耽误配送，我们可根据情况决定是否再次配送。如果收货人非我方原因拒收货品，我们将与订购人联系沟通，如果鲜花保鲜期已过，仍无法通过订单所留联系方式联系到订购人，或订购人知情后仍未给出回复，订单视为送达。</p>
			</div>
		</div>
		<div class="bar">
			<div class="descriptiontitle">配送说明</div>
			<div class="descriptioncontent">
				<p>如果我们在安排您的订单时，确认到您所配送的地址为郊区或者远郊，而您没有正确选择和支付相应配送费用时，我们将联系您告知配送地址和配送费用的情况，针求您的意见后处理订单。如果通过您所留联系方式我们无法及时联系到您，且您已成功支付订单中的货款，只是未支付或未足额支付配送费用，且配送费用少于x元时，我们默认将先安排配送订单，不影响订单配送，我们将另找时间再尝试联系您补付相关配送费用。如果所需补付配送费用多于x元，且我们无法通过您所留联系方式及时联系到您，所造成的订单延误我们将不承担责任。
				</p>
				<p>有关配送时间的特别说明：</p>
				<p>原则上，我们不承诺“?点?分”这样很具体的时间送达，我们只承诺类似“?月?日上午、下午、晚上”或？点以前”这样的时间送达。但如果您确有特殊要求，可在“配注”栏内注明，我们将尽力按照您要求的时间完成。但如果确有特殊原因：如收花人联系不到、城市严重交通问题等，而造成递送时间的拖延，我们将不承担赔偿责任，特别提示：我们将尽量在客户付款后3小时内送到。</p>
			</div>
		</div>
	</div>
</body>
</html>
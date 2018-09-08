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

<!-- <link rel="stylesheet" type="text/css"
	href="static/font-awesome/css/font-awesome.css">-->
<link rel="stylesheet" href="static/admin/css/animsition.min.css" />
<!--  <link rel="stylesheet" href="static/admin/css/drop-down.css" /> -->
<link rel="stylesheet" href="static/admin/css/common.css" />
<link rel="stylesheet" href="static/admin/css/system.css" />

<style type="text/css">
#holder {
	border: 1px solid red;
}

#flowers-reports .info {
	text-align: right;
	margin-top: 260px;
	margin-right: 5%;
	font-size: 18px;
	color: #C0C0C0;
	font-size: 18px;
}
</style>
</head>

<body>

	<!-- 	<div>
		<script type="text/javascript" src="static/jquery/jquery-1.8.3.js"></script>
		<script type="text/javascript" src="static/raphael/raphael.min.js"></script>
		<script type="text/javascript" src="static/admin/js/script.js"></script>
	</div> -->
	<div>
		<div class="data_wrap"
			style="background: #efeff5; padding: 10px; overflow: hidden;">
			<div class="animsition">
				<div style="background: white; width: 100%; overflow: hidden;">
					<div id="main" style="height:400px; width: 65%; float: left;"></div>
					<div id="main2" style="height:400px; width: 35%; float: left;">
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 	<script src="static/admin/js/jquery-1.11.3.min.js"></script>
	<script src="static/admin/js/jquery-ui.js"></script>
	<script src="static/admin/js/select-widget-min.js"></script>
	<script src="static/admin/js/jquery.animsition.min.js"></script>
	<script src="static/admin/js/echarts.min.js"></script>
	<script src="static/admin/js/macarons .js"></script>
	<script src="static/admin/js/common.js"></script> -->

	<script>
		$(document).ready(function() {
			//初始化切换
			$(".animsition").animsition({
				inClass : 'fade-in-right',
				outClass : 'fade-out',
				inDuration : 1500,
				outDuration : 800,
				linkElement : '.animsition-link',
				loading : true,
				loadingParentElement : 'body', //animsition wrapper element
				loadingClass : 'animsition-loading',
				unSupportCss : [ 'animation-duration',
					'-webkit-animation-duration',
					'-o-animation-duration'
				],
			
				overlay : false,
	
				overlayClass : 'animsition-overlay-slide',
				overlayParentElement : 'body'
			});
	
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document.getElementById('main'), 'macarons');
			var myChart2 = echarts.init(document.getElementById('main2'),'macarons');
			var option = {
				tooltip : {
					trigger : 'axis',
				},
				title : {
					text : '鲜花销售量/天',
				},
	
				toolbox : {
					show : true,
					feature : {
						mark : {
							show : true
						},
						dataView : {
							show : true,
							readOnly : true
						},
						magicType : {
							show : true,
							type : [ 'line', 'bar' ]
						},
						restore : {
							show : true
						},
						saveAsImage : {}
					}
				},
				calculable : true,
				xAxis : {
					type : 'category',
					boundaryGap : false,
					data : ${requestScope.nameList}
				},
				yAxis : {
					type : 'value',
					boundaryGap : [ 0, '100%' ]
				},
				dataZoom : [ {
					type : 'inside',
					start : 84,
					end : 100
				}, {
					start : 84,
					end : 100,
					handleSize : '80%',
					handleStyle : {
						color : '#fff',
						shadowBlur : 3,
						shadowColor : 'rgba(0, 0, 0, 0.6)',
						shadowOffsetX : 2,
						shadowOffsetY : 2
					}
				} ],
				series : [
					{
						name : '销售量',
						type : 'line',
						smooth : true,
						itemStyle : {
							normal : {
								color : 'rgb(255, 70, 131)'
							}
						},
						areaStyle : {
							normal : {
								color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
									offset : 0,
									color : 'rgb(255, 158, 68)'
								}, {
									offset : 1,
									color : 'rgb(255, 70, 131)'
								} ])
							}
						},
						data : ${requestScope.numberList}
					}
				]
			};
		var option2 = {
					title:{
						text:'鲜花总销售记录',
					},
				    tooltip: {
				        trigger: 'item',
				        formatter: function(data){
				    
							return data.name + '</br>' + '数量：' + data.value + '</br>占比：' + data.percent + '%'+'</br>';
						}
				    },
				    toolbox: {
				        show : true,
				         feature : {
				            mark : {show: true},
				            dataView : {show: true, readOnly: false},
				            restore : {show: true},
				            saveAsImage : {show: true}
				        }
				    },
				    legend: {
				    	orient: 'horizontal', // 'vertical'
				    	icon:'pie',
				       // orient: 'vertical',
				        x: 'right',
				        y: 'bottom',
				        selectedMode:true,
				        data:${requestScope.salesNameList}
				    },
				    series: [
				        {
				            name:'鲜花销售量',
				            center:['50%','50%'],
				            type:'pie',
				            radius: ['50%', '65%'],
				            avoidLabelOverlap: false,
				            label: {
				                normal: {
				                    show: false,
				                    position: 'center',
				                },
				                emphasis: {
				                    show: true,
				                    textStyle: {
				                        fontSize: '20',
				                        fontWeight: 'bold'
				                    }
				                }
				            },
				            labelLine: {
				                normal: {
				                    show: false
				                }
				            },
				            data:${requestScope.salesList}
				        }
				    ]
				};
	
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
			myChart2.setOption(option2,true);	
	
		});
	</script>


</body>
</html>

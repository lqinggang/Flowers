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

<link rel="stylesheet" href="static/admin/css/animsition.min.css" />
<link rel="stylesheet" href="static/admin/css/common.css" />
<link rel="stylesheet" href="static/admin/css/system.css" />
</head>

<body>


	<div>
		<div class="data_wrap"
			style="background: #efeff5; padding: 10px; overflow: hidden;">
			<div class="animsition">
				<div style="background: white; width: 100%; overflow: hidden;">
					<div id="users" style="height:400px; width: 65%; float: left;"></div>
					<div id="users2" style="height:400px; width: 35%; float: left;">
					</div>
				</div>
			</div>
		</div>
	</div>



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
			var myChart = echarts.init(document.getElementById('users'), 'macarons');
			var myChart2 = echarts.init(document.getElementById('users2'),'macarons');
			var option = {
				tooltip : {
					trigger : 'axis',
				},
				title : {
					text : '用户注册量/天',
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
					data : ${requestScope.usersNameList}
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
						name : '数量',
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
									color : 'rgb(255, 218, 185)'
								}, {
									offset : 1,
									color : 'rgb(255, 105, 180)'
								} ])
							}
						},
						data : ${requestScope.usersNumberList}
					}
				,{
						name : '男',
						type : 'line',
						smooth : true,
						itemStyle : {
							normal : {
								color : 'rgb(255, 127, 80)'
							}
						},
						areaStyle : {
							normal : {
								color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
									offset : 0,
									color : 'rgb(255, 250, 205)'
								}, {
									offset : 1,
									color : 'rgb(255, 140, 0)'
								} ])
							}
						},
						data : ${requestScope.manList}
					},{
						name : '女',
						type : 'line',
						smooth : true,
						itemStyle : {
							normal : {
								color : 'rgb(255, 0, 255)'
							}
						},
						areaStyle : {
							normal : {
								color : new echarts.graphic.LinearGradient(0, 0, 0, 1, [ {
									offset : 0,
									color : 'rgb(255, 250, 240)'
								}, {
									offset : 1,
									color : 'rgb(255, 218, 185)'
								} ])
							}
						},
						data : ${requestScope.womenList}
					}
				]
			};
		var option2 = {
					title:{
						text:'购买量前10用户',
					},
				    tooltip: {
				        trigger: 'item',
				        formatter: function(data){
				    
							return data.name + '</br>' + '累计购买量：￥' + data.value + '</br>占比：' + data.percent + '%'+'</br>';
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
				        data:${requestScope.usersNameSalesList}
				    },
				    series: [
				        {
				            name:'用户购买量',
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
				            data:${requestScope.usersSalesReport}
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

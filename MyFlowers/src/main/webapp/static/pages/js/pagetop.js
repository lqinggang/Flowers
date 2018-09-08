
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

layui.use('element', function() {
	var element = layui.element;
});
//Demo
layui.use('form', function() {
	var form = layui.form;

	//监听提交
	form.on('submit(formDemo)', function(data) {
		layer.msg(JSON.stringify(data.field));
		return false;
	});
});
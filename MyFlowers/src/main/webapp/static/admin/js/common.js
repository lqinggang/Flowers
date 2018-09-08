$(function(){
	
	$(".ui-select").selectWidget({
		change       : function (changes) {
			return changes;
		},
		effect       : "slide",
		keyControl   : true,
		speed        : 200,
		scrollHeight : 250
	});
			
	
})

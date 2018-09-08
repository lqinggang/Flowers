$(function() {

	//全局的checkbox选中和未选中的样式
	var $allCheckbox = $('input[type="checkbox"]'), //全局的全部checkbox
		$wholeChexbox = $('.whole_check'),
		$carttable = $('#cartinfo'),
		$sonCheckBox = $('.son_check'); //每个商铺下的商品的checkbox

	$allCheckbox.click(function() {
		/*alert($(this).attr('disabled') != 'disabled');*/
		if ($(this).is(':checked')) {
			$(this).next('label').addClass('mark');
		} else {
			$(this).next('label').removeClass('mark');
		}

	});

	//===============================================全局全选与单个商品的关系================================
	$wholeChexbox.click(function() {
		var $checkboxs = $carttable.find('input[type="checkbox"]');
		/*alert($(this).attr('disabled') != 'disabled');*/
		if ($(this).is(':checked')) {
			$checkboxs.prop("checked", true);
			$checkboxs.next('label').addClass('mark');

		} else {
			$checkboxs.prop("checked", false);
			$checkboxs.next('label').removeClass('mark');
		}
		totalMoney();
	});


	$sonCheckBox.each(function() {
		$(this).click(function() {
			if ($(this).is(':checked')) {
				//判断：所有单个商品是否勾选
				var len = $sonCheckBox.length;
				var num = 0;
				$sonCheckBox.each(function() {
					if ($(this).is(':checked')) {
						num++;
					}
				});
				if (num == len) {
					$wholeChexbox.prop("checked", true);
					$wholeChexbox.next('label').addClass('mark');
				}
			} else {
				//单个商品取消勾选，全局全选取消勾选
				$wholeChexbox.prop("checked", false);
				$wholeChexbox.next('label').removeClass('mark');
			}
			totalMoney();
		})
	})




	//商品数量--------------------------
	var $plus = $('.plus'),
		$reduce = $('.reduce');
	$plus.click(function() {
		var $inputVal = $(this).prev('input'),
			$count = parseInt($inputVal.val()),
			$obj = $(this).parents('.order_amount').find('.reduce'),
			$priceTotalObj = $(this).parents('.order_amount').find('.amount'),
			$price = $(this).parents('.goods').find('.price').html(), //单价
			$priceTotal = $count * parseInt($price.substring(1));
		$inputVal.val($count);
		$priceTotalObj.html('￥' + $priceTotal);
		if ($inputVal.val() > 1 && $obj.hasClass('reSty')) {
			$obj.removeClass('reSty');
		}
		totalMoney();
	});

	$reduce.click(function() {
		var $inputVal = $(this).next('input'),
			$count = parseInt($inputVal.val()),
			$priceTotalObj = $(this).parents('.order_amount').find('.amount'),
			$price = $(this).parents('.goods').find('.price').html(), //单价
			$priceTotal = $count * parseInt($price.substring(1));
		if ($inputVal.val() > 1) {
			$inputVal.val($count);
			$priceTotalObj.html('￥' + $priceTotal);
		}
		if ($inputVal.val() == 1 && !$(this).hasClass('reSty')) {
			$(this).addClass('reSty');
		}
		totalMoney();
	});

	//移除商品--------------
	$('.remove').click(function() {
		/*		$order_lists = $(this).parents('.order_lists');
				$order_content = $order_lists.parents('.order_content');
				$('.model_bg').fadeIn(300);
				$('.my_model').fadeIn(300);*/
		totalMoney();
	});

	//总计---------------
	function totalMoney() {
		var total_money = 0;
		var total_count = 0;
		var calBtn = $('.calBtn a');
		$sonCheckBox.each(function() {
			if ($(this).is(':checked')) {
				var amount = parseFloat($(this).parents('.goods').find('.amount').val());
				//				alert(amount);
				var price = parseFloat($(this).parents('.goods').find('.price').html().substring(1));
				//				alert(price);
				total_money += (amount * price);
				total_count += amount;
			}
		});
		$('.total_text').html('￥' + total_money.toFixed(2));
		$('.piece_num').html(total_count);

		// console.log(total_money,total_count);

		if (total_money != 0 && total_count != 0) {
			if (!calBtn.hasClass('btn_sty')) {
				calBtn.addClass('btn_sty');
			}
		} else {
			if (calBtn.hasClass('btn_sty')) {
				calBtn.removeClass('btn_sty');
			}
		}
	}

});
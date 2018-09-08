
/*
 * author：Jzq
 * time：2018年1月2日
 * version ：V1.0
 * 
 */
;(function($, window, document, undefined) {
	$.fn.commonSearch = function(options, callback) {
		$.fn.commonSearch.callback = callback;
		$.fn.commonSearch.RequestPramData = new Array();
		$.fn.commonSearch.RequestUrl = null;
		$.fn.commonSearch.param = null;
		var defaults = {
			url: "",
			title: "你已选择",
			initParam:null,
			dataList: [{
				id: "demo1",
				title: "title1",
				selectorList: [{
					id: "selector1",
					name: "选项一"
				}],
				multipleSelect: false,
				more: false
			}],
			style:{
				margin:"auto",
				width:"1200px",
				background: "#FFFFFF",
				border:"1px solid #E0DEDE"
			},
		};
		var settings = $.extend({}, defaults, options);
		$.fn.commonSearch.RequestUrl = settings.url;
		var $this = this;
		$this.css(defaults.style);
		$this.append('<div class="yixuan"><table><tr class="new"><td><p class="part-title-icon-bg part-title-icon-bg-blue"><i class="glyphicon glyphicon-compressed"></i></p></td><td width="80" align="left"><strong>' + settings.title + '</strong></td><td><div class="yixuanNavList"></div></td></tr></table></div>')

		var $selDiv = $("<div></div>");
		$selDiv.addClass("sel");
		$this.append($selDiv);
		$.each(settings.dataList, function(index, value) {
			var $article = $('<article id="' + value.id + '"></article>');
			$selDiv.append($article);
			var $aside = $('<aside><b>' + value.title + '：</b></aside>');
			$article.append($aside);
			var $section = $("<section></section>");
			$article.append($section);
			var selectorLength = value.selectorList.length;
			if(value.multipleSelect == true) {
				var $multiBtn = $('<a id="' + value.id + '_multiBtn" href="javascript:void(0);" class="" data-name="moreDown"><i class="fa fa-plus"></i><span>多选</span></a>');
				$section.append($multiBtn);
				$multiBtn.bind("click", function() {
					multiBtnClick($multiBtn);
				});

			}
			if(selectorLength > 5 || value.more == true) {
				var $moreBtn = $('<a id="' + value.id + '_moreBtn" href="javascript:void(0);" class=""><span>更多</span><i class=" fa fa-chevron-down"></i></a>');
				$section.append($moreBtn);
				$moreBtn.bind("click", function() {
					moreBtnClick($moreBtn);
				});
			}
			var $shouqiBtn = $('<a id="' + value.id + '_shouqiBtn" href="javascript:void(0);" class="hidden" data-name="moreUp"><span>收起</span><i class=" fa fa-chevron-up"></i></a>')
			$section.append($shouqiBtn);
			$shouqiBtn.bind("click", function() {
				shouqi($shouqiBtn);
			});

			var $jzqDiv = $("<div class='jzq'></div>");
			$article.append($jzqDiv);
			var $p = $("<p></p>");
			$jzqDiv.append($p);
			$.each(value.selectorList, function(idx, val) {
				var $a = $('<a data-parent="' + value.id + '" data-id="' + val.id + '"></a>')
				$p.append($a);
				var $i = $('<i id="' + val.id + 'i" data-parent_id="' + value.id + '" data-parent_title="' + value.title + '" data-id="' + val.id + '" data-name="' + val.name + '"></i>');
				$a.append($i);
				$i.bind("click", function() {
					$i.toggleClass("chbackground");
				});
				var $span = $('<span id="' + val.id + '_span" data-parent_id="' + value.id + '" data-parent_title="' + value.title + '" data-id="' + val.id + '" data-name="' + val.name + '">' + val.name + '</span>');
				$a.append($span);
				if(!$i.hasClass("ch")) {
					$span.bind("click", function() {
						onclickFor($span);
					});
				}

			});

			var $multiBtnGroup = $('<div class="multiBtn"></div>');
			$jzqDiv.append($multiBtnGroup);
			var $saveBtn = $('<button class="btn-success  btn-group-sm">确认</button>');
			$multiBtnGroup.append($saveBtn);
			$saveBtn.bind("click", function() {
				multiSave($saveBtn);
			});
			var $cancelBtn = $('<button class="btn-danger  btn-group-sm">取消</button>');
			$multiBtnGroup.append($cancelBtn);
			$cancelBtn.bind("click", function() {
				multiCancel($cancelBtn);
			})

		});
		var $articleSearch = $('<article id="" style="padding:5px"></article>');
		$selDiv.append($articleSearch);
		var $searchRow = $('<div class="row-fluid"></div>');
		$articleSearch.append($searchRow);
		var $rowDiv = $('<div class=" col-lg-6 input-group"></div>');
		$searchRow.append($rowDiv);
		var $inputGroup = $('<input id="searchInput_aaa" type="text" class="form-control" placeholder="在当前条件下搜索...">');
		$rowDiv.append($inputGroup);
		var $groupSpan = $('<span class="input-group-btn"></span>');
		$rowDiv.append($groupSpan);
		var $searchBtn = $('<button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>');
		$groupSpan.append($searchBtn);
		$searchBtn.bind("click",function(){
			searchBtnClick($inputGroup.val());
		});
		if(settings.initParam != null){
			onclickForBuildDIV(settings.initParam);
			$.fn.commonSearch.RequestPramData.push(settings.initParam);
		}
		return this;
	}
	//点击查询按钮
	function searchBtnClick(query){
		var paramArray = new Array();
		if(query != null && query!= ""){
			if($("#keyword_aaa").text() == ""){
				var $aa = $('<a id="keywordForaa">关键词：<span id="keyword_aaa">' + query + '</span><span class="tit-close">×</span></a>');
				$(".yixuanNavList").append($aa);
				$aa.bind("click", function() {
					$(this).remove();
					resetData("keyword");
					$("#searchInput_aaa").val(null);
				});
			}else{
				$("#keyword_aaa").text(query);
			}
			var param = {
					requestParamId: "keywordId",
					requestParamName: query
				};
				paramArray.push(param);
				var requestData = {
					parentId: "keyword",
					parentName: "关键词",
					requestData: paramArray
				};
				$.each($.fn.commonSearch.RequestPramData, function(index, value) {
					if("keyword" == value.parentId) {
						$.fn.commonSearch.RequestPramData.remove(index);
					}
				});
				$.fn.commonSearch.RequestPramData.push(requestData);
		}
		Get_AJAX();
	}
	//多选按钮点击事件
	function multiBtnClick(_this) {
		//选项组div
		var $selectDiv = $(_this).parent("section").siblings("div");
		//设置选项组高度切换
		$selectDiv.toggleClass("divMulti");
		$(_this).hide();
		$(_this).next("a").hide();
		$(_this).next("a").next("a").removeClass("hidden").show();

		//为每个选项设置复选框和点击事件
		$selectDiv.find("i").toggleClass("ch").removeClass("chbackground");
		$selectDiv.find("span").unbind("click");
		$selectDiv.find("span").bind("click", function() {
			$(this).siblings("i").toggleClass("chbackground");
		});
		//隐藏更多按钮
		$selectDiv.children().siblings("div").show();
	}
	//更多按钮点击事件
	function moreBtnClick(_this) {
		//_this ：更多按钮
		//选项组div
		var $selectDiv = $(_this).parent("section").siblings("div");
		//设置选项组高度切换
		$selectDiv.toggleClass("divMulti");
		$(_this).prev("a").hide();
		$(_this).hide();
		$(_this).next("a").removeClass("hidden").show();
		//屏蔽选项组中的确认取消按钮
		$selectDiv.children().siblings("div").hide();

		$selectDiv.find("span").unbind("click");
		$selectDiv.find("span").bind("click", function() {
			onclickFor(this);
		});
	}

	function shouqi(_this) {
		//_this ：收起按钮
		//选项组div
		var $selectDiv = $(_this).parent("section").siblings("div");
		//设置选项组高度切换
		$selectDiv.toggleClass("divMulti");
		$(_this).hide();
		$(_this).prev("a").show();
		$(_this).prev("a").prev("a").show();
		$selectDiv.find("i").removeClass("ch").removeClass("chbackground");
		//屏蔽选项组中的确认取消按钮
		$selectDiv.children().siblings("div").hide();
		$selectDiv.find("span").unbind("click");
		$selectDiv.find("span").bind("click", function() {
			onclickFor(this);
		});
	}

	function multiCancel(_this) {
		//_this ：取消按钮
		//选项组div
		var $selectDiv = $(_this).parent("div").parent("div");
		//设置选项组高度切换
		$selectDiv.toggleClass("divMulti");
		$selectDiv.find("i").removeClass("ch").removeClass("chbackground");
		//屏蔽选项组中的确认取消按钮
		$selectDiv.children().siblings("div").hide();
		$selectDiv.siblings("section").children("a:first-child").show().next("a").show().next("a").hide();
		$selectDiv.find("span").unbind("click");
		$selectDiv.find("span").bind("click", function() {
			onclickFor(this);
		});
	}

	function multiSave(_this) {
		//_this ：确认按钮
		//选项组div

		var $selectDiv = $(_this).parent("div").parent("div");
		var _parentId = null;
		var _parentName = null;
		var paramArray = new Array();
		$.each($selectDiv.find("i"), function() {
			if($(this).hasClass("chbackground")) {
				var parentId = $(this).attr("data-parent_id");
				var parentName = $(this).attr("data-parent_title");
				var id = $(this).attr("data-id");
				var name = $(this).attr("data-name");
				_parentId = parentId;
				_parentName = parentName;
				var param = {
					requestParamId: id,
					requestParamName: name
				}
				paramArray.push(param);
			}
		});
		if(_parentId != null) {
			var requestData = {
				parentId: _parentId,
				parentName: _parentName,
				requestData: paramArray
			};
			onclickForBuildDIV(requestData);
			$.fn.commonSearch.RequestPramData.push(requestData);
		};

		//关闭多选div
		multiCancel(_this);
		Get_AJAX();
	}

	function onclickForBuildDIV(requestData) {
		var parentId = requestData.parentId;
		var parentName = requestData.parentName;
		var nameShow = "";
		for(var i = 0; i < requestData.requestData.length; i++) {
			var id = requestData.requestData[i].requestParamId;
			var name = requestData.requestData[i].requestParamName;
			nameShow = nameShow + name + "、";

		}
		var $aa = $('<a data-parent_id="' + parentId + '">' + parentName + '：' + nameShow.substr(0, nameShow.length - 1) + '<span class="tit-close">×</span></a>');
		$(".yixuanNavList").append($aa);
		$aa.bind("click", function() {
			$(this).remove();
			resetData(parentId);
			var delParentId = $(this).attr("data-parent_id");
			$("#" + parentId).show();
			$("#" + parentId).find("span").unbind("click");
			$("#" + parentId).find(".jzq span").bind("click", function() {
				onclickFor(this);
			});
		});
		$("#" + parentId).hide();
	}

	function onclickFor(_this) {
		var parentId = $(_this).attr("data-parent_id");
		var parentName = $(_this).attr("data-parent_title");
		var id = $(_this).attr("data-id");
		var name = $(_this).attr("data-name");
		var paramArray = new Array();

		if(typeof(id) != "undefined") {
			var $aa = $('<a>' + parentName + '：' + name + '<span class="tit-close">×</span></a>');
			$(".yixuanNavList").append($aa);
			$aa.bind("click", function() {
				$(this).remove();
				$("#" + parentId).show();
				resetData(parentId);
			});
			$("#" + parentId).hide();
			var param = {
				requestParamId: id,
				requestParamName: name
			};
			paramArray.push(param);
			var requestData = {
				parentId: parentId,
				parentName: parentName,
				requestData: paramArray
			};
			$.fn.commonSearch.RequestPramData.push(requestData);
		}

		Get_AJAX();

	}

	function resetData(parentId) {
		$.each($.fn.commonSearch.RequestPramData, function(index, value) {
			if(parentId == value.parentId) {
				$.fn.commonSearch.RequestPramData.remove(index);
			}
		});
		Get_AJAX();
	}

	function Get_AJAX() {
		var rUrl = $.fn.commonSearch.RequestUrl;
		var result = null;
		/*$.ajax({
			url: rUrl,
			type: "post",
			data: {
				requestPara: JSON.stringify($.fn.commonSearch.RequestPramData),
			},
			dataType: "json",
			error: function(msg) {
				result = "请求失败";
			},
			success: function(data) {
				result = data;
			}
		});*/
		$.fn.commonSearch.callback($.fn.commonSearch.RequestPramData);

	}
	Array.prototype.remove = function(dx) {
		if(isNaN(dx) || dx > this.length) {
			return false;
		}
		for(var i = 0, n = 0; i < this.length; i++) {
			if(this[i] != this[dx]) {
				this[n++] = this[i]
			}
		}
		this.length -= 1
	}
})(jQuery, window, document);
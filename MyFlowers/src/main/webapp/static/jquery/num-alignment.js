var alignmentFns = {
    inputs: [],
    init: function(input, step, max, min, digit) {
        var width = input.width() - 3;
        var height = input.width() / 4;
        var _this = this;
        width += 3;

        input.attr("readonly", "readonly");
        //设置不分样式
        input.css("border", "none");
        input.css("width", width - height * 2 - 2);
        input.css("height", height);
        input.css("padding", "0px");
        input.css("margin", "0px");
        input.css("text-align", "center");
        input.css("vertical-align", "middle");
        input.css("line-height", height + "px");

        //添加左右加减号
        input.wrap("<div id = '" + input.attr('id') + "div' style = 'overflow:hidden;width:" + width + "px;height:" + height + "px;border: 1px solid #CCC;'></div>");
        input.before("<div id = '" + input.attr('id') + "l'  onselectstart = 'return false;' style = '-moz-user-select:none;cursor:pointer;text-align:center;width:" + height + "px;height:" + height + "px;line-height:" + height + "px;border-right: 1px solid #CCC;float:left'>-</div>");
        input.after("<div id = '" + input.attr('id') + "r'  onselectstart = 'return false;' style = '-moz-user-select:none;cursor:pointer;text-align:center;width:" + height + "px;height:" + height + "px;line-height:" + height + "px;border-left: 1px solid #CCC;float:right'> + </div>");
        //左右调用执行
        $("#" + input.attr('id') + "l").click(function() {
            _this.execute(input, step, max, min, digit, true);
        });
        $("#" + input.attr('id') + "r").click(function() {
            _this.execute(input, step, max, min, digit, false);
        });
    },
    execute: function(input, step, max, min, digit, _do) {
        var val = parseFloat(this.format(input.val(), digit));
        var ori = val;
        if (_do) val -= step;
        if (!_do) val += step;
        if (val < min) {
            val = min;
        } else if (val > max) {
            val = max;
        }
        input.val(this.format(val, digit));
    },
    format: function(val, digit) {
        if (isNaN(val)) {
            val = 0;
        }
        return parseFloat(val).toFixed(digit);
    },
    // 类型
    data: {
        default_data: {
            "step": 0.1,
            "min": 0,
            "max": 99,
            "digit": 1
        }
    },
    // 初始化
    initialize: function() {
        //使用控件必须有以下属性或者引用alignment类
        var inputs = $("input[user_data], input[data-digit], input[data-step], input[data-min], input[data-max], input.alignment");


        inputs.each(function() {
            // 记录原始input代码
            alignmentFns.inputs.push(this.outerHTML);
            //预设值数据选择
            var data = alignmentFns.data;

            var user_data = eval("data." + $(this).attr("user_data"));
            if (user_data == null) {
                user_data = JSON.parse(JSON.stringify(data.default_data));
            }

            var digit = $(this).data("digit");
            if (digit != null && !isNaN(parseFloat(digit))) {
                digit = parseFloat(digit).toFixed(0);
                user_data.digit = parseFloat(digit);
            }

            var step = $(this).data("step");
            if (step != null && !isNaN(parseFloat(step))) {
                user_data.step = parseFloat(step);
            }
            var min = $(this).data("min");
            if (min != null && !isNaN(parseFloat(min))) {
                user_data.min = parseFloat(min);
            }

            var max = $(this).data("max");
            if (max != null && !isNaN(parseFloat(max))) {
                user_data.max = parseFloat(max);
            }
            //自动装载
            alignmentFns.init($(this), user_data.step, user_data.max, user_data.min, user_data.digit);

            var data_edit = $(this).data("edit");
            if (data_edit) {
                $(this).attr("readonly", null);
            }
        });
    },
    destroy: function() {
        var inputs = this.inputs;
        $.each(inputs, function(index, obj) {
            var input = $(obj)[0];
            var id = input.id;
            $("#" + id + "div").replaceWith(input);
        });
    },
    // 动态添加类型
    createType: function(types) {
        $.each(types, function(index, obj) {
            alignmentFns.data[obj.type] = obj.data;
        });

    }
};
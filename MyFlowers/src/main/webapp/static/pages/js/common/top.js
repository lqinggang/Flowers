
//function flash()
function flash(_titles, _bodies) {
    var defaultOpts = {
        interval: 3000,
        fadeInTime: 'slow',
        fadeOutTime: 'slow'
    };
    var _count = _titles.length;
    var _current = 0;
    var _intervalID = null;
    var stop = function() {
        window.clearInterval(_intervalID);
    };
    var slide = function(opts) {
        if (opts) {
            _current = opts.current || 0;
        } else {
            _current = (_current >= (_count - 1)) ? 0 : (++_current);
        };
        _bodies.filter(":visible").fadeOut(defaultOpts.fadeOutTime,
        function() {
            _bodies.eq(_current).fadeIn(defaultOpts.fadeInTime);
            //_bodies.removeClass("current").eq(_current).addClass("current");
        });
        _titles.removeClass("current").eq(_current).addClass("current");
        //_titles_bg.removeClass("current").eq(_current).addClass("current");
    }; //endof slide
    var go = function() {
        stop();
        _intervalID = window.setInterval(function() {
            slide();
        },
        defaultOpts.interval);
    }; //endof go
    var itemMouseOver = function(target, items) {
        stop();
        var i = $.inArray(target, items);
        slide({
            current: i
        });
    }; //endof itemMouseOver
    _titles.hover(function() {
        if ($(this).attr('class') != 'current') {
            itemMouseOver(this, _titles);
        } else {
            stop();
        }
    },
    go);
    //_titles_bg.hover(function() { itemMouseOver(this, _titles_bg); }, go);
    _bodies.hover(stop, go);
    //trigger the slidebox
    go();
}

// 图片左右滚动
function DY_scroll(wraper, prev, next, img, speed, or) {
    var wraper = $(wraper);
    var prev = $(prev);
    var next = $(next);
    var img = $(img).find('ul');
    var w = img.find('li').outerWidth(true);
    var s = speed;
    next.click(function() {
        img.animate({
            'margin-left': -w
        },
        function() {
            img.find('li').eq(0).appendTo(img);
            img.css({
                'margin-left': 0
            });
        });
    });
    prev.click(function() {
        img.find('li:last').prependTo(img);
        img.css({
            'margin-left': -w
        });
        img.animate({
            'margin-left': 0
        });
    });
    if (or == true) {
        ad = setInterval(function() {
            next.click();
        },
        s * 1000);
        wraper.hover(function() {
            clearInterval(ad);
        },
        function() {
            ad = setInterval(function() {
                next.click();
            },
            s * 1000);
        });

    }
}

function b(){
	h = $(window).height();
	t = $(document).scrollTop();
	if(t > h){
		$('#gotop').show();
	}else{
		$('#gotop').hide();
	}
}

// 图片上下滚动
function DY_scrolls(wraper, prev, next, img, speed, or) {
    var wraper = $(wraper);
    var prev = $(prev);
    var next = $(next);
    var img = $(img).find('ul');
    var w = img.find('li').outerHeight(true);
    var s = speed;
    next.click(function() {
        img.animate({
            'margin-top': -w
        },
        function() {
            img.find('li').eq(0).appendTo(img);
            img.css({
                'margin-top': 0
            });
        });
    });
    prev.click(function() {
        img.find('li:last').prependTo(img);
        img.css({
            'margin-top': -w
        });
        img.animate({
            'margin-top': 0
        });
    });
    if (or == true) {
        ad = setInterval(function() {
            next.click();
        },
        s * 1000);
        wraper.hover(function() {
            clearInterval(ad);
        },
        function() {
            ad = setInterval(function() {
                next.click();
            },
            s * 1000);
        });

    }
}


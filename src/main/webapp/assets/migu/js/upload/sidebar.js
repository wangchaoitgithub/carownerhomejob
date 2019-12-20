/**
 * Created by SnowZhang on 2016/11/18.
 */
$(function () {
    var Accordion = function (el, multiple) {
        this.el = el || {};
        this.multiple = multiple || false;
        var links = this.el.find('.link');
        links.on('click', { el: this.el, multiple: this.multiple }, this.dropdown)
    }

    Accordion.prototype.dropdown = function (e) {
        var $el = e.data.el;
        $this = $(this), $next = $this.next();

        $next.slideToggle();
        $this.parent().toggleClass('open');
        if (!e.data.multiple) {
            $el.find('.submenu').not($next).slideUp().parent().removeClass('open');
        };
    }

    var accordion = new Accordion($('#accordion'), false);
});

$(document).ready(function (){
    $(".link  a").attr("onclick", "return showmenu(this);");
});

function showmenu(obj) {
    if($(obj).parent().hasClass("open")) {
        return false;
    }
    else if($(obj).parent().parent().hasClass("open")) {
        return false;
    }
    else if($(obj).parent().parent().parent().hasClass("open")) {
        return false;
    }
    else
        return true;
}

function showmenu(obj, id) {
    if(id == 'menu_stat') {
        var current_href = window.location.href ;
        if(current_href.indexOf("behavior.html") >= 0 || current_href.indexOf("stat_play.html") >= 0 ||
            current_href.indexOf("terminal.html") >= 0 || current_href.indexOf("stat_export.html") >= 0){

        }else {
            if(current_href.indexOf("data.html") < 0){
                //console.log(current_href);
                window.location.href="/data.html";
            }
            $("#stat_total").addClass("open");
            $("#stat_play").removeClass("open");
            $("#stat_terminal").removeClass("open");
            $("#stat_behavior").removeClass("open");
            $("#stat_export").removeClass("open");
        }
    }
    if(id == 'live_stat'){
        var current_href = window.location.href ;
        if(current_href.indexOf("pull_flow.html") >= 0 || current_href.indexOf("push_flow.html") >= 0 ){
        }else {
            if(current_href.indexOf("live_manager.html") < 0){
                //console.log(current_href);
                window.location.href="/live_manager.html";
            }
            $("#live_stat").addClass("open current");
            $("#push_flow").addClass("open");
            $("#poll_flow").removeClass("open");
        }
    }
    return true;
}



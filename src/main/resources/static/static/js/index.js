//公告悬停
jQuery(document).ready(function(){
    var scrtime;
    $(".bulletin").hover(function(){
        clearInterval(scrtime);
    },function(){
        scrtime = setInterval(function(){
            var $ul = $(".bulletin ul");
            var liHeight = $ul.find("li:last").height();
            $ul.animate({marginTop : 5 + "px"},300,function(){

                $ul.find("li:last").prependTo($ul)
                $ul.find("li:first").hide();
                $ul.css({marginTop:0});
                $ul.find("li:first").fadeIn(1000);
            });
        },5000);
    }).trigger("mouseleave");
});

$(function() {

    //首页文章接口
    $.get(domain+'/content/new_content', {}, function (data) {
        var doc = document.getElementById("templater").innerHTML;
        var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm'); //i g m是指分别用于指定区分大小写的匹配、全局匹配和多行匹配
        for (var i = 0; i < data.data.length; i++) {
            var source = doc.replace(reg, function (node, key) {
                if (key.indexOf(".") > 0) {
                    return data.data[i][key.split(".")[0]][key.split(".")[1]];
                } else {
                    return data.data[i][key];
                }
            })
            //替换模板
            $(".content_body").append(source)
        }
    }, 'json')

    //首页活跃用户接口
    $.get(domain+'/User/ActiveUser', {}, function (data) {
        var doc = document.getElementById("templater2").innerHTML;
        var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm'); //i g m是指分别用于指定区分大小写的匹配、全局匹配和多行匹配
        for (var i = 0; i < data.data.length; i++) {
            var source = doc.replace(reg, function (node, key) {
                if (key == "largeV") {
                    if (data.data[i][key] >= 1) {
                        return "user-identw";
                    } else {
                        return "user-ident";
                    }
                } else {
                    return data.data[i][key];
                }
            })
            //替换模板
            $(".fly_user-list").append(source)
        }
    }, 'json')

    //获取友情链接
    $.get(domain+'/links/getLinks',{},function (ret) {
        $(".links").empty();
        for (var i=0;i<ret.data.length;i++){
            var html="<li><a href=\""+ret.data[i].link+"\" title=\""+ret.data[i].desc+"\"target=\"_blank\">"+ret.data[i].siteName+" </a></li>";
            $(".links").append(html)
        }
    },'json')

    //生成日历方法
    function f() {
        var date = new Date();
        //当前是哪一年
        var year = date.getFullYear();
        //当前是哪个月，注意这里的月是从0开始计数的
        var month = date.getMonth();
        //当前月的第一天的日期
        var firstDay = new Date(year,month,1);
        //第一天是星期几
        var weekday = firstDay.getDay();
        //求当前月一共有多少天
        //new Date(year,month+1,0) ： month+1是下一个月，day为0代表的是上一个月的最后一天，即我们所需的当前月的最后一天。
        //getDate（）则返回这个日期对象是一个月中的第几天，我们由最后一天得知这个月一共有多少天
        var days = new Date(year,month+1,0).getDate();
        //获取此月最后一天星期几
        var lastweekday = new Date(year,month+1,0).getDay();
        var res = "<tr>";
        //输出第一天之前的空格
        for(var i=0;i<weekday;i++){
            res+="<td></td>";
        }

        for(var j=1;j<=days ;j++){
            if(j==date.getDate()){
                res +="<td id=\"today\">"+j+"</td>";
            }else{
                res +="<td>"+j+"</td>";
            }
            weekday++;
            //如果是周日则换行
            if(weekday%7 == 0){
                weekday = 0;
                res += '</tr><tr>';
            }
        }
        //输出最后一天后面的空格
        for(var i=6;i>lastweekday;i--){
            res+="<td></td>";
        }
        res=res+"</tr>";
        $(".calendar_body").html(res)
        $(".calendar_day").text(date.getFullYear()+"-" + (date.getMonth()+1) + "-" + date.getDate());
    }
    f();

    //评论列表头像hover详细信息和guestdetail大部分代码重复，暂且这样
    function comm_author_detail(){
        if (!navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){//移动端屏蔽
            var list_detail = $('<div id="list-detail" class="detail" />'),
                detail_left,//左边距
                detail_top,//顶距
                detail_time,//鼠标移开的缓冲
                li_hover = 0,
                detail_hover = 0;

            if ( !$( '#list-detail ')[0] ){
                $( '#FLY' ).after(list_detail);
            }
            list_detail = $( '#list-detail');
            //评论列表
            $(".fly_user-list").on("mouseover mouseout","li",function(event){
                if(event.type == "mouseover"){
                    //鼠标悬浮
                    var id = $(this).attr('user-id');
                    var _this = $(this), _window_width = $(window).width();
                    li_hover = 1;
                    clearTimeout(detail_time);
                    detail_time = setTimeout(function(){
                        $.ajax({
                            url:domain+'/User/getActiveUserDetail',
                            type:'GET',
                            data:{id: id },
                            beforeSend:function(){
                                //左距离
                                detail_left = _this.offset().left - 50;
                                if ( detail_left < 0 ) detail_left = 0;
                                if ( detail_left + 260 > _window_width ) detail_left = _window_width - 260;
                                //顶距
                                detail_top = _this.offset().top - 10;
                                //向上显示detail框
                                list_detail.show().css({'left':detail_left,'top':detail_top + 24,'opacity':0}).stop().animate({top:detail_top,opacity:1},300);
                                //预插入显示三角箭头
                                list_detail.html('<div class="list-detail"></div>');
                                //显示loading
                                if ( !$( '#list-detail .loading-bar' )[0] ){
                                    list_detail.append('<div class="loading-bar"><div class="user-loding"><img src="https://pjax.cn/content/templates/FLY/img/user-loading.gif" style="width:100%"></div></div>');//loading
                                }
                                $( '#list-detail .loading-bar' ).show();
                            },
                            error:function(){
                                list_detail.html('ajax error!');
                            },
                            success:function(data){
                                $( '.loading-bar' ).fadeOut(function(){
                                    var doc = document.getElementById("templater3").innerHTML;
                                    var reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm'); //i g m是指分别用于指定区分大小写的匹配、全局匹配和多行匹配
                                    var source = doc.replace(reg, function (node, key) {
                                        return data.data[key];
                                    })
                                    list_detail.html(source);
                                });
                            }
                        });
                    },80);
                }else if(event.type == "mouseout"){
                    //鼠标离开
                    li_hover = 0;
                    clearTimeout(detail_time);
                    detail_time = setTimeout(function(){
                        if ( detail_hover == 0 ){
                            list_detail.stop().animate({top:detail_top + 24,opacity:0},300,function(){list_detail.hide()});
                        }
                    },100);
                }
            })
            list_detail.hover(function(){
                detail_hover = 1;
            },function(){
                detail_hover = 0;
                clearTimeout(detail_time);
                detail_time = setTimeout(function(){
                    if ( li_hover == 0 ){
                        list_detail.stop().animate({top:detail_top + 24,opacity:0},300,function(){list_detail.hide()});
                    }
                },100);
            });
        }
    }
    comm_author_detail();
})

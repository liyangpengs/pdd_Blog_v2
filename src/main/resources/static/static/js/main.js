
var domain="http://localhost:8080/API";

if(navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)){
    $(document).ready(function(){$(".indexebox").click(function(){$(this).find(".indexebox").css("margin-top", "-33px");});});
    $(document).ready(function(){$(".comment-box").click(function(){$(this).find(".comment-reply-link").css("opacity", "1");});});
}else{
    $(document).ready(function(){
        $(".navbar-nav li").click(function(){
            $("li").removeClass("active");
            $(this).addClass("active");
        });
    });
}
$(function() {
    $('button.navbar-toggle').click(function(){
        $('body').toggleClass('out');
        $('nav.navbar-fixed-top').toggleClass('out');
        if ($('body').hasClass('out')) {
            $(this).focus();
        } else {
            $(this).blur();
        }
    });
});
//去顶部
function scrollTo(name, add, speed) {
    if (!speed) speed = 300
    if (!name) {
        $('html,body').animate({
            scrollTop: 0
        }, speed)
    } else {
        if ($(name).length > 0) {
            $('html,body').animate({
                scrollTop: $(name).offset().top + (add || 0)
            }, speed)
        }
    }
}
var scroller = $('.rollbar')
$(window).scroll(function() {
    document.documentElement.scrollTop + document.body.scrollTop > 200 ? scroller.fadeIn() : scroller.fadeOut();
})

$(document).ready(function() {
    if ($(".sidebar").length && $(".sidebar").find(".widget").length && $(window).width() > 991) {
        var e = $(".sidebar").offset().top,
            i = 0,
            o = 0,
            r = 0,
            d = 0,
            l = $($(".footer_").length ? ".footer_" : "footer.footer");
        $(document).bind("DOMSubtreeModified", function() {
        }), $(window).scroll(function() {
            i = $(".sidebar").outerHeight(), o = $($(".footer_").length ? ".footer_" : "footer.footer").offset().top, r = $(".content-wrap").outerHeight()
            if (!(r <= i)) {
                var t = $(window).scrollTop();
                d = Number($("footer.footer").outerHeight()) + Number($(".footer_").outerHeight())+ 0;
                $(window).height() - e > i ? t + i + e > o ? $(".sidebar").removeClass("fixed").addClass("pins").css({
                    bottom: 0,
                    top: "auto"
                }) : $(".sidebar").removeClass("pins").addClass("fixed").css({
                    bottom: "auto",
                    top: e
                }) : t + $(window).height() > o ? $(".sidebar").addClass("pins").removeClass("fixed").css({
                    bottom: d + 'px'
                }) : t + $(window).height() > e + i ? $(".sidebar").addClass("fixed").removeClass("pins").css({
                    bottom: 0
                }) : $(".sidebar").removeClass("fixed").removeClass("pins")
            }
        })
    }
})

AOS.init({
    offset: 200,
    duration: 500,
    easing: 'ease-in-sine',
    delay: 100,
    once: true,
});

$(function () {

    //获取登录信息
    $.get(domain+"/User/getLoginInfo",{},function (ret) {
        if(ret.code==0){
            update_page(ret.data)
        }
    },'json')


    //刷新二维码
    $(".code").click(function(){
        $(this).attr("src",domain+"/VerificationCode/getCode?t="+new Date().getTime());
    })

    //登录
    $("#send_ajax").click(function(){
        var name=$("#input1").val(),pwd=$("#input2").val(),code=$("#imgcode").val();
        if(name==''){
            $("#contentdiv_a").html("<font style='color: red'>账号不能为空</font>");
        }else if(pwd==''){
            $("#contentdiv_a").html("<font style='color: red'>密码不能为空</font>");
        }else if(code==''){
            $("#contentdiv_a").html("<font style='color: red'>验证码不能为空</font>");
        }else{
            $.post(domain+"/User/Login",{password:pwd,account:name,code:code},function (data) {
                if(data.code==1&&data.data){
                    $("#contentdiv_a").html("<font style='color: red'>"+data.data[0].defaultMessage+"</font>");
                }else if(data.code==1){
                    $("#contentdiv_a").html("<font style='color: red'>"+data.msg+"</font>");
                }else{
                    $('.code').attr('src',domain+"/VerificationCode/getCode?t="+new Date().getTime());
                    //前台保留登录信息
                    update_page(data.data)
                }
            })
        }
    })

    //登录 注册 修改密码 来回切换
    $('.ljzc').click(function(){
        $('.sign-login').hide();
        $('.sign-saomiao').hide();
        $('.sign-zhaohui').hide();
        $('.sign-reg').show();
    });
    $('.wjmm').click(function(){
        $('.sign-login').hide();
        $('.sign-reg').hide();
        $('.sign-saomiao').hide();
        $('.sign-zhaohui').show();
    });
    $('.ljdl').click(function(){
        $('.sign-reg').hide();
        $('.sign-saomiao').hide();
        $('.sign-zhaohui').hide();
        $('.sign-login').show();
    });

    //邮箱验证码
    $(".send-code").click(function(){
        if($("input[name=email]").val()==''){
            $("#contentdiv_b").html("<font style='color: red'>邮箱不能为空</font>");
            return false;
        }
        $.post(domain+"/VerificationCode/SendMainCode",{mainAddress:$("input[name=email]").val()},function (data) {
            if(data.code==0){
                $("#contentdiv_b").html("<font style='color: red'>"+data.msg+"</font>");
            }else{
                $("#contentdiv_b").html("<font style='color: red'>"+data.msg+"</font>");
            }
        },'json')
    })

    //注册
    $('#re_ajax').click(function (){
        var usrName = $("input[name=account]").val().replace(/(^\s*)|(\s*$)/g, "");
        var eml = $("input[name=email]").val().replace(/(^\s*)|(\s*$)/g, "");
        var pwd = $("input[name=password]").val().replace(/(^\s*)|(\s*$)/g, "");
        var pwd2 = $("input[name=repassword]").val().replace(/(^\s*)|(\s*$)/g, "");
        var yzm = $("input[name=reimgcode]").val().replace(/(^\s*)|(\s*$)/g, "");
        var code = $("input[name=code]").val().replace(/(^\s*)|(\s*$)/g, "");
        if(usrName.match(/\s/) || pwd.match(/\s/)){
            $('#contentdiv_b').css('margin-top','-12px').html('<font color="red">用户名和密码中不能有空格</font>');
            return false;
        }
        if(usrName == '' || pwd == ''){
            $('#contentdiv_b').css('margin-top','-12px').html('<font color="red">用户名或密码不能为空</font>');
            return false;
        }
        if(yzm == ''){
            $('#contentdiv_b').css('margin-top','-12px').html('<font color="red">验证码都不能为空</font>');
            return false;
        }
        if(usrName.length < 5 || pwd.length < 5){
            $('#contentdiv_b').css('margin-top','-12px').html('<font color="red">用户名和密码都不能小于5位</font>');
            return false;
        }
        if(pwd != pwd2){
            $('#contentdiv_b').css('margin-top','-12px').html('<font color="red">两次输入密码不相同</font>');
            return false;
        }
        if(code == ''){
            $('#contentdiv_b').css('margin-top','-12px').html('<font color="red">邮箱验证码未填写</font>');
            return false;
        }
        var params = $('#refrom').serialize();
        $.ajax({
            url:domain + '/User/Regist',
            type:'Post',
            dataType:'json',
            data:params,
            success:function(data){
                if(data.code==1&&data.data){
                    $('#contentdiv_b').css('margin-top','-12px').html('<font color="red">'+data.data[0].defaultMessage+'</font>');
                }else if(data.code==1){
                    $('#contentdiv_b').css('margin-top','-12px').html('<font color="red">'+data.msg+'</font>');
                }else{
                    $('#contentdiv_b').css('margin-top','-12px').html('<font color="red">'+data.msg+'</font>');
                    setTimeout(function () {$('.rbtn').val("");$('#contentdiv_b').html("")},500);
                    setTimeout(function () {$('.sign-reg').hide();$('.sign-login').show()},800);
                    $('.code').attr('src',domain+"/VerificationCode/getCode?t="+new Date().getTime());
                }
            },
            error: function () {
                $('#contentdiv_b').css('margin-top','-12px').html('<font color="red">调用接口失败</font>');
            }
        });
    });

    //找回密码
    $('#pwre_ajax').click(function(){
        var xemail = $('#reemail').val();
        var isxemail = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
        if (xemail=='') {
            $('#contentdiv_t').css('margin-bottom','-12px').html('<font color="red">邮箱不能为空</font>');
        }else if(!isxemail.test(xemail)){
            $('#contentdiv_t').css('margin-bottom','-12px').html('<font color="red">邮箱格式不正确</font>');
        }else{
            $.ajax({
                url:domain + '/VerificationCode/ResetPwdMail',
                type:'post',
                dataType:'json',
                data:{email:xemail},
                success:function(data){
                    $('#contentdiv_t').css('margin-bottom','-12px').html('<font color="red">'+data.msg+'</font>');
                }
            });
        }
    });

    //生成登录后效果
    function update_page(data) {
        $('.modal').modal('hide');
        var rzphoto = data.largeV >0 ? 'author-idents' : 'author-identw';
        var dlnickname = data.nickName != undefined ? data.nickName : '未命名';
        var headSrc = data.headSrc != undefined ? data.headSrc : 'http://gayligayqi.com/static/picture/15239783723565.png';
        var backgroundImg = data.backgroundImg != undefined ? data.backgroundImg : 'http://gayligayqi.com/static/images/weibo.jpg';
        $('.dlname').html('<div class="' + rzphoto + '"></div>');
        $('.dlset').html('<a href="/userInfo.html"><i class="fa fa-pencil-square-o"></i> 修改资料</a>');
        $('.lgset').html('');
        $('.dlcd').html('<div class="col-sm-12 sideli dlbt" style="color:red">'+dlnickname+'</div>');
        $('.dlimg').html('<a href="javascript:void(0)"><img src="' + headSrc + '"></a></a>');
        $('#shead').css("background","url("+backgroundImg+") center center no-repeat");
        $('.login-nav').hide();
        $('.logout-nav').show();
        $('.login-avatars').html('<img src="' + headSrc + '" class="login-nav-avatar">');
        $('#mobile-login').addClass('login_logout').html('<a href="javascript:void(0)" onclick="loginOut()" ><span><img src="' + headSrc + '" class="login-nav-avatar"></span> 个人 </a>');
        setTimeout(function () {$('.lbtn').val("")},400);
        $('#user-login').hide();
        $('#user-div').show();
        $('#myLogin').hide();
        $("#updatefrom input[name=username]").val(data.account)
        if(data.headSrc!=undefined){
            $("#updatefrom img[clsas=touxiangimg]").attr("src",data.headSrc)
        }
        $("#updatefrom input[name=zhuye]").val('http://gayligayqi.com/author.html?id='+data.id)
        $("#updatefrom input[name=name]").val(data.nickName==undefined?'未命名':data.nickName)
        $("#updatefrom input[name=email]").val(data.email)
        $("#updatefrom input[name=qq]").val('871080854')
        if(data.sex!=undefined){
            if(data.sex=='男'){
                $(".fasex").html('<i class="fa fa-mars"></i>')
                $("#updatefrom input[name=sex]").get(0).attr("checked","checked")
            }else{
                $(".fasex").html('<i class="fa fa-venus"></i>')
                $("#updatefrom input[name=sex]").get(1).attr("checked","checked")
            }
        }
        $("#updatefrom textarea[name=description]").text(data.userDesc)
    }
})
//注销登录
function loginOut() {
    $.get(domain+"/User/LoginOut",{},function (data) {
        if(data.code==0){
            //刷新下页面
            location.reload();
        }
    },'json')
}
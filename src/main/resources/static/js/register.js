layui.use(['form', 'jquery', 'layer'], function () {
    var form = layui.form;
    var $ = layui.jquery;
    var layer = layui.layer;
    //添加表单失焦事件
    //验证表单
    $('#user').blur(function () {
        var user = $(this).val();
        var reg = /^[\w]{4,24}$/;
        if (user.match(reg)) {
            $.ajax({
                url: '/checkUser',
                type: 'post',
                dataType: 'json',
                data: {username: user},
                //验证用户名是否可用
                success: function (data) {
                    if (data.code == 200) {
                        $('#ri').removeAttr('hidden');
                        $('#wr').attr('hidden', 'hidden');
                    } else {
                        $('#wr').removeAttr('hidden');
                        $('#ri').attr('hidden', 'hidden');
                        layer.msg('当前用户名已被占用! ')
                    }
                }
            })
        } else {
            $('#wr').removeAttr('hidden');
            $('#ri').attr('hidden', 'hidden');
            layer.msg("用户名长度太短")
        }

    });

    // you code ...
    // 为密码添加正则验证
    $('#pwd').blur(function () {
        var reg = /^[\w]{6,12}$/;
        if (!($('#pwd').val().match(reg))) {
            //layer.msg('请输入合法密码');
            $('#pwr').removeAttr('hidden');
            $('#pri').attr('hidden', 'hidden');
            layer.msg('密码长度太短');
        } else {
            $('#pri').removeAttr('hidden');
            $('#pwr').attr('hidden', 'hidden');
        }
    });

    //验证两次密码是否一致
    $('#rpwd').blur(function () {
        if ($('#pwd').val() != $('#rpwd').val()) {
            $('#rpwr').removeAttr('hidden');
            $('#rpri').attr('hidden', 'hidden');
            layer.msg('两次输入密码不一致!');
        } else {
            $('#rpri').removeAttr('hidden');
            $('#rpwr').attr('hidden', 'hidden');
        }
        ;
    });

    //
    //添加表单监听事件,提交注册信息
    form.on('submit(sub)', function () {
        if ($('#wr').attr("hidden") !== "hidden" || $('#pwr').attr("hidden") !== "hidden" || $('#rpwr').attr("hidden") !== "hidden") {
            layer.msg('请检查输入项！');
            return false;
        }
        $.ajax({
            url: '/register',
            contentType: "application/json;charset=UTF-8",
            type: 'post',
            dataType: 'json',
            data: JSON.stringify({
                "userName": $('#user').val(),
                "password": $('#pwd').val()
            }),
            success: function (data) {
                if (data.code == 200) {
                    layer.msg('注册成功');
                    location.href = "/";
                } else {
                    layer.msg('注册失败');
                }
            }
        })
        //防止页面跳转
        return false;
    });

});
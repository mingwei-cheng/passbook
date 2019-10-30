layui.use(['form', 'layer', 'jquery'], function () {

    // 操作对象
    var form = layui.form;
    var $ = layui.jquery;
    form.on('submit(login)', function (data) {
        $.ajax({
            url: '/login',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(data.field),
            dataType: 'json',
            type: 'post',
            success: function (data) {
                if (data.code == 200) {
                    location.href = "/html/index.html";
                } else {
                    layer.msg('登录名或密码错误');
                }
            }
        })
        return false;
    })

});
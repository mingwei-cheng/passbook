layui.use('table', function () {
    var table = layui.table;
    var $ = layui.jquery;
    var tableInit = table.render({
        elem: '#test'
        , url: '/getAllPass'
        , title: "网站密码列表"
        , page: {
            layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
            , groups: 5
            , theme: 'center'
        }
        , even: true
        , request: {
            statusName: 'msg'//数据状态的字段名称，默认：code
            , statusCode: 200 //成功的状态码，默认：0
        }
        , toolbar: '#addBar'
        , limit: 5
        , limits: [5, 10, 20, 50]
        , parseData: function (res) { //res 即为原始返回的数据
            return {
                "code": 0, //解析接口状态
                "count": res.data.total, //解析数据长度
                "data": res.data.list //解析数据列表
            };
        }
        , cols: [[
            {
                type: 'numbers',
                title: 'ID',
                width: '10%'
            },
            {
                hide: true,
                field: 'id',
                width: '10%'
            }, {
                field: 'website',
                title: '网站名',
                sort: true
            }, {
                field: 'userName',
                title: '用户名',
                sort: true
            }, {
                field: 'password',
                title: '密码'
            }, {
                field: 'secretQuestion',
                title: '密保问题'
            }, {
                field: 'secretAnswer',
                title: '密保答案'
            }, {
                fixed: 'right',
                title: '操作',
                toolbar: '#barDemo'
            }
        ]]
        , text: {
            none: "暂无数据"
        }
    });

    table.on('toolbar(test)', function (obj) {
        var event = obj.event;
        if(event === 'add'){
            layer.open({
                title: '新增网站'
                , anim: 2
                , scrollbar: false
                , resize: false
                , area: ['500px', 'auto']
                , skin: 'layui-layer-molv'
                , id: 'add_web'
                , content:
                    '<div class="layui-circle" id="new_web" style="width: 420px;  margin-left:7px; margin-top:10px;">'
                    + '<div class="layui-group">'
                    + '<span class="layui-word-aux">网站名:</span>'
                    + '<input type="text" class="layui-input" required  lay-verify="required" id="add_website" >' +
                    '<!-- 对号 -->' +
                    '<div class="layui-inline">' +
                    '<i class="layui-icon" id="add_website_icr" style="color: green;font-weight: bolder;" hidden></i>' +
                    '</div>' +
                    '<!-- 错号 -->' +
                    '<div class="layui-inline">' +
                    '<i class="layui-icon" id="add_website_ice" style="color: red; font-weight: bolder;" hidden>ဆ</i>' +
                    '</div>'
                    + '</div>'
                    + '<div class="layui-group">'
                    + '<span class="layui-word-aux">用户名:</span>'
                    + '<input type="text" class="layui-input" required  lay-verify="required"  id="add_user" >' +
                    '<!-- 对号 -->' +
                    '<div class="layui-inline">' +
                    '<i class="layui-icon" id="add_user_icr" style="color: green;font-weight: bolder;" hidden></i>' +
                    '</div>' +
                    '<!-- 错号 -->' +
                    '<div class="layui-inline">' +
                    '<i class="layui-icon" id="add_user_ice" style="color: red; font-weight: bolder;" hidden>ဆ</i>' +
                    '</div>'
                    + '</div>'
                    + '<div class="layui-group">'
                    + '<span class="layui-word-aux">密码:</span>'
                    + '<input type="text" class="layui-input" required  lay-verify="required" id="add_pass" >' +
                    '<!-- 对号 -->' +
                    '<div class="layui-inline">' +
                    '<i class="layui-icon" id="add_pass_icr" style="color: green;font-weight: bolder;" hidden></i>' +
                    '</div>' +
                    '<!-- 错号 -->' +
                    '<div class="layui-inline">' +
                    '<i class="layui-icon" id="add_pass_ice" style="color: red; font-weight: bolder;" hidden>ဆ</i>' +
                    '</div>'
                    + '</div>'
                    + '<div class="layui-group">'
                    + '<span class="layui-word-aux">密保问题:</span>'
                    + '<input type="text" class="layui-input" id="add_question" >' +
                    '<!-- 对号 -->' +
                    '<div class="layui-inline">' +
                    '<i class="layui-icon" id="add_pass_icr" style="color: green;font-weight: bolder;" hidden></i>' +
                    '</div>' +
                    '<!-- 错号 -->' +
                    '<div class="layui-inline">' +
                    '<i class="layui-icon" id="add_pass_ice" style="color: red; font-weight: bolder;" hidden>ဆ</i>' +
                    '</div>'
                    + '</div>'
                    + '<div class="layui-group">'
                    + '<span class="layui-word-aux">密保答案:</span>'
                    + '<input type="text" class="layui-input" id="add_answer" >' +
                    '<!-- 对号 -->' +
                    '<div class="layui-inline">' +
                    '<i class="layui-icon" id="add_pass_icr" style="color: green;font-weight: bolder;" hidden></i>' +
                    '</div>' +
                    '<!-- 错号 -->' +
                    '<div class="layui-inline">' +
                    '<i class="layui-icon" id="add_pass_ice" style="color: red; font-weight: bolder;" hidden>ဆ</i>' +
                    '</div>'
                    + '</div>'
                    + '</div>'
                , btn: ['保存', '取消']
                , btnAlign: 'c'
                , yes: function (index, layero) {
                    var website = document.getElementById("add_website").value;
                    var user = document.getElementById("add_user").value;
                    var pass = document.getElementById("add_pass").value;
                    var question = document.getElementById("add_question").value;
                    var answer = document.getElementById("add_answer").value;
                    var flag = true;
                    if (website === "") {
                        $('#add_website_ice').removeAttr('hidden');
                        $('#add_website_icr').attr('hidden', 'hidden');
                        flag = false;
                    } else {
                        $('#add_website_icr').removeAttr('hidden');
                        $('#add_website_ice').attr('hidden', 'hidden');
                    }
                    if (user === "") {
                        $('#add_user_ice').removeAttr('hidden');
                        $('#add_user_icr').attr('hidden', 'hidden');
                        flag = false;
                    } else {
                        $('#add_user_icr').removeAttr('hidden');
                        $('#add_user_ice').attr('hidden', 'hidden');
                    }
                    if (pass === "") {
                        $('#add_pass_ice').removeAttr('hidden');
                        $('#add_pass_icr').attr('hidden', 'hidden');
                        flag = false;
                    } else {
                        $('#add_pass_icr').removeAttr('hidden');
                        $('#add_pass_ice').attr('hidden', 'hidden');
                    }
                    if (flag) {
                        $.ajax({
                            url: "/insertPass",
                            contentType: "application/json;charset=UTF-8",
                            type: 'post',
                            data: JSON.stringify({
                                website: website,
                                userName: user,
                                secretQuestion: question,
                                password: pass,
                                secretAnswer: answer
                            }),
                            success: function (data) {
                                if (data.code !== 200) {
                                    layer.msg(data.msg, {icon: 5});//失败的表情
                                    return;
                                } else {
                                    tableInit.reload();
                                    layer.msg(data.msg, {
                                        icon: 6,//成功的表情
                                        time: 1000 //1秒关闭（如果不配置，默认是3秒）
                                    });
                                }
                            }
                        });
                    }
                }
                , cancel: function () {
                    //右上角关闭回调
                    //return false 开启该代码可禁止点击该按钮关闭
                }
            });
        }
    });

    table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

        if (layEvent === 'del') { //删除
            layer.confirm('真的要删除吗', function (index) {
                $.ajax({
                    url: "/delPass",
                    contentType: "application/json;charset=UTF-8",
                    type: 'post',
                    data: JSON.stringify({
                        id: data.id
                    }),
                    success: function (data) {
                        if (data.code != 200) {
                            layer.msg(data.msg, {icon: 5});//失败的表情
                            return;
                        } else {
                            layer.msg(data.msg, {
                                icon: 6,//成功的表情
                                time: 1000 //1秒关闭（如果不配置，默认是3秒）
                            }, function () {
                                location.reload();
                            });
                        }
                    }
                });
            });
        } else if (layEvent === 'edit') { //编辑
            //do something
            layer.open({
                title: '编辑' + data.website
                , area: ['500px', 'auto']
                , skin: 'layui-layer-molv'
                , content:
                    '<div class="layui-circle" style="width: 420px;  margin-left:7px; margin-top:10px;">'
                    + '<div class="layui-group">'
                    + '<span class="layui-word-aux">修改用户名:</span>'
                    + '<input type="text" class="layui-input layui-circle" id="edit_user" value=' + data.userName + '>'
                    + '</div>'
                    + '<div class="layui-group">'
                    + '<span class="layui-word-aux">修改密码:</span>'
                    + '<input type="text" class="layui-input layui-circle" id="edit_pass" value=' + data.password + '>'
                    + '</div>'
                    + '<div class="layui-group">'
                    + '<span class="layui-word-aux">修改密保问题:</span>'
                    + '<input type="text" class="layui-input layui-circle" id="edit_question" placeholder=' + data.secretQuestion + '>'
                    + '</div>'
                    + '<div class="layui-group">'
                    + '<span class="layui-word-aux">修改密保答案:</span>'
                    + '<input type="text" class="layui-input layui-circle" id="edit_answer" placeholder=' + data.secretAnswer + '>'
                    + '</div>'
                    + '</div>'
                , btn: ['保存', '取消']
                , btnAlign: 'c'
                , yes: function (index, layero) {
                    var user = document.getElementById("edit_user").value;
                    var pass = document.getElementById("edit_pass").value;
                    var question = document.getElementById("edit_question").value;
                    var answer = document.getElementById("edit_answer").value;
                    if (user === data.userName
                        && pass === data.password
                        && question === data.secretQuestion
                        && answer === data.secretAnswer) {
                        layer.msg("未做修改")
                    } else {
                        $.ajax({
                            url: "/modifyPass",
                            contentType: "application/json;charset=UTF-8",
                            type: 'post',
                            data: JSON.stringify({
                                id: data.id,
                                userName: user,
                                secretQuestion: question,
                                password: pass,
                                secretAnswer: answer
                            }),
                            success: function (data) {
                                if (data.code !== 200) {
                                    layer.msg(data.msg, {icon: 5});//失败的表情
                                    return;
                                } else {
                                    tableInit.reload();
                                    layer.msg(data.msg, {
                                        icon: 6,//成功的表情
                                        time: 1000 //1秒关闭（如果不配置，默认是3秒）
                                    });
                                }
                            }

                        });
                    }
                }
                , cancel: function () {
                    //右上角关闭回调

                    //return false 开启该代码可禁止点击该按钮关闭
                }
            });
        }
    });

});
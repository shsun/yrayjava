function getCookie(name) {
    var cookieValue = null;
    if (document.cookie && document.cookie !== '') {
        var cookies = document.cookie.split(';');
        for (var i = 0; i < cookies.length; i++) {
            var cookie = jQuery.trim(cookies[i]);
            // Does this cookie string begin with the name we want?
            if (cookie.substring(0, name.length + 1) === (name + '=')) {
                cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                break;
            }
        }
    }
    return cookieValue;
}


$(function(){
    //广告计划页面
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#pro_start_time_value', //指定元素
            type: 'date'
        });
    });
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#pro_send_time_value', //指定元素
            type: 'date'
        });
    });


    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#start_hour_min_second', //指定元素
            type: 'time'
        });
    });


    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#end_hour_min_second', //指定元素
            type: 'time'
        });
    });

    //广告计划页面 end


    //财务管理页面
    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#finance_start_time_value', //指定元素
            type: 'date'
        });
    });

    layui.use('laydate', function () {
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#finance_end_time_value', //指定元素
            type: 'date'
        });
    });

    //财务管理页面 end



});


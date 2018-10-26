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

$(function () {

    if (window.location.pathname == '/recharge_html/') {
        // var date = new Date();
        //  var year = date.getFullYear(); // 年
        //  var month = date.getMonth() + 1; // 月
        //  var day  = date.getDate(); // 日
        //  var current_time = year + '-' + month + '-' + day;

        show_list_table(p_start_time = getDay(-7), p_end_time = getDay(0));
    }

    // js获取最近7天的时间 和当前时间
    function getDay(day) {
        var today = new Date();
        var targetday_milliseconds = today.getTime() + 1000 * 60 * 60 * 24 * day;
        today.setTime(targetday_milliseconds); //注意，这行是关键代码
        var tYear = today.getFullYear();
        var tMonth = today.getMonth();
        var tDate = today.getDate();
        tMonth = doHandleMonth(tMonth + 1);
        tDate = doHandleMonth(tDate);
        return tYear + "-" + tMonth + "-" + tDate;
    }

    function doHandleMonth(month) {
        var m = month;
        if (month.toString().length == 1) {
            m = "0" + month;
        }
        return m;
    }



    $('#btn_search_finance').click(function(){
        var start_time = $('#finance_start_time_value').val();
        var end_time = $('#finance_end_time_value').val();
        if(start_time > end_time || start_time == '' || end_time == ''){
            alert('选择时间不正确');
            return false;
        }
        show_list_table(p_start_time = start_time, p_end_time = end_time);

    });

    function show_list_table(p_start_time, p_end_time) {
        $.get('/recharge_consumption/', {'start_time': p_start_time, 'end_time': p_end_time}, function (data) {
            console.log(data);
            $('#list_table').bootstrapTable('load', data);

        });
    }



    // 公用ajax
    function Base_Ajax(p_type, p_url, p_data) {
        var csrftoken = getCookie('csrftoken');
        var result;
        $.ajax({
            cache: false,
            type: p_type,
            url: p_url,
            traditional: true,
            dataType: 'json',
            data: p_data,
            async: false,//这里选择异步为false，那么这个程序执行到这里的时候会暂停，等待
            //数据加载完成后才继续执行
            success: function (data, textStatus, jqXHR) {
                //获取HTTP状态
                // console.log(jqXHR.status);
                // console.log(data);
                result = {
                    'ajax_status': jqXHR.status,
                    'ajax_data': data
                };
            },
            error: function (xhr, textStatus,msg) {
                console.log('错误');
                console.log(xhr);
                console.log(textStatus);
                console.log(msg);
            },
            beforeSend: function (xhr, settings) {
                xhr.setRequestHeader("X-CSRFToken", csrftoken);
            }
        });

        return result
    }

    // 公用ajax end


    // 广告计划表格基础设置
    $('#list_table').bootstrapTable({
        method: 'post',
        //showExport: true,                     //是否显示导出
        //exportDataType: "all",              //basic', 'all', 'selected'. //导出所有数据 当前页面 全部 选中
        striped: 'true', //是否显示隔行变色
        cache: 'false', //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        // pagination:'true', //是否显示分页（*）
        //search: 'true',   //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        showRefresh: 'true',
        //showToggle: 'true',//是否显示详细视图和列表视图的切换按钮
        minimumCountColumns: '2', //最少允许的列数
        uniqueld: 'id',
        //clickToSelect: 'true', //设置true 将在点击行时，自动选择rediobox 和 checkbox
        //toolbar: '#toolbar',  //一个jQuery 选择器，指明自定义的toolbar 例如:#toolbar,
        //radio: 'true', //开启单选按钮
        // pageSize: 5,
        // columns: data

    });


});
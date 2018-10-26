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

    if(window.location.pathname == '/h5/'){
        show_list_table();

    }

    // 添加表格内容并保存
   /* $('#btn_add').click(function () {
        form_ad_campaing_reset();

        $('#Project_Cell_Modal').modal('show');
        $('#update_save_b').hide();
        $('#submit_save_b').show();
    });*/


    $('#submit_save_b').click(function (e) {
        e.preventDefault();

        var pro_name = $('#pro_name_value').val();
        if ($.trim(pro_name) == '') {
            alert('请填写广告计划');
            return false;
        }

        var unit_price = $('#pro_unit_price_value').val();
        if (unit_price == '' || unit_price < 0.5) {
            alert('单价最低为0.5元');
            return false;
        }


        var csrftoken = getCookie('csrftoken');

        $.ajax({
            cache: false,
            type: "POST",
            url: "/ad_campaing/",
            traditional: true,
            dataType: 'json',
            async: true,
            data: {
                'advertiser': $('#base_username_id').text(),
                'name': $('#pro_name_value').val(),
                'start_time': $('#pro_start_time_value').val(),
                'end_time': $('#pro_send_time_value').val(),
                'budget': $('#pro_budget_value').val(),
                'start_hour_min_second': $('#start_hour_min_second').val(),
                'end_hour_min_second' : $('#end_hour_min_second').val(),
                'unit_price':unit_price
            },
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                // console.log(jqXHR.status);
                if (jqXHR.status == 201) {
                    $('.page-content').fadeOut(300,function(){
                        $('.center').fadeIn(300)
                    });
                    show_list_table();
                    $('#Project_Cell_Modal').modal('hide');
                    form_ad_campaing_reset();
                    return false;
                }if (data[0]['status'] == 400) {
                    console.log(data[0]['error']['non_field_errors']);
                    alert(data[0]['error']['non_field_errors']);
                }
            },
            beforeSend: function (xhr, settings) {
                xhr.setRequestHeader("X-CSRFToken", csrftoken);
            }
        });
    });

    //清空form表单
    function form_ad_campaing_reset() {
        $('#pro_name_value').val(' ');
        $('#pro_start_time_value').val(' ');
        $('#pro_send_time_value').val(' ');
        $('#pro_budget_value').val(' ');
    };

    // 添加表格内容并保存 end
    // 
    
    //新增广告计划
    $('.add_btn').on('click',function(){
        form_ad_campaing_reset();
        $('#update_save_b').hide();
        $('#submit_save_b').show();
        if($('.page-content').is(':hidden')) {
            $('.center').fadeOut(300,function(){
                $('.page-content').fadeIn(300)
            })
            
        } else {
            $('.page-content').fadeOut(300,function(){
                $('.center').fadeIn(300)
            })
        }
    })

    $('.btn-close').on('click',function(e){
        e.preventDefault()
        $('.page-content').fadeOut(300,function(){
            $('.center').fadeIn(300)
        })
    })




    //逻辑删除表格内容(软删除)
    $(document).on("click",".logic_delete",function(){
        var number_td = $(this).parent().parent().children().eq(1).text()
        console.log(number_td);

        var url = "/ad_campaing_view/";
        var type = "POST";
        var data = {number_td:number_td};
        var result = Base_Ajax(type, url, data);
        console.log(result);
        console.log(result.ajax_status);
        if (result.ajax_status == 200) {
            show_list_table()
        }
        else{
            alert('删除失败！');
        }
    });




    // 机械删除表格内容（彻底删除）
    var $table = $('#list_table');
    $('#btn_delete').click(function () {
        //console.log(JSON.stringify($table.bootstrapTable('getSelections')));
        var table_id = $table.bootstrapTable('getSelections')[0]['id'];
        console.log(table_id);
        console.log("/ad_campaing/" + table_id + "/");
        var csrftoken = getCookie('csrftoken');
        $.ajax({
            cache: false,
            type: "DELETE",
            url: "/ad_campaing/" + table_id + "",
            traditional: true,
            dataType: 'json',
            async: true,
            success: function (data, textStatus, jqXHR) {
                //获取HTTP状态
                console.log(jqXHR.status);
                if (jqXHR.status == 204) {
                    $table.bootstrapTable('remove', {
                        field: 'id',
                        values: [table_id]
                    });
                    // TODO 删除一条数据后不应该页码也应该发生改变，此方法为偷懒方法
                    show_list_table();
                }
            },
            error: function (xhr, textStatus) {
                console.log('错误');
                console.log(xhr);
                console.log(textStatus);
            },
            beforeSend: function (xhr, settings) {
                xhr.setRequestHeader("X-CSRFToken", csrftoken);
            }
        });
    });



    //更新数据
    var table_id; //表格行id
    $(document).on("click", ".table_update", function () {
        table_id = $(this).parent().parent().children().eq(1).text();
        console.log(table_id);
        var url = "/ad_campaing/" + table_id + "/";
        var type = "GET";
        var data = '';
        var result = Base_Ajax(type, url, data);
        console.log(result);
        console.log(result.ajax_status);
        if (result.ajax_status == 200) {
            $('#submit_save_b').hide();
            $('#update_save_b').show();
            Data_Back(result.ajax_data);
        }

    });

    //原更新接口
    $('#btn_update').click(function () {
        $('#myModalLabel').text('请修改广告计划');
        var table_id = $table.bootstrapTable('getSelections')[0]['id'];
        var url = "/ad_campaing/" + table_id + "/";
        var type = "GET";
        var data = '';
        var result = Base_Ajax(type, url, data);
        console.log(result);
        console.log(result.ajax_status);
        if (result.ajax_status == 200) {
            $('#submit_save_b').hide();
            $('#update_save_b').show();
            $('#Project_Cell_Modal').modal('show');
            Data_Back(result.ajax_data);
        }

    });

    $('#update_save_b').click(function (e) {
        e.preventDefault();
        //var table_id = $table.bootstrapTable('getSelections')[0]['id'];

        var url = "/ad_campaing/" + table_id + "/";
        var type = "PUT";
        var unit_price = $('#pro_unit_price_value').val();
        if (unit_price == '' || unit_price < 0.5) {
            alert('单价最低为0.5');
            return false;
        }
        var data = {
            'advertiser': $('#base_username_id').text(),
            'name': $('#pro_name_value').val(),
            'start_time': $('#pro_start_time_value').val(),
            'end_time': $('#pro_send_time_value').val(),
            'budget': $('#pro_budget_value').val(),
            'start_hour_min_second': $('#start_hour_min_second').val(),
            'end_hour_min_second': $('#end_hour_min_second').val(),
            'unit_price':unit_price
        };
        var result = Base_Ajax(type, url, data);

        console.log(result);

        if (result['ajax_data'][0]['status'] == 400) {
            alert(result['ajax_data'][0]['error']['non_field_errors']);
            return false;
        }

        //update_list_table(table_id,result.ajax_data);
        //更新表格 TODO 待优化，应采用update_list_table 方法
        show_list_table();
        //table_operation();
        $('.page-content').fadeOut(300,function(){
            $('.center').fadeIn(300)
        })

    });

    //更新表格
    // function update_list_table(p_index, p_result_ajax_data) {
    //     $table.bootstrapTable('updateRow', {
    //         index: 1,
    //         row: p_result_ajax_data
    //     });
    //
    // };


    //数据回填
    function Data_Back(p_ajax_data) {
        $('.center').fadeOut(300,function(){
            $('.page-content').fadeIn(300)
        })
        $.each(p_ajax_data, function (commentIndex, comment) {
            if (commentIndex == 'name') {
                $('#pro_name_value').val(comment);
            }
            if (commentIndex == 'start_time') {
                $('#pro_start_time_value').val(comment);
            }
            if (commentIndex == 'end_time') {
                $('#pro_send_time_value').val(comment);
            }
            if (commentIndex == 'budget') {
                $('#pro_budget_value').val(comment);
            }
            if (commentIndex == 'start_hour_min_second') {
                $('#start_hour_min_second').val(comment);
            }
            if (commentIndex == 'end_hour_min_second') {
                $('#end_hour_min_second').val(comment);
            }
            if (commentIndex == 'unit_price') {
                $('#pro_unit_price_value').val(comment);
            }
        });
    }

    //更新数据 end

    // 搜索开始


    $('#btn_search_campaing').click(function(){
            var search_key_word =  $('#input_campaing').val();
            console.log(search_key_word);
            if (search_key_word == ''){
                return false;
            }
            var p_search_key_word = search_key_word;
            search_fun(p_search_key_word)
    });

    $("#campaing_type_select").change(function () {
        var type_select_value = $(this).find("option:selected").val();
        console.log(type_select_value);
        if (type_select_value == 'status_type') {
            return false;
        }
        var p_search_key_word = type_select_value;
        search_fun(p_search_key_word)


    });

    //搜索函数
    function search_fun(p_search_key_word) {
        var url = "/search_ad_campaing/?search=" + p_search_key_word + "";
        var type = "GET";
        var data = {};
        var result = Base_Ajax(type, url, data);

        console.log(result);
        console.log(result.ajax_status);
        console.log(result.ajax_data['results']);

        if (result.ajax_status == 200) {

            $.each(result.ajax_data['results'], function (commentIndex, comment) {
                comment['advertiser'] = comment['advertiser']['username']
            });
            var total_count = result.ajax_data['count'];
            console.log('总分页数为' + total_count);
            $('#list_table').bootstrapTable('load', result.ajax_data['results']);

            //分页工具
            var p_key_word = p_search_key_word;
            Search_Pagination(total_count, p_key_word);

            // 根据后台的值，控制表格操作栏的按钮显示
            table_operation();


        } else {
            alert('搜索失败!');
        }
    }




    // 搜索开始 end


    function show_list_table() {
        $.get('/pager1/', {}, function (data) {
            console.log(data);
            $.each(data.results, function (commentIndex, comment) {
                comment['advertiser'] = comment['advertiser']['username']
            });
            var total_count = data.count;
            console.log('总分页数为' + total_count);
            $('#list_table').bootstrapTable('load', data.results);

            //分页工具
            Pagination(total_count);

            // 根据后台的值，控制表格操作栏的按钮显示
            table_operation();

        });
    }

    // 根据后台的值，控制表格操作栏的按钮显示
    function table_operation() {
        // 操作单元格
        $("#list_table tbody tr td:nth-child(11)").html('<button type="button" class="btn btn-default btn-sm logic_delete">删除</button>&nbsp;<button type="button" class="btn btn-default btn-sm table_update">修改</button>&nbsp;<button type="button" class="btn btn-default btn-sm table_pause_or_tfz">暂停</button>')

        // 控制暂停或投放中 button的显示
        $('#list_table>tbody>tr').each(function (index, element) {
            var gg_status = $(element).children().eq(3).text();
            if (gg_status == 'tfz') {
                $(element).children().find('.table_pause_or_tfz').text('暂停');
                $(element).children().eq(3).text('投放中');
            }
            else if (gg_status == 'zt') {
                $(element).children().find('.table_pause_or_tfz').text('投放中');
                $(element).children().eq(3).text('暂停');
            }
            else if (gg_status == 'dsh') {
                $(element).children().eq(3).text('待审核');
            }
            else if (gg_status == 'ygq') {
                $(element).children().eq(3).text('已过期');
            } else if (gg_status == 'yebz') {
                $(element).children().eq(3).text('余额不足');
            } else if (gg_status == 'bgdb') {
                $(element).children().eq(3).text('曝光达标');
            }else if (gg_status == 'djdb') {
                $(element).children().eq(3).text('点击达标');
            }else if (gg_status == 'ready'){
                $(element).children().eq(3).text('未到开始时间').css('color', '#5bc0de');
            }else if (gg_status == 'ysdb'){
                $(element).children().eq(3).text('预算达标');
            }else {
                $(element).children().eq(3).text('异常！！！').css('color', 'red');
            };


        });
    }



    // 暂停 or 投放中操作
    $(document).on("click",".table_pause_or_tfz",function(){
        var number_td = $(this).parent().parent().children().eq(1).text();
        console.log(number_td);
        var status = $(this).parent().parent().children().eq(3).text();

        //获取广告状态对应的英文缩写
        status = adv_state(status);
        console.log(status);

        if (status == 'tfz' || status == 'zt') {
            var url = "/ad_campaing_view/";
            var type = "POST";
            var data = {number_td: number_td, status: status};
            var result = Base_Ajax(type, url, data);
            console.log(result);
            console.log(result.ajax_status);
            if (result.ajax_status == 200) {
                show_list_table()
            }
            else {
                alert('删除失败！');
            }
        }
        else {
            alert('投放中或暂停状态下才可使用！');
            return false;
        }
    });
    // 暂停 or 投放中操作 end


    //获取广告状态对应的英文缩写
    function adv_state(p_status){
        if (p_status == '待审核'){
            status = 'dsh'
        }else if(p_status == '投放中'){
            status = 'tfz'
        }else if(p_status == '暂停'){
            status = 'zt'
        }else if(p_status == '已过期'){
            status = 'ygq'
        }else if(p_status == '余额不足') {
            status = 'yebz'
        }else if(p_status == '曝光达标'){
            status = 'bgdb'
        }else if(p_status == '点击达标'){
            status = 'djdb'
        }else if(p_status == '未到开始时间'){
            status = 'ready'
        }else if(p_status == '预算达标') {
            status = 'ysdb'
        }else {
            status = null
        }
        return status

    }



    // 搜索 分页工具
    function Search_Pagination(p_total_count, p_key_word) {
        $("#pagination3").pagination({
            currentPage: 1,
            totalPage: Math.ceil(p_total_count / 5),
            callback: function (current) {
                console.log(current);
                $("#current3").text(current);

                $.get('/search_ad_campaing/?page=' + current + '&search=' + p_key_word + '', {}, function (data) {
                    console.log(data);
                    $.each(data['results'], function (commentIndex, comment) {
                        comment['advertiser'] = comment['advertiser']['username']
                    });
                    $('#list_table').bootstrapTable('load', data['results']);
                    // 根据后台的值，控制表格操作栏的按钮显示
                    table_operation()

                });

            }
        });

    }



    //分页工具
    function Pagination(p_total_count) {
        $("#pagination3").pagination({
            currentPage: 1,
            totalPage: Math.ceil(p_total_count / 5),
            callback: function (current) {
                console.log(current);
                $("#current3").text(current);


                $.get('/pager1/?page=' + current + '', {}, function (data) {
                    $.each(data.results, function (commentIndex, comment) {
                        comment['advertiser'] = comment['advertiser']['username']
                    });
                    $('#list_table').bootstrapTable('load', data.results);
                    // 根据后台的值，控制表格操作栏的按钮显示
                    table_operation()
                });




            }
        });

    }

    // 删除表格内容 end


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
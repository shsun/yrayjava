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
    var p_nums=[];
    var p_rates=[];
    var show_nums=[];
    var num_money=[];
    var X_data;
    function get_renderdata(num){
        $.ajax({
            url:'/advertiser_hour/',
            data:{
                day_number:num
            },
            success:function(res){
                console.log(res)
                if(X_data==undefined){
                    X_data=res[0].X_data;
                    show_nums=res[0].Y_data[0].data
                    p_nums=res[0].Y_data[1].data
                    p_rates=res[0].Y_data[2].data
                    num_money=res[0].Y_data[3].data
                    renders();
                }else{
                    X_data=res[0].X_data;
                    show_nums=res[0].Y_data[0].data
                    p_nums=res[0].Y_data[1].data
                    p_rates=res[0].Y_data[2].data
                    num_money=res[0].Y_data[3].data
                    get_datas()
                }

            }
        })
    }
    get_renderdata(0)
    function renders(){
        // console.log("1")
        render_charts(['展示数','总花费'],[
            {
                type: 'value',
                name: '展示数',
                axisLabel: {
                    formatter: '{value}'
                }
            },
            {
                type: 'value',
                name: '总花费(元)',
                axisLabel: {
                    formatter: '{value}'
                }
            }
        ], X_data,[
            {
                name:'展示数',
                type:'line',
                smooth: true,
                data:show_nums,
                // label: {
                //     normal: {
                //         show: true,
                //         position: 'top'
                //     }
                // },
            },
            {
                name:'总花费',
                type:'line',
                yAxisIndex: 1,
                smooth: true,
                data:num_money,       
                // label: {
                //     normal: {
                //         show: true,
                //         position: 'top'
                //     }
                // },
            },
        ])
    }
    // render_charts('展示数')
    function render_charts(legend,Y_show,X_show,count){
            // 基于准备好的dom，初始化echarts实例
            $(".charts").html("");
            var myChart = echarts.init(document.getElementById('charts'));
            // 绘制图表
            var option = {
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data:legend
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: [
                    {
                        type: 'category',
                        data:X_show,
                        axisPointer: {
                            type: 'shadow'
                        },
                        axisLabel: {
                            interval:0,
                            rotate:40
                         }
                    }
                ],
                yAxis: Y_show,
                series: count,
            };
            
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option,true);
    }
    function render_click(parmas,name1,name2){
        if(parmas==1){
            var format='{value}';
            if($(".layui-btn-normal").eq(0).html()=="点击率"){
                format='{value}'+"%"
            }
           render_charts([$(".layui-btn-normal").eq(0).html()],
           {
               type: 'value',
               name: $(".layui-btn-normal").eq(0).html(),
               axisLabel: {
                   formatter: format
               }
           }, X_data,
           {
               name:$(".layui-btn-normal").eq(0).html(),
               type:'line',
               smooth: true,
               data:name1
           })
        }else if(parmas==2){
            var format1='{value}';
            var format2='{value}';
            if($(".layui-btn-normal").eq(0).html()=="点击率"){
                format1='{value}'+"%"
            }else if($(".layui-btn-normal").eq(1).html()=="点击率"){
                format2='{value}'+"%"
            }
            render_charts([$(".layui-btn-normal").eq(0).html(),$(".layui-btn-normal").eq(1).html()],[
                {
                    type: 'value',
                    name: $(".layui-btn-normal").eq(0).html(),
                    axisLabel: {
                        formatter: format1
                    }
                },
                {
                    type: 'value',
                    name: $(".layui-btn-normal").eq(1).html(),
                    axisLabel: {
                        formatter: format2
                    }
                }
            ], X_data,[
                {
                    name:$(".layui-btn-normal").eq(0).html(),
                    type:'line',
                    smooth: true,
                    data:name1
                },
                {
                    name:$(".layui-btn-normal").eq(1).html(),
                    type:'line',
                    yAxisIndex: 1,
                    smooth: true,
                    data:name2
                },
            ])
        }
    }
    $(".btn_es").on("click","button",function(){
        if($(this).hasClass("layui-btn-primary")){
            if($(this).parent().find(".layui-btn-normal").length>=2){
                $(this).parent().find(".layui-btn-normal").removeClass("layui-btn-normal").addClass("layui-btn-primary")  
                // $(this).removeClass("layui-btn-normal").addClass("layui-btn-primary")   
                $(this).removeClass("layui-btn-primary").addClass("layui-btn-normal")          
            }else{
                $(this).removeClass("layui-btn-primary").addClass("layui-btn-normal")
            }
        }else{
            if($(this).parent().find(".layui-btn-normal").length>=2){
                $(this).removeClass("layui-btn-normal").addClass("layui-btn-primary")   
                // $(this).parent().find(".layui-btn-normal").removeClass("layui-btn-normal").addClass("layui-btn-primary")  
                // $(this).removeClass("layui-btn-primary").addClass("layui-btn-normal")          
            }else if($(this).parent().find(".layui-btn-normal").length==1){
                // $(this).removeClass("layui-btn-primary").addClass("layui-btn-normal")          
            }else{
               $(this).removeClass("layui-btn-normal").addClass("layui-btn-primary")            
            }
        }
        get_datas()
    })
    function get_datas(){
        if($(".layui-btn-normal").length==1){
            var names_es;
            if($(".layui-btn-normal").hasClass("show")){
                names_es=show_nums;
            }else if($(".layui-btn-normal").hasClass("p_num")){
                names_es=p_nums;
            }else if($(".layui-btn-normal").hasClass("p_rate")){
                names_es=p_rates;
            }else if($(".layui-btn-normal").hasClass("p_money")){
                names_es=num_money;
            }
            render_click(1,names_es)
        }else if($(".layui-btn-normal").length==2){
            var names_1;
            var names_2;
            if($(".layui-btn-normal").eq(0).hasClass("show")){
                names_1=show_nums;
            }else if($(".layui-btn-normal").eq(0).hasClass("p_num")){
                names_1=p_nums;
            }else if($(".layui-btn-normal").eq(0).hasClass("p_rate")){
                names_1=p_rates;
            }else if($(".layui-btn-normal").eq(0).hasClass("p_money")){
                names_1=num_money;
            }
            if($(".layui-btn-normal").eq(1).hasClass("show")){
                names_2=show_nums;
            }else if($(".layui-btn-normal").eq(1).hasClass("p_num")){
                names_2=p_nums;
            }else if($(".layui-btn-normal").eq(1).hasClass("p_rate")){
                names_2=p_rates;
            }else if($(".layui-btn-normal").eq(1).hasClass("p_money")){
                names_2=num_money;
            }
            render_click(2,names_1,names_2)
        }else{
            // render_charts([],[], ['1月','2月','3月','4月','5月'],[])
        }
    }
    if (window.location.pathname == '/home/') {
        var day_number = 0;
        show_list_table(day_number)
    }


    //选择时间范围
    $("#home_time_select").change(function () {
        var day_number = $(this).find("option:selected").val();
        console.log(day_number);
        show_list_table(day_number)
        get_renderdata(day_number)
    });




    //更新数据 end
    function show_list_table(p_day_number) {
        $.get('/account_amount/?day_number='+p_day_number+'', {}, function (data) {
            console.log(data);
            // var total_count = data.count;
            //console.log('总分页数为' + total_count);

            $('#list_table').bootstrapTable('load', data[0]);
            //分页工具
            //Pagination(total_count);


        });
    }

    /*$('#list_table2').bootstrapTable({
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
            toolbar: '#toolbar2',  //一个jQuery 选择器，指明自定义的toolbar 例如:#toolbar,
            //radio: 'true', //开启单选按钮
            // pageSize: 5,
            // columns: data

        });*/


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
        toolbar: '#toolbar',  //一个jQuery 选择器，指明自定义的toolbar 例如:#toolbar,
        //radio: 'true', //开启单选按钮
        // pageSize: 5,
        // columns: data

    }); 
});


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
    var csrftoken = getCookie('csrftoken');

    if (window.location.pathname == '/adver/') {
        show_list_table()

    };

    //增加保存表格内容
   /* $('#btn_add_file').click(function () {
        form_ad_campaing_reset();

        $('#Adver_Cell_Modal').modal('show');
        $('#submit_save_b').show();
        $('#update_save_b').hide();

    });*/


   var formData = new FormData();
   var form_img_list = [];
   var remove_img_list = [];

    //改变图片
    var count = 0;
    var aa = 0;
    $("#adv_photo_value2").on('change',function(){
        var ind = $('#ad_imp_type').val();
        var objUrl = getObjectURL(this.files[0]);
        console.log(this.files[0])

        for (var i = 0; i < $('#adv_photo_value2')[0].files.length; i++) {
            var objUrl = getObjectURL($('#adv_photo_value2')[0].files[i]);
            var num = Math.ceil(Math.random()*100);
            
            var imgalt = $('#adv_photo_value2')[0].files[i]['name']
            var img_name = $('#adv_photo_value2')[0].files[i]['img'] = num+imgalt;
            

            form_img_list.push($('#adv_photo_value2')[0].files[i]);


            if(ind == 1 || ind == 2) {
              if (objUrl) {
                  $('.img_con').eq(0).find('img').attr('src',objUrl)
                  $('.img_con').eq(0).find('img').attr('alt',img_name);
                  $('.img_par').fadeOut(100);
                  $('.img_con').eq(0).fadeIn(100,function(){
                    $(this).css('display','inline-block');
                  })
                }
            } else {
              if(objUrl) {
                if(form_img_list.length <=3) {
                    
                    $('.img_con').eq(count).fadeIn(100)
                    $('.img_con').eq(count).find('img').attr('src',objUrl);
                    $('.img_con').eq(count).find('img').attr('alt',img_name);
                    count = form_img_list.length
                    console.log(count)
                    aa++
                    console.log(aa,3333)
                    if(aa >= 3) {
                      $('.img_par').fadeOut(100)
                    }
                }
              }
            }
        }

    });
     

    $('.img_con span').each(function(index,val){

       $(this).on('click',function(){

           var img_name = $(this).prev();
           // 目前获取到img的属性
           //console.log(img_name);

           var img_alt = $(img_name[0]).attr('alt');

           $(form_img_list).each(function(ind,ele){
                if (img_alt == ele['img']){
                    form_img_list.splice(ind,1); 
                    $("#adv_photo_value2").val('')
                    return false;
                }
           });

          $('.img_con').eq(index).fadeOut(100);
          $('.img_par').fadeIn(100);
          remove_img_list[index] = $(img_name[0]).attr('src');
          var ind = $('#ad_imp_type').val();
          if(ind >= 3) {
            count = index;
            aa--
          }
       });

    });
    
    $('.imgBtn').on('click',function(e){
        e.preventDefault()
    })

    //点击图片数量
    $('.btnCheck').on('click',function(e){
        e.preventDefault();
        aa = 0
        count = 0
        form_img_list = [];
        remove_img_list = [];
        var val = $(this).attr('data-val');
        var ind = $(this).attr('data-ind');
        $('#ad_imp_type').val(val);
        $(this).addClass('activeBtn').siblings('').removeClass('activeBtn')

        $('.img_con').fadeOut(100,function(){
            $('.img_par').fadeIn(100)
        });
        $("#adv_photo_value2").val('');
        $('.page-right-con p').eq(ind).addClass('page-show').siblings('').removeClass('page-show')


    });

    //建立一個可存取到file的url
    function getObjectURL(file) {
        var url = null ;
        if (window.createObjectURL!=undefined)
        { // basic
            url = window.createObjectURL(file) ;
        }
        else if (window.URL!=undefined)
        {
            // mozilla(firefox)
            url = window.URL.createObjectURL(file) ;
        }
        else if (window.webkitURL!=undefined) {
            // webkit or chrome
            url = window.webkitURL.createObjectURL(file) ;
        }
        return url ;
    }




    //模态框select下拉框 选择广告计划将对应的广告计划id写入对应input
    $("#id_select_ad_campaing").change(function () {
        var checkVal = $(this).find("option:selected").val();
        console.log(checkVal);
        $('#adv_plan_value').val(checkVal)
    });

    $('#submit_save_b').click(function (e) {
        //formData 增加值
        e.preventDefault()

        var checkText = $("#id_select_ad_campaing").find("option:selected").text();
        if (checkText == '请选择广告计划：' ){
            alert('请选择广告计划');
            return false;
        } else if($('#adv_des_value').val() == ''){
            alert('请填写广告描述');
            return false;
        } else if($('#adv_title_value').val() == ''){
            alert('请填写广告来源');
            return false;
        } else if($('#adv_landing_value').val() == ''){
            alert('请填写活动链接');
            return false;
        } else if(form_img_list.length == 0){
            alert('请选择图片');
            return false;
        }

        console.log(form_img_list,33333);
        for (var i = 0; i < form_img_list.length; i++) {
            formData.append('image_file', form_img_list[i]);
        }

        formData.append('advertiser', $('#base_username_id').text());
        formData.append('campaign_name', $('#adv_name_value').val());
        formData.append('campaign_id', $('#adv_plan_value').val());
        formData.append('data', $('#adv_des_value').val());
        formData.append('title', $('#adv_title_value').val());
        formData.append('ad_imp_type', $('#ad_imp_type').val());
        formData.append('landing_url', $('#adv_landing_value').val());
        formData.append('enable', 1);
        formData.append('remove_img_list', remove_img_list);

        console.log(formData);

        var csrftoken = getCookie('csrftoken');
        $.ajax({
            cache: false,
            type: 'POST',
            data: formData,
            url: '/advertisement_list/',
            traditional: true, //为必须内容 　　
            //dataType:'json', //data为json时必须
            processData: false, //为必须内容
            contentType: false, //为必须内容
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                if (jqXHR.status == 201) {
                    $('.page-content').fadeOut(300,function(){
                        $('.center').fadeIn(300)
                    })
                    show_list_table();
                    $('#Adver_Cell_Modal').modal('hide');
                    form_ad_campaing_reset();
                } else {
                    alert(data[0].black_status)
                    form_ad_campaing_reset()
                }

            },
            beforeSend: function (xhr, settings) {
                xhr.setRequestHeader("X-CSRFToken", csrftoken);
            },
            error:function(data){
                console.log(222)
            }
        });
    });

    //清空form表单
    function form_ad_campaing_reset() {
        $('#adv_des_value').val('');
        $('#adv_title_value').val('');
        $('#adv_landing_value').val('');
        form_img_list = []
        remove_img_list = []
        formData = new FormData()
        $("#adv_photo_value").val('')

        /*var file = $("#adv_photo_value");
        file.after(file.clone().val(""));
        file.remove();*/

        $("#id_select_ad_campaing ").get(0).selectedIndex = 0;
    };

    //增加保存表格内容 end
    //
    //新增广告单元
    $('.add_btn').on('click',function(){
       form_ad_campaing_reset();
       $('#update_save_b').hide();
       $('#submit_save_b').show();
      $('.img_par').fadeIn(100,function(){
        $(this).find('input').val('')
      })
      $('.img_con').fadeOut(100)

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

        var url = "/adver/";
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
        $.ajax({
            cache: false,
            type: "DELETE",
            url: "/advertisement_list_pager/" + table_id + "",
            traditional: true,
            //dataType:'json',
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
                console.log(textStatus)
            },
            beforeSend: function (xhr, settings) {
                xhr.setRequestHeader("X-CSRFToken", csrftoken);
            }
        });

    });


    //更新数据
    var table_id; //表格行id
    $(document).on("click", ".table_update", function () {
        table_id = $(this).parent().parent().children().eq(1).text()
        console.log(table_id);
        var url = "/advertisement_list_pager/" + table_id + "/";
        var type = "GET";
        var data = '';
        var result = Base_Ajax(type, url, data);
        console.log(result);
        console.log(result.ajax_status);
        if (result.ajax_status == 200) {
            $('#submit_save_b').hide();
            $('#update_save_b').show();
            $('#Adver_Cell_Modal').modal('show');
            Data_Back(result.ajax_data);
            aa = 0
        }

    });





    // 原更新数据接口
    $('#btn_update').click(function () {
        var table_id = $table.bootstrapTable('getSelections')[0]['id'];
        var url = "/advertisement_list_pager/" + table_id + "/";
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

    // 回填数据——更新数据
    $('#update_save_b').click(function(e){
        e.preventDefault();
        //var table_id = $(this).parent().parent().children().eq(1).text();
        
        console.log(form_img_list)
        var formData = new FormData()
        for (var i = 0; i < form_img_list.length; i++) {
            formData.append('image_file', form_img_list[i]);
        }
        
        /*console.log(remove_img_list)
        var arr = []
        for(var j in remove_img_list) {
            if(remove_img_list[j]) {
                var b = remove_img_list[j].slice(1);
                arr.push[b]
            }
           
        }*/

        formData.append('advertiser', $('#base_username_id').text());
        formData.append('campaign_name', $('#adv_name_value').val());
        formData.append('campaign_id', $('#adv_plan_value').val());
        formData.append('data', $('#adv_des_value').val());
        formData.append('title', $('#adv_title_value').val());
        formData.append('ad_imp_type', $('#ad_imp_type').val());
        formData.append('landing_url', $('#adv_landing_value').val());
        formData.append('enable', 1);
        formData.append('remove_img_list', remove_img_list);
        console.log(remove_img_list,2222)


        var url = "/advertisement_list_pager/" + table_id + "/";
        $.ajax({
            cache: false,
            type: 'PUT',
            data: formData,
            url: url,
            traditional:true, //为必须内容 　　
            //dataType:'json',
            processData: false, //为必须内容
            contentType: false, //为必须内容
            success: function(data,textStatus, jqXHR) {
                console.log(jqXHR);
                if(jqXHR.status == 200) {
                    show_list_table();
                    $('.page-content').fadeOut(300,function(){
                        $('.center').fadeIn(300)
                    })
                }

            },
            beforeSend: function(xhr, settings) {
                xhr.setRequestHeader("X-CSRFToken", csrftoken);
            },
            error:function(data){
                console.log(data)
            }
        });

    });


    //数据回填
    function Data_Back(p_ajax_data) {
        $('.center').fadeOut(300,function(){
            $('.page-content').fadeIn(300)
        })

        /*console.log(p_ajax_data);
        console.log(p_ajax_data['id']);
        console.log(p_ajax_data['campaign_id']['name']);*/

        $.each(p_ajax_data, function (commentIndex, comment) {
            console.log(commentIndex,comment);

            if (commentIndex == 'campaign_id'){

                // 返回广告计划所对应的外键(int),后循环比较现有select option的value与外键相等。 并得出是第几位设置对应的值
                // comment 为外键(int)
                var select_index = data_select(comment);
                $("#id_select_ad_campaing ").get(0).selectedIndex=select_index;
                $('#adv_plan_value').val(comment);

            }

            if (commentIndex == 'data') {
                $('#adv_des_value').val(comment);
            }
            if (commentIndex == 'title') {
                $('#adv_title_value').val(comment);
            }
            if (commentIndex == 'ad_imp_type') {
                if(comment == 1) {
                    $('.btnCheck').eq(0).addClass('activeBtn').siblings('').removeClass('activeBtn');
                    $('#ad_imp_type').val(1)
                } else if(comment == 2) {
                    $('.btnCheck').eq(1).addClass('activeBtn').siblings('').removeClass('activeBtn');
                    $('#ad_imp_type').val(2)
                } else {
                    $('.btnCheck').eq(2).addClass('activeBtn').siblings('').removeClass('activeBtn');
                    $('#ad_imp_type').val(5)
                }
                if(comment == 1 || comment == 2) {
                    if(comment == 1) {
                       $('.page-right-con p').eq(0).addClass('page-show').siblings('').removeClass('page-show')
                    } else {
                       $('.page-right-con p').eq(1).addClass('page-show').siblings('').removeClass('page-show')
                    }
                    var num = Math.ceil(Math.random()*100);

                    var altImg = num+p_ajax_data.img_url

                    aa = 1


                    $('.img_par').fadeOut(100)
                    $('.img_con').fadeOut(100)
                    $('.img_con').eq(0).fadeIn(100,function(){
                        $(this).css('display','inline-block')
                        $('.img_con').eq(0).find('img').attr('alt',altImg)
                    })
                    $('.img_con').eq(0).find('img').attr('src',p_ajax_data.img_url)
                    $('.img_par').val(p_ajax_data.img_url)
                } else {
                    $('.page-right-con p').eq(2).addClass('page-show').siblings('').removeClass('page-show')
                    $('.img_par').fadeOut(100)
                    var arr = [p_ajax_data.img_url_2,p_ajax_data.img_url_3,p_ajax_data.img_url_4];
                    console.log(arr)
                    for(var i in arr) {
                       $('.img_con').eq(i).find('img').attr('src',arr[i])
                       var num = Math.ceil(Math.random()*100);
                       var altImg = num+arr[i]
                       $('.img_con').eq(i).find('img').attr('alt',altImg)
                       aa++
                    }

                }
        
            }

            if (commentIndex == 'landing_url') {
                $('#adv_landing_value').val(comment);
            }
        });

        // 循环select 下拉框为第几个
        var select_numbser;

        // 回填select
        function data_select(p_comment){
            $("#id_select_ad_campaing option").each(function (commentIndex, comment_select) {
                //console.log(commentIndex);
                var txt = $(comment_select).text(); //获取单个text
                var val = $(comment_select).val(); //获取单个value
                // console.log(txt);
                // console.log(val);
                if (p_comment == val) {
                    select_numbser = commentIndex;
                }
            });
            return select_numbser;
        };
    }


    // 搜索开始
    $('#btn_search_adver').click(function(){
            var search_key_word =  $('#input_adver').val();
            console.log(search_key_word);
            if (search_key_word == '') {
                return false;
            }
            var p_search_key_word = search_key_word;
            search_fun(p_search_key_word)
    });

    $("#adver_type_select").change(function () {
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
        var url = "/search_ad_advertisement/?search=" + p_search_key_word + "";
        var type = "GET";
        var data = {};
        var result = Base_Ajax(type, url, data);

        console.log(result);
        console.log(result.ajax_status);
        console.log(result.ajax_data['results']);

        if (result.ajax_status == 200) {

            $.each(result.ajax_data['results'], function (commentIndex, comment) {
                comment['campaign_id'] = comment['campaign_id']['name']
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
        $.get('/advertisement_list_pager/', {}, function (data) {
            console.log(data,333);

            $.each(data.results, function (commentIndex, comment) {
                comment['advertiser'] = comment['advertiser']['username'];
                comment['campaign_id'] = comment['campaign_id']['name'];
                if (comment['ad_imp_type'] == 5) {
                    var arr = comment['img_url_2']+'</p><p>'+comment['img_url_3']+'<br>'+comment['img_url_4']
                    comment['img_url'] = arr
                }
            });
            var total_count = data.count;
            console.log('总分页数为'+total_count);
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
        $("#list_table tbody tr td:nth-child(9)").html('<button type="button" class="btn btn-default btn-sm logic_delete">删除</button>&nbsp;<button type="button" class="btn btn-default btn-sm table_update">修改</button>');
        var list_table_tr_7 = $("#list_table tbody tr td:nth-child(7)");
            list_table_tr_7.css('color','#3b72af').addClass('list_table_tr_7');
        var list_table_tr_8 = $("#list_table tbody tr td:nth-child(8)");
            list_table_tr_8.css('color','#3b72af').addClass('list_table_tr_8');

        /*$(document).on("click", '.list_table_tr_7', function () {
            var open_url = $(this).html();
            var arr = open_url.split('<p></p>');
            var str = arr.join('').replace(/<p>/g,'<br>');
            var brr = str.replace(/<\/p>/g,'')
            var arr2 = brr.split('<br>')
            
            var html = ''
            if(arr2.length >=1){
               for(var i in arr2){
                 html+='<p>'+arr2[i]+'</p>'
               }
               $(this).html(html)
               $(this).find('p').each(function(ind,val){
                  $(this).on('click',function(){
                    window.open(arr2[ind])
                  })
               })
            } else {
                window.open(arr2[0])
            }
            
        });*/
        $('.list_table_tr_7').each(function(ind,val){
            var open_url = $('.list_table_tr_7').eq(ind).html();
            var arr = open_url.split('<p></p>');
            var str = arr.join('').replace(/<p>/g,'<br>');
            var brr = str.replace(/<\/p>/g,'')
            var arr2 = brr.split('<br>')
            var html = ''
            if(arr2.length >1){
               for(var i in arr2){
                 html+='<img src='+arr2[i]+' class=img_more>'
               }
               $('.list_table_tr_7').eq(ind).html(html)
            } else {
               $('.list_table_tr_7').eq(ind).html('<img src='+arr2[0]+' class=img_one>')
            }

            $('.list_table_tr_7').eq(ind).find('img').on('click',function(){
                window.open($(this).attr('src'))
            })
        })



        $(document).on("click", '.list_table_tr_8', function () {
            var open_url = $(this).text();
            console.log(open_url);
            window.open(open_url);
        });


        // 控制暂停或投放中 button的显示
        $('#list_table>tbody>tr').each(function (index, element) {
            var gg_status = $(element).children().eq(3).text();
            if (gg_status == 'tfz') {
                $(element).children().eq(3).text('投放中');
            }
            else if (gg_status == 'zt') {
                $(element).children().eq(3).text('暂停');
            }
            else if (gg_status == 'dsh') {
                $(element).children().eq(3).text('待审核');
            }
            else if (gg_status == 'ygq') {
                $(element).children().eq(3).text('已过期');
            } else if (gg_status == 'yebz') {
                $(element).children().eq(3).text('余额不足');
            }else if (gg_status == 'ready'){
                $(element).children().eq(3).text('未到开始时间').css('color', '#5bc0de');
            }else if (gg_status == 'bgdb'){
                $(element).children().eq(3).text('曝光达标');
            }else if (gg_status == 'djdb'){
                $(element).children().eq(3).text('点击达标');
            }else if (gg_status == 'ysdb'){
                $(element).children().eq(3).text('预算达标');
            }else {
                $(element).children().eq(3).text('异常！！！').css('color', 'red');
            };

        });

    };


    // 搜索 分页工具
    function Search_Pagination(p_total_count, p_key_word) {
        $("#pagination3").pagination({
            currentPage: 1,
            totalPage: Math.ceil(p_total_count / 5),
            callback: function (current) {
                console.log(current);
                $("#current3").text(current);

                $.get('/search_ad_advertisement/?page=' + current + '&search=' + p_key_word + '', {}, function (data) {
                    console.log(data);
                    $.each(data['results'], function (commentIndex, comment) {
                        comment['campaign_id'] = comment['campaign_id']['name']
                        if (comment['ad_imp_type'] == 5) {
                            var arr = comment['img_url_2']+'<br>'+comment['img_url_3']+'<br>'+comment['img_url_4']
                            comment['img_url'] = arr
                        }
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
                $("#current3").text(current);

                $.get('/advertisement_list_pager/?page=' + current + '', {}, function (data) {
                    $.each(data.results, function (commentIndex, comment) {

                        comment['advertiser'] = comment['advertiser']['username'];
                        comment['campaign_id'] = comment['campaign_id']['name'];
                        if (comment['ad_imp_type'] == 5) {
                            var arr = comment['img_url_2']+'<br>'+comment['img_url_3']+'<br>'+comment['img_url_4']
                            comment['img_url'] = arr
                        }
                    });
                    $('#list_table').bootstrapTable('load', data.results);

                    // 根据后台的值，控制表格操作栏的按钮显示
                    table_operation();
                })
            }
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
            //dataType: 'json',
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
            error: function (xhr, textStatus) {
                console.log('错误');
                console.log(xhr);
                console.log(textStatus)
            },
            beforeSend: function (xhr, settings) {
                xhr.setRequestHeader("X-CSRFToken", csrftoken);
            }
        });

        return result
    }
    // 公用ajax end


    // 项目设置列表开始
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
        // radio: 'true', //开启单选按钮
        // pageSize: 5,
        // columns: data
    });
});





{% extends 'base.html' %}

{% block title %}广告组{% endblock %}

{% load staticfiles %}
{{ username }}



{% block custom_js %}
    <script src="{% static 'project_js/black5.js' %}"></script>
{% endblock %}

{% block custom_css %}


{% endblock %}


{% block navbar %}
<style>
    .pull-left li:nth-child(2):after{
        content:'';
        display: block;
        width:100%;
        height:3px;
        background: #F16E54;
        position: absolute;
        left:0;
        bottom:-20px;
        z-index:9999;
    }
    .pull-left{
        height:100%;
    }
    .pull-left li{
        display: inline-block;
        position: relative;
    }
    .leftside-navigation{
        background: #fff;
    }
    #main-content{
        margin-left:147px;
        height:100%;
        background: #F3F4F6;
        padding-top:40px;
    }
    .wrapper{
        margin-top:0;
    }
    #active{
        height:60px;
        font-family: SourceHanSansCN-Medium;
        font-size: 16px;
        background: #F3F4F6;
        color: #F16E54;
    }
    .span_json_value{
        margin-left:15px;
    }
    #noActive{
        color: #36414F;
        background: #fff;
        height:60px;
        font-family: SourceHanSansCN-Medium;
        font-size: 16px;
    }
    .center{
        width:100%;
        height:100%;
        background: #fff;
        padding:24px 15px;
    }
    #btn_add{
        width:106px;
        height:30px;
        background: #F16E54;
        outline: none;
        font-family: SourceHanSansCN-Medium;
        font-size: 13px;
    }
    .glyphicon{
        top:-1px;
    }
    .center-top{
        width:100%;
        border-bottom:1px solid #F3F3F3;
        padding-bottom:24px;
        margin-bottom:24px;
    }
    select{
        width:154px;
        height:28px;
        border: 1px solid #E5E5E5;
        border-radius: 4px;
        margin-right:38px;

    }
    #input_campaing{
        width:154px;
        height:28px;
        border: 1px solid #E5E5E5;
        border-radius: 4px;
        padding:2px 5px;
        margin-right:42px;
    }
    #btn_search_campaing{
        width:72px;
        height:28px;
        background: #F16E54;
        border-radius: 3px;
        line-height:10px;
        border:1px solid #F16E54;
        outline: none;
    }
    .th-inner {
        text-align:center;
    }
    .page{
        width:100%;
        padding:15px;
    }
    .page-content{
        width:100%;
        height:100%;
        background: #fff;
        padding:14px 15px;
        margin-top:30px;
        padding-bottom:177px;
        display: none;
    }
    .page-title{
        margin:0 0px 32px 160px;
        font-family: SourceHanSansCN-Medium;
        font-size: 18px;
        color: #36414F;
    }
    #project_file_upload div{
        width:500px;
        margin-bottom:24px;
    }
    #project_file_upload div span{
        display: inline-block;
        width:113px;
        text-align:right;
        margin-left:36px;
        font-family: SourceHanSansCN-Regular;
        font-size: 13px;
        color: #36414F;
        margin-right:12px;
    }
    .page-input{
        width:320px;
        height:40px;
        border: 1px solid #E6E8EA;
        padding:2px 5px;
    }
    .page-btn{
        margin-left:160px;
        padding-top:30px;
        font-family: SourceHanSansCN-Medium;
        font-size: 16px;

    }
    #submit_save_b,#update_save_b{
        width:155px;
        height:36px;
        background: #F16E54;
        border-radius: 4px;
        color: #FFFFFF;
        outline: none;
        border:none;
        margin-right:20px;
    }
    .btn-close{
        width:67px;
        height:36px;
        border:none;
        border: 1px solid #DFDCDC;
        border-radius: 4px;
        color: #9FA3A8;
        outline: none;
        background: #fff;
    }
    tbody td{
        color: #36414F !important;
    }
    thead th .th-inner,thead th,tbody{
        text-align:center;
        color: #57585D !important;
    }
</style>
    <ul style="margin-left: 10px;" class="pull-left">

        <li>
            <a href="/home/">
                <span style="margin:0 20px;font-size:20px;line-height:40px;color:#fff;">首页</span>
             </a>
        </li>


        <li>
            <a href="/h5/">
                <span style="margin:0 20px;font-size:20px;line-height:40px;color:#fff;">推广管理</span>
             </a>
        </li>


        <li>
            <a href="/recharge_html/">
                <span style="margin:0 20px;font-size:20px;line-height:35px;color:#fff;">财务管理</span>
             </a>
        </li>

    </ul>
{% endblock %}

{% block sidebar %}

    <aside>
        <div id="sidebar" class="nav-collapse" >
            <!-- sidebar menu start-->
            <div class="leftside-navigation">
                <ul class="sidebar-menu" id="nav-accordion" style="padding-top:100px">
                    <li>
                        <a id="active" href="/h5/">
                            <span class = 'span_json_value'>广告计划</span>
                        </a>

                    </li>
                    <li>
                        <a href="/adver/" id="noActive">
                            <span class = 'span_json_value'>广告单元</span>
                        </a>

                    </li>
                </ul>
            </div>
            <!-- sidebar menu end-->
        </div>
    </aside>
{% endblock %}




{% block content %}

    <section id="main-content">
        <span id="porject_btn_id">111</span>

        <!--动态增加项目模态框-->

        <!-- 二级页面 -->
        <div class="page">
            <div class="page-content">
                <div class="center-top" style="padding:15px 0 20px 0">
                    <span style="font-family: SourceHanSansCN-Regular;font-size: 12px;color: #B0B2B9;">广告计划</span>
                    <span style="font-family: SourceHanSansCN-Regular;font-size: 12px;color: #36414F;margin:0 5px">></span>
                    <span style="font-family: SourceHanSansCN-Regular;font-size: 12px;color: #36414F;">新增广告计划</span>
                </div>
                <p class="page-title">新增广告计划</p>
                <form id="project_file_upload" enctype="multipart/form-data">

                    <!-- 广告名称 -->
                    <div class="input-group">
                        <span class="page-left">广告计划名称：</span>
                        <input type="text" name="name" id="pro_name_value" class="page-input" placeholder="广告计划"
                               style="height:58px;">
                    </div>

                    <!-- 广告状态 -->
                    <!--<div  class="input-group">
                         <span class="page-left">广告状态：</span>
                         <input type="text" name="status" id="pro_status_value" class="page-input" placeholder="广告状态">
                    </div>-->

                    <!-- 开始时间 -->
                    <div class="input-group">
                        <span class="page-left">开始日期：</span>
                        <input type="text" name="start_time" id="pro_start_time_value" class="page-input"
                               placeholder="开始时间">
                    </div>

                    <!-- 结束时间 -->
                    <div class="input-group">
                        <span class="page-left">结束日期：</span>
                        <input type="text" name="end_time" id="pro_send_time_value" class="page-input"
                               placeholder="结束时间">
                    </div>

                    <div class="input-group">
                        <span class="page-left">开始时间：</span>
                        <input type="text" name="start_hour_min_second" id="start_hour_min_second" class="page-input"
                               placeholder="开始时间">
                    </div>

                    <div class="input-group">
                        <span class="page-left">结束时间：</span>
                        <input type="text" name="end_hour_min_second" id="end_hour_min_second" class="page-input"
                               placeholder="结束时间">
                    </div>



                    <div class="input-group">
                        <span class="page-left">计划预算(元/天)：</span>
                        <input type="text" name="budget" id="pro_budget_value" class="page-input" placeholder="计划预算">
                    </div>

                    <div class="input-group">
                        <span class="page-left">单价(元)：</span>
                        <input type="text" name="unit_price" id="pro_unit_price_value" class="page-input" placeholder="单价">
                    </div>


                    <!-- 最大曝光数 -->
                    <!--
                    <div  class="input-group input-group-sm">
                        <span class="input-group-addon">最大曝光数</span>
                        <input type="text" name="max_impression" id="pro_max_impression_value" class="form-control" placeholder="最大曝光数">
                    </div>
                    -->

                    <!-- 最大点击数 -->
                    <!--
                    <div  class="input-group input-group-sm">
                        <span class="input-group-addon">最大点击数</span>
                        <input type="text" name="max_click" id="pro_max_click_value" class="form-control" placeholder="最大点击数">
                    </div>
                    -->

                    <!-- 赠送曝光数 -->
                    <!--
                    <div  class="input-group input-group-sm">
                        <span class="input-group-addon">赠送曝光数</span>
                        <input type="text" name="free_max_impression" id="pro_free_max_click_value" class="form-control" placeholder="赠送曝光数">
                    </div>
                    -->

                    <!-- 赠送点击数 -->
                    <!--
                    <div  class="input-group input-group-sm">
                        <span class="input-group-addon">赠送点击数</span>
                        <input type="text" name="free_max_click" id="pro_free_max_click_value" class="form-control" placeholder="赠送点击数">
                    </div>
                    -->
                    <div class="page-btn">

                        <button type="button" id="submit_save_b">
                            保存
                        </button>
                        <button id="update_save_b">
                            保存
                        </button>
                        <button class="btn-close">返回</button>
                    </div>
                    {% csrf_token %}
                </form>
            </div><!-- /.modal -->


        </div>

        <!--动态增加项目模态框 end  -->

        <section id="main-wrapper" class="wrapper">
            <div class="center">

                <div class="center-top">

                    <button id="btn_add" type="button" class="btn btn-default add_btn">
                        <span class="glyphicon" aria-hidden="true" style="color:#fff">新增广告计划</span>
                    </button>
                </div>
                <span style="font-family: SourceHanSansCN-Regular;font-size: 14px;color: #9B9A9B;">状态：</span>

                <select id="campaing_type_select">
                    <option value="status_type">请选择状态类型</option>
                    <option value="dsh">待审核</option>
                    <option value="tfz">投放中</option>
                    <option value="zt">暂停</option>
                    <option value="ygq">已过期</option>
                    <option value="yebz">余额不足</option>
                    <option value="bgdb">曝光达标</option>
                    <option value="djdb">点击达标</option>
                    <option value="ready">未到开始时间</option>
                    <option value="ysdb">预算达标</option>
                </select>

                <span>
                    <input id="input_campaing" type="text">
                    <button id="btn_search_campaing" type="button" class="btn btn-primary">搜索</button>
                </span>


                <table id="list_table" style="text-align:center;">
                    <thead style="text-align:center;">
                    <tr>
                        {#                    <th data-field="state" data-checkbox="true"></th>#}
                        <th data-field="state" data-radio="true"></th>

                        <th data-field="id">广告计划ID</th>

                        <th data-field="name">广告计划</th>
                        <th data-field="status">广告状态</th>
                        <th data-field="start_time">开始日期</th>
                        <th data-field="end_time">结束日期</th>
                        <th data-field="start_hour_min_second">开始时间</th>
                        <th data-field="end_hour_min_second">结束时间</th>
                        <th data-field="budget">预算</th>
                        <th data-field="unit_price">单价(元)</th>
                        <!--
                        <th data-field="max_impression">最大曝光数</th>
                        <th data-field="max_click">最大点击数</th>
                        <th data-field="free_max_impression">赠送曝光数</th>
                        <th data-field="free_max_click">赠送点击数</th>
                        <th data-field="owner">广告负责人</th>
                         -->
                        <th>操作</th>
                    </tr>
                    </thead>
                </table>
                <div style="margin-top: 20px; text-align: center;" class="box">
                    <div id="pagination3" class="page fl"></div>
                    <div class="info fl">
                    </div>
                </div>
            </div>
        </section>
    </section>


{% endblock %}












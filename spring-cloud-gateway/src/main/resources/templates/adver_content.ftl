<#include "base.ftl">

{% block title %}广告单元{% endblock %}

{% load staticfiles %}
{{ username }}



{% block custom_js %}
    <script src="{% static 'project_js/adver_content.js' %}"></script>
    <script src="{% static 'project_js/adver_content_uploads.js' %}"></script>

{% endblock %}

{% block custom_css %}

{% endblock %}


{% block navbar %}
    <style>
        .pull-left li:nth-child(2):after {
            content: '';
            display: block;
            width: 100%;
            height: 3px;
            background: #F16E54;
            position: absolute;
            left: 0;
            bottom: -20px;
            z-index: 9999;
        }

        .pull-left {
            height: 100%;
        }

        .pull-left li {
            display: inline-block;
            position: relative;
        }

        .leftside-navigation {
            background: #fff;
        }

        #main-content {
            margin-left: 147px;
            height: 100%;
            background: #F3F4F6;
            padding-top: 40px;
        }

        .wrapper {
            margin-top: 0;
        }

        #active {
            height: 60px;
            font-family: SourceHanSansCN-Medium;
            font-size: 16px;
            background: #F3F4F6;
            color: #F16E54;
        }

        .span_json_value {
            margin-left: 15px;
        }

        #noActive {
            color: #36414F;
            background: #fff;
            height: 60px;
            font-family: SourceHanSansCN-Medium;
            font-size: 16px;
        }

        .center {
            width: 100%;
            height: 100%;
            background: #fff;
            padding: 24px 15px;
        }

        #btn_add_file {
            width: 106px;
            height: 30px;
            background: #F16E54;
            outline: none;
            font-family: SourceHanSansCN-Medium;
            font-size: 13px;
        }

        .glyphicon {
            top: -1px;
        }

        .center-top {
            width: 100%;
            border-bottom: 1px solid #F3F3F3;
            padding-bottom: 24px;
            margin-bottom: 24px;
        }

        select {
            width: 154px;
            height: 28px;
            border: 1px solid #E5E5E5;
            border-radius: 4px;
            margin-right: 38px;

        }

        #input_adver {
            width: 154px;
            height: 28px;
            border: 1px solid #E5E5E5;
            border-radius: 4px;
            padding: 2px 5px;
            margin-right: 42px;
        }

        #btn_search_adver {
            width: 72px;
            height: 28px;
            background: #F16E54;
            border-radius: 3px;
            line-height: 10px;
            border: 1px solid #F16E54;
            outline: none;
        }

        .th-inner {
            text-align: center;
        }

        .list_table_tr_7 {
            width: 10px;
        }

        .page {
            width: 100%;
            padding: 15px;
        }

        .page-content {
            height: 100%;
            background: #fff;
            padding: 14px 15px;
            margin-top: 30px;
            padding-bottom: 177px;
            position: relative;
            display: none;
        }

        .page-title {
            margin: 0 0px 32px 160px;
            font-family: SourceHanSansCN-Medium;
            font-size: 18px;
            color: #36414F;
        }

        #project_file_upload div {
            width: 500px;
            margin-bottom: 24px;
        }

        .page-left {
            display: inline-block;
            width: 113px;
            text-align: right;
            margin-left: 36px;
            font-family: SourceHanSansCN-Regular;
            font-size: 13px;
            color: #36414F;
            margin-right: 12px;
        }

        .page-input {
            width: 320px;
            height: 40px;
            border: 1px solid #E6E8EA;
            padding: 2px 5px;
        }

        .page-btn {
            margin-left: 160px;
            padding-top: 30px;
            font-family: SourceHanSansCN-Medium;
            font-size: 16px;

        }

        #submit_save_b,#update_save_b {
            width: 155px;
            height: 36px;
            background: #F16E54;
            border-radius: 4px;
            color: #FFFFFF;
            outline: none;
            border: none;
            margin-right: 20px;
        }

        .btn-close {
            width: 67px;
            height: 36px;
            border: none;
            border: 1px solid #DFDCDC;
            border-radius: 4px;
            color: #9FA3A8;
            outline: none;
            background: #fff;
        }

        .page-right {
            position: absolute;
            left: 700px;
            top: 160px;
        }

        .img_par {
            display: inline-block;
            width: 64px;
            height: 64px;
            background: url("{% static 'img/load.png' %}") no-repeat;
            background-size: 100% 100%;
            margin-top:-20px;
        }

        .page-right-con {
            width: 350px;
            margin-top: 8px;
        }
        .page-right-con img{
            width:100%;
        }
        .page-right-con p{
            display: none;
        }
        .page-show{
            display: block !important;
        }


        .img_con {
            display: inline-block;
            width: 64px;
            height: 64px;
            position: relative;
            display: none;
            margin:0 10px;
        }

        .img_con img {
            display: inline-block;
            width: 100%;
            height:88px;
            padding-top:26px;
        }

        .img_con span {
            display: inline-block;
            width: 11px;
            height: 11px;
            background: #000;
            color: #fff;
            border-radius: 50%;;
            line-height: 11px;
            text-align: center;
            position: absolute;
            right: 5px;
            top: 29px;
            z-index: 999;
            cursor: pointer;
        }
        .btnCheck{
            width:60px;
            height:30px;
            border:none;
            outline: none;
            background:#ccc;
            color:#fff;
            border-radius: 3px;
            margin-right:20px;
        }
        .activeBtn{
            background: #F16E54;
        }
        tbody tr td:nth-child(6){
            width:180px;
        }
        tbody tr td:nth-child(5){
            width:280px;
        }
        tbody td{
            color: #36414F !important;
        }
        thead th .th-inner,thead th,tbody{
            text-align:center;
            color: #57585D !important;
        }
        .list_table_tr_7{
            display: flex;
            width:300px;
            justify-content: center;
        }
        .img_one{
            width:60px;
            height:100px;
        }
        .img_more{
            width:100px;
            height:100px;
        }
        tbody tr td:nth-child(6){
            width:300px;
        }
        tbody tr td:nth-child(5){
            width:150px;
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
                <span style="margin:0 20px;font-size:20px;line-height:40px;color:#fff;">财务管理</span>
            </a>
        </li>
    </ul>
{% endblock %}

{% block sidebar %}

    <aside>
        <div id="sidebar" class="nav-collapse">
            <!-- sidebar menu start-->
            <div class="leftside-navigation">
                <ul class="sidebar-menu" id="nav-accordion" style="padding-top:100px">
                    <li>
                        <a href="/h5/" id="noActive">
                            <span class='span_json_value'>广告计划</span>
                        </a>
                    </li>
                    <li>
                        <a id="active" href="/adver/">
                            <span class='span_json_value'>广告单元</span>
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

        <!-- 模态框（Modal） -->
        <div class="page">
            <div class="page-content">
                <div class="center-top" style="padding:15px 0 20px 0">
                    <span style="font-family: SourceHanSansCN-Regular;font-size: 12px;color: #B0B2B9;">广告单元</span>
                    <span style="font-family: SourceHanSansCN-Regular;font-size: 12px;color: #36414F;margin:0 5px">></span>
                    <span style="font-family: SourceHanSansCN-Regular;font-size: 12px;color: #36414F;">新增广告单元</span>
                </div>
                <p class="page-title">新增广告单元</p>

                <form id="project_file_upload" enctype="multipart/form-data">

                    <div style="display: none;" class="input-group">
                        <span class="page-left">广告单元所属人：</span>
                        <input type="text" value="{{ user_id }}" name="advertiser" id="adv_name_value"
                               class="page-input" placeholder="广告单元所属人">
                    </div>

                    <div class="input-group">
                        <span class="page-left">广告计划：</span>
                            <input type="text" name="campaign_name" id="adv_name_value" class="page-input" placeholder="广告计划" style="display: none">


                            <select id="id_select_ad_campaing" class="page-input"
                                    style="display: inline-block;margin-right:0;border-radius:0px;">
                                <option>请选择广告计划：</option>
                                {% for ad_campaing in all_ad_campaing %}
                                    <option value="{{ ad_campaing.id }}">{{ ad_campaing }}</option>
                                {% endfor %}
                            </select>

                    </div>

                    <div style="display: none;" class="input-group">
                        <span class="page-left">广告计划 id：</span>
                        <input type="text" value="" name="campaign_id" id="adv_plan_value"
                               class="page-input" placeholder="广告计划id">
                    </div>



                    <div class="input-group">
                        <span class="page-left">广告描述：</span>
                        <input type="text" name="data" id="adv_des_value" class="page-input" placeholder="描述">
                    </div>

                    <div class="input-group">
                        <span class="page-left">广告来源：</span>
                        <input type="text" name="title" id="adv_title_value" class="page-input"
                               placeholder="标题" style="height:58px;">
                    </div>

                    <div class="input-group">
                        <span class="page-left">图片类型：</span>
                        <p style="display:inline-block;">
                            <button class="btnCheck activeBtn" data-val="1" data-ind="0">大图</button>
                            <button class="btnCheck" data-val="2" data-ind="1">小图</button>
                            <button class="btnCheck" data-val="5" data-ind="2">三图</button>
                        </p>
                        <input type="text" name="ad_imp_type" id="ad_imp_type" value="1" class="page-input" placeholder="广告类型" style="height:58px;" hidden>
                    </div>

                    <div class="input-group">
                        <span class="page-left" style="margin-top:-20px">上传提示：</span>
                        <p style="display: inline-block;width:300px">
                           <button style="outline: none;border:none;background: none;font-weight: 600;" class="imgBtn">小图、组图 横宽比 1.53:1， 宽 必须大于等于 360
                            大图 横宽比 16:9， 宽建议大于等于 1080</button>
                        </p>
                    </div>


                    <div class="input-group img_content img_div">
                        <span class="page-left">上传图片：</span>

                        <span class="img_par">
                        <input type="file" multiple="multiple" name="image_file" id="adv_photo_value2"
                               accept="image/png, image/jpeg, image/gif, image/jpg" class="page-input"
                               placeholder="上传图片" style="display: inline-block;width:64px;height:64px;opacity: 0">
                       </span>

                        <p class="img_con" style="display: inline-block;">
                            <img alt="">
                            <span>x</span>
                        </p>

                        <p class="img_con" style="display: inline-block;">
                            <img alt="">
                            <span>x</span>
                        </p>

                        <p class="img_con" style="display: inline-block;">
                            <img alt="">
                            <span>x</span>
                        </p>

                    </div>

                    <div class="input-group" style="margin-top:40px;">
                        <span class="page-left">跳转的活动链接：</span>
                        <input type="text" name="landing_url" id="adv_landing_value" class="page-input"
                               placeholder="最大点击数">
                    </div>


                    <div class="input-group">
                        <input type="text" name="enable" id="adv_enable_value" class="page-input" placeholder="1"
                               value="1" style="display: none">
                    </div>

                    <div class="page-btn">

                        <button id="submit_save_b">
                            保存
                        </button>
                        <button id="update_save_b">
                            修改
                        </button>
                        <button class="btn-close">关闭</button>
                    </div>

                {% csrf_token %}
                </form>

                <div class="page-right">
                    <p style="font-family: SourceHanSansCN-Regular;font-size: 12px;color: #BDC0C4;">
                        在信息流中的展现示意：
                    </p>
                    <div class="page-right-con">
                         <p class="page-show">
                             <img src="{% static 'img/big.png' %}" alt="">
                         </p>
                         <p>
                             <img src="{% static 'img/small.png' %}" alt="">
                         </p>
                         <p>
                             <img src="{% static 'img/more.png' %}" alt="">
                         </p>
                    </div>
                </div>

            </div><!-- /.modal-content -->

        </div><!-- /.modal -->
    </div>



        <section id="main-wrapper" class="wrapper">

            <div class="center">
                <div class="center-top">
                    <button id="btn_add_file" type="button" class="btn btn-default add_btn">
                        <span class="glyphicon" aria-hidden="true" style="color:#fff">新增广告单元</span>
                    </button>
                </div>


                <span style="font-family: SourceHanSansCN-Regular;font-size: 14px;color: #9B9A9B;">状态：</span>

                <select id="adver_type_select">
                    <option value="status_type">请选择状态类型</option>
                    <option value="dsh">待审核</option>
                    <option value="tfz">投放中</option>
                    <option value="zt">暂停</option>
                    <option value="ygq">已过期</option>
                    <option value="yebz">余额不足</option>
                    <option value="bgdb">曝光达标</option>
                    <option value="djdb">点击达标</option>
                    <option value="ready">未到开始时间</option>
                </select>

                <span>
                    <input id="input_adver" type="text">
                    <button id="btn_search_adver" type="button" class="btn btn-primary">搜索</button>
                </span>

                <table id="list_table" style="text-align:center;">
                    <thead>
                    <tr>
                        <th data-field="state" data-radio="true"></th>
                        <th data-field="id">广告单元ID</th>
                        <th data-field="campaign_id">广告计划</th>
                        <th data-field="campaign_id_status">广告计划状态</th>
                        <th data-field="title">标题</th>
                        <th data-field="data" >描述</th>
                        <th data-field="img_url">图片路径</th>
                        <th data-field="landing_url">落地页</th>
                        <!--
                        <th data-field="owner">广告负责人</th>
                        -->
                        <th style="width:100px">操作</th>
                    </tr>
                    </thead>
                </table>
                <div style="margin-top: 20px; text-align: center;" class="box">
                    <div id="pagination3" class="page fl"></div>
                    <div class="info fl">
                    </div>
                </div>
                {#        <p id = 'all_pag'></p>#}

            </div>

        </section>
        <input type="file" hidden id="aaa" style="display: none">
    </section>


{% endblock %}










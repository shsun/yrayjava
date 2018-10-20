<#include "base.ftl">

{% block title %}广告组{% endblock %}

{% load staticfiles %}
{{ username }}



{% block custom_js %}
    <script src="{% static 'project_js/finanace.js' %}"></script>
{% endblock %}

{% block custom_css %}


{% endblock %}


{% block navbar %}
<style>
    .pull-left li:nth-child(3):after{
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
        padding-top:80px;
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
    .wrapper-top{
        width:100%;
        background: #fff;
        padding:14px 14px 21px 14px;
    }
    .top-title{
        padding:0 0 14px 0;
        border-bottom:1px solid  #F5F5F5;
        font-family: SourceHanSansCN-Regular;
        font-size: 12px;
        color: #B0B2B9;
        text-align: left;
    }
    .top-title span{margin-right:10px;}
    h3{
        margin-top:25px;
        padding-left:15px;
        font-size: 20px;
        color: #38414E;
        font-weight: bold;
        position: relative;
    }
    h3:before{
        content:'';
        display: block;
        width:4px;
        height:21px;
        background: #F16E54;
        position: absolute;
        left:0;
    }
    .wrapper-top dl{
        display: inline-block;
        width:240px;
        padding:18px 21px;
        background: #FAFAFA;
        border: 1px solid #F2F4F5;
        margin-top:20px;
        position: relative;
    }
    .wrapper-top dl dt{
        width:40px;
        height:38px;
    }
    .wrapper-top dl dd{
        position: absolute;
        left:80px;
        top:14px;
    }
    .wrapper-top dl dt img{
        width:100%;
        height:100%;
    }
    .wrapper-top dl dd p{
        font-family: SourceHanSansCN-Regular;
        font-size: 14px;
        color: #9B9A9B;
        letter-spacing: 1px;
    }
    .wrapper-top dl dd span{
        font-family: SourceHanSansCN-Medium;
        font-size: 20px;
        color: #38414E;
        font-weight: bold;
    }
    .form-group{
        display: inline-block;
    }
    .center{
        width:100%;
        background: #fff;
        padding:14px 14px 21px 14px;
        margin-top:10px;
    }
    h4{
        font-weight: bold;
        font-family: SourceHanSansCN-Medium;
        font-size: 16px;
        color: #38414E;
        padding-bottom:11px;
    }
    tbody td{
        color: #36414F !important;
    }
    thead th .th-inner,thead th,tbody{
        text-align:center;
        color: #57585D !important;
    }
    #finance_start_time_value,#finance_end_time_value{
        height: 30px;
        margin-top: 30px;
        margin-left: 30px;
        text-indent: 1em;
        margin-left: 10px;
    }
    #btn_search_finance{
    width: 72px;
    height: 28px;
    background: #F16E54;
    border-radius: 3px;
    line-height: 10px;
    border: 1px solid #F16E54;
    outline: none;
    margin-left: 30px;
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
        <div class="nav-collapse" id="sidebar">
            <!-- sidebar menu start-->
            <div class="leftside-navigation">
                <ul class="sidebar-menu" id="nav-accordion" style="padding-top:100px">
                    <li>
                        <a href="/recharge_html/" id="active">
                            <span class = 'span_json_value'>财务管理</span>
                        </a>

                    </li>
                </ul>
            </div>
            <!-- sidebar menu end-->
        </div>
    </aside>
{% endblock %}



{% block content %}
<section id="main-content" >

       <div class="center">
            <h3>财务明细</h3>

           <span style="font-family: SourceHanSansCN-Regular;font-size: 14px;color: #9B9A9B;">时间:</span>

           <input type="text" name="start_time" id="finance_start_time_value" class="page-input"
                  placeholder="开始时间">

           <input type="text" name="end_time" id="finance_end_time_value" class="page-input"
                  placeholder="结束时间">
           <button id="btn_search_finance" type="button" class="btn btn-primary">搜索</button>

           <table id="list_table">
                <thead>
                <tr>
{#                    <th data-field="id">ID</th>#}
                    <th data-field="date">消费日期</th>
                    <th data-field="total_cost">每日消耗</th>
                    <th data-field="total_recharge">充值金额</th>
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













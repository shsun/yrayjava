<@override name="title_block">广告组</@override>

<@override name="custom_js">
<script src="/echarts/echarts.common.min.js"></script>
<script src="/js/home.js"></script>
</@override>

<@override name="custom_css">
    ${userName}
</@override>

<@override name="userName_block">
${userName}
</@override>

<@override name="navbar">
<style>
    .pull-left li:nth-child(1):after {
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
        padding-top: 80px;
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

    .wrapper-top {
        width: 100%;
        background: #fff;
        padding: 14px 14px 21px 14px;
    }

    .top-title {
        padding: 0 0 14px 0;
        border-bottom: 1px solid #F5F5F5;
        font-family: SourceHanSansCN-Regular;
        font-size: 12px;
        color: #B0B2B9;
        text-align: left;
    }

    .top-title span {
        margin-right: 10px;
    }

    h3 {
        margin-top: 25px;
        padding-left: 15px;
        font-size: 20px;
        color: #38414E;
        font-weight: bold;
        position: relative;
    }

    h3:before {
        content: '';
        display: block;
        width: 4px;
        height: 21px;
        background: #F16E54;
        position: absolute;
        left: 0;
    }

    .wrapper-top dl {
        display: inline-block;
        width: 240px;
        padding: 18px 21px;
        background: #FAFAFA;
        border: 1px solid #F2F4F5;
        margin-top: 20px;
        position: relative;
    }

    .wrapper-top dl dt {
        width: 40px;
        height: 38px;
    }

    .wrapper-top dl dd {
        position: absolute;
        left: 80px;
        top: 14px;
    }

    .wrapper-top dl dt img {
        width: 100%;
        height: 100%;
    }

    .wrapper-top dl dd p {
        font-family: SourceHanSansCN-Regular;
        font-size: 14px;
        color: #9B9A9B;
        letter-spacing: 1px;
    }

    .wrapper-top dl dd span {
        font-family: SourceHanSansCN-Medium;
        font-size: 20px;
        color: #38414E;
        font-weight: bold;
    }

    .form-group {
        display: inline-block;
    }

    .center {
        width: 100%;
        background: #fff;
        padding: 14px 14px 21px 14px;
        margin-top: 10px;
    }

    h4 {
        font-weight: bold;
        font-family: SourceHanSansCN-Medium;
        font-size: 16px;
        color: #38414E;
        padding-bottom: 11px;
    }

    tbody td {
        color: #36414F !important;
    }

    thead th .th-inner, thead th, tbody {
        text-align: center;
        color: #57585D !important;
    }

    .btn_es {
        display: flex;
        justify-content: center;
    }
</style>
    <ul style="margin-left: 10px;" class="pull-left">

        <li>
            <a href="/home/home.ftl" class="tabTop">
                <span style="margin:0 20px;font-size:20px;line-height:40px;color:#fff;">首页</span>
            </a>
        </li>


        <li>
            <a href="/h5/" class="tabTop">
                <span style="margin:0 20px;font-size:20px;line-height:40px;color:#fff;">推广管理</span>
            </a>
        </li>

        <li>
            <a href="/recharge_html/" class="tabTop">
                <span style="margin:0 20px;font-size:20px;line-height:35px;color:#fff;">财务管理</span>
            </a>
        </li>
    </ul>
</@override>


<@override name="sidebar">
    <aside>
        <div class="nav-collapse" id="sidebar">
            <!-- sidebar menu start-->
            <div class="leftside-navigation">
                <ul class="sidebar-menu" id="nav-accordion" style="padding-top:100px">
                    <li>
                        <a href="/home/home.ftl" id="active">
                            <span class='span_json_value'>账户明细</span>
                        </a>

                    </li>
                </ul>
            </div>
            <!-- sidebar menu end-->
        </div>
    </aside>
</@override>


<@override name="content">
<section id="main-content">
    <!--动态增加项目模态框-->
    <section id="main-wrapper" class="wrapper">
        <div class="wrapper-top">
    <#list queryset as qery>
        <p class="top-title">
            <span>充值时间：${qery.measurement_date}</span>
            <span>备忘录：${qery.comments}</span>
        </p>
        <h3>资产概况</h3>
        <dl class="left">
            <dt>
                <img src="/img/left.png" alt="">
            </dt>
            <dd>
                <p>充值总金额</p>
                <span>${cz_all_money}元</span>
            </dd>
        </dl>
        <dl style="margin-left:35px">
            <dt>
                <img src="/img/right.png" alt="">
            </dt>
            <dd>
                <p>现金余额</p>
                <span id='sur_money'>${sy_release_money}元</span>
            </dd>
        </dl>
        <dl style="margin-left:35px">
            <dt>
                <img src="/img/money.png" alt="">
            </dt>
            <dd>
                <p>赠送金额余额</p>
                <span id='sur_money'>${sy_gift_money}元</span>
            </dd>
        </dl>
    </#list>

        </div>

        <div class="center">
            <h3>资产概况</h3>
            <h4 style="margin:30px 0 10px 0">累计</h4>
            <table class="table table-striped table-bordered" style="margin-bottom:30px">
                <thead>
                <tr>
                    <th>曝光数</th>
                    <th>点击数</th>
                    <th>点击率</th>
                    <th>消费金额</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${impression__sum}</td>
                    <td>${click__sum}</td>
                    <td>${click_ctr}</td>
                    <td>${cost__sum}</td>
                </tr>
                </tbody>
            </table>

            <!-- <div id="toolbar"> -->
            <span style="font-family: SourceHanSansCN-Regular;font-size: 14px;color: #9B9A9B;margin-right:15px">请选择查询时间</span>
            <div class="form-group">
                <select id="home_time_select" class="form-control">
                    <option value="0">今天</option>
                    <option value="1">昨天</option>
                    <option value="7">过去7天</option>
                    <option value="30">过去30天</option>
                </select>
            </div>
            <h4>分天</h4>
            <!-- </div> -->
            <div class="btn_es">
                <button class="layui-btn layui-btn-normal show">展示数</button>
                <button class="layui-btn layui-btn-primary p_num">点击数</button>
                <button class="layui-btn layui-btn-primary p_rate">点击率</button>
                <button class="layui-btn layui-btn-normal p_money">总花费(元)</button>
            </div>
            <div id="charts" style="height: 500px;width: 100%;margin:20px auto;"></div>
            <table id="list_table">
                <thead>
                <tr>
                    <th data-field="date">消费日期</th>
                    <th data-field="campaign_name">广告计划名称</th>
                    <th data-field="impression">曝光数</th>
                    <th data-field="click">点击数</th>
                    <th data-field="ctr">点击率</th>
                    <th data-field="cost">消费金额</th>
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
</@override>

<@extends name="base.ftl"/>
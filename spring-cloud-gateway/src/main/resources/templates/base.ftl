<!DOCTYPE html>
<head>
    <title><@block name="title_block">base_title_content</@block></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content=""/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>
    <!-- bootstrap-css -->
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">

    <!-- //bootstrap-css -->

    <!-- bootstrap-table-css -->
    <link rel="stylesheet" href="/bootstrap-table/bootstrap-table.min.css">

    <!-- Custom CSS -->
    <link href="/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="/css/style-responsive.css" rel="stylesheet"/>


    <!-- font-awesome icons -->
    <link rel="stylesheet" href="/css/font.css" type="text/css"/>
    <link href="https://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/font-awesome.css">
    <link rel="stylesheet" href="/css/morris.css" type="text/css"/>
    <!-- calendar -->
    <link rel="stylesheet" href="/css/monthly.css">
    <!-- //calendar -->


    <!-- 弹出框插件 css -->
    <link rel="stylesheet" href="/alertplug/sweet-alert.css">
    <!-- 弹出框插件 css end -->


    <!-- 分页工具 -->

    <link rel="stylesheet" href="/jqueryPagination/css/jquery.pagination.css">

    <!-- 分页工具 end -->


    <!--layui-->
    <link rel="stylesheet" href="/layui/css/layui.css">

    <!--layuiend-->


    <!--加载动画-->
    <link rel="stylesheet" href="/loading/jquery.mloading.css">

    <!--加载动画end-->


    <@block name="custom_css">base_custom_css_content</@block>

</head>
<body>
<section id="container" style="min-height:100%;background: #F3F4F6;">
    <!-- //font-awesome icons -->
    <script src="/lib/raphael-min.js"></script>
    <style>
        .header {
            background: #38414E;
        }

        .header:after {
            content: "";
            display: block;
            width: 100%;
            height: 22px;
            background: url("/img/top.png");
            position: absolute;
            bottom: 0;
        }

        .logoimg {
            width: 140px;
            height: 45px;
            margin-left: 20px;
            margin-top: 17px;
            position: relative;
        }

        .logoimg img {
            width: 100%;
            height: 100%;
        }

        #sidebar {
            width: 147px;
        }

        .sidebar-toggle-box {
            position: absolute;
            right: 20px;
            top: 0;
        }
    </style>
    </head>
    <body>
    <section style="height:100%">
        <!--header start-->
        <header class="header fixed-top clearfix">
            <!--logo start-->
            <div class="brand" style="background: #38414E;">
                <div class="logoimg">
                    <img src="/img/logo.png" alt="">
                </div>
                <div class="sidebar-toggle-box">
                    <div class="fa fa-bars"></div>
                </div>
            </div>
            <!--logo end-->

            <div class="top-nav clearfix">
                <!--search & user info start-->

                <@block name="navbar"></@block>

                <ul class="nav pull-right top-menu">

                    <a href="/logout/">
                        <li>
                            <span style="margin:0 20px;font-size:25px;line-height:35px;" title="退出"
                                  class="glyphicon glyphicon-log-out"></span>
                        </li>
                    </a>
                </ul>

                <ul class="nav pull-right top-menu">
                    <li>
                        <span id="base_userName_id" style="margin:0 10px;font-size:25px;line-height:35px;color:#fff;"><@block name="userName_block">base_userName_content</@block></span>
                    </li>

                </ul>
                <!--search & user info end-->
            </div>
        </header>
        <!--header end-->
    </section>

    <@block name="sidebar">
        <!--sidebar start-->
        <aside>
            <div id="sidebar" class="nav-collapse">
                <!-- sidebar menu start-->
                <div class="leftside-navigation">
                    <ul class="sidebar-menu" id="nav-accordion">
                        <li>
                            <a class="active" href="javascript:;">
                                <i class="fa fa-book"></i>
                                <span class='span_json_value'>Dashboard</span>
                                <span class='span_text'>11</span>
                            </a>
                        </li>
                    </ul>
                </div>
                <!-- sidebar menu end-->
            </div>
        </aside>
        <!--sidebar end-->
        <!--main content start-->
    </@block>

    <@block name="content">base_content_content</@block>

    <!--main content end-->
</section>

<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
<script src="/lib/jquery2.0.3.min.js"></script>
<script src="/lib/bootstrap.js"></script>
<script src="/lib/jquery.dcjqaccordion.2.7.js"></script>
<script src="/lib/scripts.js"></script>
<script src="/lib/jquery.slimscroll.js"></script>
<script src="/lib/jquery.nicescroll.js"></script>
<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="/lib/flot-chart/excanvas.min.js"></script>
<![endif]-->
<script src="/lib/jquery.scrollTo.js"></script>
<!-- morris JavaScript -->

<!-- bootstraptable js -->
<script src="/bootstrap-table/bootstrap-table.js"></script>
<script src="/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script src="/bootstrap-table/tableExport.js"></script>
<script src="/bootstrap-table/bootstrap-table-export.js"></script>


<!-- 弹出框插件  -->
<script src="/alertplug/sweet-alert.js"></script>

<!-- 弹出框插件 end  -->


<!-- 分页工具 -->

<script src="/jqueryPagination/js/jquery.pagination.min.js"></script>

<!-- 分页工具 end -->


<!--引入layui图-->
<script src="/layui/layui.js"></script>

<!--引入layui图 end-->

<!--加载动画-->
<script src="/loading/jquery.mloading.js"></script>

<!--加载动画 end-->


<!--公用js-->
<script src="/js/project_base.js"></script>

<!--公用js end end-->

<@block name="custom_js">base_custom_js_content</@block>
<!-- mywrite js -->

<!-- mywrite js end -->
</body>
</html>

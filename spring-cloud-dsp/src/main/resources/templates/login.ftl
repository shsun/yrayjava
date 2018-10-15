<!DOCTYPE html>
<head>
    <title>请登陆</title>
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

    {#
    <link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    #}
    <link rel="stylesheet" href="/bootstrap/bootstrap.min.css">
    <!-- //bootstrap-css -->

    <!-- Custom CSS -->
    <link href="/css/style.css" rel='stylesheet' type='text/css'/>
    <link href="/css/style-responsive.css" rel="stylesheet"/>
    <!-- font-awesome icons -->
    <link rel="stylesheet" href="/css/font.css" type="text/css"/>
    {#
    <link href="https://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">
    #}
    <link rel="stylesheet" href="/css/font-awesome.css">

    <!-- //font-awesome icons -->
    {#
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    #}
    <script src="/lib/jquery2.0.3.min.js"></script>
</head>
<style>
    .log-w3 {
        width: 100%;
        height: 100%;
        background: url("/img/back.png") no-repeat;
        background-size: 100% 100%;
    }

    header {
        width: 100%;
        height: 80px;
        background: #38414E;
    }

    .logoimg {
        width: 140px;
        height: 45px;
        margin-left: 20px;
    }

    .logoimg img {
        width: 100%;
        height: 100%;
        margin-top: 17px;
    }
</style>
<body>
<div class="log-w3">
    <header>
        <div class="logoimg">
            <img src="/img/logo.png" alt="">
        </div>
    </header>
    <div class="w3layouts-main">
        <h2 style="color: #4D4E50;font-size:16px">please输入用户名和密码</h2>
        <form action="/user/ulogin2" method="post">
            <input type="userName" class="ggg" name="userName" placeholder="填写用户名">
            <input type="password" class="ggg" name="password" placeholder="填写密码">
            <div class="clearfix"></div>
            <input type="submit" value="登陆" name="登陆">
        </form>
    </div>
</div>
<script src="/lib/bootstrap.js"></script>
<script src="/lib/jquery.dcjqaccordion.2.7.js"></script>
<script src="/lib/scripts.js"></script>
<script src="/lib/jquery.slimscroll.js"></script>
<script src="/lib/jquery.nicescroll.js"></script>
<!--[if lte IE 8]>
<script language="javascript" type="text/javascript" src="/lib/flot-chart/excanvas.min.js"></script><![endif]-->
<script src="/lib/jquery.scrollTo.js"></script>
</body>
</html>

<!DOCTYPE html>
<html>
<head lang="en">
    <title>Spring Boot Demo - FreeMarker</title>
    <link href="/css/index.css" rel="stylesheet">
    <script type="text/javascript" src="/lib/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="/js/index.js"></script>
</head>
<style>
    .log-w3 {
        width: 100%;
        height: 100%;
        background: url("{% static '/img/back.png' %}") no-repeat;
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
<h2>首页--
    <h2>

        <font>
        <#list userList as item>
            ${item!}<br/>
        </#list>
        </font>

        <button class="a"> click me</button>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: LM
  Date: 2018/4/19
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>用户中心 - {{lay.base.name}}</title>

    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container">
    <div class="row">
        <nav class="navbar bg-light col-sm-2">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="home">我的主页</a>
                </li>
                <li class="nav-item">

                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">用户中心</a>
                </li>
                <li class="nav-item">

                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/interflow/user/edit">基本设置</a>
                </li>
                <li class="nav-item">

                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">我的消息</a>
                </li>
            </ul>
        </nav>
        <div class="col-sm-10">
            <div class="panel panel-default">
                <div class="panel-body">
                    这是一个基本的面板
                </div>
            </div>

        </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>


</body>
</html>

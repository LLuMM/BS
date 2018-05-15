<%--
  Created by IntelliJ IDEA.
  User: LM
  Date: 2018/4/19
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>


<nav class="navbar navbar-expand-sm bg-dark navbar-dark navbar-dark fixed-top">
    <div class="container">
        <div class="col-sm-2 ">
            <!-- Brand/logo -->
            <a class="navbar-brand" href="/index/">
                <img src="../img/Logo.jpg" style="width:100px;height: 40px">
            </a>
        </div>
        <!-- Links -->
        <div class="col-sm-5">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/new/index?type=0">热点新闻&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/new/index?type=1">技术专区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/question/toemotion?type=2">情感专区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="tochat" <%--href="/user/friendchat?uid=${user.uid}&who=1"--%>>讨论区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
                </li>
            </ul>
        </div>
        <div class="col-sm-4">
            <form class="form-inline" style="margin-top: 15px" action="/search/keyword">
                <input class="form-control" type="text" name="keyword" placeholder="输入关键词">
                <button class="btn btn-primary" type="submit" id="search">搜索</button>
            </form>
        </div>
        <div class="col-sm-3">
            <c:if test="${user !=null&&user !=''}">
                <input type="hidden" id="loginid" value="${user.uid}">
                <span style="color: #ffffff"> 嗨！<a href="/user/userindex?uid=${user.uid}">${user.username}</a>&nbsp;|
                </span><span id="quit" style="color: #ffffff">退出&nbsp;&nbsp;|&nbsp;</span>
            </c:if>
            <c:if test="${user ==null|| user ==''}">
                <input type="hidden" id="loginid" value="">
                <span data-toggle="modal" data-target="#login" style="color: #ffffff">登陆&nbsp;|&nbsp;</span>
            </c:if>
            <a style="color: #ffffff" href="/user/toregister">注册</a>

        </div>

    </div>
</nav>

<!-- 模态框（Modal） -->
<div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">
                    登陆
                </h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>

            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <label>用户名:</label>
                        <input type="text" class="form-control" id="username" placeholder="Enter username">
                    </div>
                    <div class="form-group">
                        <label>密码:</label>
                        <input type="password" class="form-control" id="password" placeholder="Enter password">
                    </div>
                    <div class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox">记住我
                        </label>
                    </div>
                    <div class="form-group">
                        <span id="error" style="color: red"></span>
                    </div>
                    <button type="button" id="but" class="btn btn-primary">登陆</button>
                </form>
            </div>

        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>
<script>
    $(function () {

        $("#but").click(function () {

            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            $.ajax({
                type: "post",
                url: "/user/login",
                dataType: "text",
                data: {
                    "username": username,
                    "password": password
                },
                success: function (Result) {
                    var dataObj = JSON.parse(Result);
                    if (dataObj.Status) {
                        if (dataObj.user == '1') {
                            window.location.href = '/user/toadmin';
                        } else {
                            location.reload();
                        }
                    } else {
                        $("#error").text("用户名或密码有误！请重新填写！");
                    }
                }

            });

        });
        $("#quit").click(function () {
            var test = window.location.href;
            var loginid = document.getElementById("loginid").value;
            alert("退出?");
            $.ajax({
                type: "get",
                url: "/user/loginout",
                success: function (Result) {
                    var dataObj = JSON.parse(Result);

                    if (dataObj.Status) {
                        if (test == "http://localhost:8080/user/toadmin" || test == "http://localhost:8080/user/userinfo?uid=" + loginid ||
                            test == "http://localhost:8080/user/userindex?uid=" + loginid
                        ) {
                            location.href = '/index';
                        } else {
                            location.reload();
                        }
                    }
                }
            });
        });
        $("#tochat").click(function () {
            var loginid = document.getElementById("loginid").value;
            if (loginid == "" || loginid == null) {
                alert("请先登录！");
            } else {
                window.location.href="/user/friendchat?uid="+loginid+"&who=1";
            }
        });
    });
</script>

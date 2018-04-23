<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <title>首页</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>
<br>
<div class="container">
    <div class="row">
        <div class="col-sm-8"><img src="../img/timg.jpg" style="width: 100%"/></div>
        <div class="col-sm-4">
            <form id="registerform" method="post" action="register">
                <div class="form-group">
                    用户名:
                    <input type="text" class="form-control" id="username" name="username" value="${user.username}"
                           placeholder="长度1-20位字符，中文、数字、_ 、字母">
                </div>
                <div class="form-group">
                    显示名:
                    <input type="text" class="form-control" id="showname" name="showname" value="${user.showname}"
                           placeholder="输入显示名">
                </div>
                <div class="form-group">
                    密码:
                    <input type="password" class="form-control" id="password" name="password" value="${user.password}"
                           placeholder="密码长度6-16位字符，由数字、字母组成">
                </div>
                <div class="form-group">
                    确认密码:
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword"
                           placeholder="请再次输入密码">
                </div>
                <div class="form-group">
                    邮箱:
                    <input type="email" class="form-control" id="email" name="email" value="${user.email}"
                           placeholder="用于取回密码,请填写正确的常用邮箱">
                </div>

                <div class="form-group">
                    工作:
                    <input type="text" class="form-control" id="uwork" name="uwork" value="${user.uwork}"
                           placeholder="输入工作单位">
                </div>
                <div class="form-group">
                    性别:
                    <label><input type="radio" id="sex" name="sex" value="${user.sex}">男</label>
                    <label><input type="radio" id="sex" name="sex" value="${user.sex}">女</label>
                </div>
                <button type="submit" class="btn btn-primary" style="width: 100%">注册</button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
<%--<script>
    $("#registerform").click(function(){
        alert("sadsa")
        var username = $("[name=username]").val();
        var password = $("[name=password]").val();

      /*  if(username!="admin" || password!="admin"){
            $.messager.alert('错误',"用户名密码不正确！");
            return ;
        }*/
        window.location.href="user/register";
    });
</script>--%>

</body>
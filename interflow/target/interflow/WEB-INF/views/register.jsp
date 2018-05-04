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
<div class="container" style="margin-top: 80px">
    <div class="row">
        <div class="col-sm-8"><img src="../img/timg.jpg" style="width: 100%"/></div>
        <div class="col-sm-4">
            <form <%--id="registerform" method="post" action="register"--%>>
                <span id="regerror" style="color: red"></span>
                <div class="form-group">
                    用户名:
                    <input type="text" class="form-control" id="regusername" name="regusername"
                           placeholder="长度1-20位字符，中文、数字、_ 、字母"/>
                </div>
                <div class="form-group">
                    密码:
                    <input type="password" class="form-control" id="regpassword" name="regpassword"
                           placeholder="密码长度6-16位字符，由数字、字母组成">
                </div>
                <div class="form-group">
                    确认密码:
                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword"
                           placeholder="请再次输入密码">
                </div>
                <div class="form-group">
                    邮箱:
                    <input type="email" class="form-control" id="email" name="email"
                           placeholder="用于取回密码,请填写正确的常用邮箱">
                </div>

                <div class="form-group">
                    电话:
                    <div class="row">
                        <input type="text" class="form-control" id="phone" style="width: 200px;margin-left: 15px"
                               placeholder="请输入手机号">
                        <button style="margin-left: 20px" class="btn btn-primary" id="getverifycode">获取验证码</button>
                    </div>
                </div>
                <div class="form-group">
                    验证码:
                    <input type="text" class="form-control" id="verifycode" id="verifycode" placeholder="请输入验证码">
                </div>

                <button <%--type="submit"--%> id="register" class="btn btn-primary" style="width: 100%">注册</button>
            </form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>

<script>
    $(function () {
        $("#regusername").blur(function () {
            var regusername = document.getElementById("regusername").value;
            if (regusername == null || $.trim(regusername) == '') {
                $("#regerror").text("用户名不能为空！");
            } else if (regusername.indexOf(" ") > -1) {
                $("#regerror").text("用户名不能含有空格！");
            } else if (regusername.length < 1) {
                $("#regerror").text("用户名长度不能小于1个字符！");
            } else if (regusername.length > 20) {
                $("#regerror").text("用户名长度不能大于20个字符！");
            } else {
                $("#regerror").text("");
                $.ajax({
                    type: "post",
                    url: "/user/checkUsername",
                    dataType: "text",
                    data: {
                        "username": regusername
                    },
                    success: function (Result) {
                        var dataObj = JSON.parse(Result);
                        if (dataObj.Status) {
                            $("#regerror").text("用户名已存在！");
                        }
                    }
                });
            }

        });
    });
        $("#regpassword").blur(function () {
            var passwordreg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/;
            var regpassword = document.getElementById("regpassword").value;

            if (regpassword == null || $.trim(regpassword) == '') {
                $("#regerror").text("密码不能是空串");
            } else if (regpassword.indexOf(" ") > -1) {
                $("#regerror").text("密码不能含有空格");
            } else if (regpassword.length < 6) {
                $("#regerror").text("密码长度不能小于6个字符");
            } else if (regpassword.length > 16) {
                $("#regerror").text("密码长度不能大于16个字符");
            }
            else if (!passwordreg.test(regpassword)) {
                $("#regerror").text("密码必须含有字母和数字");
            }else {
                $("#regerror").text("");
            }
    });
    $("#confirmPassword").blur(function () {
        var regpassword = document.getElementById("regpassword").value;
        var confirmPassword = document.getElementById("confirmPassword").value;
        if (confirmPassword != regpassword) {
            $("#regerror").text("两次密码不一致！");
        }else {
            $("#regerror").text("");
        }
    });
    $("#email").blur(function () {
        var email = document.getElementById("email").value;
        var emailreg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
        if (!emailreg.test(email)) {
            $("#regerror").text("邮箱格式不正确！");
        }else {
            $("#regerror").text("");
        }
    });

    $("#phone").blur(function () {
        var phone = document.getElementById("phone").value;
        var phonereg = /^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))[0-9]{8}$/;

    if (!phonereg.test(phone)) {
        $("#regerror").text("号码格式不正确！");
        }else {
            $("#regerror").text("");
        }
    });

    $("#register").click(function () {
        var regusername = document.getElementById("regusername").value;
        var password = document.getElementById("regpassword").value;
        var email = document.getElementById("email").value;
        var phone = document.getElementById("phone").value;
        var verifycode = document.getElementById("verifycode").value;
        alert("regusername:"+regusername +" password: "+password+" email:"+email+ " phone:"+phone);
        $.ajax({
            type: "post",
            url: "/user/register",
            dataType: "text",
            data: {
                "username": regusername,
                "password": password,
                "email": email,
                "phone": phone,
                "verifycode": verifycode,
            },
            success: function (Result) {
                var dataObj = JSON.parse(Result);
                if (!dataObj.Status) {
                    $("#regerror").text("验证码错误！");
                }
                else {
                    alert("注册成功！请重新登陆！");
                }
            }
        });

    });


</script>

</body>
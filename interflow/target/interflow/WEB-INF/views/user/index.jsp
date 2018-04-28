<%--
  Created by IntelliJ IDEA.
  User: LM
  Date: 2018/4/19
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

    <title>用户中心-${user.showname}</title>

    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="container" style="margin-top: 80px">

    <br>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" data-toggle="tab" href="#home">基本设置</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#menu1">用户中心</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#menu2">我的消息</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#menu3">版主申请</a>
        </li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <div id="home" class="container tab-pane active"><br>
            <h5>头像上传</h5>
            <p><input type="file"/></p>
            <h5>用户名</h5>
            <c:if test="${user!=null}">
            <p><input type="text" id="showname" name="showname" value="${user.showname}"
                      placeholder="输入显示名"></p>
            <h5>密码</h5>
            <p><input type="password" id="password" name="password" value="${user.password}"
                      placeholder="密码长度6-16位字符，由数字、字母组成"></p>

            <h5>用户名邮箱:</h5>
            <p><input type="email" id="email" name="email" value="${user.email}"
                      placeholder="用于取回密码,请填写正确的常用邮箱"></p>

            <h5>性别:</h5>
            <p><label><input type="radio" id="sex" name="sex" value="${user.sex}">男</label>
                <label><input type="radio" id="sex" name="sex" value="${user.sex}">女</label></p>

            <p>
                <button type="submit">修改</button>
            </p>
            </c:if>
        </div>

        <div id="menu1" class="container tab-pane fade"><br>
            <h3>帖子管理</h3>
            <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                consequat.</p>
        </div>
        <div id="menu2" class="container tab-pane fade"><br>
            <h3>最新消息</h3>
            <c:if test="${msgs!=null}">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>来自</th>
                        <th>内容</th>
                        <th>时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${msgs}" var="ms">

                        <tr>
                            <td><a href="?id=${ms.fromid}">${ms.fromname}</a></td>
                            <td>${ms.content}</td>
                            <td>${ms.time}</td>

                        </tr>
                    </c:forEach>

                    </tbody>
                </table>
            </c:if>
            <c:if test="${adminExample==null}">
                暂无消息！
            </c:if>
        </div>
    <div id="menu3" class="container tab-pane fade"><br>

        类别:
        <label><input type="radio" name="type" value="1">技术</label>
        <label><input type="radio" name="type" value="2">情感</label>
        <h5>版块名：</h5>
        <p><input type="text" id="ftitle"/></p>
        <h5>介绍：</h5>
        <p><textarea name="fcontent" id="fcontent" cols="30" rows="10"></textarea>"</p>
        <button id="apply">申请</button>
    </div>
</div>
</div>

<jsp:include page="../footer.jsp"/>
<script>

    $(function () {

        $("#apply").click(function () {
            var loginid = document.getElementById("loginid").value;
            var type = $("input[name='type']:checked").val();
            var ftitle = document.getElementById("ftitle").value;
            var fcontent = document.getElementById("fcontent").value;
            $.ajax({
                type: "post",
                url: "/interflow/question/addForum",
                dataType: "text",
                data: {
                    "loginid": loginid,
                    "type": type,
                    "ftitle": ftitle,
                    "fcontent": fcontent
                },
                success: function (Result) {
                    var dataObj = JSON.parse(Result);
                    if (dataObj.Status) {
                        alert("提交成功，等待管理员审核!");
                        location.reload();
                    }
                    else {
                        alert(dataObj.Message);
                    }
                }

            });

        });

    });


</script>

</body>
</html>

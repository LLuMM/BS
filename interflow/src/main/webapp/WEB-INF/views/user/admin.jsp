<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <title>管理员中心</title>

    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/kindeditor/themes/default/default.css"/>
    <script charset="utf-8"
            src="${pageContext.request.contextPath}/resources/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/resources/kindeditor/lang/zh_CN.js"></script>

    <script>
        //简单模式初始化
        var editor;
        KindEditor.ready(function (K) {
            editor = K.create('textarea[name="content"]', {
                resizeType: 1,
                allowPreviewEmoticons: false,
                allowImageUpload: false,
                items: [
                    'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                    'insertunorderedlist', '|', 'emoticons', 'image', 'link']
            });
        });
    </script>


</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="container" style="margin-top: 80px">

    <br>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li class="nav-item">
            <a class="nav-link active" data-toggle="tab" href="#home">版主管理</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#menu1">消息中心</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#menu2">公告管理</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#menu2">。。。。</a>
        </li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <div id="home" class="container tab-pane active"><br>
            <h3>版主用户</h3>
            <c:if test="${adminExample!=null}">
                <c:forEach items="${adminExample.users}" var="us">
                    <p>${us.username}&nbsp;</p>

                </c:forEach>
            </c:if>
        </div>

        <div id="menu1" class="container tab-pane fade"><br>
            <h3>最新消息</h3>
            <c:if test="${adminExample!=null}">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>来自</th>
                        <th>内容</th>
                        <th>时间</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${adminExample.msgs}" var="ms">

                        <tr>
                            <td>${ms.fromname}</td>
                            <td>${ms.content}</td>
                            <td>${ms.time}</td>
                            <td>
                                <button  value="${ms.fid}" onclick="btnClick2(this)">批准</button>
                                <button value="${ms.fid}" onclick="btnClick1(this)">禁止</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${adminExample==null}">
                暂无消息！
            </c:if>
        </div>

        <div id="menu2" class="container tab-pane fade"><br>
            <h5>发布公告</h5>
            <textarea id="notice" name="content" style="width:700px;height:200px;"></textarea>
            <button id="commit">发布</button>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>
<script>

    function btnClick2(event){
        var id = event.value;
        $.ajax({
            type: "post",
            url: "/interflow/question/setForumStatus",
            dataType: "text",
            data: {
                "id":id,
                "status":1
            },
            success: function (Result) {
                var dataObj = JSON.parse(Result);
                if (dataObj.Status) {
                    alert("审批成功!");
                    location.reload();
                } else {
                    $("#error").text("网络异常！");
                }
            }

        });
    }
    function btnClick1(event){
        var id = event.value;
        $.ajax({
            type: "post",
            url: "/interflow/question/setForumStatus",
            dataType: "text",
            data: {
                "id":id,
                "status":2
            },
            success: function (Result) {
                var dataObj = JSON.parse(Result);
                if (dataObj.Status) {
                    alert("审批成功!");
                    location.reload();
                } else {
                    $("#error").text("网络异常！");
                }
            }

        });
    }

    $(function () {

        $("#commit").click(function () {

            editor.sync();
            var notic = $("#notice").val();
            $.ajax({
                type: "post",
                url: "/interflow/msg/addNotic",
                dataType: "text",
                data: {
                    "notic": notic
                },
                success: function (Result) {
                    var dataObj = JSON.parse(Result);
                    if (dataObj.Status) {
                        alert("发布成功!");
                        location.reload();
                    } else {
                        $("#error").text("网络异常！");
                    }
                }

            });

        });
        });



    }


</script>

</body>
</html>

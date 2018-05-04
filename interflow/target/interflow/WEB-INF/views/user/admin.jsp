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


    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/kindeditor/themes/default/default.css"/>
    <script charset="utf-8"
            src="${pageContext.request.contextPath}/resources/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/resources/kindeditor/lang/zh_CN.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/resources/myjs/ktCreater.js"></script>
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
                        <th>标题</th>
                        <th>内容</th>
                        <th>时间</th>
                        <th><a href="#" class="btn btn-info" role="button">隐藏已读消息</a>
                            <button id="deleteMsgByReadStatus" class="btn btn-success">删除所有已读消息</button>
                        </th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${adminExample.msgs}" var="ms">
                        <tr>
                            <td>${ms.fromname}</td>
                            <td>${ms.title}</td>
                            <td>${ms.content}</td>
                            <td>${ms.time}</td>
                            <td>
                                <c:if test="${ms.readstatus!=1}">
                                    <button value="${ms.fid}" class="btn btn-success" onclick="btnClick2(this)">批准
                                    </button>
                                    <button value="${ms.fid}" class="btn btn-warning" onclick="btnClick1(this)">禁止
                                    </button>
                                    <span style="color: red">未处理消息</span>
                                </c:if>
                                <c:if test="${ms.readstatus==1}">
                                    <button value="${ms.fid}" class="btn btn-secondary" disabled
                                            onclick="btnClick2(this)">批准
                                    </button>
                                    <button value="${ms.fid}" class="btn btn-secondary" disabled
                                            onclick="btnClick1(this)">禁止
                                    </button>
                                    <button value="${ms.id}" class="btn btn-success" onclick="deleteMsgById(this)">
                                        删除消息
                                    </button>
                                </c:if>
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
            <c:if test="${adminExample.notics!=null}">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>内容</th>
                        <th>时间</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${adminExample.notics}" var="ns">
                        <tr>
                            <td>${ns.content}</td>
                            <td>${ns.time}</td>
                            <td>
                                <button value="${ns.id}" onclick="deleteNotice(this)">删除</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <h5>发布公告</h5>
            <textarea id="notice" name="content" style="width:700px;height:200px;"></textarea>
            <button id="commit">发布</button>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>
<script>

    function btnClick2(event) {
        var id = event.value;
        $.ajax({
            type: "post",
            url: "/question/setForumStatus",
            dataType: "text",
            data: {
                "id": id,
                "status": 1
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

    function btnClick1(event) {
        var id = event.value;
        $.ajax({
            type: "post",
            url: "/question/setForumStatus",
            dataType: "text",
            data: {
                "id": id,
                "status": 2
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
                url: "/msg/addNotic",
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

        $("#deleteMsgByReadStatus").click(function () {
            alert("sdsadas");
            var id = document.getElementById("loginid").value;
            if ("" != id) {
                $.ajax({
                    type: "post",
                    url: "/msg/deleteByStatus",
                    dataType: "text",
                    data: {
                        "id": id
                    },
                    success: function (Result) {
                        var dataObj = JSON.parse(Result);
                        if (dataObj.Status) {
                            alert("删除成功!");
                            location.reload();
                        } else {
                            $("#error").text("网络异常！");
                        }
                    }

                });
            }

        });


    });

    function deleteNotice(event) {
        var id = event.value;
        $.ajax({
            type: "post",
            url: "/msg/deleteNotice",
            dataType: "text",
            data: {
                "id": id
            },
            success: function (Result) {
                var dataObj = JSON.parse(Result);
                if (dataObj.Status) {
                    alert("删除成功!");
                    location.reload();
                } else {
                    $("#error").text("网络异常！");
                }
            }

        });
    }

    function deleteMsgById(event) {
        var id = event.value;
        $.ajax({
            type: "post",
            url: "/msg/deleteMsgById",
            dataType: "text",
            data: {
                "id": id
            },
            success: function (Result) {
                var dataObj = JSON.parse(Result);
                if (dataObj.Status) {
                    alert("删除成功!");
                    location.reload();
                } else {
                    $("#error").text("网络异常！");
                }
            }

        });
    }
</script>

</body>
</html>

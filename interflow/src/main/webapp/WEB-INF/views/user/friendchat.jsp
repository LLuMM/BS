<%--
  Created by IntelliJ IDEA.
  User: LM
  Date: 2018/5/7
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/kindeditor/themes/default/default.css"/>
    <script charset="utf-8"
            src="${pageContext.request.contextPath}/resources/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/resources/kindeditor/lang/zh_CN.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/resources/myjs/ktCreater.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/chat/js/sockjs.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/chat/css/style.css" type="text/css" media="all" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/chat/css/chat.css" type="text/css" media="all" />
    <script>
        function chat(event) {
            var toid = event.value;
            var fromid = document.getElementById("loginid").value;
            $.ajax({
                type: "post",
                url: "/msg/chatMessage",
                dataType: "text",
                data: {
                    "fromid":fromid,
                    "toid": toid
                },
                success: function (Result) {
                    var dataObj = JSON.parse(Result);
                    if (dataObj.Status) {
                        alert("邀请成功!");
                        $("#chatlist").append("<li><a href='/chat/tochat'>&nbsp;&nbsp;&nbsp;群聊一</a></li>");
                    } else {
                        $("#error").text("网络异常！");
                    }
                }

            });
        }
    </script>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container" style="margin-top: 90px">
    <div class="row">
        <div class="col-sm-8">
        </div>
        <div class="col-sm-4">
            <h2>好友列表</h2>
            <div id="accordion">
                <div class="card">
                    <div class="card-header">
                        <a class="card-link" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                            我的好友
                        </a>
                    </div>
                    <div id="collapseOne" class="collapse">
                        <div class="card-block">
                            <div class="list-group">
                                <c:if test="${users!=null&&users.size()>0}">
                                    <c:forEach items="${users}" var="user">
                                        <div class="list-group-item list-group-item-action" style="height: 60px">
                                            <div class="container">
                                                <div class="row">
                                                    <div class="col-sm-2">
                                                        <c:if test="${user.face!=null}">
                                                            <img src="${user.face}" style="width: 30px;height: 30px"/>
                                                        </c:if>

                                                        <c:if test="${user.face==null}">
                                                            <img src="../img/jishu.jpg"
                                                                 style="width: 30px;height: 30px"/>
                                                        </c:if>
                                                    </div>
                                                    <div class="col-sm-8">
                                                        <a href="/user/userinfo?uid=${user.uid}&who=">${user.username}</a>
                                                        <span style="font-size:10px"><c:if test="${user.status==0}">离线</c:if></span>
                                                        <span style="font-size:10px"><c:if test="${user.status==1}">在线</c:if></span>
                                                        <br>
                                                        <span style="font-size:10px">${user.sign}</span>
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <button class="badge badge-pill badge-primary">聊天</button><br>
                                                        <button id="qun" class="badge badge-pill badge-primary" value="${user.uid}" onclick="chat(this)">群聊</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>


                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header">
                        <a class="collapsed card-link" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseTwo">
                            群聊列表
                        </a>
                    </div>
                    <div id="collapseTwo" class="collapse">
                        <div class="card-block">
                            <ul id="chatlist">
                            </ul>

                        </div>
                    </div>
                </div>
                <div class="card">
                    <div class="card-header">
                        <a class="collapsed card-link" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseThree">
                            Collapsible Group Item #3
                        </a>
                    </div>
                    <div id="collapseThree" class="collapse">
                        <div class="card-block">
                            Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt
                            ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                            laboris nisi ut aliquip ex ea commodo consequat.
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </div>
</div>

<jsp:include page="../footer.jsp"/>

</body>

</html>

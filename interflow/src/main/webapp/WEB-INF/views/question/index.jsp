<%--
  Created by IntelliJ IDEA.
  User: LM
  Date: 2018/4/21
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>热点新闻</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container"  style="margin-top: 100px">

    <div class="row">
        <div class="col-sm-2">
            <button id="upquestion" type="button" class="btn btn-primary" style="margin-left: 5px">发表新帖</button>
        </div>
        <div class="col-sm-6"></div>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="col-sm-8">
            <c:if test="${forumExample.questions != null}">
                <c:forEach items="${forumExample.questions}" var="question">
                    <div class="card bg-light text-dark " style="margin-top: 5px">
                        <div class="card-body">
                            <div class="container">
                                <div class="row">
                                    <div class="col-sm-2">
                                        <a class="nav-link" href="/user/userinfo?uid=${question.userid}&who=">
                                           <img src="${question.frompic}" style="width: 50px;height: 50px"/>
                                        </a>
                                    </div>
                                    <div class="col-sm-10" style="margin-top: 15px">
                                        <span style="color: #9fcdff">${question.uname}</span>&nbsp;&nbsp;
                                        <a href="/question/detail?id=${question.id}">
                                                ${question.title}
                                        </a><br>
                                        <span> ${question.time}</span>&nbsp;&nbsp;
                                        <span>浏览量：${question.hits}</span>&nbsp;&nbsp;
                                        <c:if test="${question.stick == 1}">
                                        <span style="margin-left: 10px" class="badge badge-pill badge-primary" >置顶</span>
                                        </c:if>
                                        <c:if test="${question.status == 1}">
                                        <span class="badge badge-pill badge-danger" >置精</span>
                                        </c:if>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${forumExample.questions.size()==0}">
                <p>暂无数据！</p>
            </c:if>
        </div>
        <div class="col-sm-4">
            <div class="card bg-light text-dark" style="margin-top: 5px">
                <%-- <div class="card-body">Light card</div>--%>
                sdadasfaf
            </div>

        </div>
    </div>

</div>

<jsp:include page="../footer.jsp"/>
</body>

<script>
    $(function () {

        $("#upquestion").click(function () {
            var loginid = document.getElementById("loginid").value;
            if ("" == loginid) {
                alert("请先登录！");
            } else {
                window.location.href = "/question/add?id=${forumExample.forum.fid}";
            }

        });

    });
   /* function setTop(event){
        var id = event.value;
        $.ajax({
            type: "post",
            url: "/question/setTop",
            dataType: "text",
            data: {
                "id":id,
                "status":1
            },
            success: function (Result) {
                var dataObj = JSON.parse(Result);
                if (dataObj.Status) {
                    alert("设置成功!");
                    location.reload();
                } else {
                    $("#error").text("网络异常！");
                }
            }

        });
    }
    function setFine(event){
        var id = event.value;
        $.ajax({
            type: "post",
            url: "/question/setFine",
            dataType: "text",
            data: {
                "id":id,
                "status":1
            },
            success: function (Result) {
                var dataObj = JSON.parse(Result);
                if (dataObj.Status) {
                    alert("设置成功!");
                    location.reload();
                } else {
                    $("#error").text("网络异常！");
                }
            }

        });
    }*/

</script>
</html>

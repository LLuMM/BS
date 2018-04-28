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
<div class="container"  style="margin-top: 80px">

    <div class="row">
        <div class="col-sm-2">
            <button id="upquestion" type="button" class="btn btn-primary" style="margin-left: 5px">发表新帖</button>
        </div>
        <div class="col-sm-6"></div>
        <form class="form-inline col-sm-4">
            <input type="text" class="form-control" id="keyword" placeholder="输入关键字">
            <button type="submit" class="btn btn-primary" style="margin-left: 10px">搜索</button>
        </form>

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
                                    <div class="col-sm-1">${question.uname}</div>
                                    <div class="col-sm-11">
                                        <a href="/interflow/question/detail?id=${question.id}"
                                        >${question.title}</a><br>
                                        <span>${question.time}</span>&nbsp;&nbsp;
                                        <span>浏览量：${question.hits}</span>
                                        <button style="margin-left: 10px" class="badge badge-pill badge-primary">置顶</button>
                                        <button class="badge badge-pill badge-danger" >置精</button>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${forumExample.questions == null}">
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
                window.location.href = "/interflow/question/add?id=${forumExample.forum.fid}";
            }

        });

    });


</script>
</html>

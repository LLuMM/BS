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
    <title>${forumExample.forum.title}</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container" style="margin-top: 100px">

    <div class="row">
        <div class="col-sm-2">
            <button id="upquestion" type="button" class="btn btn-primary" style="margin-left: 5px">发表新文</button>
        </div>

        <div class="col-sm-10">
            <mark style="font-size: 20px"> ${forumExample.forum.content}</mark>
            <mark style="font-size: 20px"> 版主<a href="/user/userinfo?uid=${forumExample.user.uid}&who=">${forumExample.user.username}</a></mark>
        </div>
    </div>
</div>
<div class="container" style="margin-top: 5px">
    <div class="row">
        <div class="col-sm-8">
            <c:if test="${forumExample.questions != null}">
                <c:forEach items="${forumExample.questions}" var="question">
                    <div class="card bg-light text-dark " style="margin-top: 5px">
                        <div class="card-body">
                            <div class="container">
                                <div class="row">
                                    <div class="col-sm-2">
                                        <a class="nav-link" href="/user/touserinfo?uid=${question.userid}">
                                            <c:if test="${question.frompic!=null&&question.frompic!=''}">
                                                <img src="${question.frompic}" style="width: 50px;height: 50px"/>
                                            </c:if>

                                            <c:if test="${question.frompic==null}">
                                                <img src="../img/user.jpg" style="width: 50px;height: 50px"/>
                                            </c:if>
                                        </a>
                                    </div>
                                    <div class="col-sm-10" style="margin-top: 15px">
                                        <span style="color: #9fcdff">${question.uname}</span>&nbsp;&nbsp;
                                        <a href="/question/detail?id=${question.id}">
                                                ${question.title}
                                        </a><c:if test="${question.stick == 1}">
                                            <span style="margin-left: 10px"
                                                  class="badge badge-pill badge-primary">置顶</span>
                                    </c:if>
                                        <c:if test="${question.status == 1}">
                                            <span class="badge badge-pill badge-danger">精帖</span>
                                        </c:if>
                                        <br>
                                        <span style="font-size: 8px"> ${question.time}&nbsp;&nbsp;
                                       <img src="../img/liulan.jpg"
                                            style="width: 20px;height: 20px"/>&nbsp;&nbsp;${question.hits}</span>&nbsp;&nbsp;

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
<script>
    $(function () {

        $("#upquestion").click(function () {
            $.ajax({
                url: "/question/add",
                type: "get",
                async: false,
                success : function(Result) {  //当请求之后调用。传入返回后的数据，以及包含成功代码的字符串。
                    window.location.href = "/question/add?id=${forumExample.forum.fid}";
                },
                error : function(Result) {
                    if (Result.status==600){
                        alert("您还没登陆，请登录！");
                    }
                }

            });
        });
    });
</script>

</body>

</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: LM
  Date: 2018/4/29
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${userExample.user.username}的主页</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container">
    <div class="card" style="margin-top: 80px">
        <div class="card-header">
            <div class="container">
                <center>
                    <img class="rounded-circle" src="${userExample.user.face}" alt="Card image"
                         style="width: 80px">
                    <br>
                    <h3>${userExample.user.username}</h3><h5><c:if test="${userExample.user.privilege==1}">(版主)</c:if></h5>

                    签名：


                </center>
            </div>
        </div>
    </div>
</div>
</div>
<div class="container" style="margin-top: 30px">
    <div class="container">
        <div class="row">
            <div class="col-sm-8">
                <div class="card">
                    <div class="card-header">最近发表</div>
                    <div class="card-body">
                        <c:if test="${userExample.questions!=null}">
                            <c:forEach items="${userExample.questions}" var="question">

                                <div class="card bg-light text-dark " style="margin-top: 5px">
                                    <div class="card-body">
                                        <div class="container">
                                            <a href="/question/detail?id=${question.id}">
                                                    ${question.title}
                                            </a><br>
                                            <span>${question.time}</span>&nbsp;&nbsp;
                                            <span>浏览量：${question.hits}</span>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                        <c:if test="${userExample.questions.size()==0}">
                            暂未发表什么！
                        </c:if>
                    </div>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="card">
                    <div class="card-header">
                        主页访问量：${userExample.user.visits}<br>
                        加入时间：${userExample.user.joindate}<br>
                        城市：
                    </div>
                </div>
            </div>

        </div>

    </div>
    <div class="container" style="margin-top: 20px">
        <div class="row">
            <div class="col-sm-8">
                <div class="card">
                    <div class="card-header">最近回答</div>
                    <div class="card-body">
                        <c:if test="${userExample.answers!=null}">

                            <c:forEach items="${userExample.answers}" var="answer">
                                <div class="card bg-light text-dark " style="margin-top: 5px">
                                    <div class="card-body">
                                        <div class="container">
                                            <c:if test="${answer.questionTitle!=null}">
                                                <a href="/question/detail?id=${answer.qid}">
                                                        ${answer.questionTitle}
                                                </a><br>
                                            </c:if>

                                            回复 <a href="/user/userinfo?uid=${answer.toid}&who=">
                                                ${answer.answerTo}
                                        </a><br>
                                                ${answer.content}<br>

                                            <span>${answer.time}</span>&nbsp;&nbsp;

                                        </div>
                                    </div>
                                </div>

                            </c:forEach>
                        </c:if>
                        <c:if test="${userExample.answers.size()==0}">
                            暂无数据！
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>

</body>
</html>

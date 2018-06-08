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
    <title>情感</title>

</head>

<body>
<jsp:include page="../header.jsp"/>
<div class="container" style="margin-top: 100px;">
    <c:if test="${questionExample != null&&questionExample.forums.size()>0}">
        <%--版块列表--%>
    <a href="#demo" class="btn btn-primary" data-toggle="collapse">版块列表</a>
    <div id="demo" class="collapse" style="margin-top: 6px">
        <div class="row">
            <c:forEach items="${questionExample.forums}" var="forum">
                <a class="btn btn-outline-info" style="margin-left: 6px;margin-top: 5px" href="/question/index?fid=${forum.fid}" data-toggle="tooltip" data-placement="top" title="${forum.content}">
                       ${forum.title}
                </a>
            </c:forEach>
        </div>
    </div>
</div>
</c:if>

</div>
<div class="container" style="margin-top: 15px;height:800px ">
    <div class="row">
        <div class="col-sm-8">
            <c:if test='${questionExample.questions!= null}'>
                <c:forEach items='${questionExample.questions}' var="ns">
                    <div class="card bg-light text-dark " style="margin-top: 5px">
                        <div class="card-body">
                            <div class="container">
                                <div class="row">
                                    <div class="col-sm-2">
                                        <a class="nav-link" href="/user/touserinfo?uid=${ns.userid}">
                                        <c:if test="${ns.frompic!=null}">
                                            <img src="${ns.frompic}" style="width: 60px;height: 60px"/>
                                        </c:if>

                                        <c:if test="${ns.frompic==null}">
                                            <img src="../img/user.jpg" style="width: 60px;height: 60px"/>
                                        </c:if>
                                        </a>
                                    </div>
                                    <div class="col-sm-10">
                                        <p><a href="/question/detail?id=${ns.id}">${ns.title}</a>&nbsp;<c:if test="${ns.stick==1}"><span style="margin-left: 10px" class="badge badge-pill badge-primary">置顶
                                        </span></c:if><c:if test="${ns.status==1}"><span style="margin-left: 10px" class="badge badge-pill badge-danger">精帖
                                        </span></c:if></p>
                                        <span style="font-size: 8px">${ns.uname}&nbsp;&nbsp;${ns.time}</span>&nbsp;&nbsp;<img src="../img/liulan.jpg" style="width: 20px;height: 20px"/>&nbsp;&nbsp;${ns.hits}
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test='${questionExample.questions== null}'>
                暂无数据！
            </c:if>
        </div>
        <div class="col-sm-4">
            <div class="card bg-light text-dark" style="margin-top: 5px">
                <div class="card-body">当前热门</div>
            </div>
        </div>
    </div>
    <%--<div class="row" style="margin-top: 10px">
        <div class="mx-auto" style="width:300px">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="#">上一页</a></li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item active"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item"><a class="page-link" href="#">下一页</a></li>
            </ul>
        </div>
    </div>--%>
</div>
<jsp:include page="../footer.jsp"/>
</body>
<script>
    $(function () {

        $("#butt").click(function () {
            var loginid = document.getElementById("loginid").value;
            if ("" == loginid) {
                alert("请先登录！");
            } else {
                window.location.href = "/question/add";
            }
        });
    });
</script>
</html>

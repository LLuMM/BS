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
<div class="container" style="margin-top: 100px;">
    <c:if test="${newsExample != null&&newsExample.forums.size()>0}">
        <%--版块列表--%>
    <a href="#demo" class="btn btn-primary" data-toggle="collapse">版块列表</a>
    <div id="demo" class="collapse" style="margin-top: 6px">
        <div class="row">
            <c:forEach items="${newsExample.forums}" var="forum">
                <a class="btn btn-outline-info" style="margin-left: 6px;margin-top: 5px" href="/question/index?fid=${forum.fid}">
                       ${forum.title}
                </a>
            </c:forEach>
        </div>
    </div>
</div>
</c:if>

</div>
<div class="container" style="margin-top: 15px;height: 1150px">
    <div class="row">
        <div class="col-sm-8">
            <c:if test='${newsExample.news.get("new")!= null}'>
                <c:forEach items='${newsExample.news.get("new")}' var="ns">
                    <div class="card bg-light text-dark " style="margin-top: 5px">
                        <div class="card-body">
                            <div class="container">
                                <div class="row">
                                    <div class="col-sm-2">
                                        <c:if test="${ns.imgurl!=null}">
                                            <img src="${ns.imgurl}" style="width: 60px;height: 60px"/>
                                        </c:if>

                                        <c:if test="${ns.imgurl==null}">
                                            <img src="../img/jishu.jpg" style="width: 60px;height: 60px"/>
                                        </c:if>
                                    </div>
                                    <div class="col-sm-10">
                                        <p><a href="/new/detail?n_id=${ns.n_id}">${ns.title}</a></p>
                                        <span style="font-size: 8px">${ns.source}&nbsp;&nbsp;${ns.date}</span>&nbsp;&nbsp;<img src="../img/liulan.jpg" style="width: 20px;height: 20px"/>&nbsp;&nbsp;${ns.readnum}
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test='${newsExample.news.get("new")== null}'>
                暂无数据！
            </c:if>
        </div>
        <div class="col-sm-4">
            <div class="card bg-light text-dark" style="margin-top: 5px">
                <div class="card-body">当前热门</div>
            </div>
        </div>
    </div>
    <div class="row" style="margin-top: 10px">
        <div class="mx-auto" style="width:300px">
            <ul class="pagination">
                <li class="page-item"><a class="page-link" href="#">上一页</a></li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item active"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item"><a class="page-link" href="#">下一页</a></li>
            </ul>
        </div>
    </div>
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

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
<c:if test="${newsExample != null}">
<%--版块列表--%>
    <div class="row">
    <c:forEach items="${newsExample.forums}" var="forum" >
        <span class="badge badge-primary" ><a href="/question/index?fid=${forum.fid}" style="color: white" >${forum.title}</a> <br> ${forum.content}</span>

    </c:forEach>
    </div>
</c:if>

</div>
<div class="container" style="margin-top: 15px">
    <div class="row">
        <div class="col-sm-8">

            <c:if test='${newsExample.news.get("new")!= null}'>

                <c:forEach items='${newsExample.news.get("new")}' var="ns" >

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
                                         <a href="/new/detail?n_id=${ns.n_id}">${ns.title}</a><br>
                                        <span style="font-size: 8px">${ns.source}&nbsp;&nbsp;</span><span>${ns.date}</span>
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

</div>

<jsp:include page="../footer.jsp"/>
</body>

<script>
    $(function () {

        $("#butt").click(function () {
            var loginid = document.getElementById("loginid").value;
            if ("" == loginid){
                alert("请先登录！");
            } else {
                window.location.href = "/question/add";
            }
        });

    });


</script>
</html>

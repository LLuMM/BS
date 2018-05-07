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
    <title>搜索列表</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container"  style="margin-top: 100px">
    <div class="row">
        <div class="col-sm-8">
            <c:if test='${searchItemExample!= null&&searchItemExample.searchItems.size()>0}'>
                <c:forEach items='${searchItemExample.searchItems}' var="ns" >
                    <div class="card bg-light text-dark " style="margin-top: 5px">
                        <div class="card-body">
                            <div class="container">
                                <div class="row">
                                    <div class="col-sm-2">
                                        <c:if test="${ns.img_url!=null}">
                                            <img src="${ns.img_url}" style="width: 60px;height: 60px"/>
                                        </c:if>
                                        <c:if test="${ns.img_url==null}">
                                            <img src="../img/jishu.jpg" style="width: 60px;height: 60px"/>
                                        </c:if>
                                    </div>
                                    <div class="col-sm-10">
                                        <c:if test="${ns.news_url!=null}">
                                        <a href="/new/detail?n_id=${ns.id}">${ns.title}</a><br>
                                        <span style="font-size: 8px">${ns.source}&nbsp;&nbsp;</span><span>${ns.date}</span>
                                        </c:if>
                                        <c:if test="${ns.news_url==null}">
                                            <a href="/question/detail?id=${ns.id}">${ns.title}</a><br>
                                            <span style="font-size: 8px">${ns.source}&nbsp;&nbsp;</span><span>${ns.date}</span>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test='${searchItemExample== null||searchItemExample.searchItems.size()==0}'>
                没有相关数据！
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

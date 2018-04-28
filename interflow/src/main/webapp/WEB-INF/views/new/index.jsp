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
<c:if test="${newsExample != null}">
<%--版块列表--%>
    <div class="row">
    <c:forEach items="${newsExample.forums}" var="forum" >
        <span class="badge badge-primary" style="width: 100px;height: 20px;margin-left: 20px"><a href="/interflow/question/index?fid=${forum.fid}" style="color: white" >${forum.title}</a></span>
    </c:forEach>
    </div>
</c:if>

</div>
<div class="container" style="margin-top: 15px">
    <div class="row">
        <div class="col-sm-8">

            <c:if test="${newsExample != null}">


                <c:forEach items="${newsExample.news}" var="ns" >

                    <div class="card bg-light text-dark " style="margin-top: 5px">
                        <div class="card-body">
                            <div class="container">
                                <div class="row">
                                    <div class="col-sm-1">
                                        <c:if test="${ns.pic!=null}">
                                            <img src="#"/>
                                        </c:if>
                                    </div>
                                    <div class="col-sm-11">
                                         <a href="/interflow/new/detail?n_id=${ns.n_id}"
                                                  >${ns.title}</a><br>
                                        <span>${ns.date}</span>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </c:forEach>
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
                window.location.href = "/interflow/question/add";
            }
           /* $.ajax({
                type: "get",
                url: "/interflow/user/check",
                success: function (Result) {
                    var dataObj = JSON.parse(Result);
                    if (dataObj.Status) {
                        alert("跳转了");
                        window.location.href = "/interflow/question/add";
                    }
                    else {
                        alert("亲，请先登录！");
                    }
                }

            });
*/
        });

    });


</script>
</html>

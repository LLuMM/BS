<%--
  Created by IntelliJ IDEA.
  User: LM
  Date: 2018/4/21
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>热点新闻</title>

</head>

<body>
<jsp:include page="../header.jsp"/>
<div class="container">

    <div class="row">
        <div class="col-sm-6"></div>
        <form class="form-inline col-sm-4">

            <input type="text" class="form-control" id="keyword" placeholder="Enter email">
            <button type="submit" class="btn btn-primary">搜索</button>
        </form>
        <div class="col-sm-2">
            <button id="butt" type="button" class="btn btn-primary" style="margin-left: 5px" >发表新帖</button>
        </div>
    </div>
</div>
<div class="container">


</div>
<div class="container">
    <div class="row">
        <div class="col-sm-8">
            <div class="card bg-light text-dark " style="margin-top: 5px">
                <div class="card-body">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-1">
                                <img src="#"/>
                            </div>
                            <div class="col-sm-11">
                                <span>sdafdasfsa</span><br>
                                <span>123243253</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card bg-light text-dark" style="margin-top: 5px">
                <div class="card-body">Light card</div>
            </div>
            <div class="card bg-light text-dark" style="margin-top: 5px">
                <div class="card-body">Light card</div>
            </div>
            <div class="card bg-light text-dark" style="margin-top: 5px">
                <div class="card-body">Light card</div>
            </div>
            <div class="card bg-light text-dark" style="margin-top: 5px">
                <div class="card-body">Light card</div>
            </div>
            <div class="card bg-light text-dark" style="margin-top: 5px">
                <div class="card-body">Light card</div>
            </div>


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

<script >
    $(function () {

        $("#butt").click(function () {

            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            $.ajax({
                type: "get",
                url: "/interflow/user/check",
                success: function (Result) {
                    var dataObj = JSON.parse(Result);
                    if (dataObj.Status) {
                        alert("跳转了");
                        window.location.href="/interflow/question/add";
                    }
                    else {
                        alert("亲，请先登录！");
                    }
                }

            });

        });

    });


</script>
</html>

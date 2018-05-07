<%--
  Created by IntelliJ IDEA.
  User: LM
  Date: 2018/4/24
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/kindeditor/themes/default/default.css"/>
    <script charset="utf-8"
            src="${pageContext.request.contextPath}/resources/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/resources/kindeditor/lang/zh_CN.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/resources/myjs/ktCreater.js"></script>
    <style>
        .xline {
            border-bottom: solid 1px wheat;
            height: 5px;
        }

    </style>
</head>
<body>
<jsp:include page="../header.jsp"/>
<div class="container" style="margin-top: 90px">
            <div class="card-body">
                <c:if test="${nsNewsExample.news!=null}">
                <iframe src="${nsNewsExample.news.url}" scrolling="auto" width="100%" height="100%"></iframe>
                </c:if>
            </div>
</div>


<c:if test="${nsNewsExample.answers.size()>0}">
    <div class="container">
        <c:forEach var="answer" items="${nsNewsExample.answers}">
            <span><a href="/user/userinfo?uid=${answer.uid}&who=">${answer.username}</a></span><br>
            <span>${answer.time}</span><br>
            <c:if test="${answer.answerTo!=null}">
                <span>@<a href="/user/userinfo?uid=${answer.answerTo}">${answer.answerTo}</a></span>
            </c:if>
            <span>${answer.content}</span>&nbsp;<br>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#reply"
                    data-whatever="${answer.uid}" data-name="${answer.username}" style="color: white">回复
            </button>
            <div class="xline col-sm-9"></div>
        </c:forEach>
    </div>
</c:if>
<div class="container">
    <div class="row">
        <div class="card col-sm-9">
            <textarea id="mul_input" name="content" style="width:700px;height:200px;"></textarea>
            <c:if test="${user !=null&&user !=''}">
                <input type="hidden" id="uid" value="${user.uid}"/>
                <input type="hidden" id="name" value="${user.username}"/>
                <input type="hidden" id="nsid" value="${nsNewsExample.nsNews.nsid}"/>
            </c:if>
            <button type="button" id="subutton">立即发表</button>
        </div>
        <div class="col-sm-3">
        </div>

    </div>
</div>

<jsp:include page="../footer.jsp"/>
<!-- 模态框（Modal） -->
<div class="modal fade" id="reply" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">
                    回复
                </h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>

            </div>
            <div class="modal-body">
                <input type="hidden" id="aid" value=""/>
                <textarea id="repyltext" name="repylcontent"></textarea>

            </div>
            <div class="modal-footer">

                <button type="button" id="sreplybut" class="btn btn-primary">
                    提交
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>
<script>
    $(function () {
        /*直接发表看法*/
        var id = document.getElementById("loginid").value;
        $("#subutton").click(function () {
            if ("" == id) {
                alert("亲，请登录！");
            }
            else {
                editor.sync();
                var content = $("#mul_input").val();
                if ("" == content) {
                    alert("您还没有添加内容呢^-^...");
                } else {
                    var username = document.getElementById("name").value;
                    var uid = document.getElementById("uid").value;
                    var nsid = document.getElementById("nsid").value;
                    $.ajax({
                        type: "post",
                        url: "/new/addAnswer",
                        dataType: "text",
                        data: {
                            "uid": uid,
                            "nsid": nsid,
                            "content": content,
                            "username": username
                        },
                        success: function (Result) {
                            var dataObj = JSON.parse(Result);
                            if (dataObj.Status) {
                                alert("发表成功!");
                                window.location.replace("/new/detail?n_id=" + nsid);
                            } else {
                                alert("网络异常！")
                            }
                        }
                    });
                }
            }

        });

        /*对评论的回复*/
        $("#sreplybut").click(function () {
            var id = document.getElementById("loginid").value;
            if ("" == id) {
                alert("亲，请登录！");
                $('#reply').modal('hide');
            } else {
                var toid = document.getElementById("aid").value;
                var qid = document.getElementById("newsid").value;
                var fromid = document.getElementById("uid").value;
                editor.sync();
                var content = $("#repyltext").val();
                if ("" == content) {
                    alert("您还没有添加内容呢^-^...");
                } else {
                    $.ajax({
                        type: "post",
                        url: "/answer/addAnswerToUser",
                        dataType: "text",
                        data: {
                            "qid": qid,
                            "toid": toid,
                            "fromid": fromid,
                            "content": content,
                            "type": 0
                        },
                        success: function (Result) {
                            var dataObj = JSON.parse(Result);
                            if (dataObj.Status) {
                                alert("回复成功!");
                                location.reload();
                            } else {
                                alert(dataObj.Message);
                            }
                        }
                    });
                }
            }
        });

    });

    //绑定模态框展示的方法
    $('#reply').on('show.bs.modal', function (event) {
        KindEditor.ready(function (K) {
            editor = K.create('textarea[name="repylcontent"]', {
                resizeType: 2,
                items: [
                    'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                    'insertunorderedlist', '|', 'emoticons', 'image', 'link'],
                width: "100%", height: "200px"
            });
        });
        var button = $(event.relatedTarget) // 触发事件的按钮
        var recipient = button.data('whatever') // 解析出whatever内容
        var name = button.data('name') // 解析出whatever内容
        var modal = $(this)  //获得模态框本身
        modal.find('.modal-title').text('回复 -> ' + name)  //
        //更改将title的text
        modal.find('.modal-body input').val(recipient);


    });


</script>


</body>


</html>

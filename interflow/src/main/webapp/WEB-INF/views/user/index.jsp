<%--
  Created by IntelliJ IDEA.
  User: LM
  Date: 2018/4/19
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>用户中心-${user.username}</title>

</head>
<body>
<jsp:include page="../header.jsp"/>

<div class="container" style="margin-top: 80px">

    <br>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li class="nav-item">
            <a class="nav-link" href="/user/userinfo?uid=${user.uid}&who=1">个人主页</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/user/friendchat?uid=${user.uid}&who=1">好友中心</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" data-toggle="tab" href="#home">基本设置</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#menu1">头像上传</a>
        </li>

        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#menu2">用户中心</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#menu3">我的消息</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#menu4">我的帖子</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#menu5">版主申请</a>
        </li>

    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <div id="home" class="container tab-pane active"><br>

            <p><input type="file"/></p>
            <h5>用户名</h5>
            <c:if test="${user!=null}">
                <form id="form1">
                <p><input type="text" id="showname" name="showname" value="${user.username}"
                          placeholder="输入显示名"></p>
                <h5>密码</h5>
                <p><input type="password" id="password" name="password" value="${user.password}"
                          placeholder="密码长度6-16位字符，由数字、字母组成"></p>

                <h5>用户名邮箱:</h5>
                <p><input type="email" id="email" name="email" value="${user.email}"
                          placeholder="用于取回密码,请填写正确的常用邮箱"></p>
                <h5>签名:</h5>
                <p><input type="text" id="sign" name="sign" value="${user.sign}"></p>
                <h5>城市:</h5>
                <p><input type="text" id="city" name="city" value="${user.city}"
                ></p>
                <button type="button" onclick="updateUser()" class="btn btn-outline-primary">修改</button>
                </p>
                </form>
            </c:if>
        </div>
        <div id="menu1" class="container tab-pane fade"><br>

            <form id="uploadForm" name="uploadForm" action="/user/upimg" method="post" enctype="multipart/form-data">
                <img id="image" src=""  style="width: 100px;height: 100px;" hidden/>
                <br/>
                <input type="hidden" value="${userindex.user.uid}" name="uid">
                <input type="file" name="uploadFile" onchange="selectImage(this);"/>
                <br><br>
                <input type="submit" id="doSave" value="提交" class="btn btn-outline-primary"/>
            </form>
        </div>

        <div id="menu2" class="container tab-pane fade"><br>

            <c:if test="${userindex.user.privilege!=1}">
                <p>您还不是版主，暂时没有信息！</p>
            </c:if>
            <c:if test="${userindex.user.privilege == 1}">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>用户</th>
                        <th>标题</th>
                        <th>时间</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:if test="${userindex.forumListMap.size()>0}">
                        <c:forEach items="${userindex.forumListMap}" var="fm">
                            <c:forEach items="${fm.value}" var="question">
                                <tr>
                                    <td>${question.uname}</td>
                                    <td><a href="/question/detail?id=${question.id}">${question.title}</a></td>
                                    <td>${ms.time}</td>
                                    <td>
                                        <c:if test="${question.status!=2}">

                                            <c:if test="${question.stick!=1}">
                                                <button style="margin-left: 10px" class="badge badge-pill badge-primary"
                                                        value="${question.id}" onclick="setTop(this)">置顶
                                                </button>
                                            </c:if>

                                            <c:if test="${question.stick==1}">
                                                <button style="margin-left: 10px"
                                                        class="badge badge-pill  badge-secondary" disabled
                                                        value="${question.id}" onclick="setTop(this)">置顶
                                                </button>
                                            </c:if>
                                            <c:if test="${question.status==1}">
                                                <button style="margin-left: 10px"
                                                        class="badge badge-pill  badge-secondary" disabled
                                                        value="${question.id}" onclick="setTop(this)">置精
                                                </button>
                                            </c:if>
                                            <c:if test="${question.status!=1}">
                                                <button class="badge badge-pill badge-danger" value="${question.id}"
                                                        onclick="setQustionStatus(this,1)">置精
                                                </button>
                                            </c:if>
                                            <button class="badge badge-pill badge-warning" value="${question.id}"
                                                    onclick="setQustionStatus(this,2)">禁用
                                            </button>
                                        </c:if>
                                        <c:if test="${question.status==2}">
                                            <button style="margin-left: 10px" class="badge badge-pill badge-secondary "
                                                    disabled
                                                    value="${question.id}" onclick="setTop(this)">置顶
                                            </button>
                                            <button class="badge badge-pill badge-secondary " disabled
                                                    value="${question.id}"
                                                    onclick="setQustionStatus(this,1)">置精
                                            </button>

                                            <button class="badge badge-pill badge-secondary " disabled
                                                    value="${question.id}"
                                                    onclick="setQustionStatus(this,2)">禁用
                                            </button>
                                        </c:if>
                                    </td>
                                </tr>

                            </c:forEach>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </c:if>
        </div>
        <div id="menu3" class="container tab-pane fade"><br>
            <h3>最新消息</h3>
            <c:if test="${userindex.msgs.size()>0}">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>来自</th>
                        <th>标题</th>
                        <th>内容</th>
                        <th>时间</th>
                        <th>类型</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userindex.msgs}" var="ms">
                        <tr>

                            <td><a href="/user/userinfo?uid=${ms.fromid}&who=">${ms.fromname}</a></td>
                            <td>${ms.title}</td>
                            <td>${ms.content}</td>
                            <td>${ms.time}</td>
                            <td>
                                <c:if test="${ms.type == 0}">普通回复</c:if>
                                <c:if test="${ms.type == 1}">

                                    好友申请&nbsp;&nbsp;
                                    <c:if test="${ms.readstatus == 0}">
                                        <button type="button" class="btn btn-primary" id="agree" value="${ms.fromid}"
                                                onclick="friendRequest(this,1)">同意
                                        </button>
                                        <button type="button" class="btn btn-warning" id="refuse" value="${ms.fromid}"
                                                onclick="friendRequest(this,2)">拒绝
                                        </button>
                                    </c:if>
                                </c:if>
                                <c:if test="${ms.type == 2}">
                                    聊天邀请
                                    <c:if test="${ms.readstatus == 0}">
                                        <a href="/chat/friendchat?id=${ms.id}" target="_blank" class="btn btn-primary"
                                           id="agree" value="${ms.fromid}">进入聊天
                                        </a>
                                        <button type="button" class="btn btn-warning" id="refuse" value="${ms.fromid}"
                                                onclick="friendRequest(this,2)">拒绝回复
                                        </button>
                                    </c:if>
                                </c:if>

                                <c:if test="${ms.type == 3}">管理员回复</c:if>
                            </td>
                            <td>
                                <c:if test="${ms.readstatus!=1}">
                                    <button value="${ms.id}" class="btn btn-success"
                                            onclick="setMsgStatus(this)">标记已读
                                    </button>
                                    <button value="${ms.id}" class="btn btn-success" onclick="deleteMsgById(this)">
                                        删除消息
                                    </button>
                                </c:if>
                                <c:if test="${ms.readstatus==1}">
                                    <button value="${ms.id}" class="btn btn-success" onclick="deleteMsgById(this)">
                                        删除消息
                                    </button>
                                </c:if>

                            </td>
                        </tr>

                    </c:forEach>

                    </tbody>
                </table>
            </c:if>
            <c:if test="${userindex.msgs.size()==0}">
                暂无消息！
            </c:if>
        </div>

        <div id="menu4" class="container tab-pane fade"><br>

            <c:if test="${userindex.questions.size()>0}">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>标题</th>
                        <th>时间</th>
                        <th>浏览量</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userindex.questions}" var="question">
                        <tr>
                            <td><a href="/question/detail?id=${question.id}">
                                    ${question.title}
                            </a></td>
                            <td>${question.time}</td>
                            <td>浏览量：${question.hits}</td>
                            <td>
                                <button value="${question.id}" onclick="btnClick2(this)">编辑</button>
                                <button value="${question.id}" onclick="deleteQuestion(this)">删除</button>
                            </td>

                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${userindex.questions.size()==0}">
                暂无文章，赶快去找喜欢的专区发文吧！
            </c:if>
        </div>

        <div id="menu5" class="container tab-pane fade"><br>
            类别:
            <label><input type="radio" name="type" value="1">技术</label>
            <label><input type="radio" name="type" value="2">情感</label>
            <h5>版块名：</h5>
            <p><input type="text" id="ftitle"/></p>
            <h5>介绍：</h5>
            <textarea name="fcontent" id="fcontent" cols="20" rows="10" placeholder="20字内的描述"></textarea><br>
            <button id="apply">申请</button>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp"/>
<script>


    $(function () {
        $("#apply").click(function () {
            var loginid = document.getElementById("loginid").value;
            var type = $("input[name='type']:checked").val();
            var ftitle = document.getElementById("ftitle").value;
            var fcontent = document.getElementById("fcontent").value;
            $.ajax({
                type: "post",
                url: "/question/addForum",
                dataType: "text",
                data: {
                    "loginid": loginid,
                    "type": type,
                    "ftitle": ftitle,
                    "fcontent": fcontent
                },
                success: function (Result) {
                    var dataObj = JSON.parse(Result);
                    if (dataObj.Status) {
                        alert("提交成功，等待管理员审核!");
                        location.reload();
                    }
                    else {
                        alert(dataObj.Message);
                    }
                }

            });

        });

    });


    function deleteQuestion(event) {
        var id = event.value;
        $.ajax({
            type: "post",
            url: "/question/delete",
            dataType: "text",
            data: {
                "id": id
            },
            success: function (Result) {
                var dataObj = JSON.parse(Result);
                if (dataObj.Status) {
                    alert("删除成功!");
                    location.reload();
                } else {
                    $("#error").text("网络异常！");
                }
            }

        });
    }

    function setTop(event) {
        var id = event.value;
        $.ajax({
            type: "post",
            url: "/question/setTop",
            dataType: "text",
            data: {
                "id": id,
                "status": 1
            },
            success: function (Result) {
                var dataObj = JSON.parse(Result);
                if (dataObj.Status) {
                    alert("设置成功!");
                    event.setAttribute("disabled", true);
                    event.setAttribute("class", "badge badge-pill badge-secondary");

                } else {
                    $("#error").text("网络异常！");
                }
            }

        });
    }

    function setQustionStatus(event, status) {

        var id = event.value;
        $.ajax({
            type: "post",
            url: "/question/setStatus",
            dataType: "text",
            data: {
                "id": id,
                "status": status
            },
            success: function (Result) {
                var dataObj = JSON.parse(Result);
                if (dataObj.Status) {
                    alert("设置成功!");
                    event.setAttribute("disabled", true);
                    event.setAttribute("class", "badge badge-pill badge-secondary");
                } else {
                    alert("网络异常！");
                }
            }

        });
    }

    function deleteMsgById(event) {
        var id = event.value;
        $.ajax({
            type: "post",
            url: "/msg/deleteMsgById",
            dataType: "text",
            data: {
                "id": id
            },
            success: function (Result) {
                var dataObj = JSON.parse(Result);
                if (dataObj.Status) {
                    alert("删除成功!");
                    location.reload();
                } else {
                    $("#error").text("网络异常！");
                }
            }

        });
    }

    function setMsgStatus(event) {
        var id = event.value;
        $.ajax({
            type: "post",
            url: "/msg/setMsgStatus",
            dataType: "text",
            data: {
                "id": id
            },
            success: function (Result) {
                var dataObj = JSON.parse(Result);
                if (dataObj.Status) {
                    alert("设置成功!");
                    location.reload();
                } else {
                    $("#error").text("网络异常！");
                }
            }

        });
    }

    /*图片上传
    * */
    var image = '';

    function selectImage(file) {
        if (!file.files || !file.files[0]) {
            return;
        }
        var reader = new FileReader();
        reader.onload = function (evt) {
            document.getElementById('image').src = evt.target.result;
            document.getElementById('image').removeAttribute("hidden");
            image = evt.target.result;

        }
        reader.readAsDataURL(file.files[0]);
    }

    function friendRequest(event, status) {

        var toid = event.value;
        var fromid = document.getElementById("loginid").value;
        $.ajax({
            type: "post",
            url: "/user/friendRequest",
            dataType: "text",
            data: {
                "fromid": fromid,
                "toid": toid,
                "status": status
            },
            success: function (Result) {
                var dataObj = JSON.parse(Result);
                if (dataObj.Status) {
                    event.setAttribute("disabled", true);
                    event.setAttribute("class", "btn btn-secondary");

                } else {
                    alert("网络异常！");
                }
            }

        });
    }

    function updateUser() {
        $.ajax({
            //几个参数需要注意一下
            type: "POST",//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: "/user/updateUser",//url
            data: $('#form1').serialize(),
            success: function (result) {
                console.log(result);//打印服务端返回的数据(调试用)
                if (result.resultCode == 200) {
                    alert("SUCCESS");
                }
            },
            error : function() {
                alert("异常！");
            }
        });
    }


</script>

</body>
</html>

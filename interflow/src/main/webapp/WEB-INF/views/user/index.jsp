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

<div class="container" style="margin-top: 80px;height:500px">

    <br>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li class="nav-item">
            <a class="nav-link" href="/user/userinfo?uid=${user.uid}">个人主页</a>
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

        <c:if test="${userindex.user.privilege==1}">
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#menu2">文章、问答管理</a>
            </li>
        </c:if>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#menu3">我的消息</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#menu4">我的文章</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#menu6">我的问答</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" data-toggle="tab" href="#menu5">版块管理</a>
        </li>

    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <div id="home" class="container tab-pane active"><br>

            <h6>用户名</h6>
            <c:if test="${user!=null}">
                <form id="form1" onsubmit="return false" action="##" method="post">
                    <input type="hidden" id="uid" name="uid" value="${userindex.user.uid}"/>
                    <p><input type="text" id="username" name="username" value="${user.username}"
                              placeholder="输入显示名"></p>
                    <h6>密码</h6>
                    <p><input type="password" id="password" name="password" value="${user.password}"
                              placeholder="密码长度6-16位字符，由数字、字母组成"></p>

                    <h6>用户名邮箱:</h6>
                    <p><input type="email" id="email" name="email" value="${user.email}"
                              placeholder="用于取回密码,请填写正确的常用邮箱"></p>
                    <h6>签名:</h6>
                    <p><input type="text" id="sign" name="sign" value="${user.sign}"></p>
                    <h6>城市:</h6>
                    <p><input type="text" id="city" name="city" value="${user.city}"
                    ></p>
                    <button type="button" onclick="updateUser()" class="btn btn-outline-primary">修改</button>
                    </p>
                </form>
            </c:if>
        </div>
        <div id="menu1" class="container tab-pane fade"><br>

            <form id="uploadForm" name="uploadForm" action="/user/upimg" method="post" enctype="multipart/form-data">
                <img id="image" src="" style="width: 100px;height: 100px;" hidden/>
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
                        <th>所属版块</th>
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
                                    <td>${fm.key}</td>
                                    <td>${question.uname}</td>
                                    <td><a href="/question/detail?id=${question.id}">${question.title}</a></td>
                                    <td>${question.time}</td>
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

            <c:if test="${userindex.msgs.size()>0&&userindex.msgs!=null}">
                <h3>最新消息</h3>
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
                                        <button type="button" class="btn btn-primary" id="agree" value="${ms.id}"
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

            <c:if test="${userindex.questions.size()>0&&userindex.questions!=null}">
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
            <c:if test="${userindex.questions==null||userindex.questions.size()==0}">
                暂无文章，赶快去找喜欢的专区发文吧！
            </c:if>
        </div>

        <div id="menu5" class="container tab-pane fade"><br>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#applymodal">版块申请</button>
            <c:if test="${userindex.forums!=null&&userindex.forums.size()>0}">
                <table class="table table-hover">
                    <thead>
                    <th>版块名</th>
                    <th>简介</th>
                    <th>时间</th>
                    <th></th>
                    </thead>
                    <tbody>
                    <c:forEach items="${userindex.forums}" var="forum">
                        <tr>
                            <td><a href="/question/index?fid=${forum.fid}">${forum.title}</a></td>
                            <td>${forum.content}</td>
                            <td>${forum.time}</td>
                            <td><button id="deleteforum" type="button"
                            >删除</button><button id="update" type="button"
                            >编辑</button></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <%--模态框--%>
            <div class="modal fade" id="applymodal">
                <div class="modal-dialog">
                    <div class="modal-content">

                        <!-- 模态框头部 -->
                        <div class="modal-header">
                            <h4 class="modal-title">版块申请</h4>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>

                        <!-- 模态框主体 -->
                        <div class="modal-body">

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
            </div>


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
            dataType: "json",
            data: {
                "id": id
            },
            success: function (Result) {
                //var dataObj = JSON.parse(Result);
                if (Result.Status) {
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
            dataType: "json",
            data: {
                "id": id
            },
            success: function (Result) {
                //var dataObj = JSON.parse(Result);
                if (Result.Status) {
                    event.setAttribute("disabled", true);
                    event.setAttribute("class", "btn btn-secondary");
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
        var mid = event.value;
        var fromid = document.getElementById("loginid").value;
        $.ajax({
            type: "post",
            url: "/user/friendRequest",
            dataType: "json",
            data: {
                "fromid": fromid,
                "mid": mid,
                "status": status
            },
            success: function (Result) {
                //var dataObj = JSON.parse(Result);
                if (Result.Status) {
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
            data: $('#form1').serializeArray(),
            contentype: "application/x-www-form-urlencoded",
            success: function (Result) {

                if (Result.Status) {
                    alert(Result.Message);
                }
                else {
                    alert("异常！");
                }
            }
        });
    }



</script>

</body>
</html>

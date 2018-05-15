<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getServerName() + ":" + request.getServerPort() + path + "/";
    String baseUrlPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>chat</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/chat/js/sockjs.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap/js/jquery.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/chat/css/style.css" type="text/css"
          media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/chat/css/chat.css" type="text/css"
          media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/kindeditor/themes/default/default.css"/>
    <script charset="utf-8"
            src="${pageContext.request.contextPath}/resources/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/resources/kindeditor/lang/zh_CN.js"></script>
    <script type="text/javascript">


        //onload初始化
        $(function () {
            //发送消息
            $("#sendBtn").on("click", function () {
                sendMsg();
            });
            //给退出聊天绑定事件
            $("#exitBtn").on("click", function () {
                closeWebsocket();
                location.href = "/index";
            });

            //给输入框绑定事件
            $("#msg").on("keydown", function (event) {
                keySend(event);
            });
            //初始化时如果有消息，则滚动条到最下面：
            scrollToBottom();

        });


        var path = '<%=basePath%>';

        //发送人编号
        var uid = '${sessionScope.user.uid}';
        var fromname = '${sessionScope.user.username}';
        //接收人编号
        var to = "-1";

        // 创建一个Socket实例
        //参数为URL，ws表示WebSocket协议。onopen、onclose和onmessage方法把事件连接到Socket实例上。每个方法都提供了一个事件，以表示Socket的状态。
        var websocket;
        //不同浏览器的WebSocket对象类型不同
        //alert("ws://" + path + "/ws?uid="+uid);
        if ('WebSocket' in window) {
            websocket = new WebSocket("ws://" + path + "ws");
            console.log("=============WebSocket");
            //火狐
        } else if ('MozWebSocket' in window) {
            websocket = new MozWebSocket("ws://" + path + "ws");
            console.log("=============MozWebSocket");
        } else {
            websocket = new SockJS("http://" + path + "ws/sockjs");
            console.log("=============SockJS");
        }

        console.log("ws://" + path + "ws");
        //打开Socket,
        websocket.onopen = function (event) {
            console.log("WebSocket:已连接");
        }

        // 监听消息
        //onmessage事件提供了一个data属性，它可以包含消息的Body部分。消息的Body部分必须是一个字符串，可以进行序列化/反序列化操作，以便传递更多的数据。
        websocket.onmessage = function (event) {
            console.log('Client received a message', event);
            //var data=JSON.parse(event.data);
            var data = $.parseJSON(event.data);
            console.log("WebSocket:收到一条消息", data);

            //2种推送的消息
            //1.用户聊天信息：发送消息触发
            //2.系统消息：登录和退出触发
            //判断是否是欢迎消息（没用户编号的就是欢迎消息）
            if (data.fromid == undefined || data.fromid == null || data.fromid == "") {
                //===系统消息
                $("#contentUl").append("<li><b>" + data.time + "</b><em>系统消息：</em><span>" + data.content + "</span></li>");
                //刷新在线用户列表
                $("#chatOnline").html("在线用户(" + data.userList.length + ")人");
                $("#chatUserList").empty();
                $(data.userList).each(function () {
                    $("#chatUserList").append("<li>" + this.username + "</li>");
                });

            } else {
                //===普通消息
                //处理一下个人信息的显示：
                if (data.fromname == fromname) {
                    data.fromname = "我";
                    $("#contentUl").append('<li><span  style="display:block; float:right;"><em>' + data.fromname + '</em><span>' + data.content + '</span><b>' + data.time + '</b></span></li><br/>');
                } else {
                    $("#contentUl").append("<li><b>" + data.time + "</b><em>" + data.fromname + "</em>" + data.content + "</li><br/>");
                }
            }
            scrollToBottom();
        };
        // 监听WebSocket的关闭
        websocket.onclose = function (event) {
            $("#contentUl").append("<li><b>" + new Date().Format("yyyy-MM-dd hh:mm:ss") + "</b><em>系统消息：</em><span>连接已断开！</span></li>");
            scrollToBottom();
            console.log("WebSocket:已关闭：Client notified socket has closed", event);
        };

        //监听异常
        websocket.onerror = function (event) {
            $("#contentUl").append("<li><b>" + new Date().Format("yyyy-MM-dd hh:mm:ss") + "</b><em>系统消息：</em><span>连接异常，建议重新登录</span></li>");
            scrollToBottom();
            console.log("WebSocket:发生错误 ", event);
        };


        //使用ctrl+回车快捷键发送消息
        function keySend(e) {
            var theEvent = window.event || e;
            var code = theEvent.keyCode || theEvent.which;
            if (theEvent.ctrlKey && code == 13) {
                var msg = $("#msg");
                if (msg.innerHTML == "") {
                    msg.focus();
                    return false;
                }
                sendMsg();
            }
        }

        //发送消息
        function sendMsg() {
            //对象为空了
            if (websocket == undefined || websocket == null) {
                //alert('WebSocket connection not established, please connect.');
                alert('您的连接已经丢失，请退出聊天重新进入');
                return;
            }
            //获取用户要发送的消息内容
            editor.sync();
            var msg = $("#msg").val();

            if (msg == "") {
                return;
            } else {
                var data = {};
                data["fromid"] = uid;
                data["fromname"] = fromname;
                data["to"] = to;
                data["content"] = msg;
                //发送消息
                websocket.send(JSON.stringify(data));
                //发送完消息，清空输入框

                editor.sync('#msg');
//清空kindeditor内容
                KindEditor.html("#msg", "");
            }
        }

        //关闭Websocket连接
        function closeWebsocket() {
            if (websocket != null) {
                websocket.close();
                websocket = null;
            }

        }

        //div滚动条(scrollbar)保持在最底部
        function scrollToBottom() {
            //var div = document.getElementById('chatCon');
            var div = document.getElementById('up');
            div.scrollTop = div.scrollHeight;
        }

        //格式化日期
        Date.prototype.Format = function (fmt) { //author: meizz
            var o = {
                "M+": this.getMonth() + 1, //月份
                "d+": this.getDate(), //日
                "h+": this.getHours(), //小时
                "m+": this.getMinutes(), //分
                "s+": this.getSeconds(), //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds() //毫秒
            };
            if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            for (var k in o)
                if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            return fmt;
        }


    </script>
</head>

<body>
<!--顶部开始-->
<div class="top">
    <jsp:include page="../header.jsp"/>
</div>

<!--头部end-->
<!--聊天区域开始-->
<div class="chatArea" id="chatArea" style="margin-top: 60px">
    <div class="inChatArea">
        <div id="chatSidebar" class="chatSidebar">
            <h2 id="chatOnline">在线用户(0人)</h2>
            <ul id="chatUserList">
            </ul>
        </div>
        <div class="chatCon">
            <div class="up" id="up">
                <ul id="contentUl">
                </ul>
            </div>
            <div class="down">
                <textarea class="textInfo" id="msg" name="content" title="按ctrl+enter直接发送"></textarea>
                <button class="btn" id="sendBtn"></button>
            </div>
        </div>
        <%--<div class="ad">
            <iframe src="http://m.itheimacast.icoc.in/" width="315" height="635" scrolling="no" frameborder="no" />
        </div>--%>
    </div>
</div>
<!--聊天区域结束-->
<%--<jsp:include page="../footer.jsp"/>--%>
<script>
    var editor;
    KindEditor.ready(function (K) {
        editor = K.create('textarea[name="content"]', {

            items: ['emoticons']
        });
    });

</script>
</body>

</html>

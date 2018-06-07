<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>发表问题</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/kindeditor/themes/default/default.css"/>
    <script charset="utf-8"
            src="${pageContext.request.contextPath}/resources/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/resources/kindeditor/lang/zh_CN.js"></script>
    <script charset="utf-8" src="${pageContext.request.contextPath}/resources/myjs/ktCreater.js"></script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>

<div class="container" style="margin-top: 100px">

    <div class="col-sm-8">
        <form id="uploadForm">
            <%--<div class="container">
                当前版块位置：<a href="/question/index?fid=${forum.fid}">${forum.title}></a>
                <input type="hidden" id="fid" name="fid" value="${forum.fid}">

            </div>--%>
            <div class="container">
                <label>标题:</label>
                <div class="raw">
                    <input type="text" class="form-control" id="title" name="title" placeholder="" >
                    <input type="file" id="filecontent" name="filecontent" style=""/>
                </div>
            </div>
            <div class="container" style="margin-top: 10px">
                <textarea id="content" name="content" style="width:700px;height:200px;"></textarea>
            </div>
            <div class="container" style="margin-top: 10px">
                <button type="button" <%--id="sub"--%> class="btn btn-outline-primary" onclick="saveMsg()"> 立即发布
                </button>
            </div>
        </form>
    </div>
</div>

<jsp:include page="../footer.jsp"></jsp:include>
</body>
<script>
    $(function () {
        $("#sub").click(function () {
            var title = document.getElementById("title").value;
            editor.sync();//将KindEditor的数据同步到textarea标签。
            var value_content = $("#content").val();
            var loginid = document.getElementById("loginid").value;
            var fid = document.getElementById("forumid").value;
            $.ajax({
                type: "post",
                url: "/question/addQuestion",
                dataType: "text",
                data: {
                    "title": title,
                    "value_content": value_content,
                    "loginid": loginid,
                    "fid": fid
                },
                success: function (Result) {
                    var dataObj = JSON.parse(Result);
                    if (dataObj.Status) {
                        alert("发表成功");
                        //清空kindeditor内容
                        KindEditor.html("#content", "");
                        location.reload();
                    } else {
                        $("#error").text("网络异常！");
                    }
                }

            });
        });
    });


    /*$("#sub").click(function () {
          var title = document.getElementById("title").value;
          editor.sync();//将KindEditor的数据同步到textarea标签。
          var value_content = $("#content").val();
          var loginid = document.getElementById("loginid").value;
          var fid = document.getElementById("forumid").value;
          $.ajax({
              type: "post",
              url: "/question/addQuestion",
              dataType: "text",
              data: {
                  "title": title,
                  "value_content": value_content,
                  "loginid": loginid,
                  "fid": fid
              },
              success: function (Result) {
                  var dataObj = JSON.parse(Result);
                  if (dataObj.Status) {
                      alert("发表成功");
                      //清空kindeditor内容
                      KindEditor.html("#content","");
                      location.reload();
                  } else {
                      $("#error").text("网络异常！");
                  }
              }

          });
      });
  });
*/

    /* KindEditor.ready(function(K) {
         var  editor1 = K.create('textarea[name="content"]', {
             resizeType : 1,
             allowPreviewEmoticons : false,
             allowImageUpload : true, //打开本地上传图片功能
             items : [
                 'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'cut', 'copy', 'paste',
                 'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                 'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                 'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
                 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                 'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|',
                 'table', 'hr','image'],//image打开本地上传图片必须写,重要的事情说三遍
             afterBlur : function() {
                 this.sync();  //焦点问题，这里不写会出问题.同步KindEditor的值到textarea文本框
             }
         });
     });*/
    /*  var KE;
      KindEditor.ready(function(K) {

          KE = K.create('textarea[name="content"]',{
              uploadJson : '/question/upimg',
              fileManagerJson : 'kindeditor/asp/file_manager_json.asp',
             /!* allowImageUpload: true,*!/ //多图上传
              allowFileManager : true, //浏览图片空间
              filterMode : false, //HTML特殊代码过滤
              afterBlur: function(){ this.sync(); }, //编辑器失去焦点(blur)时执行的回调函数（将编辑器的HTML数据同步到textarea）
              afterUpload : function(url, data, name) { //上传文件后执行的回调函数，必须为3个参数
                  if(name=="image" || name=="multiimage"){ //单个和批量上传图片时
                      if(K("#pic").length>0){ //文本框存在
                          document.getElementById('piclist').options[document.getElementById('piclist').length]=new Option(url,url); //下拉列表框增加一条
                          document.getElementById('piclist').selectedIndex+=1; //选定刚新增的这一条
                          K("#indexpicimg").html("<img src='" + url + "' width='150' height='95' />"); //重置图片展示区DIV的HTML内容
                          K("#pic").val(url); //重置文本框值
                      }
                  }
              }
          });
      });*/
    function saveMsg() {
        var loginid = document.getElementById("loginid").value;
        editor.sync();//将KindEditor的数据同步到textarea标签。
        var formData = new FormData($("#uploadForm")[0]);
        formData.append("loginid", loginid);
        $.ajax({
            type: "post",
            data: formData,
            url: "/question/addQuestion",
            dataType: 'json',
            contentType: false, //必须
            processData: false, //必须
            success: function (Result) {
                alert("发表成功!");
                document.getElementById("title").val("")
                KindEditor.html("#content","");
            },
            error: function (error) {
                alert(error);
            }
        });
    }
</script>
</html>
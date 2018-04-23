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
    <script>
        //简单模式初始化
        var editor;
        KindEditor.ready(function (K) {
            editor = K.create('textarea[name="content"]', {
                resizeType: 1,
                allowPreviewEmoticons: false,
                allowImageUpload: false,
                items: [
                    'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                    'insertunorderedlist', '|', 'emoticons', 'image', 'link']
            });
        });
    </script>
</head>
<body>
<jsp:include page="../header.jsp"></jsp:include>
<form class="">
    <div class="container">

        <label>标题:</label>
        <input type="text" class="form-control" id="title" placeholder="">

    </div>
    <div class="container">
        <textarea id="mul_input" name="content" style="width:700px;height:200px;">KindEditor</textarea>

    </div>
    <div class="container">
    <button id="sub">立即发布</button>
    </div>
</form>
<jsp:include page="../footer.jsp"></jsp:include>
</body>
<script>
    $(function () {
        $("#sub").click(function () {
            editor.sync();//将KindEditor的数据同步到textarea标签。
            var value_content = $("#mul_input").val();

        });
    });

</script>
</html>
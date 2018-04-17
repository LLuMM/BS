
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>

    <title>test</title>
</head>

<body>
<form method="post" action="user/insert">
    Username:<input type="text" name="user.username"/>
    age:<input type="text" name="user.age"/>
    Password:<input type="password" name="user.password"/>
    sex<input type="text" name="user.sex"/>


    phone<input type="text" name="user.phone"/>
    Email<input type="text" name="user.email"/>
    <input type="submit" value="添加用户" />

</form>
</body>
</html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>

    <title>test</title>
</head>

<body>
<c:forEach items="${list}" var="item">
    <tr>
        <td>${item.id}</td>
        <td>${item.username}</td>
        <td>${item.sex}</td>
    </tr>

</c:forEach>
</body>
</html>
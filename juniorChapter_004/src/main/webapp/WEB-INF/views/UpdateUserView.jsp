<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
    <form method="post" action="${pageContext.servletContext.contextPath}/edit?id=<c:out value="${user.id}"></c:out>">
        <p> Id: <c:out value="${user.id}"></c:out></p>
        <p> Name: <input  name="name" value="<c:out value="${user.name}"></c:out>"></p>
        <p> Login: <input  name="login" value="<c:out value="${user.login}"></c:out>"></p>
        <p> Email: <input  name="email" value="<c:out value="${user.email}"></c:out>"></p>
        <p> Create date: <c:out value="${user.createDate}"></c:out></p>
        <p><input type="submit" value="Save"></p>
    </form>
</body>
</html>

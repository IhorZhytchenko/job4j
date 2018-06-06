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
        <p> Password: <input  name="password" value="<c:out value="${user.password}"></c:out>"></p>
        <p> Email: <input  name="email" value="<c:out value="${user.email}"></c:out>"></p>
        <c:if test="${sessionScope.user.role == 'admin'}">
        <p> Role: <select name="role">
                <option value="user">User</option>
                <option value="admin">Admin</option>
        </select></p>
        </c:if>
        <c:if test="${sessionScope.user.role == 'user'}">
            <p> Role: <input type="text" readonly  name="role" value="<c:out value="${user.role}"></c:out>"></p> </p>
        </c:if>


        <p> Create date: <c:out value="${user.createDate}"></c:out></p>
        <p><input type="submit" value="Save"></p>
    </form>
</body>
</html>

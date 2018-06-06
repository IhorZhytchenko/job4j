<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
    <form method="post" action="${pageContext.servletContext.contextPath}/create">
        <p> Name: <input  name="name"></p>
        <p> Login: <input  name="login"></p>
        <p> Password: <input  name="password"></p>
        <p> Email: <input  name="email"></p>
        <p> Role : <select name="role">
            <option value="user">User</option>
            <option value="admin">Admin</option>
        </select></p>
        <p><input type="submit" value="Save"></p>
    </form>
</body>
</html>

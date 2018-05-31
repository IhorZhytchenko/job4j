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
        <p> Email: <input  name="email"></p>
        <p><input type="submit" value="Save"></p>
    </form>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
</head>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red" >
        <c:out value="${error}"></c:out>
    </div>
</c:if>
<form method="post" action="${pageContext.servletContext.contextPath}/signin">
    <p> Login: <input  name="login"></p>
    <p> Password: <input type="password"  name="password"></p>
    <p><input type="submit" value="Sign In"></p>
</form>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container w3-yellow">
    <h3>Sign In</h3>
</div>
<c:if test="${error != ''}">
    <div class="w3-container w3-red" >
        <c:out value="${error}"></c:out>
    </div>
</c:if>
<div class="w3-container w3-green w3-padding">
    <form method="post" action="${pageContext.servletContext.contextPath}/signin">
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h5>Login:</h5>
            <input class="w3-input" type="text" name="login">
        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h5>Password:</h5>
            <input class="w3-input" type="password" name="password">
        </div>
        <div class="w3-container">
            <input type="submit" class="w3-button w3-yellow w3-right w3-large" value="Sign In"/>
        </div>
    </form>
</div>
</body>
</html>

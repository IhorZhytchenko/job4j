<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My account</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container w3-yellow">
    <a href="${pageContext.servletContext.contextPath}/logout" class="w3-margin-top w3-button w3-red w3-right">logout</a>
    <a href="${pageContext.servletContext.contextPath}/" class="w3-button w3-margin  w3-blue">Main</a>
    <h1>My account</h1>
</div>
<div class="w3-container w3-blue w3-padding">
    <form method="post" action="${pageContext.servletContext.contextPath}/users/edit?id=<c:out value="${user.id}"></c:out>" onsubmit="return validate();">
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>First Name:</h4>
            <h6><c:out value="${sessionScope.user.firstName}"></c:out></h6>
        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Last Name:</h4>
            <h6><c:out value="${sessionScope.user.lastName}"></c:out></h6>
        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Login:</h4>
            <h6><c:out value="${sessionScope.user.login}"></c:out></h6>
        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Password:</h4>
            <h6><c:out value="${sessionScope.user.password}"></c:out></h6>
        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Address:</h4>
            <h6><c:out value="${sessionScope.user.address.addr}"></c:out></h6>
        </div>

        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Role :</h4>
            <h6><c:out value="${sessionScope.user.role.roleName}"></c:out></h6>
        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Music Type :</h4>
            <ul class="w3-ul">
            <c:forEach items="${sessionScope.user.musicTypes}" var="music">
                <li><h6><c:out value="${music.type}"></c:out></h6></li>
            </c:forEach>
            </ul>
        </div>
        <div class="w3-container">
            <a class="w3-button w3-green" href="${pageContext.servletContext.contextPath}/users/edit?id=<c:out value="${sessionScope.user.id}"></c:out>">Edit</a>
        </div>
    </form>
</div>
</body>
</html>
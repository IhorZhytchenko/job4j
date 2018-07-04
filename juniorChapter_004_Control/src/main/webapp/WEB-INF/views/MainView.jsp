<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Main</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

</head>
<body>
<div class="w3-bar w3-light-grey">
    <a href="${pageContext.servletContext.contextPath}/users" class="w3-bar-item w3-button w3-yellow">Users</a>
    <a href="${pageContext.servletContext.contextPath}/musics" class="w3-bar-item w3-button w3-green">Music Types</a>
    <div class="w3-dropdown-hover">
        <button class="w3-button w3-blue">Search User</button>
        <div class="w3-dropdown-content w3-bar-block w3-card-4">
            <a href="${pageContext.servletContext.contextPath}/search?type=role" class="w3-bar-item w3-button">By Role</a>
            <a href="${pageContext.servletContext.contextPath}/search?type=music" class="w3-bar-item w3-button">By Music Type</a>
            <a href="${pageContext.servletContext.contextPath}/search?type=address" class="w3-bar-item w3-button">By Address</a>
        </div>
    </div>
    <a href="${pageContext.servletContext.contextPath}/users/current" class="w3-bar-item w3-button w3-purple w3-right">My account</a>
    <a href="${pageContext.servletContext.contextPath}/logout" class="w3-bar-item w3-button w3-red w3-right">logout</a>
</div>
</div>
</body>
</html>
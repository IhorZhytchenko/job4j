<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<a href="${pageContext.servletContext.contextPath}/logout" class="w3-margin-top w3-button w3-red w3-right">logout</a>
<a href="${pageContext.servletContext.contextPath}/" class="w3-margin-top w3-button w3-grey">Main</a>
<div class="w3-container w3-green w3-margin-bottom">
    <h1><c:out value="${message}" ></c:out></h1>
</div>

<div class="w3-container w3-padding">
    <table class="w3-table w3-striped w3-bordered">

        <c:forEach items="${users}" var="user">
            <tr>

                <td><c:out value="${user.firstName}" ></c:out>    <c:out value="${user.lastName}" ></c:out> (<c:out value="${user.login}" ></c:out>)</td>
                <td><a class="w3-button w3-green" href="${pageContext.servletContext.contextPath}/users/edit?id=<c:out value="${user.id}"></c:out>">Edit</a></td>
                <td><form method="post" action="${pageContext.servletContext.contextPath}/users?id=<c:out value="${user.id}"></c:out>">
                    <input type="submit" class="w3-button w3-red w3-large" value="Delete"/>
                </form></td>
            </tr>
        </c:forEach>

    </table>

    <a href="${pageContext.servletContext.contextPath}/users/add" class="w3-margin-top w3-button w3-green ">Add new User</a>
</div>
</body>
</html>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<a href="${pageContext.servletContext.contextPath}/exit" class="w3-margin-top w3-button w3-grey w3-right">exit</a>
<div class="w3-container w3-blue w3-margin-bottom">
    <h1>Users</h1>
</div>

    <div class="w3-container w3-padding ">
    <table class="w3-table w3-striped w3-bordered">
        <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"></c:out></td>
            <td><c:out value="${user.name}"></c:out></td>
            <td><a class="w3-button w3-yellow"  href="${pageContext.servletContext.contextPath}/edit?id=<c:out value="${user.id}"></c:out>">Edit</a></td>
            </td>
            <td><form method="post" action="${pageContext.servletContext.contextPath}/?id=<c:out value="${user.id}"></c:out>">
                <input type="submit" class="w3-button w3-red w3-large" value="Delete"/>
                </form>
            </td>

        </tr>
        </c:forEach>
    </table>
    </div>

<a href="${pageContext.servletContext.contextPath}/create" class="w3-margin-top w3-button w3-yellow ">Create User</a>

</body>
</html>

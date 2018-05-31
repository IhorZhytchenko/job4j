
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <table>
        <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"></c:out></td>
            <td><c:out value="${user.name}"></c:out></td>
            <td><a  href="${pageContext.servletContext.contextPath}/edit?id=<c:out value="${user.id}"></c:out>">Edit</a></td>
            </td>
            <td><form method="post" action="${pageContext.servletContext.contextPath}/?id=<c:out value="${user.id}"></c:out>">
                    <button type=\"submit\">Delete</button>
                </form>
            </td>

        </tr>
        </c:forEach>
    </table>


    <form action="${pageContext.servletContext.contextPath}/create">
        <button type="submit">Create User</button>
    </form>
</body>
</html>

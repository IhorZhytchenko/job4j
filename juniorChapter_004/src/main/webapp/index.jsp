<%@ page import="crudservlet.User" %>
<%@ page import="crudservlet.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <table>
        <%for (User user : ValidateService.getInstance().findAll()) {%>
        <tr>
            <td><%=user.getId()%></td>
            <td><%=user.getName()%></td>
            <<td><a  href="<%=request.getContextPath()%>/edit?id=<%=user.getId()%>">Edit</a></td>
            </td>
            <td><form method="post" action="<%=request.getContextPath()%>/list?id=<%=user.getId()%>">
                    <button type=\"submit\">Delete</button>
                </form>
            </td>

        </tr>
        <%}%>
    </table>


    <form action="<%=request.getContextPath()%>/create">
        <button type="submit">Create User</button>
    </form>
</body>
</html>

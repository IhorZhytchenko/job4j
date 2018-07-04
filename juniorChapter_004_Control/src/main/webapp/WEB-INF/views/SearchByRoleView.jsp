<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Search</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<a href="${pageContext.servletContext.contextPath}/logout" class="w3-margin-top w3-button w3-red w3-right">logout</a>
<a href="${pageContext.servletContext.contextPath}/" class="w3-margin-top w3-button w3-grey">Main</a>
<div class="w3-container w3-blue w3-padding">
    <h2>Search User by role</h2>
    <form  action="${pageContext.servletContext.contextPath}/search/get" >
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Role :</h4>
            <select style="display: inline-block !important;" class="w3-select w3-block" name="condition">
                <c:forEach items="${roles}" var="role">
                    <option value="<c:out value="${role.id}" ></c:out>"><c:out value="${role.roleName}" ></c:out></option>
                </c:forEach>
            </select>
        </div>
        <input type="hidden" name="type" value="role">
        <div class="w3-container">
            <input type="submit" class="w3-button w3-yellow w3-right w3-large" value="Search"/>
        </div>
    </form>
</div>


</body>
</html>

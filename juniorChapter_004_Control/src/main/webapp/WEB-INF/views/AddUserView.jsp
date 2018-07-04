<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
    <script>
        function validate() {
            var result = true;
            var name = document.getElementsByName("firstName")[0].value;
            if (name == '') {
                result = false;
            }
            var name = document.getElementsByName("lastName")[0].value;
            if (name == '') {
                result = false;
            }
            var name = document.getElementsByName("address")[0].value;
            if (name == '') {
                result = false;
            }
            var login = document.getElementsByName("login")[0].value;
            if (login == '') {
                result = false;
            }
            var password = document.getElementsByName("password")[0].value;
            if (password == '') {
                result = false;
            }
            if (!result) {
                alert('enter correct data')
            }
            return result;
        }
    </script>
</head>
<body>
<div class="w3-container w3-yellow">
    <a href="${pageContext.servletContext.contextPath}/" class="w3-button w3-margin  w3-blue">Main</a>
    <h1>Create user</h1>
</div>
<div class="w3-container w3-blue w3-padding">
    <form method="post" action="${pageContext.servletContext.contextPath}/users/add" onsubmit="return validate();">
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>First Name:</h4>
            <input class="w3-input" type="text" name="firstName" value="">
        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Last Name:</h4>
            <input class="w3-input" type="text" name="lastName" value="">
        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Login:</h4>
            <input class="w3-input" type="text" name="login" value="">
        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Password:</h4>
            <input class="w3-input" type="text" name="password" value="">
        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Address:</h4>
            <input class="w3-input" type="text" name="address" value="">
        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Role :</h4>
            <select style="display: inline-block !important;" class="w3-select w3-block" name="roleId">
                <c:forEach items="${roles}" var="role">
                    <c:if test="${role.roleName == 'USER'}">
                    <option value="<c:out value="${role.id}" ></c:out>"><c:out value="${role.roleName}" ></c:out></option>
                    </c:if>
                </c:forEach>
            </select>

        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Music Type :</h4>
                <c:forEach items="${musics}" var="music">
                    <input name="musics" value="<c:out value="${music.id}" ></c:out>" class="w3-check" type="checkbox" >
                    <label><c:out value="${music.type}" ></c:out></label>
                </c:forEach>
        </div>
        <div class="w3-container">
            <input type="submit" class="w3-button w3-yellow w3-right w3-large" value="Save"/>
        </div>
    </form>
</div>
</body>
</html>

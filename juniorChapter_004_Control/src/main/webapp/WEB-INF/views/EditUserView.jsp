<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
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
    <a href="${pageContext.servletContext.contextPath}/logout" class="w3-margin-top w3-button w3-red w3-right">logout</a>
    <a href="${pageContext.servletContext.contextPath}/" class="w3-button w3-margin  w3-blue">Main</a>
    <h1>Edit user</h1>
</div>
<div class="w3-container w3-blue w3-padding">
    <form method="post" action="${pageContext.servletContext.contextPath}/users/edit?id=<c:out value="${user.id}"></c:out>" onsubmit="return validate();">
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>First Name:</h4>
            <input class="w3-input" type="text" name="firstName" value="<c:out value="${user.firstName}"></c:out>">
        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Last Name:</h4>
            <input class="w3-input" type="text" name="lastName" value="<c:out value="${user.lastName}"></c:out>">
        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Login:</h4>
            <input class="w3-input" type="text" name="login" value="<c:out value="${user.login}"></c:out>">
        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Password:</h4>
            <input class="w3-input" type="text" name="password" value="<c:out value="${user.password}"></c:out>">
        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Address:</h4>
            <input class="w3-input" type="text" name="address" value="<c:out value="${user.address.addr}"></c:out>">
        </div>

        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Role :</h4>
            <c:if test="${sessionScope.user.role.roleName == 'ADMIN'}">
            <select style="display: inline-block !important;" class="w3-select w3-block" name="roleId">
                <c:forEach items="${roles}" var="role">
                    <option value="<c:out value="${role.id}" ></c:out>"><c:out value="${role.roleName}" ></c:out></option>
                </c:forEach>
            </select>
            </c:if>

            <c:if test="${sessionScope.user.role.roleName != 'ADMIN'}">
                <select style="display: inline-block !important;" class="w3-select w3-block" name="roleId">
                        <option value="<c:out value="${user.role.id}" ></c:out>"><c:out value="${user.role.roleName}" ></c:out></option>

                </select>
            </c:if>
        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Music Type :</h4>
            <c:forEach items="${musics}" var="music">

                <c:set var="contains" value="false" />

                <c:forEach var="userMusic" items="${user.musicTypes}">
                    <c:if test="${music.id == userMusic.id}">
                        <c:set var="contains" value="true" />
                    </c:if>
                </c:forEach>

                <c:if test="${contains}">
                    <input name="musics" value="<c:out value="${music.id}" ></c:out>" class="w3-check" type="checkbox" checked="checked" >
                    <label><c:out value="${music.type}" ></c:out></label>
                </c:if>

                <c:if test="${!contains}">
                    <input name="musics" value="<c:out value="${music.id}" ></c:out>" class="w3-check" type="checkbox"  >
                    <label><c:out value="${music.type}" ></c:out></label>
                </c:if>

            </c:forEach>
        </div>
        <div class="w3-container">
            <input type="submit" class="w3-button w3-yellow w3-right w3-large" value="Save"/>
        </div>
    </form>
</div>
</body>
</html>

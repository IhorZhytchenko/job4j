<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
    <script>
        function loadAddress(){
            $.ajax('./address', {
                    method: 'get',
                    complete : function (data) {
                        var addresses = JSON.parse(data.responseText);
                        var result = "";
                        for (var i=0;i<addresses.length;i++) {
                            result=result +"<option value=\""+addresses[i].id +"\">"+addresses[i].city+" ("+ addresses[i].country+")</option>";
                        }
                        var select = document.getElementById("addr");
                        select.innerHTML = result;
                    }

                }
            )
        }
        $(
            loadAddress()
        );

        function addAddress() {

            $.ajax({
                    type: "POST",
                    url : './address',
                    data: {city: document.getElementsByName("city")[0].value , country: document.getElementsByName("country")[0].value}
                }
            )
            loadAddress();
            return true;

        }

        function validate() {
            var result = true;
            var name = document.getElementsByName("name")[0].value;
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
    <a href="${pageContext.servletContext.contextPath}/" class="w3-button w3-margin w3-large w3-blue">All users</a>
    <h1>Update user</h1>
</div>
<div class="w3-container w3-blue w3-padding">
    <form method="post" action="${pageContext.servletContext.contextPath}/edit?id=<c:out value="${user.id}"></c:out>" onsubmit="return validate();">
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Id: <c:out value="${user.id}"></c:out></h4>
        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Name:</h4>
            <input class="w3-input" type="text" name="name" value="<c:out value="${user.name}"></c:out>">
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
            <h4>Email:</h4>
            <input class="w3-input" type="text" name="email" value="<c:out value="${user.email}"></c:out>">
        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Role:</h4>
            <c:if test="${sessionScope.user.role == 'admin'}">
                 <select style="display: inline-block !important;" class="w3-select w3-block" name="role">
                    <option value="user">User</option>
                    <option value="admin">Admin</option>
                </select>
            </c:if>
            <c:if test="${sessionScope.user.role == 'user'}">
                 <input class="w3-input" type="text" readonly  name="role" value="<c:out value="${user.role}"></c:out>">
            </c:if>
        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Address:</h4>
            <select id="addr" style="display: inline-block !important;" class="w3-select w3-block" name="addressId">

            </select>
            <div class="w3-container w3-grey w3-margin w3-padding" >
                <h5>Add Address</h5>
                City:
                <input  type="text" name="city" >
                Country:
                <input  type="text" name="country" >
                <input  type="button" class="w3-button w3-yellow" value="Add" onclick="return addAddress();" >
            </div>
        </div>
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Create date: <c:out value="${user.createDate}"></c:out></h4>
        </div>
        <div class="w3-container">
            <input type="submit" class="w3-button w3-yellow w3-right w3-large" value="Save"/>
        </div>
    </form>
</div>
</body>
</html>

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
<a href="${pageContext.servletContext.contextPath}/" class="w3-margin-top w3-button w3-grey ">Main</a>
<div class="w3-container w3-blue w3-padding">
    <h2>Search User by address</h2>
    <form  action="${pageContext.servletContext.contextPath}/search/get" >
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Address:</h4>
            <input class="w3-input" type="text" name="condition" value="">
        </div>
        <input type="hidden" name="type" value="address">
        <div class="w3-container">
            <input type="submit" class="w3-button w3-yellow w3-right w3-large" value="Search"/>
        </div>
    </form>
</div>


</body>
</html>

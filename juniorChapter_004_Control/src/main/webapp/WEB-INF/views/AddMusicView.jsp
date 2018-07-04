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
            var name = document.getElementsByName("type")[0].value;
            if (name == '') {
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
    <h1>Create Music Type</h1>
</div>
<div class="w3-container w3-blue w3-padding">
    <form method="post" action="${pageContext.servletContext.contextPath}/musics/add" onsubmit="return validate();">
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h4>Type:</h4>
            <input class="w3-input" type="text" name="type" value="">
        </div>

        <div class="w3-container">
            <input type="submit" class="w3-button w3-yellow w3-right w3-large" value="Save"/>
        </div>
    </form>
</div>
</body>
</html>

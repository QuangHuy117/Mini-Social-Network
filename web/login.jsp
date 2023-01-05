<%-- 
    Document   : login
    Created on : Feb 19, 2022, 10:12:20 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="./validate.js"></script>
        <link rel="stylesheet" href="Template/templateLogin.css">
    </head>
    <body>
        <div class="container" id="container">
            <div class="form-container sign-in-container">
                <form action="loginAction" method="POST">
                    <h2 style="font-weight: 600; color: #FFA500">Sign in</h2>
                    <input type="text" id="email" placeholder="Email" name="txtEmail" value="${param.txtEmail}"/>
                    <input type="password" id="password" placeholder="Password" name="txtPassword" value="${param.txtPassword}"/> <br/> 
                    <c:if test="${not empty requestScope.ERROR_STRING}">
                        <h5 style="color: #FF4B2B">${requestScope.ERROR_STRING}</h5>
                    </c:if>
                    <button style="background-color: #FFA500" >Sign In</button>

                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-right">
                        <h2 style="font-weight: 600;">Don't have an account?</h2>
                        <p>Sign Up and start journey with us</p>
                        <button class="ghost" id="signUp">Sign Up</button>
                    </div>
                </div>
            </div>
        </div>
        <script>
            const signUpButton = document.getElementById('signUp');
            signUpButton.addEventListener('click', () => {
                window.location.href = "register.html";
            });

        </script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    </body>
</html>

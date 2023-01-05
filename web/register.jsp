<%-- 
    Document   : register
    Created on : Feb 19, 2022, 10:09:15 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="./validate.js"></script>
        <link rel="stylesheet" href="Template/templateLogin.css">
    </head>
    <body>
        <div class="container" id="container">
            <div class="form-container sign-up-container">
                <form action="registerAction" method="POST">
                    <h2 style="font-weight: 600; color: #FFA500">Create Account</h2>
                    <!--                    <div class="social-container">
                                            <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                                            <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                                            <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                                        </div>-->
                    <!--<span>or use your email for registration</span>-->
                    <input type="text" placeholder="Email" name="txtReEmail" id="reEmail" value="${param.txtReEmail}"/>
                    <input type="text" placeholder="Name" name="txtReName" id="reName" value="${param.txtReName}"/>
                    <input type="password" placeholder="Password" name="txtPassword" id="rePass"/>
                    <input type="password" placeholder="Confirm Password" name="txtConPassword" id="reConPass"/> <br/> 
                    <c:if test="${not empty requestScope.ERROR_STRING}">
                        <h5 style="color: #FF4B2B">${requestScope.ERROR_STRING.emailIsExisted}</h5>
                    </c:if>
                    <button style="background-color: #FFA500" onclick="return validateForm()">Sign Up</button>

                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-right">
                        <h2 style="font-weight: 600;">Welcome Back!</h2>
                        <p>To keep connected with us please login with your personal info</p>
                        <button class="ghost" id="signIn">Sign In</button>
                    </div>
                </div>
            </div>
        </div>
        <script>
            const signInButton = document.getElementById('signIn');

            signInButton.addEventListener('click', () => {
                window.location.href = "login.html";
            });
        </script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    </body>
</html>

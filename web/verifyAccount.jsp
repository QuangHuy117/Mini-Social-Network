<%-- 
    Document   : verifyAccount
    Created on : Feb 19, 2022, 11:00:29 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify Account Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="Template/templateVerify.css">
    </head>
    <body>
        <div class="container" id="container" style="margin-top: 120px">
            <div class="mt-5">
                <ul>
                    <li>You will receive a verification code on your mail after you registered. Enter that code below.</li>
                    <li>If somehow, you did not receive the verification email then <a href="resendCodeAction">resend the verification email</a></li>
                </ul>
                <form action="verifyAction" method="POST">
                    <input class="form-control mr-sm-2" type="text" name="txtCode" placeholder="Enter the verification code"> <br/>
                    <c:if test="${not empty requestScope.ERROR_STRING}">
                        <h5 style="color: #FF4B2B">${requestScope.ERROR_STRING}</h5>
                    </c:if>
                    <button style="background-color: #FFA500" class="btn btn-info">Verify Now</button>
                </form>
            </div>
        </div>
    </body>
</html>

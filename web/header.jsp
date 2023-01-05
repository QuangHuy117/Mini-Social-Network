<%-- 
    Document   : header
    Created on : Feb 19, 2022, 10:21:42 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <style>
            .dropbtn {
                background-color: #FFA500;
                color: white;
                padding: 16px;
                font-size: 16px;
                border: none;
                cursor: pointer;
            }

            .dropdown {
                position: relative;
                display: inline-block;
            }
            .dropdown-content {
                display: none;
                position: absolute;
                right: 0;
                background-color: #FFFFFF;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown-content a:hover {
                background-color: #FFA500;
                color: white;
            }

            .dropdown:hover .dropdown-content {
                display: block;
            }
        </style>
    </head>
    <body>
        <c:set var="User" value="${sessionScope.USER}" />

        <nav class="navbar navbar-expand-md navbar-light shadow fixed-top" style="background-color: #FFA500;">
            <a class="navbar-brand" href="homePageAction" style="color: white; font-weight: 600">Mini Social Network</a>

            <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                <form action="searchAction" method="GET">
                    <input id="searchText" name="txtSearch" class="form-control mr-sm-2" type="text" placeholder="Search an Article" value="${param.txtSearch}">
                </form>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a style="color: white; font-weight: 600" class="nav-link" href="postArticleHTML">Post Article<span class="sr-only">(current)</span></a>
                        </li>
                    </ul>
                </div>
                <div class="row">
                    <span style="color: white; font-size: 16px; margin-top: 8px">Welcome,</span>
                    <c:if test="${not empty User}">
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link" data-toggle="dropdown">
                                <h5 style="color: white; font-size: 18px">${User.name}</h5>
                            </a>
                            <div class="dropdown-content">
                                <a href="userProfileAction" class="dropdown-item">Profile</a>
                                <a href="logoutAction" class="dropdown-item">Sign Out</a>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${empty User}">
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link">
                                <h5 style="color: white; font-size: 18px">User</h5>
                            </a>
                            <div class="dropdown-content">
                                <a href="login.html" class="dropdown-item">Sign In</a>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </nav>

        <!--        <script>
                    function keypressHandler(evt) {
                        var search = document.getElementById("searchText");
                        if (evt.charCode === 13) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                </script> -->

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    </body>
</html>

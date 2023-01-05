<%-- 
    Document   : userProfile
    Created on : Feb 28, 2022, 12:45:42 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/c0699e2207.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <c:if test="${empty sessionScope.USER}">
            <c:redirect url=""/>
        </c:if>

        <c:if test="${not empty sessionScope.USER}">

            <c:set var="listArticle" value="${requestScope.LIST_ARTICLE_USER}"/>

            <c:if test="${empty listArticle}">
                <div class="container-fluid" style="margin-top: 90px;">
                    <div class="col-md-12 d-flex justify-content-center">
                        <div class="alert alert-primary mt-3" role="alert">
                            No Article found!!!
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${not empty listArticle}">
                <div class="container-fluid mt-5 px-5">
                    <div class="row">
                        <div class="col-md-12 mt-5 px-5">
                            <div class="row">
                                <c:forEach var="articledto" items="${listArticle}" varStatus="counter">
                                    <div class="col-md-4 mb-2 px-4">
                                        <!--<section class="mx-auto my-5" style="max-width: 450px;">-->
                                        <div class="card shadow">
                                            <div class="card-body d-flex flex-row">
                                                <div class="col-12">
                                                    <!--<div style="background-color: #FFA500; width: max-width; height: 10px;"  ></div>-->
                                                    <h5 style="color: #FFA500;" class="card-title font-weight-bold mb-1">${articledto.title}</h5>
                                                    Posted by: <span style="display: inline; font-size: 18px">${articledto.username}</span>
                                                    <p class="card-text mt-1"><i class="far fa-clock pe-2"></i> <fmt:formatDate value="${articledto.createDate}" pattern="dd/MM/yyyy"/></p>
                                                </div>
                                            </div>
                                            <c:if test="${not empty articledto.image}">
                                                <div class="bg-image hover-overlay ripple rounded-0" data-mdb-ripple-color="light">
                                                    <img class="img-fluid" src="${articledto.image}"
                                                         alt="Card image cap" />
                                                    <a href="#">
                                                        <div class="mask" style="background-color: rgba(251, 251, 251, 0.15);"></div>
                                                    </a>
                                                </div>
                                            </c:if>
                                            <div class="card-body">
                                                <i style="color: #00BFFF; font-size: 18px" class="fas fa-thumbs-up" title="Like"></i>
                                                ${articledto.numOfLike} Like
                                                &nbsp;
                                                <i style="color: #FF6347; font-size: 18px" class="fas fa-thumbs-down" title="DisLike"></i>
                                                ${articledto.numOfDislike} DisLike
                                                <c:if test="${not empty sessionScope.USER}">
                                                    <div class="float-right">
                                                        <a href="viewDetailAction?txtArticleID=${articledto.articleID}">See more</a>
                                                    </div> <br/>
                                                </c:if>
                                                <c:if test="${empty sessionScope.USER}">
                                                    <div class="float-right">
                                                        <a href="loginPage">See more</a>
                                                    </div> <br/>
                                                </c:if>
                                                <hr>
                                                <div class="flex">
                                                    <p style="font-size: 16px; color: #404E67">${articledto.description}</p>
                                                </div>
                                            </div>
                                        </div>
                                        <!--</section>-->
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>

            </c:if>
        </c:if>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    </body>
</html>

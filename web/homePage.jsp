<%-- 
    Document   : homePage
    Created on : Feb 19, 2022, 11:24:39 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mini Social Network</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/c0699e2207.js" crossorigin="anonymous"></script>
        <style>
            .btn.btn-default {
                background-color: #FFA500;
                color: white;
            }
            /*            .page-item.active .page-link {
                            z-index: 1;
                            color: #fff !important;
                            background-color: #FFA500 !important;
                            border-color: #FFA500 !important;
                        }
                        .page-link {
                            color: #FFA500 !important;
                        }*/
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <c:if test="${empty sessionScope.USER}">
            <c:redirect url=""/>
        </c:if>

        <c:if test="${not empty sessionScope.USER}">

            <c:set var="listArticle" value="${requestScope.LIST_ARTICLE}"/>

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
                                                <div class="flex">
                                                    <p style="font-size: 16px; color: #404E67">${articledto.description}</p>
                                                </div>
                                                <hr>
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
                                            </div>
                                        </div>
                                        <!--</section>-->
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <br/>


                <nav aria-label="Page navigation example">
                    <ul class="pagination justify-content-center">
                        <c:if test="${param.txtCurPage == null || requestScope.END eq 1 || param.txtCurPage eq 1}">
                            <li class="page-item disabled">
                                <a class="page-link" href="#" tabindex="-1">Previous</a>
                            </li>
                        </c:if>
                        <c:if test="${param.txtCurPage gt 1}">
                            <li class="page-item">
                                <a class="page-link" href="homePageAction?txtCurPage=${param.txtCurPage - 1}" tabindex="-1">Previous</a>
                            </li>
                        </c:if>
                        <c:forEach begin="1" end="${requestScope.END}" var="i">
                            <c:if test="${param.txtCurPage eq i || (param.txtCurPage == null && i eq 1)}"> 
                                <li class="page-item active"><a class="page-link" href="homePageAction?txtCurPage=${i}">${i}</a></li>
                                </c:if>
                                <c:if test="${(param.txtCurPage ne i && param.txtCurPage != null) || (param.txtCurPage == null && i ne 1)}"> 
                                <li class="page-item"><a class="page-link" href="homePageAction?txtCurPage=${i}">${i}</a></li>
                                </c:if>
                            </c:forEach>
                            <c:if test="${requestScope.END eq 1 || param.txtCurPage eq requestScope.END}">
                            <li class="page-item disabled">
                                <a class="page-link" href="#">Next</a>
                            </li>
                        </c:if>
                        <c:if test="${param.txtCurPage lt requestScope.END || (param.txtCurPage == null && requestScope.END gt 1)}">
                            <c:if test="${param.txtCurPage == null}">
                                <li class="page-item">
                                    <a class="page-link" href="homePageAction?txtCurPage=${param.txtCurPage + 2}">Next</a>
                                </li>
                            </c:if>
                            <c:if test="${param.txtCurPage != null}">
                                <li class="page-item">
                                    <a class="page-link" href="homePageAction?txtCurPage=${param.txtCurPage + 1}">Next</a>
                                </li>
                            </c:if>
                        </c:if>
                    </ul>
                </nav>
            </c:if>
        </c:if>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    </body>
</html>

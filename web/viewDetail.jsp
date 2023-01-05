<%-- 
    Document   : viewDetail
    Created on : Feb 22, 2022, 10:51:37 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:if test="${not empty requestScope.ARTICLE_DETAIL}">
            <title>${requestScope.ARTICLE_DETAIL.title}</title>
        </c:if>
        <c:if test="${empty requestScope.ARTICLE_DETAIL}">
            <title>Error</title>
        </c:if>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/c0699e2207.js" crossorigin="anonymous"></script>
        <style>
            /*            @import url(http://fonts.googleapis.com/css?family=Calibri:400,300,700);
            
                        body {
                            background-color: #D32F2F;
                            font-family: 'Calibri', sans-serif !important
                        }*/

            .mt-100 {
                margin-top: 100px
            }

            .mb-100 {
                margin-bottom: 100px
            }

            .card {
                position: relative;
                display: -webkit-box;
                display: -ms-flexbox;
                display: flex;
                -webkit-box-orient: vertical;
                -webkit-box-direction: normal;
                -ms-flex-direction: column;
                flex-direction: column;
                min-width: 0;
                word-wrap: break-word;
                background-color: #fff;
                background-clip: border-box;
                border: 0px solid transparent;
                border-radius: 0px
            }

            .card-body {
                -webkit-box-flex: 1;
                -ms-flex: 1 1 auto;
                flex: 1 1 auto;
                padding: 1.25rem
            }

            .card .card-title {
                position: relative;
                font-weight: 600;
                margin-bottom: 10px
            }

            .comment-widgets {
                position: relative;
                margin-bottom: 10px
            }

            .comment-widgets .comment-row {
                border-bottom: 1px solid transparent;
                padding: 14px;
                display: -webkit-box;
                display: -ms-flexbox;
                display: flex;
                margin: 10px 0
            }

            .p-2 {
                padding: 0.5rem !important
            }

            .comment-text {
                padding-left: 15px
            }

            .w-100 {
                width: 100% !important
            }

            .m-b-15 {
                margin-bottom: 15px
            }

            .btn-sm {
                padding: 0.25rem 0.5rem;
                font-size: 0.76563rem;
                line-height: 1.5;
                border-radius: 1px
            }

            .btn-cyan {
                color: #fff;
                background-color: #27a9e3;
                border-color: #27a9e3
            }

            .btn-cyan:hover {
                color: #fff;
                background-color: #1a93ca;
                border-color: #198bbe
            }

            .comment-widgets .comment-row:hover {
                background: rgba(0, 0, 0, 0.05)
            }
        </style>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <c:if test="${empty sessionScope.USER}">
            <c:redirect url=""/>
        </c:if>

        <c:if test="${not empty sessionScope.USER}">
            <c:if test="${not empty requestScope.ARTICLE_DETAIL}">
                <div class="container-fluid pt-5 mt-5">
                    <div class="row">
                        <div class="col-12">
                            <div class="card shadow">
                                <div class="row">
                                    <c:if test="${not empty requestScope.ARTICLE_DETAIL.image}">
                                        <div class="col-6 pr-0">
                                            <div class="bg-image hover-overlay ripple rounded-0" data-mdb-ripple-color="light">
                                                <img class="img-fluid w-100" src="${requestScope.ARTICLE_DETAIL.image}"
                                                     alt="Card image cap" />
                                            </div>
                                        </div>
                                        <div class="col-6 mh-100 pl-0">
                                            <section class="mx-auto">
                                                <div class="card border-0">
                                                    <div class="card-body">
                                                        <div>
                                                            <div class="row">
                                                                <div class="col-10">
                                                                    <h5 style="color: #FFA500;" class="card-title font-weight-bold mb-1">${requestScope.ARTICLE_DETAIL.title}</h5>
                                                                </div>
                                                                <div class="col-2 text-right">
                                                                    <c:if test="${requestScope.ARTICLE_DETAIL.email.trim() eq sessionScope.USER.email.trim()}">
                                                                        <a style="color: #FFA500;" href="deleteArticleAction?txtArticleID=${requestScope.ARTICLE_DETAIL.articleID}" onclick="return deleteConfirm()">Delete</a>
                                                                    </c:if>
                                                                </div>
                                                            </div>
                                                            Posted by: <span style="display: inline; font-size: 18px">${requestScope.ARTICLE_DETAIL.username}</span>
                                                            <p class="card-text mt-1"><i class="far fa-clock pe-2"></i> <fmt:formatDate value="${requestScope.ARTICLE_DETAIL.createDate}" pattern="dd/MM/yyyy"/></p>
                                                        </div>
                                                    </div>
                                                    <div class="card-body pb-0">
                                                        <div class="flex">
                                                            <p style="font-size: 16px; color: #404E67">${requestScope.ARTICLE_DETAIL.description}</p>
                                                        </div>
                                                        <hr>
                                                        <div class="row">
                                                            <div class="col-6 pl-3">
                                                                <a style="text-decoration: none" href="makeEmotionsAction?txtStatus=Like&txtArticleID=${requestScope.ARTICLE_DETAIL.articleID}">
                                                                    <c:if test="${requestScope.LIKE_STATUS eq 'Like'}">
                                                                        <i style="color: #00BFFF; font-size: 18px" class="fas fa-thumbs-up" title="Like"></i>
                                                                    </c:if>
                                                                    <c:if test="${requestScope.LIKE_STATUS ne 'Like'}">
                                                                        <i style="color: #00BFFF; font-size: 18px" class="far fa-thumbs-up" title="Like"></i>
                                                                    </c:if>
                                                                </a>
                                                                ${requestScope.ARTICLE_DETAIL.numOfLike} Like

                                                                <a style="text-decoration: none" href="makeEmotionsAction?txtStatus=Dislike&txtArticleID=${requestScope.ARTICLE_DETAIL.articleID}">
                                                                    <c:if test="${requestScope.LIKE_STATUS eq 'Dislike'}">
                                                                        <i style="color: #FF6347; font-size: 18px" class="fas fa-thumbs-down" title="DisLike"></i>
                                                                    </c:if>
                                                                    <c:if test="${requestScope.LIKE_STATUS ne 'Dislike'}">
                                                                        <i style="color: #FF6347; font-size: 18px" class="far fa-thumbs-down" title="DisLike"></i>
                                                                    </c:if>
                                                                </a>
                                                                ${requestScope.ARTICLE_DETAIL.numOfDislike} Dislike
                                                            </div>
                                                            <div class="col-6" style="text-align: right;">
                                                                <span>${requestScope.NUMBER_COMMENT} Comments</span>
                                                            </div>
                                                        </div>
                                                        <hr>
                                                    </div>
                                                    <div class="card-body pt-0">
                                                        <section class="content-item" id="comments">
                                                            <div class="container px-0">  
                                                                <div class="col-12 pl-0 pb-2">   
                                                                    <form action="postCommentAction" method="POST">
                                                                        <div class="row">
                                                                            <div class="col-3 pr-0" style="margin-top: 15px;">
                                                                                <h5 class="pull-left">Post Comment</h5>
                                                                            </div>
                                                                            <div class="col-9 pl-0">
                                                                                <fieldset>
                                                                                    <textarea class="form-control" name="txtComment" id="message" placeholder="Your message" maxlength="250" rows="3" required=""></textarea>
                                                                                </fieldset>
                                                                                <input type="hidden" name="txtArticleID" value="${requestScope.ARTICLE_DETAIL.articleID}" />
                                                                                <button type="submit" class="btn btn-success pull-right mt-2 float-right">Submit</button>
                                                                            </div>
                                                                        </div>
                                                                    </form>
                                                                </div>
                                                                <div>
                                                                    <hr class="dotted" style="border-top: 3px dotted #bbb;">
                                                                </div>
                                                                <c:if test="${empty requestScope.LIST_CMT}">
                                                                    <span>Be the first one to comment</span>
                                                                </c:if>
                                                                <c:if test="${not empty requestScope.LIST_CMT}">
                                                                    <div class="col-lg-12 mt-3">
                                                                        <div class="comment-widgets">
                                                                            <div class="overflow-auto" style="height: 200px; overflow-y: scroll">
                                                                                <c:forEach var="cmtdto" items="${requestScope.LIST_CMT}" varStatus="counter">
                                                                                    <div class="d-flex flex-row comment-row p-0 mr-3">
                                                                                        <div class="comment-text w-100 pl-0">
                                                                                            <div class="row">
                                                                                                <div class="col-8">
                                                                                                    <h6 style="font-weight: 600; font-size: 16px;" class="font-medium mb-0">${cmtdto.username}</h6>
                                                                                                </div>
                                                                                                <c:if test="${cmtdto.email.trim() eq sessionScope.USER.email.trim()}">
                                                                                                    <div class="col-4" style="text-align: right">
                                                                                                        <a onclick="return confirmComment()" style="color: #FFA500" href="deleteCommentAction?txtCommentID=${cmtdto.commentID}&txtArticleID=${requestScope.ARTICLE_DETAIL.articleID}">
                                                                                                            Delete
                                                                                                        </a>
                                                                                                    </div>
                                                                                                </c:if>
                                                                                            </div>
                                                                                            <div class="comment-footer"> <span style="font-size: 15px;" class="text-muted">Comment on <fmt:formatDate value="${cmtdto.date}" pattern="dd/MM/yyyy"/></span>
                                                                                                <span class="m-b-15 d-block">${cmtdto.comment}</span>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                    <hr>
                                                                                </c:forEach>
                                                                            </div> 
                                                                        </div>
                                                                    </div>
                                                                </c:if>
                                                            </div>
                                                        </section>
                                                    </div>
                                                </div>
                                            </section>
                                        </div>
                                    </c:if>
                                    <c:if test="${empty requestScope.ARTICLE_DETAIL.image}">
                                        <div class="col-6 pr-0">
                                            <div class="card border-0">
                                                <div class="card-body">
                                                    <div>
                                                        <div class="row">
                                                            <div class="col-8">
                                                                <h5 style="color: #FFA500;" class="card-title font-weight-bold mb-1">${requestScope.ARTICLE_DETAIL.title}</h5>
                                                            </div>
                                                            <div class="col-4 text-right">
                                                                <c:if test="${requestScope.ARTICLE_DETAIL.email.trim() eq sessionScope.USER.email.trim()}">
                                                                    <a style="color: #FFA500;" href="deleteArticleAction?txtArticleID=${requestScope.ARTICLE_DETAIL.articleID}" onclick="return deleteConfirm()">Delete</a>
                                                                </c:if>
                                                            </div>
                                                        </div>
                                                        Posted by: <span style="display: inline; font-size: 18px">${requestScope.ARTICLE_DETAIL.username}</span>
                                                        <p class="card-text mt-1"><i class="far fa-clock pe-2"></i> <fmt:formatDate value="${requestScope.ARTICLE_DETAIL.createDate}" pattern="dd/MM/yyyy"/></p>
                                                    </div>
                                                </div>
                                                <div class="card-body pb-0">
                                                    <div class="flex">
                                                        <p style="font-size: 16px; color: #404E67">${requestScope.ARTICLE_DETAIL.description}</p>
                                                    </div>
                                                    <hr>
                                                    <div class="row">
                                                        <div class="col-6 pl-3">
                                                            <a style="text-decoration: none" href="makeEmotionsAction?txtStatus=Like&txtArticleID=${requestScope.ARTICLE_DETAIL.articleID}">
                                                                <c:if test="${requestScope.LIKE_STATUS eq 'Like'}">
                                                                    <i style="color: #00BFFF; font-size: 18px" class="fas fa-thumbs-up" title="Like"></i>
                                                                </c:if>
                                                                <c:if test="${requestScope.LIKE_STATUS ne 'Like'}">
                                                                    <i style="color: #00BFFF; font-size: 18px" class="far fa-thumbs-up" title="Like"></i>
                                                                </c:if>
                                                            </a>
                                                            ${requestScope.ARTICLE_DETAIL.numOfLike} Like

                                                            <a style="text-decoration: none" href="makeEmotionsAction?txtStatus=Dislike&txtArticleID=${requestScope.ARTICLE_DETAIL.articleID}">
                                                                <c:if test="${requestScope.LIKE_STATUS eq 'Dislike'}">
                                                                    <i style="color: #FF6347; font-size: 18px" class="fas fa-thumbs-down" title="DisLike"></i>
                                                                </c:if>
                                                                <c:if test="${requestScope.LIKE_STATUS ne 'Dislike'}">
                                                                    <i style="color: #FF6347; font-size: 18px" class="far fa-thumbs-down" title="DisLike"></i>
                                                                </c:if>
                                                            </a>
                                                            ${requestScope.ARTICLE_DETAIL.numOfDislike} Dislike
                                                        </div>
                                                        <div class="col-6" style="text-align: right;">
                                                            <span>${requestScope.NUMBER_COMMENT} Comments</span>
                                                        </div>
                                                    </div>
                                                    <hr>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-6 mh-100 pl-0">
                                            <section class="mx-auto h-100">
                                                <div class="card h-100 border-right-0 border-bottom-0 border-top-0">
                                                    <div class="card-body">
                                                        <section class="content-item" id="comments">
                                                            <div class="container px-0">  
                                                                <div class="col-12 pl-0 pb-2">   
                                                                    <form action="postCommentAction" method="POST">
                                                                        <div class="row">
                                                                            <div class="col-3 pr-0" style="margin-top: 15px;">
                                                                                <h5 class="pull-left">Post Comment</h5>
                                                                            </div>
                                                                            <div class="col-9 pl-0">
                                                                                <fieldset>
                                                                                    <textarea class="form-control" name="txtComment" id="message" placeholder="Your message" required=""></textarea>
                                                                                </fieldset>
                                                                                <input type="hidden" name="txtArticleID" value="${requestScope.ARTICLE_DETAIL.articleID}" />
                                                                                <button type="submit" class="btn btn-success pull-right mt-2 float-right">Submit</button>
                                                                            </div>
                                                                        </div>
                                                                    </form>
                                                                </div>
                                                                <div>
                                                                    <hr class="dotted" style="border-top: 3px dotted #bbb;">
                                                                </div>
                                                                <c:if test="${empty requestScope.LIST_CMT}">
                                                                    <span>Be the first one to comment</span>
                                                                </c:if>
                                                                <c:if test="${not empty requestScope.LIST_CMT}">
                                                                    <div class="col-lg-12 mt-3">
                                                                        <div class="comment-widgets">
                                                                            <div class="overflow-auto" style="height: 200px; overflow-y: scroll">
                                                                                <c:forEach var="cmtdto" items="${requestScope.LIST_CMT}" varStatus="counter">
                                                                                    <div class="d-flex flex-row comment-row p-0 mr-3">
                                                                                        <div class="comment-text w-100 pl-0">
                                                                                            <div class="row">
                                                                                                <div class="col-8">
                                                                                                    <h6 style="font-weight: 600; font-size: 16px;" class="font-medium mb-0">${cmtdto.username}</h6>
                                                                                                </div>
                                                                                                <c:if test="${cmtdto.email.trim() eq sessionScope.USER.email.trim()}">
                                                                                                    <div class="col-4" style="text-align: right">
                                                                                                        <a onclick="return confirmComment()" style="color: #FFA500" href="deleteCommentAction?txtCommentID=${cmtdto.commentID}&txtArticleID=${requestScope.ARTICLE_DETAIL.articleID}">
                                                                                                            Delete
                                                                                                        </a>
                                                                                                    </div>
                                                                                                </c:if>
                                                                                            </div>
                                                                                            <div class="comment-footer"> <span style="font-size: 15px;" class="text-muted">Comment on <fmt:formatDate value="${cmtdto.date}" pattern="dd/MM/yyyy - HH:mm"/></span>
                                                                                                <span class="m-b-15 d-block">${cmtdto.comment}</span>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                    <hr>
                                                                                </c:forEach>
                                                                            </div> 
                                                                        </div>
                                                                    </div>
                                                                </c:if>
                                                            </div>
                                                        </section>
                                                    </div>
                                                </div>
                                            </section>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${empty requestScope.ARTICLE_DETAIL}">
            <div class="container-fluid" style="margin-top: 90px;">
                <div class="col-md-12 d-flex justify-content-center">
                    <div class="alert alert-primary mt-3" role="alert">
                        Article not found!!!
                    </div>
                </div>
            </div>
        </c:if>
    </c:if>
    <script>
        function deleteConfirm() {
            if (confirm("Do you want to delete this article ?") === true) {
                return true;
            } else {
                return false;
            }
        }
        function confirmComment() {
            if (confirm("Do you want to delete this comment ?") === true) {
                return true;
            } else {
                return false;
            }
        }
    </script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
</body>
</html>

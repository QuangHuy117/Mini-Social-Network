<%-- 
    Document   : postArticle
    Created on : Feb 24, 2022, 1:32:37 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post Article Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>

        <c:if test="${empty sessionScope.USER}">
            <c:redirect url=""/>
        </c:if>

        <c:if test="${not empty sessionScope.USER}">

            <div class="container shadow" style="margin-top: 100px;">
                <br/>
                <div class="h5">Post an article!</div>
                <hr/>
                <form action="postArticleAction" method="POST">
                    <!--<input type="text" name="txtEmail" value="${sessionScope.USER.email}" hidden/>-->
                    <label>Tittle</label>
                    <input id="txtTitle" class="form-control" type="text" name="txtTitle" maxlength="100" placeholder="What's on your mind?" autocomplete="off" required=""/> <br/>
                    <label>Description</label>
                    <textarea id="txtDescription" class="form-control" name="txtDescription" maxlength="250" rows="3" autocomplete="off"></textarea> <br/>
                    <label>Image</label>
                    <div class="custom-file mb-1">
                        <input type="text" name="txtImage" id="image">
                    </div>
                    <br/>
                    <br/>
                    <input style="background-color: #FFA500; color: white;" onclick="return checkValidate()" class="btn btn-default darken btn-block" type="submit" name="btnAction" value="Post"/>
                    <c:if test="${requestScope.POST_ARTICLE_SUCCESS != null}">
                        <div class="form-group" style="margin-top: 1%; margin-bottom: 0;">
                            <div class="alert alert-success text-center rounded">
                                <c:out value="${requestScope.POST_ARTICLE_SUCCESS}"/>
                            </div>
                        </div>
                    </c:if>
                    <br/>
                </form>
            </div>
        </c:if>

        <script>
            function checkValidate() {

                var error = "";
                var title = document.getElementById('txtTitle').value;
//                var description = document.getElementById('txtDescription').value;

                if (title.trim() === "") {
                    error += "Title can't be blank! \n";
                }
//                if (description.trim() === "") {
//                    error += "Description can't be blank! \n";
//                }

                if (error === "") {
                    alert("Post Article Success!");
                    return true;
                } else {
                    alert(error);
                    return false;
                }
            }
        </script>

    </body>
</html>

<%-- 
    Document   : invalid
    Created on : Mar 2, 2022, 5:59:26 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:if test="${not empty requestScope.ERROR_STRING}">
            <title>${requestScope.ERROR_STRING}</title>
        </c:if>
        <title>Invalid Page</title>
    </head>
    <body>
        <c:if test="${not empty requestScope.ERROR_STRING}">
            <span>${requestScope.ERROR_STRING}</span>
        </c:if>
    </body>
</html>

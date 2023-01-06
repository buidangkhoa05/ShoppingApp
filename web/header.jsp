<%-- 
    Document   : header
    Created on : Oct 26, 2022, 7:34:55 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.USER}" />
        <c:choose>
            <c:when test="${not empty user}">
                <font color="red">
                Welcome, ${user.fullname}
                </font>
                <a href="logoutController">Logout</a>
                <br/>
                <c:if test="${user.role}">
                    <a href="searchJspPage">Search Account</a>
                    <a href="manageBookJspPage">Manage book</a>
                </c:if>
                <a href="shoppingViewController">Click here to buy book</a>
                <a href="createAccountJspPage">Create account</a>
            </c:when>
        </c:choose>
    </body>
</html>

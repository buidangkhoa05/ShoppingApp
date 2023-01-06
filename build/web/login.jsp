<%-- 
    Document   : login
    Created on : Oct 25, 2022, 8:24:27 PM
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
        <h1>Login Page</h1>
        <form action="loginController" method="POST">
            Username <input type="text" name="txtUsername" value="" /><br/>
            Password <input type="password" name="txtPassword" value="" /> <br/>
            <c:set var="error" value="${requestScope.LOGIN_ERROR}"/> 
            <c:if test="${not empty error.usernameOrPasswordNotValid}">
                <font color='red'>
                ${error.usernameOrPasswordNotValid}
                </font>
            </c:if>
            <br/>
            <input type="submit" value="Login"/>
            <input type="reset" value="Reset"/>
        </form><br/>
        <a href="shoppingViewController">Click here to buy book</a><br/>
        <a href="createAccountPage">Click here to create account</a>
        <c:if test="${not empty requestScope.AUTHOR_ERROR.authorError}">
            <h2>
                <font color="red">
                ${requestScope.AUTHOR_ERROR.authorError}
                </font>
            </h2>
        </c:if>
    </body>
</html>

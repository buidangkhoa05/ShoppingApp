<%-- 
    Document   : createNewAccount
    Created on : Oct 20, 2022, 9:08:38 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h1>Create Account</h1>
        <form action="createAccountController" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
            <!--start username-->
            Username <input type="text" name="txtUsername" 
                            value="${param.txtUsername}" />
            <br/>
            <c:if test="${not empty errors.userNammeLengthError}">
                <font color ="red">
                ${errors.userNammeLengthError}
                </font>
                <br/>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color ="red">
                ${errors.usernameIsExisted}
                </font>
                <br/>
            </c:if>
            <br/>
            <!--start  password-->
            Password <input type="password" name="txtPassword" value="" />
            <br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color ="red">
                ${errors.passwordLengthError}
                </font>
                <br/>
            </c:if>
            <br/>
            <!--start confirm-->
            Confirm <input type="password" name="txtConfirm" value="" />
            <br/>
            <c:if test="${not empty errors.cofirmNotMatch}">
                <font color ="red">
                ${errors.cofirmNotMatch}
                </font>
                <br/>
            </c:if>
            <br/>
            <!--start fullname-->
            Full name <input type="text" name="txtFullnname" 
                             value="${param.txtFullnname}" />
            <br/>
            <c:if test="${not empty errors.fullnameLenghtError}">
                <font color ="red">
                ${errors.fullnameLenghtError}
                </font>
                <br/>
            </c:if>
            <br/>
            <input type="submit"/>
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>

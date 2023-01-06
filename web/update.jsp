<%-- 
    Document   : update
    Created on : Nov 1, 2022, 12:21:11 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h1>Update Page</h1>
        <c:set var="error" value="${requestScope.UPDATE_ERROR}"/>
        <form action="UpdateAccountServlet" method="POST">
            <h2>Username: ${param.txtUsername}</h2>
            <input type="hidden" name="txtUsername" value="${param.txtUsername}" />
            <br/>
            Password: <input type="text" name="txtPassword" value="${param.txtPassword}" />
            <br/>
            <c:if test="${not empty error.passwordLengthError}">
                <font color="red">
                ${error.passwordLengthError}
                </font>
            </c:if>
            <br/>
            Fullname: <input type="text" name="txtFullname" value="${param.txtFullname}" />
            <br/>
             <c:if test="${not empty error.fullnameLenghtError}">
                <font color="red">
                ${error.fullnameLenghtError}
                </font>
            </c:if>
            <br/>
            Admin: <input type="checkbox" name="checkAdmin" value="ON" 
                          <c:if test="${param.isAdmin eq true}">
                              checked="check"
                          </c:if>
                          />
            <br/>
            <input type="submit" value="Confirm" />
            <input type="hidden" name="lastSearchValue" value="${param.lastSearchValue}" />
        </form>

    </body>
</html>

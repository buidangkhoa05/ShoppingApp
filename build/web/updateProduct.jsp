<%-- 
    Document   : updateProduct
    Created on : Jan 6, 2023, 12:53:39 PM
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
        <h1>Update Book Page</h1>
        <c:set var="error" value="${requestScope.UPDATE_ERROR}"/>
        
        <form action="updateProductController" method="POST">
            <h2>Sku: ${param.txtSku}</h2>
            <input type="hidden" name="txtSku" value="${param.txtSku}" />
            <br/>
            Name: <input type="text" name="txtName" value="${param.txtName}" />
            <br/>
            <c:if test="${not empty error.nameLengthError}">
                <font color="red">
                ${error.nameLengthError}
                </font>
            </c:if>
            <br/>
            Description: <input type="text" name="txtDescription" value="${param.txtDescription}" />
            <br/>
            <br/>
            Price: <input type="text" name="txtPrice" value="${param.txtPrice}" />
            <br/>
            <br/>
            Quantity: <input type="text" name="txtQuantity" value="${param.txtQuantity}" />
            <br/>
            <c:if test="${not empty error.numberFormatError}">
                <font color="red">
                ${error.numberFormatError}
                </font>
            </c:if>
            <br/>
            <input type="submit" value="Confirm" />
            <input type="hidden" name="lastSearchValue" value="${param.lastSearchValue}" />
        </form>

    </body>
</html>
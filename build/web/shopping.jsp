<%-- 
    Document   : shopping
    Created on : Oct 25, 2022, 10:41:31 PM
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
        <jsp:include page="header.jsp"/>
        <h1>Shopping Page</h1>
        <c:if test="${empty sessionScope.USER}"> 
            <a href="loginPage" >Login Page</a><br/>
        </c:if>
        <c:set var="listProduct" value="${requestScope.LIST_PRODUCT}"/>
        <!--listProduct is a List<ProductDTO>-->
        <c:choose>
            <c:when test="${not empty listProduct}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Sku</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Buy</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${listProduct}">
                            <!--product is a ProductDTO-->
                        <form action="addItemController">
                            <tr>
                                <td>
                                    ${product.sku}
                                    <input type="hidden" name="txtSku" value="${product.sku}" />
                                </td>
                                <td>
                                    ${product.name}
                                </td>
                                <td>
                                    <c:out value="${product.description}" default="No discription"/>
                                </td>
                                <td>
                                    ${product.price}
                                </td>
                                <td>
                                    <input type="number" name="nbQuantity" value="1" />
                                </td>
                                <td>
                                    <input type="submit" value="ADD TO CART"/>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
            <a href="cartJspPage" >View your cart</a>
        </c:when>
        <c:when test="${ empty listProduct}">
            <font color="red">
            <h3>List product is empty</h3>
            </font>
        </c:when>
    </c:choose>
</body>
</html>

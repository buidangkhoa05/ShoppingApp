<%-- 
    Document   : cart
    Created on : Oct 13, 2022, 8:28:00 AM
    Author     : Admin
--%>

<%@page import="java.util.Map"%>
<%@page import="khoabd.cart.CartObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <h1>Web Mart</h1>
        <h2>Your cart include</h2>
        <c:set var="addedMap" value="${sessionScope.CART.items}"/>
        <c:set var="errorMap" value="${requestScope.QUANTITY_ERROR}"/>
        <!--var addedMap is Map<String, ProductDTO> type-->
        <c:choose>
            <c:when test="${not empty addedMap}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Name</th>
                            <th>Quantity</th>
                            <th>Price</th>
                            <th>Action</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <form action="removeItemFromCartController">
                        <tbody>
                            <c:set var="totalPrice" value="${0}"/>
                            <c:forEach var="item" items="${addedMap}" varStatus="counter">
                                <c:set var="dto" value="${item.value}"/>
                                <c:set var="totalPrice" 
                                       value="${totalPrice + (dto.quantity * dto.price)}" />
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${dto.name}</td>
                                    <td>
                                        ${dto.quantity}
                                    </td>
                                    <td>${dto.quantity * dto.price}</td>
                                    <td>
                                        <input type="checkbox" name="chkItem" value="${dto.sku}" />
                                    </td>
                                    <td>
                                        <font color="red">${errorMap[dto.sku].quantityError}</font>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="3" >
                                    Total price: ${totalPrice}
                                </td>
                                <td>
                                    <input type="submit" value="Remove Selected Item" name="btAction" />
                                </td>
                            </tr>
                        </tbody>
                    </form>
                </table>
                <a href="checkQuantityController" >Checkout</a>
            </c:when>
            <c:when test="${empty addedMap}">
                <font color="blue">
                <h2>Cart is empty</h2>
                </font>
            </c:when>
        </c:choose>
        <a href="shoppingViewController">Add more cart</a>


        <%-- <%
            //1 cust goes to his/her cart palce
            if (session != null) {
                //2 cust takes his/her cart 
                CartObject cart = (CartObject) session.getAttribute("CART");
                if (cart != null) {
                    //3 cust take items 
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4 cust traverses each item
        %> 
    <!--        <table border="1">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
            </thead>
            <form action="DispatchController">
                <tbody>-->
    <%                        int count = 0;
                        for (String key : items.keySet()) {
                            int value = items.get(key);
                    %> 
    <!--                    <tr>
                        <td><%= ++count%></td>
                        <td><%= key%></td>
                        <td><%= value%></td>
                        <td>
                            <input type="checkbox" name="chkItem" value="<%= key%>" />
                        </td>
                    </tr>-->
                    <%
                        }//traversal map
                    %>
    <!--                    <tr>
                        <td colspan="3" >
                            <a href="shopping.html">Add more cart</a>
                        </td>
                        <td>
                            <input type="submit" value="Remove Selected Item" name="btAction" />
                        </td>
                    </tr>-->
                    <%
                    %> --%>
        <!--                </tbody>
                    </form>
                </table>-->
        <%--<%                        return;
        }//end items has bean exist
    }//end cart has been exist
    }//end session has been exist
        %> --%>

    </body>
</html>

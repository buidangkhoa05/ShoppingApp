<%-- 
    Document   : checkout
    Created on : Oct 29, 2022, 3:12:50 PM
    Author     : Admin
--%>

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
        <h1>Checkout Page</h1>
        <a href="cartJspPage">Back to your cart</a>
        <br/>
        <br/>
        <c:set var="addedMap" value="${sessionScope.CART.items}"/>
        <!--var addedMap is Map<String, ProductDTO> type-->
        <c:choose>
            <c:when test="${not empty addedMap}">
                <form action="checkoutController" method="POST" >
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="totalPrice" value="${0}"/>
                            <c:forEach var="item" items="${addedMap}" varStatus="counter">
                                <c:set var="dto" value="${item.value}"/>
                                <c:set var="totalPrice" 
                                       value="${totalPrice + (dto.quantity * dto.price)}" />
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${dto.name}</td>
                                    <td>${dto.quantity}</td>
                                    <td>${dto.quantity * dto.price}</td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td colspan="4">
                                    Total price: ${totalPrice}
                                    <input type="hidden" name="txtTotalPrice" value="${totalPrice}" />
                                </td>             
                            </tr>
                        </tbody>
                    </table>
                    <h3>Information</h3>
                    <c:set var="error" value="${requestScope.CHECKOUT_ERROR}"/>
                    <c:if test="${ empty sessionScope.USER}">
                        GuestName(6 - 20 char): <input type="text" name="textGuestName" 
                                                       value="${requestScope.guestname}"/><br/>
                        <c:if test="${not empty error.guestNameLengthError}">
                            <font color="red">
                            ${error.guestNameLengthError}
                            </font>
                            <br/>
                        </c:if>
                    </c:if>
                            Phone(10 char): <input type="tel" name="textPhoneNumber" 
                                                   value="${requestScope.phonenumber}"/> <br/>
                    <c:if test="${not empty error.phoneNumberLengthError}">
                        <font color="red">
                        ${error.phoneNumberLengthError}
                        </font>
                        <br/>
                    </c:if>
                    <c:if test="${not empty error.phonenumberFormatError}">
                        <font color="red">
                        ${error.phonenumberFormatError}
                        </font>
                        <br/>
                    </c:if>
                        Address(at least 10 char): <input type="text" name="textAddress" 
                                                          value="${requestScope.address}" /> <br/>
                    <c:if test="${not empty error.addressLengthError}">
                        <font color="red">
                        ${error.addressLengthError}
                        </font>
                        <br/>
                    </c:if>
                    <input type="submit" value="confirm" />
                </form>
            </c:when>
            <c:when test="${empty addedMap}">
                <font color="blue">
                <h2>Cart is empty</h2>
                </font>
            </c:when>
        </c:choose>
    </body>
</html>

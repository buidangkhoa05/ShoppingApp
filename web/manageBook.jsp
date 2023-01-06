<%-- 
    Document   : manageBook
    Created on : Jan 6, 2023, 11:00:39 AM
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
        <br/>
        <h1>Manage Book Page</h1>
        <%--Search form --%>
        <form action="searchProductController" method="GET">
            Search <input type="text" name="txtSearchValue"
                          value="${param.txtSearchValue}">
            <br/>
            <input type="submit" value="Search"/>
        </form>
        <br/>
            <c:set var="resultSearch" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty resultSearch}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>Sku</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${resultSearch}" 
                                   varStatus="counter">
                            <!--dto is ProductDTO-->
                        <form action="updateProductJspPage" method="POST">
                            <tr>
                                <td>
                                    ${dto.sku}
                                    <input type="hidden" name="txtSku" value="${dto.sku}"/>
                                </td>
                                <td>
                                    ${dto.name}
                                    <input type="hidden" name="txtName" value="${dto.name}" />
                                </td>
                                <td>
                                    ${dto.description}
                                    <input type="hidden" name="txtDescription" value="${dto.description}" />
                                </td>
                                <td>
                                    ${dto.price}
                                    <input type="hidden" name="txtPrice" value="${dto.price}" />
                                </td>
                                <td>
                                    ${dto.quantity}
                                    <input type="hidden" name="txtQuantity" value="${dto.quantity}" />
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="deleteProductController">
                                        <c:param name="pk" value="${dto.sku}"/>
                                        <c:param name="lastSearchValue" value="${param.txtSearchValue}"/>
                                    </c:url>
                                    <a href="${urlRewriting}">Delete</a>
                                </td>
                                <td>
                                    <input type="submit" value="Update"/>
                                    <input type="hidden" name="lastSearchValue" 
                                           value="${param.txtSearchValue}" />
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
                                
        <c:if test="${empty resultSearch}">
            <h2>Not record macthed!!!</h2>
        </c:if>
</body>
</html>

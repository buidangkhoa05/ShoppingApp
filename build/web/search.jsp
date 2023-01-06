<%-- 
    Document   : search
    Created on : Oct 3, 2022, 8:01:07 AM
    Author     : Admin
--%>

<%--
<%@page import="java.util.List"%>
<%@page import="khoabd.account.AccountDTO"%>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
    </head>

    <body>
        <jsp:include page="header.jsp"/>
        <br/>
        <h1>Search Page</h1>
        <%--Search form --%>
        <form action="searchByLastnameController" method="GET">
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
                            <th>No.</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Full name</th>
                            <th>Role</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${resultSearch}" 
                                   varStatus="counter">
                            <!--dto is AccouuntDTO-->
                        <form action="updateJspPage" method="POST">
                            <tr>
                                <td>
                                    ${counter.count}
                                    .</td>
                                <td>
                                    ${dto.username}
                                    <input type="hidden" name="txtUsername" value="${dto.username}"/>
                                </td>
                                <td>
                                    ${dto.password}
                                    <input type="hidden" name="txtPassword" value="${dto.password}" />
                                </td>
                                <td>
                                    ${dto.fullname}
                                    <input type="hidden" name="txtFullname" value="${dto.fullname}" />
                                </td>
                                <td>
                                    <input type="checkbox" disabled="true" value="ON" 
                                           <c:if test="${dto.role}"> 
                                               checked="check"
                                           </c:if>
                                           />
                                    <input type="hidden" name="isAdmin" value="${dto.role}" />
                                </td>
                                <td>
                                    <c:url var="urlRewriting" value="deleteAccountController">
                                        <c:param name="pk" value="${dto.username}"/>
                                        <c:param name="lastSearchValue" value="${searchValue}"/>
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
            <h2>Not record macth!!!</h2>
        </c:if>
</body>
</html>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bookings</title>
</head>
<body>
<div align="center">
    <br><br><br>
    <table align="center" cellpadding="5" border="1" valign="middle">
        <tr>
            <th>Order Id</th>
            <th>Date</th>
            <th>Source</th>
            <th>Destination</th>
            <th>Price</th>
            <th>Status</th>
            <th>Select Trip</th>
        </tr>
        <c:forEach var="order" items="${orderDetailsList}">
            <tr>
                <td align="center"><c:out value="${order.id}"/></td>
                <td><c:out value="${order.date}"/></td>
                <td><c:out value="${order.source}"/></td>
                <td><c:out value="${order.destination}"/></td>
                <td>&#8377;<c:out value="${order.price}"/></td>
                <td><c:out value="${order.status}"/></td>
                <td align="center" border="1" cellpadding="5">
                    <form:form method="POST" action="/savedOrderDetails" modelAttribute="orderWrapper">
                        <form:hidden path="Id" value="${order.id}"/>
                        <form:button type="submit">Select-Seat</form:button>
                    </form:form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br><br>
    <a href="/searchRoutes">Back</a>
</div>

</body>
</html>

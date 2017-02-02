<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bookings</title>
</head>
<body>
<div align="center">
    <table align="center" cellpadding="5" border="1">
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
                        <%--<form:form method="POST" action="/booking" modelAttribute="numberOfSeats">--%>
                        <%--<form:hidden path="orderId" value="${order.id}"/>--%>
                </td>
                    <%--<td align="center" border="1" cellpadding="5">--%>
                    <%--<form:button type="submit">Select-Seat</form:button>--%>
                    <%--</form:form>--%>

                    <%--</td>--%>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>

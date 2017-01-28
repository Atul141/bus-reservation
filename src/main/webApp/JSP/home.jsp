<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<br><br>
<h3>You are seeing travel routes between ${selectedRoute.source} and ${selectedRoute.destination} on ${selectedRoute.selectedDate}</h3><br>
<div align="center">
        <table border="2" cellpadding="5">
            <caption><h2>List of Routes</h2></caption>
            <tr>
            <th>Bus-No</th>
            <th>Journey Date</th>
            <th>Source</th>
            <th>Destination</th>
            <th>Departure-Time</th>
            <th>Arrival-Time</th>
            <th>Price</th>
            <th>Distance</th>
            <th>Available Seats</th>
            <th>Select Number Of Seats</th>
            <th>Select To Continue</th>
        </tr>
        <c:forEach var="routes" items="${routesList}">
            <tr>
                <td><c:out value="${routes.bus_no}"/></td>
                <td><c:out value="${routes.selectedDate}"/></td>
                <td><c:out value="${routes.source}"/></td>
                <td><c:out value="${routes.destination}"/></td>
                <td><c:out value="${routes.departureTime}"/></td>
                <td><c:out value="${routes.arrivalTime}"/></td>
                <td><c:out value="${routes.price}"/></td>
                <td><c:out value="${routes.distance}"/></td>
                <td><c:out value="${routes.availableNoSeats}"/></td>
                <td align="center" border="1" cellpadding="5">
                    <form:form method="POST" action="/booking" modelAttribute="numberOfSeats">
                    <form:select path="number" items="${routes.availableSeat}"/>
                    <form:hidden path="route_id" value="${routes.id}"/>
                </td>
                <td align="center" border="1" cellpadding="5">
                    <form:button type="submit">Select-Seat</form:button>
                    </form:form>

                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<a href="/default">Logout</a>
</body>
</html>

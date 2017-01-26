<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<h3>${userName} ,Welcome to Bus Reservation System</h3><br>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Routes</h2></caption>
        <tr>
            <th>Bus-No</th>
            <th>Source</th>
            <th>Destination</th>
            <th>Departure-Time</th>
            <th>Arrival-Time</th>
            <th>Price</th>
            <th>Distance</th>
        </tr>
        <c:forEach var="routes" items="${routesList}">
            <tr>
                <td><c:out value="${routes.bus_no}" /></td>
                <td><c:out value="${routes.source}" /></td>
                <td><c:out value="${routes.destination}" /></td>
                <td><c:out value="${routes.departureTime}" /></td>
                <td><c:out value="${routes.arrivalTime}" /></td>
                <td><c:out value="${routes.price}" /></td>
                <td><c:out value="${routes.distance}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>
<a href="/default">Logout</a>
</body>
</html>

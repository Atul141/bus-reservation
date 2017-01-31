<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirmation</title>
</head>
<body>
<div align="center">
    <h2>Your selected Route is</h2>
    <table border="1" cellpadding="5">
        <tr>
            <th>Bus-No</th>
            <th>Journey Date</th>
            <th>Source</th>
            <th>Destination</th>
            <th>Departure-Time</th>
            <th>Arrival-Time</th>
            <th>Price</th>
            <th>Distance</th>
        </tr>
        <tr>
            <td><c:out value="${route.bus_no}"/></td>
            <td><c:out value="${route.selectedDate}"/></td>
            <td><c:out value="${route.source}"/></td>
            <td><c:out value="${route.destination}"/></td>
            <td><c:out value="${route.departureTime}"/></td>
            <td><c:out value="${route.arrivalTime}"/></td>
            <td>&#8377;<c:out value="${route.price}"/></td>
            <td><c:out value="${route.distance}"/>Km</td>
        </tr>
    </table>
    <table border="1" cellpadding="5">
        <caption><h2>Passenger Details</h2></caption>
        <tr>
            <th>Passenger No.</th>
            <th>Name</th>
            <th>Gender</th>
            <th>Age</th>
            <th>Seat</th>
            <th>Is Senior Citizen</th>
            <th>Is Disabled</th>
        </tr>
        <form:form method="post" modelAttribute="passengerWrapper" action="saveOrder" >
        <c:forEach varStatus="i" items="${passengerWrapper.passengerList}">
            <tr>
                <th>${i.index+1}</th>
                <th><form:input path="passengerList[${i.index}].name" type="text" readonly="true"/></th>
                <th><form:input path="passengerList[${i.index}].gender" type="text" readonly="true"/></th>
                <th><form:input path="passengerList[${i.index}].age" type="text" readonly="true"/></th>
                <th><form:input path="passengerList[${i.index}].seat" type="text" readonly="true"/></th>
                <th><form:checkbox path="passengerList[${i.index}].isSeniorCitizen" readonly="true"
                                   disabled="true"/></th>
                <th><form:checkbox path="passengerList[${i.index}].isDisabled" readonly="true" disabled="true"/></th>

            </tr>
        </c:forEach>
    </table>
    <br><br>
    <caption><h3>You Are paying a Total Of  &#8377;${price} for this Journey</h3></caption>
    <button>Pay</button>
    </form:form>

</div>

</div>

</body>
</html>

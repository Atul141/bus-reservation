<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking</title>
</head>
<body>
<br><br>
<div align="center">
    <caption><h2>Your selected Route is</h2></caption>
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
            <td><c:out value="${route.price}"/></td>
            <td><c:out value="${route.distance}"/></td>
        </tr>
    </table>
    <table border="1" cellpadding="5">
        <caption><h2>Enter Passenger Details</h2></caption>
        <tr>
            <th>Passenger No.</th>
            <th>Name</th>
            <th>Gender</th>
            <th>Age</th>
            <th>Is Senior Citizen</th>
            <th>Is Disabled</th>
        </tr>
        <form:form method="post" modelAttribute="passengerWrapper" action="confirmation">
        <c:forEach varStatus="i" items="${passengerWrapper.passengerList}">
            <tr>
                <th>${i.index+1}</th>
                <th><form:input path="passengerList[${i.index}].name" type="text"/></th>
                <th><form:select path="passengerList[${i.index}].gender" items="${passengerWrapper.genderList}"/></th>
                <th><form:input path="passengerList[${i.index}].age" type="text"/></th>
                <th><form:checkbox path="passengerList[${i.index}].isSeniorCitizen"/></th>
                <th><form:checkbox path="passengerList[${i.index}].isDisabled"/></th>
            </tr>
        </c:forEach>
    </table>
    <br>
    <table border="1" cellpadding="5" align="center">
        <br><br>
            ${error}
        <caption><h2>Please Select Your Seat</h2></caption>
        <tr>
            <th>Seats Reserved For women</th>
            <th>Seats Reserved For Senior Citizens</th>
            <th>Seats Reserved For Disabled</th>
            <th>Seats For other</th>
        </tr>
        <tr>
            <td valign="top" align="center">
                <c:forEach var="availableSeatWrapper" varStatus="i" items="${availableSeatWrapper.womenReservation}">
                    <br>      ${availableSeatWrapper} <input type="checkbox" name="selectedSeatWomen"
                                                             value="${availableSeatWrapper}"/>
                </c:forEach>
            </td>
            <td valign="top" align="center">
                <c:forEach var="availableSeatWrapper" varStatus="i"
                           items="${availableSeatWrapper.seniorCitizenReserved}">
                    <br>    ${availableSeatWrapper} <input type="checkbox" name="selectedSeatSeniorCitizen"
                                                           value="${availableSeatWrapper}"/>
                </c:forEach>
            </td>
            <td valign="top" align="center">
                <c:forEach var="availableSeatWrapper" varStatus="i" items="${availableSeatWrapper.disabledReserved}">
                    <br>   ${availableSeatWrapper}<input type="checkbox" name="selectedSeatDisabled"
                                                         value="${availableSeatWrapper}"/>
                </c:forEach>
            </td>
            <td valign="top" align="center">
                <c:forEach var="availableSeatWrapper" varStatus="i" items="${availableSeatWrapper.general}">
                    <br>  ${availableSeatWrapper} <input type="checkbox" name="selectedSeatGeneral"
                                                         value="${availableSeatWrapper}" />
                </c:forEach>
            </td>
        </tr>
    </table>
    <br><br>
    <button type="submit">Confirmation</button>

</div>
</form:form>

</body>
</html>

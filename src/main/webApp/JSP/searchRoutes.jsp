<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SearchRoutes</title>
</head>
<body>
<h3>${userName} ,Welcome to Bus Reservation System</h3><br><br>
<a href="/UserBookings">Your Bookings</a><br><br><br>
<a href="/viewUserDetails">View Profile</a><br><br><br>
<a href="/default">Logout</a><br>
<div align="center">
    <table cellpadding="10">
        <caption><h2>Search Routes</h2></caption>
        <br>
        <form:form method="POST" action="/Home" modelAttribute="route">

            <tr>
                <td>Source</td>
                <td><form:input path="source"/></td>
            </tr>
            <tr>
                <td>Destination</td>
                <td><form:input path="destination"/></td>
            </tr>
            <tr>
                <td>Route Date</td>
                    <%--<td><form:input path="bus_no" /></td>--%>
                <td><input type="date" name="selectedDate" value="date"/></td>
            </tr>
            <br>
            <td colspan="5" align="right"><input type="submit" value="Search"/></td>
            <br>
            ${error}
            <br>
        </form:form>
    </table>
</div>
</body>
</html>

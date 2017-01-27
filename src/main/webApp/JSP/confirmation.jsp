<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirmation</title>
</head>
<body>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Enter Passenger Details</h2></caption>
        <tr>
            <th>Passenger No.</th>
            <th>Name</th>
            <th>Gender</th>
            <th>Age</th>
            <th>Seat</th>
        </tr>
        <form:form method="post" modelAttribute="passengerWrapper" action="confirmation">
        <c:forEach varStatus="i" items="${passengerWrapper.passengerList}">
        <tr>
            <th>${i.index+1}</th>
            <th><form:input path="passengerList[${i.index}].name" type="text" readonly="true"/></th>
            <th><form:input path="passengerList[${i.index}].gender" type="text" readonly="true"/></th>
            <th><form:input path="passengerList[${i.index}].age" type="text" readonly="true"/></th>
            <th><form:input path="passengerList[${i.index}].seat" type="text" readonly="true"/></th>
        </tr>
        </c:forEach>
        <button>Pay</button>
        </form:form>
</div>
</body>
</html>

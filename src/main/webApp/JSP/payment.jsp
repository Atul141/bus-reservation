<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment</title>
</head>
<body>
<div align="center">
    <table align="center" cellpadding="10">

        <form:form method="post" modelAttribute="payment" action="validatePayment">
            <tr>
                <td align="center">
                    <caption><h2>Credit Card Details</h2></caption>
                </td>
            </tr>
            <tr>
                <td align="center">Card Type</td>
                <td align="center"><form:select path="cardType" items="${paymentWrapper.cardType}"/></td>
            </tr>
            <tr>
                <td align="center">Card Number</td>
                <td align="center"><form:input path="cardNumber" type="text"/></td>
            </tr>
            <tr>
                <td align="center">Expiry Date</td>

                <td align="center">Year <form:select path="year" items="${paymentWrapper.year}"/></td>
                <td align="center">Month <form:select path="month" items="${paymentWrapper.month}"/></td>
            </tr>
            <tr>
                <td align="center">Cvv</td>
                <td align="center"><form:input path="cvvNumber" type="text"/></td>
            </tr>
            <tr>
                <td align="center">Name On card</td>
                <td align="center"><form:input path="name" type="text"/></td>
            </tr>
            <tr>
                <td align="center">
                    <button type="submit">pay</button>
                </td>
            </tr>
        </form:form>
        <br><br>
        <td>${error}</td>
    </table>
</div>
</body>
</html>

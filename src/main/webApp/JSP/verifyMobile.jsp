<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Verification</title>
</head>
<body>
<div align="center">
    <caption><h2>Enter OTP</h2></caption>
    <br>
    <form:form method="post" action="validatePhoneNumber">
        <table cellpadding="5">
            <input name="otp" type="text"/>
            <td colspan="5" align="right"><input type="submit" value="Verify" align="right"/></td>
        </table>

        <h3>${error}</h3>
    </form:form>
    <br><br>
</div>
<a href="/">Home</a>
</body>
</html>

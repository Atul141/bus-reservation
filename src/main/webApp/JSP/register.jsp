<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Form</title>
</head>
<body>
Registration form
<form:form method="post" modelAttribute="User" action="registration">
    <table>
        <tr>
            <td><spring:message code="lbl.firstName" text="First Name"/></td>
            <td><form:input path="firstName"/></td>
        </tr>
        <tr>
            <td><spring:message code="lbl.lastName" text="Last Name"/></td>
            <td><form:input path="lastName"/></td>
        </tr>
        <tr>
            <td><spring:message code="lbl.email" text="Email Id"/></td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td><spring:message code="lbl.phone" text="Phone"/></td>
            <td><form:input path="phone"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="registration"/></td>
        </tr>
    </table>
</form:form>
<a href="/">Home</a>
</body>
</html>

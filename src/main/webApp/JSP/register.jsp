<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Form</title>
</head>
<body>
Registration form
<form:form method="post" modelAttribute="User" action="RegisterUserDetails">
    <table>
        <tr>
            <td>First Name</td>
            <td><form:input path="firstName"/></td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><form:input path="lastName"/></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><form:input path="password"/></td>
        </tr>
        <tr>
            <td>Phone Number</td>
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

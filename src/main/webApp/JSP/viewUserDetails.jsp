<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Details</title>
</head>
<body>
<div align="left">
    <caption><h2>User Details</h2></caption>
    <br>
    <form:form method="get" modelAttribute="userDetails" action="editUserDetails">
        <table cellpadding="5">
            <tr>
                <td>First Name</td>
                <td><form:input path="firstName" readonly="true"/></td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td><form:input path="lastName" readonly="true"/></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><form:input path="email" readonly="true"/></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><form:input path="password" readonly="true"/></td>
            </tr>
            <tr>
                <td>Phone Number</td>
                <td><form:input path="phone" readonly="true"/></td>
            </tr>
            <td colspan="5" align="right"><input type="submit" value="Edit" align="right"/></td>
        </table>

    </form:form>
</div>
<a href="/searchRoutes">Home</a>
</body>
</html>

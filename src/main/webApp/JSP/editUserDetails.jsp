<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration Form</title>
</head>
<body>
<div align="left">
    <caption><h2>Edit Details</h2></caption><br>
    <form:form method="post" modelAttribute="userDetails" action="updateUserDetails">
        <table cellpadding="5">
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
            <td colspan="5" align="right"><input type="submit" value="update" align="right"/></td>
        </table>

    </form:form>
</div>
${error}<br><br>
<a href="/">Home</a>
</body>
</html>

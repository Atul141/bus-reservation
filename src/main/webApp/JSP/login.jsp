<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
Login
<form:form method="post" modelAttribute="UserDetails" action="loginValidation">
    <table>
        <tr>
            <td>Email</td>
            <td><form:input path="email"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><form:input path="password" type="password"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Login"/></td>
        </tr>
    </table>
</form:form>
${loginError}<br><br>
<a href="/">Home</a>
</body>
</html>

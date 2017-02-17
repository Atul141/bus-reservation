<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign In</title>
</head>
<body>

<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id" content="943417884034-6lf0tl2m51l2tlpfcd5jsp6gf39bub4t.apps.googleusercontent.com">
<div align="center" class="g-signin2" data-onsuccess="onSignIn"></div>

<script>
    function onSignIn(googleUser) {
        var profile = googleUser.getBasicProfile();
        window.location.replace("/validateFaceBook?" + profile.getEmail());
    }
</script>
</body>
</html>

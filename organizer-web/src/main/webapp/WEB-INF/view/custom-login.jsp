<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>

<style>

	.error{
		color: red;
	}
	.success{
		color: green;
	}

</style>

</head>
<body>

<h1>Bitte einloggen:</h1>

<form:form action="${pageContext.request.contextPath}/authenticateUser" method="POST">

	<c:if test="${param.error != null}">
		<p class="error">Nutzername oder Passwort falsch.</p>
	</c:if>

	<c:if test="${param.logout != null}">
		<p class="success">Sie wurden erfolgreich abgemeldet.</p>
	</c:if>

	<p>Username: <input type="text" name="username"/></p>
	<p>Passwort: <input type="password" name="password"/></p>

	<input type="submit" value="Einloggen" />
</form:form>

</body>
</html>
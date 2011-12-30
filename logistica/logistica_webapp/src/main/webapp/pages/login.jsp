<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<%@ page
	import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter"%>
<%@ page
	import="org.springframework.security.core.AuthenticationException"%>

<html>
<head>
<title>Ingreso al sistema</title>

<style type="text/css">
body {
	font-family: Verdana;
	margin-left:auto;
	margin-right:auto;
	padding:5px;

}

table {
	border: thin solid #ccc;
}

table tr:first-child {
	background-color: #34badb;
	color: white;
	text-align: center;
}
</style>

</head>

<body onLoad="document.getElementById('userText').focus();">

<c:if test="${not empty param.login_error}">
	<font color="red"> Usuario y/o contraseña incorrecta. O no posee
	los permisos suficientes.<br />
	<br />
	</font>
</c:if>

<form name="f" action="/logistica/j_spring_security_check" method="POST">
<table>
	<tr>
		<td colspan="2">Ingreso al sistema</td>
	</tr>
	<tr>
		<td>Usuario:</td>
		<td><input id="userText" type='text' name='j_username'
			value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>' /></td>
	</tr>
	<tr>
		<td>Contraseña:</td>
		<td><input type='password' name='j_password'></td>
	</tr>
	<tr>
		<td><input name="Ingresar" type="submit" value="Ingresar"></td>
		<td><input name="Limpiar" type="reset" value="Limpiar"></td>
	</tr>
</table>
</form>

</body>
</html>
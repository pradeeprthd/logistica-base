<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ page import="org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter" %>
<%@ page import="org.springframework.security.core.AuthenticationException" %>
 
<html>
  <head>
    <title>Ingreso al sistema</title>
  </head>
 
  <body>
    <h1>Ingreso al sistema</h1>
 
    <c:if test="${not empty param.login_error}">
      <font color="red">
        Usuario o contraseña incorrecta<br/><br/>
      </font>
    </c:if>
 
    <form name="f" action="/logistica/j_spring_security_check" method="POST">
      <table>
        <tr><td>Usuario:</td><td><input type='text' name='j_username' value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/></td></tr>
        <tr><td>Contraseña:</td><td><input type='password' name='j_password'></td></tr>
        <tr><td><input name="Ingresar" type="submit" value="Ingresar"></td><td><input name="Limpiar" type="reset" value="Limpiar"></td></tr>
      </table>
    </form>
  </body>
</html>
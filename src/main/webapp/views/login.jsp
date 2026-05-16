<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión | Sistema de Reservas</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        .error { color: #D8000C; background-color: #FFD2D2; padding: 10px; margin-bottom:10px; border-radius: 5px;}
        .exito { color: #4F8A10; background-color: #DFF2BF; padding: 10px; margin-bottom:10px; border-radius: 5px;}
        .form-group { margin-bottom: 15px; }
    </style>
</head>
<body>

    <h2>Iniciar Sesion</h2>
    <c:if test="${param.mensaje == 'registro_exitoso'}">
        <div class="exito">!Registro exitosos¡ Ahora puedes iniciar sesion</div>
    </c:if>

    <c:if test="${not empty errorMensaje}">
        <div class="error"><c:out value="${errorMensaje}" /> </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/login" method="post" >
        <div class="form-group">
            <label for="email">Correo ELectronico:</label>
            <input type="email" id="email" name="email" required >
        </div>

        <div class="form-group">
            <label for="password">COntraseña:</label>
            <input type="password" id="password" name="password" required >

        </div>

        <button type="submit">Ingresar</button>

    </form>

    <p>¿No tienes cuenta? <a href="${pageContext.request.contextPath}/registro">Registrate aqui</a> </p>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Declaración obligatoria para usar JSTL (Nivel Enterprise) --%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro | Sistema de Reservas</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; }
        .error { color: #D8000C; background-color: #FFD2D2; padding: 10px; border-radius: 5px;}
        .form-group { margin-bottom: 15px; }
    </style>
</head>
<body>

    <h2>Registro de Nuevo Usuario</h2>
    <c:if test="${not empty errorMensaje}">
        <div class="error">
            <c:out value="${errorMensaje}" />

        </div>
    </c:if>


            <form action="${pageContext.request.contextPath}/registro" method="POST">
                <div class="form-group">
                    <label for="nombre">NOMbre Completo:</label>
                    <input type="text" id="nombre" name="nombre" required>
                </div>

                <div class="form-group">
                    <label for="email">Correo Electrónico:</label><br>
                    <input type="email" id="email" name="email" required>
                </div>

                <div class="form-group">
                    <label for="password">Contraseña:</label><br>
                    <input type="password" id="password" name="password" required minlength="8">
                </div>

                <div class="form-group">
                    <label for="confirmar_password">Confirmar Contraseña:</label><br>
                    <input type="password" id="confirmar_password" name="confirmar_password" required minlength="8">
                </div>

                <button type="submit">Crear cuenta</button>

            </form>
</body>
</html>

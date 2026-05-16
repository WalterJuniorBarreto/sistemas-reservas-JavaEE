<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Nueva Reserva | Sistema</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; background-color: #f4f4f9;}
        .form-container { background: white; padding: 20px; border-radius: 5px; box-shadow: 0 2px 4px rgba(0,0,0,0.1); max-width: 500px; margin: auto;}
        .form-group { margin-bottom: 15px; }
        .error { color: #D8000C; background-color: #FFD2D2; padding: 10px; margin-bottom:10px; border-radius: 5px;}
        button { background-color: #5cb85c; color: white; border: none; padding: 10px 15px; cursor: pointer; border-radius: 4px; width: 100%;}
        button:hover { background-color: #4cae4c; }
        a.btn-back { display: block; text-align: center; margin-top: 15px; text-decoration: none; color: #337ab7; }
    </style>
</head>
<body>

    <div class="form-container">
        <h2>Agendar Nueva Reserva</h2>

        <c:if test="${not empty errorMensaje}">
            <div class="error"><c:out value="${errorMensaje}" /> </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/reservas/nueva" method="post">
            <div class="form-group">
                <label for="servicio">Servicio:</label> <br>
                <select id="servicio" name="servicio" required style="width: 100%; padding: 8px;">
                    <option value="">-- Seleccione un servicio ---</option>
                    <option value="Consultoria Tecnica">Consultoria Tecnica</option>
                    <option value="Mantenimiento de Software">Mantenimiento de Software</option>
                    <option value="Auditoria de Seguridad">Auditoria de Seguridad</option>

                </select>
            </div>

            <div class="form-group">
                <label for="fechaHora"> Fecha y Hora:</label> <br>
                <input type="datetime-local" id="fechaHora" name="fechaHora" required style="width: 100%; padding: 8px;">
            </div>

            <button type="submit">Confirmar Reserva</button>
        </form>

        <a href="${pageContext.request.contextPath}/dashboard" class="btn-back">Volver al Dashboard</a>
    </div>

</body>
</html>
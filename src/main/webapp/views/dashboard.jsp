<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Dashboard | Sistema de Reservas</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; background-color: #f4f4f9;}
        .header { display: flex; justify-content: space-between; align-items: center; background: #333; color: white; padding: 15px; border-radius: 5px; }
        .btn-logout { background-color: #d9534f; color: white; text-decoration: none; padding: 8px 15px; border-radius: 4px; }
        .btn-logout:hover { background-color: #c9302c; }
        .content { margin-top: 20px; padding: 20px; background: white; border-radius: 5px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);}
    </style>
</head>
<body>

    <div class="header">
        <h2>Panel Principal - Bienvenido, <c:out value="${usuarioLogueado.nombre}" />! </h2>
        <a href="${pageContext.request.contextPath}/logout" class="btn-logout">Cerrar sesion</a>
    </div>

    <div class="content">
        <h3>MIS RESEREVAS PROXIMAS</h3>
        <a href="${pageContext.request.contextPath}/reservas/nueva">Crear Nueva Reserva</a>
        <table border="1" style="width: 100%; border-collapse: collapse; text-align: left;">
            <thead style="background-color: #eee;">
                <tr>
                    <th>Servicio</th>
                    <th>Fecha y Hora</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${listaReservas}" var="res">
                    <tr>
                        <td><c:out value="${res.servicio}" /> </td>
                        <td><c:out value="${res.fechaHora}" /> </td>
                        <td>
                            <strong style="color: ${res.estado == 'CANCELADA' ? 'red' : 'green'};">
                                <c:out value="${res.estado}" />
                            </strong>
                        </td>
                        <td>
                            <c:if test="${res.estado == 'PENDIENTE'}">
                                <form action="${pageContext.request.contextPath}/reservas/cancelar" method="post" style="margin: 0;">
                                    <input type="hidden" name="reservaId" value="${res.id}">
                                    <button type="submit" style="background-color: #d9534f; color: white; border: none; padding: 5px 10px; border-radius: 3px; cursor: pointer;"
                                        onclick="return confirm('¿EStas seguro de cancelar esta reserva');">
                                        Cancelar
                                    </button>
                                </form>
                            </c:if>
                            <c:if test="${res.estado != 'PENDIENTE'}">
                                <span style="color: gray;">Sin acciones</span>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty listaReservas}">
                    <tr>
                        <td colspan="3" style="text-align: center;">AUN. No tienes reservas programadas</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>

</body>
</html>
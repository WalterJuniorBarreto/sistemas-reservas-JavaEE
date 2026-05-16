<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Panel de Administración | Reservas</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; background-color: #eef2f5;}
        .header { display: flex; justify-content: space-between; align-items: center; background: #8e44ad; color: white; padding: 15px; border-radius: 5px; }
        .content { margin-top: 20px; padding: 20px; background: white; border-radius: 5px; box-shadow: 0 2px 4px rgba(0,0,0,0.1);}
        .badge-admin { background-color: #f1c40f; color: #333; padding: 3px 8px; border-radius: 12px; font-size: 0.8em; font-weight: bold;}
    </style>
</head>
<body>

<div class="header">
    <h2>Centro de Control - Módulo <span class="badge-admin">ADMIN</span></h2>
    <a href="${pageContext.request.contextPath}/logout" style="color: white;">Cerrar Sesión</a>
</div>

<div class="content">
    <h3>Todas las reservas del sistema</h3>
    <table border="1" style="width: 100%; border-collapse: collapse; text-align: left;">
        <thead style="background-color: #eee;">
        <tr>
            <th>Cliente</th>
            <th>Servicio</th>
            <th>Fecha Programada</th>
            <th>Estado Operativo</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listaGlobal}" var="res">
            <tr>
                <td><strong><c:out value="${res.usuario.nombre}" /></strong></td>
                <td><c:out value="${res.servicio}" /></td>
                <td><c:out value="${res.fechaHora}" /></td>
                <td>
                    <strong style="color: ${res.estado == 'CANCELADA' ? 'red' : 'green'};">
                        <c:out value="${res.estado}" />
                    </strong>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
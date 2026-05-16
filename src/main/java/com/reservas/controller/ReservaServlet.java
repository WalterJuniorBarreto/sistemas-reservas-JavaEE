package com.reservas.controller;

import com.reservas.model.Reserva;
import com.reservas.model.Usuario;
import com.reservas.service.ReservaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "ReservaServlet", urlPatterns = {"/reservas/nueva"})
public class ReservaServlet extends HttpServlet {

    private ReservaService reservaService;

    @Override
    public void init() throws ServletException{
        this.reservaService = new ReservaService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        request.getRequestDispatcher("/views/nueva_reserva.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        HttpSession session = request.getSession(false);
        Usuario usuarioActual = (Usuario) session.getAttribute("usuarioLogueado");

        String servicio = request.getParameter("servicio");
        String fechaHoraStr = request.getParameter("fechaHora");

        try{
            LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr);

            Reserva nuevaReserva = new Reserva(usuarioActual, servicio, fechaHora, "PENDIENTE");

            String resultado = reservaService.crearReserva(nuevaReserva);

            if(resultado.startsWith("EXITO")){
                response.sendRedirect(request.getContextPath() + "/dashboard?mensaje=reserva_creada");
            } else {
                request.setAttribute("errorMensaje", resultado);
                request.getRequestDispatcher("/views/nueva_reserva.jsp").forward(request, response);
            }
        } catch (Exception e){
            request.setAttribute("errorMensaje", "Formato de fecha invalido");
            request.getRequestDispatcher("/views/nueva_reserva.jsp").forward(request, response);
        }
    }
}

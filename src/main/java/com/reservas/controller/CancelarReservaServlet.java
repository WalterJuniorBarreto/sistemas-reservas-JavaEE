package com.reservas.controller;

import com.reservas.model.Usuario;
import com.reservas.service.ReservaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "CancelarReservaServlet", urlPatterns = {"/reservas/cancelar"})
public class CancelarReservaServlet  extends HttpServlet {

    private ReservaService reservaService;

    @Override
    public void init() throws ServletException{
        this.reservaService = new ReservaService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        HttpSession session = request.getSession(false);
        Usuario usuarioActual = (Usuario) session.getAttribute("usuarioLogueado");

        try{
            int reservaId = Integer.parseInt(request.getParameter("reservaId"));

            String resultado = reservaService.cancelarReservaSegura(reservaId, usuarioActual.getId());
            response.sendRedirect(request.getContextPath() + "/dashboard?mensaje=" + resultado);
        } catch (NumberFormatException e){
            response.sendRedirect(request.getContextPath() + "/dashboard?mensaje=ERROR: ID de reserva invalido");
        }
    }
}

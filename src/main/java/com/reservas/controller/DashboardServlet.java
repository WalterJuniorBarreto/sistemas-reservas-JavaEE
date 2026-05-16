package com.reservas.controller;

import com.reservas.dao.ReservaDAO;
import com.reservas.model.Reserva;
import com.reservas.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "DashboardServlet", urlPatterns = {"/dashboard"})
public class DashboardServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();

        Usuario actual = (Usuario) session.getAttribute("usuarioLogueado");

        ReservaDAO reservaDAO = new ReservaDAO();
        List<Reserva> misReservas = reservaDAO.listarPorUsuario(actual.getId());

        request.setAttribute("listaReservas", misReservas);
        request.getRequestDispatcher("/views/dashboard.jsp").forward(request, response);
    }
}

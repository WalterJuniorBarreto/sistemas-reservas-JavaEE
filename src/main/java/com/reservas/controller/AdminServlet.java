package com.reservas.controller;

import com.reservas.dao.ReservaDAO;
import com.reservas.model.Reserva;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.List;

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin/dashboard"})
public class AdminServlet  extends HttpServlet {

    private ReservaDAO reservaDAO;

    @Override
    public void init() throws ServletException {
        this.reservaDAO = new ReservaDAO();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Reserva> todasLasReservas = reservaDAO.listarTodas();
        request.setAttribute("listaGlobal", todasLasReservas);

        request.getRequestDispatcher("/views/admin_dashboard.jsp").forward(request, response);
    }
}

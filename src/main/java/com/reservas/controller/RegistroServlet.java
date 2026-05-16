package com.reservas.controller;

import com.reservas.dto.UsuarioRegistroDTO;
import com.reservas.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOError;
import java.io.IOException;

@WebServlet(name = "RegistroServlet", urlPatterns = {"/registro"})
public class RegistroServlet extends HttpServlet {

    private UsuarioService usuarioService;

    @Override
    public void init() throws ServletException{
        this.usuarioService = new UsuarioService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        request.getRequestDispatcher("/views/registro.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmar = request.getParameter("confirmar_password");

        UsuarioRegistroDTO dto = new UsuarioRegistroDTO(nombre, email, password, confirmar);

        String resultado = usuarioService.registrarNuevoUsuario(dto);

        if(resultado.startsWith("EXITO")){
            response.sendRedirect(request.getContextPath() + "/login?mensaje=registro_exitoso");
        } else {
            request.setAttribute("errorMensaje", resultado);
            request.getRequestDispatcher("/views/registro.jsp").forward(request, response);
        }
    }

}

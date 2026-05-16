package com.reservas.controller;

import com.reservas.model.Usuario;
import com.reservas.service.UsuarioService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet  extends HttpServlet {

    private UsuarioService usuarioService;

    @Override
    public void init() throws ServletException{
        this.usuarioService = new UsuarioService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("usuarioLogueado") != null){
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");

            if("ADMIN".equals(usuario.getRol())){
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            } else {
                response.sendRedirect(request.getContextPath() + "/dashboard");
            }
            return;
        }

        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Usuario usuario = usuarioService.loginUsuario(email, password);

        if(usuario != null){
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", usuario);

            if("ADMIN".equals(usuario.getRol())){
                response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            } else {
                response.sendRedirect(request.getContextPath() + "/dashboard");
            }

        } else{
            request.setAttribute("errorMensaje", "Credenciales incorrectas");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }
}
package com.reservas.util;
import com.reservas.model.Usuario;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/dashboard", "/reservas/*", "/perfil/*", "/admin/*"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String path = httpRequest.getRequestURI();

        HttpSession session = httpRequest.getSession(false);
        Usuario usuarioLogueado = (session != null) ? (Usuario) session.getAttribute("usuarioLogueado") : null;

        if (usuarioLogueado == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login?error=acceso_denegado");
            return;
        }

        if (path.contains("/admin") && !"ADMIN".equals(usuarioLogueado.getRol())) {
            httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Escalada de privilegios detectada. Acceso denegado.");
            return;
        }

        chain.doFilter(request, response);
    }
}
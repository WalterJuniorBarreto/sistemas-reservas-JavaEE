package com.reservas.service;

import com.reservas.dao.UsuarioDAO;
import com.reservas.dto.UsuarioRegistroDTO;
import com.reservas.model.Usuario;
import org.mindrot.jbcrypt.BCrypt;

public class UsuarioService {

    private final UsuarioDAO usuarioDAO;

    public UsuarioService(){
        this.usuarioDAO = new UsuarioDAO();
    }

    public String registrarNuevoUsuario(UsuarioRegistroDTO dto){
        if(!dto.getPassword().equals(dto.getConfirmarPassword())){
            return "ERROR: Las contraseñas no coinciden";
        }

        if(dto.getPassword().length() < 8){
            return "ERROR: La contraseña debe tener al menos 8 caracteres";
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(dto.getNombre());
        nuevoUsuario.setEmail(dto.getEmail());
        nuevoUsuario.setPassword(dto.getPassword());
        nuevoUsuario.setRol("CLIENTE");

        boolean exito = usuarioDAO.registrarUsuario(nuevoUsuario);

        if(exito){
            return "EXITO: Usuario registrado correctamente";
        } else {
            return "ERROR: No se pudo registrar. Es posible que el correo ya este en uso";
        }
    }


    public Usuario loginUsuario(String email, String passwordPlana){
        Usuario usuario = usuarioDAO.buscarPorEmail(email);

        if(usuario != null){
            if(BCrypt.checkpw(passwordPlana, usuario.getPassword())){
                usuario.setPassword(null);
                return usuario;
            }
        }
        return null;
    }
}

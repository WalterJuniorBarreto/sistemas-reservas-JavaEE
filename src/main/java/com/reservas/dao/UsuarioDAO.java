package com.reservas.dao;

import com.reservas.model.Usuario;
import com.reservas.util.Conexion;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean registrarUsuario(Usuario usuario){
        String sql = "INSERT INTO usuarios (nombre, email, password, rol) VALUES (?, ?, ?, ?::rol_usuario)";

        try(Connection connection = Conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            String hashPassword = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt(12));

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, hashPassword);
            ps.setString(4, usuario.getRol() != null ? usuario.getRol() : "CLIENTE");

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e){
            System.err.println("Error en la persistencia del usuario: " + e.getMessage());
            return false;
        }
    }


    public Usuario buscarPorEmail(String email){
        String sql = "SELECT id, nombre, email, password, rol FROM usuarios WHERE email = ?";

        try(Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, email);
            try(java.sql.ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id"));
                    u.setNombre(rs.getString("nombre"));
                    u.setEmail(rs.getString("email"));
                    u.setPassword(rs.getString("password"));
                    u.setRol(rs.getString("rol"));
                    return u;
                }
            }
        } catch (SQLException e){
            System.err.println("Error al buscar usuario: " + e.getMessage());
        }
        return null;
    }

}

package com.reservas.dao;

import com.reservas.model.Reserva;
import com.reservas.model.Usuario;
import com.reservas.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {


    public List<Reserva> listarPorUsuario(int usuarioId){
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT id, servicio, fecha_hora, estado FROM reservas WHERE usuario_id = ? ORDER BY fecha_hora DESC";

        try(Connection connection = Conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setInt(1, usuarioId);
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()){
                    Reserva reserva = new Reserva();
                    reserva.setId(rs.getInt("id"));
                    reserva.setServicio(rs.getString("servicio"));
                    reserva.setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime());
                    reserva.setEstado(rs.getString("estado"));
                    lista.add(reserva);
                }
            }
        } catch (SQLException e){
            System.err.println("Error al listar reservas: " + e.getMessage());
        }
        return lista;
    }

    public boolean registrar(Reserva reserva){
        String sql = "INSERT INTO reservas (usuario_id, servicio, fecha_hora, estado) VALUES (?,?,?, CAST(? AS estado_reserva))";

        try(Connection conn = Conexion.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, reserva.getUsuario().getId());
            ps.setString(2, reserva.getServicio());
            ps.setTimestamp(3, Timestamp.valueOf(reserva.getFechaHora()));
            ps.setString(4, reserva.getEstado());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e){
            System.err.println("Error al registrar nueva reserva: " + e.getMessage());
            return false;
        }
    }

    public boolean cancelarReserva(int reservaId, int usuarioId){
        String sql = "UPDATE reservas SET estado = CAST('CANCELADA' AS estado_reserva) WHERE id = ? AND usuario_id = ?";

        try(Connection connection = Conexion.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, reservaId);
            ps.setInt(2, usuarioId);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e){
            System.err.println("Error al aplicar el soft delete (Cancelar)" + e.getMessage());
            return false;
        }
    }


    public List<Reserva> listarTodas() {
        List<Reserva> lista = new ArrayList<>();
        String sql = "SELECT r.id, r.servicio, r.fecha_hora, r.estado, u.nombre AS nombre_cliente " +
                "FROM reservas r INNER JOIN usuarios u ON r.usuario_id = u.id " +
                "ORDER BY r.fecha_hora DESC";

        try (Connection con = Conexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reserva r = new Reserva();
                r.setId(rs.getInt("id"));
                r.setServicio(rs.getString("servicio"));
                r.setFechaHora(rs.getTimestamp("fecha_hora").toLocalDateTime());
                r.setEstado(rs.getString("estado"));

                Usuario dueño = new Usuario();
                dueño.setNombre(rs.getString("nombre_cliente"));
                r.setUsuario(dueño);

                lista.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar todas las reservas (Admin): " + e.getMessage());
        }
        return lista;
    }
}

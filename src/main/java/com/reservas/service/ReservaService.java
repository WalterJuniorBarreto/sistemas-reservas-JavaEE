package com.reservas.service;

import com.reservas.dao.ReservaDAO;
import com.reservas.model.Reserva;

import java.time.LocalDateTime;

public class ReservaService {

    private ReservaDAO reservaDAO;

    public ReservaService(){
        this.reservaDAO = new ReservaDAO();

    }

    public String crearReserva(Reserva reserva){
        if(reserva.getFechaHora().isBefore(LocalDateTime.now())){
            return "ERROR: La fecha y hora de la reserva no puede ser en el pasado";
        }

        if(reserva.getServicio() == null || reserva.getServicio().trim().isEmpty()){
            return "ERROR: Debes especificar un servicio";
        }

        reserva.setEstado("PENDIENTE");
        boolean exito = reservaDAO.registrar(reserva);

        if(exito){
            return "EXITO: Reserva creada correctamente";
        } else {
            return "ERROR: Hubo un problema al guardar la reserva en el sistema";
        }
    }

    public String cancelarReservaSegura(int reservaId, int usuarioId){

        boolean exito = reservaDAO.cancelarReserva(reservaId, usuarioId);

        if(exito){
            return "EXITO: La reserva ha sido cancelada correctamente";
        } else {
            return "ERROR: Operacion denegada. La reserva no exite o no tienes permisos";
        }
    }
}

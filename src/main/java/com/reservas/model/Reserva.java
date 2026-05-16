package com.reservas.model;

import java.time.LocalDateTime;

public class Reserva {

    private int id;
    private Usuario usuario;
    private String servicio;
    private LocalDateTime fechaHora;
    private String estado;

    public Reserva(){}

    public Reserva(Usuario usuario, String servicio, LocalDateTime fechaHora, String estado){
        this.usuario = usuario;
        this.servicio = servicio;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

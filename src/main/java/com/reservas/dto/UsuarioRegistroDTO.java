package com.reservas.dto;

public class UsuarioRegistroDTO {

    private String nombre;
    private String email;
    private String password;
    private String confirmarPassword;

    public UsuarioRegistroDTO(String nombre, String email, String password, String confirmarPassword){
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.confirmarPassword = confirmarPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmarPassword() {
        return confirmarPassword;
    }

    public void setConfirmarPassword(String confirmarPassword) {
        this.confirmarPassword = confirmarPassword;
    }
}


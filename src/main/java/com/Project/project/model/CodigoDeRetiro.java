package com.Project.project.model;

import java.time.LocalDateTime;

public class CodigoDeRetiro {
    private String codigo;
    private LocalDateTime fechaGeneracion;
    private LocalDateTime fechaExpiracion;
    private String estado;
    private Usuario usuarioAsociante;

    public CodigoDeRetiro() {
    }

    public CodigoDeRetiro(String codigo, LocalDateTime fechaGeneracion, LocalDateTime fechaExpiracion, String estado, Usuario usuarioAsociante) {
        this.codigo = codigo;
        this.fechaGeneracion = fechaGeneracion;
        this.fechaExpiracion = fechaExpiracion;
        this.estado = estado;
        this.usuarioAsociante = usuarioAsociante;
    }

    public boolean validarCodigo(String codigoIngresado) {
        if (!verificarExpiracion() && this.estado.equals("ACTIVO")) {
            return this.codigo.equals(codigoIngresado);
        }
        return false;
    }

    public boolean verificarExpiracion() {
        if (LocalDateTime.now().isAfter(fechaExpiracion)) {
            this.estado = "EXPIRADO";
            return true;
        }
        return false;
    }

    public void cancelarCodigo() {
        this.estado = "CANCELADO";
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuarioAsociante() {
        return usuarioAsociante;
    }

    public void setUsuarioAsociante(Usuario usuarioAsociante) {
        this.usuarioAsociante = usuarioAsociante;
    }
}

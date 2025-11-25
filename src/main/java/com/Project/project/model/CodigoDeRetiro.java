package com.Project.project.model;

import java.time.LocalDateTime;
import java.util.UUID;

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

    public String generarCodigo() {
        // Genera un código único de 6 caracteres (por ejemplo para retiro)
        this.codigo = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        this.fechaGeneracion = LocalDateTime.now();
        this.fechaExpiracion = fechaGeneracion.plusMinutes(10); // expira en 10 minutos
        this.estado = "ACTIVO";

        return this.codigo;
    }

    public boolean validarCodigo(String codigoIngresado){
        if (verificarExpiracion()){
            return false;
        }
        if (!"ACTIVO".equals(this.estado)) {
            return false;
        }
        return this.codigo.equals(codigoIngresado);
    }

    public boolean verificarExpiracion() {
        if (LocalDateTime.now().isAfter(this.fechaExpiracion)) {
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

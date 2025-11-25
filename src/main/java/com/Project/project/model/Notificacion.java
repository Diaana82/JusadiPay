package com.Project.project.model;

import java.time.LocalDateTime;

public class Notificacion {
    private String idNotificacion;
    private Usuario usuarioDestino;
    private String contenido;
    private LocalDateTime fechaEnvio;
    private String tipoNotificacion;
    private Transaccion transaccionAsociada;
    private CodigoDeRetiro codigoRetiroAsociado;

    public Notificacion() {
    }

    public Notificacion(String idNotificacion, Usuario usuarioDestino, String contenido, LocalDateTime fechaEnvio, String tipoNotificacion, Transaccion transaccionAsociada, CodigoDeRetiro codigoRetiroAsociado) {
        this.idNotificacion = idNotificacion;
        this.usuarioDestino = usuarioDestino;
        this.contenido = contenido;
        this.fechaEnvio = fechaEnvio;
        this.tipoNotificacion = tipoNotificacion;
        this.transaccionAsociada = transaccionAsociada;
        this.codigoRetiroAsociado = codigoRetiroAsociado;
    }

    public String getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(String idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public Usuario getUsuarioDestino() {
        return usuarioDestino;
    }

    public void setUsuarioDestino(Usuario usuarioDestino) {
        this.usuarioDestino = usuarioDestino;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public Transaccion getTransaccionAsociada() {
        return transaccionAsociada;
    }

    public void setTransaccionAsociada(Transaccion transaccionAsociada) {
        this.transaccionAsociada = transaccionAsociada;
    }

    public CodigoDeRetiro getCodigoRetiroAsociado() {
        return codigoRetiroAsociado;
    }

    public void setCodigoRetiroAsociado(CodigoDeRetiro codigoRetiroAsociado) {
        this.codigoRetiroAsociado = codigoRetiroAsociado;
    }

    public boolean enviar() {
        if (usuarioDestino == null || contenido == null || contenido.isEmpty()) {
            return false;
        }

        this.fechaEnvio = LocalDateTime.now();

        System.out.println("Notificación enviada a: " + usuarioDestino.getNombre());
        System.out.println("Contenido: " + contenido);

        return true;
    }
    public void notificarCodigoRetiro(CodigoDeRetiro codigo) {
        this.codigoRetiroAsociado = codigo;

        this.tipoNotificacion = "Código de retiro";
        this.contenido = "Tu código de retiro es: " + codigo.getCodigo();
    }
}

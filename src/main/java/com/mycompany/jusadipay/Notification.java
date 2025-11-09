package com.mycompany.jusadipay;

import java.time.LocalDateTime;

public class Notification {
    private String idNotificacion;
    private User usuarioDestino;
    private String contenido;
    private LocalDateTime fechaEnvio;
    private LocalDateTime horaEnvio;
    private Transaction transaccionAsociada;
    private withdrawalcode codigoRetiroAsociado;

    public Notification() {
    }

    public Notification(String idNotificacion, User usuarioDestino, String contenido, LocalDateTime fechaEnvio, LocalDateTime horaEnvio, Transaction transaccionAsociada, withdraealcode codigoRetiroAsociado) {
        this.idNotificacion = idNotificacion;
        this.usuarioDestino = usuarioDestino;
        this.contenido = contenido;
        this.fechaEnvio = fechaEnvio;
        this.horaEnvio = horaEnvio;
        this.transaccionAsociada = transaccionAsociada;
        this.codigoRetiroAsociado = codigoRetiroAsociado;
    }
    

    public String getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(String idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public User getUsuarioDestino() {
        return usuarioDestino;
    }

    public void setUsuarioDestino(User usuarioDestino) {
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

    public LocalDateTime getHoraEnvio() {
        return horaEnvio;
    }

    public void setHoraEnvio(LocalDateTime horaEnvio) {
        this.horaEnvio = horaEnvio;
    }

    public Transaction getTransaccionAsociada() {
        return transaccionAsociada;
    }

    public void setTransaccionAsociada(Transaction transaccionAsociada) {
        this.transaccionAsociada = transaccionAsociada;
    }

    public withdrawalcode getCodigoRetiroAsociado() {
        return codigoRetiroAsociado;
    }

    public void setCodigoRetiroAsociado(withdrawalcode codigoRetiroAsociado) {
        this.codigoRetiroAsociado = codigoRetiroAsociado;
    }
}

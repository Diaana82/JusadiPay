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

    public Notification(String idNotificacion, User usuarioDestino, String contenido, LocalDateTime fechaEnvio, LocalDateTime horaEnvio, Transaction transaccionAsociada, withdrawalcode codigoRetiroAsociado) {
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
    
    // Envía la notificación (valida y sella tiempos/ID)
public boolean enviar() {
    if (usuarioDestino == null) return false;
    if (contenido == null || contenido.isBlank()) return false;

    if (this.idNotificacion == null || this.idNotificacion.isBlank()) {
        this.idNotificacion = "NTF-" + System.nanoTime();
    }
    LocalDateTime ahora = LocalDateTime.now();
    this.fechaEnvio = ahora;
    this.horaEnvio = ahora; // si manejas fecha y hora por separado, puedes diferenciarlas

    return true;
}

// Construye y envía una notificación específica para Código de Retiro
public void notificarCodigoRetiro(withdrawalcode codigo) {
    if (codigo == null) return;

    this.codigoRetiroAsociado = codigo;
    this.usuarioDestino = codigo.getCuentaAsociante();

    StringBuilder sb = new StringBuilder();
    sb.append("Código de retiro generado: ").append(codigo.getCodigo()).append('\n')
      .append("Estado: ").append(codigo.getEstado()).append('\n')
      .append("Punto de retiro: ").append(
            codigo.getPuntoretiro() != null ? codigo.getPuntoretiro() : "N/A").append('\n')
      .append("Vence: ").append(
            codigo.getFechaexpiracion() != null ? codigo.getFechaexpiracion() : "N/A");
    this.contenido = sb.toString();

    this.enviar();
}

}

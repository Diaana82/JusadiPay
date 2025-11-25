package com.Project.project.model;

import java.time.LocalDateTime;

public class Transaccion {

    private int idTransaccion;
    private LocalDateTime fecha;
    private Double monto;
    private String estado;
    private Cuenta cuentaOrigen;
    private Cuenta cuentaDestino;
    private String puntoRetiro;

    public Transaccion() {
    }

    public Transaccion(int idTransaccion, LocalDateTime fecha, Double monto,
                       String estado, Cuenta cuentaOrigen,
                       Cuenta cuentaDestino, String puntoRetiro) {

        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.monto = monto;
        this.estado = estado;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.puntoRetiro = puntoRetiro;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public String getPuntoRetiro() {
        return puntoRetiro;
    }

    public void setPuntoRetiro(String puntoRetiro) {
        this.puntoRetiro = puntoRetiro;
    }

    // Método padre
    public String obtenerDetalles() {

        String origen = (cuentaOrigen != null) ? cuentaOrigen.getNumCuenta() : "N/A";
        String destino = (cuentaDestino != null) ? cuentaDestino.getNumCuenta() : "N/A";

        return "Transacción ID: " + idTransaccion +
                "\nFecha: " + fecha +
                "\nMonto: $" + monto +
                "\nEstado: " + estado +
                "\nCuenta Origen: " + origen +
                "\nCuenta Destino: " + destino +
                "\nPunto de Retiro: " + (puntoRetiro != null ? puntoRetiro : "N/A");
    }
}

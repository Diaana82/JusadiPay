package com.mycompany.jusadipay;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String idTransaccion;
    private LocalDateTime fecha;
    private Double monto;
    private String tipoTransaccion;
    private String estado;
    private Account cuentaOrigen;
    private Account cuentaDestino;
    private UserAccess idAcceso;

    public Transaction() {
    }
    
    public Transaction(String idTransaccion, LocalDateTime fecha, Double monto, String tipoTransaccion, String estado, Account cuentaOrigen, Account cuentaDestino, UserAccess idAcceso) {
        this.idTransaccion = idTransaccion;
        this.fecha = fecha;
        this.monto = monto;
        this.tipoTransaccion = tipoTransaccion;
        this.estado = estado;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.idAcceso = idAcceso;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
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

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Account getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Account cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public Account getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Account cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public UserAccess getIdAcceso() {
        return idAcceso;
    }

    public void setIdAcceso(UserAccess idAcceso) {
        this.idAcceso = idAcceso;
    }
    
    public String obtenerDetaller() {
        String id = idTransaccion != null ? idTransaccion : "N/A";
        String f = (fecha != null)
                ? fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                : "N/A";
        String m = (monto != null) ? String.format("%,.2f", monto) : "0.00";
        String tipo = (tipoTransaccion != null) ? tipoTransaccion : "N/A";
        String est = (estado != null) ? estado : "N/A";

        String origen = "N/A";
        if (cuentaOrigen != null) {
            try {
                origen = cuentaOrigen.getNumCuenta() != null ? cuentaOrigen.getNumCuenta() : "N/A";
            } catch (Exception e) {
                origen = cuentaOrigen.toString();
            }
        }

        String destino = "N/A";
        if (cuentaDestino != null) {
            try {
                destino = cuentaDestino.getNumCuenta() != null ? cuentaDestino.getNumCuenta() : "N/A";
            } catch (Exception e) {
                destino = cuentaDestino.toString();
            }
        }

        String acceso = (idAcceso != null) ? idAcceso.toString() : "N/A";

        return "Transaccion{id=" + id +
               ", fecha=" + f +
               ", monto=" + m +
               ", tipo=" + tipo +
               ", estado=" + est +
               ", origen=" + origen +
               ", destino=" + destino +
               ", acceso=" + acceso + "}";
    }
    
}

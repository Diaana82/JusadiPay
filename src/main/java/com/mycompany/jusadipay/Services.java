package com.mycompany.jusadipay;
import java.time.LocalDateTime;

public class Services {
    private supplier proveedor;
    private double monto;
    private String numReferencia;
    private String tipoServicio;
    private LocalDateTime fechaPago;
    private String estado;
    private Account cuentaOrigen;
    private Transaction transaccion;

    public Services() {
    }

    public Services(supplier proveedor, double monto, String numReferencia, String tipoServicio, LocalDateTime fechaPago, String estado, Account cuentaOrigen, Transaction transaccion) {
        this.proveedor = proveedor;
        this.monto = monto;
        this.numReferencia = numReferencia;
        this.tipoServicio = tipoServicio;
        this.fechaPago = fechaPago;
        this.estado = estado;
        this.cuentaOrigen = cuentaOrigen;
        this.transaccion = transaccion;
    }

    
    
    public supplier getProveedor() {
        return proveedor;
    }

    public void setProveedor(supplier proveedor) {
        this.proveedor = proveedor;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getNumReferencia() {
        return numReferencia;
    }

    public void setNumReferencia(String numReferencia) {
        this.numReferencia = numReferencia;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
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

    public Transaction getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaction transaccion) {
        this.transaccion = transaccion;
    }
    
    
}

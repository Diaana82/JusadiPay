
package com.mycompany.jusadipay;
import java.time.LocalDateTime;
public class pocket {
    private String nombrebolsillo;
    private Double montoTotal;
    private Double montoActual;
    private Double porcentajeAuto;
    private String estado;
    private Account cuentaAsociada;
    private LocalDateTime fechaObjetivo;

    public pocket() {
    }

    public pocket(String nombrebolsillo, Double montoTotal, Double montoActual, Double porcentajeAuto, String estado, Account cuentaAsociada, LocalDateTime fechaObjetivo) {
        this.nombrebolsillo = nombrebolsillo;
        this.montoTotal = montoTotal;
        this.montoActual = montoActual;
        this.porcentajeAuto = porcentajeAuto;
        this.estado = estado;
        this.cuentaAsociada = cuentaAsociada;
        this.fechaObjetivo = fechaObjetivo;
    }

    public String getNombrebolsillo() {
        return nombrebolsillo;
    }

    public void setNombrebolsillo(String nombrebolsillo) {
        this.nombrebolsillo = nombrebolsillo;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Double getMontoActual() {
        return montoActual;
    }

    public void setMontoActual(Double montoActual) {
        this.montoActual = montoActual;
    }

    public Double getPorcentajeAuto() {
        return porcentajeAuto;
    }

    public void setPorcentajeAuto(Double porcentajeAuto) {
        this.porcentajeAuto = porcentajeAuto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Account getCuentaAsociada() {
        return cuentaAsociada;
    }

    public void setCuentaAsociada(Account cuentaAsociada) {
        this.cuentaAsociada = cuentaAsociada;
    }

    public LocalDateTime getFechaObjetivo() {
        return fechaObjetivo;
    }

    public void setFechaObjetivo(LocalDateTime fechaObjetivo) {
        this.fechaObjetivo = fechaObjetivo;
    }
    
    
    
}

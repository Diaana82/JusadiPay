package com.mycompany.jusadipay;

import java.time.LocalDateTime;

public class pocket {
    private String nombreBolsillo;
    private Double montoTotalMeta;
    private Double montoActual;
    private Double porcentajeAuto;
    private String estado;
    private Account cuentaAsociada;
    private LocalDateTime fechaObjetivo;

    public pocket() {
    }

    public pocket(String nombreBolsillo, Double montoTotalMeta, Double montoActual, Double porcentajeAuto, String estado, Account cuentaAsociada, LocalDateTime fechaObjetivo) {
        this.nombreBolsillo = nombreBolsillo;
        this.montoTotalMeta = montoTotalMeta;
        this.montoActual = montoActual;
        this.porcentajeAuto = porcentajeAuto;
        this.estado = estado;
        this.cuentaAsociada = cuentaAsociada;
        this.fechaObjetivo = fechaObjetivo;
    }
     
    public String getNombreBolsillo() {
        return nombreBolsillo;
    }

    public void setNombreBolsillo(String nombrebolsillo) {
        this.nombreBolsillo = nombrebolsillo;
    }

    public Double getMontoTotalMeta() {
        return montoTotalMeta;
    }

    public void setMontoTotalMeta(Double montoTotalMeta) {
        this.montoTotalMeta = montoTotalMeta;
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
    
    public boolean crearMeta() {
        if (montoTotalMeta == null || montoTotalMeta <= 0) return false;
        if (cuentaAsociada == null) return false;
        if ("ACTIVA".equalsIgnoreCase(estado)) return false;

        if (montoActual == null) montoActual = 0.0;
        if (porcentajeAuto == null) porcentajeAuto = 0.0;

        this.estado = "ACTIVA";
        return true;
    }

    public boolean distribuirIngresos(double montoIngresos) {
        if (!"ACTIVA".equalsIgnoreCase(estado)) 
            return false;
        if (montoIngresos <= 0) 
            return false;
        if (montoTotalMeta == null || montoTotalMeta <= 0) 
            return false;
        if (montoActual == null) montoActual = 0.0;
        if (porcentajeAuto == null || porcentajeAuto <= 0) 
            return false;
        if (cuentaAsociada == null) 
            return false;
    
        double faltante = Math.max(0.0, montoTotalMeta - montoActual);
            if (faltante == 0.0) 
                return false;
                
        double aporte = montoIngresos * (porcentajeAuto / 100.0);
            if (aporte <= 0) 
                return false;
            
        aporte = Math.min(aporte, faltante);
    
        if (cuentaAsociada.getSaldo() < aporte) 
            return false;
    
        cuentaAsociada.setSaldo(cuentaAsociada.getSaldo() - aporte);
        montoActual += aporte;
            return true;
    }
}

package com.Project.project.model;

import java.time.LocalDateTime;

public class Bolsillo {

    private String NombreBolsillo;
    private Double MontoTotalMeta;
    private Double MontoActual;
    private Double PorcentajeAuto;
    private String Estado;
    private Cuenta CuentaAsociada;
    private LocalDateTime FechaObjetivo;

    public Bolsillo() {
    }

    public Bolsillo(String nombreBolsillo, Double montoTotalMeta, Double montoActual, Double porcentajeAuto, String estado, Cuenta cuentaAsociada, LocalDateTime fechaObjetivo) {
        NombreBolsillo = nombreBolsillo;
        MontoTotalMeta = montoTotalMeta;
        MontoActual = montoActual;
        PorcentajeAuto = porcentajeAuto;
        Estado = estado;
        CuentaAsociada = cuentaAsociada;
        FechaObjetivo = fechaObjetivo;
    }

    public String getNombreBolsillo() {
        return NombreBolsillo;
    }

    public void setNombreBolsillo(String nombreBolsillo) {
        NombreBolsillo = nombreBolsillo;
    }

    public Double getMontoTotalMeta() {
        return MontoTotalMeta;
    }

    public void setMontoTotalMeta(Double montoTotalMeta) {
        MontoTotalMeta = montoTotalMeta;
    }

    public Double getMontoActual() {
        return MontoActual;
    }

    public void setMontoActual(Double montoActual) {
        MontoActual = montoActual;
    }

    public Double getPorcentajeAuto() {
        return PorcentajeAuto;
    }

    public void setPorcentajeAuto(Double porcentajeAuto) {
        PorcentajeAuto = porcentajeAuto;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public Cuenta getCuentaAsociada() {
        return CuentaAsociada;
    }

    public void setCuentaAsociada(Cuenta cuentaAsociada) {
        CuentaAsociada = cuentaAsociada;
    }

    public LocalDateTime getFechaObjetivo() {
        return FechaObjetivo;
    }

    public void setFechaObjetivo(LocalDateTime fechaObjetivo) {
        FechaObjetivo = fechaObjetivo;
    }

    public boolean crearMeta() {
        if (MontoTotalMeta == null || MontoTotalMeta <= 0) return false;
        if (CuentaAsociada == null) return false;
        if ("ACTIVA".equalsIgnoreCase(Estado)) return false;

        if (MontoActual == null) MontoActual = 0.0;
        if (PorcentajeAuto == null) PorcentajeAuto = 0.0;

        this.Estado = "ACTIVA";
        return true;
    }

    public boolean distribuirIngresos(double montoIngresos) {
        if (!"ACTIVA".equalsIgnoreCase(Estado))
            return false;
        if (montoIngresos <= 0)
            return false;
        if (MontoTotalMeta == null || MontoTotalMeta <= 0)
            return false;
        if (MontoActual == null) MontoActual = 0.0;
        if (PorcentajeAuto == null || PorcentajeAuto <= 0)
            return false;
        if (CuentaAsociada == null)
            return false;

        double faltante = Math.max(0.0, MontoTotalMeta - MontoActual);
        if (faltante == 0.0)
            return false;

        double aporte = montoIngresos * (PorcentajeAuto / 100.0);
        if (aporte <= 0)
            return false;

        aporte = Math.min(aporte, faltante);

        if (CuentaAsociada.getSaldo() < aporte)
            return false;

        CuentaAsociada.setSaldo(CuentaAsociada.getSaldo() - aporte);
        MontoActual += aporte;
        return true;
    }
}

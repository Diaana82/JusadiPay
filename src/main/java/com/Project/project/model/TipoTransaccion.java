package com.Project.project.model;

import java.time.LocalDateTime;

public class TipoTransaccion extends Transaccion {

    private int idTipoTransaccion;
    private String nombreTipoTransaccion;

    public TipoTransaccion() {
        super();
    }

    public TipoTransaccion(
            int idTransaccion,
            LocalDateTime fecha,
            Double monto,
            String estado,
            Cuenta cuentaOrigen,
            Cuenta cuentaDestino,
            String puntoRetiro,
            int idTipoTransaccion,
            String nombreTipoTransaccion
    ) {
        super(idTransaccion, fecha, monto, estado, cuentaOrigen, cuentaDestino, puntoRetiro);
        this.idTipoTransaccion = idTipoTransaccion;
        this.nombreTipoTransaccion = nombreTipoTransaccion;
    }

    public int getIdTipoTransaccion() {
        return idTipoTransaccion;
    }

    public void setIdTipoTransaccion(int idTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
    }

    public String getNombreTipoTransaccion() {
        return nombreTipoTransaccion;
    }

    public void setNombreTipoTransaccion(String nombreTipoTransaccion) {
        this.nombreTipoTransaccion = nombreTipoTransaccion;
    }

    // Sobrescribir el método del padre
    @Override
    public String obtenerDetalles() {

        return super.obtenerDetalles() +
                "\nTipo de Transacción: " + nombreTipoTransaccion +
                "\nID Tipo: " + idTipoTransaccion;
    }
}

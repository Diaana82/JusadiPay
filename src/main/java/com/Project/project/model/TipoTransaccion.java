package com.Project.project.model;


public class TipoTransaccion {
    private int idTipoTransaccion;
    private String nombreTipoTransaccion;

    public TipoTransaccion() {
    }

    public TipoTransaccion(int idTipoTransaccion, String nombreTipoTransaccion) {
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
}

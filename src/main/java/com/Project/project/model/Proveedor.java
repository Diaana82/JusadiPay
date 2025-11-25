package com.Project.project.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.time.LocalDateTime;

public class Proveedor {
    private String nombreproveedor;
    private String tipoServicio;
    private LocalDateTime fechaRegistro;

    private final List<Servicios> servicios = new ArrayList<>();

    public Proveedor() {
    }

    public Proveedor(LocalDateTime fechaRegistro, String nombreproveedor, String tipoServicio) {
        this.fechaRegistro = fechaRegistro;
        this.nombreproveedor = nombreproveedor;
        this.tipoServicio = tipoServicio;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNombreproveedor() {
        return nombreproveedor;
    }

    public void setNombreproveedor(String nombreproveedor) {
        this.nombreproveedor = nombreproveedor;
    }

    public List<Servicios> getServicios() {
        return servicios;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    //LOGICA

    public boolean registrarProveedor() {
        if (this.nombreproveedor == null || this.nombreproveedor.isBlank()) return false;

        for (Proveedor p : REGISTRO) {
            if (p.nombreproveedor != null &&
                    p.nombreproveedor.equalsIgnoreCase(this.nombreproveedor)) {
                return false; // Ya existe
            }
        }
        if (this.fechaRegistro == null) {
            this.fechaRegistro = LocalDateTime.now();
        }
        REGISTRO.add(this);
        return true;
    }


    public void eliminarProveedor() {
        REGISTRO.removeIf(p ->
                p == this ||
                        (p.nombreproveedor != null &&
                                p.nombreproveedor.equalsIgnoreCase(this.nombreproveedor))
        );
    }

}
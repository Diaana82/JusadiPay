package com.mycompany.jusadipay;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class supplier {
    
    private String nombreproveedor;
    private String tipoServicio;
    private LocalDateTime fechaRegistro;
    
    private static final List<supplier> REGISTRO = new ArrayList<>();
    private final List<Services> servicios = new ArrayList<>();

    public supplier() {
    }

    public supplier(String nombreproveedor, String tipoServicio, LocalDateTime fechaRegistro) {
        this.nombreproveedor = nombreproveedor;
        this.tipoServicio = tipoServicio;
        this.fechaRegistro = fechaRegistro;
    }
    
    

    public String getNombreProveedor() {
        return nombreproveedor;
    }

    public void setNombreproveedor(String nombreproveedor) {
        this.nombreproveedor = nombreproveedor;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
public boolean registrarProveedor() {
    if (this.nombreproveedor == null || this.nombreproveedor.isBlank()) return false;
   
    for (supplier s : REGISTRO) {
        if (s.nombreproveedor != null && s.nombreproveedor.equalsIgnoreCase(this.nombreproveedor)) {
            return false; 
        }
    }
    if (this.fechaRegistro == null) {
        this.fechaRegistro = java.time.LocalDateTime.now();
    }
    REGISTRO.add(this);
    return true;
}


public void eliminarProveedor() {
    REGISTRO.removeIf(s -> s == this
        || (s.nombreproveedor != null && s.nombreproveedor.equalsIgnoreCase(this.nombreproveedor)));
}


public List<Services> obtenerServicios() {
    return Collections.unmodifiableList(servicios);
    
}
    
    
}

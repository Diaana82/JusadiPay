package com.mycompany.jusadipay;
import java.time.LocalDateTime;
public class supplier {
    
    private String nombreproveedor;
    private String tipoServicio;
    private LocalDateTime fechaRegistro;

    public supplier() {
    }

    public supplier(String nombreproveedor, String tipoServicio, LocalDateTime fechaRegistro) {
        this.nombreproveedor = nombreproveedor;
        this.tipoServicio = tipoServicio;
        this.fechaRegistro = fechaRegistro;
    }
    
    

    public String getNombreproveedor() {
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
    
    
}

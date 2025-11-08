
package com.mycompany.jusadipay;
import java.time.LocalDateTime;

public class withdrawalcode {
    private int codigo;
    private LocalDateTime fechageneracion;
    private LocalDateTime fechaexpiracion;
    private String estado;
    private User cuentaAsociante;
    private String puntoretiro;

    public withdrawalcode() {
    }

    public withdrawalcode(int codigo, LocalDateTime fechageneracion, LocalDateTime fechaexpiracion, String estado, User cuentaAsociante, String puntoretiro) {
        this.codigo = codigo;
        this.fechageneracion = fechageneracion;
        this.fechaexpiracion = fechaexpiracion;
        this.estado = estado;
        this.cuentaAsociante = cuentaAsociante;
        this.puntoretiro = puntoretiro;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getFechageneracion() {
        return fechageneracion;
    }

    public void setFechageneracion(LocalDateTime fechageneracion) {
        this.fechageneracion = fechageneracion;
    }

    public LocalDateTime getFechaexpiracion() {
        return fechaexpiracion;
    }

    public void setFechaexpiracion(LocalDateTime fechaexpiracion) {
        this.fechaexpiracion = fechaexpiracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public User getCuentaAsociante() {
        return cuentaAsociante;
    }

    public void setCuentaAsociante(User cuentaAsociante) {
        this.cuentaAsociante = cuentaAsociante;
    }

    public String getPuntoretiro() {
        return puntoretiro;
    }

    public void setPuntoretiro(String puntoretiro) {
        this.puntoretiro = puntoretiro;
    }
    
    
}

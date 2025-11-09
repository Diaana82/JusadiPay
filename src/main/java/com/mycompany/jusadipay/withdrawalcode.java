
package com.mycompany.jusadipay;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.Random;

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
    
    private static final int EXP_MINUTOS = 30;
    private static final Random RND = new Random();

    public String generarCodigo() {
        int nuevo = 100000 + RND.nextInt(900000);
        this.codigo = nuevo;
        this.fechageneracion = LocalDateTime.now();
        this.fechaexpiracion = this.fechageneracion.plusMinutes(EXP_MINUTOS);
        this.estado = "PENDIENTE";
        return String.valueOf(nuevo);
    }

    public boolean validarCodigo(String codigo) {
        if (codigo == null) return false;
        if (!String.valueOf(this.codigo).equals(codigo)) return false;
        if (!"PENDIENTE".equalsIgnoreCase(this.estado)) return false;
        if (verificarExpiracion()) return false;
        return true;
    }

    public boolean verificarExpiracion() {
        if (this.fechaexpiracion == null) return true;
        boolean expiro = LocalDateTime.now().isAfter(this.fechaexpiracion);
        if (expiro) this.estado = "EXPIRADO";
        return expiro;
    }

    public void cancelarCodigo() {
        this.estado = "CANCELADO";
    }
}

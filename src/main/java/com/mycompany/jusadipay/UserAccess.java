
package com.mycompany.jusadipay;
import java.time.LocalDateTime;
public class UserAccess {
    private String Usuario;
    private String ip;
    private LocalDateTime fechahora;
    private String dispositivo;

    public UserAccess() {
    }

    public UserAccess(String Usuario, String ip, LocalDateTime fechahora, String dispositivo) {
        this.Usuario = Usuario;
        this.ip = ip;
        this.fechahora = fechahora;
        this.dispositivo = dispositivo;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LocalDateTime getFechahora() {
        return fechahora;
    }

    public void setFechahora(LocalDateTime fechahora) {
        this.fechahora = fechahora;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }
    
}

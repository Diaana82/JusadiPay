package com.mycompany.jusadipay;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    
    private static final List<UserAccess> ACCESOS = new ArrayList<>();
    
    public void registrarAccesos() {
        if (this.fechahora == null) {
            this.fechahora = LocalDateTime.now();
        }
        ACCESOS.add(new UserAccess(this.Usuario, this.ip, this.fechahora, this.dispositivo));
    }
    
    public static List<UserAccess> mostrarAccesos() {
    return Collections.unmodifiableList(ACCESOS);
    }
    
    public static List<UserAccess> FiltrarPorUsuario(User usuario) {
        if (usuario == null) return List.of();
        List<UserAccess> out = new ArrayList<>();
        for (UserAccess a : ACCESOS) {
            if (a.getUsuario() != null && a.getUsuario().equalsIgnoreCase(usuario.getNombre())) {
                out.add(a);
            }
        }
        return out;
    }
}

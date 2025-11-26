package com.Project.project.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AccesoUsuario {

    private Usuario usuario;
    private String ip;
    private LocalDateTime fechahora;
    private String dispositivo;

    public AccesoUsuario() {
    }

    public AccesoUsuario(Usuario usuario, String ip, LocalDateTime fechahora, String dispositivo) {
        this.usuario = usuario;
        this.ip = ip;
        this.fechahora = fechahora;
        this.dispositivo = dispositivo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    private static final List<AccesoUsuario> ACCESOS = new ArrayList<>();

    public void registrarAccesos() {
        if (this.fechahora == null) {
            this.fechahora = LocalDateTime.now();
        }


        ACCESOS.add(this);
    }

    public static List<AccesoUsuario> mostrarAccesos() {

        return Collections.unmodifiableList(ACCESOS);
    }

    public static List<AccesoUsuario> FiltrarPorUsuario(Usuario usuario) {
        if (usuario == null) return List.of();

        List<AccesoUsuario> out = new ArrayList<>();
        for (AccesoUsuario a : ACCESOS) {
            if (a.usuario != null &&
                    a.usuario.getNombre().equalsIgnoreCase(usuario.getNombre())) {
                out.add(a);
            }
        }
        return out;
    }
}

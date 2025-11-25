package com.Project.project.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AccesoUsuario {

    private Usuario usuario;
    private int ip;
    private LocalDateTime fechahora;
    private String dispositivo;

    public AccesoUsuario() {
    }

    public AccesoUsuario(Usuario usuario, int ip, LocalDateTime fechahora, String dispositivo) {
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

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
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


        ACCESOS.add(new AccesoUsuario(this.usuario, this.ip, this.fechahora, this.dispositivo));
    }

    public List<AccesoUsuario> mostrarAccesos() {
        return Collections.unmodifiableList(ACCESOS);
    }

    public List<AccesoUsuario> FiltrarPorUsuario(Usuario usuario) {
        if (usuario == null) return List.of();
        List<AccesoUsuario> out = new ArrayList<>();
        for (AccesoUsuario a : ACCESOS) {
            if (a.getUsuario().getNombre().equalsIgnoreCase(usuario.getNombre())
            ) {
                out.add(a);
            }
        }
        return out;
    }
}

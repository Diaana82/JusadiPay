package com.Project.project.controller;

import com.Project.project.model.Usuario;

public class CuentaRequest {

    private Usuario titular;
    private Double saldo;

    public CuentaRequest() {
    }

    public Usuario getTitular() {
        return titular;
    }

    public void setTitular(Usuario titular) {
        this.titular = titular;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}

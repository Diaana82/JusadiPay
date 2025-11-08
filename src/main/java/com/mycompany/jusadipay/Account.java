package com.mycompany.jusadipay;

public class Account {
    
    private String numCuenta;
    private User titular;
    private double saldo;

    public Account() {
    }

    public Account(String numCuenta, User titular, double saldo) {
        this.numCuenta = numCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }
    
    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public User getTitular() {
        return titular;
    }

    public void setTitular(User titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
}

package com.mycompany.jusadipay.polimorfismo;

import com.mycompany.jusadipay.Account;
import com.mycompany.jusadipay.Transaction;

public class Deposito implements OperacionCuenta{
    private final double monto;
    public Deposito(double monto) { this.monto = monto; }
    @Override
    public Transaction ejecutar(Account cuenta) {
        if (cuenta.depositar(monto)) {
            var movs = cuenta.obtenerMovimientos();
            return movs.get(movs.size() - 1);
        }
        return null;
    }
}

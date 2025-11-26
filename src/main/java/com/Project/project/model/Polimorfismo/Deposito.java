package com.Project.project.model.Polimorfismo;

import com.Project.project.model.Cuenta;
import com.Project.project.model.Transaccion;

public class Deposito implements OperacionCuenta {

    private final double monto;

    public Deposito(double monto) {
        this.monto = monto;
    }

    @Override
    public Transaccion ejecutar(Cuenta cuenta) {

        // Si el depósito fue exitoso
        if (cuenta.depositar(monto)) {
            var movimientos = cuenta.obtenerMovimientos();

            // Retorna el último movimiento realizado
            return movimientos.get(movimientos.size() - 1);
        }

        // Depósito fallido
        return null;
    }
}

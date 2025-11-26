package com.Project.project.model.Polimorfismo;

import com.Project.project.model.Cuenta;
import com.Project.project.model.Transaccion;

public class Retiro implements OperacionCuenta {

    private final double monto;

    public Retiro(double monto) {
        this.monto = monto;
    }

    @Override
    public Transaccion ejecutar(Cuenta cuenta) {

        // Si el retiro fue exitoso
        if (cuenta.retirar(monto)) {
            var movimientos = cuenta.obtenerMovimientos();

            // retorna el último movimiento registrado
            return movimientos.get(movimientos.size() - 1);
        }

        // Retorno cuando el retiro no se pudo realizar
        return null;
    }
}

package com.Project.project.model.Polimorfismo;


import com.Project.project.model.Cuenta;
import com.Project.project.model.Transaccion;

public interface OperacionCuenta {
        Transaccion ejecutar(Cuenta cuenta);
    }


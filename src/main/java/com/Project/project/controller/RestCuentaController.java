package com.Project.project.controller;

import com.Project.project.model.Cuenta;
import com.Project.project.model.Transaccion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/api/cuentas")

public class RestCuentaController {

    private static final List<Cuenta> cuentas = new ArrayList<>();

    // busca
    private Cuenta buscarCuenta(String numCuenta) {
        return cuentas.stream()
                .filter(c -> c.getNumCuenta().equalsIgnoreCase(numCuenta))
                .findFirst()
                .orElse(null);
    }

    //consulta
    @GetMapping("/{num}/saldo")
    public ResponseEntity<?> consultarSaldo(@PathVariable String num) {
        Cuenta cuenta = buscarCuenta(num);
        if (cuenta == null) {
            return ResponseEntity.badRequest().body("La cuenta no existe");
        }
        return ResponseEntity.ok(cuenta.getSaldo());
    }

    //deposita:)
    @PostMapping("/{num}/deposito")
    public ResponseEntity<?> depositar(
            @PathVariable String num,
            @RequestBody Map<String, Double> body
    ) {
        Cuenta cuenta = buscarCuenta(num);
        if (cuenta == null) {
            return ResponseEntity.badRequest().body("La cuenta no existe");
        }

        Double monto = body.get("monto");
        if (monto == null || monto <= 0) {
            return ResponseEntity.badRequest().body("Monto inválido");
        }

        cuenta.depositar(monto);
        return ResponseEntity.ok("Depósito exitoso");
    }
}

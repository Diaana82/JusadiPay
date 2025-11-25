package com.Project.project.model;

import java.time.LocalDateTime;
import java.util.*;

public class Cuenta {

    private String NumCuenta;
    private Usuario Titular;
    private Double Saldo;

    private List<Transaccion> movimientos = new ArrayList<>();
    private List<CodigoDeRetiro> codigosRetiro = new ArrayList<>();
    private Map<String, Double> montoPorCodigo = new HashMap<>();
    private List<Bolsillo> bolsillos = new ArrayList<>();

    // Random que usas en solicitarRetiroCodigo()
    private Random rnd = new Random();

    public Cuenta() {
    }

    public Cuenta(String numCuenta, Usuario titular, Double saldo) {
        NumCuenta = numCuenta;
        Titular = titular;
        Saldo = saldo;
    }

    public String getNumCuenta() {
        return NumCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        NumCuenta = numCuenta;
    }

    public Usuario getTitular() {
        return Titular;
    }

    public void setTitular(Usuario titular) {
        Titular = titular;
    }

    public Double getSaldo() {
        return Saldo;
    }

    public void setSaldo(Double saldo) {
        Saldo = saldo;
    }

    //metodos

    public boolean depositar(double monto) {
        if (monto <= 0) return false;

        this.Saldo += monto;

        Transaccion tx = new Transaccion(
                (int)(System.nanoTime() % Integer.MAX_VALUE),   // idTransaccion
                LocalDateTime.now(),                            // fecha
                monto,                                          // monto
                "COMPLETADA",                                   // estado
                this,                                           // cuentaOrigen
                null,                                           // cuentaDestino
                null                                            // puntoRetiro
        );

        movimientos.add(tx);
        return true;
    }

    public boolean retirar(double monto) {
        if (monto <= 0) return false;
        if (this.Saldo < monto) return false;

        this.Saldo -= monto;

        Transaccion tx = new Transaccion(
                (int)(System.nanoTime() % Integer.MAX_VALUE), // idTransaccion
                LocalDateTime.now(),                          // fecha
                monto,                                        // monto
                "RETIRO",                                     // estado
                this,                                         // cuentaOrigen
                null,                                         // cuentaDestino
                null                                          // puntoRetiro
        );
        movimientos.add(tx);
        return true;
    }

    public Double consultarSaldo() {
        return Saldo;
        }

    public List<Transaccion> obtenerMovimientos() {

        return Collections.unmodifiableList(movimientos);
    }

    public List<Bolsillo> obtenerBolsillos() {

        return Collections.unmodifiableList(bolsillos);
    }

    public CodigoDeRetiro solicitarRetiroCodigo(double monto, String puntoRetiro) {
        if (monto <= 0) return null;
        if (this.Saldo < monto) return null;

        this.Saldo -= monto;

        int codigo = 100000 + rnd.nextInt(900000);

        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime expira = ahora.plusMinutes(30);

        CodigoDeRetiro wc = new CodigoDeRetiro(
                String.valueOf(codigo),  // tu constructor espera String, no int
                ahora,
                expira,
                "PENDIENTE",
                this.Titular
        );

        codigosRetiro.add(wc);
        montoPorCodigo.put(wc.getCodigo(), monto);

        // Registrar transacción pendiente
        Transaccion tx = new Transaccion(
                (int)(System.nanoTime() % Integer.MAX_VALUE), // idTransaccion
                ahora,                                        // fecha
                monto,                                        // monto
                "PENDIENTE",                                  // estado
                this,                                         // cuentaOrigen
                null,                                         // cuentaDestino
                puntoRetiro
        );
        movimientos.add(tx);

        return wc;
    }

    public boolean validarCodigoRetiro(String codigoStr) {
        if (codigoStr == null || codigoStr.isEmpty()) return false;

        int codigo;
        try {
            codigo = Integer.parseInt(codigoStr);
        } catch (NumberFormatException e) {
            return false;
        }

        CodigoDeRetiro match = null;

        for (CodigoDeRetiro wc : codigosRetiro) {
            if (wc.getCodigo().equals(codigoStr)) {   // código ingresado por el usuario
                match = wc;
                break;
            }
        }
        if (match == null) return false;

        LocalDateTime ahora = LocalDateTime.now();

        if (ahora.isAfter(match.getFechaExpiracion())) {
            match.setEstado("EXPIRADO");
            Double reservado = montoPorCodigo.remove(codigo);
            if (reservado != null) this.Saldo += reservado;
            return false;
        }

        if (!"PENDIENTE".equalsIgnoreCase(match.getEstado())) {
            return false;
        }


        match.setEstado("USADO");
        Double monto = montoPorCodigo.remove(codigo);
        if (monto == null) monto = 0.0;

        // Registrar transacción completada
        Transaccion tx = new Transaccion(
                (int)(System.nanoTime() % Integer.MAX_VALUE), // idTransaccion
                ahora,                                        // fecha
                monto,                                        // monto
                "COMPLETADA",                                 // estado
                this,                                         // cuentaOrigen
                null,                                         // cuentaDestino
                null
        );
        movimientos.add(tx);

        return true;
    }
}


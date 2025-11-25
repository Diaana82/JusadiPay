package com.Project.project.model;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class Cuenta {

    private String NumCuenta;
    private Usuario Titular;
    private Double Saldo;

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
                "TX-" + System.nanoTime(),
                LocalDateTime.now(),
                monto,
                "DEPOSITO",
                "COMPLETADA",
                null,
                this,
                null
        );
        movimientos.add(tx);
        return true;
    }

    public boolean retirar(double monto) {
        if (monto <= 0) return false;
        if (this.Saldo < monto) return false;

        this.Saldo -= monto;

        Transaccion tx = new Transaccion(
                "TX-" + System.nanoTime(),
                LocalDateTime.now(),
                monto,
                "RETIRO",
                "COMPLETADA",
                this,              // cuentaOrigen
                null,              // cuentaDestino
                null               // idAcceso
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
        return Collections.unmodifiableList(Bolsillo);
    }

    public CodigoDeRetiro solicitarRetiroCodigo(double monto, String puntoRetiro) {
        if (monto <= 0) return null;
        if (this.Saldo < monto) return null;

        this.Saldo -= monto;

        int codigo = 100000 + rnd.nextInt(900000);

        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime expira = ahora.plusMinutes(30);

        CodigoDeRetiro wc = new CodigoDeRetiro(
                codigo,
                ahora,
                expira,
                "PENDIENTE",
                this.Titular,
                puntoRetiro
        );

        codigosRetiro.add(wc);
        montoPorCodigo.put(codigo, monto);

        // Registrar transacción pendiente
        Transaccion tx = new Transaccion(
                "TX-" + System.nanoTime(),
                ahora,
                monto,
                "RETIRO_CODIGO",
                "PENDIENTE",
                this,
                null,
                null
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
            if (wc.getCodigo() == codigo) {
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
                "TX-" + System.nanoTime(),
                ahora,
                monto,
                "RETIRO_CODIGO",
                "COMPLETADA",
                this,
                null,
                null
        );
        movimientos.add(tx);

        return true;
    }
}


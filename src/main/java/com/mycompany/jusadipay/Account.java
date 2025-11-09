package com.mycompany.jusadipay;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
public class Account {
    
    private String numCuenta;
    private User titular;
    private double saldo;
    
    private final List<Transaction> movimientos = new ArrayList<>();
    private final List<pocket> bolsillos = new ArrayList<>();
    private final List<withdrawalcode> codigosRetiro = new ArrayList<>();
    private final Map<Integer, Double> montoPorCodigo = new HashMap<>();
    private final Random rnd = new Random();

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
    
    //metodos
    
    public boolean depositar(double monto) {
        if (monto <= 0) return false;

        this.saldo += monto;

        Transaction tx = new Transaction(
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
        if (this.saldo < monto) return false;

        this.saldo -= monto;

        Transaction tx = new Transaction(
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
        return saldo;
    }

    public List<Transaction> obtenerMovimientos() {
        return Collections.unmodifiableList(movimientos);
    }

    public List<pocket> obtenerBolsillos() {
        return Collections.unmodifiableList(bolsillos);
    }

    public withdrawalcode solicitarRetiroCodigo(double monto, String puntoRetiro) {
        if (monto <= 0) return null;
        if (this.saldo < monto) return null;

        this.saldo -= monto;

        int codigo = 100000 + rnd.nextInt(900000);

        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime expira = ahora.plusMinutes(30);

        withdrawalcode wc = new withdrawalcode(
            codigo,
            ahora,
            expira,
            "PENDIENTE",
            this.titular, 
            puntoRetiro
        );

        codigosRetiro.add(wc);
        montoPorCodigo.put(codigo, monto);

        // Registrar transacción pendiente
        Transaction tx = new Transaction(
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

        withdrawalcode match = null;
        for (withdrawalcode wc : codigosRetiro) {
            if (wc.getCodigo() == codigo) {
                match = wc;
                break;
            }
        }
        if (match == null) return false;

        LocalDateTime ahora = LocalDateTime.now();

        if (ahora.isAfter(match.getFechaexpiracion())) {
            match.setEstado("EXPIRADO");
            Double reservado = montoPorCodigo.remove(codigo);
            if (reservado != null) this.saldo += reservado;
            return false;
        }

        if (!"PENDIENTE".equalsIgnoreCase(match.getEstado())) {
            return false;
        }


        match.setEstado("USADO");
        Double monto = montoPorCodigo.remove(codigo);
        if (monto == null) monto = 0.0;

        // Registrar transacción completada
        Transaction tx = new Transaction(
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
    


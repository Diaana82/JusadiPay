package com.mycompany.jusadipay;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class JusadiPay {

    public static void main(String[] args) {
        System.out.println("**INICIALIZANDO BILLETERA VIRTUAL**");
        
        //1. crear servicios
        Services servicio1 = new Services();
        Services servicio2 = new Services();
        
        
        //2.crear usuarios
        User sara =new User("Sara", "1054835743", "3005391231", "marin.sara1007","motitas123", 18);
        
        User diana =new User("Diana", "1002799922", "3135374647", "dianitha20","diana123", 21);
        
        try {
            sara.registrarse();
            sara.iniciarSesion();
            diana.registrarse();
            diana.iniciarSesion();
        } catch (Exception ex) {
            System.out.println("Error registro/sesión: " + ex.getMessage());
        }
        
        //3. accesos de usuario
        
        UserAccess accSara = new UserAccess("Sara", "127.0.0.1", LocalDateTime.now(), "Android");
        accSara.registrarAccesos();

        UserAccess accDiana = new UserAccess("Diana", "192.168.1.50", LocalDateTime.now(), "iOS");
        accDiana.registrarAccesos();

        // Mostrar todos los accesos
        System.out.println("=== Accesos registrados ===");
        for (UserAccess a : UserAccess.mostrarAccesos()) {
            System.out.println(a.getUsuario() + " - " + a.getIp() + " - " + a.getFechahora() + " - " + a.getDispositivo());
        }

        // Filtrar por usuario
        System.out.println("=== Accesos de Sara ===");
        for (UserAccess a : UserAccess.FiltrarPorUsuario(sara)) {
            System.out.println(a.getUsuario() + " - " + a.getIp() + " - " + a.getFechahora());
        }
        
        //4. crea cuenta
        Account cuenta1 = new Account("ACC-001", sara, 999000.00);
        Account cuenta2 = new Account("ACC-002", diana, 900000.00);

        //5. Crear bolsillos
        pocket bolsillo1 = new pocket ("Universidad", 3000000.00, 0.0, 0.0, "activo", cuenta1,LocalDateTime.now());
        pocket bolsillo2 = new pocket ("Compras", 500000.00, 85000.00, 0.17, "activo", cuenta2,LocalDateTime.now());
        
        //6. Crear proveedores
        supplier provLuz = new supplier ("Chec", "Luz", LocalDateTime.now());
        supplier provMovil = new supplier ("Tigo", "Telefonia", LocalDateTime.now());
        
        //7. Imprimir saldo actual
        System.out.println("Saldo inicial de Sara: "+cuenta1.getSaldo());
        System.out.println("Saldo inicial de Diana: "+cuenta2.getSaldo());
        
        //operaciones basicas
        //Crear transaccion
        try{
            boolean depositoOk = cuenta1.depositar(800000.00);
            if (depositoOk){
                Transaction transaccion1 = cuenta1.obtenerMovimientos().get(cuenta1.obtenerMovimientos().size() - 1);
                System.out.println("Te depositaron: " + transaccion1.getMonto() + " a tu cuenta");
            }else{
                System.out.println("No se puede hacer el deposito");
            }
            
            boolean retiroOk = cuenta2.retirar(200000.00);
            if (retiroOk){
                Transaction transaccion2 = cuenta2.obtenerMovimientos().get(cuenta2.obtenerMovimientos().size() - 1);
                System.out.println("Retiraste: " + transaccion2.getMonto());
            }else{
                System.out.println("No puedes hacer tu retiro");
            }
            
            double montoTransferir = 250000.00;
            boolean retiroTransferir = cuenta2.retirar(montoTransferir);
            if (retiroTransferir){
                boolean depositarTransferir = cuenta1.depositar(montoTransferir);
                if (depositarTransferir){
                    System.out.println("Transferencia de: " + montoTransferir);
                }else{
                    cuenta2.depositar(montoTransferir);
                    System.out.println("No se puedo realizar la transferencia.");
                }
            }else{
                System.out.println("El valor no esta disponible");
            }
            
            //codigo de retiro 
            
            withdrawalcode codigo = cuenta2.solicitarRetiroCodigo(300.0, "PUNTO-RET");
            if (codigo != null) {
                boolean usado = cuenta2.validarCodigoRetiro(String.valueOf(codigo.getCodigo()));
                if (usado) {
                    Transaction t6 = cuenta2.obtenerMovimientos()
                                                 .get(cuenta2.obtenerMovimientos().size() - 1);
                    System.out.println("Retiro con código: " + t6.getMonto());
                } else {
                    System.out.println("Código de retiro inválido/expirado");
                }
            } else {
                System.out.println("No se pudo generar código de retiro");
            }
            
            //movimientos
            List<Transaction> movsSara = cuenta1.obtenerMovimientos();
                for (Transaction tr : movsSara) {
                    System.out.println(tr.obtenerDetaller()); // o el método de detalle que tengas
                }
            } 
        catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
        }
            System.out.println("Saldo final Sara: " + cuenta1.getSaldo());
            System.out.println("Saldo final Diana: " + cuenta2.getSaldo());
            
    }
    

                
}

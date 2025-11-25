package com.Project.project;

import com.Project.project.model.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.json.GsonBuilderUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

@SpringBootApplication
public class projectApplication {

    System.out.println("**INICIALIZANDO BILLETERA VIRTUAL**");

    //1. crear servicios
    Servicios servicio1 = new Servicios();
    Servicios servicio2 = new Servicios();


    //2.crear usuarios
    Usuario sara =new Usuario("Sara", "1054835743", "3005391231", "marin.sara1007","motitas123", 18);

    Usuario diana =new Usuario("Diana", "1002799922", "3135374647", "dianitha20","diana123", 21);

        try {
        sara.registrarse();
        sara.iniciarSesion();
        diana.registrarse();
        diana.iniciarSesion();
    } catch (Exception ex) {
        System.out.println("Error registro/sesión: " + ex.getMessage());
    }

    //3. accesos de usuario

    AccesoUsuario accSara = new AccesoUsuario("Sara", "127.0.0.1", LocalDateTime.now(), "Android");
        accSara.registrarAccesos();

    AccesoUsuario accDiana = new AccesoUsuario("Diana", "192.168.1.50", LocalDateTime.now(), "iOS");
        accDiana.registrarAccesos();

    // Mostrar todos los accesos
        System.out.println("=== Accesos registrados ===");
        for (AccesoUsuario a : AccesoUsuario.mostrarAccesos()) {
        System.out.println(a.getUsuario() + " - " + a.getIp() + " - " + a.getFechahora() + " - " + a.getDispositivo());
    }

    // Filtrar por usuario
        System.out.println("=== Accesos de Sara ===");
        for (AccesoUsuario a : AccesoUsuario.FiltrarPorUsuario(sara)) {
        System.out.println(a.getUsuario() + " - " + a.getIp() + " - " + a.getFechahora());
    }

    //4. crea cuenta
    Cuenta cuenta1 = new Cuenta("ACC-001", sara, 999000.00);
    Cuenta cuenta2 = new Cuenta("ACC-002", diana, 900000.00);

    List<OperacionCuenta> ops = Arrays.asList(
            new Deposito(150_000.0),
            new Retiro(50_000.0)
    );

            for (OperacionCuenta op : ops) {
        Transaccion t = op.ejecute(cuenta1); // ojo: cuenta1, no 'cuental'
        System.out.println(t != null ? "[OK] " + t.obtenerDetalles() : "[FAIL] Operación");
    }

    //5. Crear bolsillos
    Bolsillo bolsillo1 = new Bolsillo("Universidad", 3000000.00, 0.0, 0.0, "activo", cuenta1,LocalDateTime.now());
    Bolsillo bolsillo2 = new Bolsillo ("Compras", 500000.00, 85000.00, 0.17, "activo", cuenta2,LocalDateTime.now());

    //6. Crear proveedores
    Proveedor provLuz = new Proveedor("Chec", "Luz", LocalDateTime.now());
    Proveedor provMovil = new Proveedor ("Tigo", "Telefonia", LocalDateTime.now());

    //7. Imprimir saldo actual
        System.out.println("Saldo inicial de Sara: "+cuenta1.getSaldo());
        System.out.println("Saldo inicial de Diana: "+cuenta2.getSaldo());

    //operaciones basicas
    //Crear transaccion
        try{
        boolean depositoOk = cuenta1.depositar(800000.00);
        if (depositoOk){
            Transaccion transaccion1 = cuenta1.obtenerMovimientos().get(cuenta1.obtenerMovimientos().size() - 1);
            System.out.println("Te depositaron: " + transaccion1.getMonto() + " a tu cuenta");
        }else{
            System.out.println("No se puede hacer el deposito");
        }

        boolean retiroOk = cuenta2.retirar(200000.00);
        if (retiroOk){
            Transaccion transaccion2 = cuenta2.obtenerMovimientos().get(cuenta2.obtenerMovimientos().size() - 1);
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

        CodigoDeRetiro codigo = cuenta2.solicitarRetiroCodigo(300.0, "PUNTO-RET");
        if (codigo != null) {
            boolean usado = cuenta2.validarCodigoRetiro(String.valueOf(codigo.getCodigo()));
            if (usado) {
                Transaccion t6 = cuenta2.obtenerMovimientos()
                        .get(cuenta2.obtenerMovimientos().size() - 1);
                System.out.println("Retiro con código: " + t6.getMonto());
            } else {
                System.out.println("Código de retiro inválido/expirado");
            }
        } else {
            System.out.println("No se pudo generar código de retiro");
        }

        //movimientos
        List<Transaccion> movsSara = cuenta1.obtenerMovimientos();
        for (Transaccion tr : movsSara) {
            System.out.println(tr.obtenerDetalles()); // o el método de detalle que tengas
        }
    }
        catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
            System.out.println("Saldo final Sara: " + cuenta1.getSaldo());
            System.out.println("Saldo final Diana: " + cuenta2.getSaldo());

}


	public static void main(String[] args) {
		SpringApplication.run(projectApplication.class, args);
	}

}

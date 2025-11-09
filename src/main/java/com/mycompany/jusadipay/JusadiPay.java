package com.mycompany.jusadipay;
import java.time.LocalDateTime;
import java.util.List;

public class JusadiPay {

    public static void main(String[] args) {
        System.out.println("==INICIALIZANDO BILLETERA VIRTUAL");
        
        //1. crear servicios
        Services servicio1 = new Services();
        Services servicio2 = new Services();
        
        
        //2.crear usuarios
        User sara =new User("Sara", "1054835743", "3005391231", "marin.sara1007","motitas123", 18);
        
        User diana =new User("Diana", "1002799922", "3135374647", "dianitha20","diana123", 21);
        
        //crea cuenta
        Account cuenta1 = new Account("ACC-001", sara, 999000.00);
        Account cuenta2 = new Account("ACC-002", diana, 900000.00);

        //Crear bolsillos
        pocket bolsillo1 = new pocket ("Universidad", 3000000.00, 0.0, 0.0, "activo", cuenta1,LocalDateTime.now());
        pocket bolsillo2 = new pocket ("Compras", 500000.00, 85000.00, 0.17, "activo", cuenta2,LocalDateTime.now());
        
        //Crear proveedores
        supplier provLuz = new supplier ("Chec", "Luz", LocalDateTime.now());
        supplier provMovil = new supplier ("Tigo", "Telefonia", LocalDateTime.now());
        
        //Imprimir saldo actual
        System.out.println("Saldo inicial de Sara: "+cuenta1.getSaldo());
        System.out.println("Saldo inicial de Diana: "+cuenta2.getSaldo());
    }
}

package com.mycompany.jusadipay;
import com.mycompany.jusadipay.User;
import com.mycompany.jusadipay.Account;
import com.mycompany.jusadipay.Services;

import java.time.LocalDateTime;
import java.util.List;

public class JusadiPay {

    public static void main(String[] args) {
        System.out.println("==INICIALIZANDO BILLETERA VIRTUAL");
        
        //1. crear servicios
        Services movistar = new Services();
        Services claro = new Services();
        
        
        //2.crear usuarios
        User sara =new User("Sara", "1054835743", "3005391231", "marin.sara1007","motitas123", 18);
        
        User diana =new User("Diana", "1002799922", "3135374647", "dianitha20","diana123", 21);
        
        //crea cuenta
         Account cuenta1 = new Account("ACC-001", sara, 999.000);
         Account cuenta2 = new Account("ACC-002", diana, 900.000);

        
   
        
    }
}
